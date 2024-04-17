/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6140;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.STTMsg;
import business.db.STTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL6140BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Tanaka        Create          N/A
 *</pre>
 */
public class NMAL6140BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL6140CMsg bizMsg = (NMAL6140CMsg) cMsg;

            if ("NMAL6140_INIT".equals(screenAplID)) {
                doProcess_NMAL6140_INIT(bizMsg);

            } else if ("NMAL6140Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL6140Scrn00_CMN_Clear(bizMsg);

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
     */
    private void doProcess_NMAL6140_INIT(NMAL6140CMsg bizMsg) {
        setStatePullDown(bizMsg);
    }

    /**
     * Clear Evet
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL6140Scrn00_CMN_Clear(NMAL6140CMsg bizMsg) {
        bizMsg.clear();
        setStatePullDown(bizMsg);
    }

    private void setStatePullDown(NMAL6140CMsg bizMsg) {

        STTMsg stTMsg = new STTMsg();
        stTMsg.setSQLID("001");
        stTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());

        STTMsgArray stTMsgArray = (STTMsgArray) EZDTBLAccessor.findByCondition(stTMsg);
        if (stTMsgArray.length() == 0) {
            bizMsg.setMessageInfo("NMAM8111E", new String[] {"State" });
            return;
        }

        String stCd;
        for (int i = 0; i < stTMsgArray.getValidCount(); i++) {
            stCd = stTMsgArray.no(i).stCd.getValue();
            ZYPEZDItemValueSetter.setValue(bizMsg.stCd_H1.no(i), stCd);
            ZYPEZDItemValueSetter.setValue(bizMsg.stCd_H2.no(i), stCd);
        }
    }
}
