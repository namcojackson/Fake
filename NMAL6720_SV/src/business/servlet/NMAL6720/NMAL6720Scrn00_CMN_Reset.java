/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.SCRN_EVENT_EDIT;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.SCRN_EVENT_NEW;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6720.NMAL6720CMsg;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/06   CUSA            Fujitsu         Create          N/A
 * 2015/10/15   Fujitsu         C.Tanaka        Update          CSA
 * 2016/04/06   Fujitsu         C.Yokoi         Update          CSA-QC#6633
 * 2016/04/27   SRAA            Y.Chen          Update          QC#7375
 *</pre>
 */
public class NMAL6720Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/04/08 CSA-QC#6633 Add Start
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        // Set default screen event.
        String xxEventNm = scrnMsg.xxScrEventNm.getValue();
        String dsAcctNum_P1 = scrnMsg.dsAcctNum_P1.getValue();
        String locNum_P1 = scrnMsg.locNum_P1.getValue();

        scrnMsg.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, xxEventNm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_P1, dsAcctNum_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_P1, locNum_P1);
        // 2016/04/08 CSA-QC#6633 Add End

        // QC#7375
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, dsAcctNum_P1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_H1, locNum_P1);
        if (ZYPCommonFunc.hasValue(locNum_P1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, SCRN_EVENT_EDIT);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, SCRN_EVENT_NEW);
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6720CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg, bizMsg.getUserID());
        scrnMsg.setFocusItem(scrnMsg.ctryCd_P1);
    }
}
