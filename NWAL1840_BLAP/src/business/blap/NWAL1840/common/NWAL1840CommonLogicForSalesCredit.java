/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1840.common;

import static business.blap.NWAL1840.constant.NWAL1840Constant.PCT_100;
import static business.blap.NWAL1840.constant.NWAL1840MsgConstant.NWAM0181E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.blap.NWAL1840.NWAL1840Query;
import business.blap.NWAL1840.NWAL1840_CCMsg;
import business.blap.NWAL1840.NWAL1840_UCMsg;
import business.blap.NWAL1840.NWAL1840_UCMsgArray;
import business.blap.NWAL1840.constant.NWAL1840Constant;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/11   Fujitsu         T.Murai         Create          N/A
 * 2016/05/12   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/09/20   Fujitsu         T.Murai         Update          S21_NA#14578
 * 2019/12/20   Fujitsu         A.Kazuki        Update          QC#53055
 * </pre>
 */
public class NWAL1840CommonLogicForSalesCredit {

//    /**
//     * Derive Default Sales Rep Data
//     * @param bizMsg Business Message
//     * @return Succeed drive : true
//     */
//    public static boolean deriveDefaultSlsRep(NWAL1840CMsg bizMsg) {
//
//        // Call NMZC2600 Default Sales Rep API
//        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
//        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, bizMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, bizMsg.shipToCustLocCd);
//
//        if (!NWAL1840CommonLogic.callDefSlsRepApi(bizMsg, nMZC260001PMsg)) {
//            return false;
//        }
//
//        String lineBizTpCd = bizMsg.lineBizTpCd.getValue();
//
//        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;
//
//        List<NMZC260001_defSlsRepListPMsg> matchLobSlsRepPMsgList = new ArrayList<NMZC260001_defSlsRepListPMsg>(defSlsRepMsgArray.getValidCount());
//        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
//            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);
//
//            if (defSlsRepPMsg.lineBizTpCd.getValue().equals(lineBizTpCd)) {
//                matchLobSlsRepPMsgList.add(defSlsRepPMsg);
//
//                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
//                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd)) {
//                    getSlsRepInfo(bizMsg, defSlsRepPMsg.tocCd.getValue());
//                }
//            }
//        }
//
//        if (matchLobSlsRepPMsgList.size() > 0) {
//            setDefaultSlsRep(bizMsg, matchLobSlsRepPMsgList);
//        }
//
//        return true;
//    }

