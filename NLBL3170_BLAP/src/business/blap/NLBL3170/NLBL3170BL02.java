/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3170;

import static business.blap.NLBL3170.constant.NLBL3170Constant.REPLACE_CHAR;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL3170.constant.NLBL3170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Tracking Number Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            T.Hakodate      Create          QC#21913
 * 2018/08/17   CITS            K.Ogino         Update          QC#27601
 * 2019/07/30   CITS            R.Shimamoto     Update          QC#52078
 * 2019/08/06   CITS            R.Shimamoto     Update          QC#52078-2
 * 2020/02/18   Fujitsu         R.Nakamura      Update          QC#55897
 * 2020/06/18   CITS            M.Furugoori     Update          QC#56914
 * 2020/11/18   CITS            M.Furugoori     Update          QC#57659
 *</pre>
 */
public class NLBL3170BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3170_INIT".equals(screenAplID)) {

                doProcess_NLBL3170_INIT((NLBL3170CMsg) cMsg, (NLBL3170SMsg) sMsg);

            } else if ("NLBL3170Scrn00_AddLine".equals(screenAplID)) {

                doProcess_NLBL3170_AddLine((NLBL3170CMsg) cMsg, (NLBL3170SMsg) sMsg);

            } else if ("NLBL3170Scrn00_CMN_Clear".equals(screenAplID)) {

                doProcess_NLBL3170_CMN_Clear((NLBL3170CMsg) cMsg, (NLBL3170SMsg) sMsg);

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {

            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * doProcess_NLBL3170_INIT
     * @param cMsg NLBL3170CMsg
     * @param sMsg NLBL3170SMsg
     */
    private void doProcess_NLBL3170_INIT(NLBL3170CMsg cMsg, NLBL3170SMsg sMsg) {

        search(cMsg, sMsg);

    }

    /**
     * doProcess_NLBL3170_AddLine
     * @param cMsg NLBL3170CMsg
     * @param sMsg NLBL3170SMsg
     */
    private void doProcess_NLBL3170_AddLine(NLBL3170CMsg cMsg, NLBL3170SMsg sMsg) {

        // Copy Message
        copyFromCmsgOntoSmsg(cMsg, sMsg);

    }

    /**
     * doProcess_NLBL3170_CMN_Clear
     * @param cMsg NLBL3170CMsg
     * @param sMsg NLBL3170SMsg
     */
    private void doProcess_NLBL3170_CMN_Clear(NLBL3170CMsg cMsg, NLBL3170SMsg sMsg) {

        search(cMsg, sMsg);

    }

    /**
     * search
     * @param cMsg NLBL3170CMsg
     * @param sMsg NLBL3170SMsg
     */
    private void search(NLBL3170CMsg cMsg, NLBL3170SMsg sMsg) {

        S21SsmEZDResult result = null;
        String shipFromSoNum = null;
        S21SsmEZDResult ssmResult = null;

        // QC:52078 Start
        if (!"1".equals(cMsg.xxModeCd.getValue())) {
        	ssmResult = NLBL3170Query.getInstance().searchShipFromSoNum(cMsg, sMsg);
        	if (ssmResult.isCodeNormal()) {
        		shipFromSoNum = (String) ssmResult.getResultObject();
        	}
        }

        // QC#27601
//        if (!ZYPCommonFunc.hasValue(cMsg.xxModeCd) || "1".equals(cMsg.xxModeCd.getValue())) {
//            result = NLBL3170Query.getInstance().searchProNumList(cMsg, sMsg);
//        } else if ("2".equals(cMsg.xxModeCd.getValue())) {
//            result = NLBL3170Query.getInstance().searchAsnProNumList(cMsg, sMsg);
//        } else {
//            result = NLBL3170Query.getInstance().searchProNumList(cMsg, sMsg);
//        }
        if ("1".equals(cMsg.xxModeCd.getValue())) {

            result = NLBL3170Query.getInstance().searchProNumList(cMsg, sMsg);

        } else if ("2".equals(cMsg.xxModeCd.getValue())) {

            result = NLBL3170Query.getInstance().searchAsnProNumList(cMsg, sMsg, shipFromSoNum);

        } else {

            result = NLBL3170Query.getInstance().searchAsnProNumList(cMsg, sMsg, shipFromSoNum);
            if (!result.isCodeNormal()) {
            	result = NLBL3170Query.getInstance().searchProNumList(cMsg, sMsg);
            }

        }
        // QC:52078 End

        if (result.isCodeNormal()) {

            int queryResCnt = result.getQueryResultCount();

            if (queryResCnt > sMsg.A.length() - 1) {

                cMsg.setMessageInfo(NLBL3170Constant.NZZM0001W);
                sMsg.A.setValidCount(sMsg.A.length() - 1);
                queryResCnt = sMsg.A.length() - 1;
            }

            // Copy from SMsg to cMsg

            int index = sMsg.A.getValidCount();

            for (int j = 0; j < cMsg.T.getValidCount(); j++) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index + j).mstrProNum_A1, cMsg.T.no(j).mstrProNum_T1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index + j).proNum_A1, cMsg.T.no(j).proNum_T1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index + j).trxLineNum_A1, cMsg.T.no(j).trxLineNum_T1.getValue());
                sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);
            }

            int i = 0;

            for (; i < queryResCnt + cMsg.T.getValidCount(); i++) {

                if (i == cMsg.A.length()) {

                    break;
                }

                // START 2020/06/18 [QC#56914,ADD]
                if ((ZYPCommonFunc.hasValue(sMsg.A.no(i).proNum_A1) || ZYPCommonFunc.hasValue(sMsg.A.no(i).mstrProNum_A1)) && ZYPCommonFunc.hasValue(sMsg.A.no(i).carrTrkUrl_A1)) {
                    editCarrTrkUrl(sMsg, i);
                }
                // END 2020/06/18 [QC#56914,ADD]

                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }

            ZYPEZDItemValueSetter.setValue(cMsg.wmsCarrCd, sMsg.A.no(0).wmsCarrCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.xxMsgTxt, sMsg.A.no(0).xxMsgTxt_A1.getValue());
            // Mod Start 2020/02/18 QC#55897
