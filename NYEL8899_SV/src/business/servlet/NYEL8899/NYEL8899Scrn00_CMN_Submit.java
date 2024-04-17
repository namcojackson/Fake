/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8899;

import static business.servlet.NYEL8899.constant.NYEL8899Constant.BIZ_ID;
//import static business.servlet.NYEL8899.constant.NYEL8899Constant.SCRN_ID_00;
import static business.servlet.NYEL8899.constant.NYEL8899Constant.NZZM0002I;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8899.NYEL8899CMsg;
import business.servlet.NYEL8899.common.NYEL8899CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
//import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NYEL8899Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/03   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8899Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8899BMsg scrnMsg = (NYEL8899BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.wfProcNm);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_01);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_02);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_03);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_04);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_05);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_06);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_07);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_08);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_09);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbTxt_10);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_01);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_02);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_03);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_04);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_05);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_06);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_07);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_08);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_09);
        scrnMsg.addCheckItem(scrnMsg.wfBizAttrbLbTxt_10);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_01);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_02);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_03);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_04);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_05);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_06);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_07);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_08);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_09);
        scrnMsg.addCheckItem(scrnMsg.condValTxt_10);
        scrnMsg.addCheckItem(scrnMsg.condValNum_01);
        scrnMsg.addCheckItem(scrnMsg.condValNum_02);
        scrnMsg.addCheckItem(scrnMsg.condValNum_03);
        scrnMsg.addCheckItem(scrnMsg.condValNum_04);
        scrnMsg.addCheckItem(scrnMsg.condValNum_05);
        scrnMsg.addCheckItem(scrnMsg.condValNum_06);
        scrnMsg.addCheckItem(scrnMsg.condValNum_07);
        scrnMsg.addCheckItem(scrnMsg.condValNum_08);
        scrnMsg.addCheckItem(scrnMsg.condValNum_09);
        scrnMsg.addCheckItem(scrnMsg.condValNum_10);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8899BMsg scrnMsg = (NYEL8899BMsg) bMsg;

        NYEL8899CMsg bizMsg = new NYEL8899CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8899BMsg scrnMsg = (NYEL8899BMsg) bMsg;
        NYEL8899CMsg bizMsg = (NYEL8899CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
        
        scrnMsg.setFocusItem(scrnMsg.wfProcNm);
        NYEL8899CommonLogic.initCmnBtnProp(scrnMsg, this);

    }
}
