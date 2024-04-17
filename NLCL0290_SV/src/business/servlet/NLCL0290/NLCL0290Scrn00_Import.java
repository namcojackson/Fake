/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0290;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0290.NLCL0290CMsg;
import business.servlet.NLCL0290.common.NLCL0290CommonLogic;

import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.web.ZYPUploadFileValidator;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/10   CSAI            K.Lee           Create          N/A
 * 2018/04/06   CITS            K.Masaki        Update          QC#18472
 * 2019/01/08   Fujitsu         S.Takami        Update          QC#29763
 *</pre>
 */
public class NLCL0290Scrn00_Import extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

        if (scrnMsg.xxFileData_UP.isUploaded()) {
            ZYPUploadFileValidator.isAuthorizedExtension(scrnMsg.xxFileData_UP, Arrays.asList(new String[]{"csv", "txt"}));
        } else {
            scrnMsg.xxFileData_UP.setErrorInfo(1, "ZYPM0188E", new String[]{"csv,txt"});
        }

        scrnMsg.addCheckItem(scrnMsg.xxFileData_UP);
        scrnMsg.putErrorScreen();	

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;

        if (scrnMsg.xxFileData_UP.isUploaded()) {
            scrnMsg.xxFileData_UP.makeTempFile(
                null,
                ZYPFileNameUtil.createUniqueFileNm(scrnMsg.xxFileData_UP, getUserProfileService().getContextUserInfo().getUserId()), "." + ZYPFileNameUtil.getFileEx(scrnMsg.xxFileData_UP)
            );
        }

        NLCL0290CMsg bizMsg = new NLCL0290CMsg();
        bizMsg.setBusinessID("NLCL0290");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0290BMsg scrnMsg = (NLCL0290BMsg) bMsg;
        NLCL0290CMsg bizMsg  = (NLCL0290CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLCL0290CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H);
        // 2019/01/08 QC#29763 Add Start
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).fromRtlSwhCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).toRtlSwhCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).invtyOrdLineCmntTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcConfigMstrPk_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).serNum_A);
        }
        // 2019/01/08 QC#29763 Add End
        // QC:18472
        //scrnMsg.addCheckItem(scrnMsg.adjAcctAliasNm_H);
        scrnMsg.putErrorScreen();
    }
}
