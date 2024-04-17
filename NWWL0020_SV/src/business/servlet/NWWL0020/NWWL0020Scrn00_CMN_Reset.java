/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

import static business.servlet.NWWL0020.constant.NWWL0020Constant.BIZ_ID;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.SCRN_ID_00;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.TAB_NAME_HEADER;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0020.NWWL0020CMsg;
import business.servlet.NWWL0020.common.NWWL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020Scrn00_CMN_Reset
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = new NWWL0020CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyHdrId_H0, (String) params[0]);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = (NWWL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }

        S21SortColumnIMGController.clearIMG(SCRN_ID_00, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NWWL0020CommonLogic.clearRowsBG(scrnMsg, scrnMsg.A, "A");
        NWWL0020CommonLogic.clearRowsBG(scrnMsg, scrnMsg.B, "B");

        if (ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H0) && scrnMsg.effThruDt_H0.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
        } else {
            NWWL0020CommonLogic.setControlFieldsByAuth(scrnMsg);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlProtFlg, ZYPConstant.FLG_ON_Y);
        NWWL0020CommonLogic.setIntvalRadioValue(scrnMsg);
        scrnMsg.xxDplyTab.setValue(TAB_NAME_HEADER);

        NWWL0020CommonLogic.setScrnTm(scrnMsg);
        NWWL0020CommonLogic.initCmnBtnProp(this);
        NWWL0020CommonLogic.setControlFields(scrnMsg, this);
        NWWL0020CommonLogic.setControlFieldsRepeatChkBox(scrnMsg);
        NWWL0020CommonLogic.setControlFieldsActListProt(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.ntfyHdrNm_H0);
    }
}
