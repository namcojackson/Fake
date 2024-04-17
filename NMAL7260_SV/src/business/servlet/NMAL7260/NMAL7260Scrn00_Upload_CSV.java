/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.NZZM0002I;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.ZYEM0096E;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.ZZM9000E;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7260.NMAL7260CMsg;
import business.servlet.NMAL7260.common.NMAL7260CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260Scrn00_Upload_CSV
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2016/10/21   Fujitsu         R.Nakamura      Update          QC#14936
 * 2016/02/27   Fujitsu         R.Nakamura      Update          QC#16011
 * 2018/05/17   Fujitsu         T.Noguchi       Update          QC#26125
 * 2019/12/18   Fujitsu         C.Hara          Update          QC#55108
 *</pre>
 */
public class NMAL7260Scrn00_Upload_CSV extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        if (scrnMsg.xxFileData.isUploaded()) {

            // 2018/05/17 QC#26125 Mod Start
            //if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData, Arrays.asList(new String[] {"csv", "txt" }))) {
            if (!ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData, Arrays.asList(new String[] {"csv", "txt", "xls", "xlsx" }))) {
            // 2018/05/17 QC#26125 Mod End
                scrnMsg.xxFileData.setErrorInfo(1, ZYEM0096E);
            }
        } else {
            scrnMsg.xxFileData.setErrorInfo(1, ZZM9000E, new String[] {"Upload File" });
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        // upload
        if (scrnMsg.xxFileData.isUploaded()) {
            scrnMsg.xxUpldCsvFilePathTxt.setValue(scrnMsg.xxFileData.getValue().getFileName());
            scrnMsg.xxFileData.makeTempFile(null, ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData, getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData));
        }

        NMAL7260CMsg bizMsg = new NMAL7260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg  = (NMAL7260CMsg) cMsg;

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_04);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_05);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_06);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_07);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_08);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_09);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_10);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_11);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_12);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_13);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_14);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_15);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_15);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_16);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_17);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_18);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_19);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_20);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxSvcCallDt_FR);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxSvcCallDt_TH);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_22);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_22);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_24);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_24);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_25);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_26);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_26);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_27);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_28);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_29);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_30);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_30);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_31);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_32);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcDt_FR);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcDt_TH);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_34);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_35);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_36);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxRqstDt_FR);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxRqstDt_TH);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_38);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_39);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_40);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_41);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_42);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_44);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_45);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_45);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_46);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_48);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_49);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_53);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_54);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_55);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_56);
            // 2019/12/18 QC#55108 Add Start
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_57);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_58);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondThruTxt_58);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_59);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_60);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleCondFromTxt_61);
            // 2019/12/18 QC#55108 Add End
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcFmlaPk_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlRate_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcRuleDtlTxt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effFromDt_B1);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).effThruDt_B1);
        }


        NMAL7260CommonLogic.initCmnBtnProp(this, scrnMsg, getUserProfileService());
        NMAL7260CommonLogic.setBtnProp(this, scrnMsg, getUserProfileService());

        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}
