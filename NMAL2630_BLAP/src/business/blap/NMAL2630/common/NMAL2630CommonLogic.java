/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2630.common;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import business.blap.NMAL2630.NMAL2630CMsg;

/**
 *<pre>
 * NMAL2630CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL2630CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL2630CMsg bizMsg = (NMAL2630CMsg) cMsg;

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

    //TODO [Template] if the input data have to copy to glblMsg, uncomment below method and call this method
//    /**
//     * Update the global Message.
//     * @param bizMsg NMAL2630CMsg
//     * @param glblMsg NMAL2630SMsg
//     */
//    public static void updateGlblMsg(NMAL2630CMsg bizMsg, NMAL2630SMsg glblMsg) {
//
//        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
//        }
//    }
}
