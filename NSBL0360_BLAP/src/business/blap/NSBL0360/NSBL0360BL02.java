/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0360;


import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0360.common.NSBL0360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;


/**
 *<pre>
 * Sub Group Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSBL0360BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSBL0360CMsg cMsg = (NSBL0360CMsg) arg0;
        NSBL0360SMsg sMsg = (NSBL0360SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0360_INIT".equals(screenAplID)) {
                doProcess_NSBL0360_Init(cMsg, sMsg);
            } else if ("NSBL0360Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0360_PageNext(cMsg, sMsg);
            } else if ("NSBL0360Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0360_PagePrev(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0360_Init(NSBL0360CMsg cMsg, NSBL0360SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(cMsg.cratDt_H)) {
            setValue(cMsg.cratDt_H, ZYPDateUtil.getSalesDate());
        }
        NSBL0360Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);

        sMsg = NSBL0360CommonLogic.hhmmFormat(sMsg);

        EZDMsg.copy(sMsg, null, cMsg, null);
        NSBL0360CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0360_PagePrev(NSBL0360CMsg cMsg, NSBL0360SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0360CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0360CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0360_PageNext(NSBL0360CMsg cMsg, NSBL0360SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0360CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0360CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }
}
