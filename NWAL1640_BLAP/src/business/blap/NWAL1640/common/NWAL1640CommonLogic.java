/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1640.common;

import static business.blap.NWAL1640.constant.NWAL1640Constant.NWZM0369E;
import static business.blap.NWAL1640.constant.NWAL1640Constant.NWZM1734W;
import static business.blap.NWAL1640.constant.NWAL1640Constant.NWZM1735E;
import static business.blap.NWAL1640.constant.NWAL1640Constant.NWZM1736E;
import static business.blap.NWAL1640.constant.NWAL1640Constant.NWZM1737E;
import static business.blap.NWAL1640.constant.NWAL1640Constant.NWZM1738E;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;

import java.util.List;

import business.blap.NWAL1640.NWAL1640CMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.STTMsg;
import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

/**
 *<pre>
 * NWAL1640CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 * 2017/06/16   Fujitsu         H.Sugawara      Update          QC#18368
 *</pre>
 */
public class NWAL1640CommonLogic {

    /**
     * getStNm
     * @param bizMsg NWAL1640CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean getStNm(NWAL1640CMsg bizMsg, String glblCmpyCd) {

        STTMsg stTMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(stTMsg.stCd, bizMsg.splyStCd);
        ZYPEZDItemValueSetter.setValue(stTMsg.glblCmpyCd, glblCmpyCd);

        STTMsg tMsgResult = (STTMsg) S21CacheTBLAccessor.findByKey(stTMsg);
        // has no result
        if (tMsgResult == null) {
            bizMsg.splyStCd.setErrorInfo(1, "NWAM0181E", new String[] {bizMsg.splyStCd.getValue() });
            return false;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.stNm, tMsgResult.stNm.getValue());
        return true;
    }

    /**
     * getCtryCd
     * @param glblCmpyCd String
     * @return String
     */
    public static String getCtryCd(String glblCmpyCd) {

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);

        GLBL_CMPYTMsg tMsgResult = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);
        // has no result
        if (tMsgResult == null) {
            return null;
        }

        return tMsgResult.ctryCd.getValue();
    }

    /**
     * callAddrValidApi
     * @param bizMsg NWAL1640CMsg
     * @param glblCmpyCd String
     */
    public static void callAddrValidApi(NWAL1640CMsg bizMsg, String glblCmpyCd) {

        String ctryCd = getCtryCd(glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(ctryCd)) {
            bizMsg.setMessageInfo(NWZM0369E);
            return;
        }

        NMZC003001PMsg addrValidApiPMsg = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.firstLineAddr, bizMsg.splyFirstAddr);
        // Mod Start 2017/06/16 QC#18368
        //ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctyAddr, bizMsg.splyCtyAddr);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctyAddr, bizMsg.splyCtyAddr.getValue().toUpperCase());
        // Mod End 2017/06/16 QC#18368
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.stCd, bizMsg.splyStCd);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.postCd, bizMsg.splyPostCd);
        ZYPEZDItemValueSetter.setValue(addrValidApiPMsg.ctryCd, ctryCd);

        NMZC003001 addrValidApi = new NMZC003001();
        addrValidApi.execute(addrValidApiPMsg, ONBATCH_TYPE.ONLINE);

        if (addrValidApiPMsg.xxMsgIdList.getValidCount() > 0) {
            // QC#4505
            if (!isCntyErr(addrValidApiPMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(addrValidApiPMsg);
                String msgId = msgList.get(0).getXxMsgid();
                String[] msgPrms = msgList.get(0).getXxMsgPrmArray();
                boolean hasItemError = false;
                if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_01.getValue())) {
                    bizMsg.splyFirstAddr.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_03.getValue())) {
                    bizMsg.splyCtyAddr.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_04.getValue())) {
                    bizMsg.splyStCd.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_05.getValue())) {
                    bizMsg.splyPostCd.setErrorInfo(1, msgId, msgPrms);
                    hasItemError = true;
                }
                if (!hasItemError) {
                    bizMsg.setMessageInfo(addrValidApiPMsg.xxMsgIdList.no(0).xxMsgId.getValue());
                }
                return;
            }
        }

        setAddrValidResult(bizMsg, addrValidApiPMsg);
    }
    
    // QC#4505
    private static boolean isCntyErr(NMZC003001PMsg addrValidApiPMsg) {
        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_07.getValue())) {
            return true;
        }
        return false;
    }
    
    /**
     * setAddrValidResult
     * @param bizMsg NWAL1640CMsg
     * @param addrValidApiPMsg NMZC003001PMsg
     */
    public static void setAddrValidResult(NWAL1640CMsg bizMsg, NMZC003001PMsg addrValidApiPMsg) {

        // Check Error
        boolean hasErrorFlg = false;
        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_01.getValue())) {
            bizMsg.splyFirstAddr.setErrorInfo(1, NWZM1737E);
            hasErrorFlg = true;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_03.getValue())) {
            bizMsg.splyCtyAddr.setErrorInfo(1, NWZM1738E);
            hasErrorFlg = true;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_04.getValue())) {
            bizMsg.splyStCd.setErrorInfo(1, NWZM1736E);
            hasErrorFlg = true;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPMsg.xxVldStsCd_05.getValue())) {
            bizMsg.splyPostCd.setErrorInfo(1, NWZM1735E);
            hasErrorFlg = true;
        }

        if (hasErrorFlg) {
            return;
        }

        // Check Warning
        if (ZYPConstant.FLG_OFF_N.equals(bizMsg.xxWrnSkipFlg.getValue())) {
            if (NMZC003001Constant.RTRN_CD_SUGGEST.equals(addrValidApiPMsg.xxVldStsCd_01.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.splyFirstAddr, addrValidApiPMsg.firstLineAddr);
                bizMsg.splyFirstAddr.setErrorInfo(2, NWZM1734W);
                bizMsg.setMessageInfo(NWZM1734W);
            }

            if (NMZC003001Constant.RTRN_CD_SUGGEST.equals(addrValidApiPMsg.xxVldStsCd_03.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.splyCtyAddr, addrValidApiPMsg.ctyAddr);
                bizMsg.splyCtyAddr.setErrorInfo(2, NWZM1734W);
                bizMsg.setMessageInfo(NWZM1734W);
            }

            if (NMZC003001Constant.RTRN_CD_SUGGEST.equals(addrValidApiPMsg.xxVldStsCd_04.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.splyStCd, addrValidApiPMsg.stCd);
                bizMsg.splyStCd.setErrorInfo(2, NWZM1734W);
                bizMsg.setMessageInfo(NWZM1734W);
            }

            if (NMZC003001Constant.RTRN_CD_SUGGEST.equals(addrValidApiPMsg.xxVldStsCd_05.getValue())) {
                ZYPEZDItemValueSetter.setValue(bizMsg.splyPostCd, addrValidApiPMsg.postCd);
                bizMsg.splyPostCd.setErrorInfo(2, NWZM1734W);
                bizMsg.setMessageInfo(NWZM1734W);
            }
        }
    }
}
