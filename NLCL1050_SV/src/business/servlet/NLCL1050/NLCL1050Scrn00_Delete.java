/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1050;

import static business.servlet.NLCL1050.constant.NLCL1050Constant.BIZ_ID;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.NLCM0196I;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1050.NLCL1050CMsg;
import business.servlet.NLCL1050.common.NLCL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   CITS            N.Akaishi       Create          N/A
 *</pre>
 */
public class NLCL1050Scrn00_Delete extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;

        NLCL1050CommonLogic.addCheckItem(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;

        NLCL1050CMsg bizMsg = new NLCL1050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;
        NLCL1050CMsg bizMsg  = (NLCL1050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxSupdFlg.getValue())) {
            scrnMsg.setMessageInfo(NLCM0196I);
            scrnMsg.putErrorScreen();
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSupdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxSupdFlg, ZYPConstant.FLG_OFF_N);
        }

        scrnMsg.addCheckItem(scrnMsg.abcAnlsClsNm);
        scrnMsg.putErrorScreen();

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NLCL1050CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);
        scrnMsg.setFocusItem(scrnMsg.abcAnlsClsNm);
    }
}
