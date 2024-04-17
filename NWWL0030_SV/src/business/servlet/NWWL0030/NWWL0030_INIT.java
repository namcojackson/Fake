/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0030;

import static business.servlet.NWWL0030.constant.NWWL0030Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0030.NWWL0030CMsg;
import business.servlet.NWWL0030.common.NWWL0030CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWWL0030_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0030_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0030BMsg scrnMsg = (NWWL0030BMsg) bMsg;
        NWWL0030CMsg bizMsg = new NWWL0030CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0030BMsg scrnMsg = (NWWL0030BMsg) bMsg;
        NWWL0030CMsg bizMsg = (NWWL0030CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWWL0030CommonLogic.initCmnBtnProp(this);
        NWWL0030CommonLogic.controlScreenFields(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.ntfyHdrNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWWL0030BMsg scrnMsg = (NWWL0030BMsg) bMsg;

        scrnMsg.ntfyHdrNm.setNameForMessage("Name");
        scrnMsg.ntfyHdrDescTxt.setNameForMessage("Description");
        scrnMsg.ntfyBizAreaTpCd.setNameForMessage("Business Area");
        scrnMsg.ntfySubAreaTpCd.setNameForMessage("Sub Area");
        scrnMsg.effFromDt.setNameForMessage("Date Range From");
        scrnMsg.effThruDt.setNameForMessage("Date Range Thru");
        scrnMsg.ntfyRunJobId.setNameForMessage("Job ID");
    }
}
