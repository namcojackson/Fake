/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2500;

import static business.blap.NMAL2500.constant.NMAL2500Constant.SCRN_ID_00;
import static business.blap.NMAL2500.constant.NMAL2500Constant.NMAM0182E;
import static business.blap.NMAL2500.constant.NMAL2500Constant.ZZM9000E;


import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL2500.common.NMAL2500CommonLogic;
import business.blap.NMAL2500.constant.NMAL2500Constant;
import business.db.DS_ORG_RELNTMsg;
import business.db.DS_ORG_UNITTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Org Resource Search  NMAL2500BL06
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/09/21   Fujitsu         Mz.Takahashi    Update          QC#11068
 * 2017/03/07   Fujitsu         M.Ohno          Update          S21_NA#17760
 * </pre>
 */
public class NMAL2500BL06 extends S21BusinessHandler {

    private static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (NMAL2500Constant.EVENT_NM_NMAL2500SCRN00_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NMAL2500Scrn00_Cmn_Submit((NMAL2500CMsg) cMsg, (NMAL2500SMsg) sMsg);
            } else if ("NMAL2500Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NMAL2500Scrn00_SaveSearch((NMAL2500CMsg)cMsg, (NMAL2500SMsg)sMsg);
            } else if ("NMAL2500Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcessNMAL2500Scrn00_DeleteSearch((NMAL2500CMsg)cMsg, (NMAL2500SMsg)sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL2500Scrn00_Cmn_Submit(NMAL2500CMsg cMsg, NMAL2500SMsg sMsg) {

        // Check to exists in Master Data
        if (!NMAL2500CommonLogic.checkInputForSubmit(cMsg, sMsg)) {
            return;
        }

        // Record Lock for update
        DS_ORG_UNITTMsg dsOrgUnitTMsg = new DS_ORG_UNITTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.orgCd, cMsg.orgCd_G1);

        try {
            dsOrgUnitTMsg = (DS_ORG_UNITTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgUnitTMsg);
        } catch (EZDDBRecordLockedException e) {
            cMsg.setMessageInfo(NMAL2500Constant.ZZZM9004E);
            return;
        }

        // compare uptime
        if (dsOrgUnitTMsg != null) {

            String ezUpTimeOfScrn = cMsg.ezUpTime_G1.getValue();
            String ezUpTimeZoneOfScrn = cMsg.ezUpTimeZone_G1.getValue();
            String ezUpTimeOfCurrent = dsOrgUnitTMsg.ezUpTime.getValue();
            String ezUpTimeZoneOfCurrent = dsOrgUnitTMsg.ezUpTimeZone.getValue();

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
                cMsg.setMessageInfo(NMAL2500Constant.ZZZM9004E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(dsOrgUnitTMsg.effThruDt, cMsg.effThruDt_H4);
            EZDTBLAccessor.update(dsOrgUnitTMsg);

        } else {
            cMsg.setMessageInfo(NMAL2500Constant.NZZM0000E);
            return;
        }

        if (ZYPCommonFunc.hasValue(cMsg.orgCd_G2)) {
            // Record Lock for update
            DS_ORG_RELNTMsg dsOrgRelnTMsg = new DS_ORG_RELNTMsg();
            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
            // 2017/03/06 S21_NA#17760 Mod Start
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.orgCd, cMsg.orgCd_G2);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.prntOrgCd, cMsg.prntOrgCd_G2);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.orgStruTpCd, cMsg.orgStruTpCd_G2);
//            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.effFromDt, cMsg.effFromDt_G2);
            ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.dsOrgRelnPk, cMsg.dsOrgRelnPk_G2);
            // 2017/03/06 S21_NA#17760 Mod End

            try {
                dsOrgRelnTMsg = (DS_ORG_RELNTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(dsOrgRelnTMsg);
            } catch (EZDDBRecordLockedException e) {
                cMsg.setMessageInfo(NMAL2500Constant.ZZZM9004E);
                return;
            }

            // compare uptime
            if (dsOrgRelnTMsg != null) {

                String ezUpTimeOfScrn = cMsg.ezUpTime_G2.getValue();
                String ezUpTimeZoneOfScrn = cMsg.ezUpTimeZone_G2.getValue();
                String ezUpTimeOfCurrent = dsOrgRelnTMsg.ezUpTime.getValue();
                String ezUpTimeZoneOfCurrent = dsOrgRelnTMsg.ezUpTimeZone.getValue();

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
                    cMsg.setMessageInfo(NMAL2500Constant.ZZZM9004E);
                    return;
                }

                ZYPEZDItemValueSetter.setValue(dsOrgRelnTMsg.effThruDt, cMsg.effThruDt_H4);
                EZDTBLAccessor.update(dsOrgRelnTMsg);

            } else {
                cMsg.setMessageInfo(NMAL2500Constant.NZZM0000E);
                return;
            }

            cMsg.setMessageInfo(NMAL2500Constant.NMAM8182I, new String[] {NMAL2500Constant.UPDATE });
        }
    }
    

    /**
     * SaveSearch Event
     * @param bizMsg Business Msg
     * @param sMsg Global Msg
     */
    private void doProcess_NMAL2500Scrn00_SaveSearch(NMAL2500CMsg bizMsg, NMAL2500SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) //
                && !ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }
        if (NMAL2500CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NMAM0182E // You can not [@] this record Because of [@] already exists.
                    , new String[] {//
                    converter.convLabel2i18nLabel(SCRN_ID_00, "Save") //
                            , converter.convLabel2i18nLabel(SCRN_ID_00, "Search Option Name") });
            return;
        }

        NMAL2500CommonLogic.callNszc0330forSaveSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());

    }

    /**
     * doProcessNMAL2460Scrn00_DeleteSearch
     * @param bizMsg
     * @param sMsg
     */
    private void doProcessNMAL2500Scrn00_DeleteSearch(NMAL2500CMsg bizMsg, NMAL2500SMsg sMsg) {
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            bizMsg.srchOptPk_S.setErrorInfo(1, ZZM9000E // [@] is not selected.
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID_00, "Saved Search Options") });
            return;
        }

        NMAL2500CommonLogic.callNszc0330forDeleteSearch(//
                bizMsg, sMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }
    
}
