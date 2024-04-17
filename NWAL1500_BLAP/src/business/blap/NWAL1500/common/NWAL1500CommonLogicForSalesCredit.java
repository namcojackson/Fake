/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.DEF_SLS_CR_PCT_WRITER;
import static business.blap.NWAL1500.constant.NWAL1500Constant.PCT_100;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0181E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500Query;
import business.blap.NWAL1500.NWAL1500SMsg;
import business.blap.NWAL1500.NWAL1500_ACMsg;
import business.blap.NWAL1500.NWAL1500_ASMsg;
import business.blap.NWAL1500.NWAL1500_CCMsg;
import business.blap.NWAL1500.NWAL1500_CSMsg;
import business.blap.NWAL1500.NWAL1500_FCMsg;
import business.blap.NWAL1500.NWAL1500_FCMsgArray;
import business.blap.NWAL1500.NWAL1500_GCMsg;
import business.blap.NWAL1500.NWAL1500_HCMsg;
import business.blap.NWAL1500.NWAL1500_OCMsg;
import business.blap.NWAL1500.NWAL1500_OCMsgArray;
import business.blap.NWAL1500.constant.NWAL1500Constant;
import business.blap.NWAL1600.constant.NWAL1600Constant;
import business.parts.NMZC260001_defSlsRepListPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC152001.constant.NWZC152001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_ORD_TP_PROC_DFNTMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/09   Fujitsu         S.Takami        Create          S21_NA#1550
 * 2016/02/17   Fujitsu         T.ishii         Update          S21_NA#1745
 * 2016/03/05   Fujitsu         T.ishii         Update          S21_NA#5000#64
 * 2016/03/07   Fujitsu         M.Nakamura      Update          S21_NA#5000#78
 * 2016/03/30   Fujitsu         S.Takami        Update          S21_NA#5326
 * 2016/05/24   Fujitsu         T.Murai         Update          S21_NA#8617
 * 2016/05/30   Fujitsu         S.Takami        Update          S21_NA#8252
 * 2016/09/13   Fujitsu         M.Yamada        Update          S21_NA#13319
 * 2016/12/05   Fujitsu         T.Yoshida       Update          S21_NA#15889
 * 2017/02/22   Fujitsu         T.Yoshida       Update          S21_NA#17680
 * 2017/03/09   Fujitsu         W.Honda         Update          S21_NA#16855
 * 2017/10/23   Fujitsu         A.Kosai         Update          S21_NA#21768
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2017/11/15   Fujitsu         T.Aoi           Update          S21_NA#22604
 * 2017/11/16   Fujitsu         T.Aoi           Update          S21_NA#22620
 * 2017/11/30   Fujitsu         T.Aoi           Update          S21_NA#22775
 * 2017/12/12   Fujitsu         A.Kosai         Update          S21_NA#19804(Sol#349)
 * 2018/01/25   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2019/12/20   Fujitsu         A.Kazuki        Update          QC#53055
 * 2023/06/06   Hitachi         T.Doi           Update          CSA-QC#61465
 *</pre>
 */
public class NWAL1500CommonLogicForSalesCredit {


