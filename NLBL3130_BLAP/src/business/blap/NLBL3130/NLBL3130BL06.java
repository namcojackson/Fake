/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package business.blap.NLBL3130;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3130.common.NLBL3130CommonLogic;
import business.blap.NLBL3130.constant.NLBL3130Constant;
import business.db.RWS_DELY_INSTNTMsg;
import business.db.RWS_MSGTMsg;
import business.db.S21_PSNTMsg;
import business.db.SHPG_ORD_DELY_INSTNTMsg;
import business.db.SHPG_ORD_MSGTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_ORD_MSG_TP;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Delivery Instruction
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/18/2015   CITS            M.Ito           Create          N/A
 * 04/13/2016   CSAI            Y.Imazu         Update          QC#5868
 * 07/14/2016   CSAI            Y.Imazu         Update          QC#11900
 * 02/07/2017   CITS            T.Kikuhara      Update          QC#15586
 * 01/12/2018   CITS            T.Tokutomi      Update          QC#18460_Sol#087
 * 01/24/2018   CITS            K.Ogino         Update          QC#23044
 * 06/05/2018   CITS            K.Ogino         Update          QC#25233
 * 03/14/2019   Fujitsu         T.Ogura         Update          QC#30707
 *</pre>
 */
public class NLBL3130BL06 extends S21BusinessHandler implements NLBL3130Constant {

