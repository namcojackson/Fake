/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6160;

import static business.blap.NMAL6160.constant.NMAL6160Constant.BIZ_ID_NMAL2630;
import static business.blap.NMAL6160.constant.NMAL6160Constant.BIZ_ID_NMAL6760;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL6160.common.NMAL6160CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Multi Candinate Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL6160BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL6160CMsg bizMsg = (NMAL6160CMsg) cMsg;
            NMAL6160SMsg glblMsg = (NMAL6160SMsg) sMsg;

            if ("NMAL6160_INIT".equals(screenAplID)) {
                doProcess_NMAL6160_INIT(bizMsg, glblMsg);

            } else if ("NMAL6160Scrn00_Filter".equals(screenAplID)) {
                doProcess_NMAL6160Scrn00_Filter(bizMsg, glblMsg);

            } else if ("NMAL6160Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL6160_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL6160Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6160Scrn00_PageNext(bizMsg, glblMsg);

            } else if ("NMAL6160Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6160Scrn00_PagePrev(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6160_INIT(NMAL6160CMsg bizMsg, NMAL6160SMsg glblMsg) {
        EZDMsg.copy(bizMsg.Z, "Z", glblMsg.A, "A");
        EZDMsg.copy(bizMsg.Z, null, glblMsg.Z, null);

        bizMsg.xxPageShowFromNum.setValue(1);

        if (BIZ_ID_NMAL6760.equals(bizMsg.bizId.getValue())) {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, "A", "A");
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm, "A");

        } else if (BIZ_ID_NMAL2630.equals(bizMsg.bizId.getValue())) {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.A, "A", "B");
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm, "B");
        } else {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.A, "A", "C");
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm, "C");
        }

        ZYPTableUtil.clear(bizMsg.Z);
    }

    /**
     * Filter Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6160Scrn00_Filter(NMAL6160CMsg bizMsg, NMAL6160SMsg glblMsg) {
        String matchStr = bizMsg.xxDsMsgEntryTxt.getValue().replace("%", ".*");

        int j = 0;
        for (int i = 0; i < glblMsg.Z.getValidCount(); i++) {
            String compareTxt = glblMsg.Z.no(i).xxDsMsgEntryTxt_Z.getValue();
            if (compareTxt.matches(matchStr)) {
                EZDMsg.copy(glblMsg.Z.no(i), "Z", glblMsg.A.no(j), "A");
                j++;
            }
        }

        glblMsg.A.setValidCount(j);
        bizMsg.xxPageShowFromNum.setValue(1);

        if (BIZ_ID_NMAL6760.equals(bizMsg.bizId.getValue())) {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, "A", "A");
        } else if (BIZ_ID_NMAL2630.equals(bizMsg.bizId.getValue())) {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.A, "A", "B");
        } else {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.A, "A", "C");
        }

    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6160_CMN_Clear(NMAL6160CMsg bizMsg, NMAL6160SMsg glblMsg) {
        bizMsg.xxDsMsgEntryTxt.clear();

        EZDMsg.copy(glblMsg.Z, "Z", glblMsg.A, "A");

        bizMsg.xxPageShowFromNum.setValue(1);

        if (BIZ_ID_NMAL6760.equals(bizMsg.bizId.getValue())) {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, "A", "A");
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm, "A");

        } else if (BIZ_ID_NMAL2630.equals(bizMsg.bizId.getValue())) {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.A, "A", "B");
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm, "B");
        } else {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.A, "A", "C");
            ZYPEZDItemValueSetter.setValue(bizMsg.xxTblNm, "C");
        }

        ZYPTableUtil.clear(bizMsg.Z);
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6160Scrn00_PageNext(NMAL6160CMsg bizMsg, NMAL6160SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        if (BIZ_ID_NMAL6760.equals(bizMsg.bizId.getValue())) {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, "A", "A");
        } else if (BIZ_ID_NMAL2630.equals(bizMsg.bizId.getValue())) {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.A, "A", "B");
        } else {
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.A, "A", "C");
        }

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6160Scrn00_PagePrev(NMAL6160CMsg bizMsg, NMAL6160SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg

        if (BIZ_ID_NMAL6760.equals(bizMsg.bizId.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A, "A", "A");
        } else if (BIZ_ID_NMAL2630.equals(bizMsg.bizId.getValue())) {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.B.length());
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.B, glblMsg.A, "A", "B");
        } else {
            bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.C.length());
            NMAL6160CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.C, glblMsg.A, "A", "C");
        }

    }

}