    /**
     * Copy Sales Credit Popup Data to Header Parameter
     * @param bizMsg Business Message
     * @return true: modified data false: add or delete
     */
    public static boolean copySlsCrForHdrFromPopup(NWAL1500CMsg bizMsg) {

        boolean isModified = false;
        boolean isSetdefSlsRep = false;

        // 2015/12/07 S21_NA#1550 Del Start
//        if (bizMsg.O.getValidCount() != bizMsg.F.getValidCount()) {
//            isModified = true;
//            bizMsg.F.clear();
//            bizMsg.F.setValidCount(0);
//        }
        // 2015/12/07 S21_NA#1550 Del End
        // 2015/12/07 S21_NA#1550 Add Start
        int hdrCnt = 0;
        List<NWAL1500_FCMsg> currHdrSlsCrList = new ArrayList<NWAL1500_FCMsg>(0);
        for (; hdrCnt < bizMsg.F.getValidCount(); hdrCnt++) {
            NWAL1500_FCMsg hdrSlsCr = new NWAL1500_FCMsg();
            EZDMsg.copy(bizMsg.F.no(hdrCnt), "FS", hdrSlsCr, "FS");
            currHdrSlsCrList.add(hdrSlsCr);
        }
        List<NWAL1500_FCMsg> hdrSlsCrList = new ArrayList<NWAL1500_FCMsg>(0);
        // 2015/12/07 S21_NA#1550 Add End
        // 2017/11/02 S21_NA#20146 Add Start
        List<Map<String, Object>> dummyRepList = new ArrayList<Map<String, Object>>(0);
        // 2017/11/02 S21_NA#20146 Add End
        int i = 0;
        for (; i < bizMsg.O.getValidCount(); i++) {
            String lineBizRoleTpCd = bizMsg.O.no(i).lineBizRoleTpCd_O.getValue();
            //String slsRepTocCd = bizMsg.O.no(i).slsRepTocCd_O.getValue(); // 2015/12/07 S21_NA#1550 Add // 2017/11/30 S21_NA#22775 Del
            BigDecimal slsCrPk = bizMsg.O.no(i).dsCpoSlsCrPk_O.getValue(); // 2016/05/30 S21_NA#8252 Add

            // BigDecimal dsCpoSlsCrPk = null;
            //if (ZYPCommonFunc.hasValue(bizMsg.O.no(i).dsCpoSlsCrPk_O)) {
            //    dsCpoSlsCrPk = bizMsg.O.no(i).dsCpoSlsCrPk_O.getValue();
            //}

            // 2015/12/07 S21_NA#1550 Update Start
//            if (!isSetdefSlsRep) {
//                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd)) {
//                    NWAL1500CommonLogic.deriveDefaultSlsRepForHdr(bizMsg, bizMsg.O.no(i).slsRepTocCd_O.getValue());
//                    isSetdefSlsRep = true;
//                }
//            }
//            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.F.no(i).xxRqstTpCd_FS, bizMsg.O.no(i).xxRqstTpCd_O)) {
//                isModified = true;
//                ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).xxRqstTpCd_FS, bizMsg.O.no(i).xxRqstTpCd_O);
//            }
//            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.F.no(i).dsCpoSlsCrPk_FS, bizMsg.O.no(i).dsCpoSlsCrPk_O)) {
//                isModified = true;
//                ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).dsCpoSlsCrPk_FS, bizMsg.O.no(i).dsCpoSlsCrPk_O);
//            }
//            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.F.no(i).slsRepTocCd_FS, bizMsg.O.no(i).slsRepTocCd_O)) {
//                isModified = true;
//                ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).slsRepTocCd_FS, bizMsg.O.no(i).slsRepTocCd_O);
//            }
//            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.F.no(i).slsRepRoleTpCd_FS, bizMsg.O.no(i).lineBizRoleTpCd_O)) {
//                isModified = true;
//                ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).slsRepRoleTpCd_FS, bizMsg.O.no(i).lineBizRoleTpCd_O);
//                ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).lineBizRoleTpCd_FS, bizMsg.O.no(i).lineBizRoleTpCd_O);
//            }
//            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.F.no(i).slsRepCrPct_FS, bizMsg.O.no(i).slsRepCrPct_O)) {
//                isModified = true;
//                ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).slsRepCrPct_FS, bizMsg.O.no(i).slsRepCrPct_O);
//            }
//            if (!NWAL1500CommonLogic.isEqualsEZDItem(bizMsg.F.no(i).slsCrQuotFlg_FS, bizMsg.O.no(i).slsCrQuotFlg_O)) {
//                isModified = true;
//                ZYPEZDItemValueSetter.setValue(bizMsg.F.no(i).slsCrQuotFlg_FS, bizMsg.O.no(i).slsCrQuotFlg_O);
//            }
            // 2015/12/07 S21_NA#1550 Update End
            if (!isSetdefSlsRep) {
                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) //
                        || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) //
                        // Add Start 2019/12/20 QC#53055
                        || LINE_BIZ_ROLE_TP.IS_WRITER.equals(lineBizRoleTpCd)
                        // Add End   2019/12/20 QC#53055
                        || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd) //
                        || LINE_BIZ_ROLE_TP.EMSD_WRITER.equals(lineBizRoleTpCd)) { // 2017/11/02 S21_NA#20146 Add
                    NWAL1500CommonLogic.deriveDefaultSlsRepForHdr(bizMsg, bizMsg.O.no(i).slsRepTocCd_O.getValue());
                    isSetdefSlsRep = true;
                }
            }
            boolean srched = false;
            for (NWAL1500_FCMsg currHdrSlsCr : currHdrSlsCrList) {
                // QC#13319
                if (NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(currHdrSlsCr.xxRqstTpCd_FS.getValue())) {
                    continue;
                }
                // QC#13319

                // BigDecimal hdrDsCpoSlsCrPk = null;
                //if (ZYPCommonFunc.hasValue(currHdrSlsCr.dsCpoSlsCrPk_FS)) {
                //    hdrDsCpoSlsCrPk = currHdrSlsCr.dsCpoSlsCrPk_FS.getValue();
                //}
                //String hdrSlsRepTocCd = currHdrSlsCr.slsRepTocCd_FS.getValue(); 2017/11/30 S21_NA#22775 Del
                //String hdrSlsRepRoleTpCd = currHdrSlsCr.slsRepRoleTpCd_FS.getValue(); 2017/11/30 S21_NA#22775 Del

                // 2016/05/30 S21_NA#8252 Add Start
                BigDecimal hdrSlsCrPk = currHdrSlsCr.dsCpoSlsCrPk_FS.getValue();
                // 2016/05/30 S21_NA#8252 Add End

                //                if (ZYPCommonFunc.hasValue(dsCpoSlsCrPk) && ZYPCommonFunc.hasValue(hdrDsCpoSlsCrPk) && dsCpoSlsCrPk.compareTo(hdrDsCpoSlsCrPk) == 0) {
                // if (slsRepTocCd.equals(hdrSlsRepTocCd) &&lineBizRoleTpCd.equals(hdrSlsRepRoleTpCd_FS)) { 2016/05/30 S21_NA#8252 Mod Condition
                // 2017/11/30 S21_NA#22775 Mod Start
                //if ((slsRepTocCd.equals(hdrSlsRepTocCd) && lineBizRoleTpCd.equals(hdrSlsRepRoleTpCd)) //
                //        || (slsCrPk != null && hdrSlsCrPk != null && slsCrPk.compareTo(hdrSlsCrPk) == 0)) {
                if (slsCrPk != null && hdrSlsCrPk != null && slsCrPk.compareTo(hdrSlsCrPk) == 0) {
                // 2017/11/30 S21_NA#22775 Mod End
                    NWAL1500_FCMsg hdrSlsCr = new NWAL1500_FCMsg();
                    EZDMsg.copy(currHdrSlsCr, "FS", hdrSlsCr, "FS");
                    // 2015/12/08 S21_NA#1550 Del Start
//                    if (!NWAL1500CommonLogic.isEqualsEZDItem(hdrSlsCr.xxRqstTpCd_FS, bizMsg.O.no(i).xxRqstTpCd_O)) {
//                        isModified = true;
//                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.xxRqstTpCd_FS, bizMsg.O.no(i).xxRqstTpCd_O);
//                    }
                    // 2015/12/08 S21_NA#1550 Del End
                    if (!NWAL1500CommonLogic.isEqualsEZDItem(hdrSlsCr.dsCpoSlsCrPk_FS, bizMsg.O.no(i).dsCpoSlsCrPk_O)) {
                        isModified = true;
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.dsCpoSlsCrPk_FS, bizMsg.O.no(i).dsCpoSlsCrPk_O);
                    }
                    if (!NWAL1500CommonLogic.isEqualsEZDItem(hdrSlsCr.slsRepTocCd_FS, bizMsg.O.no(i).slsRepTocCd_O)) {
                        isModified = true;
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepTocCd_FS, bizMsg.O.no(i).slsRepTocCd_O);
                    }
                    if (!NWAL1500CommonLogic.isEqualsEZDItem(hdrSlsCr.slsRepRoleTpCd_FS, bizMsg.O.no(i).lineBizRoleTpCd_O)) {
                        isModified = true;
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepRoleTpCd_FS, bizMsg.O.no(i).lineBizRoleTpCd_O);
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.lineBizRoleTpCd_FS, bizMsg.O.no(i).lineBizRoleTpCd_O);
                    }
                    if (!NWAL1500CommonLogic.isEqualsEZDItem(hdrSlsCr.slsRepCrPct_FS, bizMsg.O.no(i).slsRepCrPct_O)) {
                        isModified = true;
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepCrPct_FS, bizMsg.O.no(i).slsRepCrPct_O);
                    }
                    if (!NWAL1500CommonLogic.isEqualsEZDItem(hdrSlsCr.slsCrQuotFlg_FS, bizMsg.O.no(i).slsCrQuotFlg_O)) {
                        isModified = true;
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsCrQuotFlg_FS, bizMsg.O.no(i).slsCrQuotFlg_O);
                    }
                    // 2015/12/08 S21_NA#1550 Add Start
                    if (!NWAL1500CommonLogic.isEqualsEZDItem(hdrSlsCr.xxRqstTpCd_FS, bizMsg.O.no(i).xxRqstTpCd_O)) {
                        if (!isModified && !NWZC152001Constant.MODE_MOD.equals(bizMsg.O.no(i).xxRqstTpCd_O.getValue())) {
                            isModified = true;
                        }
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.xxRqstTpCd_FS, bizMsg.O.no(i).xxRqstTpCd_O);
                    }
                    // 2015/12/08 S21_NA#1550 Add End
                    hdrSlsCrList.add(hdrSlsCr);
                    srched = true;
                }
            }
            // 2017/11/02 S21_NA#20146 Add Start
            String fstBizCtxAttbTxt = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                if (fstBizCtxAttbTxt.equals(bizMsg.O.no(i).lineBizRoleTpCd_O.getValue()) && ZYPConstant.FLG_OFF_N.equals(bizMsg.O.no(i).slsCrQuotFlg_O.getValue())) {

                    List<Map<String, Object>> slsRepList = NWAL1500CommonLogic.getSalesRepList(bizMsg.glblCmpyCd.getValue(), bizMsg.O.no(i).slsRepTocCd_O.getValue());
                    if (slsRepList != null && slsRepList.size() != 0 && !NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(bizMsg.O.no(i).xxRqstTpCd_O.getValue())) {
                        for (Map<String, Object> slsRepMap : slsRepList) {
                            dummyRepList.add(slsRepMap);
                        }
                    }
                }
            }
            // 2017/11/02 S21_NA#20146 Add End

            if (!srched) {
                NWAL1500_FCMsg hdrSlsCr = new NWAL1500_FCMsg();
                EZDMsg.copy(bizMsg.O.no(i), "O", hdrSlsCr, "FS");
                ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepRoleTpCd_FS, bizMsg.O.no(i).lineBizRoleTpCd_O);
                hdrSlsCrList.add(hdrSlsCr);
                isModified = true; // S21_NA#17680 Add
            }
        }
        // 2017/11/02 S21_NA#20146 Add Start
        NWAL1500CommonLogic.clearGLSegment(bizMsg);
        if (dummyRepList != null && dummyRepList.size() != 0) {
            NWAL1500CommonLogic.setGLSegment(bizMsg, dummyRepList.get(0));
        }
        // 2017/11/02 S21_NA#20146 Add End
        for (NWAL1500_FCMsg currHdrSlsCr : currHdrSlsCrList) {
            // QC#13319
            if (NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(currHdrSlsCr.xxRqstTpCd_FS.getValue())) {
                NWAL1500_FCMsg hdrSlsCr = new NWAL1500_FCMsg();
                EZDMsg.copy(currHdrSlsCr, "FS", hdrSlsCr, "FS");
                hdrSlsCrList.add(hdrSlsCr);
                continue;
            }
            // QC#13319

            String hdrSlsRepTocCd = currHdrSlsCr.slsRepTocCd_FS.getValue();
            String hdrSlsRepRoleTpCd = currHdrSlsCr.slsRepRoleTpCd_FS.getValue();
            BigDecimal hdrSlsCrPk = currHdrSlsCr.dsCpoSlsCrPk_FS.getValue(); // 2016/05/30 S21_NA#8252 Add
            boolean srched = false;
            for (i = 0; i < bizMsg.O.getValidCount(); i++) {
                String slsRepTocCd = bizMsg.O.no(i).slsRepTocCd_O.getValue();
                String lineBizRoleTpCd = bizMsg.O.no(i).lineBizRoleTpCd_O.getValue();
                BigDecimal slsCrPk = bizMsg.O.no(i).dsCpoSlsCrPk_O.getValue(); // 2016/05/30 S21_NA#8252 Add
                // if (hdrSlsRepTocCd.equals(slsRepTocCd) && hdrSlsRepRoleTpCd.equals(lineBizRoleTpCd)) { 2016/05/30 S21_NA#8252 Mod Condition
                if ((hdrSlsRepTocCd.equals(slsRepTocCd) && hdrSlsRepRoleTpCd.equals(lineBizRoleTpCd)) //
                        || (hdrSlsCrPk != null && slsCrPk != null && hdrSlsCrPk.compareTo(slsCrPk) == 0)) {
                    srched = true;
                    break;
                }
            }
            if (!srched && ZYPCommonFunc.hasValue(currHdrSlsCr.dsCpoSlsCrPk_FS)) {
                NWAL1500_FCMsg hdrSlsCr = new NWAL1500_FCMsg();
                EZDMsg.copy(currHdrSlsCr, "FS", hdrSlsCr, "FS");
                hdrSlsCr.xxRqstTpCd_FS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
                hdrSlsCrList.add(hdrSlsCr);
            }
        }
        hdrCnt = 0;
        bizMsg.F.clear();
        bizMsg.F.setValidCount(0);
        for (NWAL1500_FCMsg hdrSlsCr : hdrSlsCrList) {
            EZDMsg.copy(hdrSlsCr, "FS", bizMsg.F.no(hdrCnt), "FS");
            hdrCnt++;
        }
        bizMsg.F.setValidCount(hdrCnt);
        // 2015/12/07 S21_NA#1550 Del Start
        // if (isModified) {
        //     bizMsg.F.setValidCount(i);
        // }
        // 2015/12/07 S21_NA#1550 Del End
        return isModified;
    }

    /**
     * <pre>
     * Check all Sales Credit Data to be deleted
     * @param slsCrParamMsgArray Sales Credit Popup Parameter
     * @return true: all sales credit popup data will be deleted
     * false: almost one or more are not deleted.
     * </pre>
     */
    public static boolean isParamDtAllDeleted(NWAL1500_OCMsgArray slsCrParamMsgArray) {
        for (int i = 0; i < slsCrParamMsgArray.getValidCount(); i++) {
            String xxRqstTpCd = slsCrParamMsgArray.no(i).xxRqstTpCd_O.getValue();
            if (!NWZC152001Constant.MODE_DEL.equals(xxRqstTpCd)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Copy Parameter Sales Credint information to Line Config Sales Credit Information
     * @param bizMsg Business Message
     * @param configLineMsg line Config Message
     */
    public static void copyParamSlsCrInfo(NWAL1500CMsg bizMsg, NWAL1500_ACMsg configLineMsg) {
        // 2015/12/07 Update S21_NA#1550 Start
//        deleteConfigSlsCrInfo(bizMsg, configLineMsg);
//        int configCnt = bizMsg.G.getValidCount();
//        for (int hdrCnt = 0; hdrCnt < bizMsg.F.getValidCount(); hdrCnt++, configCnt++) {
//            EZDMsg.copy(bizMsg.F.no(hdrCnt), "FS", bizMsg.G.no(configCnt), "GS");
//            ZYPEZDItemValueSetter.setValue(bizMsg.G.no(configCnt).dsOrdPosnNum_GS, configLineMsg.dsOrdPosnNum_LC);
//            ZYPEZDItemValueSetter.setValue(bizMsg.G.no(configCnt).dsCpoConfigPk_GS, configLineMsg.dsCpoConfigPk_LC);
//        }
        String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
        BigDecimal dsCpoConfigPk = configLineMsg.dsCpoConfigPk_LC.getValue();

        List<NWAL1500_GCMsg> targetLineConfigSlsCrList = deleteConfigSlsCrInfo(bizMsg, configLineMsg);
        for (NWAL1500_GCMsg configSlsCr : targetLineConfigSlsCrList) {
            boolean srched = false;

            // String slsRepTocCd =
            // configSlsCr.slsRepTocCd_GS.getValue();
            // String slsRepRoleTpCd =
            // configSlsCr.slsRepRoleTpCd_GS.getValue();
            BigDecimal dsCpoSlsCrPk = configSlsCr.dsCpoSlsCrPk_GS.getValue(); // S21NA#1745

            int prmCnt = 0;
            for (; prmCnt < bizMsg.O.getValidCount(); prmCnt++) {
                // String hdrSlsRepTocCd =
                // bizMsg.O.no(prmCnt).slsRepTocCd_O.getValue();
                // String hdrSlsRepRoleTpCd =
                // bizMsg.O.no(prmCnt).lineBizRoleTpCd_O.getValue();
                BigDecimal resultDsCpoSlsCrPk = bizMsg.O.no(prmCnt).dsCpoSlsCrPk_O.getValue(); // S21NA#1745
                // if (slsRepTocCd.equals(hdrSlsRepTocCd) &&
                // slsRepRoleTpCd.equals(hdrSlsRepRoleTpCd)) {
                if (resultDsCpoSlsCrPk != null && dsCpoSlsCrPk != null && dsCpoSlsCrPk.compareTo(resultDsCpoSlsCrPk) == 0) {
                    srched = true;
                    break;
                }
            }
            // BigDecimal dsCpoSlsCrPk = configSlsCr.dsCpoSlsCrPk_GS.getValue();
            if (srched) {
                EZDMsg.copy(bizMsg.O.no(prmCnt), "O", configSlsCr, "GS");
                ZYPEZDItemValueSetter.setValue(configSlsCr.dsOrdPosnNum_GS, dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoConfigPk_GS, dsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(configSlsCr.slsRepRoleTpCd_GS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);
                // if (ZYPCommonFunc.hasValue(dsCpoSlsCrPk)) {
                String xxRqstTpCd = bizMsg.O.no(prmCnt).xxRqstTpCd_O.getValue();
                if (!ZYPCommonFunc.hasValue(xxRqstTpCd)) {
                    xxRqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
                }
                configSlsCr.xxRqstTpCd_GS.setValue(xxRqstTpCd);
                ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoSlsCrPk_GS, dsCpoSlsCrPk);
                // } else {
                // configSlsCr.xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                // }
            } else {
                // Delete Check
                // if (ZYPCommonFunc.hasValue(dsCpoSlsCrPk)) {
                configSlsCr.xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
                // }
            }
        }
        // Add New Data from Header
        for (int prmCnt = 0; prmCnt < bizMsg.O.getValidCount(); prmCnt++) {
            // String hdrSlsRepTocCd =
            // bizMsg.O.no(prmCnt).slsRepTocCd_O.getValue();
            // String hdrSlsRepRoleTpCd =
            // bizMsg.O.no(prmCnt).lineBizRoleTpCd_O.getValue();
            BigDecimal resultDsCpoSlsCrPk = bizMsg.O.no(prmCnt).dsCpoSlsCrPk_O.getValue(); // S21NA#1745
            // boolean srched = false;
            // for (NWAL1500_GCMsg configSlsCr :
            // targetLineConfigSlsCrList) {
            // String slsRepTocCd =
            // configSlsCr.slsRepTocCd_GS.getValue();
            // String slsRepRoleTpCd =
            // configSlsCr.slsRepRoleTpCd_GS.getValue();
            // if (hdrSlsRepTocCd.equals(slsRepTocCd) &&
            // hdrSlsRepRoleTpCd.equals(slsRepRoleTpCd)) {
            // srched = true;
            // break;
            // }
            // }
            if (resultDsCpoSlsCrPk == null) {
                // if (!srched) {
                NWAL1500_GCMsg configSlsCr = new NWAL1500_GCMsg();
                EZDMsg.copy(bizMsg.O.no(prmCnt), "O", configSlsCr, "GS");
                ZYPEZDItemValueSetter.setValue(configSlsCr.dsOrdPosnNum_GS, dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoConfigPk_GS, dsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(configSlsCr.slsRepRoleTpCd_GS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);
                configSlsCr.xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                targetLineConfigSlsCrList.add(configSlsCr);
            }
        }
        int configCnt = bizMsg.G.getValidCount();
        for (NWAL1500_GCMsg configSlsCr : targetLineConfigSlsCrList) {
            EZDMsg.copy(configSlsCr, null, bizMsg.G.no(configCnt), null);
            configCnt++;
        }
        // 2015/12/07 Update S21_NA#1550 End
        bizMsg.G.setValidCount(configCnt);
    }

    /**
     * <pre>
     * Delete sales credit informations which are related to parameter config from bizMsg.G.
     * </pre>
     * @param bizMsg Business Message
     * @param configLineMsg Config Message
     * @return targetLineConfigSlsCrList Target config line Sales Credit data #S21_NA#1550 add
     */
    public static List<NWAL1500_GCMsg> deleteConfigSlsCrInfo(NWAL1500CMsg bizMsg, NWAL1500_ACMsg configLineMsg) {
        String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
        List<NWAL1500_GCMsg> lineConfigSlsCrList = new ArrayList<NWAL1500_GCMsg>();
        List<NWAL1500_GCMsg> targetLineConfigSlsCrList = new ArrayList<NWAL1500_GCMsg>(); // 2015/12/07 S21_NA#1550 add

        int slsCrCnt = 0;
        for (; slsCrCnt < bizMsg.G.getValidCount(); slsCrCnt++) {
            if (dsOrdPosnNum.equals(bizMsg.G.no(slsCrCnt).dsOrdPosnNum_GS.getValue())) {
                if (!ZYPCommonFunc.hasValue(bizMsg.G.no(slsCrCnt).dsCpoSlsCrPk_GS)) {
                    continue;
                }
                // 2015/12/07 S21_NA#1550 add Start
                NWAL1500_GCMsg targetLineConfigSlsCr = new NWAL1500_GCMsg();
                EZDMsg.copy(bizMsg.G.no(slsCrCnt), null, targetLineConfigSlsCr, null);
                targetLineConfigSlsCrList.add(targetLineConfigSlsCr);
                // 2015/12/07 S21_NA#1550 add End
                continue;
            }
            NWAL1500_GCMsg lineConfigSlsCrMsg = new NWAL1500_GCMsg();
            EZDMsg.copy(bizMsg.G.no(slsCrCnt), null, lineConfigSlsCrMsg, null);
            lineConfigSlsCrList.add(lineConfigSlsCrMsg);
        }

        bizMsg.G.clear();
        slsCrCnt = 0;
        for (NWAL1500_GCMsg lineConfigSlsCrMsg : lineConfigSlsCrList) {
            EZDMsg.copy(lineConfigSlsCrMsg, null, bizMsg.G.no(slsCrCnt), null);
            slsCrCnt++;
        }
        bizMsg.G.setValidCount(slsCrCnt);
        return targetLineConfigSlsCrList; // 2015/12/07 S21_NA#1550 add
    }

    /**
     * <pre>
     * Delete sales credit informations which are related to parameter rma config from bizMsg.H.
     * </pre>
     * @param bizMsg Business Message
     * @param rmaConfigLineMsg Config Message
     * @return targetRmaConfigSlsCrList Target RMA config line Sales Credit data #S21_NA#1550 add
     */
    public static List<NWAL1500_HCMsg> deleteConfigSlsCrInfo(NWAL1500CMsg bizMsg, NWAL1500_CCMsg rmaConfigLineMsg) {
        String dsOrdPosnNum = rmaConfigLineMsg.dsOrdPosnNum_RC.getValue();
        List<NWAL1500_HCMsg> rmaConfigSlsCrList = new ArrayList<NWAL1500_HCMsg>();
        List<NWAL1500_HCMsg> targetRmaConfigSlsCrList = new ArrayList<NWAL1500_HCMsg>(); // 2015/12/07 S21_NA#1550 add

        int slsCrCnt = 0;
        for (; slsCrCnt < bizMsg.H.getValidCount(); slsCrCnt++) {
            if (dsOrdPosnNum.equals(bizMsg.H.no(slsCrCnt).dsOrdPosnNum_HS.getValue())) {
                if (!ZYPCommonFunc.hasValue(bizMsg.H.no(slsCrCnt).dsCpoSlsCrPk_HS)) {
                    continue;
                }
                // 2015/12/07 S21_NA#1550 add Start
                NWAL1500_HCMsg targetRmaConfigSlsCr = new NWAL1500_HCMsg();
                EZDMsg.copy(bizMsg.H.no(slsCrCnt), null, targetRmaConfigSlsCr, null);
                targetRmaConfigSlsCrList.add(targetRmaConfigSlsCr);
                // 2015/12/07 S21_NA#1550 add End
                continue;
            }
            NWAL1500_HCMsg rmaConfigSlsCrMsg = new NWAL1500_HCMsg();
            EZDMsg.copy(bizMsg.H.no(slsCrCnt), null, rmaConfigSlsCrMsg, null);
            rmaConfigSlsCrList.add(rmaConfigSlsCrMsg);
        }

        bizMsg.H.clear();
        slsCrCnt = 0;
        for (NWAL1500_HCMsg rmaConfigSlsCrMsg : rmaConfigSlsCrList) {
            EZDMsg.copy(rmaConfigSlsCrMsg, null, bizMsg.H.no(slsCrCnt), null);
            slsCrCnt++;
        }
        bizMsg.H.setValidCount(slsCrCnt);
        return targetRmaConfigSlsCrList; // 2015/12/07 S21_NA#1550 add
    }

    /**
     * Copy Parameter Sales Credint information to RMA Config Sales Credit Information
     * @param bizMsg Business Message
     * @param rmaConfigLineMsg line Config Message
     */
    public static void copyParamSlsCrInfo(NWAL1500CMsg bizMsg, NWAL1500_CCMsg rmaConfigLineMsg) {
        // 2015/12/07 Update S21_NA#1550 Start
//        deleteConfigSlsCrInfo(bizMsg, rmaConfigLineMsg);
//        int configCnt = bizMsg.H.getValidCount();
//        for (int hdrCnt = 0; hdrCnt < bizMsg.F.getValidCount(); hdrCnt++, configCnt++) {
//            EZDMsg.copy(bizMsg.F.no(hdrCnt), "FS", bizMsg.H.no(configCnt), "HS");
//            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(configCnt).dsOrdPosnNum_HS, rmaConfigLineMsg.dsOrdPosnNum_RC);
//            ZYPEZDItemValueSetter.setValue(bizMsg.H.no(configCnt).dsCpoConfigPk_HS, rmaConfigLineMsg.dsCpoConfigPk_RC);
//        }
        String dsOrdPosnNum = rmaConfigLineMsg.dsOrdPosnNum_RC.getValue();
        BigDecimal dsCpoConfigPk = rmaConfigLineMsg.dsCpoConfigPk_RC.getValue();

        List<NWAL1500_HCMsg> targetRmaConfigSlsCrList = deleteConfigSlsCrInfo(bizMsg, rmaConfigLineMsg);
        for (NWAL1500_HCMsg rmaConfigSlsCr : targetRmaConfigSlsCrList) {
            boolean srched = false;
            // String slsRepTocCd =
            // rmaConfigSlsCr.slsRepTocCd_HS.getValue();
            // String slsRepRoleTpCd =
            // rmaConfigSlsCr.slsRepRoleTpCd_HS.getValue();
            BigDecimal dsCpoSlsCrPk = rmaConfigSlsCr.dsCpoSlsCrPk_HS.getValue(); // S21NA#1745
            int prmCnt = 0;
            for (; prmCnt < bizMsg.O.getValidCount(); prmCnt++) {
                // String hdrSlsRepTocCd =
                // bizMsg.O.no(prmCnt).slsRepTocCd_O.getValue();
                // String hdrSlsRepRoleTpCd =
                // bizMsg.O.no(prmCnt).lineBizRoleTpCd_O.getValue();
                BigDecimal resultDsCpoSlsCrPk = bizMsg.O.no(prmCnt).dsCpoSlsCrPk_O.getValue(); // S21NA#1745
                // if (slsRepTocCd.equals(hdrSlsRepTocCd) &&
                // slsRepRoleTpCd.equals(hdrSlsRepRoleTpCd)) {
                if (resultDsCpoSlsCrPk != null && dsCpoSlsCrPk != null && dsCpoSlsCrPk.compareTo(resultDsCpoSlsCrPk) == 0) {
                    srched = true;
                    break;
                }
            }
            // BigDecimal dsCpoSlsCrPk =
            // rmaConfigSlsCr.dsCpoSlsCrPk_HS.getValue();
            if (srched) {
                EZDMsg.copy(bizMsg.O.no(prmCnt), "O", rmaConfigSlsCr, "HS");
                ZYPEZDItemValueSetter.setValue(rmaConfigSlsCr.dsOrdPosnNum_HS, dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(rmaConfigSlsCr.dsCpoConfigPk_HS, dsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(rmaConfigSlsCr.slsRepRoleTpCd_HS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);
                // if (ZYPCommonFunc.hasValue(dsCpoSlsCrPk)) {
                String xxRqstTpCd = bizMsg.O.no(prmCnt).xxRqstTpCd_O.getValue();
                if (!ZYPCommonFunc.hasValue(xxRqstTpCd)) {
                    xxRqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
                }
                rmaConfigSlsCr.xxRqstTpCd_HS.setValue(xxRqstTpCd);
                ZYPEZDItemValueSetter.setValue(rmaConfigSlsCr.dsCpoSlsCrPk_HS, dsCpoSlsCrPk);
                // } else {
                // rmaConfigSlsCr.xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                // }
            } else {
                // Delete Check
                // if (ZYPCommonFunc.hasValue(dsCpoSlsCrPk)) {
                rmaConfigSlsCr.xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
                // }
            }
        }
        // Add New Data from Header
        for (int prmCnt = 0; prmCnt < bizMsg.O.getValidCount(); prmCnt++) {
            // String hdrSlsRepTocCd =
            // bizMsg.O.no(prmCnt).slsRepTocCd_O.getValue();
            // String hdrSlsRepRoleTpCd =
            // bizMsg.O.no(prmCnt).lineBizRoleTpCd_O.getValue();
            BigDecimal resultDsCpoSlsCrPk = bizMsg.O.no(prmCnt).dsCpoSlsCrPk_O.getValue(); // S21NA#1745
            // boolean srched = false;
            // for (NWAL1500_HCMsg rmaConfigSlsCr :
            // targetRmaConfigSlsCrList) {
            // String slsRepTocCd =
            // rmaConfigSlsCr.slsRepTocCd_HS.getValue();
            // String slsRepRoleTpCd =
            // rmaConfigSlsCr.slsRepRoleTpCd_HS.getValue();
            // if (hdrSlsRepTocCd.equals(slsRepTocCd) &&
            // hdrSlsRepRoleTpCd.equals(slsRepRoleTpCd)) {
            // srched = true;
            // break;
            // }
            // }
            if (resultDsCpoSlsCrPk == null) {
                // if (!srched) {
                NWAL1500_HCMsg rmaConfigSlsCr = new NWAL1500_HCMsg();
                EZDMsg.copy(bizMsg.O.no(prmCnt), "O", rmaConfigSlsCr, "HS");
                ZYPEZDItemValueSetter.setValue(rmaConfigSlsCr.dsOrdPosnNum_HS, dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(rmaConfigSlsCr.dsCpoConfigPk_HS, dsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(rmaConfigSlsCr.slsRepRoleTpCd_HS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);
                rmaConfigSlsCr.xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                targetRmaConfigSlsCrList.add(rmaConfigSlsCr);
            }
        }
        int configCnt = bizMsg.H.getValidCount();
        for (NWAL1500_HCMsg configSlsCr : targetRmaConfigSlsCrList) {
            EZDMsg.copy(configSlsCr, null, bizMsg.H.no(configCnt), null);
            configCnt++;
        }
        // 2015/12/07 Update S21_NA#1550 End
        bizMsg.H.setValidCount(configCnt);
    }

    /**
     * Set Sales Credit Output Parameter to Line Config or RMA
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
    // public static void setSlsCreditOutPutParamToBizMsg(NWAL1500CMsg bizMsg) {
    public static void setSlsCreditOutPutParamToBizMsg(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
    // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        if (bizMsg.O.getValidCount() == 0) {
            return;
        }
        // START 2023/06/06 T.Doi [CSA-QC#61465, DEL]
        //String dsOrdPosnNum = bizMsg.O.no(0).dsOrdPosnNum_O.getValue();

        //int lineCnt = 0;
        // END 2023/06/06 T.Doi [CSA-QC#61465, DEL]
        if (TAB_LINE_CONFIG.equals(bizMsg.xxDplyTab.getValue())) {
            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
//          NWAL1500_ACMsg configMsg = null;
//          for (lineCnt = 0; lineCnt < bizMsg.A.getValidCount(); lineCnt++) {
//              configMsg = bizMsg.A.no(lineCnt);
//              if (dsOrdPosnNum.equals(configMsg.dsOrdPosnNum_LC.getValue())) {
//                  // break; 2015/12/07 S21_NA#1550
//                  copyParamSlsCrInfo(bizMsg, configMsg);
//                  // S21_NA#5000#64
//                  if (getSalesCreditQuoteCount(bizMsg, dsOrdPosnNum, TAB_LINE_CONFIG) > 1) {
//                      configMsg.slsCrSplFlg_LC.setValue(ZYPConstant.FLG_ON_Y);
//                  } else {
//                      configMsg.slsCrSplFlg_LC.setValue(ZYPConstant.FLG_OFF_N);
//                  }
//                  // S21_NA#5000#64 end
//              }
//          }
            List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            NWAL1500_ACMsg configMsg = new NWAL1500_ACMsg();
            for (int index : checkList) {
                EZDMsg.copy(glblMsg.A.no(index), null, configMsg, null);
                String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();
                copyParamSlsCrInfo(bizMsg, configMsg);
                if (getSalesCreditQuoteCount(bizMsg, dsOrdPosnNum, TAB_LINE_CONFIG) > 1) {
                    configMsg.slsCrSplFlg_LC.setValue(ZYPConstant.FLG_ON_Y);
                } else {
                    configMsg.slsCrSplFlg_LC.setValue(ZYPConstant.FLG_OFF_N);
                }
            }
            // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            // 2017/11/02 S21_NA#20146 Add Start
            List<Map<String, Object>> dummyRepList = new ArrayList<Map<String, Object>>(0);
            int i = 0;
            for (; i < bizMsg.O.getValidCount(); i++) {
                String fstBizCtxAttbTxt = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
                if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                    if (fstBizCtxAttbTxt.equals(bizMsg.O.no(i).lineBizRoleTpCd_O.getValue()) && ZYPConstant.FLG_OFF_N.equals(bizMsg.O.no(i).slsCrQuotFlg_O.getValue())) {

                        List<Map<String, Object>> slsRepList = NWAL1500CommonLogic.getSalesRepList(bizMsg.glblCmpyCd.getValue(), bizMsg.O.no(i).slsRepTocCd_O.getValue());
                        if (slsRepList != null && slsRepList.size() != 0 && !NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(bizMsg.O.no(i).xxRqstTpCd_O.getValue())) {
                            for (Map<String, Object> slsRepMap : slsRepList) {
                                dummyRepList.add(slsRepMap);
                            }
                        }
                    }
                }
            }
            NWAL1500CommonLogic.clearGLSegment(bizMsg);
            if (dummyRepList != null && dummyRepList.size() != 0) {
                NWAL1500CommonLogic.setGLSegment(bizMsg, dummyRepList.get(0));
            }
            // 2017/11/02 S21_NA#20146 Add End
            // 2015/12/07 S21_NA#1550 Del Start
//            List<NWAL1500_GCMsg> lineConfSlsCreditList = new ArrayList<NWAL1500_GCMsg>(0);
//            for (lineCnt = 0; lineCnt < bizMsg.G.getValidCount(); lineCnt++) {
//                if (!dsOrdPosnNum.equals(bizMsg.G.no(lineCnt).dsOrdPosnNum_GS.getValue())) {
//                    NWAL1500_GCMsg tempLineConfSlsCredit = new NWAL1500_GCMsg();
//                    EZDMsg.copy(bizMsg.G.no(lineCnt), null, tempLineConfSlsCredit, null);
//                    lineConfSlsCreditList.add(tempLineConfSlsCredit);
//                }
//            }
//            for (lineCnt = 0; lineCnt < bizMsg.O.getValidCount(); lineCnt++) {
//                NWAL1500_GCMsg tempLineConfSlsCredit = new NWAL1500_GCMsg();
//                EZDMsg.copy(bizMsg.O.no(lineCnt), "O", tempLineConfSlsCredit, "GS");
//                ZYPEZDItemValueSetter.setValue(tempLineConfSlsCredit.dsCpoConfigPk_GS, configMsg.dsCpoConfigPk_LC);
//                String lineBizRoleTpCd = bizMsg.O.no(lineCnt).lineBizRoleTpCd_O.getValue();
//                ZYPEZDItemValueSetter.setValue(tempLineConfSlsCredit.slsRepRoleTpCd_GS, lineBizRoleTpCd);
//                lineConfSlsCreditList.add(tempLineConfSlsCredit);
//            }
//            lineCnt = 0;
//            bizMsg.G.clear();
//            for (NWAL1500_GCMsg lineConfSlsCreditMsg : lineConfSlsCreditList) {
//                EZDMsg.copy(lineConfSlsCreditMsg, null, bizMsg.G.no(lineCnt), null);
//                lineCnt++;
//            }
//            bizMsg.G.setValidCount(lineCnt);
            // 2015/12/07 S21_NA#1550 Del End
        } else if (TAB_RMA.equals(bizMsg.xxDplyTab.getValue())) {
            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
//          NWAL1500_CCMsg rmaConfigMsg = null;
//          for (lineCnt = 0; lineCnt < bizMsg.C.getValidCount(); lineCnt++) {
//              rmaConfigMsg = bizMsg.C.no(lineCnt);
//              if (dsOrdPosnNum.equals(rmaConfigMsg.dsOrdPosnNum_RC.getValue())) {
//                  // break; 2015/12/07 S21_NA#1550
//                  copyParamSlsCrInfo(bizMsg, rmaConfigMsg);
//                  // S21_NA#5000#64 start
//                  if (getSalesCreditQuoteCount(bizMsg, dsOrdPosnNum, TAB_RMA) > 1) {
//                      rmaConfigMsg.slsCrSplFlg_RC.setValue(ZYPConstant.FLG_ON_Y);
//                  } else {
//                      rmaConfigMsg.slsCrSplFlg_RC.setValue(ZYPConstant.FLG_OFF_N);
//                  }
//                  // S21_NA#5000#64 end
//              }
//          }
            List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            NWAL1500_CCMsg rmaConfigMsg = new NWAL1500_CCMsg();
            for (int index : checkList) {
                EZDMsg.copy(glblMsg.C.no(index), null, rmaConfigMsg, null);
                String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
                copyParamSlsCrInfo(bizMsg, rmaConfigMsg);
                if (getSalesCreditQuoteCount(bizMsg, dsOrdPosnNum, TAB_RMA) > 1) {
                    rmaConfigMsg.slsCrSplFlg_RC.setValue(ZYPConstant.FLG_ON_Y);
                } else {
                    rmaConfigMsg.slsCrSplFlg_RC.setValue(ZYPConstant.FLG_OFF_N);
                }
            }
            // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            // 2017/11/02 S21_NA#20146 Add Start
            List<Map<String, Object>> dummyRepList = new ArrayList<Map<String, Object>>(0);
            int i = 0;
            for (; i < bizMsg.O.getValidCount(); i++) {
                String fstBizCtxAttbTxt = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.GL_SGMT_DPLY_ORD_TP, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
                if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                    if (fstBizCtxAttbTxt.equals(bizMsg.O.no(i).lineBizRoleTpCd_O.getValue()) && ZYPConstant.FLG_OFF_N.equals(bizMsg.O.no(i).slsCrQuotFlg_O.getValue())) {

                        List<Map<String, Object>> slsRepList = NWAL1500CommonLogic.getSalesRepList(bizMsg.glblCmpyCd.getValue(), bizMsg.O.no(i).slsRepTocCd_O.getValue());
                        if (slsRepList != null && slsRepList.size() != 0 && !NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(bizMsg.O.no(i).xxRqstTpCd_O.getValue())) {
                            for (Map<String, Object> slsRepMap : slsRepList) {
                                dummyRepList.add(slsRepMap);
                            }
                        }
                    }
                }
            }
            NWAL1500CommonLogic.clearGLSegment(bizMsg);
            if (dummyRepList != null && dummyRepList.size() != 0) {
                NWAL1500CommonLogic.setGLSegment(bizMsg, dummyRepList.get(0));
            }
            // 2017/11/02 S21_NA#20146 Add End
            // 2015/12/07 S21_NA#1550 Del Start
//            List<NWAL1500_HCMsg> rmaSlsCreditList = new ArrayList<NWAL1500_HCMsg>(0);
//            for (lineCnt = 0; lineCnt < bizMsg.H.getValidCount(); lineCnt++) {
//                if (!dsOrdPosnNum.equals(bizMsg.H.no(lineCnt).dsOrdPosnNum_HS.getValue())) {
//                    NWAL1500_HCMsg tempRmaLineConfSlsCredit = new NWAL1500_HCMsg();
//                    EZDMsg.copy(bizMsg.H.no(lineCnt), null, tempRmaLineConfSlsCredit, null);
//                    rmaSlsCreditList.add(tempRmaLineConfSlsCredit);
//                }
//            }
//            for (lineCnt = 0; lineCnt < bizMsg.O.getValidCount(); lineCnt++) {
//                NWAL1500_HCMsg tempRmaLineConfSlsCredit = new NWAL1500_HCMsg();
//                EZDMsg.copy(bizMsg.O.no(lineCnt), "O", tempRmaLineConfSlsCredit, "HS");
//                ZYPEZDItemValueSetter.setValue(tempRmaLineConfSlsCredit.dsCpoConfigPk_HS, rmaConfigMsg.dsCpoConfigPk_RC);
//                String lineBizRoleTpCd = bizMsg.O.no(lineCnt).lineBizRoleTpCd_O.getValue();
//                ZYPEZDItemValueSetter.setValue(tempRmaLineConfSlsCredit.slsRepRoleTpCd_HS, lineBizRoleTpCd);
//                rmaSlsCreditList.add(tempRmaLineConfSlsCredit);
//            }
//            lineCnt = 0;
//            bizMsg.H.clear();
//            for (NWAL1500_HCMsg rmaSlsCreditMsg : rmaSlsCreditList) {
//                EZDMsg.copy(rmaSlsCreditMsg, null, bizMsg.H.no(lineCnt), null);
//                lineCnt++;
//            }
//            bizMsg.H.setValidCount(lineCnt);
            // 2015/12/07 S21_NA#1550 Del End
        } else {
            return;
        }
    }

    // 2018/01/25 S21_NA#19808 Add Start
    /**
     * Copy Header Sales Credint information to Line Config Sales Credit Information
     * @param bizMsg Business Message
     * @param configMsg line Config Message
     */
    public static void copyHdrSlsCrInfo(NWAL1500CMsg bizMsg, EZDMsg configMsg) {

        if (configMsg instanceof NWAL1500_ACMsg) {
            copyHdrSlsCrInfo(bizMsg, (NWAL1500_ACMsg) configMsg);
        } else if (configMsg instanceof NWAL1500_ASMsg) {
            NWAL1500_ACMsg configBizMsg = new NWAL1500_ACMsg();
            EZDMsg.copy((NWAL1500_ASMsg) configMsg, null, configBizMsg, null);
            copyHdrSlsCrInfo(bizMsg, configBizMsg);
        } else if (configMsg instanceof NWAL1500_CCMsg) {
            copyHdrSlsCrInfo(bizMsg, (NWAL1500_CCMsg) configMsg);
        } else if (configMsg instanceof NWAL1500_CSMsg) {
            NWAL1500_CCMsg configBizMsg = new NWAL1500_CCMsg();
            EZDMsg.copy((NWAL1500_CSMsg) configMsg, null, configBizMsg, null);
            copyHdrSlsCrInfo(bizMsg, configBizMsg);
        }
    }
    // 2018/01/25 S21_NA#19808 Add End
    /**
     * Copy Header Sales Credint information to Line Config Sales Credit Information
     * @param bizMsg Business Message
     * @param configLineMsg line Config Message
     */
    public static void copyHdrSlsCrInfo(NWAL1500CMsg bizMsg, NWAL1500_ACMsg configLineMsg) {
        copyHdrSlsCrToParamSlsCr(bizMsg);
        copyParamSlsCrInfo(bizMsg, configLineMsg);

        // Add 2016/03/07 S21_NA#5000#78 Start
        if (getSalesCreditQuoteCount(bizMsg, configLineMsg.dsOrdPosnNum_LC.getValue(), TAB_LINE_CONFIG) > 1) {
            configLineMsg.slsCrSplFlg_LC.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            configLineMsg.slsCrSplFlg_LC.setValue(ZYPConstant.FLG_OFF_N);
        }
        // Add 2016/03/07 S21_NA#5000#78 End
    }

    /**
     * Copy Header Sales Credint information to RMA Config Sales Credit Information
     * @param bizMsg Business Message
     * @param configLineMsg line Config Message
     */
    public static void copyHdrSlsCrInfo(NWAL1500CMsg bizMsg, NWAL1500_CCMsg configLineMsg) {
        copyHdrSlsCrToParamSlsCr(bizMsg);
        copyParamSlsCrInfo(bizMsg, configLineMsg);
    }

    /**
     * <pre>
     * Search Sales Credit info from bizMsg.G related to configLineMsg
     * </pre>
     * @param bizMsg Business Message
     * @param configLineMsg target config
     * @return true: exists false: not exists.
     */
    public static boolean isExistsSlsCr(NWAL1500CMsg bizMsg, EZDMsg ezdMsg) { // 2018/01/25 S21_NA#19808 Mod
        // 2018/01/25 S21_NA#19808 Mod Start
        String dsOrdPosnNum_L = null;
        String dsOrdPosnNum_R = null;
        if (ezdMsg instanceof NWAL1500_ACMsg) {
            dsOrdPosnNum_L = ((NWAL1500_ACMsg) ezdMsg).dsOrdPosnNum_LC.getValue();
        } else if (ezdMsg instanceof NWAL1500_ASMsg) {
            dsOrdPosnNum_L = ((NWAL1500_ASMsg) ezdMsg).dsOrdPosnNum_LC.getValue();
        } else if (ezdMsg instanceof NWAL1500_CCMsg) {
            dsOrdPosnNum_R = ((NWAL1500_CCMsg) ezdMsg).dsOrdPosnNum_RC.getValue();
        } else if (ezdMsg instanceof NWAL1500_CSMsg) {
            dsOrdPosnNum_R = ((NWAL1500_CSMsg) ezdMsg).dsOrdPosnNum_RC.getValue();
        }
        // 2018/01/25 S21_NA#19808 Mod End
        if (ZYPCommonFunc.hasValue(dsOrdPosnNum_L)) {
            for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
                if (dsOrdPosnNum_L.equals(bizMsg.G.no(i).dsOrdPosnNum_GS.getValue())) {
                    return true;
                }
            }
        }
        if (ZYPCommonFunc.hasValue(dsOrdPosnNum_R)) {
            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                if (dsOrdPosnNum_R.equals(bizMsg.H.no(i).dsOrdPosnNum_HS.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    // 2018/01/25 S21_NA#19808 Del Start
//    /**
//     * <pre>
//     * Search Sales Credit info from bizMsg.H related to configLineMsg
//     * </pre>
//     * @param bizMsg Business Message
//     * @param rmaConfigLineMsg target config
//     * @return true: exists false: not exists.
//     */
//    public static boolean isExistsSlsCr(NWAL1500CMsg bizMsg, NWAL1500_CCMsg rmaConfigLineMsg) {
//        String dsOrdPosnNum = rmaConfigLineMsg.dsOrdPosnNum_RC.getValue();
//        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
//            if (dsOrdPosnNum.equals(bizMsg.H.no(i).dsOrdPosnNum_HS.getValue())) {
//                return true;
//            }
//        }
//        return false;
//    }
    // 2018/01/25 S21_NA#19808 Mod End

    /**
     * <pre>
     * setup sales credit data from prime sales rep data.
     * delete new line.
     * set delete mode for already existing line.
     * set new line from prime sales rep data as new save.
     * @param bizMsg
     * </pre>
     */
    public static void editSlsCreditWithPrimeSlsRep(NWAL1500CMsg bizMsg) {
        // Header
        NWAL1500_FCMsg tmpFcMsg = new NWAL1500_FCMsg();
        setSlsCrDataFromPrimeSlsRep(bizMsg, tmpFcMsg);
        List<NWAL1500_FCMsg> hdrSlsCrList = new ArrayList<NWAL1500_FCMsg>();

        hdrSlsCrList.add(tmpFcMsg);

        for (int n = 0; n < bizMsg.F.getValidCount(); n++) {
            NWAL1500_FCMsg fcMsg = bizMsg.F.no(n);
            if (!ZYPCommonFunc.hasValue(fcMsg.dsCpoSlsCrPk_FS)) {
                continue;
            }
            tmpFcMsg = new NWAL1500_FCMsg();
            EZDMsg.copy(fcMsg, null, tmpFcMsg, null);
            // tmpFcMsg.xxRqstTpCd_FS.setValue(NWZC150001.DS_CPO_CANCEL);
            tmpFcMsg.xxRqstTpCd_FS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            hdrSlsCrList.add(tmpFcMsg);
        }
        bizMsg.F.clear();
        int n = 0;
        for (; n < hdrSlsCrList.size(); n++) {
            EZDMsg.copy(hdrSlsCrList.get(n), null, bizMsg.F.no(n), null);
        }
        bizMsg.F.setValidCount(n);

        // Line Config
        NWAL1500_GCMsg tmpGcMsg = new NWAL1500_GCMsg();
        EZDMsg.copy(bizMsg.F.no(0), "FS", tmpGcMsg, "GS");
        List<NWAL1500_GCMsg> linConfigSlsCrList = new ArrayList<NWAL1500_GCMsg>();
        linConfigSlsCrList.add(tmpGcMsg);
        for (n = 0; n < bizMsg.G.getValidCount(); n++) {
            NWAL1500_GCMsg gcMsg = bizMsg.G.no(n);
            if (!ZYPCommonFunc.hasValue(gcMsg.dsCpoSlsCrPk_GS)) {
                continue;
            }
            tmpGcMsg = new NWAL1500_GCMsg();
            EZDMsg.copy(gcMsg, null, tmpGcMsg, null);
            // tmpGcMsg.xxRqstTpCd_GS.setValue(NWZC150001.DS_CPO_CANCEL);
            tmpGcMsg.xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            linConfigSlsCrList.add(tmpGcMsg);
        }
        bizMsg.G.clear();
        for (n = 0; n < linConfigSlsCrList.size(); n++) {
            EZDMsg.copy(linConfigSlsCrList.get(n), null, bizMsg.G.no(n), null);
        }
        bizMsg.G.setValidCount(n);

        // RMA Config
        NWAL1500_HCMsg tmpHcMsg = new NWAL1500_HCMsg();
        EZDMsg.copy(bizMsg.F.no(0), "FS", tmpGcMsg, "HS");
        List<NWAL1500_HCMsg> rmaSlsCrList = new ArrayList<NWAL1500_HCMsg>();
        rmaSlsCrList.add(tmpHcMsg);
        for (n = 0; n < bizMsg.H.getValidCount(); n++) {
            NWAL1500_HCMsg hcMsg = bizMsg.H.no(n);
            if (!ZYPCommonFunc.hasValue(hcMsg.dsCpoSlsCrPk_HS)) {
                continue;
            }
            tmpHcMsg = new NWAL1500_HCMsg();
            EZDMsg.copy(hcMsg, null, tmpHcMsg, null);
            // tmpHcMsg.xxRqstTpCd_HS.setValue(NWZC150001.DS_CPO_CANCEL);
            tmpHcMsg.xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            rmaSlsCrList.add(tmpHcMsg);
        }
        bizMsg.H.clear();
        for (n = 0; n < rmaSlsCrList.size(); n++) {
            EZDMsg.copy(rmaSlsCrList.get(n), null, bizMsg.H.no(n), null);
        }
        bizMsg.H.setValidCount(n);
    }

    private static void copyHdrSlsCrToParamSlsCr(NWAL1500CMsg bizMsg) {
        bizMsg.O.clear();
        bizMsg.O.setValidCount(0);

        // S21_NA#8617 Mod Start
//        int i = 0;
//        for (; i < bizMsg.F.getValidCount(); i++) {
//                EZDMsg.copy(bizMsg.F.no(i), "FS", bizMsg.O.no(i), "O");
//                bizMsg.O.no(i).dsCpoSlsCrPk_O.clear();
//                bizMsg.O.no(i).dsOrdPosnNum_O.clear();
//                bizMsg.O.no(i).xxRqstTpCd_O.setValue(NWZC152001Constant.MODE_NEW);
//        }
//        bizMsg.O.setValidCount(i);

        int count = 0;
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            if (!NWZC152001Constant.MODE_DEL.equals(bizMsg.F.no(i).xxRqstTpCd_FS.getValue())) {

                EZDMsg.copy(bizMsg.F.no(i), "FS", bizMsg.O.no(count), "O");
                bizMsg.O.no(count).dsCpoSlsCrPk_O.clear();
                bizMsg.O.no(count).dsOrdPosnNum_O.clear();
                bizMsg.O.no(count).xxRqstTpCd_O.setValue(NWZC152001Constant.MODE_NEW);

                count++;
            }
        }
        bizMsg.O.setValidCount(count);
        // S21_NA#8617 Mod End
    }

    // 20-15/12/08 S21_NA#1550 Add Start
    private static boolean isHdrSlsCrAllDel(NWAL1500_FCMsgArray hdrSlsCrArray) {
        boolean isHdrSlsCrAllDel = true;
        for (int i = 0; i < hdrSlsCrArray.getValidCount(); i++) {
            String xxRqstTpCd = hdrSlsCrArray.no(i).xxRqstTpCd_FS.getValue();
            if (ZYPCommonFunc.hasValue(xxRqstTpCd) && !NWZC152001Constant.MODE_DEL.equals(xxRqstTpCd)) {
                isHdrSlsCrAllDel = false;
            } else if (!ZYPCommonFunc.hasValue(xxRqstTpCd)) {
                isHdrSlsCrAllDel = false; // New
            }
        }
        return isHdrSlsCrAllDel;
    }

    /**
     * create sales credit data (Line Config, RMA) as deleting data.
     * @param bizMsg Business Message
     */
    public static void allSlsCrInfoSetDeleteMode(NWAL1500CMsg bizMsg) {
        List<NWAL1500_GCMsg> lineConfigSlsCrMsgList = new ArrayList<NWAL1500_GCMsg>();
        for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.G.no(i).dsCpoSlsCrPk_GS)) {
                NWAL1500_GCMsg lineConfigSlsCrMsg = new NWAL1500_GCMsg();
                EZDMsg.copy(bizMsg.G.no(i), "GS", lineConfigSlsCrMsg, "GS");
                lineConfigSlsCrMsg.xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
                lineConfigSlsCrMsgList.add(lineConfigSlsCrMsg);
            }
        }
        bizMsg.G.clear();
        bizMsg.G.setValidCount(0);
        int slsCrConfCnt = 0;
        for (NWAL1500_GCMsg lineConfigSlsCrMsg : lineConfigSlsCrMsgList) {
            EZDMsg.copy(lineConfigSlsCrMsg, "GS", bizMsg.G.no(slsCrConfCnt), "GS");
            slsCrConfCnt++;
        }
        bizMsg.G.setValidCount(slsCrConfCnt);

        List<NWAL1500_HCMsg> rmaConfigSlsCrMsgList = new ArrayList<NWAL1500_HCMsg>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.H.no(i).dsCpoSlsCrPk_HS)) {
                NWAL1500_HCMsg rmaConfigSlsCrMsg = new NWAL1500_HCMsg();
                EZDMsg.copy(bizMsg.H.no(i), "HS", rmaConfigSlsCrMsg, "HS");
                rmaConfigSlsCrMsg.xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
                rmaConfigSlsCrMsgList.add(rmaConfigSlsCrMsg);
            }
        }
        bizMsg.H.clear();
        bizMsg.H.setValidCount(0);
        slsCrConfCnt = 0;
        for (NWAL1500_HCMsg rmaConfigSlsCrMsg : rmaConfigSlsCrMsgList) {
            EZDMsg.copy(rmaConfigSlsCrMsg, "HS", bizMsg.H.no(slsCrConfCnt), "HS");
            slsCrConfCnt++;
        }
        bizMsg.H.setValidCount(slsCrConfCnt);
    }
    // 20-15/12/08 S21_NA#1550 Add End
    /**
     * <pre>
     * check sales credit data setup.
     * If there is no Sales Credit data, create it from Primary Sales Rep.
     * If primary sales rep not equal to Sales Credit Writer, 
     * set up from primary sales rep and delete not DB set up date and
     * cancel DB set uped data.
     * S21_NA#1550 modify all logic.
     * </pre>
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    public static void checkAndSetSalesCredit(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg) {
        // Header has Sales Credit Data?
        boolean isHdrAllDelMode = isHdrSlsCrAllDel(bizMsg.F);
        if (bizMsg.F.getValidCount() == 0 || isHdrAllDelMode) {
            int currValCnt = bizMsg.F.getValidCount();
            setSlsCrDataFromPrimeSlsRep(bizMsg, bizMsg.F.no(currValCnt));
            bizMsg.F.setValidCount(currValCnt + 1);
            // copy to Line Config
//            int n = 0;
//            for (; n < bizMsg.A.getValidCount(); n++) {
//                NWAL1500_ACMsg acMsg = bizMsg.A.no(n);
//                EZDMsg.copy(hdrDefaultSlsCr, "FS", bizMsg.G.no(n), "GS");
//                bizMsg.G.no(n).dsOrdPosnNum_GS.setValue(acMsg.dsOrdPosnNum_LC.getValue());
//            }
//            bizMsg.G.setValidCount(n);

            // copy to RMA Config
//            for (n = 0; n < bizMsg.C.getValidCount(); n++) {
//                NWAL1500_CCMsg ccMsg = bizMsg.C.no(n);
//                EZDMsg.copy(hdrDefaultSlsCr, "FS", bizMsg.H.no(n), "HS");
//                bizMsg.H.no(n).dsOrdPosnNum_HS.setValue(ccMsg.dsOrdPosnNum_RC.getValue());
//            }
//            bizMsg.H.setValidCount(n);
        } else if (!isSamePrimeSlsRepAsWriter(bizMsg)) {
            editSlsCreditWithPrimeSlsRep(bizMsg);
        }
        // If there is a configu which doesn't have sales credit, copy from header.
        List<NWAL1500_FCMsg> hdrValidSlsCrList = new ArrayList<NWAL1500_FCMsg>(0);
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            if (!NWZC152001Constant.MODE_DEL.equals(bizMsg.F.no(i).xxRqstTpCd_FS.getValue())) {
                hdrValidSlsCrList.add(bizMsg.F.no(i));
            }
        }
        // Line Config
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            String dsOrdPosnNum = bizMsg.A.no(i).dsOrdPosnNum_LC.getValue();
            boolean srched = false;
            for (int j = 0; j < bizMsg.G.getValidCount(); j++) {
                String dsOrdPosnNumSlsCr = bizMsg.G.no(j).dsOrdPosnNum_GS.getValue();
                String xxRqstTpCd = bizMsg.G.no(j).xxRqstTpCd_GS.getValue();
                if (!ZYPCommonFunc.hasValue(xxRqstTpCd)) {
                    xxRqstTpCd = NWZC152001Constant.MODE_NEW;
                }
                if (dsOrdPosnNum.equals(dsOrdPosnNumSlsCr) && !NWZC152001Constant.MODE_DEL.equals(xxRqstTpCd)) {
                    srched = true;
                    break;
                }
            }
            if (!srched) {
                int slsCrCnt = bizMsg.G.getValidCount();
                for (NWAL1500_FCMsg hdrValidSlsCr : hdrValidSlsCrList) {
                    EZDMsg.copy(hdrValidSlsCr, "FS", bizMsg.G.no(slsCrCnt), "GS");
                    bizMsg.G.no(slsCrCnt).xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                    bizMsg.G.no(slsCrCnt).dsOrdPosnNum_GS.setValue(dsOrdPosnNum);
                    ZYPEZDItemValueSetter.setValue(bizMsg.G.no(slsCrCnt).dsCpoConfigPk_GS, bizMsg.A.no(i).dsCpoConfigPk_LC);
                    bizMsg.G.no(slsCrCnt).dsCpoSlsCrPk_GS.clear();
                    slsCrCnt++;
                }
                bizMsg.G.setValidCount(slsCrCnt);
            }
        }
        // RMA
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            String dsOrdPosnNum = bizMsg.C.no(i).dsOrdPosnNum_RC.getValue();
            boolean srched = false;
            for (int j = 0; j < bizMsg.H.getValidCount(); j++) {
                String dsOrdPosnNumSlsCr = bizMsg.H.no(j).dsOrdPosnNum_HS.getValue();
                String xxRqstTpCd = bizMsg.H.no(j).xxRqstTpCd_HS.getValue();
                if (!ZYPCommonFunc.hasValue(xxRqstTpCd)) {
                    xxRqstTpCd = NWZC152001Constant.MODE_NEW;
                }
                if (dsOrdPosnNum.equals(dsOrdPosnNumSlsCr) && !NWZC152001Constant.MODE_DEL.equals(xxRqstTpCd)) {
                    srched = true;
                    break;
                }
            }
            if (!srched) {
                int slsCrCnt = bizMsg.H.getValidCount();
                for (NWAL1500_FCMsg hdrValidSlsCr : hdrValidSlsCrList) {
                    EZDMsg.copy(hdrValidSlsCr, "FS", bizMsg.H.no(slsCrCnt), "HS");
                    bizMsg.H.no(slsCrCnt).xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                    bizMsg.H.no(slsCrCnt).dsOrdPosnNum_HS.setValue(dsOrdPosnNum);
                    ZYPEZDItemValueSetter.setValue(bizMsg.H.no(slsCrCnt).dsCpoConfigPk_HS, bizMsg.C.no(i).dsCpoConfigPk_RC);
                    bizMsg.H.no(slsCrCnt).dsCpoSlsCrPk_HS.clear();
                    slsCrCnt++;
                }
                bizMsg.H.setValidCount(slsCrCnt);
            }
        }
    }

    /**
     * <pre>
     * compare prime sales rep toc code and writer sales credt sales rep toc code.
     * @param bizMsg Business Message
     * @return true: prime sales rep is same as writer sales credit sales rep toc<br>
     * false: not same
     * </pre>
     */
    public static boolean isSamePrimeSlsRepAsWriter(NWAL1500CMsg bizMsg) {
        String primeSlsRepCd = bizMsg.slsRepTocCd.getValue();
        String writerSlsRepCd = null;
        for (int n = 0; n < bizMsg.F.getValidCount(); n++) {
            NWAL1500_FCMsg fcMsg = bizMsg.F.no(n);
            String slsRepRoleTpCd = fcMsg.slsRepRoleTpCd_FS.getValue();
            // 2017/11/02 S21_NA#20146 Mod Start
            //if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(slsRepRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(slsRepRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(slsRepRoleTpCd)) {
            if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(slsRepRoleTpCd) //
                    || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(slsRepRoleTpCd) //
                    // Add Start 2019/12/20 QC#53055
                    || LINE_BIZ_ROLE_TP.IS_WRITER.equals(slsRepRoleTpCd)
                    // Add End   2019/12/20 QC#53055
                    || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(slsRepRoleTpCd) //
                    || LINE_BIZ_ROLE_TP.EMSD_WRITER.equals(slsRepRoleTpCd)) {
            // 2017/11/02 S21_NA#20146 Mod End
                writerSlsRepCd = fcMsg.slsRepTocCd_FS.getValue();
                break;
            }
        }
        return primeSlsRepCd.equals(writerSlsRepCd);
    }

    private static NWAL1500_FCMsg setSlsCrDataFromPrimeSlsRep(NWAL1500CMsg bizMsg, NWAL1500_FCMsg fcMsg) {
        String lineBizTpCd = bizMsg.lineBizTpCd.getValue();
        String slsRepRoleTpCd = null;
        // 2017/11/02 S21_NA#20146 Mod Start
        //if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
        //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
        //} else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
        //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.LFS_WRITER;
        //} else {
        //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.PPS_WRITER;
        //}
        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.LFS_WRITER;
           // Add Start 2019/12/20 QC#53055 ISW check
           // When LINE_BIZ_TP = LFS and TRTY_GRP_TP_TXT = IS, Set "IS Writer".
           DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
           ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
           ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
           tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
           if (tMsg != null && NWAL1500Constant.TRTY_GRP_TP_IS.equals(tMsg.trtyGrpTpTxt.getValue())) {
               slsRepRoleTpCd = LINE_BIZ_ROLE_TP.IS_WRITER;
           }
           // Add End   2019/12/20 QC#53055
        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.PPS_WRITER;
        } else if (LINE_BIZ_TP.EMSD.equals(lineBizTpCd)) {
            // 2017/11/16 S21_NA#22620 Mod Start
            //String resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
            //if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
            //} else {
            //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.EMSD_WRITER;
            //}
            String fstBizCtxAttbTxt = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                slsRepRoleTpCd = fstBizCtxAttbTxt;
            }
            // 2017/11/16 S21_NA#22620 Mod End
        }
        // 2017/11/02 S21_NA#20146 Mod End
        ZYPEZDItemValueSetter.setValue(fcMsg.slsRepTocCd_FS, bizMsg.slsRepTocCd);
        // ZYPEZDItemValueSetter.setValue(fcMsg.xxRqstTpCd_FS, NWZC150001.DS_CPO_DTL_SAVE);
        ZYPEZDItemValueSetter.setValue(fcMsg.xxRqstTpCd_FS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
        ZYPEZDItemValueSetter.setValue(fcMsg.slsRepRoleTpCd_FS, slsRepRoleTpCd);
        ZYPEZDItemValueSetter.setValue(fcMsg.slsRepCrPct_FS, BigDecimal.valueOf(100));
        // ZYPEZDItemValueSetter.setValue(fcMsg.slsCrQuotFlg_FS, ZYPConstant.FLG_OFF_N); 2015/12/02 S21_NA#xxxx
        ZYPEZDItemValueSetter.setValue(fcMsg.slsCrQuotFlg_FS, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(fcMsg.lineBizRoleTpCd_FS, slsRepRoleTpCd); // S21NA#1673

        return fcMsg;
    }

    /**
     * <pre>
     * Create Default Sales Credit Info from input Sals Rep Toc Code.
     * call createDefaultHdrSlsCrInfo methos as reCreateFlg = false;
     * </pre>
     * @param bizMsg Business Message
     *//*
    public static void createDefaultHdrSlsCrInfo(NWAL1500CMsg bizMsg) {
        createDefaultHdrSlsCrInfo(bizMsg, false);
    }*/

    /**
     * <pre>
     * Create Default Sales Credit Info from input Sals Rep Toc Code.
     * </pre>
     * @param bizMsg Business Message
     * @param reCreateFlg <br>
     * true: recareate header sales credit info<br>
     * false: not recreate. If header sales credit info already exists, this method do nothing.
     */
/*    public static void createDefaultHdrSlsCrInfo(NWAL1500CMsg bizMsg, boolean reCreateFlg) {
        if (bizMsg.F.getValidCount() > 0 && !reCreateFlg) {
            return;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.slsRepTocCd)) {
            return;
        }
        if (reCreateFlg) {
            bizMsg.F.clear();
            bizMsg.F.setValidCount(0);
        }
        // Default Header
        NWAL1500_FCMsg fCMsg = bizMsg.F.no(0);

        fCMsg.xxRqstTpCd_FS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
        fCMsg.dsCpoSlsCrPk_FS.clear();
        fCMsg.dsOrdPosnNum_FS.clear();
        fCMsg.dsCpoConfigPk_FS.clear();
        ZYPEZDItemValueSetter.setValue(fCMsg.slsRepTocCd_FS, bizMsg.slsRepTocCd);

        String lineBizTpCd = bizMsg.lineBizTpCd.getValue();
        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
            ZYPEZDItemValueSetter.setValue(fCMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_WRITER);
            ZYPEZDItemValueSetter.setValue(fCMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_WRITER);
        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
            ZYPEZDItemValueSetter.setValue(fCMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.PPS_WRITER);
            ZYPEZDItemValueSetter.setValue(fCMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.PPS_WRITER);
        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
            ZYPEZDItemValueSetter.setValue(fCMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.LFS_WRITER);
            ZYPEZDItemValueSetter.setValue(fCMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.LFS_WRITER);
        }

        fCMsg.slsRepCrPct_FS.setValue(BigDecimal.valueOf(100));
        fCMsg.slsCrQuotFlg_FS.setValue(ZYPConstant.FLG_ON_Y);

        bizMsg.F.setValidCount(1);
    }*/
    /**
     * Delete Existing Sales Rep Info
     * @param bizMsg NWAL1500CMsg
     */
    public static void delExistingSlsRep(NWAL1500CMsg bizMsg) {

        // delete Header
        List<Integer> deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            NWAL1500_FCMsg hdrSlsRepMsg = bizMsg.F.no(i);

            if (ZYPCommonFunc.hasValue(hdrSlsRepMsg.dsCpoSlsCrPk_FS)) {
                ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.xxRqstTpCd_FS, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteRows.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.F, deleteRows);

        // delete Line
        deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
            NWAL1500_GCMsg lineSlsRepMsg = bizMsg.G.no(i);

            if (ZYPCommonFunc.hasValue(lineSlsRepMsg.dsCpoSlsCrPk_GS)) {
                ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.xxRqstTpCd_GS, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteRows.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.G, deleteRows);

        // delete RMA Line
        deleteRows = new ArrayList<Integer>();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            NWAL1500_HCMsg rmaLineSlsRepMsg = bizMsg.H.no(i);

            if (ZYPCommonFunc.hasValue(rmaLineSlsRepMsg.dsCpoSlsCrPk_HS)) {
                ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.xxRqstTpCd_HS, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteRows.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.H, deleteRows);
    }

    /**
     * Set New Sales Rep Info
     * @param bizMsg NWAL1500CMsg
     * @param slsRepPMsgList List<NMZC260001_defSlsRepListPMsg>
     */
    // 2017/03/09 S21_NA#16855 Mod Start
//    public static void setNewSlsRep(NWAL1500CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList) {
    public static void setNewSlsRep(NWAL1500CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList, List<String> targetWriterList) {
    // 2017/03/09 S21_NA#16855 Mod End

        // QC#17637 2017/03/01 Add Start
        String mode = getSalesCreditPrecentMode(slsRepPMsgList);
        BigDecimal writerPct = BigDecimal.ZERO;
        BigDecimal installerPct = BigDecimal.ZERO;
        if(S21StringUtil.isEquals("2",mode)){
            BigDecimal pct = ZYPCodeDataUtil.getNumConstValue(DEF_SLS_CR_PCT_WRITER, bizMsg.glblCmpyCd.getValue());
            if(pct != null){
                writerPct = pct;
                installerPct = PCT_100.subtract(writerPct);
            }
        }
        // QC#17637 2017/03/01 Add End
        // set Header
        int validCntForHdr = bizMsg.F.getValidCount();
        // 2017/12/12 S21_NA#19804(Sol#349) Mod Start
//        for (int i = 0; i < slsRepPMsgList.size(); i++) {
        for (int i = 0; i < slsRepPMsgList.size() && i < 10; i++) {
        // 2017/12/12 S21_NA#19804(Sol#349) Mod Start
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = slsRepPMsgList.get(i);

            NWAL1500_FCMsg hdrSlsRepMsg = bizMsg.F.no(validCntForHdr);
            ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.xxRqstTpCd_FS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepTocCd_FS, defSlsRepPMsg.tocCd);
            ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepRoleTpCd_FS, defSlsRepPMsg.lineBizRoleTpCd);
            ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.lineBizRoleTpCd_FS, defSlsRepPMsg.lineBizRoleTpCd);
            ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepRoleTpCd_FS, defSlsRepPMsg.lineBizRoleTpCd);
            ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.lineBizRoleTpCd_FS, defSlsRepPMsg.lineBizRoleTpCd);
            // Add Start 2019/12/20 QC#53055 ISW check
            // When LINE_BIZ_TP = LFS and TRTY_GRP_TP_TXT = IS, Set "IS Writer".
            if (LINE_BIZ_TP.LFS.equals(bizMsg.lineBizTpCd.getValue())) {
                DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
                tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
                if (tMsg != null && NWAL1500Constant.TRTY_GRP_TP_IS.equals(tMsg.trtyGrpTpTxt.getValue())) {
                    ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.IS_WRITER);
                    ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.IS_WRITER);
                }
            }
            // Add End   2019/12/20 QC#53055

            // QC#17727 2017/03/01 Mod Start
            // if (i == 0) {
            //     ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepCrPct_FS, PCT_100);
            // } else {
            //     ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepCrPct_FS, BigDecimal.ZERO);
            // }
            if (S21StringUtil.isEquals("1", mode)) {
                ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepCrPct_FS, PCT_100);
            } else if (S21StringUtil.isEquals("2", mode)) {
                // 2017/03/09 S21_NA#16855 Mod Start
//                if (isWriter(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                if (targetWriterList.contains(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepCrPct_FS, writerPct);
                } else {
                    ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepCrPct_FS, installerPct);
                }
                // 2017/03/09 S21_NA#16855 Mod End
            } else {
                ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsRepCrPct_FS, BigDecimal.ZERO);
            }
            // QC#17727 2017/03/01 Mod End
            // 2017/11/02 S21_NA#20146 Mod Start
            // 2016/03/30 S21_NA#5326 Mod Start
