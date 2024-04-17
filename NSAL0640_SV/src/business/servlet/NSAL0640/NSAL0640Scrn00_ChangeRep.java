/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0640;

import static business.servlet.NSAL0640.common.NSAL0640CommonLogic.*;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.BUSINESS_ID;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.NWAL1130_PRM_LENGTH;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_DTL;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_HEAD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0640.NSAL0640CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/31   Hitachi         M.Gotou         Create          N/A
 *</pre>
 */
public class NSAL0640Scrn00_ChangeRep extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        int index = getButtonSelectNumber();
        scrnMsg.xxRowNum.setValue(index);
        if (index >= 0) {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_DTL);
            scrnMsg.addCheckItem(scrnMsg.A.no(index).xxPsnNm_A2);
        } else {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_HEAD);
            scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H);
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;

        NSAL0640CMsg bizMsg = new NSAL0640CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        NSAL0640CMsg bizMsg  = (NSAL0640CMsg) cMsg;

        this.setResult("no");
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg.getValue())) {
            this.setResult("yes");

            scrnMsg.psnCd_P.clear();
            int index = getButtonSelectNumber();
            if (index >= 0) {
                setValue(scrnMsg.svcContrBrCd_P, scrnMsg.A.no(index).svcContrBrCd_A2);
                scrnMsg.A.no(index).svcContrBrCd_A2.clear();
                setValue(scrnMsg.xxPsnNm_P, scrnMsg.A.no(index).xxPsnNm_A2);
            } else {
                setValue(scrnMsg.svcContrBrCd_P, scrnMsg.svcContrBrCd_H);
                scrnMsg.svcContrBrCd_H.clear();
                setValue(scrnMsg.xxPsnNm_P, scrnMsg.xxPsnNm_H);
            }
            String st = "";
            st = scrnMsg.xxPsnNm_P.getValue() + "%";
            setValue(scrnMsg.xxPsnNm_P, st);
            ZYPTableUtil.clear(scrnMsg.R);
            // 0 : Lv1 : Suffix (String: Output Item Suffix)
            // 1 : Lv1 : Window Title (String: Popup Title)
            // 2 : Lv1 : Select Table Name (String: Search SQL)
            // 3 : Lv2 : Where List (List: Search condition columns)
            // 4 : Lv2 : Column List (List: Search result columns)
            // 5 : Lv2 : Sort Condition (List: Sort columns)
            // 6 : Lv2 : Output
            Object[] prm = new Object[NWAL1130_PRM_LENGTH];
            int i = 0;
            prm[i++] = "";
            prm[i++] = "Rep  Search Popup";
            prm[i++] = getSelectSQL(scrnMsg, getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            if (index >= 0) {
                prm[i++] = getSearchConditionSettingDtl(scrnMsg, index);
            } else {
                prm[i++] = getSearchConditionSetting(scrnMsg);
            }
            prm[i++] = getDisplayColumnSetting(scrnMsg);
            prm[i++] = getSortSetting(scrnMsg);
            prm[i++] = scrnMsg.R;
            setArgForSubScreen(prm);

            return;
        }
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            throw new EZDFieldErrorException();
        }
    }
}
