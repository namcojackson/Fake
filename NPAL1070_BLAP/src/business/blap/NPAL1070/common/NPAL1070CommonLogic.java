/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1070.common;

import static business.blap.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;
import static business.blap.NPAL1070.constant.NPAL1070Constant.BIZ_APP_NM;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_APL_ID;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_ATT_DATA_KEY_TXT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_BIZ_APP_NM;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_CMSG;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_EZBUSINESSID;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_MRP_ENBL_FLG;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_PRCH_PLN_AVAL_FLG;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_PROCR_TP_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_ROWNUM;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_RTL_SWH_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_RTL_SWH_DSBL_FLG;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_RTL_WH_CATG_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_SALES_DATE;
import static business.blap.NPAL1070.constant.NPAL1070Constant.DB_PARAM_WH_MGR_PSN_CD;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_CMN_SUBMIT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_COPY;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_DISABLE;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_IMPORT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_INIT;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EVENT_NM_NPAL1070_SEARCH;
import static business.blap.NPAL1070.constant.NPAL1070Constant.EZBUSINESSID;
import static business.blap.NPAL1070.constant.NPAL1070Constant.FUNC_EDIT_FSM;
import static business.blap.NPAL1070.constant.NPAL1070Constant.ITEM_NUMBER;
import static business.blap.NPAL1070.constant.NPAL1070Constant.ITEM_WH_SWH;
import static business.blap.NPAL1070.constant.NPAL1070Constant.MAX_ROW_1000;
import static business.blap.NPAL1070.constant.NPAL1070Constant.MAX_ROW_999;
import static business.blap.NPAL1070.constant.NPAL1070Constant.NMAM0038I;
import static business.blap.NPAL1070.constant.NPAL1070Constant.NMAM0039E;
import static business.blap.NPAL1070.constant.NPAL1070Constant.NMAM8181W;
import static business.blap.NPAL1070.constant.NPAL1070Constant.NPAM1232E;
import static business.blap.NPAL1070.constant.NPAL1070Constant.NPAM1512E;
import static business.blap.NPAL1070.constant.NPAL1070Constant.NPAM1599E;
import static business.blap.NPAL1070.constant.NPAL1070Constant.NPZM0272E;
import static business.blap.NPAL1070.constant.NPAL1070Constant.NZZM0010E;
import static business.blap.NPAL1070.constant.NPAL1070Constant.ROWNUM_SEARCH;
import static business.blap.NPAL1070.constant.NPAL1070Constant.RTL_WH_SWH;
import static business.blap.NPAL1070.constant.NPAL1070Constant.SOURCE_RTL_WH_SWH;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1070.NPAL1070CMsg;
import business.blap.NPAL1070.NPAL1070Query;
import business.blap.NPAL1070.NPAL1070SMsg;
import business.db.RTL_WHTMsg;
import business.db.RTL_WH_CATGTMsg;
import business.db.SWHTMsg;
import business.parts.NWZC206001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC206001.NWZC206001;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRT_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Business ID : NPAL1070 Min-Max Planning Entry
 * Function Name : Common Logic (BLAP)
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/24/2016   CITS            Keiichi Masaki  Create          N/A
 * 10/26/2016   CITS            K.Ogino         Update          QC#15125
 * 12/01/2016   CITS            R.Shimamoto     Update          QC#15124
 * 12/05/2016   CITS            S.Endo          Update          QC#16380/QC#16381
 * 02/09/2017   CITS            Y.IWASAKI       Update          QC#17478
 * 02/17/2017   CITS     Takeshi Tokutomi       Update          QC#17572
 * 08/17/2017   CITS            S.Endo          Update          Sol#013(QC#18717)
 * 10/05/2017   CITS            K.Ogino         Update          QC#21229
 * 11/07/2017   CITS            S.Katsuma       Update          Sol#014(QC#18401)
 * 12/12/2017   CITS            K.Ogino         Update          QC#21784
 * 01/09/2018   CITS            T.Tokutomi      Update          QC#17571
 * 03/13/2018   CITS            T.Wada          Update          Sol#118(QC#12110)
 * 04/04/2018   CITS            K.Ogino         Update          QC#18387
 * 2018/04/11   CITS            K.Ogino         Update          QC#21229
 * 2018/04/14   CITS            K.Ogino         Update          QC#24796
 * 2018/06/19   CITS            K.Ogino         Update          QC#25857
 * 2018/07/18   CITS            S.Katsuma       Update          QC#26709
 * 2018/08/29   CITS            K.Ogino         Update          QC#26709-1
 * 11/19/2018   CITS            T.Tokutomi      Update          QC#29154
 * 2018/12/05   Hitachi         J.Kim           Update          QC#18224
 * 2019/06/27   CITS            K.Ogino         Update          QC#50939
 * 2019/07/08   CITS            T.Wada          Update          QC#50988
 * 2019/08/29   CITS            M.Naito         Update          QC#51796
 * 2022/10/05   Hitachi         M.Kikushima     Update          QC#60560
 * 2023/04/17   Hitachi         S.Dong          Update          QC#61348
 *</pre>
 */

public class NPAL1070CommonLogic {
    /**
     * Set Item Description
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param glblCmpyCd String
     * @param validateFlg String
     */
    public static void setItemDescription(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String glblCmpyCd, String validateFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd);

        S21SsmEZDResult result = NPAL1070Query.getInstance().getItemDescription(ssmParam);

