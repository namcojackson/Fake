/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0370;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSBL0370.common.NSBL0370CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Hourly Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/24   Hitachi         T.Mizuki        Create          N/A
 *</pre>
 */
public class NSBL0370BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NSBL0370CMsg cMsg = (NSBL0370CMsg) arg0;
        NSBL0370SMsg sMsg = (NSBL0370SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0370_INIT".equals(screenAplID)) {
                doProcess_NSBL0370_Init(cMsg, sMsg);
            } else if ("NSBL0370Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0370_PageNext(cMsg, sMsg);
            } else if ("NSBL0370Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0370_PagePrev(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0370_Init(NSBL0370CMsg cMsg, NSBL0370SMsg sMsg) {

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(cMsg.cratDt_H)) {
            setValue(cMsg.cratDt_H, ZYPDateUtil.getSalesDate());
        }

        NSBL0370Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (cMsg.getMessageCode().equals("NZZM0000E")) {
            return;
        }

        sMsg = NSBL0370CommonLogic.hhmmFormat(sMsg);

        EZDMsg.copy(sMsg, null, cMsg, null);
        NSBL0370CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0370_PagePrev(NSBL0370CMsg cMsg, NSBL0370SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0370CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0370CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0370_PageNext(NSBL0370CMsg cMsg, NSBL0370SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0370CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0370CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }
}
