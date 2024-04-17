/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFBL2120.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import business.blap.NFBL2120.NFBL2120CMsg;
import business.blap.NFBL2120.NFBL2120SMsg;
import business.blap.NFBL2120.NFBL2120_ASMsg;

/**
 *<pre>
 * NFBL2120CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NFBL2120CommonLogic {

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NFBL2120CMsg bizMsg = (NFBL2120CMsg) cMsg;

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
     * setSeachResult
     * @param resultList List<Map<?, ?>>
     * @param glblMsg NMAL7080SMsg
     * @param bizMsg NMAL7080CMsg
     */
    public static void setSeachResult(List<Map< ? , ? >> resultList, NFBL2120SMsg glblMsg, NFBL2120CMsg bizMsg) {

        int i = 0;
        for (Map< ? , ? > result : resultList) {
            NFBL2120_ASMsg glblLineMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrDply_A1, (String) result.get("ATT_DATA_NM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.docMgtCatgDescTxt_A1, (String) result.get("DOC_MGT_CATG_DESC_TXT"));

            i++;
            if (i >= glblMsg.A.length()) {
                break;
            }
        }
    }

    /**
     * nvl BigDecimal
     * @param val BigDecimal
     * @return int
     */
    public static int nvlBigDecimal(BigDecimal val) {

        if (ZYPCommonFunc.hasValue(val)) {
            return val.intValue();
        }
        return 0;
    }
}
