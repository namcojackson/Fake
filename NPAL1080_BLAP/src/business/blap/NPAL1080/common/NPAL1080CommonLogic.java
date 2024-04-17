package business.blap.NPAL1080.common;

import static business.blap.NPAL1080.constant.NPAL1080Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDCSVInFile;
import parts.common.EZDFStringItem;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1080.NPAL1080BL02;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.blap.NPAL1080.NPAL1080Query;
import business.blap.NPAL1080.NPAL1080SMsg;
import business.blap.NPAL1080.NPAL1080_ACMsg;
import business.blap.NPAL1080.NPAL1080_ASMsg;
import business.blap.NPAL1080.constant.NPAL1080Constant;
import business.db.CNTYTMsg;
import business.db.CNTYTMsgArray;
import business.db.CTRYTMsg;
import business.db.CTRYTMsgArray;
import business.db.MDSETMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SWHTMsg;
import business.db.TECH_MSTRTMsg;
import business.db.TECH_MSTRTMsgArray;
import business.file.NPAL1080F00FMsg;
import business.parts.NPZC108001PMsg;

import com.canon.cusa.s21.api.NPZ.NPZC108001.NPZC108001;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ACK_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRT_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS            Yasushi Nomura  Create          N/A
 * 05/06/2016   CSAI            D.Fukaya        Update          QC#7628
 * 12/19/2016   CITS            K.Ogino         Update          QC#15825
 * 02/14/2017   CITS            M.Naito         Update          QC#17456
 * 07/07/2017   CITS            M.Naito         Update          QC#18019
 * 08/22/2017   CITS            R.Shimamoto     Update          QC#19966
 * 10/24/2017   CITS            S.Katsuma       Update          QC#21884
 * 10/25/2017   CITS            S.Katsuma       Update          QC#21896
 * 12/26/2017   CITS            K.Ogino         Update          QC#21908
 * 01/15/2018   CITS            K.Ogino         Update          QC#21088
 * 02/07/2018   CITS            K.Ogino         Update          QC#18922
 * 02/26/2018   CITS            S.Katsuma       Update          QC#24312
 * 03/13/2018   CITS            S.Katsuma       Update          SOL#118(QC#12110)
 * 05/25/2018   CITS            Y.Iwasaki       Update          SOL#135(QC#2366)
 * 06/01/2018   CITS            T.Tokutomi      Update          SOL#135(QC#2366)
 * 07/09/2018   CITS            K.Ogino         Update          QC#24918
 * 07/31/2018   CITS            Y.Iwasaki       Update          QC#27459
 * 08/17/2018   CITS            K.Ogino         Update          QC#27601  
 * 08/17/2018   CITS            T.Tokutomi      Update          QC#26581
 * 08/29/2018   CITS            K.Ogino         Update          QC#28018
 * 10/29/2018   CITS            M.Naito         Update          QC#28965
 * 11/08/2018   CITS            T.Tokutomi      Update          QC#29020
 * 12/07/2018   CITS            T.Tokutomi      Update          QC#25900
 * 12/18/2018   CITS            T.Tokutomi      Update          QC#29299
 * 01/24/2019   CITS            K.Ogino         Update          QC#29988
 * 02/08/2019   CITS            K.Ogino         Update          QC#30284
 * 02/08/2019   CITS            M.Naito         Update          QC#30103
 * 02/13/2020   CITS            Y.Iwasaki       Update          QC#55702,55709
 * 02/14/2020   CITS            K.Ogino         Update          QC#55710
 * 03/05/2020   Fujitsu         R.Nakamura      Update          QC#56103
 * 03/12/2020   Fujitsu         R.Nakamura      Update          QC#56104
 * 2020/11/12   CITS            K.Ogino         Update          QC#57659
 * 2020/12/09   CITS            K.Ogino         Update          QC#57659-2
 * 2021/06/18   CITS            J.Evangelista   Update          QC#58876
 * 2022/06/09   CITS            A.Cullano       Update          QC#60154
 * 2023/03/17   Hitachi         T.Kuroda        Update          QC#61204
 * 2023/10/20   Hitachi         G.Quan          Update          QC#61494
 *</pre>
 */
public class NPAL1080CommonLogic {

    /**
     * Create Pulldown RequisitionType
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequisitionTypeForScreenEntry(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqTpCd_CD.clear();
        cMsg.prchReqTpDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_REQUEST, PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_RETURN, PRCH_REQ_REC_TP.TECH_RETURN);
        ssmParam.put(DB_PARAM_SCR_ENT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().getRequisitionTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_CD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpDescTxt_DI.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_DESC_TXT));

                ZYPEZDItemValueSetter.setValue(cMsg.X.no(i).prchReqTpCd_X1, (String) recode.get(DB_COLUMN_PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.X.no(i).prchReqRecTpCd_X1, (String) recode.get(DB_COLUMN_PRCH_REQ_REC_TP_CD));
                cMsg.X.setValidCount(i + 1);

                ZYPEZDItemValueSetter.setValue(sMsg.X.no(i).prchReqTpCd_X1, cMsg.X.no(i).prchReqTpCd_X1);
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(i).prchReqRecTpCd_X1, cMsg.X.no(i).prchReqRecTpCd_X1);
                sMsg.X.setValidCount(i + 1);
                if (i >= cMsg.prchReqTpCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Create Pulldown RequisitionType
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDownRequisitionType(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        cMsg.prchReqTpCd_CD.clear();
        cMsg.prchReqTpDescTxt_DI.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_REQUEST, PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_RETURN, PRCH_REQ_REC_TP.TECH_RETURN);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().getRequisitionTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_CD.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpDescTxt_DI.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_TP_DESC_TXT));

                ZYPEZDItemValueSetter.setValue(cMsg.X.no(i).prchReqTpCd_X1, (String) recode.get(DB_COLUMN_PRCH_REQ_TP_CD));
                ZYPEZDItemValueSetter.setValue(cMsg.X.no(i).prchReqRecTpCd_X1, (String) recode.get(DB_COLUMN_PRCH_REQ_REC_TP_CD));
                cMsg.X.setValidCount(i + 1);

                ZYPEZDItemValueSetter.setValue(sMsg.X.no(i).prchReqTpCd_X1, cMsg.X.no(i).prchReqTpCd_X1);
                ZYPEZDItemValueSetter.setValue(sMsg.X.no(i).prchReqRecTpCd_X1, cMsg.X.no(i).prchReqRecTpCd_X1);
                sMsg.X.setValidCount(i + 1);
                if (i >= cMsg.prchReqTpCd_CD.length()) {
                    break;
                }
            }
        }
    }

    /**
     * @param sMsg NPAL1080SMsg
     * @param prchReqTpCd String
     * @return prchReqTpCd String
     */
    public static String getPrchReqRecTpCdFromReqTpCd(NPAL1080SMsg sMsg, String prchReqTpCd) {
        for (int i = 0; i < sMsg.X.getValidCount(); i++) {
            if (prchReqTpCd.equals(sMsg.X.no(i).prchReqTpCd_X1.getValue())) {
                return sMsg.X.no(i).prchReqRecTpCd_X1.getValue();
            }
        }
        return null;
    }

    /**
     * Get PulldownData LineType
     * @param sMsg NPAL1080CMsg
     * @param glblCmpyCd String
     */
    public static void getPullDownDataLineTypeForScreenEntry(NPAL1080SMsg sMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        sMsg.prchReqLineTpCd_D1.clear();
        sMsg.prchReqLineTpNm_D1.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_REQUEST, PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_RETURN, PRCH_REQ_REC_TP.TECH_RETURN);
        ssmParam.put(DB_PARAM_SCR_ENT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().getLineTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map recode = resultList.get(i);

                ZYPEZDItemValueSetter.setValue(sMsg.prchReqLineTpCd_D1.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_LINE_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqLineTpNm_D1.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_LINE_TP_DESC_TXT));

                if (i >= sMsg.prchReqLineTpCd_D1.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Get Request Receiving Date And Time Pulldown List By RequisitionType
     * QC:7389
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    public static void getRequestReceivingDateAndTimePulldownListByRequisitionType(NPAL1080SMsg sMsg, String glblCmpyCd) {
        
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DS_COND_CONST_GRP_ID, DS_COND_CONST_GRP_ID);
        ssmParam.put(DB_PARAM_SCR_ENT_AVAL_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put("prchReqRecTpCd", sMsg.prchReqTpCd_SE);

        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().getRequestReceivingDateAndTimePulldownListByRequisitionType(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> searchOptionList = (List<Map>) result.getResultObject();
            
            // set valid count.
            sMsg.D.setValidCount(searchOptionList.size());

            for (int i = 0; i < searchOptionList.size(); i++) {
                Map recode = searchOptionList.get(i);

                if (i == 0 ){
                    ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_SE, (String) recode.get(DB_COLUMN_DS_COND_CONST_CD));
                    //ZYPEZDItemValueSetter.setValue(cMsg.dsCondConstCd_SE, (String) recode.get(DB_COLUMN_DS_COND_CONST_CD));
                }
                //Pull down
                ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_CD.no(i), (String) recode.get(DB_COLUMN_DS_COND_CONST_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_DI.no(i), (String) recode.get(DB_COLUMN_DS_COND_CONST_VAL_TXT_03));
                //Date & Time Calculation Setting
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).dsCondConstCd, (String) recode.get(DB_COLUMN_DS_COND_CONST_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).dsCondConstValTxt_02, (String) recode.get(DB_COLUMN_DS_COND_CONST_VAL_TXT_02));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).dsCondConstValTxt_03, (String) recode.get(DB_COLUMN_DS_COND_CONST_VAL_TXT_03));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).dsCondConstValTxt_06, (String) recode.get(DB_COLUMN_DS_COND_CONST_VAL_TXT_06));
                ZYPEZDItemValueSetter.setValue(sMsg.D.no(i).dsCondConstValTxt_09, (String) recode.get(DB_COLUMN_DS_COND_CONST_VAL_TXT_09));
            }

            if (!ZYPCommonFunc.hasValue(sMsg.D.no(0).dsCondConstValTxt_03.getValue())){
                // in case of Premium Rush
                defaultRequestDateByCalcualtionSettingInCaseOfDisplayTimeIsBlank(sMsg);
            } else {
                // START 2023/10/20 G.Quan [QC#61494, MOD]
                //defaultRequestDateByCalcualtionSetting(sMsg, glblCmpyCd);
                defaultRequestDateByCalcualtionSetting(sMsg, glblCmpyCd, false);
                // END 2023/10/20 G.Quan [QC#61494, MOD]
            }
            
        } else {
            sMsg.rqstRcvDt_H1.clear();
        }
    }
    
    /**
     * Default Request Date By Calculation Setting in case of display time is blank.
     * QC:7389
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg 
     */
    public static void defaultRequestDateByCalcualtionSettingInCaseOfDisplayTimeIsBlank(NPAL1080SMsg sMsg) {
        
        try {
            // 1st row
            int rowNum = 0;
            // get current time
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            Date dt = df.parse(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmm"));
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            int currentMinute = cal.get(Calendar.MINUTE);

            // if default additional time is null
            int addHour;
            if (!ZYPCommonFunc.hasValue(sMsg.D.no(rowNum).dsCondConstValTxt_09.getValue())) {
                addHour = 0;
            } else {
                addHour = Integer.parseInt(sMsg.D.no(rowNum).dsCondConstValTxt_09.getValue());
            }

            // calculate default request
            cal.add(Calendar.HOUR, addHour);
            if (currentMinute > 30) {
                cal.set(Calendar.MINUTE, 00);
                cal.add(Calendar.HOUR, 1);
            } else {
                cal.set(Calendar.MINUTE, 30);
            }

            // set calculated date.
            SimpleDateFormat sfdDate = new SimpleDateFormat("yyyyMMdd", Locale.US);
            sMsg.rqstRcvDt_H1.setValue(sfdDate.format(cal.getTime()));

            // set calculated time.
            SimpleDateFormat sfdTime = new SimpleDateFormat("h:mm a", Locale.US);
            //cMsg.rqstRcvDtTxt_DI.no(rowNum).setValue(sfdTime.format(cal.getTime()));
            sMsg.rqstRcvDtTxt_DI.no(rowNum).setValue(sfdTime.format(cal.getTime()));

            // QC#57659. Mod QC#57659-2
            // set calculated time for Scheduled Delivery.
            SimpleDateFormat sfdTm = new SimpleDateFormat("Hmm", Locale.US);
            sMsg.xxDtTm_H1.setValue(sfdTm.format(cal.getTime()));
            splitHourMinute(sMsg.xxDtTm_H1, sMsg.rqstRcvDtTxt_SL, sMsg.xxDtTm_H1.getValue());

            // Shipping Service Level (Req Service Level) QC#14729
            if (ZYPCommonFunc.hasValue(sMsg.D.no(rowNum).dsCondConstValTxt_06)) {
                ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_SE, sMsg.D.no(rowNum).dsCondConstValTxt_06.getValue());
            }

        } catch (ParseException e) {
            return;
        }
    }
    
    /**
     * Default Request Date By Calculation Setting
     * QC:7389
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg 
     * @param flg 
     */
    public static void defaultRequestDateByCalcualtionSetting(NPAL1080SMsg sMsg, String glblCmpyCd, boolean requestServiceLevelFlg) {
        
        try {
            // get sales date
            // START 2023/10/20 G.Quan [QC#61494, ADD]
            String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd, BUSINESS_APPLICATION_ID);
            // END 2023/10/20 G.Quan [QC#61494, ADD]
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            // START 2023/10/20 G.Quan [QC#61494, MOD]
//            Date dt = df.parse(ZYPDateUtil.getSalesDate(glblCmpyCd));
            Date dt = df.parse(salesDate);
            // END 2023/10/20 G.Quan [QC#61494, MOD]
            Calendar cal = Calendar.getInstance();
            cal.setTime(dt);
            // END 2023/10/20 G.Quan [QC#61494, ADD]
            // Order Creation Day of Week
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            // END 2023/10/20 G.Quan [QC#61494, ADD]

            // if default additional time is null
            int addDate = 0;
            // get additional date by selected value
            for (int rowNum = 0; rowNum < sMsg.D.getValidCount(); rowNum++) {
                if (sMsg.dsCondConstCd_SE.getValue().equals(sMsg.D.no(rowNum).dsCondConstCd.getValue())) {

                    // START 2023/10/20 G.Quan [QC#61494, MOD]
                    // Shipping Service Level (Req Service Level) QC#14729
//                    if (ZYPCommonFunc.hasValue(sMsg.D.no(rowNum).dsCondConstValTxt_06)) {
                    if (ZYPCommonFunc.hasValue(sMsg.D.no(rowNum).dsCondConstValTxt_06) && !requestServiceLevelFlg) {
                    // END 2023/10/20 G.Quan [QC#61494, MOD]
                        ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_SE, sMsg.D.no(rowNum).dsCondConstValTxt_06.getValue());
                    }

                    // if default additional date is null
                    if (!ZYPCommonFunc.hasValue(sMsg.D.no(rowNum).dsCondConstValTxt_02)) {
                        addDate = 0;
                        break;
                    } else {
                        addDate = Integer.parseInt(sMsg.D.no(rowNum).dsCondConstValTxt_02.getValue());
                        break;
                    }
                }
            }

            // START 2023/10/20 G.Quan [QC#61494, MOD]
            // calculate default requests
//            cal.add(Calendar.DAY_OF_MONTH, addDate);

            // set calculated date.
