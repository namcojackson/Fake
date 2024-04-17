/*
 * <Pre>Copyright (c) 2010 Canon USA Inc. All rights reserved.</Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2010/07/13   Fujitsu         N.Yamamoto      Create          4486
 *</pre>
 */
package business.servlet.NLBL3060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3060.NLBL3060CMsg;
import business.servlet.NLBL3060.common.NLBL3060CommonLogic;
import business.servlet.NLBL3060.constant.NLBL3060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public class NLBL3060Scrn00_Search extends S21CommonHandler implements NLBL3060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;

        NLBL3060CMsg bizMsg = new NLBL3060CMsg();
        bizMsg.setBusinessID("NLBL3060");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        NLBL3060CMsg bizMsg = (NLBL3060CMsg) cMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        // START 2023/10/18 Y.Ogura [QC#61793, ADD]
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_HD);
        scrnMsg.addCheckItem(scrnMsg.physWhCd_HD);
        // END 2023/10/18 Y.Ogura [QC#61793, ADD]
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.schdCoordPsnCd);
        scrnMsg.putErrorScreen();

        String submitBtnFlg = ZYPConstant.FLG_OFF_N;
        if (bizMsg.A.getValidCount() > 0) {
            submitBtnFlg = ZYPConstant.FLG_ON_Y;
        }

        NLBL3060CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID(), submitBtnFlg);
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        scrnMsg.setMessageInfo(MESSAGE_ID.NZZM0002I.toString());
    }

}
