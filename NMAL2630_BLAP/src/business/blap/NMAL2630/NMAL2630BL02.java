/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2630;

import static business.blap.NMAL2630.constant.NMAL2630Constant.NZZM0000E;
import static business.blap.NMAL2630.constant.NMAL2630Constant.NZZM0001W;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsgArray;
import parts.common.EZDSMsg;
import parts.common.EZDTStringItem;
import business.blap.NMAL2630.common.NMAL2630CommonLogic;
import business.blap.NMAL2630.constant.NMAL2630Constant;
import business.db.ORG_TIERTMsg;
import business.db.ORG_TIERTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_OPRD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/07/20   Fujitsu         N.Sugiura       Create          N/A
 * 2016/03/01   Fujitsu         M.Suzuki        Update          S21_NA#4547
 * 2016/03/02   Fujitsu         C.Tanaka        Update          QC#4784
 * 2016/05/31   SRAA            Y.Chen          Update          QC#9182
 * 2016/06/23   Hitach          A.Kohinata      Update          CSA-QC#10280
 *</pre>
 */
public class NMAL2630BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL2630_INIT".equals(screenAplID)) {
                doProcess_NMAL2630_INIT((NMAL2630CMsg) cMsg, (NMAL2630SMsg) sMsg);
            } else if ("NMAL2630Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2630Scrn00_Search((NMAL2630CMsg) cMsg, (NMAL2630SMsg) sMsg);
            } else if ("NMAL2630Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2630Scrn00_CMN_Clear((NMAL2630CMsg) cMsg, (NMAL2630SMsg) sMsg);
            } else if ("NMAL2630Scrn00_InsertRow".equals(screenAplID)) {
                doProcess_NMAL2630Scrn00_InsertRow((NMAL2630CMsg) cMsg, (NMAL2630SMsg) sMsg);
            // 2016/03/01 S21_NA#4547 Add Start --------------
            } else if ("NMAL2630Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL2630Scrn00_PageNext((NMAL2630CMsg) cMsg, (NMAL2630SMsg) sMsg);
            } else if ("NMAL2630Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL2630Scrn00_PagePrev((NMAL2630CMsg) cMsg, (NMAL2630SMsg) sMsg);
            // 2016/03/01 S21_NA#4547 Add End --------------
            }   else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    // 2016/03/01 S21_NA#4547 Add Start --------------
    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2630Scrn00_PageNext(NMAL2630CMsg bizMsg, NMAL2630SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NMAL2630CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL2630Scrn00_PagePrev(NMAL2630CMsg bizMsg, NMAL2630SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NMAL2630CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);

    }
    // 2016/03/01 S21_NA#4547 Add Start --------------

    private void doProcess_NMAL2630Scrn00_Search(NMAL2630CMsg bizMsg, NMAL2630SMsg sMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL2630Query.getInstance().getTerritory(bizMsg, sMsg);
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
            NMAL2630CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, sMsg.A);
        }
    }

    /**
     * @param bizMsg NMAL2630CMsg
     * @param sMsg NMAL2630SMsg
     */
    private void doProcess_NMAL2630_INIT(NMAL2630CMsg bizMsg, NMAL2630SMsg sMsg) {

        createPullDown(bizMsg);

        // When the parameter is set.
        if (ZYPCommonFunc.hasValue(bizMsg.orgStruTpCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.orgNm_H1)
                // QC#9182
                || ZYPCommonFunc.hasValue(bizMsg.orgCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.orgNm_H2)
                || ZYPCommonFunc.hasValue(bizMsg.xxPsnNm_H1)
                || ZYPCommonFunc.hasValue(bizMsg.psnCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.orgTierCd_H1)
                // 2016/06/23 CSA-QC#10280 Mod Start
//                || ZYPCommonFunc.hasValue(bizMsg.lineBizTpCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.trtyTpCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.trtyGrpTpCd_H1)
                // 2016/06/23 CSA-QC#10280 Mod End
                || ZYPCommonFunc.hasValue(bizMsg.orgNm_H3)) {
            doProcess_NMAL2630Scrn00_Search(bizMsg, sMsg);
        }

        for (int i = 0; i < NMAL2630Constant.ADV_DEF_CNT; i++) {
            doProcess_NMAL2630Scrn00_InsertRow((NMAL2630CMsg) bizMsg, (NMAL2630SMsg) sMsg);
        }

    }

    private void doProcess_NMAL2630Scrn00_CMN_Clear(NMAL2630CMsg cMsg, NMAL2630SMsg sMsg) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.clear();
        doProcess_NMAL2630_INIT(cMsg, sMsg);
    }

    private void createPullDown(NMAL2630CMsg cMsg) {

        S21SsmEZDResult ssmResult = NMAL2630Query.getInstance().getBusinessArea(cMsg);

        if (ssmResult.isCodeNormal()) {

            List<Map> whList = (List<Map>) ssmResult.getResultObject();

            createPulldownList(cMsg.orgStruTpCd_H2, cMsg.orgStruTpNm_H1, whList, new String[]{"BIZ_AREA_ORG_CD", "BIZ_AREA_ORG_NM"});
        }

        createOrgTierPulldown(cMsg);
        // 2016/06/23 CSA-QC#10280 Mod Start
//        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, cMsg.lineBizTpCd_H2, cMsg.lineBizTpNm_H1);
        // Territory Type
        ZYPCodeDataUtil.createPulldownList(TRTY_TP.class, cMsg.trtyTpCd_H2, cMsg.trtyTpDescTxt_H1);
        // Territory Group Type
        ZYPCodeDataUtil.createPulldownList(TRTY_GRP_TP.class, cMsg.trtyGrpTpCd_H2, cMsg.trtyGrpTpDescTxt_H1);
        // 2016/06/23 CSA-QC#10280 Mod End
        ZYPCodeDataUtil.createPulldownList(TRTY_RULE_TP.class, cMsg.trtyRuleTpCd_B2, cMsg.trtyRuleTpNm_B1);
        ZYPCodeDataUtil.createPulldownList(TRTY_RULE_OPRD_TP.class, cMsg.trtyRuleOprdTpCd_B2, cMsg.trtyRuleOprdTpNm_B1);
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

    private void createOrgTierPulldown(NMAL2630CMsg cMsg) {

        ORG_TIERTMsg orgTierTMsg = new ORG_TIERTMsg();
        orgTierTMsg.setSQLID("001");
        orgTierTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());

        ORG_TIERTMsgArray orgTierTMsgArray = (ORG_TIERTMsgArray) S21ApiTBLAccessor.findByCondition(orgTierTMsg);
        int len = orgTierTMsgArray.length();
        if (len > 0) {
            int cnt = 0;
            EZDTStringItem orgTierCd;
            for (int i = 0; i < len; i++) {
                orgTierCd = orgTierTMsgArray.no(i).orgTierCd;
                if (!ORG_TIER._0.equals(orgTierCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.orgTierCd_H2.no(cnt), orgTierCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.orgTierNm_H1.no(cnt), orgTierTMsgArray.no(i).orgTierNm);
                    cnt++;
                }
            }
        }
    }

    private void doProcess_NMAL2630Scrn00_InsertRow(NMAL2630CMsg bizMsg, NMAL2630SMsg sMsg) {

        EZDMsgArray targetArray = bizMsg.B;
        int newRowIndex = targetArray.getValidCount();
        targetArray.get(newRowIndex).clear();
        targetArray.setValidCount(newRowIndex + 1);

    }
}
