package business.blap.ZZML0040;

import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import business.blap.ZZML0010.constant.ZZML0010Constant;
import business.blap.ZZML0040.constant.ZZML0040Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

public class ZZML0040BL02 extends S21BusinessHandler implements ZZML0040Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZML0040_INIT".equals(screenAplID)) {
                doProcess_ZZML0040_INIT((ZZML0040CMsg) cMsg);

            } else if ("ZZML0040Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_Search((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);
                
            } else if ("ZZML0040Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_CMN_Reset((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);
                
            } else if ("ZZML0040Scrn01_CMN_Reset".equals(screenAplID)) {
                doProcess_ZZML0040Scrn01_CMN_Reset((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_PageNext((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_PagePrev((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn00_View".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_View((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn00_Edit".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_Edit((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn00_Add".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_Add((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_CMN_Delete((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZML0040Scrn00_CMN_Clear((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn01_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZML0040Scrn01_CMN_Submit((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else if ("ZZML0040Scrn01_CMN_Return".equals(screenAplID)) {
                doProcess_ZZML0040Scrn01_CMN_Return((ZZML0040CMsg) cMsg, (ZZML0040SMsg) sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * initialization processing
     * @param cMsg EZDCMsg
     */
    private void doProcess_ZZML0040_INIT(ZZML0040CMsg cMsg) {

        // set global company code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn00_Search(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        S21SsmEZDResult ssmResult = ZZML0040Query.getInstance().getList(cMsg, sMsg);

        // 検索結果あり
        if (ssmResult.isCodeNormal()) {
            // 最大取得件数の超過時メッセージの設定
            int queryResCnt = ssmResult.getQueryResultCount();         
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }
            // Change language code to language name.
            for (int j = 0;j < queryResCnt; j++) {
                String langCode = sMsg.A.no(j).langNm_A.getValue();
                for (ZZML0040Constant.Language langCd : ZZML0040Constant.Language.values()) {
                    if (langCode.equalsIgnoreCase(langCd.toString())) {
                        sMsg.A.no(j).langNm_A.setValue(langCd.getLangName());
                        break;
                    }
                }
            } 

            // １ページ分の検索結果の転記（SMsg -> CMsg）
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // 改ページ項目への値の設定
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

            // 検索結果なし
        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
            cMsg.A.setValidCount(0);
            
            sMsg.clear();
        }
    }

    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn00_PageNext(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int prevPagenationFrom = pagenationFrom - cMsg.A.length();

        // check search item error
        if ( chkSearchItem(cMsg, sMsg) ) {
            cMsg.xxPageShowFromNum.setValue(prevPagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(prevPagenationFrom + cMsg.A.getValidCount());
            return;
        }

        // set next page data
        setChkBox(cMsg, sMsg, prevPagenationFrom);
        setPagenation(cMsg, sMsg, pagenationFrom);
    }
    
    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn00_CMN_Reset(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        doProcess_ZZML0040Scrn00_CMN_Clear(cMsg, sMsg);
    }
    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn01_CMN_Reset(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {
        // Add
        if (cMsg.xxRowNum.getValueInt() == -1) {
            cMsg.mlTmplId_01.clear();
            cMsg.mlSubjTmplTxt_01.clear();
            cMsg.xxMlBodyTxt_01.clear();
            // set dropdown listbox value
            if (!setLangList(cMsg)) {
                return;
            }
            cMsg.mlLocId_01.setValue(LANG);
            return;
        }


        // Edit
        doProcess_ZZML0040Scrn00_View(cMsg, sMsg);


    }
    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn00_PagePrev(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int nextPagenationFrom = pagenationFrom + cMsg.A.length();

        // check search item error
        if ( chkSearchItem(cMsg, sMsg) ) {
            cMsg.xxPageShowFromNum.setValue(nextPagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(nextPagenationFrom + cMsg.A.getValidCount());
            return;
        }

        // set prev page data
        setChkBox(cMsg, sMsg, nextPagenationFrom);
        setPagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * edit screen (view mode) Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn00_View(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        int rownum = cMsg.xxRowNum.getValueInt() + cMsg.xxPageShowFromNum.getValueInt() - 1;

        S21SsmEZDResult ssmResult = ZZML0040Query.getInstance().getDetail(cMsg, sMsg, rownum);
        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo("ZZZM9006E", new String[] {"ML_TMPL table data" });
            return;
        }

        sMsg.ezUpTime_01.setValue(cMsg.ezUpTime_01.getValue());
        sMsg.ezUpTimeZone_01.setValue(cMsg.ezUpTimeZone_01.getValue());

        // set dropdown listbox value
        if (!setLangList(cMsg)) {
            return;
        }
    }

    /**
     * edit screen (edit mode) Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn00_Edit(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {
        
        doProcess_ZZML0040Scrn00_View(cMsg, sMsg);
    }

    /**
     * edit screen (append mode) Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn00_Add(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        // check search item error
        if ( cMsg.A.getValidCount() > 0 && chkSearchItem(cMsg, sMsg) ) {
            return;
        }

        // set global company code
        cMsg.glblCmpyCd_01.setValue(cMsg.glblCmpyCd.getValue());

        // set dropdown listbox value
        if (!setLangList(cMsg)) {
            return;
        }
        cMsg.mlLocId_01.setValue(LANG);
    }

    /**
     * delete Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn00_CMN_Delete(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        // check search item error
        if ( chkSearchItem(cMsg, sMsg) ) {
            return;
        }

//        reSearch(cMsg, sMsg);
        doProcess_ZZML0040Scrn00_Search(cMsg, sMsg);
    }

    /**
     * clear Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn00_CMN_Clear(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        cMsg.clear();
        cMsg.A.setValidCount(0);

        sMsg.clear();
        sMsg.A.setValidCount(0);

        doProcess_ZZML0040_INIT(cMsg);
    }

    /**
     * edit screen submit Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn01_CMN_Submit(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {
        
        // flag finished with submit processing practice
        sMsg.xxRsltFlg_01.setValue( FLG_SUBMIT );
    }
    
    /**
     * edit screen return Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0040Scrn01_CMN_Return(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {

        // Initialization
        cMsg.glblCmpyCd_01.clear();
        cMsg.mlTmplId_01.clear();
        cMsg.mlLocId_01.clear();
        cMsg.mlSubjTmplTxt_01.clear();
        cMsg.xxMlBodyTxt_01.clear();
        cMsg.ezUpTime_01.clear();
        cMsg.ezUpTimeZone_01.clear();
        cMsg.xxRowNum.clear();

        sMsg.ezUpTime_01.clear();
        sMsg.ezUpTimeZone_01.clear();

        // When there is update, search it
        if ( sMsg.xxRsltFlg_01.getValue().length() > 0  && sMsg.A.getValidCount() > 0 ) {
            reSearch(cMsg, sMsg);
        }
        sMsg.xxRsltFlg_01.clear();
    }

    /**
     * set Language listbox value
     * @param cMsg
     * @return boolean false:error true:success
     */
    private boolean setLangList(ZZML0040CMsg cMsg) {

        // START 2013/08/15 M.Sumida Mod from language only to locale(lang + country)
        // cMsg.langCd_01.clear();
        cMsg.mlLocId.clear();
        // END   2013/08/15 M.Sumida Mod from language only to locale(lang + country)
        cMsg.langNm_01.clear();

        int j = 0;
        for (ZZML0040Constant.Language langCd : ZZML0040Constant.Language.values()) {
            // START 2013/08/15 M.Sumida Mod from language only to locale(lang + country)
            // cMsg.langCd_01.no(j).setValue(langCd.toString());
            cMsg.mlLocId.no(j).setValue(langCd.toString());
            // END   2013/08/15 M.Sumida Mod from language only to locale(lang + country)
            cMsg.langNm_01.no(j).setValue(langCd.getLangName());
            j++;
        }
        return true;
    }

    /**
     * set checkbox value
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param pagenationFrom  Page row number
     */
    private void setChkBox(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg, int pagenationFrom) {
        
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
            if (i >= sMsg.A.getValidCount()) {
                break;
            }
            // checkbox value copy from SMsg onto CMsg
            sMsg.A.no(i).xxChkBox_A.setValue( cMsg.A.no(i - pagenationFrom).xxChkBox_A.getValue() );
        }
    }

    /**
     * set pagenation
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param pagenationFrom  Page row number
     */
    private void setPagenation(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg, int pagenationFrom) {

        // copy data from SMsg onto CMsg
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i >= sMsg.A.getValidCount()) {
                break;
            }
            EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
    }
    
    /**
     * re-search without changing a page position
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void reSearch(ZZML0040CMsg cMsg, ZZML0040SMsg sMsg) {
        
        S21SsmEZDResult ssmResult = ZZML0040Query.getInstance().getList(cMsg, sMsg);

        // search results
        if (ssmResult.isCodeNormal()) {
            // Setting of the excess of the maximum acquisition number
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                queryResCnt = sMsg.A.length();
            }
            // Change language code to language name.
            for (int j = 0;j < queryResCnt; j++) {
                String langCode = sMsg.A.no(j).langNm_A.getValue();
                for (ZZML0040Constant.Language langCd : ZZML0040Constant.Language.values()) {
                    if (langCode.equalsIgnoreCase(langCd.toString())) {
                        sMsg.A.no(j).langNm_A.setValue(langCd.getLangName());
                        break;
                    }
                }
            }

            // get start row number of page
            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
            while (queryResCnt <= pagenationFrom) {
                pagenationFrom -= cMsg.A.length();
            }

            cMsg.A.clear();
            cMsg.A.setValidCount(0);

            // The posting of search results for the designated page
            int cnt = 0;
            for (int i = pagenationFrom; i < sMsg.A.getValidCount(); i++) {
                if (cnt < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
                } else {
                    break;
                }
                cnt++;
            }
            cMsg.A.setValidCount(cnt);

            // The setting of the value to the turning a page item
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

            // no search results
        } else {
            cMsg.A.clear();
            cMsg.A.setValidCount(0);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }

    }

    /**
     * search item error check
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return boolean false:error true:normal
     */
    private boolean chkSearchItem( ZZML0040CMsg cMsg, ZZML0040SMsg sMsg ) {
        
        boolean chkFlg = false;
        
        // compare EZDCMsg with EZDSMsg
        chkFlg |= chkItem( cMsg.glblCmpyCd      , sMsg.glblCmpyCd       , "Global Company Code" );
        chkFlg |= chkItem( cMsg.mlTmplId        , sMsg.mlTmplId         , "Mail Template ID(*)" );
        chkFlg |= chkItem( cMsg.mlSubjTmplTxt   , sMsg.mlSubjTmplTxt    , "Subject(*)" );
        
        return chkFlg;
    }
    
    /**
     * item comparison
     * @param cItem   EZDCItem
     * @param sItem   EZDSItem
     * @param msgCode message code
     * @return boolean  false:different value  true:constant value
     */
    private boolean chkItem( EZDCItem cItem, EZDSItem sItem, String msgCode ) {
        String cStr = null;
        String sStr = null;
        
        if ( cItem instanceof EZDCStringItem ) {
            cStr = ( (EZDCStringItem) cItem ).getValue();
        } else
        if ( cItem instanceof EZDCDateItem ) {
            cStr = ( (EZDCDateItem) cItem ).getValue();            
        }

        if ( sItem instanceof EZDSStringItem ) {
            sStr = ( (EZDSStringItem) sItem ).getValue();
        } else
        if ( sItem instanceof EZDSDateItem ) {
            sStr = ( (EZDSDateItem) sItem ).getValue();            
        }
        
        if ( !cStr.equals( sStr ) ) {
            cItem.setErrorInfo( 1, "ZZZM9021E", new String[] { msgCode } );
            return true;
        }

        return false;
    }

}
