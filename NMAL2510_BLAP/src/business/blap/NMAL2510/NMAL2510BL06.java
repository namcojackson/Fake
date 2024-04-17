/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2510;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2510.common.NMAL2510CommonLogic;
import business.blap.NMAL2510.constant.NMAL2510Constant;
import business.db.DS_ORG_RESRC_RELNTMsg;
import business.db.DS_ORG_RESRC_REVTMsg;
import business.db.ORG_FUNC_ASGTMsg;
import business.db.ORG_TOC_CHNG_RQSTTMsg;
import business.db.S21_ORGTMsg;
import business.db.S21_PSNTMsg;
import business.db.TOCTMsg;
import business.db.TOC_ORG_STRU_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Resource Maintenance  NMAL2510BL02
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/01/19   Fujitsu         N.Sugiura       Update          CSA-QC#1930,2807
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/02/23   Fujitsu         C.Yokoi         Update          CSA-QC#4336
 * 2016/02/29   Fujitsu         C.Yokoi         Update          CSA-QC#4080
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4545
 * 2016/03/14   Fujitsu         C.Yokoi         Update          CSA-QC#5416
 * 2016/03/18   Fujitsu         C.Yokoi         Update          CSA-QC#5658
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2016/04/14   Fujitsu         C.Yokoi         Update          CSA-QC#4080
 * 2016/04/21   Fujitsu         C.Yokoi         Update          CSA-QC#4080
 * 2016/06/20   Hitachi         A.Kohinata      Update          CSA-QC#10424
 * 2016/06/29   Hitachi         A.Kohinata      Update          CSA-QC#11087
 * 2016/07/14   Hitachi         Y.Tsuchimoto    Update          CSA-QC#9290
 * 2016/08/05   Fujitsu         C.Yokoi         Update          CSA-QC#12834
 * 2016/09/05   Fujitsu         C.Yokoi         Update          CSA-QC#4099
 * 2016/09/15   SRAA            Y.Chen          Update          CSA-QC#14587
 * 2016/10/04   Fujitsu         Mz.Takahashi    Update          CSA-QC#4099
 * 2016/10/07   Fujitsu         Mz.Takahashi    Update          CSA-QC#15033
 * 2016/11/14   Fujitsu         C.Yokoi         Update          CSA-QC#15937
 * 2017/09/29   Fujitsu         M.Ohno          Update          CSA-QC#21361
 * 2017/10/17   Fujitsu         H.Sugawara      Update          QC#21753
 * 2018/07/26   Fujitsu         R.Nakamura      Update          CSA-QC#20237
 * 2018/10/02   Fujitsu         H.Kumagai       Update          QC#24923
 * 2018/10/16   Fujitsu         K.Ishizuka      Update          CSA-QC#28351
 * 2019/02/13   Fujitsu         Hd.Sugawara     Update          QC#29668
 * 2019/02/27   Fujitsu         Hd.Sugawara     Update          QC#30564
 * </pre>
 */