    /**
     * Screen Application ID
     */
    private String screenAplID;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLBL3130SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLBL3130Scrn00_CMN_Submit((NLBL3130CMsg) cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3130Scrn00_CMN_Submit
     * @param cMsg Business Component Interface Message
     */
    private void doProcess_NLBL3130Scrn00_CMN_Submit(NLBL3130CMsg cMsg) {

        // QC#23044
        if (ZYPCommonFunc.hasValue(cMsg.soNum_H1)) {
            SHPG_ORD_DELY_INSTNTMsg shpgOrdDelyInstnTMsg = new SHPG_ORD_DELY_INSTNTMsg();
            shpgOrdDelyInstnTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_G1.getValue());
            shpgOrdDelyInstnTMsg.soNum.setValue(cMsg.soNum_H1.getValue());

            try {

                shpgOrdDelyInstnTMsg = (SHPG_ORD_DELY_INSTNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrdDelyInstnTMsg);

            } catch (EZDDBRecordLockedException e) {

                cMsg.setMessageInfo(NLBM0050E);
                return;
            }

            if (shpgOrdDelyInstnTMsg == null) {

                shpgOrdDelyInstnTMsg = new SHPG_ORD_DELY_INSTNTMsg();
                shpgOrdDelyInstnTMsg = setItemValues(cMsg, shpgOrdDelyInstnTMsg);

                EZDTBLAccessor.insert(shpgOrdDelyInstnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDelyInstnTMsg.getReturnCode())) {

                    cMsg.setMessageInfo(ZZZM9012E, new String[] {shpgOrdDelyInstnTMsg.getReturnCode() });
                    return;

                } else {

                    ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm_E1, getPsnNm(shpgOrdDelyInstnTMsg.glblCmpyCd.getValue(), shpgOrdDelyInstnTMsg.vldUsrId.getValue()));
                    ZYPEZDItemValueSetter.setValue(cMsg.vldUsrId_E1, shpgOrdDelyInstnTMsg.vldUsrId.getValue());
                    ZYPEZDItemValueSetter.setValue(cMsg.xxTsDsp19Txt_E1, shpgOrdDelyInstnTMsg.vldTs.getValue());
                    cMsg.setMessageInfo(NZZM0002I);
                }

            } else {

                shpgOrdDelyInstnTMsg = new SHPG_ORD_DELY_INSTNTMsg();
                shpgOrdDelyInstnTMsg = setItemValues(cMsg, shpgOrdDelyInstnTMsg);

                EZDTBLAccessor.update(shpgOrdDelyInstnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdDelyInstnTMsg.getReturnCode())) {

                    cMsg.setMessageInfo(ZZZM9013E, new String[] {shpgOrdDelyInstnTMsg.getReturnCode() });
                    return;

                } else {

                    ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm_E1, getPsnNm(shpgOrdDelyInstnTMsg.glblCmpyCd.getValue(), shpgOrdDelyInstnTMsg.vldUsrId.getValue()));
                    ZYPEZDItemValueSetter.setValue(cMsg.vldUsrId_E1, shpgOrdDelyInstnTMsg.vldUsrId.getValue());
                    ZYPEZDItemValueSetter.setValue(cMsg.xxTsDsp19Txt_E1, shpgOrdDelyInstnTMsg.vldTs.getValue());
                    cMsg.setMessageInfo(NZZM0002I);
                }
            }

            // QC#15586 add start
            registShpgOrdMsg(cMsg);
            // QC#15586 add end

        } else if (ZYPCommonFunc.hasValue(cMsg.rwsNum_H1)) {

            RWS_DELY_INSTNTMsg rwsDelyInstnTMsg = new RWS_DELY_INSTNTMsg();
            rwsDelyInstnTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_G1.getValue());
            rwsDelyInstnTMsg.rwsNum.setValue(cMsg.rwsNum_H1.getValue());

            try {

                rwsDelyInstnTMsg = (RWS_DELY_INSTNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsDelyInstnTMsg);

            } catch (EZDDBRecordLockedException e) {

                cMsg.setMessageInfo(NLBM0050E);
                return;
            }

            if (rwsDelyInstnTMsg == null) {

                rwsDelyInstnTMsg = new RWS_DELY_INSTNTMsg();
                rwsDelyInstnTMsg = setItemValuesRws(cMsg, rwsDelyInstnTMsg);

                EZDTBLAccessor.insert(rwsDelyInstnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDelyInstnTMsg.getReturnCode())) {

                    cMsg.setMessageInfo(ZZZM9012E, new String[] {rwsDelyInstnTMsg.getReturnCode() });
                    return;

                } else {

                    ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm_E1, getPsnNm(rwsDelyInstnTMsg.glblCmpyCd.getValue(), rwsDelyInstnTMsg.vldUsrId.getValue()));
                    ZYPEZDItemValueSetter.setValue(cMsg.vldUsrId_E1, rwsDelyInstnTMsg.vldUsrId.getValue());
                    ZYPEZDItemValueSetter.setValue(cMsg.xxTsDsp19Txt_E1, rwsDelyInstnTMsg.vldTs.getValue());
                    cMsg.setMessageInfo(NZZM0002I);
                }

            } else {

                rwsDelyInstnTMsg = new RWS_DELY_INSTNTMsg();
                rwsDelyInstnTMsg = setItemValuesRws(cMsg, rwsDelyInstnTMsg);

                EZDTBLAccessor.update(rwsDelyInstnTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsDelyInstnTMsg.getReturnCode())) {

                    cMsg.setMessageInfo(ZZZM9013E, new String[] {rwsDelyInstnTMsg.getReturnCode() });
                    return;

                } else {

                    ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm_E1, getPsnNm(rwsDelyInstnTMsg.glblCmpyCd.getValue(), rwsDelyInstnTMsg.vldUsrId.getValue()));
                    ZYPEZDItemValueSetter.setValue(cMsg.vldUsrId_E1, rwsDelyInstnTMsg.vldUsrId.getValue());
                    ZYPEZDItemValueSetter.setValue(cMsg.xxTsDsp19Txt_E1, rwsDelyInstnTMsg.vldTs.getValue());
                    cMsg.setMessageInfo(NZZM0002I);
                }
            }

            // QC#25233 
            // Create RWS_MSG
            registRwsMsg(cMsg);
        }
   }

    /**
     * setItemValues
     * @param cMsg Business Component Interface Message
     * @param shpgOrdDelyInstnTMsg SHPG_ORD_DELY_INSTNTMsg
     */
    private SHPG_ORD_DELY_INSTNTMsg setItemValues(NLBL3130CMsg cMsg, SHPG_ORD_DELY_INSTNTMsg shpgOrdDelyInstnTMsg) {

        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);

        // Header
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.soNum, cMsg.soNum_H1);

        // Site Information
        // QC#18460_Sol#087 decode time.
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.opsFromHourMn, NLBL3130CommonLogic.decodeTime(cMsg.opsFromHourMn_H1.getValue(), cMsg));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.opsToHourMn, NLBL3130CommonLogic.decodeTime(cMsg.opsToHourMn_H1.getValue(), cMsg));

        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.flCovTxt, cMsg.flCovTxt_H1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.insdStepNum, cMsg.insdStepNum_H1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.otsdStepNum, cMsg.otsdStepNum_H1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.bldgStryNum, cMsg.bldgStryNum_H1);

        // Site Information(Check Box)
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.secClncReqTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.secClncReqTxt_Y, cMsg.secClncReqTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.insCertReqTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.insCertReqTxt_Y, cMsg.insCertReqTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.trctrAndTrailAccsTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.trctrAndTrailAccsTxt_Y, cMsg.trctrAndTrailAccsTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.loadDockAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.loadDockAvalTxt_Y, cMsg.loadDockAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.rampAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.rampAvalTxt_Y, cMsg.rampAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.stepAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.stepAvalTxt_Y, cMsg.stepAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.frontDoorAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.frontDoorAvalTxt_Y, cMsg.frontDoorAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.backDoorAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.backDoorAvalTxt_Y, cMsg.backDoorAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.pwrOtltConfigTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.pwrOtltConfigTxt_Y, cMsg.pwrOtltConfigTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.sgnOnBldgRdsdTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.sgnOnBldgRdsdTxt_Y, cMsg.sgnOnBldgRdsdTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.bldgGurdNtfyTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.bldgGurdNtfyTxt_Y, cMsg.bldgGurdNtfyTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.indlParkTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.indlParkTxt_Y, cMsg.indlParkTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.bizParkTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.bizParkTxt_Y, cMsg.bizParkTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.proBldgTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.proBldgTxt_Y, cMsg.proBldgTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.shpngCtrTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.shpngCtrTxt_Y, cMsg.shpngCtrTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.pvtResTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.pvtResTxt_Y, cMsg.pvtResTxt_N));

        // Building Entrance Dimensions
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.bldgEntDoorHgt, cMsg.bldgEntDoorHgt_A1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.bldgEntDoorWdt, cMsg.bldgEntDoorWdt_A1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.crdrWdt, cMsg.crdrWdt_A1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.doorWdt, cMsg.doorWdt_A1);

        // Instructions
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.shpgInstnCmntTxt, cMsg.shpgInstnCmntTxt_B1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.delyInstnCmntTxt, cMsg.delyInstnCmntTxt_B1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.istlInstnCmntTxt, cMsg.istlInstnCmntTxt_B1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.techInstnCmntTxt, cMsg.techInstnCmntTxt_B1);

        // Elevator Information & Dimensions
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.elevAvalTxt_Y, cMsg.elevAvalTxt_N));
        // QC#18460_Sol#087 decode time.
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevAvalFromHourMn, NLBL3130CommonLogic.decodeTime(cMsg.elevAvalFromHourMn_C1.getValue(), cMsg));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevAvalToHourMn, NLBL3130CommonLogic.decodeTime(cMsg.elevAvalToHourMn_C1.getValue(), cMsg));

        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevApptReqTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.elevApptReqTxt_Y, cMsg.elevApptReqTxt_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevCtacPsnNm, cMsg.elevCtacPsnNm_C1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevCtacTelNum, cMsg.elevCtacTelNum_C1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevWdt, cMsg.elevWdt_C1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevDepthNum, cMsg.elevDepthNum_C1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevCapWt, cMsg.elevCapWt_C1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevDoorHgt, cMsg.elevDoorHgt_C1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.elevDoorWdt, cMsg.elevDoorWdt_C1);

        // Additional Comments
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.delyInstnAddlCmntTxt, cMsg.delyInstnAddlCmntTxt_D1);

        // Validation
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.vldUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.vldTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP_TIME)));

        // QC#18460_Sol#087 Add column
        // Site Information
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.cmpyInfoFlNm, cMsg.cmpyInfoFlNm_H1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.loadDockHgt, cMsg.loadDockHgt_H1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.delyTrnspOptCd, cMsg.delyTrnspOptCd_H1);
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.stairCrawReqFlg, NLBL3130CommonLogic.setChkBoxValNotNull(cMsg.stairCrawReqFlg_Y, cMsg.stairCrawReqFlg_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.stairAndLdgWdt, cMsg.stairAndLdgWdt_H1);

        // Validation
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.custInfoBoFlg, NLBL3130CommonLogic.setChkBoxValNotNull(cMsg.custInfoBoFlg_Y, cMsg.custInfoBoFlg_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.pickUpXtrTonerFlg, NLBL3130CommonLogic.setChkBoxValNotNull(cMsg.pickUpXtrTonerFlg_Y, cMsg.pickUpXtrTonerFlg_N));
        ZYPEZDItemValueSetter.setValue(shpgOrdDelyInstnTMsg.pickUpAtSameTmFlg, NLBL3130CommonLogic.setChkBoxValNotNull(cMsg.pickUpAtSameTmFlg_Y, cMsg.pickUpAtSameTmFlg_N));

        return shpgOrdDelyInstnTMsg;
    }


    /**
     * QC#23044
     * setItemValuesRws
     * @param cMsg Business Component Interface Message
     * @param rwsDelyInstnTMsg RWS_DELY_INSTNTMsg
     */
    private RWS_DELY_INSTNTMsg setItemValuesRws(NLBL3130CMsg cMsg, RWS_DELY_INSTNTMsg rwsDelyInstnTMsg) {

        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.glblCmpyCd, cMsg.glblCmpyCd_G1);

        // Header
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.rwsNum, cMsg.rwsNum_H1);

        // Site Information
        // QC#18460_Sol#087 decode time.
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.opsFromHourMn, NLBL3130CommonLogic.decodeTime(cMsg.opsFromHourMn_H1.getValue(), cMsg));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.opsToHourMn, NLBL3130CommonLogic.decodeTime(cMsg.opsToHourMn_H1.getValue(), cMsg));

        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.flCovTxt, cMsg.flCovTxt_H1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.insdStepNum, cMsg.insdStepNum_H1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.otsdStepNum, cMsg.otsdStepNum_H1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.bldgStryNum, cMsg.bldgStryNum_H1);

        // Site Information(Check Box)
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.secClncReqTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.secClncReqTxt_Y, cMsg.secClncReqTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.insCertReqTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.insCertReqTxt_Y, cMsg.insCertReqTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.trctrAndTrailAccsTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.trctrAndTrailAccsTxt_Y, cMsg.trctrAndTrailAccsTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.loadDockAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.loadDockAvalTxt_Y, cMsg.loadDockAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.rampAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.rampAvalTxt_Y, cMsg.rampAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.stepAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.stepAvalTxt_Y, cMsg.stepAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.frontDoorAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.frontDoorAvalTxt_Y, cMsg.frontDoorAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.backDoorAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.backDoorAvalTxt_Y, cMsg.backDoorAvalTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.pwrOtltConfigTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.pwrOtltConfigTxt_Y, cMsg.pwrOtltConfigTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.sgnOnBldgRdsdTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.sgnOnBldgRdsdTxt_Y, cMsg.sgnOnBldgRdsdTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.bldgGurdNtfyTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.bldgGurdNtfyTxt_Y, cMsg.bldgGurdNtfyTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.indlParkTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.indlParkTxt_Y, cMsg.indlParkTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.bizParkTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.bizParkTxt_Y, cMsg.bizParkTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.proBldgTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.proBldgTxt_Y, cMsg.proBldgTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.shpngCtrTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.shpngCtrTxt_Y, cMsg.shpngCtrTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.pvtResTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.pvtResTxt_Y, cMsg.pvtResTxt_N));

        // Building Entrance Dimensions
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.bldgEntDoorHgt, cMsg.bldgEntDoorHgt_A1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.bldgEntDoorWdt, cMsg.bldgEntDoorWdt_A1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.crdrWdt, cMsg.crdrWdt_A1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.doorWdt, cMsg.doorWdt_A1);

        // Instructions
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.shpgInstnCmntTxt, cMsg.shpgInstnCmntTxt_B1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.delyInstnCmntTxt, cMsg.delyInstnCmntTxt_B1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.istlInstnCmntTxt, cMsg.istlInstnCmntTxt_B1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.techInstnCmntTxt, cMsg.techInstnCmntTxt_B1);

        // Elevator Information & Dimensions
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevAvalTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.elevAvalTxt_Y, cMsg.elevAvalTxt_N));
        // QC#18460_Sol#087 decode time.
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevAvalFromHourMn, NLBL3130CommonLogic.decodeTime(cMsg.elevAvalFromHourMn_C1.getValue(), cMsg));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevAvalToHourMn, NLBL3130CommonLogic.decodeTime(cMsg.elevAvalToHourMn_C1.getValue(), cMsg));

        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevApptReqTxt, NLBL3130CommonLogic.setChkBoxVal(cMsg.elevApptReqTxt_Y, cMsg.elevApptReqTxt_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevCtacPsnNm, cMsg.elevCtacPsnNm_C1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevCtacTelNum, cMsg.elevCtacTelNum_C1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevWdt, cMsg.elevWdt_C1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevDepthNum, cMsg.elevDepthNum_C1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevCapWt, cMsg.elevCapWt_C1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevDoorHgt, cMsg.elevDoorHgt_C1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.elevDoorWdt, cMsg.elevDoorWdt_C1);

        // Additional Comments
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.delyInstnAddlCmntTxt, cMsg.delyInstnAddlCmntTxt_D1);

        // Validation
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.vldUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.vldTs, ZYPDateUtil.getSalesDate().concat(ZYPDateUtil.getCurrentSystemTime(FORMAT_TIMESTAMP_TIME)));

        // QC#18460_Sol#087 Add column
        // Site Information
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.cmpyInfoFlNm, cMsg.cmpyInfoFlNm_H1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.loadDockHgt, cMsg.loadDockHgt_H1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.delyTrnspOptCd, cMsg.delyTrnspOptCd_H1);
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.stairCrawReqFlg, NLBL3130CommonLogic.setChkBoxValNotNull(cMsg.stairCrawReqFlg_Y, cMsg.stairCrawReqFlg_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.stairAndLdgWdt, cMsg.stairAndLdgWdt_H1);

        // Validation
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.custInfoBoFlg, NLBL3130CommonLogic.setChkBoxValNotNull(cMsg.custInfoBoFlg_Y, cMsg.custInfoBoFlg_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.pickUpXtrTonerFlg, NLBL3130CommonLogic.setChkBoxValNotNull(cMsg.pickUpXtrTonerFlg_Y, cMsg.pickUpXtrTonerFlg_N));
        ZYPEZDItemValueSetter.setValue(rwsDelyInstnTMsg.pickUpAtSameTmFlg, NLBL3130CommonLogic.setChkBoxValNotNull(cMsg.pickUpAtSameTmFlg_Y, cMsg.pickUpAtSameTmFlg_N));

        return rwsDelyInstnTMsg;
    }

    /**
     * getPsnNm
     * @param glblCmpyCd String
     * @param userId String
     */
    private String getPsnNm(String glblCmpyCd, String userId) {

        if (ZYPCommonFunc.hasValue(userId)) {

            S21_PSNTMsg s21PsnTMsg = new S21_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(s21PsnTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnCd, userId);
            s21PsnTMsg = (S21_PSNTMsg) EZDTBLAccessor.findByKey(s21PsnTMsg);

            if (s21PsnTMsg == null) {

                return userId;
            }

            String psnFirstNm = s21PsnTMsg.psnFirstNm.getValue();
            String psnLastNm = s21PsnTMsg.psnLastNm.getValue();

            if (ZYPCommonFunc.hasValue(psnFirstNm) && ZYPCommonFunc.hasValue(psnLastNm)) {

                return ZYPCommonFunc.concatString(psnFirstNm, " ", psnLastNm);

            } else if (ZYPCommonFunc.hasValue(psnFirstNm) && !ZYPCommonFunc.hasValue(psnLastNm)) {

                return psnFirstNm;

            } else if (!ZYPCommonFunc.hasValue(psnFirstNm) && ZYPCommonFunc.hasValue(psnLastNm)) {

                return psnLastNm;
            }

            return userId;
        }

        return null;
    }

    // QC#15586 add start
    /**
     * registShpgOrdMsg
     * @param cMsg Business Component Interface Message
     */
    private void registShpgOrdMsg(NLBL3130CMsg cMsg) {
        // Split Shipping Instruction Comment By 65 Byte
        String shpgInstnCmntTxt = cMsg.shpgInstnCmntTxt_B1.getValue();
        List<String> soMsgList = new ArrayList<String>();
        int beginIndexCurrent = 0;
        int endIndexCurrent = 0;
        // QC#25233 Start
        int endIndex = 0;

        int shpgInstnCnt = 0;    // 2019/03/14 T.Ogura [QC#30707,ADD]
        if (ZYPCommonFunc.hasValue(shpgInstnCmntTxt)) {
            endIndex = shpgInstnCmntTxt.length();
            while (0 != shpgInstnCmntTxt.length() && beginIndexCurrent < endIndex) {

                if (endIndex < endIndexCurrent + SO_MSG_MAX_LEN) {
                    endIndexCurrent = endIndex;
                } else {
                    endIndexCurrent = endIndexCurrent + SO_MSG_MAX_LEN;
                }

                soMsgList.add(shpgInstnCmntTxt.substring(beginIndexCurrent, endIndexCurrent));
                beginIndexCurrent = beginIndexCurrent + SO_MSG_MAX_LEN;
                shpgInstnCnt++;    // 2019/03/14 T.Ogura [QC#30707,ADD]
            }
        }

        String istlInstnCmntTxt = cMsg.istlInstnCmntTxt_B1.getValue();
        beginIndexCurrent = 0;
        endIndexCurrent = 0;
        int istlInstnCnt = 0;    // 2019/03/14 T.Ogura [QC#30707,ADD]
        if (ZYPCommonFunc.hasValue(istlInstnCmntTxt)) {
            endIndex = istlInstnCmntTxt.length();
            while (0 != istlInstnCmntTxt.length() && beginIndexCurrent < endIndex) {

                if (endIndex < endIndexCurrent + SO_MSG_MAX_LEN) {
                    endIndexCurrent = endIndex;
                } else {
                    endIndexCurrent = endIndexCurrent + SO_MSG_MAX_LEN;
                }

                soMsgList.add(istlInstnCmntTxt.substring(beginIndexCurrent, endIndexCurrent));
                beginIndexCurrent = beginIndexCurrent + SO_MSG_MAX_LEN;
                istlInstnCnt++;    // 2019/03/14 T.Ogura [QC#30707,ADD]
            }
        }

        String delyInstnCmntTxt = cMsg.delyInstnCmntTxt_B1.getValue();
        beginIndexCurrent = 0;
        endIndexCurrent = 0;
        int delyInstnCnt = 0;    // 2019/03/14 T.Ogura [QC#30707,ADD]
        if (ZYPCommonFunc.hasValue(delyInstnCmntTxt)) {
            endIndex = delyInstnCmntTxt.length();
            while (0 != delyInstnCmntTxt.length() && beginIndexCurrent < endIndex) {

                if (endIndex < endIndexCurrent + SO_MSG_MAX_LEN) {
                    endIndexCurrent = endIndex;
                } else {
                    endIndexCurrent = endIndexCurrent + SO_MSG_MAX_LEN;
                }

                soMsgList.add(delyInstnCmntTxt.substring(beginIndexCurrent, endIndexCurrent));
                beginIndexCurrent = beginIndexCurrent + SO_MSG_MAX_LEN;
                delyInstnCnt++;    // 2019/03/14 T.Ogura [QC#30707,ADD]
            }
        }
        String techInstnCmntTxt = cMsg.techInstnCmntTxt_B1.getValue();
        beginIndexCurrent = 0;
        endIndexCurrent = 0;
        int techInstnCnt = 0;    // 2019/03/14 T.Ogura [QC#30707,ADD]
        if (ZYPCommonFunc.hasValue(techInstnCmntTxt)) {
            endIndex = techInstnCmntTxt.length();
            while (0 != techInstnCmntTxt.length() && beginIndexCurrent < endIndex) {

                if (endIndex < endIndexCurrent + SO_MSG_MAX_LEN) {
                    endIndexCurrent = endIndex;
                } else {
                    endIndexCurrent = endIndexCurrent + SO_MSG_MAX_LEN;
                }

                soMsgList.add(techInstnCmntTxt.substring(beginIndexCurrent, endIndexCurrent));
                beginIndexCurrent = beginIndexCurrent + SO_MSG_MAX_LEN;
                techInstnCnt++;    // 2019/03/14 T.Ogura [QC#30707,ADD]
            }
        }
        // QC#25233 End

        // START 2019/03/14 T.Ogura [QC#30707,ADD]
        if (isInstructionsCntCheck(cMsg, shpgInstnCnt, istlInstnCnt, delyInstnCnt, techInstnCnt)) {
            return;
        }
        // END   2019/03/14 T.Ogura [QC#30707,ADD]

        // Registration SHPG_ORD_MSG
        SHPG_ORD_MSGTMsg shpgOrdMsgTMsg = new SHPG_ORD_MSGTMsg();
        shpgOrdMsgTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_G1.getValue());
        shpgOrdMsgTMsg.soNum.setValue(cMsg.soNum_H1.getValue());
        shpgOrdMsgTMsg.soMsgTpCd.setValue(SHPG_ORD_MSG_TP.CPO_INFORMATION);

        int intSqNum = 0;
        if (0 != soMsgList.size()) {

            for (String soMsgDescTxt : soMsgList) {
                intSqNum++;
                shpgOrdMsgTMsg.txtSqNum.setValue(String.valueOf(intSqNum));

                SHPG_ORD_MSGTMsg lockShpgOrdMsgTMsg = new SHPG_ORD_MSGTMsg();
                try {
                    lockShpgOrdMsgTMsg = (SHPG_ORD_MSGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(shpgOrdMsgTMsg);
                } catch (EZDDBRecordLockedException e) {
                    cMsg.setMessageInfo(NLBM0050E);
                    return;
                }

                if (lockShpgOrdMsgTMsg == null) {
                    shpgOrdMsgTMsg.soMsgDescTxt.setValue(soMsgDescTxt);
                    S21ApiTBLAccessor.insert(shpgOrdMsgTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(shpgOrdMsgTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(ZZZM9012E, new String[]{shpgOrdMsgTMsg.getReturnCode()});
                        return;
                    }
                } else {
                    lockShpgOrdMsgTMsg.soMsgDescTxt.setValue(soMsgDescTxt);
                    S21ApiTBLAccessor.update(lockShpgOrdMsgTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(lockShpgOrdMsgTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(ZZZM9013E, new String[]{lockShpgOrdMsgTMsg.getReturnCode()});
                        return;
                    }
                }
            }
        }

        // Delete SHPG_ORD_MSG
        int delSqNum = intSqNum + 1;
        int maxSqNum = 0;
        S21SsmEZDResult ssmResult = NLBL3130Query.getInstance().getMaxSqNumBySoMsg(cMsg);
        if (ssmResult.isCodeNormal()) {
            maxSqNum = Integer.parseInt((String) ssmResult.getResultObject());
        }
        while(delSqNum <= maxSqNum) {
            shpgOrdMsgTMsg.txtSqNum.setValue(String.valueOf(delSqNum));
            shpgOrdMsgTMsg = (SHPG_ORD_MSGTMsg) EZDTBLAccessor.findByKey(shpgOrdMsgTMsg);
            if (shpgOrdMsgTMsg != null){
                EZDTBLAccessor.remove(shpgOrdMsgTMsg);
            }
            delSqNum++;
        }

        cMsg.setMessageInfo(NZZM0002I);

    }
    // QC#15586 add end

    /**
     * Add QC#25233
     * registRwsMsg
     * @param cMsg Business Component Interface Message
     */
    private void registRwsMsg(NLBL3130CMsg cMsg) {
        // Split Shipping Instruction Comment By 65 Byte
        String shpgInstnCmntTxt = cMsg.shpgInstnCmntTxt_B1.getValue();
        List<String> rwsMsgList = new ArrayList<String>();
        int beginIndexCurrent = 0;
        int endIndexCurrent = 0;
        int endIndex = 0;

        int shpgInstnCnt = 0;    // 2019/03/14 T.Ogura [QC#30707,ADD]
        if (ZYPCommonFunc.hasValue(shpgInstnCmntTxt)) {
            endIndex = shpgInstnCmntTxt.length();
            while (0 != shpgInstnCmntTxt.length() && beginIndexCurrent < endIndex) {

                if (endIndex < endIndexCurrent + RWS_MSG_MAX_LEN) {
                    endIndexCurrent = endIndex;
                } else {
                    endIndexCurrent = endIndexCurrent + RWS_MSG_MAX_LEN;
                }

                rwsMsgList.add(shpgInstnCmntTxt.substring(beginIndexCurrent, endIndexCurrent));
                beginIndexCurrent = beginIndexCurrent + RWS_MSG_MAX_LEN;
                shpgInstnCnt++;    // 2019/03/14 T.Ogura [QC#30707,ADD]
            }
        }

        // Split Install Instructions Comment By 65 Byte
        String istlInstnCmntTxt = cMsg.istlInstnCmntTxt_B1.getValue();
        beginIndexCurrent = 0;
        endIndexCurrent = 0;
        int istlInstnCnt = 0;    // 2019/03/14 T.Ogura [QC#30707,ADD]
        if (ZYPCommonFunc.hasValue(istlInstnCmntTxt)) {
            endIndex = istlInstnCmntTxt.length();
            while (0 != istlInstnCmntTxt.length() && beginIndexCurrent < endIndex) {

                if (endIndex < endIndexCurrent + RWS_MSG_MAX_LEN) {
                    endIndexCurrent = endIndex;
                } else {
                    endIndexCurrent = endIndexCurrent + RWS_MSG_MAX_LEN;
                }

                rwsMsgList.add(istlInstnCmntTxt.substring(beginIndexCurrent, endIndexCurrent));
                beginIndexCurrent = beginIndexCurrent + RWS_MSG_MAX_LEN;
                istlInstnCnt++;    // 2019/03/14 T.Ogura [QC#30707,ADD]
            }
        }

        // Split Delivery Instructions Comment By 65 Byte
        String delyInstnCmntTxt = cMsg.delyInstnCmntTxt_B1.getValue();
        beginIndexCurrent = 0;
        endIndexCurrent = 0;
        int delyInstnCnt = 0;    // 2019/03/14 T.Ogura [QC#30707,ADD]
        if (ZYPCommonFunc.hasValue(delyInstnCmntTxt)) {
            endIndex = delyInstnCmntTxt.length();
            while (0 != delyInstnCmntTxt.length() && beginIndexCurrent < endIndex) {

                if (endIndex < endIndexCurrent + RWS_MSG_MAX_LEN) {
                    endIndexCurrent = endIndex;
                } else {
                    endIndexCurrent = endIndexCurrent + RWS_MSG_MAX_LEN;
                }

                rwsMsgList.add(delyInstnCmntTxt.substring(beginIndexCurrent, endIndexCurrent));
                beginIndexCurrent = beginIndexCurrent + RWS_MSG_MAX_LEN;
                delyInstnCnt++;    // 2019/03/14 T.Ogura [QC#30707,ADD]
            }
        }

        // Split Technician Instructions Comment By 65 Byte
        String techInstnCmntTxt = cMsg.techInstnCmntTxt_B1.getValue();
        beginIndexCurrent = 0;
        endIndexCurrent = 0;
        int techInstnCnt = 0;    // 2019/03/14 T.Ogura [QC#30707,ADD]
        if (ZYPCommonFunc.hasValue(techInstnCmntTxt)) {
            endIndex = techInstnCmntTxt.length();
            while (0 != techInstnCmntTxt.length() && beginIndexCurrent < endIndex) {

                if (endIndex < endIndexCurrent + RWS_MSG_MAX_LEN) {
                    endIndexCurrent = endIndex;
                } else {
                    endIndexCurrent = endIndexCurrent + RWS_MSG_MAX_LEN;
                }

                rwsMsgList.add(techInstnCmntTxt.substring(beginIndexCurrent, endIndexCurrent));
                beginIndexCurrent = beginIndexCurrent + RWS_MSG_MAX_LEN;
                techInstnCnt++;    // 2019/03/14 T.Ogura [QC#30707,ADD]
            }
        }

        // START 2019/03/14 T.Ogura [QC#30707,ADD]
        if (isInstructionsCntCheck(cMsg, shpgInstnCnt, istlInstnCnt, delyInstnCnt, techInstnCnt)) {
            return;
        }
        // END   2019/03/14 T.Ogura [QC#30707,ADD]

        // Registration RWS_MSG
        RWS_MSGTMsg rwsMsgTMsg = new RWS_MSGTMsg();
        rwsMsgTMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd_G1.getValue());
        rwsMsgTMsg.rwsNum.setValue(cMsg.rwsNum_H1.getValue());

        int intSqNum = 0;
        if (0 != rwsMsgList.size()) {

            for (String rwsMsgTxt : rwsMsgList) {
                intSqNum++;
                rwsMsgTMsg.rwsMsgSqNum.setValue(String.valueOf(intSqNum));

                RWS_MSGTMsg lockRwsMsgTMsg = new RWS_MSGTMsg();
                try {
                    lockRwsMsgTMsg = (RWS_MSGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsMsgTMsg);
                } catch (EZDDBRecordLockedException e) {
                    cMsg.setMessageInfo(NLBM0050E);
                    return;
                }

                if (lockRwsMsgTMsg == null) {
                    rwsMsgTMsg.rwsMsgTxt.setValue(rwsMsgTxt);
                    S21ApiTBLAccessor.insert(rwsMsgTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rwsMsgTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(ZZZM9012E, new String[] {rwsMsgTMsg.getReturnCode() });
                        return;
                    }
                } else {
                    lockRwsMsgTMsg.rwsMsgTxt.setValue(rwsMsgTxt);
                    S21ApiTBLAccessor.update(lockRwsMsgTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(lockRwsMsgTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(ZZZM9013E, new String[] {lockRwsMsgTMsg.getReturnCode() });
                        return;
                    }
                }
            }
        }

        // Delete RWS_MSG
        int delSqNum = intSqNum + 1;
        int maxSqNum = 0;
        S21SsmEZDResult ssmResult = NLBL3130Query.getInstance().getMaxSqNumBySoMsg(cMsg);
        if (ssmResult.isCodeNormal()) {
            maxSqNum = Integer.parseInt((String) ssmResult.getResultObject());
        }
        while (delSqNum <= maxSqNum) {
            rwsMsgTMsg.rwsMsgSqNum.setValue(String.valueOf(delSqNum));
            rwsMsgTMsg = (RWS_MSGTMsg) EZDTBLAccessor.findByKey(rwsMsgTMsg);
            if (rwsMsgTMsg != null) {
                EZDTBLAccessor.remove(rwsMsgTMsg);
            }
            delSqNum++;
        }

        cMsg.setMessageInfo(NZZM0002I);
    }

    // START 2019/03/14 T.Ogura [QC#30707,ADD]
    /**
     * isInstructionsCntCheck
     * @param cMsg Business Component Interface Message
     * @param shpgInstnCnt int
     * @param istlInstnCnt int
     * @param delyInstnCnt int
     * @param techInstnCnt int
     * @return boolean
     */
    private boolean isInstructionsCntCheck(NLBL3130CMsg cMsg, int shpgInstnCnt, int istlInstnCnt, int delyInstnCnt, int techInstnCnt) {

        boolean errFlg = false;
        int msgListCnt = shpgInstnCnt + istlInstnCnt + delyInstnCnt + techInstnCnt;

        if (msgListCnt > 9) {

            if (shpgInstnCnt > 2) {
                cMsg.shpgInstnCmntTxt_B1.setErrorInfo(1, NLBM1370E, new String[]{"Shipping Instructions"});
                errFlg = true;
            }
            if (istlInstnCnt > 2) {
                cMsg.istlInstnCmntTxt_B1.setErrorInfo(1, NLBM1370E, new String[]{"Install Instructions"});
                errFlg = true;
            }
            if (delyInstnCnt > 2) {
                cMsg.delyInstnCmntTxt_B1.setErrorInfo(1, NLBM1370E, new String[]{"Delivery Instructions"});
                errFlg = true;
            }
            if (techInstnCnt > 2) {
                cMsg.techInstnCmntTxt_B1.setErrorInfo(1, NLBM1370E, new String[]{"Technician Instructions"});
                errFlg = true;
            }
        }

        return errFlg;
    }
    // END   2019/03/14 T.Ogura [QC#30707,ADD]

}