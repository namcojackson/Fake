package business.blap.ZYPL0210.common;

import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.ZYPL0210.ZYPL0210CMsg;
import business.blap.ZYPL0210.constant.ZYPL0210Constant;
import business.parts.ZYEQ021000Join;
import business.parts.ZYEQ021000PMsg;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * @author Administrator
 */
public class ZYPL0210CommonLogic implements ZYPL0210Constant {

    /**
     * 検索処理
     * @param cMsg 業務アプリインターフェースメッセージ
     * @param sMsg 業務共有メッセージ
     */
    public static void doSearch(ZYPL0210CMsg cMsg, EZDSMsg sMsg) {
        // 1.get Global Company Code from user profile Info.
        String glblCmpyCd = S21UserProfileServiceFactory.getInstance().getService().getGlobalCompanyCode();

        // 2.
        ZYEQ021000PMsg joinMsg = new ZYEQ021000PMsg();
        joinMsg.glblCmpyCd.setValue(glblCmpyCd);
        joinMsg.upldCsvId.setValue(cMsg.upldCsvId_0H.getValue());
        joinMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValue());
        joinMsg.xxUpldCsvUsrTxt.setValue(cMsg.xxUpldCsvUsrTxt_0H.getValue());
        
        ZYEQ021000Join join = new ZYEQ021000Join();
        join.exec(joinMsg);

        // 3.
        ZYEQ021000Join.RETURN_CODE retCd = ZYEQ021000Join.RETURN_CODE.valueFromCode(joinMsg.getReturnCode());
        if (retCd == ZYEQ021000Join.RETURN_CODE.RTNCD_NORMAL) {
            // 検索結果あり
            // copy data from PMsg onto CMsg
            EZDMsg.copy(joinMsg, null, cMsg, null);

            // set value to pagenation items
            cMsg.xxPageShowToNum.setValue(joinMsg.xxPageShowToNum.getValue());
            cMsg.xxPageShowOfNum.setValue(joinMsg.xxPageShowOfNum.getValue());

            cMsg.setMessageInfo(MSG_CD_SEARCH_NORMAL_END);
        } else {
            // 検索結果なし
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();

            if (joinMsg.xxMsgIdList.getValidCount() > 0) {
                cMsg.setMessageInfo(joinMsg.xxMsgIdList.no(0).xxMsgId.getValue());
            } else {
                cMsg.setMessageInfo(MSG_CD_NOT_FOUND_UPLD_CSV_RQST);
            }
        }

        cMsg.upldCsvId_CT.setValue(cMsg.upldCsvId_0H.getValue());
    }
}
