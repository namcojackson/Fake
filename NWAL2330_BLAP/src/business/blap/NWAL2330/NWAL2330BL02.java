/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2330;

import static business.blap.NWAL2330.constant.NWAL2330Constant.NZZM0000E;
import static business.blap.NWAL2330.constant.NWAL2330Constant.NZZM0001W;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import business.blap.NWAL2330.common.NWAL2330CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL2330BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NWAL2330BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2330CMsg bizMsg = (NWAL2330CMsg) cMsg;

            if ("NWAL2330Scrn00_Search".equals(screenAplID)) {
                doProcess_NWAL2330Scrn00_Search(bizMsg);

            } else if ("NWAL2330Scrn00_Select_OrderCategory".equals(screenAplID)) {
                doProcess_NWAL2330Scrn00_Select_OrderCategory(bizMsg);

            } else if ("NWAL2330_INIT".equals(screenAplID)) {
                doProcess_NWAL2330_INIT(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2330Scrn00_Search(NWAL2330CMsg bizMsg) {
        // search
        search(bizMsg);
    }

    /**
     * Select_OrderCategory Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2330Scrn00_Select_OrderCategory(NWAL2330CMsg bizMsg) {

        EZDCStringItem selectCd = bizMsg.dsOrdCatgCd_H1;
        if (ZYPCommonFunc.hasValue(selectCd)) {
            if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd_BK)) {
                if (selectCd.getValue().equals(bizMsg.dsOrdCatgCd_BK.getValue())) {
                    return;
                } else {
                    // Create Pull Down.
                    NWAL2330CommonLogic.createPullDown(bizMsg);
                }
            } else {
                // Create Pull Down.
                NWAL2330CommonLogic.createPullDown(bizMsg);
            }
        } else {
            if (ZYPCommonFunc.hasValue(bizMsg.dsOrdCatgCd_BK)) {
                bizMsg.dsOrdCatgCd_BK.clear();
                // Create Pull Down.
                NWAL2330CommonLogic.createPullDown(bizMsg, false);
            } else {
                return;
            }
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NWAL2330_INIT(NWAL2330CMsg bizMsg) {
        // Create Pull Down.
        NWAL2330CommonLogic.createPullDown(bizMsg, true);

        if (ZYPCommonFunc.hasValue(bizMsg.cpoOrdNum_BK)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.cpoOrdNum_H1, bizMsg.cpoOrdNum_BK);
            doProcess_NWAL2330Scrn00_Search(bizMsg);
        } else {
            ZYPTableUtil.clear(bizMsg.A);
        }
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     */
    private void search(NWAL2330CMsg bizMsg) {

        ZYPTableUtil.clear(bizMsg.A);

        S21SsmEZDResult ssmResult = NWAL2330Query.getInstance().search(bizMsg);

        if (ssmResult.isCodeNotFound()) {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        } else {
              if (ssmResult.getQueryResultCount() >= bizMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                bizMsg.A.setValidCount(bizMsg.A.length() - 1);
            } else {
                bizMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }
        }
    }

}
