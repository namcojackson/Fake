/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1510;

import static business.blap.NWAL1510.constant.NWAL1510Constant.ROW_NUM_ONE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.db.CPOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/27   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/04   Fujitsu         T.ishii         Update          S21_NA#5339
 * 2016/08/02   Fujitsu         M.Hara          Update          S21_NA#7306
 * 2017/02/09   Fujitsu         M.Ohno          Update          S21_NA#17500
 * 2017/03/01   Fujitsu         S.Ohki          Update          S21_NA#17807
 * 2017/06/28   Fujitsu         M.Yamada        Update          S21_NA#19610
 * 2017/07/31   Fujitsu         S.Ohki          Update          S21_NA#19999
 * 2017/08/08   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2017/09/13   Fujitsu         S.Takami        Update          S21_NA#21069
 * 2018/01/18   CITS            T.Hakodate      Update          S21_NA#18460(Sol#087)
 * 2018/03/26   Fujitsu         T.Aoi           Update          S21_NA#22954
 * 2018/07/23   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 * 2019/02/15   Fujitsu         M.Ohno          Update          S21_NA#30343
 * 2019/10/25   Fujitsu         Mz.Takahashi    Update          S21_NA#53993
 * 2023/09/05   Hitachi         K.Watanabe      Update          S21_QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          S21_QC#61300
 * 
 *</pre>
 */
