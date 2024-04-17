/**
 * <Pre>Copyright (c) 2011 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NPAL0190;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NPAL0190.common.NPAL0190CommonLogic;
import business.blap.NPAL0190.constant.NPAL0190Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * PO History Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/29/2012   SRA             N.Otsuji        Create          N/A
 * 03/14/2013   Hitachi         T.Arakawa       Create          K25(QC822)
 * 01/25/2016   CITS            K.Ogino         Update          CSA
 * 03/08/2016   CITS            K.Ogino         Update          QC#4976
 *</pre>
 */
public class NPAL0190BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {

        super.preDoProcess(ezdCMsg, ezdSMsg);

        NPAL0190CMsg cMsg = (NPAL0190CMsg) ezdCMsg;
        NPAL0190SMsg sMsg = (NPAL0190SMsg) ezdSMsg;

        try {
            String screenAplId = cMsg.getScreenAplID();
            if (NPAL0190Constant.APL_ID_INIT.equals(screenAplId)) {
                doProcess_NPAL0190_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData((NPAL0190CMsg) cMsg, (NPAL0190SMsg) sMsg);
            } else if (NPAL0190Constant.APL_ID_PAGE_NEXT.equals(screenAplId)) {
                doProcess_NPAL0190Scrn00_PageNext(cMsg, sMsg);
            } else if (NPAL0190Constant.APL_ID_PAGE_PREV.equals(screenAplId)) {
                doProcess_NPAL0190Scrn00_PagePrev(cMsg, sMsg);
            } else if (NPAL0190Constant.APL_ID_TBL_COLUMN_SORT.equals(screenAplId)) {
                doProcess_NPAL0190Scrn00_TBLColumnSort(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    private void doProcess_NPAL0190_INIT(NPAL0190CMsg cMsg, NPAL0190SMsg sMsg) {
        String poOrdNum = cMsg.poOrdNum_H.getValue();
        String poDispLineNum = cMsg.dispPoDtlLineNum_H.getValue();
        String xxComnColOrdTxt = cMsg.xxComnColOrdTxt.getValue();
        cMsg.clear();
        cMsg.xxComnColOrdTxt.setValue(xxComnColOrdTxt);
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum_H, poOrdNum);
        ZYPEZDItemValueSetter.setValue(cMsg.dispPoDtlLineNum_H, poDispLineNum);
        boolean isNormalEnd = false;
        isNormalEnd = NPAL0190Query.getInstance().getBizProcLog(poOrdNum, poDispLineNum, cMsg, sMsg);
        // nothing data.
        if (!isNormalEnd) {
            return;
        }
        NPAL0190CommonLogic.paginate(cMsg, sMsg, 0);
    }

    private void doProcess_NPAL0190Scrn00_PageNext(NPAL0190CMsg cMsg, NPAL0190SMsg sMsg) {

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1;

        NPAL0190CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private void doProcess_NPAL0190Scrn00_PagePrev(NPAL0190CMsg cMsg, NPAL0190SMsg sMsg) {

        int startIndex = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;

        NPAL0190CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

    private void doProcess_NPAL0190Scrn00_TBLColumnSort(NPAL0190CMsg cMsg, NPAL0190SMsg sMsg) {

        NPAL0190CommonLogic.sort(sMsg.A, sMsg.A.no(0).getBaseContents(), cMsg.xxSortItemNm.getValue(), cMsg.xxSortOrdByTxt.getValue());

        int startIndex = 0;

        NPAL0190CommonLogic.paginate(cMsg, sMsg, startIndex);
    }

}
