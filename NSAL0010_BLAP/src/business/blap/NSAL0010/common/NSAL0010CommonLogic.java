/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0010.common;

import static business.blap.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0010.NSAL0010CMsg;
import business.blap.NSAL0010.NSAL0010Query;
import business.blap.NSAL0010.NSAL0010SMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CNTYTMsg;
import business.db.CTRYTMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.DS_ASSET_MSTRTMsgArray;
import business.db.DS_ORD_TPTMsg;
import business.db.MACH_STS_VLD_MAPTMsg;
import business.db.MACH_STS_VLD_MAPTMsgArray;
import business.db.MDSETMsg;
import business.db.POSTTMsg;
import business.db.POSTTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.db.SVC_CONFIG_MSTR_DTLTMsg;
import business.db.SVC_CONFIG_MSTR_DTLTMsgArray;
import business.db.SVC_MACH_CTAC_PSNTMsg;
import business.db.SVC_MACH_CTAC_PSNTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_MACH_MSTR_DEF_MAPTMsg;
import business.db.SVC_MACH_MSTR_STSTMsg;
import business.db.TOCTMsg;
import business.db.WHTMsg;
import business.db.WHTMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC001001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IWR_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/20   CUSA            Fujitsu         Create          N/A
 * 2013/08/26   Hitachi         Y.Igarashi      Update          QC1770
 * 2013/08/28   WDS Team        K.Aratani       Update          QC1936
 * 2015/11/06   Hitachi         T.Tomita        Update          QC#474
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 * 2015/11/25   Hitachi         T.Tomita        Update          QC#970
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC#2255
 * 2016/02/17   Hitachi         A.Kohinata      Update          QC#1986
 * 2016/02/26   Hitachi         T.Tomita        Update          QC#942
 * 2016/03/30   Hitachi         T.Tomita        Update          QC#5398, 6092
 * 2016/04/05   Hitachi         K.Yamada        Update          QC#6313
 * 2016/04/19   Hitachi         T.Tomita        Update          QC#6223
 * 2016/04/28   Hitachi         T.Tomita        Update          QC#6313
 * 04/29/2016   Hitachi         S.Nakai         Update          QC#7787
 * 2016/05/09   Hitachi         T.Tomita        Update          QC#7842
 * 2016/05/12   Hitachi         T.Tomita        Update          QC#8215
 * 2016/05/16   Hitachi         T.Tomita        Update          QC#4578
 * 2016/05/26   Hitachi         T.Tomita        Update          QC#8782
 * 2016/06/01   Hitachi         T.Tomita        Update          QC#7794
 * 2016/06/10   Hitachi         Y.Tsuchimoto    Update          QC#9591
 * 2016/09/13   Hitachi         T.Tomita        Update          QC#14361
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2016/10/13   Hitachi         T.Tomita        Update          QC#14361
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/19   Hitachi         K.Kojima        Update          QC#16600
 * 2017/01/20   Hitachi         K.Kojima        Update          QC#16600
 * 2018/04/03   Hitachi         K.Kitachi       Update          QC#17300
 * 2018/06/11   Hitachi         T.Tomita        Update          QC#23428
 * 2018/08/23   Hitachi         K.Kitachi       Update          QC#27773
 * 2019/02/13   Hitachi         T.Tomita        Update          QC#30391
 * 2019/08/07   Hitachi         A.Kohinata      Update          QC#52198
 * 2023/10/06   Hitachi         K.Ishizuka      Update          QC#54186
 *</pre>
 */
public class NSAL0010CommonLogic {

