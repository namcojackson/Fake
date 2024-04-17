/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import java.util.LinkedHashMap;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CITS            N Akaishi       Create          N/A
 * 2016/09/30   CITS            S.Endo          Update          QC#8759
 *</pre>
 */
public class NPAL1500_NLBL3000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        int idx = this.getButtonSelectNumber();

        // create old map
        Map<String, String> oldMap = new LinkedHashMap<String, String>();
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).serNumListTxt_A1)
                && ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).serNumListTxt_A2)) {
            String[] items = scrnMsg.A.no(idx).serNumListTxt_A1.getValue().split(",");
            String[] pks = scrnMsg.A.no(idx).serNumListTxt_A2.getValue().split(",");
            for (int i = 0; i < items.length; i++) {
                if (i < pks.length) {
                    oldMap.put(items[i], pks[i]);
                } else {
                    oldMap.put(items[i], "");
                }
            }
        }
        Map<String, String> newMap = new LinkedHashMap<String, String>();
        for (int i = 0; i < scrnMsg.T.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.T.no(i).serNum_T)) {
                newMap.put(scrnMsg.T.no(i).serNum_T.getValue(), "");
            }
        }
        for (Map.Entry<String, String> e : newMap.entrySet()) {
            if (oldMap.containsKey(e.getKey())) {
                e.setValue(oldMap.get(e.getKey()));
            }
        }
        StringBuilder sbSerNum = new StringBuilder();
        StringBuilder sbPk = new StringBuilder();
        for (Map.Entry<String, String> e : newMap.entrySet()) {
            if (0 < sbSerNum.length()) {
                sbSerNum.append(",");
            }
            if (0 < sbPk.length()) {
                sbPk.append(",");
            }
            sbSerNum.append(e.getKey());
            sbPk.append(e.getValue());
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).serNumListTxt_A1, sbSerNum.toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).serNumListTxt_HD, sbSerNum.toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).serNumListTxt_A2, sbPk.toString());
        scrnMsg.setFocusItem(scrnMsg.A.no(idx).serNumListTxt_A1);
    }
}
