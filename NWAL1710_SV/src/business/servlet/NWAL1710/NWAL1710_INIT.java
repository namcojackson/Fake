/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1710;

import static business.servlet.NWAL1710.constant.NWAL1710Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1710.NWAL1710CMsg;
import business.servlet.NWAL1710.common.NWAL1710CommonLogic;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1710_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/09   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NWAL1710_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
         checkBusinessAppGranted(getContextUserInfo().getUserId(),
         BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        NWAL1710CMsg bizMsg = new NWAL1710CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        NWAL1710CMsg bizMsg = (NWAL1710CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.actvFlg.setValue(ZYPConstant.FLG_ON_Y);
        NWAL1710CommonLogic.initCmnBtnProp(this);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsOrdCatgCd_A.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdCatgNm_A.setInputProtected(true);
            scrnMsg.A.no(i).ordProcTpNm_A.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdRsnGrpNm_A.setInputProtected(true);
            scrnMsg.A.no(i).dsOrdTpDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).lineBizTpNm_A.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A.setInputProtected(true);
            scrnMsg.A.no(i).xxYesNoNm_A.setInputProtected(true);
        }
        scrnMsg.setFocusItem(scrnMsg.dsOrdCatgNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1710BMsg scrnMsg = (NWAL1710BMsg) bMsg;
        scrnMsg.dsOrdCatgNm.setNameForMessage("Order Category");
        scrnMsg.dsOrdTpNm.setNameForMessage("Reason Code");
        scrnMsg.ordProcTpCd.setNameForMessage("Workflow");
        scrnMsg.lineBizTpCd.setNameForMessage("Line of Business");
        scrnMsg.effFromDt.setNameForMessage("Effective From");
        scrnMsg.effThruDt.setNameForMessage("Effective Thru");
        scrnMsg.actvFlg.setNameForMessage("Active Only");
    }

}