public class NMAL2510BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (NMAL2510Constant.EVENT_NM_NMAL2510SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NMAL2510Scrn00_Cmn_Submit((NMAL2510CMsg) cMsg, (NMAL2510SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL2510Scrn00_Cmn_Submit(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        int i = 0;

        // 2016/02/08 S21-QC#2869 Delete Start
        // String currentTab = cMsg.xxDplyTab.getValue();

        // if (NMAL2510Constant.TAB_HIERARCHY.equals(currentTab)) {
        // 2016/02/08 S21-QC#2869 Delete End

        // 2018/10/02 Update Start QC#24923
//        if (!NMAL2510CommonLogic.checkInputMandantoryForSubmit(cMsg)) {
        if (!NMAL2510CommonLogic.checkInputMandantoryForSubmit(cMsg, getGlobalCompanyCode())) {
        // 2018/10/02 Update End QC#24923
            cMsg.setMessageInfo(NMAL2510Constant.ZZM9037E);
            return;
        }

        // 2017/09/29 CSA-QC#21361 Add Start
        boolean isChangePsnEndDt = false;

        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1) && !cMsg.effThruDt_H1.getValue().equals(cMsg.effThruDt_HB.getValue())) {
            isChangePsnEndDt = true;
        }
        // 2017/09/29 CSA-QC#21361 Add End

        // ### Header ###
        if (!NMAL2510CommonLogic.checkInputHeaderForSubmit(cMsg, getGlobalCompanyCode())) {
            cMsg.setMessageInfo(NMAL2510Constant.ZZM9037E);
            return;
        }

        // ### Hierarchy ###
        // 2016/06/20 CSA-QC#10424 Mod Start
        if (!NMAL2510CommonLogic.checkInputHierarchyForSubmit(cMsg, getGlobalCompanyCode(), isChangePsnEndDt)) {
        // 2016/06/20 CSA-QC#10424 Mod End
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY);
            return;
        }

        // ### Territory ###
        // 2016/06/29 CSA-QC#11087 Mod Start
        if (!NMAL2510CommonLogic.checkInputTerritoryForSubmit(cMsg, getGlobalCompanyCode(), isChangePsnEndDt)) {
        // 2016/06/29 CSA-QC#11087 Mod End
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_TERRITORY);
            return;
        }

        // ### Revenue ###
        if (!NMAL2510CommonLogic.checkInputRevenueForSubmit(cMsg, getGlobalCompanyCode(), isChangePsnEndDt)) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_REVENUE);
            return;
        }

        // ### Miscellaneouss ###
        if (!NMAL2510CommonLogic.checkInputMiscForSubmit(cMsg)) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_MISC);
            return;
        }

        // Record Lock for update
        S21_PSNTMsg s21PsnTMsg = null;
        if (ZYPCommonFunc.hasValue(cMsg.psnCd_H1)) {
            s21PsnTMsg = new S21_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(s21PsnTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnCd, cMsg.psnCd_H1);

            try {
                s21PsnTMsg = (S21_PSNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(s21PsnTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                return;
            }
        }

        // Add Start 2019/02/13 QC#29668
        String salesDate = ZYPDateUtil.getSalesDate();
        // Add End 2019/02/13 QC#29668

        boolean isDataRegistered = false;
        // 2016/09/05 CSA-QC#4099 Mod Start
        // 2017/09/29 CSA-QC#21361 Del Start
//        boolean isChangePsnEndDt = false;
        // 2017/09/29 CSA-QC#21361 Del End
        // 2016/04/21 CSA-QC#4080 Add Start
        // boolean isEmployeeExpired = false;
        // if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1) && !cMsg.effThruDt_H1.getValue().equals(cMsg.effThruDt_HB.getValue())
        // && cMsg.effThruDt_H1.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {
        // isEmployeeExpired = true;
        // }
        // 2016/04/21 CSA-QC#4080 Add End
        // 2017/09/29 CSA-QC#21361 Del Start
//        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1) && !cMsg.effThruDt_H1.getValue().equals(cMsg.effThruDt_HB.getValue())) {
//            isChangePsnEndDt = true;
//        }
        // 2017/09/29 CSA-QC#21361 Del End
        // 2016/09/05 CSA-QC#4099 Mod End

        // compare uptime
        if (s21PsnTMsg != null) {
            if (NMAL2510CommonLogic.checkChangedFieldsForS21Psn(cMsg)) {
                String ezUpTimeOfScrnPsn = cMsg.ezUpTime_H1.getValue();
                String ezUpTimeZoneOfScrnPsn = cMsg.ezUpTimeZone_H1.getValue();
                String ezUpTimeOfCurrentPsn = s21PsnTMsg.ezUpTime.getValue();
                String ezUpTimeZoneOfCurrentPsn = s21PsnTMsg.ezUpTimeZone.getValue();

                if (!ZYPCommonFunc.hasValue(ezUpTimeOfScrnPsn)) {
                    ezUpTimeOfScrnPsn = "";
                }
                if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfScrnPsn)) {
                    ezUpTimeZoneOfScrnPsn = "";
                }
                if (!ZYPCommonFunc.hasValue(ezUpTimeOfCurrentPsn)) {
                    ezUpTimeOfCurrentPsn = "";
                }
                if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfCurrentPsn)) {
                    ezUpTimeZoneOfCurrentPsn = "";
                }

                // 2016/07/14 CSA-QC#9290 Add Start
                boolean employToThirdFlg = PSN_TP.EMPLOYEE.equals(s21PsnTMsg.psnTpCd.getValue()) && PSN_TP._3RD_PARTY_REP.equals(cMsg.psnTpCd_P1.getValue());
                boolean employToOtherFlg = PSN_TP.EMPLOYEE.equals(s21PsnTMsg.psnTpCd.getValue()) && PSN_TP.OTHER.equals(cMsg.psnTpCd_P1.getValue());
                boolean thirdToEmployFlg = PSN_TP._3RD_PARTY_REP.equals(s21PsnTMsg.psnTpCd.getValue()) && PSN_TP.EMPLOYEE.equals(cMsg.psnTpCd_P1.getValue());
                boolean otherToEmployFlg = PSN_TP.OTHER.equals(s21PsnTMsg.psnTpCd.getValue()) && PSN_TP.EMPLOYEE.equals(cMsg.psnTpCd_P1.getValue());
                if (employToThirdFlg || employToOtherFlg || thirdToEmployFlg || otherToEmployFlg) {
                    cMsg.psnTpCd_P1.setErrorInfo(1, NMAL2510Constant.NMAM8630E);
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8630E);
                    return;
                }
                // 2016/07/14 CSA-QC#9290 Add End

                if (!ezUpTimeOfScrnPsn.equals(ezUpTimeOfCurrentPsn)
                        || !ezUpTimeZoneOfScrnPsn.equals(ezUpTimeZoneOfCurrentPsn)) {
                    cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnFirstNm, cMsg.psnFirstNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnLastNm, cMsg.psnLastNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.hrTtlNm, cMsg.hrTtlNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnTpCd, cMsg.psnTpCd_P1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.emlAddr, cMsg.emlAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.jobTtlCd, cMsg.jobTtlCd_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.effFromDt, cMsg.effFromDt_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.effThruDt, cMsg.effThruDt_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.cellTelNum, cMsg.cellTelNum_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.workTelNum, cMsg.workTelNum_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnNum, cMsg.psnNum_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.hrSupvId, cMsg.hrSupvId_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.hrSupvNm, cMsg.hrSupvNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.hrPsnCmpyNm, cMsg.hrPsnCmpyNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.firstLineAddr, cMsg.firstLineAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.scdLineAddr, cMsg.scdLineAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.frthLineAddr, cMsg.frthLineAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.ctyAddr, cMsg.ctyAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.cntyPk, cMsg.cntyPk_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.provNm, cMsg.provNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.stCd, cMsg.stCd_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.postCd, cMsg.postCd_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.ctryCd, cMsg.ctryCd_H1);
                // 2016/02/08 S21-QC#2869 Add Start
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.geoCd, cMsg.geoCd_C1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.dsInsdCtyLimitFlg, cMsg.dsInsdCtyLimitFlg_C1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.moveOrdLimitAmt, cMsg.moveOrdLimitAmt_D1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.tmZoneCd, cMsg.tmZoneCd_P1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.costPerHourAmt, cMsg.costPerHourAmt_D1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.ctacPsnPk, cMsg.ctacPsnPk_D1);
                // 2016/02/08 S21-QC#2869 Add End
                // 2016/11/14 CSA-QC#15937 Add Start
                if (NMAL2510CommonLogic.checkActiveDate(cMsg.effFromDt_H1.getValue(), cMsg.effThruDt_H1.getValue(), ZYPDateUtil.getSalesDate())) {
                    ZYPEZDItemValueSetter.setValue(s21PsnTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                } else {
                    ZYPEZDItemValueSetter.setValue(s21PsnTMsg.delFlg, ZYPConstant.FLG_ON_Y);
                }
                // 2016/11/14 CSA-QC#15937 Add End

                EZDTBLAccessor.update(s21PsnTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(s21PsnTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"S21_PSN"});
                    return;
                }
                isDataRegistered = true;
            }
        } else {
            if (!PSN_TP.EMPLOYEE.equals(cMsg.psnTpCd_P1.getValue())) {

                if (NMAL2510CommonLogic.checkDuplicatePsnNum(cMsg, sMsg)) {
                    return;
                }

                // Except Employee needs sequence for new PSN_CD
                // QC#14587
                // ZYPEZDItemValueSetter.setValue(cMsg.psnCd_H1, ZYPExtnNumbering.getUniqueID(NMAL2510Constant.BIZAPL_PSNCDKEY));
                ZYPEZDItemValueSetter.setValue(cMsg.psnCd_H1, ZYPMaxTenDigitsNumbering.getUniqueID(NMAL2510Constant.BIZAPL_PSNCDKEY));

                s21PsnTMsg = new S21_PSNTMsg();
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnCd, cMsg.psnCd_H1);

                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnFirstNm, cMsg.psnFirstNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnLastNm, cMsg.psnLastNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.hrTtlNm, cMsg.hrTtlNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnTpCd, cMsg.psnTpCd_P1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.emlAddr, cMsg.emlAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.jobTtlCd, cMsg.jobTtlCd_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.effFromDt, cMsg.effFromDt_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.effThruDt, cMsg.effThruDt_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.cellTelNum, cMsg.cellTelNum_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.workTelNum, cMsg.workTelNum_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.psnNum, cMsg.psnNum_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.hrSupvId, cMsg.hrSupvId_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.hrSupvNm, cMsg.hrSupvNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.hrPsnCmpyNm, cMsg.hrPsnCmpyNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.firstLineAddr, cMsg.firstLineAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.scdLineAddr, cMsg.scdLineAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.thirdLineAddr, cMsg.thirdLineAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.frthLineAddr, cMsg.frthLineAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.ctyAddr, cMsg.ctyAddr_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.cntyPk, cMsg.cntyPk_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.provNm, cMsg.provNm_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.stCd, cMsg.stCd_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.postCd, cMsg.postCd_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.ctryCd, cMsg.ctryCd_H1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.geoCd, cMsg.geoCd_C1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.dsInsdCtyLimitFlg, cMsg.dsInsdCtyLimitFlg_C1);
                // 2016/02/08 S21-QC#2869 Add Start
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.moveOrdLimitAmt, cMsg.moveOrdLimitAmt_D1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.tmZoneCd, cMsg.tmZoneCd_P1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.costPerHourAmt, cMsg.costPerHourAmt_D1);
                ZYPEZDItemValueSetter.setValue(s21PsnTMsg.ctacPsnPk, cMsg.ctacPsnPk_D1);
                // 2016/02/08 S21-QC#2869 Add End

                EZDTBLAccessor.create(s21PsnTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(s21PsnTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0176E, new String[] {"S21_PSN"});
                    return;
                }
                isDataRegistered = true;
            } else {
                cMsg.setMessageInfo(NMAL2510Constant.NMAM0296E);
                return;
            }
        }

        // 2016/02/26 CSA-QC#4627 Add Start
        boolean isChangeResourceSpec = false;
        if (NMAL2510CommonLogic.checkUpdateActivePersonRevenue(cMsg)) {
            isChangeResourceSpec = true;
            isDataRegistered = true;
        }
        // 2016/02/26 CSA-QC#4627 Add End

        sMsg.R.setValidCount(0);
        for (i = 0; i < cMsg.A.getValidCount(); i++) {

            boolean isChgOrgCd = false; // 2018/10/16 S21_NA#28351 Add
            TOC_ORG_STRU_RELNTMsg tocOrgStruRelnTMsg = null;
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).tocCd_A2)) {
                // Record Lock for update
                tocOrgStruRelnTMsg = new TOC_ORG_STRU_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgStruTpCd, cMsg.A.no(i).orgStruTpCd_A2);
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.tocCd, cMsg.A.no(i).tocCd_A2);
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgCd, cMsg.A.no(i).orgCd_AB);

                try {
                    tocOrgStruRelnTMsg = (TOC_ORG_STRU_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tocOrgStruRelnTMsg);
                } catch (EZDDBRecordLockedException e) {
                    cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                    cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY); // 2016/02/08 S21-QC#2869 Add
                    return;
                }
            }

           // 2016/02/26 CSA-QC#4627 Add Start
            boolean isChangeOrgSpec = false;
            if (NMAL2510CommonLogic.checkChangeActiveOrgFuncRoleType(cMsg, i)) {
                isChangeOrgSpec = true;
            }
            // 2016/02/26 CSA-QC#4627 Add End

            // compare uptime
            if (tocOrgStruRelnTMsg != null) {
              // 2016/09/05 CSA-QC#4099 Mod Start
                if (NMAL2510CommonLogic.checkChangedFieldsForTocOrgStruReln(cMsg, i) || isChangePsnEndDt) {
              // if (NMAL2510CommonLogic.checkChangedFieldsForTocOrgStruReln(cMsg, i) || isEmployeeExpired) {
              // 2016/09/05 CSA-QC#4099 Mod End
                    String ezUpTimeOfScrnReln = cMsg.A.no(i).ezUpTime_A3.getValue();
                    String ezUpTimeZoneOfScrnReln = cMsg.A.no(i).ezUpTimeZone_A3.getValue();
                    String ezUpTimeOfCurrentReln = tocOrgStruRelnTMsg.ezUpTime.getValue();
                    String ezUpTimeZoneOfCurrentReln = tocOrgStruRelnTMsg.ezUpTimeZone.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        return;
                    }

                    // Add Start 2019/02/13 QC#29668
                    if (NMAL2510CommonLogic.isActiveOrg(cMsg, i)) {
                        isChangeOrgSpec = true;
                    }
                    // Add End 2019/02/13 QC#29668

                    // 2016/02/26 CSA-QC#4627 Add Start
                    if (NMAL2510CommonLogic.checkChangeActiveOrgCd(cMsg, i)) {
                        // Mod Start 2019/02/13 QC#29668
                        //S21FastTBLAccessor.removePhysical(new TOC_ORG_STRU_RELNTMsg[] {tocOrgStruRelnTMsg });
                        if (!changePastTocOrgStruReln(cMsg, i, tocOrgStruRelnTMsg, salesDate)) {
                            return;
                        }
                        // Mod End 2019/02/13 QC#29668

                        // 2018/10/16 S21_NA#28351 Add Start
                        cMsg.A.no(i).tocCd_A2.setValue(ZYPExtnNumbering.getUniqueID(NMAL2510Constant.BIZAPL_TOCCDKEY));
                        isChgOrgCd = true;
                        // 2018/10/16 S21_NA#28351 Add End
                        // Mod Start 2019/02/13 QC#29668
                        //insertTocOrgStruReln(cMsg, i);
                        insertTocOrgStruRelnForOrgChg(cMsg, i, salesDate);
                        // Mod End 2019/02/13 QC#29668
                        isChangeOrgSpec = true;
                    } else {
                        // 2016/02/26 CSA-QC#4627 Add End

                        // Add Start 2019/02/27 QC#30564
                        if (NMAL2510CommonLogic.isNotEquals(cMsg.A.no(i).orgCd_AB.getValue(), cMsg.A.no(i).orgCd_A2.getValue())) {
                            // Becouse of orgCd is primary key, if
                            // orgCd is changed, need to delete and
                            // insert.
                            TOC_ORG_STRU_RELNTMsg newTocOrgStruRelnTMsg = (TOC_ORG_STRU_RELNTMsg) tocOrgStruRelnTMsg.clone();

                            S21FastTBLAccessor.removePhysical(new TOC_ORG_STRU_RELNTMsg[] {tocOrgStruRelnTMsg });

                            String rtCd = tocOrgStruRelnTMsg.getReturnCode();
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                                cMsg.setMessageInfo(NMAL2510Constant.MMAM0005E, new String[] {"TOC_ORG_STRU_RELN" });
                                return;
                            }

                            ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.orgCd, cMsg.A.no(i).orgCd_A2);
                            ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.effFromDt, cMsg.A.no(i).effFromDt_A2);
                            ZYPEZDItemValueSetter.setValue(newTocOrgStruRelnTMsg.effThruDt, cMsg.A.no(i).effThruDt_A2);

                            EZDTBLAccessor.insert(newTocOrgStruRelnTMsg);

                            rtCd = newTocOrgStruRelnTMsg.getReturnCode();
                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                                cMsg.setMessageInfo(NMAL2510Constant.MMAM0003E, new String[] {"TOC_ORG_STRU_RELN" });
                                return;
                            }
                        } else {
                            // Add End 2019/02/27 QC#30564

                        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effFromDt, cMsg.A.no(i).effFromDt_A2);
                        // 2016/04/21 CSA-QC#4080 Mod Start
                        // 2016/09/05 CSA-QC#4099 Mod Start
                        //if (isChangePsnEndDt) {
                        // if (isEmployeeExpired){
                        // 2016/09/05 CSA-QC#4099 Mod End
                        // 2017/09/29 CSA-QC#21361 Mod Start
