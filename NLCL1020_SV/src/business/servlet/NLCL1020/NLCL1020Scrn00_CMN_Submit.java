/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
*/
package business.servlet.NLCL1020;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1020.NLCL1020CMsg;
import business.servlet.NLCL1020.common.NLCL1020CommonLogic;
import business.servlet.NLCL1020.constant.NLCL1020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 * 03/11/2019   Fujitsu         T.Ogura         Update          QC#30705
 * 03/26/2019   Fujitsu         T.Ogura         Update          QC#30124
 *</pre>
 */
public class NLCL1020Scrn00_CMN_Submit extends S21CommonHandler {

    /** Need Double Check*/
//    private boolean needDoubleCheck = false;    // 2019/03/26 T.Ogura [QC#30124,DEL]

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        // 10/19/2015 mod start
        // scrnMsg.invtyLocCd_FR.clearErrorInfo();
        // scrnMsg.invtyLocCd_TO.clearErrorInfo();
        scrnMsg.fromRtlWhCd.clearErrorInfo();
        scrnMsg.fromRtlSwhCd.clearErrorInfo();
        scrnMsg.toRtlWhCd.clearErrorInfo();
        scrnMsg.toRtlSwhCd.clearErrorInfo();
        // 10/19/2015 mod end

        // START 2019/03/26 T.Ogura [QC#30124,MOD]
//        needDoubleCheck = NLCL1020CommonLogic.needDoubleCheck(scrnMsg);
//        if (needDoubleCheck) {
//            return;
//        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxChkBox_AD.clear();
        }
        // END   2019/03/26 T.Ogura [QC#30124,MOD]

        // 10/19/2015 mod start
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_FR);
        // scrnMsg.addCheckItem(scrnMsg.invtyLocCd_TO);
        scrnMsg.addCheckItem(scrnMsg.fromRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.fromRtlSwhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlWhCd);
        scrnMsg.addCheckItem(scrnMsg.toRtlSwhCd);
        // 10/19/2015 mod end

        // START 2019/03/11 T.Ogura [QC#30705,MOD]
//        NLCL1020CommonLogic.checkInputCMN_Apply(scrnMsg);
        NLCL1020CommonLogic.checkInputCMN_Apply(scrnMsg, ctx.getEventName());
        // END   2019/03/11 T.Ogura [QC#30705,MOD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        // START 2019/03/26 T.Ogura [QC#30124,MOD]
//        if (needDoubleCheck) {
//            scrnMsg.setMessageInfo(NLCL1020Constant.NLCM0112W, new String[] {"Delete item", "checked, press the Submit button to proceed" });
//            scrnMsg.xxYesNoCd.setValue("Y");
//            return null;
//        } else {
//            NLCL1020CMsg bizMsg = NLCL1020CommonLogic.setRequestData40(scrnMsg);
//            scrnMsg.xxErrFlg_RG.setValue(NLCL1020Constant.ERRFLAG_RG0);
//
//            EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//            return bizMsg;
//        }
        NLCL1020CMsg bizMsg = NLCL1020CommonLogic.setRequestData40(scrnMsg);
        scrnMsg.xxErrFlg_RG.setValue(NLCL1020Constant.ERRFLAG_RG0);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END   2019/03/26 T.Ogura [QC#30124,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;
        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);

            if (NLCL1020Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
                if (NLCL1020Constant.ERRFLAG_RG1.equals(bizMsg.xxErrFlg_RG.getValue())) {
                    // 10/19/2015 mod start
                    // scrnMsg.setFocusItem(scrnMsg.invtyLocCd_FR);
                    scrnMsg.setFocusItem(scrnMsg.fromRtlWhCd);
                    // 10/19/2015 mod end
                    throw new EZDFieldErrorException();
                }
            }
            NLCL1020CommonLogic.transMsgCheck(scrnMsg);
            scrnMsg.putErrorScreen();

            NLCL1020CommonLogic.initialize(this, scrnMsg);

            NLCL1020CommonLogic.scrnItemControl_For_Submit_Or_Search(this, scrnMsg);
        }
    }

}
