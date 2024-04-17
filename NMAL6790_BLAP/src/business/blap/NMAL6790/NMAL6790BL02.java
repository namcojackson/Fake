/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6790;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL6790.constant.NMAL6790Constant;
import business.db.CTAC_TPTMsg;
import business.db.CTAC_TPTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PSN_DEPT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PSN_TTL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/21   Fujitsu         K.Koitabashi    Create          N/A
 * 2017/10/17   Fujitsu         K.Ishizuka      Update          QC#20249(Sol#454)
 * 2018/10/11   Fujitsu         T.Noguchi       Update          QC#27869
 *</pre>
 */
public class NMAL6790BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL6790_INIT".equals(screenAplID)) {
                doProcess_NMAL6790_INIT((NMAL6790CMsg) cMsg, (NMAL6790SMsg) sMsg);
            } else if ("NMAL6790Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL6790Scrn00_Search((NMAL6790CMsg) cMsg, (NMAL6790SMsg) sMsg);
                doProcess_NMAL6790Scrn00_ShowContactPointDtl((NMAL6790CMsg) cMsg, (NMAL6790SMsg) sMsg);
            } else if ("NMAL6790Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NMAL6790Scrn00_PageNext((NMAL6790CMsg) cMsg, (NMAL6790SMsg) sMsg);
            } else if ("NMAL6790Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NMAL6790Scrn00_PagePrev((NMAL6790CMsg) cMsg, (NMAL6790SMsg) sMsg);
            } else if ("NMAL6790Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL6790Scrn00_CMN_Clear((NMAL6790CMsg) cMsg, (NMAL6790SMsg) sMsg);
            } else if ("NMAL6790Scrn00_ShowContactPointDtl".equals(screenAplID)) {
                doProcess_NMAL6790Scrn00_ShowContactPointDtl((NMAL6790CMsg) cMsg, (NMAL6790SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL6790Scrn00_Search(NMAL6790CMsg bizMsg, NMAL6790SMsg sMsg) {

        bizMsg.A.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.B.clear();
        bizMsg.B.setValidCount(0);

        S21SsmEZDResult ssmResult = NMAL6790Query.getInstance().getContact(bizMsg, sMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                bizMsg.setMessageInfo(NMAL6790Constant.NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // Move first page date from SMsg to CMsg
            int i = 0;
            boolean isFirst = true;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }

                EZDMsg.copy(sMsg.A.no(i), null, bizMsg.A.no(i), null);

                // For change Background color
                if (isFirst) {
                    bizMsg.A.no(i).xxDplyCtrlFlg_A1.setValue(ZYPConstant.FLG_ON_Y);
                    bizMsg.xxRowNum.setValue(0);
                    isFirst = false;
                } else {
                    bizMsg.A.no(i).xxDplyCtrlFlg_A1.setValue(ZYPConstant.FLG_OFF_N);
                }
            }
            bizMsg.A.setValidCount(i);

            // Set page number
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            bizMsg.setMessageInfo(NMAL6790Constant.NZZM0000E);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }

    }

    private void doProcess_NMAL6790Scrn00_ShowContactPointDtl(NMAL6790CMsg bizMsg, NMAL6790SMsg sMsg) {

        if (bizMsg.A.getValidCount() > 0) {

            S21SsmEZDResult ssmResult;

            bizMsg.B.clear();
            bizMsg.B.setValidCount(0);

            if (ZYPCommonFunc.hasValue(bizMsg.serNum_H1)) {
                ssmResult = NMAL6790Query.getInstance().getDsCtacPntWithSerialIB(bizMsg, sMsg);
            } else if (ZYPCommonFunc.hasValue(bizMsg.svcCtacTpCd_H1)) {
                ssmResult = NMAL6790Query.getInstance().getDsCtacPntWithSerialIB(bizMsg, sMsg);
            } else {
                ssmResult = NMAL6790Query.getInstance().getDsCtacPntWithoutSerialIB(bizMsg, sMsg);
            }

            if (ssmResult.isCodeNormal()) {
                int i = 0;
                for (; i < sMsg.B.getValidCount(); i++) {
                    if (i == bizMsg.B.length()) {
                        break;
                    }
                    EZDMsg.copy(sMsg.B.no(i), null, bizMsg.B.no(i), null);
                }
                bizMsg.B.setValidCount(i);
            } else {
                bizMsg.setMessageInfo(NMAL6790Constant.NZZM0000E);
                return;
            }
        }
    }

    /**
     * @param bizMsg NMAL6790CMsg
     * @param sMsg NMAL6790SMsg
     */
    private void doProcess_NMAL6790_INIT(NMAL6790CMsg bizMsg, NMAL6790SMsg sMsg) {

        createPullDown(bizMsg);

        // When the parameter is set.
        if (ZYPCommonFunc.hasValue(bizMsg.ctacTpCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H2)
                || ZYPCommonFunc.hasValue(bizMsg.dsAcctNm_H1)
                || ZYPCommonFunc.hasValue(bizMsg.ctacPsnFirstNm_H1)
                || ZYPCommonFunc.hasValue(bizMsg.dsCtacPntValTxt_H1)
                || ZYPCommonFunc.hasValue(bizMsg.dsAcctNum_H1)
                || ZYPCommonFunc.hasValue(bizMsg.ctacPsnLastNm_H1)
                || ZYPCommonFunc.hasValue(bizMsg.dsCtacPsnTtlCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.dsLocNm_H1)
                || ZYPCommonFunc.hasValue(bizMsg.locNum_H1)
                || ZYPCommonFunc.hasValue(bizMsg.dsCtacPsnDeptCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.serNum_H1)
                || ZYPCommonFunc.hasValue(bizMsg.xxDplyByItemNm_H1)
                || ZYPCommonFunc.hasValue(bizMsg.svcCtacTpCd_H1)
                || ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H1)
                || ZYPCommonFunc.hasValue(bizMsg.xxChkBox_H2)) {

            doProcess_NMAL6790Scrn00_Search(bizMsg, sMsg);

            ZYPEZDItemValueSetter.setValue(bizMsg.xxRowNum, BigDecimal.ZERO);
            doProcess_NMAL6790Scrn00_ShowContactPointDtl(bizMsg, sMsg);
        }
    }

    private void doProcess_NMAL6790Scrn00_PageNext(NMAL6790CMsg cMsg, NMAL6790SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int paginationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = paginationFrom;
        for (; i < paginationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - paginationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - paginationFrom);

        // set value to pagination items
        cMsg.xxPageShowFromNum.setValue(paginationFrom + 1);
        cMsg.xxPageShowToNum.setValue(paginationFrom + cMsg.A.getValidCount());
    }

    private void doProcess_NMAL6790Scrn00_PagePrev(NMAL6790CMsg cMsg, NMAL6790SMsg sMsg) {
        // copy data from SMsg to CMsg
        int paginationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = paginationFrom;
        for (; i < paginationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - paginationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - paginationFrom);

        // set value to pagination items
        paginationFrom = paginationFrom + 1;
        cMsg.xxPageShowFromNum.setValue(paginationFrom);
        cMsg.xxPageShowToNum.setValue(paginationFrom + cMsg.A.getValidCount() - 1);
    }

    private void doProcess_NMAL6790Scrn00_CMN_Clear(NMAL6790CMsg cMsg, NMAL6790SMsg sMsg) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);
        sMsg.clear();
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.clear();
        sMsg.B.clear();
        sMsg.B.setValidCount(0);
        sMsg.clear();
        doProcess_NMAL6790_INIT(cMsg, sMsg);
    }

    private void createPullDown(NMAL6790CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(DS_CTAC_PSN_TTL.class, cMsg.dsCtacPsnTtlCd_H2, cMsg.dsCtacPsnTtlDescTxt_H1);
        // 2018/10/11 QC#27869 Del Start
        //ZYPCodeDataUtil.createPulldownList(SVC_CTAC_TP.class, cMsg.svcCtacTpCd_H2, cMsg.svcCtacTpDescTxt_H1);
        // 2018/10/11 QC#27869 Del End
        ZYPCodeDataUtil.createPulldownList(DS_CTAC_PSN_DEPT.class, cMsg.dsCtacPsnDeptCd_H2, cMsg.dsCtacPsnDeptDescTxt_H1);

        CTAC_TPTMsg ctacTpTMsg = new CTAC_TPTMsg();
        ctacTpTMsg.setSQLID("002");
        ctacTpTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        ctacTpTMsg.setConditionValue("actvFlg01", ZYPConstant.FLG_ON_Y);
        ctacTpTMsg.setConditionValue("mstrViewAvalFlg01", ZYPConstant.FLG_ON_Y);// S21_NA ADD QC#20249(Sol#454)

        CTAC_TPTMsgArray ctacTpTMsgArray = (CTAC_TPTMsgArray) EZDTBLAccessor.findByCondition(ctacTpTMsg);
        if (ctacTpTMsgArray.length() == 0) {
            cMsg.setMessageInfo(NMAL6790Constant.NMAM8111E, new String[] {NMAL6790Constant.CTAC_TP_DBCOLUMN });
            return;
        }

        for (int i = 0; i < ctacTpTMsgArray.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.ctacTpCd_H2.no(i), ctacTpTMsgArray.no(i).ctacTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.ctacTpDescTxt_H1.no(i), ctacTpTMsgArray.no(i).ctacTpDescTxt.getValue());
        }

        // 2018/10/11 QC#27869 Add Start
        S21SsmEZDResult ssmResult = NMAL6790Query.getInstance().getSvcCatgTpList();

        if (ssmResult.isCodeNormal()) {
            List<Map> list = (List<Map>) ssmResult.getResultObject();
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    Map map = list.get(i);
                    ZYPEZDItemValueSetter.setValue(cMsg.ctacTpCd_H2.no(i + ctacTpTMsgArray.getValidCount()), (String) map.get("SVC_CTAC_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(cMsg.ctacTpDescTxt_H1.no(i + ctacTpTMsgArray.getValidCount()), (String) map.get("SVC_CTAC_TP_DESC_TXT"));
                }
            } else {
                cMsg.setMessageInfo(NMAL6790Constant.NMAM8111E, new String[] {NMAL6790Constant.SVC_CTAC_TP_DBCOLUMN });
            }
        } else {
            cMsg.setMessageInfo(NMAL6790Constant.NMAM8111E, new String[] {NMAL6790Constant.SVC_CTAC_TP_DBCOLUMN });
        }
        // 2018/10/11 QC#27869 Add End
    }
}