//            ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsCrQuotFlg_FS, ZYPConstant.FLG_OFF_N);
            //ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsCrQuotFlg_FS, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(hdrSlsRepMsg.slsCrQuotFlg_FS, defSlsRepPMsg.slsCrQuotFlg);
            // 2016/03/30 S21_NA#5326 Mod End
            // 2017/11/02 S21_NA#20146 Mod End
            validCntForHdr++;

        }
        bizMsg.F.setValidCount(validCntForHdr);

        // set Line
        int validCntForLine = bizMsg.G.getValidCount();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL1500_ACMsg confMsg = bizMsg.A.no(i);

            for (int j = 0; j < slsRepPMsgList.size(); j++) {
                NMZC260001_defSlsRepListPMsg defSlsRepPMsg = slsRepPMsgList.get(j);

                NWAL1500_GCMsg lineSlsRepMsg = bizMsg.G.no(validCntForLine);
                ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.xxRqstTpCd_GS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.dsOrdPosnNum_GS, confMsg.dsOrdPosnNum_LC);
                ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.dsCpoConfigPk_GS, confMsg.dsCpoConfigPk_LC);
                ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepTocCd_GS, defSlsRepPMsg.tocCd);
                ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepRoleTpCd_GS, defSlsRepPMsg.lineBizRoleTpCd);
                ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.lineBizRoleTpCd_GS, defSlsRepPMsg.lineBizRoleTpCd);
                // QC#17727 2017/03/01 Mod Start
                // if (j == 0) {
                //     ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, PCT_100);
                // } else {
                //     ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, BigDecimal.ZERO);
                // }
                if (S21StringUtil.isEquals("1", mode)) {
                    ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, PCT_100);
                } else if (S21StringUtil.isEquals("2", mode)) {
                    if (isWriter(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, writerPct);
                    } else {
                        ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, installerPct);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, BigDecimal.ZERO);
                }
                // QC#17727 2017/03/01 Mod End
                // 2017/11/02 S21_NA#20146 Mod Start
                // 2016/03/30 S21_NA#5326 Mod Start
