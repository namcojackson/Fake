/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1570;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1570.NWAL1570CMsg;
import business.servlet.NWAL1570.common.NWAL1570CommonLogic;
import business.servlet.NWAL1570.constant.NWAL1570Constant;
import business.servlet.NWAL1570.constant.NWAL1570Constant.RSLT_MODE;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1570Scrn01_CMN_Return
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         K.Sato          Create          N/A
 * 2018/06/05   Fujitsu         H.Tomimatsu     Update          QC#24816
 *</pre>
 */
public class NWAL1570Scrn01_CMN_Return extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1570CMsg bizMsg = null;
        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;

        if (NWAL1570Constant.SCRN_ID_02.equals(scrnMsg.xxScrId.getValue())) {

            // Mode:Summary
            scrnMsg.xxRsltModeCd.setValue(RSLT_MODE.STATUS_SUMMARY.getRsltModeCd());

            // retrieve Search Criteria
            NWAL1570CommonLogic.retrieveSearchCriteria(scrnMsg);

            bizMsg = NWAL1570CommonLogic.createSearchCMsg();
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1570BMsg scrnMsg = (NWAL1570BMsg) bMsg;
        NWAL1570CMsg bizMsg  = (NWAL1570CMsg) cMsg;

        String resultId = "";

       // 2018/06/05 S21_NA#24816 Add Start
        if (scrnMsg != null) {
            scrnMsg.xxComnColOrdTxt.clear();
        }
       // 2018/06/05 S21_NA#24816 Add End

        if (NWAL1570Constant.SCRN_ID_00.equals(scrnMsg.xxScrId.getValue())
                || NWAL1570Constant.SCRN_ID_01.equals(scrnMsg.xxScrId.getValue())) {
            // 1. initialize GUI attribute.
            NWAL1570CommonLogic.initGuiAttrScrn00(this, scrnMsg);
            // 2. initialize GUI value.
            NWAL1570CommonLogic.initGuiValueScrnRslt(scrnMsg);
            // 3. set focus.
            scrnMsg.setFocusItem(scrnMsg.xxCpoOrdNumSrchTxt_H1);

            resultId = "toNWAL1570Scrn00";

        }  else if (NWAL1570Constant.SCRN_ID_02.equals(scrnMsg.xxScrId.getValue())) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            scrnMsg.xxScrId.clear();
            resultId = "toNWAL1570Scrn01";

        } else {
            resultId = "returnToOtherScreen";

        }

        setResult(resultId);
    }
}
