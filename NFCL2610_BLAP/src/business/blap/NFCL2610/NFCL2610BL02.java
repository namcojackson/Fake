/**
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFCL2610;

import static business.blap.NFCL2610.constant.NFCL2610Constant.*;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL2610.common.NFCL2610CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 *
 * AR Refund by Check Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#7250
 * 2016/04/25   Hitachi         K.Kojima        Update          QC#7532
 * 2016/09/16   Hitachi         K.Kasai         Update          QC#14308
 * 2017/11/09   Fujitsu         M.Ohno          Update          QC#20587
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26989
 * 2022/02/02   Hitachi         A.Kohinata      Update          QC#59612
 * 2022/07/27   Hitachi         A.Kohinata      Update          QC#57418
 *</pre>
 */
public class NFCL2610BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NFCL2610CMsg cMsg = (NFCL2610CMsg) arg0;
        NFCL2610SMsg sMsg = (NFCL2610SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();
            if ("NFCL2610_INIT".equals(screenAplID)) {
                doProcess_NFCL2610_INIT(cMsg, sMsg);
            } else if ("NFCL2610Scrn00_Search".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_Search(cMsg, sMsg);
            } else if ("NFCL2610Scrn00_GetBillToLoc".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_GetBillToLoc(cMsg, sMsg);
            } else if ("NFCL2610Scrn00_GetCustomerNm".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_GetCustomerNm(cMsg, sMsg);
            } else if ("NFCL2610Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_PageJump(cMsg, sMsg);
            } else if ("NFCL2610Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_PageNext(cMsg, sMsg);
            } else if ("NFCL2610Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_PagePrev(cMsg, sMsg);
            // add start 2022/07/27 QC#57418
            } else if ("NFCL2610Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_CMN_Save(cMsg, sMsg);
            // add end 2022/07/27 QC#57418
            } else if ("NFCL2610Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFCL2610Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFCL2610_NMAL6860".equals(screenAplID)) {
                doProcess_NFCL2610_NMAL6860(cMsg, sMsg);
            } else if ("NFCL2610_NMAL6760".equals(screenAplID)) {
                doProcess_NFCL2610_NMAL6760(cMsg, sMsg);
            // START 2018/07/11 [QC#26989, ADD]
            } else if ("NFCL2610Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_TBLColumnSort(cMsg, sMsg);
            // END   2018/07/11 [QC#26989, ADD]
            // add start 2022/02/02 QC#59612
            } else if ("NFCL2610Scrn00_ReEntry".equals(screenAplID)) {
                doProcess_NFCL2610Scrn00_ReEntry(cMsg, sMsg);
            // add start 2022/02/02 QC#59612
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NFCL2610_INIT(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        searchResult(cMsg, sMsg);
    }

    private void doProcess_NFCL2610Scrn00_Search(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.billToCustLocCd)) {
            NFCL2610CommonLogic.searchBillToCustomerName(cMsg);
        }
        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd)) {
            NFCL2610CommonLogic.searchAddressForBillToCustomerAccount(cMsg);
        }
        NFCL2610CommonLogic.getAddress(cMsg);
        searchDetailResult(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.xxTotRfAmt, BigDecimal.ZERO);
        // add start 2022/02/02 QC#59612
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_1B, cMsg.xxChkBox_1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_2B, cMsg.xxChkBox_2);
        // add end 2022/02/02 QC#59612
    }

    private void doProcess_NFCL2610Scrn00_GetBillToLoc(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.billToCustLocCd)) {
            NFCL2610CommonLogic.searchBillToCustomerName(cMsg);
            NFCL2610CommonLogic.getAddress(cMsg);
        } else {
            cMsg.billToCustNm.clear();
            cMsg.xxAllLineAddr.clear();
        }
    }

    private void doProcess_NFCL2610Scrn00_GetCustomerNm(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd)) {
            NFCL2610CommonLogic.searchAddressForBillToCustomerAccount(cMsg);
            NFCL2610CommonLogic.getAddress(cMsg);
        } else {
            cMsg.dsAcctNm.clear();
            cMsg.xxAllLineAddr.clear();
        }
    }

    private void doProcess_NFCL2610Scrn00_PageJump(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        int rowIndex = NFCL2610CommonLogic.convertPageNumToFirstRowIndex(cMsg.A.length(), cMsg.xxPageShowCurNum.getValueInt());
        NFCL2610CommonLogic.pagenation(cMsg, sMsg, rowIndex);
    }

    private void doProcess_NFCL2610Scrn00_PageNext(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFCL2610CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;
        NFCL2610CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }

    private void doProcess_NFCL2610Scrn00_PagePrev(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NFCL2610CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
        NFCL2610CommonLogic.pagenation(cMsg, sMsg, pageFrom);
    }

    private void doProcess_NFCL2610Scrn00_CMN_Submit(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.billToCustLocCd) && !ZYPCommonFunc.hasValue(cMsg.billToCustNm)) {
            NFCL2610CommonLogic.searchBillToCustomerName(cMsg);
        }
        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd) && !ZYPCommonFunc.hasValue(cMsg.dsAcctNm)) {
            NFCL2610CommonLogic.searchAddressForBillToCustomerAccount(cMsg);
        }
        NFCL2610CommonLogic.getAddress(cMsg);
        searchDetailResult(cMsg, sMsg);
    }

    private void doProcess_NFCL2610Scrn00_CMN_Clear(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        cMsg.clear();
        searchInit(cMsg, sMsg);
    }

    private void doProcess_NFCL2610_NMAL6860(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.arRfRqstPk)) {
            NFCL2610CommonLogic.searchArTrxBalInfo(cMsg, sMsg);
        } else {
            NFCL2610CommonLogic.searchArRfRqstInfo(cMsg, sMsg);
        }
    }

    private void doProcess_NFCL2610_NMAL6760(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.billToCustLocCd)) {
            NFCL2610CommonLogic.searchBillToCustomerName(cMsg);
        }

        // 2017/11/09 QC#20587 add start
        if ("OpenWin_BillToLoc".equals(cMsg.xxScrEventNm.getValue())) {
            // 2017/11/09 QC#20587 add end
            StringBuffer sb = new StringBuffer();
            sb.append(cMsg.xxPopPrm_P4.getValue());
            if (ZYPCommonFunc.hasValue(cMsg.xxPopPrm_PH)) {
                sb.append(SEPARATOR);
                sb.append(cMsg.xxPopPrm_PH.getValue());
            }
            if (ZYPCommonFunc.hasValue(cMsg.xxPopPrm_P5)) {
                sb.append(SEPARATOR);
                sb.append(cMsg.xxPopPrm_P5.getValue());
            }
            if (ZYPCommonFunc.hasValue(cMsg.xxPopPrm_P6)) {
                sb.append(SEPARATOR);
                sb.append(cMsg.xxPopPrm_P6.getValue());
            }
            if (ZYPCommonFunc.hasValue(cMsg.xxPopPrm_P7)) {
                sb.append(SEPARATOR);
                sb.append(cMsg.xxPopPrm_P7.getValue());
            }
            ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr, sb.toString());
        }
    }

    private void searchResult(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.arRfRqstPk)) {
            searchInit(cMsg, sMsg);
        } else {
            searchInit(cMsg, sMsg);
            if (ZYPCommonFunc.hasValue(cMsg.billToCustLocCd)) {
                NFCL2610CommonLogic.searchBillToCustomerName(cMsg);
            }
            if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd)) {
                NFCL2610CommonLogic.searchAddressForBillToCustomerAccount(cMsg);
            }
            NFCL2610CommonLogic.getAddress(cMsg);
            searchDetailResult(cMsg, sMsg);
        }
    }

    private void searchInit(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        clearSearchResultTable(cMsg, sMsg);
        cMsg.glblCmpyCd.setValue(getGlobalCompanyCode());

        NFCL2610CommonLogic.searchInit(cMsg, sMsg);
    }

    private void searchDetailResult(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.arRfRqstPk)) {
            NFCL2610CommonLogic.searchArTrxBalInfo(cMsg, sMsg);
        } else {
            NFCL2610CommonLogic.searchArRfRqstInfo(cMsg, sMsg);
        }
        NFCL2610CommonLogic.pagenation(cMsg, sMsg, 0);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    /**
     * clear Search Result
     * @param bizMsg NFCL2610CMsg
     * @param globalMsg NFCL2610SMsg
     */
    private void clearSearchResultTable(NFCL2610CMsg bizMsg, NFCL2610SMsg globalMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        globalMsg.A.clear();
        globalMsg.A.setValidCount(0);

        bizMsg.B.clear();
        bizMsg.B.setValidCount(0);
        globalMsg.B.clear();
        globalMsg.B.setValidCount(0);
    }

    // START 2018/07/11 [QC#26989, ADD]
    private void doProcess_NFCL2610Scrn00_TBLColumnSort(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        if ("A".equals(sortTblNm)) {
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();

            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            EZDMsg.copy(sMsg.A, null, cMsg.A, null);
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        }
    }
    // END   2018/07/11 [QC#26989, ADD]

    // add start 2022/02/02 QC#59612
    private void doProcess_NFCL2610Scrn00_ReEntry(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        String arRfTpCdH = cMsg.arRfTpCd_H.getValue();
        String xxChkBox1 = cMsg.xxChkBox_1B.getValue();
        String xxChkBox2 = cMsg.xxChkBox_2B.getValue();
        String billToCustCd1 = cMsg.billToCustCd_1.getValue();
        String billToCustAcctCd = cMsg.billToCustAcctCd.getValue();
        String dsAcctNm = cMsg.dsAcctNm.getValue();
        String xxChkBox5 = cMsg.xxChkBox_5.getValue();
        String billToCustCd2 = cMsg.billToCustCd_2.getValue();
        String billToCustLocCd = cMsg.billToCustLocCd.getValue();
        String billToCustNm = cMsg.billToCustNm.getValue();
        String xxAllLineAddr = cMsg.xxAllLineAddr.getValue();
        String xxQueryFltrTxt = cMsg.xxQueryFltrTxt.getValue();

        cMsg.clear();
        searchInit(cMsg, sMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.arRfTpCd_H, arRfTpCdH);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_1, xxChkBox1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_1B, xxChkBox1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_2, xxChkBox2);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_2B, xxChkBox2);
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustCd_1, billToCustCd1);
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustAcctCd, billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, dsAcctNm);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_5, xxChkBox5);
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustCd_2, billToCustCd2);
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustLocCd, billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustNm, billToCustNm);
        ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr, xxAllLineAddr);
        ZYPEZDItemValueSetter.setValue(cMsg.xxQueryFltrTxt, xxQueryFltrTxt);

        searchDetailResult(cMsg, sMsg);
        ZYPEZDItemValueSetter.setValue(cMsg.xxTotRfAmt, BigDecimal.ZERO);
    }
    // add end 2022/02/02 QC#59612

    // add start 2022/07/27 QC#57418
    private void doProcess_NFCL2610Scrn00_CMN_Save(NFCL2610CMsg cMsg, NFCL2610SMsg sMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.billToCustLocCd) && !ZYPCommonFunc.hasValue(cMsg.billToCustNm)) {
            NFCL2610CommonLogic.searchBillToCustomerName(cMsg);
        }
        if (ZYPCommonFunc.hasValue(cMsg.billToCustAcctCd) && !ZYPCommonFunc.hasValue(cMsg.dsAcctNm)) {
            NFCL2610CommonLogic.searchAddressForBillToCustomerAccount(cMsg);
        }
        NFCL2610CommonLogic.getAddress(cMsg);
        searchDetailResult(cMsg, sMsg);
    }
    // add end 2022/07/27 QC#57418
}