//                            if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.A.no(i).effFromDt_A2.getValue()) < 0) {
//                                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effThruDt, cMsg.A.no(i).effFromDt_A2);
//                            } else if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A2.getValue())
//                                    || cMsg.effThruDt_H1.getValue().compareTo(cMsg.A.no(i).effThruDt_A2.getValue()) <= 0) {
//                                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effThruDt, cMsg.effThruDt_H1);
//                            } else {
//                                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effThruDt, cMsg.A.no(i).effThruDt_A2);
//                            }
//                        } else {
                        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effThruDt, cMsg.A.no(i).effThruDt_A2);
//                        }
                        // 2017/09/29 CSA-QC#21361 Mod End
                        // 2016/04/21 CSA-QC#4080 Mod End

                        EZDTBLAccessor.update(tocOrgStruRelnTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnTMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"TOC_ORG_STRU_RELN"});
                            return;
                        }

                        // Add Start 2019/02/27 QC#30564
                        }
                        // Add End 2019/02/27 QC#30564
                    }
                    isDataRegistered = true;
                }
            } else {
                cMsg.A.no(i).tocCd_A2.setValue(ZYPExtnNumbering.getUniqueID(NMAL2510Constant.BIZAPL_TOCCDKEY));
                if (!insertTocOrgStruReln(cMsg, i)) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0176E, new String[] {"TOC_ORG_STRU_RELN"});
                    return;
                }
                isDataRegistered = true;
            }

            // Record Lock for update
            ORG_TOC_CHNG_RQSTTMsg orgTocChngRqstTMsg = new ORG_TOC_CHNG_RQSTTMsg();
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocCd, cMsg.A.no(i).tocCd_A2);
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgChngRqstPk, cMsg.A.no(i).orgChngRqstPk_A2);

            try {
                orgTocChngRqstTMsg = (ORG_TOC_CHNG_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(orgTocChngRqstTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY); // 2016/02/08
                // S21-QC#2869 Add
                return;
            }

            // compare uptime
            if (orgTocChngRqstTMsg != null) {
                    if (NMAL2510CommonLogic.checkChangedFieldsForOrgTocChngRqst(cMsg, i) || isChangeOrgSpec || isChangeResourceSpec) {
                        String ezUpTimeOfScrnRqst = cMsg.A.no(i).ezUpTime_A4.getValue();
                        String ezUpTimeZoneOfScrnRqst = cMsg.A.no(i).ezUpTimeZone_A4.getValue();
                        String ezUpTimeOfCurrentRqst = orgTocChngRqstTMsg.ezUpTime.getValue();
                        String ezUpTimeZoneOfCurrentRqst = orgTocChngRqstTMsg.ezUpTimeZone.getValue();

                        if (!ZYPCommonFunc.hasValue(ezUpTimeOfScrnRqst)) {
                            ezUpTimeOfScrnRqst = "";
                        }
                        if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfScrnRqst)) {
                            ezUpTimeZoneOfScrnRqst = "";
                        }
                        if (!ZYPCommonFunc.hasValue(ezUpTimeOfCurrentRqst)) {
                            ezUpTimeOfCurrentRqst = "";
                        }
                        if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfCurrentRqst)) {
                            ezUpTimeZoneOfCurrentRqst = "";
                        }

                        if (!ezUpTimeOfScrnRqst.equals(ezUpTimeOfCurrentRqst) || !ezUpTimeZoneOfScrnRqst.equals(ezUpTimeZoneOfCurrentRqst)) {
                            cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY); // 2016/02/08
                            // S21-QC#2869
                            // Add
                            return;
                        }
                        // 2016/01/19 CSA-QC#2807 Mod Start
                        // ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocNm,
                        // cMsg.psnNum_H1);
                        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocNm, S21StringUtil.subStringByLength(S21StringUtil.concatStrings(cMsg.psnFirstNm_H1.getValue(), " ", cMsg.psnLastNm_H1.getValue()), 0,
                                NMAL2510Constant.COLUMN_LEN_TOC_NM));
                        // 2016/01/19 CSA-QC#2807 Mod End
                        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgFuncRoleTpCd, cMsg.A.no(i).orgFuncRoleTpCd_P2);
                        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgCd, cMsg.A.no(i).orgCd_A2);
                        if (NMAL2510CommonLogic.getDefaultRevenue(cMsg)) {
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaBrCd, cMsg.coaBrCd_H1);
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaExtnCd, cMsg.coaExtnCd_H1);
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCcCd, cMsg.coaCcCd_H1);
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCmpyCd, cMsg.coaCmpyCd_H1);
                        }

                        // 2016/02/26 CSA-QC#4627 Add Start
                        if (isChangeOrgSpec || isChangeResourceSpec) {
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                        }
                        // 2016/02/26 CSA-QC#4627 Add End
                        // Add Start 2019/02/27 QC#30564
                        if (NMAL2510CommonLogic.isChangedHierarchyDate(cMsg, i)) {
                            // If hierarchy date is changed, need to processed by NMAB2400
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                        }
                        // Add End 2019/02/27 QC#30564

                        EZDTBLAccessor.update(orgTocChngRqstTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgTocChngRqstTMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"ORG_TOC_CHNG_RQST"});
                            return;
                        }
                        isDataRegistered = true;
                    }

            } else {
                if (!NMAL2510CommonLogic.createOrgTocChngRqst(cMsg, orgTocChngRqstTMsg, getGlobalCompanyCode(), i)) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0176E, new String[] {"ORG_TOC_CHNG_RQST"});
                }
                isDataRegistered = true;
            }

            // Record Lock for update
            ORG_FUNC_ASGTMsg orgFuncAsgTMsg = new ORG_FUNC_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.tocCd, cMsg.A.no(i).tocCd_A2);
            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.psnCd, cMsg.psnCd_H1);
            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effFromDt, cMsg.A.no(i).effFromDt_A2);

            try {
                orgFuncAsgTMsg = (ORG_FUNC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(orgFuncAsgTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY); // 2016/02/08 S21-QC#2869 Add
                return;
            }

            // compare uptime
            if (orgFuncAsgTMsg != null) {
                    // 2016/09/05 CSA-QC#4099 Mod Start
                    if (NMAL2510CommonLogic.checkChangedFieldsForOrgFuncAsg(cMsg, i) || isChangeOrgSpec || isChangeResourceSpec || isChangePsnEndDt) {
                    // if (NMAL2510CommonLogic.checkChangedFieldsForOrgFuncAsg(cMsg, i) ||isChangeOrgSpec || isChangeResourceSpec || isEmployeeExpired) {
                    // 2016/09/05 CSA-QC#4099 Mod End
                        String ezUpTimeOfScrnAsg = cMsg.A.no(i).ezUpTime_A5.getValue();
                        String ezUpTimeZoneOfScrnAsg = cMsg.A.no(i).ezUpTimeZone_A5.getValue();
                        String ezUpTimeOfCurrentAsg = orgFuncAsgTMsg.ezUpTime.getValue();
                        String ezUpTimeZoneOfCurrentAsg = orgFuncAsgTMsg.ezUpTimeZone.getValue();

                        if (!ZYPCommonFunc.hasValue(ezUpTimeOfScrnAsg)) {
                            ezUpTimeOfScrnAsg = "";
                        }
                        if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfScrnAsg)) {
                            ezUpTimeZoneOfScrnAsg = "";
                        }
                        if (!ZYPCommonFunc.hasValue(ezUpTimeOfCurrentAsg)) {
                            ezUpTimeOfCurrentAsg = "";
                        }
                        if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfCurrentAsg)) {
                            ezUpTimeZoneOfCurrentAsg = "";
                        }

                        if (!ezUpTimeOfScrnAsg.equals(ezUpTimeOfCurrentAsg) || !ezUpTimeZoneOfScrnAsg.equals(ezUpTimeZoneOfCurrentAsg)) {
                            cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY); // 2016/02/08
                            // S21-QC#2869
                            // Add
                            return;
                        }

                        // 2016/02/26 CSA-QC#4627 Add Start
                        // Add Start 2017/10/17 QC#21753
                        //if (isChangeOrgSpec || isChangeResourceSpec) {
                        if (isChangeOrgSpec || isChangeResourceSpec || NMAL2510CommonLogic.checkChangedFieldsForOrgFuncAsg(cMsg, i)) {
                            // Add End 2017/10/17 QC#21753
                            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                        }
                        // 2016/02/26 CSA-QC#4627 Add End

                        // 2016/04/21 CSA-QC#4080 Mod Start
                        // 2016/09/05 CSA-QC#4099 Mod Start
                        //if (isChangePsnEndDt) {
                        // if (isEmployeeExpired){
                        // 2016/09/05 CSA-QC#4099 Mod End
                        // 2017/09/29 CSA-QC#21361 Mod Start
