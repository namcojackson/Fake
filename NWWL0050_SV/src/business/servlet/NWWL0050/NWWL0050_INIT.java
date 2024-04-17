/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0050;

import static business.servlet.NWWL0050.constant.NWWL0050Constant.BIZ_ID;
import static business.servlet.NWWL0050.constant.NWWL0050Constant.SCRN_ID_00;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0050.NWWL0050CMsg;
import business.servlet.NWWL0050.common.NWWL0050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWWL0050_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/05   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NWWL0050_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;
        NWWL0050CMsg bizMsg = new NWWL0050CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1 && params[0] != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyDistListId, (String) params[0]);
        } else {
            scrnMsg.ntfyDistListId.clear();
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;
        NWWL0050CMsg bizMsg = (NWWL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWWL0050CommonLogic.initCmnBtnProp(this);
        NWWL0050CommonLogic.controlScreenFields(this, scrnMsg);
        NWWL0050CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWWL0050CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.B, "B");

        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.B.no(0).getBaseContents());
        

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxYesNoCd, ZYPConstant.FLG_OFF_N);

        scrnMsg.setFocusItem(scrnMsg.ntfyDistListNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWWL0050BMsg scrnMsg = (NWWL0050BMsg) bMsg;

        scrnMsg.ntfyDistListNm.setNameForMessage("Name");
        scrnMsg.ntfyDistListId.setNameForMessage("Dist List ID");
        scrnMsg.ntfyDistListDescTxt.setNameForMessage("Description");
        scrnMsg.ntfyBizAreaTpCd_D.setNameForMessage("Business Area");
        scrnMsg.ntfySubAreaTpCd_D.setNameForMessage("Sub Area");
        scrnMsg.effFromDt_D.setNameForMessage("Start Date");
        scrnMsg.effThruDt_D.setNameForMessage("End Date");

        scrnMsg.xxRadioBtn.setNameForMessage("Radio Button");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).ntfyDistQlfyCd_A.setNameForMessage("Assign Type");
            scrnMsg.A.no(i).ntfyDistListAsgValTxt_A.setNameForMessage("Value");
        }
    }
}
