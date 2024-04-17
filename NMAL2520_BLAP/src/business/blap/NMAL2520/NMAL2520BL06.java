/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2520;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2510.constant.NMAL2510Constant;
import business.blap.NMAL2520.common.NMAL2520CommonLogic;
import business.blap.NMAL2520.constant.NMAL2520Constant;
import business.db.DS_ORG_RELNTMsg;
import business.db.DS_ORG_UNITTMsg;
import business.db.ORG_FUNC_ASGTMsg;
import business.db.ORG_TOC_CHNG_RQSTTMsg;
import business.db.TOC_ORG_STRU_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Org Structure Maintenance  NMAL2520BL06
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/01/19   Fujitsu         N.Sugiura       Update          CSA-QC#1930,2807
 * 2016/01/21   Fujitsu         N.Sugiura       Update          CSA-QC#3120
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/02/22   Fujitsu         C.Yokoi         Update          CSA-QC#3280
 * 2016/02/23   Fujitsu         C.Yokoi         Update          CSA-QC#4336
 * 2016/03/30   SRAA            Y.Chen          Update          CSA-QC#4429
 * 2016/03/31   Fujitsu         C.Yokoi         Update          CSA-QC#5945
 * 2016/08/05   Fujitsu         C.Yokoi         Update          CSA-QC#12834
 * 2017/03/07   Fujitsu         M.Ohno          Update          S21_NA#17760
 * 2017/03/13   Fujitsu         M.Ohno          Update          S21_NA#17760-2
 * 2017/06/14   Hitachi         J.Kim           Update          QC#18924
 * 2017/10/13   Fujitsu         H.Sugawara      Update          QC#21753
 * 2017/11/21   Fujitsu         M.Ohno          Update          QC#21350
 * 2018/01/30   Fujitsu         Hd.Sugawara     Update          QC#22699
 * 2018/02/14   Fujitsu         Hd.Sugawara     Update          QC#23905
 * 2018/04/13   Fujitsu         Hd.Sugawara     Update          QC#23867
 * 2018/10/18   Fujitsu         K.Ishizuka      Update          QC#28351
 * 2019/02/27   Fujitsu         Hd.Sugawara     Update          QC#30564
 * </pre>
 */
