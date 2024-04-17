/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC002001;

import static com.canon.cusa.s21.common.NSX.NSXC002001.constant.NSXC002001Constant.NSAB0560_DEFAULT_FIN_BR;
import business.db.COA_BRTMsg;
import business.db.SVC_BR_FIN_BR_XREFTMsg;
import business.db.SVC_BR_FIN_BR_XREFTMsgArray;
import business.db.SVC_BR_POST_XREFTMsg;
import business.db.SVC_BR_POST_XREFTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 * Get Branch Code
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/25/2016   Hitachi         A.Kohinata      Create          QC#6951
 * 24/18/2018   Fujitsu         A.Kosai         Update          QC#21919
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 * 2020/11/16   CITS            R.Shimamoto     Update          QC#57983
 * </pre>
 */
public class NSXC002001GetBrCd {

    /** length: 5 */
    private static final int LEN_5 = 5;

    /**
     * Get Branch Code
     * @param brCdBean BrCdBean
     */
    public static void getBrCd(NSXC002001GetBrCdBean brCdBean) {
        String glblCmpyCd = brCdBean.getGlblCmpyCd();
        String postCd = brCdBean.getPostCd();
        String svcLineBizCd = brCdBean.getSvcLineBizCd();
        String salesDate = brCdBean.getSalesDate();
        String fldSvcBrCd = null;
        String finBrCd = null;
        // 2018/04/18 QC#21919 Add Start
        String svcBrCdDescTxt = null;
        // 2018/04/18 QC#21919 Add End
        // START 2018/09/10 K.Kitachi [QC#26260, ADD]
        String sldByLineBizTpCd = brCdBean.getSldByLineBizTpCd();
        // END 2018/09/10 K.Kitachi [QC#26260, ADD]

        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(postCd) || !ZYPCommonFunc.hasValue(svcLineBizCd)) {
            return;
        }

        // Get Field Service Branch Code
        SVC_BR_POST_XREFTMsgArray postXrefTMsgArray = getSvcBrPostXref(glblCmpyCd, postCd, svcLineBizCd);
        if (postXrefTMsgArray.getValidCount() == 0) {
            if (postCd.length() > LEN_5) {
                postXrefTMsgArray = getSvcBrPostXref(glblCmpyCd, postCd.substring(0, LEN_5), svcLineBizCd);
            }
        }
        if (postXrefTMsgArray.getValidCount() > 0) {
            fldSvcBrCd = postXrefTMsgArray.no(0).svcBrCd.getValue();
            // 2018/04/18 QC#21919 Add Start
            svcBrCdDescTxt = postXrefTMsgArray.no(0).svcBrCdDescTxt.getValue();
            // 2018/04/18 QC#21919 Add End
        } else {
            return;
        }

        // Get Finance Branch Code
        // START 2020/11/16 R.Shimamoto [QC#57983, DEL]
//        if (ZYPCommonFunc.hasValue(salesDate)) {
//            // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////            SVC_BR_FIN_BR_XREFTMsgArray finBrXrefTMsgArray = getSvcBrFinBrXref(glblCmpyCd, fldSvcBrCd, svcLineBizCd, salesDate);
//            SVC_BR_FIN_BR_XREFTMsgArray finBrXrefTMsgArray = getSvcBrFinBrXref(glblCmpyCd, fldSvcBrCd, svcLineBizCd, salesDate, sldByLineBizTpCd);
//            // END 2018/09/10 K.Kitachi [QC#26260, MOD]
//            if (finBrXrefTMsgArray.getValidCount() > 0) {
//                finBrCd = finBrXrefTMsgArray.no(0).finBrCd.getValue();
//            } else {
//                // START 2018/09/10 K.Kitachi [QC#26260, MOD]
////                finBrCd = fldSvcBrCd;
//                finBrCd = ZYPCodeDataUtil.getVarCharConstValue(NSXC002001Constant.NSAB0560_DEFAULT_FIN_BR, glblCmpyCd);
//                // END 2018/09/10 K.Kitachi [QC#26260, MOD]
//            }
//            if (!existCoaBr(glblCmpyCd, finBrCd)) {
//                finBrCd = null;
//            }
//        }
        // END 2020/11/16 R.Shimamoto [QC#57983, DEL]

        brCdBean.setFldSvcBrCd(fldSvcBrCd);
        // START 2020/11/16 R.Shimamoto [QC#57983, MOD]
//        brCdBean.setFinBrCd(finBrCd);
        brCdBean.setFinBrCd(ZYPCodeDataUtil.getVarCharConstValue(NSAB0560_DEFAULT_FIN_BR, brCdBean.getGlblCmpyCd()));
        // END 2020/11/16 R.Shimamoto [QC#57983, MOD]
        // 2018/04/18 QC#21919 Add Start
        brCdBean.setSvcBrCdDescTxt(svcBrCdDescTxt);
        // 2018/04/18 QC#21919 Add End
    }

    private static SVC_BR_POST_XREFTMsgArray getSvcBrPostXref(String glblCmpyCd, String postCd, String svcLineBizCd) {
        SVC_BR_POST_XREFTMsg tMsg = new SVC_BR_POST_XREFTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("postCd01", postCd);
        tMsg.setConditionValue("svcLineBizCd01", svcLineBizCd);

        return (SVC_BR_POST_XREFTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//    private static SVC_BR_FIN_BR_XREFTMsgArray getSvcBrFinBrXref(String glblCmpyCd, String fldSvcBrCd, String svcLineBizCd, String salesDate) {
    private static SVC_BR_FIN_BR_XREFTMsgArray getSvcBrFinBrXref(String glblCmpyCd, String fldSvcBrCd, String svcLineBizCd, String salesDate, String sldByLineBizTpCd) {
    // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        SVC_BR_FIN_BR_XREFTMsg tMsg = new SVC_BR_FIN_BR_XREFTMsg();
        tMsg.setSQLID("001");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcBrCd01", fldSvcBrCd);
        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//        tMsg.setConditionValue("svcLineBizCd01", svcLineBizCd);
        tMsg.setConditionValue("svcByLineBizTpCd01", svcLineBizCd);
        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        tMsg.setConditionValue("effFromDt01", salesDate);
        tMsg.setConditionValue("effThruDt01", salesDate);
        // START 2018/09/10 K.Kitachi [QC#26260, ADD]
        if (!ZYPCommonFunc.hasValue(sldByLineBizTpCd)) {
            tMsg.setConditionValue("sldByLineBizTpCd01", svcLineBizCd);
            return (SVC_BR_FIN_BR_XREFTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        }
        tMsg.setConditionValue("sldByLineBizTpCd01", sldByLineBizTpCd);
        SVC_BR_FIN_BR_XREFTMsgArray tMsgArray = (SVC_BR_FIN_BR_XREFTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() > 0) {
            return tMsgArray;
        }
        tMsg.setConditionValue("sldByLineBizTpCd01", svcLineBizCd);
        // END 2018/09/10 K.Kitachi [QC#26260, ADD]

        return (SVC_BR_FIN_BR_XREFTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private static boolean existCoaBr(String glblCmpyCd, String coaBrCd) {
        COA_BRTMsg tMsg = new COA_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, coaBrCd);

        tMsg = (COA_BRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }
}
