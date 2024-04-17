/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2620;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2620.NMAL2620CMsg;
import business.servlet.NMAL2620.common.NMAL2620CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/27   Fujitsu         C.Yokoi         Update          N/A
 *</pre>
 */
public class NMAL2620_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NMAL2620");

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;

        NMAL2620CMsg bizMsg = new NMAL2620CMsg();
        bizMsg.setBusinessID("NMAL2620");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        NMAL2620CMsg bizMsg  = (NMAL2620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2620CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_V);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        // Search Criteria
        scrnMsg.bizAreaOrgCd_V.setNameForMessage("Business Area");
        scrnMsg.xxPsnNm_H.setNameForMessage("Resource Name(*)");
        scrnMsg.psnNum_H.setNameForMessage("Resource#(*)");
        scrnMsg.orgNm.setNameForMessage("Territory Name(*)");
        scrnMsg.psnCd.setNameForMessage("Employee ID(*)");

        // 2016/04/27 Add Start
        // Control Detail
        scrnMsg.trtyUpdModeTpCd_V.setNameForMessage("Select Mode");
        scrnMsg.rqstRsltCmntTxt.setNameForMessage("Mass Update Reason");

        // Control Detail : Move Resource
        scrnMsg.xxPsnNm_D.setNameForMessage("Move Territory To");
        scrnMsg.xxFromDt.setNameForMessage("Move Effective From");
        scrnMsg.xxThruDt.setNameForMessage("Move Effective To");

        // Control Detail : End Territories
        scrnMsg.endDt.setNameForMessage("End Date Territory on");
        // 2016/04/27 Add End
    }
}
