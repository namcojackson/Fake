package business.blap.ZZOL0050;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0050.constant.ZZOL0050Constant;
import business.db.GLBL_CMPYTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

public class ZZOL0050BL02 extends S21BusinessHandler implements ZZOL0050Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("ZZOL0050_INIT".equals(screenAplID)) {
                doProcess_ZZOL0050_INIT((ZZOL0050CMsg) cMsg);

            } else if ("ZZOL0050Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZOL0050Scrn00_Search((ZZOL0050CMsg) cMsg, (ZZOL0050SMsg) sMsg);

            } else if ("ZZOL0050Scrn00_PageNext".equals(screenAplID)) {
                doProcess_ZZOL0050Scrn00_PageNext((ZZOL0050CMsg) cMsg, (ZZOL0050SMsg) sMsg);

            } else if ("ZZOL0050Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_ZZOL0050Scrn00_PagePrev((ZZOL0050CMsg) cMsg, (ZZOL0050SMsg) sMsg);

            } else if ("ZZOL0050Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_ZZOL0050Scrn00_TBLColumnSort((ZZOL0050CMsg) cMsg, (ZZOL0050SMsg) sMsg);

            } else if ("ZZOL0050Scrn00_CMN_Delete".equals(screenAplID)) {
                doProcess_ZZOL0050Scrn00_CMN_Delete((ZZOL0050CMsg) cMsg, (ZZOL0050SMsg) sMsg);

            } else if ("ZZOL0050Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_ZZOL0050Scrn00_CMN_Clear((ZZOL0050CMsg) cMsg, (ZZOL0050SMsg) sMsg);

            } else if ("ZZOL0050_ZZOL0051".equals(screenAplID)) {
                doProcess_ZZOL0050_ZZOL0051((ZZOL0050CMsg) cMsg, (ZZOL0050SMsg) sMsg);

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
    private void doProcess_ZZOL0050_INIT(ZZOL0050CMsg cMsg) {

        // set global company code
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
    }

    /**
     * search processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0050Scrn00_Search(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

        //Check exist Global company code
        if (!chkGlbCmpCd(cMsg)) {
            return;
        }
        S21SsmEZDResult ssmResult = ZZOL0050Query.getInstance().getList(cMsg, sMsg);

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
            // set global company code as previous value.
            cMsg.glblCmpyCd_H.setValue(cMsg.glblCmpyCd.getValue());

            // 検索結果なし
        } else {
            if ( cMsg.getMessageInfo() == null ) {
                cMsg.setMessageInfo("ZZZM9005W");
            }
            EZDMsg.copy(sMsg.A, null, cMsg.A, null);

            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }
    
    /**
     * @param cMsg ZZOL0050CMsg
     * @param sMsg ZZOL0050SMsg
     */
    public static void searchZZOL0050Scrn00_returnZZOL0051Scrn00(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        if (pagenationFrom == -1) {
            pagenationFrom += 1;
        }

        S21SsmEZDResult ssmResult = ZZOL0050Query.getInstance().getList(cMsg, sMsg);

        // search results
        if (ssmResult.isCodeNormal()) {
            // Setting of the excess of the maximum acquisition number
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }
            
            cMsg.A.clear();
            cMsg.A.setValidCount(0);       
            
            // The posting of search results for the designated page
            int cnt = 0;
            for (int k = pagenationFrom; k < sMsg.A.getValidCount(); k++) {
                if (cnt < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(k), null, cMsg.A.no(cnt), null);
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
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.A.clear();
            cMsg.A.setValidCount(0);
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
    private void doProcess_ZZOL0050Scrn00_PageNext(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int prevPagenationFrom = pagenationFrom - cMsg.A.length();

        // set next page data
        setChkBox(cMsg, sMsg, prevPagenationFrom);
        setPagenation(cMsg, sMsg, pagenationFrom);
    }
    
    /**
     * previous page Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0050Scrn00_PagePrev(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

        // get start row number of page
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int nextPagenationFrom = pagenationFrom + cMsg.A.length();

        // set prev page data
        setChkBox(cMsg, sMsg, nextPagenationFrom);
        setPagenation(cMsg, sMsg, pagenationFrom);
    }

    /**
     * table column sort Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0050Scrn00_TBLColumnSort(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

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
            if ( !ST_UPLD_CSV_ID.equals( sortItemNm ) ) {
                sortKey.add(ST_UPLD_CSV_ID, sortOrdBy);
            }
            
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // data copy（SMsg -> CMsg）
            EZDMsg.copy(sMsg.A, null, cMsg.A, null);
            cMsg.A.setValidCount( sMsg.A.getValidCount() );
        }
    }
    
    /**
     * delete Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0050Scrn00_CMN_Delete(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

        // search processing
        doProcess_ZZOL0050Scrn00_Search(cMsg, sMsg);
        doProcess_ZZOL0050Scrn00_TBLColumnSort(cMsg, sMsg);
    }

    /**
     * clear Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0050Scrn00_CMN_Clear(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

        cMsg.clear();
        cMsg.A.setValidCount(0);

        sMsg.clear();
        sMsg.A.setValidCount(0);

        doProcess_ZZOL0050_INIT(cMsg);
    }
    
    /**
     * edit screen return Processing
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_ZZOL0050_ZZOL0051(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg) {

        // search processing
//        doProcess_ZZOL0050Scrn00_Search(cMsg, sMsg);
        searchZZOL0050Scrn00_returnZZOL0051Scrn00(cMsg, sMsg);
        doProcess_ZZOL0050Scrn00_TBLColumnSort(cMsg, sMsg);
    }
    
    /**
     * set checkbox value
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param pagenationFrom  Page row number
     */
    private void setChkBox(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg, int pagenationFrom) {
        
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
    private void setPagenation(ZZOL0050CMsg cMsg, ZZOL0050SMsg sMsg, int pagenationFrom) {

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
    
    private  boolean chkGlbCmpCd(ZZOL0050CMsg cMsg) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();

        // Search GLBL_CMPY table by Primary Key
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();

        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] {"Global Company Code"});
            return false;
        }
        return true;
    }

}