//                ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsCrQuotFlg_GS, ZYPConstant.FLG_OFF_N);
                //ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsCrQuotFlg_GS, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsCrQuotFlg_GS, defSlsRepPMsg.slsCrQuotFlg);
                // 2016/03/30 S21_NA#5326 Mod End
                // 2017/11/02 S21_NA#20146 Mod End
                validCntForLine++;
            }
            bizMsg.G.setValidCount(validCntForLine);
        }

        // set RMA
        int validCntForRmaLine = bizMsg.H.getValidCount();
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL1500_CCMsg rmaConfMsg = bizMsg.C.no(i);

            for (int j = 0; j < slsRepPMsgList.size(); j++) {
                NMZC260001_defSlsRepListPMsg defSlsRepPMsg = slsRepPMsgList.get(j);

                NWAL1500_HCMsg rmaLineSlsRepMsg = bizMsg.H.no(validCntForRmaLine);
                ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.xxRqstTpCd_HS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.dsOrdPosnNum_HS, rmaConfMsg.dsOrdPosnNum_RC);
                ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.dsCpoConfigPk_HS, rmaConfMsg.dsCpoConfigPk_RC);
                ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepTocCd_HS, defSlsRepPMsg.tocCd);
                ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepRoleTpCd_HS, defSlsRepPMsg.lineBizRoleTpCd);
                ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.lineBizRoleTpCd_HS, defSlsRepPMsg.lineBizRoleTpCd);
                // QC#17727 2017/03/01 Mod Start
                // if (j == 0) {
                //     ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, PCT_100);
                // } else {
                //     ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, BigDecimal.ZERO);
                // }
                if (S21StringUtil.isEquals("1", mode)) {
                    ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, PCT_100);
                } else if (S21StringUtil.isEquals("2", mode)) {
                    if (isWriter(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, writerPct);
                    } else {
                        ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, installerPct);
                    }
                } else {
                    ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, BigDecimal.ZERO);
                }
                // QC#17727 2017/03/01 Mod End
                // 2017/11/02 S21_NA#20146 Mod Start
                // 2016/03/30 S21_NA#5326 Mod Start
