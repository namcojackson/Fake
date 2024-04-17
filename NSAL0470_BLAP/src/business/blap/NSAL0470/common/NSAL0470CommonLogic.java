/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0470.common;

import static business.blap.NSAL0470.constant.NSAL0470Constant.BUSINESS_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.List;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;

import business.blap.NSAL0470.NSAL0470CMsg;
import business.blap.NSAL0470.NSAL0470SMsg;
import business.parts.NSZC047099PMsg;
import parts.common.EZDMsg;

/** 
 *<pre>
 * Complete Contract
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Yamada        Create          N/A
 * 2020/03/09   CITS            T.Wada          Update          QC#55830
 *</pre>
 */
public class NSAL0470CommonLogic {

    /**
     * copyCMsgToSMsg
     * @param cMsg NSAL0470CMsg
     * @param sMsg NSAL0470SMsg
     * @param pagenationFrom int
     */
    public static void copyCMsgToSMsg(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;

        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * copySMsgToCMsg
     * @param cMsg NSAL0470CMsg
     * @param sMsg NSAL0470SMsg
     * @param pagenationFrom int
     */
    public static void copySMsgToCMsg(NSAL0470CMsg cMsg, NSAL0470SMsg sMsg, int pagenationFrom) {

        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pagenationFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

     // QC#55830 Add Start
    /**
     * callBllgSchdApiRecovMode
     * @param glblCmpyCode
     * @param cMsg
     * @param dsContrPk
     * @return
     */
    public static boolean callBllgSchdApiRecovMode(String glblCmpyCode, NSAL0470CMsg cMsg, BigDecimal dsContrPk) {
        NSZC047099PMsg pMsg = new NSZC047099PMsg();
        setValue(pMsg.glblCmpyCd, glblCmpyCode);
        setValue(pMsg.xxModeCd, "99");
        setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCode, BUSINESS_ID));
        setValue(pMsg.dsContrPk, dsContrPk);
        NSZC047001 api = new NSZC047001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            S21ApiMessage msg = msgList.get(0);
            cMsg.setMessageInfo(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            return false;
        }
        return true;
    }
    // QC#55830 Add End

}
