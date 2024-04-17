/*
 * <Pre>Copyright(c)2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2590;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2590.common.NMAL2590CommonLogic;
import business.blap.NMAL2590.constant.NMAL2590Constant;
import business.parts.NMXC107001PMsg;

import com.canon.cusa.s21.api.NMX.NMXC107001.NMXC107001;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 *  Geo Code Search Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/07   Hitachi         T.mizuki        Create          CSA-QC#4096
 * 2016/10/07   Fujitsu         C.Yokoi         Update          CSA-QC#4096
 *</pre>
 */
public class NMAL2590BL02 extends S21BusinessHandler implements NMAL2590Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL2590_INIT".equals(screenAplID)) {
                doProcess_NMAL2590_INIT((NMAL2590CMsg) cMsg, (NMAL2590SMsg) sMsg);
            } else if ("NMAL2590Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2590Scrn00_TBLColumnSort((NMAL2590CMsg) cMsg, (NMAL2590SMsg) sMsg);
            } else if ("NMAL2590Scrn00_SelectItem".equals(screenAplID)) {
                doProcess_NMAL2590Scrn00_SelectItem((NMAL2590CMsg) cMsg, (NMAL2590SMsg) sMsg);
            // 2016/10/25 Add CSA-QC#4096 Start
            } else if ("NMAL2590Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2590Scrn00_Search((NMAL2590CMsg) cMsg, (NMAL2590SMsg) sMsg);
            } else if ("NMAL2590Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2590Scrn00_PageNext((NMAL2590CMsg) cMsg, (NMAL2590SMsg) sMsg);
            } else if ("NMAL2590Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2590Scrn00_PagePrev((NMAL2590CMsg) cMsg, (NMAL2590SMsg) sMsg);
            // 2016/10/25 Add CSA-QC#4096 End
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Process of screen event NMAL2590_INIT
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NMAL2590_INIT(NMAL2590CMsg bizMsg, NMAL2590SMsg glblMsg) {
        doProcess_NMAL2590Scrn00_Search(bizMsg, glblMsg);
    }

    /**
     * <pre>
     * Process of screen event NMAL2590Scrn00_TBLColumnSort
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NMAL2590Scrn00_TBLColumnSort(NMAL2590CMsg bizMsg, NMAL2590SMsg glblMsg) {
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy  = bizMsg.xxSortOrdByTxt.getValue();

            // execute column sort function
        S21SortTarget sortTarget = new S21SortTarget(glblMsg.A, glblMsg.A.no(0).getBaseContents());
        S21SortKey sortKey = new S21SortKey();
        sortKey.add(sortItemNm, sortOrdBy);
        S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, glblMsg.A.getValidCount());

        // 2016/10/25 Add CSA-QC#4096 Start
        // SMsg -> CMsg
        int i = 0;
        for (; i < glblMsg.A.getValidCount(); i++) {
            if (i == bizMsg.A.length()) {
                break;
            }
            EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
        }
        bizMsg.A.setValidCount(i);

        // Show 1st page
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowFromNum, BigDecimal.ONE);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxPageShowToNum, new BigDecimal(bizMsg.A.getValidCount()));
        // 2016/10/25 Add CSA-QC#4096 End
    }

    /**
     * <pre>
     * Process of screen event NMAL2590Scrn00_Search
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NMAL2590Scrn00_Search(NMAL2590CMsg bizMsg, NMAL2590SMsg glblMsg) {
        bizMsg.A.clear();
        glblMsg.A.clear();
        bizMsg.A.setValidCount(0);
        glblMsg.A.setValidCount(0);
        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2590Query.getInstance().searchList(bizMsg, glblMsg);

        // 2016/10/25 Add CSA-QC#4096 Start
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if( queryResCnt > glblMsg.A.length() ) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = glblMsg.A.length();
            }
         
            // Move first page date from SMsg to CMsg
            int i = 0;
            for (; i < glblMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);

            // Set page number
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }

        bizMsg.xxPageShowOfNum.setValue(glblMsg.A.getValidCount());
        // 2016/10/25 Add CSA-QC#4096 End
    }

    /**
     * <pre>
     * Process of screen event doProcess_NMAL2590Scrn00_SelectItem
     * </pre>
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void doProcess_NMAL2590Scrn00_SelectItem(NMAL2590CMsg bizMsg, NMAL2590SMsg glblMsg) {
        int idx =  bizMsg.xxCellIdx.getValueInt();
        NMXC107001PMsg pMsg = new NMXC107001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMXC107001.GET_GEO_CODE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm, bizMsg.A.no(idx).cntyNm_A);
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr, bizMsg.A.no(idx).ctyAddr_A);
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, bizMsg.A.no(idx).postCd_A);
        ZYPEZDItemValueSetter.setValue(pMsg.stCd, bizMsg.A.no(idx).stCd_A);

        // Get Geo Code
        NMXC107001 api = new NMXC107001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            String msgId = pMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            bizMsg.setMessageInfo(msgId);
            return;
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.geoCd, pMsg.geoCd);
    }

    /**
     * PageNext Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NMAL2590Scrn00_PageNext(NMAL2590CMsg bizMsg, NMAL2590SMsg glblMsg) {
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL2590CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business bizMsg
     * @param glblMsg Global glblMsg
     */
    private void doProcess_NMAL2590Scrn00_PagePrev(NMAL2590CMsg bizMsg, NMAL2590SMsg glblMsg) {
        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL2590CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }
}
