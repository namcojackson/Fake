/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3000.common;

import business.blap.NLBL3000.constant.NLBL3000Constant;

/**
 *<pre>
 *  Serial Number Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2012   Fujitsu         T.Ishii         Create          N/A
 * 07/26/2013   Fujitsu         M.Nakamura      Update          R-OM031
 *</pre>
 */
public class NLBL3000CommonLogic implements NLBL3000Constant {

    // 07/26/2013 R-OM031 Del Start
//    /**
//     * loadOnePageToCMsg
//     * @param cMsg EZDCMsg
//     * @param cMsgArray EZDCMsgArray
//     * @param sMsgArray EZDSMsgArray
//     */
//    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {
//
//        NLBL3000CMsg bizMsg = (NLBL3000CMsg) cMsg;
//
//        ZYPTableUtil.clear(cMsgArray);
//
//        bizMsg.xxPageShowToNum.clear();
//        bizMsg.xxPageShowOfNum.clear();
//
//        int maxDisplayRows = cMsgArray.length();
//        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;
//
//        int i = startIndex;
//        for (; i < startIndex + cMsgArray.length(); i++) {
//
//            if (i < sMsgArray.getValidCount()) {
//
//                EZDMsg csMsg = sMsgArray.get(i);
//                int indexOfCMsg = i - startIndex;
//
//                EZDMsg.copy(csMsg, null, cMsgArray.get(indexOfCMsg), null);
//
//            } else {
//
//                break;
//            }
//        }
//
//        cMsgArray.setValidCount(i - startIndex);
//        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
//        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
//        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
//    }
    // 07/26/2013 R-OM031 Del End
}
