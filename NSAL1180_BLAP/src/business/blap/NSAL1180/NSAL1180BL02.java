/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1180;

import static business.blap.NSAL1180.constant.NSAL1180Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;


import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/30   Hitachi         K.Yamada        Create          N/A
 *</pre>
 */
public class NSAL1180BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL1180_INIT".equals(screenAplID)) {
                doProcess_NSAL1180Scrn00_init((NSAL1180CMsg) cMsg);
            } else if ("NSAL1180Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NSAL1180Scrn00_init((NSAL1180CMsg) cMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1180Scrn00_init(NSAL1180CMsg cMsg) {

        //get Region and Branch
        S21SsmEZDResult ssmResult = NSAL1180Query.getInstance().findRgAndBr(cMsg);
        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rgBrList = (List<Map<String, Object>>) ssmResult.getResultObject();
        //set region info to cMsg
        setRgInfo(cMsg, rgBrList.get(0));

        for (int i = 0; i < rgBrList.size(); i++) {
            Map<String, Object> rgBrMap = rgBrList.get(i);

            //set branch
            setBrInfo(cMsg, rgBrMap);

            //get resource
            ssmResult = NSAL1180Query.getInstance().findResource(cMsg, (String) rgBrMap.get("SVC_CONTR_BR_CD"));
            if (ssmResult.isCodeNotFound()) {
                cMsg.setMessageInfo(NZZM0000E);
                break;
            }

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> resrcList = (List<Map<String, Object>>) ssmResult.getResultObject();
            setResrcInfo(cMsg, rgBrMap, resrcList);
            filterRep(cMsg);
        }
    }

    private void setRgInfo(NSAL1180CMsg cMsg, Map<String, Object> rgBrMap) {
        setValue(cMsg.A.no(0).svcRgPk_A, (BigDecimal) rgBrMap.get("SVC_RG_PK"));
        setValue(cMsg.A.no(0).svcRgNm_A, (String) rgBrMap.get("SVC_RG_NM"));

        StringBuilder sb = new StringBuilder();
        sb.append(RG_PREFIX);
        sb.append(" ");
        sb.append(cMsg.A.no(0).svcRgNm_A.getValue());
        setValue(cMsg.A.no(0).xxRecNmTxt_HI, sb.toString());
        cMsg.A.setValidCount(1);
    }

    private boolean setBrInfo(NSAL1180CMsg cMsg, Map<String, Object> rgBrMap) {
        int idx = cMsg.A.getValidCount();

        if (idx == cMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
            return false;
        }

        setValue(cMsg.A.no(idx).svcRgPk_A, (BigDecimal) rgBrMap.get("SVC_RG_PK"));
        setValue(cMsg.A.no(idx).svcRgNm_A, (String) rgBrMap.get("SVC_RG_NM"));
        setValue(cMsg.A.no(idx).lineBizTpCd_A, (String) rgBrMap.get("LINE_BIZ_TP_CD"));
        setValue(cMsg.A.no(idx).lineBizTpDescTxt_A, (String) rgBrMap.get("LINE_BIZ_TP_DESC_TXT"));
        setValue(cMsg.A.no(idx).svcContrBrCd_A, (String) rgBrMap.get("SVC_CONTR_BR_CD"));
        setValue(cMsg.A.no(idx).svcContrBrDescTxt_A, (String) rgBrMap.get("SVC_CONTR_BR_DESC_TXT"));

        StringBuilder sb = new StringBuilder();
        sb.append(createIndent(1));
        sb.append(BR_PREFIX);
        sb.append(" ");
        sb.append(cMsg.A.no(idx).lineBizTpCd_A.getValue());
        sb.append("-");
        sb.append(cMsg.A.no(idx).svcContrBrCd_A.getValue());
        sb.append("-");
        sb.append(cMsg.A.no(idx).svcContrBrDescTxt_A.getValue());
        setValue(cMsg.A.no(idx).xxRecNmTxt_HI, sb.toString());
        idx++;
        cMsg.A.setValidCount(idx);

        return true;
    }

    private String createIndent(int indentLv) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= indentLv; i++) {
            sb.append(INDENT);
        }
        return sb.toString();
    }

    private boolean setResrcInfo(NSAL1180CMsg cMsg, Map<String, Object> rgBrMap, List<Map<String, Object>> resrcList) {
        int idx = cMsg.A.getValidCount();

        if (idx == cMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
            return false;
        }

        int indentLv = 2;
        String prevFuncRoleTp = null;

        for (Map<String, Object> resrcMap : resrcList) {
            setValue(cMsg.A.no(idx).svcRgPk_A, (BigDecimal) rgBrMap.get("SVC_RG_PK"));
            setValue(cMsg.A.no(idx).svcRgNm_A, (String) rgBrMap.get("SVC_RG_NM"));
            setValue(cMsg.A.no(idx).lineBizTpCd_A, (String) rgBrMap.get("LINE_BIZ_TP_CD"));
            setValue(cMsg.A.no(idx).lineBizTpDescTxt_A, (String) rgBrMap.get("LINE_BIZ_TP_DESC_TXT"));
            setValue(cMsg.A.no(idx).svcContrBrCd_A, (String) rgBrMap.get("SVC_CONTR_BR_CD"));
            setValue(cMsg.A.no(idx).svcContrBrDescTxt_A, (String) rgBrMap.get("SVC_CONTR_BR_DESC_TXT"));
            setValue(cMsg.A.no(idx).orgFuncRoleTpCd_A, (String) resrcMap.get("ORG_FUNC_ROLE_TP_CD"));
            setValue(cMsg.A.no(idx).orgFuncRoleTpNm_A, (String) resrcMap.get("ORG_FUNC_ROLE_TP_NM"));
            setValue(cMsg.A.no(idx).apvlLimitAmt_A, (BigDecimal) resrcMap.get("APVL_LIMIT_AMT"));
            setValue(cMsg.A.no(idx).psnCd_A, (String) resrcMap.get("PSN_CD"));
            setValue(cMsg.A.no(idx).psnFirstNm_A, (String) resrcMap.get("PSN_FIRST_NM"));
            setValue(cMsg.A.no(idx).psnMidNm_A, (String) resrcMap.get("PSN_MID_NM"));
            setValue(cMsg.A.no(idx).psnLastNm_A, (String) resrcMap.get("PSN_LAST_NM"));

            if (!hasValue(prevFuncRoleTp)) {
                prevFuncRoleTp = cMsg.A.no(idx).orgFuncRoleTpCd_A.getValue();
            }
            if (!prevFuncRoleTp.equals(cMsg.A.no(idx).orgFuncRoleTpNm_A.getValue())) {
                indentLv++;
                prevFuncRoleTp = cMsg.A.no(idx).orgFuncRoleTpCd_A.getValue();
            }

            StringBuilder sb = new StringBuilder();
            sb.append(createIndent(indentLv));
            sb.append(cMsg.A.no(idx).psnCd_A.getValue());
            setValue(cMsg.A.no(idx).xxRecNmTxt_HI, sb.toString());

            StringBuilder psn = new StringBuilder();
            psn.append(cMsg.A.no(idx).psnFirstNm_A.getValue());
            if (hasValue(cMsg.A.no(idx).psnMidNm_A)) {
                psn.append(" ");
                psn.append(cMsg.A.no(idx).psnMidNm_A.getValue());
            }
            if (hasValue(cMsg.A.no(idx).psnLastNm_A)) {
                psn.append(" ");
                psn.append(cMsg.A.no(idx).psnLastNm_A.getValue());
            }
            setValue(cMsg.A.no(idx).xxRecNmTxt_PS, psn.toString());

            idx++;
            cMsg.A.setValidCount(idx);

            if (idx == cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                return false;
            }
        }

        return true;
    }

    private void filterRep(NSAL1180CMsg cMsg) {
        if (!hasValue(cMsg.psnCd)) {
            return;
        }

        String targetFuncRole = null;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL1180_ACMsg detail = cMsg.A.no(i);
            if (cMsg.psnCd.getValue().equals(detail.psnCd_A.getValue())) {
                targetFuncRole = detail.orgFuncRoleTpCd_A.getValue();
                break;
            }
        }

        if (!hasValue(targetFuncRole)) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }

        List<Integer> delList = new ArrayList<Integer>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            NSAL1180_ACMsg detail = cMsg.A.no(i);
            if (!hasValue(detail.psnCd_A)) {
                continue;
            }
            if (targetFuncRole.equals(detail.orgFuncRoleTpCd_A.getValue())
                    && !cMsg.psnCd.getValue().equals(detail.psnCd_A.getValue())) {
                delList.add(i);
            }
        }
        ZYPTableUtil.deleteRows(cMsg.A, delList);
    }

}
