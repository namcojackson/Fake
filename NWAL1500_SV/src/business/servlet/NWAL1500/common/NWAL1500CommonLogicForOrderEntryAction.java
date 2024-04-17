/*
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NWAL1500.common;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.ATTACH_BUSINESS_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.ATTACH_DATA_ORD_NUM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID_THEREFORE_API;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.COMMA;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_0;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_1;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_2;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_3;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_4;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.NWAL1500_ATT_LIMIT;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.NWAL1500_AUTHORIZE_FILE_EXT;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.NWAL1500_AUTHORIZE_FILE_VOL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.NWAL1500_DOC_TP_CD_AUTH_LGSC;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.NWAL1500_DOC_TP_CD_AUTH_OTC;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.ON_CHANGE_ORD_ENTRY_ACTION_ADDL_INFO_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.ON_CHANGE_ORD_ENTRY_ACTION_DELY_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.ON_CHANGE_ORD_ENTRY_ACTION_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.ON_CHANGE_ORD_ENTRY_ACTION_TOOL_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.PERIOD;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CMPT_LINE;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CONFIG_ID;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CONF_LINE;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_LINE_CONFIG_TAB;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_MDL;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_ORD_NUM;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_RMA_TAB;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_SERIAL_NUM;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0136I;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0504E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0666E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0667E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0671E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0680E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0683E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0688E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0690E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0697E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0766E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0774E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0781E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0855E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0871E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0872E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0876E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0962E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM8474E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.ZZM9000E;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_BOOKED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CANCELLED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CLOSED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CREDIT_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_DI_CHECK_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_ENTERED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_PENDING;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_PROFIITABILITY_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_SUPPLY_ABUSE_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_VALIDATION_HOLD;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_CANCELLED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_LOGISTICS;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_PRICE_AUTH;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_PRICE_INBOUND;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_PRICE_OUTBOUND;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_QTY_AUTH;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.MOD_WF_ITEMS_AUTH;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.RGTN_AUTH;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.NWAL1500BMsg;
import business.servlet.NWAL1500.NWAL1500_ABMsg;
import business.servlet.NWAL1500.NWAL1500_BBMsg;
import business.servlet.NWAL1500.NWAL1500_CBMsg;
import business.servlet.NWAL1500.NWAL1500_DBMsg;
import business.servlet.NWAL1500.constant.NWAL1500Constant;
import business.servlet.NWAL1500.constant.NWAL1500MsgConstant;
import business.servlet.NWAL1540.constant.NWAL1540Constant;
import business.servlet.NWAL1660.constant.NWAL1660Constant;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500CommonLogicForOrderEntryAction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         Y.Taoka         Create          S21_NA#1694
 * 2016/02/26   Fujitsu         M.Suzuki        Update          S21_NA#966
 * 2016/02/28   Fujitsu         K.Sato          Update          S21_NA#1738
 * 2016/03/02   Fujitsu         K.Sato          Update          S21_NA#4377
 * 2016/03/30   Fujitsu         S.Takami        Update          S21_NA#5523
 * 2016/04/01   Fujitsu         S.Takami        Update          S21_NA#5523-2
 * 2016/04/05   Fujitsu         T.Ishii         Update          S21_NA#6306
 * 2016/05/19   Fujitsu         T.Murai         Update          S21_NA#5350
 * 2016/05/25   Fujitsu         T.Murai         Update          S21_NA#7337
 * 2016/06/06   Fujitsu         T.Murai         Update          S21_NA#6442
 * 2016/06/21   Fujitsu         S.Takami        Update          S21_NA#9849-2
 * 2016/07/05   Fujitsu         M.Hara          Update          S21_NA#7441
 * 2016/07/29   Fujitsu         T.Mizuki        Update          S21_NA#12607
 * 2016/08/02   Fujitsu         Y.Taoka         Update          S21_NA#11700
 * 2016/08/24   Fujitsu         Y.Taoka         Update          S21_NA#11630
 * 2016/09/20   Fujitsu         S.Takami        Update          S21_NA#8279
 * 2016/09/21   Fujitsu         S.Iidaka        Update          UnitTest#201
 * 2016/10/13   Fujitsu         N.Sugiura       Update          S21_NA#7700
 * 2016/10/19   Fujitsu         N.Sugiura       Update          S21_NA#14728
 * 2016/10/21   Fujitsu         S.Takami        Update          S21_NA#15472
 * 2017/02/16   Fujitsu         T.Yoshida       Update          S21_NA#17530
 * 2017/02/28   Fujitsu         K.Ishizuka      Update          S21_NA#17851
 * 2017/06/28   Fujitsu         A.Kosai         Update          QC#18138
 * 2017/08/07   Fujitsu         T.Ogura         Update          Sol#249
 * 2017/09/12   Fujitsu         S.Ohki          Update          S21_NA#21016
 * 2017/10/13   Fujitsu         S.Takami        Update          S21_NA#21267
 * 2017/10/19   Fujitsu         S.Takami        Update          S21_NA#21122
 * 2017/11/07   Fujitsu         S.Takami        Update          S21_NA#22351
 * 2018/02/23   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/03/16   Fujitsu         M.Ohno          Update          S21_NA#22805
 * 2018/03/20   Fujitsu         A.Kosai         Update          S21_NA#24853
 * 2018/04/10   Fujitsu         N.Sugiura       Update          S21_NA#22167
 * 2018/04/11   Fujitsu         Y.Kanefusa      Update          S21_NA#22965
 * 2018/05/10   Fujitsu         S.Takami        Update          S21_NA#25251
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#26681
 * 2018/06/14   Fujitsu         A.Kosai         Update          S21_NA#25227
 * 2018/07/03   Fujitsu         A.Kosai         Update          S21_NA#26908
 * 2018/07/03   Fujitsu         H.Kumagai       Update          QC#26932
 * 2018/07/10   Fujitsu         T.Noguchi       Update          S21_NA#26661,26713
 * 2018/07/10   Fujitsu         Mz.Takahashi    Update          S21_NA#26188 
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/08/08   Fujitsu         M.Ishii         Update          QC#26551
 * 2019/05/09   Fujitsu         R.Nakamura      Update          S21_NA#50015
 * 2019/05/23   Fujitsu         Mz.Takahashi    Update          QC#50043
 * 2019/07/05   Fujitsu         S.Takami        Update          S21_NA#51151
 * 2019/07/11   Fujitsu         T.Noguchi       Update          S21_NA#51287
 * 2019/07/18   Fujitsu         K.Kato          Update          S21_NA#51327
 * 2023/06/06   Hitachi         T.Doi           Update          CSA-QC#61465
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1500CommonLogicForOrderEntryAction {

    /**
     * checkInput
     * @param handler S21CommonHandler
     * @param ctx EZDApplicationContext
     * @param bMsg EZDBMsg
     */
    public static void checkInput(S21CommonHandler handler, EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String eventNm = ctx.getEventName();
        String ordEntryActCd = "0";
        String ordEntryActNm = "";
        EZDBStringItem ezdbItem = null;

        if (ON_CHANGE_ORD_ENTRY_ACTION_EVENT_NM.equals(eventNm)) {
            ordEntryActCd = scrnMsg.ordEntryActCd_AC.getValue();
            ezdbItem = scrnMsg.ordEntryActCd_AC;

        } else if (ON_CHANGE_ORD_ENTRY_ACTION_ADDL_INFO_EVENT_NM.equals(eventNm)) {
            ordEntryActCd = scrnMsg.ordEntryActCd_IF.getValue();
            ezdbItem = scrnMsg.ordEntryActCd_IF;

        } else if (ON_CHANGE_ORD_ENTRY_ACTION_DELY_EVENT_NM.equals(eventNm)) {
            ordEntryActCd = scrnMsg.ordEntryActCd_DL.getValue();
            ezdbItem = scrnMsg.ordEntryActCd_DL;

        } else if (ON_CHANGE_ORD_ENTRY_ACTION_TOOL_EVENT_NM.equals(eventNm)) {
            ordEntryActCd = scrnMsg.ordEntryActCd_TO.getValue();
            ezdbItem = scrnMsg.ordEntryActCd_TO;

        } else {
            resetAction(scrnMsg);
            throw new EZDFieldErrorException();
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.ordEntryActCd, ordEntryActCd);
        resetAction(scrnMsg);

        // Check Event
        if (!checkEvent(scrnMsg)) {
            ordEntryActNm = getOrdEntryActNm(scrnMsg, ordEntryActCd);
            ezdbItem.setErrorInfo(1, NWAM0774E, new String[] {ordEntryActNm});
        }
        scrnMsg.addCheckItem(ezdbItem);
        scrnMsg.putErrorScreen();

        if (ORD_ENTRY_ACT.CANCEL.equals(ordEntryActCd)) {
            // Order Cancel
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.ORDER_COPY.equals(ordEntryActCd)) {
            // Order Copy
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
                scrnMsg.cpoOrdNum.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ORD_NUM });
                scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
                scrnMsg.putErrorScreen();
            }

            // ADD 2016/08/02 CSA S21_NA#12607
            if (containCreditRebillLine(scrnMsg)) {
                scrnMsg.setMessageInfo(NWAM0871E, null);
                throw new EZDFieldErrorException();
            }

        } else if (ORD_ENTRY_ACT.HOLDS.equals(ordEntryActCd)) {
            // Hold
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.CHANGE_ORDER_MODIFICATION.equals(ordEntryActCd)) {
            // Change Order Modification
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
                scrnMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0014E);
                scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
            }
            scrnMsg.putErrorScreen();

        } else if (ORD_ENTRY_ACT.ATTACHMENT.equals(ordEntryActCd)) {
            // 2016/02/26 S21_NA#966 Mod Start-----------
            // Attach
            //scrnMsg.setMessageInfo("ZZXM0001E", new String[] {"This event is not available." });
            //throw new EZDFieldErrorException();
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
                scrnMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0014E);
                scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
            }
            scrnMsg.putErrorScreen();
            // 2016/02/26 S21_NA#966 Mod End-----------

        } else if (ORD_ENTRY_ACT.PRICING.equals(ordEntryActCd)) {
            // Pricing($)
            // do nothing
            return;

        // 2018/05/11 QC#22139 Add Start
        } else if (ORD_ENTRY_ACT.ORDER_CONFIRMATION.equals(ordEntryActCd)) {
            // Order Confirmation
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
                scrnMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0014E);
                scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
            }
            scrnMsg.putErrorScreen();

        } else if (ORD_ENTRY_ACT.RETURN_AUTHORIZATION.equals(ordEntryActCd)) {
            // Return Authorization
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
                scrnMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0014E);
                scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
            }
            scrnMsg.putErrorScreen();
        // 2018/05/11 QC#22139 Add End

        } else if (ORD_ENTRY_ACT.DELIVERY_OR_INVOICE.equals(ordEntryActCd) || ORD_ENTRY_ACT.DELIVERY_OR_INVOICE_2.equals(ordEntryActCd)) {
            // Delivery or Invoice(Shipping Detail)
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd)) {
            // Special Instruction
            // do nothing
            return;
        // 2018/07/27 S21_NA#14307 Add Start
        } else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS_2.equals(ordEntryActCd)) {
            // Special Instruction(Line Config, RMA)
            List<Integer> selectRows = null;
            int confValidCnt = 0;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
                confValidCnt = scrnMsg.A.getValidCount();
            } else {
                selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
                confValidCnt = scrnMsg.C.getValidCount();
            }

            if (selectRows.size() == 0 && confValidCnt != 1) {
                scrnMsg.setMessageInfo(NWAM0697E, new String[] {MSG_PARAM_CONF_LINE });
                throw new EZDFieldErrorException();
            }
            if (selectRows.size() > 1) {
                scrnMsg.setMessageInfo(NWAM0683E, null);
                throw new EZDFieldErrorException();
            }

            return;
        // 2018/07/27 S21_NA#14307 Add End
        } else if (ORD_ENTRY_ACT.VIEW_WORKFLOW.equals(ordEntryActCd)) {
            // Work Flow
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
                scrnMsg.cpoOrdNum.setErrorInfo(1, ZZM9000E, new String[] {MSG_PARAM_ORD_NUM });
                scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
                scrnMsg.putErrorScreen();
            }

        } else if (ORD_ENTRY_ACT.EDI_ATTRIBUTES.equals(ordEntryActCd) || ORD_ENTRY_ACT.EDI_ATTRIBUTES_2.equals(ordEntryActCd)) {
            // 2018/07/03 S21_NA#26908 Mod Start
//            // TODO
//            ordEntryActNm = getOrdEntryActNm(scrnMsg, ordEntryActCd);
//            ezdbItem.setErrorInfo(1, NWAM0774E, new String[]{ordEntryActNm});
//            scrnMsg.addCheckItem(ezdbItem);
//            scrnMsg.putErrorScreen();
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoSrcTpCd) || !CPO_SRC_TP.EDI.equals(scrnMsg.cpoSrcTpCd.getValue())) {
                ordEntryActNm = getOrdEntryActNm(scrnMsg, ordEntryActCd);
                ezdbItem.setErrorInfo(1, NWAM0774E, new String[]{ordEntryActNm});
                scrnMsg.addCheckItem(ezdbItem);
                scrnMsg.putErrorScreen();
            }

            String dplyTab = scrnMsg.xxDplyTab.getValue();

            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

                if (checkListItemLine.size() > 1) {
                    scrnMsg.setMessageInfo(NWAM0666E, new String[] {"Component Line" });
                    throw new EZDFieldErrorException();
                }
            }
            // 2018/07/03 S21_NA#26908 Mod End

        } else if (ORD_ENTRY_ACT.VIEW_CHANGE_LOG.equals(ordEntryActCd) || ORD_ENTRY_ACT.VIEW_CHANGE_LOG_2.equals(ordEntryActCd)) {
            String dplyTab = scrnMsg.xxDplyTab.getValue();

            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                List<Integer> checkListItemConfig = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
                List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

                if (checkListItemConfig.size() > 0 && checkListItemLine.size() > 0) {
                    scrnMsg.setMessageInfo(NWAM0688E, null);
                    throw new EZDFieldErrorException();
                }
                // ADD START S21_NA QC#17851
                if (checkListItemConfig.size() > 0 ){
                    for (int i : checkListItemConfig){
                        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).dsCpoConfigPk_LC)){
                            scrnMsg.setMessageInfo(NWAM0680E, null);
                            throw new EZDFieldErrorException();
                        }
                    }
                }
                // ADD END S21_NA QC#17851
            } else if (TAB_RMA.equals(dplyTab)) {
                List<Integer> checkListRMAConfig = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
                List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

                if (checkListRMAConfig.size() > 0 && checkListRMALine.size() > 0) {
                    scrnMsg.setMessageInfo(NWAM0688E, null);
                    throw new EZDFieldErrorException();
                }
                // ADD START S21_NA QC#17851
                if (checkListRMAConfig.size() > 0 ){
                    for (int i : checkListRMAConfig){
                        if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).dsCpoConfigPk_RC)){
                            scrnMsg.setMessageInfo(NWAM0680E, null);
                            throw new EZDFieldErrorException();
                        }
                    }
                }
                // ADD END S21_NA QC#17851
            }

        } else if (ORD_ENTRY_ACT.ORDER_SUMMARY.equals(ordEntryActCd)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum)) {
                scrnMsg.cpoOrdNum.setErrorInfo(1, NWAL1500MsgConstant.NWAM0014E);
                scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
            }
            scrnMsg.putErrorScreen();

        } else if (ORD_ENTRY_ACT.SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.REP_SALES_CREDIT.equals(ordEntryActCd)) {
            String dplyTab = scrnMsg.xxDplyTab.getValue();
            List<Integer> checkList = null;

            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            } else if (TAB_RMA.equals(dplyTab)) {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            }

            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            // if (checkList != null) {
            //     if (checkList.size() == 1) {
            //         scrnMsg.xxCellIdx.setValue(checkList.get(0));
            //     } else if (checkList.size() == 0) {

            //         scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CONF_LINE });
            //         throw new EZDFieldErrorException();
            //     } else if (checkList.size() > 1) {
            //         scrnMsg.setMessageInfo(NWAM0666E, new String[] {MSG_PARAM_CONF_LINE });
            //         throw new EZDFieldErrorException();
            //     }
            // }
            if (checkList != null && checkList.size() > 0) {
                scrnMsg.xxCellIdx.setValue(checkList.get(0));
            } else {
                scrnMsg.xxCellIdx.clear();
            }

        } else if (ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT_2.equals(ordEntryActCd)) {
            return;
         // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]

        // 2018/07/20 S21_NA#26188 Mod Start
        } else if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_MASS_APPLY.equals(ordEntryActCd)) {
        //} else if (ORD_ENTRY_ACT.D_I_S_C.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_2.equals(ordEntryActCd)) {
        // 2018/07/20 S21_NA#26188 Mod End
            // Delivery Info
            return;

        } else if (ORD_ENTRY_ACT.PROFITABILITY_QA.equals(ordEntryActCd) || ORD_ENTRY_ACT.PROFITABILITY_QA_2.equals(ordEntryActCd)) {
            // Profitability
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.DATA_INTEGRITY_DI.equals(ordEntryActCd) || ORD_ENTRY_ACT.DATA_INTEGRITY_DI_2.equals(ordEntryActCd)) {
            // DI Check
            // do nothing
            return;

        // 2018/07/20 S21_NA#26188 Del Start
        //} else if (ORD_ENTRY_ACT.SERVICE_PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_2.equals(ordEntryActCd)) {
        //    // Service Pricing
        //    // S21_NA#13769(S21_NA#8388) ADD START
        //    if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.dclnSvcCd.getValue())) {
        //        ezdbItem.setErrorInfo(1, NWAM0876E);
        //        scrnMsg.addCheckItem(ezdbItem);
        //        scrnMsg.putErrorScreen();
        //    }
        //    // S21_NA#13769(S21_NA#8388) ADD
        //    return;
        // 2018/07/20 S21_NA#26188 Del End

        } else if (ORD_ENTRY_ACT.SERVICE_PRICING_NEW.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_NEW_2.equals(ordEntryActCd)) { // Add S21_NA#17530
            // Service Pricing(New)
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.dclnSvcCd.getValue())) {
                ezdbItem.setErrorInfo(1, NWAM0876E);
                scrnMsg.addCheckItem(ezdbItem);
                scrnMsg.putErrorScreen();
            }
            return;

        } else if (ORD_ENTRY_ACT.CANCEL_2.equals(ordEntryActCd)) {
            // Line Cancel
            // ADD START 2016/03/03 #2176
            List<Integer> checkList = null;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(checkLine);
                            String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
                        
                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
                        }

                        // S21_NA#11630
                        if (isCreditRebillLine(lineMsg)) {
                            // Credit or Rebill Line
                            lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0872E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
                        }
                    }
                    scrnMsg.putErrorScreen();
                }
            } else {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                        NWAL1500_DBMsg lineMsg = scrnMsg.D.no(checkLine);
                        String lineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();

                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_RL);
                        }

                        // S21_NA#11630
                        if (isCreditRebillLine(lineMsg)) {
                            // Credit or Rebill Line
                            lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0872E);
                            scrnMsg.addCheckItem(lineMsg.crRebilCd_RL);
                        }
                    }
                    scrnMsg.putErrorScreen();
                }
            }
            // ADD END 2016/03/03 #2176
        } else if (ORD_ENTRY_ACT.DELETE.equals(ordEntryActCd)) {
            // Line Delete
            // ADD START 2016/03/03 #2176
            List<Integer> checkList = null;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                // Line
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                        NWAL1500_BBMsg lineMsg = scrnMsg.B.no(checkLine);
                        String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();

                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
                        }
                        // S21_NA#11700
                        if (isCreditRebillLine(lineMsg)) {
                            // Credit or Rebill Line
                            lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0872E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
                        }
                    }
                }
                // S21_NA#11700
                // Config
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
                for (int checkLine : checkList) {
                    NWAL1500_ABMsg configMsg = scrnMsg.A.no(checkLine);
                    for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                        NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
                        if (configMsg.dsOrdPosnNum_LC.getValue().equals(lineMsg.dsOrdPosnNum_LL.getValue())) {
                            if (isCreditRebillLine(lineMsg)) {
                                // Credit or Rebill Line
                                configMsg.xxChkBox_LC.setErrorInfo(1, NWAM0872E);
                                scrnMsg.addCheckItem(configMsg.xxChkBox_LC);
                            }
                        }
                    }                   
                }
                scrnMsg.putErrorScreen();

            } else {
                // Line
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                        NWAL1500_DBMsg lineMsg = scrnMsg.D.no(checkLine);
                        String lineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();

                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_RL);
                        }
                        // S21_NA#11700
                        if (isCreditRebillLine(lineMsg)) {
                            // Credit or Rebill Line
                            lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0872E);
                            scrnMsg.addCheckItem(lineMsg.crRebilCd_RL);
                        }
                    }
                }
                // S21_NA#11700
                // Config
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
                for (int checkLine : checkList) {
                    NWAL1500_CBMsg configMsg = scrnMsg.C.no(checkLine);
                    for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                        NWAL1500_DBMsg lineMsg = scrnMsg.D.no(i);
                        if (configMsg.dsOrdPosnNum_RC.getValue().equals(lineMsg.dsOrdPosnNum_RL.getValue())) {
                            if (isCreditRebillLine(lineMsg)) {
                                // Credit or Rebill Line
                                configMsg.dsOrdPosnNum_RC.setErrorInfo(1, NWAM0872E);
                                scrnMsg.addCheckItem(configMsg.dsOrdPosnNum_RC);
                            }
                        }
                    }                   
                }
                scrnMsg.putErrorScreen();
            }
            // ADD END 2016/03/03 #2176

        } else if (ORD_ENTRY_ACT.ORDER_LINE_COPY.equals(ordEntryActCd)) {
            // Line Copy
            List<Integer> slctConfList = null;
            List<Integer> slctLineList = null;
            scrnMsg.xxCellIdx.clear();
            scrnMsg.xxCellIdx_BB.clear();

            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                slctConfList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
                slctLineList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
            } else {
                slctConfList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
                slctLineList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
            }

            if (slctConfList.size() == 0 && slctLineList.size() == 0) {
                scrnMsg.setMessageInfo(NWAM0504E, null);
                throw new EZDFieldErrorException();
            } else if (slctConfList.size() > 0) {
                if (slctLineList.size() > 0 || slctConfList.size() != 1) {
                    scrnMsg.setMessageInfo(NWAM0683E, null);
                    throw new EZDFieldErrorException();
                }
            } else if (slctLineList.size() != 1) {
                scrnMsg.setMessageInfo(NWAM0683E, null);
                throw new EZDFieldErrorException();
            }

            // mod start 2016/07/29 CSA S21_NA#12607
            // check Credit Rebill Order
            if (slctConfList.size() > 0) {
                //Selected Config
                scrnMsg.xxCellIdx.setValue(slctConfList.get(0)); // Config Mode
                int slctConfNo = slctConfList.get(0);

                if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {

                    // [Line Config Tab] Target Config Line
                    NWAL1500_ABMsg  configLine = scrnMsg.A.no(slctConfNo);
                    for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                        NWAL1500_BBMsg DtlLine = scrnMsg.B.no(i);
                        if (configLine.dsOrdPosnNum_LC.getValue().equals(DtlLine.dsOrdPosnNum_LL.getValue())) {
                            if (isCreditRebillLine(DtlLine)) {
                                scrnMsg.setMessageInfo(NWAM0871E, null);
                                throw new EZDFieldErrorException();
                            }
                        }
                    }
                } else {

                    // [RMA Tab] Target Config Line
                    NWAL1500_CBMsg  configLine = scrnMsg.C.no(slctConfNo);
                    for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                        NWAL1500_DBMsg DtlLine = scrnMsg.D.no(i);
                        if (configLine.dsOrdPosnNum_RC.getValue().equals(DtlLine.dsOrdPosnNum_RL.getValue())) {
                            if (isCreditRebillLine(DtlLine)) {
                                scrnMsg.setMessageInfo(NWAM0871E, null);
                                throw new EZDFieldErrorException();
                            }
                        }
                    }
                }
            }
            if (slctLineList.size() > 0) {
                // Selected Line
                scrnMsg.xxCellIdx_BB.setValue(slctLineList.get(0)); // Line Mode
                int slctLineNo = slctLineList.get(0);

                if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {

                    if (isCreditRebillLine(scrnMsg.B.no(slctLineNo))) {
                        scrnMsg.setMessageInfo(NWAM0871E, null);
                        throw new EZDFieldErrorException();
                    }
                } else {
                    if (isCreditRebillLine(scrnMsg.D.no(slctLineNo))) {
                        scrnMsg.setMessageInfo(NWAM0871E, null);
                        throw new EZDFieldErrorException();
                    }
                }
                // mod end 2016/07/29 CSA S21_NA#12607
            }
        } else if (ORD_ENTRY_ACT.HOLDS_2.equals(ordEntryActCd)) {
            // Hold
            // ADD START 2016/03/03 #2176
            List<Integer> checkList = null;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(checkLine);
                            String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
                        
                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
                        }
                    }
                    scrnMsg.putErrorScreen();
                }
            } else {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                            NWAL1500_DBMsg lineMsg = scrnMsg.D.no(checkLine);
                            String lineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();
                        
                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_RL);
                        }
                    }
                    scrnMsg.putErrorScreen();
                }
            }
            // ADD END 2016/03/03 #2176
        } else if (ORD_ENTRY_ACT.PRICING_2.equals(ordEntryActCd)) {
            // Pricing
            // ADD START 2016/03/03 #2176
            List<Integer> checkList = null;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(checkLine);
                            String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
                        
                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
                        }
                    }
                    scrnMsg.putErrorScreen();
                }
            } else {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                            NWAL1500_DBMsg lineMsg = scrnMsg.D.no(checkLine);
                            String lineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();
                        
                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_RL);
                        }
                    }
                    scrnMsg.putErrorScreen();
                }
            }
            // ADD END 2016/03/03 #2176
        } else if (ORD_ENTRY_ACT.SUPPLY_AUTO_ADD.equals(ordEntryActCd)) {
            // Auto Add Supply
            List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);

            if (checkList.size() == 1) {
                int slctConfNum = checkList.get(0);
                scrnMsg.xxCellIdx.setValue(slctConfNum);
                EZDBStringItem mdlDescTxt = scrnMsg.A.no(slctConfNum).mdlDescTxt_LC;

                if (!ZYPCommonFunc.hasValue(mdlDescTxt)) {
                    mdlDescTxt.setErrorInfo(1, NWAM0671E, new String[] {MSG_PARAM_MDL });
                    scrnMsg.addCheckItem(mdlDescTxt);
                    scrnMsg.putErrorScreen();
                }
//            } else if (checkList.size() == 0) {  2016/03/04 S21_NA#1866 Del Start
//                scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CONF_LINE });
//                throw new EZDFieldErrorException();
//            } else if (checkList.size() > 1) {
//                scrnMsg.setMessageInfo(NWAM0666E, new String[] {MSG_PARAM_CONF_LINE });
//                throw new EZDFieldErrorException();  2016/03/04 S21_NA#1866 Del End
            }

        } else if (ORD_ENTRY_ACT.MASS_UPDATE.equals(ordEntryActCd)) {
            // 2019/07/05 S21_NA#51151 Del Start
//            // Mass Update
//            String dplyTab = scrnMsg.xxDplyTab.getValue();
//
//            if (TAB_LINE_CONFIG.equals(dplyTab)) {
//                List<Integer> checkListItemConfig = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
//                List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
//                if (checkListItemConfig.size() == 0 && checkListItemLine.size() == 0) {
//                    scrnMsg.setMessageInfo(NWAM0504E, null);
//                    throw new EZDFieldErrorException();
//                }
//                if (checkListItemConfig.size() > 0 && checkListItemLine.size() > 0) {
//                    scrnMsg.setMessageInfo(NWAM0688E, null);
//                    throw new EZDFieldErrorException();
//                }
//                // ADD START 2016/02/12 #1864
//                if (checkListItemConfig.size() == 1 || checkListItemLine.size() == 1) {
//                    scrnMsg.setMessageInfo(NWAM0766E, null);
//                    throw new EZDFieldErrorException();
//                }
//                // ADD END 2016/02/12 #1864
//                // ADD START 2016/03/03 #2176
//                for(int checkLine : checkListItemLine){
//                    NWAL1500_BBMsg lineMsg = scrnMsg.B.no(checkLine);
//                    String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
//                    if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
//                        lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0781E);
//                        scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
//                    }
//                }
//
//                // S21_NA#11700
//                // check Credit Rebill Order
//                //// Config
//                for(int checkLine : checkListItemConfig){
//                    NWAL1500_ABMsg configMsg = scrnMsg.A.no(checkLine);
//                    for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
//                        NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
//                        if (isCreditRebillLine(lineMsg)){
//                            configMsg.xxChkBox_LC.setErrorInfo(1, NWAM0872E);
//                            scrnMsg.addCheckItem(configMsg.xxChkBox_LC);
//                        }
//                    }
//
//                }
//                //// Line
//                for(int checkLine : checkListItemLine){
//                    NWAL1500_BBMsg lineMsg = scrnMsg.B.no(checkLine);
//                    if (isCreditRebillLine(lineMsg)){
//                        lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0872E);
//                        scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
//                    }
//                }
//
//                scrnMsg.putErrorScreen();
//                // ADD END 2016/03/03 #2176
//            } else if (TAB_RMA.equals(dplyTab)) {
//                List<Integer> checkListRMAConfig = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
//                List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
//                if (checkListRMAConfig.size() == 0 && checkListRMALine.size() == 0) {
//                    scrnMsg.setMessageInfo(NWAM0504E, null);
//                    throw new EZDFieldErrorException();
//                }
//                if (checkListRMAConfig.size() > 0 && checkListRMALine.size() > 0) {
//                    scrnMsg.setMessageInfo(NWAM0688E, null);
//                    throw new EZDFieldErrorException();
//                }
//                // ADD START 2016/02/12 #1864
//                if (checkListRMAConfig.size() == 1 || checkListRMALine.size() == 1) {
//                    scrnMsg.setMessageInfo(NWAM0766E, null);
//                    throw new EZDFieldErrorException();
//                }
//                // ADD END 2016/02/12 #1864
//                // ADD START 2016/03/03 #2176
//                for(int checkLine : checkListRMALine){
//                    NWAL1500_DBMsg lineMsg = scrnMsg.D.no(checkLine);
//                    String lineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();
//                    if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
//                        lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0781E);
//                        scrnMsg.addCheckItem(lineMsg.xxChkBox_RL);
//                    }
//                    if(ZYPCommonFunc.hasValue(lineMsg.dsCpoLineSubNum_RL)){
//                        lineMsg.xxChkBox_RL.setErrorInfo(1, NWAL1500MsgConstant.NWAM0783E, new String[]{NWAL1500MsgConstant.MSG_PARAM_SET_COMPONENT});
//                        scrnMsg.addCheckItem(lineMsg.xxChkBox_RL);
//                    }
//                }
//                scrnMsg.putErrorScreen();
//                // ADD END 2016/03/03 #2176
//            }
            // 2019/07/05 S21_NA#51151 Del End
        } else if (ORD_ENTRY_ACT.ALL_COLLAPSE.equals(ordEntryActCd)) {
            // All Collapse
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.EXPAND_ALL.equals(ordEntryActCd)) {
            // Expand All
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.OUTBOUND_CONFIG_SELECT_ALL.equals(ordEntryActCd)) {
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.LINES_SELECT_ALL.equals(ordEntryActCd)) {
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.OUTBOUND_CONFIG_UNSELECT_ALL.equals(ordEntryActCd)) {
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.LINES_UNSELECT_ALL.equals(ordEntryActCd)) {
            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.COPY_FROM.equals(ordEntryActCd)) {
            
            String dplyTab = scrnMsg.xxDplyTab.getValue();
            List<Integer> checkListLine = new ArrayList<Integer>();
            List<Integer> checkListRMA = new ArrayList<Integer>();

            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                checkListLine = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            } else if (TAB_RMA.equals(dplyTab)) {
                checkListRMA = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            }

            if (checkListLine.size() > 0 && checkListRMA.size() > 0) {
                scrnMsg.setMessageInfo(NWAM0690E, new String[] {MSG_PARAM_LINE_CONFIG_TAB, MSG_PARAM_RMA_TAB});
                throw new EZDFieldErrorException();
            }
            
        } else if (ORD_ENTRY_ACT.RMA_AUTO_ADD.equals(ordEntryActCd)) {
            // Auto Add RMA
            List<Integer> checkConfList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkLineList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

            // S21_NA#5350 Del Start
            // if (checkConfList.size() + checkLineList.size() == 0) {
            // scrnMsg.setMessageInfo(NWAM0504E);
            // throw new EZDFieldErrorException();
            // }
            // S21_NA#5350 Del End

            if (checkConfList.size() > 0) {
                for (int checkConf : checkConfList) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(checkConf).svcConfigMstrPk_RC)) {
                        scrnMsg.C.no(checkConf).svcConfigMstrPk_RC.setErrorInfo(1, NWAM0671E, new String[] {MSG_PARAM_CONFIG_ID });
                    }
                    scrnMsg.addCheckItem(scrnMsg.C.no(checkConf).svcConfigMstrPk_RC);
                }
            }
            if (checkLineList.size() > 0) {
                for (int checkLine : checkLineList) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(checkLine).serNum_RL)) {
                        scrnMsg.D.no(checkLine).serNum_RL.setErrorInfo(1, NWAM0671E, new String[] {MSG_PARAM_SERIAL_NUM });
                    }
                    scrnMsg.addCheckItem(scrnMsg.D.no(checkLine).serNum_RL);
                }
            }

            scrnMsg.putErrorScreen();
            
        } else if (ORD_ENTRY_ACT.BUYOUT.equals(ordEntryActCd)) {
            // Buyout
            List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            if (checkList.size() == 1) {
                scrnMsg.xxCellIdx.setValue(checkList.get(0));
            } else {
                if (checkList.size() == 0) {
                    scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CMPT_LINE });
                } else if (checkList.size() > 1) {
                    scrnMsg.setMessageInfo(NWAM0666E, new String[] {MSG_PARAM_CMPT_LINE });
                }
                throw new EZDFieldErrorException();
            }

        } else if (ORD_ENTRY_ACT.ADDITIONAL_LINE_INFORMATION.equals(ordEntryActCd)) {
            // Add Line
            String dplyTab = scrnMsg.xxDplyTab.getValue();
            List<Integer> checkList = null;

            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
            } else {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
            }

            if (checkList.size() == 1) {
                scrnMsg.xxCellIdx.setValue(checkList.get(0));
            } else if (checkList.size() == 0) {
                scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CMPT_LINE });
                throw new EZDFieldErrorException();
            } else if (checkList.size() > 1) {
                scrnMsg.setMessageInfo(NWAM0666E, new String[] {MSG_PARAM_CMPT_LINE });
                throw new EZDFieldErrorException();
            }

        } else if (ORD_ENTRY_ACT.CONTROL_FIELDS.equals(ordEntryActCd)) {
            // Control Field
            List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            if (checkList.size() == 1) {
                scrnMsg.xxCellIdx.setValue(checkList.get(0));
            } else {
                if (checkList.size() == 0) {
                    scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CMPT_LINE });
                } else if (checkList.size() > 1) {
                    scrnMsg.setMessageInfo(NWAM0666E, new String[] {MSG_PARAM_CMPT_LINE });
                }
                throw new EZDFieldErrorException();
            }

        } else if (ORD_ENTRY_ACT.WH_INVENTORY_INQUIRY.equals(ordEntryActCd)) {
            // 2018/03/05 S21_NA#19808 Del Start
//            // Inventory Inquiry
//            List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
//            int datLength = scrnMsg.M.getValidCount();
//
//            if (checkList.size() == 0) {
//                scrnMsg.setMessageInfo(NWAM0667E, new String[] {MSG_PARAM_CMPT_LINE });
//                throw new EZDFieldErrorException();
//            }
//
//            for (int slctLineNum : checkList) {
//                NWAL1500_BBMsg lineMsg = scrnMsg.B.no(slctLineNum);
//                String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
//                scrnMsg.addCheckItem(lineMsg.mdseCd_LL);
//                scrnMsg.addCheckItem(lineMsg.ordCustUomQty_LL);
//                // ADD START 2016/03/03 #2176
//                if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
//                    lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0781E);
//                    scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
//                }
//                // ADD END 2016/03/03 #2176
//            }
//            scrnMsg.putErrorScreen();
            // do nothing
            // 2018/03/05 S21_NA#19808 Del End
            return;
        } else if (ORD_ENTRY_ACT.PRICE_CHANGE.equals(ordEntryActCd)) {
            // Price Change
            // 2018/08/08 QC#26551 Add Start

            if (S21StringUtil.isEquals(TAB_LINE_CONFIG, scrnMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).entCpoDtlDealSlsAmt_LL)) {
                        scrnMsg.ordEntryActCd_IF.setErrorInfo(1, NWAM0962E);
                        scrnMsg.addCheckItem(scrnMsg.ordEntryActCd_IF);
                    }
                }
            }
            if (S21StringUtil.isEquals(TAB_RMA, scrnMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(i).entCpoDtlDealSlsAmt_RL)) {
                        scrnMsg.ordEntryActCd_IF.setErrorInfo(1, NWAM0962E);
                        scrnMsg.addCheckItem(scrnMsg.ordEntryActCd_IF);
                    }
                }
            }
            scrnMsg.putErrorScreen();
            // 2018/08/08 QC#26551 Add End
            return;

        } else if (ORD_ENTRY_ACT.FINAL_METER.equals(ordEntryActCd)) {
            // Final Meter
            // ADD START 2016/03/03 #2176
            List<Integer> checkList = null;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(checkLine);
                            String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
                        
                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_LL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
                        }
                    }
                    scrnMsg.putErrorScreen();
                }
            } else {
                checkList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
                if(checkList.size() > 0){
                    for (int checkLine : checkList) {
                            NWAL1500_DBMsg lineMsg = scrnMsg.D.no(checkLine);
                            String lineStsNm = lineMsg.rtrnLineStsDescTxt_RL.getValue();
                        
                        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)){
                            lineMsg.xxChkBox_RL.setErrorInfo(1, NWAM0781E);
                            scrnMsg.addCheckItem(lineMsg.xxChkBox_RL);
                        }
                    }
                    scrnMsg.putErrorScreen();
                }
            }
            return;

        } else if (ORD_ENTRY_ACT.ADD_CONFIG.equals(ordEntryActCd)) { // S21_NA#6306

            // do nothing
            return;

        } else if (ORD_ENTRY_ACT.ADD_LINE.equals(ordEntryActCd)) { // S21_NA#6306

            List<Integer> selectRows = null;
            int confValidCnt = 0;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", "Y");
                confValidCnt = scrnMsg.A.getValidCount();
            } else {
                selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", "Y");
                confValidCnt = scrnMsg.C.getValidCount();
            }

            if (selectRows.size() == 0 && confValidCnt > 1) {
                scrnMsg.setMessageInfo(NWAM0697E, new String[] {MSG_PARAM_CONF_LINE });
                throw new EZDFieldErrorException();
            }

            return;

        } else if (ORD_ENTRY_ACT.LINE_FILTER.equals(ordEntryActCd)) {   // 2017/08/07 Sol#249 Add

            // do nothing
            return;
        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        } else if (ORD_ENTRY_ACT.SHOW_1ST_CONFIG.equals(ordEntryActCd)) {
            //Show 1st Config
            return;
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        }
    }

    /**
     * setDISCError
     * @param scrnMsg
     * @param checkList
     * @param msgId
     * @param params
     */
    private static void setDISCError(NWAL1500BMsg scrnMsg, List<Integer> checkList, String msgId, String[] params){

        for (int idx : checkList){
            String dplyTab = scrnMsg.xxDplyTab.getValue();
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                scrnMsg.A.no(idx).xxChkBox_LC.setErrorInfo(1, msgId, params);
                scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxChkBox_LC);
            } else if (TAB_RMA.equals(dplyTab)) {
                scrnMsg.C.no(idx).xxChkBox_RC.setErrorInfo(1, msgId, params);
                scrnMsg.addCheckItem(scrnMsg.C.no(idx).xxChkBox_RC);
            } else {
                break;
            }
        }

    }
    
    /**
     * setRequestData
     * @param handler S21CommonHandler
     * @param ctx EZDApplicationContext
     * @param bMsg EZDBMsg
     * @return EZDCMsg
     */
    public static EZDCMsg setRequestData(S21CommonHandler handler, EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        String ordEntryActCd = scrnMsg.ordEntryActCd.getValue();

        clearDeleteActionStatus(scrnMsg); // 2016/09/20 S21_NA#8279 Add
        clearLineFilterActionStatus(scrnMsg);   // 2017/08/07 Sol#249 Add

        if (ORD_ENTRY_ACT.CANCEL.equals(ordEntryActCd)) {
           // return update(scrnMsg);
           // return null;
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.ORDER_COPY.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.HOLDS.equals(ordEntryActCd) || ORD_ENTRY_ACT.HOLDS_2.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.CHANGE_ORDER_MODIFICATION.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.ATTACHMENT.equals(ordEntryActCd)) {
            // 2016/02/25 S21_NA#966 Add Start-----------
            //return null;
            return search(scrnMsg);
            // 2016/02/25 S21_NA#966 End Start-----------
        } else if (ORD_ENTRY_ACT.PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.PRICING_2.equals(ordEntryActCd)) {
            return search(scrnMsg);

        // 2018/05/11 QC#22139 Add Start
        } else if (ORD_ENTRY_ACT.ORDER_CONFIRMATION.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        } else if (ORD_ENTRY_ACT.RETURN_AUTHORIZATION.equals(ordEntryActCd)) {
            // 2018/07/03 QC#26932 Mod Start
            //return null;
            return search(scrnMsg);
            // 2018/07/03 QC#26932 Mod End
        // 2018/05/11 QC#22139 Add End

        } else if (ORD_ENTRY_ACT.DELIVERY_OR_INVOICE.equals(ordEntryActCd) || ORD_ENTRY_ACT.DELIVERY_OR_INVOICE_2.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        // 2018/07/27 S21_NA#14307 Mod Start
        // } else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd)) {
        } else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd) || ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS_2.equals(ordEntryActCd)) {
        // 2018/07/27 S21_NA#14307 Mod End
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.VIEW_WORKFLOW.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        } else if (ORD_ENTRY_ACT.EDI_ATTRIBUTES.equals(ordEntryActCd) || ORD_ENTRY_ACT.EDI_ATTRIBUTES_2.equals(ordEntryActCd)) {
            // 2018/07/03 S21_NA#26908 Mod Start
//            // TODO
//            return null;
            return search(scrnMsg);
            // 2018/07/03 S21_NA#26908 Mod End

        } else if (ORD_ENTRY_ACT.VIEW_CHANGE_LOG.equals(ordEntryActCd) || ORD_ENTRY_ACT.VIEW_CHANGE_LOG_2.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        } else if (ORD_ENTRY_ACT.ORDER_SUMMARY.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        } else if (ORD_ENTRY_ACT.SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.REP_SALES_CREDIT.equals(ordEntryActCd)) {
            int idx = handler.getButtonSelectNumber();
            scrnMsg.xxCellIdx_BB.setValue(idx);
            return search(scrnMsg);

         // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        } else if (ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT_2.equals(ordEntryActCd)) {
            return search(scrnMsg);
         // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]

        // 2018/07/20 S21_NA#26188 Mod Start
        } else if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_MASS_APPLY.equals(ordEntryActCd)) {
        //} else if (ORD_ENTRY_ACT.D_I_S_C.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_2.equals(ordEntryActCd)) {
        // 2018/07/20 S21_NA#26188 Mod End
            return search(scrnMsg); // 2016/03/03 S21_NA#5000 Mod

        } else if (ORD_ENTRY_ACT.PROFITABILITY_QA.equals(ordEntryActCd) || ORD_ENTRY_ACT.PROFITABILITY_QA_2.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        } else if (ORD_ENTRY_ACT.DATA_INTEGRITY_DI.equals(ordEntryActCd) || ORD_ENTRY_ACT.DATA_INTEGRITY_DI_2.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null; // 2016/03/30 S21_NA#5523-2 Alive
            // 2019/05/24 QC#50043 Mod End
            // return search(scrnMsg); // 2016/03/30 S21_NA#5523-2 Del

        // 2018/07/20 S21_NA#26188 Del Start
        //} else if (ORD_ENTRY_ACT.SERVICE_PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_2.equals(ordEntryActCd)) {
        //    return search(scrnMsg);
        // 2018/07/20 S21_NA#26188 Del End

        } else if (ORD_ENTRY_ACT.CANCEL_2.equals(ordEntryActCd)) {
            int idx = handler.getButtonSelectNumber();
            scrnMsg.xxCellIdx_BB.setValue(idx);
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.DELETE.equals(ordEntryActCd)) {
            return update(scrnMsg);

        } else if (ORD_ENTRY_ACT.ORDER_LINE_COPY.equals(ordEntryActCd)) {

            // QC#24245 2018/06/13 Del Start
            //List<Integer> slctConfList = null;
            //int slctConfIdx = -1;
            //boolean callBlap = false;
            //if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            //    slctConfList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            //    if (slctConfList.size() > 0) {
            //        slctConfIdx = slctConfList.get(0);
            //        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(slctConfIdx).svcConfigMstrPk_LC)) {
            //            callBlap = true;
            //        }
            //    }
            //} else {
            //    slctConfList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            //    if (slctConfList.size() > 0) {
            //        slctConfIdx = slctConfList.get(0);
            //        if (ZYPCommonFunc.hasValue(scrnMsg.C.no(slctConfIdx).svcConfigMstrPk_RC)) {
            //            callBlap = true;
            //        }
            //    }
            //}
            //if (slctConfIdx >= 0 && callBlap) {
            //    scrnMsg.xxCellIdx.setValue(slctConfIdx);
            //    return search(scrnMsg);
            //}
            // 2016/02/10 S21_NA#3398 Add End
            // QC#24245 2018/06/13 Del End
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        } else if (ORD_ENTRY_ACT.SUPPLY_AUTO_ADD.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.MASS_UPDATE.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.ALL_COLLAPSE.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.EXPAND_ALL.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.OUTBOUND_CONFIG_SELECT_ALL.equals(ordEntryActCd)) {
            // 2018/02/23 S21_NA#19808
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.LINES_SELECT_ALL.equals(ordEntryActCd)) {
            // 2018/02/23 S21_NA#19808
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.OUTBOUND_CONFIG_UNSELECT_ALL.equals(ordEntryActCd)) {
            // 2018/02/23 S21_NA#19808
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.LINES_UNSELECT_ALL.equals(ordEntryActCd)) {
            // 2018/02/23 S21_NA#19808
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.COPY_FROM.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        } else if (ORD_ENTRY_ACT.RMA_AUTO_ADD.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.BUYOUT.equals(ordEntryActCd)) {
            NWAL1500CommonLogic.clearPopUpParam(scrnMsg);
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.ADDITIONAL_LINE_INFORMATION.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        } else if (ORD_ENTRY_ACT.CONTROL_FIELDS.equals(ordEntryActCd)) {
            // 2019/05/24 QC#50043 Mod Start
            return search(scrnMsg);
            //return null;
            // 2019/05/24 QC#50043 Mod End

        } else if (ORD_ENTRY_ACT.WH_INVENTORY_INQUIRY.equals(ordEntryActCd)) {
            // 2016/07/05 S21_NA#7441 Mod Start
            return search(scrnMsg);
//            return null;
            // 2016/07/05 S21_NA#7441 Mod End

        } else if (ORD_ENTRY_ACT.PRICE_CHANGE.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.FINAL_METER.equals(ordEntryActCd)) {
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.ADD_CONFIG.equals(ordEntryActCd)) { // S21_NA#6306
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.ADD_LINE.equals(ordEntryActCd)) { // S21_NA#6306
            return search(scrnMsg);

        } else if (ORD_ENTRY_ACT.LINE_FILTER.equals(ordEntryActCd)) { // 2017/08/07 Sol#249 Add
            return search(scrnMsg);
        // 2019/05/28 QC#50043 Add Start
        } else if (ORD_ENTRY_ACT.SERVICE_PRICING_NEW.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_NEW_2.equals(ordEntryActCd)) {
            return search(scrnMsg);

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        } else if (ORD_ENTRY_ACT.SHOW_1ST_CONFIG.equals(ordEntryActCd)) {
            return search(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        }
        // 2019/05/28 QC#50043 Add End

        return null;
    }

    /**
     * doProcess
     * @param handler S21CommonHandler
     * @param ctx EZDApplicationContext
     * @param scrnMsg NWAL1500BMsg
     * @param bizMsg NWAL1500CMsg
     * @return Object
     */
    public static Object[] doProcess(S21CommonHandler handler, EZDApplicationContext ctx, NWAL1500BMsg scrnMsg, NWAL1500CMsg bizMsg) {

        String ordEntryActCd = scrnMsg.ordEntryActCd.getValue();

        if (ORD_ENTRY_ACT.CANCEL.equals(ordEntryActCd)) {
            return doProcessOrderCancel(handler, ctx, scrnMsg);

        } else if (ORD_ENTRY_ACT.ORDER_COPY.equals(ordEntryActCd)) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.xxCellIdx.setValue(handler.getButtonSelectNumber());
            Object[] params = NWAL1500CommonLogic.getParamNWAL1620ForHeader(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.HOLDS.equals(ordEntryActCd) || ORD_ENTRY_ACT.HOLDS_2.equals(ordEntryActCd)) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_LL);
            }
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_RL);
            }
            scrnMsg.putErrorScreen();

            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            // set param
            Object[] params = new Object[IDX_3];
            params[IDX_0] = scrnMsg.cpoOrdNum;
            params[IDX_1] = scrnMsg.configCatgCd_AW;
            // 2018/03/07 S21_NA#19808 Mod Start