    /**
     * get SvcMachMstr
     * @param glblCmpyCd String
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTRTMsg
     */
    public static SVC_MACH_MSTRTMsg findSvcMachMstrForUpdate(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg prmTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    /**
     * findSvcMachMstr
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @param svcMachTpCd String
     * @return SVC_MACH_MSTRTMsg
     */
    public static SVC_MACH_MSTRTMsgArray findSvcMachMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk, String svcMachTpCd) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        inMsg.setConditionValue("svcMachTpCd01", svcMachTpCd);
        return (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

//QC1936
//    /**
//     * get Mdse
//     * @param cMsg NSAL0010CMsg
//     * @return MDSETMsg
//     */
//    public static MDSETMsg getMdse(NSAL0010CMsg cMsg) {
//        MDSETMsg inMsg = new MDSETMsg();
//        inMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
//        inMsg.mdseCd.setValue(cMsg.mdseCd_H1.getValue());
//        return (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
//    }

    /**
     * get ShipToCust
     * @param cMsg NSAL0010CMsg
     * @return SHIP_TO_CUSTTMsg
     */
//    public static SHIP_TO_CUSTTMsg getShipToCust(NSAL0010CMsg cMsg) {
//        SHIP_TO_CUSTTMsgArray array = getShipToCust(cMsg.glblCmpyCd.getValue(), cMsg.shipToCustCd_H1.getValue());
//        if (array.getValidCount() == 0) {
//            return null;
//        }
//        return array.no(0);
//    }

    /**
     * get ShipToCust
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return SHIP_TO_CUSTTMsgArray
     */
    public static SHIP_TO_CUSTTMsgArray getShipToCust(String glblCmpyCd, String shipToCustCd) {
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        return (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * get CurrentVersion
     * @param glblCmpyCd String
     * @param svcConfigMastrPk BigDecimal
     * @return BigDecimal
     */
    public static BigDecimal getCurrentVersion(String glblCmpyCd, BigDecimal svcConfigMastrPk) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("svcConfigMastrPk", svcConfigMastrPk);
        return NSAL0010Query.getInstance().getCurrentVersion(queryMap);
    }

    /**
     * getDsMdl
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult getDsMdl(String glblCmpyCd, String mdseCd) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", glblCmpyCd);
        queryMap.put("mdseCd", mdseCd);
        return NSAL0010Query.getInstance().getDsMdl(queryMap);
    }

    /**
     * get County
     * @param glblCmpyCd String
     * @param ctryCd String
     * @return CTRYTMsg
     */
    public static CTRYTMsg getCounty(String glblCmpyCd, String ctryCd) {
        CTRYTMsg inMsg = new CTRYTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.ctryCd.setValue(ctryCd);
        return (CTRYTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * get State
     * @param glblCmpyCd String
     * @param stCd String
     * @return STTMsg
     */
    public static STTMsg getState(String glblCmpyCd, String stCd) {
        STTMsg inMsg = new STTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.stCd.setValue(stCd);
        return (STTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    // START 2016/12/14 K.Kojima [QC#15653,DEL]
    // /**
    //  * get TechMstr
    //  * @param glblCmpyCd String
    //  * @param techTocCd String
    //  * @return TECH_MSTRTMsgArray
    //  */
    // public static TECH_MSTRTMsgArray getTechMstr(String glblCmpyCd, String techTocCd) {
    //     TECH_MSTRTMsg inMsg = new TECH_MSTRTMsg();
    //     inMsg.setSQLID("001");
    //     inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //     inMsg.setConditionValue("techTocCd01", techTocCd);
    //     return (TECH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    // }
    // END 2016/12/14 K.Kojima [QC#15653,DEL]

    /**
     * get Toc
     * @param glblCmpyCd String
     * @param tocCd String
     * @return TOCTMsg
     */
    public static TOCTMsg getToc(String glblCmpyCd, String tocCd) {
        TOCTMsg inMsg = new TOCTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.tocCd.setValue(tocCd);
        return (TOCTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * getWh
     * @param glblCmpyCd String
     * @param whCd String
     * @return WHTMsg
     */
    public static WHTMsg getWh(String glblCmpyCd, String whCd) {
        WHTMsg inMsg = new WHTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("whCd01", whCd);
        inMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        WHTMsgArray array = (WHTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (array.getValidCount() == 0) {
            return null;
        }
        return array.no(0);
    }

    /**
     * get SvcMachCtacPsn
     * @param glblCmpyCd String
     * @param svcMachMstrPk String
     * @return SVC_MACH_CTAC_PSNTMsgArray
     */
    public static SVC_MACH_CTAC_PSNTMsgArray getSvcMachCtacPsn(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_CTAC_PSNTMsg inMsg = new SVC_MACH_CTAC_PSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        return (SVC_MACH_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * get DsAssetMstr
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param serNum String
     * @return DS_ASSET_MSTRTMsg
     */
    public static DS_ASSET_MSTRTMsg getDsAssetMstr(String glblCmpyCd, String mdseCd, String serNum) {
        DS_ASSET_MSTRTMsg inMsg = new DS_ASSET_MSTRTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdseCd01", mdseCd);
        inMsg.setConditionValue("serNum01", serNum);
        inMsg.setConditionValue("actvAssetFlg01", ZYPConstant.FLG_ON_Y);
        DS_ASSET_MSTRTMsgArray array = (DS_ASSET_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (array.getValidCount() == 0) {
            return null;
        }
        return array.no(0);
    }

    /**
     * get DsAssetMstr
     * @param glblCmpyCd String
     * @param svcMachMstrPk String
     * @return DS_ASSET_MSTRTMsg
     */
    public static DS_ASSET_MSTRTMsg getDsAssetMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        DS_ASSET_MSTRTMsg inMsg = new DS_ASSET_MSTRTMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        inMsg.setConditionValue("actvAssetFlg01", ZYPConstant.FLG_ON_Y);
        DS_ASSET_MSTRTMsgArray array = (DS_ASSET_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (array.getValidCount() == 0) {
            return null;
        }
        return array.no(0);
    }

    /**
     * get Service Machine master
     * @param glblCmpyCd String
     * @param stCd String
     * @return STTMsg
     */
    public static SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.svcMachMstrPk.setValue(svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * is DsAssetMstr
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param serNum String
     * @return boolean true/false
     */
    public static boolean isDsAssetMstr(String glblCmpyCd, String mdseCd, String serNum) {
        return NSAL0010Query.getInstance().isDsAsstMstr(glblCmpyCd, mdseCd, serNum);
    }

    /**
     * Count Same Config Service Machine Master
     * @param glblCmpyCd String
     * @param svcConfigMstrPk BigDecimal
     * @param svcMachMstrPk BigDecimal
     * @return SVC_MACH_MSTR_VRSNTMsgArray
     */
    public static int countSameConfigSvcMachMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);
        tMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        return EZDTBLAccessor.count(tMsg);
    }

    /**
     * clear HeaderInfo
     * @param cMsg NSAL0010CMsg
     */
    public static void clearHeaderInfo(NSAL0010CMsg cMsg) {

//        cMsg.svcMachMstrPk_H1.clear();
//        cMsg.serNum_H1.clear();
//        cMsg.mdseCd_H1.clear();
//        cMsg.mdseNm_H1.clear();
//        cMsg.svcMachTpCd_H3.clear();
//        cMsg.mdlNm_H1.clear();
//        cMsg.custMachCtrlNum_H1.clear();
//        cMsg.svcConfigMstrPk_H1.clear();
//        ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrStsCd_H3, SVC_MACH_MSTR_STS.INSTALLED);
//        cMsg.istlDt_H1.clear();
//        cMsg.svcMachRmvDt_H1.clear();
//        cMsg.svcConfigTpCd_H1.clear();
//        cMsg.sellToCustCd_H1.clear();
//        cMsg.sellToCustLocNm_H1.clear();
//        cMsg.billToCustCd_H1.clear();
//        cMsg.billToCustLocNm_H1.clear();
//        cMsg.usrDlrTpCd_H1.clear();
//        cMsg.usrDlrTpNm_H1.clear();
//        ZYPEZDItemValueSetter.setValue(cMsg.xxYesNoCd_MM, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(cMsg.autoCratFlg_H1, ZYPConstant.FLG_OFF_N);
//        cMsg.shipToCustCd_H1.clear();
//        cMsg.shipToCustLocNm_H1.clear();
//        cMsg.locNm.clear();
//        cMsg.addlLocNm.clear();
//        cMsg.firstLineAddr.clear();
//        cMsg.scdLineAddr.clear();
//        cMsg.thirdLineAddr.clear();
//        cMsg.frthLineAddr.clear();
//        cMsg.ctryCd.clear();
//        cMsg.ctryNm.clear();
//        cMsg.postCd.clear();
//        cMsg.ctyAddr.clear();
//        cMsg.cntyNm.clear();
//        cMsg.stCd.clear();
//        cMsg.provNm.clear();
//        cMsg.svcMachFlNm.clear();
//        cMsg.prfTechCd.clear();
//        cMsg.orgLayerNum_H1.clear();
//        cMsg.orgCd_H1.clear();
//        cMsg.orgNm_H1.clear();
//        cMsg.soNum_H1.clear();
//        cMsg.shipFromWhCd_H1.clear();
//        cMsg.shipDt_H1.clear();
//        cMsg.rtrnToWhCd_H1.clear();
//        ZYPEZDItemValueSetter.setValue(cMsg.istlStsUpdCpltFlg_H1, ZYPConstant.FLG_OFF_N);
//        // Warranty Start Date, Warranty End Date
//        cMsg.xxFromDt_W.clear();
//        cMsg.xxToDt_W.clear();
//        // Service Contract Information
//        cMsg.xxViewNm_C1.clear();
//        cMsg.xxViewNm_C2.clear();
//        cMsg.xxViewNm_C3.clear();
//        cMsg.xxFromDt_C.clear();
//        cMsg.xxToDt_C.clear();
//
//        cMsg.xxChkBox_NS.clear();
//        cMsg.serNum_NS.clear();
//        cMsg.cpoOrdTpCd.clear();
//        cMsg.xxChkBox_UA.clear();
//        ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg_NS, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg_SR, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg_US, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg_TP, ZYPConstant.FLG_OFF_N);

        ZYPTableUtil.clear(cMsg.A);
    }

    // QC1770 Add Start
    /**
     * get Cnty
     * @param glblCmpyCd Global Company Code
     * @param cntyPk BigDecimal
     * @return CNTYTMsg
     */
    public static CNTYTMsg getCnty(String glblCmpyCd, BigDecimal cntyPk) {
        CNTYTMsg inMsg = new CNTYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.cntyPk, cntyPk);
        return (CNTYTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * get Ctry
     * @param glblCmpyCd Global Company Code
     * @param ctryCd Country Code
     * @return CTRYTMsg
     */
    public static CTRYTMsg getCtry(String glblCmpyCd, String ctryCd) {
        CTRYTMsg inMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.ctryCd, ctryCd);
        return (CTRYTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // QC1770 Add End

    /**
     * getDsOrdTp
     * @param glblCmpyCd String
     * @param dsOrdTpCd String
     * @return DS_ORD_TPTMsg
     */
    public static DS_ORD_TPTMsg getDsOrdTp(String glblCmpyCd, String dsOrdTpCd) {
        DS_ORD_TPTMsg inMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsOrdTpCd, dsOrdTpCd);
        return (DS_ORD_TPTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * isSame
     * @param a EZDTStringItem
     * @param b EZDCStringItem
     * @return result
     */
    public static boolean isSame(EZDTStringItem a, EZDCStringItem b) {
        boolean result = true;
        if (ZYPCommonFunc.hasValue(a)) {
            if (ZYPCommonFunc.hasValue(b)) {
                if (!a.getValue().equals(b.getValue())) {
                    result = false;
                }
            } else {
                result = false;
            }
        } else {
            if (ZYPCommonFunc.hasValue(b)) {
                result = false;
            }
        }
        return result;
    }

    /**
     * isSame
     * @param a EZDTBigDecimalItem
     * @param b EZDCBigDecimalItem
     * @return result
     */
    public static boolean isSame(EZDTBigDecimalItem a, EZDCBigDecimalItem b) {
        boolean result = true;
        if (ZYPCommonFunc.hasValue(a)) {
            if (ZYPCommonFunc.hasValue(b)) {
                if (a.getValue().compareTo(b.getValue()) != 0) {
                    result = false;
                }
            } else {
                result = false;
            }
        } else {
            if (ZYPCommonFunc.hasValue(b)) {
                result = false;
            }
        }
        return result;
    }

    /**
     * isSame
     * @param a BigDecimal
     * @param b BigDecimal
     * @return result
     */
    public static boolean isSame(BigDecimal a, BigDecimal b) {
        boolean result = true;
        if (ZYPCommonFunc.hasValue(a)) {
            if (ZYPCommonFunc.hasValue(b)) {
                if (a.compareTo(b) != 0) {
                    result = false;
                }
            } else {
                result = false;
            }
        } else {
            if (ZYPCommonFunc.hasValue(b)) {
                result = false;
            }
        }
        return result;
    }

    // add start 2016/08/30 CSA Defect#13532
    /**
     * isSame
     * @param a EZDTStringItem
     * @param b EZDTStringItem
     * @return result
     */
    public static boolean isSame(EZDTStringItem a, EZDTStringItem b) {
        boolean result = true;
        if (ZYPCommonFunc.hasValue(a)) {
            if (ZYPCommonFunc.hasValue(b)) {
                if (!a.getValue().equals(b.getValue())) {
                    result = false;
                }
            } else {
                result = false;
            }
        } else {
            if (ZYPCommonFunc.hasValue(b)) {
                result = false;
            }
        }
        return result;
    }
    // add end 2016/08/30 CSA Defect#13532

    // START 2015/11/16 T.Tomita [QC#647, MOD]
    public static void setParentMachineLine(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg, Integer i, String glblCmpyCd) {
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_A, cMsg.mdseCd_H1);
     // START 2016/09/21 N.Arai [QC#11616, MOD]
     // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseNm_A, cMsg.mdseNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_A, cMsg.mdseDescShortTxt_H1);
     // END 2016/09/21 N.Arai [QC#11616, MOD]
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mktMdlNm_A, cMsg.mktMdlNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).serNum_A, cMsg.serNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).istlDt_A, cMsg.istlDt_H1);
        SVC_MACH_MSTR_STSTMsg tMsg = (SVC_MACH_MSTR_STSTMsg) ZYPCodeDataUtil.findByCode(SVC_MACH_MSTR_STS.class, glblCmpyCd, cMsg.svcMachMstrStsCd_H3.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrStsDescTxt_A, tMsg.svcMachMstrStsDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrPk_A1, cMsg.svcMachMstrPk_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrPk_A2, cMsg.svcMachMstrPk_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachTpCd_A, SVC_MACH_TP.MACHINE);
        return;
    }
    // END 2015/11/16 T.Tomita [QC#647, MOD]

    // START 2016/05/09 T.Tomita [QC#7842, DEL]
//    /**
//     * Sets the pagenation.
//     * @param xxPageShowOfNum the xx page show of num
//     * @param xxPageShowToNum the xx page show to num
//     * @param xxPageShowFromNum the xx page show from num
//     * @param pageRecs the page records
//     * @param totalRecs the total records
//     */
//    public static void setPagenation(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {
//
//        if (xxPageShowOfNum == null || xxPageShowToNum == null) {
//            return;
//        }
//        if (pageRecs == 0 || totalRecs == 0) {
//            return;
//        }
//
//        setValue(xxPageShowToNum, new BigDecimal(xxPageShowFromNum + pageRecs - 1));
//        setValue(xxPageShowOfNum, new BigDecimal(totalRecs));
//    }
    // END 2016/05/09 T.Tomita [QC#7842, DEL]
    // START 2016/05/09 T.Tomita [QC#7842, ADD]
    /**
     * Calculation the xx page show to number.
     * @param xxPageShowFromNum the xx page show from num
     * @param pageRecs the page records
     * @return BigDecimal
     */
    public static BigDecimal calcXxPageShowToNum(int xxPageShowFromNum, int pageRecs) {

        if (pageRecs == 0) {
            return BigDecimal.ZERO;
        }

        return new BigDecimal(xxPageShowFromNum + pageRecs - 1);
    }
    // END 2016/05/09 T.Tomita [QC#7842, ADD]

    /**
     * Copy SMsg To cMsg
     * @param cMsg NSAL0010CMsg
     * @param sMsg NSAL0010SMsg
     */
    public static void copyGlblMsgToBizMsg(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        int i = 0;
        if (cMsg.xxDplyTab_01.getValue().equals(TAB_MACH_CONFIG)) {
            int pageFromNum = (cMsg.xxPageShowFromNum_A.getValueInt() - 1);
            for (; i < cMsg.A.length() && pageFromNum < sMsg.A.getValidCount(); i++) {
                EZDMsg.copy(sMsg.A.no(pageFromNum++), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SOLUTION)) {
            int pageFromNum = (cMsg.xxPageShowFromNum_B.getValueInt() - 1);
            for (; i < cMsg.B.length() && pageFromNum < sMsg.B.getValidCount(); i++) {
                EZDMsg.copy(sMsg.B.no(pageFromNum++), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTACTS)) {
            int pageFromNum = (cMsg.xxPageShowFromNum_C.getValueInt() - 1);
            for (; i < cMsg.C.length() && pageFromNum < sMsg.C.getValidCount() && sMsg.C.getValidCount() > 0; i++) {
                EZDMsg.copy(sMsg.C.no(pageFromNum++), null, cMsg.C.no(i), null);
            }
            cMsg.C.setValidCount(i);
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SLS_ORD_HIST)) {
            int pageFromNum = (cMsg.xxPageShowFromNum_G.getValueInt() - 1);
            for (; i < cMsg.G.length() && pageFromNum < sMsg.G.getValidCount(); i++) {
                EZDMsg.copy(sMsg.G.no(pageFromNum++), null, cMsg.G.no(i), null);
            }
            cMsg.G.setValidCount(i);
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_IB_HISTORY)) {
            int pageFromNum = (cMsg.xxPageShowFromNum_I.getValueInt() - 1);
            for (; i < cMsg.I.length() && pageFromNum < sMsg.I.getValidCount(); i++) {
                EZDMsg.copy(sMsg.I.no(pageFromNum++), null, cMsg.I.no(i), null);
            }
            cMsg.I.setValidCount(i);
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTR_SMRY)) {
            int pageFromNum = (cMsg.xxPageShowFromNum_J.getValueInt() - 1);
            for (; i < cMsg.J.length() && pageFromNum < sMsg.J.getValidCount(); i++) {
                EZDMsg.copy(sMsg.J.no(pageFromNum++), null, cMsg.J.no(i), null);
            }
            cMsg.J.setValidCount(i);
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SVC_CALL_HIST)) {
            int pageFromNum = (cMsg.xxPageShowFromNum_K.getValueInt() - 1);
            for (; i < cMsg.K.length() && pageFromNum < sMsg.K.getValidCount(); i++) {
                EZDMsg.copy(sMsg.K.no(pageFromNum++), null, cMsg.K.no(i), null);
            }
            cMsg.K.setValidCount(i);
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_INVOICE_HIST)) {
            int pageFromNum = (cMsg.xxPageShowFromNum_L.getValueInt() - 1);
            for (; i < cMsg.L.length() && pageFromNum < sMsg.L.getValidCount(); i++) {
                EZDMsg.copy(sMsg.L.no(pageFromNum++), null, cMsg.L.no(i), null);
            }
            cMsg.L.setValidCount(i);
        }
    }

    public static void copyCMsgToSMsg(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        if (cMsg.xxDplyTab_01.getValue().equals(TAB_MACH_CONFIG)) {
            sMsg.A.setValidCount(cMsg.xxPageShowOfNum_A.getValueInt());
            for (int idx = 0; idx < cMsg.A.getValidCount(); idx++) {

                int sMsgIdx = idx + cMsg.xxPageShowFromNum_A.getValueInt() - 1;

                if (sMsgIdx < sMsg.A.getValidCount()) {
                    EZDMsg.copy(cMsg.A.no(idx), null, sMsg.A.no(sMsgIdx), null);
                }
            }
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SOLUTION)) {
            sMsg.B.setValidCount(cMsg.xxPageShowOfNum_B.getValueInt());
            for (int idx = 0; idx < cMsg.B.getValidCount(); idx++) {

                int sMsgIdx = idx + cMsg.xxPageShowFromNum_B.getValueInt() - 1;

                if (sMsgIdx < sMsg.B.getValidCount()) {
                    EZDMsg.copy(cMsg.B.no(idx), null, sMsg.B.no(sMsgIdx), null);
                }
            }
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTACTS)) {
            sMsg.C.setValidCount(cMsg.xxPageShowOfNum_C.getValueInt());
            for (int idx = 0; idx < cMsg.C.getValidCount(); idx++) {

                int sMsgIdx = idx + cMsg.xxPageShowFromNum_C.getValueInt() - 1;

                if (sMsgIdx < sMsg.C.getValidCount()) {
                    EZDMsg.copy(cMsg.C.no(idx), null, sMsg.C.no(sMsgIdx), null);
                }
            }
        }
    }

    /**
     * Pre Set To Page One
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        setValue(xxPageShowFromNum, BigDecimal.ONE);
    }

    /**
     * getMdse
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return MDSETMsg
     */
    public static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mdseCd, mdseCd);
        return (MDSETMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * getPost
     * @param glblCmpyCd String
     * @param postCd String
     * @return POSTTMsg
     */
    public static POSTTMsg getPost(String glblCmpyCd, String postCd) {
        POSTTMsg inMsg = new POSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("postCd01", postCd);
        POSTTMsgArray array = (POSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (array.getValidCount() == 0) {
            return null;
        }
        return array.no(0);
    }

    // START 2016/02/16 [QC#2255, ADD]
    /**
     * getMachStsVldMap
     * @param glblCmpyCd String
     * @param svcMachMstrStsCd String
     * @param svcMachUsgStsCd String
     * @return MACH_STS_VLD_MAPTMsg
     */
    public static MACH_STS_VLD_MAPTMsg getMachStsVldMap(String glblCmpyCd, String svcMachMstrStsCd, String svcMachUsgStsCd) {
        MACH_STS_VLD_MAPTMsg inMsg = new MACH_STS_VLD_MAPTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrStsCd01", svcMachMstrStsCd);
        inMsg.setConditionValue("svcMachUsgStsCd01", svcMachUsgStsCd);
        MACH_STS_VLD_MAPTMsgArray array = (MACH_STS_VLD_MAPTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (array.getValidCount() == 0) {
            return null;
        }
        return array.no(0);
    }
    // END 2016/02/16 [QC#2255, ADD]

    // START 2016/02/17 [QC#1986, ADD]
    /**
     * exists SellToCust
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return boolean
     */
    public static boolean existsSellToCust(String glblCmpyCd, String dsAcctNum) {
        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        if (EZDTBLAccessor.count(inMsg) == 0) {
            return false;
        }
        return true;
    }

    // START 2016/05/16 T.Tomita [QC#4578, MOD]
    /**
     * exists BillToCust
     * @param glblCmpyCd String
     * @param locNum String
     * @return boolean
     */
    public static boolean existsBillToCust(String glblCmpyCd, String locNum) {
        BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
        inMsg.setSQLID("060");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("locNum01", locNum);
        if (EZDTBLAccessor.count(inMsg) == 0) {
            return false;
        }
        return true;
    }

    /**
     * exists ShipToCust
     * @param glblCmpyCd String
     * @param locNum String
     * @return boolean
     */
    public static boolean existsShipToCust(String glblCmpyCd, String locNum) {
        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setSQLID("048");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("locNum01", locNum);
        if (EZDTBLAccessor.count(inMsg) == 0) {
            return false;
        }
        return true;
    }

    /**
     * checkAcctBillEligible
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param locNum String
     * @return boolean
     */
    public static boolean checkAcctBillEligible(String glblCmpyCd, String dsAcctNum, String locNum) {
        String billToCustCd = getBillToCustCd(glblCmpyCd, locNum);
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return false;
        }

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        setValue(apiMsg.dsAcctNum_I2, dsAcctNum);
        setValue(apiMsg.billToCustCd, billToCustCd);

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnBillToFlg_B.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * checkAcctShipEligible
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param locNum String
     * @return boolean
     */
    public static boolean checkAcctShipEligible(String glblCmpyCd, String dsAcctNum, String locNum) {
        String shipToCustCd = getShipToCustCd(glblCmpyCd, locNum);
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            return false;
        }

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        setValue(apiMsg.dsAcctNum_I2, dsAcctNum);
        setValue(apiMsg.shipToCustCd, shipToCustCd);

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            return false;
        }

        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnShipToFlg_S.getValue())) {
            return true;
        }
        return false;
    }
    // END 2016/05/16 T.Tomita [QC#4578, MOD]
    // END 2016/02/17 [QC#1986, ADD]

    // START 2016/05/16 T.Tomita [QC#4578, ADD]
    /**
     * getBillToCustCd
     * @param glblCmpyCd String
     * @param locNum String
     * @return boolean
     */
    public static String getBillToCustCd(String glblCmpyCd, String locNum) {
        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.setSQLID("060");
        billToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        billToCustTMsg.setConditionValue("locNum01", locNum);
        BILL_TO_CUSTTMsgArray billToCustTMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(billToCustTMsg);
        if (billToCustTMsgArray.getValidCount() == 0) {
            return null;
        }
        return billToCustTMsgArray.no(0).billToCustCd.getValue();
    }

    /**
     * getShipToCustCd
     * @param glblCmpyCd String
     * @param locNum String
     * @return boolean
     */
    public static String getShipToCustCd(String glblCmpyCd, String locNum) {
        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("048");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shipToCustTMsg.setConditionValue("locNum01", locNum);
        SHIP_TO_CUSTTMsgArray shipToCustTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
        if (shipToCustTMsgArray.getValidCount() == 0) {
            return null;
        }
        return shipToCustTMsgArray.no(0).shipToCustCd.getValue();
    }
    // END 2016/05/16 T.Tomita [QC#4578, ADD]

    /**
     * Copy cMsg To SMsg
     * @param cMsg NSAL0010CMsg
     * @param sMsg NSAL0010SMsg
     */
    public static void copyBizMsgToGlblMsg(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        if (cMsg.xxDplyTab_01.getValue().equals(TAB_MACH_CONFIG)) {
            int fromRec = cMsg.xxPageShowFromNum_A.getValueInt() - 1;
            for (int n = 0, m = fromRec; n < cMsg.A.getValidCount() && m < sMsg.A.length(); n++, m++) {
                EZDMsg.copy(cMsg.A.no(n), null, sMsg.A.no(m), null);
            }
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SOLUTION)) {
            int fromRec = cMsg.xxPageShowFromNum_B.getValueInt() - 1;
            for (int n = 0, m = fromRec; n < cMsg.B.getValidCount() && m < sMsg.B.length(); n++, m++) {
                EZDMsg.copy(cMsg.B.no(n), null, sMsg.B.no(m), null);
            }
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTACTS)) {
            int fromRec = cMsg.xxPageShowFromNum_C.getValueInt() - 1;
            for (int n = 0, m = fromRec; n < cMsg.C.getValidCount() && m < sMsg.C.length(); n++, m++) {
                EZDMsg.copy(cMsg.C.no(n), null, sMsg.C.no(m), null);
            }
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SLS_ORD_HIST)) {
            int fromRec = cMsg.xxPageShowFromNum_G.getValueInt() - 1;
            for (int n = 0, m = fromRec; n < cMsg.G.getValidCount() && m < sMsg.G.length(); n++, m++) {
                EZDMsg.copy(cMsg.G.no(n), null, sMsg.G.no(m), null);
            }
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_IB_HISTORY)) {
            int fromRec = cMsg.xxPageShowFromNum_I.getValueInt() - 1;
            for (int n = 0, m = fromRec; n < cMsg.I.getValidCount() && m < sMsg.I.length(); n++, m++) {
                EZDMsg.copy(cMsg.I.no(n), null, sMsg.I.no(m), null);
            }
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_CONTR_SMRY)) {
            int fromRec = cMsg.xxPageShowFromNum_J.getValueInt() - 1;
            for (int n = 0, m = fromRec; n < cMsg.J.getValidCount() && m < sMsg.J.length(); n++, m++) {
                EZDMsg.copy(cMsg.J.no(n), null, sMsg.J.no(m), null);
            }
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_SVC_CALL_HIST)) {
            int fromRec = cMsg.xxPageShowFromNum_K.getValueInt() - 1;
            for (int n = 0, m = fromRec; n < cMsg.K.getValidCount() && m < sMsg.K.length(); n++, m++) {
                EZDMsg.copy(cMsg.K.no(n), null, sMsg.K.no(m), null);
            }
        } else if (cMsg.xxDplyTab_01.getValue().equals(TAB_INVOICE_HIST)) {
            int fromRec = cMsg.xxPageShowFromNum_L.getValueInt() - 1;
            for (int n = 0, m = fromRec; n < cMsg.L.getValidCount() && m < sMsg.L.length(); n++, m++) {
                EZDMsg.copy(cMsg.L.no(n), null, sMsg.L.no(m), null);
            }
        // START 2015/11/16 T.Tomita [QC#647, ADD]
        }else if (cMsg.xxDplyTab_01.getValue().equals(TAB_ADDL_ATTRB)) {
            EZDMsg.copy(cMsg.E, null, sMsg.E, null);
        }
        // END 2015/11/16 T.Tomita [QC#647, ADD]
    }

    // START 2015/11/25 T.Tomita [QC#970, ADD]
    // START 2016/12/19 K.Kojima [QC#16600,MOD]
    // public static boolean existsSerNum(String glblCmpyCd, String serNum) {
    public static boolean existsSerNum(String glblCmpyCd, String serNum, String mdseCd) {
    // END 2016/12/19 K.Kojima [QC#16600,MOD]
        if (!ZYPCommonFunc.hasValue(serNum)) {
            return false;
        }
        // START 2016/12/19 K.Kojima [QC#16600,ADD]
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return false;
        }
        // END 2016/12/19 K.Kojima [QC#16600,ADD]

        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        // START 2016/12/19 K.Kojima [QC#16600,MOD]
        // inMsg.setSQLID("002");
        inMsg.setSQLID("001");
        // END 2016/12/19 K.Kojima [QC#16600,MOD]
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("serNum01", serNum);
        // START 2016/12/19 K.Kojima [QC#16600,ADD]
        inMsg.setConditionValue("mdseCd01", mdseCd);
        // END 2016/12/19 K.Kojima [QC#16600,ADD]
        SVC_MACH_MSTRTMsgArray rtnArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (rtnArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }
    // END 2015/11/25 T.Tomita [QC#647, ADD]

    // START 2017/01/20 K.Kojima [QC#16600,ADD]
    public static boolean existsSerNum(String glblCmpyCd, String serNum, String mdseCd, BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(serNum)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return false;
        }

        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("serNum01", serNum);
        inMsg.setConditionValue("mdseCd01", mdseCd);
        SVC_MACH_MSTRTMsgArray rtnArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        for (int i = 0; i < rtnArray.getValidCount(); i++) {
            SVC_MACH_MSTRTMsg tMsg = rtnArray.no(i);
            if (tMsg.svcMachMstrPk.getValue().compareTo(svcMachMstrPk) != 0) {
                return true;
            }
        }
        return false;
    }

    // END 2017/01/20 K.Kojima [QC#16600,ADD]

    // START 2016/02/17 [QC#1986, ADD]
    public static boolean hasValues(String... values) {
        for (String value : values) {
            if (!ZYPCommonFunc.hasValue(value)) {
                return false;
            }
        }
        return true;
    }
    // END 2016/02/17 [QC#1986, ADD]
    // START 2016/02/26 T.Tomita [QC#942, ADD]
    public static void setDefaultValue(NSZC001001PMsg pMsg, NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        SVC_MACH_MSTR_DEF_MAPTMsg defTMsg = new SVC_MACH_MSTR_DEF_MAPTMsg();
        if (hasValues(cMsg.svcMachTrxTpCd_H3.getValue())) {
            defTMsg = getSvcMachMstrDefMapTMsg(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrStsCd_H3.getValue(), cMsg.svcMachTrxTpCd_H3.getValue());
            if (defTMsg == null) {
                defTMsg = getSvcMachMstrDefMapTMsg(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrStsCd_H3.getValue(), "*");
            }
        } else {
            defTMsg = getSvcMachMstrDefMapTMsg(cMsg.glblCmpyCd.getValue(), cMsg.svcMachMstrStsCd_H3.getValue(), "*");
        }

        if (defTMsg == null) {
            return;
        }

        // Set Values
        if (hasValues(defTMsg.stkStsCd.getValue())) {
            setValue(pMsg.stkStsCd, defTMsg.stkStsCd);
        }
        if (hasValues(defTMsg.locStsCd.getValue())) {
            setValue(pMsg.svcMachMstrLocStsCd, defTMsg.locStsCd);
        }
    }

    private static SVC_MACH_MSTR_DEF_MAPTMsg getSvcMachMstrDefMapTMsg(String glblCmpyCd, String svcMachMstrStsCd, String svcMachTrxTpCd) {
        SVC_MACH_MSTR_DEF_MAPTMsg inMsg = new SVC_MACH_MSTR_DEF_MAPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcMachMstrStsCd, svcMachMstrStsCd);
        setValue(inMsg.svcMachTrxTpCd, svcMachTrxTpCd);
        return (SVC_MACH_MSTR_DEF_MAPTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // END 2016/02/26 T.Tomita [QC#942, ADD]

    // START 2016/03/30 T.Tomita [QC#5398, ADD]
    /**
     * checkHeaderMaster
     * @param cMsg NSAL0010CMsg
     * @param sMsg NSAL0010SMsg
     * @return boolean
     */
    public static boolean checkHeaderMaster(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {

        MDSETMsg mdse = getMdse(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd_H1.getValue());
        if (mdse == null) {
            cMsg.mdseCd_H1.setErrorInfo(1, NSAM0063E, new String[] {"Mdse Code", "Mdse Master" });
            return false;
        }

        if (!ZYPConstant.FLG_ON_Y.equals(mdse.instlBaseCtrlFlg.getValue())) {
            cMsg.mdseCd_H1.setErrorInfo(1, NSAM0399E);
            return false;
        }

        // Add Start 2019/02/13 QC#30391
        String screenAplID = cMsg.getScreenAplID();
        if ("NSAL0010Scrn00_CMN_Submit".equals(screenAplID)) {
            // Serial Number
            if (!ZYPCommonFunc.hasValue(cMsg.serNum_H1)) {
                if (ZYPConstant.FLG_ON_Y.equals(mdse.rcvSerTakeFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(mdse.shpgSerTakeFlg.getValue())) {
                    cMsg.serNum_H1.setErrorInfo(1, ZZM9000E, new String[] {SERIAL_NUM_STR });
                    return false;
                }
            }
            // New Serial Number
            if (!ZYPCommonFunc.hasValue(cMsg.serNum_H2) && ZYPCommonFunc.hasValue(cMsg.xxChkBox_H1) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(mdse.rcvSerTakeFlg.getValue()) || ZYPConstant.FLG_ON_Y.equals(mdse.shpgSerTakeFlg.getValue())) {
                    cMsg.serNum_H2.setErrorInfo(1, ZZM9000E, new String[] {NEW_SERIAL_STR });
                    return false;
                }
            }
        }
        // Add End 2019/02/13 QC#30391

        // add start 2016/04/28 CSA Defect#6313
        // START 2018/04/03 K.Kitachi [QC#17300, MOD]
//        if (!ZYPConstant.FLG_ON_Y.equals(mdse.invtyCtrlFlg.getValue())) {
        if (ZYPConstant.FLG_OFF_N.equals(mdse.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdse.rcvSerTakeFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdse.shpgSerTakeFlg.getValue())) {
        // END 2018/04/03 K.Kitachi [QC#17300, MOD]
            // START 2016/05/12 T.Tomita [QC#8215, MOD]
            if (ZYPCommonFunc.hasValue(cMsg.serNum_H1)) {
                cMsg.serNum_H1.setErrorInfo(1, NSAM0473E);
                return false;
            }
            if (ZYPCommonFunc.hasValue(cMsg.serNum_H2)) {
                cMsg.serNum_H2.setErrorInfo(1, NSAM0473E);
                return false;
            }
            // END 2016/05/12 T.Tomita [QC#8215, MOD]
        }
        // add end 2016/04/28 CSA Defect#6313
        // add start 2016/04/05 CSA Defect#6313
        // START 2018/04/03 K.Kitachi [QC#17300, MOD]
//        if (!ZYPConstant.FLG_ON_Y.equals(mdse.rcvSerTakeFlg.getValue()) && !ZYPConstant.FLG_ON_Y.equals(mdse.shpgSerTakeFlg.getValue())) {
        if (ZYPConstant.FLG_ON_Y.equals(mdse.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdse.rcvSerTakeFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdse.shpgSerTakeFlg.getValue())) {
        // END 2018/04/03 K.Kitachi [QC#17300, MOD]
            if (ZYPCommonFunc.hasValue(cMsg.serNum_H1)) {
                cMsg.serNum_H1.setErrorInfo(1, NSAM0451E);
                return false;
            }
            if (ZYPCommonFunc.hasValue(cMsg.serNum_H2)) {
                cMsg.serNum_H2.setErrorInfo(1, NSAM0451E);
                return false;
            }
        }
        // add end 2016/04/05 CSA Defect#6313

        // START 2016/06/10 [QC#9591, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            if (IWR_RGTN_STS.REGISTERED.equals(cMsg.iwrRgtnStsCd_H1.getValue())) {
                cMsg.xxChkBox_H1.setErrorInfo(1, NSAM0521E);
                cMsg.serNum_H2.setErrorInfo(1, NSAM0521E);
                return false;
            }
        }
        // END   2016/06/10 [QC#9591, ADD]
        return true;
    }
    // END 2016/03/30 T.Tomita [QC#5398, ADD]

    // add start 2016/03/30 CSA Defect#6092
    public static void setMachCntForConfig(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        int cnt = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (SVC_MACH_TP.MACHINE.equals(sMsg.A.no(i).svcMachTpCd_A.getValue())) {
                cnt++;
            }
        }

        setValue(cMsg.xxTotCnt_A, new BigDecimal(cnt));
    }
    // add end 2016/03/30 CSA Defect#6092

    // add start 2016/04/19 CSA Defect#6223
    public static BigDecimal getPageShowFromNum(int index, int cMsgSize) {
        int lineNum = index + 1;
        int rtnVal = (lineNum / cMsgSize) * cMsgSize + 1;
        return new BigDecimal(rtnVal);
    }
    // add end 2016/04/19 CSA Defect#6223

    // START 2016/05/26 T.Tomita [QC#8782, ADD]
    /**
     * checkAcctOwnrBillTo
     * @param glblCmpyCd String
     * @param ownrAcctNum String
     * @param billToAcctNum String
     * @param svcMachTrxTpCd String
     * @return boolean
     */
    // mod start 2019/08/07 QC#52198
    //public static boolean checkAcctOwnrBillTo(String glblCmpyCd, String ownrAcctNum, String billToAcctNum) {
    public static boolean checkAcctOwnrBillTo(String glblCmpyCd, String ownrAcctNum, String billToAcctNum, String svcMachTrxTpCd) {
    // mod end 2019/08/07 QC#52198
        // START 2016/09/13 T.Tomita [QC#14361, DEL]
//        String ownrLocNum = getOwnrLocNum(glblCmpyCd, ownrAcctNum);
//        if (!ZYPCommonFunc.hasValue(ownrLocNum)) {
//            return false;
//        }
        // END 2016/09/13 T.Tomita [QC#14361, DEL]

        // add start 2019/08/07 QC#52198
        String ownrRelnCheckExclTrxTpCd = ZYPCodeDataUtil.getVarCharConstValue(OWNR_RELN_CHECK_EXCL_TRX_TP_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(ownrRelnCheckExclTrxTpCd)) {
            String[] list = ownrRelnCheckExclTrxTpCd.split(",");
            for (String val : list) {
                if (val.equals(svcMachTrxTpCd)) {
                    return true;
                }
            }
        }
        String ownrRelnCheckExclAcctNum = ZYPCodeDataUtil.getVarCharConstValue(OWNR_RELN_CHECK_EXCL_ACCT_NUM, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(ownrRelnCheckExclAcctNum) && ownrRelnCheckExclAcctNum.equals(ownrAcctNum)) {
            return true;
        }
        // add end 2019/08/07 QC#52198

        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
        setValue(apiMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        // START 2016/10/13 T.Tomita [QC#14361, MOD]
        setValue(apiMsg.dsAcctNum_I1, ownrAcctNum);
        setValue(apiMsg.dsAcctNum_I2, billToAcctNum);
        // END 2016/10/13 T.Tomita [QC#14361, MOD]

        NMZC610001 api = new NMZC610001();
        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            return false;
        }

        // START 2016/09/13 T.Tomita [QC#14361, MOD]
//        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnBillToFlg_B.getValue())) {
        if (ZYPConstant.FLG_ON_Y.equals(apiMsg.EligibleCheckList.no(0).dsAcctRelnRecipFlg.getValue())) {
        // END 2016/09/13 T.Tomita [QC#14361, MOD]
            return true;
        }
        return false;
    }

    // START 2016/09/13 T.Tomita [QC#14361, DEL]