//                            if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.A.no(i).effFromDt_A2.getValue()) < 0) {
//                                ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effThruDt, cMsg.A.no(i).effFromDt_A2);
//                            } else if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A2.getValue())
//                                    || cMsg.effThruDt_H1.getValue().compareTo(cMsg.A.no(i).effThruDt_A2.getValue()) <= 0) {
//                                ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effThruDt, cMsg.effThruDt_H1);
//                            } else {
//                                ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effThruDt, cMsg.A.no(i).effThruDt_A2);
//                            }
//                        } else {
                        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effThruDt, cMsg.A.no(i).effThruDt_A2);
//                        }
                        // 2017/09/29 CSA-QC#21361 Mod End
                        // 2016/04/21 CSA-QC#4080 Mod End

                        EZDTBLAccessor.update(orgFuncAsgTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgFuncAsgTMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"ORG_FUNC_ASG"});
                            return;
                        }
                        isDataRegistered = true;
                }
            } else {
                // 2016/01/19 CSA-QC#1930 Del Start
                // from date should be bigger than sales date when
                // it's registered.
                // if
                // (!NMAL2510CommonLogic.checkFromDate(cMsg.A.no(i).effFromDt_A2.getValue()))
                // {
                // cMsg.setMessageInfo(NMAL2510Constant.NMAM0044E, new
                // String[]
                // {NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
                // NMAL2510Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                // return;
                // }
                // 2016/01/19 CSA-QC#1930 Del End
                // start date has been changed, old data should be
                // deleted.
                // Mod Start 2019/02/13 QC#29668
                //if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A2)) {
                if (!isChgOrgCd && ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A2)) {
                    // Mod End 2019/02/13 QC#29668
                    if (!cMsg.A.no(i).effFromDt_A2.getValue().equals(sMsg.A.no(i).effFromDt_A2.getValue())) {

                        // ORG_FUNC_ASG
                        ZYPEZDItemValueSetter.setValue(sMsg.R.no(sMsg.R.getValidCount()).tocCd_R1, sMsg.A.no(i).tocCd_A2.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.R.no(sMsg.R.getValidCount()).psnCd_R1, sMsg.psnCd_H1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.R.no(sMsg.R.getValidCount()).effFromDt_R1, sMsg.A.no(i).effFromDt_A2.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.R.no(sMsg.R.getValidCount()).ezUpTime_R1, sMsg.A.no(i).ezUpTime_A5.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.R.no(sMsg.R.getValidCount()).ezUpTimeZone_R1, sMsg.A.no(i).ezUpTimeZone_A5.getValue());

                        sMsg.R.setValidCount(sMsg.R.getValidCount() + 1);
                    }
                }
                // 2018/10/17 S21_NA#28351 Add Start
                if(isChgOrgCd){
                    ORG_FUNC_ASGTMsg orgFuncAsgTMsgForRm = new ORG_FUNC_ASGTMsg();
                    ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsgForRm.glblCmpyCd, getGlobalCompanyCode());
                    ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsgForRm.tocCd, sMsg.A.no(i).tocCd_A2);
                    ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsgForRm.psnCd, sMsg.psnCd_H1);
                    ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsgForRm.effFromDt, sMsg.A.no(i).effFromDt_A2);
                    // Mod Start 2019/02/13 QC#29668
                    //S21FastTBLAccessor.removePhysical(new ORG_FUNC_ASGTMsg[] {orgFuncAsgTMsgForRm });
                    if(!changePastOrgFuncAsgWithLock(cMsg, i, orgFuncAsgTMsgForRm, salesDate)) {
                        return;
                    }
                    // Mod End 2019/02/13 QC#29668
                }
                // 2018/10/17 S21_NA#28351 Add End

                // Mod Start 2019/02/13 QC#29668
                //if (!NMAL2510CommonLogic.createOrgFuncAsg(cMsg, orgFuncAsgTMsg, getGlobalCompanyCode(), i)) {
                if (!NMAL2510CommonLogic.createOrgFuncAsg(cMsg, orgFuncAsgTMsg, getGlobalCompanyCode(), i, salesDate)) {
                    // Mod End 2019/02/13 QC#29668
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0176E, new String[] {"ORG_FUNC_ASG"});
                }
                isDataRegistered = true;
            }
        }

        if (!deleteTocOrgStruReln(cMsg, sMsg)) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY);
            return;
        }

        if (!deleteOrgTocChngRqst(cMsg, sMsg)) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY);
            return;
        }

        if (!deleteOrgFuncAsg(cMsg, sMsg)) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY);
            return;
        }

        if (!deleteOrgFuncAsgForUpdate(cMsg, sMsg)) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY);
            return;
        }

        if (sMsg.X.getValidCount() > 0 || sMsg.R.getValidCount() > 0) {
            isDataRegistered = true;
        }

        for (i = 0; i < cMsg.B.getValidCount(); i++) {

            // Record Lock for update
            DS_ORG_RESRC_RELNTMsg dsOrgResrcRelnTMsg = new DS_ORG_RESRC_RELNTMsg();

            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgCd, cMsg.B.no(i).orgCd_BB);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgStruTpCd, cMsg.B.no(i).orgStruTpCd_B2);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.psnCd, cMsg.psnCd_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgFuncRoleTpCd, cMsg.B.no(i).orgFuncRoleTpCd_BB);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effFromDt, cMsg.B.no(i).effFromDt_BB);

            try {
                dsOrgResrcRelnTMsg = (DS_ORG_RESRC_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgResrcRelnTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_TERRITORY); // 2016/02/08 S21-QC#2869 Add
                return;
            }

            // compare uptime
            if (dsOrgResrcRelnTMsg != null) {
                // 2016/09/05 CSA-QC#4099 Mod Start
                if (NMAL2510CommonLogic.checkChangedFieldsForDsOrgResrcReln(cMsg, i) || isChangePsnEndDt) {
                // if (NMAL2510CommonLogic.checkChangedFieldsForDsOrgResrcReln(cMsg, i) || isEmployeeExpired) {
                // 2016/09/05 CSA-QC#4099 Mod End
                    String ezUpTimeOfScrnReln = cMsg.B.no(i).ezUpTime_B3.getValue();
                    String ezUpTimeZoneOfScrnReln = cMsg.B.no(i).ezUpTimeZone_B3.getValue();
                    String ezUpTimeOfCurrentReln = dsOrgResrcRelnTMsg.ezUpTime.getValue();
                    String ezUpTimeZoneOfCurrentReln = dsOrgResrcRelnTMsg.ezUpTimeZone.getValue();

                    if (!ZYPCommonFunc.hasValue(ezUpTimeOfScrnReln)) {
                        ezUpTimeOfScrnReln = "";
                    }
                    if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfScrnReln)) {
                        ezUpTimeZoneOfScrnReln = "";
                    }
                    if (!ZYPCommonFunc.hasValue(ezUpTimeOfCurrentReln)) {
                        ezUpTimeOfCurrentReln = "";
                    }
                    if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfCurrentReln)) {
                        ezUpTimeZoneOfCurrentReln = "";
                    }

                    if (!ezUpTimeOfScrnReln.equals(ezUpTimeOfCurrentReln)
                            || !ezUpTimeZoneOfScrnReln.equals(ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_TERRITORY); // 2016/02/08 S21-QC#2869 Add
                        return;
                    }

                    // 2016/03/14 S21-QC#5416 Add End
                    // 2016/03/18 QC-CSA#5658 Add Start
                    if (NMAL2510CommonLogic.isNotEquals(cMsg.B.no(i).orgCd_BB.getValue(), cMsg.B.no(i).orgCd_B2.getValue())
                            || NMAL2510CommonLogic.isNotEquals(cMsg.B.no(i).orgFuncRoleTpCd_BB.getValue(), cMsg.B.no(i).orgFuncRoleTpCd_B2.getValue())
                            || NMAL2510CommonLogic.isNotEquals(cMsg.B.no(i).effFromDt_BB.getValue(), cMsg.B.no(i).effFromDt_B2.getValue())) {
                        // 2016/03/18 QC-CSA#5658 Add End
                        S21FastTBLAccessor.removePhysical(new DS_ORG_RESRC_RELNTMsg[] {dsOrgResrcRelnTMsg });
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgResrcRelnTMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"DS_ORG_RESRC_RELN"});
                            return;
                        }

                        dsOrgResrcRelnTMsg = new DS_ORG_RESRC_RELNTMsg();
                        if (!createDsOrgResrcReln(cMsg, i, dsOrgResrcRelnTMsg)) {
                            cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"DS_ORG_RESRC_RELN"});
                            return;
                        }
                    // 2016/03/14 S21-QC#5416 Add End

                    } else {

                        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.acctTeamRoleTpCd, cMsg.B.no(i).acctTeamRoleTpCd_P3);
                        // 2016/04/21 CSA-QC#4080 Mod Start
                        // 2016/09/05 CSA-QC#4099 Mod Start
                        //if (isChangePsnEndDt) {
                        // if (isEmployeeExpired){
                        // 2016/09/05 CSA-QC#4099 Mod End
                        // 2017/09/29 CSA-QC#21361 Mod Start
//                            if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.B.no(i).effFromDt_B2.getValue()) < 0) {
//                                ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effThruDt, cMsg.B.no(i).effFromDt_B2);
//                            } else if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B2.getValue())
//                                    || cMsg.effThruDt_H1.getValue().compareTo(cMsg.B.no(i).effThruDt_B2.getValue()) <= 0) {
//                                ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effThruDt, cMsg.effThruDt_H1);
//                            } else {
//                                ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effThruDt, cMsg.B.no(i).effThruDt_B2);
//                            }
//                        } else {
                        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effThruDt, cMsg.B.no(i).effThruDt_B2);