//            SimpleDateFormat sfdDate = new SimpleDateFormat("yyyyMMdd", Locale.US);
//            sMsg.rqstRcvDt_H1.setValue(sfdDate.format(cal.getTime()));
            // get system time
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            Date date = dateFormat.parse(ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmm"));
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int minute = now.get(Calendar.MINUTE);

            Calendar currentTime = Calendar.getInstance();
            currentTime.set(Calendar.HOUR_OF_DAY, hour);
            currentTime.set(Calendar.MINUTE, minute);
            currentTime.set(Calendar.SECOND, 0);
            currentTime.set(Calendar.MILLISECOND, 0);

            String shipCutOffStart = ZYPCodeDataUtil.getVarCharConstValue(SHIPPING_CUT_OFF_START_TIME, glblCmpyCd);
            String[] shippingCutOffStart = new String[2];
            shippingCutOffStart[0] = shipCutOffStart.substring(0,2);
            shippingCutOffStart[1] = shipCutOffStart.substring(2,4);
            Calendar shippingCutOffStartTime = Calendar.getInstance();
            shippingCutOffStartTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(shippingCutOffStart[0]));
            shippingCutOffStartTime.set(Calendar.MINUTE, Integer.parseInt(shippingCutOffStart[1]));
            shippingCutOffStartTime.set(Calendar.SECOND, 0);
            shippingCutOffStartTime.set(Calendar.MILLISECOND, 0);

            String shipCutOffEnd = ZYPCodeDataUtil.getVarCharConstValue(SHIPPING_CUT_OFF_END_TIME, glblCmpyCd);
            String[] shippingCutOffEnd = new String[2];
            shippingCutOffEnd[0] = shipCutOffEnd.substring(0,2);
            shippingCutOffEnd[1] = shipCutOffEnd.substring(2,4);
            Calendar shippingCutOffEndTime = Calendar.getInstance();
            shippingCutOffEndTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(shippingCutOffEnd[0]));
            shippingCutOffEndTime.set(Calendar.MINUTE, Integer.parseInt(shippingCutOffEnd[1]));
            shippingCutOffEndTime.set(Calendar.SECOND, 0);
            shippingCutOffEndTime.set(Calendar.MILLISECOND, 0);

            String  rqstRcvDt = salesDate;
            if(PRCH_REQ_TP.RUSH.equals(sMsg.prchReqTpCd_SE.getValue())) {

                if(dayOfWeek >= ZYPDateUtil.WEEK_MONDAY && dayOfWeek <= ZYPDateUtil.WEEK_THURSDAY) {
                    if((currentTime.after(shippingCutOffEndTime) && currentTime.before(shippingCutOffStartTime))
                            || currentTime.equals(shippingCutOffStartTime)) {
                        rqstRcvDt = ZYPDateUtil.addDays(salesDate, addDate);
                    }
                    else {
                        rqstRcvDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, salesDate, 2);
                    }

                } else if (dayOfWeek == ZYPDateUtil.WEEK_FRIDAY) {
                    if(SHPG_SVC_LVL.NEXT_DAY_AIR_E_AM.equals(sMsg.shpgSvcLvlCd_SE.getValue())
                            || SHPG_SVC_LVL.NEXT_DAY_AIR.equals(sMsg.shpgSvcLvlCd_SE.getValue())
                            || SHPG_SVC_LVL.NEXT_DAY_AIR_SAVER.equals(sMsg.shpgSvcLvlCd_SE.getValue())) {
                        if((currentTime.after(shippingCutOffEndTime) && currentTime.before(shippingCutOffStartTime))
                                || currentTime.equals(shippingCutOffStartTime)) {
                            rqstRcvDt = ZYPDateUtil.addDays(salesDate, 3);
                        } else {
                            rqstRcvDt = ZYPDateUtil.addDays(salesDate, 4);
                        }
 
                     } else if (SHPG_SVC_LVL.SATURDAY.equals(sMsg.shpgSvcLvlCd_SE.getValue())) {
                         if((currentTime.after(shippingCutOffEndTime) && currentTime.before(shippingCutOffStartTime))
                                 || currentTime.equals(shippingCutOffStartTime)) {
                             rqstRcvDt = ZYPDateUtil.addDays(salesDate, 1);
                         } else {
                             rqstRcvDt = ZYPDateUtil.addDays(salesDate, 3);
                         }
                     } else {
                         rqstRcvDt = ZYPDateUtil.addDays(salesDate, addDate);
                     }

                } else {
                    if(SHPG_SVC_LVL.NEXT_DAY_AIR_E_AM.equals(sMsg.shpgSvcLvlCd_SE.getValue())
                            || SHPG_SVC_LVL.NEXT_DAY_AIR.equals(sMsg.shpgSvcLvlCd_SE.getValue())
                            || SHPG_SVC_LVL.NEXT_DAY_AIR_SAVER.equals(sMsg.shpgSvcLvlCd_SE.getValue())) {
                        if(dayOfWeek == ZYPDateUtil.WEEK_SATURDAY) {
                            rqstRcvDt = ZYPDateUtil.addDays(salesDate, 3);
                        }else if (dayOfWeek == ZYPDateUtil.WEEK_SUNDAY) {
                            rqstRcvDt = ZYPDateUtil.addDays(salesDate, 2);
                        }
                         
                     } else {
                         rqstRcvDt = ZYPDateUtil.addDays(salesDate, addDate);
                     }
                }
            } else if (PRCH_REQ_TP.STANDARD.equals(sMsg.prchReqTpCd_SE.getValue())
                    || PRCH_REQ_TP.MIN_MAX.equals(sMsg.prchReqTpCd_SE.getValue())) {

                if(SHPG_SVC_LVL.GROUND_SERVICE.equals(sMsg.shpgSvcLvlCd_SE.getValue())) {
                    rqstRcvDt = ZYPDateUtil.addDays(salesDate, addDate);
                    if((currentTime.after(shippingCutOffEndTime) && currentTime.before(shippingCutOffStartTime))
                            || currentTime.equals(shippingCutOffStartTime)) {
                        if(ZYPDateUtil.getDayOfWeek(rqstRcvDt) == ZYPDateUtil.WEEK_SATURDAY 
                                || ZYPDateUtil.getDayOfWeek(rqstRcvDt) == ZYPDateUtil.WEEK_SUNDAY) {
                            rqstRcvDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, rqstRcvDt, 2);
                        }
                    } else {
                        rqstRcvDt = ZYPDateUtil.addDays(rqstRcvDt, 1);
                        if(ZYPDateUtil.getDayOfWeek(rqstRcvDt) == ZYPDateUtil.WEEK_SATURDAY 
                                || ZYPDateUtil.getDayOfWeek(rqstRcvDt) == ZYPDateUtil.WEEK_SUNDAY) {
                            rqstRcvDt = ZYPDateUtil.addBusinessDay(glblCmpyCd, rqstRcvDt, 2);
                        }
                    }
                } else {
                    rqstRcvDt = ZYPDateUtil.addDays(salesDate, addDate);
                }
                
            } else {
                // calculate default requests
                cal.add(Calendar.DAY_OF_MONTH, addDate);

                // set calculated date.
                SimpleDateFormat sfdDate = new SimpleDateFormat("yyyyMMdd", Locale.US);
                //sMsg.rqstRcvDt_H1.setValue(sfdDate.format(cal.getTime()));
                rqstRcvDt = sfdDate.format(cal.getTime());
            }

            sMsg.rqstRcvDt_H1.setValue(rqstRcvDt);
            // END 2023/10/20 G.Quan [QC#61494, MOD]

        } catch (ParseException e) {
            return;
        }
    }
    
    /**
     * Get PulldownData LineType
     * @param sMsg NPAL1080CMsg
     * @param glblCmpyCd String
     */
    public static void getPullDownDataLineType(NPAL1080SMsg sMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        sMsg.prchReqLineTpCd_D2.clear();
        sMsg.prchReqLineTpNm_D2.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_REQUEST, PRCH_REQ_REC_TP.TECH_REQUEST);
        ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_RETURN, PRCH_REQ_REC_TP.TECH_RETURN);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().getLineTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map recode = resultList.get(i);

                ZYPEZDItemValueSetter.setValue(sMsg.prchReqLineTpCd_D2.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_LINE_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqLineTpNm_D2.no(i), (String) recode.get(DB_COLUMN_PRCH_REQ_LINE_TP_DESC_TXT));

                if (i >= sMsg.prchReqLineTpCd_D2.length()) {
                    break;
                }
            }
        }
    }

    /**
     * Get PulldownData Source Type
     * @param sMsg NPAL1080CMsg
     * @param glblCmpyCd String
     */
    public static void getPullDownDataSourceType(NPAL1080SMsg sMsg, String glblCmpyCd) {
        // Clear Pulldown Data
        sMsg.procrTpCd_D1.clear();
        sMsg.procrTpNm_D1.clear();

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PROCR_TP_CD_SUPPLIER, PROCR_TP.SUPPLIER);
        ssmParam.put(DB_PARAM_PROCR_TP_CD_WAREHOUSE, PROCR_TP.WAREHOUSE);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().getSourceTypePulldownList(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();

            for (int i = 0; i < resultList.size(); i++) {
                Map recode = resultList.get(i);

                ZYPEZDItemValueSetter.setValue(sMsg.procrTpCd_D1.no(i), (String) recode.get(DB_COLUMN_PROCR_TP_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.procrTpNm_D1.no(i), (String) recode.get(DB_COLUMN_PROCR_TP_DESC_TXT));

                if (i >= sMsg.procrTpCd_D1.length()) {
                    break;
                }
            }
        }
    }

    /**
     * getTechLocation
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return Map<String, Object>
     */
    public static Map<String, Object> getTechLocation(String glblCmpyCd, String rtlWhCd) {

        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return null;
        }

        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);
        tMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.ctryCd) && ZYPCommonFunc.hasValue(tMsg.postCd)) {
            Map<String, Object> rslt = new HashMap<String, Object>();
            rslt.put("POST_CD", tMsg.postCd.getValue());
            rslt.put("CTRY_CD", tMsg.ctryCd.getValue());
            // Get Location Info!
            return rslt;
        }

        return null;
    }

    /**
     * get int time from string
     * @param time String
     * @return int array
     */
    public static int[] getTimeIntValue(String time) {
        if (time == null) {
            return null;
        }
        String sHh, sMm;
        if (0 <= time.indexOf(":")) {
            String[] temp = time.split(":");
            if (temp.length != 2) {
                return null;
            }
            sHh = temp[0];
            sMm = temp[1];
        } else {
            if ((time.length() != 3) && (time.length() != 4) && (time.length() != 6)) {
                return null;
            }
            if (time.length() == 3) {
                time = "0" + time;
            } else if (time.length() == 6) {
                time = time.substring(0, 4);
            }
            sHh = time.substring(0, 2);
            sMm = time.substring(2, 4);
        }
        int iHh = -1;
        int iMm = -1;
        try {
            iHh = Integer.valueOf(sHh);
        } catch (NumberFormatException e) {
            return null;
        }
        try {
            iMm = Integer.valueOf(sMm);
        } catch (NumberFormatException e) {
            return null;
        }
        if ((iHh < 0) || (23 < iHh)) {
            return null;
        }
        if ((iMm < 0) || (59 < iMm)) {
            return null;
        }
        return new int[] {iHh, iMm };
    }

    /**
     * convert Request Time
     * @param time String 
     * @return String
     */
    public static String convertRequestTime(String time) {

        try {
            SimpleDateFormat sfd = new SimpleDateFormat("h:mm a", Locale.US);
            Date rqstRcvTm = sfd.parse(time);
            sfd = new SimpleDateFormat("HHmm");
            return sfd.format(rqstRcvTm);
        } catch (ParseException e) {
            return "0000";
        }
    }
    
    /**
     * convert Request Time For Search
     * @param time String 
     * @return String
     */
    public static String convertRequestTimeForSearch(String time) {

        try {
            SimpleDateFormat sfd;
            if (time.length() == 4) {
                sfd = new SimpleDateFormat("HHmm");
            } else {
                sfd = new SimpleDateFormat("HHmmss");
            }
            Date rqstRcvTm = sfd.parse(time);
            sfd = new SimpleDateFormat("KK:mm a", Locale.US);
            return sfd.format(rqstRcvTm);
        } catch (ParseException e) {
            return "00:00 AM";
        }
    }

    /**
     * convertTimeSys2Lcl
     * @param date String
     * @param time String
     * @param lclTZId String
     * @return ZYPLocalTimeData
     */
    public static ZYPLocalTimeData convertTimeSys2Lcl(String date, String time, String lclTZId) {
        if ((!ZYPCommonFunc.hasValue(date)) || (!ZYPCommonFunc.hasValue(lclTZId))) {
            return null;
        }
        if (time == null) {
            time = "000000";
        } else if (time.length() == HOUR_MINUTE_STR_LENGTH - 1) {
            time = "0" + time + "00";
        } else if (time.length() == HOUR_MINUTE_STR_LENGTH) {
            time = time + "00";
        }
        return ZYPLocalTimeUtil.convertTimeSys2Lcl(lclTZId, date + time, NPAL1080Constant.TIME_FORMAT_YYYYMMDDHHMMSS);
    }

    /**
     * convertDBTime2DispTime
     * @param time String
     * @return String
     */
    public static String convertDBTime2DispTime(String time) {
        int[] temp = getTimeIntValue(time);
        if (temp == null) {
            return "";
        }
        if (HALF_DAY_HOURS <= temp[0]) {
            temp[0] -= HALF_DAY_HOURS;
        }
        return String.format("%02d:%02d", temp[0], temp[1]);
    }

    /**
     * convertDBTime2DispTimeA
     * @param time String
     * @return String
     */
    public static String convertDBTime2DispTimeA(String time) {
        int[] temp = getTimeIntValue(time);
        if (temp == null) {
            return "";
        }
        if ((0 <= temp[0]) && (temp[0] < HALF_DAY_HOURS)) {
            return String.format("%02d:%02d %s", temp[0], temp[1], TIME_AM);
        }
        return String.format("%02d:%02d %s", temp[0] - HALF_DAY_HOURS, temp[1], TIME_PM);
    }

    /**
     * getTimeAmpmString
     * @param time String
     * @return String
     */
    public static String getTimeAmpmString(String time) {
        int[] temp = getTimeIntValue(time);
        if (temp == null) {
            return "";
        }
        if ((0 <= temp[0]) && (temp[0] < HALF_DAY_HOURS)) {
            return TIME_AM;
        }
        return TIME_PM;
    }

    /**
     * Create Pulldown
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    public static void createPullDown(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd) {
        createPullDownRequisitionTypeForScreenEntry(cMsg, sMsg, glblCmpyCd);
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, cMsg.shpgSvcLvlCd_CD, cMsg.shpgSvcLvlDescTxt_DI);
        // QC#57659
        cMsg.rqstRcvDtTxt_CD.no(0).setValue("AM");
        cMsg.rqstRcvDtTxt_CD.no(1).setValue("PM");
        cMsg.rqstRcvDtTxt_NM.no(0).setValue("AM");
        cMsg.rqstRcvDtTxt_NM.no(1).setValue("PM");
    }

    /**
     * Init Pulldown data
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    public static void initPullDownData(NPAL1080SMsg sMsg, String glblCmpyCd) {
        getPullDownDataLineTypeForScreenEntry(sMsg, glblCmpyCd);
        getPullDownDataLineType(sMsg, glblCmpyCd);
        getPullDownDataSourceType(sMsg, glblCmpyCd);
    }

    /**
     * @param shipToCustCd String
     * @param glblCmpyCd String
     * @return SHIP_TO_CUST
     */
    public static SHIP_TO_CUSTTMsg getShipToCust(String shipToCustCd, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            return null;
        }

        SHIP_TO_CUSTTMsg inMsg = new SHIP_TO_CUSTTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("shipToCustCd01", shipToCustCd);
        inMsg.setMaxCount(0);
        inMsg.setSQLID("004");

        SHIP_TO_CUSTTMsgArray outMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (outMsgArray.length() >= 1) {
            return (SHIP_TO_CUSTTMsg) outMsgArray.get(0);
        } else {
            return null;
        }
    }

    /**
     * @param ctryCd String
     * @param glblCmpyCd String
     * @return CTRY
     */
    public static CTRYTMsg getCtry(String ctryCd, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(ctryCd)) {
            return null;
        }

        CTRYTMsg inMsg = new CTRYTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("ctryCd01", ctryCd);
        inMsg.setMaxCount(0);
        inMsg.setSQLID("002");

        CTRYTMsgArray outMsgArray = (CTRYTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (outMsgArray.length() >= 1) {
            return (CTRYTMsg) outMsgArray.get(0);
        } else {
            return null;
        }
    }

    /**
     * @param cntyPk BigDecimal
     * @param glblCmpyCd String
     * @return CNTY
     */
    public static CNTYTMsg getCnty(BigDecimal cntyPk, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(cntyPk)) {
            return null;
        }

        CNTYTMsg inMsg = new CNTYTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("cntyPk01", cntyPk);
        inMsg.setMaxCount(0);
        inMsg.setSQLID("003");

        CNTYTMsgArray outMsgArray = (CNTYTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

        if (outMsgArray.length() >= 1) {
            return (CNTYTMsg) outMsgArray.get(0);
        } else {
            return null;
        }
    }

    /**
     * @param prchReqTpCd String
     * @param glblCmpyCd String
     * @return RequisitionRecordTypeCode
     */
    public static String findRequisitionRecordTypeCode(String prchReqTpCd, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(prchReqTpCd)) {
            return null;
        }
        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("prchReqTpCd", prchReqTpCd);
        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findRequisitionRecordTypeCode(ssmParam);

        String prchReqRecTpCd = null;
        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            if (resultList.size() == 1) {
                prchReqRecTpCd = (String) resultList.get(0).get(DB_COLUMN_PRCH_REQ_REC_TP_CD);
            }
        }
        return prchReqRecTpCd;
    }

    /**
     * @param techNm String
     * @param glblCmpyCd String
     * @return TechTocCode
     */
    public static String findTechTocCode(String techNm, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(techNm)) {
            return null;
        }

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put("techNm", techNm);

        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findTechTocCode(ssmParam);

        String techTocCd = null;
        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            if (resultList.size() == 1) {
                techTocCd = (String) resultList.get(0).get("TECH_TOC_CD");
            }
        }
        return techTocCd;
    }

    /**
     * @param cMsg NPAL1080CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean setTechTocCodeAndName(NPAL1080CMsg cMsg, String glblCmpyCd) {

        if (!ZYPCommonFunc.hasValue(cMsg.rqstTechTocCd_H1) && !ZYPCommonFunc.hasValue(cMsg.techNm_H1)) {
            cMsg.rqstTechTocCd_H1.setErrorInfo(1, NPAM1517E, new String[] {"Technician Code", "Technician Name" });
            cMsg.techNm_H1.setErrorInfo(1, NPAM1517E, new String[] {"Technician Code", "Technician Name" });
            return false;
        }

        String salseDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        if (ZYPCommonFunc.hasValue(cMsg.rqstTechTocCd_H1)) {
            ssmParam.put("techTocCd", cMsg.rqstTechTocCd_H1.getValue());
        } else {
            ssmParam.put("techNm", cMsg.techNm_H1.getValue());
        }

        ssmParam.put(DB_PARAM_SALES_DATE, salseDate);
        ssmParam.put(DB_PARAM_EFF_THRU_DT_DEFALUT, MAX_DATE);

        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findTechTocCodeAndName(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            if (resultList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.rqstTechTocCd_H1, (String) resultList.get(0).get("TECH_TOC_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.techNm_H1, (String) resultList.get(0).get("TECH_NM"));
                return true;
            } else {
                cMsg.rqstTechTocCd_H1.setErrorInfo(1, NPAM1518E);
                cMsg.techNm_H1.setErrorInfo(1, NPAM1518E);
                return false;
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.rqstTechTocCd_H1)) {
            cMsg.rqstTechTocCd_H1.setErrorInfo(1, NPAM1361E, new String[] {"Technician Code" });
        } else {
            cMsg.rqstTechTocCd_H1.setErrorInfo(1, NPAM1361E, new String[] {"Technician Name" });
        }
        return false;
    }

    /**
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    public static void recalculateTotalCost(NPAL1080SMsg sMsg, String glblCmpyCd) {
        BigDecimal total = BigDecimal.ZERO;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (sMsg.A.no(i).xxNewRowNum.getValueInt() == -1) {
                continue;
            }
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineSubNum_D1) && BigDecimal.ZERO.compareTo(sMsg.A.no(i).prchReqLineSubNum_D1.getValue()) != 0) {
                continue;
            }
            if (PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(i).prchReqLineStsCd_D1.getValue())) {
                continue;
            }
            NLXC001001GetInventoryItemCostBean param = new NLXC001001GetInventoryItemCostBean();
            param.setGlblCmpyCd(glblCmpyCd);
            param.setInvtyLocCd(sMsg.A.no(i).destInvtyLocCd_D1.getValue());
            param.setMdseCd(sMsg.A.no(i).mdseCd_D1.getValue());
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqQty_D1)) {
                param.setQty(sMsg.A.no(i).prchReqQty_D1.getValue());
            } else {
                param.setQty(BigDecimal.ONE);
            }
            param = NLXC001001GetInventoryItemCost.getInventoryItemCost(param);
            if (param.getTotPrcAmt() != null) {
                total = total.add(param.getTotPrcAmt());
            }
        }
        ZYPEZDItemValueSetter.setValue(sMsg.thisMthTotStdCostAmt_D2, total);
    }

    /**
     * @param asMsg NPAL1080_ASMsg
     * @return true if given line is checked
     */
    public static boolean isChecked(NPAL1080_ASMsg asMsg) {
        return ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_D1.getValue());
    }

    /**
     * @param acMsg NPAL1080_ACMsg
     * @return true if given line is checked
     */
    public static boolean isChecked(NPAL1080_ACMsg acMsg) {
        return ZYPConstant.CHKBOX_ON_Y.equals(acMsg.xxChkBox_D1.getValue());
    }

    /**
     * Copy footer comment to each checked lines.
     * @param sMsg NPAL1080SMsg
     */
    public static void copyPrchReqCmntTxtToLines(NPAL1080SMsg sMsg) {
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NPAL1080_ASMsg line = sMsg.A.no(i);
            if (!NPAL1080CommonLogic.isChecked(line)) {
                continue;
            }

            // 2.
            if (ZYPCommonFunc.hasValue(sMsg.prchReqLineCmntTxt_D2)) {
                // copy comment to checked lines
                setValue(line.prchReqLineCmntTxt_D1, sMsg.prchReqLineCmntTxt_D2);
            }
        }
    }

    /**
     * <pre>
     * Join WH Code and SWH Code
     * </pre>
     * @param rtlWhCdItem EZDSStringItem
     * @param rtlSwhCdItem EZDSStringItem
     * @return WH Code + SWH Code
     */
    public static String joinRtlWhCdAndSwhCd(EZDSStringItem rtlWhCdItem, EZDSStringItem rtlSwhCdItem) {
        String rtlWhCd = "";
        String rtlSwhCd = "";

        if (ZYPCommonFunc.hasValue(rtlWhCdItem)) {
            rtlWhCd = rtlWhCdItem.getValue();
        }
        if (ZYPCommonFunc.hasValue(rtlSwhCdItem)) {
            rtlSwhCd = rtlSwhCdItem.getValue();
        }

        return rtlWhCd + rtlSwhCd;
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    public static void copyFromSMsgOntoCmsg(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTs_H1, sMsg.xxRqstTs_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRqstTz_H1, sMsg.xxRqstTz_H1);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_H1, sMsg.prchReqNum_H1);
        // Tech Request Type
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SE, sMsg.prchReqTpCd_SE);
        // Header Status
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsDescTxt_H1, sMsg.prchReqStsDescTxt_H1);
        // Approval Status
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsDescTxt_H1, sMsg.prchReqApvlStsDescTxt_H1);
        // Date Created
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratDt_H1, sMsg.prchReqCratDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_H1, sMsg.xxDtNm_H1);
        // Date & Time Needed
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDt_H1, sMsg.rqstRcvDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtNm_H2, sMsg.xxDtNm_H2);
        ZYPEZDItemValueSetter.setValue(cMsg.dsCondConstCd_SE, sMsg.dsCondConstCd_SE);
        
        for (int i = 0; i < sMsg.dsCondConstCd_CD.length(); i++){
            if (ZYPCommonFunc.hasValue(sMsg.dsCondConstCd_CD.no(i))){
                ZYPEZDItemValueSetter.setValue(cMsg.dsCondConstCd_CD.no(i), sMsg.dsCondConstCd_CD.no(i));
                ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_DI.no(i), sMsg.rqstRcvDtTxt_DI.no(i));
            } else {
                break;
            }
        }
        
        // Document Source Type
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSrcTpDescTxt_H1, sMsg.prchReqSrcTpDescTxt_H1);
        // Requester
        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_H1, sMsg.fullPsnNm_H1);
        // Service Request#
        ZYPEZDItemValueSetter.setValue(cMsg.fsrNum_H1, sMsg.fsrNum_H1);
        // Service Request Task#
        ZYPEZDItemValueSetter.setValue(cMsg.svcTaskNum_H1, sMsg.svcTaskNum_H1);
        // Service Request Serial#
        ZYPEZDItemValueSetter.setValue(cMsg.svcMachSerNum_H1, sMsg.svcMachSerNum_H1);
        // Attention To
        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNm_H1, sMsg.ctacPsnNm_H1);
        // Technician Name
        ZYPEZDItemValueSetter.setValue(cMsg.rqstTechTocCd_H1, sMsg.rqstTechTocCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.techNm_H1, sMsg.techNm_H1);
        // Tech WH / SWH
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd_H1, sMsg.destRtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd_H1, sMsg.destRtlSwhCd_H1);
        // Ship To Customer
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H1, sMsg.shipToCustCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, sMsg.dsAcctNm_H1);
        // Req Service Level
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SE, sMsg.shpgSvcLvlCd_SE);
        // Requested Carrier
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd_AC, sMsg.carrCd_AC);
        ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, sMsg.carrNm_H1);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqSavedFlg_H1, sMsg.prchReqSavedFlg_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqStsCd_H1, sMsg.prchReqStsCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlDt_H1, sMsg.prchReqApvlDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCratByPsnCd_H1, sMsg.prchReqCratByPsnCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.openStsFlg_H1, sMsg.openStsFlg_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqApvlStsCd_H1, sMsg.prchReqApvlStsCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqCmntTxt_H1, sMsg.prchReqCmntTxt_H1);

        // WHO BEGIN
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratTs_H1, sMsg.xxRecHistCratTs_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistCratByNm_H1, sMsg.xxRecHistCratByNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdTs_H1, sMsg.xxRecHistUpdTs_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistUpdByNm_H1, sMsg.xxRecHistUpdByNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxRecHistTblNm_H1, sMsg.xxRecHistTblNm_H1);
        // WHO END

        // Additional Header
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H2, sMsg.shipToCustCd_H2);
        // Name
        ZYPEZDItemValueSetter.setValue(cMsg.locNm_H2, sMsg.locNm_H2);
        // Additional Name
        ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm_H2, sMsg.shipToAddlLocNm_H2);
        // Address
        ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_H2, sMsg.xxPopPrm_H2);
        // Post Code
        ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_H2, sMsg.shipToPostCd_H2);
        // City
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_H2, sMsg.shipToCtyAddr_H2);
        // County
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_H2, sMsg.shipToCntyNm_H2);
        // State
        ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_H2, sMsg.shipToStCd_H2);
        // Province
        ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm_H2, sMsg.shipToProvNm_H2);
        // Country
        ZYPEZDItemValueSetter.setValue(cMsg.ctryNm_H2, sMsg.ctryNm_H2);
        // Shipping Instructions
        ZYPEZDItemValueSetter.setValue(cMsg.delyAddlCmntTxt_H2, sMsg.delyAddlCmntTxt_H2);

        // cancel comment
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqLineCmntTxt_D2, sMsg.prchReqLineCmntTxt_D2);

        ZYPEZDItemValueSetter.setValue(cMsg.prchReqRecTpCd_H2, sMsg.prchReqRecTpCd_H2);

        // START 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
        // Tool
        ZYPEZDItemValueSetter.setValue(cMsg.techExpRqstFlg, sMsg.techExpRqstFlg);
        // END 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.hazMatFlg, sMsg.hazMatFlg);
        // QC#57659
        ZYPEZDItemValueSetter.setValue(cMsg.xxDtTm_H1, sMsg.xxDtTm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.rqstRcvDtTxt_SL, sMsg.rqstRcvDtTxt_SL);

        // copy data from SMsg onto CMsg
        ZYPTableUtil.clear(cMsg.A);
        setPagePos(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum_D2.getValueInt() - 1; i < cMsg.xxPageShowToNum_D2.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NPAL1080SMsg
     * @param cMsg NPAL1080CMsg
     */
    public static void copyFromCmsgOntoSmsg(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(sMsg.xxRqstTs_H1, cMsg.xxRqstTs_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxRqstTz_H1, cMsg.xxRqstTz_H1);

        ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum_H1, cMsg.prchReqNum_H1);
        // Tech Request Type
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqTpCd_SE, cMsg.prchReqTpCd_SE);
        // Header Status
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsDescTxt_H1, cMsg.prchReqStsDescTxt_H1);
        // Approval Status
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlStsDescTxt_H1, cMsg.prchReqApvlStsDescTxt_H1);
        // Date Created
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratDt_H1, cMsg.prchReqCratDt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_H1, cMsg.xxDtNm_H1);
        // Date & Time Needed
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt_H1, cMsg.rqstRcvDt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_H2, cMsg.xxDtNm_H2);
        ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_SE, cMsg.dsCondConstCd_SE);

        for (int i = 0; i < cMsg.dsCondConstCd_CD.length(); i++){
            if (ZYPCommonFunc.hasValue(cMsg.dsCondConstCd_CD.no(i))){
                ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_CD.no(i), cMsg.dsCondConstCd_CD.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_DI.no(i), cMsg.rqstRcvDtTxt_DI.no(i));
            } else {
                break;
            }
        }
        // Document Source Type
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpDescTxt_H1, cMsg.prchReqSrcTpDescTxt_H1);
        // Requester
        ZYPEZDItemValueSetter.setValue(sMsg.fullPsnNm_H1, cMsg.fullPsnNm_H1);
        // Service Request#
        ZYPEZDItemValueSetter.setValue(sMsg.fsrNum_H1, cMsg.fsrNum_H1);
        // Service Request Task#
        ZYPEZDItemValueSetter.setValue(sMsg.svcTaskNum_H1, cMsg.svcTaskNum_H1);
        // Service Request Serial#
        ZYPEZDItemValueSetter.setValue(sMsg.svcMachSerNum_H1, cMsg.svcMachSerNum_H1);
        // Attention To
        ZYPEZDItemValueSetter.setValue(sMsg.ctacPsnNm_H1, cMsg.ctacPsnNm_H1);
        // Technician Name
        ZYPEZDItemValueSetter.setValue(sMsg.rqstTechTocCd_H1, cMsg.rqstTechTocCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.techNm_H1, cMsg.techNm_H1);
        // Tech WH / SWH
        ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd_H1, cMsg.destRtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd_H1, cMsg.destRtlSwhCd_H1);
        // Ship To Customer
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_H1, cMsg.shipToCustCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.dsAcctNm_H1, cMsg.dsAcctNm_H1);
        // Req Service Level
        ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_SE, cMsg.shpgSvcLvlCd_SE);
        // Requested Carrier
        ZYPEZDItemValueSetter.setValue(sMsg.carrCd_AC, cMsg.carrCd_AC);
        ZYPEZDItemValueSetter.setValue(sMsg.carrNm_H1, cMsg.carrNm_H1);

        ZYPEZDItemValueSetter.setValue(sMsg.prchReqSavedFlg_H1, cMsg.prchReqSavedFlg_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsCd_H1, cMsg.prchReqStsCd_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlDt_H1, cMsg.prchReqApvlDt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratByPsnCd_H1, cMsg.prchReqCratByPsnCd_H1);

        // Additional Header
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_H2, cMsg.shipToCustCd_H2);
        // Name
        ZYPEZDItemValueSetter.setValue(sMsg.locNm_H2, cMsg.locNm_H2);
        // Additional Name
        ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm_H2, cMsg.shipToAddlLocNm_H2);
        // Address
        ZYPEZDItemValueSetter.setValue(sMsg.xxPopPrm_H2, cMsg.xxPopPrm_H2);
        // Post Code
        ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd_H2, cMsg.shipToPostCd_H2);
        // City
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr_H2, cMsg.shipToCtyAddr_H2);
        // County
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm_H2, cMsg.shipToCntyNm_H2);
        // State
        ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd_H2, cMsg.shipToStCd_H2);
        // Province
        ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm_H2, cMsg.shipToProvNm_H2);
        // Country
        ZYPEZDItemValueSetter.setValue(sMsg.ctryNm_H2, cMsg.ctryNm_H2);
        // Shipping Instructions
        ZYPEZDItemValueSetter.setValue(sMsg.delyAddlCmntTxt_H2, cMsg.delyAddlCmntTxt_H2);

        ZYPEZDItemValueSetter.setValue(sMsg.prchReqRecTpCd_H2, cMsg.prchReqRecTpCd_H2);

        // cancel comment
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqLineCmntTxt_D2, cMsg.prchReqLineCmntTxt_D2);
        // QC#57659
        ZYPEZDItemValueSetter.setValue(sMsg.xxDtTm_H1, cMsg.xxDtTm_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_SL, cMsg.rqstRcvDtTxt_SL);

        if (cMsg.A.getValidCount() == 0) {
            return;
        }
        for (int j = cMsg.xxPageShowFromNum_D2.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum_D2.getValueInt(); j++, k++) {
            EZDMsg.copy(cMsg.A.no(k), null, sMsg.A.no(j), null);
        }
    }

    /**
     * <pre>
     * Set page pos.
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     */
    public static void setPagePos(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg) {
        if ((cMsg.A.length() == 0) || (sMsg.A.length() == 0) || (sMsg.A.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum_D2.setValue(0);
            cMsg.xxPageShowToNum_D2.setValue(0);
            cMsg.xxPageShowOfNum_D2.setValue(0);
            return;
        }
        int startRowCount = 0;
        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum_D2)) {
            startRowCount = cMsg.xxPageShowFromNum_D2.getValueInt();
        }
        int allRowCount = sMsg.A.getValidCount();
        if (startRowCount == 0) {
            cMsg.xxPageShowFromNum_D2.setValue(1);
        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
            // last page
            cMsg.xxPageShowFromNum_D2.setValue(getLastPageStartCount(allRowCount, cMsg.A.length()));
        } else {
            if ((startRowCount % cMsg.A.length()) != 1) {
                startRowCount = startRowCount - (startRowCount % cMsg.A.length()) + 1;
            }
            cMsg.xxPageShowFromNum_D2.setValue(startRowCount);
        }
        if ((cMsg.xxPageShowFromNum_D2.getValueInt() + cMsg.A.length() - 1) < allRowCount) {
            cMsg.xxPageShowToNum_D2.setValue(cMsg.xxPageShowFromNum_D2.getValueInt() + cMsg.A.length() - 1);
        } else {
            cMsg.xxPageShowToNum_D2.setValue(allRowCount);
        }
        cMsg.xxPageShowOfNum_D2.setValue(allRowCount);
    }

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

    /**
     * <pre>
     * Search Detail.
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     * @param copyFlag boolean
     */
    public static void searchDetail(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd, boolean copyFlag) {
        // Create Param
        String prchReqNum = null;
        if (ZYPCommonFunc.hasValue(cMsg.prchReqNum_H1)) {
            prchReqNum = cMsg.prchReqNum_H1.getValue();
        }
        cMsg.clear();
        createPullDown(cMsg, sMsg, glblCmpyCd);
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        if (prchReqNum != null) {
            cMsg.prchReqNum_H1.setValue(prchReqNum);
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_REQUEST, PRCH_REQ_REC_TP.TECH_REQUEST);
            ssmParam.put(DB_PARAM_PRCH_REQ_REC_TP_CD_TECH_RETURN, PRCH_REQ_REC_TP.TECH_RETURN);
            ssmParam.put(DB_PARAM_PRCH_REQ_SRC_TP_CD_INSOURCING, PRCH_REQ_SRC_TP.INSOURCING);
            ssmParam.put(DB_PARAM_PRCH_REQ_NUM, cMsg.prchReqNum_H1);
            ssmParam.put(DB_PARAM_PARENT_LINE_SUB_NUM, PARENT_LINE_SUB_NUM);
            // QC#27601
            ssmParam.put("InsourcingPO", PRCH_REQ_LINE_TP.INSOURCED_PO);
            ssmParam.put("S", PO_ACK_LINE_STS.SHIPPED);
            ssmParam.put("Shipped", ZYPCodeDataUtil.getName(PO_ACK_LINE_STS.class, glblCmpyCd, PO_ACK_LINE_STS.SHIPPED));
            // Execute
            S21SsmEZDResult result = NPAL1080Query.getInstance().search(ssmParam);

            if (result.isCodeNormal()) {
                if (!copyFlag) {
                    ZYPEZDItemValueSetter.setValue(sMsg.prchReqNum_H1, cMsg.prchReqNum_H1);
                    ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum_HD, cMsg.prchReqNum_H1);
                } else {
                    sMsg.prchReqNum_H1.clear();
                    sMsg.prchReqNum_HD.clear();
                    cMsg.prchReqNum_HD.clear();
                }

                // QC#25900 Add.
                boolean includeTool = false;
                boolean includeHazmat = false;

                List<Map> resultMap = (List<Map>) result.getResultObject();
                int addLineCount = 0;
                for (int i = 0; i < resultMap.size(); i++) {
                    if ((sMsg.A.length()) <= i) {
                        break;
                    }
                    Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                    if (i == 0) {
                        //QC:7389
                        //setHeader(sMsg, recode, glblCmpyCd, copyFlag);
                        setHeader(cMsg, sMsg, recode, glblCmpyCd, copyFlag);
                    }

                    // Detail
                    if (!copyFlag || (BigDecimal.ZERO.equals((BigDecimal) recode.get(DB_COLUMN_PRCH_REQ_LINE_SUB_NUM)))) {
                        setDetail(sMsg, recode, addLineCount, glblCmpyCd, copyFlag);
                        addLineCount++;
                    }
                    
                    // QC#25900 check include Tool & Hazmat.
                    if(ZYPConstant.FLG_ON_Y.equals((String) recode.get("TECH_EXP_RQST_FLG"))){
                        includeTool = true;
                    }

                    if(ZYPConstant.FLG_ON_Y.equals((String) recode.get("HAZ_MAT_FLG"))){
                        includeHazmat = true;
                    }
                }

                // QC#25900 set include Tool & Hazmat Flag.
                if(includeTool){
                    ZYPEZDItemValueSetter.setValue(sMsg.techExpRqstFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.techExpRqstFlg, ZYPConstant.FLG_OFF_N);
                }

                if(includeHazmat){
                    ZYPEZDItemValueSetter.setValue(sMsg.hazMatFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.hazMatFlg, ZYPConstant.FLG_OFF_N);
                }

                // START 2017/10/24 S.Katsuma QC#21884 ADD
                if (!copyFlag) {
                    String lastPrchReqLineNum = "";
                    List<Integer> cancIdxListPerLineNum = new ArrayList<Integer>();
                    List<Integer> cancIdxList = new ArrayList<Integer>();
                    boolean cancDtlDelFlag = false;
                    boolean cancOnlyFlag = true;
                    int cancLineCount = 0;
                    for (int i = 0; i < addLineCount; i++) {
                        NPAL1080_ASMsg dtl = sMsg.A.no(i);
                        String prchReqLineNum = dtl.prchReqLineNum_D1.getValue();

                        if (lastPrchReqLineNum.equals(prchReqLineNum) && dtl.prchReqLineSubNum_D1.getValue().equals(BigDecimal.ONE)) {
                            if (PRCH_REQ_LINE_TP.INSOURCED_PO.equals(dtl.prchReqLineTpCd_SE.getValue())) {
                                if ("Canceled".equals(dtl.rwsStsDescTxt_D1.getValue())) {
                                    cancDtlDelFlag = true;
                                    cancIdxListPerLineNum.add(i);
                                } else {
                                    cancOnlyFlag = false;
                                }
                            }
                        } else {
                            if (cancDtlDelFlag) {
                                if (cancOnlyFlag) {
                                    for (int j = 0; j < cancIdxListPerLineNum.size() - 1; j ++) {
                                        cancIdxList.add(cancIdxListPerLineNum.get(j));
                                        cancLineCount++;
                                    }
                                } else {
                                    for (int j = 0; j < cancIdxListPerLineNum.size(); j ++) {
                                        cancIdxList.add(cancIdxListPerLineNum.get(j));
                                        cancLineCount++;
                                    }
                                }
                            }
                            cancDtlDelFlag = false;
                            cancOnlyFlag = true;
                            cancIdxListPerLineNum = new ArrayList<Integer>();
                        }

                        lastPrchReqLineNum = prchReqLineNum;
                        if (i == addLineCount - 1) {
                            if (cancDtlDelFlag) {
                                if (cancOnlyFlag) {
                                    for (int j = 0; j < cancIdxListPerLineNum.size() - 1; j ++) {
                                        cancIdxList.add(cancIdxListPerLineNum.get(j));
                                        cancLineCount++;
                                    }
                                } else {
                                    for (int j = 0; j < cancIdxListPerLineNum.size(); j ++) {
                                        cancIdxList.add(cancIdxListPerLineNum.get(j));
                                        cancLineCount++;
                                    }
                                }
                            }
                            cancDtlDelFlag = false;
                            cancOnlyFlag = true;
                            cancIdxListPerLineNum = new ArrayList<Integer>();
                        }
                    }

                    if (cancIdxList.size() > 0){
                        NPAL1080SMsg sMsg2 = (NPAL1080SMsg)sMsg.clone();
                        sMsg2.A.clear();
                        int sMsg2Idx = 0;
                        for (int i = 0; i < addLineCount; i++) {
                            if (!cancIdxList.contains(i)){
                                EZDMsg.copy(sMsg.A.no(i), null, sMsg2.A.no(sMsg2Idx++), null);
                            }
                        }
                        addLineCount = addLineCount - cancLineCount;
                        sMsg2.A.setValidCount(addLineCount);

                        sMsg.A.clear();
                        for (int i = 0; i < sMsg2.A.getValidCount(); i++) {
                            EZDMsg.copy(sMsg2.A.no(i), null, sMsg.A.no(i), null);
                        }
                    }
                }
                sMsg.A.setValidCount(addLineCount);
                // END 2017/10/24 S.Katsuma QC#21884 ADD
                if (addLineCount >= sMsg.A.length()) {
                    // 999 over
                    cMsg.setMessageInfo(NMAM8181W, new String[] {"" + sMsg.A.length(), "" + sMsg.A.length() });
                }

                // Change RequisitionType
                if (copyFlag) {
                    createPullDownRequisitionTypeForScreenEntry(cMsg, sMsg, glblCmpyCd);
                } else {
                    createPullDownRequisitionType(cMsg, sMsg, glblCmpyCd);
                }

                // QC#29020 Add.
                setUpAddress4sMsg(glblCmpyCd, sMsg);

                copyFromSMsgOntoCmsg(cMsg, sMsg);
                if (!copyFlag) {
                    recalculateTotalCost(sMsg, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.thisMthTotStdCostAmt_D2, sMsg.thisMthTotStdCostAmt_D2);
                }
            } else {
                // not has search result
                cMsg.setMessageInfo(NPAM0089E);
                cMsg.xxPageShowFromNum_D2.clear();
                cMsg.xxPageShowToNum_D2.clear();
                cMsg.xxPageShowOfNum_D2.clear();
                cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
            }
            cMsg.xxDplyTab.setValue(TAB_DETAIL);
        }
    }

    private static boolean has1TimeLoc(Map<String, Object> rec) {
        // Check if any of one-time location info is specified.
        String[] colNames = new String[] {
                DB_COLUMN_SHIP_TO_LOC_NM,
                DB_COLUMN_SHIP_TO_ADDL_LOC_NM,
                DB_COLUMN_SHIP_TO_FIRST_LINE_ADDR,
                DB_COLUMN_SHIP_TO_SCD_LINE_ADDR,
                DB_COLUMN_SHIP_TO_THIRD_LINE_ADDR,
                DB_COLUMN_SHIP_TO_FRTH_LINE_ADDR,
                DB_COLUMN_SHIP_TO_CTY_ADDR,
                DB_COLUMN_SHIP_TO_ST_CD,
                DB_COLUMN_SHIP_TO_PROV_NM,
                DB_COLUMN_SHIP_TO_CNTY_NM,
                DB_COLUMN_SHIP_TO_POST_CD,
                DB_COLUMN_SHIP_TO_CTRY_CD,
                DB_COLUMN_CTAC_PSN_NM
        };
        for (String colName : colNames) {
            String val = (String) rec.get(colName);
            if (ZYPCommonFunc.hasValue(val)) {
                return true;
            }
        }
        return false;
    }

    //QC:7389
    //private static void setHeader(NPAL1080SMsg sMsg, Map<String, Object> map, String glblCmpyCd, boolean copyFlag) {
    private static void setHeader(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, Map<String, Object> map, String glblCmpyCd, boolean copyFlag) {
        // Clear header info
        if (copyFlag) {
            sMsg.prchReqNum_H1.clear();
            sMsg.prchReqNum_HD.clear();
        }
        sMsg.prchReqTpCd_SE.clear();
        sMsg.prchReqStsDescTxt_H1.clear();
        // QC#17456
        sMsg.prchReqStsCd_H1.clear();
        sMsg.prchReqApvlStsDescTxt_H1.clear();
        sMsg.prchReqCratDt_H1.clear();
        sMsg.xxDtNm_H1.clear();
        sMsg.rqstRcvDt_H1.clear();
        // QC#18019
        sMsg.rqstRcvDtTxt_DI.clear();
        sMsg.xxDtNm_H2.clear();
        sMsg.prchReqSrcTpDescTxt_H1.clear();
        sMsg.fullPsnNm_H1.clear();
        sMsg.fsrNum_H1.clear();
        sMsg.svcTaskNum_H1.clear();
        sMsg.svcMachSerNum_H1.clear();
        sMsg.ctacPsnNm_H1.clear();
        sMsg.rqstTechTocCd_H1.clear();
        sMsg.techNm_H1.clear();
        sMsg.destRtlWhCd_H1.clear();
        sMsg.destRtlSwhCd_H1.clear();
        sMsg.shipToCustCd_H1.clear();
        sMsg.dsAcctNm_H1.clear();
        sMsg.shpgSvcLvlCd_SE.clear();
        sMsg.carrNm_H1.clear();
        // QC2366 Update
        sMsg.carrCd_AC.clear();
        sMsg.prchReqCmntTxt_H1.clear();
        sMsg.shipToCustCd_H2.clear();
        sMsg.locNm_H2.clear();
        sMsg.shipToAddlLocNm_H2.clear();
        sMsg.xxPopPrm_H2.clear();
        sMsg.shipToPostCd_H2.clear();
        sMsg.shipToCtyAddr_H2.clear();
        sMsg.shipToCntyNm_H2.clear();
        sMsg.shipToStCd_H2.clear();
        sMsg.shipToProvNm_H2.clear();
        sMsg.ctryNm_H2.clear();
        sMsg.xxRqstTs_H1.clear();
        sMsg.xxRqstTz_H1.clear();
        sMsg.openStsFlg_H1.clear();
        sMsg.prchReqApvlStsCd_H1.clear();
        sMsg.thisMthTotStdCostAmt_D2.clear();
        sMsg.dsCondConstCd_SE.clear();
        sMsg.prchReqNum_HD.clear();
        // START 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
        sMsg.techExpRqstFlg.clear();
        // END 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
        sMsg.hazMatFlg.clear();
        // WHO BEGIN
        sMsg.xxRecHistCratTs_H1.clear();
        sMsg.xxRecHistCratByNm_H1.clear();
        sMsg.xxRecHistUpdTs_H1.clear();
        sMsg.xxRecHistUpdByNm_H1.clear();
        sMsg.xxRecHistTblNm_H1.clear();
        // WHO END

        if (!copyFlag) {
            ZYPEZDItemValueSetter.setValue(sMsg.xxRqstTs_H1, (String) map.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(sMsg.xxRqstTz_H1, (String) map.get("EZUPTIMEZONE"));
        }

        // Tech Request Type
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqTpCd_SE, (String) map.get("PRCH_REQ_TP_CD"));
        String prchReqRecTpCd = (String) map.get("PRCH_REQ_REC_TP_CD");
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqRecTpCd_H2, prchReqRecTpCd);
        if (!copyFlag) {
            // QC#15825
            String postCd = null;
            String ctryCd = null;
            String rtlWhCd = null;
            // QC#55710
            if (PRCH_REQ_TP.TECH_RETURN.equals((String) map.get("PRCH_REQ_TP_CD")) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals((String) map.get("PRCH_REQ_TP_CD"))) {
                rtlWhCd = (String) map.get("SRC_RTL_WH_CD");
            } else {
                rtlWhCd = (String) map.get("DEST_RTL_WH_CD");
            }

            Map<String, Object> locInfo =  getTechLocation(glblCmpyCd, rtlWhCd);
            if (locInfo != null) {
                postCd = (String) locInfo.get("POST_CD");
                ctryCd = (String) locInfo.get("CTRY_CD");
            }

            String lclTZId = null;
            if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
                try {
                    // QC#18922
                    postCd = subStrPostCd(postCd);
                    lclTZId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
                } catch (ZYPLocalTimeException e) {
                    lclTZId = null;
                }
            } else {
                S21InfoLogOutput.println("Address information of the technician is not registered. TECH_TOC_CD = " + (String) map.get("RQST_TECH_TOC_CD"));
            }

            // Header Status
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsDescTxt_H1, (String) map.get("PRCH_REQ_STS_DESC_TXT"));
            // Approval Status
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlStsDescTxt_H1, (String) map.get("PRCH_REQ_APVL_STS_DESC_TXT"));
            // Date Created
            String prchReqCratDt = (String) map.get("PRCH_REQ_CRAT_DT");
            String prchReqCratTm = (String) map.get("PRCH_REQ_CRAT_TM");
            ZYPLocalTimeData lcl = NPAL1080CommonLogic.convertTimeSys2Lcl(prchReqCratDt, prchReqCratTm, lclTZId);
            if ((prchReqCratDt != null) && (prchReqCratTm != null) && (lcl != null)) {
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratDt_H1, lcl.getTime().substring(0, DATE_STR_LENGTH));
                ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_H1, NPAL1080CommonLogic.convertDBTime2DispTimeA(lcl.getTime().substring(DATE_STR_LENGTH, DATE_STR_LENGTH + HOUR_MINUTE_STR_LENGTH)));
            } else if ((prchReqCratDt != null) && (prchReqCratTm != null)) {
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratDt_H1, prchReqCratDt);
                ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_H1, NPAL1080CommonLogic.convertDBTime2DispTimeA(prchReqCratTm));
            } else if (prchReqCratDt != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratDt_H1, prchReqCratDt);
            }
            // Date & Time Needed
            String rqstRcvDt = (String) map.get("RQST_RCV_DT");
            String rqstRcvTm = (String) map.get("RQST_RCV_TM");
            lcl = NPAL1080CommonLogic.convertTimeSys2Lcl(rqstRcvDt, rqstRcvTm, lclTZId);
            if ((rqstRcvDt != null) && (rqstRcvTm != null) && (lcl != null)) {
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt_H1, lcl.getTime().substring(0, DATE_STR_LENGTH));
                ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_H2, NPAL1080CommonLogic.convertDBTime2DispTime(lcl.getTime().substring(DATE_STR_LENGTH, DATE_STR_LENGTH + HOUR_MINUTE_STR_LENGTH)));
                // QC#57659. QC#57659-2
                ZYPEZDItemValueSetter.setValue(sMsg.xxDtTm_H1, lcl.getTime().substring(DATE_STR_LENGTH, DATE_STR_LENGTH + HOUR_MINUTE_STR_LENGTH));
                splitHourMinute(sMsg.xxDtTm_H1, sMsg.rqstRcvDtTxt_SL, sMsg.xxDtTm_H1.getValue());
                //QC:7389
                //ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_SE, NPAL1080CommonLogic.getTimeAmpmString(lcl.getTime().substring(DATE_STR_LENGTH, DATE_STR_LENGTH + HOUR_MINUTE_STR_LENGTH)));
                String convertTime = NPAL1080CommonLogic.convertRequestTimeForSearch(lcl.getTime().substring(DATE_STR_LENGTH, DATE_STR_LENGTH + HOUR_MINUTE_STR_LENGTH));
                ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_SE, convertTime);
                ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_CD.no(0), convertTime);
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_DI.no(0), convertTime);
            } else if ((rqstRcvDt != null) && (rqstRcvTm != null)) {
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt_H1, rqstRcvDt);
                ZYPEZDItemValueSetter.setValue(sMsg.xxDtNm_H2, NPAL1080CommonLogic.convertDBTime2DispTime(rqstRcvTm));
                // QC#57659. QC#57659-2
                ZYPEZDItemValueSetter.setValue(sMsg.xxDtTm_H1, rqstRcvTm);
                splitHourMinute(sMsg.xxDtTm_H1, sMsg.rqstRcvDtTxt_SL, sMsg.xxDtTm_H1.getValue());
                
                //QC:7389
                //ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_SE, NPAL1080CommonLogic.getTimeAmpmString(rqstRcvTm));
                String convertTime = NPAL1080CommonLogic.convertRequestTimeForSearch(rqstRcvTm);
                ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_SE, convertTime);
                ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_CD.no(0), convertTime);
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_DI.no(0), convertTime);
            } else if (rqstRcvDt != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt_H1, rqstRcvDt);
            }
            //QC:7389
            if (PRCH_REQ_APVL_STS.ENTERED.equals((String) map.get("PRCH_REQ_APVL_STS_CD")) && (rqstRcvDt == null) && (rqstRcvTm == null)) {
                NPAL1080CommonLogic.getRequestReceivingDateAndTimePulldownListByRequisitionType(sMsg, glblCmpyCd);
            } else if (PRCH_REQ_APVL_STS.ENTERED.equals((String) map.get("PRCH_REQ_APVL_STS_CD")) && (rqstRcvDt != null) && (rqstRcvTm == null)){
                NPAL1080CommonLogic.getRequestReceivingDateAndTimePulldownListByRequisitionType(sMsg, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDt_H1, rqstRcvDt);
            } else if (PRCH_REQ_APVL_STS.ENTERED.equals((String) map.get("PRCH_REQ_APVL_STS_CD")) && (rqstRcvDt == null) && (rqstRcvTm != null)){
                NPAL1080CommonLogic.getRequestReceivingDateAndTimePulldownListByRequisitionType(sMsg, glblCmpyCd);
                String convertTime = NPAL1080CommonLogic.convertRequestTimeForSearch(rqstRcvTm);
                ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_SE, convertTime);
                ZYPEZDItemValueSetter.setValue(sMsg.dsCondConstCd_CD.no(0), convertTime);
                ZYPEZDItemValueSetter.setValue(sMsg.rqstRcvDtTxt_DI.no(0), convertTime);
            }

            // Document Source Type
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpDescTxt_H1, (String) map.get("PRCH_REQ_SRC_TP_DESC_TXT"));
            // QC#17456
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqSrcTpCd_H1, (String) map.get("PRCH_REQ_SRC_TP_CD"));
            // Requester
            ZYPEZDItemValueSetter.setValue(sMsg.fullPsnNm_H1, (String) map.get("FULL_PSN_NM"));
            // Service Request#
            ZYPEZDItemValueSetter.setValue(sMsg.fsrNum_H1, (String) map.get("FSR_NUM"));
            // Service Request Task#
            ZYPEZDItemValueSetter.setValue(sMsg.svcTaskNum_H1, (String) map.get("SVC_TASK_NUM"));
            // Service Request Serial#
            ZYPEZDItemValueSetter.setValue(sMsg.svcMachSerNum_H1, (String) map.get("SVC_MACH_SER_NUM"));
            // START 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
            ZYPEZDItemValueSetter.setValue(sMsg.techExpRqstFlg, (String) map.get("TECH_EXP_RQST_FLG"));
            // END 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
            // Hazmat
            ZYPEZDItemValueSetter.setValue(sMsg.hazMatFlg, (String) map.get("HAZ_MAT_FLG"));
        }
        // Attention To
        ZYPEZDItemValueSetter.setValue(sMsg.ctacPsnNm_H1, (String) map.get("CTAC_PSN_NM"));
        // Technician Name
        ZYPEZDItemValueSetter.setValue(sMsg.rqstTechTocCd_H1, (String) map.get("RQST_TECH_TOC_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.techNm_H1, (String) map.get("TECH_NM"));
        // Tech WH / SWH
        if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(prchReqRecTpCd)) {
            ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd_H1, (String) map.get("DEST_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd_H1, (String) map.get("DEST_RTL_SWH_CD"));
        } else if (PRCH_REQ_REC_TP.TECH_RETURN.equals(prchReqRecTpCd)) {
            ZYPEZDItemValueSetter.setValue(sMsg.destRtlWhCd_H1, (String) map.get("SRC_RTL_WH_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.destRtlSwhCd_H1, (String) map.get("SRC_RTL_SWH_CD"));
        }
        ZYPEZDItemValueSetter.setValue(sMsg.rtlWhNm_H1, (String) map.get("RTL_WH_NM_1"));
        // Ship To Customer
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_H1, (String) map.get("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.dsAcctNm_H1, (String) map.get("DS_ACCT_NM"));
        // Req Service Level
        ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_SE, (String) map.get("SHPG_SVC_LVL_CD"));
        // Requested Carrier
        ZYPEZDItemValueSetter.setValue(sMsg.carrNm_H1, (String) map.get("CARR_NM"));
        // QC2366 Update
        ZYPEZDItemValueSetter.setValue(sMsg.carrCd_AC, (String) map.get("CARR_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.prchReqCmntTxt_H1, (String) map.get("PRCH_REQ_CMNT_TXT"));

        if (!copyFlag) {
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqSavedFlg_H1, (String) map.get("PRCH_REQ_SAVED_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqStsCd_H1, (String) map.get("PRCH_REQ_STS_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlDt_H1, (String) map.get("PRCH_REQ_APVL_DT"));
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqCratByPsnCd_H1, (String) map.get("PRCH_REQ_CRAT_BY_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.openStsFlg_H1, (String) map.get("OPEN_STS_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqApvlStsCd_H1, (String) map.get("PRCH_REQ_APVL_STS_CD"));
        }

        // Additional Header
        ZYPEZDItemValueSetter.setValue(sMsg.shipToCustCd_H2, sMsg.shipToCustCd_H1);
        if (!copyFlag) {
            ZYPEZDItemValueSetter.setValue(sMsg.delyAddlCmntTxt_H2, (String) map.get("DELY_ADDL_CMNT_TXT"));
        }

        if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd_H2)) {
            // Name
            SHIP_TO_CUSTTMsg data = NPAL1080CommonLogic.getShipToCust(sMsg.shipToCustCd_H2.getValue(), glblCmpyCd);
            if (data != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.locNm_H2, data.locNm);
            }
            // Additional Name
            ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm_H2, (String) map.get(DB_COLUMN_SHIP_TO_ADDL_LOC_NM));
            // Address
            StringBuilder sb = new StringBuilder();
            if (ZYPCommonFunc.hasValue((String) map.get(DB_COLUMN_SHIP_TO_FIRST_LINE_ADDR))) {
                sb.append((String) map.get(DB_COLUMN_SHIP_TO_FIRST_LINE_ADDR));
            }
            if (ZYPCommonFunc.hasValue((String) map.get(DB_COLUMN_SHIP_TO_SCD_LINE_ADDR))) {
                sb.append((String) map.get(DB_COLUMN_SHIP_TO_SCD_LINE_ADDR));
            }
            if (ZYPCommonFunc.hasValue((String) map.get(DB_COLUMN_SHIP_TO_THIRD_LINE_ADDR))) {
                sb.append((String) map.get(DB_COLUMN_SHIP_TO_THIRD_LINE_ADDR));
            }
            if (ZYPCommonFunc.hasValue((String) map.get(DB_COLUMN_SHIP_TO_FRTH_LINE_ADDR))) {
                sb.append((String) map.get(DB_COLUMN_SHIP_TO_FRTH_LINE_ADDR));
            }
            ZYPEZDItemValueSetter.setValue(sMsg.xxPopPrm_H2, sb.toString());
            // Post Code
            ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd_H2, (String) map.get(DB_COLUMN_SHIP_TO_POST_CD));
            // City
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr_H2, (String) map.get(DB_COLUMN_SHIP_TO_CTY_ADDR));
            // County
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm_H2, (String) map.get(DB_COLUMN_SHIP_TO_CNTY_NM));
            // State
            ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd_H2, (String) map.get(DB_COLUMN_SHIP_TO_ST_CD));
            // Province
            ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm_H2, (String) map.get(DB_COLUMN_SHIP_TO_PROV_NM));
            // Country
            CTRYTMsg ctry = NPAL1080CommonLogic.getCtry((String) map.get(DB_COLUMN_SHIP_TO_CTRY_CD), glblCmpyCd);
            if (ctry != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.ctryNm_H2, ctry.ctryNm);
            }
        } else if (has1TimeLoc(map)) {
            // One-time location info are specified.

            // Name
            ZYPEZDItemValueSetter.setValue(sMsg.locNm_H2, (String) map.get(DB_COLUMN_SHIP_TO_LOC_NM));
            // Additional Name
            ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm_H2, (String) map.get(DB_COLUMN_SHIP_TO_ADDL_LOC_NM));
            // Address
            StringBuilder sb = new StringBuilder();
            if (ZYPCommonFunc.hasValue((String) map.get(DB_COLUMN_SHIP_TO_FIRST_LINE_ADDR))) {
                sb.append((String) map.get(DB_COLUMN_SHIP_TO_FIRST_LINE_ADDR));
            }
            if (ZYPCommonFunc.hasValue((String) map.get(DB_COLUMN_SHIP_TO_SCD_LINE_ADDR))) {
                sb.append((String) map.get(DB_COLUMN_SHIP_TO_SCD_LINE_ADDR));
            }
            if (ZYPCommonFunc.hasValue((String) map.get(DB_COLUMN_SHIP_TO_THIRD_LINE_ADDR))) {
                sb.append((String) map.get(DB_COLUMN_SHIP_TO_THIRD_LINE_ADDR));
            }
            if (ZYPCommonFunc.hasValue((String) map.get(DB_COLUMN_SHIP_TO_FRTH_LINE_ADDR))) {
                sb.append((String) map.get(DB_COLUMN_SHIP_TO_FRTH_LINE_ADDR));
            }
            ZYPEZDItemValueSetter.setValue(sMsg.xxPopPrm_H2, sb.toString());
            // Post Code
            ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd_H2, (String) map.get(DB_COLUMN_SHIP_TO_POST_CD));
            // City
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr_H2, (String) map.get(DB_COLUMN_SHIP_TO_CTY_ADDR));
            // County
            ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm_H2, (String) map.get(DB_COLUMN_SHIP_TO_CNTY_NM));
            // State
            ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd_H2, (String) map.get(DB_COLUMN_SHIP_TO_ST_CD));
            // Province
            ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm_H2, (String) map.get(DB_COLUMN_SHIP_TO_PROV_NM));
            // Country
            CTRYTMsg ctry = NPAL1080CommonLogic.getCtry((String) map.get(DB_COLUMN_SHIP_TO_CTRY_CD), glblCmpyCd);
            if (ctry != null) {
                ZYPEZDItemValueSetter.setValue(sMsg.ctryNm_H2, ctry.ctryNm);
            }
        } else {
            SHIP_TO_CUSTTMsg data = NPAL1080CommonLogic.getShipToCust((String) map.get(DB_COLUMN_DEST_INVTY_LOC_CD), glblCmpyCd);
            if (data != null) {
                // Name
                ZYPEZDItemValueSetter.setValue(sMsg.locNm_H2, data.locNm);
                // Additional Name
                ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm_H2, data.addlLocNm);
                // Address
                StringBuilder sb = new StringBuilder();
                if (ZYPCommonFunc.hasValue(data.firstLineAddr)) {
                    sb.append(data.firstLineAddr.getValue());
                }
                if (ZYPCommonFunc.hasValue(data.scdLineAddr)) {
                    sb.append(data.scdLineAddr.getValue());
                }
                if (ZYPCommonFunc.hasValue(data.thirdLineAddr)) {
                    sb.append(data.thirdLineAddr.getValue());
                }
                if (ZYPCommonFunc.hasValue(data.frthLineAddr)) {
                    sb.append(data.frthLineAddr.getValue());
                }
                ZYPEZDItemValueSetter.setValue(sMsg.xxPopPrm_H2, sb.toString());
                // Post Code
                ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd_H2, data.postCd);
                // City
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr_H2, data.ctyAddr);
                // County
                CNTYTMsg cnty = NPAL1080CommonLogic.getCnty(data.cntyPk.getValue(), glblCmpyCd);
                if (cnty != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm_H2, cnty.cntyNm);
                }
                // State
                ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd_H2, data.stCd);
                // Province
                ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm_H2, data.provNm);
                // Country
                CTRYTMsg ctry = NPAL1080CommonLogic.getCtry(data.ctryCd.getValue(), glblCmpyCd);
                if (ctry != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.ctryNm_H2, ctry.ctryNm);
                }
            }
        }
        // WHO BEGIN
        ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratTs_H1, (String)map.get(DB_COLUMN_XX_REC_HIST_CRAT_TS_H1));
        ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistCratByNm_H1, ZYPRecHistUtil.getFullNameForRecHist((String)map.get(DB_COLUMN_XX_REC_HIST_CRAT_BY_NM_H1)));
        ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdTs_H1, (String)map.get(DB_COLUMN_XX_REC_HIST_UPD_TS_H1));
        ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistUpdByNm_H1, ZYPRecHistUtil.getFullNameForRecHist((String)map.get(DB_COLUMN_XX_REC_HIST_UPD_BY_NM_H1)));
        ZYPEZDItemValueSetter.setValue(sMsg.xxRecHistTblNm_H1, (String)map.get(DB_COLUMN_XX_REC_HIST_TBL_NM_H1));
        // WHO END
    }

    private static void setDetail(NPAL1080SMsg sMsg, Map<String, Object> map, int index, String glblCmpyCd, boolean copyFlag) {
        // New Line Flag
        sMsg.A.no(index).xxNewRowNum.setValue(index);
        // Line# Mod QC#21908.
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqNum_D1, (String) map.get(DB_COLUMN_SRC_PRCH_REQ_NUM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineNum_D1, (String) map.get(DB_COLUMN_PRCH_REQ_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineSubNum_D1, (BigDecimal) map.get(DB_COLUMN_PRCH_REQ_LINE_SUB_NUM));

        // Line Type
        // QC#55710
        if (!copyFlag || !(PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()))) {
            for (int i = 0; i < sMsg.prchReqLineTpCd_D2.length(); i++) {
                if (!ZYPCommonFunc.hasValue(sMsg.prchReqLineTpCd_D2.no(i))) {
                    break;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(i), sMsg.prchReqLineTpCd_D2.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(i), sMsg.prchReqLineTpNm_D2.no(i));
            }
        } else {
            for (int i = 0; i < sMsg.prchReqLineTpCd_D1.length(); i++) {
                if (!ZYPCommonFunc.hasValue(sMsg.prchReqLineTpCd_D1.no(i))) {
                    break;
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(i), sMsg.prchReqLineTpCd_D1.no(i));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(i), sMsg.prchReqLineTpNm_D1.no(i));
            }
        }

        // Set Line Type
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_SE, (String) map.get(DB_COLUMN_PRCH_REQ_LINE_TP_CD));

        // Mdse Code
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseCd_D1, (String) map.get(DB_COLUMN_MDSE_CD));
        // Mdse Name
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseDescShortTxt_D1, (String) map.get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
        // Procurement Type
        for (int i = 0; i < sMsg.procrTpCd_D1.length(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.procrTpCd_D1.no(i))) {
                break;
            }
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_CD.no(i), sMsg.procrTpCd_D1.no(i));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpNm_DI.no(i), sMsg.procrTpNm_D1.no(i));
        }
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_SE, (String) map.get(DB_COLUMN_PROCR_TP_CD));
        // WH /Supplier WH Name
        if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
            if (PROCR_TP.WAREHOUSE.equals(sMsg.A.no(index).procrTpCd_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_D1, (String) map.get(DB_COLUMN_SRC_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndNm_D1, (String) map.get(DB_COLUMN_RTL_WH_NM_1));
            } else if (PROCR_TP.SUPPLIER.equals(sMsg.A.no(index).procrTpCd_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_D1, (String) map.get(DB_COLUMN_PRNT_VND_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndNm_D1, (String) map.get(DB_COLUMN_PRNT_VND_NM));
            }
        } else if (PRCH_REQ_REC_TP.TECH_RETURN.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
            if (PROCR_TP.WAREHOUSE.equals(sMsg.A.no(index).procrTpCd_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_D1, (String) map.get(DB_COLUMN_DEST_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndNm_D1, (String) map.get(DB_COLUMN_RTL_WH_NM_2));
            }
        }
        // SWH
        if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
            if (PROCR_TP.WAREHOUSE.equals(sMsg.A.no(index).procrTpCd_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).locNm_D1, (String) map.get(DB_COLUMN_SRC_RTL_SWH_CD));
                // QC#2366 Update
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndCd_D1, (String) map.get(DB_COLUMN_SRC_RTL_SWH_CD));
            } else if (PROCR_TP.SUPPLIER.equals(sMsg.A.no(index).procrTpCd_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).locNm_D1, (String) map.get(DB_COLUMN_LOC_NM));
                // QC#2366 Update
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndCd_D1, (String) map.get("VND_CD"));

            }
        } else if (PRCH_REQ_REC_TP.TECH_RETURN.equals(sMsg.prchReqRecTpCd_H2.getValue())) {
            if (PROCR_TP.WAREHOUSE.equals(sMsg.A.no(index).procrTpCd_SE.getValue())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).locNm_D1, (String) map.get(DB_COLUMN_DEST_RTL_SWH_CD));
                // QC#28018
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndCd_D1, (String) map.get(DB_COLUMN_DEST_RTL_SWH_CD));
            }
        }
        // DEST_INVTY_LOC_CD
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).destInvtyLocCd_D1, (String) map.get(DB_COLUMN_DEST_INVTY_LOC_CD));
        if (!copyFlag) {
            // Line Status
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineStsDescTxt_D1, (String) map.get(DB_COLUMN_PRCH_REQ_LINE_STS_DESC_TXT));
            // SO Status
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsSoLineStsDescTxt_D1, (String) map.get(DB_COLUMN_DS_SO_LINE_STS_DESC_TXT));
            // RWS Status
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsStsDescTxt_D1, (String) map.get(DB_COLUMN_RWS_STS_DESC_TXT));
            // QC#55710
            if (PRCH_REQ_TP.TECH_RETURN.equals((String) map.get(DB_COLUMN_PRCH_REQ_TP_CD)) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals((String) map.get(DB_COLUMN_PRCH_REQ_TP_CD))) {
                if (BigDecimal.ZERO.compareTo((BigDecimal) map.get(DB_COLUMN_PRCH_REQ_LINE_SUB_NUM)) == 0) {
                    // Parent
                    // Req QTY
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqQty_D1, (BigDecimal) map.get(DB_COLUMN_PRCH_REQ_QTY));
                    // Fulfilled Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipQty_D1, (BigDecimal) map.get(DB_COLUMN_SHIP_QTY));
                    // Received Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsPutAwayQty_D1, (BigDecimal) map.get(DB_COLUMN_RWS_PUT_AWAY_QTY));
                } else {
                    // Child
                    // Ord Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ordQty_D1, (BigDecimal) map.get(DB_COLUMN_PRCH_REQ_QTY));
                    // Fulfilled Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipQty_D1, (BigDecimal) map.get(DB_COLUMN_SHIP_QTY));
                    // Received Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsPutAwayQty_D1, (BigDecimal) map.get(DB_COLUMN_RWS_PUT_AWAY_QTY));
                }
            } else {
                if (BigDecimal.ZERO.compareTo((BigDecimal) map.get(DB_COLUMN_PRCH_REQ_LINE_SUB_NUM)) == 0) {
                    // Parent
                    // Req QTY
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqQty_D1, (BigDecimal) map.get(DB_COLUMN_PRCH_REQ_QTY));
                    // Fulfilled Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipQty_D1, (BigDecimal) map.get(DB_COLUMN_SUM_SHIP_QTY));
                    // Received Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsPutAwayQty_D1, (BigDecimal) map.get(DB_COLUMN_SUM_RWS_PUT_AWAY_QTY));
                } else {
                    // Child
                    // Ord Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ordQty_D1, (BigDecimal) map.get(DB_COLUMN_PRCH_REQ_QTY));
                    // Fulfilled Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shipQty_D1, (BigDecimal) map.get(DB_COLUMN_SHIP_QTY));
                    // Received Qty
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsPutAwayQty_D1, (BigDecimal) map.get(DB_COLUMN_RWS_PUT_AWAY_QTY));
                }
            }

            // Cancel QTY
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqCancQty_D1, (BigDecimal) map.get(DB_COLUMN_PRCH_REQ_CANC_QTY));
            // PO#
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poOrdNum_AC, (String) map.get(DB_COLUMN_PO_ORD_NUM));
            // PR#
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqNum_AC, (String) map.get(DB_COLUMN_PRCH_REQ_NUM));
            // SO# Mod QC#21908.
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndSoNum_AC, (String) map.get(DB_COLUMN_SO_NUM));
            // Add Start 2020/03/12 QC#56104
            // Alt SO#
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsRefNum_AC, (String) map.get(DB_COLUMN_ALT_SO_NUM));
            // Add End 2020/03/12 QC#56104
            // START 2021/06/18 J.Evangelista [QC#58876,ADD]
            // Ship Detail Anchor Control Flag
            if (PRCH_REQ_REC_TP.TECH_REQUEST.equals(sMsg.prchReqRecTpCd_H2.getValue())
                    && PROCR_TP.WAREHOUSE.equals(sMsg.A.no(index).procrTpCd_SE.getValue())
                    && ZYPCommonFunc.hasValue((String) map.get(DB_COLUMN_SO_NUM))
                    && isDisplayWhShipDetail(glblCmpyCd, (String) map.get(DB_COLUMN_SRC_RTL_WH_CD))) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxScrItem20Txt_AC, SCR_ITEM_SHIP_DETAIL);
            }
            // END 2021/06/18 J.Evangelista [QC#58876,ADD]
            // RWS#
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rwsNum_AC, (String) map.get(DB_COLUMN_RWS_NUM));
            // START 2021/06/18 J.Evangelista [QC#58876,ADD]
            // Transaction Reference Number
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).trxRefNum_D1, (String) map.get(DB_COLUMN_TRX_REF_NUM));
            // END 2021/06/18 J.Evangelista [QC#58876,ADD]
            // Insourced Qty
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqInsrcQty_D1, (BigDecimal) map.get(DB_COLUMN_PRCH_REQ_INSRC_QTY));
            // Insourced Flag
            if (ZYPConstant.FLG_ON_Y.equals((String) map.get(DB_COLUMN_INSRC_FLG))) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).insrcFlg_D1, (String) map.get(DB_COLUMN_INSRC_FLG));
            }
            // PO Flag
            if (ZYPConstant.FLG_ON_Y.equals((String) map.get(DB_COLUMN_PO_CRAT_FLG))) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poCratFlg_D1, (String) map.get(DB_COLUMN_PO_CRAT_FLG));
            }
            // Freeze Flag
            if (ZYPConstant.FLG_ON_Y.equals((String) map.get(DB_COLUMN_PRCH_REQ_FRZ_FLG))) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqFrzFlg_D1, (String) map.get(DB_COLUMN_PRCH_REQ_FRZ_FLG));
            }
            // Line Comment
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineCmntTxt_D1, (String) map.get(DB_COLUMN_PRCH_REQ_LINE_CMNT_TXT));
            // Ref#
            if (sMsg.A.no(index).prchReqLineSubNum_D1.getValueInt() != 0) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxPopPrm_D1, (String) map.get("ORIG_PRCH_REQ_LINE_NUM"));
            }
            // Actual Service Level
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shpgSvcLvlCd_D1, (String) map.get(DB_COLUMN_SHPG_SVC_LVL_CD));
            // QC#24918
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).shpgSvcLvlDescTxt_D1, (String) map.get(DB_COLUMN_SHPG_SVC_LVL_DESC_TXT));
            // Tracking Number
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).proNum_D1, (String) map.get(DB_COLUMN_PRO_NUM));

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineStsCd_D1, (String) map.get(DB_COLUMN_PRCH_REQ_LINE_STS_CD));
            // START 2018/02/26 S.Katsuma [QC#24312,ADD]
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqBalQty_D1, (BigDecimal) map.get(DB_COLUMN_PRCH_REQ_BAL_QTY));
            // END 2018/02/26 S.Katsuma [QC#24312,ADD]
            // QC#55615
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poSchdRelDt_D1, (String) map.get("PO_SCHD_REL_DT"));
            // WHO BEGIN
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistCratTs_D1, (String)map.get(DB_COLUMN_XX_REC_HIST_CRAT_TS_D1));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistCratByNm_D1, ZYPRecHistUtil.getFullNameForRecHist((String)map.get(DB_COLUMN_XX_REC_HIST_CRAT_BY_NM_D1)));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistUpdTs_D1, (String)map.get(DB_COLUMN_XX_REC_HIST_UPD_TS_D1));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistUpdByNm_D1, ZYPRecHistUtil.getFullNameForRecHist((String)map.get(DB_COLUMN_XX_REC_HIST_UPD_BY_NM_D1)));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistTblNm_D1, (String)map.get(DB_COLUMN_XX_REC_HIST_TBL_NM_D1));
            // WHO END

            // QC#55710
            if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
                int prchReqLineTpCd_CDIdx = 0;
                sMsg.A.no(index).prchReqLineTpCd_CD.clear();
                sMsg.A.no(index).prchReqLineTpNm_DI.clear();
                for (int i = 0; i < sMsg.prchReqLineTpCd_D1.length(); i++) {
                    if (!ZYPCommonFunc.hasValue(sMsg.prchReqLineTpCd_D1.no(i))) {
                        break;
                    }
                    // QC#55710
                    if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) && sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_G)) {
                        if (sMsg.prchReqLineTpCd_D1.no(i).getValue().equals(PRCH_REQ_LINE_TP.LOCAL_RETURN) || sMsg.prchReqLineTpCd_D1.no(i).getValue().equals(PRCH_REQ_LINE_TP.DEFECTIVE_RETURN)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpCd_D1.no(i));
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpNm_D1.no(i));
                            prchReqLineTpCd_CDIdx++;
                        }
                    // QC#55710
                    } else if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) && sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                        if (sMsg.prchReqLineTpCd_D1.no(i).getValue().equals(PRCH_REQ_LINE_TP.USED_LOCAL_RETURN)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpCd_D1.no(i));
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpNm_D1.no(i));
                            prchReqLineTpCd_CDIdx++;
                        }
                    } else if (PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
                        if (sMsg.prchReqLineTpCd_D1.no(i).getValue().equals(PRCH_REQ_LINE_TP.LOCAL_RETURN)) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpCd_D1.no(i));
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpNm_D1.no(i));
                            prchReqLineTpCd_CDIdx++;
                        }
                    } else {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpCd_CD.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpCd_D1.no(i));
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineTpNm_DI.no(prchReqLineTpCd_CDIdx), sMsg.prchReqLineTpNm_D1.no(i));
                        prchReqLineTpCd_CDIdx++;
                    }
                }
            }
        }
    }

    /**
     * @param cMsg NPAL1080CMsg
     * @param glblCmpyCd String
     * @return cMsg
     */
    public static NPAL1080CMsg findTechWhCode(NPAL1080CMsg cMsg, String glblCmpyCd) {

        cMsg.destRtlWhCd_H1.clear();
        cMsg.destRtlSwhCd_H1.clear();

        // Find Technician Location Code
        String techTocCd = NPAL1080CommonLogic.findTechTocCode(cMsg.techNm_H1.getValue(), glblCmpyCd);

        // Find Technician WH Code
        if (techTocCd != null) {
            // Create Param
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_LOC_TP_CD, LOC_TP.TECHNICIAN);
            ssmParam.put(DB_PARAM_TECH_TOC_CD, techTocCd);
            ssmParam.put(DB_PARAM_PRTY_LOC_FLG_Y, ZYPConstant.FLG_ON_Y);

            // Execute
            S21SsmEZDResult result = NPAL1080Query.getInstance().findTechWhCode(ssmParam);

            if (result.isCodeNormal()) {
                List<Map> resultList = (List<Map>) result.getResultObject();
                if (resultList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd_H1, (String) resultList.get(0).get("RTL_WH_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd_H1, (String) resultList.get(0).get("RTL_SWH_CD"));
                }
            }
        }

        return cMsg;
    }

    /**
     * @param glblCmpyCd String
     * @param sMsg NPAL1080SMsg
     * @param index int
     */
    public static void findDefaultWh(String glblCmpyCd, NPAL1080SMsg sMsg, int index) {

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DEST_WH_CD, sMsg.destRtlWhCd_H1.getValue());
        ssmParam.put(DB_PARAM_PROCR_TP_CD_SUPPLIER, PROCR_TP.SUPPLIER);
        // QC#55710
        if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
            ssmParam.put(DB_PARAM_DEST_SWH_CD, sMsg.destRtlSwhCd_H1);
        } else {
            ssmParam.put(DB_PARAM_DEST_SWH_CD, "");
        }

        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findDefaultWh(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            if (resultList.size() == 1) {
                // QC#55710
                if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
                    // START 2017/10/25 S.Katsuma QC#21896 ADD
                    if (!sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                        String rtlWhCd = "";
                        String rtlWhNm = "";
                        String rtlSwhCd = "";

                        // START 2018/10/29 M.Naito [QC#28965,MOD]
                        if (PRCH_REQ_LINE_TP.DEFECTIVE_RETURN.equals(sMsg.A.no(index).prchReqLineTpCd_SE.getValue())) {
                            String defRtrnToRtlWhCd = (String) resultList.get(0).get("DEF_RTRN_TO_RTL_WH_CD");
                            String defRtrnToRtlSwhCd = (String) resultList.get(0).get("DEF_RTRN_TO_RTL_SWH_CD");
                            if (ZYPCommonFunc.hasValue(defRtrnToRtlWhCd)) {
                                rtlWhCd = defRtrnToRtlWhCd;
                                rtlWhNm = (String) resultList.get(0).get("DEF_RTRN_PRNT_VND_NM");
                            }
                            if (ZYPCommonFunc.hasValue(defRtrnToRtlSwhCd)) {
                                rtlSwhCd = defRtrnToRtlSwhCd;
                            }
                        } else {
                            String rswDefRtrnToRtlWhCd = (String) resultList.get(0).get(DB_COLUMN_RSW_DEF_RTRN_TO_RTL_WH_CD);
                            String rswDefRtrnToRtlSwhCd = (String) resultList.get(0).get(DB_COLUMN_RSW_DEF_RTRN_TO_RTL_SWH_CD);
                            if (ZYPCommonFunc.hasValue(rswDefRtrnToRtlWhCd) && ZYPCommonFunc.hasValue(rswDefRtrnToRtlSwhCd)) {
                                rtlWhCd = rswDefRtrnToRtlWhCd;
                                rtlWhNm = (String) resultList.get(0).get(DB_COLUMN_RSW_DEF_RTRN_PRNT_VND_NM);
                                rtlSwhCd = rswDefRtrnToRtlSwhCd;
                            } else {
                                String defRtrnToRtlWhCd = (String) resultList.get(0).get("DEF_RTRN_TO_RTL_WH_CD");
                                String defSrcRtlSwhCd = (String) resultList.get(0).get("DEF_SRC_RTL_SWH_CD");

                                if (ZYPCommonFunc.hasValue(rswDefRtrnToRtlWhCd)) {
                                    rtlWhCd = rswDefRtrnToRtlWhCd;
                                    rtlWhNm = (String) resultList.get(0).get(DB_COLUMN_RSW_DEF_RTRN_PRNT_VND_NM);
                                } else {
                                    if (ZYPCommonFunc.hasValue(defRtrnToRtlWhCd)) {
                                        rtlWhCd = defRtrnToRtlWhCd;
                                        rtlWhNm = (String) resultList.get(0).get("DEF_RTRN_PRNT_VND_NM");
                                    }
                                }
                                if (ZYPCommonFunc.hasValue(rswDefRtrnToRtlSwhCd)) {
                                    rtlSwhCd = rswDefRtrnToRtlSwhCd;
                                } else {
                                    if (ZYPCommonFunc.hasValue(defSrcRtlSwhCd)) {
                                        rtlSwhCd = defSrcRtlSwhCd;
                                    }
                                }
                            }
                        }
                        // END 2018/10/29 M.Naito [QC#28965,MOD]

                        setWhForTechReq(sMsg.A.no(index), rtlWhCd, rtlWhNm, rtlSwhCd);
                    } else if (sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                        setWhForTechReq(sMsg.A.no(index), "", "", "");
                    }
                    // END 2017/10/25 S.Katsuma QC#21896 ADD
                } else {
                    // START 2017/10/25 S.Katsuma QC#21896 ADD
                    if (ZYPCommonFunc.hasValue((String) resultList.get(0).get("DEF_SRC_PROCR_TP_CD"))) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_SE, (String) resultList.get(0).get("DEF_SRC_PROCR_TP_CD"));
                    } else {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_SE, PROCR_TP.SUPPLIER);
                    }
                    // END 2017/10/25 S.Katsuma QC#21896 ADD
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_D1, (String) resultList.get(0).get("DEF_SRC_RTL_WH_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndNm_D1, (String) resultList.get(0).get("DEF_SRC_PRNT_VND_NM"));
                    // for Request
                    // QC#21088
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).locNm_D1, (String) resultList.get(0).get("DEF_SRC_RTL_SWH_CD"));
                }
            } else {
                // START 2017/10/25 S.Katsuma QC#21896 ADD
                // QC#55710
                if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
                    // Set Source Type Default
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_SE, PROCR_TP.WAREHOUSE);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_SE, PROCR_TP.SUPPLIER);
                }
                // END 2017/10/25 S.Katsuma QC#21896 ADD

                sMsg.A.no(index).prntVndCd_D1.clear();
                sMsg.A.no(index).prntVndNm_D1.clear();
                sMsg.A.no(index).locNm_D1.clear();
            }
        }
    }

    /**
     * <pre>
     * Get Preferred Carrier for Tech WH
     * </pre>
     * @param glblCmpyCd String
     * @param sMsg NPAL1080SMsg
     */
    public static void getPrfCarrCd(String glblCmpyCd, NPAL1080SMsg sMsg) {

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DEST_WH_CD, sMsg.destRtlWhCd_H1.getValue());

        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findCarrNm(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            if (resultList.size() == 1) {
                ZYPEZDItemValueSetter.setValue(sMsg.carrNm_H1, (String) resultList.get(0).get("CARR_NM"));
            } else {
                sMsg.carrNm_H1.clear();
            }
        }
    }

    /**
     * <pre>
     * Get Preferred Carrier for Tech WH
     * </pre>
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param index int
     * @return int
     */
    public static int convertIndexFromCmsgOntoSmsg(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, int index) {
        if (index < 0) {
            return index;
        }
        int ret = cMsg.xxPageShowFromNum_D2.getValueInt();
        ret += index - 1;
        if (sMsg.A.getValidCount() <= ret) {
            return -1;
        }
        return ret;
    }

    /**
     * checkWhCode
     * @param cMsg NPAL1080CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkWhCode(NPAL1080CMsg cMsg, String glblCmpyCd) {

        String salseDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DEST_WH_CD, cMsg.destRtlWhCd_H1.getValue());
        ssmParam.put(DB_PARAM_ROW_NUM, 1);
        ssmParam.put(DB_PARAM_SALES_DATE, salseDate);
        ssmParam.put(DB_PARAM_EFF_THRU_DT_DEFALUT, MAX_DATE);
        ssmParam.put(DB_PARAM_TECHNICIAN, LOC_TP.TECHNICIAN);

        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().checkWhCode(ssmParam);

        if (result.isCodeNormal()) {
            List<Map> resultList = (List<Map>) result.getResultObject();
            if (resultList.size() != 0) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * findTechnician
     * @param cMsg NPAL1080CMsg
     * @param glblCmpyCd String
     * @param techTocCd String
     * @return boolean
     */
    public static boolean findTechnician(NPAL1080CMsg cMsg, String glblCmpyCd, String techTocCd) {
        TECH_MSTRTMsg tMsg = new TECH_MSTRTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("techTocCd01",techTocCd);
        
        TECH_MSTRTMsgArray tMsgList = (TECH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if ((tMsgList != null && 0 < tMsgList.getValidCount())) {
            ZYPEZDItemValueSetter.setValue(cMsg.rqstTechTocCd_H1, tMsgList.no(0).techTocCd);
            ZYPEZDItemValueSetter.setValue(cMsg.techNm_H1, tMsgList.no(0).techNm);
        } else {
            return false;
        }
        return true;
    }

    /** QC#19966 Add.
     * checkTechRtnSWH
     * @param cMsg NPAL1080CMsg
     * @param glblCmpyCd String
     * @param idx int
     * @return boolean
     */
    public static boolean checkTechRtnSWH(NPAL1080CMsg cMsg, String glblCmpyCd, int idx) {

    	boolean chkFlg = true;
    	if (!ZYPCommonFunc.hasValue(cMsg.destRtlSwhCd_H1)
    			|| !SWH_R.equals(cMsg.destRtlSwhCd_H1.getValue())
    			// QC#55710
    			|| !(PRCH_REQ_TP.TECH_RETURN.equals(cMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(cMsg.prchReqTpCd_SE.getValue()))) {
    		return chkFlg;
    	}

		if (SWH_NEW.equals(cMsg.A.no(idx).locNm_D1.getValue())) {
			chkFlg = false;
			cMsg.A.no(idx).locNm_D1.setErrorInfo(1, NPZM0301E, new String[] {});
		}

        return chkFlg;
    }

    /**
     * getItem
     * @param glblCmpyCd
     * @param mdseCd
     * @return Map<String, Object>
     */
    public static Map<String, Object> getItem(String glblCmpyCd, String mdseCd) {
        S21SsmEZDResult ssmResult = NPAL1080Query.getInstance().getMdse(glblCmpyCd, mdseCd);

        if (!ssmResult.isCodeNormal()) {
            return null;
        }

        return (Map<String, Object>) ssmResult.getResultObject();
    }

    /**
     * checkPrchAvalFlg
     * @param item
     * @return Boolean
     */
    public static Boolean checkPrchAvalFlg(Map<String, Object> item) {
        String prchAvalFlg = (String) item.get(DB_COLUMN_PRCH_AVAL_FLG);
        if (!ZYPCommonFunc.hasValue(prchAvalFlg)) {
            return null;
        }
        if (ZYPConstant.FLG_ON_Y.equals(prchAvalFlg)) {
            return Boolean.TRUE;
        }
        if (ZYPConstant.FLG_OFF_N.equals(prchAvalFlg)) {
            return Boolean.FALSE;
        }
        return null;
    }

    // START 2017/10/25 S.Katsuma QC#21896 ADD
    /**
     * getItemName
     * @param cMsg
     * @param sMsg
     * @param glblCmpyCd
     * @param idx
     * @return
     */
    public static boolean getItemName(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd, int idx) {

        boolean chkFlg = true;

        S21SsmEZDResult ssmResult = NPAL1080Query.getInstance().getMdseNm(glblCmpyCd, sMsg.A.no(idx).mdseCd_D1.getValue());

        if (ssmResult.isCodeNormal()) {

            List<Map> resultList = (List<Map>) ssmResult.getResultObject();

            if (resultList.size() == 1) {
                if (sMsg.A.no(idx).prchReqLineTpCd_SE.getValue().equals(PRCH_REQ_LINE_TP.USED_LOCAL_RETURN)) {
                    if (ZYPConstant.FLG_OFF_N.equals((String) resultList.get(0).get(DB_COLUMN_RTRN_REQ_PRT_FLG))) {
                        sMsg.A.no(idx).mdseCd_D1.setErrorInfo(1, NPAM1605E, new String[] {sMsg.A.no(idx).mdseCd_D1.getValue() });
                        cMsg.setMessageInfo(NPAM1605E, new String[] {sMsg.A.no(idx).mdseCd_D1.getValue() });
                        chkFlg = false;
                    }
                }

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).mdseDescShortTxt_D1, (String) resultList.get(0).get(DB_COLUMN_MDSE_DESC_SHORT_TXT));
                // QC#55710
                if (PRCH_REQ_TP.TECH_RETURN.equals(sMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(sMsg.prchReqTpCd_SE.getValue())) {
                    if (sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                        if ("NPAL1080Scrn00_GetItemName".equals(cMsg.getScreenAplID())) {
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).prntVndCd_D1, (String) resultList.get(0).get(DB_COLUMN_RTRN_WH_CD));
                            ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).prntVndNm_D1, (String) resultList.get(0).get(DB_COLUMN_RTL_WH_NM));
                        }

                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).locNm_D1, (String) resultList.get(0).get(DB_COLUMN_RTRN_DSPL_TP_CD));
                    }
                }
            }
        } else {

            sMsg.A.no(idx).mdseCd_D1.setErrorInfo(1, NPAM0076E, new String[] {sMsg.A.no(idx).mdseCd_D1.getValue() });
            cMsg.setMessageInfo(NPAM0076E, new String[] {"Item#" });
            chkFlg = false;
        }

        return chkFlg;
    }

    /**
     * @param glblCmpyCd String
     * @param sMsg NPAL1080SMsg
     * @param index int
     */
    public static void findDefaultWhForTechSWHR(String glblCmpyCd, NPAL1080SMsg sMsg, int index) {

        // Create Param
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DEST_WH_CD, sMsg.destRtlWhCd_H1.getValue());
        ssmParam.put(DB_PARAM_PROCR_TP_CD_SUPPLIER, PROCR_TP.SUPPLIER);
        ssmParam.put(DB_PARAM_DEST_SWH_CD, sMsg.destRtlSwhCd_H1);

        // Execute
        S21SsmEZDResult result = NPAL1080Query.getInstance().findDefaultWh(ssmParam);

        String rtlWhCd = sMsg.A.no(index).prntVndCd_D1.getValue();
        String rtlWhNm = sMsg.A.no(index).prntVndNm_D1.getValue();
        String rtlSwhCd = sMsg.A.no(index).locNm_D1.getValue();
        List<Map> resultList = null;
        if (result.isCodeNormal()) {
            resultList = (List<Map>) result.getResultObject();
            if (resultList.size() == 1) {
                String rswDefRtrnToRtlWhCd = (String) resultList.get(0).get(DB_COLUMN_RSW_DEF_RTRN_TO_RTL_WH_CD);
                String rswDefSrcRtlSWhCd = (String) resultList.get(0).get(DB_COLUMN_RSW_DEF_SRC_RTL_SWH_CD);

                if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
                    if (ZYPCommonFunc.hasValue(rswDefRtrnToRtlWhCd)) {
                        rtlWhCd = rswDefRtrnToRtlWhCd;
                        rtlWhNm = (String) resultList.get(0).get(DB_COLUMN_RSW_DEF_RTRN_PRNT_VND_NM);
                    }
                }
                if (!ZYPCommonFunc.hasValue(rtlSwhCd)) {
                    if (ZYPCommonFunc.hasValue(rswDefSrcRtlSWhCd)) {
                        rtlSwhCd = rswDefSrcRtlSWhCd;
                    }
                }
                if (ZYPCommonFunc.hasValue(rtlWhCd) || ZYPCommonFunc.hasValue(rtlSwhCd)) {
                    setWhForTechReq(sMsg.A.no(index), rtlWhCd, rtlWhNm, rtlSwhCd);
                }
                if (ZYPCommonFunc.hasValue(rtlWhCd) && ZYPCommonFunc.hasValue(rtlSwhCd)) {
                    return;
                }
            }
        }

        // Create Param
        ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_DEST_WH_CD, sMsg.destRtlWhCd_H1.getValue());
        ssmParam.put(DB_PARAM_RQST_TECH_TOC_CD, sMsg.rqstTechTocCd_H1.getValue());

        // Execute
        result = NPAL1080Query.getInstance().getTechCustomerWH(ssmParam);

        if (result.isCodeNormal()) {
            resultList = (List<Map>) result.getResultObject();
            if (resultList.size() == 1) {
                String defRtrnToRtlWhCd = (String) resultList.get(0).get("DEF_RTRN_TO_RTL_WH_CD");
                String defRtrnToRtlSwhCd = (String) resultList.get(0).get("DEF_RTRN_TO_RTL_SWH_CD");
                if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
                    if (ZYPCommonFunc.hasValue(defRtrnToRtlWhCd)) {
                        rtlWhCd = defRtrnToRtlWhCd;
                        rtlWhNm = (String) resultList.get(0).get("RTL_WH_NM");
                    }
                }
                if (!ZYPCommonFunc.hasValue(rtlSwhCd)) {
                    if (ZYPCommonFunc.hasValue(defRtrnToRtlSwhCd)) {
                        rtlSwhCd = defRtrnToRtlSwhCd;
                    }
                }
                if (ZYPCommonFunc.hasValue(rtlWhCd) || ZYPCommonFunc.hasValue(rtlSwhCd)) {
                    setWhForTechReq(sMsg.A.no(index), rtlWhCd, rtlWhNm, rtlSwhCd);
                }
                if (ZYPCommonFunc.hasValue(rtlWhCd) && ZYPCommonFunc.hasValue(rtlSwhCd)) {
                    return;
                }
            }
        }
    }

    private static void setWhForTechReq(NPAL1080_ASMsg asMsg, String prntVndCd, String prntVndNm, String locNm) {
        ZYPEZDItemValueSetter.setValue(asMsg.procrTpCd_SE, PROCR_TP.WAREHOUSE);
        ZYPEZDItemValueSetter.setValue(asMsg.prntVndCd_D1, prntVndCd);
        ZYPEZDItemValueSetter.setValue(asMsg.prntVndNm_D1, prntVndNm);
        ZYPEZDItemValueSetter.setValue(asMsg.locNm_D1, locNm);
    }
    // END 2017/10/25 S.Katsuma QC#21896 ADD

    /**
     * QC#18922
     */
    private static String subStrPostCd(String postCd) {
        if (!ZYPCommonFunc.hasValue(postCd)) {
            return null;
        }
        if (postCd.length() > 5) {
            return postCd.substring(0, 5);
        }
        return postCd;
    }

    // START 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
    /**
     * checkPrtItemTpCdTool
     * @param cMsg NPAL1080CMsg
     * @param glblCmpyCd String
     * @param idx int
     * @return boolean
     */
    public static boolean checkPrtItemTpCdTool(NPAL1080CMsg cMsg, String glblCmpyCd, int idx) {

        boolean chkFlg = true;

        MDSETMsg mdseTMsg = new MDSETMsg();
        mdseTMsg.glblCmpyCd.setValue(glblCmpyCd);
        mdseTMsg.mdseCd.setValue(cMsg.A.no(idx).mdseCd_D1.getValue());
        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg != null) {
            if (ZYPCommonFunc.hasValue(mdseTMsg.prtItemTpCd)) {
                if (PRT_ITEM_TP.TOOL.equals(mdseTMsg.prtItemTpCd.getValue())) {
                    chkFlg = false;
                }
            }
        } else {
            chkFlg = false;
        }

        return chkFlg;
    }
    // END 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
    // QC#29988 Order Line Status Chacek Start
    /**
     * isPoDtlCancel
     * @param glblCmpyCd
     * @param prchReqNum
     * @param prchReqLineNum
     * @return true:Exists Open PO
     */
    public static boolean isPoDtlCancel(String glblCmpyCd, String prchReqNum, String prchReqLineNum) {
        return NPAL1080Query.getInstance().isPoDtlCancel(glblCmpyCd, prchReqNum, prchReqLineNum);
    }

    /**
     * isPrDtlCancel
     * @param glblCmpyCd
     * @param prchReqNum
     * @param prchReqLineNum
     * @return true:Exists Open PR
     */
    public static boolean isPrDtlCancel(String glblCmpyCd, String prchReqNum, String prchReqLineNum) {
        return NPAL1080Query.getInstance().isPrDtlCancel(glblCmpyCd, prchReqNum, prchReqLineNum);
    }

    /**
     * isRwsDtlCancel
     * @param glblCmpyCd
     * @param prchReqNum
     * @param prchReqLineNum
     * @return true:Exists Open RWS
     */
    public static boolean isRwsDtlCancel(String glblCmpyCd, String prchReqNum, String prchReqLineNum) {
        return NPAL1080Query.getInstance().isRwsDtlCancel(glblCmpyCd, prchReqNum, prchReqLineNum);
    }
    
    /**
     * isSoDtlCancel
     * @param glblCmpyCd
     * @param prchReqNum
     * @param prchReqLineNum
     * @return true:Exists Open SO
     */
    public static boolean isSoDtlCancel(String glblCmpyCd, String prchReqNum, String prchReqLineNum) {
        return NPAL1080Query.getInstance().isSoDtlCancel(glblCmpyCd, prchReqNum, prchReqLineNum);
    }
    // QC#29988 Order Line Status Chacek End
    // QC#29020 Add method.
    /**
     * setUpAddress4sMsg
     * @param glblCmpyCd String
     * @param sMsg NPAL1080SMsg
     */
    public static void setUpAddress4sMsg(String glblCmpyCd, NPAL1080SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(sMsg.shipToCustCd_H1)) {
            // Get Ship to Address.
            String globalCompanyCode = glblCmpyCd;
            SHIP_TO_CUSTTMsg data = getShipToCust(sMsg.shipToCustCd_H1.getValue(), globalCompanyCode);
            if (data != null) {
                // Name
                ZYPEZDItemValueSetter.setValue(sMsg.locNm_H2, data.locNm);
                // Additional Name
                ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm_H2, data.addlLocNm);
                // Address
                ZYPEZDItemValueSetter.setValue(sMsg.xxPopPrm_H2, data.firstLineAddr.getValue() + data.scdLineAddr.getValue() + data.thirdLineAddr.getValue() + data.frthLineAddr.getValue());
                // Post Code
                ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd_H2, data.postCd);
                // City
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr_H2, data.ctyAddr);
                // County
                CNTYTMsg cnty = getCnty(data.cntyPk.getValue(), globalCompanyCode);
                if (cnty != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm_H2, cnty.cntyNm);
                }
                // State
                ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd_H2, data.stCd);
                // Province
                ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm_H2, data.provNm);
                // Country
                CTRYTMsg ctry = getCtry(data.ctryCd.getValue(), globalCompanyCode);
                if (ctry != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.ctryNm_H2, ctry.ctryNm);
                }
            }
          // START 2019/02/08 M.Naito [QC#30103,MOD]