    /**
     * Derive Default Sales Rep Data For Header
     * @param bizMsg NWAL1840CMsg
     * @param slsRepTocCd Sales Rep TOC Code
     */
    public static void getSlsRepInfo(NWAL1840CMsg bizMsg, String slsRepTocCd) {

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getSlsRepInfo(bizMsg, slsRepTocCd);

        if (ssmResult.isCodeNormal()) {
            Map<String, String> resultMap = (Map<String, String>) ssmResult.getResultObject();
            ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, slsRepTocCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm, resultMap.get("TOC_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, resultMap.get("COA_BR_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, resultMap.get("COA_BR_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, resultMap.get("COA_EXTN_CD"));
            ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt, resultMap.get("COA_EXTN_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, resultMap.get("COA_BR_ITEM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, resultMap.get("COA_EXTN_ITEM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.psnNum, resultMap.get("PSN_NUM")); 
        }
    }

    /**
     * Set Default Sales Rep
     * @param bizMsg NWAL1840CMsg
     * @param slsRepPMsgList List<NMZC260001_defSlsRepListPMsg>
     */
    private static void setDefaultSlsRep(NWAL1840CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList) {

        // Delete Existing Sales Rep Info
        delExistingSlsRep(bizMsg);

        // Set New Sales Rep
        setNewSlsRep(bizMsg, slsRepPMsgList);
    }

    /**
     * Delete Existing Sales Rep Info
     * @param bizMsg NWAL1840CMsg
     */
    private static void delExistingSlsRep(NWAL1840CMsg bizMsg) {

        // Delete Header
        List<Integer> deleteRows = new ArrayList<Integer>(bizMsg.C.getValidCount());
        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL1840_CCMsg slsRepMsg = bizMsg.C.no(i);

            if (ZYPCommonFunc.hasValue(slsRepMsg.schdAgmtSlsCrPk_C)) {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.xxRqstTpCd_C, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteRows.add(i);
            }
        }
        ZYPTableUtil.deleteRows(bizMsg.C, deleteRows);
    }

    /**
     * Set New Sales Rep Info
     * @param bizMsg NWAL1840CMsg
     * @param slsRepPMsgList List<NMZC260001_defSlsRepListPMsg>
     */
    private static void setNewSlsRep(NWAL1840CMsg bizMsg, List<NMZC260001_defSlsRepListPMsg> slsRepPMsgList) {

        // Set Header
        int vldCnt = bizMsg.C.getValidCount();
        for (int i = 0; i < slsRepPMsgList.size(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = slsRepPMsgList.get(i);

            NWAL1840_CCMsg slsRepMsg = bizMsg.C.no(vldCnt);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.xxRqstTpCd_C, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_C, defSlsRepPMsg.tocCd);
            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_C, defSlsRepPMsg.lineBizRoleTpCd);
            // Add Start 2019/12/20 QC#53055 ISW check
            // When LINE_BIZ_TP = LFS and TRTY_GRP_TP_TXT = IS, Set "IS Writer".
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg != null && NWAL1840Constant.TRTY_GRP_TP_IS.equals(tMsg.trtyGrpTpTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_C, LINE_BIZ_ROLE_TP.IS_WRITER);
            }
            // Add End   2019/12/20 QC#53055
            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_C, defSlsRepPMsg.lineBizRoleTpCd);

            if (i == 0) {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_C, PCT_100);
            } else {
                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepCrPct_C, BigDecimal.ZERO);
            }

