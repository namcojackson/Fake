/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1300;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL1300.constant.NSAL1300Constant.PAST_MONTHS;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Usage Meter Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/01   Hitachi         N.Arai          Create          N/A
 * 2017/06/21   Hitachi         K.Kishimoto     Update          QC#19423
 * 2018/05/14   Hitachi         K.Kojima        Update          QC#24817
 *</pre>
 */
public final class NSAL1300Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1300Query INSTANCE = new NSAL1300Query();

    /**
     * Constructor.
     */
    private NSAL1300Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1300Query
     */
    public static NSAL1300Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSvcCntrBllg
     * @param cMsg NSAL1300CMsg
     * @return SVC_CONTR_BLLGTMsg
     */
    public SVC_CONTR_BLLGTMsg getSvcCntrBllg(NSAL1300CMsg cMsg){
        SVC_CONTR_BLLGTMsg svcContrBllgTMsg = new SVC_CONTR_BLLGTMsg();
        setValue(svcContrBllgTMsg.glblCmpyCd, this.getGlobalCompanyCode());
        setValue(svcContrBllgTMsg.svcContrBllgPk, cMsg.svcContrBllgPk);
        return (SVC_CONTR_BLLGTMsg) EZDTBLAccessor.findByKey(svcContrBllgTMsg);
        
    }

    /**
     * getUsageMeterData
     * @param cMsg NSAL1300CMsg
     * @param sCBTMsg SVC_CONTR_BLLGTMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getUsageMeterData(NSAL1300CMsg cMsg, SVC_CONTR_BLLGTMsg sCBTMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd" , this.getGlobalCompanyCode());
        params.put("billToCustCd", sCBTMsg.billToCustCd.getValue());
        params.put("svcContrBllgThruDt", sCBTMsg.svcContrBllgThruDt.getValue());

        // Mod Start 06/21/2017 <QC#19423>
        if (DS_CONTR_CATG.FLEET.equals(sCBTMsg.dsContrCatgCd.getValue())) {
            params.put("dsContrPk" , sCBTMsg.dsContrPk.getValue());
            params.put("fleetCalcFlg" , ZYPConstant.FLG_ON_Y);
        } else {
            params.put("svcMachMstrPk" , sCBTMsg.svcMachMstrPk.getValue());
        }
        // Mod End   06/21/2017 <QC#19423>
        
        return getSsmEZDClient().queryEZDMsgArray("getUsageMeterData", params, cMsg.A);
    }

    /**
     * getFleetUsageMeterDetail
     * @param cMsg NSAL1300CMsg
     * @param sMsg NSAL1300SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getFleetUsageMeterDetail(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getFleetUsageMeterDetail", setBDtlParam(cMsg, cnt), sMsg.B);
    }

    /**
     * getNotFleetUsageMeterDetail
     * @param cMsg NSAL1300CMsg
     * @param sMsg NSAL1300SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNotFleetUsageMeterDetail(NSAL1300CMsg cMsg, NSAL1300SMsg sMsg, int cnt) {
        return getSsmEZDClient().queryEZDMsgArray("getNotFleetUsageMeterDetail", setBDtlParam(cMsg, cnt), sMsg.B);
    }

    /**
     * setBDtlParam
     * @param cMsg NSAL1300CMsg
     * @param cnt int
     * @return Map<String, Object>
     */
    public Map<String, Object> setBDtlParam(NSAL1300CMsg cMsg, int cnt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd" , this.getGlobalCompanyCode());
        params.put("svcContrBllgPk", cMsg.svcContrBllgPk.getValue());
        params.put("flterCounterName", cMsg.mtrLbDescTxt_FN.getValue());
        params.put("flterCounterType", cMsg.mtrLbDescTxt_FT.getValue());
        params.put("flterSerial", cMsg.serNum_F.getValue());
        params.put("pastMonths", PAST_MONTHS);
        params.put("limitCnt", cnt);

        return params;
    }

    /**
     * getPastSvcPhysMtrRead
     * @param bSMsg NSAL1300_BSMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPastSvcPhysMtrRead(NSAL1300_BSMsg bSMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd" , this.getGlobalCompanyCode());
        params.put("svcMachMstrPk", bSMsg.svcMachMstrPk_B.getValue());
        params.put("mtrLbCd", bSMsg.mtrLbCd_B.getValue());
        params.put("dsMtrReadTpGrp", DS_MTR_READ_TP_GRP.BILLABLE_READS);
        params.put("mtrReadDtFr", bSMsg.mtrReadDt_BS.getValue());
        params.put("mtrReadDtTo", bSMsg.mtrReadDt_BL.getValue());

        return getSsmEZDClient().queryObject("getPastSvcPhysMtrRead", params);
    }

    /**
     * getSvcPhysMtrRead
     * @param svcPMRGrpSq BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPhysMtrRead(BigDecimal svcPMRGrpSq) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd" , this.getGlobalCompanyCode());
        params.put("svcPhysMtrReadGrpSq", svcPMRGrpSq);

        return getSsmEZDClient().queryObjectList("getSvcPhysMtrRead", params);
    }

    // START 2018/05/14 K.Kojima [QC#24817,ADD]
    /**
     * getSvcPhysMtrReadForMeterEntry
     * @param svcPMRGrpSq BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPhysMtrReadForMeterEntry(BigDecimal svcPMRGrpSq) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd" , this.getGlobalCompanyCode());
        params.put("svcPhysMtrReadGrpSq", svcPMRGrpSq);

        return getSsmEZDClient().queryObjectList("getSvcPhysMtrReadForMeterEntry", params);
    }
    // END 2018/05/14 K.Kojima [QC#24817,ADD]

    /**
     * getShortageSvcPhysMtr
     * @param bSMsg BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcPhysMtrReadGrpSq(NSAL1300_BSMsg bSMsg) {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("glblCmpyCd" , this.getGlobalCompanyCode());
        params.put("svcMachMstrPk", bSMsg.svcMachMstrPk_B.getValue());
        params.put("svcPhysMtrPk", bSMsg.svcPhysMtrPk_B);
        params.put("startReadDt", bSMsg.mtrReadDt_BL);
        params.put("endReadDt", bSMsg.mtrReadDt_B);
        params.put("startReadMtrCnt", bSMsg.readMtrCnt_BL);
        params.put("newReadMtrCnt", bSMsg.readMtrCnt_BN);

        return getSsmEZDClient().queryObjectList("getSvcPhysMtrReadGrpSq", params);
    }

    /**
     * updateSvcPhysMtrReadQuery
     * @param svcPhysMtrRPk
     * @return String
     */
    public String updateSvcPhysMtrReadQuery(BigDecimal svcPhysMtrRPk) {
        SVC_PHYS_MTR_READTMsg inTMsg = new SVC_PHYS_MTR_READTMsg();

        setValue(inTMsg.glblCmpyCd, this.getGlobalCompanyCode());
        setValue(inTMsg.svcPhysMtrReadPk, svcPhysMtrRPk);

        SVC_PHYS_MTR_READTMsg updTMsg = (SVC_PHYS_MTR_READTMsg) S21FastTBLAccessor.findByKeyForUpdate(inTMsg);

        setValue(updTMsg.vldMtrFlg, ZYPConstant.FLG_OFF_N);

        S21FastTBLAccessor.update(updTMsg);
        return updTMsg.getReturnCode();
    }

}
