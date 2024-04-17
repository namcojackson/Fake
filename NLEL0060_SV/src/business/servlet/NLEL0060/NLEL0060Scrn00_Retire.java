/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.ASSET_RETIRE_RSN_CMNT_TXT;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.FUNC_CD_UPD;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0060.NLEL0060CMsg;
import business.servlet.NLEL0060.common.NLEL0060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_Retire
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/28   Hitachi         J.Kim           Update          QC#7650
 * 2016/09/14   Fujitsu         C.Tanaka        Update          QC#12697
 * 2017/05/19   CITS            M.Naito         Update          Merge DS Lv2
 * 2017/10/26   CITS            M.Naito         Update          QC#22052
 *</pre>
 */
public class NLEL0060Scrn00_Retire extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        scrnMsg.xxBtnFlg_CA.clear();
        scrnMsg.xxBtnFlg_CM.clear();

        // START 2018/04/06 J.Kim [QC#24561,DEL]
        // if (!ZYPCommonFunc.hasValue(scrnMsg.acctYrMth_T1)) {
        //    scrnMsg.acctYrMth_T1.setErrorInfo(1, ZZM9000E, new String[] {ACCT_YR_MTH });
        // }
        // END 2018/04/06 J.Kim [QC#24561,DEL]
        if (!ZYPCommonFunc.hasValue(scrnMsg.assetRtireRsnCmntTxt_T1)) {
            scrnMsg.assetRtireRsnCmntTxt_T1.setErrorInfo(1, ZZM9000E, new String[] {ASSET_RETIRE_RSN_CMNT_TXT });
        }

        // START 2018/04/06 J.Kim [QC#24561,DEL]
        // scrnMsg.addCheckItem(scrnMsg.acctYrMth_T1);
        // END 2018/04/06 J.Kim [QC#24561,DEL]
        scrnMsg.addCheckItem(scrnMsg.assetRtireRsnCmntTxt_T1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        NLEL0060CMsg bizMsg = new NLEL0060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLEL0060CommonLogic.initFieldProp(this, scrnMsg);
        NLEL0060CommonLogic.ctrlCmnSubBtnProp(this, scrnMsg);

        // START 2018/04/06 J.Kim [QC#24561,MOD]
        // scrnMsg.addCheckItem(scrnMsg.acctYrMth_T1);
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_C1);
        }
        // END 2018/04/06 J.Kim [QC#24561,MOD]
        scrnMsg.addCheckItem(scrnMsg.assetRtireRsnCmntTxt_T1);

        // START 2018/04/06 J.Kim [QC#24561,DEL]
        // scrnMsg.acctYrMth_T1.clear();
        // scrnMsg.setFocusItem(scrnMsg.acctYrMth_T1);
        // END 2018/04/06 J.Kim [QC#24561,DEL]
        scrnMsg.assetRtireRsnCmntTxt_T1.clear();
        scrnMsg.addCheckItem(scrnMsg.assetRtireRsnCmntTxt_T1);
        scrnMsg.putErrorScreen();

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // START 2017/10/26 M.Naito [QC#22052,DEL]
//        scrnMsg.setMessageInfo(NZZM0002I, null, 0);
        // END 2017/10/26 M.Naito [QC#22052,DEL]
    }
}