//          } else {
          } else if (!ZYPCommonFunc.hasValue(sMsg.prchReqNum_H1) || !has1TimeLocForSMsg(sMsg)) {
          // END 2019/02/08 M.Naito [QC#30103,MOD]
            // Get Tech WH Address.
            String globalCompanyCode = glblCmpyCd;
            SHIP_TO_CUSTTMsg data = getShipToCust(joinRtlWhCdAndSwhCd(sMsg.destRtlWhCd_H1, sMsg.destRtlSwhCd_H1), globalCompanyCode);
            if (data != null) {
                // Name
                ZYPEZDItemValueSetter.setValue(sMsg.locNm_H2, data.locNm);
                // Additional Name
                ZYPEZDItemValueSetter.setValue(sMsg.shipToAddlLocNm_H2, data.addlLocNm);
                // Address
                ZYPEZDItemValueSetter.setValue(sMsg.xxPopPrm_H2, data.firstLineAddr.getValue() + data.scdLineAddr.getValue() + data.thirdLineAddr.getValue() + data.frthLineAddr.getValue());
                // Post Code
                ZYPEZDItemValueSetter.setValue(sMsg.shipToPostCd_H2, data.postCd);
                // City
                ZYPEZDItemValueSetter.setValue(sMsg.shipToCtyAddr_H2, data.ctyAddr);
                // County
                CNTYTMsg cnty = getCnty(data.cntyPk.getValue(), globalCompanyCode);
                if (cnty != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.shipToCntyNm_H2, cnty.cntyNm);
                }
                // State
                ZYPEZDItemValueSetter.setValue(sMsg.shipToStCd_H2, data.stCd);
                // Province
                ZYPEZDItemValueSetter.setValue(sMsg.shipToProvNm_H2, data.provNm);
                // Country
                CTRYTMsg ctry = getCtry(data.ctryCd.getValue(), globalCompanyCode);
                if (ctry != null) {
                    ZYPEZDItemValueSetter.setValue(sMsg.ctryNm_H2, ctry.ctryNm);
                }
            }
        }
    }
    
    // QC#29020 Add method.
    /**
     * setUpAddress4cMsg
     * @param glblCmpyCd String
     * @param cMsg NPAL1080SMsg
     */
    public static void setUpAddress4cMsg(String glblCmpyCd, NPAL1080CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_H1)) {
            // Get Ship to Address.
            String globalCompanyCode = glblCmpyCd;
            SHIP_TO_CUSTTMsg data = getShipToCust(cMsg.shipToCustCd_H1.getValue(), globalCompanyCode);
            if (data != null) {
                // Name
                ZYPEZDItemValueSetter.setValue(cMsg.locNm_H2, data.locNm);
                // Additional Name
                ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm_H2, data.addlLocNm);
                // Address
                ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_H2, data.firstLineAddr.getValue() + data.scdLineAddr.getValue() + data.thirdLineAddr.getValue() + data.frthLineAddr.getValue());
                // Post Code
                ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_H2, data.postCd);
                // City
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_H2, data.ctyAddr);
                // County
                CNTYTMsg cnty = getCnty(data.cntyPk.getValue(), globalCompanyCode);
                if (cnty != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_H2, cnty.cntyNm);
                }
                // State
                ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_H2, data.stCd);
                // Province
                ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm_H2, data.provNm);
                // Country
                CTRYTMsg ctry = getCtry(data.ctryCd.getValue(), globalCompanyCode);
                if (ctry != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.ctryNm_H2, ctry.ctryNm);
                }
            }
        // START 2019/02/08 M.Naito [QC#30103,MOD]
