/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0990;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CTRYTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Supply Order
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         K.Kasai         Create          N/A
 * 2016/03/23   Hitachi         A.Kohinata      Update          QC#5730
 * 2016/04/04   Hitachi         K.Kasai         Update          QC#6490
 * 2016/10/04   Hitachi         A.Kohinata      Update          QC#12898
 *</pre>
 */

public final class NSAL0990QueryForDsCPOUpdate extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL0990QueryForDsCPOUpdate MY_INSTANCE = new NSAL0990QueryForDsCPOUpdate();

    /**
     * Constructor.
     */
    private NSAL0990QueryForDsCPOUpdate() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0990Query
     */
    public static NSAL0990QueryForDsCPOUpdate getInstance() {
        return MY_INSTANCE;
    }

    /**
     * find DS Order Type
     * @param bizMsg NSAL0990CMsg
     * @return DS_ORD_TPTMsg
     */
    public DS_ORD_TPTMsg findDsOrdTp(NSAL0990CMsg bizMsg) {
        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
        setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
        return (DS_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * find Ship To Customer
     * @param bizMsg NSAL0990CMsg
     * @return SHIP_TO_CUSTTMsg
     */
    public SHIP_TO_CUSTTMsg findShipToCust(NSAL0990CMsg bizMsg) {
        SHIP_TO_CUSTTMsg tMsg = new SHIP_TO_CUSTTMsg();
        tMsg.setSQLID("004");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("shipToCustCd01", bizMsg.curLocNum.getValue());
        SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() > 0) {
            return (SHIP_TO_CUSTTMsg) tMsgArray.get(0);
        }
        return null;
    }

    /**
     * find Bill To Customer
     * @param bizMsg NSAL0990CMsg
     * @return BILL_TO_CUSTTMsg
     */
    public BILL_TO_CUSTTMsg findBillToCust(NSAL0990CMsg bizMsg) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("billToCustCd01", bizMsg.ownrLocNum.getValue());
        BILL_TO_CUSTTMsgArray tMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() > 0) {
            return (BILL_TO_CUSTTMsg) tMsgArray.get(0);
        }
        return null;
    }

    /**
     * find Country
     * @param bizMsg NSAL0990CMsg
     * @param ctryCd String
     * @return CTRYTMsg
     */
    public CTRYTMsg findCtry(NSAL0990CMsg bizMsg, String ctryCd) {
        CTRYTMsg tMsg = new CTRYTMsg();
        setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        setValue(tMsg.ctryCd, ctryCd);
        return (CTRYTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * find Service Configuration Master
     * @param bizMsg NSAL0990CMsg
     * @param svcConfigMstrPk BigDecimal
     * @return SVC_CONFIG_MSTRTMsg
     */
    public SVC_CONFIG_MSTRTMsg findSvcConfigMstr(NSAL0990CMsg bizMsg, BigDecimal svcConfigMstrPk) {
        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
        setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        setValue(tMsg.svcConfigMstrPk, svcConfigMstrPk);
        return (SVC_CONFIG_MSTRTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * find Service Machine Master
     * @param bizMsg NSAL0990CMsg
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    public SVC_MACH_MSTRTMsg findSvcMachMstr(NSAL0990CMsg bizMsg, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * find DS Model
     * @param bizMsg NSAL0990CMsg
     * @param mdlId BigDecimal
     * @return DS_MDLTMsg
     */
    public DS_MDLTMsg findDsMdl(NSAL0990CMsg bizMsg, BigDecimal mdlId) {
        DS_MDLTMsg tMsg = new DS_MDLTMsg();
        setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        setValue(tMsg.mdlId, mdlId);
        return (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * find Line Business Role Type
     * @param bizMsg NSAL0990CMsg
     * @param lineBizTpCd String
     * @return LINE_BIZ_ROLE_TPTMsg
     */
    public LINE_BIZ_ROLE_TPTMsg findLineBizRoleTp(NSAL0990CMsg bizMsg, String lineBizTpCd) {
        LINE_BIZ_ROLE_TPTMsg tMsg = new LINE_BIZ_ROLE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizRoleTpCd, lineBizTpCd);

        return (LINE_BIZ_ROLE_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
    }

    /**
     * get DsAcpo
     * @param glblCmpyCd String
     * @param dsContrNum String
     * @param svcMachMstrPk BigDecimal
     * @return String
     */
    public String getDsAcpo(String glblCmpyCd, String dsContrNum, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrNum", dsContrNum);
        param.put("svcMachMstrPk", svcMachMstrPk);
        // add start 2016/10/04 CSA Defect#12898
        param.put("dsContrCatgFleet", DS_CONTR_CATG.FLEET);
        param.put("dsContrDtlTpFleet", DS_CONTR_DTL_TP.FLEET);
        // add end 2016/10/04 CSA Defect#12898

        return (String) getSsmEZDClient().queryObject("getDsAcpo", param).getResultObject();
    }

    /**
     * get DsAcpo Dtl
     * @param glblCmpyCd String
     * @param dsContrNum String
     * @param svcMachMstrPk BigDecimal
     * @param mdseCd String
     * @return BigDecimal
     */
    public BigDecimal getDsAcpoDtl(String glblCmpyCd, String dsContrNum, BigDecimal svcMachMstrPk, String mdseCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrNum", dsContrNum);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("mdseCd", mdseCd);
        // add start 2016/10/04 CSA Defect#12898
        param.put("dsContrCatgFleet", DS_CONTR_CATG.FLEET);
        param.put("dsContrDtlTpFleet", DS_CONTR_DTL_TP.FLEET);
        // add end 2016/10/04 CSA Defect#12898

        return (BigDecimal) getSsmEZDClient().queryObject("getDsAcpoDtl", param).getResultObject();
    }

    /**
     * get Next DsAcpoTrgtLineNum
     * @param glblCmpyCd String
     * @param dsAcpoNum String
     * @return String
     */
    public String getNextDsAcpoTrgtLineNum(String glblCmpyCd, String dsAcpoNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsAcpoNum", dsAcpoNum);
        params.put("dsAcpoTrgtLineNum", BigDecimal.ONE);

        return (String) getSsmEZDClient().queryObject("getNextDsAcpoTrgtLineNum", params).getResultObject();
    }
}
