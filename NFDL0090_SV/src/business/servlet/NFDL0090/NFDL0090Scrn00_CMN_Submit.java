/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import static business.servlet.NFDL0090.constant.NFDL0090Constant.BIZ_ID;
import static business.servlet.NFDL0090.constant.NFDL0090Constant.OTHER_CODE;
import static business.servlet.NFDL0090.constant.NFDL0090Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0090.NFDL0090CMsg;
import business.servlet.NFDL0090.common.NFDL0090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Write Off
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Fujitsu         M.Nakamura      Create          N/A
 * 2017/08/21   Hitachi         T.Tsuchida      Update          QC#19573
 * 2018/08/23   Fujitsu         T.Ogura         Update          QC#27465
 * 2018/09/11   Hitachi         Y.Takeno        Update          QC#24884
 * 2022/11/24   Hitachi         S.Naya          Update          QC#57252
 *</pre>
 */
public class NFDL0090Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        // START 2018/08/23 T.Ogura [QC#27465,ADD]
        scrnMsg.addCheckItem(scrnMsg.arWrtOffNoteTxt_FS);
        // END   2018/08/23 T.Ogura [QC#27465,ADD]
        // START 2022/11/24 S.Naya [QC#57252,ADD]
        if (OTHER_CODE.equals(scrnMsg.arAdjTpCd_FS.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.xxCmntTxt_FS)) {
            scrnMsg.xxCmntTxt_FS.setErrorInfo(1, ZZM9000E, new String[] {"Charge Account" });
            scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_FS);
        }
        // END   2022/11/24 S.Naya [QC#57252,ADD]
        NFDL0090CommonLogic.checkDetail(scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;

        NFDL0090CMsg bizMsg = new NFDL0090CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        NFDL0090CMsg bizMsg = (NFDL0090CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2017/08/21 T.Tsuchida [QC#19573,DEL]
        //scrnMsg.arWrtOffNoteTxt_FS.clear();
        // END 2017/08/21 T.Tsuchida [QC#19573,DEL]
        // START 2022/11/24 S.Naya [QC#57252,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_FS);
        scrnMsg.putErrorScreen();
        // END   2022/11/24 S.Naya [QC#57252,ADD]
        NFDL0090CommonLogic.initCmnBtnProp(this, scrnMsg);
        // START 2018/09/11 [QC#24884, ADD]
        NFDL0090CommonLogic.protectHeader(scrnMsg);
        // END   2018/09/11 [QC#24884, ADD]
        NFDL0090CommonLogic.protectDetail(scrnMsg);

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxDealApplyAmtNum_A1);
        }

    }
}
