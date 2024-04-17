/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL2060;

import static business.blap.NFBL2060.constant.NFBL2060Constant.BIZ_ID;
import static business.blap.NFBL2060.constant.NFBL2060Constant.NFCM0865E;
import static business.blap.NFBL2060.constant.NFBL2060Constant.YYYYMMDD;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFBL2060.common.NFBL2060CommonLogic;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NFBL2060BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/30   Fujitsu         C.Tanaka        Create          QC#5521
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/24   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2018/03/26   Hitachi         Y.Takeno        Update          QC#22350
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 * 2024/02/05   Hitachi         S.Ikariya       Update          QC#63451
 *</pre>
 */
public class NFBL2060BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
            NFBL2060SMsg glblMsg = (NFBL2060SMsg) sMsg;

            if ("NFBL2060Scrn00_CMN_ColClear".equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if ("NFBL2060Scrn00_CMN_ColSave".equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if ("NFBL2060Scrn00_DeleteSearch".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_DeleteSearch(bizMsg, glblMsg);
            } else if ("NFBL2060Scrn00_SaveSearch".equals(screenAplID)) {
                doProcess_NFBL2060Scrn00_SaveSearch(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    

    /**
     * Delete_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2060Scrn00_DeleteSearch(NFBL2060CMsg bizMsg, NFBL2060SMsg glblMsg) {

        String glblCmpyCd = getGlobalCompanyCode();

        // set API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);

        // call Search Option API
        if (NFBL2060CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFBL2060CommonLogic.createPulldownListSaveOpt(bizMsg, getContextUserInfo().getUserId(), glblCmpyCd);
            bizMsg.srchOptPk_S.clear();
        }
    }

    /**
     * Save_Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFBL2060Scrn00_SaveSearch(NFBL2060CMsg bizMsg, NFBL2060SMsg glblMsg) {

        if (NFBL2060CommonLogic.isExistSaveSearchName(bizMsg)) {
            bizMsg.srchOptNm_S.setErrorInfo(1, NFCM0865E, new String[] {"Save", "Search Option Name" });
            return;
        }

        String glblCmpyCd = getGlobalCompanyCode();
        String userId = getContextUserInfo().getUserId();

        // set search option value to API parameter
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S) && NFBL2060CommonLogic.isSameSaveSearchName(bizMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_S);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_S);
        } else {
            NFBL2060CommonLogic.setSelectSaveSearchName(bizMsg, pMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, userId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, bizMsg.dplyVndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, bizMsg.apVndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, bizMsg.apVndInvNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, bizMsg.acctInvStsCd_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, bizMsg.prntVndCd);
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, bizMsg.poNum);
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, bizMsg.delyOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, bizMsg.vndPmtTermDescTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, bizMsg.xxCmntTxt_FR);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, bizMsg.xxCmntTxt_TO);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, bizMsg.vndPmtMethDescTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, bizMsg.apInvCatgCd_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, bizMsg.apPmtChkNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, bizMsg.xxChkBox_01);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, bizMsg.xxChkBox_02);
        // START 2018/03/26 [QC#22350, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, bizMsg.dispPoDtlLineNum);
        // END   2018/03/26 [QC#22350, ADD]
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.acInvTotCostAmt_FR)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, bizMsg.acInvTotCostAmt_FR.getValue().toString());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.acInvTotCostAmt_TO)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, bizMsg.acInvTotCostAmt_TO.getValue().toString());
        }
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]

        if (ZYPCommonFunc.hasValue(bizMsg.invDt_FR)) {
            if (ZYPDateUtil.isValidDate(bizMsg.invDt_FR.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, bizMsg.invDt_FR.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.invDt_TO)) {
            if (ZYPDateUtil.isValidDate(bizMsg.invDt_TO.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, bizMsg.invDt_TO.getValue());
            }
        } 
        if (ZYPCommonFunc.hasValue(bizMsg.poDt_FR)) {
            if (ZYPDateUtil.isValidDate(bizMsg.poDt_FR.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, bizMsg.poDt_FR.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.poDt_TO)) {
            if (ZYPDateUtil.isValidDate(bizMsg.poDt_TO.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, bizMsg.poDt_TO.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.pmtDt_FR)) {
            if (ZYPDateUtil.isValidDate(bizMsg.pmtDt_FR.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, bizMsg.pmtDt_FR.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.pmtDt_TO)) {
            if (ZYPDateUtil.isValidDate(bizMsg.pmtDt_TO.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, bizMsg.pmtDt_TO.getValue());
            }
        }
        // QC#25902
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, bizMsg.vndRtrnNum);
        if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnSubmtDt_FR)) {
            if (ZYPDateUtil.isValidDate(bizMsg.vndRtrnSubmtDt_FR.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, bizMsg.vndRtrnSubmtDt_FR.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.vndRtrnSubmtDt_TO)) {
            if (ZYPDateUtil.isValidDate(bizMsg.vndRtrnSubmtDt_TO.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, bizMsg.vndRtrnSubmtDt_TO.getValue());
            }
        }
        // QC#25902 End
        // START 2018/10/18 J.Kim [QC#28816,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.invAuthDt_FR)) {
            if (ZYPDateUtil.isValidDate(bizMsg.invAuthDt_FR.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, bizMsg.invAuthDt_FR.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.invAuthDt_TO)) {
            if (ZYPDateUtil.isValidDate(bizMsg.invAuthDt_TO.getValue(), YYYYMMDD)) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, bizMsg.invAuthDt_TO.getValue());
            }
        }
        // END 2018/10/18 J.Kim [QC#28816,ADD]
        // call Search Option API
        if (NFBL2060CommonLogic.callSrchOptApi(bizMsg, pMsg)) {
            NFBL2060CommonLogic.createPulldownListSaveOpt(bizMsg, userId, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_S, pMsg.srchOptPk);
            bizMsg.srchOptNm_S.clear();
        }
    }

}
