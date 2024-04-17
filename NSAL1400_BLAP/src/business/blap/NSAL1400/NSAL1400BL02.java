/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1400;

import static business.blap.NSAL1400.common.NSAL1400CommonLogic.*;
import static business.blap.NSAL1400.constant.NSAL1400Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            NSAL1400CMsg bizMsg = (NSAL1400CMsg) cMsg;
            NSAL1400SMsg glblMsg = (NSAL1400SMsg) sMsg;
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1400_INIT".equals(screenAplID)) {
                doProcess_NSAL1400_INIT(bizMsg, glblMsg);
            } else if ("NSAL1400Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL1400Scrn00_Search(bizMsg, glblMsg);
            } else if ("NSAL1400Scrn00_Add".equals(screenAplID)) {
                doProcess_NSAL1400Scrn00_Add(bizMsg, glblMsg);
            } else if ("NSAL1400Scrn00_Delete".equals(screenAplID)) {
                doProcess_NSAL1400Scrn00_Delete(bizMsg, glblMsg);
            } else if ("NSAL1400Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL1400Scrn00_PagePrev(bizMsg, glblMsg);
            } else if ("NSAL1400Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL1400Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NSAL1400Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1400_INIT(bizMsg, glblMsg);
            } else if ("NSAL1400Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL1400Scrn00_CMN_Submit(bizMsg, glblMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1400_INIT(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        initMsg(bizMsg, glblMsg);

        setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate());
        setValue(bizMsg.xxMaxSrchCnt, new BigDecimal(glblMsg.A.length()));
        setValue(bizMsg.xxChkBox_H, FLG_ON_Y);

        // pulldown
        setSvcLineBizTpPulldown(bizMsg);
    }

    private void doProcess_NSAL1400Scrn00_Search(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        // init
        ZYPTableUtil.clear(bizMsg.A);
        // sMsg
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        ZYPTableUtil.clear(glblMsg.B);

        // Search Item Copy CMsg -> SMsg
        EZDMsg.copy(bizMsg, "H", glblMsg, "PV");

        // Search
        S21SsmEZDResult ssmResult = NSAL1400Query.getInstance().search(bizMsg, glblMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 2000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                glblMsg.A.setValidCount(glblMsg.A.length());
            } else {
                bizMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            bizMsg.setMessageInfo(NZZM0000E);
            return;
        }

        pagenation(bizMsg, glblMsg, 0);
        bizMsg.xxPageShowOfNum_A.setValue(glblMsg.A.getValidCount());
    }

    private void doProcess_NSAL1400Scrn00_Add(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        if (bizMsg.A.getValidCount() != 0) {
            int pageFromNum = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
            setPagenation(bizMsg, glblMsg, pageFromNum);
        }

        if (glblMsg.A.length() == glblMsg.A.getValidCount()) {
            bizMsg.setMessageInfo(NSAM0320E, new String[] {"SETUP", String.valueOf(glblMsg.A.length()) });
            return;
        }

        glblMsg.A.no(glblMsg.A.getValidCount()).xxChkBox_A1.setValue(FLG_OFF_N);
        glblMsg.A.no(glblMsg.A.getValidCount()).xxChkBox_A2.setValue(FLG_ON_Y);
        glblMsg.A.setValidCount(glblMsg.A.getValidCount() + 1);
        int firstNumOnPage = (glblMsg.A.getValidCount() / bizMsg.A.length()) * bizMsg.A.length();
        pagenation(bizMsg, glblMsg, firstNumOnPage);
    }

    private void doProcess_NSAL1400Scrn00_Delete(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        int pageFromNum = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        setPagenation(bizMsg, glblMsg, pageFromNum);

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_A1", FLG_ON_Y);

        if (checkList.size() == 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                bizMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NSAM0015E);
            }
            return;
        }

        for (int checkIndex : checkList) {
            NSAL1400_ASMsg glblAMsg = glblMsg.A.no(checkIndex);

            if (ZYPCommonFunc.hasValue(glblAMsg.acctContrAdminRelnPk_A)) {
                NSAL1400_BSMsg delgGlblMsg = glblMsg.B.no(glblMsg.B.getValidCount());
                EZDMsg.copy(glblAMsg, "A", delgGlblMsg, "B");
                glblMsg.B.setValidCount(glblMsg.B.getValidCount() + 1);
            }
        }

        ZYPTableUtil.deleteRows(glblMsg.A, checkList);

        if (glblMsg.A.getValidCount() == 0) {
            ZYPTableUtil.clear(bizMsg.A);

            bizMsg.xxPageShowFromNum_A.setValue(0);
            bizMsg.xxPageShowToNum_A.setValue(0);
            bizMsg.xxPageShowOfNum_A.setValue(0);
            return;
        }

        if (pageFromNum >= glblMsg.A.getValidCount()) {
            pageFromNum = pageFromNum - bizMsg.A.length();
        }
        pagenation(bizMsg, glblMsg, pageFromNum);
    }

    private void doProcess_NSAL1400Scrn00_PagePrev(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        int pageFromNum = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        setPagenation(bizMsg, glblMsg, pageFromNum);

        pageFromNum = pageFromNum - bizMsg.A.length();
        pagenation(bizMsg, glblMsg, pageFromNum);
    }

    private void doProcess_NSAL1400Scrn00_PageNext(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        int pageFromNum = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
        setPagenation(bizMsg, glblMsg, pageFromNum);

        pageFromNum = pageFromNum + bizMsg.A.length();
        pagenation(bizMsg, glblMsg, pageFromNum);
    }

    private void doProcess_NSAL1400Scrn00_CMN_Submit(NSAL1400CMsg bizMsg, NSAL1400SMsg glblMsg) {
        // Search Item Copy SMsg -> CMsg
        EZDMsg.copy(glblMsg, "PV", bizMsg, "H");
        doProcess_NSAL1400Scrn00_Search(bizMsg, glblMsg);
    }
}
