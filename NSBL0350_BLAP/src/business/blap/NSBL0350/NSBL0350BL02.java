/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0350;

import static business.blap.NSBL0350.constant.NSBL0350Constant.NSBM0025E;
import static business.blap.NSBL0350.constant.NSBL0350Constant.ORG_CD;
import static business.blap.NSBL0350.constant.NSBL0350Constant.DS_ORG_UNIT;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0350.common.NSBL0350CommonLogic;
import business.db.DS_ORG_UNITTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Group Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/18   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/31   Hitachi         K.Yamada        Update          CSA QC#6082
 *</pre>
 */
public class NSBL0350BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0350CMsg cMsg = (NSBL0350CMsg) arg0;
        NSBL0350SMsg sMsg = (NSBL0350SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0350_INIT".equals(screenAplID)) {
                doProcess_NSBL0350_INIT(cMsg, sMsg);
            } else if ("NSBL0350Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSBL0350Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NSBL0350Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0350Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0350Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0350Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSBL0350Scrn00_Search".equals(screenAplID)) {
                doProcess_NSBL0350Scrn00_Search(cMsg, sMsg);
            } else if ("NSBL0350Scrn00_Search_SvcGrp".equals(screenAplID)) {
                doProcess_NSBL0350Scrn00_Search_SvcGrp(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0350_INIT(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {

        NSBL0350CommonLogic.clearMsg(cMsg, sMsg);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        NSBL0350CommonLogic.setInitParams(cMsg, sMsg);

        NSBL0350CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0350Scrn00_CMN_Clear(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {
        NSBL0350CommonLogic.clearMsg(cMsg, sMsg);
        NSBL0350CommonLogic.setInitParams(cMsg, sMsg);
        NSBL0350CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0350Scrn00_PageNext(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0350CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NSBL0350CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0350Scrn00_PagePrev(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0350CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NSBL0350CommonLogic.pagenation(cMsg, sMsg, pagenationFrom);
    }

    private void doProcess_NSBL0350Scrn00_Search(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {
        // set As of Date
        if (!NSBL0350CommonLogic.hasValueItems(cMsg.xxFromDt_H)) {
            setValue(cMsg.xxFromDt_H, cMsg.slsDt);
        }
        // check SvcGrp
        if (ZYPCommonFunc.hasValue(cMsg.orgCd_HT)) {
            DS_ORG_UNITTMsg result = NSBL0350CommonLogic.isExistSvcGrp(cMsg);
            if (result == null) {
                cMsg.orgCd_HT.setErrorInfo(1, NSBM0025E, new String[] {ORG_CD, DS_ORG_UNIT });
                return;
            }
            // mod start 2016/03/31 CSA Defect#6082
            setValue(cMsg.orgDescTxt_H, result.orgNm);
            // mod end 2016/03/31 CSA Defect#6082
        }

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();

        NSBL0350CommonLogic.getSearchData(cMsg, sMsg);

        NSBL0350CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0350Scrn00_Search_SvcGrp(NSBL0350CMsg cMsg, NSBL0350SMsg sMsg) {
        DS_ORG_UNITTMsg result = NSBL0350CommonLogic.isExistSvcGrp(cMsg);
        if (result == null) {
            cMsg.orgCd_HT.setErrorInfo(1, NSBM0025E, new String[] {ORG_CD, DS_ORG_UNIT });
            return;
        }
        // mod start 2016/03/31 CSA Defect#6082
        setValue(cMsg.orgDescTxt_H, result.orgNm);
        // mod end 2016/03/31 CSA Defect#6082
    }
}