//                ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsCrQuotFlg_HS, ZYPConstant.FLG_OFF_N);
                //ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsCrQuotFlg_HS, ZYPConstant.FLG_ON_Y);
                ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsCrQuotFlg_HS, defSlsRepPMsg.slsCrQuotFlg);
                // 2016/03/30 S21_NA#5326 Mod End
                // 2017/11/02 S21_NA#20146 Mod End
                validCntForRmaLine++;
            }
            bizMsg.H.setValidCount(validCntForRmaLine);
        }
    }
    // QC#17637 2017/03/01 Add Start
    private static String getSalesCreditPrecentMode(List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList){

        boolean hasWriter = false;
        boolean hasInstaller = false;
        if (slsRepPMsgList.size() == 1) {
            return "1";
        } else if (slsRepPMsgList.size() == 2) {
            for (NMZC260001_defSlsRepListPMsg data : slsRepPMsgList) {
                if (isWriter(data.lineBizRoleTpCd.getValue())) {
                    hasWriter = true;
                } else if (isInstaller(data.lineBizRoleTpCd.getValue())) {
                    hasInstaller = true;
                }
            }
            if (hasWriter && hasInstaller) {
                return "2";
            }
        }
        return "0";
    }

    /**
     * isWriter
     * @param slsRepRoleTpCd
     * @return
     */
    private static boolean isWriter(String slsRepRoleTpCd){
        // 2017/11/02 S21_NA#20146 Mod Start
        //List<String> writerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER);
        List<String> writerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_WRITER, LINE_BIZ_ROLE_TP.LFS_WRITER, LINE_BIZ_ROLE_TP.PPS_WRITER, LINE_BIZ_ROLE_TP.EMSD_WRITER);
        // 2017/11/02 S21_NA#20146 Mod End
        return writerList.contains(slsRepRoleTpCd);
    }

    /**
     * isInstaller
     * @param slsRepRoleTpCd
     * @return
     */
    private static boolean isInstaller(String slsRepRoleTpCd){
        List<String> installerList = Arrays.asList(LINE_BIZ_ROLE_TP.ESS_INSTALLER, LINE_BIZ_ROLE_TP.LFS_INSTALLER, LINE_BIZ_ROLE_TP.PPS_INSTALLER);
        return installerList.contains(slsRepRoleTpCd);
    }
    // QC#17637 2017/03/01 Add End

    // S21_NA#15889 Add Start
    /**
     * Set New Sales Rep Info For Config
     * @param bizMsg NWAL1500CMsg
     * @param slsRepPMsgList List<NMZC260001_defSlsRepListPMsg>
     * @param confPosnNum Config Position Number
     * @param isCalledLineConfig Called Line Config Tab
     */
    public static void setNewSlsRepForConfig(NWAL1500CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList, String confPosnNum, boolean isCalledLineConfig) {

        // QC#17637 2017/03/01 Add Start
        String mode = getSalesCreditPrecentMode(slsRepPMsgList);
        BigDecimal writerPct = BigDecimal.ZERO;
        BigDecimal installerPct = BigDecimal.ZERO;
        if(S21StringUtil.isEquals("2",mode)){
            BigDecimal pct = ZYPCodeDataUtil.getNumConstValue(DEF_SLS_CR_PCT_WRITER, bizMsg.glblCmpyCd.getValue());
            if(pct != null){
                writerPct = pct;
                installerPct = PCT_100.subtract(writerPct);
            }
        }
        // QC#17637 2017/03/01 Add End
        if (isCalledLineConfig) {
            
            // set Line
            int validCntForLine = bizMsg.G.getValidCount();
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                NWAL1500_ACMsg confMsg = bizMsg.A.no(i);

                if (!confPosnNum.equals(confMsg.dsOrdPosnNum_LC.getValue())) {
                    continue;
                }

                for (int j = 0; j < slsRepPMsgList.size(); j++) {
                    NMZC260001_defSlsRepListPMsg defSlsRepPMsg = slsRepPMsgList.get(j);

                    NWAL1500_GCMsg lineSlsRepMsg = bizMsg.G.no(validCntForLine);
                    ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.xxRqstTpCd_GS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                    ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.dsOrdPosnNum_GS, confMsg.dsOrdPosnNum_LC);
                    ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.dsCpoConfigPk_GS, confMsg.dsCpoConfigPk_LC);
                    ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepTocCd_GS, defSlsRepPMsg.tocCd);
                    ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepRoleTpCd_GS, defSlsRepPMsg.lineBizRoleTpCd);
                    ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.lineBizRoleTpCd_GS, defSlsRepPMsg.lineBizRoleTpCd);
                    // QC#17727 2017/03/01 Mod Start
                    // if (j == 0) {
                    //     ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, PCT_100);
                    // } else {
                    //     ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, BigDecimal.ZERO);
                    // }
                    if (S21StringUtil.isEquals("1", mode)) {
                        ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, PCT_100);
                    } else if (S21StringUtil.isEquals("2", mode)) {
                        if (isWriter(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, writerPct);
                        } else {
                            ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, installerPct);
                        }
                    } else {
                        ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsRepCrPct_GS, BigDecimal.ZERO);
                    }
                    // QC#17727 2017/03/01 Mod End
                    // 2017/11/02 S21_NA#20146 Mod Start
                    //ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsCrQuotFlg_GS, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(lineSlsRepMsg.slsCrQuotFlg_GS, defSlsRepPMsg.slsCrQuotFlg);
                    // 2017/11/02 S21_NA#20146 Mod End
                    validCntForLine++;
                }
                bizMsg.G.setValidCount(validCntForLine);
                break;
            }

        } else {
            // set RMA
            int validCntForRmaLine = bizMsg.H.getValidCount();
            for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
                NWAL1500_CCMsg rmaConfMsg = bizMsg.C.no(i);

                if (!confPosnNum.equals(rmaConfMsg.dsOrdPosnNum_RC.getValue())) {
                    continue;
                }

                for (int j = 0; j < slsRepPMsgList.size(); j++) {
                    NMZC260001_defSlsRepListPMsg defSlsRepPMsg = slsRepPMsgList.get(j);

                    NWAL1500_HCMsg rmaLineSlsRepMsg = bizMsg.H.no(validCntForRmaLine);
                    ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.xxRqstTpCd_HS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                    ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.dsOrdPosnNum_HS, rmaConfMsg.dsOrdPosnNum_RC);
                    ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.dsCpoConfigPk_HS, rmaConfMsg.dsCpoConfigPk_RC);
                    ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepTocCd_HS, defSlsRepPMsg.tocCd);
                    ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepRoleTpCd_HS, defSlsRepPMsg.lineBizRoleTpCd);
                    ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.lineBizRoleTpCd_HS, defSlsRepPMsg.lineBizRoleTpCd);
                    // QC#17727 2017/03/01 Mod Start
                    // if (j == 0) {
                    //     ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, PCT_100);
                    // } else {
                    //     ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, BigDecimal.ZERO);
                    // }
                    if (S21StringUtil.isEquals("1", mode)) {
                        ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, PCT_100);
                    } else if (S21StringUtil.isEquals("2", mode)) {
                        if (isWriter(defSlsRepPMsg.lineBizRoleTpCd.getValue())) {
                            ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, writerPct);
                        } else {
                            ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, installerPct);
                        }
                    } else {
                        ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsRepCrPct_HS, BigDecimal.ZERO);
                    }
                    // 2017/11/02 S21_NA#20146 Mod Start
                    //ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsCrQuotFlg_HS, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(rmaLineSlsRepMsg.slsCrQuotFlg_HS, defSlsRepPMsg.slsCrQuotFlg);
                    // 2017/11/02 S21_NA#20146 Mod End
                    validCntForRmaLine++;
                }
                bizMsg.H.setValidCount(validCntForRmaLine);
                break;
            }
        }
    }
    // S21_NA#15889 Add End

    /**
     * Get Sales Rep Code
     * @param bizMsg NWAL1500CMsg
     * @return Sales Rep Code
     */
    /**
     * Get Sales Rep Code
     * @param bizMsg Business Message
     * @param isCallName true: call by name changing false: call by person number changing
     * @param msgParm message parameter
     * @return sales rep TOC Code (null: if error will be occurred)
     */
    @SuppressWarnings("unchecked")
    public static String getSlsRepCd(NWAL1500CMsg bizMsg, boolean isCallName, String msgParm) {

        S21SsmEZDResult ssmResult = NWAL1500Query.getInstance().getSlsRepInfoList(bizMsg, isCallName);

        if (ssmResult.isCodeNotFound()) {
            if (isCallName) {
                bizMsg.slsRepTocCd.clear();
                bizMsg.slsRepTocNm.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            } else {
                bizMsg.slsRepTocCd.clear();
                bizMsg.psnNum.setErrorInfo(1, NWAM0181E, new String[] {msgParm }); // 2016/05/11 S21_NA#7861 Mod slsRepPsnCd -> psnNum
            }

            return null;
        }

        List<Map<String, String>> slsRepInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (slsRepInfoList.size() != 1) {
            if (isCallName) {
                bizMsg.psnNum.clear(); // 2016/05/11 S21_NA#7861 Mod slsRepPsnCd -> psnNum
                bizMsg.slsRepTocCd.clear();
            } else {
                bizMsg.slsRepTocNm.clear();
                bizMsg.slsRepTocCd.clear();
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        Map<String, String> slsRepInfo = slsRepInfoList.get(0);
        String slsRepTocCd = slsRepInfo.get("TOC_CD");
        ZYPEZDItemValueSetter.setValue(bizMsg.psnNum, slsRepInfo.get("PSN_NUM")); // 2016/05/11 S21_NA#7861 Mod slsRepPsnCd -> psnNum
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm, slsRepInfo.get("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, slsRepInfo.get("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, slsRepInfo.get("COA_BR_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, slsRepInfo.get("COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt, slsRepInfo.get("COA_EXTN_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, slsRepInfo.get("COA_BR_ITEM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, slsRepInfo.get("COA_EXTN_ITEM"));

        return slsRepTocCd;
    }

    /**
     * Delete All Sales Credit Information
     * @param bizMsg NWAL1500CMsg
     */
    public static void delAllSlsCreditInfo(NWAL1500CMsg bizMsg) {

        List<Integer> deleteList = new ArrayList<Integer>();

        // For Header
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            NWAL1500_FCMsg hdrSlsCreditMsg = bizMsg.F.no(i);

            if (ZYPCommonFunc.hasValue(hdrSlsCreditMsg.dsCpoSlsCrPk_FS)) {
                ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.xxRqstTpCd_FS, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.F, deleteList);

        // For Line Config
        deleteList.clear();
        for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
            NWAL1500_GCMsg lineSlsCreditMsg = bizMsg.G.no(i);

            if (ZYPCommonFunc.hasValue(lineSlsCreditMsg.dsCpoSlsCrPk_GS)) {
                ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.xxRqstTpCd_GS, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.G, deleteList);

        // For RMA Config
        deleteList.clear();
        for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
            NWAL1500_HCMsg rmaSlsCreditMsg = bizMsg.H.no(i);

            if (ZYPCommonFunc.hasValue(rmaSlsCreditMsg.dsCpoSlsCrPk_HS)) {
                ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.xxRqstTpCd_HS, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.H, deleteList);
    }

    // S21_NA#15889 Add Start
    /**
     * Delete All Sales Credit Information For Config
     * @param bizMsg NWAL1500CMsg
     * @param confPosnNum Config Position Number
     * @param isCalledLineConfig Called Line Config Tab
     */
    public static void delAllSlsCreditInfoForConfig(NWAL1500CMsg bizMsg, String confPosnNum, boolean isCalledLineConfig) {

        List<Integer> deleteList = new ArrayList<Integer>();

        if (isCalledLineConfig) {
            // For Line Config
            for (int i = 0; i < bizMsg.G.getValidCount(); i++) {
                NWAL1500_GCMsg lineSlsCreditMsg = bizMsg.G.no(i);

                if (!confPosnNum.equals(lineSlsCreditMsg.dsOrdPosnNum_GS.getValue())) {
                    continue;
                }

                if (ZYPCommonFunc.hasValue(lineSlsCreditMsg.dsCpoSlsCrPk_GS)) {
                    ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.xxRqstTpCd_GS, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
                } else {
                    deleteList.add(i);
                }
            }
            ZYPTableUtil.deleteRows(bizMsg.G, deleteList);

        } else {
            // For RMA Config
            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {
                NWAL1500_HCMsg rmaSlsCreditMsg = bizMsg.H.no(i);

                if (!confPosnNum.equals(rmaSlsCreditMsg.dsOrdPosnNum_HS.getValue())) {
                    continue;
                }

                if (ZYPCommonFunc.hasValue(rmaSlsCreditMsg.dsCpoSlsCrPk_HS)) {
                    ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.xxRqstTpCd_HS, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
                } else {
                    deleteList.add(i);
                }
            }
            ZYPTableUtil.deleteRows(bizMsg.H, deleteList);
        }
    }
    // S21_NA#15889 Add End

    /**
     * Set Writer Sales Credit Information
     * @param bizMsg NWAL1500CMsg
     * @param writerSlsRepTocCd Write Sales Rep Toc Code
     */
    public static void setWriterSlsCreditInfo(NWAL1500CMsg bizMsg, NWAL1500SMsg glblMsg, String writerSlsRepTocCd) {

        String lineBizTpCd = bizMsg.lineBizTpCd.getValue();
        String slsRepRoleTpCd = null;
        // 2017/11/02 S21_NA#20146 Mod Start
        //if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
        //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
        //} else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
        //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.LFS_WRITER;
        //} else {
        //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.PPS_WRITER;
        //}
        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.LFS_WRITER;
           // Add Start 2019/12/20 QC#53055 ISW check
           // When LINE_BIZ_TP = LFS and TRTY_GRP_TP_TXT = IS, Set "IS Writer".
           DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
           ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
           ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
           tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
           if (tMsg != null && NWAL1500Constant.TRTY_GRP_TP_IS.equals(tMsg.trtyGrpTpTxt.getValue())) {
               slsRepRoleTpCd = LINE_BIZ_ROLE_TP.IS_WRITER;
           }
           // Add End   2019/12/20 QC#53055
        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.PPS_WRITER;
        } else if (LINE_BIZ_TP.EMSD.equals(lineBizTpCd)) {
            // 2017/11/16 S21_NA#22620 Mod Start
            //String resultFlg = NWAL1500Query.getInstance().getOrdCatgBizCtx(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
            //if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
            //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
            //} else {
            //    slsRepRoleTpCd = LINE_BIZ_ROLE_TP.EMSD_WRITER;
            //}
            String fstBizCtxAttbTxt = NWAL1500Query.getInstance().getOrdCatgBizCtxFstAttbTxt(bizMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, bizMsg.dsOrdCatgCd.getValue(), bizMsg.dsOrdTpCd.getValue());
            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                slsRepRoleTpCd = fstBizCtxAttbTxt;
            }
            // 2017/11/16 S21_NA#22620 Mod End
        }
        // 2017/11/02 S21_NA#20146 Mod End

        // For Header
        NWAL1500_FCMsg hdrSlsCreditMsg = bizMsg.F.no(bizMsg.F.getValidCount());
        ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.slsRepTocCd_FS, writerSlsRepTocCd);
        ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.xxRqstTpCd_FS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
        ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.slsRepRoleTpCd_FS, slsRepRoleTpCd);
        ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.slsRepCrPct_FS, PCT_100);
        ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.slsCrQuotFlg_FS, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(hdrSlsCreditMsg.lineBizRoleTpCd_FS, slsRepRoleTpCd);
        bizMsg.F.setValidCount(bizMsg.F.getValidCount() + 1);

        // For Line Config
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            NWAL1500_GCMsg lineSlsCreditMsg = bizMsg.G.no(bizMsg.G.getValidCount());
            ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.dsOrdPosnNum_GS, glblMsg.A.no(i).dsOrdPosnNum_LC);
            ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.slsRepTocCd_GS, writerSlsRepTocCd);
            ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.xxRqstTpCd_GS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.slsRepRoleTpCd_GS, slsRepRoleTpCd);
            ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.slsRepCrPct_GS, PCT_100);
            ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.slsCrQuotFlg_GS, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(lineSlsCreditMsg.lineBizRoleTpCd_GS, slsRepRoleTpCd);
            bizMsg.G.setValidCount(bizMsg.G.getValidCount() + 1);
        }

        // For RMA Config
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            NWAL1500_HCMsg rmaSlsCreditMsg = bizMsg.H.no(bizMsg.H.getValidCount());
            ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.dsOrdPosnNum_HS, glblMsg.C.no(i).dsOrdPosnNum_RC);
            ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.slsRepTocCd_HS, writerSlsRepTocCd);
            ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.xxRqstTpCd_HS, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.slsRepRoleTpCd_HS, slsRepRoleTpCd);
            ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.slsRepCrPct_HS, PCT_100);
            ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.slsCrQuotFlg_HS, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(rmaSlsCreditMsg.lineBizRoleTpCd_HS, slsRepRoleTpCd);
            bizMsg.H.setValidCount(bizMsg.H.getValidCount() + 1);
        }
    }

    /**
     * getSalesCreditQuoteCount S21_NA#5000#64
     * @param bizMsg NWAL1500CMsg
     * @param dsOrdPosnNum String
     * @param xxDplyTab String
     * @return SalesCreditQuoteCount
     */
    public static int getSalesCreditQuoteCount(NWAL1500CMsg bizMsg, String dsOrdPosnNum, String xxDplyTab) {

        int salesCreditQuoteCount = 0;
        if (TAB_LINE_CONFIG.equals(xxDplyTab)) {

            // for line config
            for (int i = 0; i < bizMsg.G.getValidCount(); i++) {

                if (!S21StringUtil.isEquals(bizMsg.G.no(i).dsOrdPosnNum_GS.getValue(), dsOrdPosnNum)) {

                    continue;
                }
                if (S21StringUtil.isEquals(bizMsg.G.no(i).xxRqstTpCd_GS.getValue(), NWZC150001Constant.RQST_TP_SLS_CR_DELETE)) {

                    continue;
                }
                if (!S21StringUtil.isEquals(bizMsg.G.no(i).slsCrQuotFlg_GS.getValue(), ZYPConstant.FLG_ON_Y)) {

                    continue;
                }
                salesCreditQuoteCount++;
            }
            return salesCreditQuoteCount;
        } else if (TAB_RMA.equals(xxDplyTab)) {

            // for RMA
            for (int i = 0; i < bizMsg.H.getValidCount(); i++) {

                if (!S21StringUtil.isEquals(bizMsg.H.no(i).dsOrdPosnNum_HS.getValue(), dsOrdPosnNum)) {

                    continue;
                }
                if (S21StringUtil.isEquals(bizMsg.H.no(i).xxRqstTpCd_HS.getValue(), NWZC150001Constant.RQST_TP_SLS_CR_DELETE)) {

                    continue;
                }
                if (!S21StringUtil.isEquals(bizMsg.H.no(i).slsCrQuotFlg_HS.getValue(), ZYPConstant.FLG_ON_Y)) {

                    continue;
                }
                salesCreditQuoteCount++;
            }
            return salesCreditQuoteCount;
        } else {

            // for header
            for (int i = 0; i < bizMsg.F.getValidCount(); i++) {

                if (!S21StringUtil.isEquals(bizMsg.F.no(i).dsOrdPosnNum_FS.getValue(), dsOrdPosnNum)) {

                    continue;
                }
                if (S21StringUtil.isEquals(bizMsg.F.no(i).xxRqstTpCd_FS.getValue(), NWZC150001Constant.RQST_TP_SLS_CR_DELETE)) {

                    continue;
                }
                if (!S21StringUtil.isEquals(bizMsg.F.no(i).slsCrQuotFlg_FS.getValue(), ZYPConstant.FLG_ON_Y)) {

                    continue;
                }
                salesCreditQuoteCount++;
            }
            return salesCreditQuoteCount;
        }
    }

    // 2016/05/24 S21_NA#8617 Add Start
    /**
     * <pre>
     * Copy Sales Credit Information from popup date.
     * If there is same biz role type and same sales rep toc code date between popup data and current config data,
     * set the sales credit data for modification.
     * @param bizMsg Business Message
     * @param configLineMsg Line Config Message
     * </pre>
     */
    public static void copyParamSlsCrInfoFromModifyHeader(NWAL1500CMsg bizMsg, NWAL1500_ACMsg configLineMsg) {

      String dsOrdPosnNum = configLineMsg.dsOrdPosnNum_LC.getValue();
      BigDecimal dsCpoConfigPk = configLineMsg.dsCpoConfigPk_LC.getValue();

      List<NWAL1500_GCMsg> targetLineConfigSlsCrList = deleteConfigSlsCrInfo(bizMsg, configLineMsg);
      for (NWAL1500_GCMsg configSlsCr : targetLineConfigSlsCrList) {
          boolean srched = false;
          BigDecimal dsCpoSlsCrPk = configSlsCr.dsCpoSlsCrPk_GS.getValue();

          int prmCnt = 0;
          for (; prmCnt < bizMsg.O.getValidCount(); prmCnt++) {
              NWAL1500_OCMsg prmMsg = bizMsg.O.no(prmCnt);
              String slsCrMode = prmMsg.xxRqstTpCd_O.getValue();
              if (NWAL1600Constant.REQ_DEL.equals(slsCrMode)) {
                  continue;
              }
              if (S21StringUtil.isEquals(prmMsg.lineBizRoleTpCd_O.getValue(), configSlsCr.lineBizRoleTpCd_GS.getValue())//
                      && S21StringUtil.isEquals(prmMsg.slsRepTocCd_O.getValue(), configSlsCr.slsRepTocCd_GS.getValue())) {
                  srched = true;
                  break;
              }
          }

          if (srched) {
              EZDMsg.copy(bizMsg.O.no(prmCnt), "O", configSlsCr, "GS");
              ZYPEZDItemValueSetter.setValue(configSlsCr.dsOrdPosnNum_GS, dsOrdPosnNum);
              ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoConfigPk_GS, dsCpoConfigPk);
              ZYPEZDItemValueSetter.setValue(configSlsCr.slsRepRoleTpCd_GS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);

              configSlsCr.xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_MODIFY);
              ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoSlsCrPk_GS, dsCpoSlsCrPk);
          } else {
              // Delete Check
              configSlsCr.xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
          }
      }
      // Add New Data from Header
      for (int prmCnt = 0; prmCnt < bizMsg.O.getValidCount(); prmCnt++) {
          // 2016/05/30 S21_NA#8252 Mod Start
//          BigDecimal resultDsCpoSlsCrPk = bizMsg.O.no(prmCnt).dsCpoSlsCrPk_O.getValue();
//          if (resultDsCpoSlsCrPk == null) {
//              NWAL1500_GCMsg configSlsCr = new NWAL1500_GCMsg();
//              EZDMsg.copy(bizMsg.O.no(prmCnt), "O", configSlsCr, "GS");
//              ZYPEZDItemValueSetter.setValue(configSlsCr.dsOrdPosnNum_GS, dsOrdPosnNum);
//              ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoConfigPk_GS, dsCpoConfigPk);
//              ZYPEZDItemValueSetter.setValue(configSlsCr.slsRepRoleTpCd_GS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);
//              configSlsCr.xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
//              targetLineConfigSlsCrList.add(configSlsCr);
//          }

          // 2017/10/23 S21_NA#21768 Add Start
          if (S21StringUtil.isEquals(bizMsg.O.no(prmCnt).xxRqstTpCd_O.getValue(), NWAL1600Constant.REQ_DEL)) {
              continue;
          }
          // 2017/10/23 S21_NA#21768 Add End

          String tocCd = bizMsg.O.no(prmCnt).slsRepTocCd_O.getValue();
          String roleTpCd = bizMsg.O.no(prmCnt).lineBizRoleTpCd_O.getValue();
          boolean srched = false;

          for (NWAL1500_GCMsg configSlsCr : targetLineConfigSlsCrList) {
              String curTocCd = configSlsCr.slsRepTocCd_GS.getValue();
              String curRoleTpCd = configSlsCr.lineBizRoleTpCd_GS.getValue();
              if (S21StringUtil.isEquals(tocCd, curTocCd) //
                      && S21StringUtil.isEquals(roleTpCd, curRoleTpCd)) {
                  srched = true;
              }
          }
          if (!srched) {
                NWAL1500_GCMsg configSlsCr = new NWAL1500_GCMsg();
                EZDMsg.copy(bizMsg.O.no(prmCnt), "O", configSlsCr, "GS");
                ZYPEZDItemValueSetter.setValue(configSlsCr.dsOrdPosnNum_GS, dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoConfigPk_GS, dsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(configSlsCr.slsRepRoleTpCd_GS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);
                configSlsCr.xxRqstTpCd_GS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                configSlsCr.dsCpoSlsCrPk_GS.clear();
                targetLineConfigSlsCrList.add(configSlsCr);
          }
          // 2016/05/30 S21_NA#8252 Mod End
      }
      int configCnt = bizMsg.G.getValidCount();
      for (NWAL1500_GCMsg configSlsCr : targetLineConfigSlsCrList) {
          EZDMsg.copy(configSlsCr, null, bizMsg.G.no(configCnt), null);
          configCnt++;
      }
      bizMsg.G.setValidCount(configCnt);
    }

    /**
     * <pre>
     * Copy Sales Credit Information from popup date.
     * If there is same biz role type and same sales rep toc code date between popup data and current config data,
     * set the sales credit data for modification.
     * @param bizMsg Business Message
     * @param rmaConfigMsg Line Config Message
     * </pre>
     */
    public static void copyParamSlsCrInfoFromModifyHeader(NWAL1500CMsg bizMsg, NWAL1500_CCMsg rmaConfigMsg) {

      String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();
      BigDecimal dsCpoConfigPk = rmaConfigMsg.dsCpoConfigPk_RC.getValue();

      List<NWAL1500_HCMsg> targetRmaConfigSlsCrList = deleteConfigSlsCrInfo(bizMsg, rmaConfigMsg);
      for (NWAL1500_HCMsg configSlsCr : targetRmaConfigSlsCrList) {
          boolean srched = false;
          BigDecimal dsCpoSlsCrPk = configSlsCr.dsCpoSlsCrPk_HS.getValue();

          int prmCnt = 0;
          for (; prmCnt < bizMsg.O.getValidCount(); prmCnt++) {
              NWAL1500_OCMsg prmMsg = bizMsg.O.no(prmCnt);
              String slsCrMode = prmMsg.xxRqstTpCd_O.getValue();
              if (NWAL1600Constant.REQ_DEL.equals(slsCrMode)) {
                  continue;
              }
              if (S21StringUtil.isEquals(prmMsg.lineBizRoleTpCd_O.getValue(), configSlsCr.lineBizRoleTpCd_HS.getValue())//
                      && S21StringUtil.isEquals(prmMsg.slsRepTocCd_O.getValue(), configSlsCr.slsRepTocCd_HS.getValue())) {
                  srched = true;
                  break;
              }
          }

          if (srched) {
              EZDMsg.copy(bizMsg.O.no(prmCnt), "O", configSlsCr, "HS");
              ZYPEZDItemValueSetter.setValue(configSlsCr.dsOrdPosnNum_HS, dsOrdPosnNum);
              ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoConfigPk_HS, dsCpoConfigPk);
              ZYPEZDItemValueSetter.setValue(configSlsCr.slsRepRoleTpCd_HS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);

              configSlsCr.xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_MODIFY);
              ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoSlsCrPk_HS, dsCpoSlsCrPk);
          } else {
              // Delete Check
              configSlsCr.xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
          }
      }
      // Add New Data from Header
      for (int prmCnt = 0; prmCnt < bizMsg.O.getValidCount(); prmCnt++) {
          // 2016/05/30 S21_NA#8252 Mod Start
//          BigDecimal resultDsCpoSlsCrPk = bizMsg.O.no(prmCnt).dsCpoSlsCrPk_O.getValue();
//          if (resultDsCpoSlsCrPk == null) {
//              NWAL1500_HCMsg configSlsCr = new NWAL1500_HCMsg();
//              EZDMsg.copy(bizMsg.O.no(prmCnt), "O", configSlsCr, "HS");
//              ZYPEZDItemValueSetter.setValue(configSlsCr.dsOrdPosnNum_HS, dsOrdPosnNum);
//              ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoConfigPk_HS, dsCpoConfigPk);
//              ZYPEZDItemValueSetter.setValue(configSlsCr.slsRepRoleTpCd_HS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);
//              configSlsCr.xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
//              targetRmaConfigSlsCrList.add(configSlsCr);
//          }

          // 2017/10/23 S21_NA#21768 Add Start
          if (S21StringUtil.isEquals(bizMsg.O.no(prmCnt).xxRqstTpCd_O.getValue(), NWAL1600Constant.REQ_DEL)) {
              continue;
          }
          // 2017/10/23 S21_NA#21768 Add End

          String tocCd = bizMsg.O.no(prmCnt).slsRepTocCd_O.getValue();
          String roleTpCd = bizMsg.O.no(prmCnt).lineBizRoleTpCd_O.getValue();
          boolean srched = false;

          for (NWAL1500_HCMsg configSlsCr : targetRmaConfigSlsCrList) {
              String curTocCd = configSlsCr.slsRepTocCd_HS.getValue();
              String curRoleTpCd = configSlsCr.lineBizRoleTpCd_HS.getValue();
              if (S21StringUtil.isEquals(tocCd, curTocCd) //
                      && S21StringUtil.isEquals(roleTpCd, curRoleTpCd)) {
                  srched = true;
              }
          }
          if (!srched) {
                NWAL1500_HCMsg configSlsCr = new NWAL1500_HCMsg();
                EZDMsg.copy(bizMsg.O.no(prmCnt), "O", configSlsCr, "HS");
                ZYPEZDItemValueSetter.setValue(configSlsCr.dsOrdPosnNum_HS, dsOrdPosnNum);
                ZYPEZDItemValueSetter.setValue(configSlsCr.dsCpoConfigPk_HS, dsCpoConfigPk);
                ZYPEZDItemValueSetter.setValue(configSlsCr.slsRepRoleTpCd_HS, bizMsg.O.no(prmCnt).lineBizRoleTpCd_O);
                configSlsCr.xxRqstTpCd_HS.setValue(NWZC150001Constant.RQST_TP_SLS_CR_NEW);
                configSlsCr.dsCpoSlsCrPk_HS.clear();
                targetRmaConfigSlsCrList.add(configSlsCr);
          }
          // 2016/05/30 S21_NA#8252 Mod End
      }
      int configCnt = bizMsg.H.getValidCount();
      for (NWAL1500_HCMsg configSlsCr : targetRmaConfigSlsCrList) {
          EZDMsg.copy(configSlsCr, null, bizMsg.H.no(configCnt), null);
          configCnt++;
      }
      bizMsg.H.setValidCount(configCnt);
    }
    // 2016/05/24 S21_NA#8617 Add End

    // 2017/11/15 S21_NA#22604 Add Start
    public static boolean isExistQuoteRep(NWAL1500CMsg bizMsg) {
        for (int i = 0; i < bizMsg.F.getValidCount(); i++) {
            NWAL1500_FCMsg hdrSlsCreditMsg = bizMsg.F.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(hdrSlsCreditMsg.slsCrQuotFlg_FS.getValue())) {
                return true;
            }
        }
        return false;
    }
    // 2017/11/15 S21_NA#22604 Add End
}
