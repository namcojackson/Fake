/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0030;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NLGL0030.common.NLGL0030CommonLogic;
import business.blap.NLGL0030.constant.NLGL0030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * ForcedItem Master download
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            M.Shimamura       Create            MW Replace Initial
 *</pre>
 */
public class NLGL0030BL02 extends S21BusinessHandler implements NLGL0030Constant {

    /**
     * The method explanation: this is a method of the execution after
     * the SV enent(setRequestData).
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if (EVENT_NM_NLGL0030_INIT.equals(screenAplID)) {
                doProcess_NLGL0030_INIT((NLGL0030CMsg) cMsg, (NLGL0030SMsg) sMsg);
            } else if (EVENT_NM_NLGL0030_ONCLICK_HISTSEARCH.equals(screenAplID)) {
                doProcess_NLGL0030_SEARCH((NLGL0030CMsg) cMsg, (NLGL0030SMsg) sMsg);
            } else if (EVENT_NM_NLGL0030_ONCLICK_TAB_DNLD.equals(screenAplID)) {
                // there is no process
            } else if (EVENT_NM_NLGL0030_ONCLICK_TAB_HIST.equals(screenAplID)) {
                // there is no process
            } else if (EVENT_NM_NLGL0030_ONCLICK_DNLDSRCH.equals(screenAplID)) {
                doProcess_NLGL0030_DOWNLOAD_SEARCH((NLGL0030CMsg) cMsg, (NLGL0030SMsg) sMsg);
            } else if (EVENT_NM_NLGL0030_CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NLGL0030_DOWNLOAD_SEARCH_FOR_SUBMIT((NLGL0030CMsg) cMsg, (NLGL0030SMsg) sMsg);
            } else if (EVENT_NM_NLGL0030_CMN_RESET.equals(screenAplID)) {
                doProcess_NLGL0030_RESET((NLGL0030CMsg) cMsg, (NLGL0030SMsg) sMsg);
            } else if (EVENT_NM_NLGL0030_PAGENEXT.equals(screenAplID)) {
                doProcess_NLGL0030Scrn00_CMN_Next((NLGL0030CMsg) cMsg, (NLGL0030SMsg) sMsg);
            } else if (EVENT_NM_NLGL0030_PAGEPREV.equals(screenAplID)) {
                doProcess_NLGL0030Scrn00_CMN_Prev((NLGL0030CMsg) cMsg, (NLGL0030SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * The method explanation: The event[INIT] processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0030_INIT(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        // cMsg initialization
        cMsg.whCd_H0.clear();
        cMsg.whCd_P0.clear();
        cMsg.wmsMdseCd_H0.clear();

        cMsg.whCd_H1.clear();
        cMsg.whCd_P1.clear();
        cMsg.mdseCd_H1.clear();

        // sMsg initialization
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        createWHPulldownListHistScrn(cMsg);
        createWHPulldownListDnldScrn(cMsg);
    }

    /**
     * The method explanation: This method sets the wh code(With ALL)
     * to pulldownlist of header.
     * @param cMsg NLBL0030CMsg
     */
    private void createWHPulldownListHistScrn(NLGL0030CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_WH_ALL_VAL, WH_ALL_VALUE);
        ssmParam.put(DB_WH_ALL_SEL_VAL, WH_ALL_SELECTION_VALUE);
        S21SsmEZDResult dsWH = NLGL0030Query.getInstance().getWHPullDownListHistScrn(cMsg, ssmParam);

        if (dsWH.isCodeNormal()) {
            List dsPoTpOutList = (List) dsWH.getResultObject();

            cMsg.whCd_H0.clear();
            cMsg.xxEdtCdNm_H0.clear();

            for (int i = 0; i < dsPoTpOutList.size(); i++) {
                Map pullDownData = (Map) dsPoTpOutList.get(i);

                cMsg.whCd_H0.no(i).setValue((String) pullDownData.get(DB_WH_CD));
                cMsg.xxEdtCdNm_H0.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_CD) //
                        , PULL_DOWN_DELIMITER, (String) pullDownData.get(DB_WMS_DESC_NM)));
            }
        }
    }

    /**
     * The method explanation: This method sets the wh code to
     * pulldownlist of header.
     * @param cMsg NLBL0030CMsg
     */
    private void createWHPulldownListDnldScrn(NLGL0030CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_WH_ALL_VAL, WH_ALL_VALUE);
        S21SsmEZDResult dsWH = NLGL0030Query.getInstance().getWHPullDownListDnldScrn(cMsg, ssmParam);

        if (dsWH.isCodeNormal()) {
            List dsPoTpOutList = (List) dsWH.getResultObject();

            cMsg.whCd_H1.clear();
            cMsg.xxEdtCdNm_H1.clear();

            for (int i = 0; i < dsPoTpOutList.size(); i++) {
                Map pullDownData = (Map) dsPoTpOutList.get(i);

                cMsg.whCd_H1.no(i).setValue((String) pullDownData.get(DB_WH_CD));
                cMsg.xxEdtCdNm_H1.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(DB_WH_CD) //
                        , PULL_DOWN_DELIMITER, (String) pullDownData.get(DB_WMS_DESC_NM)));
            }
        }
    }

    /**
     * The method explanation: The event[SEARCH] processing is
     * executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0030_SEARCH(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        NLGL0030CommonLogic.getMdseHistList(cMsg, sMsg);
    }

    /**
     * The method explanation: The event[SEARCH] processing is
     * executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0030_DOWNLOAD_SEARCH(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        NLGL0030CommonLogic.getMdseDnldList(cMsg, sMsg);
    }

    /**
     * The method explanation: The event[SEARCH] processing is
     * executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0030_DOWNLOAD_SEARCH_FOR_SUBMIT(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        NLGL0030CommonLogic.getMdseDnldListForSubmit(cMsg, sMsg);
    }

    /**
     * The method explanation: The event[RESET] processing is
     * executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLGL0030_RESET(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        // cMsg initialization
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.B);

        // sMsg initialization
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        createWHPulldownListHistScrn(cMsg);
        createWHPulldownListDnldScrn(cMsg);

        ZYPEZDItemValueSetter.setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

    }

    /**
     * PagePrev Event
     * @param cMsg NLGL0030CMsg
     * @param sMsg NLGL0030SMsg
     */
    private void doProcess_NLGL0030Scrn00_CMN_Prev(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        // set values to items of pageNation for previous page
        // pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_H0.setValue(cMsg.xxPageShowFromNum_H0.getValueInt() - cMsg.A.length());
        cMsg.xxPageShowToNum_H0.clear();
        NLGL0030CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * PageNext Event
     * @param cMsg NLGL0030CMsg
     * @param sMsg NLGL0030SMsg
     */
    private void doProcess_NLGL0030Scrn00_CMN_Next(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        // set values to items of pageNation for next page pageNation
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_H0.setValue(cMsg.xxPageShowToNum_H0.getValueInt() + 1);
        cMsg.xxPageShowToNum_H0.clear();
        NLGL0030CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }
}