            // Mod 2016/09/20 S21_NA#14578
            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsCrQuotFlg_C, ZYPConstant.FLG_ON_Y);
            //ZYPEZDItemValueSetter.setValue(slsRepMsg.slsCrQuotFlg_C, ZYPConstant.FLG_OFF_N);

            vldCnt++;

        }
        bizMsg.C.setValidCount(vldCnt);
    }

    /**
     * Copy Sales Credit From Popup
     * @param bizMsg NWAL1840CMsg
     */
    public static void copySlsCrFromPopup(NWAL1840CMsg bizMsg) {

        int hdrCnt = 0;
        List<NWAL1840_CCMsg> slsCreditList = new ArrayList<NWAL1840_CCMsg>(bizMsg.C.getValidCount());

        for (; hdrCnt < bizMsg.C.getValidCount(); hdrCnt++) {
            NWAL1840_CCMsg slsCreditMsg = new NWAL1840_CCMsg();
            EZDMsg.copy(bizMsg.C.no(hdrCnt), "C", slsCreditMsg, "C");
            slsCreditList.add(slsCreditMsg);
        }

        boolean isSetDefSlsRep = false;
        List<NWAL1840_CCMsg> hdrSlsCrList = new ArrayList<NWAL1840_CCMsg>();

        for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
            NWAL1840_UCMsg paramSlsCreditMsg = bizMsg.U.no(i);
            String lineBizRoleTpCd = paramSlsCreditMsg.lineBizRoleTpCd_U.getValue();
            String slsRepTocCd = paramSlsCreditMsg.slsRepTocCd_U.getValue();
            BigDecimal slsCrPk = paramSlsCreditMsg.dsCpoSlsCrPk_U.getValue(); // 2016/05/30 S21_NA#8252 Add

            if (!isSetDefSlsRep) {
                // Del Start 2019/12/20 QC#53055
//                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd)) {
                // Del End   2019/12/20 QC#53055
                // Add Start 2019/12/20 QC#53055
                if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd)
                        || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.IS_WRITER.equals(lineBizRoleTpCd)) {
                // Add End   2019/12/20 QC#53055
                    getSlsRepInfo(bizMsg, slsRepTocCd);
                    isSetDefSlsRep = true;
                }
            }

            boolean srchedFlg = false;

            for (NWAL1840_CCMsg currHdrSlsCr : slsCreditList) {
                
                // QC#14578
                if (NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(currHdrSlsCr.xxRqstTpCd_C.getValue())) {
                    continue;
                }
                // QC#14578
                String hdrSlsRepTocCd = currHdrSlsCr.slsRepTocCd_C.getValue();
                String hdrSlsRepRoleTpCd = currHdrSlsCr.slsRepRoleTpCd_C.getValue();

                // 2016/05/30 S21_NA#8252 Add Start
                BigDecimal hdrSlsCrPk = currHdrSlsCr.schdAgmtSlsCrPk_C.getValue();
                // 2016/05/30 S21_NA#8252 Add End

                // 2016/06/02 S21_NA#8252 Mod Start
//                 if (slsRepTocCd.equals(hdrSlsRepTocCd) && lineBizRoleTpCd.equals(hdrSlsRepRoleTpCd)) {                // if (slsRepTocCd.equals(hdrSlsRepTocCd) &&lineBizRoleTpCd.equals(hdrSlsRepRoleTpCd_FS)) { 2016/05/30 S21_NA#8252 Mod Condition
                   if ((slsRepTocCd.equals(hdrSlsRepTocCd) && lineBizRoleTpCd.equals(hdrSlsRepRoleTpCd)) //
                        || (slsCrPk != null && hdrSlsCrPk != null && slsCrPk.compareTo(hdrSlsCrPk) == 0)) {
                       // 2016/06/02 S21_NA#8252 Mod End
                    NWAL1840_CCMsg hdrSlsCr = new NWAL1840_CCMsg();
                    EZDMsg.copy(currHdrSlsCr, "C", hdrSlsCr, "C");

                    if (!NWAL1840CommonLogic.isEqualsEZDItem(hdrSlsCr.schdAgmtSlsCrPk_C, paramSlsCreditMsg.dsCpoSlsCrPk_U)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.schdAgmtSlsCrPk_C, paramSlsCreditMsg.dsCpoSlsCrPk_U);
                    }

                    if (!NWAL1840CommonLogic.isEqualsEZDItem(hdrSlsCr.slsRepTocCd_C, paramSlsCreditMsg.slsRepTocCd_U)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepTocCd_C, paramSlsCreditMsg.slsRepTocCd_U);
                    }

                    if (!NWAL1840CommonLogic.isEqualsEZDItem(hdrSlsCr.slsRepRoleTpCd_C, paramSlsCreditMsg.lineBizRoleTpCd_U)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepRoleTpCd_C, paramSlsCreditMsg.lineBizRoleTpCd_U);
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.lineBizRoleTpCd_C, paramSlsCreditMsg.lineBizRoleTpCd_U);
                    }

                    if (!NWAL1840CommonLogic.isEqualsEZDItem(hdrSlsCr.slsRepCrPct_C, paramSlsCreditMsg.slsRepCrPct_U)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepCrPct_C, paramSlsCreditMsg.slsRepCrPct_U);
                    }

                    if (!NWAL1840CommonLogic.isEqualsEZDItem(hdrSlsCr.slsCrQuotFlg_C, paramSlsCreditMsg.slsCrQuotFlg_U)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsCrQuotFlg_C, paramSlsCreditMsg.slsCrQuotFlg_U);
                    }

                    if (!NWAL1840CommonLogic.isEqualsEZDItem(hdrSlsCr.xxRqstTpCd_C, paramSlsCreditMsg.xxRqstTpCd_U)) {
                        ZYPEZDItemValueSetter.setValue(hdrSlsCr.xxRqstTpCd_C, paramSlsCreditMsg.xxRqstTpCd_U);
                    }

                    hdrSlsCrList.add(hdrSlsCr);
                    srchedFlg = true;
                }
            }

            if (!srchedFlg) {
                NWAL1840_CCMsg hdrSlsCr = new NWAL1840_CCMsg();
                EZDMsg.copy(paramSlsCreditMsg, "U", hdrSlsCr, "C");
                ZYPEZDItemValueSetter.setValue(hdrSlsCr.schdAgmtSlsCrPk_C, paramSlsCreditMsg.dsCpoSlsCrPk_U);
                ZYPEZDItemValueSetter.setValue(hdrSlsCr.slsRepRoleTpCd_C, paramSlsCreditMsg.lineBizRoleTpCd_U);
                hdrSlsCrList.add(hdrSlsCr);
            }
        }

        for (NWAL1840_CCMsg curSlsCredit : slsCreditList) {
            // QC#14578
            if (NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(curSlsCredit.xxRqstTpCd_C.getValue())) {
                NWAL1840_CCMsg hdrSlsCr = new NWAL1840_CCMsg();
                EZDMsg.copy(curSlsCredit, "C", hdrSlsCr, "C");
                hdrSlsCrList.add(hdrSlsCr);
                continue;
            }
            // QC#14578
            String hdrSlsRepTocCd = curSlsCredit.slsRepTocCd_C.getValue();
            String hdrSlsRepRoleTpCd = curSlsCredit.slsRepRoleTpCd_C.getValue();
            BigDecimal hdrSlsCrPk = curSlsCredit.schdAgmtSlsCrPk_C.getValue(); // 2016/05/30 S21_NA#8252 Add

            boolean srched = false;
            for (int i = 0; i < bizMsg.U.getValidCount(); i++) {
                NWAL1840_UCMsg paramSlsCreditMsg = bizMsg.U.no(i);
                String slsRepTocCd = paramSlsCreditMsg.slsRepTocCd_U.getValue();
                String lineBizRoleTpCd = paramSlsCreditMsg.lineBizRoleTpCd_U.getValue();
                BigDecimal slsCrPk = paramSlsCreditMsg.dsCpoSlsCrPk_U.getValue(); // 2016/05/30 S21_NA#8252 Add
                // if (hdrSlsRepTocCd.equals(slsRepTocCd) && hdrSlsRepRoleTpCd.equals(lineBizRoleTpCd)) { 2016/05/30 S21_NA#8252 Mod Condition
                if ((hdrSlsRepTocCd.equals(slsRepTocCd) && hdrSlsRepRoleTpCd.equals(lineBizRoleTpCd)) //
                        || (hdrSlsCrPk != null && slsCrPk != null && hdrSlsCrPk.compareTo(slsCrPk) == 0)) {
                    srched = true;
                    break;
                }
            }

            if (!srched && ZYPCommonFunc.hasValue(curSlsCredit.schdAgmtSlsCrPk_C)) {
                NWAL1840_CCMsg slsCreditMsg = new NWAL1840_CCMsg();
                EZDMsg.copy(curSlsCredit, "C", slsCreditMsg, "C");
                ZYPEZDItemValueSetter.setValue(slsCreditMsg.xxRqstTpCd_C, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
                hdrSlsCrList.add(slsCreditMsg);
            }
        }

        hdrCnt = 0;
        ZYPTableUtil.clear(bizMsg.C);

        for (NWAL1840_CCMsg hdrSlsCr : hdrSlsCrList) {
            EZDMsg.copy(hdrSlsCr, "C", bizMsg.C.no(hdrCnt), "C");
            hdrCnt++;
        }
        bizMsg.C.setValidCount(hdrCnt);
    }

    /**
     * Delete All Sales Credit Information
     * @param bizMsg NWAL1840CMsg
     */
    public static void delAllSlsCreditInfo(NWAL1840CMsg bizMsg) {

        List<Integer> deleteList = new ArrayList<Integer>(bizMsg.C.getValidCount());

        for (int i = 0; i < bizMsg.C.getValidCount(); i++) {
            NWAL1840_CCMsg slsCreditMsg = bizMsg.C.no(i);

            if (ZYPCommonFunc.hasValue(slsCreditMsg.schdAgmtSlsCrPk_C)) {
                ZYPEZDItemValueSetter.setValue(slsCreditMsg.xxRqstTpCd_C, NWZC150001Constant.RQST_TP_SLS_CR_DELETE);
            } else {
                deleteList.add(i);
            }
        }

        ZYPTableUtil.deleteRows(bizMsg.C, deleteList);
    }

    /**
     * Set Writer Sales Credit Information
     * @param bizMsg NWAL1840CMsg
     * @param writerSlsRepTocCd Write Sales Rep Toc Code
     */
    public static void setWriterSlsCreditInfo(NWAL1840CMsg bizMsg, String writerSlsRepTocCd) {

        String lineBizTpCd = bizMsg.lineBizTpCd.getValue();
        String slsRepRoleTpCd = null;
        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.ESS_WRITER;
        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.LFS_WRITER;

            // Add Start 2019/12/20 QC#53055
            DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, bizMsg.dsOrdTpCd);
            tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg != null && NWAL1840Constant.TRTY_GRP_TP_IS.equals(tMsg.trtyGrpTpTxt.getValue())) {
                slsRepRoleTpCd = LINE_BIZ_ROLE_TP.IS_WRITER;
            }
            // Add End   2019/12/20 QC#53055
        } else {
            slsRepRoleTpCd = LINE_BIZ_ROLE_TP.PPS_WRITER;
        }

        NWAL1840_CCMsg slsCreditMsg = bizMsg.C.no(bizMsg.C.getValidCount());
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.slsRepTocCd_C, writerSlsRepTocCd);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.xxRqstTpCd_C, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.slsRepRoleTpCd_C, slsRepRoleTpCd);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.slsRepCrPct_C, PCT_100);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.slsCrQuotFlg_C, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(slsCreditMsg.lineBizRoleTpCd_C, slsRepRoleTpCd);
        bizMsg.C.setValidCount(bizMsg.C.getValidCount() + 1);
    }

    /**
     * Get Sales Rep Code
     * @param bizMsg NWAL1840CMsg
     * @param isCallName Call Name Field
     * @param msgParm Message Parameter
     * @return Sales Rep Code
     */
    public static String getSlsRepCd(NWAL1840CMsg bizMsg, boolean isCallName, String msgParm) {

        S21SsmEZDResult ssmResult = NWAL1840Query.getInstance().getSlsRepInfoList(bizMsg, isCallName);

        if (ssmResult.isCodeNotFound()) {
            if (isCallName) {
                bizMsg.slsRepTocCd.clear();
                bizMsg.slsRepTocNm.setErrorInfo(1, NWAM0181E, new String[] {msgParm });
            } else {
                bizMsg.slsRepTocCd.clear();
                bizMsg.psnNum.setErrorInfo(1, NWAM0181E, new String[] {msgParm }); // S21_NA#7861 Mod slsRepPsnCd -> psnNum
            }

            return null;
        }

        List<Map<String, String>> slsRepInfoList = (List<Map<String, String>>) ssmResult.getResultObject();

        if (slsRepInfoList.size() != 1) {
            if (isCallName) {
                bizMsg.psnNum.clear(); // S21_NA#7861 Mod slsRepPsnCd -> psnNum
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

        ZYPEZDItemValueSetter.setValue(bizMsg.psnNum, slsRepInfo.get("PSN_NUM")); // S21_NA#7861 Mod slsRepPsnCd -> psnNum
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocCd, slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(bizMsg.slsRepTocNm, slsRepInfo.get("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrCd, slsRepInfo.get("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaBrDescTxt, slsRepInfo.get("COA_BR_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnCd, slsRepInfo.get("COA_EXTN_CD"));
        ZYPEZDItemValueSetter.setValue(bizMsg.coaExtnDescTxt, slsRepInfo.get("COA_EXTN_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CB, slsRepInfo.get("COA_BR_ITEM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxScrItem54Txt_CE, slsRepInfo.get("COA_EXTN_ITEM"));
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        return slsRepTocCd;
    }

    /**
     * Check Sales Rep Deleted
     * @param slsCrParamMsgArray NWAL1840_TCMsgArray
     * @return All Deleted : true
     */
    public static boolean isParamDtAllDeleted(NWAL1840_UCMsgArray slsCrParamMsgArray) {

        for (int i = 0; i < slsCrParamMsgArray.getValidCount(); i++) {
            String xxRqstTpCd = slsCrParamMsgArray.no(i).xxRqstTpCd_U.getValue();
            if (!NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(xxRqstTpCd)) {
                return false;
            }
        }
        return true;
    }
}