//            params[IDX_2] = NWAL1500CommonLogic.getLineNums(scrnMsg);
            params[IDX_2] = scrnMsg.condSqlTxt_AW.getValue();
            // 2018/03/07 S21_NA#19808 Mod End
            return params;

        } else if (ORD_ENTRY_ACT.CHANGE_ORDER_MODIFICATION.equals(ordEntryActCd)) {
            scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
            scrnMsg.putErrorScreen();
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNWAL2090(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.ATTACHMENT.equals(ordEntryActCd)) {
            // 2016/02/26 S21_NA#966 Add Start-----------
            scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
            scrnMsg.putErrorScreen();
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            // Mod Start 2019/05/09 QC#50015
//            Object[] params = new Object[10];//2016/09/21 UnitTest#201
            Object[] params = new Object[11];
            // Mod End 2019/05/09 QC#50015
            params[0] = ZYPL0310Constant.DISPLAY_MODE_READ_ONLY;
            for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
                String funcId = scrnMsg.L.no(i).xxFuncId.getValue();
                // Mod Start 2019/05/10 QC#50015
                if (RGTN_AUTH.equals(funcId) 
                        || MOD_WF_ITEMS_AUTH.equals(funcId)
                        || MOD_PRICE_AUTH.equals(funcId)
//                        || MOD_QTY_AUTH.equals(funcId)) {
                        || MOD_QTY_AUTH.equals(funcId)
                        || MOD_LOGISTICS.equals(funcId)) {
                    params[0] = ZYPL0310Constant.DISPLAY_MODE_MODIFICATION;
                    break;
                }
                // Mod End 2019/05/10 QC#50015
            }
            params[1] = BIZ_ID;
            params[2] = scrnMsg.cpoOrdNum.getValue();
            params[3] = ATTACH_BUSINESS_NM;
            params[4] = ATTACH_DATA_ORD_NUM;
            params[5] = null;
            params[6] = NWAL1500_ATT_LIMIT;
            params[7] = NWAL1500_AUTHORIZE_FILE_EXT;
            params[8] = NWAL1500_AUTHORIZE_FILE_VOL;
            params[9] = BIZ_ID_THEREFORE_API;//2016/09/21 UnitTest#201
            // Add Start 2019/05/09 QC#50015
            StringBuilder docTpCd = new StringBuilder();
            int authOMCount = 0;
            for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
                String funcId = scrnMsg.L.no(i).xxFuncId.getValue();
                if (MOD_LOGISTICS.equals(funcId)) {
                    docTpCd = getDocTpConstVal(docTpCd, NWAL1500_DOC_TP_CD_AUTH_LGSC, scrnMsg.glblCmpyCd.getValue());
                } else if (authOMCount == 0) {
                    docTpCd = getDocTpConstVal(docTpCd, NWAL1500_DOC_TP_CD_AUTH_OTC, scrnMsg.glblCmpyCd.getValue());
                    authOMCount++;
                }
            }
            params[10] = docTpCd.toString();
            // Add End 2019/05/09 QC#50015
            // 2016/02/26 S21_NA#966 Add End-------------
            return params;

        } else if (ORD_ENTRY_ACT.PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.PRICING_2.equals(ordEntryActCd)) {
            NWAL1500CommonLogic.addCheckItemBizLayerErr(scrnMsg, true);
            scrnMsg.putErrorScreen();
            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);//add QC#3943 
        // 2018/05/11 QC#22139 Add Start
        } else if (ORD_ENTRY_ACT.ORDER_CONFIRMATION.equals(ordEntryActCd)) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            Object[] params = NWAL1500CommonLogic.getParamNWAL1790(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.RETURN_AUTHORIZATION.equals(ordEntryActCd)) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            Object[] params = NWAL1500CommonLogic.getParamNWAL2420(scrnMsg);
            return params;
        // 2018/05/11 QC#22139 Add End

        } else if (ORD_ENTRY_ACT.DELIVERY_OR_INVOICE.equals(ordEntryActCd) || ORD_ENTRY_ACT.DELIVERY_OR_INVOICE_2.equals(ordEntryActCd)) {
            // set param
            Object[] params = new Object[3];
            params[IDX_0] = scrnMsg.cpoOrdNum;
            // 2018/03/16 QC#22805 add start
            String dplyTab = scrnMsg.xxDplyTab.getValue();
            List<Object[]> checkNumList = new ArrayList<Object[]>();
            String[] numArray = null;

            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

                if (checkListItemLine.size() > 0) {
                    params[IDX_1] = CONFIG_CATG.OUTBOUND;
                    for (int lineIndex : checkListItemLine) {
                        numArray = new String[2];
                        numArray[0] = scrnMsg.B.no(lineIndex).dsOrdPosnNum_LL.getValue();
                        numArray[1] = scrnMsg.B.no(lineIndex).dsCpoLineNum_LL.getValue().toString();
                        checkNumList.add(numArray);
                    }
                }

            } else if (TAB_RMA.equals(dplyTab)) {
                List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

                if (checkListRMALine.size() > 0) {
                    params[IDX_1] = CONFIG_CATG.INBOUND;
                    for (int lineIndex : checkListRMALine) {
                        numArray = new String[2];
                        numArray[0] = scrnMsg.D.no(lineIndex).dsOrdPosnNum_RL.getValue();
                        numArray[1] = scrnMsg.D.no(lineIndex).dsCpoLineNum_RL.getValue().toString();
                        checkNumList.add(numArray);
                    }
                }
            }
            params[IDX_2] = checkNumList;
            // 2018/03/16 QC#22805 add end
            return params;

        // 2018/07/27 S21_NA#14307 Mod Start
        // } else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd)) {
        } else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd) || ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS_2.equals(ordEntryActCd)) {
        // 2018/07/27 S21_NA#14307 Mod End
            return getArgForSubScreenSpecialInstruction(scrnMsg);

        } else if (ORD_ENTRY_ACT.VIEW_WORKFLOW.equals(ordEntryActCd)) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            scrnMsg.xxCellIdx.setValue(handler.getButtonSelectNumber());

            scrnMsg.xxPopPrm_P0.setValue(NWAL1500Constant.WF_CALL_MODE_GRP_NM);
            Object[] params = new Object[3];
            params[0] = scrnMsg.xxPopPrm_P0;
            params[1] = null;
            params[2] = scrnMsg.cpoOrdNum;
            return params;

        } else if (ORD_ENTRY_ACT.EDI_ATTRIBUTES.equals(ordEntryActCd) || ORD_ENTRY_ACT.EDI_ATTRIBUTES_2.equals(ordEntryActCd)) {
            // 2018/07/03 S21_NA#26908 Mod Start
//            return null;
            Object[] params = NWAL1500CommonLogic.getParamNWAL2260(scrnMsg);
            return params;
            // 2018/07/03 S21_NA#26908 Mod End

        } else if (ORD_ENTRY_ACT.VIEW_CHANGE_LOG.equals(ordEntryActCd) || ORD_ENTRY_ACT.VIEW_CHANGE_LOG_2.equals(ordEntryActCd)) {
            Object[] params = NWAL1500CommonLogic.getParamNWAL1810(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.ORDER_SUMMARY.equals(ordEntryActCd)) {
            // set param
            Object[] params = new Object[IDX_2];
            params[IDX_0] = scrnMsg.cpoOrdNum;
            params[IDX_1] = scrnMsg.shipToCustAcctNm;
            return params;

        } else if (ORD_ENTRY_ACT.SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.REP_SALES_CREDIT.equals(ordEntryActCd)) {

            // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }
            // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]

            Object[] params = NWAL1500CommonLogic.getParamNWAL1600(scrnMsg);
            return params;

        // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        } else if (ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT_2.equals(ordEntryActCd)) {
            Object[] params = NWAL1500CommonLogic.getParamNWAL1130ForViewAllSalesCredit(scrnMsg);
            return params;
        // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]

        // 2018/07/20 S21_NA#26188 Mod Start
        } else if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_MASS_APPLY.equals(ordEntryActCd)) {
        //} else if (ORD_ENTRY_ACT.D_I_S_C.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_2.equals(ordEntryActCd)) {
        // 2018/07/20 S21_NA#26188 Mod End
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_LC);
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_LL);
            }
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_RC);
            }
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_RL);
            }
            scrnMsg.putErrorScreen();

            // set param
            Object[] params = new Object[IDX_3];
            params[IDX_0] = scrnMsg.cpoOrdNum;
            // 2017/10/19 S21_NA#21122 Del Start
