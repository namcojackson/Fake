/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL0090.common;

import parts.common.EZDCStringItemArray;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   CITS            Y.Nomura        Update          for CSA
 *</pre>
 */
public class NLBL0090CommonLogic {

    /**
     * <pre>
     * initPullDownCreate
     * </pre>
     * @param cMsgValue1 EZDCStringItemArray
     * @param cMsgValue2 EZDCStringItemArray
     * @param pullDownList pullDownList
     */
    public static void initPullDownCreate(EZDCStringItemArray cMsgValue1, EZDCStringItemArray cMsgValue2, String[][] pullDownList) {

        for (int i = 0; i < cMsgValue1.length(); i++) {

            if (i < pullDownList.length) {
                cMsgValue1.no(i).setValue(pullDownList[i][0]);

                EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
                cMsgValue2.no(i).setValue(converter.convLabel2i18nLabel("", pullDownList[i][1]));

            } else {
                break;
            }
        }
    }

}
