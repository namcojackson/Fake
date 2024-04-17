/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1740.common;

import java.util.List;
import java.util.Map;
import parts.common.EZDCStringItemArray;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * NWAL1740CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         M.Suzuki        Create          N/A
 * 2016/04/06   Fujitsu         M.Suzuki        Update          QC#5919
 *</pre>
 */
public class NWAL1740CommonLogic {
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
            //2016/04/06 S21_NA#5919 MOD Start ------------
            Map pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (String) pullDownData.get(dbColumnCd));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumnValue));
        }
    }
}
