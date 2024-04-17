/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0170.common;

import static business.blap.NMAL0170.constant.NMAL0170Constant.COL_SUPD_FROM_MDSE_CD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant.SUPD_RELN_STAGE_SQ;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL0170.NMAL0170CMsg;
import business.blap.NMAL0170.NMAL0170Query;
import business.blap.NMAL0170.NMAL0170SMsg;
import business.blap.NMAL0170.NMAL0170_ACMsg;
import business.db.MDSETMsg;
import business.db.SUPD_RELN_STAGETMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_CLS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * NMAL0170CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Arima          Create          N/A
 * 2015/12/11   Fujitsu         T.Arima          Update          QC#1882
 * 2016/01/04   Fujitsu         M.Nakamura       Update          QC#2528
 * 2016/02/23   CITS            S.Tanikawa       Update          QC#2669
 * 2017/01/23   Fujitsu         R.Nakamura       Update          QC#17195
 * 2017/02/09   Fujitsu         K.Ishizuka       Update          QC#17463
 *</pre>
 */
public class NMAL0170CommonLogic {

    /**
     * Create Pulldown list.
     * @param bizMsg NMAL0170CMsg
     */
    public static void createPulldownList(NMAL0170CMsg bizMsg, NMAL0170SMsg glblMsg) {
        // ADD START S21_NA #17463
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);
        // ADD END S21_NA #17463
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class, bizMsg.mdseItemTpCd_L1, bizMsg.mdseItemTpDescTxt_L1);
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_CLS_TP.class, bizMsg.mdseItemClsTpCd_L1, bizMsg.mdseItemClsTpDescTxt_L1);
        // DELETE START 2016/02/24 QC#2669
        // ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class,
        // bizMsg.mdseItemTpCd_AL, bizMsg.mdseItemTpDescTxt_AL);
        // ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_CLS_TP.class,
        // bizMsg.mdseItemClsTpCd_AL, bizMsg.mdseItemClsTpDescTxt_AL);
        // DELETE END 2016/02/24 QC#2669
    }

    /**
     * Find Mdse
     * @param glblCmpyCd Global Company Code.
     * @param mdseCd Mdse Code.
     * @return MDSETMsg
     */
    // UPDATE START 2016/02/24 QC#2669
    public static boolean findMdse(String glblCmpyCd, String mdseCd) {

        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return false;
        }

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        MDSETMsg resTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(mdseTMsg);

        // Mod Start 2017/01/23 QC#17195
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(resTMsg.getReturnCode()) || EZDTBLAccessor.RTNCD_NOT_FOUND.equals(resTMsg.getReturnCode())) {
        if (null == resTMsg) {
        // Mod End 2017/01/23 QC#17195
            return false;
        }
        return true;
    }

    // UPDATE END 2016/02/24 QC#2669
    // ADD START 2015/12/11
    /**
     * Find SUPD_RELN_STAGE
     * @param glblCmpyCd Global Company Code.
     * @param supdRelnStagePk Primary Key
     * @return SUPD_RELN_STAGETMsg
     */
    public static SUPD_RELN_STAGETMsg findSupdRelnStage(String glblCmpyCd, BigDecimal supdRelnStagePk) {

        SUPD_RELN_STAGETMsg supdRelnStageTMsg = new SUPD_RELN_STAGETMsg();
        ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(supdRelnStageTMsg.supdRelnStagePk, supdRelnStagePk);
        SUPD_RELN_STAGETMsg resTMsg = (SUPD_RELN_STAGETMsg) S21CacheTBLAccessor.findByKey(supdRelnStageTMsg);
        return resTMsg;
    }

    // ADD END 2015/12/11

    /**
     * Insert SupdRelnStage
     * @param glblCmpyCd Global Company Code.
     * @param seq Sequence Number.
     * @param lineMsg Line Message.
     * @return RTNCD_NORMAL == SUPD_RELN_STAGETMsg.getReturnCode()
     */
    public static boolean insertSupdRelnStage(String glblCmpyCd, BigDecimal seq, NMAL0170_ACMsg lineMsg) {
        SUPD_RELN_STAGETMsg insertMsg = new SUPD_RELN_STAGETMsg();

        ZYPEZDItemValueSetter.setValue(insertMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(insertMsg.supdRelnStagePk, seq);
        ZYPEZDItemValueSetter.setValue(insertMsg.supdFromMdseCd, lineMsg.supdFromMdseCd_A1);
        ZYPEZDItemValueSetter.setValue(insertMsg.supdToMdseCd, lineMsg.supdToMdseCd_A1);
        ZYPEZDItemValueSetter.setValue(insertMsg.supdRelnStageDt, lineMsg.supdRelnStageDt_A1);
        ZYPEZDItemValueSetter.setValue(insertMsg.submtFlg, FLG_OFF_N);

        EZDTBLAccessor.create(insertMsg);
        if (EZDTBLAccessor.RTNCD_NORMAL.equals(insertMsg.getReturnCode())) {
            return true;
        }
        return false;
    }

    /**
     * Find Mdse
     * @param glblCmpyCd Global Company Code.
     * @param mdseCd MDSE Code.
     * @return MDSETMsg
     */
    public static MDSETMsg findDsMdseInfo(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        MDSETMsg resTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        return resTMsg;

    }

    // DEL START 2016/01/04 QC#2528
    // /**
    // * Update DsMdseInfo
    // * @param glblCmpyCd Global Company Code
    // * @param lineMsg Line Message.
    // * @return RTNCD_NORMAL == DS_MDSE_INFOTMsg.getReturnCode()
    // */
    // public static boolean updateDsMdseInfo(String glblCmpyCd,
    // NMAL0170_ACMsg lineMsg) {
    // DS_MDSE_INFOTMsg updateTMsg = findDsMdseInfo(glblCmpyCd,
    // lineMsg.supdToMdseCd_A1.getValue());
    // ZYPEZDItemValueSetter.setValue(updateTMsg.glblCmpyCd,
    // glblCmpyCd);
    // ZYPEZDItemValueSetter.setValue(updateTMsg.mdseItemClsTpCd,
    // lineMsg.mdseItemClsTpCd_A1);
    // ZYPEZDItemValueSetter.setValue(updateTMsg.mdseItemTpCd,
    // lineMsg.mdseItemTpCd_A1);
    // S21FastTBLAccessor.update(updateTMsg);
    // if
    // (S21FastTBLAccessor.RTNCD_NORMAL.equals(updateTMsg.getReturnCode()))
    // {
    // return true;
    // }
    // return false;
    // }
    // DEL END 2016/01/04 QC#2528

    /**
     * Get SUPD_RELN_STAGE_SQ
     * @return sequence.
     */
    public static BigDecimal getSupdRelnStageSeq() {
        BigDecimal seq = ZYPOracleSeqAccessor.getNumberBigDecimal(SUPD_RELN_STAGE_SQ);
        return seq;
    }

    // ADD START 2016/02/23 QC#2669
    /**
     * copyFromSMsgToCMsg
     * @param cMsg NMAL0170CMsg
     * @param sMsg NMAL0170SMsg
     * @param flg boolean
     */
    public static void copyFromSMsgToCMsg(NMAL0170CMsg cMsg, NMAL0170SMsg sMsg, boolean flg) {
        int currentPage = cMsg.xxPageShowCurNum.getValueInt();
        int s = (currentPage - 1) * cMsg.A.length();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(s + i), null);
        }
        if (flg) {
            ZYPTableUtil.clear(cMsg.A);
        }
    }

    /**
     * isExistSupdReln is Exist in SUPD_RELN/SUPD_STAGE
     * @param glblCmpyCd
     * @param lineMsg NMAL0170_ACMsg
     * @return boolean
     */
    public static boolean isExistSupdReln(NMAL0170_ACMsg lineMsg) {

        Integer resultCnt = 0;
        resultCnt += NMAL0170Query.getInstance().getCountSupdReln(lineMsg);
        resultCnt += NMAL0170Query.getInstance().getCountSupdRelnStage(lineMsg);

        if (resultCnt > 0) {
            return true;
        }

        return false;
    }

    /**
     * isExistSupdFrom
     * @param lineMsg NMAL0170_ACMsg
     * @return boolean
     */
    public static boolean isExistSupdFrom(NMAL0170_ACMsg lineMsg) {

        Integer resultCnt = NMAL0170Query.getInstance().getCountSupdFrom(lineMsg);

        if (resultCnt > 0) {
            return true;
        }

        return false;
    }

    /**
     * isLoopSupd
     * @param lineMsg NMAL0170_ACMsg
     * @return boolean
     */
    public static boolean isLoopSupd(NMAL0170_ACMsg lineMsg) {

        S21SsmEZDResult result = NMAL0170Query.getInstance().getSupdRelnList(lineMsg);

        if (result.isCodeNormal()) {
            List<Map<String, String>> list = (List<Map<String, String>>) result.getResultObject();
            if (list != null && list.size() > 0) {
                for (Map<String, String> map : list) {
                    if (lineMsg.supdToMdseCd_A1.getValue().equals(map.get(COL_SUPD_FROM_MDSE_CD))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    // ADD END 2016/02/23 QC#2669
    
    // ADD START S21_NA #17463
    /**
     * control pagenation
     * @param cMsg NMAL0170CMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsg NMAL0170SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NMAL0170CMsg cMsg, EZDCMsgArray cMsgArray, NMAL0170SMsg sMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (cMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
        for (; i < startIndex + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);
            } else {
                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        cMsg.xxPageShowFromNum.setValue(startIndex + 1);
        cMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }
    
    /**
     * update S message
     * @param cMsg NMAL0170CMsg
     * @param sMsg NMAL0170SMsg
     */
    public static void updateSMsg(NMAL0170CMsg cMsg, NMAL0170SMsg sMsg) {
        int ixG = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(ixG + i), null);
        }
    }
    
   // ADD END S21_NA #17463
}
