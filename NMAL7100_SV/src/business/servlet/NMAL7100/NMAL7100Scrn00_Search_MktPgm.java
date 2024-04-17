/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7100.NMAL7100CMsg;
import business.servlet.NMAL7100.common.NMAL7100CommonLogic;
//import business.servlet.NMAL7100.constant.NMAL7100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.BIZ_ID;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.ZZM9000E;;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2017/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 *</pre>
 */
public class NMAL7100Scrn00_Search_MktPgm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.prcMktPrmoPk_H1)) {
            scrnMsg.prcMktPrmoPk_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcMktPrmoPk_H1.getNameForMessage()});
        }

        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoPk_H1);
        scrnMsg.putErrorScreen();

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;

        NMAL7100CMsg bizMsg = new NMAL7100CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7100BMsg scrnMsg = (NMAL7100BMsg) bMsg;
        NMAL7100CMsg bizMsg  = (NMAL7100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(MESSAGE_KIND_ERROR)) {
            // Mod Start 2017/02/27 QC#16011
//            NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg);
            NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
            // Mod End 2017/02/27 QC#16011
            throw new EZDFieldErrorException();
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.prcMktPrmoPk_BK, scrnMsg.prcMktPrmoPk_H1);

        // Mod Start 2017/02/27 QC#16011
//        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg);
        NMAL7100CommonLogic.scrnAllGUIControl(this, scrnMsg, getUserProfileService());
        // Mod End 2017/02/27 QC#16011
    }
}