        if (result.isCodeNormal()) {
            List<String> record = (List<String>) result.getResultObject();

            // 1 record only
            ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, (String) record.get(0));

        } else {
            cMsg.mdseDescShortTxt.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.mdseCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.mdseCd.getValue()});
            }
        }
    }

    /**
     * Set Warehouse Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param glblCmpyCd String
     * @param validateFlg String
     */
    public static void setWarehouseName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String glblCmpyCd, String validateFlg) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.rtlWhCd.getValue());

        RTL_WHTMsg existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            // QC#21784 Start
            if (RTL_WH_CATG.TECH_CAR_STOCK.equals(existTMsg.rtlWhCatgCd.getValue())) {

                if (!ZYPCommonFunc.hasValue(cMsg.procrTpCd_SL)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_SL, existTMsg.defSrcProcrTpCd);
                    if (!PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_SL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, existTMsg.defSrcRtlWhCd);
                        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, existTMsg.defSrcRtlSwhCd);
                    }
                }
                if (!ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_SL, existTMsg.defSrcProcrTpCd);
                    if (!PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_SL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, existTMsg.defSrcRtlWhCd);
                        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, existTMsg.defSrcRtlSwhCd);
                    }
                }
                if (!ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_SL, existTMsg.defSrcProcrTpCd);
                    if (!PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_SL.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, existTMsg.defSrcRtlWhCd);
                        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, existTMsg.defSrcRtlSwhCd);
                    }
                }

                if (PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_SL.getValue())) {
                    return;
                }

                tMsg = new RTL_WHTMsg();

                tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                tMsg.rtlWhCd.setValue(cMsg.srcRtlWhCd.getValue());

                existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

                if (existTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W2, existTMsg.rtlWhNm);
                }

                SWHTMsg swhTmMsg = new SWHTMsg();

                swhTmMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                swhTmMsg.rtlSwhCd.setValue(cMsg.srcRtlSwhCd.getValue());

                SWHTMsg existSwhTmsg = (SWHTMsg) EZDTBLAccessor.findByKey(swhTmMsg);

                if (existTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S2, existSwhTmsg.rtlSwhNm.getValue());
                }

            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W1, existTMsg.rtlWhNm.getValue());
            }
            // QC#21784 End
        } else {
            cMsg.rtlWhNm_W1.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.rtlWhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlWhCd.getValue() });
            }
        }
    }

    /**
     * Set Sub Warehouse Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param glblCmpyCd String
     * @param validateFlg String
     */
    public static void setSubWarehouseName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String glblCmpyCd, String validateFlg) {

        SWHTMsg tMsg = new SWHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlSwhCd.setValue(cMsg.rtlSwhCd.getValue());

        SWHTMsg existTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S1, existTMsg.rtlSwhNm.getValue());
        } else {
            cMsg.rtlSwhNm_S1.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.rtlSwhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlSwhCd.getValue()});
            }
        }
    }

    /**
     * Set Manager Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param glblCmpyCd String
     * @param validateFlg String
     */
    public static void setManagerName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String glblCmpyCd, String validateFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_WH_MGR_PSN_CD, cMsg.whMgrPsnCd);

        S21SsmEZDResult result = NPAL1070Query.getInstance().getManagerName(ssmParam);

        if (result.isCodeNormal()) {
            List<String> record = (List<String>) result.getResultObject();

            // 1 record only
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, (String) record.get(0));

        } else {
            cMsg.fullPsnNm.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.whMgrPsnCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.whMgrPsnCd.getValue()});
            }
        }
    }

    /**
     * Set Source Warehouse Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param glblCmpyCd String
     * @param validateFlg String
     */
    public static void setSourceWarehouseName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String glblCmpyCd, String validateFlg) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.srcRtlWhCd.getValue());

        RTL_WHTMsg existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W2, existTMsg.rtlWhNm.getValue());
        } else {
            cMsg.rtlWhNm_W2.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.srcRtlWhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.srcRtlWhCd.getValue()});
            }
        }
    }

    /**
     * Set Source Warehouse Name Detail
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param glblCmpyCd String
     * @param validateFlg String
     */
    public static void setSourceWarehouseNameDetail(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String glblCmpyCd, String validateFlg) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCd.setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).srcRtlWhCd_A1.getValue());

        RTL_WHTMsg existTMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhNm_A2, existTMsg.rtlWhNm.getValue());
        } else {
            cMsg.A.no(cMsg.xxNum.getValueInt()).rtlWhNm_A2.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.A.no(cMsg.xxNum.getValueInt()).srcRtlWhCd_A1.setErrorInfo(1, NZZM0010E, new String[] {cMsg.A.no(cMsg.xxNum.getValueInt()).srcRtlWhCd_A1.getValue()});
            }
        }
    }

    /**
     * Set Source Sub Warehouse Name
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param glblCmpyCd String
     * @param validateFlg String
     */
    public static void setSourceSubWarehouseName(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String glblCmpyCd, String validateFlg) {

        SWHTMsg tMsg = new SWHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlSwhCd.setValue(cMsg.srcRtlSwhCd.getValue());

        SWHTMsg existTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S2, existTMsg.rtlSwhNm.getValue());
        } else {
            cMsg.rtlSwhNm_S2.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.srcRtlSwhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.srcRtlSwhCd.getValue()});
            }
        }
    }

    /**
     * Set Source Sub Warehouse Name Detail
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param glblCmpyCd String
     * @param validateFlg String
     */
    public static void setSourceSubWarehouseNameDetail(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String glblCmpyCd, String validateFlg) {

        SWHTMsg tMsg = new SWHTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlSwhCd.setValue(cMsg.srcRtlSwhCd.getValue());

        SWHTMsg existTMsg = (SWHTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (existTMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S2, existTMsg.rtlSwhNm.getValue());
        } else {
            cMsg.rtlSwhNm_S2.clear();
            if (ZYPConstant.FLG_ON_Y.equals(validateFlg)) {
                cMsg.srcRtlSwhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.srcRtlSwhCd.getValue()});
            }
        }
    }

    /**
     * get RTL_WH_CATG
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    public static void getRtlWhCatg(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {
        RTL_WH_CATGTMsg tMsg = new RTL_WH_CATGTMsg();

        tMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        tMsg.rtlWhCatgCd.setValue(cMsg.rtlWhCatgCd_SL.getValue());

        RTL_WH_CATGTMsg rtlWhCatgTMsg = (RTL_WH_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.locTpCd, rtlWhCatgTMsg.locTpCd);
    }

    /**
     * Search
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param eventName String
     */
    public static void search(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String eventName) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);
        ssmParam.put(DB_PARAM_ROWNUM, ROWNUM_SEARCH);
        // QC#21229
        ssmParam.put(DB_PARAM_BIZ_APP_NM, "Item Master Attachments");
        ssmParam.put(DB_PARAM_ATT_DATA_KEY_TXT, "Item Number");

        List<BigDecimal> pkList = new ArrayList<BigDecimal>();

        if (eventName.equals(EVENT_NM_NPAL1070_INIT)
            || eventName.equals(EVENT_NM_NPAL1070_SEARCH)) {
            ssmParam.put(DB_PARAM_CMSG, cMsg);
        } else if (eventName.equals(EVENT_NM_NPAL1070_CMN_SUBMIT)
            || eventName.equals(EVENT_NM_NPAL1070_DISABLE)) {
            int j = 0;
            for (; j < sMsg.A.getValidCount(); j++) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(j).mrpInfoPk_A1)
                    && MRP_INFO_RGTN_STS.AVAILABLE.equals(sMsg.A.no(j).mrpInfoRgtnStsCd_A1.getValue())) {
                    pkList.add(sMsg.A.no(j).mrpInfoPk_A1.getValue());
                }
            }
        } else if (eventName.equals(EVENT_NM_NPAL1070_IMPORT)) {
            pkList.add(cMsg.mrpInfoPk.getValue());
        } else if (eventName.equals(EVENT_NM_NPAL1070_COPY)) {
            if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
                ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd.getValue());
            }
            if (ZYPCommonFunc.hasValue(cMsg.rtlSwhCd)) {
                ssmParam.put(DB_PARAM_RTL_SWH_CD, cMsg.rtlSwhCd.getValue());
            }
            if (ZYPCommonFunc.hasValue(cMsg.rtlWhCatgCd_SL)) {
                ssmParam.put(DB_PARAM_RTL_WH_CATG_CD, cMsg.rtlWhCatgCd_SL.getValue());
            }
            if (ZYPCommonFunc.hasValue(cMsg.mrpEnblFlg)) {
                ssmParam.put(DB_PARAM_MRP_ENBL_FLG, cMsg.mrpEnblFlg.getValue());
            }
        }

        if (!pkList.isEmpty()) {
            ssmParam.put("pkList", pkList);
        }

        // clear name column
        if (!ZYPCommonFunc.hasValue(cMsg.mdseCd)) {
            cMsg.mdseDescShortTxt.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.rtlWhCd)) {
            cMsg.rtlWhNm_W1.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.rtlSwhCd)) {
            cMsg.rtlSwhNm_S1.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd)) {
            cMsg.rtlWhNm_W2.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
            cMsg.rtlSwhNm_S2.clear();
        }
        if (!ZYPCommonFunc.hasValue(cMsg.whMgrPsnCd)) {
            cMsg.fullPsnNm.clear();
        }

        ZYPTableUtil.clear(sMsg.A);
        S21SsmEZDResult result = NPAL1070Query.getInstance().search(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max record Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W, new String[]{MAX_ROW_1000, MAX_ROW_999});
                queryResCnt = sMsg.A.length();
            }

            // Copy Header Info from 1st line (SMsg -> SMsg)
            if (!eventName.equals(EVENT_NM_NPAL1070_COPY)) {
                ZYPEZDItemValueSetter.setValue(sMsg.mrpPlnNm, sMsg.A.no(0).mrpPlnNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rplshDlyFlg, sMsg.A.no(0).rplshDlyFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rplshMonFlg, sMsg.A.no(0).rplshMonFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rplshTueFlg, sMsg.A.no(0).rplshTueFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rplshWedFlg, sMsg.A.no(0).rplshWedFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rplshThuFlg, sMsg.A.no(0).rplshThuFlg_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.rplshFriFlg, sMsg.A.no(0).rplshFriFlg_A1);
                // START 2023/04/17 S.Dong [QC#61348,ADD]
                ZYPEZDItemValueSetter.setValue(sMsg.xxSelFlg, sMsg.A.no(0).xxSelFlg_A1);
                // END 2023/04/17 S.Dong [QC#61348,ADD]
                // START 2022/10/05 M.Kikushima [QC#60560,MOD]
                // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                //if (!ZYPCommonFunc.hasValue(sMsg.A.no(0).calcOrdProcCd_A1)) {
                    //ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).calcOrdProcCd_A1, ZYPConstant.FLG_OFF_0);
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).calcOrdProcCd_A1, ZYPConstant.FLG_OFF_N);
                //} else if (ZYPConstant.FLG_ON_1.equals(sMsg.A.no(0).calcOrdProcCd_A1.getValue())){
                    //ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).calcOrdProcCd_A1, ZYPConstant.FLG_ON_1);
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).calcOrdProcCd_A1, ZYPConstant.FLG_ON_Y);
                //} else if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(0).calcOrdProcCd_A1.getValue())){
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).calcOrdProcCd_A1, ZYPConstant.FLG_ON_1);
                //} else {
                //ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).calcOrdProcCd_A1, ZYPConstant.FLG_OFF_0);
                //}
                // END 2022/10/05 M.Kikushima [QC#60560,MOD]
                if (!eventName.equals(EVENT_NM_NPAL1070_IMPORT)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.calcOrdProcCd, sMsg.A.no(0).calcOrdProcCd_A1);
                }
                // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                // QC#24796
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm, sMsg.A.no(0).xxRecHistCratByNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratTs, sMsg.A.no(0).xxRecHistCratTs_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistTblNm, sMsg.A.no(0).xxRecHistTblNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm, sMsg.A.no(0).xxRecHistUpdByNm_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdTs, sMsg.A.no(0).xxRecHistUpdTs_A1);

                // Copy Header Info from 1st line(SMsg -> CMsg)
                ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, sMsg.A.no(0).mrpPlnNm_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.rplshDlyFlg, sMsg.A.no(0).rplshDlyFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.rplshMonFlg, sMsg.A.no(0).rplshMonFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.rplshTueFlg, sMsg.A.no(0).rplshTueFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.rplshWedFlg, sMsg.A.no(0).rplshWedFlg_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.rplshThuFlg, sMsg.A.no(0).rplshThuFlg_A1);
                // QC#50939
                ZYPEZDItemValueSetter.setValue(cMsg.rplshFriFlg, sMsg.A.no(0).rplshFriFlg_A1);
                // START 2023/04/17 S.Dong [QC#61348,ADD]
                ZYPEZDItemValueSetter.setValue(cMsg.xxSelFlg, sMsg.A.no(0).xxSelFlg_A1);
                // END 2023/04/17 S.Dong [QC#61348,ADD]
                // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                if (!eventName.equals(EVENT_NM_NPAL1070_IMPORT)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.calcOrdProcCd, sMsg.A.no(0).calcOrdProcCd_A1);
                }
                // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
                // QC#24796
                ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratByNm, sMsg.A.no(0).xxRecHistCratByNm_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratTs, sMsg.A.no(0).xxRecHistCratTs_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistTblNm, sMsg.A.no(0).xxRecHistTblNm_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdByNm, sMsg.A.no(0).xxRecHistUpdByNm_A1);
                ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdTs, sMsg.A.no(0).xxRecHistUpdTs_A1);
            }

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {

                // START 2022/10/05 M.Kikushima [QC#60560,ADD]
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).calcOrdProcCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).calcOrdProcCd_A1, ZYPConstant.FLG_OFF_N);
                } else if (ZYPConstant.FLG_ON_1.equals(sMsg.A.no(i).calcOrdProcCd_A1.getValue())){
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).calcOrdProcCd_A1, ZYPConstant.FLG_ON_Y);
                } else if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).calcOrdProcCd_A1.getValue())){
                    ;
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).calcOrdProcCd_A1, ZYPConstant.FLG_OFF_0);
                }
                // END 2022/10/05 M.Kikushima [QC#60560,ADD]

                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            int pageShowFromNum = getShowPageFrom(cMsg, sMsg);

            cMsg.xxPageShowFromNum.setValue(pageShowFromNum);
            cMsg.xxPageShowToNum.setValue(pageShowFromNum + cMsg.A.getValidCount() - 1);
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            ZYPTableUtil.clear(cMsg.A);
        }
    }

    /**
     * Get Show Page From
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @return int
     */
    public static int getShowPageFrom(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        int pageShowFromNum = 1;

        boolean error = false;
        int errorLineNum = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (sMsg.A.no(i).srcRtlWhCd_A1.isError()
                || sMsg.A.no(i).srcRtlSwhCd_A1.isError()
                || sMsg.A.no(i).ropQty_A1.isError()
                || sMsg.A.no(i).maxInvtyQty_A1.isError()
                || sMsg.A.no(i).ovrdMaxInvtyQty_A1.isError()
                || sMsg.A.no(i).procrTpCd_AS.isError()
                || sMsg.A.no(i).mrpEnblFlg_A1.isError()
                || sMsg.A.no(i).mdseCd_A1.isError()) {
                error = true;
                break;
            }
            errorLineNum++;
        }

        if (error) {
            int length = cMsg.A.length();
            pageShowFromNum = (errorLineNum / length) * length + 1;
        }
        return pageShowFromNum;
    }

    /**
     * Add_Check
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param eventName String
     * @return boolean
     */
    public static boolean addCheck(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        if (sMsg.A.length() == sMsg.A.getValidCount()) {
            cMsg.setMessageInfo("NPAM1199E");
            return false;
        }
        if (sMsg.B.length() == sMsg.B.getValidCount()) {
            cMsg.setMessageInfo("NPAM1199E");
            return false;
        }

        // START 2018/07/18 S.Katsuma [QC#26709,MOD]
        // Check Item Master
        S21SsmEZDResult itemResult = getItemMaster(cMsg);

        if (itemResult.isCodeNormal()) {
            List<Map> itemResultList = (List<Map>) itemResult.getResultObject();

            Map itemRecoad = itemResultList.get(0);
            if (ZYPConstant.FLG_OFF_N.equals((String) itemRecoad.get("INVTY_CTRL_FLG"))) {
                cMsg.mdseCd.setErrorInfo(1, "NPAM1528E");
                return false;
            }
            setItemInfo(cMsg, itemRecoad);
        } else {
            cMsg.mdseCd.setErrorInfo(1, NMAM0039E, new String[] {ITEM_NUMBER});
            cMsg.setMessageInfo(NMAM0039E, new String[] {ITEM_NUMBER});
            return false;
        }

        // Check Source Retail WH / SWH
        if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd) || ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
            // param 1 = WAREHOUSE only
            if (!checkEnableWH(cMsg.glblCmpyCd.getValue(), cMsg.srcRtlWhCd.getValue() + cMsg.srcRtlSwhCd.getValue(), 1)) {
                cMsg.srcRtlWhCd.setErrorInfo(1, NMAM0039E, new String[] {SOURCE_RTL_WH_SWH});
                cMsg.srcRtlSwhCd.setErrorInfo(1, NMAM0039E, new String[] {SOURCE_RTL_WH_SWH});
                return false;
            }
        }

        // Check Retail WH / SWH
        // param 0 = WAREHOUSE and TECHNICIAN
        if (!checkEnableWH(cMsg.glblCmpyCd.getValue(), cMsg.rtlWhCd.getValue() + cMsg.rtlSwhCd.getValue(), 0)) {
            cMsg.rtlWhCd.setErrorInfo(1, NMAM0039E, new String[] {RTL_WH_SWH});
            cMsg.rtlSwhCd.setErrorInfo(1, NMAM0039E, new String[] {RTL_WH_SWH});
            return false;
        }

        // BEGIN:Duplicate check with MRP_INFO.
        S21SsmEZDResult mrpResult = getMrpInfo(cMsg);
        // Check if same mdse in MRP_INFO.
        if (mrpResult.isCodeNormal()) {
            /** QC#17572 02/17/2017 T.Tokutomi START **/
            List<Map> mrpResultList = (List<Map>) mrpResult.getResultObject();

            Map mrpRecoad = mrpResultList.get(0);
            ZYPEZDItemValueSetter.setValue(cMsg.mrpInfoPk, (BigDecimal) mrpRecoad.get("MRP_INFO_PK"));

            if (ZYPCommonFunc.hasValue(cMsg.mrpInfoPk)) {
                String plnName = (String) mrpRecoad.get("MRP_PLN_NM");
                cMsg.mdseCd.setErrorInfo(1, NPAM1599E, new String[] {ITEM_WH_SWH, plnName });
                cMsg.rtlWhCd.setErrorInfo(1, NPAM1599E, new String[] {ITEM_WH_SWH, plnName });
                cMsg.rtlSwhCd.setErrorInfo(1, NPAM1599E, new String[] {ITEM_WH_SWH, plnName });
                return false;
            }
            /** QC#17572 02/17/2017 T.Tokutomi END **/
        }

        // Check if same 8 digit mdse exists in MRP_INFO.
        if (!checkMrpInfoWithOrdTakeMdse(cMsg)){
            cMsg.mdseCd.setErrorInfo(1, NPAM1232E, new String[] {ITEM_WH_SWH});
            cMsg.rtlWhCd.setErrorInfo(1, NPAM1232E, new String[] {ITEM_WH_SWH});
            cMsg.rtlSwhCd.setErrorInfo(1, NPAM1232E, new String[] {ITEM_WH_SWH});
            return false;
        }
        // END:Duplicate check with MRP_INFO.

        // BEGIN:Duplicate check with entered items.
        // Confirm if the 8 digit mdse check is needed.
        boolean check8Digit = isOrdTakeMdse(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue());

        for (int i = 0; i < sMsg.A.getValidCount(); ++i) {

            if (cMsg.rtlWhCd.getValue().equals(sMsg.A.no(i).rtlWhCd_A1.getValue()) && cMsg.rtlSwhCd.getValue().equals(sMsg.A.no(i).rtlSwhCd_A1.getValue())) {
                boolean duplicated = false;

                // Check if same mdse in entered items.
                if (cMsg.mdseCd.getValue().equals(sMsg.A.no(i).mdseCd_A1.getValue())) {
                    duplicated = true;
                }
                if (check8Digit && cMsg.mdseCd.getValue().length() >= 8 && sMsg.A.no(i).mdseCd_A1.getValue().length() >= 8) {
                    // Check if same 8 digit mdse exists in
                    // entered items.
                    if (cMsg.mdseCd.getValue().equals(sMsg.A.no(i).mdseCd_A1.getValue().substring(0, 8)) || cMsg.mdseCd.getValue().substring(0, 8).equals(sMsg.A.no(i).mdseCd_A1.getValue())) {
                        duplicated = true;
                    }
                }
                if (duplicated) {
                    // Error: Specified mdse is duplicated in
                    // enter items.
                    cMsg.mdseCd.setErrorInfo(1, NPAM1232E, new String[] {ITEM_WH_SWH });
                    cMsg.rtlWhCd.setErrorInfo(1, NPAM1232E, new String[] {ITEM_WH_SWH });
                    cMsg.rtlSwhCd.setErrorInfo(1, NPAM1232E, new String[] {ITEM_WH_SWH });
                    return false;
                }
            }
        }
        // END:Duplicate check with entered items.

        // check WH SWH master
        S21SsmEZDResult whResult = getRtlWhSwh(cMsg.glblCmpyCd.getValue(), cMsg.rtlWhCd.getValue(), cMsg.rtlSwhCd.getValue(), true);
        if (whResult.isCodeNormal()) {
            List<Map> whResultList = (List<Map>) whResult.getResultObject();

            Map whRecoad = whResultList.get(0);
            setWhSwh(cMsg, whRecoad);

            // Sol#118(QC#12110) Start
            if (isToolForTechnician(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue(), (String) whRecoad.get("LOC_TP_CD"))) {
                // Error
                cMsg.mdseCd.setErrorInfo(1, "NPAM1609E");
                return false;
            }
            // Sol#118(QC#12110) End

        } else {
            cMsg.rtlWhCd.setErrorInfo(1, NMAM0039E, new String[] {RTL_WH_SWH});
            cMsg.rtlSwhCd.setErrorInfo(1, NMAM0039E, new String[] {RTL_WH_SWH});
            return false;
        }

        // check Supersession
        if (!checkSuperSession(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue(), cMsg.mdseCd)) {
            return false;
        }

        // check Kit Item for WO
        if (!checkDsCondConst(cMsg)) {
            cMsg.mdseCd.setErrorInfo(1, NPAM1512E);
            cMsg.procrTpCd_SL.setErrorInfo(1, NPAM1512E);
            return false;
        }

        // get Source WH / SWH Name
        setSrcWhSwh(cMsg, sMsg);

        // QC#25857 check ASL
        if (ZYPCommonFunc.hasValue(cMsg.procrTpCd_SL) && PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_SL.getValue())) {
            if (!isExistAslData(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue(), check8Digit)) {
                cMsg.mdseCd.setErrorInfo(1, NPZM0272E, null);
                return false;
            }
        }

        // QC#29154 Add same warehouse check.
        if ((ZYPCommonFunc.hasValue(cMsg.procrTpCd_SL) && PROCR_TP.WAREHOUSE.equals(cMsg.procrTpCd_SL.getValue()))//
                || !ZYPCommonFunc.hasValue(cMsg.procrTpCd_SL)) {
            if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd) && ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd) //
                    && cMsg.rtlWhCd.getValue().equals(cMsg.srcRtlWhCd.getValue())) {
                cMsg.rtlWhCd.setErrorInfo(1, "NPAM1195E", new String[] {"Warehouse", "Source Warehouse" });
                cMsg.srcRtlWhCd.setErrorInfo(1, "NPAM1195E", new String[] {"Warehouse", "Source Warehouse" });
                return false;
            }
        }
        // END 2018/07/18 S.Katsuma [QC#26709,MOD]

        // check max row count
        if (sMsg.A.length() == sMsg.A.getValidCount()) {
            cMsg.setMessageInfo("NPAM0077E", new String[] {String.valueOf(sMsg.A.length()) });
            return false;
        }
        if (sMsg.B.length() == sMsg.B.getValidCount()) {
            cMsg.setMessageInfo("NPAM0077E", new String[] {String.valueOf(sMsg.B.length()) });
            return false;
        }

        return true;
    }

    /**
     * Sol#118(QC#12110)
     * @param glblCmpyCd String
     * @param mdseCd String
     * @return boolean
     */
    public static boolean isTool(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, mdseCd);
        ssmParam.put(DB_PARAM_PRCH_PLN_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        S21SsmEZDResult itemResult = NPAL1070Query.getInstance().checkItemMaster4Submit(ssmParam);
        if (itemResult.isCodeNormal()) {
            List<Map> itemResultList = (List<Map>) itemResult.getResultObject();

            Map itemRecoad = itemResultList.get(0);
            String prtItemTpCd = (String) itemRecoad.get("PRT_ITEM_TP_CD");

            if (ZYPCommonFunc.hasValue(prtItemTpCd) && PRT_ITEM_TP.TOOL.equals(prtItemTpCd)) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * Submit Check
     * </pre>
     * @param sMsg NPAL1070SMsg
     * @param cMsg NPAL1070CMsg
     * @return boolean
     */
    public static boolean submitCheck(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        String salseDate = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
        String lineErrFlg = ZYPConstant.FLG_OFF_N;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).procrTpCd_AS)) {
                sMsg.A.no(i).procrTpCd_AS.setErrorInfo(1, "ZZM9000E", new String[] {"Source Type"});
                lineErrFlg = ZYPConstant.FLG_ON_Y;
            }

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).mrpEnblFlg_A1.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).ropQty_A1)
                    && !ZYPCommonFunc.hasValue(sMsg.A.no(i).maxInvtyQty_A1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).maxInvtyQty_A1, sMsg.A.no(i).ropQty_A1);
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).ropQty_A1)) {
                    // Not input Min Quantity
                    sMsg.A.no(i).ropQty_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Min Qty"});
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }
                // QC#17571 Update.
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).ropQty_A1) && BigDecimal.ZERO.compareTo(sMsg.A.no(i).ropQty_A1.getValue()) > 0) {
                    // Min Quantity < 0
                    sMsg.A.no(i).ropQty_A1.setErrorInfo(1, "NPAM1212E", new String[] {"Min Qty", "0" });
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }
                // QC#17571 Update. 02/05/2018
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).maxInvtyQty_A1) && BigDecimal.ZERO.compareTo(sMsg.A.no(i).maxInvtyQty_A1.getValue()) > 0) {
                    // Max Quantity < 0
                    sMsg.A.no(i).maxInvtyQty_A1.setErrorInfo(1, "NPAM1212E", new String[] {"Max Qty", "0" });
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }

                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).ropQty_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).maxInvtyQty_A1) && sMsg.A.no(i).ropQty_A1.getValue().compareTo(sMsg.A.no(i).maxInvtyQty_A1.getValue()) > 0) {
                    // Min Quantity > Max Quantity
                    sMsg.A.no(i).maxInvtyQty_A1.setErrorInfo(1, "NPAM1212E", new String[] {"Max Qty", "Min Qty" });
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }
                // QC#17571 Delete ovrdMaxInvtyQty check.
                // QC#25857
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).procrTpCd_AS) && PROCR_TP.SUPPLIER.equals(sMsg.A.no(i).procrTpCd_AS.getValue())) {

                    boolean check8Digit = false;
                    if (sMsg.A.no(i).mdseCd_A1.getValue().length() >= 8) {
                        // Query ORD_TAKE_MDSE
                        Map<String, Object> bindParams = new HashMap<String, Object>();
                        bindParams.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
                        bindParams.put(DB_PARAM_MDSE_CD, sMsg.A.no(i).mdseCd_A1.getValue().substring(0, 8));
                        S21SsmEZDResult ezdResult = NPAL1070Query.getInstance().getOrdTakeMdse(bindParams);
                        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ezdResult.getResultObject();

                        if (ezdResult.isCodeNormal()) {
                            if (resultList.size() > 0) {
                                // Record exists on ORD_TAKE_MDSE. Need 8 digit mdse check.
                                check8Digit = true;
                            }
                        }
                    }

                    Map<String, Object> ssmParamASL = new HashMap<String, Object>();
                    ssmParamASL.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
                    if (check8Digit) {
                        ssmParamASL.put(DB_PARAM_MDSE_CD, sMsg.A.no(i).mdseCd_A1.getValue() + '%');
                    } else {
                        ssmParamASL.put(DB_PARAM_MDSE_CD, sMsg.A.no(i).mdseCd_A1.getValue());
                    }
                    ssmParamASL.put(DB_PARAM_SALES_DATE, salseDate);

                    S21SsmEZDResult aslResult = NPAL1070Query.getInstance().countASLData(ssmParamASL);

                    if (aslResult.isCodeNormal()) {
                        if (((BigDecimal) aslResult.getResultObject()).compareTo(BigDecimal.ZERO) == 0) {
                            sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPZM0272E, null);
                            lineErrFlg = ZYPConstant.FLG_ON_Y;
                        }

                    } else {
                        sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPZM0272E, null);
                        lineErrFlg = ZYPConstant.FLG_ON_Y;
                    }
                }

                // QC#18387 check Supersession
                NWZC206001PMsg superSedeApiPMsg = new NWZC206001PMsg();
                superSedeApiPMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
                superSedeApiPMsg.slsDt.setValue(salseDate);
                superSedeApiPMsg.mdseCd.setValue(sMsg.A.no(i).mdseCd_A1.getValue());
                superSedeApiPMsg.xxModeCd.setValue(NWZC206001.SUPD_LATEST_MODE);
                superSedeApiPMsg.xxAvalOrdFlg.setValue(ZYPConstant.FLG_OFF_N);
                superSedeApiPMsg.xxAvalPrchFlg.setValue(ZYPConstant.FLG_ON_Y);
                new NWZC206001().execute(superSedeApiPMsg, ONBATCH_TYPE.ONLINE);

                if (superSedeApiPMsg.xxMsgIdList.getValidCount() != 0) {
                    for (int c = 0; c < superSedeApiPMsg.xxMsgIdList.getValidCount(); c++) {
                        String messageId = superSedeApiPMsg.xxMsgIdList.no(c).xxMsgId.getValue();
                        sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, messageId);
                        break;
                    }
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }

                if (superSedeApiPMsg.A.getValidCount() != 0) {
                    if (!superSedeApiPMsg.A.no(0).mdseCd.getValue().equals(sMsg.A.no(i).mdseCd_A1.getValue())) {
                        sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NPAM1324E", new String[] {sMsg.A.no(i).mdseCd_A1.getValue(), superSedeApiPMsg.A.no(0).mdseCd.getValue()});
                        lineErrFlg = ZYPConstant.FLG_ON_Y;
                    }
                }
            }

            // Check Source Retail WH / SWH
            List<String> locationTypeList = new ArrayList<String>();
            locationTypeList.add(LOC_TP.WAREHOUSE);

            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)
                || ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                NMXC100001EnableWHData srclocData = NMXC100001EnableWH.checkEnableWH(
                    sMsg.A.no(i).glblCmpyCd_A1.getValue(), sMsg.A.no(i).srcRtlWhCd_A1.getValue() + sMsg.A.no(i).srcRtlSwhCd_A1.getValue(), BIZ_APP_ID, locationTypeList, null, ZYPConstant.FLG_ON_Y);

                if (srclocData == null || !ZYPCommonFunc.hasValue(srclocData.getInvtyLocNm())) {
                    sMsg.A.no(i).srcRtlWhCd_A1.setErrorInfo(1, "ZZZM9026E", new String[] {"Source WH/SWH :" + sMsg.A.no(i).srcRtlWhCd_A1.getValue() + "/" + sMsg.A.no(i).srcRtlSwhCd_A1.getValue()});
                    sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, "ZZZM9026E", new String[] {"Source WH/SWH :" + sMsg.A.no(i).srcRtlWhCd_A1.getValue() + "/" + sMsg.A.no(i).srcRtlSwhCd_A1.getValue()});
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }
            }
            // get Source WH / SWH Name
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)
                || ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                Map<String, Object> ssmParamSrcWH = new HashMap<String, Object>();

                ssmParamSrcWH.put(DB_PARAM_GLBL_CMPY_CD, sMsg.A.no(i).glblCmpyCd_A1.getValue());
                ssmParamSrcWH.put(DB_PARAM_RTL_WH_CD, sMsg.A.no(i).srcRtlWhCd_A1.getValue());
                ssmParamSrcWH.put(DB_PARAM_RTL_SWH_CD, sMsg.A.no(i).srcRtlSwhCd_A1.getValue());
                ssmParamSrcWH.put(DB_PARAM_RTL_SWH_DSBL_FLG, ZYPConstant.FLG_OFF_N);
                ssmParamSrcWH.put(DB_PARAM_SALES_DATE, salseDate);

                S21SsmEZDResult srcWhResult = NPAL1070Query.getInstance().checkRtlWhSwh(ssmParamSrcWH);

                if (srcWhResult.isCodeNormal()) {

                    List<Map> srcWhResultList = (List<Map>) srcWhResult.getResultObject();

                    Map srcWhRecoad = srcWhResultList.get(0);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, (String) srcWhRecoad.get("RTL_WH_NM"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhNm_A2, (String) srcWhRecoad.get("RTL_SWH_NM"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcLocCd_A1, (String) srcWhRecoad.get("RTL_WH_CD") + srcWhRecoad.get("RTL_SWH_CD"));

                }
            }

            // START 2018/07/18 S.Katsuma [QC#26709,MOD]
            // Sol#118(QC#12110) Start
            S21SsmEZDResult whResult = getRtlWhSwh(sMsg.A.no(i).glblCmpyCd_A1.getValue(), sMsg.A.no(i).rtlWhCd_A1.getValue(), sMsg.A.no(i).rtlSwhCd_A1.getValue(), true);

            if (whResult.isCodeNormal()) {
                List<Map> whResultList = (List<Map>) whResult.getResultObject();

                Map whRecoad = whResultList.get(0);
                setWhSwh(cMsg, whRecoad);

                if (isToolForTechnician(sMsg.A.no(i).glblCmpyCd_A1.getValue(), sMsg.A.no(i).mdseCd_A1.getValue(), (String) whRecoad.get("LOC_TP_CD"))){
                    sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NPAM1609E");
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }
            }
            // Sol#118(QC#12110) End
            // END 2018/07/18 S.Katsuma [QC#26709,MOD]

            //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) START
            //if (PROCR_TP.WAREHOUSE.equals(sMsg.A.no(i).procrTpCd_AS.getValue())) {
            if (PROCR_TP.WAREHOUSE.equals(sMsg.A.no(i).procrTpCd_AS.getValue()) || PROCR_TP.WAREHOUSE_SUPPLIER.equals(sMsg.A.no(i).procrTpCd_AS.getValue())) {
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)) {
                    sMsg.A.no(i).srcRtlWhCd_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Source Warehouse Code" });
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                    sMsg.A.no(i).srcRtlWhCd_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Source WH/SWH" });
                    sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, "ZZM9000E", new String[] {"Source WH/SWH" });
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }

                // QC#29154 Add same warehouse check.
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).rtlWhCd_A1) //
                        && sMsg.A.no(i).srcRtlWhCd_A1.getValue().equals(sMsg.A.no(i).rtlWhCd_A1.getValue())) {
                    sMsg.A.no(i).srcRtlWhCd_A1.setErrorInfo(1, "NPAM1195E", new String[] {"Source WH", "WH Code" });
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }
            }
            //08/17/2017 CITS S.Endo Mod Sol#013(QC#18717) END
            if (PROCR_TP.SUPPLIER.equals(sMsg.A.no(i).procrTpCd_AS.getValue())) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1) || (ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1))) {
                    sMsg.A.no(i).srcRtlWhCd_A1.setErrorInfo(1, "NPAM0016E", new String[] {"Source WH/SWH" });
                    sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, "NPAM0016E", new String[] {"Source WH/SWH" });
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }
            }
            // check Kit Item for WO
            if (PROCR_TP.WORK_ORDER.equals(sMsg.A.no(i).procrTpCd_AS.getValue())) {

                // QC#15125
                boolean whErrFlg = false;

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlWhCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, sMsg.A.no(i).rtlWhCd_A1);
                }

                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).srcRtlSwhCd_A1)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlSwhCd_A1, sMsg.A.no(i).rtlSwhCd_A1);
                }

                if (!sMsg.A.no(i).srcRtlWhCd_A1.getValue().equals(sMsg.A.no(i).rtlWhCd_A1.getValue())) {
                    sMsg.A.no(i).srcRtlWhCd_A1.setErrorInfo(1, "NPAM1587E", new String[] {"WH Code", "Source WH" });
                    sMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, "NPAM1587E", new String[] {"WH Code", "Source WH" });
                    whErrFlg = true;
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }

                if (!sMsg.A.no(i).srcRtlSwhCd_A1.getValue().equals(sMsg.A.no(i).rtlSwhCd_A1.getValue())) {
                    sMsg.A.no(i).srcRtlSwhCd_A1.setErrorInfo(1, "NPAM1587E", new String[] {"SWH Code", "Source SWH" });
                    sMsg.A.no(i).rtlSwhCd_A1.setErrorInfo(1, "NPAM1587E", new String[] {"SWH Code", "Source SWH" });
                    whErrFlg = true;
                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }

                if (!whErrFlg) {
                    sMsg.A.no(i).srcLocCd_A1.setValue(sMsg.A.no(i).srcRtlWhCd_A1.getValue() + sMsg.A.no(i).srcRtlSwhCd_A1.getValue());
                }

                boolean checkDsCondConst = false;

                Map<String, Object> woSsmParam = new HashMap<String, Object>();
                woSsmParam.put(DB_PARAM_CMSG, cMsg);
                woSsmParam.put(DB_PARAM_APL_ID, BIZ_APP_ID);
                woSsmParam.put(DB_PARAM_PROCR_TP_CD, PROCR_TP.WORK_ORDER);

                S21SsmEZDResult kitItemResult = NPAL1070Query.getInstance().checkDsCondConst(woSsmParam);

                if (kitItemResult.isCodeNormal()) {
                    List<Map> kitItemResultList = (List<Map>) kitItemResult.getResultObject();

                    Map kitItemRecoad = kitItemResultList.get(0);

                    String condConstValTxt01 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_01");
                    String condConstValTxt02 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_02");
                    String condConstValTxt03 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_03");
                    String condConstValTxt04 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_04");
                    String condConstValTxt05 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_05");

                    if (ZYPCommonFunc.hasValue(condConstValTxt01) && condConstValTxt01.equals(sMsg.A.no(i).mdseTpCd_A1.getValue())) {
                        checkDsCondConst = true;
                    }
                    if (ZYPCommonFunc.hasValue(condConstValTxt02) && condConstValTxt02.equals(sMsg.A.no(i).mdseTpCd_A1.getValue())) {
                        checkDsCondConst = true;
                    }
                    if (ZYPCommonFunc.hasValue(condConstValTxt03) && condConstValTxt03.equals(sMsg.A.no(i).mdseTpCd_A1.getValue())) {
                        checkDsCondConst = true;
                    }
                    if (ZYPCommonFunc.hasValue(condConstValTxt04) && condConstValTxt04.equals(sMsg.A.no(i).mdseTpCd_A1.getValue())) {
                        checkDsCondConst = true;
                    }
                    if (ZYPCommonFunc.hasValue(condConstValTxt05) && condConstValTxt05.equals(sMsg.A.no(i).mdseTpCd_A1.getValue())) {
                        checkDsCondConst = true;
                    }

                    if (!checkDsCondConst) {
                        sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, "NPAM1512E");
                        sMsg.A.no(i).procrTpCd_AS.setErrorInfo(1, "NPAM1512E");
                        lineErrFlg = ZYPConstant.FLG_ON_Y;
                    }
                }
            }

            /** QC#17572 02/17/2017 T.Tokutomi START **/
            // Check duplicate.
            Map<String, Object> ssmParamMrp = new HashMap<String, Object>();

            ssmParamMrp.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            ssmParamMrp.put(DB_PARAM_RTL_WH_CD, sMsg.A.no(i).rtlWhCd_A1.getValue());
            ssmParamMrp.put(DB_PARAM_RTL_SWH_CD, sMsg.A.no(i).rtlSwhCd_A1.getValue());
            ssmParamMrp.put(DB_PARAM_MDSE_CD, sMsg.A.no(i).mdseCd_A1.getValue());
            ssmParamMrp.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);

            // Check if same mdse in MRP_INFO.
            S21SsmEZDResult mrpResult = NPAL1070Query.getInstance().checkMrpInfoForDetail(ssmParamMrp);
            if (mrpResult.isCodeNormal()) {
                List<Map> mrpResultList = (List<Map>) mrpResult.getResultObject();

                Map mrpRecoad = mrpResultList.get(0);
                BigDecimal mrpInfoPk = (BigDecimal) mrpRecoad.get("MRP_INFO_PK");

                if ((ZYPCommonFunc.hasValue(mrpInfoPk) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).mrpInfoPk_A1)) //
                        || (ZYPCommonFunc.hasValue(mrpInfoPk) //
                                && ZYPCommonFunc.hasValue(sMsg.A.no(i).mrpInfoPk_A1) //
                                && mrpInfoPk.compareTo(sMsg.A.no(i).mrpInfoPk_A1.getValue()) != 0)) {
                    String plnName = (String) mrpRecoad.get("MRP_PLN_NM");
                    sMsg.A.no(i).mdseCd_A1.setErrorInfo(1, NPAM1599E, new String[] {ITEM_WH_SWH, plnName });
                    sMsg.A.no(i).rtlWhCd_A1.setErrorInfo(1, NPAM1599E, new String[] {ITEM_WH_SWH, plnName });
                    sMsg.A.no(i).rtlSwhCd_A1.setErrorInfo(1, NPAM1599E, new String[] {ITEM_WH_SWH, plnName });

                    lineErrFlg = ZYPConstant.FLG_ON_Y;
                }
            }
            /** QC#17572 02/17/2017 T.Tokutomi END **/
        }

        if (ZYPConstant.FLG_OFF_N.equals(lineErrFlg)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NPAL1070SMsg
     * @param cMsg NPAL1070CMsg
     */
    public static void copyFromCmsgOntoSmsg(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        // header copy
        ZYPEZDItemValueSetter.setValue(sMsg.mrpPlnNm_H1, cMsg.mrpPlnNm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseCd_H2, cMsg.mdseCd_H2);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCd_H3, cMsg.rtlWhCd_H3);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhCd_H4, cMsg.rtlSwhCd_H4);
        ZYPEZDItemValueSetter.setValue(sMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sMsg.mrpPlnNm, cMsg.mrpPlnNm);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseCd, cMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseDescShortTxt, cMsg.mdseDescShortTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.mdseTpCd, cMsg.mdseTpCd);
        ZYPEZDItemValueSetter.setValue(sMsg.coaMdseTpCd, cMsg.coaMdseTpCd);
        ZYPEZDItemValueSetter.setValue(sMsg.coaProdCd, cMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCatgCd_SL, cMsg.rtlWhCatgCd_SL);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCatgDescTxt, cMsg.rtlWhCatgDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCd, cMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_W1, cMsg.rtlWhNm_W1);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhCd, cMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_S1, cMsg.rtlSwhNm_S1);
        ZYPEZDItemValueSetter.setValue(sMsg.whMgrPsnCd, cMsg.whMgrPsnCd);
        ZYPEZDItemValueSetter.setValue(sMsg.fullPsnNm, cMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(sMsg.procrTpCd_SL, cMsg.procrTpCd_SL);
        ZYPEZDItemValueSetter.setValue(sMsg.srcRtlWhCd, cMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_W2, cMsg.rtlWhNm_W2);
        ZYPEZDItemValueSetter.setValue(sMsg.srcRtlSwhCd, cMsg.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlSwhNm_S2, cMsg.rtlSwhNm_S2);
        ZYPEZDItemValueSetter.setValue(sMsg.mrpEnblFlg, cMsg.mrpEnblFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.rplshDlyFlg, cMsg.rplshDlyFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.rplshMonFlg, cMsg.rplshMonFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.rplshTueFlg, cMsg.rplshTueFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.rplshWedFlg, cMsg.rplshWedFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.rplshThuFlg, cMsg.rplshThuFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.rplshFriFlg, cMsg.rplshFriFlg);
        // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        ZYPEZDItemValueSetter.setValue(sMsg.calcOrdProcCd, cMsg.calcOrdProcCd);
        // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]

        if (cMsg.A.getValidCount() == 0) {
            return;
        }
        for (int j = cMsg.xxPageShowFromNum.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum.getValueInt(); j++, k++) {
            EZDMsg.copy(cMsg.A.no(k), null, sMsg.A.no(j), null);
        }
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From sMsg Onto cMsg
     * </pre>
     * @param sMsg NPAL1070SMsg
     * @param cMsg NPAL1070CMsg
     */
    public static void copyFromSmsgOntoCmsg(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        // header copy
//        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, sMsg.mrpPlnNm);
//        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm_H1, sMsg.mrpPlnNm_H1);
//        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H2, sMsg.mdseCd_H2);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd_H3, sMsg.rtlWhCd_H3);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd_H4, sMsg.rtlSwhCd_H4);
//        ZYPEZDItemValueSetter.setValue(cMsg.mrpPlnNm, sMsg.mrpPlnNm);
//        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, sMsg.mdseCd);
//        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, sMsg.mdseDescShortTxt);
//        ZYPEZDItemValueSetter.setValue(cMsg.mdseTpCd, sMsg.mdseTpCd);
//        ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd, sMsg.coaMdseTpCd);
//        ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd, sMsg.coaProdCd);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SL, sMsg.rtlWhCatgCd_SL);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgDescTxt, sMsg.rtlWhCatgDescTxt);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, sMsg.rtlWhCd);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W1, sMsg.rtlWhNm_W1);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhCd, sMsg.rtlSwhCd);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S1, sMsg.rtlSwhNm_S1);
//        ZYPEZDItemValueSetter.setValue(cMsg.whMgrPsnCd, sMsg.whMgrPsnCd);
//        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, sMsg.fullPsnNm);
//        ZYPEZDItemValueSetter.setValue(cMsg.procrTpCd_SL, sMsg.procrTpCd_SL);
//        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlWhCd, sMsg.srcRtlWhCd);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W2, sMsg.rtlWhNm_W2);
//        ZYPEZDItemValueSetter.setValue(cMsg.srcRtlSwhCd, sMsg.srcRtlSwhCd);
//        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S2, sMsg.rtlSwhNm_S2);
//        ZYPEZDItemValueSetter.setValue(cMsg.mrpEnblFlg, sMsg.mrpEnblFlg);
//        ZYPEZDItemValueSetter.setValue(cMsg.rplshDlyFlg, sMsg.rplshDlyFlg);
//        ZYPEZDItemValueSetter.setValue(cMsg.rplshMonFlg, sMsg.rplshMonFlg);
//        ZYPEZDItemValueSetter.setValue(cMsg.rplshTueFlg, sMsg.rplshTueFlg);
//        ZYPEZDItemValueSetter.setValue(cMsg.rplshWedFlg, sMsg.rplshWedFlg);
//        ZYPEZDItemValueSetter.setValue(cMsg.rplshThuFlg, sMsg.rplshThuFlg);
//        ZYPEZDItemValueSetter.setValue(cMsg.rplshFriFlg, sMsg.rplshFriFlg);

        // line copy
        ZYPTableUtil.clear(cMsg.A);
        setPagePos(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * Add_Line
     * Set add line data
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param glblCmpyCd String
     * @param functionList List<String>
     */
    public static void addLine(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, String glblCmpyCd, List<String> functionList) {

        int index = sMsg.A.getValidCount();

        sMsg.A.no(index).xxChkBox_A1.clear();
        sMsg.A.no(index).mrpInfoPk_A1.clear();
        sMsg.A.no(index).ropQty_A1.clear();
        sMsg.A.no(index).maxInvtyQty_A1.clear();
        sMsg.A.no(index).ovrdMaxInvtyQty_A1.clear();
        sMsg.A.no(index).mrpInfoCmntTxt_A1.clear();

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).glblCmpyCd_A1, sMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mrpPlnNm_A1, sMsg.mrpPlnNm);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhCatgCd_A1, sMsg.rtlWhCatgCd_SL);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhCatgDescTxt_A1, sMsg.rtlWhCatgDescTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).invtyLocCd_A1, sMsg.rtlWhCd.getValue() + sMsg.rtlSwhCd.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhCd_A1, sMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhNm_A1, sMsg.rtlWhNm_W1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlSwhCd_A1, sMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlSwhNm_A1, sMsg.rtlSwhNm_S1);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseCd_A1, sMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseDescShortTxt_A1, sMsg.mdseDescShortTxt);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseTpCd_A1, sMsg.mdseTpCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaMdseTpCd_A1, sMsg.coaMdseTpCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).coaProdCd_A1, sMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mrpEnblFlg_A1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_AS, sMsg.procrTpCd_SL);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcLocCd_A1, sMsg.srcRtlWhCd.getValue() + sMsg.srcRtlSwhCd.getValue());
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcRtlWhCd_A1, sMsg.srcRtlWhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhNm_A2, sMsg.rtlWhNm_W2);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcRtlSwhCd_A1, sMsg.srcRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlSwhNm_A2, sMsg.rtlSwhNm_S2);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rplshDlyFlg_A1, sMsg.rplshDlyFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rplshMonFlg_A1, sMsg.rplshMonFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rplshTueFlg_A1, sMsg.rplshTueFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rplshWedFlg_A1, sMsg.rplshWedFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rplshThuFlg_A1, sMsg.rplshThuFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rplshFriFlg_A1, sMsg.rplshFriFlg);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).supdFlg_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mrpInfoRgtnStsCd_A1, MRP_INFO_RGTN_STS.AVAILABLE);
        // START 2022/10/05 M.Kikushima [QC#60560,MOD]
        // START 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        //ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).calcOrdProcCd_A1, sMsg.calcOrdProcCd);
        // END 2017/11/07 S.Katsuma [Sol#014(QC#18401),ADD]
        if(ZYPCommonFunc.hasValue(cMsg.calcOrdProcCd_C2)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).calcOrdProcCd_A1, ZYPConstant.FLG_ON_Y);
        }
        // END 2022/10/05 M.Kikushima [QC#60560,MOD]
        // START 2018/12/03 J.Kim [QC#18224,ADD]
        // START 2019/08/29 M.Naito [QC#51796,MOD]