//            // 2016/10/21 S21_NA#15472 Mod Start
//            // QC#2478
////            if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_AW)) {
////                params[IDX_1] = Arrays.asList(scrnMsg.dsOrdPosnNum_AW.getValue().split(","));
////            }
//            // 2017/06/28 QC#18138 Mod Start
////            if (ZYPCommonFunc.hasValue(scrnMsg.xxDsMsgEntryTxt_AW)) {
//            if (ORD_ENTRY_ACT.D_I_S_C_2.equals(ordEntryActCd) && ZYPCommonFunc.hasValue(scrnMsg.xxDsMsgEntryTxt_AW)) {
//            // 2017/06/28 QC#18138 Mod End
//                params[IDX_1] = Arrays.asList(scrnMsg.xxDsMsgEntryTxt_AW.getValue().split(","));
//            }
//            // 2016/10/21 S21_NA#15472 Mod End
            // 2017/10/19 S21_NA#21122 Del End
            // 2017/10/19 S21_NA#21122 Add Start
            
            
            // 2018/07/20 S21_NA#26188 Mod Start
            if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_MASS_APPLY.equals(ordEntryActCd)) {
            //if (ORD_ENTRY_ACT.D_I_S_C_2.equals(ordEntryActCd)) {
            // 2018/07/20 S21_NA#26188 Mod End
                List<String> selPosnNumList = new ArrayList<String>(0);
                // 2018/03/07 S21_NA#19808 Del Start
//                if (S21StringUtil.isEquals(TAB_LINE_CONFIG, scrnMsg.xxDplyTab.getValue())) {
//                    List<Integer> selPosnNumIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
//                    for (Integer idx : selPosnNumIdxList) {
//                        selPosnNumList.add(scrnMsg.A.no(idx.intValue()).dsOrdPosnNum_LC.getValue());
//                    }
//                } else if (S21StringUtil.isEquals(TAB_RMA, scrnMsg.xxDplyTab.getValue())) {
//                    List<Integer> selPosnNumIdxList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
//                    for (Integer idx : selPosnNumIdxList) {
//                        selPosnNumList.add(scrnMsg.C.no(idx.intValue()).dsOrdPosnNum_RC.getValue());
//                    }
//                }
                // 2018/03/07 S21_NA#19808 Del End
                // 2018/03/07 S21_NA#19808 Add Start
                for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                    selPosnNumList.add(scrnMsg.E.no(i).xxLineNum_AW.getValue());
                }
                // 2018/03/07 S21_NA#19808 Add End
                params[IDX_1] = selPosnNumList;
            }
            // 2017/10/19 S21_NA#21122 Add End
            params[IDX_2] = scrnMsg.configCatgCd_AW;
            return params;

        } else if (ORD_ENTRY_ACT.PROFITABILITY_QA.equals(ordEntryActCd) || ORD_ENTRY_ACT.PROFITABILITY_QA_2.equals(ordEntryActCd)) {
            // set param
            Object[] params = new Object[IDX_2];
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1540Constant.MODE.ORDER.getValue());
            params[IDX_0] = scrnMsg.xxPopPrm_P0;
            params[IDX_1] = scrnMsg.cpoOrdNum;
            return params;

        } else if (ORD_ENTRY_ACT.DATA_INTEGRITY_DI.equals(ordEntryActCd) || ORD_ENTRY_ACT.DATA_INTEGRITY_DI_2.equals(ordEntryActCd)) {
            // 2016/03/30 S21_NA#5523 Add Start
            scrnMsg.addCheckItem(scrnMsg.ordEntryActCd_AC);
            scrnMsg.putErrorScreen();
            // 2016/03/30 S21_NA#5523 Add End

            // set param
            Object[] params = new Object[IDX_1];
            params[IDX_0] = scrnMsg.cpoOrdNum;
            return params;

        // 2018/07/20 S21_NA#26188 Del Start
        //} else if (ORD_ENTRY_ACT.SERVICE_PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_2.equals(ordEntryActCd)) {
        //    // set param
        //    Object[] params = new Object[IDX_1];
        //    params[IDX_0] = scrnMsg.cpoOrdNum;
        //    return params;
        // 2018/07/20 S21_NA#26188 Del End

        } else if (ORD_ENTRY_ACT.SERVICE_PRICING_NEW.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_NEW_2.equals(ordEntryActCd)) { // Add S21_NA#17530
            // set param
            Object[] params = new Object[IDX_1];
            params[IDX_0] = scrnMsg.cpoOrdNum;
            return params;

        } else if (ORD_ENTRY_ACT.CANCEL_2.equals(ordEntryActCd)) {
            return doProcessLineCancel(handler, ctx, scrnMsg);
            
        } else if (ORD_ENTRY_ACT.DELETE.equals(ordEntryActCd)) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NWAL1500_ABMsg lineConfigMsg = scrnMsg.A.no(i);
                scrnMsg.addCheckItem(lineConfigMsg.xxChkBox_LC);
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
                scrnMsg.addCheckItem(lineMsg.xxChkBox_LL);
                // 2018/06/05 S21_NA#26681 Add Start
                scrnMsg.addCheckItem(lineMsg.dplyLineRefNum_LL);
                // 2018/06/05 S21_NA#26681 Add End
            }
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                NWAL1500_CBMsg rmaConfigMsg = scrnMsg.C.no(i);
                scrnMsg.addCheckItem(rmaConfigMsg.xxChkBox_RC);
            }
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
                scrnMsg.addCheckItem(rmaLineMsg.xxChkBox_RL);
                // 2018/06/05 S21_NA#26681 Add Start
                scrnMsg.addCheckItem(rmaLineMsg.dplyLineRefNum_RL);
                // 2018/06/05 S21_NA#26681 Add End
            }
            scrnMsg.putErrorScreen();
            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
            return null;

        } else if (ORD_ENTRY_ACT.ORDER_LINE_COPY.equals(ordEntryActCd)) {
            // 2016/02/10 S21_NA#3398 Add Start
            if (null != bizMsg) {
                if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                    for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_LC);
                    }
                } else {
                    for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                        scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_RC);
                    }
                }
                scrnMsg.putErrorScreen();
            }
            // 2016/02/10 S21_NA#3398 Add End
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNWAL1620ForDetail(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.SUPPLY_AUTO_ADD.equals(ordEntryActCd)) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);

            // 2017/09/12 S21_NA#21016 Mod Start
            // scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxCellIdx.getValueInt()).xxChkBox_LC);
            List<Integer> checkConfList = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);

            int selIdx = -1;
            if (checkConfList.size() > 0) {
                selIdx = checkConfList.get(0);
            }

            if (selIdx == -1) {
                selIdx = 0;
            }
            scrnMsg.setFocusItem(scrnMsg.A.no(selIdx).xxChkBox_LC);
            // 2017/09/12 S21_NA#21016 Mod End

        } else if (ORD_ENTRY_ACT.MASS_UPDATE.equals(ordEntryActCd)) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            // 2019/07/11 S21_NA#51287 Add Start
            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
            // 2019/07/11 S21_NA#51287 Add End

            // 2019/07/05 S21_NA#51151 Add Start
            if (S21StringUtil.isEquals(TAB_LINE_CONFIG, scrnMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_LL);
                }
            } else if (S21StringUtil.isEquals(TAB_RMA, scrnMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                    scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_RL);
                }
            }
            scrnMsg.putErrorScreen();
            // 2019/07/05 S21_NA#51151 Add End
            Object[] params = NWAL1500CommonLogic.getParamNWAL1610(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.ALL_COLLAPSE.equals(ordEntryActCd) || ORD_ENTRY_ACT.EXPAND_ALL.equals(ordEntryActCd)) {
            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
            return null;

        } else if (ORD_ENTRY_ACT.OUTBOUND_CONFIG_SELECT_ALL.equals(ordEntryActCd)) {
            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
//            configLineAllSelect(scrnMsg, ZYPConstant.CHKBOX_ON_Y); 2018/02/23 S21_NA#19808 Del
            return null;

        } else if (ORD_ENTRY_ACT.LINES_SELECT_ALL.equals(ordEntryActCd)) {
            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
//            lineAllSelect(scrnMsg, ZYPConstant.CHKBOX_ON_Y); 2018/02/23 S21_NA#19808 Del
            return null;

        } else if (ORD_ENTRY_ACT.OUTBOUND_CONFIG_UNSELECT_ALL.equals(ordEntryActCd)) {
            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
//            configLineAllSelect(scrnMsg, null); 2018/02/23 S21_NA#19808 Del
            return null;

        } else if (ORD_ENTRY_ACT.LINES_UNSELECT_ALL.equals(ordEntryActCd)) {
            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
//            lineAllSelect(scrnMsg, null); 2018/02/23 S21_NA#19808 Del
            return null;

        } else if (ORD_ENTRY_ACT.COPY_FROM.equals(ordEntryActCd)) {
            // mod start 2016/07/29 CSA S21_NA#12607
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }
            // mod end 2016/07/29 CSA S21_NA#12607
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = NWAL1500CommonLogic.getParamNWAL1620ForCopyFrom(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.RMA_AUTO_ADD.equals(ordEntryActCd)) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.C.no(i).svcConfigMstrPk_RC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).configTpCd_RC); // 2016/06/21 S21_NA#9849-2
            }
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.D.no(i).rtlWhNm_RL);
                scrnMsg.addCheckItem(scrnMsg.D.no(i).rtlSwhNm_RL);
            }
            scrnMsg.putErrorScreen();
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
            // S21_NA#1634NWAL1500CommonLogic.controlMdseField(scrnMsg);

            // 2016/01/12 S21_NA#2990 Mod Start
            // scrnMsg.setFocusItem(scrnMsg.C.no(scrnMsg.xxCellIdx.getValueInt()).xxChkBox_RC);
            List<Integer> checkConfList = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkLineList = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
            int selIdx = -1;
            if (checkConfList.size() > 0) {
                selIdx = checkConfList.get(0);
            }
            if (checkLineList.size() > 0) {
                String dsOrdPosnNum = scrnMsg.D.no(checkLineList.get(0)).dsOrdPosnNum_RL.getValue();
                for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                    if (dsOrdPosnNum.equals(scrnMsg.C.no(i).dsOrdPosnNum_RC.getValue())) {
                        if (selIdx > -1 && selIdx > i) {
                            selIdx = i;
                        }
                        break;
                    }
                }
            }
            if (selIdx == -1) {
                selIdx = 0;
            }
            scrnMsg.setFocusItem(scrnMsg.C.no(selIdx).xxChkBox_RC);
            // 2016/01/12 S21_NA#2990 Add End
            return null;

        } else if (ORD_ENTRY_ACT.BUYOUT.equals(ordEntryActCd)) {
            Object[] params = NWAL1500CommonLogic.getParamNWAL1640(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.ADDITIONAL_LINE_INFORMATION.equals(ordEntryActCd)) {
            Object[] params = NWAL1500CommonLogic.getParamNWAL1630(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.CONTROL_FIELDS.equals(ordEntryActCd)) {
            Object[] params = NWAL1500CommonLogic.getParamNWAL1650(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.WH_INVENTORY_INQUIRY.equals(ordEntryActCd)) {
            // 2018/03/05 S21_NA#19808 Add Start
            scrnMsg.addCheckItem(scrnMsg.B);
            scrnMsg.putErrorScreen();
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }
            // 2018/03/05 S21_NA#19808 Add End
            Object[] params = NWAL1500CommonLogic.getParamNLCL1000(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.PRICE_CHANGE.equals(ordEntryActCd)) {
            scrnMsg.addCheckItem(scrnMsg.B);
            scrnMsg.addCheckItem(scrnMsg.D);
            scrnMsg.putErrorScreen();
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            Object[] params = getArgForSubScreenPriceChange(scrnMsg);
            return params;

        } else if (ORD_ENTRY_ACT.FINAL_METER.equals(ordEntryActCd)) {
            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }

            List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
            int selectedIndex = selectedRows.get(0);

            scrnMsg.addCheckItem(scrnMsg.B.no(selectedIndex).xxChkBox_LL);
            scrnMsg.putErrorScreen();

            return getArgForSubScreenFinalMeter(scrnMsg, selectedIndex);

        } else if (ORD_ENTRY_ACT.ADD_CONFIG.equals(ordEntryActCd)) { // S21_NA#6306

            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);

            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    if (scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).dsOrdPosnNum_LC.getValue().equals(scrnMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
                        scrnMsg.setFocusItem(scrnMsg.B.no(i).mdseCd_LL);
                    }
                }
            } else {
                for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                    if (scrnMsg.C.no(scrnMsg.C.getValidCount() - 1).dsOrdPosnNum_RC.getValue().equals(scrnMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
                        scrnMsg.setFocusItem(scrnMsg.D.no(i).mdseCd_RL);
                    }
                }
            }
            return null;

        } else if (ORD_ENTRY_ACT.ADD_LINE.equals(ordEntryActCd)) { // S21_NA#6306

            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);

            List<Integer> selectRows = null;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", "Y");
                if (selectRows.size() == 0) {
                    selectRows.add(0);
                }
                for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                    if (scrnMsg.A.no(selectRows.get(0)).dsOrdPosnNum_LC.getValue().equals(scrnMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
                        scrnMsg.setFocusItem(scrnMsg.B.no(i).mdseCd_LL);
                    }
                }
                // 2019/07/18 S21_NA#51327 Add Start
