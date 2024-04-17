/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0490.NSAL0490CMsg;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/10   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/02/10   Hitachi         A.Kohinata      Update          CSA QC#1157
 * 2024/03/14   Hitachi         K.Watanabe      Update          QC#63542
 *</pre>
 */
public class NSAL0490_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        if (NSAL0490Constant.ITEM_NM_MDSE_CD.equals(scrnMsg.xxScrEventNm.getValue()) && !"CMN_Close".equals(getLastGuard())) {
            NSAL0490CMsg bizMsg = new NSAL0490CMsg();
            bizMsg.setBusinessID(NSAL0490Constant.BUSINESS_ID);
            bizMsg.setFunctionCode(NSAL0490Constant.FUNCTION_SEARCH);

            int selectIndex = getButtonSelectNumber();
            if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.xxCellIdx_A.setValue(selectIndex);
            } else {
                scrnMsg.xxCellIdx_B.setValue(selectIndex);
            }

            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!"CMN_Close".equals(getLastGuard())) {
            NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
            NSAL0490CMsg bizMsg = (NSAL0490CMsg) cMsg;

            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }

            if (NSAL0490Constant.ITEM_NM_MDL_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm, scrnMsg.xxCondNm.getValue());
                scrnMsg.setFocusItem(scrnMsg.mdlNm);

            } else if (NSAL0490Constant.ITEM_NM_MDL_GRP.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlGrpNm, scrnMsg.xxCondNm.getValue());
                scrnMsg.setFocusItem(scrnMsg.mdlGrpNm);
            // 2015/10/07 CSA Y.Tsuchimoto Del Start
//            } else if (NSAL0490Constant.ITEM_NM_MDSE_CD.equals(scrnMsg.xxScrEventNm.getValue())) {
//                int selectIndex = getButtonSelectNumber();
//
//                if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
//                    NSAL0490_ABMsg abMsg = scrnMsg.A.no(selectIndex);
//
//                    if (NSAL0490Constant.DTL_TP_PRNT.equals(abMsg.xxFlgNm_A.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(abMsg.prntMdseCd_A, scrnMsg.xxCondCd.getValue());
//                        scrnMsg.setFocusItem(abMsg.prntMdseCd_A);
//                    } else {
//                        ZYPEZDItemValueSetter.setValue(abMsg.childMdseCd_A, scrnMsg.xxCondCd.getValue());
//                        scrnMsg.setFocusItem(abMsg.childMdseCd_A);
//                    }
//                } else {
//                    NSAL0490_BBMsg bbMsg = scrnMsg.B.no(selectIndex);
//                    ZYPEZDItemValueSetter.setValue(bbMsg.mdseCd_B, scrnMsg.xxCondCd.getValue());
//                    scrnMsg.setFocusItem(bbMsg.mdseCd_B);
//                }
            // 2015/10/07 CSA Y.Tsuchimoto Del End
            // START 2016/02/10 A.Kohinata [QC#1157, ADD]
            } else if (NSAL0490Constant.ITEM_NM_SVC_SKILL.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcSkillNum, scrnMsg.xxCondCd.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcSkillDescTxt, scrnMsg.xxCondNm.getValue());
                scrnMsg.setFocusItem(scrnMsg.svcSkillNum);
            // END 2016/02/10 A.Kohinata [QC#1157, ADD]
            // START 2024/03/14 K.Watanabe [QC#63542, ADD]
            } else if (NSAL0490Constant.ITEM_NM_COPY_MDL_NM.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdlNm_CF, scrnMsg.xxCondNm.getValue());
                scrnMsg.setFocusItem(scrnMsg.mdlNm_CF);
            // END 2024/03/14 K.Watanabe [QC#63542, ADD]
            }
        }
    }
}