//                        }
                        // 2017/09/29 CSA-QC#21361 Mod End
                        // 2016/04/21 CSA-QC#4080 Mod End

                        // Add Start 2017/10/17 QC#21753
                        if (NMAL2510CommonLogic.isNotEquals(cMsg.B.no(i).effThruDt_BB.getValue(), cMsg.B.no(i).effThruDt_B2.getValue())) {
                            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                        }
                        // Add End 2017/10/17 QC#21753

                        EZDTBLAccessor.update(dsOrgResrcRelnTMsg);
                    }
                    isDataRegistered = true;
                }
            } else {

                dsOrgResrcRelnTMsg = new DS_ORG_RESRC_RELNTMsg();
                if (!createDsOrgResrcReln(cMsg, i, dsOrgResrcRelnTMsg)) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0176E, new String[] {"DS_ORG_RESRC_RELN"});
                    return;
                }
                isDataRegistered = true;
            }
        }

        if (!deletedsOrgResrcReln(cMsg, sMsg)) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_TERRITORY);
            return;
        }

        if (sMsg.Y.getValidCount() > 0) {
            isDataRegistered = true;
        }

        for (i = 0; i < cMsg.C.getValidCount(); i++) {
            // Record Lock for update
            // 2016/10/07 CSA-QC#15033 Mod Start
            DS_ORG_RESRC_REVTMsg dsOrgResrcRevTMsg = null;
            DS_ORG_RESRC_REVTMsg dsOrgResrcRevCondTMsg = null;

            if (ZYPCommonFunc.hasValue(cMsg.C.no(i).ezUpTime_C3)) {
                dsOrgResrcRevCondTMsg = new DS_ORG_RESRC_REVTMsg();

                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevCondTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevCondTMsg.psnCd, cMsg.psnCd_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevCondTMsg.revAcctTpCd, cMsg.C.no(i).revAcctTpCd_CB);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevCondTMsg.effFromDt, cMsg.C.no(i).effFromDt_CB);

                //ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.glblCmpyCd, getGlobalCompanyCode());
                //ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.psnCd, cMsg.psnCd_H1);
                //ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.revAcctTpCd, cMsg.C.no(i).revAcctTpCd_P1);
                //ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.effFromDt, cMsg.C.no(i).effFromDt_C2);
                // 2016/10/07 CSA-QC#15033 Mod End

                try {
                    dsOrgResrcRevTMsg = (DS_ORG_RESRC_REVTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgResrcRevCondTMsg);
                } catch (EZDDBRecordLockedException e) {
                    cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                    cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_REVENUE); // 2016/02/08 S21-QC#2869 Add
                    return;
                }
            }

            // compare uptime
            if (dsOrgResrcRevTMsg != null) {
                // 2016/09/05 CSA-QC#4099 Mod Start
                if (NMAL2510CommonLogic.checkChangedFieldsForDsOrgResrcRev(cMsg, i) || isChangePsnEndDt) {
                // if (NMAL2510CommonLogic.checkChangedFieldsForDsOrgResrcRev(cMsg, i) || isEmployeeExpired) {
                // 2016/09/05 CSA-QC#4099 Mod End
                    String ezUpTimeOfScrn = cMsg.C.no(i).ezUpTime_C3.getValue();
                    String ezUpTimeZoneOfScrn = cMsg.C.no(i).ezUpTimeZone_C3.getValue();
                    String ezUpTimeOfCurrent = dsOrgResrcRevTMsg.ezUpTime.getValue();
                    String ezUpTimeZoneOfCurrent = dsOrgResrcRevTMsg.ezUpTimeZone.getValue();

                    if (!ZYPCommonFunc.hasValue(ezUpTimeOfScrn)) {
                        ezUpTimeOfScrn = "";
                    }
                    if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfScrn)) {
                        ezUpTimeZoneOfScrn = "";
                    }
                    if (!ZYPCommonFunc.hasValue(ezUpTimeOfCurrent)) {
                        ezUpTimeOfCurrent = "";
                    }
                    if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfCurrent)) {
                        ezUpTimeZoneOfCurrent = "";
                    }

                    if (!ezUpTimeOfScrn.equals(ezUpTimeOfCurrent) || !ezUpTimeZoneOfScrn.equals(ezUpTimeZoneOfCurrent)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_REVENUE); // 2016/02/08 S21-QC#2869 Add
                        return;
                    }

                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.coaCmpyCd, cMsg.C.no(i).coaCmpyCd_C2);
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.coaExtnCd, cMsg.C.no(i).coaExtnCd_C2);
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.coaBrCd, cMsg.C.no(i).coaBrCd_C2);
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.coaCcCd, cMsg.C.no(i).coaCcCd_C2);
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.manEntryFlg, cMsg.C.no(i).xxChkBox_C3);
                    // 2016/10/07 CSA-QC#15033 Add Start
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.effFromDt, cMsg.C.no(i).effFromDt_C2);
                    // 2016/10/07 CSA-QC#15033 Add End

                    // 2016/04/21 CSA-QC#4080 Mod Start
                    // 2016/09/05 CSA-QC#4099 Mod Start
                    //if (isChangePsnEndDt) {
                    // if (isEmployeeExpired) {
                    // 2016/09/05 CSA-QC#4099 Mod End
                    // 2017/09/29 CSA-QC#21361 Mod Start
