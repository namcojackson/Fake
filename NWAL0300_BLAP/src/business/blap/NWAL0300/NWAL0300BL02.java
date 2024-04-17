/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/15   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
package business.blap.NWAL0300;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL0300.constant.NWAL0300Constant;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001OrderTakeMdseTBLAccessor;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * NWAL0300BL02
 */
public class NWAL0300BL02 extends S21BusinessHandler implements NWAL0300Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        NWAL0300CMsg bizMsg = (NWAL0300CMsg) cMsg;

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NWAL0300_INIT".equals(screenAplID)) {
                doProcessNWAL0300_INIT(bizMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcessNWAL0300_INIT
     * @param bizMsg NWAL0300CMsg
     */
    private void doProcessNWAL0300_INIT(NWAL0300CMsg bizMsg) {

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        checkInitItem(bizMsg);

        if (!getRecord(bizMsg)) {
            return;
        }
    }

    /**
     * checkInitItem
     * @param bizMsg NWAL0300CMsg
     */
    private void checkInitItem(NWAL0300CMsg bizMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxReadOnlyFlg.getValue())) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.rvwPsnCd_H2, bizMsg.getUserID());
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21UserInfo s21UserInfo = profileService.getContextUserInfo();
        ZYPEZDItemValueSetter.setValue(bizMsg.rvwPsnNm_H2, s21UserInfo.getFullName());
        ZYPEZDItemValueSetter.setValue(bizMsg.rvwDt_H2, ZYPDateUtil.getSalesDate());

        if (!ZYPCommonFunc.hasValue(bizMsg.rvwPsnCd)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rvwPsnCd, bizMsg.rvwPsnCd_H2);
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.rvwPsnNm)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rvwPsnNm, bizMsg.rvwPsnNm_H2);
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.rvwDt)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.rvwDt, bizMsg.rvwDt_H2);
        }
    }

    /**
     * getRecord
     * @param bizMsg NWAL0300CMsg
     * @return boolean (true: Success, false: Error)
     */
    private boolean getRecord(NWAL0300CMsg bizMsg) {
        boolean isSuccess = true;

        if (!getHld(bizMsg)) {
            isSuccess = false;
        }

        if (!getCodeName(bizMsg)) {
            isSuccess = false;
            bizMsg.setMessageInfo("NWAM0008E");
        }

        return isSuccess;
    }

    /**
     *  getHld
     * @param bizMsg NWAL0300CMsg
     * @return boolean (true: Success, false: Error)
     */
    private boolean getHld(NWAL0300CMsg bizMsg) {
        S21SsmEZDResult ssmResult;

        if (ZYPCommonFunc.hasValue(bizMsg.hldPk)) {
            ssmResult = NWAL0300Query.getInstance().getHld(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.setMessageInfo("NWAM0270E");
                return false;
            }
        } else {
            bizMsg.setMessageInfo("NWAM0270E");
            return false;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.cpoDtlLineNum) && ZYPCommonFunc.hasValue(bizMsg.cpoDtlLineSubNum)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxDtlSubNum, concatString(bizMsg.cpoDtlLineNum.getValue(), bizMsg.cpoDtlLineSubNum.getValue(), STR_PERIOD));
        }

        if (!getOrderInfo(bizMsg)) {
            bizMsg.setMessageInfo("NWAM0270E");
            return false;
        }

        ssmResult = NWAL0300Query.getInstance().getCPOOrdTp(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo("NWAM0008E");
            return false;
        }

        return true;
    }

    /**
     * getOrderInfo
     * @param bizMsg NWAL0300CMsg
     */
    private boolean getOrderInfo(NWAL0300CMsg bizMsg) {

        S21SsmEZDResult ssmResult;

        if (ZYPCommonFunc.hasValue(bizMsg.cpoDtlLineNum)) {
            ssmResult = NWAL0300Query.getInstance().getCPODtlOrderInfo(bizMsg);
        } else {
            ssmResult = NWAL0300Query.getInstance().getCPOOrderInfo(bizMsg);
        }
        if (ssmResult.isCodeNotFound()) {
            return false;
        }
        return true;
    }

    /**
     * getCodeName
     * @param bizMsg NWAL0300CMsg
     * @return boolean (true: Success, false: Error)
     */
    private boolean getCodeName(NWAL0300CMsg bizMsg) {
        boolean isSuccess = true;

        S21SsmEZDResult ssmResult = NWAL0300Query.getInstance().getSellToCust(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.xxLocAddrNm_H1.clear();
            isSuccess = false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxLocAddrNm_H1, concatString(bizMsg.locNm_H1.getValue(), bizMsg.addlLocNm_H1.getValue(), STR_SLASH));
        }

        ssmResult = NWAL0300Query.getInstance().getBillToCust(bizMsg);
        if (ssmResult.isCodeNotFound()) {
            bizMsg.xxLocAddrNm_H2.clear();
            isSuccess = false;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxLocAddrNm_H2, concatString(bizMsg.locNm_H2.getValue(), bizMsg.addlLocNm_H2.getValue(), STR_SLASH));
        }

        if (!getShipToCustName(bizMsg)) {
            isSuccess = false;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd)) {
            Map<String, String> ssmParam = new HashMap<String, String>();
            ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
            ssmParam.put("mdseCd", NWXC001001OrderTakeMdseTBLAccessor.findMdseCd(bizMsg.glblCmpyCd.getValue(), bizMsg.mdseCd.getValue()));
            ssmResult = NWAL0300Query.getInstance().getMdse(ssmParam, bizMsg);
            if (ssmResult.isCodeNotFound()) {
                isSuccess = false;
            }
        }

        return isSuccess;
    }

    /**
     * getShipToCustName
     * @param bizMsg NWAL0300CMsg
     * @return boolean (true: Success, false: Error)
     */
    private Boolean getShipToCustName(NWAL0300CMsg bizMsg) {
        boolean isSuccess = true;

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.dropShipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxLocAddrNm_H3, concatString(bizMsg.shipToLocNm.getValue(), bizMsg.shipToAddlLocNm.getValue(), STR_SLASH));
        } else {
            S21SsmEZDResult ssmResult = NWAL0300Query.getInstance().getShipToCust(bizMsg);
            if (ssmResult.isCodeNotFound()) {
                bizMsg.xxLocAddrNm_H3.clear();
                isSuccess = false;
            } else {
                ZYPEZDItemValueSetter.setValue(bizMsg.xxLocAddrNm_H3, concatString(bizMsg.locNm_H3.getValue(), bizMsg.addlLocNm_H3.getValue(), STR_SLASH));
            }
        }
        return isSuccess;
    }

    /**
     * concatString
     * @param val1 String
     * @param val2 String
     * @param item String(Not Null)
     * @return String(val1 + item + val2)
     */
    private String concatString(String val1, String val2, String item) {
        return nvl(val1).trim().concat(item).concat(nvl(val2).trim());
    }

    /**
     * nvl
     * @param val
     * @return String
     */
    private String nvl(String val) {
        if (val == null) {
            return "";
        }
        return val;
    }
}
