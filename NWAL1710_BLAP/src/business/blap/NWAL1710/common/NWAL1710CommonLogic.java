/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1710.common;

import static business.blap.NWAL1710.constant.NWAL1710Constant.YYYYMMDD_LENGTH;
import java.util.List;
import java.util.Map;
import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NWAL1710.NWAL1710CMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * NWAL1710CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/06   Fujitsu         M.Suzuki        Update          S21_NA#5919
 *</
 *</pre>
 */
public class NWAL1710CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NWAL1710CMsg bizMsg = (NWAL1710CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
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
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumnCd String
     * @param dbColumnValue String
     */
    public static void createPulldownList(EZDCStringItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String dbColumnCd, String dbColumnValue) {

        for (int i = 0; i < pullDownList.size(); i++) {
            //2016/04/06 S21_NA#5919 MOD Start ------------
            if (i >= cd.length()) {
                break;
            }
            //2016/04/06 S21_NA#5919 MOD End --------------
            Map pullDownData = pullDownList.get(i);
            ZYPEZDItemValueSetter.setValue(cd.no(i), (String) pullDownData.get(dbColumnCd));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumnValue));
        }
    }

    /**
     * formatDt
     * @param dt DateItem
     * @return DateItem
     */
    public static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";

        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }

}
