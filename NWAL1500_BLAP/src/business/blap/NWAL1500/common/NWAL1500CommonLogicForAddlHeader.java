/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500.common;

import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.blap.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_ASSN_PROGRAM_NM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_ASSN_PROGRAM_NUM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CARR_SVC_LVL;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CSA_NUM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CSMP_NUM;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_FRT_TERMS;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_PMT_TERMS;
import static business.blap.NWAL1500.constant.NWAL1500MsgConstant.NWAM0181E;

import java.util.List;
import java.util.Map;

import business.blap.NWAL1500.NWAL1500CMsg;
import business.blap.NWAL1500.NWAL1500QueryForAddlHeader;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Fujitsu         T.Yoshida       Create          N/A
 * 2016/08/26   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2018/07/17   Fujitsu         A.Kosai         Update          S21_NA#27073
 * </pre>
 */
public class NWAL1500CommonLogicForAddlHeader {

    /**
     * Get Freight Term Information
     * @param bizMsg NWAL1500CMsg
     * @return Freight Term Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getFreightTermInfo(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForAddlHeader.getInstance().getFreightTermInfoList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.frtCondDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_FRT_TERMS });
            return null;
        }

        List<Map<String, String>> freightTermInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (freightTermInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return freightTermInfoList.get(0);
    }

    /**
     * Get Payment Term Information
     * @param bizMsg NWAL1500CMsg
     * @return Payment Term Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getPaymentTermInfo(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForAddlHeader.getInstance().getPaymentTermInfoList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.pmtTermCashDiscDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_PMT_TERMS });
            return null;
        }

        List<Map<String, String>> paymentTermInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (paymentTermInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return paymentTermInfoList.get(0);
    }

    /**
     * Get Carrier Service Level Information
     * @param bizMsg NWAL1500CMsg
     * @return Carrier Service Level Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getCarrSvcLvlInfo(NWAL1500CMsg bizMsg) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForAddlHeader.getInstance().getCarrSvcLvlInfoList(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.carrSvcLvlDescTxt.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CARR_SVC_LVL });
            return null;
        }

        List<Map<String, String>> carrSvcLvlInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (carrSvcLvlInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return carrSvcLvlInfoList.get(0);
    }

    /**
     * Get CSMP Contract Information
     * @param bizMsg NWAL1500CMsg
     * @param isReqSettingParam is Required Setting Parameter
     * @param isCallCsmpNum is Called CSMP Number Field
     * @return CSMP Contract Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getCsmpContrInfo(NWAL1500CMsg bizMsg, boolean isReqSettingParam, boolean isCallCsmpNum) {

        S21SsmEZDResult ssmResult = NWAL1500QueryForAddlHeader.getInstance().getCsmpContrInfoList(bizMsg, isReqSettingParam, isCallCsmpNum);

        int cellIdx = bizMsg.xxCellIdx.getValueInt(); // 2016/08/26 S21_NA#9806 Add
        String dplyTab = bizMsg.xxDplyTab.getValue(); // 2016/08/26 S21_NA#9806 Add
        if (ssmResult.isCodeNotFound()) {
            // 2016/08/26 S21_NA#9806 Mod Start
//            if (isCallCsmpNum) {
//                bizMsg.csmpContrNum.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CSMP_NUM });
//            } else {
//                bizMsg.dlrRefNum.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CSA_NUM });
//            }
            if (cellIdx < 0) {
                if (isCallCsmpNum) {
                    bizMsg.csmpContrNum.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CSMP_NUM });
                } else {
                    bizMsg.dlrRefNum.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CSA_NUM });
                }
            } else {
                if (TAB_LINE_CONFIG.equals(dplyTab)) {
                    if (isCallCsmpNum) {
                        bizMsg.A.no(cellIdx).csmpContrNum_LC.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CSMP_NUM });
                    } else {
                        bizMsg.A.no(cellIdx).dlrRefNum_LC.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CSA_NUM });
                    }
                } else if (TAB_RMA.equals(dplyTab)) {
                    if (isCallCsmpNum) {
                        bizMsg.C.no(cellIdx).csmpContrNum_RC.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CSMP_NUM });
                    } else {
                        bizMsg.C.no(cellIdx).dlrRefNum_RC.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_CSA_NUM });
                    }
                }
            }
            // 2016/08/26 S21_NA#9806 Mod End
            return null;
        }

        List<Map<String, String>> csmpContrInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (csmpContrInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return csmpContrInfoList.get(0);
    }

    /**
     * Get Price Contract Information
     * @param bizMsg NWAL1500CMsg
     * @param isCallAssnProgramNm Called Association Program Name Field
     * @return Price Contract Information
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getPrcContrInfo(NWAL1500CMsg bizMsg, boolean isCallAssnProgramNm) {

        // 2018/07/17 S21_NA#27073 Mod Start
//        S21SsmEZDResult ssmResult = NWAL1500QueryForAddlHeader.getInstance().getPrcContrInfoList(bizMsg, isCallAssnProgramNm);
        S21SsmEZDResult ssmResult;
        boolean isLfsOrPps = LINE_BIZ_TP.LFS.equals(bizMsg.lineBizTpCd.getValue()) || LINE_BIZ_TP.PPS.equals(bizMsg.lineBizTpCd.getValue());
        if (isLfsOrPps) {
            ssmResult = NWAL1500QueryForAddlHeader.getInstance().getPrcContrInfoList(bizMsg, isCallAssnProgramNm);
        } else {
            ssmResult = NWAL1500QueryForAddlHeader.getInstance().getCustPrcContrInfoList(bizMsg, isCallAssnProgramNm);
        }
        // 2018/07/17 S21_NA#27073 Mod End

        if (ssmResult.isCodeNotFound()) {
            if (isCallAssnProgramNm) {
                bizMsg.prcContrNm.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ASSN_PROGRAM_NM });
            } else {
                bizMsg.prcContrNum.setErrorInfo(1, NWAM0181E, new String[] {MSG_PARAM_ASSN_PROGRAM_NUM });
            }
            return null;
        }

        List<Map<String, String>> prcContrInfoList = (List<Map<String, String>>) ssmResult.getResultObject();
        ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_OFF_N);

        if (prcContrInfoList.size() != 1) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxRqstFlg, ZYPConstant.FLG_ON_Y);
            return null;
        }

        return prcContrInfoList.get(0);
    }
}
