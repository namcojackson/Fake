/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1200;

import static business.blap.NPAL1200.constant.NPAL1200Constant.INSRC_ZN_PLN_SQ;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_ADD;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_DELETE;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_EDIT;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_ERROR;
import static business.blap.NPAL1200.constant.NPAL1200Constant.MODE_NORMAL;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NMAM0182E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPAM0006E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPAM1234E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPAM1350E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPZM0216E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPZM0217E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.NPZM0218E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.ZZM9000E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.ZZM9015E;
import static business.blap.NPAL1200.constant.NPAL1200Constant.ZZZM9003I;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1200.common.NPAL1200CommonLogic;
import business.db.INSRC_ZN_PLNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1200 Insourcing Planning Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/25/2016   CITS       Yasushi Nomura        Create          N/A
 * 08/22/2016   CITS       S.Endo                Update          QC#12612
 * 08/22/2016   CITS       T.Kikuhara            Update          QC#17484
 * 09/04/2018   CITS       K.Ogino               Update          QC#28076
 * 03/01/2023   CITS       R.Kurahashi           Update          QC#61128
 *</pre>
 */
public class NPAL1200BL06 extends S21BusinessHandler {
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NPAL1200Scrn00_SaveSearchOption".equals(screenAplID)) {
                doProcess_SaveSearchOption((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if ("NPAL1200Scrn00_DeleteSearchOption".equals(screenAplID)) {
                doProcess_DeleteSearchOption((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else if ("NPAL1200Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_Submit((NPAL1200CMsg) cMsg, (NPAL1200SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Save Search option
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    private void doProcess_SaveSearchOption(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE) && !ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            cMsg.srchOptNm_TX.setErrorInfo(1, ZZM9000E, new String[] {"Saved Option Name" });
            return;
        }

        if (NPAL1200CommonLogic.isExistSaveSearchName(cMsg)) {
            cMsg.srchOptNm_TX.setErrorInfo(1, NMAM0182E, new String[] {"Save", "Saved Option Name" });
            return;
        }

        NPAL1200CommonLogic.callNszc0330forSaveSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * Delete Search option
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    private void doProcess_DeleteSearchOption(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SE)) {
            return;
        }

        NPAL1200CommonLogic.callNszc0330forDeleteSearchOption(cMsg, getContextUserInfo().getUserId(), getGlobalCompanyCode());
    }

    /**
     * Submit
     * @param cMsg NPAL1200CMsg
     * @param sMsg NPAL1200SMsg
     */
    private void doProcess_Submit(NPAL1200CMsg cMsg, NPAL1200SMsg sMsg) {
        NPAL1200CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        String glblCmpyCd = getGlobalCompanyCode();
        cMsg.xxNum_EF.setValue(MODE_NORMAL);
        boolean isError = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if ((sMsg.A.no(i).xxNum_M1.getValueInt() == MODE_EDIT) || (sMsg.A.no(i).xxNum_M1.getValueInt() == MODE_ADD)) {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).insrcItemPrcThrhdAmt_D1)) {
                    // QC#17484 Update start
                    if (BigDecimal.ZERO.compareTo(sMsg.A.no(i).insrcItemPrcThrhdAmt_D1.getValue()) > 0) {
                        sMsg.A.no(i).insrcItemPrcThrhdAmt_D1.setErrorInfo(1, NPAM1234E, new String[]{"$ Threshold"});
                        isError = true;
                    }
                    // QC#17484 Update end
                }
                // QC#28076
                if (!ZYPCommonFunc.hasValue(sMsg.A.no(i).insrcRnkSortNum_D1)) {
                    sMsg.A.no(i).insrcRnkSortNum_D1.setErrorInfo(1, ZZM9000E, new String[] {"Rank" });
                    isError = true;
                } else {
                    if (BigDecimal.ZERO.compareTo(sMsg.A.no(i).insrcRnkSortNum_D1.getValue()) >= 0) {
                        sMsg.A.no(i).insrcRnkSortNum_D1.setErrorInfo(1, NPAM1234E, new String[]{"Rank"});
                        isError = true;
                    } else {
                        BigDecimal newVal = sMsg.A.no(i).insrcRnkSortNum_D1.getValue().setScale(0, BigDecimal.ROUND_DOWN);
                        if (sMsg.A.no(i).insrcRnkSortNum_D1.getValue().compareTo(newVal) != 0) {
                            sMsg.A.no(i).insrcRnkSortNum_D1.setErrorInfo(1, ZZM9015E);
                            isError = true;
                        }
                    }
                }
                // QC#61128 Add Start
                if (!WH_OWNR.MNX.equals(sMsg.A.no(i).whOwnrCd_D1.getValue())
                        && NPAL1200CommonLogic.checkInsrcZoneForPrchReqTp(sMsg.A.no(i).fromRtlWhCd_D1.getValue(), sMsg.A.no(i).toRtlWhCd_D1.getValue(), sMsg.A.no(i).mdseItemClsTpCd_SE.getValue(), sMsg.A.no(i).prchReqTpCd_SE.getValue(), glblCmpyCd)) {
                    sMsg.A.no(i).prchReqTpCd_SE.setErrorInfo(1, NPAM1350E);
                    isError = true;
                }
                // QC#61128 Add End
            }
            if (sMsg.A.no(i).xxNum_M1.getValueInt() == MODE_ADD) {
                // QC#61128 Mod Start
                //if (NPAL1200CommonLogic.checkInsrcZone(sMsg.A.no(i).fromRtlWhCd_D1.getValue(), sMsg.A.no(i).toRtlWhCd_D1.getValue(), sMsg.A.no(i).mdseItemClsTpCd_SE.getValue(), glblCmpyCd)) {
                if (WH_OWNR.MNX.equals(sMsg.A.no(i).whOwnrCd_D1.getValue())
                        && NPAL1200CommonLogic.checkInsrcZone(sMsg.A.no(i).fromRtlWhCd_D1.getValue(), sMsg.A.no(i).toRtlWhCd_D1.getValue(), sMsg.A.no(i).mdseItemClsTpCd_SE.getValue(), glblCmpyCd)) {
                // QC#61128 Mod End
                    sMsg.A.no(i).fromRtlWhCd_D1.setErrorInfo(1, NPAM1350E);
                    isError = true;
                }
            }
        }
        if (isError) {
            int index = 0;
            for (; index < sMsg.A.getValidCount(); index++) {
                if (sMsg.A.no(index).insrcItemPrcThrhdAmt_D1.isError()
                        || sMsg.A.no(index).insrcRnkSortNum_D1.isError()
                        // QC#61128 Add Star
                        || sMsg.A.no(index).prchReqTpCd_SE.isError()
                        // QC#61128 Add End
                        || sMsg.A.no(index).fromRtlWhCd_D1.isError()) {
                    break;
                }
            }
            int length = cMsg.A.length();
            int start = (index / length) * length + 1;
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum, new BigDecimal(start));
            NPAL1200CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            cMsg.xxNum_EF.setValue(MODE_ERROR);
            return;
        }
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if ((sMsg.A.no(i).xxNum_M1.getValueInt() == MODE_EDIT) || (sMsg.A.no(i).xxNum_M1.getValueInt() == MODE_DELETE)) {
                INSRC_ZN_PLNTMsg tMsg = lockIns(cMsg, sMsg.A.no(i));
                if (tMsg == null) {
                    cMsg.xxNum_EF.setValue(MODE_ERROR);
                    return;
                }
                if ((sMsg.A.no(i).xxNum_M1.getValueInt() == MODE_EDIT)) {
                    ZYPEZDItemValueSetter.setValue(tMsg.mdseItemClsTpCd, sMsg.A.no(i).mdseItemClsTpCd_SE);
                    // QC#61128 Add Start
                    ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, sMsg.A.no(i).prchReqTpCd_SE);
                    // QC#61128 Add End
                    ZYPEZDItemValueSetter.setValue(tMsg.insrcItemPrcThrhdAmt, sMsg.A.no(i).insrcItemPrcThrhdAmt_D1);
                    ZYPEZDItemValueSetter.setValue(tMsg.insrcRnkSortNum, sMsg.A.no(i).insrcRnkSortNum_D1);
                    ZYPEZDItemValueSetter.setValue(tMsg.insrcEnblFlg, sMsg.A.no(i).insrcEnblFlg_SD);
                    EZDTBLAccessor.update(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPZM0217E);
                        cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                        cMsg.xxNum_EF.setValue(MODE_ERROR);
                        return;
                    }
                } else if (sMsg.A.no(i).xxNum_M1.getValueInt() == MODE_DELETE) {
                    EZDTBLAccessor.logicalRemove(tMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                        cMsg.setMessageInfo(NPZM0218E);
                        cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                        cMsg.xxNum_EF.setValue(MODE_ERROR);
                        return;
                    }
                }
            } else if (sMsg.A.no(i).xxNum_M1.getValueInt() == MODE_ADD) {
                INSRC_ZN_PLNTMsg tMsg = new INSRC_ZN_PLNTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
                ZYPEZDItemValueSetter.setValue(tMsg.insrcZnPlnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(INSRC_ZN_PLN_SQ));
                ZYPEZDItemValueSetter.setValue(tMsg.fromSrcZnCd, sMsg.A.no(i).fromSrcZnCd_D1);
                ZYPEZDItemValueSetter.setValue(tMsg.fromRtlWhCd, sMsg.A.no(i).fromRtlWhCd_D1);
                ZYPEZDItemValueSetter.setValue(tMsg.toSrcZnCd, sMsg.A.no(i).toSrcZnCd_D1);
                ZYPEZDItemValueSetter.setValue(tMsg.toRtlWhCd, sMsg.A.no(i).toRtlWhCd_D1);
                ZYPEZDItemValueSetter.setValue(tMsg.mdseItemClsTpCd, sMsg.A.no(i).mdseItemClsTpCd_SE);
                // QC#61128 Add Start
                ZYPEZDItemValueSetter.setValue(tMsg.prchReqTpCd, sMsg.A.no(i).prchReqTpCd_SE);
                // QC#61128 Add End
                ZYPEZDItemValueSetter.setValue(tMsg.insrcItemPrcThrhdAmt, sMsg.A.no(i).insrcItemPrcThrhdAmt_D1);
                ZYPEZDItemValueSetter.setValue(tMsg.insrcRnkSortNum, sMsg.A.no(i).insrcRnkSortNum_D1);
                ZYPEZDItemValueSetter.setValue(tMsg.insrcEnblFlg, sMsg.A.no(i).insrcEnblFlg_SD);
                EZDTBLAccessor.insert(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NPZM0216E);
                    cMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_ROLLBACK);
                    cMsg.xxNum_EF.setValue(MODE_ERROR);
                    return;
                }
            }
        }
        // Normal End
        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
    }

    private INSRC_ZN_PLNTMsg lockIns(NPAL1200CMsg bizMsg, NPAL1200_ASMsg sMsg) {
        INSRC_ZN_PLNTMsg tMsg = findIns(sMsg);
        if (tMsg == null) {
            bizMsg.setMessageInfo(NPAM0006E);
            return null;
        }

        String preUpTime = sMsg.xxRqstTs_D1.getValue();
        String preTimeZone = sMsg.xxRqstTz_D1.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        if (ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            return tMsg;
        } else {
            bizMsg.setMessageInfo(NPAM0006E);
            return null;
        }
    }

    private INSRC_ZN_PLNTMsg findIns(NPAL1200_ASMsg bizMsg) {
        INSRC_ZN_PLNTMsg tMsg = new INSRC_ZN_PLNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, "0");
        ZYPEZDItemValueSetter.setValue(tMsg.insrcZnPlnPk, bizMsg.insrcZnPlnPk_D1);
        return (INSRC_ZN_PLNTMsg) EZDTBLAccessor.findByKey(tMsg);
    }
}
