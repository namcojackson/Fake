package business.servlet.NMAL6050.common;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6050.NMAL6050BMsg;
import business.servlet.NMAL6050.constant.NMAL6050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 *  Common Code PopUp Common Logic (SV)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   Hitachi         T.Arakawa       Update          WDS Defect#2453
 *</pre>
 */
public class NMAL6050CommonLogic implements NMAL6050Constant {

    /**
     * 共通ボタンの属性制御
     * 
     * <pre>
     * F8:ClearボタンおよびF9:Closeボタンの設定を行う。
     * 
     * </pre>
     * 
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * 固有ボタン属性制御
     * 
     * <pre>
     * 検索ボタンの活性化を行う。
     * 
     * </pre>
     * 
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {

        handler.setButtonEnabled(BTN_SEARCH[0], true);
    }

    /**
     * 初期化処理
     * 
     * <pre>
     * 画面のクリアを行う。
     * 
     * </pre>
     * 
     * @param scrnMsg NMAL6050BMsg
     */
    public static void clearCondition(NMAL6050BMsg scrnMsg) {

        scrnMsg.xxCondCd.clear();
        scrnMsg.xxCondNm.clear();
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
    }

    /**
     * ＣＯＤＥが語尾につく不要文字列削除
     * 
     * <pre>
     * いらない文字がないかをチェックして削除します。
     * 
     * </pre>
     * 
     * @param String retrievedString チェック対象文字列
     * @return String 不要文字削除後の文字列
     */
    public static String catEndWithStringCd(String retrievedString) {

        String retString = new String(catEndWith(retrievedString, "CODE"));

        if (retrievedString.equals(retString)) {
            retString = new String(catEndWith(retrievedString, "CD"));
        }

        return retString;

    }

    /**
     * ＮＡＭＥが語尾につく不要文字列削除
     * 
     * <pre>
     * いらない文字がないかをチェックして削除します。
     * 
     * </pre>
     * 
     * @param String retrievedString チェック対象文字列
     * @return String 不要文字削除後の文字列
     */
    public static String catEndWithStringNm(String retrievedString) {

        String retString = new String(catEndWith(retrievedString, "NAME"));

        if (retrievedString.equals(retString)) {
            retString = new String(catEndWith(retrievedString, "NM"));
        }

        return retString;

    }

    /**
     * 語尾につく不要文字列削除部品
     * 
     * <pre>
     * 指定された文字が語尾についていた場合その文字を切って値を返却します。
     * 指定された文字が語尾についていない場合そのままの文字列を返却します。
     * 
     * </pre>
     * 
     * @param String retrievedString チェック対象文字列
     * @param String retrieveString 指定文字列
     * @return String
     */
    private static String catEndWith(String retrievedString, String retrieveString) {

        if (retrievedString.length() >= retrieveString.length()) {
            if (retrievedString.toUpperCase().endsWith(retrieveString.toUpperCase())) {
                return retrievedString.substring(0, retrievedString.length() - retrieveString.length());
            }
        }
        return retrievedString;

    }

    /**
     * 画面の表示設定
     * 
     * <pre>
     * 画面の表示設定を行います
     * ・ソートの△or▽を削除
     * ・明細行カラー制御
     * ・フォーカス制御
     * 
     * </pre>
     * 
     * @param scrnMsg NMAL6050BMsg
     * @param String[][] BaseContents
     */
    public static void initScrn(NMAL6050BMsg scrnMsg, String[][] BaseContents) {

        // ソートの△or▽を削除
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, BaseContents);

        // 明細行カラー制御
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

        // フォーカス制御
        scrnMsg.setFocusItem(scrnMsg.xxCondCd);

    }

    // S-ADD-20130920 WDS Defect#2453
    /**
     * Convert to i18n label names
     * @param scrnMsg NMAL6050BMsg
     */
    public static void convLabelNames(NMAL6050BMsg scrnMsg) {
        List<EZDBStringItem> items = new ArrayList<EZDBStringItem>();

        // Screen name
        items.add(scrnMsg.xxScrNm);
        // Header code label
        items.add(scrnMsg.xxHdrCdLbNm);
        // Header name label
        items.add(scrnMsg.xxHdrNmLbNm);
        // Detail code label
        items.add(scrnMsg.xxDtlCdLbNm);
        // Detail name label
        items.add(scrnMsg.xxDtlNmLbNm);

        // Convert
        EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        for (EZDBStringItem item : items) {
            ZYPEZDItemValueSetter.setValue(item, converter.convLabel2i18nLabel("", item.getValue()));
        }
    }
    // E-ADD-20130920 WDS Defect#2453

}
