/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/06/2009   Fujitsu         T.Kawamura      Create          N/A
 * 03/03/2020   Fujitsu         K.takahama      Update          QC#56127
 *</pre>
 */
package business.blap.ZZML0030;

import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import business.blap.ZZML0030.constant.ZZML0030Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class ZZML0030BL02 extends S21BusinessHandler implements ZZML0030Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZML0030_INIT".equals(screenAplID)) {
                doProcess_ZZML0030_INIT((ZZML0030CMsg) cMsg);

            } else if ("ZZML0030Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZML0030Scrn00_Search((ZZML0030CMsg) cMsg, (ZZML0030SMsg) sMsg);

            } else if ("ZZML0030Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZML0030Scrn00_PageNext((ZZML0030CMsg) cMsg, (ZZML0030SMsg) sMsg);

            } else if ("ZZML0030Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZML0030Scrn00_PagePrev((ZZML0030CMsg) cMsg, (ZZML0030SMsg) sMsg);

            } else if ("ZZML0030Scrn00_View".equals(screenAplID)) {
                doProcess_ZZML0030Scrn00_View((ZZML0030CMsg) cMsg, (ZZML0030SMsg) sMsg);

            } else if ("ZZML0030Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZML0030Scrn00_CMN_Clear((ZZML0030CMsg) cMsg, (ZZML0030SMsg) sMsg);

            } else if ("ZZML0030Scrn00_Send".equals(screenAplID)) {
                doProcess_ZZML0030Scrn00_Send((ZZML0030CMsg) cMsg, (ZZML0030SMsg) sMsg);
                
            } else if ("ZZML0030Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZML0030Scrn00_TBLColumnSort((ZZML0030CMsg) cMsg, (ZZML0030SMsg) sMsg);
                
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
    private void doProcess_ZZML0030_INIT(ZZML0030CMsg cMsg) {

        String hmCd = null;
        String hmDisp = null;

        // set status listbox
        for ( int i = 0; i < cMsg.mlSendStsCd_HC.length(); i++ ) {
            cMsg.mlSendStsCd_HC.no(i).setValue( ML_SEND_STS[i][COL_STS_CD] );
            cMsg.xxScrItem7Txt_H.no(i).setValue( ML_SEND_STS[i][COL_STS_NM] );
        }
        
        // 03/03/2020 Mod QC#56127 Start
        // set mail address type listbox
        for ( int i = 0; i < cMsg.mlAddrTpCd_TC.length(); i++ ) {
            cMsg.mlAddrTpCd_TC.no(i).setValue( ML_ADDR_TP[i][COL_STS_CD] );
            cMsg.xxScrItem8Txt_T.no(i).setValue( ML_ADDR_TP[i][COL_STS_NM] );
        }
        // 03/03/2020 Mod QC#56127 End
        
        // set time listbox
        for ( int i = 0; i < cMsg.xxHrs_FC.length(); i++ ) {
            hmCd = String.format("%02d", i);
            hmDisp = String.format("%02d:00", i);
            
            cMsg.xxHrs_FC.no(i).setValue(hmCd);
            cMsg.xxHrsMn_F.no(i).setValue(hmDisp);
            
            cMsg.xxHrs_TC.no(i).setValue(hmCd);
            cMsg.xxHrsMn_T.no(i).setValue(hmDisp);
        }
        
        // set global company code
        cMsg.glblCmpyCd.setValue( getGlobalCompanyCode() );

    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0030Scrn00_Search(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg) {

        S21SsmEZDResult ssmResult = ZZML0030Query.getInstance().getList(cMsg, sMsg);

        // 検索結果あり
        if (ssmResult.isCodeNormal()) {
            // 最大取得件数の超過時メッセージの設定
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
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
        }
    }
    
    /**
     * next page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0030Scrn00_PageNext(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();

        // search item error check
        if ( chkSearchItem( cMsg, sMsg ) ) {
            copyData(cMsg, sMsg, pagenationFrom - cMsg.A.length(), true );
            return;
        }
        
        // copy data from SMsg onto CMsg
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue( pagenationFrom + 1 );
        cMsg.xxPageShowToNum.setValue( pagenationFrom + cMsg.A.getValidCount() );
    }
    
    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0030Scrn00_PagePrev(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();

        // search item error check
        if ( chkSearchItem( cMsg, sMsg ) ) {
            copyData(cMsg, sMsg, pagenationFrom + cMsg.A.length(), true );
            return;
        }
        
        // copy data from SMsg onto CMsg
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue( pagenationFrom + 1 );
        cMsg.xxPageShowToNum.setValue( pagenationFrom + cMsg.A.getValidCount() );
    }
    
    /**
     * detail screen Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0030Scrn00_View(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg) {
        
        S21SsmEZDResult ssmResult = null;
        
        cMsg.B.clear();
        cMsg.B.setValidCount( 0 );
        cMsg.C.clear();
        cMsg.C.setValidCount( 0 );
        cMsg.glblCmpyCd_01.clear();
        cMsg.mlSubjTxt_01.clear();
        cMsg.xxMlBodyTxt_01.clear();

        sMsg.B.clear();
        sMsg.B.setValidCount( 0 );
        sMsg.C.clear();
        sMsg.C.setValidCount( 0 );
        sMsg.glblCmpyCd_01.clear();
        sMsg.mlSubjTxt_01.clear();
        sMsg.xxMlBodyTxt_01.clear();

        int rownum = cMsg.xxRowNum.getValueInt() + cMsg.xxPageShowFromNum.getValueInt() - 1;
        
        ssmResult = ZZML0030Query.getInstance().getMlUsr(cMsg, sMsg, rownum);
        if (ssmResult.isCodeNormal()) {
            EZDMsg.copy(sMsg.B, null, cMsg.B, null);
        } else {
            cMsg.setMessageInfo("ZZZM9006E", new String[] {"ML_USR table data"});
        }

        ssmResult = ZZML0030Query.getInstance().getMlAtt(cMsg, sMsg, rownum);
        if (ssmResult.isCodeNormal()) {
            EZDMsg.copy(sMsg.C, null, cMsg.C, null);
        }

        ssmResult = ZZML0030Query.getInstance().getMlSend(cMsg, sMsg, rownum);
        if (ssmResult.isCodeNormal()) {
            cMsg.glblCmpyCd_01.setValue( sMsg.glblCmpyCd_01.getValue() );
            cMsg.mlSubjTxt_01.setValue( sMsg.mlSubjTxt_01.getValue() );
            cMsg.xxMlBodyTxt_01.setValue( sMsg.xxMlBodyTxt_01.getValue() );
        } else {
            cMsg.setMessageInfo("ZZZM9006E", new String[] {"ML_SEND table data"});
        }

    }
    
    /**
     * clear Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0030Scrn00_CMN_Clear(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg) {

        cMsg.clear();
        cMsg.A.setValidCount(0);

        sMsg.clear();
        sMsg.A.setValidCount(0);

        doProcess_ZZML0030_INIT(cMsg);
    }

    /**
     * research processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0030Scrn00_Send(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg) {

        // search item error check
        if (chkSearchItem(cMsg, sMsg)) {
            copyData(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt(), false);
            return;
        }

        int rownum = cMsg.xxRowNum.getValueInt();
        String rstFlg = cMsg.A.no( rownum ).xxRsltFlg_A.getValue();
        if ( rstFlg.length() > 0 ) {
            return;
        }
        
        S21SsmEZDResult ssmResult = ZZML0030Query.getInstance().getList(cMsg, sMsg);

        // 検索結果あり
        if (ssmResult.isCodeNormal()) {
            // 最大取得件数の超過時メッセージの設定
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                queryResCnt = sMsg.A.length();
            }

            // get start row number of page
            int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
            while (queryResCnt <= pagenationFrom) {
                pagenationFrom -= cMsg.A.length();
            }

            // １ページ分の検索結果の転記（SMsg -> CMsg）
            int cnt = 0;
            for (int i = pagenationFrom; i < sMsg.A.getValidCount(); i++) {
                if (cnt == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
                cnt++;
            }
            cMsg.A.setValidCount(cnt);

            // 改ページ項目への値の設定
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

            // 検索結果なし
        } else {
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
    private boolean chkSearchItem( ZZML0030CMsg cMsg, ZZML0030SMsg sMsg ) {
        
        boolean chkFlg = false;
        
        // compare EZDCMsg with EZDSMsg
        chkFlg |= chkItem( cMsg.glblCmpyCd      , sMsg.glblCmpyCd       , "Global Company Code" );
        chkFlg |= chkItem( cMsg.mlSendStsCd_H   , sMsg.mlSendStsCd_H    , "Status" );
        chkFlg |= chkItem( cMsg.mlSubjTxt_H     , sMsg.mlSubjTxt_H      , "Subject(*)" );
        chkFlg |= chkItem( cMsg.mlUsrAddr_H     , sMsg.mlUsrAddr_H      , "Mail Address From(*)" );
        // 03/03/2020 Mod QC#56127 Start
        chkFlg |= chkItem( cMsg.mlAddrTpCd_T     , sMsg.mlAddrTpCd_T      , "Mail Address Type" );
        chkFlg |= chkItem( cMsg.mlUsrAddr_T     , sMsg.mlUsrAddr_T      , "Mail Address To(*)" );
        // 03/03/2020 Mod QC#56127 End
        chkFlg |= chkItem( cMsg.xxFromDt_H      , sMsg.xxFromDt_H       , "Mail Send Date(From)" );
        chkFlg |= chkItem( cMsg.xxToDt_H        , sMsg.xxToDt_H         , "Mail Send Date(To)" );
        chkFlg |= chkItem( cMsg.xxHrs_F         , sMsg.xxHrs_F          , "Mail Send Time(From)" );
        chkFlg |= chkItem( cMsg.xxHrs_T         , sMsg.xxHrs_T          , "Mail Send Time(To)" );
        
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
    
    /**
     * Table sort processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZML0030Scrn00_TBLColumnSort(ZZML0030CMsg cMsg, ZZML0030SMsg sMsg) {
        
        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();
            
        // Table:A
        if( "A".equals(sortTblNm ) ) {
                
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget( sMsg.A, sMsg.A.no(0).getBaseContents() );
            S21SortKey sortKey = new S21SortKey();
            sortKey.add( sortItemNm, sortOrdBy );
            S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, sMsg.A.getValidCount() );
                
                // 転記（SMsg -> CMsg）
                int i = 0;
                for( ; i < sMsg.A.getValidCount(); i++ ) {
                    if( i == cMsg.A.length() ) {
                        break;
                    }
                    EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i ), null );
                }
                cMsg.A.setValidCount( i );
                
                // 改ページ項目への値の設定（１ページ目を表示）
                cMsg.xxPageShowFromNum.setValue( 1 );
                cMsg.xxPageShowToNum.setValue( cMsg.A.getValidCount() );
        }
    }

    /**
     * data copy
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param pagenationFrom Page Number
     * @param boolean revFlg Revision value Flag
     */
    private void copyData( ZZML0030CMsg cMsg, ZZML0030SMsg sMsg, int pagenationFrom, boolean revFlg ) {

        if ( !revFlg ) {
            pagenationFrom--;
        }

        int num = 0;
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                num = i - pagenationFrom;

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(num), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );

        // set value to pagenation items
        cMsg.xxPageShowFromNum.setValue( pagenationFrom + 1 );
        cMsg.xxPageShowToNum.setValue( pagenationFrom + cMsg.A.getValidCount() );
    }

}
