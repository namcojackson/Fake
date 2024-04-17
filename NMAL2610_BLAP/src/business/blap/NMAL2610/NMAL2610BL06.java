/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2610;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItemArray;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2610.common.NMAL2610CommonLogic;
import business.blap.NMAL2610.constant.NMAL2610Constant;
import business.db.DS_ORG_RELNTMsg;
import business.db.DS_ORG_RESRC_RELNTMsg;
import business.db.DS_ORG_UNITTMsg;
import business.db.TRTY_RULETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Resource Maintenance  NMAL2610BL02
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/15   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/03   Fujitsu         C.Tanaka        Create          QC#4551
 * 2016/03/14   Fujitsu         C.Yokoi         Update          CSA-QC#5414, CSA-QC#5416
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2016/07/08   Fujitsu         C.Tanaka        Update          CSA-QC#10675
 * 2016/08/05   Fujitsu         C.Yokoi         Update          CSA-QC#12834
 * 2017/03/07   Fujitsu         M.Ohno          Update          S21_NA#17760
 * 2017/03/03   Fujitsu         R.Nakamura      Update          QC#15878
 * 2017/03/13   Fujitsu         M.Ohno          Update          S21_NA#17760-2
 * 2017/06/26   Fujitsu         N.Aoyama        Update          S21_NA#16677
 * 2017/09/19   Fujitsu         A.Kosai         Update          QC#20650
 * 2017/10/17   Fujitsu         H.Sugawara      Update          QC#21753
 * 2017/11/21   Fujitsu         M.Ohno          Update          QC#21350
 * 2018/06/01   Fujitsu         Hd.Sugawara     Update          QC#24293
 * 2018/08/03   Fujitsu         T.Noguchi       Update          QC#26752
 * 2018/10/18   Fujitsu         M.Ohno          Update          QC#28277
 * 2019/09/05   Fujitsu         Hd.Sugawara     Update          QC#51704
 * 2019/12/06   Fujitsu         A.Kazuki        Update          QC#53019
 * 2019/12/27   Fujitsu         A.Kazuki        Update          QC#54222-1
 * </pre>
 */
public class NMAL2610BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (NMAL2610Constant.EVENT_NM_NMAL2610SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NMAL2610Scrn00_Cmn_Submit((NMAL2610CMsg) cMsg, (NMAL2610SMsg) sMsg);
                resetTerritoryRuleOperandPulldownList((NMAL2610CMsg) cMsg);
                // 2018/10/18 QC#28277 Add Start
                if (cMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_ERROR) || cMsg.getMessageCode().endsWith(NMAL2610Constant.MESSAGE_KIND_WARN)) {
                    NMAL2610SMsg glblMsg = (NMAL2610SMsg) sMsg;
                    NMAL2610CMsg bizMsg = (NMAL2610CMsg) cMsg;
                    if (!ZYPCommonFunc.hasValue(glblMsg.orgCd_H1)) {
                        bizMsg.orgCd_H1.clear();
                    }
                }
                // 2018/10/18 QC#28277 Add End
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL2610Scrn00_Cmn_Submit(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        // 2017/06/23 QC#16677 ADD START
        // the input data have to copy to glblMsg
        NMAL2610CommonLogic.updateGlblMsg(cMsg, sMsg);
        // 2017/06/23 QC#16677 ADD E N D

        // 2018/08/03 QC#26752 Add Start
        cMsg.setCommitSMsg(true);
        // 2018/08/03 QC#26752 Add End

        // 2016/02/09 S21-QC#2869 Mod Start
        // String currentTab = cMsg.xxDplyTab.getValue();
        cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_OFF_N);
        String glblCompanyCd = getGlobalCompanyCode();

        // get Terrtitory ID
        DS_ORG_UNITTMsg dsOrgUnitTMsg = null;
        dsOrgUnitTMsg = setTerritoryId(cMsg, dsOrgUnitTMsg);

        if (NMAL2610Constant.MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            return;
        }

        // Mandantory Check
        // 2017/06/23 QC#16677 Mod START
        // Mod Start 2017/03/03 QC#15878
        // if (!NMAL2610CommonLogic.checkMandantoryItem(cMsg)) {
        // if (!NMAL2610CommonLogic.checkMandantoryItem(cMsg, getGlobalCompanyCode())) {
        if (!NMAL2610CommonLogic.checkMandantoryItem(cMsg, sMsg, getGlobalCompanyCode())) {
        // Mod End 2017/03/03 QC#15878
        // 2017/06/23 QC#16677 Mod E N D
            cMsg.setMessageInfo(NMAL2610Constant.ZZM9037E);
            return;
        }
        // 2017/11/21 QC#21350 add start
        // ### Copy Header End Date ###
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1) && !cMsg.effThruDt_H1.getValue().equals(cMsg.effThruDt_HB.getValue())) {
            for (int j = 0; j < cMsg.A.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).effThruDt_A1, NMAL2610CommonLogic.copyHdrEndDt(cMsg.effThruDt_H1.getValue(), cMsg.A.no(j).effFromDt_A1.getValue(), cMsg.A.no(j).effThruDt_A1.getValue()));
            }

            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).effThruDt_B1, NMAL2610CommonLogic.copyHdrEndDt(cMsg.effThruDt_H1.getValue(), cMsg.B.no(j).effFromDt_B1.getValue(), cMsg.B.no(j).effThruDt_B1.getValue()));
            }

            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).effThruDt_C1, NMAL2610CommonLogic.copyHdrEndDt(cMsg.effThruDt_H1.getValue(), sMsg.C.no(j).effFromDt_C1.getValue(), sMsg.C.no(j).effThruDt_C1.getValue()));
            }

            for (int j = 0; j < cMsg.D.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(cMsg.D.no(j).effThruDt_D1, NMAL2610CommonLogic.copyHdrEndDt(cMsg.effThruDt_H1.getValue(), cMsg.D.no(j).effFromDt_D1.getValue(), cMsg.D.no(j).effThruDt_D1.getValue()));
            }
        }
        // 2017/11/21 QC#21350 add end

        // Master Check : Header
        if (!NMAL2610CommonLogic.checkInputHeaderForSubmit(cMsg, sMsg)) {
            return;
        }

        // Master Check : Territory
        if (!NMAL2610CommonLogic.checkInputTerritoryForSubmit(cMsg)) {
            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY);
            return;
        }

        // Master Check : Territory Rules
        // Del Start 2019/12/06 QC#53019
        // QC#7966
