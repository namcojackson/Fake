/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1010;

import static business.servlet.NPAL1010.constant.NPAL1010Constant.BUSINESS_ID;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.FUNC_SRCH_ID;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_ADDL_LOC_NM;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_CTY_ADDR;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_INVTY_ACCT_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_LOC_NM;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_LOC_ROLE_TP_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_OWNR_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_OWNR_NM;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_POST_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_RTL_SWH_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_RTL_SWH_NM;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_RTL_WH_CATG_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_RTL_WH_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_RTL_WH_NM;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_ST_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_WH_CD;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.NZZM0000E;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1010.NPAL1010CMsg;
import business.servlet.NPAL1010.common.NPAL1010CommonLogic;
import business.servlet.NPAL1010.constant.NPAL1010Constant;
import business.servlet.NPAL1010.constant.NPAL1010Constant.PARAMS_INDEX;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No888
 * ----------------------------------------------------------------------
 * 2012/06/26   Fujitsu         S.Noguchi       Create          N/A
 * 2012/09/13   Fujitsu         Y.Kamide        Update          N/A
 * 2013/05/14   Fujitsu         H.Mizutani      Update          N/A
 * 2015/04/28   CITS            H.Sugawara      Update          N/A
 * 2016/02/23   CSAI            D.Fukaya        Update          QC#2378
 * 03/18/2016   CSAI            Y.Imazu         Update          QC#3134
 * 04/21/2016   CSAI            D.Fukaya        Update          QC#2378
 * 05/04/2016   CSAI            D.Fukaya        Update          QC#7629
 *</pre>
 */
