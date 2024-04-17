/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0060;

import static business.blap.NWCL0060.constant.NWCL0060Constant.COMMA;
import static business.blap.NWCL0060.constant.NWCL0060Constant.ML_TMPL;
import static business.blap.NWCL0060.constant.NWCL0060Constant.ML_TMPL_KEY_BILL_TO_DS_ACCT_NM;
import static business.blap.NWCL0060.constant.NWCL0060Constant.NWCL0060_DEF_FROM_EML_ADDR;
import static business.blap.NWCL0060.constant.NWCL0060Constant.WIN_CRLF;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;

import business.blap.NWCL0060.constant.NWCL0060Constant;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;


/**
 *<pre>
 * Mail Entry.
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/25   Fujitsu         M.Nakamura      Create          N/A
 * 2018/09/19   Hitachi         Y.Takeno        Update          QC#19578
 * 2022/03/31   CITS            A.Cullano       Update          QC#59828
 * 2023/03/07   Hitachi         S.Fujita        Update          QC#61169
 *</pre>
 */
public class NWCL0060BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWCL0060CMsg bizMsg = (NWCL0060CMsg) cMsg;

            if ("NWCL0060_INIT".equals(screenAplID)) {
                doProcess_NWCL0060_INIT(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * INIT Event
     * </pre>
     * @param bizMsg Business Msg
     */
    private void doProcess_NWCL0060_INIT(NWCL0060CMsg bizMsg) {

        // START 2022/03/31 A.Cullano [QC#59828, ADD]
        // Set From Address (personal)
        String defFromEmailAddr = ZYPCodeDataUtil.getVarCharConstValue(NWCL0060_DEF_FROM_EML_ADDR, getGlobalCompanyCode());
        String psnEmlAddr = getContextUserInfo().getEmailAddress();
        if (!ZYPCommonFunc.hasValue(psnEmlAddr)) {
            psnEmlAddr = defFromEmailAddr;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.fromEmlAddr_H1, psnEmlAddr);

        String defEmlAddr = null;
        S21SsmEZDResult ssmResult = NWCL0060Query.getInstance().getInvPrtCtrl(bizMsg);
        if (ssmResult.isCodeNormal()) {
            Map< ? , ? > resultMap = (Map< ? , ? >) ssmResult.getResultObject();

            String lineBizTp = (String) resultMap.get("LINE_BIZ_TP_CD");
            String bizArea = (String) resultMap.get("DS_BIZ_AREA_CD");

            // Get email address from ML_BIZ_ADDR_MAP
            defEmlAddr = NWCL0060Query.getInstance().getFromAddrByBiz(lineBizTp, bizArea);

            if (!ZYPCommonFunc.hasValue(defEmlAddr)) {
                // If email address is not found in ML_BIZ_ADDR_MAP, check generic email by line of business (LOB)
                S21MailGroup groupfrom = new S21MailGroup(getGlobalCompanyCode(), NWCL0060Constant.MAIL_GROUP_FROM);
                groupfrom.setMailKey1(lineBizTp);
                if (groupfrom.getMailAddress().size() > 0) {
                    defEmlAddr = groupfrom.getMailAddress().get(0).getAddress();
                }
            }
        }
        if (!ZYPCommonFunc.hasValue(defEmlAddr)) {
            // Set a default email address if previous checks failed
            defEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(NWCL0060Constant.STGY_DEF_CLTR_EML, getGlobalCompanyCode());
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.fromEmlAddr_H2, defEmlAddr);
        // END 2022/03/31 A.Cullano [QC#59828, ADD]

        // Set Mail Information.
        S21MailTemplate s21MailTemplate = new S21MailTemplate(getGlobalCompanyCode(), ML_TMPL);

        if (!ZYPCommonFunc.hasValue(s21MailTemplate.getSubject())) {
            bizMsg.setMessageInfo("NWCM0092E");
            return;
        }
        // START 2018/09/19 [QC#19578, ADD]
        String billToDsAcctNm = bizMsg.A.no(0).billToDsAcctNm_A1.getValue();
        s21MailTemplate.setTemplateParameter(ML_TMPL_KEY_BILL_TO_DS_ACCT_NM, billToDsAcctNm);
        // END   2018/09/19 [QC#19578, ADD]
        ZYPEZDItemValueSetter.setValue(bizMsg.mlSubjTxt_H1, s21MailTemplate.getSubject());

        // Set To Address/Attachment Information.
        StringBuilder sbAdrs = new StringBuilder();
        StringBuilder sbAtt = new StringBuilder();
        List<String> adrsList = new ArrayList<String>();

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).invEmlAddr_A1.getValue())) {
                if (!adrsList.contains(bizMsg.A.no(i).invEmlAddr_A1.getValue())) {
                    if (sbAdrs.toString().isEmpty()) {
                        sbAdrs = sbAdrs.append(bizMsg.A.no(i).invEmlAddr_A1.getValue());
                    } else {
                        sbAdrs = sbAdrs.append(COMMA);
                        sbAdrs = sbAdrs.append(bizMsg.A.no(i).invEmlAddr_A1.getValue());
                    }
                    adrsList.add(bizMsg.A.no(i).invEmlAddr_A1.getValue());
                }
            }

//CSA MOD Start
//            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxFilePathTxt_A1.getValue())) {
//                if (sbAtt.toString().isEmpty()) {
//                    sbAtt = sbAtt.append(bizMsg.A.no(i).xxFilePathTxt_A1.getValue());
//                } else {
//                    sbAtt = sbAtt.append(WIN_CRLF);
//                    sbAtt = sbAtt.append(bizMsg.A.no(i).xxFilePathTxt_A1.getValue());
//                }
//            }

            String filePath = null;
            StringBuilder fileName = new StringBuilder();
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).xxFilePathTxt_A1.getValue())) {
                filePath = bizMsg.A.no(i).xxFilePathTxt_A1.getValue();
                
            } else if(ZYPCommonFunc.hasValue(bizMsg.A.no(i).eipRptRqstPk_A1.getValue())){
//                fileName.append(bizMsg.A.no(i).eipRptRqstPk_A1.getValue().toString());
//                SimpleDateFormat fmt = new SimpleDateFormat("_yyyyMMddHHmmss");
//                fileName.append(fmt.format(new Date())).append(".pdf");
                // START 2023/03/07 S.Fujita [QC#61169, MOD]
            	// if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).invNum_A1)) {
                if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).arCustRefNum_A1)) {
	                fileName.append("Invoice(");
	                // fileName.append(bizMsg.A.no(i).invNum_A1.getValue());
	                fileName.append(bizMsg.A.no(i).arCustRefNum_A1.getValue());
	            // END 2023/03/07 S.Fujita [QC#61169, MOD]
            	} else {
	                fileName.append("Bill(");
	                fileName.append(bizMsg.A.no(i).conslBillNum_A1.getValue());
            	}
                fileName.append(")_");
                fileName.append(bizMsg.A.no(i).eipRptRqstPk_A1.getValue().toString());
                fileName.append(".pdf");

                filePath = fileName.toString();
                bizMsg.A.no(i).xxFilePathTxt_A1.setValue(filePath);
            }
            if (ZYPCommonFunc.hasValue(filePath)) {
                if (sbAtt.toString().isEmpty()) {
                    sbAtt = sbAtt.append(filePath);
                } else {
                    sbAtt = sbAtt.append(WIN_CRLF);
                    sbAtt = sbAtt.append(filePath);
                }
            }
//CSA MOD End
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.emlAddr_H1, sbAdrs.toString());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxArMlBodyTmplTxt_H1, sbAtt.toString());
    }
}