//        if (hasRegisterAuthority(functionList)) {
        if (hasRegisterAuthority(functionList) && (!ZYPCommonFunc.hasValue(sMsg.A.no(index).procrTpCd_AS) || !PROCR_TP.SUPPLIER.equals(sMsg.A.no(index).procrTpCd_AS.getValue()))) {
            String mdseCd = sMsg.mdseCd.getValue();
            String rtlWhCd = sMsg.rtlWhCd.getValue();
            String rtlSwhCd = sMsg.rtlSwhCd.getValue();
            Map<String, Object> fsmRtlWhInfo = getRtlWhInfo(glblCmpyCd, sMsg.rtlWhCd.getValue());
            if (fsmRtlWhInfo == null) {
                cMsg.setMessageInfo(NMAM0039E, new String[] {"Item Number:" + mdseCd + ",WH Code:" + rtlWhCd + ",SWH Code:" + rtlSwhCd });
                return;
            }
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_AS, (String) fsmRtlWhInfo.get("DEF_SRC_PROCR_TP_CD"));
            if (!PROCR_TP.SUPPLIER.equals(sMsg.A.no(index).procrTpCd_AS.getValue())) {
                String rtlWHcd = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_CD");
                String rtlWhNm = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_NM");
                String rtlSwhNm = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_SWH_NM");
                if (!ZYPCommonFunc.hasValue(rtlWHcd) || !ZYPCommonFunc.hasValue(rtlWhNm) || !ZYPCommonFunc.hasValue(rtlSwhNm)) {
                    cMsg.setMessageInfo(NMAM0039E, new String[] {"Item Number:" + mdseCd + ",WH Code:" + rtlWhCd + ",SWH Code:" + rtlSwhCd });
                    return;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcRtlWhCd_A1, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rtlWhNm_A2, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).srcRtlSwhCd_A1, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_SWH_NM"));
            }
        }
        // END 2019/08/29 M.Naito [QC#51796,MOD]
        // END 2018/12/03 J.Kim [QC#18224,ADD]

        // QC#21229
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_MDSE_CD, sMsg.A.no(index).mdseCd_A1.getValue());
        ssmParam.put(DB_PARAM_EZBUSINESSID, EZBUSINESSID);
        ssmParam.put(DB_PARAM_BIZ_APP_NM, BIZ_APP_NM);

        S21SsmEZDResult result = NPAL1070Query.getInstance().countAttData(ssmParam);

        if (result.isCodeNormal()) {
            BigDecimal cnt = (BigDecimal) result.getResultObject();

            if (BigDecimal.ZERO.compareTo(cnt) == 0) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRsltFlg_A1, ZYPConstant.FLG_OFF_N);
            } else {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRsltFlg_A1, ZYPConstant.FLG_ON_Y);
            }

        } else {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRsltFlg_A1, ZYPConstant.FLG_OFF_N);
        }

        sMsg.A.setValidCount(index + 1);

        int pageShowFromNum = 1;
        int length = cMsg.A.length();

        pageShowFromNum = (index / length) * length + 1;

        cMsg.xxPageShowFromNum.setValue(pageShowFromNum);
        cMsg.xxPageShowToNum.setValue(pageShowFromNum + cMsg.A.getValidCount() - 1);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

    }

    /**
     * copy
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @param functionList List<String>
     */
    public static void copy(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg, List<String> functionList) {

        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCd, cMsg.xxPopPrm_C2);
        setWarehouseName(cMsg, sMsg, cMsg.glblCmpyCd.getValue(), ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhCd, cMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_W1, cMsg.rtlWhNm_W1);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMsg);
            ssmParam.put(DB_PARAM_MDSE_CD, sMsg.A.no(i).mdseCd_A1.getValue());

            S21SsmEZDResult result = NPAL1070Query.getInstance().getCopyToMrpInfoPk(ssmParam);

            if (result.isCodeNormal()) {
                List<Map> resultList = (List<Map>) result.getResultObject();

                Map record = resultList.get(0);

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mrpInfoPk_A1, (BigDecimal) record.get("MRP_INFO_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRqstTs_A1, (String) record.get("EZUPTIME"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRqstTz_A1, (String) record.get("EZUPTIMEZONE"));
            } else {
                sMsg.A.no(i).mrpInfoPk_A1.clear();
            }

            // Copy To WH / SWH set cMsg
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCd_A1, cMsg.xxPopPrm_C2);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A1, cMsg.rtlWhNm_W1);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlSwhCd_A1, cMsg.xxPopPrm_C3);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).invtyLocCd_A1, cMsg.xxPopPrm_C2.getValue() + cMsg.xxPopPrm_C3.getValue());

            // clear Copy From WH / SWH
            //sMsg.rtlWhCd.clear();
            //sMsg.rtlSwhCd.clear();
            // START 2018/12/05 J.Kim [QC#18224,ADD]
            Map<String, Object> fsmRtlWhInfo = null;
            // START 2019/08/29 M.Naito [QC#51796,MOD]