//    private static String getOwnrLocNum(String glblCmpyCd, String ownrAcctNum) {
//        NMZC610001PMsg apiMsg = new NMZC610001PMsg();
//        setValue(apiMsg.glblCmpyCd, glblCmpyCd);
//        setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_RELATED_BILL_SHIP);
//        setValue(apiMsg.dsAcctNum_I1, ownrAcctNum);
//        setValue(apiMsg.xxChildRelnFlg, ZYPConstant.FLG_OFF_N);
//
//        NMZC610001 api = new NMZC610001();
//        api.execute(apiMsg, ONBATCH_TYPE.ONLINE);
//        if (S21ApiUtil.isXxMsgId(apiMsg)) {
//            return null;
//        }
//
//        if (apiMsg.RelatedBillShipList.getValidCount() == 0) {
//            return null;
//        }
//        // mod start 2016/06/01 CSA Defect#7794
//        String rtnBillToCustCd = null;
//        for (int i = 0; i < apiMsg.RelatedBillShipList.getValidCount(); i++) {
//            if (!ZYPCommonFunc.hasValue(apiMsg.RelatedBillShipList.no(i).billToCustCd)) {
//                continue;
//            }
//            rtnBillToCustCd = apiMsg.RelatedBillShipList.no(i).billToCustCd.getValue();
//            break;
//        }
//        return rtnBillToCustCd;
//        // mod end 2016/06/01 CSA Defect#7794
//    }
    // END 2016/09/13 T.Tomita [QC#14361, DEL]
    // END 2016/05/26 T.Tomita [QC#8782, ADD]

    // add start 2016/08/30 CSA Defect#13532
    public static SVC_MACH_MSTRTMsgArray getUpdateAccMachList(SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", svcMachMstrTMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcConfigMstrPk01", svcMachMstrTMsg.svcConfigMstrPk.getValue());
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrTMsg.svcMachMstrPk.getValue());

        return (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }
    // add end 2016/08/30 CSA Defect#13532
    // Add Start 2018/06/11 QC#23428
    public static SVC_CONFIG_MSTR_DTLTMsg getSvcConfigMstrDtlTMsg(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_CONFIG_MSTR_DTLTMsg inMsg = new SVC_CONFIG_MSTR_DTLTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcMachMstrPk01", svcMachMstrPk);
        SVC_CONFIG_MSTR_DTLTMsgArray array = (SVC_CONFIG_MSTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (array.getValidCount() == 0) {
            return null;
        }
        return array.no(0);
    }
    // Add End 2018/06/11 QC#23428

    // START 2018/08/23 K.Kitachi [QC#27773, ADD]
    /**
     * setStdWtyFlg
     * @param cMsg NSAL0010CMsg
     * @param sMsg NSAL0010SMsg
     */
    public static void setStdWtyFlg(NSAL0010CMsg cMsg, NSAL0010SMsg sMsg) {
        NSAL0010Query query = NSAL0010Query.getInstance();
        setValue(cMsg.stdWtyFlg_H1, ZYPConstant.FLG_OFF_N);
        if (!ZYPCommonFunc.hasValue(cMsg.mdseCd_H1)) {
            return;
        }
        BigDecimal countMdse = query.countMdse(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd_H1.getValue());
        if (BigDecimal.ZERO.compareTo(countMdse) == 0) {
            return;
        }
        String svcMachTpCd = cMsg.svcMachTpCd_H1.getValue();
        if (!ZYPCommonFunc.hasValue(svcMachTpCd) || sMsg.A.getValidCount() == 0) {
            setValue(cMsg.stdWtyFlg_H1, ZYPConstant.FLG_ON_Y);
            return;
        }
        String dsContrNum = null;
        String dsContrSrcRefNum = null;
        Map<String, Object> dsContrWty = query.getDsContrWty(cMsg.glblCmpyCd.getValue(), sMsg.A.no(0).svcConfigMstrPk_A.getValue(), cMsg.slsDt.getValue());
        Map<String, Object> dsContrIntfc = query.getDsContrIntfc(cMsg.glblCmpyCd.getValue(), sMsg.A.no(0).svcConfigMstrPk_A.getValue());
        if (dsContrWty != null) {
            dsContrNum = (String) dsContrWty.get("DS_CONTR_NUM");
        }
        if (dsContrIntfc != null) {
            dsContrSrcRefNum = (String) dsContrIntfc.get("DS_CONTR_SRC_REF_NUM");
        }
        if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
            if (ZYPCommonFunc.hasValue(dsContrNum) || ZYPCommonFunc.hasValue(dsContrSrcRefNum)) {
                return;
            }
        }
        if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
            if (!ZYPCommonFunc.hasValue(dsContrNum) && !ZYPCommonFunc.hasValue(dsContrSrcRefNum)) {
                return;
            }
            if (ZYPCommonFunc.hasValue(dsContrNum)) {
                BigDecimal countDsContrWtyForAcc = query.countDsContrWtyForAcc(cMsg.glblCmpyCd.getValue(), dsContrNum, cMsg.svcMachMstrPk_H1.getValue(), cMsg.slsDt.getValue());
                if (BigDecimal.ZERO.compareTo(countDsContrWtyForAcc) < 0) {
                    return;
                }
            }
            if (ZYPCommonFunc.hasValue(dsContrSrcRefNum)) {
                BigDecimal countDsContrIntfcForAcc = query.countDsContrIntfcForAcc(cMsg.glblCmpyCd.getValue(), dsContrSrcRefNum, cMsg.svcMachMstrPk_H1.getValue());
                if (BigDecimal.ZERO.compareTo(countDsContrIntfcForAcc) < 0) {
                    return;
                }
            }
        }
        setValue(cMsg.stdWtyFlg_H1, ZYPConstant.FLG_ON_Y);
    }
    // END 2018/08/23 K.Kitachi [QC#27773, ADD]

    // START 2023/10/06 K.Ishizuka [QC#54186, ADD]
    public static void setSvcPrvdPtyPulldown(NSAL0010CMsg bizMsg, boolean toBeIstlByFlg, String glblCmpyCd) {
        S21SsmEZDResult result = NSAL0010Query.getInstance().getSvcPrvdPty(bizMsg, toBeIstlByFlg, glblCmpyCd);
        if (result.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
            if (list == null || list.size() <= 0) {
                return;
            }
            int i = 0;
            for (Map<String, Object> map : list) {
                if (toBeIstlByFlg){
                    bizMsg.istlBySvcPrvdPtyCd_DC.no(i).setValue((String) map.get("SVC_PRVD_PTY_CD"));
                    bizMsg.svcPrvdPtyDescTxt_D1.no(i).setValue((String) map.get("SVC_PRVD_PTY_DESC_TXT"));
                } else {
                    bizMsg.svcBySvcPrvdPtyCd_DC.no(i).setValue((String) map.get("SVC_PRVD_PTY_CD"));
                    bizMsg.svcPrvdPtyDescTxt_D2.no(i).setValue((String) map.get("SVC_PRVD_PTY_DESC_TXT"));
                }

                i++;
            }
        }
    }
    // END 2023/10/06 K.Ishizuka [QC#54186, ADD]
}
