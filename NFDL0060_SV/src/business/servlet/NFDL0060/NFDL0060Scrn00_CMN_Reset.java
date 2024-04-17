/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFDL0060.NFDL0060CMsg;
import business.servlet.NFDL0060.common.NFDL0060CommonLogic;
import business.servlet.NFDL0060.constant.NFDL0060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2016/07/25   Hitachi         K.Kojima        Update          QC#10260
 *</pre>
 */
public class NFDL0060Scrn00_CMN_Reset extends S21CommonHandler implements NFDL0060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;
        NFDL0060CMsg bizMsg = new NFDL0060CMsg();

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(SEARCH_FUNCTION);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;
        NFDL0060CMsg bizMsg = (NFDL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFDL0060CommonLogic.controlScreenFields(scrnMsg);
        // START 2016/07/25 K.Kojima [QC#10260,ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H1, ZYPConstant.FLG_ON_Y);
        // END 2016/07/25 K.Kojima [QC#10260,ADD]

        NFDL0060CommonLogic.alternateTableRowColors(scrnMsg);
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
