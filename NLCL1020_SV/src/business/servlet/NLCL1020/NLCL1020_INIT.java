/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
*/
package business.servlet.NLCL1020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1020.NLCL1020CMsg;
import business.servlet.NLCL1020.common.NLCL1020CommonLogic;
import business.servlet.NLCL1020.constant.NLCL1020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         Y.Taoka         Create          R-WH001
 * 01/17/2017   CITS            T.Kikuhara      Update          QC#16256
 * 12/25/2017   CITS            T.Tokutomi      Update          QC#19540
 * 03/11/2019   Fujitsu         T.Ogura         Update          QC#30705
 *</pre>
 */
public class NLCL1020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLCL1020Constant.BUSINESS_ID);
        setInputMode(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1020CMsg bizMsg = null;
        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;
        bizMsg = NLCL1020CommonLogic.setRequestData20(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1020CMsg bizMsg = (NLCL1020CMsg) cMsg;
        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL1020CommonLogic.initialize(this, scrnMsg);

        NLCL1020CommonLogic.setScrnItemValue_NLCL1020_INIT(scrnMsg);

        NLCL1020CommonLogic.commonBtnControl_NLCL1020_INIT(this);

        NLCL1020CommonLogic.scrnItemControl_NLCL1020_INIT(this, scrnMsg);

        if (bizMsg != null) {

            if (NLCL1020Constant.NLCM0001E.equals(bizMsg.getMessageCode())) {

                // START 2019/03/11 T.Ogura [QC#30705,MOD]
//                scrnMsg.xxMdseCdAncr.setInputProtected(true);
//                setButtonEnabled("Display_MDSEName", false);
//                setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);
//                setButtonEnabled("CMN_Cancel", false);
                NLCL1020CommonLogic.scrnItemControl_For_Submit_Or_Search(this, scrnMsg);
                // END   2019/03/11 T.Ogura [QC#30705,MOD]
                setButtonEnabled("btn2", false);
                setButtonEnabled("btn3", false);
                setButtonEnabled("btn6", false);
                setButtonEnabled("btn8", false);
                setButtonEnabled("btn9", false);

            } else {
                // 10/19/2015 mod start
                // scrnMsg.setFocusItem(scrnMsg.invtyLocCd_FR);
                scrnMsg.setFocusItem(scrnMsg.fromRtlWhCd);
                // 10/19/2015 mod end
            }
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLCL1020BMsg scrnMsg = (NLCL1020BMsg) bMsg;

        scrnMsg.invtyOrdNum.setNameForMessage("Transaction Number");
        // 10/19/2015 mod start
//        scrnMsg.invtyLocCd_FR.setNameForMessage("Loc From");
//        scrnMsg.invtyLocCd_TO.setNameForMessage("Loc To");
        scrnMsg.fromRtlWhCd.setNameForMessage("From Retail WH Code");
        scrnMsg.fromRtlSwhCd.setNameForMessage("From Retail Sub WH Code");
        scrnMsg.toRtlWhCd.setNameForMessage("To Retail WH Code");
        scrnMsg.toRtlSwhCd.setNameForMessage("To Retail Sub WH Code");
        // 10/19/2015 mod end
        scrnMsg.locStsCd_P1.setNameForMessage("Loc Sts");
        scrnMsg.firstInvtyOrdCmntTxt.setNameForMessage("Comment");
        scrnMsg.scdInvtyOrdCmntTxt.setNameForMessage("Comment");
        scrnMsg.thirdInvtyOrdCmntTxt.setNameForMessage("Comment");
        scrnMsg.mdseCd_HD.setNameForMessage("Mdse Cd");
        scrnMsg.stkStsCd_PH.setNameForMessage("SS");
        scrnMsg.invtyAvalQty_HI.setNameForMessage("Qty");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).stkStsCd_AP.setNameForMessage("Stock Status");
            scrnMsg.A.no(i).invtyAvalQty_AI.setNameForMessage("Qty");
        }
    }

    private void setInputMode(NLCL1020BMsg scrnMsg) {

        S21UserInfo userInfo = getContextUserInfo();
        String userId = userInfo.getUserId();
        S21UserProfileService profile = getUserProfileService();

        // QC#19540 mod start
        if (profile.isFunctionGranted(userId, NLCL1020Constant.FUNC_NLCL1020T020)) {
            // QC#16256 mod start
            // NLCL1020T020
            // The functions for Update
            scrnMsg.xxFuncId.setValue(NLCL1020Constant.ENTRY);
            scrnMsg.msgTxtInfoTxt.clear();
        } else {
            // NLCL1020T010
            // The functions for Inquiry
            scrnMsg.xxFuncId.setValue(NLCL1020Constant.INQUIRY_ONLY);
            scrnMsg.msgTxtInfoTxt.setValue("Inquiry Mode");
            // QC#16256 mod end
        }
        // QC#19540 mod end.
    }

}