//                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_LC);
//                }
                // 2019/07/18 S21_NA#51327 Add End
            } else {
                selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", "Y");
                if (selectRows.size() == 0) {
                    selectRows.add(0);
                }
                for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                    if (scrnMsg.C.no(selectRows.get(0)).dsOrdPosnNum_RC.getValue().equals(scrnMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
                        scrnMsg.setFocusItem(scrnMsg.D.no(i).mdseCd_RL);
                    }
                }
            }
            // 2019/07/18 S21_NA#51327 Add Start
//            scrnMsg.putErrorScreen();
//            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
            // 2019/07/18 S21_NA#51327 Add End

            return null;

        } else if (ORD_ENTRY_ACT.LINE_FILTER.equals(ordEntryActCd)) { // 2017/08/07 Sol#249 Add

            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()
                    || S21StringUtil.isEquals(NWAM0136I, scrnMsg.getMessageCode())) {
                throw new EZDFieldErrorException();
            }

            Object[] params = NWAL1500CommonLogic.getParamNWAL1890(scrnMsg);
            return params;

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        } else if (ORD_ENTRY_ACT.SHOW_1ST_CONFIG.equals(ordEntryActCd)) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_LC);
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_LL);
            }
            scrnMsg.putErrorScreen();

            if (scrnMsg.A.getValidCount() == 0) {
                scrnMsg.setMessageInfo(NWAM8474E);
                throw new EZDFieldErrorException();
            } else if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(0).dsCpoConfigPk_LC)) {
                scrnMsg.setMessageInfo(NWAM8474E);
                throw new EZDFieldErrorException();
            }

            Object[] params = new Object[IDX_4];

            params[IDX_0] = scrnMsg.cpoOrdNum;

            List<String> selPosnNumList = new ArrayList<String>(0);
            selPosnNumList.add(scrnMsg.A.no(0).dsOrdPosnNum_LC.getValue());
            params[IDX_1] = selPosnNumList;

            params[IDX_2] = CONFIG_CATG.OUTBOUND;
            params[IDX_3] = "Contact";

            return params;
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        }
        return null;
    }

    /**
     * search
     * @param scrnMsg NWAL1500BMsg
     * @return NWAL1500CMsg
     */
    private static NWAL1500CMsg search(NWAL1500BMsg scrnMsg) {

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * update
     * @param scrnMsg NWAL1500BMsg
     * @return NWAL1500CMsg
     */
    private static NWAL1500CMsg update(NWAL1500BMsg scrnMsg) {

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * getParamNWAL2000ForLineConfigConfigLevel
     * @param scrnMsg NWAL1500BMsg
     * @param selectedRow int
     * @return Object[]
     */
    private static Object[] getParamNWAL2000ForOrderLevel(NWAL1500BMsg scrnMsg) {

        Object[] params = new Object[11];

        scrnMsg.xxModeCd_P1.setValue("03");
        scrnMsg.glblCmpyCd_P1.setValue(scrnMsg.glblCmpyCd.getValue());
        scrnMsg.cpoOrdNum_P1.setValue(scrnMsg.cpoOrdNum.getValue());
        scrnMsg.configCatgCd_P1.setValue(CONFIG_CATG.OUTBOUND);
        // 2018/06/14 S21_NA#25227 Mod Start 
//        scrnMsg.dsOrdPosnNum_P1.clear();
//        scrnMsg.dsCpoLineNum_P1.clear();
//        scrnMsg.dsCpoLineSubNum_P1.clear()
        scrnMsg.xxPopPrm_P0.clear();
        // 2018/06/14 S21_NA#25227 Mod End
        scrnMsg.cancQty_P1.clear();
        scrnMsg.cancQty_P2.clear();
        scrnMsg.chngRsnTpCd_P1.clear();
        scrnMsg.bizProcCmntTxt_P1.clear();

        int index = 0;
        params[index++] = scrnMsg.xxModeCd_P1;
        params[index++] = scrnMsg.glblCmpyCd_P1;
        params[index++] = scrnMsg.cpoOrdNum_P1;
        params[index++] = scrnMsg.configCatgCd_P1;
        // 2018/06/14 S21_NA#25227 Mod Start
//        params[index++] = scrnMsg.dsOrdPosnNum_P1;
//        params[index++] = scrnMsg.dsCpoLineNum_P1;
//        params[index++] = scrnMsg.dsCpoLineSubNum_P1;
        params[index++] = scrnMsg.xxPopPrm_P0;
        // 2018/06/14 S21_NA#25227 Mod End
        params[index++] = scrnMsg.cancQty_P1;
        params[index++] = scrnMsg.cancQty_P2;
        params[index++] = scrnMsg.chngRsnTpCd_P1;
        params[index++] = scrnMsg.bizProcCmntTxt_P1;

        return params;
    }
    /**
     * getParamNWAL2000ForLineConfigConfigLevel
     * @param scrnMsg NWAL1500BMsg
     * @param selectedRow int
     * @return Object[]
     */
    // 2018/06/14 S21_NA#25227 Mod Start
//    private static Object[] getParamNWAL2000ForLineConfigConfigLevel(NWAL1500BMsg scrnMsg, int selectedRow) {
    private static Object[] getParamNWAL2000ForLineConfigConfigLevel(NWAL1500BMsg scrnMsg) {
    // 2018/06/14 S21_NA#25227 Mod End

        Object[] params = new Object[11];

        scrnMsg.xxModeCd_P1.setValue("01");
        scrnMsg.glblCmpyCd_P1.setValue(scrnMsg.glblCmpyCd.getValue());
        scrnMsg.cpoOrdNum_P1.setValue(scrnMsg.cpoOrdNum.getValue());
        scrnMsg.configCatgCd_P1.setValue(CONFIG_CATG.OUTBOUND);
        // 2018/06/14 S21_NA#25227 Del Start
//        scrnMsg.dsOrdPosnNum_P1.setValue(scrnMsg.A.no(selectedRow).dsOrdPosnNum_LC.getValue());
//        scrnMsg.dsCpoLineNum_P1.clear();
//        scrnMsg.dsCpoLineSubNum_P1.clear();
        // 2018/06/14 S21_NA#25227 Del End
        scrnMsg.cancQty_P1.clear();
        scrnMsg.cancQty_P2.clear();
        scrnMsg.chngRsnTpCd_P1.clear();
        scrnMsg.bizProcCmntTxt_P1.clear();

        int index = 0;
        params[index++] = scrnMsg.xxModeCd_P1;
        params[index++] = scrnMsg.glblCmpyCd_P1;
        params[index++] = scrnMsg.cpoOrdNum_P1;
        params[index++] = scrnMsg.configCatgCd_P1;
        // 2018/06/14 S21_NA#25227 Mod Start
//        params[index++] = scrnMsg.dsOrdPosnNum_P1;
//        params[index++] = scrnMsg.dsCpoLineNum_P1;
//        params[index++] = scrnMsg.dsCpoLineSubNum_P1;
        params[index++] = scrnMsg.xxPopPrm_P0;
        // 2018/06/14 S21_NA#25227 Mod End
        params[index++] = scrnMsg.cancQty_P1;
        params[index++] = scrnMsg.cancQty_P2;
        params[index++] = scrnMsg.chngRsnTpCd_P1;
        params[index++] = scrnMsg.bizProcCmntTxt_P1;

        return params;
    }

    /**
     * doProcessLineCancel
     * @param handler S21CommonHandler
     * @param ctx EZDApplicationContext
     * @param scrnMsg NWAL1500BMsg
     */
    private static Object[] doProcessLineCancel(S21CommonHandler handler, EZDApplicationContext ctx, NWAL1500BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_LC);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_LL);
            // 2018/06/14 S21_NA#25227 Add Start
            scrnMsg.addCheckItem(scrnMsg.B.no(i).dplyLineRefNum_LL);
            // 2018/06/14 S21_NA#25227 Add End
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_RC);
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            // 2018/06/14 S21_NA#25227 Add Start
            scrnMsg.addCheckItem(scrnMsg.D.no(i).dplyLineRefNum_RL);
            // 2018/06/14 S21_NA#25227 Add End
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_RL);
        }
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        //        setResult(scrnMsg.xxRqstFlg.getValue());
        if (S21StringUtil.isEquals(scrnMsg.xxRqstFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = null;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                // 2018/06/14 S21_NA#25227 Mod Start
//                List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
//                if (selectedRows.size() > 0) {
//                    params = getParamNWAL2000ForLineConfigConfigLevel(scrnMsg, selectedRows.get(0));
//                } else {
//                    selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
//                    if (selectedRows.size() > 0) {
//                        params = getParamNWAL2000ForLineConfigLineLevel(scrnMsg, selectedRows.get(0));
//                    }
//                }
                if (isConfigSelectedForCancel(scrnMsg.xxPopPrm_P0.getValue())) {
                    params = getParamNWAL2000ForLineConfigConfigLevel(scrnMsg);
                } else if (isLineSelectedForCancel(scrnMsg.xxPopPrm_P0.getValue())) {
                    params = getParamNWAL2000ForLineConfigLineLevel(scrnMsg);
                }
                // 2018/06/14 S21_NA#25227 Mod End
            } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
                // 2018/06/14 S21_NA#25227 Mod Start
//                List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
//                if (selectedRows.size() > 0) {
//                    params = getParamNWAL2000ForRMAConfigLevel(scrnMsg, selectedRows.get(0));
//                } else {
//                    selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
//                    if (selectedRows.size() > 0) {
//                        params = getParamNWAL2000ForRMALineLevel(scrnMsg, selectedRows.get(0));
//                    }
//                }
                if (isConfigSelectedForCancel(scrnMsg.xxPopPrm_P0.getValue())) {
                    params = getParamNWAL2000ForRMAConfigLevel(scrnMsg);
                } else if (isLineSelectedForCancel(scrnMsg.xxPopPrm_P0.getValue())) {
                    params = getParamNWAL2000ForRMALineLevel(scrnMsg);
                }
                // 2018/06/14 S21_NA#25227 Mod End
            }
            return params;
        } else {
            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
        }
        return null;
    }

    /**
     * getParamNWAL2000ForLineConfigLineLevel
     * @param scrnMsg NWAL1500BMsg
     * @param selectedRow int
     * @return Object[]
     */
    // 2018/06/14 S21_NA#25227 Mod Start