//        } else {
        } else if  (!ZYPCommonFunc.hasValue(cMsg.prchReqNum_H1) || !has1TimeLocForCMsg(cMsg)) {
        // END 2019/02/08 M.Naito [QC#30103,MOD]
            // Get Tech WH Address.
            String globalCompanyCode = glblCmpyCd;

            String invtyLocCd = cMsg.destRtlWhCd_H1.getValue() + cMsg.destRtlSwhCd_H1.getValue();

            SHIP_TO_CUSTTMsg data = getShipToCust(invtyLocCd, globalCompanyCode);
            if (data != null) {
                // Name
                ZYPEZDItemValueSetter.setValue(cMsg.locNm_H2, data.locNm);
                // Additional Name
                ZYPEZDItemValueSetter.setValue(cMsg.shipToAddlLocNm_H2, data.addlLocNm);
                // Address
                ZYPEZDItemValueSetter.setValue(cMsg.xxPopPrm_H2, data.firstLineAddr.getValue() + data.scdLineAddr.getValue() + data.thirdLineAddr.getValue() + data.frthLineAddr.getValue());
                // Post Code
                ZYPEZDItemValueSetter.setValue(cMsg.shipToPostCd_H2, data.postCd);
                // City
                ZYPEZDItemValueSetter.setValue(cMsg.shipToCtyAddr_H2, data.ctyAddr);
                // County
                CNTYTMsg cnty = getCnty(data.cntyPk.getValue(), globalCompanyCode);
                if (cnty != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.shipToCntyNm_H2, cnty.cntyNm);
                }
                // State
                ZYPEZDItemValueSetter.setValue(cMsg.shipToStCd_H2, data.stCd);
                // Province
                ZYPEZDItemValueSetter.setValue(cMsg.shipToProvNm_H2, data.provNm);
                // Country
                CTRYTMsg ctry = getCtry(data.ctryCd.getValue(), globalCompanyCode);
                if (ctry != null) {
                    ZYPEZDItemValueSetter.setValue(cMsg.ctryNm_H2, ctry.ctryNm);
                }
            }
        }
    }
    
    // QC#29299 Update.
    /**
     * getProcurmentSource
     * @param glblCmpyCd
     * @param cMsg
     * @param i
     * @return true(OK)/false(Error)
     */
    public static boolean getProcurmentSource(String glblCmpyCd, NPAL1080SMsg sMsg, int i) {

        NPZC108001PMsg apiPMsg = new NPZC108001PMsg();
        setValue(apiPMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        setValue(apiPMsg.mdseCd, sMsg.A.no(i).mdseCd_D1);
        setValue(apiPMsg.invtyLocCd, sMsg.destRtlWhCd_H1.getValue() + sMsg.destRtlSwhCd_H1.getValue());

        NPZC108001 procSrcAPI = new NPZC108001();
        procSrcAPI.execute(apiPMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.isXxMsgId(apiPMsg)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).procrTpCd_SE, apiPMsg.procrTpCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndCd_D1, apiPMsg.srcRtlWhCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_D1, apiPMsg.srcRtlSwhCd);

            // Set Name.Mod QC#30284
            setNameOfVendorOrWh(glblCmpyCd, sMsg, i, true);

        } else {
            sMsg.A.no(i).mdseCd_D1.setErrorInfo(1, apiPMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            return false;
        }

        return true;
    }

    // QC#29299 Add method.
    /**
     * isSourceEmergencyWh
     * @param glblCmpyCd String
     * @param prchReqTpCd String
     * @return true(Emer WH) / false(Not Emer WH)
     */
    public static boolean isSourceEmergencyWh(String glblCmpyCd, String prchReqTpCd) {

        String srcWH = NPAL1080Query.getInstance().getSourceCondition(glblCmpyCd, prchReqTpCd);

        if ("EMER_SRC".equals(srcWH)) {
            return true;
        }

        return false;
    }

    // QC#29299 Add method.
    /**
     * setEmergencyWh
     * @param glblCmpyCd String
     * @param sMsg String
     * @param i int
     */
    public static void setEmergencyWh(String glblCmpyCd, NPAL1080SMsg sMsg, int i) {

        RTL_WHTMsg rtlWh = NPAL1080Query.getInstance().getRtlWH(glblCmpyCd, sMsg.destRtlWhCd_H1.getValue());

        if (ZYPCommonFunc.hasValue(rtlWh.emerSrcProcrTpCd)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).procrTpCd_SE, rtlWh.emerSrcProcrTpCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndCd_D1, rtlWh.emerSrcRtlWhCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_D1, rtlWh.emerSrcRtlSwhCd);
        } else {
            // Not Set Emergency WH. set Default.
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).procrTpCd_SE, rtlWh.defSrcProcrTpCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndCd_D1, rtlWh.defSrcRtlWhCd);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_D1, rtlWh.defSrcRtlSwhCd);
        }

        // Set Name. QC#30284
        setNameOfVendorOrWh(glblCmpyCd, sMsg, i, false);
    }

    // QC#29299 Add method.
    /**
     * setNameOfVendorOrWh
     * Mod QC#30284
     * @param glblCmpyCd String
     * @param sMsg String
     * @param i int
     * @param isErr boolean
     */
    private static void setNameOfVendorOrWh(String glblCmpyCd, NPAL1080SMsg sMsg, int i, boolean isErr) {
        // Get Parent Vendor name when Procurement Type is
        // "Supplier"
        if (PROCR_TP.SUPPLIER.equals(sMsg.A.no(i).procrTpCd_SE.getValue())) {

            S21SsmEZDResult ssmResult = NPAL1080Query.getInstance().getPrntVndNm(glblCmpyCd, sMsg.A.no(i).prntVndCd_D1.getValue());

            if (ssmResult.isCodeNormal()) {

                List<Map> resultList = (List<Map>) ssmResult.getResultObject();

                if (resultList.size() == 1) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndNm_D1, (String) resultList.get(0).get(DB_COLUMN_PRNT_VND_NM));
                    sMsg.A.no(i).locNm_D1.clear();
                }
            } else if (isErr) {
                sMsg.A.no(i).prntVndCd_D1.setErrorInfo(1, NPAM0076E, new String[] {sMsg.A.no(i).prntVndCd_D1.getValue() });
            }

            // Get Warehouse name when Procurement Type is
            // "Warehouse"
        } else {
            S21SsmEZDResult ssmResult = NPAL1080Query.getInstance().getWhNm(glblCmpyCd, sMsg.A.no(i).prntVndCd_D1.getValue());

            if (ssmResult.isCodeNormal()) {

                List<Map> resultList = (List<Map>) ssmResult.getResultObject();

                if (resultList.size() == 1) {

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).prntVndNm_D1, (String) resultList.get(0).get(DB_COLUMN_RTL_WH_NM));
                    // START 2017/10/25 S.Katsuma QC#21896 ADD
                    if (!sMsg.destRtlSwhCd_H1.getValue().equals(NPAL1080Constant.TECH_SWH_CD_R)) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_D1, (String) resultList.get(0).get(DB_COLUMN_RTL_SWH_CD));
                    }
                    // END 2017/10/25 S.Katsuma QC#21896 ADD
                }
            } else if(isErr) {
                sMsg.A.no(i).prntVndCd_D1.setErrorInfo(1, NPAM0076E, new String[] {sMsg.A.no(i).prntVndCd_D1.getValue() });
            }
        }
    }

    // START 2019/02/08 M.Naito [QC#30103,ADD]
    private static boolean has1TimeLocForSMsg(NPAL1080SMsg sMsg) {
        // Name
        if (ZYPCommonFunc.hasValue(sMsg.locNm_H2)) {
            return true;
        }
        // Additional Name
        if (ZYPCommonFunc.hasValue(sMsg.shipToAddlLocNm_H2)) {
            return true;
        }
        // Address
        if (ZYPCommonFunc.hasValue(sMsg.xxPopPrm_H2)) {
            return true;
        }
        // Post Code
        if (ZYPCommonFunc.hasValue(sMsg.shipToPostCd_H2)) {
            return true;
        }
        // City
        if (ZYPCommonFunc.hasValue(sMsg.shipToCtyAddr_H2)) {
            return true;
        }
        // County
        if (ZYPCommonFunc.hasValue(sMsg.shipToCntyNm_H2)) {
            return true;
        }
        // State
        if (ZYPCommonFunc.hasValue(sMsg.shipToStCd_H2)) {
            return true;
        }
        // Province
        if (ZYPCommonFunc.hasValue(sMsg.shipToProvNm_H2)) {
            return true;
        }
        // Country
        if (ZYPCommonFunc.hasValue(sMsg.ctryNm_H2)) {
            return true;
        }
        return false;
    }

    private static boolean has1TimeLocForCMsg(NPAL1080CMsg cMsg) {
        // Name
        if (ZYPCommonFunc.hasValue(cMsg.locNm_H2)) {
            return true;
        }
        // Additional Name
        if (ZYPCommonFunc.hasValue(cMsg.shipToAddlLocNm_H2)) {
            return true;
        }
        // Address
        if (ZYPCommonFunc.hasValue(cMsg.xxPopPrm_H2)) {
            return true;
        }
        // Post Code
        if (ZYPCommonFunc.hasValue(cMsg.shipToPostCd_H2)) {
            return true;
        }
        // City
        if (ZYPCommonFunc.hasValue(cMsg.shipToCtyAddr_H2)) {
            return true;
        }
        // County
        if (ZYPCommonFunc.hasValue(cMsg.shipToCntyNm_H2)) {
            return true;
        }
        // State
        if (ZYPCommonFunc.hasValue(cMsg.shipToStCd_H2)) {
            return true;
        }
        // Province
        if (ZYPCommonFunc.hasValue(cMsg.shipToProvNm_H2)) {
            return true;
        }
        // Country
        if (ZYPCommonFunc.hasValue(cMsg.ctryNm_H2)) {
            return true;
        }
        return false;
    }
    // END 2019/02/08 M.Naito [QC#30103,ADD]

    // Add Start 2020/03/05 QC#56103
    /**
     * clearValueForError
     * @param glblCmpyCd String
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param newTechReqFlg boolean
     */
    public static void clearValueForError(String glblCmpyCd, NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, boolean newTechReqFlg) {

        if (newTechReqFlg) {
            sMsg.prchReqNum_H1.clear();
            sMsg.prchReqNum_HD.clear();
            sMsg.prchReqNum_P2.clear();
            sMsg.prchReqStsCd_H1.clear();
            sMsg.prchReqStsDescTxt_H1.clear();
            sMsg.prchReqApvlStsCd_H1.clear();
            sMsg.prchReqApvlStsDescTxt_H1.clear();
            sMsg.xxDtNm_H1.clear();

            clearDetailValueForError(glblCmpyCd, cMsg, sMsg, true);
        } else {
            clearDetailValueForError(glblCmpyCd, cMsg, sMsg, false);
        }
    }

    /**
     * clearDetailValueForError
     * @param glblCmpyCd String
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param clearAllFlg boolean
     */
    private static void clearDetailValueForError(String glblCmpyCd, NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, boolean clearAllFlg) {

        boolean isNotExistPrchReq = false;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NPAL1080_ASMsg asMsg = sMsg.A.no(i);
            if (clearAllFlg) {
                asMsg.prchReqNum_AC.clear();
                asMsg.prchReqNum_D1.clear();
                isNotExistPrchReq = true;
            } else {
                String prchReqNum = asMsg.prchReqNum_D1.getValue();
                String prchReqLineNum = asMsg.prchReqLineNum_D1.getValue();
                BigDecimal prchReqLineSubNum = asMsg.prchReqLineSubNum_D1.getValue();

                PRCH_REQ_DTLTMsg dtl = NPAL1080Query.getInstance().getPrchReqDtl(glblCmpyCd, prchReqNum, prchReqLineNum, prchReqLineSubNum);

                if (null == dtl || !ZYPCommonFunc.hasValue(dtl.prchReqNum)) {
                    asMsg.prchReqNum_AC.clear();
                    asMsg.prchReqNum_D1.clear();
                    isNotExistPrchReq = true;
                }
            }
        }

        if (isNotExistPrchReq) {
            copyFromSMsgOntoCmsg(cMsg, sMsg);
        }
    }
    // Add End 2020/03/05 QC#56103

    /**
     * getAllDayTimes. ADD QC#57659
     * @param timeHHMM EZDSStringItem
     * @param timeAMPM EZDSStringItem
     * @param hourMinute String
     */
    public static void splitHourMinute(EZDSStringItem timeHHMM, EZDSStringItem timeAMPM, String hourMinute) {

        if (ZYPCommonFunc.hasValue(hourMinute) && ZYPCommonFunc.isNumberCheck(hourMinute)) {

            String hh = null;
            String mm = null;
            int hour = 0;

            if (hourMinute.length() == NPAL1080Constant.HOUR_MINUTE_STR_LENGTH) {

                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2);
                hour = Integer.valueOf(hh);

            } else if (hourMinute.length() == NPAL1080Constant.HOUR_MINUTE_STR_LENGTH - 1) {

                hourMinute = "0" + hourMinute;
                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2);
                hour = Integer.valueOf(hh);

            } else if (hourMinute.length() == NPAL1080Constant.HOUR_MINUTE_STR_LENGTH + 2) {
                hh = hourMinute.substring(0, 2);
                mm = hourMinute.substring(2, 4);
                hour = Integer.valueOf(hh);

            } else {

                timeHHMM.clear();
                timeAMPM.clear();
                return;
            }

            if (NPAL1080Constant.HALF_DAY_HOURS <= hour) {

                hour -= NPAL1080Constant.HALF_DAY_HOURS;
                ZYPEZDItemValueSetter.setValue(timeAMPM, NPAL1080Constant.TIME_PM);
                ZYPEZDItemValueSetter.setValue(timeHHMM, ZYPCommonFunc.concatString(String.format("%1$02d", hour), ":", mm));
                return;

            } else {

                ZYPEZDItemValueSetter.setValue(timeAMPM, NPAL1080Constant.TIME_AM);
                ZYPEZDItemValueSetter.setValue(timeHHMM, ZYPCommonFunc.concatString(hh, ":", mm));
                return;
            }
        }

        timeHHMM.clear();
        timeAMPM.clear();
        return;
    }

    /**
     * getAllDayTimes. ADD QC#57659
     * @param timeHHMM String
     * @param timeAMPM String
     * @return String
     */
    public static String getAllDayTimes(String timeHHMM, String timeAMPM) {

        int hour = 0;
        int minute = 0;

        if (0 <= timeHHMM.indexOf(":")) {

            String[] temp = timeHHMM.split(":");

            if (temp.length != 2) {

                return null;
            }

            if (!ZYPCommonFunc.isNumberCheck(temp[0]) || !ZYPCommonFunc.isNumberCheck(temp[1])) {

                return null;
            }

            hour = Integer.valueOf(temp[0]);
            minute = Integer.valueOf(temp[1]);

        } else {

            if (!ZYPCommonFunc.isNumberCheck(timeHHMM)) {

                return null;
            }

            if (timeHHMM.length() == NPAL1080Constant.HOUR_MINUTE_STR_LENGTH) {

                hour = Integer.valueOf(timeHHMM.substring(0, 2));
                minute = Integer.valueOf(timeHHMM.substring(2));

            } else if (timeHHMM.length() == NPAL1080Constant.HOUR_MINUTE_STR_LENGTH - 1) {

                timeHHMM = "0" + timeHHMM;
                hour = Integer.valueOf(timeHHMM.substring(0, 2));
                minute = Integer.valueOf(timeHHMM.substring(2));

            } else {

                return null;
            }
        }

        if (hour < 0 || minute < 0) {

            return null;

        } else if (NPAL1080Constant.HALF_DAY_HOURS <= hour) {

            return null;

        } else if (NPAL1080Constant.ONE_HOUR_MINUTES <= minute) {

            return null;
        }

        if (NPAL1080Constant.TIME_PM.equals(timeAMPM)) {

            hour += NPAL1080Constant.HALF_DAY_HOURS;
        }

        return (String.format("%1$02d", hour)).concat(String.format("%1$02d", minute));
    }

    // START 2021/06/18 J.Evangelista [QC#58876,ADD]
    /**
     * isDisplayWhShipDetail
     * @param glblCmpyCd String
     * @param rtlWhCd String
     * @return boolean
     */
    public static boolean isDisplayWhShipDetail(String glblCmpyCd, String rtlWhCd) {

        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);

        tMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.whOwnrCd)) {

            String whOwnerCds = ZYPCodeDataUtil.getVarCharConstValue(NPAL1080Constant.VAR_CHAR_CONST_SHIP_DTL_WH_OWNER_CD, glblCmpyCd);
            if (!ZYPCommonFunc.hasValue(whOwnerCds)) {
                whOwnerCds = NPAL1080Constant.SHIP_DTL_WH_OWNER_CD_DEFAULT;
            }

            List<String> whOwnerCdList = Arrays.asList(whOwnerCds.split(","));
            if (whOwnerCdList.contains(tMsg.whOwnrCd.getValue())) {
                return true;
            }
        }

        return false;
    }
    // END 2021/06/18 J.Evangelista [QC#58876,ADD]

    // START 2022/06/09 A.Cullano [QC#60154,ADD]
    /**
     * chkRtlSwhCdAndDestSwhCd
     * @param glblCmpyCd String
     * @param techSwhCd String
     * @param destSwhCd String
     * @return boolean
     */
    public static boolean chkRtlSwhCdAndDestSwhCd(String glblCmpyCd, String techSwhCd, String destSwhCd) {

        SWHTMsg techSwhInfo = NPAL1080Query.getInstance().getSwh(glblCmpyCd, techSwhCd);
        SWHTMsg destSwhInfo = NPAL1080Query.getInstance().getSwh(glblCmpyCd, destSwhCd);

        if (techSwhInfo == null || destSwhInfo == null) {
            return false;
        } else {
            // Check that the technichan SWH mdseCostTpCd and mdseInvtyCostPct is the same as destination SWH
            return (techSwhInfo.mdseCostTpCd.getValue().equals(destSwhInfo.mdseCostTpCd.getValue()))
                    && (techSwhInfo.mdseInvtyCostPct.getValue().equals(destSwhInfo.mdseInvtyCostPct.getValue()));
        }
    }
    // END 2022/06/09 A.Cullano [QC#60154,ADD]

    // START 2023/03/17 T.Kuroda [QC#61204, ADD]
    /**
     * Upload
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    public static void upload(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd) {

        // Reset warning flag
        sMsg.xxBtnFlg_H1.clear();

        copyFromCmsgOntoSmsg(cMsg, sMsg);

        /******************************************/
        /******   Validate File for Upload   ******/
        /******************************************/
        if (!validateUploadFileForAllLines(cMsg, sMsg, glblCmpyCd)) {
            copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxFileData.deleteTempFile();
            return;
        }

        /*****************************/
        /******   Upload File   ******/
        /*****************************/
        String path = cMsg.xxFileData.getTempFilePath();
        NPAL1080F00FMsg fMsg = new NPAL1080F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);

        try {
            int header = mappedFile.read();
            if (header == 1) {
                // Upload File is empty
                cMsg.setMessageInfo(ZYEM0004E);
                return;
            }

            int index = sMsg.A.getValidCount();

            // create prchReqLineNum
            int num = 0;
            for (int i = 0; i < index; i++) {
                int temp = Integer.valueOf(sMsg.A.no(i).prchReqLineNum_D1.getValue());
                if (num < temp) {
                    num = temp;
                }
            }

            while (mappedFile.read() != 1) {
                // Get Item Description
                String mdseDescShortTxt = "";
                if (ZYPCommonFunc.hasValue(fMsg.mdseCd_A1)) {
                    S21SsmEZDResult ssmResult = NPAL1080Query.getInstance().getMdseNm(glblCmpyCd, fMsg.mdseCd_A1.getValue());
                    if (ssmResult.isCodeNormal()) {
                        List<Map> resultList = (List<Map>) ssmResult.getResultObject();
                        if (resultList.size() == 1) {
                            mdseDescShortTxt = (String) resultList.get(0).get(DB_COLUMN_MDSE_DESC_SHORT_TXT);
                        }
                    } 
                }

                // Get Source Type Code
                String procrTpCd_SE = "";
                if (ZYPCommonFunc.hasValue(fMsg.procrTpNm_A1)) {
                    for (int i = 0; i < sMsg.procrTpNm_D1.length(); i++) {
                        if (!ZYPCommonFunc.hasValue(sMsg.procrTpNm_D1.no(i))) {
                            break;
                        }
                        if (fMsg.procrTpNm_A1.getValue().equals(sMsg.procrTpNm_D1.no(i).getValue())) {
                            procrTpCd_SE = sMsg.procrTpCd_D1.no(i).getValue();
                            break;
                        }
                    }
                }

                // Get WH / Supplier Name
                String prntVndNm = "";
                if (ZYPCommonFunc.hasValue(fMsg.prntVndCd_A1)) {
                    if (PROCR_TP.SUPPLIER.equals(procrTpCd_SE)) {
                        // Get Parent Vendor name when Procurement Type is "Supplier"
                        S21SsmEZDResult ssmResult = NPAL1080Query.getInstance().getPrntVndNm(glblCmpyCd, fMsg.prntVndCd_A1.getValue());

                        if (ssmResult.isCodeNormal()) {
                            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
                            if (resultList.size() == 1) {
                                prntVndNm = (String) resultList.get(0).get(DB_COLUMN_PRNT_VND_NM);
                            }
                        }
                    } else if (PROCR_TP.WAREHOUSE.equals(procrTpCd_SE)) {
                        // Get Warehouse name when Procurement Type is "Warehouse"
                        S21SsmEZDResult ssmResult = NPAL1080Query.getInstance().getWhNm(glblCmpyCd, fMsg.prntVndCd_A1.getValue());

                        if (ssmResult.isCodeNormal()) {
                            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
                            if (resultList.size() == 1) {
                                prntVndNm = (String) resultList.get(0).get(DB_COLUMN_RTL_WH_NM);
                            }
                        }
                    }
                }

                // Set Detail
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxNewRowNum, new BigDecimal(-1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineNum_D1, String.format("%03d", (num + 1)));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineSubNum_D1, BigDecimal.ZERO);
                NPAL1080BL02.setPrchReqLineTpCd(sMsg, index);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseCd_D1, toUpperCaseForEZDFStringItem(fMsg.mdseCd_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseDescShortTxt_D1, mdseDescShortTxt);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqQty_D1, fMsg.prchReqQty_A1);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_SE, procrTpCd_SE);
                for (int i = 0; i < sMsg.procrTpCd_D1.length(); i++) {
                    if (!ZYPCommonFunc.hasValue(sMsg.procrTpCd_D1.no(i))) {
                        break;
                    }
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpCd_CD.no(i), sMsg.procrTpCd_D1.no(i));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).procrTpNm_DI.no(i), sMsg.procrTpNm_D1.no(i));
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_D1, toUpperCaseForEZDFStringItem(fMsg.prntVndCd_A1));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndNm_D1, prntVndNm);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).locNm_D1, toUpperCaseForEZDFStringItem(fMsg.locNm_A1));
                if (new NPAL1080BL02().isSpecialUpdate()
                        && PRCH_REQ_APVL_STS.APPROVED.equals(sMsg.prchReqApvlStsCd_H1.getValue())) {
                    // Freeze Flag
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqFrzFlg_D1, ZYPConstant.FLG_ON_Y);
                    // Line Comment
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prchReqLineCmntTxt_D1
                            , ZYPCodeDataUtil.getVarCharConstValue("NPAL1080_SPCL_FRZ_CMNT", glblCmpyCd));
                } else {
                    // Freeze Flag
                    sMsg.A.no(index).prchReqFrzFlg_D1.clear();
                    // Line Comment
                    sMsg.A.no(index).prchReqLineCmntTxt_D1.clear();
                }

                sMsg.A.setValidCount(index + 1);

                index++;
                num++;
            }
            // Find ReqRecType
            ZYPEZDItemValueSetter.setValue(sMsg.prchReqRecTpCd_H2, findRequisitionRecordTypeCode(sMsg.prchReqTpCd_SE.getValue(), glblCmpyCd));

            // Get Address process methodized
            setUpAddress4sMsg(glblCmpyCd, sMsg);

            // Show last page.
            cMsg.xxPageShowFromNum_D2.setValue(-1);

            copyFromSMsgOntoCmsg(cMsg, sMsg);

            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Import"});

        } finally {
            mappedFile.close();
            cMsg.xxFileData.deleteTempFile();
        }
    }

    /**
     * validateUploadFileForAllLines
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    private static boolean validateUploadFileForAllLines(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd) {

        String path = cMsg.xxFileData.getTempFilePath();
        if (path.length() == 0) {
            cMsg.setMessageInfo(ZYEM0004E);
            return false;
        }

        NPAL1080F00FMsg fMsg = new NPAL1080F00FMsg();
        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(path, fMsg, option);

        try {
            int header = mappedFile.read();
            if (header == 1) {
                // Upload File is empty
                cMsg.setMessageInfo(ZYEM0004E);
                return false;
            }

            int status = -1;
            int uploadFileLineNum = 0;

            while ((status = mappedFile.read()) != 1) {
                uploadFileLineNum++;

                // Check format
                if (!validateUploadFileForEachLine(cMsg, status, uploadFileLineNum)) {
                    return false;
                }

                // Exceeds the max numbers
                if (sMsg.A.getValidCount() + uploadFileLineNum > sMsg.A.length()) {
                    cMsg.setMessageInfo(NPAM1199E);
                    return false;
                }
            }

            if (uploadFileLineNum == 0) {
                // Only has a header line
                cMsg.setMessageInfo(ZYEM0004E);
                return false;
            }
        } finally {
            mappedFile.close();
        }
        return true;
    }

    /**
     * validateUploadFileForEachLine
     * @param cMsg NPAL1080CMsg
     * @param status int
     * @param uploadFileLineNum int
     * @return boolean
     */
    private static boolean validateUploadFileForEachLine(NPAL1080CMsg cMsg, int status, int uploadFileLineNum) {

        if (status == CSV_READ_STATUS_CODE_1000) {
            cMsg.setMessageInfo(NMAM0052E, new String[] {UPLOAD_DATA_FORMAT});
            return false;
        }

        int errCol1 = status - CSV_READ_STATUS_CODE_1000;
        int errCol2 = status - CSV_READ_STATUS_CODE_2000;

        // Item Number
        if (errCol1 == 1 || errCol2 == 1) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Item Number (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
        // Req Qty
        } else if (errCol1 == 2 || errCol2 == 2) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Req Qty (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
        // Source Type
        } else if (errCol1 == 3 || errCol2 == 3) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"Source Type (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
        // WH / Supplier
        } else if (errCol1 == 4 || errCol2 == 4) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"WH / Supplier (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
        // SWH / Site
        } else if (errCol1 == 5 || errCol2 == 5) {
            cMsg.setMessageInfo(NMAM8191E, new String[] {"SWH / Site (Line# " + String.valueOf(uploadFileLineNum) + ")" });
            return false;
        }

        return true;
    }

    /**
     * toUpperCaseForEZDFStringItem
     * @param stringItem EZDFStringItem
     * @return String
     */
    private static String toUpperCaseForEZDFStringItem(EZDFStringItem stringItem) {
        return ZYPCommonFunc.hasValue(stringItem) ? stringItem.getValue().toUpperCase() : "";
    }

    /**
     * Template Download
     * @param cMsg NPAL1080CMsg
     * @param sMsg NPAL1080SMsg
     * @param glblCmpyCd String
     */
    public static void templateDownload(NPAL1080CMsg cMsg, NPAL1080SMsg sMsg, String glblCmpyCd) {

        NPAL1080F00FMsg fMsg = new NPAL1080F00FMsg();

        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), TMPL_FILE_EXTENSION);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_HEADER);
        csvOutFile.close();

    }
    // END   2023/03/17 T.Kuroda [QC#61204, ADD]

}