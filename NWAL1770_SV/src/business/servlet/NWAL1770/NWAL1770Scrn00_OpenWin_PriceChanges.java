/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0683E;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NWAM0962E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.constant.NWAL1770Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/07   Fujitsu         T.Yoshida       Create          N/A
 * 2018/04/16   Fujitsu         N.Sugiura       Update          S21_NA#22965
 * 2018/08/08   Fujitsu         M.Ishii         Update          QC#26551
 * 2018/09/19   Fujitsu         S.Kosaka        Update          QC#9700
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 * 2024/04/03   CITS            A.Shimada       Update          CSA-QC#63691
 *</pre>
 */
public class NWAL1770Scrn00_OpenWin_PriceChanges extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_B", ZYPConstant.FLG_ON_Y);
      //START 2024/04/03 [CSA-QC#63691,ADD]
        int blankCount = 0;
      //END 2024/04/03 [CSA-QC#63691,ADD]
// S21_NA#22965 Del Start
//        if (checkList.size() == 0) {
//            scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_ITEM_LINE });
//            throw new EZDFieldErrorException();
//        } else if (checkList.size() > 1) {
//            scrnMsg.setMessageInfo(NWAM0683E);
//            throw new EZDFieldErrorException();
//        }
// S21_NA#22965 Del End
        if (checkList.size() > 1) {
            scrnMsg.setMessageInfo(NWAM0683E);
            throw new EZDFieldErrorException();
        }
        // 2018/08/08 QC#26551 Add Start
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            //START 2024/04/03 [CSA-QC#63691,ADD]
            NWAL1770_BBMsg itemLineMsg = scrnMsg.B.no(i);
            boolean isCheck = ZYPCommonFunc.hasValue(itemLineMsg.xxChkBox_B);
            boolean isSellPriceBlank = !ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dealSplyQuoteDtlSlsAmt_B);
            if (!isCheck && isSellPriceBlank) {
                blankCount++;
            }
            if (isCheck && isSellPriceBlank) {
            //END 2024/04/03 [CSA-QC#63691,ADD]
              //START 2024/04/03 [CSA-QC#63691,DEL]
//            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).dealSplyQuoteDtlSlsAmt_B)) {
              //END 2024/04/03 [CSA-QC#63691,DEL]
                scrnMsg.B.no(i).dealSplyQuoteDtlSlsAmt_B.setErrorInfo(1, NWAM0962E);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).dealSplyQuoteDtlSlsAmt_B);
            }
        }
        //START 2024/04/03 [CSA-QC#63691,ADD]
        if (blankCount == scrnMsg.B.getValidCount()) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.B.no(i).dealSplyQuoteDtlSlsAmt_B.setErrorInfo(1, NWAM0962E);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).dealSplyQuoteDtlSlsAmt_B);
            }
        }
        //END 2024/04/03 [CSA-QC#63691,ADD]
        scrnMsg.putErrorScreen();
        // 2018/08/08 QC#26551 Add End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        // QC#9700  2018/09/18 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxViewChngLogSrcCd_S, NWAL1770Constant.MODE_SUPPLY_QUOTE);
        // QC#9700  2018/09/18 Add End
        Object[] params = NWAL1770CommonLogic.getParamNWAL1660(scrnMsg);
        setArgForSubScreen(params);
    }
}