//    private static Object[] getParamNWAL2000ForLineConfigLineLevel(NWAL1500BMsg scrnMsg, int selectedRow) {
    private static Object[] getParamNWAL2000ForLineConfigLineLevel(NWAL1500BMsg scrnMsg) {
    // 2018/06/14 S21_NA#25227 Mod End

        Object[] params = new Object[11];

        scrnMsg.xxModeCd_P1.setValue("02");
        scrnMsg.glblCmpyCd_P1.setValue(scrnMsg.glblCmpyCd.getValue());
        scrnMsg.cpoOrdNum_P1.setValue(scrnMsg.cpoOrdNum.getValue());
        scrnMsg.configCatgCd_P1.setValue(CONFIG_CATG.OUTBOUND);
        // 2018/06/14 S21_NA#25227 Del Start
//        scrnMsg.dsOrdPosnNum_P1.setValue(scrnMsg.B.no(selectedRow).dsOrdPosnNum_LL.getValue());
//        scrnMsg.dsCpoLineNum_P1.setValue(scrnMsg.B.no(selectedRow).dsCpoLineNum_LL.getValue());
//        scrnMsg.dsCpoLineSubNum_P1.clear();
        // 2018/06/14 S21_NA#25227 Del End
        // scrnMsg.cancQty_P1.clear();
        scrnMsg.cancQty_P2.clear();
        scrnMsg.chngRsnTpCd_P1.clear();
        scrnMsg.bizProcCmntTxt_P1.clear();

        int index = 0;
        params[index++] = scrnMsg.xxModeCd_P1;
        params[index++] = scrnMsg.glblCmpyCd_P1;
        params[index++] = scrnMsg.cpoOrdNum_P1;
        params[index++] = scrnMsg.configCatgCd_P1;
        // 2018/06/14 S21_NA#25227 Mod Start
//        params[index++] = scrnMsg.dsOrdPosnNum_P1;
//        params[index++] = scrnMsg.dsCpoLineNum_P1;
//        params[index++] = scrnMsg.dsCpoLineSubNum_P1;
        params[index++] = scrnMsg.xxPopPrm_P0;
        // 2018/06/14 S21_NA#25227 Mod End
        params[index++] = scrnMsg.cancQty_P1;
        params[index++] = scrnMsg.cancQty_P2;
        params[index++] = scrnMsg.chngRsnTpCd_P1;
        params[index++] = scrnMsg.bizProcCmntTxt_P1;

        return params;
    }

    /**
     * getParamNWAL2000ForRMAConfigLevel
     * @param scrnMsg NWAL1500BMsg
     * @param selectedRow int
     * @return Object[]
     */
    // 2018/06/14 S21_NA#25227 Mod Start
