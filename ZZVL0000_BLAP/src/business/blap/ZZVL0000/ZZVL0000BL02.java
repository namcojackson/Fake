package business.blap.ZZVL0000;

import java.math.BigDecimal;
//START 01/27/17 C.Ogaki [Add] Release 2000 byte length limit
import java.util.List;
// END   01/27/17 C.Ogaki [Add] Release 2000 byte length limit

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.ZZVL0000.constant.ZZVL0000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/11/04   Fujitsu         C.Ogaki         Create          N/A
 * 2017/01/27   Fujitsu         C.Ogaki         Update          ---
 *</pre>
 */
public class ZZVL0000BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplId = cMsg.getScreenAplID();
            if ("ZZVL0000Scrn00_Search".equals(screenAplId)) {
                doProcess_ZZVL0000Scrn00_Search((ZZVL0000CMsg) cMsg, (ZZVL0000SMsg) sMsg);
            } else if ("ZZVL0000Scrn00_PageNext".equals(screenAplId)) {
                doProcess_ZZVL0000Scrn00_PageNext((ZZVL0000CMsg) cMsg, sMsg);
            } else if ("ZZVL0000Scrn00_PagePrev".equals(screenAplId)) {
                doProcess_ZZVL0000Scrn00_PagePrev((ZZVL0000CMsg) cMsg, sMsg);
            } else if ("ZZVL0000Scrn00_TBLColumnSort".equals(screenAplId)) {
                doProcess_ZZVL0000Scrn00_TBLColumnSort((ZZVL0000CMsg) cMsg, (ZZVL0000SMsg) sMsg);
            } else if ("ZZVL0000Scrn01_Search".equals(screenAplId)) {
                doProcess_ZZVL0000Scrn01_Search((ZZVL0000CMsg) cMsg, (ZZVL0000SMsg) sMsg);
            } else if ("ZZVL0000Scrn01_PageNext".equals(screenAplId)) {
                doProcess_ZZVL0000Scrn01_PageNext((ZZVL0000CMsg) cMsg, (ZZVL0000SMsg) sMsg);
            } else if ("ZZVL0000Scrn01_PagePrev".equals(screenAplId)) {
                doProcess_ZZVL0000Scrn01_PagePrev((ZZVL0000CMsg) cMsg, (ZZVL0000SMsg) sMsg);
            } else if ("ZZVL0000Scrn01_CMN_Return".equals(screenAplId)) {
                doProcess_ZZVL0000Scrn01_CMN_Return((ZZVL0000CMsg) cMsg, (ZZVL0000SMsg) sMsg);
            } else if ("ZZVL0000Scrn01_TBLColumnSort".equals(screenAplId)) {
                doProcess_ZZVL0000Scrn01_TBLColumnSort((ZZVL0000CMsg) cMsg, (ZZVL0000SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZVL0000Scrn00_Search(ZZVL0000CMsg cMsg, ZZVL0000SMsg sMsg) {

        // START 01/27/17 C.Ogaki [Modify] Release 2000 byte length limit
//        S21SsmEZDResult ssmResult = ZZVL0000Query.getInstance().getPreferredViewListForAdd(cMsg, sMsg);
//
//        if( ssmResult.isCodeNormal() ) {
//
//            int queryResCnt = ssmResult.getQueryResultCount();
//            if( queryResCnt > sMsg.B.length() ) {
//                cMsg.setMessageInfo( "ZZZM9002W" );
//                queryResCnt = sMsg.B.length();
//            }
//
//            int i = 0;
//            for( ; i < sMsg.B.getValidCount(); i++ ) {
//                if( i == cMsg.B.length() ) {
//                    break;
//                }
//                EZDMsg.copy( sMsg.B.no( i ), null, cMsg.B.no( i ), null );
//            }
//            cMsg.B.setValidCount( i );
//
//            cMsg.setMessageInfo( ZZVL0000Constant.ZZM8002I );
//            cMsg.xxPageShowFromNum_B.setValue( 1 );
//            cMsg.xxPageShowToNum_B.setValue( cMsg.B.getValidCount() );
//            cMsg.xxPageShowOfNum_B.setValue( queryResCnt );
//
//        } else {
//            cMsg.setMessageInfo( "ZZZM9005W" );
//            cMsg.xxPageShowFromNum_B.clear();
//            cMsg.xxPageShowToNum_B.clear();
//            cMsg.xxPageShowOfNum_B.clear();
//        }
        ScrTblColDefAccessor query = new ScrTblColDefAccessor();
        List<String[]> result = query.getPreferredViewList(cMsg.glblCmpyCd_1.getValue(), cMsg.roleNm_1.getValue(), sMsg.A.length() + 1);
        if (result.size() > 0) {
            int queryResCnt = result.size();
            if (result.size() > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            sMsg.A.setValidCount(queryResCnt);
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                String defNm     = result.get(i)[0];
                String scrAppId  = result.get(i)[1];
                String scrTblNm  = result.get(i)[2];
                String bizAppNm  = result.get(i)[3];
                String usrDefFlg = result.get(i)[4];
                String orgOwner  = result.get(i)[5];
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).scrTblColDefNm_1, defNm);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).scrAppId_1, scrAppId);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).scrTblNm_1, scrTblNm);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).bizAppNm_1,  bizAppNm);
                if (Boolean.parseBoolean(usrDefFlg)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_3, "Y");
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_3, "N");
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).usrId_1, orgOwner);

                if (i < cMsg.A.length()) {
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
            }
            if (i < cMsg.A.length()) {
                cMsg.A.setValidCount(i);
            } else {
                cMsg.A.setValidCount(cMsg.A.length());
            }

            cMsg.setMessageInfo(ZZVL0000Constant.ZZM8002I);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(cMsg.A.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_A, BigDecimal.valueOf(queryResCnt));

        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
        }
        // END   01/27/17 C.Ogaki [Modify] Release 2000 byte length limit

    }

    private void doProcess_ZZVL0000Scrn01_Search(ZZVL0000CMsg cMsg, ZZVL0000SMsg sMsg) {

        S21SsmEZDResult ssmResult = ZZVL0000Query.getInstance().getPreferredViewListForAdd(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.B.length();
            }

            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);

            cMsg.setMessageInfo(ZZVL0000Constant.ZZM8002I);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_B, BigDecimal.valueOf(cMsg.B.getValidCount()));
            ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowOfNum_B, BigDecimal.valueOf(queryResCnt));

        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum_B.clear();
            cMsg.xxPageShowToNum_B.clear();
            cMsg.xxPageShowOfNum_B.clear();
        }

    }

    private void doProcess_ZZVL0000Scrn00_PageNext(ZZVL0000CMsg cMsg, EZDSMsg sMsg) {
        ZZVL0000CMsg bizMsg = (ZZVL0000CMsg) cMsg;
        ZZVL0000SMsg glblMsg = (ZZVL0000SMsg) sMsg;

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_A.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.valueOf(pagenationFrom + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(pagenationFrom + bizMsg.A.getValidCount()));

    }

    private void doProcess_ZZVL0000Scrn00_PagePrev(ZZVL0000CMsg cMsg, EZDSMsg sMsg) {
        ZZVL0000CMsg bizMsg = (ZZVL0000CMsg) cMsg;
        ZZVL0000SMsg glblMsg = (ZZVL0000SMsg) sMsg;

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_A.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.valueOf(pagenationFrom));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(pagenationFrom + bizMsg.A.getValidCount() - 1));

    }

    private void doProcess_ZZVL0000Scrn00_TBLColumnSort(ZZVL0000CMsg cMsg, ZZVL0000SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_A.getValue();
        String sortItemNm = cMsg.xxSortItemNm_A.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_A.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

                int i = 0;
                for ( ; i < sMsg.A.getValidCount(); i++) {
                    if (i == cMsg.A.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                }
                cMsg.A.setValidCount(i);

                ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_A, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_A, BigDecimal.valueOf(cMsg.A.getValidCount()));
        }

    }

    private void doProcess_ZZVL0000Scrn01_PageNext(ZZVL0000CMsg cMsg, EZDSMsg sMsg) {
        ZZVL0000CMsg bizMsg = (ZZVL0000CMsg) cMsg;
        ZZVL0000SMsg glblMsg = (ZZVL0000SMsg) sMsg;

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_B.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.B.length(); i++) {
            if (i < glblMsg.B.getValidCount()) {
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.B.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, BigDecimal.valueOf(pagenationFrom + 1));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_B, BigDecimal.valueOf(pagenationFrom + bizMsg.B.getValidCount()));

    }

    private void doProcess_ZZVL0000Scrn01_PagePrev(ZZVL0000CMsg cMsg, EZDSMsg sMsg) {
        ZZVL0000CMsg bizMsg = (ZZVL0000CMsg) cMsg;
        ZZVL0000SMsg glblMsg = (ZZVL0000SMsg) sMsg;

        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum_B.getValueInt();
        int i              = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.B.length(); i++) {
            if (i < glblMsg.B.getValidCount()) {
                EZDMsg.copy(glblMsg.B.no(i), null, bizMsg.B.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.B.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        pagenationFrom = pagenationFrom + 1;
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, BigDecimal.valueOf(pagenationFrom));
        ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_B, BigDecimal.valueOf(pagenationFrom + bizMsg.B.getValidCount() - 1));

    }

    private void doProcess_ZZVL0000Scrn01_CMN_Return(ZZVL0000CMsg cMsg, ZZVL0000SMsg sMsg) {
        doProcess_ZZVL0000Scrn00_Search(cMsg, sMsg);
        cMsg.setMessageInfo(null);
    }

    private void doProcess_ZZVL0000Scrn01_TBLColumnSort(ZZVL0000CMsg cMsg, ZZVL0000SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_B.getValue();
        String sortItemNm = cMsg.xxSortItemNm_B.getValue();
        String sortOrdBy  = cMsg.xxSortOrdByTxt_B.getValue();

        // Table:B
        if ("B".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.B, sMsg.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.B.getValidCount());

                int i = 0;
                for (; i < sMsg.B.getValidCount(); i++) {
                    if (i == cMsg.B.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
                }
                cMsg.B.setValidCount(i);


                ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowFromNum_B, BigDecimal.ONE);
                ZYPEZDItemValueSetter.setValue(cMsg.xxPageShowToNum_B, BigDecimal.valueOf(cMsg.B.getValidCount()));
        }

    }

}
