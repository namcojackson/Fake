/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0090.NFDL0090CMsg;
import business.servlet.NFDL0090.common.NFDL0090CommonLogic;
import business.servlet.NFDL0090.constant.NFDL0090Constant;
import static business.servlet.NFDL0090.constant.NFDL0090Constant.*;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Write Off.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12    Fujitsu         M.Nakamura      Create          N/A
 * 2016/03/01    CSAI           K.Uramori       Update          Parameter Check (There will be the case of having only one parameter.)
 * 2017/01/05    Fujitsu         H.Ikeda         Update          QC#12865
 * 2018/07/17    Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/19    Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/24    Hitachi         Y.Takeno        Update          QC#26989-1
 * 2018/09/11    Hitachi         Y.Takeno        Update          QC#24884
 * 2019/02/04    Hitachi         H.Umeda         Update          QC#30162
 * 2019/02/06    Hitachi         H.Umeda         Update          QC#30162
 * 2022/11/24    Hitachi         S.Naya          Update          QC#57252
 *</pre>
 */
public class NFDL0090_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Start 2017/01/05 H.Ikeda [QC#12865,ADD]
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NFDL0090Constant.BIZ_ID);
        // End   2017/01/05 H.Ikeda [QC#12865,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;

        // IN Parameter -Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 2) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, (EZDBStringItem) params[0]);

            if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                scrnMsg.setMessageInfo(NFDL0090Constant.NFDM0001E, new String[] {scrnMsg.dsAcctNum_H1.getNameForMessage()});
                return null;
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_H1, (EZDBStringItem) params[1]);
        // add 2016/03/01
        } else if (params != null && params.length == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H1, (EZDBStringItem) params[0]);
            
            if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum_H1)) {
                scrnMsg.setMessageInfo(NFDL0090Constant.NFDM0001E, new String[] {scrnMsg.dsAcctNum_H1.getNameForMessage()});
                return null;
            }
        // end 2016/03/01
            
        } else {
            scrnMsg.setMessageInfo(NFDL0090Constant.NFDM0001E, new String[] {scrnMsg.dsAcctNum_H1.getNameForMessage()});
            return null;
        }

        NFDL0090CMsg bizMsg = new NFDL0090CMsg();
        bizMsg.setBusinessID("NFDL0090");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        NFDL0090CMsg bizMsg = (NFDL0090CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NFDL0090CommonLogic.initCmnBtnProp(this, scrnMsg);
        // START 2018/09/11 [QC#24884, ADD]
        NFDL0090CommonLogic.protectHeader(scrnMsg);
        // END   2018/09/11 [QC#24884, ADD]
        NFDL0090CommonLogic.protectDetail(scrnMsg);

        setAppFracDigit(scrnMsg);
        // START 2019/02/04 [QC#30162, ADD]
        scrnMsg.xxDealApplyAmtNum_H1.setInputProtected(true);
        // END   2019/02/04 [QC#30162, ADD]

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxDealApplyAmtNum_A1);
        // START 2018/07/19 [QC#26989, ADD]
        } else {
            setButtonEnabled("Click_Search", false);
        }
        // END   2018/07/19 [QC#26989, ADD]
        // START 2022/11/24 S.Naya [QC#57252,ADD]
        if (OTHER_CODE.equals(scrnMsg.arAdjTpCd_FS.getValue())) {
            scrnMsg.xxCmntTxt_FS.setInputProtected(false);
            setButtonEnabled(BTN_A, true);
        } else {
            scrnMsg.xxCmntTxt_FS.setInputProtected(true);
            setButtonEnabled(BTN_A, false);
        }
        // END   2022/11/24 S.Naya [QC#57252,ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;

        // Header
        // START 2018/07/24 [QC#26989-1, MOD]
        scrnMsg.dsAcctNum_H1.setNameForMessage("Account");
        scrnMsg.dsAcctNm_H1.setNameForMessage("Account Name");
        scrnMsg.xxQueryFltrTxt_H1.setNameForMessage("Transaction#");
        scrnMsg.custIssPoNum_H1.setNameForMessage("PO Number");
        // END   2018/07/24 [QC#26989-1, MOD]

       // Footer
        scrnMsg.arAdjTpCd_FS.setNameForMessage("Write Off Activity");
        scrnMsg.arWrtOffNoteCd_FS.setNameForMessage("Explanation Code");
        scrnMsg.arWrtOffNoteTxt_FS.setNameForMessage("Note");
        // START 2022/11/24 S.Naya [QC#57252,ADD]
        scrnMsg.xxCmntTxt_FS.setNameForMessage("Charge Account");
        // END   2022/11/24 S.Naya [QC#57252,ADD]

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setNameForMessage("Write Off Amt");
        }
    }

    private void setAppFracDigit(NFDL0090BMsg scrnMsg) {

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealOrigGrsAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).dealApplyAdjRsvdAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).xxDealApplyAmtNum_A1.setAppFracDigit(2);
        }
        // START 2019/02/04 [QC#30162, ADD]
        scrnMsg.xxDealApplyAmtNum_H1.setAppFracDigit(2);
        // START 2019/02/06 [QC#30162, DEL]
        // scrnMsg.xxDealApplyAmtNum_H1.setValue((int) 0.00);
        // END   2019/02/06 [QC#30162, DEL]
        // END   2019/02/04 [QC#30162, ADD]
    }
}