//    private static Object[] getParamNWAL2000ForRMAConfigLevel(NWAL1500BMsg scrnMsg, int selectedRow) {
    private static Object[] getParamNWAL2000ForRMAConfigLevel(NWAL1500BMsg scrnMsg) {
    // 2018/06/14 S21_NA#25227 Mod Start

        Object[] params = new Object[11];

        scrnMsg.xxModeCd_P1.setValue("01");
        scrnMsg.glblCmpyCd_P1.setValue(scrnMsg.glblCmpyCd.getValue());
        scrnMsg.cpoOrdNum_P1.setValue(scrnMsg.cpoOrdNum.getValue());
        scrnMsg.configCatgCd_P1.setValue(CONFIG_CATG.INBOUND);
        // 2018/06/14 S21_NA#25227 Del Start
//        scrnMsg.dsOrdPosnNum_P1.setValue(scrnMsg.C.no(selectedRow).dsOrdPosnNum_RC.getValue());
//        scrnMsg.dsCpoLineNum_P1.clear();
//        scrnMsg.dsCpoLineSubNum_P1.clear();
        // 2018/06/14 S21_NA#25227 Del End
        scrnMsg.cancQty_P1.clear();
        scrnMsg.cancQty_P2.clear();
        scrnMsg.chngRsnTpCd_P1.clear();
        scrnMsg.bizProcCmntTxt_P1.clear();

        int index = 0;
        params[index++] = scrnMsg.xxModeCd_P1;
        params[index++] = scrnMsg.glblCmpyCd_P1;
        params[index++] = scrnMsg.cpoOrdNum_P1;
        params[index++] = scrnMsg.configCatgCd_P1;
        // 2018/06/14 S21_NA#25227 Mod Start
//        params[index++] = scrnMsg.dsOrdPosnNum_P1;
//        params[index++] = scrnMsg.dsCpoLineNum_P1;
//        params[index++] = scrnMsg.dsCpoLineSubNum_P1;
        params[index++] = scrnMsg.xxPopPrm_P0;
        // 2018/06/14 S21_NA#25227 Mod End
        params[index++] = scrnMsg.cancQty_P1;
        params[index++] = scrnMsg.cancQty_P2;
        params[index++] = scrnMsg.chngRsnTpCd_P1;
        params[index++] = scrnMsg.bizProcCmntTxt_P1;

        return params;
    }

    /**
     * getParamNWAL2000ForRMALineLevel
     * @param scrnMsg NWAL1500BMsg
     * @param selectedRow int
     * @return Object[]
     */
    // 2018/06/14 S21_NA#25227 Mod Start
