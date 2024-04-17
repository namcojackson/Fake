/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0450;

import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.checkMandatory;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.initialControlScreen;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.protectCheckBox;
import static business.servlet.NSBL0450.common.NSBL0450CommonLogic.showDetail;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.*;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0450.NSBL0450CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 * 2015/12/22   Hitachi         T.Iwamoto         Update          QC#2298
 * 2017/03/01   Hitachi         A.Kohinata        Update          QC#17608
 *</pre>
 */
public class NSBL0450Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        checkMandatory(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;

        scrnMsg.svcSupplTaskNum_S2.clear();
        scrnMsg.techPsnNm_S2.clear();
        scrnMsg.mgrNm_S2.clear();
        scrnMsg.svcSupplTaskTpCd_S2.clear();
        scrnMsg.cratDt_S2.clear();
        // [QC#2298,MOD]START
        scrnMsg.svcSupplTaskNum_D.clear();
        scrnMsg.svcSupplTaskTpDescTxt_D.clear();
        scrnMsg.techPsnNm_D.clear();
        scrnMsg.mgrNm_D.clear();
        scrnMsg.xxCmntTxt_D.clear();
        scrnMsg.svcTaskNum_D.clear();
        scrnMsg.svcSupplFromDt_D.clear();
        scrnMsg.xxDtTm_D1.clear();
        scrnMsg.svcSupplToDt_D.clear();
        scrnMsg.xxDtTm_D2.clear();
        scrnMsg.xxDtTm_D3.clear();
        scrnMsg.xxDtTm_D4.clear();
        scrnMsg.xxDtTm_D5.clear();
        scrnMsg.xxAllPsnNm_D1.clear();
        scrnMsg.cratDt_D.clear();
        scrnMsg.xxDtTm_D6.clear();
        scrnMsg.xxAllPsnNm_D2.clear();
        scrnMsg.updDt_D.clear();
        scrnMsg.xxDtTm_D7.clear();
        scrnMsg.svcSupplTaskStsCd_D.clear();
        scrnMsg.xxRowNum_D.clear();
        // [QC#2298,MOD]END

        ZYPEZDItemValueSetter.setValue(scrnMsg.svcSupplTaskNum_S2, scrnMsg.svcSupplTaskNum_S);
        ZYPEZDItemValueSetter.setValue(scrnMsg.techPsnNm_S2, scrnMsg.techPsnNm_S);
        ZYPEZDItemValueSetter.setValue(scrnMsg.mgrNm_S2, scrnMsg.mgrNm_S);
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcSupplTaskTpCd_S2, scrnMsg.svcSupplTaskTpCd_SS);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cratDt_S2, scrnMsg.cratDt_S);

        NSBL0450CMsg bizMsg = new NSBL0450CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;
        NSBL0450CMsg bizMsg = (NSBL0450CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        // add start 2017/03/01 CSA Defect#17608
        protectCheckBox(this, scrnMsg);
        // add end 2017/03/01 CSA Defect#17608
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        showDetail(this, scrnMsg, 0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_A, BigDecimal.valueOf(0));
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
    }
}
