/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1810;

import static business.servlet.NWAL1810.common.NWAL1810CommonLogic.pageItemClear;
import static business.servlet.NWAL1810.common.NWAL1810CommonLogic.setTabProtect;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.*;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.DEF_CD_SUMMARY;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.LVL_CD_ALL;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.LVL_CD_CONF;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.LVL_DISP_ALL;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.LVL_DISP_CONF;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.LVL_DISP_LINE;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.NWAM0270E;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.SOURCE_ID_ORDER;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.SOURCE_ID_QUOTE;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.SOURCE_NM_ORDER;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.SOURCE_NM_QUOTE;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.SOURCE_NM_SCHEDULE;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.TAB_DETAIL;
import static business.servlet.NWAL1810.constant.NWAL1810Constant.TAB_SUMMARY;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1810.NWAL1810CMsg;
import business.servlet.NWAL1810.common.NWAL1810CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NWAL1810_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         S.Ohki          Create          N/A
 * 2016/04/08   Fujitsu         M.Yamada        Update          QC#6731
 * 2022/10/20   Hitachi         H.Watanabe      Update          QC#60258
 *</pre>
 */
public class NWAL1810_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1810BMsg scrnMsg = (NWAL1810BMsg) bMsg;

        pageItemClear(scrnMsg);

        NWAL1810CMsg bizMsg = new NWAL1810CMsg();

        scrnMsg.xxTrnsnOrigScrnId.setValue(super.getSubmitedScrnId(ctx));
        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == PRM_NUM) {
            int ixP = 0;
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxViewChngLogSrchNum, (EZDBStringItem) params[ixP++]); // 0
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxViewChngLogSrcCd, (EZDBStringItem) params[ixP++]); // 1
            ZYPEZDItemValueSetter.setValue(scrnMsg.noteLvlCd, (EZDBStringItem) params[ixP++]); // 2
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDefSelTabCd, (EZDBStringItem) params[ixP++]); // 3

            // Number
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxViewChngLogSrchNum)) {
                setTabProtect(scrnMsg, scrnMsg.xxViewChngLogSrchNum);
                return null;
            }

            // Source
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxViewChngLogSrcCd)) {
                setTabProtect(scrnMsg, scrnMsg.xxViewChngLogSrcCd);
                return null;
            } else {

                if (SOURCE_ID_ORDER.equals(scrnMsg.xxViewChngLogSrcCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.viewChngLogSrcNm, SOURCE_NM_ORDER);
                } else if (SOURCE_ID_QUOTE.equals(scrnMsg.xxViewChngLogSrcCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.viewChngLogSrcNm, SOURCE_NM_QUOTE);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.viewChngLogSrcNm, SOURCE_NM_SCHEDULE);
                }
            }

            // Level
            if (!ZYPCommonFunc.hasValue(scrnMsg.noteLvlCd)) {
                setTabProtect(scrnMsg, scrnMsg.noteLvlCd);
                return null;
            } else {

                if (LVL_CD_ALL.equals(scrnMsg.noteLvlCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxAudLvlNm, LVL_DISP_ALL);
                } else if (LVL_CD_CONF.equals(scrnMsg.noteLvlCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxAudLvlNm, LVL_DISP_CONF);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxAudLvlNm, LVL_DISP_LINE);
                }

                // Line#
                if (!LVL_CD_ALL.equals(scrnMsg.noteLvlCd.getValue())) {

                    if (params[ixP] == null) { // 4
                        setTabProtect(scrnMsg, scrnMsg.L.no(0).dsOrdPosnNum_L0);
                        return null;
                    }

                    if (params[ixP] instanceof List) {

                        @SuppressWarnings("unchecked")
                        List<Object[]> paramList = (List<Object[]>) params[ixP]; // 4
                        int indexOrd = 0;
                        int indexRtn = 0;
                        for (Object[] paramAry : paramList) {

                            // QC#5283 start
                            //                            if (paramAry.length == 1 || (paramAry.length == 2 && paramAry[1] == null)) {
                            //                                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(indexOrd).xxLineNum_L0, (String) paramAry[0]);
                            //                                indexOrd++;
                            //                            } else if (paramAry.length == 2 && paramAry[1] != null) {
                            //
                            //                                if (CONFIG_CATG.OUTBOUND.equals((String) paramAry[1])) {
                            //                                    ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(indexOrd).xxLineNum_L0, (String) paramAry[0]);
                            //                                    indexOrd++;
                            //                                } else {
                            //                                    ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(indexRtn).xxLineNum_R0, (String) paramAry[0]);
                            //                                    indexRtn++;
                            //                                }
                            //                            }
                            //----
                            String configCatg = "";
                            if (paramAry.length == 4 && paramAry[3] != null) {
                                configCatg = (String) paramAry[3];
                            }
                            int ixAp = 0;
                            if (CONFIG_CATG.OUTBOUND.equals(configCatg)) {
                                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(indexOrd).dsOrdPosnNum_L0, (String) paramAry[ixAp++]);
                                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(indexOrd).cpoDtlLineNum_L0, (String) paramAry[ixAp++]);
                                ZYPEZDItemValueSetter.setValue(scrnMsg.L.no(indexOrd).cpoDtlLineSubNum_L0, (String) paramAry[ixAp++]);
                                indexOrd++;
                            } else {
                                ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(indexRtn).dsOrdPosnNum_R0, (String) paramAry[ixAp++]);
                                ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(indexRtn).dsCpoRtrnLineNum_R0, (String) paramAry[ixAp++]);
                                ZYPEZDItemValueSetter.setValue(scrnMsg.R.no(indexRtn).dsCpoRtrnLineSubNum_R0, (String) paramAry[ixAp++]);
                                indexRtn++;
                            }
                            // QC#5283 end
                        }
                        scrnMsg.L.setValidCount(indexOrd);
                        scrnMsg.R.setValidCount(indexRtn);

                        if (indexOrd == 0 && indexRtn == 0) {
                            setTabProtect(scrnMsg, scrnMsg.L.no(0).dsOrdPosnNum_L0);
                            return null;
                        }
                    }
                }
            }

            // Default(TAB)
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxDefSelTabCd)) {
                setTabProtect(scrnMsg, scrnMsg.xxDefSelTabCd);
                return null;
            } else {

                if (DEF_CD_SUMMARY.equals(scrnMsg.xxDefSelTabCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SUMMARY);
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_DETAIL);
                }
            }

        } else {
            scrnMsg.setMessageInfo(NWAM0270E);
            scrnMsg.xxTabProt_DE.setInputProtected(true);
            scrnMsg.xxTabProt_SU.setInputProtected(true);
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1810BMsg scrnMsg = (NWAL1810BMsg) bMsg;

        if (cMsg != null) {
            NWAL1810CMsg bizMsg = (NWAL1810CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        scrnMsg.xxViewChngLogSrchNum.setInputProtected(true);
        scrnMsg.xxAudLvlNm.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).eventNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A0.setInputProtected(true);
            scrnMsg.A.no(i).bizProcCmntTxt_A0.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).eventNm_B0.setInputProtected(true);
            scrnMsg.B.no(i).xxPsnNm_B0.setInputProtected(true);
            scrnMsg.B.no(i).xxRecValBefTxt_B0.setInputProtected(true);
            scrnMsg.B.no(i).xxRecValAftTxt_B0.setInputProtected(true);
        }

        S21TableColorController tblColor = new S21TableColorController("NWAL1810Scrn00", scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("B", scrnMsg.B);

        NWAL1810CommonLogic.initCmnBtnProp(this);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1810BMsg scrnMsg = (NWAL1810BMsg) bMsg;

        scrnMsg.xxViewChngLogSrchNum.setNameForMessage("Number");
        scrnMsg.xxViewChngLogSrcCd.setNameForMessage("Source");
        scrnMsg.noteLvlCd.setNameForMessage("Level");
        scrnMsg.xxDefSelTabCd.setNameForMessage("Default");
        scrnMsg.xxAudLvlNm.setNameForMessage("Level");
        // 2022/10/20 QC#60258 Add Start
        scrnMsg.xxFromDt.setNameForMessage("FromDate");
        scrnMsg.xxThruDt.setNameForMessage("ThruDate");
        scrnMsg.eventId.setNameForMessage("Event Name");
        scrnMsg.ordPrftTrxCatgCd.setNameForMessage("Category Code");
        scrnMsg.docId.setNameForMessage("Line");
        scrnMsg.xxSrUsrId.setNameForMessage("User Id");
        scrnMsg.xxPsnNm.setNameForMessage("User Name");
        scrnMsg.bizProcCmntTxt.setNameForMessage("Coment");
        scrnMsg.recDbItemAttrbNm.setNameForMessage("Attrbute Num");
        scrnMsg.xxRecValBefTxt.setNameForMessage("Before Text");
        scrnMsg.xxRecValAftTxt.setNameForMessage("Aftere Text");
        // 2022/10/20 QC#60258 Add End

        for (int i = 0; i < scrnMsg.L.length(); i++) {
            scrnMsg.L.no(i).dsOrdPosnNum_L0.setNameForMessage("Line");
        }
    }
}
