/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0380;

import static business.blap.NSBL0380.constant.NSBL0380Constant.DS_ORG_UNIT;
import static business.blap.NSBL0380.constant.NSBL0380Constant.NSBM0025E;
import static business.blap.NSBL0380.constant.NSBL0380Constant.ORG_CD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0380.common.NSBL0380CommonLogic;
import business.db.DS_ORG_UNITTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * History Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0380BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0380CMsg cMsg = (NSBL0380CMsg) arg0;
        NSBL0380SMsg sMsg = (NSBL0380SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0380_INIT".equals(screenAplID)) {
                doProcess_NSBL0380_INIT(cMsg, sMsg);
            } else if ("NSBL0380Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0380Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSBL0380Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0380Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0380Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0380Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0380Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0380Scrn00_Search(cMsg, sMsg);
            } else if ("NSBL0380Scrn00_Search_SvcGrp".equals(screenAplID)) {
                doProcess_NSBL0380Scrn00_Search_SvcGrp(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0380_INIT(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {

        NSBL0380CommonLogic.clearMsg(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        if (!NSBL0380CommonLogic.isErrorSearchCondition(cMsg)) {
            ZYPTableUtil.clear(cMsg.A);
        } else {
            NSBL0380CommonLogic.findItems(cMsg, sMsg);
            EZDMsg.copy(sMsg, null, cMsg, null);
            NSBL0380CommonLogic.pagenation(cMsg, sMsg, 0);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        }
    }

    private void doProcess_NSBL0380Scrn00_CMN_Clear(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {
        NSBL0380CommonLogic.clearMsg(cMsg, sMsg);
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);
    }

    private void doProcess_NSBL0380Scrn00_PageNext(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0380CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0380CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0380Scrn00_PagePrev(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0380CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0380CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0380Scrn00_Search(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        NSBL0380CommonLogic.setSearchParams(cMsg, sMsg);
        if (NSBL0380CommonLogic.checkParams(cMsg)) {
            NSBL0380CommonLogic.clearMsg(cMsg, sMsg);

            NSBL0380CommonLogic.findItems(cMsg, sMsg);
            EZDMsg.copy(sMsg, null, cMsg, null);
            NSBL0380CommonLogic.pagenation(cMsg, sMsg, 0);
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        } else {
            return;
        }
    }

    private void doProcess_NSBL0380Scrn00_Search_SvcGrp(NSBL0380CMsg cMsg, NSBL0380SMsg sMsg) {
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        DS_ORG_UNITTMsg result = NSBL0380CommonLogic.getDsOrgUnit(cMsg);
        if (result == null) {
            cMsg.orgCd_HT.setErrorInfo(1, NSBM0025E, new String[] {ORG_CD, DS_ORG_UNIT });
            return;
        }
        setValue(cMsg.orgDescTxt_H, result.orgNm);
    }
}