//                        ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.effThruDt, cMsg.effThruDt_H1);
//                    } else {
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.effThruDt, cMsg.C.no(i).effThruDt_C2);
//                    }
                    // 2017/09/29 CSA-QC#21361 Mod End
                    // 2016/04/21 CSA-QC#4080 Mod End
                    // Add Start 2019/02/13 QC#29668
                    if (NMAL2510CommonLogic.isNotEquals(cMsg.C.no(i).effThruDt_CB.getValue(), cMsg.C.no(i).effThruDt_C2.getValue())) {
                        String beforeEffThruDt = cMsg.C.no(i).effThruDt_CB.getValue();
                        String afterEffThruDt = cMsg.C.no(i).effThruDt_C2.getValue();
                        if (!ZYPCommonFunc.hasValue(afterEffThruDt)) {
                            afterEffThruDt = NMAL2510Constant.MAX_EFF_THRU_DT;
                        }
                        if (ZYPCommonFunc.hasValue(beforeEffThruDt) && salesDate.compareTo(beforeEffThruDt) > 0) {
                            if (salesDate.compareTo(afterEffThruDt) <= 0) {
                                // If change from PAST to CURRENT.
                                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.tocRgtnReqFlg, ZYPConstant.FLG_ON_Y);
                                ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).tocRgtnReqFlg_C2, ZYPConstant.FLG_ON_Y);
                            }
                        }
                    }

                    String effFromDt = cMsg.C.no(i).effFromDt_C2.getValue();
                    if (effFromDt.compareTo(salesDate) > 0) {
                        ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.tocRgtnReqFlg, ZYPConstant.FLG_ON_Y);
                        ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).tocRgtnReqFlg_C2, ZYPConstant.FLG_ON_Y);
                    } else {
                        ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.tocRgtnReqFlg, cMsg.C.no(i).tocRgtnReqFlg_C2);
                    }
                    // Add End 2019/02/13 QC#29668

                    // 2016/10/07 CSA-QC#15033 Add Start
                    //Delete->Insert
                    // Mod Start 2019/02/13 QC#29668
                    //int result = S21FastTBLAccessor.removeLogical(new DS_ORG_RESRC_REVTMsg[]{dsOrgResrcRevCondTMsg});
                    int result = S21FastTBLAccessor.removePhysical(new DS_ORG_RESRC_REVTMsg[] {dsOrgResrcRevCondTMsg });
                    // Mod End 2019/02/13 QC#29668
                    if (result <= 0) {
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {NMAL2510Constant.MSG_DS_ORG_RESRC_REV });
                        return;
                    }

                    try {
                        EZDTBLAccessor.create(dsOrgResrcRevTMsg);
                    } catch (Exception e) {
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM8182I, new String[] {NMAL2510Constant.TAB_REVENUE });
                        return;
                    }

                    //EZDTBLAccessor.update(dsOrgResrcRevTMsg);

                    //if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgResrcRevTMsg.getReturnCode())) {
                    //    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"DS_ORG_RESRC_REV"});
                    //    return;
                    //}
                    // 2016/10/07 CSA-QC#15033 Add End
                    isDataRegistered = true;
                }
            } else {
                // error if from date is pastdate
                // Del Start 2018/07/26 QC#20237
//                if (!NMAL2510CommonLogic.isDateFuture(cMsg.C.no(i).effFromDt_C2.getValue())) {
                // Del End 2018/07/26 QC#20237
                // 2016/10/04 CSA-QC#4099 Add Start
                // Mod Start 2018/07/26 QC#20237
//                if (!NMAL2510CommonLogic.isExitsFutureFromDate(cMsg, cMsg.C.no(i).effThruDt_C2.getValue(), i)) {
                if (!NMAL2510CommonLogic.isExitsFutureFromDateAddLine(cMsg, cMsg.C.no(i).effThruDt_C2.getValue(), i)) {
                // Mod End 2018/07/26 QC#20237
                // 2016/10/04 CSA-QC#4099 Add End
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.C.no(i).effFromDt_C2.setErrorInfo(1, NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2510Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                    // 2016/03/03 CSA-QC#4545 Add End
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2510Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                    cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_REVENUE); // 2016/02/08
                    // S21-QC#2869 Add
                    return;
                // 2016/10/04 CSA-QC#4099 Add Start
                }
                // 2016/10/04 CSA-QC#4099 Add End
                // Del Start 2018/07/26 QC#20237
//                }
                // Del Start 2018/07/26 QC#20237

                // start date has been changed, old data should be
                // deleted.
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).effFromDt_C2)) {
                    if (!cMsg.C.no(i).effFromDt_C2.getValue().equals(sMsg.C.no(i).effFromDt_C2.getValue())) {

                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).psnCd_Z1, sMsg.psnCd_H1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).revAcctTpCd_Z1, sMsg.C.no(i).revAcctTpCd_P1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).effFromDt_Z1, sMsg.C.no(i).effFromDt_C2.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTime_Z1, sMsg.C.no(i).ezUpTime_C3.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTimeZone_Z1, sMsg.C.no(i).ezUpTimeZone_C3.getValue());

                        sMsg.Z.setValidCount(sMsg.Z.getValidCount() + 1);
                    }
                }

                dsOrgResrcRevTMsg = new DS_ORG_RESRC_REVTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.psnCd, cMsg.psnCd_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.revAcctTpCd, cMsg.C.no(i).revAcctTpCd_P1);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.effFromDt, cMsg.C.no(i).effFromDt_C2);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.coaCmpyCd, cMsg.C.no(i).coaCmpyCd_C2);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.coaExtnCd, cMsg.C.no(i).coaExtnCd_C2);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.coaBrCd, cMsg.C.no(i).coaBrCd_C2);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.coaCcCd, cMsg.C.no(i).coaCcCd_C2);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.effThruDt, cMsg.C.no(i).effThruDt_C2);
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.manEntryFlg, cMsg.C.no(i).xxChkBox_C3);
                // Add Start 2019/02/13 QC#29668
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRevTMsg.tocRgtnReqFlg, cMsg.C.no(i).tocRgtnReqFlg_C2);
                // Add End 2019/02/13 QC#29668

                try {
                    EZDTBLAccessor.create(dsOrgResrcRevTMsg);
                    isDataRegistered = true;
                } catch (Exception e) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8182I, new String[] {NMAL2510Constant.TAB_REVENUE });
                }
            }

            // Add Start 2019/02/13 QC#29668
            if (NMAL2510CommonLogic.isChangeExistingActiveGLString(cMsg, i, salesDate)) {
                if (!updateExistingActiveGLString(cMsg, i)) {
                    return;
                }
            }
            // Add End 2019/02/13 QC#29668
        }

        if (!deletedsOrgResrcRev(cMsg, sMsg)) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_REVENUE); // 2016/02/08
            // S21-QC#2869
            // Add
            return;
        }

        if (sMsg.Z.getValidCount() > 0) {
            isDataRegistered = true;
        }

        if (isDataRegistered) {
            cMsg.setMessageInfo(NMAL2510Constant.NMAM8182I, new String[] {NMAL2510Constant.SUBMIT });
        } else {
            cMsg.setMessageInfo(NMAL2510Constant.NMAM8333I);
        }
    }

    // Add Start 2019/02/13 QC#29668
    /**
     * changePastTocOrgStruReln
     * @param cMsg
     * @param i
     * @param oldTocOrgStruRelnTMsg
     * @param salseDate
     * @return boolean
     */
    private boolean changePastTocOrgStruReln(NMAL2510CMsg cMsg, int i, TOC_ORG_STRU_RELNTMsg oldTocOrgStruRelnTMsg, String salesDate) {
        String effFromDt = oldTocOrgStruRelnTMsg.effFromDt.getValue();
        String effThruDt = null;

        if (NMAL2510CommonLogic.isNotEquals(cMsg.A.no(i).effFromDt_AB.getValue(), cMsg.A.no(i).effFromDt_A2.getValue())) {
            effThruDt = ZYPDateUtil.addDays(cMsg.A.no(i).effFromDt_A2.getValue(), -1);
        } else {
            effThruDt = ZYPDateUtil.addDays(salesDate, -1);
        }

        if (ZYPCommonFunc.hasValue(effFromDt) && effFromDt.compareTo(effThruDt) > 0) {
            // If effFromDt > effThruDt, effFromDt is same as
            // effThruDt.
            ZYPEZDItemValueSetter.setValue(oldTocOrgStruRelnTMsg.effFromDt, effThruDt);
        }

        ZYPEZDItemValueSetter.setValue(oldTocOrgStruRelnTMsg.effThruDt, effThruDt);

        // Update TOC_ORG_STRU_RELN
        S21FastTBLAccessor.update(oldTocOrgStruRelnTMsg);

        String rtCd = oldTocOrgStruRelnTMsg.getReturnCode();
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
            cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"TOC_ORG_STRU_RELN" });
            return false;
        }

        return true;
    }

    /**
     * changePastOrgFuncAsg
     * @param cMsg
     * @param oldOrgFuncAsgTMsg
     * @param salseDate
     * @return boolean
     */
    private boolean changePastOrgFuncAsgWithLock(NMAL2510CMsg cMsg, int i, ORG_FUNC_ASGTMsg oldOrgFuncAsgTMsg, String salesDate) {

        String effFromDt = oldOrgFuncAsgTMsg.effFromDt.getValue();
        String effThruDt = null;
        ORG_FUNC_ASGTMsg newOrgFuncAsgTMsg = null;

        if (NMAL2510CommonLogic.isNotEquals(cMsg.A.no(i).effFromDt_AB.getValue(), cMsg.A.no(i).effFromDt_A2.getValue())) {
            effThruDt = ZYPDateUtil.addDays(cMsg.A.no(i).effFromDt_A2.getValue(), -1);
        } else {
            effThruDt = ZYPDateUtil.addDays(salesDate, -1);
        }

        boolean delInsFlag = false;
        if (effFromDt.compareTo(effThruDt) > 0) {
            delInsFlag = true;

            newOrgFuncAsgTMsg = (ORG_FUNC_ASGTMsg) oldOrgFuncAsgTMsg.clone();

            // If effFromDt > effThruDt, effFromDt is same as
            // effThruDt.
            ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.effFromDt, effThruDt);
            ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.effThruDt, effThruDt);
            ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);
        }

        try {
            oldOrgFuncAsgTMsg = (ORG_FUNC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(oldOrgFuncAsgTMsg);
        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
            return false;
        }

        if (oldOrgFuncAsgTMsg != null) {
            if (delInsFlag) {
                // Delete ORG_FUNC_ASG
                S21FastTBLAccessor.removePhysical(new ORG_FUNC_ASGTMsg[] {oldOrgFuncAsgTMsg });

                String rtCd = oldOrgFuncAsgTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                    cMsg.setMessageInfo(NMAL2510Constant.MMAM0005E, new String[] {"ORG_FUNC_ASG" });
                    return false;
                }
            } else {
                // Update ORG_FUNC_ASG
                ZYPEZDItemValueSetter.setValue(oldOrgFuncAsgTMsg.effThruDt, effThruDt);
                ZYPEZDItemValueSetter.setValue(oldOrgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);

                S21FastTBLAccessor.update(oldOrgFuncAsgTMsg);

                String rtCd = oldOrgFuncAsgTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"ORG_FUNC_ASG" });
                    return false;
                }
            }
        }

        if (delInsFlag) {
            EZDTBLAccessor.insert(newOrgFuncAsgTMsg);

            String rtCd = newOrgFuncAsgTMsg.getReturnCode();
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                cMsg.setMessageInfo(NMAL2510Constant.MMAM0003E, new String[] {"ORG_FUNC_ASG" });
                return false;
            }
        }

        return true;
    }

    /**
     * @param cMsg NMAL2510CMsg
     * @param index int
     * @return boolean
     */
    private boolean updateExistingActiveGLString(NMAL2510CMsg cMsg, int index) {

        TOCTMsg tocTMsg = null;
        S21_ORGTMsg s21OrgTMsg = null;
        String rtCd = null;
        String slsDate = ZYPDateUtil.getSalesDate();

        NMAL2510_CCMsg revenueLine = cMsg.C.no(index);

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NMAL2510_ACMsg hierarchyLine = cMsg.A.no(i);

            if (!ZYPCommonFunc.hasValue(hierarchyLine.tocCd_A2)) {
                continue;
            }

            if (hierarchyLine.effFromDt_A2.getValue().compareTo(slsDate) > 0) {
                continue;
            }

            if (ZYPCommonFunc.hasValue(hierarchyLine.effThruDt_A2) && hierarchyLine.effThruDt_A2.getValue().compareTo(slsDate) < 0) {
                continue;
            }

            // Record Lock for update
            tocTMsg = new TOCTMsg();
            ZYPEZDItemValueSetter.setValue(tocTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(tocTMsg.tocCd, hierarchyLine.tocCd_A2);

            try {
                tocTMsg = (TOCTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tocTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                return false;
            }

            if (tocTMsg != null) {
                ZYPEZDItemValueSetter.setValue(tocTMsg.coaCmpyCd, revenueLine.coaCmpyCd_C2);
                ZYPEZDItemValueSetter.setValue(tocTMsg.coaExtnCd, revenueLine.coaExtnCd_C2);
                ZYPEZDItemValueSetter.setValue(tocTMsg.coaBrCd, revenueLine.coaBrCd_C2);
                ZYPEZDItemValueSetter.setValue(tocTMsg.coaCcCd, revenueLine.coaCcCd_C2);

                EZDTBLAccessor.update(tocTMsg);

                rtCd = tocTMsg.getReturnCode();
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"TOC" });
                    return false;
                }
            }

            // Record Lock for update
            s21OrgTMsg = new S21_ORGTMsg();
            ZYPEZDItemValueSetter.setValue(s21OrgTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(s21OrgTMsg.tocCd, hierarchyLine.tocCd_A2);

            try {
                s21OrgTMsg = (S21_ORGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(s21OrgTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                return false;
            }

            if (s21OrgTMsg != null) {
                ZYPEZDItemValueSetter.setValue(s21OrgTMsg.coaBrCd, revenueLine.coaBrCd_C2);
                ZYPEZDItemValueSetter.setValue(s21OrgTMsg.coaCcCd, revenueLine.coaCcCd_C2);

                EZDTBLAccessor.update(s21OrgTMsg);

                rtCd = s21OrgTMsg.getReturnCode();
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(s21OrgTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"S21_ORG" });
                    return false;
                }
            }
        }

        return true;
    }
    // Add End 2019/02/13 QC#29668

    private boolean createDsOrgResrcReln(NMAL2510CMsg cMsg, int i, DS_ORG_RESRC_RELNTMsg dsOrgResrcRelnTMsg) {
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgCd, cMsg.B.no(i).orgCd_B2);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgStruTpCd, cMsg.B.no(i).orgStruTpCd_B2);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.psnCd, cMsg.psnCd_H1);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgFuncRoleTpCd, cMsg.B.no(i).orgFuncRoleTpCd_B2);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effFromDt, cMsg.B.no(i).effFromDt_B2);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effThruDt, cMsg.B.no(i).effThruDt_B2);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.acctTeamRoleTpCd, cMsg.B.no(i).acctTeamRoleTpCd_P3);

        if (ZYPCommonFunc.hasValue(cMsg.B.no(i).acctTeamRoleTpCd_P3)) {
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.nonSlsRepFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.nonSlsRepFlg, ZYPConstant.FLG_OFF_N);
        }

        EZDTBLAccessor.create(dsOrgResrcRelnTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgResrcRelnTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean insertTocOrgStruReln(NMAL2510CMsg cMsg, int i) {
        // For Manual Input pressed Add button

        TOC_ORG_STRU_RELNTMsg tocOrgStruRelnTMsg = new TOC_ORG_STRU_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgStruTpCd, cMsg.A.no(i).orgStruTpCd_A2);
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.tocCd, cMsg.A.no(i).tocCd_A2);

        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgCd, cMsg.A.no(i).orgCd_A2);
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effFromDt, cMsg.A.no(i).effFromDt_A2);
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effThruDt, cMsg.A.no(i).effThruDt_A2);

        EZDTBLAccessor.insert(tocOrgStruRelnTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    // Add Start 2019/02/13 QC#29668
    private boolean insertTocOrgStruRelnForOrgChg(NMAL2510CMsg cMsg, int i, String salesDate) {
        // For Manual Input pressed Add button
        TOC_ORG_STRU_RELNTMsg tocOrgStruRelnTMsg = new TOC_ORG_STRU_RELNTMsg();

        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgStruTpCd, cMsg.A.no(i).orgStruTpCd_A2);
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.tocCd, cMsg.A.no(i).tocCd_A2);
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgCd, cMsg.A.no(i).orgCd_A2);

        String effFromDt = null;

        if (NMAL2510CommonLogic.isNotEquals(cMsg.A.no(i).effFromDt_AB.getValue(), cMsg.A.no(i).effFromDt_A2.getValue())) {
            effFromDt = cMsg.A.no(i).effFromDt_A2.getValue();
        } else {
            effFromDt = salesDate;
        }

        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effFromDt, effFromDt);
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effThruDt, cMsg.A.no(i).effThruDt_A2);

        EZDTBLAccessor.insert(tocOrgStruRelnTMsg);

        String rtCd = tocOrgStruRelnTMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
            return false;
        }

        return true;
    }

    // Add End 2019/02/13 QC#29668

    private boolean deleteTocOrgStruReln(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        // 2016/08/05 CSA-QC#12834 Delete Start
        // int len = sMsg.X.getValidCount();
        // int cnt = 0;
        // if (len > 0) {
        // TOC_ORG_STRU_RELNTMsg[] tocOrgStruRelnArr = new TOC_ORG_STRU_RELNTMsg[len];
        // 2016/08/05 CSA-QC#12834 Delete End
          if (sMsg.X.getValidCount() > 0) {

            String ezUpTimeOfScrnReln = null;
            String ezUpTimeZoneOfScrnReln = null;
            String ezUpTimeOfCurrentReln = null;
            String ezUpTimeZoneOfCurrentReln = null;

            for (int j = 0; j < sMsg.X.getValidCount(); j++) {
                TOC_ORG_STRU_RELNTMsg tocOrgStruRelnDel = new TOC_ORG_STRU_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.orgStruTpCd, sMsg.X.no(j).orgStruTpCd_X1.getValue());
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.tocCd, sMsg.X.no(j).tocCd_X1.getValue());
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.orgCd, sMsg.X.no(j).orgCd_X1.getValue());

                tocOrgStruRelnDel = (TOC_ORG_STRU_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tocOrgStruRelnDel);

                if (tocOrgStruRelnDel == null || !RTNCD_NORMAL.equals(tocOrgStruRelnDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8111E, new String[] {NMAL2510Constant.MSG_TOC_ORG_STRU_RELN });
                    return false;
                }

                // 2016/03/30 CSA-QC#5945 Add Start
                if (GNRN_TP.DELETE.equals(sMsg.X.no(j).gnrnTpCd_X1.getValue())) {
                    ezUpTimeOfScrnReln = tocOrgStruRelnDel.ezUpTime.getValue();
                    ezUpTimeZoneOfScrnReln = tocOrgStruRelnDel.ezUpTimeZone.getValue();
                    ezUpTimeOfCurrentReln = sMsg.X.no(j).ezUpTime_X1.getValue();
                    ezUpTimeZoneOfCurrentReln = sMsg.X.no(j).ezUpTimeZone_X1.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.effThruDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(), -1));
                    EZDTBLAccessor.update(tocOrgStruRelnDel);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnDel.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"TOC_ORG_STRU_RELN"});
                        return false;
                    }