//          if (hasRegisterAuthority(functionList)) {
          if (hasRegisterAuthority(functionList) && (!ZYPCommonFunc.hasValue(sMsg.A.no(i).procrTpCd_AS) || !PROCR_TP.SUPPLIER.equals(sMsg.A.no(i).procrTpCd_AS.getValue()))) {
                String glblCmpyCd = cMsg.glblCmpyCd.getValue();
                String mdseCd = sMsg.A.no(i).mdseCd_A1.getValue();
                String rtlWhCd = sMsg.A.no(i).rtlWhCd_A1.getValue();
                String rtlSwhCd = sMsg.A.no(i).rtlSwhCd_A1.getValue();
                fsmRtlWhInfo = getRtlWhInfo(glblCmpyCd, cMsg.rtlWhCd.getValue());
                if (fsmRtlWhInfo == null) {
                    cMsg.setMessageInfo(NMAM0039E, new String[] {"Item Number:" + mdseCd + ",WH Code:" + rtlWhCd + ",SWH Code:" + rtlSwhCd });
                    ZYPTableUtil.clear(cMsg.A);
                    ZYPTableUtil.clear(sMsg.A);
                    return;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).procrTpCd_AS, (String) fsmRtlWhInfo.get("DEF_SRC_PROCR_TP_CD"));
                if (!PROCR_TP.SUPPLIER.equals(sMsg.A.no(i).procrTpCd_AS.getValue())) {
                    String rtlWHcd = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_CD");
                    String rtlWhNm = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_NM");
                    String rtlSwhNm = (String) fsmRtlWhInfo.get("DEF_SRC_RTL_SWH_NM");
                    if (!ZYPCommonFunc.hasValue(rtlWHcd) || !ZYPCommonFunc.hasValue(rtlWhNm) || !ZYPCommonFunc.hasValue(rtlSwhNm)) {
                        cMsg.setMessageInfo(NMAM0039E, new String[] {"Item Number:" + mdseCd + ",WH Code:" + rtlWhCd + ",SWH Code:" + rtlSwhCd });
                        return;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlWhCd_A1, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhNm_A2, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_WH_NM"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).srcRtlSwhCd_A1, (String) fsmRtlWhInfo.get("DEF_SRC_RTL_SWH_NM"));
                }
            }
            // END 2019/08/29 M.Naito [QC#51796,MOD]
            // END 2018/12/05 J.Kim [QC#18224,ADD]
        }
    }

    /**
     * <pre>
     * Set page position
     * </pre>
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    public static void setPagePos(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {
        if ((cMsg.A.length() == 0) || (sMsg.A.length() == 0) || (sMsg.A.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum.setValue(0);
            cMsg.xxPageShowToNum.setValue(0);
            cMsg.xxPageShowOfNum.setValue(0);
            return;
        }
        int startRowCount = 0;
        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            startRowCount = cMsg.xxPageShowFromNum.getValueInt();
        }
        int allRowCount = sMsg.A.getValidCount();
        if (startRowCount == 0) {
            cMsg.xxPageShowFromNum.setValue(1);
        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
            // last page
            cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.A.length()));
        } else {
            if ((startRowCount % cMsg.A.length()) != 1) {
                startRowCount = startRowCount - (startRowCount % cMsg.A.length()) + 1;
            }
            cMsg.xxPageShowFromNum.setValue(startRowCount);
        }
        if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1) < allRowCount) {
            cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1);
        } else {
            cMsg.xxPageShowToNum.setValue(allRowCount);
        }
        cMsg.xxPageShowOfNum.setValue(allRowCount);
    }

    /**
     * <pre>
     * Get Last Page Start Count
     * </pre>
     * @param int allRowCount
     * @param int pageRowCount
     */
    private static int getLastPageStartCount(int allRowCount, int pageRowCount) {
        if ((allRowCount <= 0) || (pageRowCount <= 0)) {
            return 0;
        }
        if (allRowCount <= pageRowCount) {
            return 1;
        }
        if (allRowCount % pageRowCount == 0) {
            return allRowCount - pageRowCount + 1;
        }
        return ((int) (allRowCount / pageRowCount)) * pageRowCount + 1;
    }

    /** QC#15124 Add.
     * toUpperCase
     * @param arg String
     * @return String
     */
    public static String toUpperCase(String arg) {

        if (ZYPCommonFunc.hasValue(arg)) {

            return arg.toUpperCase();
        }

        return arg;
    }

    /**
     * updateLineForImportProcess
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     * @return Duplicate Line
     */
    public static int getUpdateLineForImportProcess(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {

        String mdseCd = cMsg.mdseCd.getValue();
        boolean check8Digit = false;
        if (mdseCd.length() >= 8) {
            // Query ORD_TAKE_MDSE
            Map<String, Object> bindParams = new HashMap<String, Object>();
            bindParams.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
            bindParams.put(DB_PARAM_MDSE_CD, mdseCd.substring(0, 8));
            S21SsmEZDResult ezdResult = NPAL1070Query.getInstance().getOrdTakeMdse(bindParams);
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ezdResult.getResultObject();

            if (ezdResult.isCodeNormal()) {
                if (resultList.size() > 0) {
                    // Record exists on ORD_TAKE_MDSE. Need 8 digit
                    // mdse check.
                    check8Digit = true;
                }
            }
        }

        // When "import" event, entered items are TEMPORARILY stored
        // in sMsg.B.
        for (int i = 0; i < sMsg.B.getValidCount(); ++i) {

            if (cMsg.rtlWhCd.getValue().equals(sMsg.B.no(i).rtlWhCd_B1.getValue()) && cMsg.rtlSwhCd.getValue().equals(sMsg.B.no(i).rtlSwhCd_B1.getValue())) {

                // Check if same mdse in entered items.
                if (cMsg.mdseCd.getValue().equals(sMsg.B.no(i).mdseCd_B1.getValue())) {
                    return i;
                }
                if (check8Digit && cMsg.mdseCd.getValue().length() >= 8 && sMsg.B.no(i).mdseCd_B1.getValue().length() >= 8) {
                    // Check if same 8 digit mdse exists in entered
                    // items.
                    if (cMsg.mdseCd.getValue().equals(sMsg.B.no(i).mdseCd_B1.getValue().substring(0, 8)) || cMsg.mdseCd.getValue().substring(0, 8).equals(sMsg.B.no(i).mdseCd_B1.getValue())) {
                        return i;
                    }
                }
            }
        }

        return -1;
    }

    /**
     * copyDetailAtoB
     * @param sMsg NPAL1070SMsg
     */
    public static void copyDetailAtoB(NPAL1070SMsg sMsg) {
        int i = 0;
        for (; i < sMsg.A.getValidCount(); i++) {
            EZDSMsg.copy(sMsg.A.no(i), "A1", sMsg.B.no(i), "B1");
            EZDSMsg.copy(sMsg.A.no(i), "A2", sMsg.B.no(i), "B2");
            EZDSMsg.copy(sMsg.A.no(i), "AS", sMsg.B.no(i), "BS");
        }
        sMsg.B.setValidCount(i);
    }

    // START 2018/07/18 S.Katsuma [QC#26709,ADD]
    /**
     * addCheck4Upload
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    public static boolean addCheck4Upload(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String mdseCd = cMsg.mdseCd.getValue();
        String rtlWhCd = cMsg.rtlWhCd.getValue();
        String rtlSwhCd = cMsg.rtlSwhCd.getValue();
        String srcRtlWhCd = cMsg.srcRtlWhCd.getValue();
        String srcRtlSwhCd = cMsg.srcRtlSwhCd.getValue();

        S21SsmEZDResult mrpResult = getMrpInfo(cMsg);
        boolean flInsert = true;
        if (mrpResult.isCodeNormal()) {
            List<Map> mrpResultList = (List<Map>) mrpResult.getResultObject();

            String mrpPlnNm = (String) mrpResultList.get(0).get("MRP_PLN_NM");
            if (ZYPCommonFunc.hasValue(mrpPlnNm) && ZYPCommonFunc.hasValue(cMsg.mrpPlnNm.getValue())) {
                if (mrpPlnNm.equals(cMsg.mrpPlnNm.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.mrpInfoPk, (BigDecimal) mrpResultList.get(0).get("MRP_INFO_PK"));
                    // QC#26709-1
                    ZYPEZDItemValueSetter.setValue(cMsg.mrpInfoPk_IM, (BigDecimal) mrpResultList.get(0).get("MRP_INFO_PK"));
                    flInsert = false;
                }
            }

            if (flInsert) {
                cMsg.setMessageInfo("NPAM0073E", new String[] {"Import file(Item Number:" + mdseCd + ",WH Code:" + rtlWhCd + ",SWH Code:" +rtlSwhCd + ")" });
                // QC#26709-1
                cMsg.mrpInfoPk_IM.clear();
            }
        // QC#26709-1
        } else {
            cMsg.mrpInfoPk_IM.clear();
        }

        // Check Item Master
        S21SsmEZDResult itemResult = getItemMaster(cMsg);
        if (itemResult.isCodeNormal()) {
            List<Map> itemResultList = (List<Map>) itemResult.getResultObject();
            Map itemRecoad = itemResultList.get(0);
            setItemInfo(cMsg, itemRecoad);

            if (flInsert) {
                if (ZYPConstant.FLG_OFF_N.equals((String) itemRecoad.get("INVTY_CTRL_FLG"))) {
                    cMsg.setMessageInfo("NPAM1528E");
                    return false;
                }
            }
        } else {
            if (flInsert) {
                cMsg.setMessageInfo("ZZZM9026E", new String[]{"Item Number :" + mdseCd});
                return false;
            }
        }

        // check WH SWH master
        S21SsmEZDResult whResult = getRtlWhSwh(glblCmpyCd, rtlWhCd, rtlSwhCd, true);
        if (whResult.isCodeNormal()) {
            List<Map> whResultList = (List<Map>) whResult.getResultObject();

            Map whRecoad = whResultList.get(0);
            setWhSwh(cMsg, whRecoad);

            if (flInsert) {
                if (isToolForTechnician(glblCmpyCd, mdseCd, (String) whRecoad.get("LOC_TP_CD"))) {
                    cMsg.setMessageInfo("NPAM1609E");
                    return false;
                }
            }
        } else {
            if (flInsert) {
                cMsg.setMessageInfo("ZZZM9026E", new String[]{"WH/SWH :" + rtlWhCd + "/" + rtlSwhCd});
                return false;
            }
        }

        // get Source WH / SWH Name
        setSrcWhSwh(cMsg, sMsg);

        // validate only new record.
        if (flInsert) {
            // Check Source Retail WH / SWH
            if (ZYPCommonFunc.hasValue(srcRtlWhCd) || ZYPCommonFunc.hasValue(srcRtlSwhCd)) {
                // param 1 = WAREHOUSE only
                if (!checkEnableWH(glblCmpyCd, srcRtlWhCd + srcRtlSwhCd, 1)) {
                    cMsg.setMessageInfo("ZZZM9026E", new String[]{"Source WH/SWH :" + srcRtlWhCd + "/" + srcRtlSwhCd});
                    return false;
                }
            }

            // Check Retail WH / SWH
            // param 0 = WAREHOUSE and TECHNICIAN
            if (!checkEnableWH(cMsg.glblCmpyCd.getValue(), rtlWhCd + rtlSwhCd, 0)) {
                cMsg.setMessageInfo("ZZZM9026E", new String[]{"WH/SWH :" + rtlWhCd + "/" + rtlSwhCd});
                return false;
            }

            // Check if same 8 digit mdse exists in MRP_INFO.
            if (!checkMrpInfoWithOrdTakeMdse(cMsg)){
                // Error: Specified mdse is duplicated in enter items.
                cMsg.setMessageInfo("NPAM0073E", new String[] {"Import file(Item Number,WH Code,SWH Code)" });
                return false;
            }

            // check Supersession
            if (!checkSuperSession(glblCmpyCd, mdseCd, cMsg)) {
                return false;
            }

            // check Kit Item for WO
            if (!checkDsCondConst(cMsg)) {
                cMsg.setMessageInfo(NPAM1512E);
                return false;
            }

            // Confirm if the 8 digit mdse check is needed.
            // QC#25857 check ASL
            if (ZYPCommonFunc.hasValue(cMsg.procrTpCd_SL) && PROCR_TP.SUPPLIER.equals(cMsg.procrTpCd_SL.getValue())) {
                boolean check8Digit = isOrdTakeMdse(cMsg.glblCmpyCd.getValue(), mdseCd);
                if(!isExistAslData(glblCmpyCd, mdseCd, check8Digit)) {
                    cMsg.setMessageInfo("NPAM1007E", new String[]{"ASL", "MDSE_CD", cMsg.mdseCd.getValue()});
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * getMrpInfo
     * @param cMsg NPAL1070CMsg
     */
    public static S21SsmEZDResult getMrpInfo(NPAL1070CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);

        return NPAL1070Query.getInstance().checkMrpInfo(ssmParam);
    }

    /**
     * getItemMaster
     * @param cMsg NPAL1070CMsg
     */
    public static S21SsmEZDResult getItemMaster(NPAL1070CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_PRCH_PLN_AVAL_FLG, ZYPConstant.FLG_ON_Y);

        return NPAL1070Query.getInstance().checkItemMaster(ssmParam);
    }

    /**
     * setItemInfo
     * @param cMsg NPAL1070CMsg
     * @param itemRecoad Map
     */
    public static void setItemInfo(NPAL1070CMsg cMsg, Map itemRecoad) {
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt, (String) itemRecoad.get("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd, (String) itemRecoad.get("COA_MDSE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd, (String) itemRecoad.get("COA_PROD_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.mdseTpCd, (String) itemRecoad.get("MDSE_TP_CD"));
    }

    /**
     * getRtlWhSwh
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @param rtlSwhCd String
     */
    public static S21SsmEZDResult getRtlWhSwh(String glblCmpyCd, String rtlWhCd, String rtlSwhCd, boolean flMrp) {
        Map<String, Object> ssmParamWH = new HashMap<String, Object>();
        ssmParamWH.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParamWH.put(DB_PARAM_RTL_WH_CD, rtlWhCd);
        ssmParamWH.put(DB_PARAM_RTL_SWH_CD, rtlSwhCd);
        if (flMrp) {
            ssmParamWH.put(DB_PARAM_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);
        }
        ssmParamWH.put(DB_PARAM_RTL_SWH_DSBL_FLG, ZYPConstant.FLG_OFF_N);
        ssmParamWH.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));

        return NPAL1070Query.getInstance().checkRtlWhSwh(ssmParamWH);
    }
    // QC#50988 Add Start
    /**
     * findMrpInfoByPlnName
     * @param glblCmpyCd
     * @param mrpPlnNm
     * @return
     */
    public static S21SsmEZDResult findMrpInfoByPlnName(String glblCmpyCd, String mrpPlnNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mrpPlnNm", mrpPlnNm);
        ssmParam.put("rgtnStsCd", MRP_INFO_RGTN_STS.AVAILABLE);
        return NPAL1070Query.getInstance().findMrpInfoByPlnName(ssmParam);
    }
    // QC#50988 Add End
    /**
     * setWhSwh
     * @param cMsg NPAL1070CMsg
     * @param whRecoad Map
     */
    public static void setWhSwh(NPAL1070CMsg cMsg, Map whRecoad) {
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W1, (String) whRecoad.get("RTL_WH_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S1, (String) whRecoad.get("RTL_SWH_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgCd_SL, (String) whRecoad.get("RTL_WH_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhCatgDescTxt, (String) whRecoad.get("RTL_WH_CATG_DESC_TXT"));
    }

    /**
     * isToolForTechnician
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param locTpCd String
     */
    public static boolean isToolForTechnician(String glblCmpyCd, String mdseCd, String locTpCd) {
        boolean result = false;

        if (isTool(glblCmpyCd, mdseCd)){
            if (ZYPCommonFunc.hasValue(locTpCd) && LOC_TP.TECHNICIAN.equals(locTpCd)) {
                // Error
                result = true;
            }
        }

        return result;
    }

    /**
     * checkEnableWH
     * @param glblCmpyCd String
     * @param locCd String
     * @param locCd clLocTp
     */
    public static boolean checkEnableWH(String glblCmpyCd, String locCd, int clLocTp) {
        List<String> locationTypeList = new ArrayList<String>();
        if (clLocTp == 1) {
            locationTypeList.add(LOC_TP.WAREHOUSE);
        } else if (clLocTp == 2) {
            locationTypeList.add(LOC_TP.TECHNICIAN);
        } else {
            locationTypeList.add(LOC_TP.WAREHOUSE);
            locationTypeList.add(LOC_TP.TECHNICIAN);
        }

        return checkEnableWH(glblCmpyCd, locCd, locationTypeList);
    }

    /**
     * checkEnableWH
     * @param glblCmpyCd String
     * @param locCd String
     * @param locationTypeList List<String>
     */
    public static boolean checkEnableWH(String glblCmpyCd, String locCd, List<String> locationTypeList) {
        boolean result = true;

        NMXC100001EnableWHData locData = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, locCd, BIZ_APP_ID, locationTypeList, null, ZYPConstant.FLG_ON_Y);
        if (locData == null || !ZYPCommonFunc.hasValue(locData.getInvtyLocNm())) {
            result = false;
        }

        return result;
    }

    /**
     * getMrpInfoWithOrdTakeMdse
     * @param cMsg NPAL1070CMsg
     */
    public static S21SsmEZDResult getMrpInfoWithOrdTakeMdse(NPAL1070CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMsg);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);

        return NPAL1070Query.getInstance().checkMrpInfoWithOrdTakeMdse(ssmParam);
    }

    /**
     * checkMrpInfoWithOrdTakeMdse
     * @param cMsg NPAL1070CMsg
     */
    public static boolean checkMrpInfoWithOrdTakeMdse(NPAL1070CMsg cMsg) {
        boolean result = true;

        S21SsmEZDResult mrpResult = getMrpInfoWithOrdTakeMdse(cMsg);
        if (mrpResult.isCodeNormal()) {
            List<Map> mrpResultList = (List<Map>) mrpResult.getResultObject();

            Map mrpRecoad = mrpResultList.get(0);
            ZYPEZDItemValueSetter.setValue(cMsg.mrpInfoPk, (BigDecimal) mrpRecoad.get("MRP_INFO_PK"));

            if (ZYPCommonFunc.hasValue(cMsg.mrpInfoPk)) {
                result = false;
            }
        }

        return result;
    }

    /**
     * isOrdTakeMdse
     * @param glblCmpyCd String
     * @param mdseCd String
     */
    public static boolean isOrdTakeMdse(String glblCmpyCd, String mdseCd) {
        boolean check8Digit = false;
        if (mdseCd.length() >= 8) {
            // Query ORD_TAKE_MDSE
            Map<String, Object> bindParams = new HashMap<String, Object>();
            bindParams.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            bindParams.put(DB_PARAM_MDSE_CD, mdseCd.substring(0, 8));
            S21SsmEZDResult ezdResult = NPAL1070Query.getInstance().getOrdTakeMdse(bindParams);
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ezdResult.getResultObject();

            if (ezdResult.isCodeNormal()) {
                if (resultList.size() > 0) {
                    // Record exists on ORD_TAKE_MDSE. Need 8 digit mdse check.
                    check8Digit = true;
                }
            }
        }

        return check8Digit;
    }

    /**
     * callNWZC206001
     * @param glblCmpyCd String
     * @param mdseCd String
     */
    public static NWZC206001PMsg callNWZC206001(String glblCmpyCd, String mdseCd) {
        NWZC206001PMsg superSedeApiPMsg = new NWZC206001PMsg();
        superSedeApiPMsg.glblCmpyCd.setValue(glblCmpyCd);
        superSedeApiPMsg.slsDt.setValue(ZYPDateUtil.getSalesDate(glblCmpyCd));
        superSedeApiPMsg.mdseCd.setValue(mdseCd);
        superSedeApiPMsg.xxModeCd.setValue(NWZC206001.SUPD_LATEST_MODE);
        superSedeApiPMsg.xxAvalOrdFlg.setValue(ZYPConstant.FLG_OFF_N);
        superSedeApiPMsg.xxAvalPrchFlg.setValue(ZYPConstant.FLG_ON_Y);
        new NWZC206001().execute(superSedeApiPMsg, ONBATCH_TYPE.ONLINE);

        return superSedeApiPMsg;
    }

    /**
     * checkSuperSession
     * @param glblCmpyCd String
     * @param mdseCd errorObj
     * @param errorObj <T>
     */
    public static <T> boolean checkSuperSession(String glblCmpyCd, String mdseCd, T errorObj) {
        boolean result = true;

        NWZC206001PMsg superSedeApiPMsg = callNWZC206001(glblCmpyCd, mdseCd);

        if (superSedeApiPMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < superSedeApiPMsg.xxMsgIdList.getValidCount(); i++) {
                setError(errorObj, superSedeApiPMsg.xxMsgIdList.no(i).xxMsgId.getValue(), null);
                break;
            }
            result = false;
        }

        if (superSedeApiPMsg.A.getValidCount() != 0) {
            if (!superSedeApiPMsg.A.no(0).mdseCd.getValue().equals(mdseCd)) {
                setError(errorObj, "NPAM1324E", new String[] {mdseCd, superSedeApiPMsg.A.no(0).mdseCd.getValue()});
                result = false;
            }
        }

        return result;
    }

    /**
     * checkDsCondConst
     * @param cMsg NPAL1070CMsg
     */
    public static boolean checkDsCondConst(NPAL1070CMsg cMsg) {
        boolean result = false;

        Map<String, Object> woSsmParam = new HashMap<String, Object>();
        woSsmParam.put(DB_PARAM_CMSG, cMsg);
        woSsmParam.put(DB_PARAM_APL_ID, BIZ_APP_ID);
        woSsmParam.put(DB_PARAM_PROCR_TP_CD, cMsg.procrTpCd_SL.getValue());

        S21SsmEZDResult kitItemResult = NPAL1070Query.getInstance().checkDsCondConst(woSsmParam);

        if (kitItemResult.isCodeNormal()) {
            List<Map> kitItemResultList = (List<Map>) kitItemResult.getResultObject();

            Map kitItemRecoad = kitItemResultList.get(0);

            String condConstValTxt01 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_01");
            String condConstValTxt02 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_02");
            String condConstValTxt03 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_03");
            String condConstValTxt04 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_04");
            String condConstValTxt05 = (String) kitItemRecoad.get("DS_COND_CONST_VAL_TXT_05");

            if (ZYPCommonFunc.hasValue(condConstValTxt01) && condConstValTxt01.equals(cMsg.mdseTpCd.getValue())) {
                result = true;
            }
            if (ZYPCommonFunc.hasValue(condConstValTxt02) && condConstValTxt02.equals(cMsg.mdseTpCd.getValue())) {
                result = true;
            }
            if (ZYPCommonFunc.hasValue(condConstValTxt03) && condConstValTxt03.equals(cMsg.mdseTpCd.getValue())) {
                result = true;
            }
            if (ZYPCommonFunc.hasValue(condConstValTxt04) && condConstValTxt04.equals(cMsg.mdseTpCd.getValue())) {
                result = true;
            }
            if (ZYPCommonFunc.hasValue(condConstValTxt05) && condConstValTxt05.equals(cMsg.mdseTpCd.getValue())) {
                result = true;
            }
        } else {
            result = true;
        }

        return result;
    }

    /**
     * isExistAslData
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param check8Digit boolean
     */
    public static boolean isExistAslData(String glblCmpyCd, String mdseCd, boolean check8Digit) {
        boolean result = false;

        Map<String, Object> ssmParamASL = new HashMap<String, Object>();
        ssmParamASL.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if (check8Digit) {
            ssmParamASL.put(DB_PARAM_MDSE_CD, mdseCd + '%');
        } else {
            ssmParamASL.put(DB_PARAM_MDSE_CD, mdseCd);
        }
        ssmParamASL.put(DB_PARAM_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));

        S21SsmEZDResult aslResult = NPAL1070Query.getInstance().countASLData(ssmParamASL);

        if (aslResult.isCodeNormal()) {
            if (((BigDecimal) aslResult.getResultObject()).compareTo(BigDecimal.ZERO) == 0) {
                result = false;
            } else {
                result = true;
            }
        } else {
            result = false;
        }

        return result;
    }

    /**
     * setSrcWhSwh
     * @param cMsg NPAL1070CMsg
     * @param sMsg NPAL1070SMsg
     */
    public static void setSrcWhSwh(NPAL1070CMsg cMsg, NPAL1070SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.srcRtlWhCd) || ZYPCommonFunc.hasValue(cMsg.srcRtlSwhCd)) {
            S21SsmEZDResult srcWhResult = getRtlWhSwh(cMsg.glblCmpyCd.getValue(), cMsg.srcRtlWhCd.getValue(), cMsg.srcRtlSwhCd.getValue(), false);

            if (srcWhResult.isCodeNormal()) {
                List<Map> srcWhResultList = (List<Map>) srcWhResult.getResultObject();
                Map srcWhRecoad = srcWhResultList.get(0);
                ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_W2, (String) srcWhRecoad.get("RTL_WH_NM"));
                ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm_S2, (String) srcWhRecoad.get("RTL_SWH_NM"));
            }
        } else {
            // QC#21784
            setWarehouseName(cMsg, sMsg, cMsg.glblCmpyCd.getValue(), ZYPConstant.FLG_OFF_N);
        }
    }
    /**
     * setError
     * @param errorObj <T>
     * @param msgId String
     * @param params String[]
     */
    public static <T> void setError(T errorObj, String msgId, String[] params) {
        if (errorObj instanceof EZDCStringItem) {
            setError((EZDCStringItem) errorObj, msgId, params);
        } else if (errorObj instanceof EZDCMsg) {
            setError((EZDCMsg) errorObj, msgId, params);
        }
    }

    /**
     * setError
     * @param errorObj EZDCStringItem
     * @param msgId String
     * @param params String[]
     */
    public static void setError(EZDCStringItem errorObj, String msgId, String[] params) {
        if (params != null && params.length > 0) {
            errorObj.setErrorInfo(1, msgId, params);
        } else {
            errorObj.setErrorInfo(1, msgId);
        }
    }

    /**
     * setError
     * @param errorObj EZDCMsg
     * @param msgId String
     * @param params String[]
     */
    public static void setError(EZDCMsg errorObj, String msgId, String[] params) {
        if (params != null && params.length > 0) {
            errorObj.setMessageInfo(msgId, params);
        } else {
            errorObj.setMessageInfo(msgId);
        }
    }
    // END 2018/07/18 S.Katsuma [QC#26709,ADD]

    // START 2018/12/03 J.Kim [QC#18224,ADD]
    /**
     * getRtlWhInfo
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getRtlWhInfo(String glblCmpyCd, String rtlWhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, rtlWhCd);
        ssmParam.put("procrTpSupplier", PROCR_TP.SUPPLIER);
        S21SsmEZDResult itemResult = NPAL1070Query.getInstance().getRtlWhInfo(ssmParam);
        if (itemResult.isCodeNormal()) {
            Map<String, Object> rtlWhInfo = (Map<String, Object>) itemResult.getResultObject();
            // START 2019/08/29 M.Naito [QC#51796,MOD]
            String procrTpCd = (String) rtlWhInfo.get("DEF_SRC_PROCR_TP_CD");
//            String rtlWHcd = (String) rtlWhInfo.get("DEF_SRC_RTL_WH_CD");
//            String rtlWhNm = (String) rtlWhInfo.get("DEF_SRC_RTL_WH_NM");
//            String rtlSwhNm = (String) rtlWhInfo.get("DEF_SRC_RTL_SWH_NM");
//            if (!ZYPCommonFunc.hasValue(procrTpCd) || !ZYPCommonFunc.hasValue(rtlWHcd) || !ZYPCommonFunc.hasValue(rtlWhNm) || !ZYPCommonFunc.hasValue(rtlSwhNm)) {
            if (!ZYPCommonFunc.hasValue(procrTpCd)) {
                return null;
            }
            // END 2019/08/29 M.Naito [QC#51796,MOD]
            return rtlWhInfo;
        }
        return null;
    }

    /**
     * Checks for register authority.
     * @param functionList List<String>
     * @return true, if successful
     */
    public static boolean hasRegisterAuthority(List<String> functionList) {
        for (String function : functionList) {
            if (FUNC_EDIT_FSM.equals(function)) {
                return true;
            }
        }
        return false;
    }
    // END 2018/12/03 J.Kim [QC#18224,ADD]
}
