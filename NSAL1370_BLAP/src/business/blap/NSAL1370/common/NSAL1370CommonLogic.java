/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1370.common;

import static business.blap.NSAL1370.constant.NSAL1370Constant.*;
import static business.blap.NSAL1370.constant.NSAL1370Constant.NSAM0640E;
import static business.blap.NSAL1370.constant.NSAL1370Constant.NSAM0643E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL1370.NSAL1370CMsg;
import business.blap.NSAL1370.NSAL1370Query;
import business.blap.NSAL1370.NSAL1370_ACMsg;
import business.db.DS_CONTRTMsg;
import business.db.MTR_LBTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NSAL1370CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Osawa         Create          N/A
 * 2017/10/26   Hitachi         K.Kojima        Update          QC#21556
 * 2018/07/09   Fujitsu         K.Ishizuka      Update          QX#26528
 *</pre>
 */
public class NSAL1370CommonLogic {
    /**
     * @param bizMsg Business Msg
     */
    public static void setBllgMtrLbDescTxt(NSAL1370CMsg bizMsg) {
        MTR_LBTMsg tMsg = new MTR_LBTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mtrLbCd, bizMsg.bllgMtrLbCd_P);
        tMsg = (MTR_LBTMsg) S21CodeTableAccessor.findByKey(tMsg);

