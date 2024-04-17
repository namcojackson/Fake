/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3110;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/14/2015   CSAI            Y.Imazu         Create          N/A
 * 06/15/2017   CITS            R.Shimamoto     Update          QC#18272
 *</pre>
 */
public class NLBL3110BL02 extends S21BusinessHandler {

    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        cMsg.setCommitSMsg(true);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3110_INIT".equals(screenAplID)) {

                doProcess_NLBL3110_INIT((NLBL3110CMsg) cMsg, (NLBL3110SMsg) sMsg);

            } else if ("NLBL3110Scrn00_PageNext".equals(screenAplID)) {

                doProcess_NLBL3110Scrn00_PageNext((NLBL3110CMsg) cMsg, (NLBL3110SMsg) sMsg);

            } else if ("NLBL3110Scrn00_PagePrev".equals(screenAplID)) {

                doProcess_NLBL3110Scrn00_PagePrev((NLBL3110CMsg) cMsg, (NLBL3110SMsg) sMsg);

            } else if ("NLBL3110Scrn00_TBLColumnSort".equals(screenAplID)) {

                doProcess_NLBL3110Scrn00_TBLColumnSort((NLBL3110CMsg) cMsg, (NLBL3110SMsg) sMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3110_INIT
     * @param cMsg NLBL3110CMsg
     * @param sMsg NLBL3110SMsg
     */
    private void doProcess_NLBL3110_INIT(NLBL3110CMsg cMsg, NLBL3110SMsg sMsg) {

        boolean isCheckInputSearch = true;

        if (!ZYPCommonFunc.hasValue(cMsg.inbdOtbdCd) || !ZYPCommonFunc.hasValue(cMsg.trxHdrNum_H1)) {

            cMsg.setMessageInfo("NLAM1006E", new String[]{"Inbound Outbound Code or Trx Header Number"});
            isCheckInputSearch = false;

        } else if (INBD_OTBD.INBOUND.equals(cMsg.inbdOtbdCd.getValue()) && !ZYPCommonFunc.hasValue(cMsg.trxLineNum_H1)) {

            cMsg.setMessageInfo("NLAM1006E", new String[]{"Trx Line Number"});
            isCheckInputSearch = false;

        } else if (!INBD_OTBD.INBOUND.equals(cMsg.inbdOtbdCd.getValue()) && !INBD_OTBD.OUTBOUND.equals(cMsg.inbdOtbdCd.getValue())) {

            cMsg.setMessageInfo("NLAM1131E", new String[]{"Inbound Outbound Code", cMsg.inbdOtbdCd.getValue()});
            isCheckInputSearch = false;
        }

        if (!isCheckInputSearch) {

            return;
        }

        // Search
        S21SsmEZDResult result = NLBL3110Query.getInstance().search(cMsg, sMsg);

        if (result.isCodeNormal()) {

            int queryResCnt = result.getQueryResultCount();

            if (queryResCnt > sMsg.A.length() - 1) {

                cMsg.setMessageInfo("NZZM0001W");
                queryResCnt = sMsg.A.length() - 1;
            }

            // Copy from SMsg to cMsg
            int i = 0;

            for (; i < queryResCnt; i++) {

                // QC#18272 Add.
            	// UpdTs
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).updTs_A1)) {

                    String updTs = ZYPCommonFunc.rightPad(sMsg.A.no(i).updTs_A1.getValue(), 14, "0");
                    String updTs14 = ZYPCommonFunc.subByteString(updTs, 14);
                    String updTsChg = ZYPDateUtil.formatEzd14ToDisp(updTs14);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxTsDsp19Txt_A1, updTsChg);

                }

                // Schdule Date Time
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyDt_A1) && ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyTm_A1)) {

                    String schdDt = sMsg.A.no(i).schdDelyDt_A1.getValue();
                    String schdTm = sMsg.A.no(i).schdDelyTm_A1.getValue();
                    String schdTs14 = ZYPCommonFunc.rightPad(schdDt + schdTm, 14, "0");
                    String schdTsChg = ZYPDateUtil.formatEzd14ToDisp(schdTs14);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxTsDsp19Txt_AS, schdTsChg);

                } else if (ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyDt_A1) && !ZYPCommonFunc.hasValue(sMsg.A.no(i).schdDelyTm_A1)) {

                	String schdDt = sMsg.A.no(i).schdDelyDt_A1.getValue();
                    String schdTsChg = ZYPDateUtil.formatEzd8ToDisp(schdDt);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxTsDsp19Txt_AS, schdTsChg);

                }

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            // Copy from Detail to Header
            ZYPEZDItemValueSetter.setValue(cMsg.cpoNum_H1, cMsg.A.no(0).cpoNum_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum_H1, cMsg.A.no(0).trxHdrNum_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.trxLineNum_H1, cMsg.A.no(0).trxLineNum_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H1, cMsg.A.no(0).shipToCustCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H1, cMsg.A.no(0).dsAcctNm_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_H1, cMsg.A.no(0).xxAllLineAddr_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_H1, cMsg.A.no(0).ctyAddr_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.stCd_H1, cMsg.A.no(0).stCd_A1);

            // Set page number
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A.setValue(queryResCnt);
            cMsg.setMessageInfo("ZZZM9003I", new String[]{"Search"});

        } else {

            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
            cMsg.xxPageShowFromNum_A.clear();
            cMsg.xxPageShowToNum_A.clear();
            cMsg.xxPageShowOfNum_A.clear();
            cMsg.setMessageInfo("NZZM0000E");
        }
    }

    /**
     * doProcess_NLBL3110Scrn00_PageNext
     * @param cMsg NLBL3110CMsg
     * @param sMsg NLBL3110SMsg
     */
    private void doProcess_NLBL3110Scrn00_PageNext(NLBL3110CMsg cMsg, NLBL3110SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to paging items
        cMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * doProcess_NLBL3110Scrn00_PagePrev
     * @param cMsg NLBL3110CMsg
     * @param sMsg NLBL3110SMsg
     */
    private void doProcess_NLBL3110Scrn00_PagePrev(NLBL3110CMsg cMsg, NLBL3110SMsg sMsg) {

        // copy data from SMsg to CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum_A.getValueInt();
        int i = pagenationFrom;

        for (; i < pagenationFrom + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);

            } else {

                break;
            }
        }

        cMsg.A.setValidCount(i - pagenationFrom);

        // set value to paging items
        cMsg.xxPageShowFromNum_A.setValue(pagenationFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pagenationFrom + cMsg.A.getValidCount());
    }

    /**
     * doProcess_NLBL3110Scrn00_TBLColumnSort
     * @param cMsg NLBL3110CMsg
     * @param sMsg NLBL3110SMsg
     */
    private void doProcess_NLBL3110Scrn00_TBLColumnSort(NLBL3110CMsg cMsg, NLBL3110SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm_A.getValue();
        String sortItemNm = cMsg.xxSortItemNm_A.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt_A.getValue();

        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // copy(SMsg -> CMsg)
            int i = 0;

            for (; i < sMsg.A.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            cMsg.A.setValidCount(i);

            //set page no 
            cMsg.xxPageShowFromNum_A.setValue(1);
            cMsg.xxPageShowToNum_A.setValue(cMsg.A.getValidCount());
        }
    }
}