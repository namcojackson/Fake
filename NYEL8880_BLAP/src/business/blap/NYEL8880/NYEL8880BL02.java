/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NYEL8880;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

import business.blap.NYEL8880.common.NYEL8880CommonLogic;

/**
 *<pre>
 * NYEL8850BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/14   Fujitsu         Q09079          Create          N/A
 *</pre>
 */
public class NYEL8880BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NYEL8880CMsg bizMsg = (NYEL8880CMsg) cMsg;
            NYEL8880SMsg glblMsg = (NYEL8880SMsg) sMsg;

            if ("NYEL8880_INIT".equals(screenAplID)) {
                doProcess_NYEL8880_INIT(bizMsg, glblMsg);
            } else if("NYEL8880Scrn00_Search".equals(screenAplID)) {
                    doProcess_NYEL8880_INIT(bizMsg, glblMsg);
            } else if("NYEL8880Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NYEL8880Scrn00_TBLColumnSort(bizMsg, glblMsg);
            } else if("NYEL8880Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NYEL8880Scrn00_PageNext(bizMsg, glblMsg);
            } else if("NYEL8880Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NYEL8880Scrn00_PagePrev(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NYEL8880_INIT(NYEL8880CMsg bizMsg, NYEL8880SMsg glblMsg) {
        String myUser = getContextUserInfo().getUserId();

        NYEL8880CommonLogic.search(bizMsg, glblMsg, myUser);
    }
    
    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NYEL8880Scrn00_TBLColumnSort(NYEL8880CMsg cMsg, NYEL8880SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt.getValue();
        // Table:A
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget( sMsg.A, sMsg.A.no(0).getBaseContents() );
            S21SortKey sortKey = new S21SortKey();
            sortKey.add( sortItemNm, sortOrdBy );
            S21EZDMsgArraySort.sort( sortTarget, sortKey, 0, sMsg.A.getValidCount() );
            int i = 0;
            for(; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i ), null );
            }
            cMsg.A.setValidCount( i );

            cMsg.xxPageShowFromNum.setValue( 1 );
            cMsg.xxPageShowToNum.setValue( cMsg.A.getValidCount() );
        }
    }


    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NYEL8880Scrn00_PageNext(NYEL8880CMsg cMsg, NYEL8880SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowToNum.getValueInt());

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
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
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NYEL8880Scrn00_PagePrev(NYEL8880CMsg cMsg, NYEL8880SMsg sMsg) {
        cMsg.xxPageShowFromNum.setValue(cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1);

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i              = pagenationFrom;
        for( ; i < pagenationFrom + cMsg.A.length(); i++ ) {
            if( i < sMsg.A.getValidCount() ) {
                EZDMsg.copy( sMsg.A.no( i ), null, cMsg.A.no( i - pagenationFrom ), null );
            } else {
                break;
            }
        }
        cMsg.A.setValidCount( i - pagenationFrom );

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        cMsg.xxPageShowFromNum.setValue( pagenationFrom );
        cMsg.xxPageShowToNum.setValue( pagenationFrom + cMsg.A.getValidCount() - 1 );
    }
}
