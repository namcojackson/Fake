package business.blap.ZZXL0050;

import parts.common.EZDCDateItem;
import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSDateItem;
import parts.common.EZDSItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import business.blap.ZZXL0050.ZZXL0050CMsg;
import business.blap.ZZXL0050.ZZXL0050SMsg;
import business.blap.ZZXL0050.ZZXL0050Query;
import business.blap.ZZXL0050.constant.ZZXL0050Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;

public class ZZXL0050BL02 extends S21BusinessHandler implements ZZXL0050Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZXL0050_INIT".equals(screenAplID)) {
                doProcess_ZZXL0050_INIT((ZZXL0050CMsg) cMsg);

            } else if ("ZZXL0050Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn00_Search((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

            } else if ("ZZXL0050Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn00_TBLColumnSort((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

            } else if ("ZZXL0050Scrn00_Add".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn00_Add((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

            } else if ("ZZXL0050Scrn00_Edit".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn00_Edit((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

            } else if ("ZZXL0050Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn00_CMN_Delete((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

            } else if ("ZZXL0050Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn00_CMN_Clear((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

            } else if ("ZZXL0050Scrn01_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn01_CMN_Submit((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

            } else if ("ZZXL0050Scrn01_CMN_Return".equals(screenAplID)) {
                doProcess_ZZXL0050Scrn01_CMN_Return((ZZXL0050CMsg) cMsg, (ZZXL0050SMsg) sMsg);

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
    private void doProcess_ZZXL0050_INIT(ZZXL0050CMsg cMsg) {

        // set global company code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        
        // set Listbox
        cMsg.xxProcTpCd_L.no(0).setValue( S21CalendarUtilConstants.ONL_BAT_FLG_BAT_S21 );
        cMsg.xxDsplTpTxt_L.no(0).setValue( LS_BAT_S21 );

        cMsg.xxProcTpCd_L.no(1).setValue( S21CalendarUtilConstants.ONL_BAT_FLG_ONL_S21 );
        cMsg.xxDsplTpTxt_L.no(1).setValue( LS_ONL_S21 );
    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZXL0050Scrn00_Search(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {

        S21SsmEZDResult ssmResult = ZZXL0050Query.getInstance().getList(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            // search results

            // The setting of the excess time message of the greatest acquisition number
            if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
            }

            EZDMsg.copy(sMsg.A, null, cMsg.A, null);
            cMsg.A.setValidCount(sMsg.A.getValidCount());

        } else {
            // no search results
            cMsg.setMessageInfo("ZZZM9005W");
            sMsg.clear();
        }
    }

    /**
     * table column sort Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZXL0050Scrn00_TBLColumnSort(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {

        // check search item error
        if ( chkSearchItem(cMsg, sMsg) ) {
            return;
        }
        if (cMsg.A.getValidCount() == 0) {
            return;
        }

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            
            if ( ST_DT_PROC_CD.equals( sortItemNm ) ) {
                sortKey.add(ST_DT_MGT_PGM_ID, sortOrdBy);
                sortKey.add(ST_MGT_DT, sortOrdBy);
            } else if ( ST_DT_MGT_PGM_ID.equals( sortItemNm ) ) {
                sortKey.add(ST_DT_PROC_CD, sortOrdBy);
                sortKey.add(ST_MGT_DT, sortOrdBy);
            } else if ( ST_MGT_DT.equals( sortItemNm ) ) {
                sortKey.add(ST_DT_PROC_CD, sortOrdBy);
                sortKey.add(ST_DT_MGT_PGM_ID, sortOrdBy);
            }
            
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // data copy（SMsg -> CMsg）
            EZDMsg.copy(sMsg.A, null, cMsg.A, null);
            cMsg.A.setValidCount( sMsg.A.getValidCount() );
        }
        
    }
    
    /**
     * edit screen (append mode) Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZXL0050Scrn00_Add(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {
        
        // no processing
    }

    /**
     * edit screen (edit mode) Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZXL0050Scrn00_Edit(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {
        
        // check search item error
        if ( chkSearchItem(cMsg, sMsg) ) {
            return;
        }

        // set screen01 parameter
        int rownum = cMsg.xxRowNum.getValueInt();
        EZDMsg.copy(sMsg.A.no(rownum), "A", sMsg, "01");
        EZDMsg.copy(sMsg.A.no(rownum), "A", cMsg, "01");
    }

    /**
     * delete Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZXL0050Scrn00_CMN_Delete(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {

        // check search item error
        if ( chkSearchItem(cMsg, sMsg) ) {
            return;
        }
        // search processing
        doProcess_ZZXL0050Scrn00_Search(cMsg, sMsg);
        doProcess_ZZXL0050Scrn00_TBLColumnSort(cMsg, sMsg);
    }

    /**
     * clear Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZXL0050Scrn00_CMN_Clear(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {

        cMsg.clear();
        cMsg.A.setValidCount(0);

        sMsg.clear();
        sMsg.A.setValidCount(0);

        doProcess_ZZXL0050_INIT(cMsg);
    }

    /**
     * edit screen submit Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZXL0050Scrn01_CMN_Submit(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {
        // no processing
    }

    /**
     * edit screen return Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZXL0050Scrn01_CMN_Return(ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg) {

        // Initialization
        cMsg.dtProcCd_01.clear();
        cMsg.dtMgtPgmId_01.clear();
        cMsg.mgtDt_01.clear();
        cMsg.ezUpTime_01.clear();
        cMsg.ezUpTimeZone_01.clear();
        
        if ( sMsg.A.getValidCount() > 0 ) {
            doProcess_ZZXL0050Scrn00_Search(cMsg, sMsg);
            doProcess_ZZXL0050Scrn00_TBLColumnSort(cMsg, sMsg);
        }
        
    }

    /**
     * search item error check
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @return boolean false:error true:normal
     */
    private boolean chkSearchItem( ZZXL0050CMsg cMsg, ZZXL0050SMsg sMsg ) {
        
        boolean chkFlg = false;
        
        // compare EZDCMsg with EZDSMsg
        chkFlg |= chkItem( cMsg.glblCmpyCd      , sMsg.glblCmpyCd    , "Global Company Code" );
        chkFlg |= chkItem( cMsg.dtProcCd        , sMsg.dtProcCd      , "Date Type" );
        chkFlg |= chkItem( cMsg.dtMgtPgmId      , sMsg.dtMgtPgmId    , "Program ID (*)" );
        
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
