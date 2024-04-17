/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.copyScrnMsgToBizMsgForSearch;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setCursorRuleForSwhDetail;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setInputProtectedForDetailTab;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setInputProtectedForGeneralTab;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.NEW_SUPPLIER;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6860.NMAL6860CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID   : NMAL6860 Supplier Entry
 * Function Name : The business process for On Click Add Supplier Site.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/06   CITS            M.Ouchi         Create          N/A
 * 2016/09/15   CITS            T.Gotoda        Update          QC#13313
 * 2018/08/07   CITS            K.Ogino         Update          QC#27280
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 * 2020/12/12   CITS            R.Kurahashi     Update          QC#57732-1
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 * </pre>
 */
public class NMAL6860Scrn00_OnClick_AddSupplierSite extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return copyScrnMsgToBizMsgForSearch((NMAL6860BMsg) bMsg);
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        EZDMsg.copy((NMAL6860CMsg) cMsg, null, scrnMsg, null);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            return;
        }

        NMAL6860_ABMsg lastRow = scrnMsg.A.no(scrnMsg.A.getValidCount() - 1);

        // QC#13313 Start
        if (VND_TP.SUPPLIER.equals(scrnMsg.vndTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(lastRow.splyPoFlg_A, ZYPConstant.FLG_ON_Y);
            // START 2021/03/01 G.Delgado [QC#56057,DEL]
            // lastRow.splyPoFlg_A.setInputProtected(true);
            // END 2021/03/01 G.Delgado [QC#56057,DEL]
        }
        // QC#13313 End

        // sets enable/disable.
        lastRow.vndCd_A.setInputProtected(true);
        // QC#27280
        lastRow.locNm_A.setInputProtected(false);
        // START 2020/10/28 R.Kurahashi [QC#57732,MOD]
        //lastRow.xxComnScrFirstValTxt_AL.setInputProtected(true);
        //lastRow.inacDt_A.setInputProtected(true);
        if (ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && scrnMsg.prntVndNm_DF.getValue().equals(NEW_SUPPLIER)) {
            lastRow.xxComnScrFirstValTxt_AL.setInputProtected(false);
            lastRow.inacDt_A.setInputProtected(false);
        } else {
            lastRow.xxComnScrFirstValTxt_AL.setInputProtected(true);
            lastRow.inacDt_A.setInputProtected(true);
        }
        // END 2020/10/28 R.Kurahashi [QC#57732,MOD]
        // START 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
        lastRow.ctryCd_A.setInputProtected(false);
        lastRow.xxComnScrFirstValTxt_A.setInputProtected(false);
        lastRow.xxComnScrScdValTxt_A.setInputProtected(false);
        lastRow.ctyAddr_A.setInputProtected(false);
        lastRow.postCd_A.setInputProtected(false);
        lastRow.stCd_A.setInputProtected(false);
        lastRow.cntyNm_A.setInputProtected(false);
        // END 2020/12/12 R.Kurahashi [QC#57732-1,ADD]

        // sets the input fields of General Tab.
        setInputProtectedForGeneralTab(scrnMsg);
        // sets the input fields of Detail Tab.
        setInputProtectedForDetailTab(scrnMsg);
        setCursorRuleForSwhDetail(scrnMsg);

        // focus on Site Name
        scrnMsg.setFocusItem(lastRow.locNm_A);
    }
}