//    private static Object[] getParamNWAL2000ForRMALineLevel(NWAL1500BMsg scrnMsg, int selectedRow) {
    private static Object[] getParamNWAL2000ForRMALineLevel(NWAL1500BMsg scrnMsg) {
    // 2018/06/14 S21_NA#25227 Mod Start

        Object[] params = new Object[11];

        scrnMsg.xxModeCd_P1.setValue("02");
        scrnMsg.glblCmpyCd_P1.setValue(scrnMsg.glblCmpyCd.getValue());
        scrnMsg.cpoOrdNum_P1.setValue(scrnMsg.cpoOrdNum.getValue());
        scrnMsg.configCatgCd_P1.setValue(CONFIG_CATG.INBOUND);
        // 2018/06/14 S21_NA#25227 Del Start
//        scrnMsg.dsOrdPosnNum_P1.setValue(scrnMsg.D.no(selectedRow).dsOrdPosnNum_RL.getValue());
//        scrnMsg.dsCpoLineNum_P1.setValue(scrnMsg.D.no(selectedRow).dsCpoLineNum_RL.getValue());
//        scrnMsg.dsCpoLineSubNum_P1.setValue(scrnMsg.D.no(selectedRow).dsCpoLineSubNum_RL.getValue());
        // 2018/06/14 S21_NA#25227 Del End
        // scrnMsg.cancQty_P1.clear();
        scrnMsg.cancQty_P2.clear();
        scrnMsg.chngRsnTpCd_P1.clear();
        scrnMsg.bizProcCmntTxt_P1.clear();

        int index = 0;
        params[index++] = scrnMsg.xxModeCd_P1;
        params[index++] = scrnMsg.glblCmpyCd_P1;
        params[index++] = scrnMsg.cpoOrdNum_P1;
        params[index++] = scrnMsg.configCatgCd_P1;
        // 2018/06/14 S21_NA#25227 Mod Start
//        params[index++] = scrnMsg.dsOrdPosnNum_P1;
//        params[index++] = scrnMsg.dsCpoLineNum_P1;
//        params[index++] = scrnMsg.dsCpoLineSubNum_P1;
        params[index++] = scrnMsg.xxPopPrm_P0;
        // 2018/06/14 S21_NA#25227 Mod End
        params[index++] = scrnMsg.cancQty_P1;
        params[index++] = scrnMsg.cancQty_P2;
        params[index++] = scrnMsg.chngRsnTpCd_P1;
        params[index++] = scrnMsg.bizProcCmntTxt_P1;

        return params;
    }

    /**
     * doProcessLineCancel
     * @param handler S21CommonHandler
     * @param ctx EZDApplicationContext
     * @param scrnMsg NWAL1500BMsg
     */
    private static Object[] doProcessOrderCancel(S21CommonHandler handler, EZDApplicationContext ctx, NWAL1500BMsg scrnMsg) {

        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        //        setResult(scrnMsg.xxRqstFlg.getValue());
//        if (S21StringUtil.isEquals(scrnMsg.xxRqstFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = null;

            params = getParamNWAL2000ForOrderLevel(scrnMsg);

            return params;
//        } else {
//            NWAL1500CommonLogicForScrnFields.setProtect(handler, scrnMsg);
//        }
     //   return null;
    }

    /**
     * getArgForSubScreenPriceChange
     * @param scrnMsg NWAL1500BMsg
     * @return Object[]
     */
    private static Object[] getArgForSubScreenPriceChange(NWAL1500BMsg scrnMsg) {

        // 2016/03/02 S21_NA#4377 Add Start
        // 2017/10/13 S21_NA#21267 Add Start
        boolean isOrderCredit = NWAL1500CommonLogic.isOrderCredit(scrnMsg);
        // 2017/10/13 S21_NA#21267 Add End
        if (NWAL1500CommonLogic.checkReadOnlyByStatus(scrnMsg) //
                || isOrderCredit) { // 2017/10/13 S21_NA#21267 Add isOrderCredit
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_N, NWAL1660Constant.MODE_REFERENCE);
        }
        // 2016/03/02 S21_NA#4377 Add End

        List<Object> parameterList = new ArrayList<Object>();
        parameterList.add(scrnMsg.xxModeCd_N);                // 0
        parameterList.add(scrnMsg.xxModeCd_PM);               // 1 QC#22965 2018/04/11 Add
        parameterList.add(scrnMsg.xxViewChngLogSrcCd_N);      // 2 QC#9700  2018/09/03 Add
        parameterList.add(scrnMsg.trxHdrNum_N);               // 3
        parameterList.add(scrnMsg.prcBaseDt_N);               // 4
        parameterList.add(scrnMsg.prcCalcDt_N);               // 5
        parameterList.add(scrnMsg.dsOrdTpCd_N);               // 6
        parameterList.add(scrnMsg.dsOrdCatgCd_N);             // 7
        parameterList.add(scrnMsg.lineBizTpCd_N);             // 8
        parameterList.add(scrnMsg.cpoSrcTpCd_N);              // 9
        parameterList.add(scrnMsg.custIssPoNum_N);            // 10
        parameterList.add(scrnMsg.dsPmtMethCd_N);             // 11
        parameterList.add(scrnMsg.spclHdlgTpCd_N);            // 12
        parameterList.add(scrnMsg.leasePrchOptCd_N);          // 13
        parameterList.add(scrnMsg.dsOrdPosnNum_N);            // 14
        parameterList.add(scrnMsg.trxLineNum_N);              // 15
        parameterList.add(scrnMsg.trxLineSubNum_N);           // 16
        parameterList.add(scrnMsg.configCatgCd_N);            // 17
        parameterList.add(scrnMsg.inEachQty_N);               // 18
        parameterList.add(scrnMsg.shipToFirstLineAddr_N);     // 19
        parameterList.add(scrnMsg.shipToScdLineAddr_N);       // 20
        parameterList.add(scrnMsg.shipToCtyAddr_N);           // 21
        parameterList.add(scrnMsg.shipToStCd_N);              // 22
        parameterList.add(scrnMsg.shipToCntyNm_N);            // 23
        parameterList.add(scrnMsg.shipToPostCd_N);            // 24
        parameterList.add(scrnMsg.shipToCtryCd_N);            // 25
        parameterList.add(scrnMsg.prcCatgCd_N);               // 26
        parameterList.add(scrnMsg.csmpNum_N);                 // 27
        parameterList.add(scrnMsg.dlrRefNum_N);               // 28
        parameterList.add(scrnMsg.prcContrNum_N);             // 29
        parameterList.add(scrnMsg.coaBrCd_N);                 // 30
        parameterList.add(scrnMsg.mdseCd_N);                  // 31
        parameterList.add(scrnMsg.billToCustCd_N);            // 32
        parameterList.add(scrnMsg.billToCustAcctCd_N);        // 33
        parameterList.add(scrnMsg.sellToCustCd_N);            // 34
        parameterList.add(scrnMsg.soldToCustLocCd_N);         // 35
        parameterList.add(scrnMsg.shipToCustCd_N);            // 36
        parameterList.add(scrnMsg.shipToCustAcctCd_N);        // 37
        parameterList.add(scrnMsg.frtCondCd_N);               // 38
        parameterList.add(scrnMsg.shpgSvcLvlCd_N);            // 39
        parameterList.add(scrnMsg.ccyCd_N);                   // 40
        parameterList.add(scrnMsg.uomCd_N);                   // 41
        if(scrnMsg.ordCustUomQty_N.isClear()){
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordCustUomQty_N, new BigDecimal(0));
        }
        parameterList.add(scrnMsg.ordCustUomQty_N);           // 42
        parameterList.add(scrnMsg.dsCpoLineCatgCd_N);         // 43
        parameterList.add(scrnMsg.invQty_N);                  // 44
        parameterList.add(scrnMsg.mdlId_N);                   // 45
        parameterList.add(scrnMsg.rtrnRsnCd_N);               // 46
        parameterList.add(scrnMsg.coaExtnCd_N);               // 47
        parameterList.add(scrnMsg.slsRepOrSlsTeamTocCd_N);    // 48
        parameterList.add(scrnMsg.rtlWhCd_N);                 // 49
        parameterList.add(scrnMsg.xxTotBaseAmt_N);            // 50
        parameterList.add(scrnMsg.xxSubTotCalcPrcAmt_N);      // 51
        parameterList.add(scrnMsg.xxTotChrgPrcAmt_N);         // 52
        parameterList.add(scrnMsg.xxTotDiscAmt_N);            // 53
        parameterList.add(scrnMsg.xxTotSpclPrcAmt_N);         // 54
        parameterList.add(scrnMsg.xxTotNetDiscAmt_N);         // 55
        parameterList.add(scrnMsg.xxUnitNetPrcAmt_N);         // 56
        parameterList.add(scrnMsg.xxUnitGrsPrcAmt_N);         // 57
        parameterList.add(scrnMsg.xxTotNetPrcAmt_N);          // 58
        parameterList.add(scrnMsg.xxGrsAmt_N);                // 59
        parameterList.add(scrnMsg.xxUnitFrtAmt_N);            // 60
        parameterList.add(scrnMsg.xxTotFrtAmt_N);             // 61
        parameterList.add(scrnMsg.xxUnitSpclChrgAmt_N);       // 62
        parameterList.add(scrnMsg.xxTotSpclChrgAmt_N);        // 63
        parameterList.add(scrnMsg.xxTotFrtSubAmt_N);          // 64
        parameterList.add(scrnMsg.xxUnitRestkFeeAmt_N);       // 65
        parameterList.add(scrnMsg.xxTotRestkFeeAmt_N);        // 66
        parameterList.add(scrnMsg.xxTotNetPrcAmt_N2);         // 67
        parameterList.add(scrnMsg.xxTotTaxAmt_N);             // 68
        parameterList.add(scrnMsg.xxUnitPrc_N);               // 69
        parameterList.add(scrnMsg.xxTotAmt_N);                // 70
        parameterList.add(scrnMsg.dealPrcListPrcAmt_N);       // 71
        // 2015/11/24 S21_NA#1020 Add Start
        parameterList.add(scrnMsg.dsCpoLineNum_N);            // 72
        parameterList.add(scrnMsg.dsCpoLineSubNum_N);         // 73
        parameterList.add(scrnMsg.dealSvcRevTrnsfAmt_N);      // 74
        parameterList.add(scrnMsg.dealSvcCostTrnsfAmt_N);     // 75
        parameterList.add(scrnMsg.dealManFlAdjAmt_N);         // 76
        parameterList.add(scrnMsg.dealManRepRevAdjAmt_N);     // 77
        // 2015/11/24 S21_NA#1020 Add End
        parameterList.add(scrnMsg.xxTotUnitNetWt_N);          // 78
        scrnMsg.xxSfxKeyTxt_N.setValue("NL");
        parameterList.add(scrnMsg.xxSfxKeyTxt_N);             // 79
        parameterList.add(scrnMsg.N);                         // 80
        // 2018/05/10 S21_NA#25251 Add Start
        String prcChangeAuth = ZYPConstant.FLG_OFF_N;
        if (S21StringUtil.isEquals(TAB_LINE_CONFIG, scrnMsg.xxDplyTab.getValue())) {
            if (hasPriceChangeAuthForOutbound(scrnMsg)) {
                prcChangeAuth = ZYPConstant.FLG_ON_Y;
            }
        } else if (S21StringUtil.isEquals(TAB_RMA, scrnMsg.xxDplyTab.getValue())) {
            if (hasPriceChangeAuthForInbound(scrnMsg)) {
                prcChangeAuth = ZYPConstant.FLG_ON_Y;
            }
        }
        parameterList.add(prcChangeAuth); // 80
        // 2018/05/10 S21_NA#25251 Add End
        return parameterList.toArray();
    }

    /**
     * getArgForSubScreenFinalMeter
     * @param scrnMsg NWAL1500BMsg
     * @param selectedIndex int
     * @return Serializable
     */
    private static Object[] getArgForSubScreenFinalMeter(NWAL1500BMsg scrnMsg, int selectedIndex) {

        List<Object> parameters = new ArrayList<Object>();
        parameters.add(scrnMsg.B.no(selectedIndex).svcMachMstrPk_LL);
        parameters.add(scrnMsg.cpoOrdNum);
        return parameters.toArray(new Object[0]);
    }

    /**
     * getArgForSubScreenSpecialInstruction
     * @param scrnMsg NWAL1500BMsg
     * @return Serializable
     */
    // 2018/07/27 S21_NA#14307 Mod Start
    //private static Object[] getArgForSubScreenSpecialInstruction(NWAL1500BMsg scrnMsg) {
    public static Object[] getArgForSubScreenSpecialInstruction(NWAL1500BMsg scrnMsg) {
    // 2018/07/27 S21_NA#14307 Mod End

        // 2018/07/27 S21_NA#14307 Add Start
        if (!NWAL1500CommonLogic.checkSpecialInstrctionData(scrnMsg)) {
            return null;
        }
        // 2018/07/27 S21_NA#14307 Add End

        List<Object> parmeters = new ArrayList<Object>();

        // [0] : Global Company Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.glblCmpyCd);
        parmeters.add(scrnMsg.xxPopPrm_P0);

        // [1] : Function ID
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, BIZ_ID);
        parmeters.add(scrnMsg.xxPopPrm_P1);

        // [2] : Function Category ID
        // 2018/07/10 S21_NA#26661,26713 Del Start
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, "");
        // 2018/07/10 S21_NA#26661,26713 Del End
        parmeters.add(scrnMsg.xxPopPrm_P2);

        // [3] : Transaction Type
        parmeters.add(scrnMsg.xxPopPrm_P3);

        // [4] : Business Area
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, "");
        parmeters.add(scrnMsg.xxPopPrm_P4);

        // [5] : Customer Special Instruction Line Suffix
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, "QL"); // S21_NA#1963
        parmeters.add("QL");

        // [6] : Customer Special Instruction Line
        int index = 0;
        // 2018/07/27 S21_NA#14307 Mod Start
        //ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, scrnMsg.sellToCustCd);
        //ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");
        //ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");

        //ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");
        //ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, scrnMsg.billToCustCd);
        //ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");

        //ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");
        //ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");
        //ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, scrnMsg.shipToCustCd);
        scrnMsg.Q.clear();

        // Sold To
        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, scrnMsg.sellToCustCd_SP);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");
        }

        // Bill To
        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, scrnMsg.billToCustCd_SP);
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, "");
        }

        // Ship To
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_SP)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).dsAcctNum_QL, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index).billToCustCd_QL, "");
            ZYPEZDItemValueSetter.setValue(scrnMsg.Q.no(index++).shipToCustCd_QL, scrnMsg.shipToCustCd_SP);
        }
        // 2018/07/27 S21_NA#14307 Mod End
        scrnMsg.Q.setValidCount(index);
        parmeters.add(scrnMsg.Q);

        // 2018/07/10 S21_NA#26661,26713 Add Start
        // [7] : Line of Business Code
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, scrnMsg.lineBizTpCd);
        parmeters.add(scrnMsg.xxPopPrm_P5);
        // 2018/07/10 S21_NA#26661,26713 Add End

        return parmeters.toArray(new Object[0]);
    }

    private static boolean checkEvent(NWAL1500BMsg scrnMsg) {

        // 2017/10/13 S21_NA#21267 Add Start
        boolean isOrderCredit = NWAL1500CommonLogic.isOrderCredit(scrnMsg);
        // 2017/10/13 S21_NA#21267 Add End

        String ordEntryActCd = scrnMsg.ordEntryActCd.getValue();
        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();
        // 2016/10/13 S21_NA#7700 Add Start
        if (!checkEventForEdtblFlg(scrnMsg, ordEntryActCd)) {
            return false;
        }
        // 2016/10/13 S21_NA#7700 Add End
        // 2016/10/19 S21_NA#14728 Add Start
        if (!checkEventByAuthority(scrnMsg, ordEntryActCd)) {
            return false;
        }
        // 2016/10/19 S21_NA#14728 Add End
        if (ORD_ENTRY_ACT.CANCEL.equals(ordEntryActCd)) {

            // 2018/03/20 S21_NA#24853 Del Start
//            // 2016/10/13 S21_NA#7700 Add Start
//            if (!chkCancelAvailable(scrnMsg)) {
//                return false;
//            }
            // 2018/03/20 S21_NA#24853 Del End
            // 2016/10/13 S21_NA#7700 Add End
            if (!ZYPCommonFunc.hasValue(hdrStsNm) || HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
                return false;
            }

        } else if (ORD_ENTRY_ACT.ORDER_COPY.equals(ordEntryActCd)) {
            // 2017/10/13 S21_NA#21267 Add Start
            if (isOrderCredit) {
                return false;
            }
            // 2017/10/13 S21_NA#21267 Add End
        } else if (ORD_ENTRY_ACT.HOLDS.equals(ordEntryActCd) || ORD_ENTRY_ACT.HOLDS_2.equals(ordEntryActCd)) {
            if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
                return false;
            }
            // 2018/04/10 S21_NA#22167 Del Start
            // 2017/10/13 S21_NA#21267 Add Start
            // if (isOrderCredit) {
            //     return false;
            // }
            // 2017/10/13 S21_NA#21267 Add End
            // 2018/04/10 S21_NA#22167 Del End

        } else if (ORD_ENTRY_ACT.CHANGE_ORDER_MODIFICATION.equals(ordEntryActCd)) {
            if (HEADER_STS_NM_ENTERED.equals(hdrStsNm) || HEADER_STS_NM_DI_CHECK_HOLD.equals(hdrStsNm) || HEADER_STS_NM_VALIDATION_HOLD.equals(hdrStsNm) || HEADER_STS_NM_SUPPLY_ABUSE_HOLD.equals(hdrStsNm)
                    || HEADER_STS_NM_PROFIITABILITY_HOLD.equals(hdrStsNm) || HEADER_STS_NM_CREDIT_HOLD.equals(hdrStsNm) || HEADER_STS_NM_PENDING.equals(hdrStsNm) || HEADER_STS_NM_BOOKED.equals(hdrStsNm)
                    || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }

        } else if (ORD_ENTRY_ACT.ATTACHMENT.equals(ordEntryActCd)) {
            // S21_NA#7337 Add Start
            if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
                return false;
            }
            // S21_NA#7337 Add End
            // 2018/04/10 S21_NA#22167 Del Start
            // 2017/10/13 S21_NA#21267 Add Start
            // if (isOrderCredit) {
            //     return false;
            // }
            // 2017/10/13 S21_NA#21267 Add End
            // 2018/04/10 S21_NA#22167 Del End

        } else if (ORD_ENTRY_ACT.PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.PRICING_2.equals(ordEntryActCd)) {
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }
            // S21_NA#6442 Add Start
            if (CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(scrnMsg.cpoSrcTpCd.getValue())) {
                scrnMsg.ordEntryActCd_AC.setErrorInfo(1, NWAM0855E);
                return false;
            }
            // S21_NA#6442 Add End

        // 2018/05/11 S21_NA#22139 Add Start
        } else if (ORD_ENTRY_ACT.ORDER_CONFIRMATION.equals(ordEntryActCd)) {
            if (scrnMsg.B.getValidCount() == 0) {
                return false;
            }
        } else if (ORD_ENTRY_ACT.RETURN_AUTHORIZATION.equals(ordEntryActCd)) {
            if (scrnMsg.D.getValidCount() == 0) {
                return false;
            }
        // 2018/05/11 S21_NA#22139 Add End
        } else if (ORD_ENTRY_ACT.DELIVERY_OR_INVOICE.equals(ordEntryActCd) || ORD_ENTRY_ACT.DELIVERY_OR_INVOICE_2.equals(ordEntryActCd)) {
            if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
                return false;
            }

        // 2018/07/27 S21_NA#14307 Mod Start
        //} else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd)) {
        } else if (ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS.equals(ordEntryActCd) || ORD_ENTRY_ACT.CUSTOMER_SPECIAL_INSTRUCTIONS_2.equals(ordEntryActCd)) {
        // 2018/07/27 S21_NA#14307 Mod End
            // 2016/10/13 S21_NA#7700 Del Start
            // if (HEADER_STS_NM_BOOKED.equals(hdrStsNm) || HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
            //     return false;
            // }
            // 2016/10/13 S21_NA#7700 Del End

        } else if (ORD_ENTRY_ACT.VIEW_WORKFLOW.equals(ordEntryActCd)) {

        } else if (ORD_ENTRY_ACT.EDI_ATTRIBUTES.equals(ordEntryActCd) || ORD_ENTRY_ACT.EDI_ATTRIBUTES_2.equals(ordEntryActCd)) {
            // TODO

        } else if (ORD_ENTRY_ACT.VIEW_CHANGE_LOG.equals(ordEntryActCd) || ORD_ENTRY_ACT.VIEW_CHANGE_LOG_2.equals(ordEntryActCd)) {
            if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
                return false;
            }

        } else if (ORD_ENTRY_ACT.SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.REP_SALES_CREDIT.equals(ordEntryActCd)) {

            //TODO
            //        } else if (ORD_ENTRY_ACT.LINE_COPY_FROM.equals(ordEntryActCd)) {
                //            if (HEADER_STS_NM_VALIDATION_HOLD.equals(hdrStsNm) || HEADER_STS_NM_SUPPLY_ABUSE_HOLD.equals(hdrStsNm) || HEADER_STS_NM_PROFIITABILITY_HOLD.equals(hdrStsNm)
            //                    || HEADER_STS_NM_CREDIT_HOLD.equals(hdrStsNm) || HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
            //                return false;
            //            }

        // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        } else if (ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT.equals(ordEntryActCd) || ORD_ENTRY_ACT.VIEW_ALL_SALES_CREDIT_2.equals(ordEntryActCd)) {
        // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]

        // 2018/07/20 S21_NA#26188 Mod Start
        } else if (ORD_ENTRY_ACT.D_I_S_C_LINE_UPDATE.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_MASS_APPLY.equals(ordEntryActCd)) {
        //} else if (ORD_ENTRY_ACT.D_I_S_C.equals(ordEntryActCd) || ORD_ENTRY_ACT.D_I_S_C_2.equals(ordEntryActCd)) {
        // 2018/07/20 S21_NA#26188 Mod End
            if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
                return false;
            }

        } else if (ORD_ENTRY_ACT.PROFITABILITY_QA.equals(ordEntryActCd) || ORD_ENTRY_ACT.PROFITABILITY_QA_2.equals(ordEntryActCd)) {
            if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
                return false;
            }
        } else if (ORD_ENTRY_ACT.DATA_INTEGRITY_DI.equals(ordEntryActCd) || ORD_ENTRY_ACT.DATA_INTEGRITY_DI_2.equals(ordEntryActCd)) {
            if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
                return false;
            }
            // 2017/10/13 S21_NA#21267 Add Start
            if (isOrderCredit) {
                return false;
            }
            // 2017/10/13 S21_NA#21267 Add End

        // 2018/07/20 S21_NA#26188 Del Start
        //} else if (ORD_ENTRY_ACT.SERVICE_PRICING.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_2.equals(ordEntryActCd)) {
        //    if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
        //        return false;
        //    }
        //    // 2017/10/13 S21_NA#21267 Add Start
        //    if (isOrderCredit) {
        //        return false;
        //    }
            // 2017/10/13 S21_NA#21267 Add End
        // 2018/07/20 S21_NA#26188 Del End

        } else if (ORD_ENTRY_ACT.SERVICE_PRICING_NEW.equals(ordEntryActCd) || ORD_ENTRY_ACT.SERVICE_PRICING_NEW_2.equals(ordEntryActCd)) { // Add S21_NA#17530
            if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
                return false;
            }
            // 2017/10/13 S21_NA#21267 Add Start
            if (isOrderCredit) {
                return false;
            }
            // 2017/10/13 S21_NA#21267 Add End

        } else if (ORD_ENTRY_ACT.CANCEL_2.equals(ordEntryActCd)) {// Line Cancel

            // 2017/10/13 S21_NA#21267 Add Start
            if (isOrderCredit) {
                return false;
            }
            // 2017/10/13 S21_NA#21267 Add End

            // 2018/03/20 S21_NA#24853 Del Start
//            // 2016/10/13 S21_NA#7700 Add Start
//            if (!chkCancelAvailable(scrnMsg)) {
//                return false;
//            }
//            // 2016/10/13 S21_NA#7700 Add End
            // 2018/03/20 S21_NA#24853 Del End

            // 2016/02/28 S21_NA#1738 Mod Start
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
                return false;
            }
            // 2016/02/28 S21_NA#1738 Mod End
        } else if (ORD_ENTRY_ACT.DELETE.equals(ordEntryActCd)) {
            // 2016/02/28 S21_NA#1738 Add Start
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }
            // 2016/02/28 S21_NA#1738 Add End

        } else if (ORD_ENTRY_ACT.ORDER_LINE_COPY.equals(ordEntryActCd)) {
            // 2016/02/28 S21_NA#1738 Mod Start
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }
            // 2016/02/28 S21_NA#1738 Mod End

        } else if (ORD_ENTRY_ACT.COPY_FROM.equals(ordEntryActCd)) { // 2017/10/13 S21_NA#21267 Add Start
            if (isOrderCredit) { 
                return false;
            }
            // 2017/10/13 S21_NA#21267 Add End

        } else if (ORD_ENTRY_ACT.SUPPLY_AUTO_ADD.equals(ordEntryActCd)) {
            if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
                return false;
            }
            // 2016/02/28 S21_NA#1738 Mod Start
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }
            // 2016/02/28 S21_NA#1738 Mod End

        } else if (ORD_ENTRY_ACT.RMA_AUTO_ADD.equals(ordEntryActCd)) {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                return false;
            }
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }

        } else if (ORD_ENTRY_ACT.MASS_UPDATE.equals(ordEntryActCd)) {
            // 2016/02/28 S21_NA#1738 Mod Start
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }
            // 2016/02/28 S21_NA#1738 Mod End

            // S21_NA#6442 Add Start
        } else if (ORD_ENTRY_ACT.PRICE_CHANGE.equals(ordEntryActCd)) {
            if (CPO_SRC_TP.CHANGE_ORDER_MODIFICATION.equals(scrnMsg.cpoSrcTpCd.getValue())) {
                scrnMsg.ordEntryActCd_IF.setErrorInfo(1, NWAM0855E);
                return false;
            }
            // S21_NA#6442 Add End
            
        } else if (ORD_ENTRY_ACT.ALL_COLLAPSE.equals(ordEntryActCd)) {

        } else if (ORD_ENTRY_ACT.EXPAND_ALL.equals(ordEntryActCd)) {

        } else if (ORD_ENTRY_ACT.OUTBOUND_CONFIG_SELECT_ALL.equals(ordEntryActCd)) {

        } else if (ORD_ENTRY_ACT.LINES_SELECT_ALL.equals(ordEntryActCd)) {

        } else if (ORD_ENTRY_ACT.BUYOUT.equals(ordEntryActCd)) {
            // 2017/10/13 S21_NA#21267 Add Start
            if (isOrderCredit) {
                return false;
            }
            // 2017/10/13 S21_NA#21267 Add End

        } else if (ORD_ENTRY_ACT.ADDITIONAL_LINE_INFORMATION.equals(ordEntryActCd)) {

        } else if (ORD_ENTRY_ACT.CONTROL_FIELDS.equals(ordEntryActCd)) {
            if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
                return false;
            }

        } else if (ORD_ENTRY_ACT.WH_INVENTORY_INQUIRY.equals(ordEntryActCd)) {
            if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
                return false;
            } else if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
                return false;
            }

        } else if (ORD_ENTRY_ACT.PRICE_CHANGE.equals(ordEntryActCd)) {

        } else if (ORD_ENTRY_ACT.FINAL_METER.equals(ordEntryActCd)) {
            if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
                return false;
            } else if (!ZYPCommonFunc.hasValue(hdrStsNm) || HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }
        // 2016/10/13 S21_NA#7700 Add Start
        } else if (ORD_ENTRY_ACT.ADD_CONFIG.equals(ordEntryActCd)) {
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }
        } else if (ORD_ENTRY_ACT.ADD_LINE.equals(ordEntryActCd)) {
            if (HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm) //
                    || isOrderCredit) { // // 2017/10/13 S21_NA#21267 Add isOrderCredit
                return false;
            }
        // 2016/10/13 S21_NA#7700 Add End
        } else if (ORD_ENTRY_ACT.LINE_FILTER.equals(ordEntryActCd)) {   // 2017/08/07 Sol#249 Add

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        } else if (ORD_ENTRY_ACT.SHOW_1ST_CONFIG.equals(ordEntryActCd)) {
            if (!ZYPCommonFunc.hasValue(hdrStsNm)) {
                return false;
            }
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        }

        return true;
    }
    // 2016/10/13 S21_NA#7700 Add Start
    private static boolean checkEventForEdtblFlg(NWAL1500BMsg scrnMsg, String ordEntryActCd) {
        if (ZYPCommonFunc.hasValue(scrnMsg.ordEntryScrEdtblFlg)
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.ordEntryScrEdtblFlg.getValue())) {
            if (ORD_ENTRY_ACT.ORDER_COPY.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.CHANGE_ORDER_MODIFICATION.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.PRICING.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.DELETE.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.ORDER_LINE_COPY.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.COPY_FROM.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.PRICING_2.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.SUPPLY_AUTO_ADD.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.RMA_AUTO_ADD.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.MASS_UPDATE.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.ADD_CONFIG.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.ADD_LINE.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.FINAL_METER.equals(ordEntryActCd)) {

                return false;
            }
        }

        return true;

    }
    // 2016/10/13 S21_NA#7700 Add End
    // 2016/10/19 S21_NA#14728 Add Start
    private static boolean checkEventByAuthority(NWAL1500BMsg scrnMsg, String ordEntryActCd) {

        if (NWAL1500CommonLogic.isOnlyReferenceAuthority(scrnMsg)) {
            if (ORD_ENTRY_ACT.CANCEL.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.ORDER_COPY.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.CHANGE_ORDER_MODIFICATION.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.PRICING.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.CANCEL_2.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.DELETE.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.ORDER_LINE_COPY.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.COPY_FROM.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.PRICING_2.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.SUPPLY_AUTO_ADD.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.RMA_AUTO_ADD.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.MASS_UPDATE.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.ADD_CONFIG.equals(ordEntryActCd)
                    || ORD_ENTRY_ACT.ADD_LINE.equals(ordEntryActCd)) {

                return false;
            }
        }
        return true;

    }
    // 2016/10/19 S21_NA#14728 Add End
    /**
     * Get OrdEntryActNm
     * @param scrnMsg NWAL1500BMsg
     * @param ordEntryActCd String
     * @return String
     */
    public static String getOrdEntryActNm(NWAL1500BMsg scrnMsg, String ordEntryActCd) {
        for (int i = 0; i < scrnMsg.ordEntryActCd_HA.length(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.ordEntryActCd_HA.no(i).getValue())) {
                break;
            } else if (ordEntryActCd.equals(scrnMsg.ordEntryActCd_HA.no(i).getValue())) {
                return scrnMsg.ordEntryActDescTxt_HA.no(i).getValue();
            }
        }
        for (int i = 0; i < scrnMsg.ordEntryActCd_HI.length(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.ordEntryActCd_HI.no(i).getValue())) {
                break;
            } else if (ordEntryActCd.equals(scrnMsg.ordEntryActCd_HI.no(i).getValue())) {
                return scrnMsg.ordEntryActDescTxt_HI.no(i).getValue();
            }
        }
        for (int i = 0; i < scrnMsg.ordEntryActCd_HD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.ordEntryActCd_HD.no(i).getValue())) {
                break;
            } else if (ordEntryActCd.equals(scrnMsg.ordEntryActCd_HD.no(i).getValue())) {
                return scrnMsg.ordEntryActDescTxt_HD.no(i).getValue();
            }
        }
        for (int i = 0; i < scrnMsg.ordEntryActCd_HT.length(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.ordEntryActCd_HT.no(i).getValue())) {
                break;
            } else if (ordEntryActCd.equals(scrnMsg.ordEntryActCd_HT.no(i).getValue())) {
                return scrnMsg.ordEntryActDescTxt_HT.no(i).getValue();
            }
        }
        for (int i = 0; i < scrnMsg.ordEntryActCd_LA.length(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.ordEntryActCd_LA.no(i).getValue())) {
                break;
            } else if (ordEntryActCd.equals(scrnMsg.ordEntryActCd_LA.no(i).getValue())) {
                return scrnMsg.ordEntryActDescTxt_LA.no(i).getValue();
            }
        }
        for (int i = 0; i < scrnMsg.ordEntryActCd_LI.length(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.ordEntryActCd_LI.no(i).getValue())) {
                break;
            } else if (ordEntryActCd.equals(scrnMsg.ordEntryActCd_LI.no(i).getValue())) {
                return scrnMsg.ordEntryActDescTxt_LI.no(i).getValue();
            }
        }
        for (int i = 0; i < scrnMsg.ordEntryActCd_LD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.ordEntryActCd_LD.no(i).getValue())) {
                break;
            } else if (ordEntryActCd.equals(scrnMsg.ordEntryActCd_LD.no(i).getValue())) {
                return scrnMsg.ordEntryActDescTxt_LD.no(i).getValue();
            }
        }
        for (int i = 0; i < scrnMsg.ordEntryActCd_LT.length(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.ordEntryActCd_LT.no(i).getValue())) {
                break;
            } else if (ordEntryActCd.equals(scrnMsg.ordEntryActCd_LT.no(i).getValue())) {
                return scrnMsg.ordEntryActDescTxt_LT.no(i).getValue();
            }
        }
        return "";
    }

    public static void resetAction(NWAL1500BMsg scrnMsg) {
        scrnMsg.ordEntryActCd_AC.clear();
        scrnMsg.ordEntryActCd_IF.clear();
        scrnMsg.ordEntryActCd_DL.clear();
        scrnMsg.ordEntryActCd_TO.clear();
    }
    
    /**
     * configLineAllSelect
     * @param scrnMsg NWAL1500BMsg
     * @param chkBoxVal String
     */
    private static void configLineAllSelect(NWAL1500BMsg scrnMsg, String chkBoxVal) {
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!scrnMsg.A.no(i).xxChkBox_LC.isInputProtected()) {
                    if (ZYPCommonFunc.hasValue(chkBoxVal)) {
                        // 2017/08/07 Sol#249 Mod Start
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_LC, chkBoxVal);
                        if (!S21StringUtil.isEquals(scrnMsg.A.no(i).xxResFltrFlg_LC.getValue(), ZYPConstant.FLG_ON_Y)) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).xxChkBox_LC, chkBoxVal);
                        } else {
                            scrnMsg.A.no(i).xxChkBox_LC.clear();
                        }
                        // 2017/08/07 Sol#249 Mod End
                    } else {
                        scrnMsg.A.no(i).xxChkBox_LC.clear();
                    }
                }
            }
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if (!scrnMsg.C.no(i).xxChkBox_RC.isInputProtected()) {
                    if (ZYPCommonFunc.hasValue(chkBoxVal)) {
                        // 2017/08/07 Sol#249 Mod Start
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(i).xxChkBox_RC, chkBoxVal);
                        if (!S21StringUtil.isEquals(scrnMsg.C.no(i).xxResFltrFlg_RC.getValue(), ZYPConstant.FLG_ON_Y)) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(i).xxChkBox_RC, chkBoxVal);
                        } else {
                            scrnMsg.C.no(i).xxChkBox_RC.clear();
                        }
                        // 2017/08/07 Sol#249 Mod End
                    } else {
                        scrnMsg.C.no(i).xxChkBox_RC.clear();
                    }
                }
            }
        }
    }

    /**
     * lineAllSelect
     * @param scrnMsg NWAL1500BMsg
     * @param chkBoxVal String
     */
    private static void lineAllSelect(NWAL1500BMsg scrnMsg, String chkBoxVal) {
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (!scrnMsg.B.no(i).xxChkBox_LL.isInputProtected()) {
                    if (ZYPCommonFunc.hasValue(chkBoxVal)) {
                        // 2017/08/07 Sol#249 Mod Start
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxChkBox_LL, chkBoxVal);
                        if (!S21StringUtil.isEquals(scrnMsg.B.no(i).xxResFltrFlg_LL.getValue(), ZYPConstant.FLG_ON_Y)) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).xxChkBox_LL, chkBoxVal);
                        } else {
                            scrnMsg.B.no(i).xxChkBox_LL.clear();
                        }
                        // 2017/08/07 Sol#249 Mod End
                    } else {
                        scrnMsg.B.no(i).xxChkBox_LL.clear();
                    }
                }
            } 
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                if (!scrnMsg.D.no(i).xxChkBox_RL.isInputProtected()) {
                    if (ZYPCommonFunc.hasValue(chkBoxVal)) {
                        // 2017/08/07 Sol#249 Mod Start
//                        ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).xxChkBox_RL, chkBoxVal);
                        if (!S21StringUtil.isEquals(scrnMsg.D.no(i).xxResFltrFlg_RL.getValue(), ZYPConstant.FLG_ON_Y)) {
                            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).xxChkBox_RL, chkBoxVal);
                        } else {
                            scrnMsg.D.no(i).xxChkBox_RL.clear();
                        }
                        // 2017/08/07 Sol#249 Mod End
                    } else {
                        scrnMsg.D.no(i).xxChkBox_RL.clear();
                    }
                }
            }
        }
    }

    /**
     * isCreditRebillLine
     * @param lineMsg NWAL1500_BBMsg
     * @return boolean
     */
    private static boolean isCreditRebillLine(NWAL1500_BBMsg lineMsg) {
        // 2017/11/07 S21_NA#22351 Mod Start
//        if (CR_REBIL.CREDIT.equals(lineMsg.crRebilCd_LL.getValue()) || CR_REBIL.REBILL.equals(lineMsg.crRebilCd_LL.getValue())) {
        if (CR_REBIL.CREDIT.equals(lineMsg.crRebilCd_LL.getValue())) { // 2017/11/07 S21_NA#22351 Mod End
            return true;
        }
        return false;
    }

    /**
     * isCreditRebillLine
     * @param lineMsg NWAL1500_BBMsg
     * @return boolean
     */
    private static boolean isCreditRebillLine(NWAL1500_DBMsg lineMsg) {
        if (CR_REBIL.CREDIT.equals(lineMsg.crRebilCd_RL.getValue()) || CR_REBIL.REBILL.equals(lineMsg.crRebilCd_RL.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * isCreditRebillLine
     * @param scrnMsg NWAL1500BMsg
     * @return boolean
     */
    private static boolean containCreditRebillLine(NWAL1500BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (isCreditRebillLine(scrnMsg.B.no(i))) {
                return true;
            }

        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            if (isCreditRebillLine(scrnMsg.D.no(i))) {
                return true;
            }

        }
        return false;
    }

    // 2016/09/20 S21_NA#8279 Add Start
    /**
     * <pre>
     * Clear delete Action Status.
     * @param scrnMsg Screen Message
     * </pre>
     */
    public static void clearDeleteActionStatus(NWAL1500BMsg scrnMsg) {

        String ordEntryActCd = scrnMsg.ordEntryActCd.getValue();
        if (!S21StringUtil.isEquals(ORD_ENTRY_ACT.DELETE, ordEntryActCd) //
                && !S21StringUtil.isEquals(ORD_ENTRY_ACT.CANCEL, ordEntryActCd)) {
            scrnMsg.xxYesNoCd_CA.setValue(ZYPConstant.FLG_OFF_N);
        }
    }
    // 2016/09/20 S21_NA#8279 Add End

    // 2017/08/07 Sol#249 Add Start
    /**
     * <pre>
     * Clear Line Filter Action Status.
     * @param scrnMsg Screen Message
     * </pre>
     */
    public static void clearLineFilterActionStatus(NWAL1500BMsg scrnMsg) {

        if (!S21StringUtil.isEquals(ORD_ENTRY_ACT.LINE_FILTER, scrnMsg.ordEntryActCd.getValue())) {
            scrnMsg.xxYesNoCd_FP.setValue(ZYPConstant.FLG_OFF_N);
        }
    }
    // 2017/08/07 Sol#249 Add End

    // 2016/10/13 S21_NA#7700 Add Start
    /**
     * <pre>
     * Check Cancel Available.
     * @param scrnMsg Screen Message
     * </pre>
     */
    public static boolean chkCancelAvailable(NWAL1500BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxDplyCtrlFlg_CA)
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.xxDplyCtrlFlg_CA.getValue())) {
            return false;
        }
        return true;
    }
    // 2016/10/13 S21_NA#7700 Add End

    // 2018/05/10 S21_NA#25251 Add Start
    private static boolean hasPriceChangeAuthForOutbound(NWAL1500BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            if (S21StringUtil.isEquals(MOD_PRICE_OUTBOUND, scrnMsg.L.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasPriceChangeAuthForInbound(NWAL1500BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.L.getValidCount(); i++) {
            if (S21StringUtil.isEquals(MOD_PRICE_INBOUND, scrnMsg.L.no(i).xxFuncId.getValue())) {
                return true;
            }
        }
        return false;
    }
    // 2018/05/10 S21_NA#25251 Add End
    // 2018/06/14 S21_NA#25227 Add Start
    private static boolean isConfigSelectedForCancel(String configVals) {

        if (ZYPCommonFunc.hasValue(configVals)) {
            return !configVals.contains(PERIOD);
        }
        return false;
    }

    private static boolean isLineSelectedForCancel(String lineVals) {

        if (ZYPCommonFunc.hasValue(lineVals)) {
            return lineVals.contains(PERIOD);
        }
        return false;
    }
    // 2018/06/14 S21_NA#25227 Add End

    // Add Start 2019/05/09 QC#50015
    private static StringBuilder getDocTpConstVal(StringBuilder docTpCd, String constKeyVal, String glblCmpyCd) {

        if (docTpCd.length() > 0) {
            docTpCd.append(COMMA);
        }
        String docTpConstVal = ZYPCodeDataUtil.getVarCharConstValue(constKeyVal, glblCmpyCd);
        docTpCd.append(docTpConstVal);

        return docTpCd;
    }
    // Add End 2019/05/09 QC#50015
}
