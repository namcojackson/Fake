/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0400;

import static business.blap.NSAL0400.constant.NSAL0400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0400.common.NSAL0400CommonLogic;
import business.blap.NSAL0400.constant.NSAL0400Constant.MSG_PRM;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/18   Fujitsu         M.Yamada        Create          N/A
 * 2015/12/03   Hitachi         T.Tsuchida      Update          QC#1425
 * 2016/01/05   Hitachi         K.Yamada        Update          CSA Modify
 * 2016/02/10   Hitachi         T.Aoyagi        Update          QC3690
 * 2016/02/15   Hitachi         T.Aoyagi        Update          QC3691
 * 2016/08/01   Hitachi         A.Kohinata      Update          QC#2853
 * 2017/07/03   Hitachi         A.Kohinata      Update          QC#18349
 * 2017/11/20   Hitachi         K.Yamada        Update          QC#22654
 * 2022/02/04   Hitachi         K.Kitachi       Update          QC#59684
 * 2022/05/24   Hitachi         K.Kishimoto     Update          QC#59684
 *</pre>
 */
public class NSAL0400BL02 extends S21BusinessHandler {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplId = cMsg.getScreenAplID();

            if ("NSAL0400_INIT".equals(screenAplId)) {
                doProcess_NSAL0400_INIT((NSAL0400CMsg) cMsg);

                // events of NSAL0400Scrn00.
            } else if (screenAplId.startsWith("NSAL0400Scrn00")) {
                if (screenAplId.endsWith("_Review")) {
                    doProcess_NSAL0400_Review((NSAL0400CMsg) cMsg);
                } else if (screenAplId.endsWith("_Apply")) {
                    doProcess_NSAL0400_Apply((NSAL0400CMsg) cMsg);
                } else if (screenAplId.endsWith("_Reset")) {
                    doProcess_NSAL0400_INIT((NSAL0400CMsg) cMsg);
                // START 2016/02/15 T.Aoyagi [QC3691, ADD]
                } else if (screenAplId.endsWith("_Submit")) {
                    doProcess_NSAL0400_INIT((NSAL0400CMsg) cMsg);
                }
                // END 2016/02/15 T.Aoyagi [QC3691, ADD]
            } else if (screenAplId.equals("NSAL0400_NSBL0160")) {
                doProcess_NSAL0400_NSBL0160((NSAL0400CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0400_Apply(NSAL0400CMsg cMsg) {

        // START 2022/02/04 K.Kitachi [QC#59684, MOD]
//        if (!hasValue(cMsg.contrCloDt_H)) {
        if (!hasValue(cMsg.contrCloDt_H) && !ZYPConstant.FLG_ON_Y.equals(cMsg.contrTrmnFlg_H.getValue())) {
        // END 2022/02/04 K.Kitachi [QC#59684, MOD]
            cMsg.contrCloDt_H.setErrorInfo(1, ZZM9000E
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.TERMINATION_DATE.getMsgPrm()) });
            return;
        }

        NSAL0400CommonLogic.setContrHdrInfoToDtl(cMsg);

        // START 2016/02/10 T.Aoyagi [QC3690, ADD]
        final List<Integer> targetList = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_AD", "Y");
        if (targetList == null || targetList.isEmpty()) {
            //Please select at least 1 check box.
            cMsg.setMessageInfo(NSAM0015E);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                cMsg.A.no(i).xxChkBox_AD.setErrorInfo(1, NSAM0015E);
            }
            return;
        }
        HashSet<BigDecimal> targetContrPk = new HashSet<BigDecimal>();
        // END 2016/02/10 T.Aoyagi [QC3690, ADD]

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL0400_ACMsg acMsg = cMsg.A.no(i);
            // START 2016/02/10 T.Aoyagi [QC3690, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(acMsg.xxChkBox_AD.getValue())) {
                continue;
            }
            // END 2016/02/10 T.Aoyagi [QC3690, ADD]

