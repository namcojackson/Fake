/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL3130.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import business.blap.NLBL3130.NLBL3130CMsg;
import business.blap.NLBL3130.NLBL3130Query;
import business.db.SHPG_ORD_DELY_INSTNTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NLBL3130 Delivery Instruction
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 04/13/2016   CSAI            Y.Imazu         Update          QC#5868
 * 01/12/2018   CITS            T.Tokutomi      Update          QC#18460_Sol#087
 * 01/24/2018   CITS            K.Ogino         Update          QC#23044
 *</pre>
 */
public class NLBL3130CommonLogic {

    /**
     * clear Msg
     * @param cMsg NLBL3130CMsg
     */
    public static void clearMsg(NLBL3130CMsg cMsg) {

        // cMsg initialization
        // Site Information
        cMsg.opsFromHourMn_H1.clear();
        cMsg.xxScrItem7Txt_OF.clear();
        cMsg.opsToHourMn_H1.clear();
        cMsg.xxScrItem7Txt_OT.clear();
        cMsg.secClncReqTxt_Y.clear();
        cMsg.secClncReqTxt_N.clear();
        cMsg.insCertReqTxt_Y.clear();
        cMsg.insCertReqTxt_N.clear();
        cMsg.flCovTxt_H1.clear();
        cMsg.trctrAndTrailAccsTxt_Y.clear();
        cMsg.trctrAndTrailAccsTxt_N.clear();
        cMsg.loadDockAvalTxt_Y.clear();
        cMsg.loadDockAvalTxt_N.clear();
        cMsg.rampAvalTxt_Y.clear();
        cMsg.rampAvalTxt_N.clear();
        cMsg.stepAvalTxt_Y.clear();
        cMsg.stepAvalTxt_N.clear();
        cMsg.insdStepNum_H1.clear();
        cMsg.otsdStepNum_H1.clear();
        cMsg.frontDoorAvalTxt_Y.clear();
        cMsg.frontDoorAvalTxt_N.clear();
        cMsg.backDoorAvalTxt_Y.clear();
        cMsg.backDoorAvalTxt_N.clear();
        cMsg.pwrOtltConfigTxt_Y.clear();
        cMsg.pwrOtltConfigTxt_N.clear();
        cMsg.sgnOnBldgRdsdTxt_Y.clear();
        cMsg.sgnOnBldgRdsdTxt_N.clear();
        cMsg.bldgStryNum_H1.clear();
        cMsg.bldgGurdNtfyTxt_Y.clear();
        cMsg.bldgGurdNtfyTxt_N.clear();
        cMsg.indlParkTxt_Y.clear();
        cMsg.indlParkTxt_N.clear();
        cMsg.bizParkTxt_Y.clear();
        cMsg.bizParkTxt_N.clear();
        cMsg.proBldgTxt_Y.clear();
        cMsg.proBldgTxt_N.clear();
        cMsg.shpngCtrTxt_Y.clear();
        cMsg.shpngCtrTxt_N.clear();
        cMsg.pvtResTxt_Y.clear();
        cMsg.pvtResTxt_N.clear();

        // Building Entrance Dimensions
        cMsg.bldgEntDoorHgt_A1.clear();
        cMsg.bldgEntDoorWdt_A1.clear();
        cMsg.crdrWdt_A1.clear();
        cMsg.doorWdt_A1.clear();

        // Instructions
        cMsg.shpgInstnCmntTxt_B1.clear();
        cMsg.delyInstnCmntTxt_B1.clear();
        cMsg.istlInstnCmntTxt_B1.clear();
        cMsg.techInstnCmntTxt_B1.clear();

        // Elevator Information & Dimensions
        cMsg.elevAvalTxt_Y.clear();
        cMsg.elevAvalTxt_N.clear();
        cMsg.elevAvalFromHourMn_C1.clear();
        cMsg.xxScrItem7Txt_EF.clear();
        cMsg.elevAvalToHourMn_C1.clear();
        cMsg.xxScrItem7Txt_ET.clear();
        cMsg.elevApptReqTxt_Y.clear();
        cMsg.elevApptReqTxt_N.clear();
        cMsg.elevCtacPsnNm_C1.clear();
        cMsg.elevCtacTelNum_C1.clear();
        cMsg.elevWdt_C1.clear();
        cMsg.elevDepthNum_C1.clear();
        cMsg.elevCapWt_C1.clear();
        cMsg.elevDoorHgt_C1.clear();
        cMsg.elevDoorWdt_C1.clear();

        // Additional Comments
        cMsg.delyInstnAddlCmntTxt_D1.clear();

        cMsg.dsCpoConfigPk_G1.clear();
        cMsg.cpoOrdNum_P1.clear();
        cMsg.dsOrdPosnNum_P1.clear();
        cMsg.configCatgCd_P1.clear();

        // QC#18460_Sol#087 Add column
        // Site Information
        cMsg.xxTmFrameTxt_OF.clear();
        cMsg.xxTmFrameTxt_OT.clear();
        cMsg.cmpyInfoFlNm_H1.clear();
        cMsg.loadDockHgt_H1.clear();
        cMsg.delyTrnspOptCd_H1.clear();
        cMsg.stairCrawReqFlg_Y.clear();
        cMsg.stairCrawReqFlg_N.clear();
        cMsg.stairAndLdgWdt_H1.clear();
        // Elevator Information & Dimensions
        cMsg.xxTmFrameTxt_EF.clear();
        cMsg.xxTmFrameTxt_ET.clear();
        // Validation
        cMsg.custInfoBoFlg_Y.clear();
        cMsg.custInfoBoFlg_N.clear();
        cMsg.pickUpXtrTonerFlg_Y.clear();
        cMsg.pickUpXtrTonerFlg_N.clear();
        cMsg.pickUpAtSameTmFlg_Y.clear();
        cMsg.pickUpAtSameTmFlg_N.clear();
    }

