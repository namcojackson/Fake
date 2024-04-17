/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : NPAL1010
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 03/01/2016   CITS            K.Ogino          Update          QC#4641
 *</pre>
 */
public class NPAL1280_NPAL1010 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlWhCd, scrnMsg.xxPopPrm_P6);
        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlWhCd, scrnMsg.xxPopPrm_P6);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.xxPopPrm_P7);
        ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlSwhCd, scrnMsg.xxPopPrm_P8);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.xxPopPrm_P9);
        scrnMsg.setFocusItem(scrnMsg.destRtlSwhCd);

    }
}
