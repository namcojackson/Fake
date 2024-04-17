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
import business.servlet.NLBL3060.NLBL3060BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/16   CSAI            K.Lee           Create          
 * 2018/11/21   Fujitsu         T.Ogura         Update          QC#29258
 * 2023/10/18   Hitachi         Y.Ogura         Update          QC#61793
 *</pre>
 */
public class NLBL3060Scrn00_CMN_Submit extends S21CommonHandler implements NLBL3060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.A);
        // START 2023/10/18 Y.Ogura [QC#61793,DEL]
//        scrnMsg.A.setCheckParam(new String[] {NLBL3060Bean.rtlWhCd_A1, NLBL3060Bean.schdCoordPsnCd_A1, NLBL3060Bean.effFromDt_A1 , NLBL3060Bean.effThruDt_A1 }, 1);
        // END 2023/10/18 Y.Ogura [QC#61793,DEL]
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;

        NLBL3060CMsg bizMsg = new NLBL3060CMsg();
        bizMsg.setBusinessID("NLBL3060");
        bizMsg.setFunctionCode("40");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3060BMsg scrnMsg = (NLBL3060BMsg) bMsg;
        NLBL3060CMsg bizMsg = (NLBL3060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLBL3060CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID(), ZYPConstant.FLG_ON_Y);

        // START 2023/10/18 Y.Ogura [QC#61793,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhCatgCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).physWhCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).schdCoordPsnCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
        }
        // END 2023/10/18 Y.Ogura [QC#61793,ADD]

        // START 2023/10/18 Y.Ogura [QC#61793,DEL]
//        scrnMsg.addCheckItem(scrnMsg.A);
        // END 2023/10/18 Y.Ogura [QC#61793,DEL]
        
        // START 2018/11/21 T.Ogura [QC#29258,MOD]
//        scrnMsg.A.setCheckParam(new String[] {NLBL3060Bean.rtlWhCd_A1, NLBL3060Bean.schdCoordPsnCd_A1, NLBL3060Bean.effFromDt_A1 , NLBL3060Bean.effThruDt_A2 }, 1);
        // START 2023/10/18 Y.Ogura [QC#61793,DEL]
//        scrnMsg.A.setCheckParam(new String[] {NLBL3060Bean.rtlWhCd_A1, NLBL3060Bean.schdCoordPsnCd_A1, NLBL3060Bean.effFromDt_A1 , NLBL3060Bean.effThruDt_A1 }, 1);
        // END 2023/10/18 Y.Ogura [QC#61793,DEL]
        // END   2018/11/21 T.Ogura [QC#29258,MOD]
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        scrnMsg.setMessageInfo(MESSAGE_ID.NZZM0002I.toString());
    }

}