    /**
     * setChkBoxVal
     * @param chkBoxY EZDBStringItem
     * @param chkBoxN EZDBStringItem
     * @return String
     */
    public static String setChkBoxVal(EZDCStringItem chkBoxY, EZDCStringItem chkBoxN) {

        if (ZYPConstant.CHKBOX_ON_Y.equals(chkBoxY.getValue())) {

            return ZYPConstant.FLG_ON_Y;
        }

        if (ZYPConstant.CHKBOX_ON_Y.equals(chkBoxN.getValue())) {

            return ZYPConstant.FLG_OFF_N;
        }

        return null;
    }

    /**
     * setChkBoxValNotNull
     * QC#18460_Sol#087 add.
     * @param chkBoxY EZDBStringItem
     * @param chkBoxN EZDBStringItem
     * @return String
     */
    public static String setChkBoxValNotNull(EZDCStringItem chkBoxY, EZDCStringItem chkBoxN) {
        if (ZYPConstant.CHKBOX_ON_Y.equals(chkBoxY.getValue())) {
            return ZYPConstant.FLG_ON_Y;
        } else {
            return ZYPConstant.FLG_OFF_N;
        }
    }

    /**
     * setScrnItem
     * @param cMsg NLBL3130CMsg
     * @param delyInstnInfoMap Map<String, Object>
     */
    public static void setScrnItem(NLBL3130CMsg cMsg, Map<String, Object> delyInstnInfoMap) {

        // QC#23044
        String ordType = (String) delyInstnInfoMap.get("ORD_TYPE");
        ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum_H1, (String) delyInstnInfoMap.get("TRX_HDR_NUM"));
        if ("SO".equals(ordType)) {
            ZYPEZDItemValueSetter.setValue(cMsg.soNum_H1, (String) delyInstnInfoMap.get("ORD_NUM"));
            cMsg.rwsNum_H1.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.rwsNum_H1, (String) delyInstnInfoMap.get("ORD_NUM"));
            cMsg.soNum_H1.clear();
        }
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, (String) delyInstnInfoMap.get("DS_ACCT_NM"));
        // QC#18460_Sol#087 convert OPS_FROM_HOUR_MN and OPS_TO_HOUR_MN
        ZYPEZDItemValueSetter.setValue(cMsg.opsFromHourMn_H1, convertTime((String) delyInstnInfoMap.get("OPS_FROM_HOUR_MN"), cMsg));
        ZYPEZDItemValueSetter.setValue(cMsg.opsToHourMn_H1, convertTime((String) delyInstnInfoMap.get("OPS_TO_HOUR_MN"), cMsg));

        ZYPEZDItemValueSetter.setValue(cMsg.flCovTxt_H1, (String) delyInstnInfoMap.get("FL_COV_TXT"));
        ZYPEZDItemValueSetter.setValue(cMsg.insdStepNum_H1, (String) delyInstnInfoMap.get("INSD_STEP_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.otsdStepNum_H1, (String) delyInstnInfoMap.get("OTSD_STEP_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.bldgStryNum_H1, (String) delyInstnInfoMap.get("BLDG_STRY_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.bldgEntDoorHgt_A1, (BigDecimal) delyInstnInfoMap.get("BLDG_ENT_DOOR_HGT"));
        ZYPEZDItemValueSetter.setValue(cMsg.bldgEntDoorWdt_A1, (BigDecimal) delyInstnInfoMap.get("BLDG_ENT_DOOR_WDT"));
        ZYPEZDItemValueSetter.setValue(cMsg.crdrWdt_A1, (BigDecimal) delyInstnInfoMap.get("CRDR_WDT"));
        ZYPEZDItemValueSetter.setValue(cMsg.doorWdt_A1, (BigDecimal) delyInstnInfoMap.get("DOOR_WDT"));
        ZYPEZDItemValueSetter.setValue(cMsg.delyInstnCmntTxt_B1, (String) delyInstnInfoMap.get("DELY_INSTN_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(cMsg.istlInstnCmntTxt_B1, (String) delyInstnInfoMap.get("ISTL_INSTN_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(cMsg.techInstnCmntTxt_B1, (String) delyInstnInfoMap.get("TECH_INSTN_CMNT_TXT"));

        // QC#18460_Sol#087 convert ELEV_AVAL_FROM_HOUR_MN and ELEV_AVAL_TO_HOUR_MN
        ZYPEZDItemValueSetter.setValue(cMsg.elevAvalFromHourMn_C1, convertTime((String) delyInstnInfoMap.get("ELEV_AVAL_FROM_HOUR_MN"), cMsg));
        ZYPEZDItemValueSetter.setValue(cMsg.elevAvalToHourMn_C1, convertTime((String) delyInstnInfoMap.get("ELEV_AVAL_TO_HOUR_MN"), cMsg));

        ZYPEZDItemValueSetter.setValue(cMsg.elevCtacPsnNm_C1, (String) delyInstnInfoMap.get("ELEV_CTAC_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.elevCtacTelNum_C1, (String) delyInstnInfoMap.get("ELEV_CTAC_TEL_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.elevWdt_C1, (BigDecimal) delyInstnInfoMap.get("ELEV_WDT"));
        ZYPEZDItemValueSetter.setValue(cMsg.elevDepthNum_C1, (BigDecimal) delyInstnInfoMap.get("ELEV_DEPTH_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.elevCapWt_C1, (BigDecimal) delyInstnInfoMap.get("ELEV_CAP_WT"));
        ZYPEZDItemValueSetter.setValue(cMsg.elevDoorHgt_C1, (BigDecimal) delyInstnInfoMap.get("ELEV_DOOR_HGT"));
        ZYPEZDItemValueSetter.setValue(cMsg.elevDoorWdt_C1, (BigDecimal) delyInstnInfoMap.get("ELEV_DOOR_WDT"));
        ZYPEZDItemValueSetter.setValue(cMsg.delyInstnAddlCmntTxt_D1, (String) delyInstnInfoMap.get("DELY_INSTN_ADDL_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(cMsg.vldUsrId_E1, (String) delyInstnInfoMap.get("VLD_USR_ID"));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm_E1, (String) delyInstnInfoMap.get("VLD_USR_NM"));

        if (!ZYPCommonFunc.hasValue(cMsg.xxPsnNm_E1)) {

            ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm_E1, cMsg.vldUsrId_E1.getValue());
        }

        // QC#18460_Sol#087 convert VLD_TS.
        if (ZYPCommonFunc.hasValue((String) delyInstnInfoMap.get("VLD_TS")) // 
                && ZYPCommonFunc.hasValue(cMsg.postCd_S1)) {
            String convertTimeStamp = convertTimeStamp(cMsg.ctryCd_S1.getValue(), cMsg.postCd_S1.getValue(), (String) delyInstnInfoMap.get("VLD_TS"));
            if (ZYPCommonFunc.hasValue(convertTimeStamp) && convertTimeStamp.length() > 14) {
                convertTimeStamp = convertTimeStamp.substring(0, 14);
            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxTsDsp19Txt_E1, convertTimeStamp);
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxTsDsp19Txt_E1, (String) delyInstnInfoMap.get("VLD_TS"));
        }

        ZYPEZDItemValueSetter.setValue(cMsg.dsCpoConfigPk_G1, (BigDecimal) delyInstnInfoMap.get("DS_CPO_CONFIG_PK"));
        ZYPEZDItemValueSetter.setValue(cMsg.cpoOrdNum_P1, (String) delyInstnInfoMap.get("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.dsOrdPosnNum_P1, (String) delyInstnInfoMap.get("DS_ORD_POSN_NUM"));
        ZYPEZDItemValueSetter.setValue(cMsg.configCatgCd_P1, (String) delyInstnInfoMap.get("CONFIG_CATG_CD"));

        // QC#23044 Shipping Instructions
        if ("SO".equals(ordType)) {
            ZYPEZDItemValueSetter.setValue(cMsg.shpgInstnCmntTxt_B1, getShpgInstnCmntTxt(cMsg, (String) delyInstnInfoMap.get("SHPG_INSTN_CMNT_TXT")));
        }

        // QC#18460_Sol#087 Add column.
        ZYPEZDItemValueSetter.setValue(cMsg.cmpyInfoFlNm_H1, (String) delyInstnInfoMap.get("CMPY_INFO_FL_NM"));
        ZYPEZDItemValueSetter.setValue(cMsg.loadDockHgt_H1, (BigDecimal) delyInstnInfoMap.get("LOAD_DOCK_HGT"));
        ZYPEZDItemValueSetter.setValue(cMsg.delyTrnspOptCd_H1, (String) delyInstnInfoMap.get("DELY_TRNSP_OPT_CD"));
        ZYPEZDItemValueSetter.setValue(cMsg.stairAndLdgWdt_H1, (BigDecimal) delyInstnInfoMap.get("STAIR_AND_LDG_WDT"));

        // Check Box
        setRsltValToChkBox(cMsg.secClncReqTxt_Y, cMsg.secClncReqTxt_N, (String) delyInstnInfoMap.get("SEC_CLNC_REQ_TXT"));
        setRsltValToChkBox(cMsg.insCertReqTxt_Y, cMsg.insCertReqTxt_N, (String) delyInstnInfoMap.get("INS_CERT_REQ_TXT"));
        setRsltValToChkBox(cMsg.trctrAndTrailAccsTxt_Y, cMsg.trctrAndTrailAccsTxt_N, (String) delyInstnInfoMap.get("TRCTR_AND_TRAIL_ACCS_TXT"));
        setRsltValToChkBox(cMsg.loadDockAvalTxt_Y, cMsg.loadDockAvalTxt_N, (String) delyInstnInfoMap.get("LOAD_DOCK_AVAL_TXT"));
        setRsltValToChkBox(cMsg.rampAvalTxt_Y, cMsg.rampAvalTxt_N, (String) delyInstnInfoMap.get("RAMP_AVAL_TXT"));
        setRsltValToChkBox(cMsg.stepAvalTxt_Y, cMsg.stepAvalTxt_N, (String) delyInstnInfoMap.get("STEP_AVAL_TXT"));
        setRsltValToChkBox(cMsg.frontDoorAvalTxt_Y, cMsg.frontDoorAvalTxt_N, (String) delyInstnInfoMap.get("FRONT_DOOR_AVAL_TXT"));
        setRsltValToChkBox(cMsg.backDoorAvalTxt_Y, cMsg.backDoorAvalTxt_N, (String) delyInstnInfoMap.get("BACK_DOOR_AVAL_TXT"));
        setRsltValToChkBox(cMsg.pwrOtltConfigTxt_Y, cMsg.pwrOtltConfigTxt_N, (String) delyInstnInfoMap.get("PWR_OTLT_CONFIG_TXT"));
        setRsltValToChkBox(cMsg.sgnOnBldgRdsdTxt_Y, cMsg.sgnOnBldgRdsdTxt_N, (String) delyInstnInfoMap.get("SGN_ON_BLDG_RDSD_TXT"));
        setRsltValToChkBox(cMsg.bldgGurdNtfyTxt_Y, cMsg.bldgGurdNtfyTxt_N, (String) delyInstnInfoMap.get("BLDG_GURD_NTFY_TXT"));
        setRsltValToChkBox(cMsg.indlParkTxt_Y, cMsg.indlParkTxt_N, (String) delyInstnInfoMap.get("INDL_PARK_TXT"));
        setRsltValToChkBox(cMsg.bizParkTxt_Y, cMsg.bizParkTxt_N, (String) delyInstnInfoMap.get("BIZ_PARK_TXT"));
        setRsltValToChkBox(cMsg.proBldgTxt_Y, cMsg.proBldgTxt_N, (String) delyInstnInfoMap.get("PRO_BLDG_TXT"));
        setRsltValToChkBox(cMsg.shpngCtrTxt_Y, cMsg.shpngCtrTxt_N, (String) delyInstnInfoMap.get("SHPNG_CTR_TXT"));
        setRsltValToChkBox(cMsg.pvtResTxt_Y, cMsg.pvtResTxt_N, (String) delyInstnInfoMap.get("PVT_RES_TXT"));
        setRsltValToChkBox(cMsg.elevAvalTxt_Y, cMsg.elevAvalTxt_N, (String) delyInstnInfoMap.get("ELEV_AVAL_TXT"));
        setRsltValToChkBox(cMsg.elevApptReqTxt_Y, cMsg.elevApptReqTxt_N, (String) delyInstnInfoMap.get("ELEV_APPT_REQ_TXT"));

        // QC#18460_Sol#087 Add checkbox.
        setRsltValToChkBox(cMsg.stairCrawReqFlg_Y, cMsg.stairCrawReqFlg_N, (String) delyInstnInfoMap.get("STAIR_CRAW_REQ_FLG"));
        setRsltValToChkBox(cMsg.custInfoBoFlg_Y, cMsg.custInfoBoFlg_N, (String) delyInstnInfoMap.get("CUST_INFO_BO_FLG"));
        setRsltValToChkBox(cMsg.pickUpXtrTonerFlg_Y, cMsg.pickUpXtrTonerFlg_N, (String) delyInstnInfoMap.get("PICK_UP_XTR_TONER_FLG"));
        setRsltValToChkBox(cMsg.pickUpAtSameTmFlg_Y, cMsg.pickUpAtSameTmFlg_N, (String) delyInstnInfoMap.get("PICK_UP_AT_SAME_TM_FLG"));
    }

    /**
     * getShpgInstnCmntTxt
     * @param cMsg NLBL3130CMsg
     * @param shpgInstnCmntTxt String
     * @return String
     */
    private static String getShpgInstnCmntTxt(NLBL3130CMsg cMsg, String shpgInstnCmntTxt) {

        if (ZYPCommonFunc.hasValue(shpgInstnCmntTxt)) {

            return shpgInstnCmntTxt;
        }

        S21SsmEZDResult ssmResult = NLBL3130Query.getInstance().getShpgInstnCmntTxt(cMsg);

        if (ssmResult.isCodeNormal()) {

            List<String> soMsgDescTxtList = (ArrayList<String>) ssmResult.getResultObject();

            if (soMsgDescTxtList != null && !soMsgDescTxtList.isEmpty()) {

                SHPG_ORD_DELY_INSTNTMsg shpgOrdDelyInstnTMsg = new SHPG_ORD_DELY_INSTNTMsg();
                int shpgInstnCmntTxtLength = shpgOrdDelyInstnTMsg.getSchema().getAttr("shpgInstnCmntTxt").getDigit();

                StringBuilder soMsgDescTxtBuilder = new StringBuilder();

                for (String soMsgDescTxt : soMsgDescTxtList) {

                    soMsgDescTxtBuilder.append(soMsgDescTxt);
                }

                if (soMsgDescTxtBuilder != null) {

                    if (soMsgDescTxtBuilder.length() > shpgInstnCmntTxtLength) {

                        return soMsgDescTxtBuilder.substring(0, shpgInstnCmntTxtLength);
                    }

                    return soMsgDescTxtBuilder.toString();
                }
            }
        }

        return null;
    }

    /**
     * setRsltValToChkBox
     * @param chkBoxY EZDCStringItem
     * @param chkBoxN EZDCStringItem
     * @param chkBoxVal String
     */
    private static void setRsltValToChkBox(EZDCStringItem chkBoxY, EZDCStringItem chkBoxN, String chkBoxVal) {

        if (ZYPCommonFunc.hasValue(chkBoxVal)) {

            if (ZYPConstant.FLG_ON_Y.equals(chkBoxVal)) {

                ZYPEZDItemValueSetter.setValue(chkBoxY, ZYPConstant.CHKBOX_ON_Y);

            } else if (ZYPConstant.FLG_OFF_N.equals(chkBoxVal)) {

                ZYPEZDItemValueSetter.setValue(chkBoxN, ZYPConstant.CHKBOX_ON_Y);
            }
        }
    }

    /**
     * getSystemTimeZoneCd
     * QC#18460_Sol#087 add method.
     * @return timeZoneCode String
     */
    public static String getSystemTimeZoneCd(){
        return NSXC001001SvcTimeZone.getSysTimeZone(ZYPDateUtil.getSalesDate());
    }

    /**
     * getTimeZoneCd
     * QC#18460_Sol#087 add method.
     * @param ctryCd String
     * @param postCd String
     * @return timeZoneCode String
     */
    public static String getTimeZoneCd(String ctryCd, String postCd){
        SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, ZYPDateUtil.getSalesDate(), ctryCd, postCd);
        return timeInfo.getTimeZone();
    }

    /**
     * convertTime
     * QC#18460_Sol#087 add method.
     * @param ctryCd String
     * @param postCd String
     * @param timeStamp String
     * @return time stamp (location)
     */
    public static String convertTimeStamp(String ctryCd, String postCd, String timeStamp){
        SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE1_SYS_TO_LOC, timeStamp, ctryCd, postCd);
        return timeInfo.getDateTime();
    }

    /**
     * convertTime
     * QC#18460_Sol#087 Add method.
     * @param time String
     * @param cMsg NLBL3130CMsg
     * @return convert time(hh:mm) String
     */
    public static String convertTime(String time, NLBL3130CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(time)) {
            // dummy date
            String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd_G1.getValue());
            // time zone parameter
            String ctryCd = cMsg.ctryCd_S1.getValue();
            String postCd = cMsg.postCd_S1.getValue();
            // time zone convert flag
            boolean timeConvertFlg = ZYPCommonFunc.hasValue(postCd);

            String convertTime = formatHHmm(time);
            String convertTimeStamp = slsDt + convertTime;

            if (timeConvertFlg) {
                return convertTimeStamp(ctryCd, postCd, convertTimeStamp).substring(8, 12);
            } else {
                return convertTime;
            }
        } else {
            return time;
        }
    }

    /**
     * decodeTime
     * QC#18460_Sol#087 add method.
     * @param ctryCd String
     * @param postCd String
     * @param timeStamp String
     * @return time stamp (system)
     */
    public static String decodeTimeStamp(String ctryCd, String postCd, String timeStamp){
        SvcTimeZoneInfo timeInfo = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, timeStamp, ctryCd, postCd);
        return timeInfo.getDateTime();
    }

    /**
     * decodeTime
     * QC#18460_Sol#087 Add method.
     * @param time String
     * @param cMsg NLBL3130CMsg
     * @return decode time(hh:mm) String
     */
    public static String decodeTime(String time, NLBL3130CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(time)) {
            // dummy date
            String slsDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd_G1.getValue());
            // time zone parameter
            String ctryCd = cMsg.ctryCd_S1.getValue();
            String postCd = cMsg.postCd_S1.getValue();
            // time zone convert flag
            boolean timeConvertFlg = ZYPCommonFunc.hasValue(postCd);

            String decodeTime = formatHHmm(time);
            String decodeTimeStamp = slsDt + decodeTime;

            if (timeConvertFlg) {
                return decodeTimeStamp(ctryCd, postCd, decodeTimeStamp).substring(8, 12);
            } else {
                return decodeTime;
            }
        } else {
            return time;
        }
    }

    /**
     * formatHHmm
     * QC#18460_Sol#087 add method.
     * @param hhmm String
     * @return HHmm String
     */
    public static String formatHHmm(String hhmm){
        if(hhmm.length() < 4){
            hhmm = "0" + hhmm;
        }
        return hhmm;
    }
}
