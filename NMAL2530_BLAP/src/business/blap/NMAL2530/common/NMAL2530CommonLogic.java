/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2530.common;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import business.blap.NMAL2530.NMAL2530CMsg;

/**
 *<pre>
 * NMAL2530CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL2530CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {


        NMAL2530CMsg bizMsg = (NMAL2530CMsg) cMsg;

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
//     * @param bizMsg NMAL2530CMsg
//     * @param glblMsg NMAL2530SMsg
//     */
//    public static void updateGlblMsg(NMAL2530CMsg bizMsg, NMAL2530SMsg glblMsg) {
//
//        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
//        }
//    }
}