//                    len--;
                    continue;
                }
                // 2016/03/30 CSA-QC#5945 Add End

        // 2016/08/05 CSA-QC#12834 Delete Start
//                tocOrgStruRelnArr[cnt] = tocOrgStruRelnDel;
//                cnt++;
        // 2016/08/05 CSA-QC#12834 Delete End
            }

        // 2016/08/05 CSA-QC#12834 Delete Start
//            if (cnt > 0) {
//                int result = S21FastTBLAccessor.removeLogical(tocOrgStruRelnArr);
//                if (result != len) {
//                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {NMAL2510Constant.MSG_TOC_ORG_STRU_RELN });
//                    return false;
//                }
//            }
        // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    private boolean deleteOrgTocChngRqst(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        // 2016/08/05 CSA-QC#12834 Delete Start
//        int len = sMsg.X.getValidCount();
//        int cnt = 0;
//        if (len > 0) {
//          ORG_TOC_CHNG_RQSTTMsg[] orgTocChngRqstArr = new ORG_TOC_CHNG_RQSTTMsg[sMsg.X.getValidCount()];
        // 2016/08/05 CSA-QC#12834 Delete Start
        if (sMsg.X.getValidCount() > 0) {

            String ezUpTimeOfScrnReln = null;
            String ezUpTimeZoneOfScrnReln = null;
            String ezUpTimeOfCurrentReln = null;
            String ezUpTimeZoneOfCurrentReln = null;

            for (int j = 0; j < sMsg.X.getValidCount(); j++) {

                ORG_TOC_CHNG_RQSTTMsg orgTocChngRqstDel = new ORG_TOC_CHNG_RQSTTMsg();
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstDel.tocCd, sMsg.X.no(j).tocCd_X2.getValue());
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstDel.orgChngRqstPk, sMsg.X.no(j).orgChngRqstPk_X2.getValue());

                orgTocChngRqstDel = (ORG_TOC_CHNG_RQSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(orgTocChngRqstDel);

                if (orgTocChngRqstDel == null || !RTNCD_NORMAL.equals(orgTocChngRqstDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8111E, new String[] {NMAL2510Constant.MSG_ORG_TOC_CHNG_RQST });
                    return false;
                }
                if (GNRN_TP.DELETE.equals(sMsg.X.no(j).gnrnTpCd_X1.getValue())) {
                    ezUpTimeOfScrnReln = orgTocChngRqstDel.ezUpTime.getValue();
                    ezUpTimeZoneOfScrnReln = orgTocChngRqstDel.ezUpTimeZone.getValue();
                    ezUpTimeOfCurrentReln = sMsg.X.no(j).ezUpTime_X2.getValue();
                    ezUpTimeZoneOfCurrentReln = sMsg.X.no(j).ezUpTimeZone_X2.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(orgTocChngRqstDel.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);

                    EZDTBLAccessor.update(orgTocChngRqstDel);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgTocChngRqstDel.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"ORG_TOC_CHNG_RQST"});
                        return false;
                    }
//                    len--;
                }
        // 2016/08/05 CSA-QC#12834 Delete Start
//                orgTocChngRqstArr[cnt] = orgTocChngRqstDel;
//                cnt++;
        // 2016/08/05 CSA-QC#12834 Delete End
            }

        // 2016/08/05 CSA-QC#12834 Delete Start
//            if (cnt > 0) {
//                int result = S21FastTBLAccessor.removeLogical(orgTocChngRqstArr);
//                if (result != len) {
//                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {NMAL2510Constant.MSG_ORG_TOC_CHNG_RQST });
//                    return false;
//                }
//            }
        // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    private boolean deleteOrgFuncAsg(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        // 2016/08/05 CSA-QC#12834 Delete Start