public class NPAL1010_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NPAL1010Constant.BUSINESS_ID);
        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;
        NPAL1010CommonLogic.addCheckItemSearchCondition(scrnMsg);

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;

            EZDBStringItem param1;
            EZDBStringItem param2;
            EZDBStringItem param3;
            EZDBStringItem param4;
            EZDBStringItem param5;
            EZDBStringItem param7;
            EZDBStringItem param8;
            EZDBStringItem param9;
            EZDBStringItem param10;
            EZDBStringItem param11;
            EZDBStringItem param12;
            EZDBStringItem param13;
            EZDBStringItem param14;
            EZDBStringItem param15;
            EZDBStringItem param16;
            EZDBStringItem param17;
            EZDBStringItem param18;
            EZDBStringItem param19;
            EZDBStringItem param20;
            EZDBStringItem param21;
            EZDBStringItem param22;

            // QC#10213
            EZDBStringItem param23;

            // Location Type
            String locRoleTp;

            // Location Code
            if (params.length > PARAMS_INDEX.LOCATION_CODE.getIndex()) {
                param1 = (EZDBStringItem) params[PARAMS_INDEX.LOCATION_CODE.getIndex()];
                if (ZYPCommonFunc.hasValue(param1)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt, param1);
                }
            }

            // Location Name
            if (params.length > PARAMS_INDEX.LOCATION_NAME.getIndex()) {
                param2 = (EZDBStringItem) params[PARAMS_INDEX.LOCATION_NAME.getIndex()];
                if (ZYPCommonFunc.hasValue(param2)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt, param2);
                }
            }

            // Location Type
            if (params.length > PARAMS_INDEX.LOC_ROLE_TP_CD_LIST.getIndex()) {
                param3 = (EZDBStringItem) params[PARAMS_INDEX.LOC_ROLE_TP_CD_LIST.getIndex()];
                if (ZYPCommonFunc.hasValue(param3)) {
                    locRoleTp = param3.getValue();

                    if (null != locRoleTp) {

                        List<String> locRoleTpCdList = Arrays.asList(locRoleTp.split(","));

                        for (int i = 0; i <= locRoleTpCdList.size() - 1; i++) {

                            if (i >= scrnMsg.locRoleTpCd_A2.length()) {

                                break;
                            }

                            scrnMsg.locRoleTpCd_A2.no(i).setValue(locRoleTpCdList.get(i));
                        }
                    }
                }
            }

            // Data Security Flag
            if (params.length > PARAMS_INDEX.DATA_SECURITY_FLAG.getIndex()) {

                param4 = (EZDBStringItem) params[PARAMS_INDEX.DATA_SECURITY_FLAG.getIndex()];

                if (ZYPCommonFunc.hasValue(param4)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkInpValFlg, param4);

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkInpValFlg, ZYPConstant.FLG_OFF_N);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkInpValFlg, ZYPConstant.FLG_OFF_N);
            }

            // Virtual Warehouse Flag
            if (params.length > PARAMS_INDEX.VIRTUAL_WH_FLAG.getIndex()) {

                param5 = (EZDBStringItem) params[PARAMS_INDEX.VIRTUAL_WH_FLAG.getIndex()];

                if (ZYPCommonFunc.hasValue(param5)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg, param5);

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg, ZYPConstant.FLG_ON_Y);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg, ZYPConstant.FLG_ON_Y);
            }

            // Retail WH Code
            if (params.length > PARAMS_INDEX.RETAIL_WH_CODE.getIndex()) {
                param7  = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_WH_CODE.getIndex()];
                if (ZYPCommonFunc.hasValue(param7)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_RW, param7);
                }
            }

            // Retail WH Name
            if (params.length > PARAMS_INDEX.RETAIL_WH_NAME.getIndex()) {
                param8  = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_WH_NAME.getIndex()];
                if (ZYPCommonFunc.hasValue(param8)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt_RW, param8);
                }
            }

            // SWH Code
            if (params.length > PARAMS_INDEX.RETAIL_SWH_CODE.getIndex()) {
                param9  = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_SWH_CODE.getIndex()];
                if (ZYPCommonFunc.hasValue(param9)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_SW, param9);
                }
            }

            // SWH Name
            if (params.length > PARAMS_INDEX.RETAIL_SWH_NAME.getIndex()) {
                param10  = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_SWH_NAME.getIndex()];
                if (ZYPCommonFunc.hasValue(param10)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNmSrchTxt_SW, param10);
                }
            }

            // Only RTL_WH Flag
            if (params.length > PARAMS_INDEX.ONLY_RETAIL_WH_FLG.getIndex()) {

                param11 = (EZDBStringItem) params[PARAMS_INDEX.ONLY_RETAIL_WH_FLG.getIndex()];

                if (ZYPCommonFunc.hasValue(param11)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox, param11);

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox, ZYPConstant.FLG_OFF_N);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox, ZYPConstant.FLG_OFF_N);
            }

            // Inventory Account
            if (params.length > PARAMS_INDEX.INVTY_ACCT_CD.getIndex()) {
                param12  = (EZDBStringItem) params[PARAMS_INDEX.INVTY_ACCT_CD.getIndex()];
                if (ZYPCommonFunc.hasValue(param12)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.invtyAcctCd, param12);
                }
            }

            // Inventory Owner
            if (params.length > PARAMS_INDEX.INVTY_OWNR_CD.getIndex()) {
                param13  = (EZDBStringItem) params[PARAMS_INDEX.INVTY_OWNR_CD.getIndex()];
                if (ZYPCommonFunc.hasValue(param13)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.invtyOwnrCd, param13);
                }
            }

            // WH Owner
            if (params.length > PARAMS_INDEX.WH_OWNR_CD.getIndex()) {
                param14  = (EZDBStringItem) params[PARAMS_INDEX.WH_OWNR_CD.getIndex()];
                if (ZYPCommonFunc.hasValue(param14)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.whOwnrCd, param14);
                }
            }

            // WH Category
            if (params.length > PARAMS_INDEX.RTL_WH_CATG_CD.getIndex()) {
                param15  = (EZDBStringItem) params[PARAMS_INDEX.RTL_WH_CATG_CD.getIndex()];
                if (ZYPCommonFunc.hasValue(param15)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCatgCd, param15);
                }
            }

            // Multiple Select Flag
            if (params.length > PARAMS_INDEX.MULT_SEL_FLG.getIndex()) {

                param16 = (EZDBStringItem) params[PARAMS_INDEX.MULT_SEL_FLG.getIndex()];

                if (ZYPCommonFunc.hasValue(param16)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_MS, param16);

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_MS, ZYPConstant.FLG_OFF_N);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelFlg_MS, ZYPConstant.FLG_OFF_N);
            }

            // WH Type Lock Flag
            if (params.length > PARAMS_INDEX.RETAIL_WH_CATG_LOCK_FLG.getIndex()) {

                param17 = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_WH_CATG_LOCK_FLG.getIndex()];

                if (ZYPCommonFunc.hasValue(param17)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_WT, param17);

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_WT, ZYPConstant.FLG_OFF_N);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_WT, ZYPConstant.FLG_OFF_N);
            }

            // Inventory Owner Lock Flag
            if (params.length > PARAMS_INDEX.INVENTORY_OWNER_LOCK_FLG.getIndex()) {

                param18 = (EZDBStringItem) params[PARAMS_INDEX.INVENTORY_OWNER_LOCK_FLG.getIndex()];

                if (ZYPCommonFunc.hasValue(param18)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_IO, param18);

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_IO, ZYPConstant.FLG_OFF_N);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_IO, ZYPConstant.FLG_OFF_N);
            }

            // WH Operation Lock Flag
            if (params.length > PARAMS_INDEX.WH_OWNER_LOCK_FLG.getIndex()) {

                param19 = (EZDBStringItem) params[PARAMS_INDEX.WH_OWNER_LOCK_FLG.getIndex()];

                if (ZYPCommonFunc.hasValue(param19)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_WO, param19);

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_WO, ZYPConstant.FLG_OFF_N);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_WO, ZYPConstant.FLG_OFF_N);
            }

            // Inventory Account Lock Flag
            if (params.length > PARAMS_INDEX.INVENTORY_ACCOUNT_LOCK_FLG.getIndex()) {

                param20 = (EZDBStringItem) params[PARAMS_INDEX.INVENTORY_ACCOUNT_LOCK_FLG.getIndex()];

                if (ZYPCommonFunc.hasValue(param20)) {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_IA, param20);

                } else {

                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_IA, ZYPConstant.FLG_OFF_N);
                }

            } else {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRopFlg_IA, ZYPConstant.FLG_OFF_N);
            }

            // WH Manager Code
            if (params.length > PARAMS_INDEX.WH_MGR_PSN_CD.getIndex()) {
                param21  = (EZDBStringItem) params[PARAMS_INDEX.WH_MGR_PSN_CD.getIndex()];
                if (ZYPCommonFunc.hasValue(param21)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.whMgrPsnCd_H1, param21);
                }
            }

            // WH Manager Name
            if (params.length > PARAMS_INDEX.FULL_PSN_NM.getIndex()) {
                param22  = (EZDBStringItem) params[PARAMS_INDEX.FULL_PSN_NM.getIndex()];
                if (ZYPCommonFunc.hasValue(param22)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_H1, param22);
                }
            }

            // QC#10213 Active Flg
            ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg, ZYPConstant.FLG_OFF_N);
            if (params.length > PARAMS_INDEX.ACTIVE_FLG.getIndex()) {
                if (params[PARAMS_INDEX.ACTIVE_FLG.getIndex()] instanceof EZDBStringItem) {
                    param23 = (EZDBStringItem) params[PARAMS_INDEX.ACTIVE_FLG.getIndex()];
                    if (ZYPCommonFunc.hasValue(param23)) {
                        if (ZYPConstant.FLG_ON_Y.equals(param23.getValue())) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                        }
                    }
                } else {
                    if (params[PARAMS_INDEX.ACTIVE_FLG.getIndex()] != null) {
                        String actFlg = params[PARAMS_INDEX.ACTIVE_FLG.getIndex()].toString();
                        if (ZYPConstant.FLG_ON_Y.equals(actFlg)) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.actvFlg, ZYPConstant.FLG_ON_Y);
                        }
                    }
                }
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;
        NPAL1010CMsg bizMsg = new NPAL1010CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH_ID);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;
        NPAL1010CMsg bizMsg = (NPAL1010CMsg) cMsg;
        // 10/13/2015 mod start
        // scrnMsg.setFocusItem(scrnMsg.invtyLocCd);
        scrnMsg.setFocusItem(scrnMsg.rtlWhCatgCd);
        // 10/13/2015 mod end

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NPAL1010CommonLogic.initScreenItem(this, scrnMsg);
        NPAL1010CommonLogic.setHeaderItemProtected(scrnMsg);
        NPAL1010CommonLogic.setListItemProtected(scrnMsg);
        NPAL1010CommonLogic.setScreenList(scrnMsg);

        if (ZYPCommonFunc.hasValue(bizMsg.getMessageCode()) && EZDMessageInfo.MSGTYPE_ERROR == bizMsg.getMessageInfo().getMessageType() && !NZZM0000E.equals(bizMsg.getMessageCode())) {

            NPAL1010CommonLogic.setCloseCond(this, scrnMsg);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) arg0;

        scrnMsg.xxRtrnInvtyLocSrchTxt.setNameForMessage(LABEL_WH_CD);
        // WDS R-OM025 Inventory Transaction Mizutani Start
        scrnMsg.locRoleTpCd.setNameForMessage(LABEL_LOC_ROLE_TP_CD);
        // WDS R-OM025 Inventory Transaction Mizutani End
        // START 2015/04/28 H.Sugawara E850 Warehouse Setup
        scrnMsg.rtlWhCatgCd.setNameForMessage(LABEL_RTL_WH_CATG_CD);
        // END 2015/04/28 H.Sugawara E850 Warehouse Setup
        scrnMsg.rtlWhNmSrchTxt.setNameForMessage(LABEL_LOC_NM);
        scrnMsg.addlLocNm.setNameForMessage(LABEL_ADDL_LOC_NM);
        scrnMsg.firstLineAddr.setNameForMessage(LABEL_ADDL_LOC_NM);
        scrnMsg.postCd.setNameForMessage(LABEL_POST_CD);
        scrnMsg.ctyAddr.setNameForMessage(LABEL_CTY_ADDR);
        scrnMsg.stCd.setNameForMessage(LABEL_ST_CD);
        scrnMsg.xxRtrnInvtyLocSrchTxt_RW.setNameForMessage(LABEL_RTL_WH_CD); // 10/12/2015 add
        scrnMsg.rtlWhNmSrchTxt_RW.setNameForMessage(LABEL_RTL_WH_NM); // 10/12/2015 add
        scrnMsg.xxRtrnInvtyLocSrchTxt_SW.setNameForMessage(LABEL_RTL_SWH_CD); // 10/12/2015 add
        scrnMsg.rtlWhNmSrchTxt_SW.setNameForMessage(LABEL_RTL_SWH_NM); // 10/12/2015 add
        scrnMsg.invtyAcctCd.setNameForMessage(LABEL_INVTY_ACCT_CD); // 10/28/2015 add
        scrnMsg.whMgrPsnCd_H1.setNameForMessage(LABEL_OWNR_CD);
        scrnMsg.fullPsnNm_H1.setNameForMessage(LABEL_OWNR_NM);
    }
}
