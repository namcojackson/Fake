/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2160.common;

import static business.blap.NWAL2160.constant.NWAL2160Constant.*;
import static business.blap.NWAL2160.constant.NWAL2160Constant.NWAM0712E;
import static business.blap.NWAL2160.constant.NWAL2160Constant.NWAM0760E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import business.blap.NWAL2160.NWAL2160CMsg;
import business.blap.NWAL2160.NWAL2160Query;
import business.blap.NWAL2160.NWAL2160_ACMsg;
import business.db.MTR_LBTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 *<pre>
 * NWAL2160CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Fujitsu         M.Yamada        Create          N/A
 * 2018/07/06   Fujitsu         K.Ishizuka      Update          QC#26528
 *</pre>
 */
public class NWAL2160CommonLogic {
    /**
     * @param bizMsg
     */
    public static void setBllgMtrLbDescTxt(NWAL2160CMsg bizMsg) {
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
     * @param bizMsg
     */
    public static void setMdlDescTxt(NWAL2160CMsg bizMsg) {
        if (DS_CONTR_CATG.FLEET.equals(bizMsg.dsContrCatgCd_P.getValue())) {
            bizMsg.t_MdlNm.setValue(MDL_NM_FLEET);
            return;
        }
        S21SsmEZDResult rsltMdl = NWAL2160Query.getInstance().getMdlDescTxt(bizMsg);
        if (rsltMdl == null || rsltMdl.isCodeNotFound()) {
            bizMsg.t_MdlNm.clear();
            return;
        }
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsMdlList = (List<Map<String, Object>>) rsltMdl.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.t_MdlNm, (String) rsMdlList.get(0).get("T_MDL_NM"));
    }

    public static void setAddTier(NWAL2160CMsg bizMsg) {
        int ix = bizMsg.A.getValidCount();

        S21SsmEZDResult rsltTier = NWAL2160Query.getInstance().getAddTier(bizMsg, ix);
        if (rsltTier == null || rsltTier.isCodeNotFound()) {
            bizMsg.setMessageInfo(NWAM0100E);
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rsTierList = (List<Map<String, Object>>) rsltTier.getResultObject();

        NWAL2160_ACMsg aBizMsg = bizMsg.A.no(ix);
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

    public static void deleteTier(NWAL2160CMsg bizMsg) {
        int ix = bizMsg.A.getValidCount();
        if (ix == 0) {
            return;
        }

        ZYPTableUtil.deleteRows(bizMsg.A, new ArrayList<Integer>(Arrays.asList(ix - 1)));
    }

    public static void closeCheck(NWAL2160CMsg bizMsg) {
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NWAL2160_ACMsg bizLineMsg = bizMsg.A.no(i);

            if (MAX_RANGE.compareTo(bizLineMsg.minCopyVolCnt_A.getValue()) < 0) {
                bizLineMsg.minCopyVolCnt_A.setErrorInfo(1, NWAM0707E, new String[] {"999,999,999" });
            }
            if (MAX_RANGE.compareTo(bizLineMsg.maxCopyVolCnt_A.getValue()) < 0) {
                bizLineMsg.maxCopyVolCnt_A.setErrorInfo(1, NWAM0707E, new String[] {"999,999,999" });
            }
            if (MAX_XS_RATE.compareTo(bizLineMsg.xsMtrAmtRate_A.getValue()) < 0) {
                bizLineMsg.xsMtrAmtRate_A.setErrorInfo(1, NWAM0707E, new String[] {"9.99999" });
            }

            if (i == 0) {
                if (bizLineMsg.minCopyVolCnt_A.getValue().compareTo(BigDecimal.ONE) != 0) {
                    bizLineMsg.minCopyVolCnt_A.setErrorInfo(1, NWAM0760E, new String[] {"Range From", "1" });
                }
            } else {
                if (bizLineMsg.minCopyVolCnt_A.getValue().compareTo(bizMsg.A.no(i - 1).maxCopyVolCnt_A.getValue().add(BigDecimal.ONE)) != 0) {
                    //                    bizMsg.A.no(i - 1).maxCopyVolCnt_A.setErrorInfo(1, NWAM0760E, new String[] {"Range From", bizMsg.A.no(i - 1).maxCopyVolCnt_A.getValue().add(BigDecimal.ONE).toString() });
                    bizLineMsg.minCopyVolCnt_A.setErrorInfo(1, NWAM0760E, new String[] {"Range From", bizMsg.A.no(i - 1).maxCopyVolCnt_A.getValue().add(BigDecimal.ONE).toString() });
                }
            }
            if (bizLineMsg.minCopyVolCnt_A.getValue().compareTo(bizLineMsg.maxCopyVolCnt_A.getValue()) >= 0) {
                bizLineMsg.maxCopyVolCnt_A.setErrorInfo(1, NWAM0712E, new String[] {"Range To", "Range From" });
            }
        }
        // 2018/07/06 S21_NA#26528 Del Start
        // if (bizMsg.A.getValidCount() < 2) {
            // bizMsg.setMessageInfo(NWAM0724E);
        // }
        // 2018/07/06 S21_NA#26528 Del End

    }

}
