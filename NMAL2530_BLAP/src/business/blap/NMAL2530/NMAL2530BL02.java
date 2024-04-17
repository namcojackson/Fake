/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2530;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItemArray;
import parts.common.EZDSMsg;

import business.blap.NMAL2530.common.NMAL2530CommonLogic;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSR_RG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import static business.blap.NMAL2530.constant.NMAL2530Constant.NZZM0001W;
import static business.blap.NMAL2530.constant.NMAL2530Constant.NZZM0000E;



/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2016/02/29   Fujitsu         M.Suzuki        Update          QC#4540
 * 2016/02/29   Fujitsu         M.Suzuki        Update          QC#4544
 *</pre>
 */
public class NMAL2530BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL2530_INIT".equals(screenAplID)) {
                doProcess_NMAL2530_INIT((NMAL2530CMsg) cMsg, (NMAL2530SMsg) sMsg);
            } else if ("NMAL2530Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2530Scrn00_Search((NMAL2530CMsg) cMsg, (NMAL2530SMsg) sMsg);
            } else if ("NMAL2530Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2530Scrn00_CMN_Clear((NMAL2530CMsg) cMsg, (NMAL2530SMsg) sMsg);
            // 2016/02/29 S21_NA#4544 Mod Start --------------
            } else if ("NMAL2530Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2530Scrn00_PageNext((NMAL2530CMsg) cMsg, (NMAL2530SMsg) sMsg);
            } else if ("NMAL2530Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2530Scrn00_PagePrev((NMAL2530CMsg) cMsg, (NMAL2530SMsg) sMsg);
            // 2016/02/29 S21_NA#4544 Mod End --------------
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // 2016/02/29 S21_NA#4544 Add Start --------------
    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2530Scrn00_PageNext(NMAL2530CMsg bizMsg, NMAL2530SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL2530CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2530Scrn00_PagePrev(NMAL2530CMsg bizMsg, NMAL2530SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL2530CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }
    // 2016/02/29 S21_NA#4544 Add Start --------------

    private void doProcess_NMAL2530Scrn00_Search(NMAL2530CMsg bizMsg, NMAL2530SMsg sMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        S21SsmEZDResult ssmResult = NMAL2530Query.getInstance().getOrganization(bizMsg, sMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            bizMsg.setCommitSMsg(true);
            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                sMsg.A.setValidCount(sMsg.A.length());
            } else {
                sMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }
            bizMsg.xxPageShowFromNum.setValue(1);
            NMAL2530CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sMsg.A);
        }
    }

    /**
     * @param bizMsg NMAL2530CMsg
     * @param sMsg NMAL2530SMsg
     */
    private void doProcess_NMAL2530_INIT(NMAL2530CMsg bizMsg, NMAL2530SMsg sMsg) {

        createPullDown(bizMsg);

        // When the parameter is set.
        if (ZYPCommonFunc.hasValue(bizMsg.orgStruTpCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.orgNm_H1)
                || ZYPCommonFunc.hasValue(bizMsg.orgNm_H2)
                || ZYPCommonFunc.hasValue(bizMsg.orgTierCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.psnCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.orgTierCd_H1)
                //2016/02/29 S21_NA#4540 Mod Start ------------
                || ZYPCommonFunc.hasValue(bizMsg.csrRgTpCd_H1)) {
                //2016/02/29 S21_NA#4540 Mod End --------------
            doProcess_NMAL2530Scrn00_Search(bizMsg, sMsg);
        }

    }

    private void doProcess_NMAL2530Scrn00_CMN_Clear(NMAL2530CMsg cMsg, NMAL2530SMsg sMsg) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.clear();
        doProcess_NMAL2530_INIT(cMsg, sMsg);
    }

    private void createPullDown(NMAL2530CMsg cMsg) {

        S21SsmEZDResult ssmResult = NMAL2530Query.getInstance().getBusinessArea(cMsg);

        if (ssmResult.isCodeNormal()) {

            List<Map> whList = (List<Map>) ssmResult.getResultObject();

            createPulldownList(cMsg.orgStruTpCd_H2, cMsg.orgStruTpNm_H1, whList, new String[]{"BIZ_AREA_ORG_CD", "BIZ_AREA_ORG_NM"});
        }
        
        // ZYPCodeDataUtil.createPulldownList(ORG_TIER.class, cMsg.orgTierCd_H2, cMsg.orgTierNm_H1);

        ssmResult = NMAL2530Query.getInstance().getOrgTierNot0(cMsg);
        if (ssmResult.isCodeNormal()) {

            List<Map> whList = (List<Map>) ssmResult.getResultObject();

            createPulldownList(cMsg.orgTierCd_H2, cMsg.orgTierNm_H1, whList, new String[]{"ORG_TIER_CD", "ORG_TIER_DESC_TXT"});
        }

        cMsg.csrRgTpCd_H2.clear();
        cMsg.csrRgTpCd_H1.clear();
        //2016/02/29 S21_NA#4540 Mod Start ------------
        ZYPCodeDataUtil.createPulldownList(CSR_RG_TP.class, cMsg.csrRgTpCd_H2, cMsg.csrRgTpDescTxt_H1);
        //2016/02/29 S21_NA#4540 Mod End --------------
    }
    /**
     * 
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public void createPulldownList(EZDCStringItemArray cd,
            EZDCStringItemArray value, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < cd.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cd.no(i))) {

                for (int j = 0; j < pullDownList.size(); j++) {

                    Map pullDownData = pullDownList.get(j);
                    cd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
                    value.no(i).setValue((String) pullDownData.get(dbColumn[1]));

                    i = i + 1;
                    if (i >= cd.length()) {
                        break;
                    }
                }

                break;
            }
        }
    }
}
