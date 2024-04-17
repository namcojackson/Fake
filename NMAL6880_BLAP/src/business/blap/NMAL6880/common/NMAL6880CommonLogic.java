/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6880.common;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NMAL6880.NMAL6880CMsg;
import business.blap.NMAL6880.NMAL6880SMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 * 05/23/2016   CITS            K.Ogino         Create          QC#8435
 *</pre>
 */
public class NMAL6880CommonLogic {

    /**
     * Update the global Message.
     * @param bizMsg NMAL6880CMsg
     * @param glblMsg NMAL6880SMsg
     */
    public static void updateGlblMsg(NMAL6880CMsg bizMsg, NMAL6880SMsg glblMsg) {
        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
    }

    /**
     * loadOnePageToCMsg
     * @param bizMsg NMAL6880CMsg
     * @param cMsgArray EZDCMsgArray
     * @param glblMsg NMAL6880SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NMAL6880CMsg bizMsg, EZDCMsgArray cMsgArray, NMAL6880SMsg glblMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

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
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }
}