//        int len = sMsg.X.getValidCount();
//        int cnt = 0;
//        if (len > 0) {
//            ORG_FUNC_ASGTMsg[] orgFuncAsgArr = new ORG_FUNC_ASGTMsg[sMsg.X.getValidCount()];
        // 2016/08/05 CSA-QC#12834 Delete End

        if (sMsg.X.getValidCount() > 0) {
            String ezUpTimeOfScrnReln = null;
            String ezUpTimeZoneOfScrnReln = null;
            String ezUpTimeOfCurrentReln = null;
            String ezUpTimeZoneOfCurrentReln = null;

            for (int j = 0; j < sMsg.X.getValidCount(); j++) {
                ORG_FUNC_ASGTMsg orgFuncAsgDel = new ORG_FUNC_ASGTMsg();
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.tocCd, sMsg.X.no(j).tocCd_X3.getValue());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.psnCd, sMsg.X.no(j).psnCd_X3.getValue());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.effFromDt, sMsg.X.no(j).effFromDt_X3.getValue());

                orgFuncAsgDel = (ORG_FUNC_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(orgFuncAsgDel);

                if (orgFuncAsgDel == null || !RTNCD_NORMAL.equals(orgFuncAsgDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8111E, new String[] {NMAL2510Constant.MSG_ORG_FUNC_ASG });
                    return false;
                }

                // 2016/03/30 CSA-QC#5945 Add Start
                if (GNRN_TP.DELETE.equals(sMsg.X.no(j).gnrnTpCd_X1.getValue())) {
                    ezUpTimeOfScrnReln = orgFuncAsgDel.ezUpTime.getValue();
                    ezUpTimeZoneOfScrnReln = orgFuncAsgDel.ezUpTimeZone.getValue();
                    ezUpTimeOfCurrentReln = sMsg.X.no(j).ezUpTime_X3.getValue();
                    ezUpTimeZoneOfCurrentReln = sMsg.X.no(j).ezUpTimeZone_X3.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.effThruDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(), -1));
                    ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.gnrnTpCd, GNRN_TP.DELETE);

                    EZDTBLAccessor.update(orgFuncAsgDel);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgFuncAsgDel.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"ORG_FUNC_ASG"});
                        return false;
                    }
//                    len--;
                    continue;
                }
                // 2016/03/30 CSA-QC#5945 Add End

        // 2016/08/05 CSA-QC#12834 Delete Start
//                orgFuncAsgArr[cnt] = orgFuncAsgDel;
//                cnt++;
        // 2016/08/05 CSA-QC#12834 Delete End
            }

        // 2016/08/05 CSA-QC#12834 Delete Start
//            if (cnt > 0) {
//                int result = S21FastTBLAccessor.removeLogical(orgFuncAsgArr);
//                if (result != len) {
//                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {NMAL2510Constant.MSG_ORG_FUNC_ASG });
//                    return false;
//                }
//            }
        // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    private boolean deleteOrgFuncAsgForUpdate(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        int len = sMsg.R.getValidCount();
        int cnt = 0;

        if (len > 0) {
            ORG_FUNC_ASGTMsg[] orgFuncAsgArr = new ORG_FUNC_ASGTMsg[len];

            for (int j = 0; j < sMsg.R.getValidCount(); j++) {
                ORG_FUNC_ASGTMsg orgFuncAsgDel = new ORG_FUNC_ASGTMsg();
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.tocCd, sMsg.R.no(j).tocCd_R1.getValue());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.psnCd, sMsg.R.no(j).psnCd_R1.getValue());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.effFromDt, sMsg.R.no(j).effFromDt_R1.getValue());

                orgFuncAsgDel = (ORG_FUNC_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(orgFuncAsgDel);

                if (orgFuncAsgDel == null || !RTNCD_NORMAL.equals(orgFuncAsgDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8111E, new String[] {NMAL2510Constant.MSG_ORG_FUNC_ASG });
                    return false;
                }
                orgFuncAsgArr[cnt] = orgFuncAsgDel;
                cnt++;
            }

            if (cnt > 0) {
                // Mod Start 2019/02/13 QC#29668
                //int result = S21FastTBLAccessor.removeLogical(orgFuncAsgArr);
                int result = S21FastTBLAccessor.removePhysical(orgFuncAsgArr);
                // Mod End 2019/02/13 QC#29668
                if (result != len) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {NMAL2510Constant.MSG_ORG_FUNC_ASG });
                    return false;
                }
            }
        }

        return true;
    }

    private boolean deletedsOrgResrcReln(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {
        // 2016/08/05 CSA-QC#12834 Delete Start
        // int len = sMsg.Y.getValidCount();
        // int cnt = 0;

        //  if (len > 0) {
        //  DS_ORG_RESRC_RELNTMsg[] dsOrgResrcRelnArr = new DS_ORG_RESRC_RELNTMsg[len];
        // 2016/08/05 CSA-QC#12834 Delete End
         if (sMsg.Y.getValidCount() > 0) {

            String ezUpTimeOfScrnReln = null;
            String ezUpTimeZoneOfScrnReln = null;
            String ezUpTimeOfCurrentReln = null;
            String ezUpTimeZoneOfCurrentReln = null;

            for (int j = 0; j < sMsg.Y.getValidCount(); j++) {
                DS_ORG_RESRC_RELNTMsg dsORgResrcRelnDel = new DS_ORG_RESRC_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(dsORgResrcRelnDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsORgResrcRelnDel.orgCd, sMsg.Y.no(j).orgCd_Y1.getValue());
                ZYPEZDItemValueSetter.setValue(dsORgResrcRelnDel.orgStruTpCd, sMsg.Y.no(j).orgStruTpCd_Y1.getValue());
                ZYPEZDItemValueSetter.setValue(dsORgResrcRelnDel.psnCd, sMsg.Y.no(j).psnCd_Y1.getValue());
                ZYPEZDItemValueSetter.setValue(dsORgResrcRelnDel.orgFuncRoleTpCd, sMsg.Y.no(j).orgFuncRoleTpCd_Y1.getValue());
                ZYPEZDItemValueSetter.setValue(dsORgResrcRelnDel.effFromDt, sMsg.Y.no(j).effFromDt_Y1.getValue());

                dsORgResrcRelnDel = (DS_ORG_RESRC_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsORgResrcRelnDel);

                if (dsORgResrcRelnDel == null || !RTNCD_NORMAL.equals(dsORgResrcRelnDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8111E, new String[] {NMAL2510Constant.MSG_DS_ORG_RESRC_RELN });
                    return false;
                }

                // 2016/03/30 CSA-QC#5945 Add Start
                if (GNRN_TP.DELETE.equals(sMsg.Y.no(j).gnrnTpCd_Y1.getValue())) {
                    ezUpTimeOfScrnReln = dsORgResrcRelnDel.ezUpTime.getValue();
                    ezUpTimeZoneOfScrnReln = dsORgResrcRelnDel.ezUpTimeZone.getValue();
                    ezUpTimeOfCurrentReln = sMsg.Y.no(j).ezUpTime_Y1.getValue();
                    ezUpTimeZoneOfCurrentReln = sMsg.Y.no(j).ezUpTimeZone_Y1.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(dsORgResrcRelnDel.effThruDt, ZYPDateUtil.addDays(ZYPDateUtil.getSalesDate(), -1));
                    ZYPEZDItemValueSetter.setValue(dsORgResrcRelnDel.gnrnTpCd, GNRN_TP.DELETE);

                    EZDTBLAccessor.update(dsORgResrcRelnDel);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsORgResrcRelnDel.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"DS_ORG_RESRC_RELN"});
                        return false;
                    }
                // 2016/08/05 CSA-QC#12834 Delete Start
                //   len--;
                //   continue;
                // 2016/08/05 CSA-QC#12834 Delete End
                }
                // 2016/03/30 CSA-QC#5945 Add End

            // 2016/08/05 CSA-QC#12834 Delete Start
            //  dsOrgResrcRelnArr[cnt] = dsORgResrcRelnDel;
            //  cnt++;
            // 2016/08/05 CSA-QC#12834 Delete End
            }

        // 2016/08/05 CSA-QC#12834 Delete Start
        //   if (cnt > 0) {
        //     int result = S21FastTBLAccessor.removeLogical(dsOrgResrcRelnArr);
        //     if (result != len) {
        //        cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {NMAL2510Constant.MSG_DS_ORG_RESRC_RELN });
        //       return false;
        //   }
        //  }
        // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    private boolean deletedsOrgResrcRev(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        int len = sMsg.Z.getValidCount();
        if (len > 0) {
            DS_ORG_RESRC_REVTMsg[] dsOrgResrcRevArr = new DS_ORG_RESRC_REVTMsg[len];
            int cnt = 0;

            for (int j = 0; j < sMsg.Z.getValidCount(); j++) {
                DS_ORG_RESRC_REVTMsg dsORgResrcRevDel = new DS_ORG_RESRC_REVTMsg();
                ZYPEZDItemValueSetter.setValue(dsORgResrcRevDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsORgResrcRevDel.psnCd, sMsg.Z.no(j).psnCd_Z1.getValue());
                ZYPEZDItemValueSetter.setValue(dsORgResrcRevDel.revAcctTpCd, sMsg.Z.no(j).revAcctTpCd_Z1.getValue());
                ZYPEZDItemValueSetter.setValue(dsORgResrcRevDel.effFromDt, sMsg.Z.no(j).effFromDt_Z1.getValue());

                dsORgResrcRevDel = (DS_ORG_RESRC_REVTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsORgResrcRevDel);

                if (dsORgResrcRevDel == null || !RTNCD_NORMAL.equals(dsORgResrcRevDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8111E, new String[] {NMAL2510Constant.MSG_DS_ORG_RESRC_REV });
                    return false;
                }
                dsOrgResrcRevArr[cnt] = dsORgResrcRevDel;
                cnt++;
            }

            // Mod Start 2019/02/13 QC#29668
            //int result = S21FastTBLAccessor.removeLogical(dsOrgResrcRevArr);
            int result = S21FastTBLAccessor.removePhysical(dsOrgResrcRevArr);
            // Mod End 2019/02/13 QC#29668
            if (result != len) {
                cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {NMAL2510Constant.MSG_DS_ORG_RESRC_REV });
                return false;
            }
        }

        return true;
    }
}