public final class NWAL1510Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1510Query MY_INSTANCE = new NWAL1510Query();

    /**
     * Constructor.
     */
    private NWAL1510Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1510Query
     */
    public static NWAL1510Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Service Install Rule
     * @param cMsg NWAL1510CMsg
     * @param glblCmpyCd Global Company Code
     * @return Service Install Rule
     */
    public S21SsmEZDResult getSvcIstlRuleList(NWAL1510CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getSvcIstlRuleList", ssmParam);
    }

    // S21_NA#7306
    /**
     * getCtacPsnTpPulldown
     * @param cMsg NWAL1510CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacPsnTpPulldown(NWAL1510CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getCtacPsnTpList", ssmParam);
    }

    
    /**
     * Search With Configuration Number
     * @param cMsg NWAL1510CMsg
     * @return Result
     */
    public S21SsmEZDResult searchWithConfNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("searchWithConfNum", ssmParam);
    }

    /**
     * Search With Order Number
     * @param cMsg NWAL1510CMsg
     * @return Result
     */
    public S21SsmEZDResult searchWithOrdNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);

        return getSsmEZDClient().queryObject("searchWithOrdNum", ssmParam);
    }

    /**
     * Get Install Details Info by Configuration Number
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getIstlDtlWithConfNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getIstlDtlWithConfNum", ssmParam);
    }

    /**
     * Get Default Install Type
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getDefaultInstTypeWithConfNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getDefaultInstTypeWithConfNum", ssmParam);
    }

    // START 2023/09/05 K.Watanabe [QC#53408, ADD]
    /**
     * Get Default Accessory Install Type
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getDefaultAccessoryInstTypeWithConfNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);
        List<String> configTpCdList = new ArrayList<String>();
        configTpCdList.add(CONFIG_TP.ADD_TO_CONFIG);
        configTpCdList.add(CONFIG_TP.SERVICE_EXCHANGE);
        ssmParam.put("configTpCdList", configTpCdList);
        ssmParam.put("baseCmptFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);

        return getSsmEZDClient().queryObject("getDefaultAccessoryInstTypeWithConfNum", ssmParam);
    }
    // END 2023/09/05 K.Watanabe [QC#53408, ADD]

    /**
     * Get Install Details Info by Order Number
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getIstlDtlWithOrdNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);

        return getSsmEZDClient().queryObject("getIstlDtlWithOrdNum", ssmParam);
    }

    /**
     * Get Delivery Details Info by Configuration Number
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getDeliveryDtlWithConfNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getDeliveryDtlWithConfNum", ssmParam);
    }

    /**
     * Get Delivery Details Info by Order Number
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getDeliveryDtlWithOrdNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);

        return getSsmEZDClient().queryObject("getDeliveryDtlWithOrdNum", ssmParam);
    }

    /**
     * Get DS_CPO Info
     * @param cMsg NWAL1510CMsg
     * @return CPO
     */
    public CPOTMsg getCpo(NWAL1510CMsg cMsg) {
        CPOTMsg tMsg = new CPOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdNum, cMsg.cpoOrdNum_H0);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);

        return (CPOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

//    /**
//     * QC#19610
//     * Get Technician Info
//     * @param cMsg NWAL1510CMsg
//     * @return TECH_MSTR
//     */
//    public TECH_MSTRTMsg getTechMstr(NWAL1510CMsg cMsg) {
//        TECH_MSTRTMsg tMsg = new TECH_MSTRTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.techTocCd, cMsg.istlTechCd_DI);
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
//
//        return (TECH_MSTRTMsg) S21FastTBLAccessor.findByKey(tMsg);
//    }

    /**
     * QC#19610
     * Get Technician Info
     * @param cMsg NWAL1510CMsg
     * @return TECH_MSTR
     */
    public S21SsmEZDResult getTechNm(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("techCd", cMsg.istlTechCd_DI);
        ssmParam.put("effDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObject("getTechNm", ssmParam);
    }

    /**
     * Get Install Detail List by Configuration Number
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getInstDtlListbyConfNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        // START 2023/12/12 K.Watanabe [QC#61300, MOD]
        //ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0.getValue());
        ssmParam.put("reqFlg", ZYPConstant.FLG_ON_Y);
        // END   2023/12/12 K.Watanabe [QC#61300, MOD]

        return getSsmEZDClient().queryObjectList("getInstDtlListbyConfNum", ssmParam);
    }

    /**
     * Get Install Detail List by Order Number
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getInstDtlListbyOrdNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);

        return getSsmEZDClient().queryObjectList("getInstDtlListbyOrdNum", ssmParam);
    }

    /**
     * Get Site Survey by Configuration Number
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getSiteSurveybyConfNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getSiteSurveybyConfNum", ssmParam);
    }

    /**
     * Get Site Survey by Order Number
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getSiteSurveybyOrdNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);

        return getSsmEZDClient().queryObject("getSiteSurveybyOrdNum", ssmParam);
    }

    /**
     * Get Material Info by Configuration Number
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     * @return result
     */
    public S21SsmEZDResult getMaterialInfobyConfNum(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);
        ssmParam.put("pkgUomCd", PKG_UOM.EACH);

        return getSsmEZDClient().queryEZDMsgArray("getMaterialInfobyConfNum", ssmParam, sMsg.B);
    }

    /**
     * Get Material Return Info by Configuration Number
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     * @return result
     */
    public S21SsmEZDResult getMaterialRtnInfobyConfNum(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);
        ssmParam.put("pkgUomCd", PKG_UOM.EACH);

        return getSsmEZDClient().queryEZDMsgArray("getMaterialRtnInfobyConfNum", ssmParam, sMsg.B);
    }

    /**
     * Get Material Info by Order Number
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     * @return result
     */
    public S21SsmEZDResult getMaterialInfobyOrdNum(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("pkgUomCd", PKG_UOM.EACH);

        return getSsmEZDClient().queryEZDMsgArray("getMaterialInfobyOrdNum", ssmParam, sMsg.B);
    }

    // 2018/03/26 QC#22954 Add Start
    /**
     * Get Material Return Info by Order Number
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     * @return result
     */
    public S21SsmEZDResult getMaterialRtrnInfobyOrdNum(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("pkgUomCd", PKG_UOM.EACH);

        return getSsmEZDClient().queryEZDMsgArray("getMaterialRtrnInfobyOrdNum", ssmParam, sMsg.B);
    }
    // 2018/03/26 QC#22954 Add End

    /**
     * get DS_CPO_CTAC_PSN regist record number including cancel
     * record
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult isFirstTimeRegistOfDsCpoCtacPsn(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsCpoConfigPk", cMsg.dsCpoConfigPk_H0);

        return getSsmEZDClient().queryObject("countDsCpoCtacPsnRecordNum", ssmParam);
    }

    /**
     * Get Contact List by Configuration Number
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     * @return result
     */
    public S21SsmEZDResult getCtacListbyConfNum(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryEZDMsgArray("getCtacListbyConfNum", ssmParam, sMsg.C);
    }
    
    // 2018/07/23 S21_NA#26188 Add Start
    /**
     * Get Contact List by Configuration Number
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     * @return result
     */
    public S21SsmEZDResult getCtacDtlbyConfNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObjectList("getCtacDtlbyConfNum", ssmParam);
    }
    // 2018/07/23 S21_NA#26188 Add End
    
    /**
     * Get Delete Contact List by Configuration Number
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     * @return result
     */
    public S21SsmEZDResult getDeleteCtacListbyConfNum(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryEZDMsgArray("getDeleteCtacListbyConfNum", ssmParam, sMsg.D);
    }

    /**
     * Get Contact List by Order Number
     * @param cMsg NWAL1510CMsg
     * @param sMsg NWAL1510SMsg
     * @return result
     */
    public S21SsmEZDResult getCtacListbyOrdNum(NWAL1510CMsg cMsg, NWAL1510SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);

        return getSsmEZDClient().queryEZDMsgArray("getCtacListbyOrdNum", ssmParam, sMsg.C);
    }

    /**
     * Get Configuration Number
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getConfigurationNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getConfigurationNum", ssmParam);
    }

    /**
     * Get Count CpoDtl Opened
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getCountCpoDtlOpened(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);
        ssmParam.put("openFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getCountCpoDtlOpened", ssmParam);
    }

    /**
     * Get Count CpoRtnDtl Opened
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getCountCpoRtnDtlOpened(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);
        ssmParam.put("openFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("getCountCpoRtnDtlOpened", ssmParam);
    }

    /**
     * Get Count Order Opened
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getCountOrderOpened(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);

        List<String> ordHdrStsCdList = new ArrayList<String>();
        ordHdrStsCdList.add(ORD_HDR_STS.CLOSED);
        ordHdrStsCdList.add(ORD_HDR_STS.CANCELLED);
        ssmParam.put("ordHdrStsCdList", ordHdrStsCdList);

        return getSsmEZDClient().queryObject("getCountOrderOpened", ssmParam);
    }

    /**
     * Get Count Outbound Allocation
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getCountOutboundAllocation(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("ordLineStsCd", ORD_LINE_STS.CANCELLED);
        ssmParam.put("invtyCtrlFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("shpgStsCd", SHPG_STS.CANCELLED);
        ssmParam.put("validated", SHPG_STS.VALIDATED);
        ssmParam.put("poCancelled", SHPG_STS.P_OR_O_CANCELLED);
//        ssmParam.put("avalSoQty", QTY_ZERO);
//        ssmParam.put("avalPoQty", QTY_ZERO);
        // S21_NA#5339 start
        // ssmParam.put("rtlWhCatgCd", RTL_WH_CATG_CD_DUMMY);
        String crAndBillOnlyDummyWhCd = ZYPCodeDataUtil.getVarCharConstValue("CR_AND_BILL_ONLY_DUMMY_WH_CD", cMsg.glblCmpyCd.getValue());
        if (S21StringUtil.isNotEmpty(crAndBillOnlyDummyWhCd)) {
            List<String> crAndBillOnlyDummyWhCdList = S21StringUtil.toList(crAndBillOnlyDummyWhCd);
            ssmParam.put("rtlWhCdList", crAndBillOnlyDummyWhCdList.toArray(new String[0]));
        }
        // S21_NA#5339 end

//        if (ZYPCommonFunc.hasValue(cMsg.dsOrdPosnNum_H0)) {
//            ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
//            ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);
//        }
        List<String> dsOrdPosnNumList = new ArrayList<String>();
        for (int i = 0; i < cMsg.H.getValidCount(); i++) {
            dsOrdPosnNumList.add(cMsg.H.no(i).dsOrdPosnNum_H1.getValue());
        }
        ssmParam.put("dsOrdPosnNumList", dsOrdPosnNumList);
        ssmParam.put("ConfigCatgCd", CONFIG_CATG.OUTBOUND);

        return getSsmEZDClient().queryObject("getCountOutboundAllocation", ssmParam);
    }

    /**
     * Get Count Inbound Allocation
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getCountInboundAllocation(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", cMsg.cpoOrdNum_H0);
        ssmParam.put("invtyCtrlFlg", ZYPConstant.FLG_ON_Y);

        List<String> rtrnLineStsCdList = new ArrayList<String>();
        rtrnLineStsCdList.add(RTRN_LINE_STS.ENTERED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.BOOKED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.RWS_CANCELLED);
        // 2019/02/15 QC#30343 Add Start
        rtrnLineStsCdList.add(RTRN_LINE_STS.RWS_CREATED);
        rtrnLineStsCdList.add(RTRN_LINE_STS.PARTIALLY_RECEIVED);
        // 2019/02/15 QC#30343 Add End
        ssmParam.put("rtrnLineStsCdList", rtrnLineStsCdList);

//        if (ZYPCommonFunc.hasValue(cMsg.dsOrdPosnNum_H0)) {
//            ssmParam.put("dsOrdPosnNum", cMsg.dsOrdPosnNum_H0);
//            ssmParam.put("ConfigCatgCd", cMsg.configCatgCd_H0);
//        }
        List<String> dsOrdPosnNumList = new ArrayList<String>();
        for (int i = 0; i < cMsg.H.getValidCount(); i++) {
            dsOrdPosnNumList.add(cMsg.H.no(i).dsOrdPosnNum_H1.getValue());
        }
        ssmParam.put("dsOrdPosnNumList", dsOrdPosnNumList);
        ssmParam.put("ConfigCatgCd", CONFIG_CATG.INBOUND);

        return getSsmEZDClient().queryObject("getCountInboundAllocation", ssmParam);
    }

    /**
     * Get Ship To Cust
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getShipToCust(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("shipToCustCd", cMsg.shipToCustLocCd_H0.getValue());
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rowNum", ROW_NUM_ONE);

        return getSsmEZDClient().queryObject("getShipToCust", ssmParam);
    }
    
    /**
     * Get Location Num
     * @param cMsg NWAL1510CMsg
     * @return result
     */
    public S21SsmEZDResult getLocNum(NWAL1510CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("shipToCustCd", cMsg.shipToCustLocCd_H0);
        ssmParam.put("rowNum", ROW_NUM_ONE);

        return getSsmEZDClient().queryObject("getLocNum", ssmParam);
    }

    /**
     * S21_NA#7626
     * getOrdLineCnt
     * @param bizMsg    NWAL1510CMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdConfigCnt(NWAL1510CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", bizMsg.cpoOrdNum_H0);

        return getSsmEZDClient().queryObject("getOrdConfigCnt", ssmParam);
    }

    /**
     * Get Ds Contact Person Information
     * @param cMsg NWAL1510CMsg
     * @param ctacPsnPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCtacPntInfo(NWAL1510CMsg cMsg, BigDecimal ctacPsnPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ctacPsnPk", ctacPsnPk);

        return getSsmEZDClient().queryObjectList("getDsCtacPntInfo", ssmParam);
    }

    // 2017/03/01 S21_NA#17807 Add Start
    /**
     * Get Ds Contact Person Relation Information
     * @param cMsg NWAL1510CMsg
     * @param ctacPsnPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCtacPsnRelnInfo(NWAL1510CMsg cMsg, BigDecimal ctacPsnPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("ctacPsnPk", ctacPsnPk);

        return getSsmEZDClient().queryObject("getDsCtacPsnRelnInfo", ssmParam);
    }
    // 2017/03/01 S21_NA#17807 Add End

    // 2017/07/31 S21_NA#19999 Add Start
    /**
     * Check Exist Order Category
     * @param bizMsg NWAL1510CMsg
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @param isCatgOnly Order Category Only
     * @return true: exist
     */
    public boolean isExistOrdCatg(NWAL1510CMsg bizMsg, String ordCatgCtxTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("ordCatgCtxTpCd", ordCatgCtxTpCd);
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd_H0.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd_H0.getValue());
        params.put("dsOrdRsnCd", bizMsg.dsOrdRsnCd_H0.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistOrdCatg", params);

        return 0 < (Integer) result.getResultObject();
    }
    // 2017/07/31 S21_NA#19999 Add End

    //QC#16452 add Start
    /**
     * Contact Customer Reference Search
     * @param bizMsg NWAL1510CMsg
     * @param idx int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacCustRefTp(NWAL1510CMsg bizMsg, String ctacTpCd, String locNum) {

        int idx = bizMsg.xxCellIdx_CO.getValueInt();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("locNum", locNum);
        params.put("ctacPsnPk", bizMsg.C.no(idx).ctacPsnPk_C0.getValue());
        params.put("ctacTpCd", ctacTpCd);

        return getSsmEZDClient().queryObject("getCtacCustRefTp", params);
    }

    /**
     * get Loction Number
     * @param bizMsg NWAL1840CMsg
     * @param ctacMsg NWAL1840_DCMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLocationNumber(NWAL1510CMsg bizMsg, int idx) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        String query = null;
        String ctacCustRefTpCd = bizMsg.C.no(idx).ctacCustRefTpCd_C0.getValue();
        if (CTAC_CUST_REF_TP.BILL_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", bizMsg.billToCustLocCd_H0.getValue());
            query = "getBillToLocationNumber";

        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", bizMsg.shipToCustLocCd_H0.getValue());
            query = "getShipToLocationNumber";

        } else if (CTAC_CUST_REF_TP.SOLD_TO.equals(ctacCustRefTpCd)) {
            ssmParam.put("custCd", bizMsg.soldToCustLocCd_H0.getValue());
            query = "getBillToLocationNumber";
        } else {
            ssmParam.put("custCd", bizMsg.shipToCustLocCd_H0.getValue());
            query = "getShipToLocationNumber";
        }
        ssmParam.put("rgtnSts", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rowNum", 1);

        return getSsmEZDClient().queryObject(query, ssmParam);
    }

    //QC#16452 add End


    // 2017/09/13 S21_NA#21069 Add Start
    /**
     * Search With Configuration Number
     * @param cMsg NWAL1510CMsg
     * @return Result
     */
    public S21SsmEZDResult searchWithConfPk(NWAL1510CMsg cMsg, BigDecimal dsCpoConfigPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmParam.put("dsCpoConfigPk", dsCpoConfigPk);

        return getSsmEZDClient().queryObject("searchWithConfPk", ssmParam);
    }
    // 2017/09/13 S21_NA#21069 Add End

    // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD START
    public S21SsmEZDResult getServiceInstallTypeEditableFlag(NWAL1510CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("svcIstlRuleNum", bizMsg.svcIstlRuleNum_DI.getValue());

        return getSsmEZDClient().queryObject("getServiceInstallTypeEditableFlag", params);

    }
    // 2018/01/18 T.Hakodate S21_NA#18460(Sol#087) ADD END

    // 2019/10/25 QC#53993 Add Start
    /**
     * Get Service Provide Party Pull Down
     * @param bizMsg NWAL1510CMsg
     * @param toBeIstlOrSvcPrvdByFlg true:toBeIstlByFlg='Y' false:svcPrvdByFlg='Y'
     * @param glblCmpyCd Global Company Code
     * @return Result
     */
    public S21SsmEZDResult getSvcPrvdPty(NWAL1510CMsg bizMsg, boolean toBeIstlOrSvcPrvdByFlg, String glblCmpyCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);

        if (toBeIstlOrSvcPrvdByFlg){
            params.put("toBeIstlByFlg", ZYPConstant.FLG_ON_Y);
        } else {
            params.put("svcPrvdByFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryObjectList("getSvcPrvdPty", params);

    }
    // 2019/10/25 QC#53993 Add End

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /**
     * Get Deinstall Information
     * @param cMsg NWAL1510CMsg
     * @return Result
     */
    public S21SsmEZDResult getDeinstallInfo(NWAL1510CMsg bizMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd);
        ssmParam.put("cpoOrdNum", bizMsg.cpoOrdNum_H0);
        ssmParam.put("dsOrdPosnNum", bizMsg.dsOrdPosnNum_H0);
        ssmParam.put("ConfigCatgCd", bizMsg.configCatgCd_H0);

        return getSsmEZDClient().queryObject("getDeinstallInfo", ssmParam);
    }

    /**
     * Get Model Service Deinstall Rule Number
     * @param String glblCmpyCd
     * @param BigDecimal dsCpoConfigPk
     * @param BigDecimal svcMachMstrPk
     * @return Result
     */
    public S21SsmEZDResult getMdlSvcDeinsRuleNum(String glblCmpyCd, BigDecimal dsCpoConfigPk, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCpoConfigPk", dsCpoConfigPk);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("svcMachTpCd", SVC_MACH_TP.MACHINE);

        return getSsmEZDClient().queryObject("getSvcDeinsRuleNum", ssmParam);
    }

    /**
     * Get Mdse Service Deinstall Rule Number
     * @param String glblCmpyCd
     * @param BigDecimal dsCpoConfigPk
     * @return Result
     */
    public S21SsmEZDResult getMdseSvcDeinsRuleNum(String glblCmpyCd, BigDecimal dsCpoConfigPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCpoConfigPk", dsCpoConfigPk);

        return getSsmEZDClient().queryObject("getSvcDeinsRuleNum", ssmParam);
    }
    // END 2023/12/12 K.Watanabe [QC#61300, ADD]
}