public class NMAL2520BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (NMAL2520Constant.EVENT_NM_NMAL2520SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NMAL2520Scrn00_Cmn_Submit((NMAL2520CMsg) cMsg, (NMAL2520SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL2520Scrn00_Cmn_Submit(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        int i = 0;
        // Add Start 2019/02/27 QC#30564
        String slsDt = ZYPDateUtil.getSalesDate();
        // Add End 2019/02/27 QC#30564

        // Add Start 2018/02/14 QC#23905
        cMsg.xxHldFlg_H0.clear();
        // Add End 2018/02/14 QC#23905

        // 2016/02/05 CSA-QC#2869 Del Start
        // String currentTab = cMsg.xxDplyTab.getValue();
        // if (NMAL2520Constant.TAB_RESRC_ASIGN.equals(currentTab)) {
        // 2016/02/05 CSA-QC#2869 Del End

        // START 2017/06/14 J.Kim [QC#18924,ADD]
        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NMAL2520CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
        cMsg.setCommitSMsg(true);
        // END 2017/06/14 J.Kim [QC#18924,ADD]

        // check Mandantory Field
        if (!NMAL2520CommonLogic.checkInputMandantoryItem(cMsg, sMsg)) {
            cMsg.setMessageInfo(NMAL2520Constant.ZZM9037E);
            NMAL2520CommonLogic.jumpToErrorPage(cMsg, sMsg);
            return;
        }

        // 2017/11/21 QC#21350 add start
        // ### Copy Header End Date ###
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1) && !cMsg.effThruDt_H1.getValue().equals(cMsg.effThruDt_HB.getValue())) {
            for (int j = 0; j < cMsg.A.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(j).effThruDt_A1, NMAL2520CommonLogic.copyHdrEndDt(cMsg.effThruDt_H1.getValue(), cMsg.A.no(j).effFromDt_A1.getValue(), cMsg.A.no(j).effThruDt_A1.getValue()));
            }

            for (int j = 0; j < cMsg.B.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(cMsg.B.no(j).effThruDt_B1, NMAL2520CommonLogic.copyHdrEndDt(cMsg.effThruDt_H1.getValue(), cMsg.B.no(j).effFromDt_B1.getValue(), cMsg.B.no(j).effThruDt_B1.getValue()));
            }

            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                ZYPEZDItemValueSetter.setValue(sMsg.C.no(j).effThruDt_C1, NMAL2520CommonLogic.copyHdrEndDt(cMsg.effThruDt_H1.getValue(), sMsg.C.no(j).effFromDt_C1.getValue(), sMsg.C.no(j).effThruDt_C1.getValue()));
            }
        }
        // 2017/11/21 QC#21350 add end
        // Check to exists in Master Data
        // ###  Header ###
        if (!NMAL2520CommonLogic.checkInputHeaderForSubmit(cMsg)) {
            return;
        }

        // ### Build Hierarchy ###
        if (!NMAL2520CommonLogic.checkInputBuildForSubmit(cMsg, sMsg)) { // 2016/02/22 CSA-QC#3280 Mod
            cMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_BUILD_HIERARCHY);
            return;
        }

        // ### Resource Assign ###
        // START 2017/06/14 J.Kim [QC#18924,MOD]
        //if (!NMAL2520CommonLogic.checkInputAsignForSubmit(cMsg, getGlobalCompanyCode())) {
        if (!NMAL2520CommonLogic.checkInputAsignForSubmit(cMsg, sMsg, getGlobalCompanyCode())) {
            cMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_RESRC_ASIGN);
            NMAL2520CommonLogic.jumpToErrorPage(cMsg, sMsg);
            return;
        }
        // END 2017/06/14 J.Kim [QC#18924,MOD]

        DS_ORG_UNITTMsg dsOrgUnitTMsg = null;
        if (ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
            // UPDATE DS_ORG_UNIT
            dsOrgUnitTMsg = new DS_ORG_UNITTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgCd, cMsg.orgCd_H1);

            try {
                dsOrgUnitTMsg = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgUnitTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                return;
            }
        }

        if (dsOrgUnitTMsg == null) {
            if (!ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
                cMsg.orgCd_H1.setValue(ZYPExtnNumbering.getUniqueID(NMAL2520Constant.BIZAPL_ORGCDKEY));
            }

            if (!ZYPCommonFunc.hasValue(cMsg.orgStruTpCd_H1)) {
                cMsg.orgStruTpCd_H1.setValue(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
            }
        }


        boolean isDataRegister = false;
        boolean isChangeResource = false;
        boolean isChangeResourceSpec = false;
        boolean isChangePersonCode = false;
        boolean isChangeOrganization = false;
        if (NMAL2520CommonLogic.checkChangeOrganization(cMsg)) {
            isChangeOrganization = true;
        }

        // START 2017/06/14 J.Kim [QC#18924,MOD]
        // ### Resource Assign ###
        for (i = 0; i < sMsg.C.getValidCount(); i++) {
            if (NMAL2520CommonLogic.checkChangeActiveResourceAssign(sMsg, i)) {
                isChangeResource = true;
                isChangeResourceSpec = true;
            }

            if (NMAL2520CommonLogic.checkChangeActivePersonCode(sMsg, i)) {
                isChangeResource = true;
                isChangePersonCode = true;
            }

            TOC_ORG_STRU_RELNTMsg tocOrgStruRelnTMsg = null;
            if (ZYPCommonFunc.hasValue(sMsg.C.no(i).tocCd_U1)) {

                tocOrgStruRelnTMsg = new TOC_ORG_STRU_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgStruTpCd, sMsg.C.no(i).orgStruTpCd_U1);
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.tocCd, sMsg.C.no(i).tocCd_U1);
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgCd, sMsg.C.no(i).orgCd_U1);

                try {
                    tocOrgStruRelnTMsg = (TOC_ORG_STRU_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tocOrgStruRelnTMsg);
                } catch (EZDDBRecordLockedException e) {
                    cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                    return;
                }
            }

            if (tocOrgStruRelnTMsg != null) {
                // 2018/10/18 S21_NA#28351 Add Start
                if (isChangePersonCode) {
                    // Mod Start 2019/02/27 QC#30564
                    //S21FastTBLAccessor.removePhysical(new TOC_ORG_STRU_RELNTMsg[] {tocOrgStruRelnTMsg });
                    if (!changePastTocOrgStruReln(cMsg, sMsg, i, tocOrgStruRelnTMsg, slsDt)) {
                        return;
                    }
                    // Mod End 2019/02/27 QC#30564

                    sMsg.C.no(i).tocCd_U1.setValue(ZYPExtnNumbering.getUniqueID(NMAL2520Constant.BIZAPL_TOCCDKEY));
                    // Mod Start 2019/02/27 QC#30564
                    //insertTocOrgStruReln(cMsg, sMsg, i);
                    insertTocOrgStruReln(cMsg, sMsg, i, slsDt);
                    // Mod End 2019/02/27 QC#30564
                } else if (NMAL2520CommonLogic.checkChangedFieldsForTocOrgStruReln(sMsg, i)) { // 2018/10/18 S21_NA#28351 Add End
                // if (NMAL2520CommonLogic.checkChangedFieldsForTocOrgStruReln(sMsg, i)) { // 2018/10/18 S21_NA#28351 Mod
                    String ezUpTimeOfScrnReln = sMsg.C.no(i).ezUpTime_U1.getValue();
                    String ezUpTimeZoneOfScrnReln = sMsg.C.no(i).ezUpTimeZone_U1.getValue();
                    String ezUpTimeOfCurrentReln = tocOrgStruRelnTMsg.ezUpTime.getValue();
                    String ezUpTimeZoneOfCurrentReln = tocOrgStruRelnTMsg.ezUpTimeZone.getValue();

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
                        cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                        return;
                    }

                    ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effFromDt, sMsg.C.no(i).effFromDt_C1);
                    ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effThruDt, sMsg.C.no(i).effThruDt_C1);

                    EZDTBLAccessor.update(tocOrgStruRelnTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"TOC_ORG_STRU_RELN"});
                        return;
                    }
                    isDataRegister = true;
                }
            } else {
                // 2016/01/19 CSA-QC#1930 Del Start
                // from date should be bigger than sales date when it's registered.
                // if (!NMAL2520CommonLogic.checkFromDate(cMsg.C.no(i).effFromDt_C1.getValue())) {
                // cMsg.setMessageInfo(NMAL2520Constant.NMAM0044E, new String[]
                // {NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2520Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                // return;
                // }
                // 2016/01/19 CSA-QC#1930 Del End

                // For Manual Input pressed Add button
                sMsg.C.no(i).tocCd_U1.setValue(ZYPExtnNumbering.getUniqueID(NMAL2520Constant.BIZAPL_TOCCDKEY));

                tocOrgStruRelnTMsg = new TOC_ORG_STRU_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgStruTpCd, cMsg.orgStruTpCd_H1);
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.tocCd, sMsg.C.no(i).tocCd_U1);

                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgCd, cMsg.orgCd_H1);
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effFromDt, sMsg.C.no(i).effFromDt_C1);
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effThruDt, sMsg.C.no(i).effThruDt_C1);

                EZDTBLAccessor.create(tocOrgStruRelnTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM0176E, new String[] {"TOC_ORG_STRU_RELN"});
                    return;
                }
                isDataRegister = true;
            }

            // UPDATE ORG_TOC_CHNG_RQST
            ORG_TOC_CHNG_RQSTTMsg orgTocChngRqstTMsg = new ORG_TOC_CHNG_RQSTTMsg();
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocCd, sMsg.C.no(i).tocCd_U1);
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgChngRqstPk, sMsg.C.no(i).orgChngRqstPk_U2);

            try {
                orgTocChngRqstTMsg = (ORG_TOC_CHNG_RQSTTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(orgTocChngRqstTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                return;
            }

            if (orgTocChngRqstTMsg != null) {
                // Mod Start 2017/10/13 QC#21753
                //if (isChangeResourceSpec || isChangeOrganization || isChangePersonCode) {
                if (isChangeResourceSpec || isChangeOrganization || isChangePersonCode || NMAL2520CommonLogic.checkChangedFieldsForOrgTocChngRqst(sMsg, i)) {
                    // Mod End 2017/10/13 QC#21753
                    String ezUpTimeOfScrnRqst = sMsg.C.no(i).ezUpTime_U2.getValue();
                    String ezUpTimeZoneOfScrnRqst = sMsg.C.no(i).ezUpTimeZone_U2.getValue();
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
                        cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                        return;
                    }

                    // Mod Start 2019/02/27 QC#30564
                    //if (isChangeResourceSpec) {
                    if (NMAL2520CommonLogic.isChangedResrcAsignRoleAndOther(sMsg, i)) {
                        // Mod End 2019/02/27 QC#30564
                        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.asgCustFromNm, sMsg.C.no(i).asgCustFromNm_C1);
                        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.asgCustToNm, sMsg.C.no(i).asgCustToNm_C1);
                        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgFuncRoleTpCd, sMsg.C.no(i).orgFuncRoleTpCd_P1);
                    }
                    // Mod Start 2019/02/27 QC#30564
                    //if (isChangePersonCode) {
                    if (NMAL2520CommonLogic.isChangedResrcAsignRsrcNum(sMsg, i)) {
                        // Mod End 2019/02/27 QC#30564
                        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocNm, S21StringUtil.subStringByLength(S21StringUtil.concatStrings(sMsg.C.no(i).psnFirstNm_C1.getValue(), " ", sMsg.C.no(i).psnLastNm_C1.getValue()), 0,
                                NMAL2520Constant.COLUMN_LEN_TOC_NM));

                        // Add Start 2019/02/27 QC#30564
                        if (NMAL2520CommonLogic.getDefaultRevenue(sMsg, i)) {
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaBrCd, sMsg.C.no(i).coaBrCd_U2);
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaExtnCd, sMsg.C.no(i).coaExtnCd_U2);
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCcCd, sMsg.C.no(i).coaCcCd_U2);
                            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCmpyCd, sMsg.C.no(i).coaCmpyCd_U2);
                        }
                        // Add End 2019/02/27 QC#30564
                    }

                    // Add Start 2019/02/27 QC#30564
                    if (isChangeResourceSpec || isChangeOrganization || isChangePersonCode || //
                            NMAL2520CommonLogic.isChangedResrcAsignDate(sMsg, i) || //
                            NMAL2520CommonLogic.isChangedResrcAsignRsrcNum(sMsg, i)) {
                    // Add End 2019/02/27 QC#30564

                    ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);

                    // Add Start 2019/02/27 QC#30564
                    }
                    // Add End 2019/02/27 QC#30564

                    EZDTBLAccessor.update(orgTocChngRqstTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"ORG_TOC_CHNG_RQST"});
                        return;
                    }
                    isDataRegister = true;
                }
            } else {
                orgTocChngRqstTMsg = new ORG_TOC_CHNG_RQSTTMsg();
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocCd, sMsg.C.no(i).tocCd_U1);

                BigDecimal orgChngRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.ORG_CHNG_RQST_SQ);
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgChngRqstPk, orgChngRqstPk);

                // 2016/01/19 CSA-QC#2807 Mod Start
                // ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocNm, cMsg.C.no(i).psnNum_C1);
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocNm, S21StringUtil.subStringByLength(S21StringUtil.concatStrings(sMsg.C.no(i).psnFirstNm_C1.getValue(), " ", sMsg.C.no(i).psnLastNm_C1.getValue()), 0,
                        NMAL2520Constant.COLUMN_LEN_TOC_NM));
                // 2016/01/19 CSA-QC#2807 Mod End
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.asgCustFromNm, sMsg.C.no(i).asgCustFromNm_C1);
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.asgCustToNm, sMsg.C.no(i).asgCustToNm_C1);
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgFuncRoleTpCd, sMsg.C.no(i).orgFuncRoleTpCd_P1);
                // Mod Start 2018/02/14 QC#23905
                //ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgCd, sMsg.orgCd_H1);
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgCd, cMsg.orgCd_H1);
                // Mod End 2018/02/14 QC#23905
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);
                if (NMAL2520CommonLogic.getDefaultRevenue(sMsg, i)) {
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaBrCd, sMsg.C.no(i).coaBrCd_U2);
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaExtnCd, sMsg.C.no(i).coaExtnCd_U2);
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCcCd, sMsg.C.no(i).coaCcCd_U2);
                    ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCmpyCd, sMsg.C.no(i).coaCmpyCd_U2);
                }

                EZDTBLAccessor.create(orgTocChngRqstTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM0176E, new String[] {"ORG_TOC_CHNG_RQST"});
                    return;
                }
                isDataRegister = true;
            }

            // UPDATE ORG_FUNC_ASG
            ORG_FUNC_ASGTMsg orgFuncAsgTMsg = new ORG_FUNC_ASGTMsg();
            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.tocCd, sMsg.C.no(i).tocCd_U1);
            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.psnCd, sMsg.C.no(i).psnCd_CB);
            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effFromDt, sMsg.C.no(i).effFromDt_U3);

            try {
                orgFuncAsgTMsg = (ORG_FUNC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(orgFuncAsgTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                return;
            }

            if (orgFuncAsgTMsg != null) {
                // Mod Start 2018/04/13 QC#23867
                //if (isChangeOrganization || isChangeResourceSpec || isChangePersonCode || NMAL2520CommonLogic.isNotEquals(sMsg.C.no(i).effThruDt_CB.getValue(), sMsg.C.no(i).effThruDt_C1.getValue())) {
                if (isChangeOrganization || isChangeResourceSpec || isChangePersonCode || NMAL2520CommonLogic.checkChangedFieldsForOrgFuncAsg(sMsg, i)) {
                    // Mod End 2018/04/13 QC#23867
                    String ezUpTimeOfScrnAsg = sMsg.C.no(i).ezUpTime_U3.getValue();
                    String ezUpTimeZoneOfScrnAsg = sMsg.C.no(i).ezUpTimeZone_U3.getValue();
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
                        cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                        return;
                    }

                    // Mod Start 2018/04/13 QC#23867
                    //if (isChangePersonCode) {
                    // Mod Start 2019/02/27 QC#30564
                    //if (isChangePersonCode || NMAL2520CommonLogic.checkChangedResrcAsignStartDate(sMsg, i)) {
                    if (isChangePersonCode || NMAL2520CommonLogic.checkChangedResrcAsignStartDate(sMsg, i) || //
                            NMAL2520CommonLogic.isChangedResrcAsignRsrcNum(sMsg, i)) {
                        // Mod End 2019/02/27 QC#30564
                        // Mod End 2018/04/13 QC#23867
                        S21FastTBLAccessor.removePhysical(new ORG_FUNC_ASGTMsg[] {orgFuncAsgTMsg });
                        ORG_FUNC_ASGTMsg createOrgFuncAsgTMsg = new ORG_FUNC_ASGTMsg();
                        ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).psnCd_U3, sMsg.C.no(i).psnCd_C1);
                        if (!createOrgFuncAsg(sMsg, i, createOrgFuncAsgTMsg)) {
                            cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"ORG_FUNC_ASG"});
                            return;
                        }
                    } else {
                        // Mod Start 2017/10/13 QC#21753
                        //if (isChangeOrganization || isChangeResource) {
                        if (isChangeOrganization || isChangeResource || NMAL2520CommonLogic.isNotEquals(sMsg.C.no(i).effThruDt_CB.getValue(), sMsg.C.no(i).effThruDt_C1.getValue())) {
                            // Mod End 2017/10/13 QC#21753
                            ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                        }
                        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effThruDt, sMsg.C.no(i).effThruDt_C1);
                        EZDTBLAccessor.update(orgFuncAsgTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnTMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"ORG_FUNC_ASG"});
                            return;
                        }
                    }
                    isDataRegister = true;
                }

            } else {

                // 2016/01/19 CSA-QC#1930 Del Start
                // from date should be bigger than sales date when it's registered.
                // if (!NMAL2520CommonLogic.checkFromDate(cMsg.C.no(i).effFromDt_C1.getValue())) {
                // cMsg.setMessageInfo(NMAL2520Constant.NMAM0044E, new String[]
                // {NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2520Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                // return;
                // }
                // 2016/01/19 CSA-QC#1930 Del End

                // start date has been changed, old data should be deleted.
                if (ZYPCommonFunc.hasValue(sMsg.C.no(i).psnCd_CB) && ZYPCommonFunc.hasValue(sMsg.C.no(i).effFromDt_C1)) {
                    // if (!sMsg.C.no(i).effFromDt_CB.getValue().equals(sMsg.C.no(i).effFromDt_C1.getValue())) { // 2018/10/24 S21_NA#28351 Mod
                    // Mod Start 2019/02/27 QC#30564
                    //if (!sMsg.C.no(i).effFromDt_CB.getValue().equals(sMsg.C.no(i).effFromDt_C1.getValue()) || isChangePersonCode) {
                    if (!sMsg.C.no(i).effFromDt_CB.getValue().equals(sMsg.C.no(i).effFromDt_C1.getValue()) && !isChangePersonCode) {
                        // Mod End 2019/02/27 QC#30564
                        // ORG_FUNC_ASG
                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).tocCd_Z1, sMsg.C.no(i).tocCd_U3.getValue());
                        // ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).psnCd_Z1, sMsg.C.no(i).psnCd_U3.getValue()); // 2018/10/24 S21_NA#28351 Mod
                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).psnCd_Z1, sMsg.C.no(i).psnCd_CB.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).effFromDt_Z1, sMsg.C.no(i).effFromDt_U3.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTime_Z1, sMsg.C.no(i).ezUpTime_U3.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.Z.no(sMsg.Z.getValidCount()).ezUpTimeZone_Z1, sMsg.C.no(i).ezUpTimeZone_U3.getValue());

                        sMsg.Z.setValidCount(sMsg.Z.getValidCount() + 1);
                    }
                }

                // Add Start 2019/02/27 QC#30564
                if (isChangePersonCode) {
                    if(!changePastOrgFuncAsg(cMsg, sMsg, i, slsDt)){
                        return;
                    }
                }
                // Add End 2019/02/27 QC#30564

                // Mod Start 2019/02/27 QC#30564
                //if (!createOrgFuncAsg(sMsg, i, orgFuncAsgTMsg)) {
                if (!createOrgFuncAsgForChangePerson(sMsg, i, orgFuncAsgTMsg, isChangePersonCode, slsDt)) {
                    // Mod End 2019/02/27 QC#30564
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM0176E, new String[] {"ORG_FUNC_ASG"});
                    return;
                }
                isDataRegister = true;
            }
            isChangeResourceSpec = false;
            isChangePersonCode = false;
        }

        // Delete for check box on
        if (!deleteTocOrgStruReln(cMsg, sMsg)) {
            return;
        }

        // Delete for check box on
        if (!deleteOrgTocChngRqst(cMsg, sMsg)) {
            return;
        }

        // Delete for check box on
        if (!deleteOrgFuncAsg(cMsg, sMsg)) {
            return;
        }

        // Delete for change start date.
        if (!deleteOrgFuncAsgForUpdate(cMsg, sMsg)) {
            return;
        }

        if (sMsg.Y.getValidCount() > 0 || sMsg.Z.getValidCount() > 0) {
            isDataRegister = true;
        }
        // 2016/02/05 CSA-QC#2869 Delete Start
        // cMsg.setMessageInfo(NMAL2520Constant.NMAM8182I, new String[] {NMAL2520Constant.TAB_RESRC_ASIGN });

        // } else if
        // (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(currentTab)) {

        // // Check to exists in Master Data
        // if (!NMAL2520CommonLogic.checkInputHeaderForSubmit(cMsg)) {
        // return;
        // }
        //
        // if (!updateDsOrgUnit(cMsg, sMsg)) {
        // return;
        // }
        // // Check to exists in Master Data
        // if (!NMAL2520CommonLogic.checkInputBuildForSubmit(cMsg)) {
        // cMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_BUILD_HIERARCHY);
        // return;
        // }
        // 2016/02/05 CSA-QC#2869 Delete End


        // ### Build Hierarychy ###
        i = 0;
        for (i = 0; i < cMsg.A.getValidCount(); i++) {

            // UPDATE DS_ORG_RELN
            DS_ORG_RELNTMsg dsOrgRelnRelnTMsg = new DS_ORG_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
            // 2017/03/07 S21_NA#17760 Mod Start
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgCd, cMsg.orgCd_H1);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.prntOrgCd, cMsg.A.no(i).orgCd_AB);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgStruTpCd, cMsg.orgStruTpCd_H1);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, cMsg.A.no(i).effFromDt_A1);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.dsOrgRelnPk, cMsg.A.no(i).dsOrgRelnPk_A1);
            // 2017/03/07 S21_NA#17760 Mod End

            try {
                dsOrgRelnRelnTMsg = (DS_ORG_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgRelnRelnTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                return;
            }

            if (dsOrgRelnRelnTMsg != null) {
                if (isChangeOrganization || isChangeResource || NMAL2520CommonLogic.isNotEquals(cMsg.A.no(i).effThruDt_AB.getValue(), cMsg.A.no(i).effThruDt_A1.getValue()) || NMAL2520CommonLogic.isNotEquals(cMsg.A.no(i).effFromDt_AB.getValue(), cMsg.A.no(i).effFromDt_A1.getValue())) { // 2017/03/08 S21_NA#17760 Mod
                    String ezUpTimeOfScrnReln = cMsg.A.no(i).ezUpTime_A1.getValue();
                    String ezUpTimeZoneOfScrnReln = cMsg.A.no(i).ezUpTimeZone_A1.getValue();
                    String ezUpTimeOfCurrentReln = dsOrgRelnRelnTMsg.ezUpTime.getValue();
                    String ezUpTimeZoneOfCurrentReln = dsOrgRelnRelnTMsg.ezUpTimeZone.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                        return;
                    }

                    // Del Start 2017/10/13 QC#21753
                    //if (isChangeOrganization || isChangeResource) {
                    // Del End 2017/10/13 QC#21753
                        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                    // Del Start 2017/10/13 QC#21753
                    //}
                    // Del End 2017/10/13 QC#21753

                    if (NMAL2520CommonLogic.checkChangeParentOrganization(cMsg, i)) {
                        S21FastTBLAccessor.removePhysical(new DS_ORG_RELNTMsg[] {dsOrgRelnRelnTMsg });
                        DS_ORG_RELNTMsg createDsOrgRelnTMsg = new DS_ORG_RELNTMsg();
                        if (!createDsOrgReln(cMsg, i, createDsOrgRelnTMsg)) {
                            cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                            return;
                        }
                    } else {
                        // Del Start 2017/10/13 QC#21753
                        //if (isChangeOrganization || isChangeResource) {
                        // Del End 2017/10/13 QC#21753
                            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                        // Del Start 2017/10/13 QC#21753
                        //}
                        // Del End 2017/10/13 QC#21753
                        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effThruDt, cMsg.A.no(i).effThruDt_A1);
                        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, cMsg.A.no(i).effFromDt_A1); // 2017/03/08 S21_NA#17760 Add
                        EZDTBLAccessor.update(dsOrgRelnRelnTMsg);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnRelnTMsg.getReturnCode())) {
                            cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                            return;
                        }
                    }
                    isDataRegister = true;
                }
            } else {
                // 2016/01/19 CSA-QC#1930 Del Start
                // from date should be bigger than sales date when it's registered.
                // if (!NMAL2520CommonLogic.checkFromDate(cMsg.A.no(i).effFromDt_A1.getValue())) {
                // cMsg.setMessageInfo(NMAL2520Constant.NMAM0044E, new String[]
                // {NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2520Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                // return;
                // }
                // 2016/01/19 CSA-QC#1930 Del End

                // start date has been changed, old data should be deleted.
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).effFromDt_A1)) {
                    if (!cMsg.A.no(i).effFromDt_A1.getValue().equals(sMsg.A.no(i).effFromDt_A1.getValue())) {

                        // DS_ORG_RELN
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgCd_X1, sMsg.orgCd_H1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).prntOrgCd_X1, sMsg.A.no(i).orgCd_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgStruTpCd_X1, sMsg.orgStruTpCd_H1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).effFromDt_X1, sMsg.A.no(i).effFromDt_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTime_X1, sMsg.A.no(i).ezUpTime_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTimeZone_X1, sMsg.A.no(i).ezUpTimeZone_A1.getValue());
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).dsOrgRelnPk_X1, sMsg.A.no(i).dsOrgRelnPk_A1.getValue()); // 2017/03/07 S21_NA#17760 Add

                        sMsg.X.setValidCount(sMsg.X.getValidCount() + 1);
                    }
                }
                if (!createDsOrgReln(cMsg, i, dsOrgRelnRelnTMsg)) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM0176E, new String[] {"DS_ORG_RELN"});
                    return;
                }
                isDataRegister = true;
            }
        }

        // 2016/01/21 CSA-QC#3120 Add Start
        for (i = 0; i < cMsg.B.getValidCount(); i++) {
            // UPDATE DS_ORG_RELN
            DS_ORG_RELNTMsg dsOrgRelnRelnTMsg = new DS_ORG_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
            // 2017/03/07 S21_NA#17760 Mod Start
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgCd, cMsg.B.no(i).orgCd_B1);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.prntOrgCd, cMsg.orgCd_H1);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgStruTpCd, cMsg.orgStruTpCd_H1);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, cMsg.B.no(i).effFromDt_B1);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.dsOrgRelnPk, cMsg.B.no(i).dsOrgRelnPk_B1); // 2017/03/07 S21_NA#17760 Add
            // 2017/03/07 S21_NA#17760 Mod End

            try {
                dsOrgRelnRelnTMsg = (DS_ORG_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgRelnRelnTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                return;
            }
            if (dsOrgRelnRelnTMsg != null || isChangeOrganization) {
                if (NMAL2520CommonLogic.isNotEquals(cMsg.B.no(i).effThruDt_BB.getValue(), cMsg.B.no(i).effThruDt_B1.getValue()) || NMAL2520CommonLogic.isNotEquals(cMsg.B.no(i).effFromDt_BB.getValue(), cMsg.B.no(i).effFromDt_B1.getValue())) { // 2017/03/08 S21_NA#17760 Mod
                    String ezUpTimeOfScrnReln = cMsg.B.no(i).ezUpTime_B1.getValue();
                    String ezUpTimeZoneOfScrnReln = cMsg.B.no(i).ezUpTimeZone_B1.getValue();
                    String ezUpTimeOfCurrentReln = dsOrgRelnRelnTMsg.ezUpTime.getValue();
                    String ezUpTimeZoneOfCurrentReln = dsOrgRelnRelnTMsg.ezUpTimeZone.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                        return;
                    }

                    // Mod Start 2017/10/13 QC#21753
                    //if (isChangeOrganization) {
                    // Mod End 2017/10/13 QC#21753
                        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                    // Mod Start 2017/10/13 QC#21753
                    //}
                    // Mod End 2017/10/13 QC#21753
                    ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effThruDt, cMsg.B.no(i).effThruDt_B1);
                    ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, cMsg.B.no(i).effFromDt_B1); // 2017/03/07 S21_NA#17760 Mod
                    EZDTBLAccessor.update(dsOrgRelnRelnTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnRelnTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                        return;
                    }
                    isDataRegister = true;
                }
            } else {
                // start date has been changed, old data should be deleted.
                if (ZYPCommonFunc.hasValue(sMsg.B.no(i).effFromDt_B1)) {
                    if (!cMsg.B.no(i).effFromDt_B1.getValue().equals(sMsg.B.no(i).effFromDt_B1.getValue())) {

                        // DS_ORG_RELN
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgCd_X1, sMsg.B.no(i).orgCd_B1);
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).prntOrgCd_X1, sMsg.orgCd_H1);
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).orgStruTpCd_X1, sMsg.orgStruTpCd_H1);
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).effFromDt_X1, sMsg.B.no(i).effFromDt_B1);
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTime_X1, sMsg.B.no(i).ezUpTime_B1);
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).ezUpTimeZone_X1, sMsg.B.no(i).ezUpTimeZone_B1);
                        ZYPEZDItemValueSetter.setValue(sMsg.X.no(sMsg.X.getValidCount()).dsOrgRelnPk_X1, sMsg.B.no(i).dsOrgRelnPk_B1); // 2017/03/07 S21_NA#17760 Add

                        sMsg.X.setValidCount(sMsg.X.getValidCount() + 1);
                    }
                }

                dsOrgRelnRelnTMsg = new DS_ORG_RELNTMsg();

                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgCd, cMsg.B.no(i).orgCd_B1);
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.prntOrgCd, cMsg.orgCd_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgStruTpCd, cMsg.orgStruTpCd_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, cMsg.B.no(i).effFromDt_B1);
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effThruDt, cMsg.B.no(i).effThruDt_B1);
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.dsOrgRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ORG_RELN_SQ)); // 2017/03/07 S21_NA#17760 Add

                EZDTBLAccessor.create(dsOrgRelnRelnTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnRelnTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM0176E, new String[] {"DS_ORG_RELN"});
                    return;
                }
                isDataRegister = true;
            }

        }
        // 2016/01/21 CSA-QC#3120 Add End

        if (!deleteDsOrgReln(cMsg, sMsg)) {
            return;
        }

        if (sMsg.X.getValidCount() > 0) {
            isDataRegister = true;
        }

        // ### Header ###
        if (dsOrgUnitTMsg != null) {
            if (isChangeOrganization || isChangeResource
                    || NMAL2520CommonLogic.checkChangedFieldsForDsOrgUnit(cMsg)) {
                String ezUpTimeOfScrnOrg = cMsg.ezUpTime_H1.getValue();
                String ezUpTimeZoneOfScrnOrg = cMsg.ezUpTimeZone_H1.getValue();
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
                    cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.effFromDt, cMsg.effFromDt_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.effThruDt, cMsg.effThruDt_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgNm, cMsg.orgNm_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgDescTxt, cMsg.orgDescTxt_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgTierCd, cMsg.orgTierCd_P1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgShortNm, cMsg.orgShortNm_H1);
                // QC#4429
                // ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.trtyGrpTpCd, cMsg.lineBizTpCd_P1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.firstOrgCd, cMsg.bizAreaOrgCd_P1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.autoEstFlg, cMsg.xxChkBox_H1);
                ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.csrRgTpCd, cMsg.csrRgTpCd_P1);

                // Mod Start 2017/10/13 QC#21753
                //if (isChangeOrganization) {
                if (isChangeOrganization || NMAL2520CommonLogic.checkChangedFieldsForDsOrgUnit(cMsg)) {
                    // Mod End 2017/10/13 QC#21753
                    ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.gnrnTpCd, GNRN_TP.FUTURE);
                }

                EZDTBLAccessor.update(dsOrgUnitTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgUnitTMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"DS_ORG_UNIT"});
                    return;
                }

                // Add Start 2018/01/30 QC#22699
                if (NMAL2520Constant.FIRST_TIER_CD.equals(cMsg.orgTierCd_P1.getValue()) && //
                        !NMAL2520Constant.FIRST_TIER_CD.equals(cMsg.orgTierCd_HB.getValue())) {
                    // If Tier(LEVEL) changed from 2-10 to 1,
                    // relation to zeroth will create automatically.
                    if (!createRelationforFirstOrg(cMsg, sMsg)) {
                        cMsg.setMessageInfo(NMAL2520Constant.NMAM0176E, new String[] {"DS_ORG_RELN" });
                        return;
                    }
                } else if (!NMAL2520Constant.FIRST_TIER_CD.equals(cMsg.orgTierCd_P1.getValue()) && //
                        NMAL2520Constant.FIRST_TIER_CD.equals(cMsg.orgTierCd_HB.getValue())) {
                    // If Tier(LEVEL) changed from 1 to 2-10,
                    // relation to zeroth will delete automatically.
                    if (!deleteRelationForFirstOrg(cMsg)) {
                        return;
                    }
                }
                // Add End 2018/01/30 QC#22699

                isDataRegister = true;
            }
        } else {
            // 2016/01/19 CSA-QC#1930 Del Start
            // from date should be bigger than sales date when it's
            // registered.
            // if
            // (!NMAL2520CommonLogic.checkFromDate(cMsg.effFromDt_H1.getValue()))
            // {
            // cMsg.setMessageInfo(NMAL2520Constant.NMAM0044E, new
            // String[]
            // {NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
            // NMAL2520Constant.NAME_FOR_MESSAGE_CURRENT_DT });
            // return false;
            // }
            // 2016/01/19 CSA-QC#1930 Del End

            // For Manual Input create new
            dsOrgUnitTMsg = new DS_ORG_UNITTMsg();

            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgCd, cMsg.orgCd_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgStruTpCd, cMsg.orgStruTpCd_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.effFromDt, cMsg.effFromDt_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.effThruDt, cMsg.effThruDt_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgNm, cMsg.orgNm_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgDescTxt, cMsg.orgDescTxt_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgTierCd, cMsg.orgTierCd_P1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.gnrnTpCd, GNRN_TP.FUTURE);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgShortNm, cMsg.orgShortNm_H1);
            // QC#4429
            // ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.trtyGrpTpCd, cMsg.lineBizTpCd_P1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.lineBizTpCd, cMsg.lineBizTpCd_P1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.firstOrgCd, cMsg.bizAreaOrgCd_P1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.autoEstFlg, cMsg.xxChkBox_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.csrRgTpCd, cMsg.csrRgTpCd_P1);

            EZDTBLAccessor.create(dsOrgUnitTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgUnitTMsg.getReturnCode())) {
                cMsg.setMessageInfo(NMAL2520Constant.NMAM0176E, new String[] {"DS_ORG_UNIT"});
                return;
            }
            isDataRegister = true;

            // relation to zeroth will create automatically.
            if (NMAL2520Constant.FIRST_TIER_CD.equals(cMsg.orgTierCd_P1.getValue())) {
                if (!createRelationforFirstOrg(cMsg, sMsg)) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM0176E, new String[] {"DS_ORG_RELN"});
                    return;
                }
            }
        }

        if (!isDataRegister) {
            cMsg.setMessageInfo(NMAL2520Constant.NMAM8333I);
        }

        // START 2017/06/14 J.Kim [QC#18924,ADD]
        if (cMsg.C.getValidCount() > 0) {
            NMAL2520CommonLogic.pagenation(cMsg, sMsg, pageFromNum);
            cMsg.xxPageShowOfNum.setValue(sMsg.C.getValidCount());
        }
        // END 2017/06/14 J.Kim [QC#18924,ADD]
    }

    // Add Start 2019/02/27 QC#30564
    /**
     * changePastTocOrgStruReln
     * @param sMsg NMAL2520SMsg
     * @param i int
     * @param oldTocOrgStruRelnTMsg TOC_ORG_STRU_RELNTMsg
     * @param slsDt String
     * @return boolean
     */
    private boolean changePastTocOrgStruReln(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg, int i, TOC_ORG_STRU_RELNTMsg oldTocOrgStruRelnTMsg, String slsDt) {
        String effFromDt = oldTocOrgStruRelnTMsg.effFromDt.getValue();
        String effThruDt = null;

        if (NMAL2520CommonLogic.isNotEquals(sMsg.C.no(i).effFromDt_CB.getValue(), sMsg.C.no(i).effFromDt_C1.getValue())) {
            effThruDt = ZYPDateUtil.addDays(sMsg.C.no(i).effFromDt_C1.getValue(), -1);
        } else {
            effThruDt = ZYPDateUtil.addDays(slsDt, -1);
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
            cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"TOC_ORG_STRU_RELN" });
            return false;
        }

        return true;
    }

    /**
     * changePastOrgFuncAsg
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @param i int
     * @param slsDt String
     * @return
     */
    private boolean changePastOrgFuncAsg(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg, int i, String slsDt) {

        if(!ZYPCommonFunc.hasValue(sMsg.C.no(i).psnCd_CB)){
            // First regist.
            return true;
        }

        ORG_FUNC_ASGTMsg orgFuncAsgTMsg = new ORG_FUNC_ASGTMsg();

        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.tocCd, sMsg.C.no(i).tocCd_U3);
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.psnCd, sMsg.C.no(i).psnCd_CB);
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effFromDt, sMsg.C.no(i).effFromDt_CB);

        String effFromDt = orgFuncAsgTMsg.effFromDt.getValue();
        String effThruDt = null;
        ORG_FUNC_ASGTMsg newOrgFuncAsgTMsg = null;

        if (NMAL2520CommonLogic.isNotEquals(sMsg.C.no(i).effFromDt_CB.getValue(), sMsg.C.no(i).effFromDt_C1.getValue())) {
            effThruDt = ZYPDateUtil.addDays(sMsg.C.no(i).effFromDt_C1.getValue(), -1);
        } else {
            effThruDt = ZYPDateUtil.addDays(slsDt, -1);
        }

        boolean delInsFlag = false;
        if (effFromDt.compareTo(effThruDt) > 0) {
            delInsFlag = true;

            newOrgFuncAsgTMsg = (ORG_FUNC_ASGTMsg) orgFuncAsgTMsg.clone();

            // If effFromDt > effThruDt, effFromDt is same as
            // effThruDt.
            ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.effFromDt, effThruDt);
            ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.effThruDt, effThruDt);
            ZYPEZDItemValueSetter.setValue(newOrgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);
        }

        try {
            orgFuncAsgTMsg = (ORG_FUNC_ASGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(orgFuncAsgTMsg);
        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(NMAL2520Constant.ZZZM9004E);
            return false;
        }

        if (orgFuncAsgTMsg != null) {
            if (delInsFlag) {
                // Delete ORG_FUNC_ASG
                S21FastTBLAccessor.removePhysical(new ORG_FUNC_ASGTMsg[] {orgFuncAsgTMsg });

                String rtCd = orgFuncAsgTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                    cMsg.setMessageInfo(NMAL2520Constant.MMAM0005E, new String[] {"ORG_FUNC_ASG" });
                    return false;
                }
            } else {
                // Update ORG_FUNC_ASG
                ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effThruDt, effThruDt);
                ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);

                S21FastTBLAccessor.update(orgFuncAsgTMsg);

                String rtCd = orgFuncAsgTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {"ORG_FUNC_ASG" });
                    return false;
                }
            }
        }

        if (delInsFlag) {
            EZDTBLAccessor.insert(newOrgFuncAsgTMsg);

            String rtCd = newOrgFuncAsgTMsg.getReturnCode();
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtCd)) {
                cMsg.setMessageInfo(NMAL2520Constant.MMAM0003E, new String[] {"ORG_FUNC_ASG" });
                return false;
            }
        }

        return true;
    }
    // Add End 2019/02/27 QC#30564

    // 2018/10/18 S21_NA#28351 Add Start
    // Mod Start 2019/02/27 QC#30564
    //private boolean insertTocOrgStruReln(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg, int i) {
    private boolean insertTocOrgStruReln(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg, int i, String slsDt) {
        // Mod End 2019/02/27 QC#30564
        // For Manual Input pressed Add button

        TOC_ORG_STRU_RELNTMsg tocOrgStruRelnTMsg = new TOC_ORG_STRU_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgStruTpCd, cMsg.orgStruTpCd_H1);
        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.tocCd, sMsg.C.no(i).tocCd_U1);

        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.orgCd, cMsg.orgCd_H1);
        // Mod Start 2019/02/27 QC#30564
        //ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effFromDt, sMsg.C.no(i).effFromDt_C1);
        String effFromDt = null;

        if (NMAL2520CommonLogic.isNotEquals(sMsg.C.no(i).effFromDt_CB.getValue(), sMsg.C.no(i).effFromDt_C1.getValue())) {
            effFromDt = sMsg.C.no(i).effFromDt_C1.getValue();
        } else {
            effFromDt = slsDt;
        }

        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effFromDt, effFromDt);
        // Mod End 2019/02/27 QC#30564

        ZYPEZDItemValueSetter.setValue(tocOrgStruRelnTMsg.effThruDt, sMsg.C.no(i).effThruDt_C1);

        EZDTBLAccessor.insert(tocOrgStruRelnTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }
    // 2018/10/18 S21_NA#28351 Add End

    private boolean createOrgFuncAsg(NMAL2520SMsg sMsg, int i, ORG_FUNC_ASGTMsg orgFuncAsgTMsg) {
        orgFuncAsgTMsg = new ORG_FUNC_ASGTMsg();

        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.tocCd, sMsg.C.no(i).tocCd_U1);

        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.psnCd, sMsg.C.no(i).psnCd_C1);
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effFromDt, sMsg.C.no(i).effFromDt_C1);
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effThruDt, sMsg.C.no(i).effThruDt_C1);
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);

        EZDTBLAccessor.insert(orgFuncAsgTMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL.equals(orgFuncAsgTMsg.getReturnCode())) {
            return true;
        }
        return false;
    }

    // Add Start 2019/02/27 QC#30564
    /**
     * createOrgFuncAsgForChangePerson
     * @param sMsg NMAL2520SMsg
     * @param i int
     * @param orgFuncAsgTMsg ORG_FUNC_ASGTMsg
     * @param isChangePerson boolean
     * @param slsDt String
     * @return
     */
    private boolean createOrgFuncAsgForChangePerson(NMAL2520SMsg sMsg, int i, ORG_FUNC_ASGTMsg orgFuncAsgTMsg, boolean isChangePersonCode, String slsDt) {
        orgFuncAsgTMsg = new ORG_FUNC_ASGTMsg();

        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.tocCd, sMsg.C.no(i).tocCd_U1);

        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.psnCd, sMsg.C.no(i).psnCd_C1);

        String effFromDt = null;
        if (isChangePersonCode && //
                sMsg.C.no(i).effFromDt_C1.getValue().equals(sMsg.C.no(i).effFromDt_CB.getValue())) {
            // Update(Change person and NOT change start date).
            effFromDt = slsDt;
        } else {
            // First regist or Update(Change person and start date).
            effFromDt = sMsg.C.no(i).effFromDt_C1.getValue();
        }

        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effFromDt, effFromDt);
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effThruDt, sMsg.C.no(i).effThruDt_C1);
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);

        EZDTBLAccessor.insert(orgFuncAsgTMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL.equals(orgFuncAsgTMsg.getReturnCode())) {
            return true;
        }

        return false;
    }
    // Add End 2019/02/27 QC#30564

    private boolean createDsOrgReln(NMAL2520CMsg cMsg, int i, DS_ORG_RELNTMsg dsOrgRelnTMsg) {
        dsOrgRelnTMsg = new DS_ORG_RELNTMsg();

        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.orgCd, cMsg.orgCd_H1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.prntOrgCd, cMsg.A.no(i).orgCd_A1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.orgStruTpCd, cMsg.orgStruTpCd_H1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.effFromDt, cMsg.A.no(i).effFromDt_A1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.effThruDt, cMsg.A.no(i).effThruDt_A1);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.gnrnTpCd, GNRN_TP.FUTURE);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.dsOrgRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ORG_RELN_SQ)); // 2017/03/07 S21_NA#17760 Add

        EZDTBLAccessor.insert(dsOrgRelnTMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnTMsg.getReturnCode())) {
            return true;
        }
        return false;

    }

    private boolean deleteDsOrgReln(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        // 2016/08/05 CSA-QC#12834 Delete Start
        // int len = sMsg.X.getValidCount();
        // int cnt = 0;
        // if (len > 0) {
        //     DS_ORG_RELNTMsg[] dsOrgRelnArr = new DS_ORG_RELNTMsg[len];
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
//                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.orgCd, sMsg.X.no(j).orgCd_X1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.prntOrgCd, sMsg.X.no(j).prntOrgCd_X1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.orgStruTpCd, sMsg.X.no(j).orgStruTpCd_X1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.effFromDt, sMsg.X.no(j).effFromDt_X1.getValue());
                ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.dsOrgRelnPk, sMsg.X.no(j).dsOrgRelnPk_X1.getValue());
                // 2017/03/07 S21_NA#17760 Mod Endt

                dsOrgRelnDel = (DS_ORG_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsOrgRelnDel);

                if (dsOrgRelnDel == null || !RTNCD_NORMAL.equals(dsOrgRelnDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM8111E, new String[] {NMAL2520Constant.MSG_DS_ORG_RELN });
                    return false;
                }

                // 2016/03/30 CSA-QC#5945 Add Start
                if (GNRN_TP.DELETE.equals(sMsg.X.no(j).gnrnTpCd_X1.getValue())) {
                    ezUpTimeOfScrnReln = dsOrgRelnDel.ezUpTime.getValue();
                    ezUpTimeZoneOfScrnReln = dsOrgRelnDel.ezUpTimeZone.getValue();
                    ezUpTimeOfCurrentReln = sMsg.X.no(j).ezUpTime_X1.getValue();
                    ezUpTimeZoneOfCurrentReln = sMsg.X.no(j).ezUpTimeZone_X1.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.effThruDt, ZYPDateUtil.addDays(dsOrgRelnDel.effFromDt.getValue(), -1));
                    ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.gnrnTpCd, GNRN_TP.DELETE);
                    EZDTBLAccessor.update(dsOrgRelnDel);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnDel.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"DS_ORG_RELN"});
                        return false;
                    }

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
         //  int result = S21FastTBLAccessor.removeLogical(dsOrgRelnArr);
         //  if (result != len) {
         //   cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {NMAL2520Constant.MSG_DS_ORG_RELN });
         //  return false;
         // }
         //  }
         // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    // Add Start 2018/01/30 QC#22699
    /**
     * @param cMsg NMAL2520CMsg
     * @return boolean
     */
    private boolean deleteRelationForFirstOrg(NMAL2520CMsg cMsg) {
        BigDecimal dsOrgRelnPk = null;

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2520Query.getInstance().getDeleteZeroTierReln(cMsg);
        if (ssmResult.isCodeNormal()) {
            Map<String, Object> resultMap = (Map<String, Object>) ssmResult.getResultObject();
            dsOrgRelnPk = (BigDecimal) resultMap.get("DS_ORG_RELN_PK");
        } else {
            cMsg.setMessageInfo(NMAL2520Constant.NMAM8111E, new String[] {NMAL2520Constant.MSG_DS_ORG_RELN });
            return false;
        }

        DS_ORG_RELNTMsg dsOrgRelnDel = new DS_ORG_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.dsOrgRelnPk, dsOrgRelnPk);

        dsOrgRelnDel = (DS_ORG_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsOrgRelnDel);

        if (dsOrgRelnDel == null || !RTNCD_NORMAL.equals(dsOrgRelnDel.getReturnCode())) {
            cMsg.setMessageInfo(NMAL2520Constant.NMAM8111E, new String[] {NMAL2520Constant.MSG_DS_ORG_RELN });
            return false;
        }

        String slsDt = ZYPDateUtil.getSalesDate();
        String effFromDt = dsOrgRelnDel.effFromDt.getValue();
        String effThruDt = dsOrgRelnDel.effThruDt.getValue();

        if (ZYPCommonFunc.hasValue(effThruDt)) {
            if (ZYPDateUtil.compare(effThruDt, slsDt) >= 0) {
                effThruDt = ZYPDateUtil.addDays(slsDt, -1);
            }
        } else {
            effThruDt = ZYPDateUtil.addDays(slsDt, -1);
        }

        if (ZYPDateUtil.compare(effFromDt, effThruDt) > 0) {
            effThruDt = effFromDt;
        }

        ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.effThruDt, effThruDt);
        ZYPEZDItemValueSetter.setValue(dsOrgRelnDel.gnrnTpCd, GNRN_TP.DELETE);

        EZDTBLAccessor.update(dsOrgRelnDel);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnDel.getReturnCode())) {
            cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {NMAL2520Constant.MSG_DS_ORG_RELN });
            return false;
        }

        return true;
    }
    // Add End 2018/01/30 QC#22699

    private boolean deleteTocOrgStruReln(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
     // 2016/08/05 CSA-QC#12834 Delete Start
     // int len = sMsg.Y.getValidCount();
     // int cnt = 0;

     // if (len > 0) {
     //     TOC_ORG_STRU_RELNTMsg[] tocOrgStruRelnArr = new TOC_ORG_STRU_RELNTMsg[len];
     // 2016/08/05 CSA-QC#12834 Delete End
        if (sMsg.Y.getValidCount() > 0) {

            String ezUpTimeOfScrnReln = null;
            String ezUpTimeZoneOfScrnReln = null;
            String ezUpTimeOfCurrentReln = null;
            String ezUpTimeZoneOfCurrentReln = null;

            for (int j = 0; j < sMsg.Y.getValidCount(); j++) {
                TOC_ORG_STRU_RELNTMsg tocOrgStruRelnDel = new TOC_ORG_STRU_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.orgStruTpCd, sMsg.Y.no(j).orgStruTpCd_Y1.getValue());
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.tocCd, sMsg.Y.no(j).tocCd_Y1.getValue());
                ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.orgCd, sMsg.Y.no(j).orgCd_Y1.getValue());

                tocOrgStruRelnDel = (TOC_ORG_STRU_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(tocOrgStruRelnDel);

                if (tocOrgStruRelnDel == null || !RTNCD_NORMAL.equals(tocOrgStruRelnDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM8111E, new String[] {NMAL2520Constant.MSG_TOC_ORG_STRU_RELN });
                    return false;
                }

                // 2016/03/30 CSA-QC#5945 Add Start
                if (GNRN_TP.DELETE.equals(sMsg.Y.no(j).gnrnTpCd_Y1.getValue())) {
                    ezUpTimeOfScrnReln = tocOrgStruRelnDel.ezUpTime.getValue();
                    ezUpTimeZoneOfScrnReln = tocOrgStruRelnDel.ezUpTimeZone.getValue();
                    ezUpTimeOfCurrentReln = sMsg.Y.no(j).ezUpTime_Y1.getValue();
                    ezUpTimeZoneOfCurrentReln = sMsg.Y.no(j).ezUpTimeZone_Y1.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(tocOrgStruRelnDel.effThruDt, ZYPDateUtil.addDays(tocOrgStruRelnDel.effFromDt.getValue(), -1));
                    EZDTBLAccessor.update(tocOrgStruRelnDel);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tocOrgStruRelnDel.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"TOC_ORG_STRU_RELN"});
                        return false;
                    }
                 // 2016/08/05 CSA-QC#12834 Delete Start
                 // len--;
                 // continue;
                 // 2016/08/05 CSA-QC#12834 Delete End
                }
                // 2016/03/30 CSA-QC#5945 Add End

             // 2016/08/05 CSA-QC#12834 Delete Start
             // tocOrgStruRelnArr[cnt] = tocOrgStruRelnDel;
             // cnt++;
             // 2016/08/05 CSA-QC#12834 Delete End
            }

         // 2016/08/05 CSA-QC#12834 Delete Start
         // if (cnt > 0) {
         // int result = S21FastTBLAccessor.removeLogical(tocOrgStruRelnArr);
         // if (result != len) {
         // cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {NMAL2520Constant.MSG_TOC_ORG_STRU_RELN });
         // return false;
         // }
         // }
         // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    private boolean deleteOrgTocChngRqst(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

     // 2016/08/05 CSA-QC#12834 Delete Start
     // int len = sMsg.Y.getValidCount();
     // int cnt = 0;
     // if (len > 0) {
     // ORG_TOC_CHNG_RQSTTMsg[] orgTocChngRqstArr = new ORG_TOC_CHNG_RQSTTMsg[len];
     // 2016/08/05 CSA-QC#12834 Delete End
         if (sMsg.Y.getValidCount() > 0) {

            for (int j = 0; j < sMsg.Y.getValidCount(); j++) {

                ORG_TOC_CHNG_RQSTTMsg orgTocChngRqstDel = new ORG_TOC_CHNG_RQSTTMsg();
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstDel.tocCd, sMsg.Y.no(j).tocCd_Y2.getValue());
                ZYPEZDItemValueSetter.setValue(orgTocChngRqstDel.orgChngRqstPk, sMsg.Y.no(j).orgChngRqstPk_Y2.getValue());

                orgTocChngRqstDel = (ORG_TOC_CHNG_RQSTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(orgTocChngRqstDel);

                if (orgTocChngRqstDel == null || !RTNCD_NORMAL.equals(orgTocChngRqstDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM8111E, new String[] {NMAL2520Constant.MSG_ORG_TOC_CHNG_RQST });
                    return false;
                }

                ZYPEZDItemValueSetter.setValue(orgTocChngRqstDel.rgtnStsCd, RGTN_STS.READY_FOR_ORDER_TAKING);
                EZDTBLAccessor.update(orgTocChngRqstDel);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgTocChngRqstDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"ORG_TOC_CHNG_RQST"});
                    return false;
                }
                // 2016/08/05 CSA-QC#12834 Delete Start
                // orgTocChngRqstArr[cnt] = orgTocChngRqstDel;
                // cnt++;
                // 2016/08/05 CSA-QC#12834 Delete End
            }

         // 2016/08/05 CSA-QC#12834 Delete Start
         // if (cnt > 0) {
         // int result = S21FastTBLAccessor.removeLogical(orgTocChngRqstArr);
         // if (result != len) {
         // cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {NMAL2520Constant.MSG_ORG_TOC_CHNG_RQST });
         // return false;
         //  }
         // }
         // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    private boolean deleteOrgFuncAsg(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
     // 2016/08/05 CSA-QC#12834 Delete Start
     // int len = sMsg.Y.getValidCount();
     // int cnt = 0;

     // if (len > 0) {
     // ORG_FUNC_ASGTMsg[] orgFuncAsgArr = new ORG_FUNC_ASGTMsg[len];
     // 2016/08/05 CSA-QC#12834 Delete End
        if (sMsg.Y.getValidCount() > 0) {
            String ezUpTimeOfScrnReln = null;
            String ezUpTimeZoneOfScrnReln = null;
            String ezUpTimeOfCurrentReln = null;
            String ezUpTimeZoneOfCurrentReln = null;

            for (int j = 0; j < sMsg.Y.getValidCount(); j++) {
                ORG_FUNC_ASGTMsg orgFuncAsgDel = new ORG_FUNC_ASGTMsg();
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.tocCd, sMsg.Y.no(j).tocCd_Y3.getValue());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.psnCd, sMsg.Y.no(j).psnCd_Y3.getValue());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.effFromDt, sMsg.Y.no(j).effFromDt_Y3.getValue());

                orgFuncAsgDel = (ORG_FUNC_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(orgFuncAsgDel);

                if (orgFuncAsgDel == null || !RTNCD_NORMAL.equals(orgFuncAsgDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM8111E, new String[] {NMAL2520Constant.MSG_ORG_FUNC_ASG });
                    return false;
                }

                // 2016/03/30 CSA-QC#5945 Add Start
                if (GNRN_TP.DELETE.equals(sMsg.Y.no(j).gnrnTpCd_Y1.getValue())) {
                    ezUpTimeOfScrnReln = orgFuncAsgDel.ezUpTime.getValue();
                    ezUpTimeZoneOfScrnReln = orgFuncAsgDel.ezUpTimeZone.getValue();
                    ezUpTimeOfCurrentReln = sMsg.Y.no(j).ezUpTime_Y3.getValue();
                    ezUpTimeZoneOfCurrentReln = sMsg.Y.no(j).ezUpTimeZone_Y3.getValue();

                    if (!ZYPDateUtil.isSameTimeStamp(ezUpTimeOfScrnReln, ezUpTimeZoneOfScrnReln, ezUpTimeOfCurrentReln, ezUpTimeZoneOfCurrentReln)) {
                        cMsg.setMessageInfo(NMAL2510Constant.ZZZM9004E);
                        return false;
                    }

                    ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.effThruDt, ZYPDateUtil.addDays(orgFuncAsgDel.effFromDt.getValue(), -1));
                    ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.gnrnTpCd, GNRN_TP.DELETE);
                    EZDTBLAccessor.update(orgFuncAsgDel);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgFuncAsgDel.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0177E, new String[] {"TOC_ORG_STRU_RELN"});
                        return false;
                    }
                 // 2016/08/05 CSA-QC#12834 Delete Start
                 // len--;
                 // continue;
                 // 2016/08/05 CSA-QC#12834 Delete End
                }
                // 2016/03/30 CSA-QC#5945 Add End

             // 2016/08/05 CSA-QC#12834 Delete Start
             // orgFuncAsgArr[cnt] = orgFuncAsgDel;
             // cnt++;
             // 2016/08/05 CSA-QC#12834 Delete End
            }

         // 2016/08/05 CSA-QC#12834 Delete Start
         // if (cnt > 0) {
         // int result = S21FastTBLAccessor.removeLogical(orgFuncAsgArr);
         // if (result != len) {
         //  cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {NMAL2520Constant.MSG_ORG_FUNC_ASG });
         //  return false;
         // }
         // }
         // 2016/08/05 CSA-QC#12834 Delete End
        }

        return true;
    }

    private boolean deleteOrgFuncAsgForUpdate(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        int len = sMsg.Z.getValidCount();
        if (len > 0) {
            ORG_FUNC_ASGTMsg[] orgFuncAsgArr = new ORG_FUNC_ASGTMsg[len];
            int cnt = 0;

            for (int j = 0; j < sMsg.Z.getValidCount(); j++) {
                ORG_FUNC_ASGTMsg orgFuncAsgDel = new ORG_FUNC_ASGTMsg();
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.tocCd, sMsg.Z.no(j).tocCd_Z1.getValue());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.psnCd, sMsg.Z.no(j).psnCd_Z1.getValue());
                ZYPEZDItemValueSetter.setValue(orgFuncAsgDel.effFromDt, sMsg.Z.no(j).effFromDt_Z1.getValue());

                orgFuncAsgDel = (ORG_FUNC_ASGTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(orgFuncAsgDel);

                if (orgFuncAsgDel == null || !RTNCD_NORMAL.equals(orgFuncAsgDel.getReturnCode())) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM8111E, new String[] {NMAL2520Constant.MSG_ORG_FUNC_ASG });
                    return false;
                }
                orgFuncAsgArr[cnt] = orgFuncAsgDel;
                cnt++;
            }

            // Mod Start 2019/02/27 QC#30564
            //int result = S21FastTBLAccessor.removeLogical(orgFuncAsgArr);
            int result = S21FastTBLAccessor.removePhysical(orgFuncAsgArr);
            // Mod End 2019/02/27 QC#30564
            if (result != len) {
                cMsg.setMessageInfo(NMAL2520Constant.NMAM0177E, new String[] {NMAL2520Constant.MSG_ORG_FUNC_ASG });
                return false;
            }
        }

        return true;
    }

    private boolean createRelationforFirstOrg(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2520Query.getInstance().getZeroTierOrg(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {

            DS_ORG_RELNTMsg dsOrgRelnRelnTMsg = new DS_ORG_RELNTMsg();

            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgCd, cMsg.orgCd_H1);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.orgStruTpCd, cMsg.orgStruTpCd_H1);

            List resultList = (List) ssmResult.getResultObject();

            Map map = (Map) resultList.get(0);

            if (map.get(NMAL2520Constant.ORG_CD_DBCOLUMN) != null) {
                ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.prntOrgCd, (String) map.get(NMAL2520Constant.ORG_CD_DBCOLUMN));

                if (map.get(NMAL2520Constant.EFF_FROM_DT_DBCOLUMN) != null) {
                    ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effFromDt, (String) map.get(NMAL2520Constant.EFF_FROM_DT_DBCOLUMN));

                    if (map.get(NMAL2520Constant.EFF_THRU_DT_DBCOLUMN) != null) {
                        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.effThruDt, (String) map.get(NMAL2520Constant.EFF_THRU_DT_DBCOLUMN));
                    }

                    if (map.get(NMAL2520Constant.GNRN_TP_CD_DBCOLUMN) != null) {
                        ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.gnrnTpCd, (String) map.get(NMAL2520Constant.GNRN_TP_CD_DBCOLUMN));
                    }

                    ZYPEZDItemValueSetter.setValue(dsOrgRelnRelnTMsg.dsOrgRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_ORG_RELN_SQ)); // 2017/03/13 S21_NA#17760-2 Add

                    EZDTBLAccessor.create(dsOrgRelnRelnTMsg);

                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsOrgRelnRelnTMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NMAL2520Constant.NMAM0176E, new String[] {"DS_ORG_RELN"});
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
