/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2240;

import static business.blap.NWAL2240.constant.NWAL2240Constant.ROW_NUM_ONE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.TECH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL2240Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/13   Fujitsu         S.Ohki          Create          N/A
 * 2019/11/09   Fujitsu         Mz.Takahashi    Update          QC#53993
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 * 
 *</pre>
 */
public final class NWAL2240Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL2240Query MY_INSTANCE = new NWAL2240Query();

    /**
     * Constructor.
     */
    private NWAL2240Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL2240Query
     */
    public static NWAL2240Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Service Install Rule
     * @param cMsg NWAL2240CMsg
     * @param glblCmpyCd Global Company Code
     * @return Service Install Rule
     */
    public S21SsmEZDResult getSvcIstlRuleList(NWAL2240CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getSvcIstlRuleList", ssmParam);
    }

    /**
     * Search With Configuration Number
     * @param cMsg NWAL2240CMsg
     * @return Result
     */
    public S21SsmEZDResult searchWithConfNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("configCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("searchWithConfNum", ssmParam);
    }

    /**
     * Search With Order Number
     * @param cMsg NWAL2240CMsg
     * @return Result
     */
    public S21SsmEZDResult searchWithOrdNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);

        return getSsmEZDClient().queryObject("searchWithOrdNum", ssmParam);
    }

    /**
     * Get Install Details Info by Configuration Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getIstlDtlWithConfNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("configCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getIstlDtlWithConfNum", ssmParam);
    }

    /**
     * Get Default Install Type
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getDefaultInstTypeWithConfNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("configCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getDefaultInstTypeWithConfNum", ssmParam);
    }

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    /**
     * Get Default Accessory Install Type
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getDefaultAccessoryInstTypeWithConfNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);
        List<String> configTpCdList = new ArrayList<String>();
        configTpCdList.add(CONFIG_TP.ADD_TO_CONFIG);
        ssmParam.put("configTpCdList", configTpCdList);
        ssmParam.put("imptLineFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getDefaultAccessoryInstTypeWithConfNum", ssmParam);
    }
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    /**
     * Get Install Details Info by Order Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getIstlDtlWithOrdNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);

        return getSsmEZDClient().queryObject("getIstlDtlWithOrdNum", ssmParam);
    }

    /**
     * Get Delivery Details Info by Configuration Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getDeliveryDtlWithConfNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("configCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getDeliveryDtlWithConfNum", ssmParam);
    }

    /**
     * Get Delivery Details Info by Order Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getDeliveryDtlWithOrdNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);

        return getSsmEZDClient().queryObject("getDeliveryDtlWithOrdNum", ssmParam);
    }

    /**
     * Get DS_CPO Info
     * @param cMsg NWAL2240CMsg
     * @return CPO
     */
    public S21SsmEZDResult getDsImptOrd(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);

        return getSsmEZDClient().queryObject("getDsImptOrd", ssmParam);
    }

    /**
     * Get Technician Info
     * @param cMsg NWAL2240CMsg
     * @return TECH_MSTR
     */
    public TECH_MSTRTMsg getTechMstr(NWAL2240CMsg cMsg) {
        TECH_MSTRTMsg tMsg = new TECH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.techTocCd, cMsg.istlTechCd_DI);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);

        return (TECH_MSTRTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    /**
     * Get Site Survey by Configuration Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getSiteSurveybyConfNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("configCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getSiteSurveybyConfNum", ssmParam);
    }

    /**
     * Get Site Survey by Order Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getSiteSurveybyOrdNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);

        return getSsmEZDClient().queryObject("getSiteSurveybyOrdNum", ssmParam);
    }

    /**
     * Get Material Info by Configuration Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getMaterialInfobyConfNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("configCatgCd", cMsg.configCatgCd_H0);
        ssmParam.put("pkgUomCd", PKG_UOM.EACH);

        return getSsmEZDClient().queryEZDMsgArray("getMaterialInfobyConfNum", ssmParam, cMsg.B);
    }

    /**
     * Get Material Return Info by Configuration Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getMaterialRtnInfobyConfNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("configCatgCd", cMsg.configCatgCd_H0);
        ssmParam.put("pkgUomCd", PKG_UOM.EACH);

        return getSsmEZDClient().queryEZDMsgArray("getMaterialRtnInfobyConfNum", ssmParam, cMsg.B);
    }

    /**
     * Get Material Info by Order Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getMaterialInfobyOrdNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("pkgUomCd", PKG_UOM.EACH);

        return getSsmEZDClient().queryEZDMsgArray("getMaterialInfobyOrdNum", ssmParam, cMsg.B);
    }

    /**
     * Get Contact List by Configuration Number
     * @param cMsg NWAL2240CMsg
     * @param sMsg NWAL2240SMsg
     * @return result
     */
    public S21SsmEZDResult getCtacListbyConfNum(NWAL2240CMsg cMsg, NWAL2240SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("configCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryEZDMsgArray("getCtacListbyConfNum", ssmParam, sMsg.C);
    }

    /**
     * Get Contact List by Order Number
     * @param cMsg NWAL2240CMsg
     * @param sMsg NWAL2240SMsg
     * @return result
     */
    public S21SsmEZDResult getCtacListbyOrdNum(NWAL2240CMsg cMsg, NWAL2240SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);

        return getSsmEZDClient().queryEZDMsgArray("getCtacListbyOrdNum", ssmParam, sMsg.C);
    }

    /**
     * Get Configuration Number
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getConfigurationNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("configCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getConfigurationNum", ssmParam);
    }

    /**
     * Get Count Order By Status
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getCountImptOrderByStatus(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", cMsg.ordSrcRefNum_H0);

        List<String> stsCdList = new ArrayList<String>();
        stsCdList.add(IMPT_STS.SUCCESS);
        stsCdList.add(IMPT_STS.REJECT);
        ssmParam.put("stsCdList", stsCdList);

        return getSsmEZDClient().queryObject("getCountImptOrderByStatus", ssmParam);
    }

    /**
     * Get Ship To Cust
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getShipToCust(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("shipToCustCd", cMsg.shipToCustLocCd_H0.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rowNum", ROW_NUM_ONE);

        return getSsmEZDClient().queryObject("getShipToCust", ssmParam);
    }

    /**
     * Get Location Num
     * @param cMsg NWAL2240CMsg
     * @return result
     */
    public S21SsmEZDResult getLocNum(NWAL2240CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("shipToCustCd", cMsg.shipToCustLocCd_H0);
        ssmParam.put("rowNum", ROW_NUM_ONE);

        return getSsmEZDClient().queryObject("getLocNum", ssmParam);
    }

    // 2019/11/09 QC#53993 Add Start
    /**
     * Get Service Provide Party Pull Down
     * @param bizMsg NWAL2240CMsg
     * @param toBeIstlOrSvcPrvdByFlg true:toBeIstlByFlg='Y' false:svcPrvdByFlg='Y'
     * @param glblCmpyCd Global Company Code
     * @return Result
     */
    public S21SsmEZDResult getSvcPrvdPty(NWAL2240CMsg bizMsg, boolean toBeIstlOrSvcPrvdByFlg, String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        if (toBeIstlOrSvcPrvdByFlg) {
            params.put("toBeIstlByFlg", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("svcPrvdByFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryObjectList("getSvcPrvdPty", params);

    }
    // 2019/11/09 QC#53993 Add End

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /**
     * Get Deinstall Information
     * @param cMsg NWAL2240CMsg
     * @return Result
     */
    public S21SsmEZDResult getDeinstallInfo(NWAL2240CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd);
        ssmParam.put("ordSrcRefNum", bizMsg.ordSrcRefNum_H0);
        ssmParam.put("dsOrdPosnNum", bizMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", bizMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getDeinstallInfo", ssmParam);
    }

    /**
     * Get Model Service Deinstall Rule Number
     * @param String glblCmpyCd
     * @param BigDecimal dsImptOrdConfigPk
     * @param BigDecimal svcMachMstrPk
     * @return Result
     */
    public S21SsmEZDResult getMdlSvcDeinsRuleNum(String glblCmpyCd, BigDecimal dsImptOrdConfigPk, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsImptOrdConfigPk", dsImptOrdConfigPk);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);

        return getSsmEZDClient().queryObject("getSvcDeinsRuleNum", ssmParam);
    }

    /**
     * Get Mdse Service Deinstall Rule Number
     * @param String glblCmpyCd
     * @param BigDecimal dsImptOrdConfigPk
     * @return Result
     */
    public S21SsmEZDResult getMdseSvcDeinsRuleNum(String glblCmpyCd, BigDecimal dsImptOrdConfigPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsImptOrdConfigPk", dsImptOrdConfigPk);

        return getSsmEZDClient().queryObject("getSvcDeinsRuleNum", ssmParam);
    }
    // END 2023/12/12 K.Watanabe [QC#61300, ADD]
}
