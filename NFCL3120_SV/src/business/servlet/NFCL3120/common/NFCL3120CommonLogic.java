package business.servlet.NFCL3120.common;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NFCL3120.NFCL3120BMsg;
import business.servlet.NFCL3120.constant.NFCL3120Constant;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Tanaka        Create          N/A
 * 2016/02/01   Fujitsu         T.Tanaka        Update          Def#2617
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public class NFCL3120CommonLogic implements NFCL3120Constant {

    public static void initialize(EZDCommonHandler scrnAppli, NFCL3120BMsg scrnMsg, S21UserProfileService userProfileService) {

        scrnMsg.setInputProtected(false);

        scrnAppli.setButtonProperties("Click_SetCustomerName"   , "Click_SetCustomerName"   , ">>"    , 1, null);
        scrnAppli.setButtonProperties("Click_SetBankAccountName", "Click_SetBankAccountName", ">>"    , 1, null);
        // Start 2016/12/27 H.Ikeda [QC#12865,MOD]
        if (hasUpdateFuncId(scrnMsg)) {
            scrnAppli.setButtonProperties("New"                  , "New"                  , "New"   , 1, null);
        } else {
            scrnAppli.setButtonProperties("New"                  , "New"                  , "New"   , 0, null);
        }
        // End   2016/12/27 H.Ikeda [QC#12865,MOD]

        scrnAppli.setButtonProperties("Search"               , "Search"               , "Search", 1, null);

        scrnAppli.setButtonProperties("btn1" , ""            , "Save"    , 0, null);
        scrnAppli.setButtonProperties("btn2" , ""            , "Submit"  , 0, null);
        scrnAppli.setButtonProperties("btn3" , ""            , "Apply"   , 0, null);
        scrnAppli.setButtonProperties("btn4" , ""            , "Approve" , 0, null);
        scrnAppli.setButtonProperties("btn5" , ""            , "Reject"  , 0, null);
        scrnAppli.setButtonProperties("btn6" , "CMN_Download", "Download", 1, null);
        scrnAppli.setButtonProperties("btn7" , ""            , "Delete"  , 0, null);
        scrnAppli.setButtonProperties("btn8" , "CMN_Clear"   , "Clear"   , 1, null);
        scrnAppli.setButtonProperties("btn9" , ""            , "Reset"   , 0, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Return"  , "Return"  , 1, null);

        scrnMsg.dsBankAcctNm_H1.setInputProtected(false);
        scrnMsg.dsAcctNm_H1.setInputProtected(false);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dsBankAcctNum_A1.setInputProtected(false);
            scrnMsg.A.no(i).dsBankAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsBankNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).bankRteNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsBankBrNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
        }
    }

    // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId(NFCL3120BMsg scrnMsg) {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            scrnMsg.setMessageInfo(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId()});
            return false;
        }
        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }
        return false;
    }
    // End  2016/12/27 H.Ikeda [QC#12865,ADD]
}