        if (tMsg == null) {
            bizMsg.mtrLbDescTxt_BL.clear();
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.mtrLbDescTxt_BL, tMsg.mtrLbDescTxt);
    }

    /**
     * @param bizMsg Business Msg
     */
    public static void setMdlDescTxt(NSAL1370CMsg bizMsg) {
        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd_P.getValue())) {
            bizMsg.t_MdlNm.setValue(MDL_NM_FLEET);
            return;
        }
        S21SsmEZDResult rsltMdl = NSAL1370Query.getInstance().getMdlDescTxt(bizMsg);
        if (rsltMdl == null || rsltMdl.isCodeNotFound()) {
            bizMsg.t_MdlNm.clear();
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsMdlList = (List<Map<String, Object>>) rsltMdl.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.t_MdlNm, (String) rsMdlList.get(0).get("T_MDL_NM"));
    }

    /**
     * @param bizMsg Business Msg
     */
    public static void setAddTier(NSAL1370CMsg bizMsg) {
        int ix = bizMsg.A.getValidCount();

        S21SsmEZDResult rsltTier = NSAL1370Query.getInstance().getAddTier(bizMsg, ix);
        if (rsltTier == null || rsltTier.isCodeNotFound()) {
            bizMsg.setMessageInfo(NSAM0112E);
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsTierList = (List<Map<String, Object>>) rsltTier.getResultObject();

        NSAL1370_ACMsg aBizMsg = bizMsg.A.no(ix);
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcSvcTierTpDescTxt_A, (String) rsTierList.get(0).get("PRC_SVC_TIER_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(aBizMsg.prcSvcTierTpCd_A, (String) rsTierList.get(0).get("PRC_SVC_TIER_TP_CD"));
        aBizMsg.minCopyVolCnt_A.clear();
        aBizMsg.maxCopyVolCnt_A.clear();
        aBizMsg.xsMtrAmtRate_A.clear();
        if (ix > 0 && ZYPCommonFunc.hasValue(bizMsg.A.no(ix - 1).maxCopyVolCnt_A)) {
            ZYPEZDItemValueSetter.setValue(aBizMsg.minCopyVolCnt_A, bizMsg.A.no(ix - 1).maxCopyVolCnt_A.getValue().add(BigDecimal.ONE));
        } else if (ix == 0) {
            aBizMsg.minCopyVolCnt_A.setValue(BigDecimal.ONE);
        }

        bizMsg.A.setValidCount(ix + 1);
    }

    /**
     * @param bizMsg Business Msg
     */
    public static void deleteTier(NSAL1370CMsg bizMsg) {
        int ix = bizMsg.A.getValidCount();
        if (ix == 0) {
            return;
        }

        ZYPTableUtil.deleteRows(bizMsg.A, new ArrayList<Integer>(Arrays.asList(ix - 1)));
    }

    /**
     * @param bizMsg Business Msg
     */
    public static void closeCheck(NSAL1370CMsg bizMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NSAL1370_ACMsg bizLineMsg = bizMsg.A.no(i);

            if (MAX_RANGE.compareTo(bizLineMsg.minCopyVolCnt_A.getValue()) < 0) {
                bizLineMsg.minCopyVolCnt_A.setErrorInfo(1, NSAM0642E, new String[] {"999,999,999" });
            }
            if (MAX_RANGE.compareTo(bizLineMsg.maxCopyVolCnt_A.getValue()) < 0) {
                bizLineMsg.maxCopyVolCnt_A.setErrorInfo(1, NSAM0642E, new String[] {"999,999,999" });
            }
            if (MAX_XS_RATE.compareTo(bizLineMsg.xsMtrAmtRate_A.getValue()) < 0) {
                bizLineMsg.xsMtrAmtRate_A.setErrorInfo(1, NSAM0642E, new String[] {"9.99999" });
            }

            if (i == 0) {
                if (bizLineMsg.minCopyVolCnt_A.getValue().compareTo(BigDecimal.ONE) != 0) {
                    bizLineMsg.minCopyVolCnt_A.setErrorInfo(1, NSAM0643E, new String[] {"Range From", "1" });
                }
            } else {
                if (bizLineMsg.minCopyVolCnt_A.getValue().compareTo(bizMsg.A.no(i - 1).maxCopyVolCnt_A.getValue().add(BigDecimal.ONE)) != 0) {
                    //                    bizMsg.A.no(i - 1).maxCopyVolCnt_A.setErrorInfo(1, NWAM0760E, new String[] {"Range From", bizMsg.A.no(i - 1).maxCopyVolCnt_A.getValue().add(BigDecimal.ONE).toString() });
                    bizLineMsg.minCopyVolCnt_A.setErrorInfo(1, NSAM0643E, new String[] {"Range From", bizMsg.A.no(i - 1).maxCopyVolCnt_A.getValue().add(BigDecimal.ONE).toString() });
                }
            }
            if (bizLineMsg.minCopyVolCnt_A.getValue().compareTo(bizLineMsg.maxCopyVolCnt_A.getValue()) >= 0) {
                bizLineMsg.maxCopyVolCnt_A.setErrorInfo(1, NSAM0640E, new String[] {"Range To", "Range From" });
            }
        }
        // 2018/07/09 S21_NA#26528 Mod Start
        // if (bizMsg.A.getValidCount() < 2) {
            // bizMsg.setMessageInfo(NSAM0641E);
        // }
        if (bizMsg.A.getValidCount() < 1) {
            bizMsg.setMessageInfo(NSAM0641E);
        }
        // 2018/07/09 S21_NA#26528 Mod End

    }

    // START 2017/10/26 K.Kojima [QC#21556,ADD]
    /**
     * setXxDplyCtrlFlg
     * @param bizMsg NSAL1370CMsg
     * @param glblCmpyCd String
     */
    public static void setXxDplyCtrlFlg(NSAL1370CMsg bizMsg) {
        String xxDplyCtrlFlg = ZYPConstant.FLG_OFF_N;
        if (ZYPCommonFunc.hasValue(bizMsg.dsContrPk_P)) {
            DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, bizMsg.dsContrPk_P);
            tMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(tMsg);
            if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.dsContrCatgCd) && DS_CONTR_CATG.AGGREGATE.equals(tMsg.dsContrCatgCd.getValue())) {
                xxDplyCtrlFlg = ZYPConstant.FLG_ON_Y;
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyCtrlFlg_AG, xxDplyCtrlFlg);
    }
    // END 2017/10/26 K.Kojima [QC#21556,ADD]
}