            if (hasValue(acMsg.xxGenlFldAreaTxt_AD)) {
                //this line can not terminate due to the content of return message.
                continue;
            }
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            setValue(acMsg.contrTrmnFlg_AH, cMsg.contrTrmnFlg_H);
            setValue(acMsg.contrTrmnFlg_AD, cMsg.contrTrmnFlg_H);
            if (ZYPConstant.FLG_ON_Y.equals(acMsg.contrTrmnFlg_AH.getValue())) {
                if (!hasValue(acMsg.contrCloDt_AH) && !hasValue(cMsg.contrCloDt_H)) {
                    setValue(acMsg.contrCloDt_AH, acMsg.contrEffFromDt_AD);

                }
                if (!hasValue(acMsg.contrCloDt_AD) && !hasValue(cMsg.contrCloDt_H)) {
                    setValue(acMsg.contrCloDt_AD, acMsg.contrEffFromDt_AD);
                }
            }
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
            // START 2022/05/24 K.Kishimoto [QC#59684, Mod]
            if (hasValue(cMsg.contrCloDt_H)) {
                setValue(acMsg.contrCloDt_AH, cMsg.contrCloDt_H);
            }
            // END   2022/05/24 K.Kishimoto [QC#59684, Mod]
            setValue(acMsg.supprCrFlg_AH, cMsg.supprCrFlg_H);
            // START 2022/05/24 K.Kishimoto [QC#59684, Mod]
            if (hasValue(cMsg.contrCloDt_H)) {
                setValue(acMsg.contrCloDt_AD, cMsg.contrCloDt_H);
            }
            // END   2022/05/24 K.Kishimoto [QC#59684, Mod]
            setValue(acMsg.supprCrFlg_AD, cMsg.supprCrFlg_H);
            // START 2022/05/24 K.Kishimoto [QC#59684, ADD]
            if (ZYPDateUtil.compare(acMsg.contrCloDt_AD.getValue(), acMsg.contrEffFromDt_AD.getValue()) < 0
                    || ZYPDateUtil.compare(acMsg.contrEffThruDt_AD.getValue(), acMsg.contrCloDt_AD.getValue()) < 0) {
                acMsg.contrCloDt_AD.setErrorInfo(1, NSAM0306E 
                        , new String[] {converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.START_DATE.getMsgPrm()) //
                                , converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.END_DATE.getMsgPrm()) });
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(acMsg.contrTrmnFlg_AD.getValue())) {
                if (hasValue(acMsg.contrCloDt_AD.getValue()) && !acMsg.contrCloDt_AD.getValue().equals(acMsg.contrEffFromDt_AD.getValue())) {
                    acMsg.contrTrmnFlg_AD.setErrorInfo(1, NSAM0759E);
                    acMsg.contrCloDt_AD.setErrorInfo(1, NSAM0759E);
                    continue;
                }
            }
            // END   2022/05/24 K.Kishimoto [QC#59684, ADD]

            // START 2016/02/10 T.Aoyagi [QC3690, ADD]
            targetContrPk.add(acMsg.dsContrPk_AH.getValue());
            // END 2016/02/10 T.Aoyagi [QC3690, ADD]

            // mod start 2017/07/03 QC#18349
            NSAL0400CommonLogic.calcCreditAmt(cMsg, acMsg);
            // mod end 2017/07/03 QC#18349
        }

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NSAL0400_BCMsg bcMsg = cMsg.B.no(i);
            // START 2016/02/10 T.Aoyagi [QC3690, ADD]
            if (!targetContrPk.contains(bcMsg.dsContrPk_B.getValue())) {
                continue;
            }
            // END 2016/02/10 T.Aoyagi [QC3690, ADD]
            setValue(bcMsg.contrCloDt_B, cMsg.contrCloDt_H);
            setValue(bcMsg.supprCrFlg_B, cMsg.supprCrFlg_H);
            // START 2022/02/04 K.Kitachi [QC#59684, ADD]
            setValue(bcMsg.contrTrmnFlg_B, cMsg.contrTrmnFlg_H);
            // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        }

        cMsg.setMessageInfo(NZZM0002I);
    }

    private void doProcess_NSAL0400_Review(NSAL0400CMsg cMsg) {
        NSAL0400CommonLogic.setContrHdrInfoToDtl(cMsg);

        // Check Box
        final List<Integer> targetList = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_AD", "Y");
        if (targetList == null || targetList.isEmpty()) {
            //Please select at least 1 check box.
            cMsg.setMessageInfo(NSAM0015E);
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                cMsg.A.no(i).xxChkBox_AD.setErrorInfo(1, NSAM0015E);
            }
            return;
        }

        if (NSAL0400CommonLogic.hasErrDtlOfReview(cMsg, targetList)) {
            return;
        }

        for (int rowIndex : targetList) {
            NSAL0400_ACMsg acMsg = cMsg.A.no(rowIndex);
            // mod start 2017/07/03 QC#18349
            NSAL0400CommonLogic.calcCreditAmt(cMsg, acMsg);
            // mod end 2017/07/03 QC#18349
        }
        // add start 2017/11/20 QC#22654
        NSAL0400CommonLogic.calcFleetCreditAmt(cMsg);
        // add end 2017/11/20 QC#22654

        cMsg.setMessageInfo(NZZM0002I);
    }

    /**
     * doProcess_NSAL0400_INIT
     * @param cMsg NSAL0400CMsg
     * @param sMsg NSAL0400SMsg
     */
    private void doProcess_NSAL0400_INIT(NSAL0400CMsg cMsg) {

        // add start 2016/08/01 CSA Defect#2853
        if ("E".equals(cMsg.getMessageKind()) || "W".equals(cMsg.getMessageKind())) {
            return;
        }
        // add end 2016/08/01 CSA Defect#2853

        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());

        if (checkInparam(cMsg)) {
            return;
        }
        cMsg.contrCloDt_H.clear();
        cMsg.supprCrFlg_H.clear();
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        cMsg.contrTrmnFlg_H.clear();
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]
        createSvcMemoRsnCdPullDown(cMsg);
        cMsg.svcCmntTxt_F.clear();
        // add start 2016/08/01 CSA Defect#2853
        cMsg.svcMemoRsnCd_FS.clear();
        cMsg.xxRsltFlg.clear();
        // add end 2016/08/01 CSA Defect#2853
        // START 2022/02/04 K.Kitachi [QC#59684, ADD]
        cMsg.xxRsltFlg_W.clear();
        // END 2022/02/04 K.Kitachi [QC#59684, ADD]

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
        NSAL0400CommonLogic.getDtlData(cMsg);

    }

    private boolean checkInparam(NSAL0400CMsg cMsg) {
        if (cMsg.P.getValidCount() <= 0) {
            cMsg.setMessageInfo(NSAM0131E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.DS_CONTR_PK.getMsgPrm()) });
            return true;
        }
        return false;
    }

    private void createSvcMemoRsnCdPullDown(NSAL0400CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL0400Query.getInstance().getSvcMemoRsnList();
        if (ssmResult.isCodeNotFound()) {
            throw new S21AbendException(NSAM0219E //
                    , new String[] {converter.convLabel2i18nLabel(SCRN_ID, MSG_PRM.NO_SVC_RSN_CD.getMsgPrm()) });
        }

        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            cMsg.svcMemoRsnCd_FC.no(i).setValue((String) resultMap.get("SVC_MEMO_RSN_CD"));
            cMsg.xxScrItem130Txt_FD.no(i).setValue((String) resultMap.get("SVC_MEMO_RSN_DESC_TXT"));
        }
    }

    private void doProcess_NSAL0400_NSBL0160(NSAL0400CMsg cMsg) {

        S21SsmEZDResult ssmResult = null;
        NSAL0400Query query = NSAL0400Query.getInstance();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            ssmResult = query.countSvcMemo(cMsg.A.no(i).dsContrDtlPk_AD.getValue());
            if (ssmResult.isCodeNotFound()) {
                setValue(cMsg.A.no(i).xxExstFlg_AD, ZYPConstant.FLG_OFF_N);
                continue;
            }

            int svcMemoCnt = (Integer) ssmResult.getResultObject();
            if (svcMemoCnt > 0) {
                setValue(cMsg.A.no(i).xxExstFlg_AD, ZYPConstant.FLG_ON_Y);
            } else {
                setValue(cMsg.A.no(i).xxExstFlg_AD, ZYPConstant.FLG_OFF_N);
            }
        }
    }

}