//        if (!NMAL2610CommonLogic.checkInputRulesForSubmit(cMsg, sMsg, glblCompanyCd)) {
//            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
//            // 2017/06/23 QC#16677 ADD START
//            NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
//            // 2017/06/23 QC#16677 ADD END
//            return;
//        }
        // Del End   2019/12/06 QC#53019

        // Add Start 2019/12/06 QC#53019
        if (NMAL2610Constant.ADD_LINE_FLG.compareTo(sMsg.xxRsltFlg.getValue()) == 0) {
            if (!NMAL2610CommonLogic.checkInputNewDispRulesForSubmit(cMsg, sMsg, glblCompanyCd)) {
                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
                NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
                return;
            }
        } else {
            if (!NMAL2610CommonLogic.checkInputRulesForSubmit(cMsg, sMsg, glblCompanyCd)) {
                cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
                NMAL2610CommonLogic.loadOnePageToCMsg(cMsg, cMsg.C, sMsg.C);
                return;
            }
        }
        // Add End   2019/12/06 QC#53019
        if (!NMAL2610CommonLogic.checkResource(cMsg, sMsg, true, true)) {
            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_RESOURCE_ASSIGNE);
            return;
        }

        // Master Check : Resource
        if (!NMAL2610CommonLogic.checkInputResourceForSubmit(cMsg)) {
            cMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_RESOURCE_ASSIGNE);
            return;
        }
        // 2016/02/09 S21-QC#2869 Mod End

        if (!updateDsOrgUnit(cMsg, sMsg, dsOrgUnitTMsg)) {
            return;
        }

        // 2016/02/09 S21-QC#2869 Delete Start
        // if (NMAL2610Constant.TAB_TERRITORY.equals(currentTab)) {
        // 2016/02/09 S21-QC#2869 Delete End

        if (!deleteDsOrgReln(cMsg, sMsg)) {
            return;
        }

        int i = 0;
        for (; i < cMsg.A.getValidCount(); i++) {
            if (!createDsOrgReln(cMsg, sMsg, i)) {
                return;
            }
        }

        for (i = 0; i < cMsg.B.getValidCount(); i++) {
            if (!createDsOrgRelnChild(cMsg, sMsg, i)) {
                return;
            }
        }

        // 2016/02/09 S21-QC#2869 Delete Start
        // cMsg.setMessageInfo(NMAL2610Constant.NMAM8182I, new String[] {NMAL2610Constant.TAB_TERRITORY });
        // } else if (NMAL2610Constant.TAB_TERRITORY_RULES.equals(currentTab)) {
        //
        // String glblCompanyCd = getGlobalCompanyCode();
        //
        // if (!NMAL2610CommonLogic.checkInputRulesForSubmit(cMsg, glblCompanyCd)) {
        // return;
        // }
        // i = 0;
        // 2016/02/09 S21-QC#2869 Delete Start

        for (i = 0; i < sMsg.C.getValidCount(); i++) {

            // Add Start 2017/03/08 QC#15878
            if (!NMAL2610CommonLogic.checkDiffLine(cMsg, sMsg, i)) {
                continue;
            }
            // Add End 2017/03/08 QC#15878

            if (!createTrtyRule(cMsg, sMsg, i)) {
                return;
            }
        }

        if (!deletetrtyRule(cMsg, sMsg)) {
            return;
        }

        // 2016/02/09 S21-QC#2869 Delete Start
        // cMsg.setMessageInfo(NMAL2610Constant.NMAM8182I, new String[] {NMAL2610Constant.TAB_TERRITORY_RULES });
        // } else if (NMAL2610Constant.TAB_RESOURCE_ASSIGNE.equals(currentTab)) {

        // i = 0;

        // if (!NMAL2610CommonLogic.checkInputResourceForSubmit(cMsg)) {
        // return;
        // }
        // 2016/02/09 S21-QC#2869 Delete Start

        for (i = 0; i < cMsg.D.getValidCount(); i++) {
            if (!createdsOrgResrcReln(cMsg, sMsg, i)) {
                return;
            }
        }

        if (!deletedsOrgResrcReln(cMsg, sMsg)) {
            return;
        }

        // 2016/02/09 S21-QC#2869 Mod Start
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxValUpdFlg.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(NMAL2610Constant.NZZM0002I);
            }
        } else {
            cMsg.setMessageInfo(NMAL2610Constant.NMAM8333I);
        }
        // 2016/02/09 S21-QC#2869 Mod End

    }

    private boolean createDsOrgReln(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, int i) {

        DS_ORG_RELNTMsg dsOrgRelnTMsg = null;

        if (ZYPCommonFunc.hasValue(cMsg.A.no(i).ezUpTime_X2)) {
            // Not Created by Add button
            dsOrgRelnTMsg = new DS_ORG_RELNTMsg();

            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
            // 2017/03/07 S21_NA#17760 Mod Start
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.orgCd, cMsg.orgCd_H1);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.prntOrgCd, cMsg.A.no(i).orgCd_AB);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.effFromDt, cMsg.A.no(i).effFromDt_A1);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.dsOrgRelnPk, cMsg.A.no(i).dsOrgRelnPk_A1);
            // 2017/03/07 S21_NA#17760 Mod End

            try {
                dsOrgRelnTMsg = (DS_ORG_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgRelnTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                return false;
            }
        }

        if (dsOrgRelnTMsg != null) {
            if (NMAL2610CommonLogic.isNotEquals(cMsg.A.no(i).effThruDt_A1.getValue(), cMsg.A.no(i).effThruDt_AB.getValue())
                    || NMAL2610CommonLogic.isNotEquals(cMsg.A.no(i).orgCd_A1.getValue(), cMsg.A.no(i).orgCd_AB.getValue()) //
                    || NMAL2610CommonLogic.isNotEquals(cMsg.A.no(i).effFromDt_A1.getValue(), cMsg.A.no(i).effFromDt_AB.getValue())) { // 2017/03/07 S21_NA#17760 Add Start
                String ezUpTimeOfScrnReln = cMsg.A.no(i).ezUpTime_X2.getValue();
                String ezUpTimeZoneOfScrnReln = cMsg.A.no(i).ezUpTimeZone_X2.getValue();
                String ezUpTimeOfCurrentReln = dsOrgRelnTMsg.ezUpTime.getValue();
                String ezUpTimeZoneOfCurrentReln = dsOrgRelnTMsg.ezUpTimeZone.getValue();

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

                if (!ezUpTimeOfScrnReln.equals(ezUpTimeOfCurrentReln) || !ezUpTimeZoneOfScrnReln.equals(ezUpTimeZoneOfCurrentReln)) {
                    cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                    return false;
                }

                // 2016/03/14 CSA-QC#5414 Add Start
                if (NMAL2610CommonLogic.isNotEquals(cMsg.A.no(i).orgCd_A1.getValue(), cMsg.A.no(i).orgCd_AB.getValue())) {
                    S21FastTBLAccessor.removePhysical(new DS_ORG_RELNTMsg[] {dsOrgRelnTMsg });
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                        return false;
                    }

                    dsOrgRelnTMsg = new DS_ORG_RELNTMsg();
                    if (!createDsOrgReln(cMsg, i, dsOrgRelnTMsg)) {
                        cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                        return false;
                    }
                // 2016/03/14 CSA-QC#5414 Add End

                } else {
                    ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.effThruDt, cMsg.A.no(i).effThruDt_A1);
                    ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.effFromDt, cMsg.A.no(i).effFromDt_A1); // 2017/03/07 S21_NA#17760 Add

                    // Add Start 2017/10/17 QC#21753
                    ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                    // Add End 2017/10/17 QC#21753

                    EZDTBLAccessor.update(dsOrgRelnTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                        return false;
                    }
                }
                cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else {
            // from date should be bigger than sales date when
            // it's registered.
            if (!NMAL2610CommonLogic.checkFromDate(cMsg.A.no(i).effFromDt_A1.getValue())) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                return false;
            }

            // start date has been changed, old data should be deleted.
            if (ZYPCommonFunc.hasValue(sMsg.orgCd_H1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A1)) {
                if (!cMsg.A.no(i).effFromDt_A1.getValue().equals(sMsg.A.no(i).effFromDt_A1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgCd_R1, sMsg.orgCd_H1.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).prntOrgCd_R1, sMsg.A.no(i).orgCd_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgStruTpCd_R1, ORG_STRU_TP.TERRITORY_STRUCTURE);
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).effFromDt_R1, sMsg.A.no(i).effFromDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).dsOrgRelnPk_R1, sMsg.A.no(i).dsOrgRelnPk_A1.getValue()); // 2017/03/07 S21_NA#17760 Add

                    sMsg.X.setValidCount(sMsg.X.getValidCount() + 1);
                }
            }

            dsOrgRelnTMsg = new DS_ORG_RELNTMsg();
            // 2016/03/14 CSA-QC#5414 Mod Start
            if (!createDsOrgReln(cMsg, i, dsOrgRelnTMsg)) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                return false;
            }
            // 2016/03/14 CSA-QC#5414 Mod End
            cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        return true;
    }

    private boolean createDsOrgRelnChild(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, int i) {
        // for (i = 0; i < cMsg.B.getValidCount(); i++) {
        // UPDATE DS_ORG_RELN
        DS_ORG_RELNTMsg dsOrgRelnRelnTMsg = new DS_ORG_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
        // 2017/03/07 S21_NA#17760 Mod Start
//        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgCd, cMsg.B.no(i).orgCd_B1);
//        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.prntOrgCd, cMsg.orgCd_H1);
//        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
//        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, cMsg.B.no(i).effFromDt_B1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.dsOrgRelnPk, cMsg.B.no(i).dsOrgRelnPk_B1);
        // 2017/03/07 S21_NA#17760 Mod End

        try {
            dsOrgRelnRelnTMsg = (DS_ORG_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgRelnRelnTMsg);
        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
            return false;
        }
        if (dsOrgRelnRelnTMsg != null) {
            // Mod Start 2019/09/05 QC#51704
            //if (NMAL2610CommonLogic.isNotEquals(cMsg.B.no(i).effThruDt_B1.getValue(), cMsg.B.no(i).effThruDt_BB.getValue())) {
            if (NMAL2610CommonLogic.isNotEquals(cMsg.B.no(i).effThruDt_B1.getValue(), cMsg.B.no(i).effThruDt_BB.getValue()) || //
                    NMAL2610CommonLogic.isNotEquals(cMsg.B.no(i).effFromDt_B1.getValue(), cMsg.B.no(i).effFromDt_BB.getValue())) {
                // Mod End 2019/09/05 QC#51704
                String ezUpTimeOfScrnReln = cMsg.B.no(i).ezUpTime_B1.getValue();
                String ezUpTimeZoneOfScrnReln = cMsg.B.no(i).ezUpTimeZone_B1.getValue();
                String ezUpTimeOfCurrentReln = dsOrgRelnRelnTMsg.ezUpTime.getValue();
                String ezUpTimeZoneOfCurrentReln = dsOrgRelnRelnTMsg.ezUpTimeZone.getValue();

                if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                    cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effThruDt, cMsg.B.no(i).effThruDt_B1);
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, cMsg.B.no(i).effFromDt_B1); // 2017/03/07 S21_NA#17760 Add

                // Add Start 2017/10/17 QC#21753
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                // Add End 2017/10/17 QC#21753

                EZDTBLAccessor.update(dsOrgRelnRelnTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnRelnTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                    return false;
                }

                cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
            }

        } else {
            // start date has been changed, old data should be
            // deleted.
            if (ZYPCommonFunc.hasValue(sMsg.B.no(i).effFromDt_B1)) {
                if (!cMsg.B.no(i).effFromDt_B1.getValue().equals(sMsg.B.no(i).effFromDt_B1.getValue())) {

                    // DS_ORG_RELN
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgCd_R1, sMsg.B.no(i).orgCd_B1);
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).prntOrgCd_R1, sMsg.orgCd_H1);
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgStruTpCd_R1, ORG_STRU_TP.TERRITORY_STRUCTURE);
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).effFromDt_R1, sMsg.B.no(i).effFromDt_B1);
                    ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).dsOrgRelnPk_R1, sMsg.B.no(i).dsOrgRelnPk_B1);

                    sMsg.X.setValidCount(sMsg.X.getValidCount() + 1);
                }
            }

            dsOrgRelnRelnTMsg = new DS_ORG_RELNTMsg();

            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgCd, cMsg.B.no(i).orgCd_B1);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.prntOrgCd, cMsg.orgCd_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, cMsg.B.no(i).effFromDt_B1);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effThruDt, cMsg.B.no(i).effThruDt_B1);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.dsOrgRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ORG_RELN_SQ)); // 2017/03/07 S21_NA#17760 Add Start

            EZDTBLAccessor.create(dsOrgRelnRelnTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnRelnTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM0176E, new String[] {"DS_ORG_RELN"});
                return false;
            }

            cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
        }
        // }

        return true;
    }

    private boolean createTrtyRule(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, int i) {

        TRTY_RULETMsg trtyRuleTMsg = null;

        if (ZYPCommonFunc.hasValue(sMsg.C.no(i).trtyRulePk_X3)) {
            // Not Created by Add button
            trtyRuleTMsg = new TRTY_RULETMsg();

            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRulePk, sMsg.C.no(i).trtyRulePk_X3);

            try {
                trtyRuleTMsg = (TRTY_RULETMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(trtyRuleTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                return false;
            }
        }

        if (trtyRuleTMsg != null) {
            if (NMAL2610CommonLogic.checkChangedFieldsForTrtyRule(cMsg, sMsg, i)) {
                String ezUpTimeOfScrnReln = sMsg.C.no(i).ezUpTime_X3.getValue();
                String ezUpTimeZoneOfScrnReln = sMsg.C.no(i).ezUpTimeZone_X3.getValue();
                String ezUpTimeOfCurrentReln = trtyRuleTMsg.ezUpTime.getValue();
                String ezUpTimeZoneOfCurrentReln = trtyRuleTMsg.ezUpTimeZone.getValue();

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

                if (!ezUpTimeOfScrnReln.equals(ezUpTimeOfCurrentReln) || !ezUpTimeZoneOfScrnReln.equals(ezUpTimeZoneOfCurrentReln)) {
                    cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgCd, cMsg.orgCd_H1);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleTpCd, sMsg.C.no(i).trtyRuleTpCd_P1);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleFromValTxt, sMsg.C.no(i).trtyRuleFromValTxt_C1);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleThruValTxt, sMsg.C.no(i).trtyRuleThruValTxt_C1);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effFromDt, sMsg.C.no(i).effFromDt_C1);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effThruDt, sMsg.C.no(i).effThruDt_C1);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgTierCd, cMsg.orgTierCd_P1);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleOprdTpCd, sMsg.C.no(i).trtyRuleOprdTpCd_P1);
                ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleLogicTpCd, sMsg.C.no(i).trtyRuleLogicTpCd_P1);

                EZDTBLAccessor.update(trtyRuleTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(trtyRuleTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"TRTY_RULE"});
                    return false;
                }

                cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else {

            // from date should be bigger than sales date when
            // it's registered.
            if (!NMAL2610CommonLogic.checkFromDate(sMsg.C.no(i).effFromDt_C1.getValue())) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                return false;
            }

            trtyRuleTMsg = new TRTY_RULETMsg();

            BigDecimal trtyRulePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.TRTY_RULE_SQ);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRulePk, trtyRulePk);

            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgCd, cMsg.orgCd_H1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleTpCd, sMsg.C.no(i).trtyRuleTpCd_P1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleFromValTxt, sMsg.C.no(i).trtyRuleFromValTxt_C1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleThruValTxt, sMsg.C.no(i).trtyRuleThruValTxt_C1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effFromDt, sMsg.C.no(i).effFromDt_C1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.effThruDt, sMsg.C.no(i).effThruDt_C1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.orgTierCd, cMsg.orgTierCd_P1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleOprdTpCd, sMsg.C.no(i).trtyRuleOprdTpCd_P1);
            ZYPEZDItemValueSetter.setValue(trtyRuleTMsg.trtyRuleLogicTpCd, sMsg.C.no(i).trtyRuleLogicTpCd_P1);

            EZDTBLAccessor.create(trtyRuleTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(trtyRuleTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM0176E, new String[] {"TRTY_RULE"});
                return false;
            }

            cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        return true;

    }

    private boolean createdsOrgResrcReln(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, int i) {

        DS_ORG_RESRC_RELNTMsg dsOrgResrcRelnTMsg = null;

        if (ZYPCommonFunc.hasValue(cMsg.D.no(i).orgFuncRoleTpCd_X4)) {
            // Not Created by Add button
            dsOrgResrcRelnTMsg = new DS_ORG_RESRC_RELNTMsg();

            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgCd, cMsg.orgCd_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.psnCd, cMsg.D.no(i).psnCd_DB);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgFuncRoleTpCd, cMsg.D.no(i).orgFuncRoleTpCd_DB);
            // 2017/09/19 QC#20650 Mod Start
            //ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effFromDt, cMsg.D.no(i).effFromDt_D1);
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effFromDt, cMsg.D.no(i).effFromDt_DB);
            // 2017/09/19 QC#20650 Mod End

            try {
                dsOrgResrcRelnTMsg = (DS_ORG_RESRC_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgResrcRelnTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                return false;
            }
        }

        if (dsOrgResrcRelnTMsg != null) {
            if (NMAL2610CommonLogic.checkChangedFieldsForRsrcAsgn(cMsg, i)) {

                String ezUpTimeOfScrnReln = cMsg.D.no(i).ezUpTime_X4.getValue();
                String ezUpTimeZoneOfScrnReln = cMsg.D.no(i).ezUpTimeZone_X4.getValue();
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

                if (!ezUpTimeOfScrnReln.equals(ezUpTimeOfCurrentReln) || !ezUpTimeZoneOfScrnReln.equals(ezUpTimeZoneOfCurrentReln)) {
                    cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                    return false;
                }

                // 2016/03/14 CSA-QC#5416 Add Start
                if (NMAL2610CommonLogic.isNotEquals(cMsg.D.no(i).psnCd_D1.getValue(), cMsg.D.no(i).psnCd_DB.getValue())
                        || NMAL2610CommonLogic.isNotEquals(cMsg.D.no(i).orgFuncRoleTpCd_DB.getValue(), cMsg.D.no(i).orgFuncRoleTpCd_X4.getValue())
                        || NMAL2610CommonLogic.isNotEquals(cMsg.D.no(i).effFromDt_DB.getValue(), cMsg.D.no(i).effFromDt_D1.getValue())) {
                    S21FastTBLAccessor.removePhysical(new DS_ORG_RESRC_RELNTMsg[] {dsOrgResrcRelnTMsg });
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgResrcRelnTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RESRC_RELN"});
                        return false;
                    }

                    dsOrgResrcRelnTMsg = new DS_ORG_RESRC_RELNTMsg();
                    if (!createDsOrgResrcReln(cMsg, i, dsOrgResrcRelnTMsg)) {
                        cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RESRC_RELN"});
                        return false;
                    }
                // 2016/03/14 CSA-QC#5416 Add End
                } else {
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effThruDt, cMsg.D.no(i).effThruDt_D1);
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.acctTeamRoleTpCd, cMsg.D.no(i).acctTeamRoleTpCd_P1);

                    if (ZYPCommonFunc.hasValue(cMsg.D.no(i).acctTeamRoleTpCd_P1)) {
                        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.nonSlsRepFlg, ZYPConstant.FLG_ON_Y);
                    } else {
                        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.nonSlsRepFlg, ZYPConstant.FLG_OFF_N);
                    }

                    // Add Start 2017/10/17 QC#21753
                    if (NMAL2610CommonLogic.isNotEquals(cMsg.D.no(i).effThruDt_D1.getValue(), cMsg.D.no(i).effThruDt_DB.getValue())) {
                        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                    }
                    // Add End 2017/10/17 QC#21753

                    EZDTBLAccessor.update(dsOrgResrcRelnTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgResrcRelnTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RESRC_RELN"});
                        return false;
                    }
                }

                cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        } else {

            // from date should be bigger than sales date when
            // it's registered.
            if (!NMAL2610CommonLogic.checkFromDate(cMsg.D.no(i).effFromDt_D1.getValue())) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM0044E, new String[] {NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2610Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                return false;
            }

            // start date has been changed, old data should be
            // deleted.
            if (ZYPCommonFunc.hasValue(sMsg.D.no(i).effFromDt_D1)) {
                if (!cMsg.D.no(i).effFromDt_D1.getValue().equals(sMsg.D.no(i).effFromDt_D1.getValue())) {

                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).orgCd_R3, sMsg.orgCd_H1.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).orgStruTpCd_R3, ORG_STRU_TP.TERRITORY_STRUCTURE);
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).psnCd_R3, sMsg.D.no(i).psnCd_D1.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).orgFuncRoleTpCd_R3, sMsg.D.no(i).orgFuncRoleTpCd_X4.getValue());
                    ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).effFromDt_R3, sMsg.D.no(i).effFromDt_D1.getValue());

                    sMsg.Z.setValidCount(sMsg.Z.getValidCount() + 1);
                }
            }

            dsOrgResrcRelnTMsg = new DS_ORG_RESRC_RELNTMsg();
            // 2016/03/14 CSA-QC#5416 Mod Start
            if (!createDsOrgResrcReln(cMsg, i, dsOrgResrcRelnTMsg)) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RESRC_RELN"});
                return false;
            }
            // 2016/03/14 CSA-QC#5416 Mod Start

            cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
        }

        return true;
    }


    private boolean createDsOrgResrcReln(NMAL2610CMsg cMsg, int i, DS_ORG_RESRC_RELNTMsg dsOrgResrcRelnTMsg) {
        // 2016/03/14 CSA-QC#5416 Add Start
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgCd, cMsg.orgCd_H1);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.psnCd, cMsg.D.no(i).psnCd_D1);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.orgFuncRoleTpCd, cMsg.D.no(i).orgFuncRoleTpCd_X4);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effFromDt, cMsg.D.no(i).effFromDt_D1);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.effThruDt, cMsg.D.no(i).effThruDt_D1);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
        ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.acctTeamRoleTpCd, cMsg.D.no(i).acctTeamRoleTpCd_P1);

        if (ZYPCommonFunc.hasValue(cMsg.D.no(i).acctTeamRoleTpCd_P1)) {
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.nonSlsRepFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnTMsg.nonSlsRepFlg, ZYPConstant.FLG_OFF_N);
        }

        EZDTBLAccessor.create(dsOrgResrcRelnTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgResrcRelnTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAL2610Constant.NMAM0176E, new String[] {"DS_ORG_RESRC_RELN"});
            return false;
        }
        return true;
        // 2016/03/14 CSA-QC#5416 Add End
    }

    private boolean createDsOrgReln(NMAL2610CMsg cMsg, int i, DS_ORG_RELNTMsg dsOrgRelnTMsg) {
        // 2016/03/14 CSA-QC#5414 Add End
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.orgCd, cMsg.orgCd_H1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.prntOrgCd, cMsg.A.no(i).orgCd_A1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.effFromDt, cMsg.A.no(i).effFromDt_A1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.effThruDt, cMsg.A.no(i).effThruDt_A1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.dsOrgRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ORG_RELN_SQ)); // 2017/03/07 S21_NA#17760 Add Start

        EZDTBLAccessor.create(dsOrgRelnTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnTMsg.getReturnCode())) {
            cMsg.setMessageInfo(NMAL2610Constant.NMAM0176E, new String[] {"DS_ORG_RELN"});
            return false;
        }
        return true;
        // 2016/03/14 CSA-QC#5414 Add End
    }

    private boolean deleteDsOrgReln(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
    // 2016/08/05 CSA-QC#12834 Delete Start
    // int len = sMsg.X.getValidCount();
    // int cnt = 0;

    // if (len > 0) {
    // DS_ORG_RELNTMsg[] dsOrgRelnArr = new DS_ORG_RELNTMsg[len];
    // 2016/08/05 CSA-QC#12834 Delete End
         if (sMsg.X.getValidCount() > 0) {

            String ezUpTimeOfScrnReln = null;
            String ezUpTimeZoneOfScrnReln = null;
            String ezUpTimeOfCurrentReln = null;
            String ezUpTimeZoneOfCurrentReln = null;

            for (int j = 0; j < sMsg.X.getValidCount(); j++) {
                DS_ORG_RELNTMsg dsOrgRelnDel = new DS_ORG_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.glblCmpyCd, getGlobalCompanyCode());
                // 2017/03/07 S21_NA#17760 Mod Start
//                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.orgCd, sMsg.X.no(j).orgCd_R1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.prntOrgCd, sMsg.X.no(j).prntOrgCd_R1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.orgStruTpCd, sMsg.X.no(j).orgStruTpCd_R1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.effFromDt, sMsg.X.no(j).effFromDt_R1.getValue());
                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.dsOrgRelnPk, sMsg.X.no(j).dsOrgRelnPk_R1.getValue());
                // 2017/03/07 S21_NA#17760 Mod End

                dsOrgRelnDel = (DS_ORG_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsOrgRelnDel);

                if (dsOrgRelnDel == null || !RTNCD_NORMAL.equals(dsOrgRelnDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM8111E, new String[] {NMAL2610Constant.MSG_DS_ORG_RELN });
                    return false;
                }

                // 2016/03/30 CSA-QC#5945 Add Start
                if (GNRN_TP.DELETE.equals(sMsg.X.no(j).gnrnTpCd_R1.getValue())) {
                    ezUpTimeOfScrnReln = dsOrgRelnDel.ezUpTime.getValue();
                    ezUpTimeZoneOfScrnReln = dsOrgRelnDel.ezUpTimeZone.getValue();
                    ezUpTimeOfCurrentReln = sMsg.X.no(j).ezUpTime_R1.getValue();
                    ezUpTimeZoneOfCurrentReln = sMsg.X.no(j).ezUpTimeZone_R1.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.effThruDt, ZYPDateUtil.addDays(dsOrgRelnDel.effFromDt.getValue(), -1));
                    ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.gnrnTpCd, GNRN_TP.DELETE);

                    EZDTBLAccessor.update(dsOrgRelnDel);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnDel.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                        return false;
                    }
                    cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
                 // 2016/08/05 CSA-QC#12834 Delete Start
                 // len--;
                 // continue;
                 // 2016/08/05 CSA-QC#12834 Delete End
                }
                // 2016/03/30 CSA-QC#5945 Add End

             // 2016/08/05 CSA-QC#12834 Delete Start
             // dsOrgRelnArr[cnt] = dsOrgRelnDel;
             // cnt++;
             // 2016/08/05 CSA-QC#12834 Delete End
            }

         // 2016/08/05 CSA-QC#12834 Delete Start
         // if (cnt > 0) {
         // int result = S21FastTBLAccessor.removeLogical(dsOrgRelnArr);
         // if (result != len) {
         //     cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {NMAL2610Constant.MSG_DS_ORG_RELN });
         //    return false;
         // }

         // if (result > 0) {
         //     cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
         // }
         // }
         // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    private DS_ORG_UNITTMsg setTerritoryId(NMAL2610CMsg cMsg, DS_ORG_UNITTMsg dsOrgUnitTMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
            dsOrgUnitTMsg = new DS_ORG_UNITTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgCd, cMsg.orgCd_H1);

            try {
                dsOrgUnitTMsg = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgUnitTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                return null;
            }
        }

        // Mod Start 2018/06/01 QC#24293
        //if (dsOrgUnitTMsg == null) {
        if (dsOrgUnitTMsg == null && !ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
            // Mod End 2018/06/01 QC#24293
            cMsg.orgCd_H1.setValue(ZYPExtnNumbering.getUniqueID(NMAL2610Constant.BIZAPL_ORGCDKEY));
        }
        return dsOrgUnitTMsg;
    }

    private boolean deletetrtyRule(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {
        int len = sMsg.Y.getValidCount();
        if (len > 0) {
            TRTY_RULETMsg[] trtyRuleArr = new TRTY_RULETMsg[len];
            int cnt = 0;

            // Mod Start 2019/12/27 QC#54222-1
//            for (int j = 0; j < sMsg.Y.getValidCount(); j++) {
            for (int j = 0; j < len; j++) {
            // Mod End   2019/12/27 QC#54222-1
                TRTY_RULETMsg trtyRuleDel = new TRTY_RULETMsg();
                ZYPEZDItemValueSetter.setValue(trtyRuleDel.glblCmpyCd, getGlobalCompanyCode());
                // Mod Start 2019/12/27 QC#54222-1
//                ZYPEZDItemValueSetter.setValue(trtyRuleDel.trtyRulePk, sMsg.Y.no(j).trtyRulePk_R2.getValue());
                ZYPEZDItemValueSetter.setValue(trtyRuleDel.trtyRulePk, cMsg.Y.no(j).trtyRulePk_R2.getValue());
                // Mod End   2019/12/27 QC#54222-1

                trtyRuleDel = (TRTY_RULETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(trtyRuleDel);

                if (trtyRuleDel == null || !RTNCD_NORMAL.equals(trtyRuleDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM8111E, new String[] {NMAL2610Constant.MSG_TRTY_RULE });
                    return false;
                }
                trtyRuleArr[cnt] = trtyRuleDel;
                cnt++;
            }

            int result = S21FastTBLAccessor.removeLogical(trtyRuleArr);
            if (result != len) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {NMAL2610Constant.MSG_TRTY_RULE });
                return false;
            }

            if (result > 0) {
                cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
            }
        }

        return true;
    }

    private boolean deletedsOrgResrcReln(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

     // 2016/08/05 CSA-QC#12834 Delete Start
     // int len = sMsg.Z.getValidCount();
     // int cnt = 0;
     // if (len > 0) {
     //  DS_ORG_RESRC_RELNTMsg[] dsOrgResrcRelnArr = new DS_ORG_RESRC_RELNTMsg[len];
     // 2016/08/05 CSA-QC#12834 Delete End
         if (sMsg.Z.getValidCount() > 0) {

            String ezUpTimeOfScrnReln = null;
            String ezUpTimeZoneOfScrnReln = null;
            String ezUpTimeOfCurrentReln = null;
            String ezUpTimeZoneOfCurrentReln = null;

            for (int j = 0; j < sMsg.Z.getValidCount(); j++) {
                DS_ORG_RESRC_RELNTMsg dsOrgResrcRelnDel = new DS_ORG_RESRC_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnDel.orgCd, sMsg.Z.no(j).orgCd_R3.getValue());
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnDel.orgStruTpCd, sMsg.Z.no(j).orgStruTpCd_R3.getValue());
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnDel.psnCd, sMsg.Z.no(j).psnCd_R3.getValue());
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnDel.orgFuncRoleTpCd, sMsg.Z.no(j).orgFuncRoleTpCd_R3.getValue());
                ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnDel.effFromDt, sMsg.Z.no(j).effFromDt_R3.getValue());

                dsOrgResrcRelnDel = (DS_ORG_RESRC_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsOrgResrcRelnDel);

                if (dsOrgResrcRelnDel == null || !RTNCD_NORMAL.equals(dsOrgResrcRelnDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM8111E, new String[] {NMAL2610Constant.MSG_DS_ORG_RESRC_RELN });
                    return false;
                }

                // 2016/03/30 CSA-QC#5945 Add Start
                if (GNRN_TP.DELETE.equals(sMsg.Z.no(j).gnrnTpCd_R3.getValue())) {
                    ezUpTimeOfScrnReln = dsOrgResrcRelnDel.ezUpTime.getValue();
                    ezUpTimeZoneOfScrnReln = dsOrgResrcRelnDel.ezUpTimeZone.getValue();
                    ezUpTimeOfCurrentReln = sMsg.Z.no(j).ezUpTime_R3.getValue();
                    ezUpTimeZoneOfCurrentReln = sMsg.Z.no(j).ezUpTimeZone_R3.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnDel.effThruDt, ZYPDateUtil.addDays(dsOrgResrcRelnDel.effFromDt.getValue(), -1));
                    ZYPEZDItemValueSetter.setValue(dsOrgResrcRelnDel.gnrnTpCd, GNRN_TP.DELETE);

                    EZDTBLAccessor.update(dsOrgResrcRelnDel);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgResrcRelnDel.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                        return false;
                    }
                    cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
                 // 2016/08/05 CSA-QC#12834 Delete Start
                 //  len--;
                 // continue;
                 // 2016/08/05 CSA-QC#12834 Delete End
                }
                // 2016/03/30 CSA-QC#5945 Add End

             // 2016/08/05 CSA-QC#12834 Delete Start
             //  dsOrgResrcRelnArr[cnt] = dsOrgResrcRelnDel;
             // cnt++;
             // 2016/08/05 CSA-QC#12834 Delete End
            }

         // 2016/08/05 CSA-QC#12834 Delete Start
         // if (cnt > 0) {
         // int result = S21FastTBLAccessor.removeLogical(dsOrgResrcRelnArr);
         //  if (result != len) {
         //  cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {NMAL2610Constant.MSG_DS_ORG_RESRC_RELN });
         // return false;
         // }

         //  if (result > 0) {
         //     cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
         // }
         //  }
         // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    private boolean updateDsOrgUnit(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg, DS_ORG_UNITTMsg dsOrgUnitTMsg) {
        // DS_ORG_UNITTMsg dsOrgUnitTMsg = null;
        //
        // if (ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
        //
        // dsOrgUnitTMsg = new DS_ORG_UNITTMsg();
        // ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.glblCmpyCd,
        // getGlobalCompanyCode());
        // ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgCd,
        // cMsg.orgCd_H1);
        //
        // try {
        // dsOrgUnitTMsg = (DS_ORG_UNITTMsg)
        // EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgUnitTMsg);
        // } catch (EZDDBRecordLockedException e) {
        // cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
        // return false;
        // }
        // }

        if (dsOrgUnitTMsg != null) {
            if (NMAL2610CommonLogic.checkChangedFieldsForDsOrgUnit(cMsg)) {

                String ezUpTimeOfScrnOrg = cMsg.ezUpTime_X1.getValue();
                String ezUpTimeZoneOfScrnOrg = cMsg.ezUpTimeZone_X1.getValue();
                String ezUpTimeOfCurrentOrg = dsOrgUnitTMsg.ezUpTime.getValue();
                String ezUpTimeZoneOfCurrentOrg = dsOrgUnitTMsg.ezUpTimeZone.getValue();

                if (!ZYPCommonFunc.hasValue(ezUpTimeOfScrnOrg)) {
                    ezUpTimeOfScrnOrg = "";
                }
                if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfScrnOrg)) {
                    ezUpTimeZoneOfScrnOrg = "";
                }
                if (!ZYPCommonFunc.hasValue(ezUpTimeOfCurrentOrg)) {
                    ezUpTimeOfCurrentOrg = "";
                }
                if (!ZYPCommonFunc.hasValue(ezUpTimeZoneOfCurrentOrg)) {
                    ezUpTimeZoneOfCurrentOrg = "";
                }

                if (!ezUpTimeOfScrnOrg.equals(ezUpTimeOfCurrentOrg) || !ezUpTimeZoneOfScrnOrg.equals(ezUpTimeZoneOfCurrentOrg)) {
                    cMsg.setMessageInfo(NMAL2610Constant.ZZZM9004E);
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.firstOrgCd, cMsg.bizAreaOrgCd_P1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.trtyTpCd, cMsg.trtyTpCd_P1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgNm, cMsg.orgNm_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgTierCd, cMsg.orgTierCd_P1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgDescTxt, cMsg.orgDescTxt_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.trtyGrpTpCd, cMsg.trtyGrpTpCd_P1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.effFromDt, cMsg.effFromDt_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.effThruDt, cMsg.effThruDt_H1);

                // Add Start 2017/10/17 QC#21753
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                // Add End 2017/10/17 QC#21753

                EZDTBLAccessor.update(dsOrgUnitTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgUnitTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM0177E, new String[] {"DS_ORG_UNIT"});
                    return false;
                }
                cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);
            }

        } else {
            // For Manual Input create new
            // cMsg.orgCd_H1.setValue(ZYPExtnNumbering.getUniqueID(NMAL2610Constant.BIZAPL_ORGCDKEY));

            dsOrgUnitTMsg = new DS_ORG_UNITTMsg();

            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgCd, cMsg.orgCd_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.effFromDt, cMsg.effFromDt_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.effThruDt, cMsg.effThruDt_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgNm, cMsg.orgNm_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgDescTxt, cMsg.orgDescTxt_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgTierCd, cMsg.orgTierCd_P1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.gnrnTpCd, GNRN_TP.FUTURE);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.trtyGrpTpCd, cMsg.trtyGrpTpCd_P1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.trtyTpCd, cMsg.trtyTpCd_P1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.firstOrgCd, cMsg.bizAreaOrgCd_P1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.autoEstFlg, ZYPConstant.FLG_OFF_N);

            EZDTBLAccessor.create(dsOrgUnitTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgUnitTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAL2610Constant.NMAM0176E, new String[] {"DS_ORG_UNIT"});
                return false;
            }

            cMsg.xxValUpdFlg.setValue(ZYPConstant.FLG_ON_Y);

            // relation to zeroth will create automatically.
            if (NMAL2610Constant.FIRST_TIER_CD.equals(cMsg.orgTierCd_P1.getValue())) {
                if (!createRelationforFirstOrg(cMsg, sMsg)) {
                    cMsg.setMessageInfo(NMAL2610Constant.NMAM0176E, new String[] {"DS_ORG_RELN"});
                    return false;
                }
            }
        }

        return true;
    }

    private boolean createRelationforFirstOrg(NMAL2610CMsg cMsg, NMAL2610SMsg sMsg) {

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2610Query.getInstance().getZeroTierOrg(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {

            DS_ORG_RELNTMsg dsOrgRelnRelnTMsg = new DS_ORG_RELNTMsg();

            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgCd, cMsg.orgCd_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgStruTpCd, ORG_STRU_TP.TERRITORY_STRUCTURE);

            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();

            Map<String, Object> map = (Map<String, Object>) resultList.get(0);

            String prntOrgCd = (String) map.get(NMAL2610Constant.ORG_CD_DBCOLUMN);
            if (prntOrgCd != null) {
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.prntOrgCd, prntOrgCd);

                String effFromDt = (String) map.get(NMAL2610Constant.EFF_FROM_DT_DBCOLUMN);
                if (effFromDt != null) {
                    ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, effFromDt);

                    String effThruDt = (String) map.get(NMAL2610Constant.EFF_THRU_DT_DBCOLUMN);
                    if (effThruDt != null) {
                        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effThruDt, effThruDt);
                    }

                    String gnrnTpCd = (String) map.get(NMAL2610Constant.GNRN_TP_CD_DBCOLUMN);
                    if (gnrnTpCd != null) {
                        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.gnrnTpCd, gnrnTpCd);
                    }

                    ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.dsOrgRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ORG_RELN_SQ)); // 2017/03/13 S21_NA#17760-2 Add Start

                    EZDTBLAccessor.create(dsOrgRelnRelnTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnRelnTMsg.getReturnCode())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private void resetTerritoryRuleOperandPulldownList(NMAL2610CMsg cMsg) {
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            resetTerritoryRuleOperandPulldownList(cMsg, i);
        }
    }

    private void resetTerritoryRuleOperandPulldownList(NMAL2610CMsg cMsg, int row) {
        String glblCmpyCd = this.getGlobalCompanyCode();
        String trtyRuleTpCd = cMsg.C.no(row).trtyRuleTpCd_P1.getValue();
        if (!ZYPCommonFunc.hasValue(trtyRuleTpCd)) {
            trtyRuleTpCd = cMsg.trtyRuleTpCd_C1.no(0).getValue();
        }
        EZDCStringItemArray codeItems = cMsg.C.no(row).trtyRuleOprdTpCd_C1;
        EZDCStringItemArray valueItems = cMsg.C.no(row).trtyRuleOprdTpDescTxt_C1;
        NMAL2610CommonLogic.createTerritoryRuleOperandPulldownList(glblCmpyCd, trtyRuleTpCd, codeItems, valueItems);
        ZYPEZDItemValueSetter.setValue(cMsg.C.no(row).trtyRuleTpCd_P1, trtyRuleTpCd);
    }
    
}