//            ZYPEZDItemValueSetter.setValue(cMsg.soNum, sMsg.A.no(0).trxHdrNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyOrdNum_SN, sMsg.A.no(0).xxDplyOrdNum_A1.getValue());
            // Mod End 2020/02/18 QC#55897

            cMsg.A.setValidCount(i);
            cMsg.setMessageInfo(NLBL3170Constant.ZZZM9003I, new String[] {"Search" });

        } else if (cMsg.T.getValidCount() > 0) {

            int index = sMsg.A.getValidCount();

            for (int j = 0; j < cMsg.T.getValidCount(); j++) {

                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index + j).mstrProNum_A1, cMsg.T.no(j).mstrProNum_T1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index + j).proNum_A1, cMsg.T.no(j).proNum_T1.getValue());
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index + j).trxLineNum_A1, cMsg.T.no(j).trxLineNum_T1.getValue());
                sMsg.A.setValidCount(sMsg.A.getValidCount() + 1);
            }

            EZDMsg.copy(sMsg.A, null, cMsg.A, null);
            // Mod Start 2020/02/18 QC#55897
//            ZYPEZDItemValueSetter.setValue(cMsg.soNum, cMsg.trxHdrNum);
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyOrdNum_SN, cMsg.xxDplyOrdNum_TH);
            // Mod End 2020/02/18 QC#55897

        } else {

            // Mod Start 2020/02/18 QC#55897
//            ZYPEZDItemValueSetter.setValue(cMsg.soNum, cMsg.trxHdrNum);
            ZYPEZDItemValueSetter.setValue(cMsg.xxDplyOrdNum_SN, cMsg.xxDplyOrdNum_TH);
            // Mod End 2020/02/18 QC#55897
            ZYPTableUtil.clear(cMsg.A);
            ZYPTableUtil.clear(sMsg.A);
        }
    }

    private void copyFromCmsgOntoSmsg(NLBL3170CMsg cMsg, NLBL3170SMsg sMsg) {

        // detail
        // T:Additional Tracking Number
        EZDMsg.copy(cMsg.T, null, sMsg.T, null);

        // add new line to screen
        cMsg.A.setValidCount(cMsg.A.getValidCount() + 1);

        int index = cMsg.A.getValidCount();

        if (index > 0) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(index - 1).mstrProNum_A1, cMsg.A.no(0).mstrProNum_A1.getValue());
        }

    }

    // START 2020/06/18 [QC#56914, ADD]
    /**
     * <pre>
     * editCarrTrkUrl      
     * ADD QC:11819
     * </pre>
     * @param sMsg NLBL3170SMsg
     */
    private static void editCarrTrkUrl(NLBL3170SMsg sMsg, int i) {
        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).mstrProNum_A1)) {
            String url = sMsg.A.no(i).carrTrkUrl_A1.getValue();
            Pattern pattern = Pattern.compile(REPLACE_CHAR);
            Matcher matcher = pattern.matcher(url);
            String str = matcher.replaceAll(sMsg.A.no(i).mstrProNum_A1.getValue());
            // START 2020/11/18 [QC#57659, MOD]
//            sMsg.A.no(i).carrTrkUrl_A1.setValue(str);
            sMsg.A.no(i).carrEncTrkUrl_A1.setValue(str);
            // END 2020/11/18 [QC#57659, MOD]
        }
        if (ZYPCommonFunc.hasValue(sMsg.A.no(i).proNum_A1)) {
            String url2 = sMsg.A.no(i).carrTrkUrl_A2.getValue();
            Pattern pattern = Pattern.compile(REPLACE_CHAR);
            Matcher matcher = pattern.matcher(url2);
            String str2 = matcher.replaceAll(sMsg.A.no(i).proNum_A1.getValue());
            // START 2020/11/18 [QC#57659, MOD]
//            sMsg.A.no(i).carrTrkUrl_A2.setValue(str2);
            sMsg.A.no(i).carrEncTrkUrl_A2.setValue(str2);
            // END 2020/11/18 [QC#57659, MOD]
        }
    }
    // END 2020/06/18 [QC#56914, ADD]
}
