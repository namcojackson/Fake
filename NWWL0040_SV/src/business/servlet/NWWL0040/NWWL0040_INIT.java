/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0040;

import static business.servlet.NWWL0040.constant.NWWL0040Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0040.NWWL0040CMsg;
import business.servlet.NWWL0040.common.NWWL0040CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWWL0040_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0040_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;
        NWWL0040CMsg bizMsg = new NWWL0040CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;
        NWWL0040CMsg bizMsg = (NWWL0040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWWL0040CommonLogic.initCmnBtnProp(this);

        scrnMsg.setFocusItem(scrnMsg.ntfyDistListNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWWL0040BMsg scrnMsg = (NWWL0040BMsg) bMsg;
        scrnMsg.ntfyDistListNm.setNameForMessage("Dist Name");
        scrnMsg.ntfyDistListDescTxt.setNameForMessage("Dist Desc");
        scrnMsg.ntfyBizAreaTpCd_D.setNameForMessage("Distribution Business Area");
        scrnMsg.ntfySubAreaTpCd_D.setNameForMessage("Distribution Sub Area");
        scrnMsg.effFromDt_D.setNameForMessage("Distribution Start Date");
        scrnMsg.effThruDt_D.setNameForMessage("Distribution End Date");
        scrnMsg.ntfyDistListActvFlg.setNameForMessage("Distribution Enable");

        scrnMsg.ntfyHdrNm.setNameForMessage("Notif Name");
        scrnMsg.ntfyHdrDescTxt.setNameForMessage("Notif Description");
        scrnMsg.ntfyBizAreaTpCd_N.setNameForMessage("Notification Business Area");
        scrnMsg.ntfySubAreaTpCd_N.setNameForMessage("Notification Sub Area");
        scrnMsg.effFromDt_N.setNameForMessage("Notification Start Date");
        scrnMsg.effThruDt_N.setNameForMessage("Notification End Date");
        scrnMsg.ntfyHdrActvFlg.setNameForMessage("Notification Enable");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).ntfyDistListId_A.setNameForMessage("Dist ID");
        }
    }

}
