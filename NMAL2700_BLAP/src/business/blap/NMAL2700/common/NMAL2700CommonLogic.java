/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2700.common;

import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import business.blap.NMAL2700.NMAL2700CMsg;

/**
 *<pre>
 * NMAL2700CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL2700CommonLogic {
    /**
     * isExistRecord
     * @param bizMsg NMAL2700CMsg
     * @param idx int
     * @return boolean
     */
    public static boolean isExistRecord(NMAL2700CMsg bizMsg, int idx) {
        if (!ZYPCommonFunc.hasValue(bizMsg.A.no(idx).xxRowId_A.getValue())) {
            return false;
        }
        return true;
    }

    /**
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownList(EZDCStringItemArray cd, EZDCStringItemArray value, List<Map<String, Object>> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map<String, Object> pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (String) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumn[1]));

            if (i >= cd.length()) {
                break;
            }
        }
    }
}
