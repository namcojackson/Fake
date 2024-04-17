/*
 * Copyright(c)2015 Canon USA Inc. All rights reserved.
 */
package business.servlet.NWAL1500.common;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_BOOK_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_ADD_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_ADD_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_ADD_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_APR_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_APR_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_APR_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_CFG_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_CFG_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_CFG_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.BTN_PRICING_EVENT_NM;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.COMMA;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.DEF_DROP_SHIP_RTL_WH_CD;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.EIGHT_DIGIT_MODE;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.FRT_COND_CD_COLLECT;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_0;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_1;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_10;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_12;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_13;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_2;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_20;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_3;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_30;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_33;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_4;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_5;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_50;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_6;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_7;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_8;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_9;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.NLCL1000_DROP_SHIP_RTL_WH_CD;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.NO_SLCT_DTL;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.PERCENT;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.SPACE;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_ADDL_HEADER;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_HEADER;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TEN_DIGIT_MODE;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0368W;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0779W;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0953E;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_BOOKED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CANCELLED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.HEADER_STS_NM_CLOSED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.LINE_STS_NM_CANCELLED;
import static business.servlet.NWAL1500.constant.NWAL1500StatusConstant.REF_AUTH;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_BILL;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static com.canon.cusa.s21.common.NWX.NWXC150001.constant.NWXC150001ConstantsForOtherBiz.NMAL6760_STATUS_CD_ACTIVE;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import parts.common.EZDBItem;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDMsg;
import parts.servletcommon.EZDCommonHandler;
import business.db.ORD_TAKE_MDSETMsg;
import business.servlet.NWAL1500.NWAL1500BMsg;
import business.servlet.NWAL1500.NWAL1500_ABMsg;
import business.servlet.NWAL1500.NWAL1500_BBMsg;
import business.servlet.NWAL1500.NWAL1500_BBMsgArray;
import business.servlet.NWAL1500.NWAL1500_CBMsg;
import business.servlet.NWAL1500.NWAL1500_DBMsg;
import business.servlet.NWAL1500.NWAL1500_FBMsg;
import business.servlet.NWAL1500.NWAL1500_GBMsg;
import business.servlet.NWAL1500.NWAL1500_HBMsg;
import business.servlet.NWAL1500.NWAL1500_MBMsgArray;
import business.servlet.NWAL1500.NWAL1500_OBMsg;
import business.servlet.NWAL1600.constant.NWAL1600Constant;
import business.servlet.NWAL1610.constant.NWAL1610Constant;
import business.servlet.NWAL1620.constant.NWAL1620Constant;
import business.servlet.NWAL1630.constant.NWAL1630Constant;
import business.servlet.NWAL1640.constant.NWAL1640Constant;
import business.servlet.NWAL1650.constant.NWAL1650Constant;
import business.servlet.NWAL1790.constant.NWAL1790Constant;
import business.servlet.NWAL1810.constant.NWAL1810Constant;
import business.servlet.NWAL1890.constant.NWAL1890Constant;

import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/01   Fujitsu         T.Yoshida       Create          N/A
 * 2016/01/13   Fujitsu         T.Ishii         Update          S21_NA#2548
 * 2016/01/14   Fujitsu         T.Ishii         Update          S21_NA#2557
 * 2016/02/08   Fujitsu         M.Hara          Update          QC#1615
 * 2016/02/09   Fujitsu         M.Hara          Update          QC#1679
 * 2016/02/15   Fujitsu         M.Hara          Update          QC#1692
 * 2016/02/18   Fujitsu         T.Ishii         Update          QC#1634
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 * 2016/02/22   Fujitsu         Y.Taoka         Update          S21_NA#1694
 * 2016/02/22   Fujitsu         M.Ohno          Update          QC#3243
 * 2016/02/23   Fujitsu         Y.Taoka         Update          S21_NA#1694/S21_NA#1699/S21_NA#1620
 * 2016/02/24   Fujitsu         K.Sato          Update          S21_NA#3239
 * 2016/02/25   Fujitsu         K.Sato          Update          S21_NA#1729
 * 2016/02/29   Fujitsu         K.Sato          Update          S21_NA#1738
 * 2016/03/01   Fujitsu         T.Ishii         Update          S21_NA#1623
 * 2016/03/02   Fujitsu         K.Sato          Update          S21_NA#4377
 * 2016/03/05   Fujitsu         S.Yamamoto      Update          S21_NA#1698
 * 2016/03/11   Fujitsu         S.TaKami        Update          S21_NA#4389
 * 2016/03/25   Fujitsu         S.TaKami        Update          S21_NA#4693
 * 2016/03/30   Fujitsu         S.Takami        Update          S21_NA#5523
 * 2016/04/06   Fujitsu         T.Ishii         Update          S21_NA#5283
 * 2016/04/08   Fujitsu         S.Takami        Update          S21_NA#5356
 * 2016/04/14   Fujitsu         S.Takami        Update          S21_NA#6937
 * 2016/05/09   Fujitsu         S.Takami        Update          (NMAL6760 Calling Mode)
 * 2016/05/11   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/05/18   Fujitsu         T.Murai         Update          S21_NA#5337
 * 2016/05/20   Fujitsu         M.Hara          Update          S21_NA#8544
 * 2016/05/26   Fujitsu         T.Murai         Update          S21_NA#8393
 * 2016/05/31   Fujitsu         M.Yamada        Update          S21_NA#9221
 * 2016/06/01   Fujitsu         T.Murai         Update          S21_NA#9189
 * 2016/06/10   Fujitsu         S.Takami        Update          S21_NA#8912
 * 2016/06/16   Fujitsu         S.Takami        Update          S21_NA#9855
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/07/02   Fujitsu         M.Hara          Update          S21_NA#1698,3235
 * 2016/07/05   Fujitsu         M.Hara          Update          S21_NA#7441
 * 2016/07/21   Fujitsu         S.Takami        Update          S21_NA#9228
 * 2016/07/25   Fujitsu         S.Takami        Update          S21_NA#5062, 9789
 * 2016/07/26   Fujitsu         M.Hara          Update          S21_NA#8412
 * 2016/08/01   Fujitsu         M.Hara          Update          S21_NA#6148
 * 2016/08/09   Fujitsu         Y.Taoka         Update          S21_NA#8388
 * 2016/08/23   Hitachi         Y.Takeno        Update          S21_NA#12024
 * 2016/08/26   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2016/09/14   Fujitsu         R.Nakamura      Update          QC#11614
 * 2016/09/28   Fujitsu         Y.Taoka         Update          S21_NA#9761
 * 2016/09/29   Fujitsu         M.Ohno          Update          S21_NA#12684
 * 2016/10/13   Fujitsu         N.Sugiura       Update          S21_NA#7700
 * 2016/10/19   Fujitsu         N.Sugiura       Update          S21_NA#14728
 * 2016/10/21   Fujitsu         S.Iidaka        Update          S21_NA#14607
 * 2016/10/25   Fujitsu         S.Takami        Update          S21_NA#9705
 * 2017/06/14   Fujitsu         S.Takami        Update          S21_NA#18623
 * 2017/08/07   Fujitsu         T.Ogura         Update          Sol#249
 * 2017/09/21   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 * 2017/09/22   Fujitsu         T.Ogura         Update          S21_NA#18859(Sol#154)
 * 2017/10/13   Fujitsu         S.Takami        Update          S21_NA#21267
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2017/11/16   Fujitsu         T.Aoi           Update          S21_NA#22620
 * 2017/11/17   Fujitsu         A.Kosai         Update          S21_NA#22252
 * 2017/11/24   Fujitsu         Y.Kanefusa      Update          S21_NA#22789
 * 2017/12/07   Fujitsu         A.Kosai         Update          S21_NA#21621
 * 2017/12/12   Fujitsu         N.Sugiura       Update          S21_NA#20164
 * 2018/01/26   Fujitsu         K.Ishizuka      Update          S21_NA#23140
 * 2018/02/01   Fujitsu         S.Takami        Update          S21_NA#19808
 * 2018/04/02   Fujitsu         S.Takami        Update          S21_NA#25043
 * 2018/04/03   Fujitsu         K.Ishizuka      Update          S21_NA#24982
 * 2018/04/04   Fujitsu         S.Takami        Update          S21_NA#25152
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#21139
 * 2018/06/29   Fujitsu         M.Ishii         Update          S21_NA#25441
 * 2018/07/03   Fujitsu         A.Kosai         Update          S21_NA#26908
 * 2018/07/03   Fujitsu         H.Kumagai       Update          QC#26932
 * 2018/07/17   Fujitsu         A.Kosai         Update          S21_NA#27073
 * 2018/07/27   Fujitsu         T.Noguchi       Update          S21_NA#14307
 * 2018/10/16   Fujitsu         K.Ishizuka      Update          S21_NA#28712
 * 2018/10/23   Fujitsu         M.Ohno          Update          S21_NA#28425
 * 2018/10/23   Fujitsu         M.Ohno          Update          S21_NA#28426
 * 2018/12/12   Fujitsu         S.Kosaka        Update          QC#29315
 * 2019/01/09   Fujitsu         K.Ishizuka      Update          S21_NA#29789
 * 2019/01/23   Fujitsu         M.Ishii         Update          S21_NA#29996
 * 2019/01/23   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 * 2019/07/11   Fujitsu         T.Noguchi       Update          S21_NA#51287
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2023/06/06   Hitachi         T.Doi           Update          CSA-QC#61465
 * 2024/02/26   CITS            T.Miki          Update          CSA QC#63577
 *</pre>
 */
public class NWAL1500CommonLogic {

    /**
     * <pre>
     * Initial Common Button
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NWAL1500BMsg
     */
    public static void initCommonButton(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) { // 2017/10/13 S21_NA#21267 Add param, scrnMsg
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 1, null);
        if (scrnMsg != null //
                && isOrderCredit(scrnMsg)) {
            handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        }
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APR_BTN_NM, BTN_CMN_APR_EVENT_NM, BTN_CMN_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);

        handler.setButtonProperties(BTN_CMN_CFG_BTN_NM, BTN_CMN_CFG_EVENT_NM, BTN_CMN_CFG_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ADD_BTN_NM, BTN_CMN_ADD_EVENT_NM, BTN_CMN_ADD_LABEL, 0, null);

        handler.setButtonEnabled(BTN_BOOK_EVENT_NM, true);
       // 2024/02/26 CSA QC#63577 Add Start
        handler.setButtonEnabled(BTN_PRICING_EVENT_NM, true);
       // 2024/02/26 CSA QC#63577 Add End
    }

    /**
     * <pre>
     * The pop up parameter is cleared.
     * </pre>
     * @param scrnMsg Screen Msg
     */
    public static void clearPopUpParam(NWAL1500BMsg scrnMsg) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();

        // Add Start NA QC#2177
        scrnMsg.dsOrdPosnNum_P1.clear();
        scrnMsg.dsCpoLineNum_P1.clear();
        // Add End NA QC#2177
    }

    /**
     * <pre>
     * Active Add Button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void activeAddButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CFG_BTN_NM, BTN_CMN_CFG_EVENT_NM, BTN_CMN_CFG_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_ADD_BTN_NM, BTN_CMN_ADD_EVENT_NM, BTN_CMN_ADD_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 1, null); // S21_NA#1623
    }

    /**
     * <pre>
     * Inactive Add Button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void inactiveAddButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_CFG_BTN_NM, BTN_CMN_CFG_EVENT_NM, BTN_CMN_CFG_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_ADD_BTN_NM, BTN_CMN_ADD_EVENT_NM, BTN_CMN_ADD_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 0, null); // S21_NA#1623
    }

    // 2016/10/19 S21_NA#14728 Mod Start
    /**
     * <pre>
     * Active Registration Button
     * </pre>
     * @param handler EZCommandHandler
     * @param scrnMsg NWAL1500BMsg
     */
    public static void activeRegistrationButton(EZDCommonHandler handler, NWAL1500BMsg scrnMsg) {

        if (!NWAL1500CommonLogic.isOnlyReferenceAuthority(scrnMsg)) {
            handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 1, null);
            // 2017/10/13 S21_NA#21267 Add Start
            if (scrnMsg != null && NWAL1500CommonLogic.isOrderCredit(scrnMsg)) {
                handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
            }
            // 2017/10/13 S21_NA#21267 Add End
            handler.setButtonEnabled(BTN_BOOK_EVENT_NM, true);
            // 2024/02/26 CSA QC#63577 Add Start
            handler.setButtonEnabled(BTN_PRICING_EVENT_NM, true);
            // 2024/02/26 CSA QC#63577 Add End
        }

    }
    // 2016/10/19 S21_NA#14728 Mod End

    /**
     * <pre>
     * Inactive Registration Button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void inactiveRegistrationButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
//        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
        handler.setButtonEnabled(BTN_BOOK_EVENT_NM, false);
        // 2024/02/26 CSA QC#63577 Add Start
        handler.setButtonEnabled(BTN_PRICING_EVENT_NM, false);
        // 2024/02/26 CSA QC#63577 Add End
    }

    /**
     * <pre>
     * Check Item
     * </pre>
     * @param scrnMsg Screen Msg
     * @param chkWh Check Warehouse
     */
    public static void addCheckItemBizLayerErr(NWAL1500BMsg scrnMsg, boolean chkWh) {
        addCheckItemBizLayerErr(scrnMsg, chkWh, true);
    }

    /**
     * <pre>
     * Check Item
     * </pre>
     * @param scrnMsg Screen Msg
     * @param chkWh Check Warehouse
     * @param chkConfigId Check Config ID
     */
    public static void addCheckItemBizLayerErr(NWAL1500BMsg scrnMsg, boolean chkWh, boolean chkConfigId) { //2018/03/27 S21_NA#25043 Add

        // Header
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.dsOrdRsnCd);

        String origxxDplyTab = scrnMsg.xxDplyTab.getValue();
        scrnMsg.xxDplyTab.clear();
        Set<EZDBItem> itemList = new LinkedHashSet<EZDBItem>();

        // Header Tab
        itemList.add(scrnMsg.billToCustAcctNm);
        itemList.add(scrnMsg.billToCustAcctCd);
        itemList.add(scrnMsg.billToCustCd);
        itemList.add(scrnMsg.shipToCustAcctNm);
        itemList.add(scrnMsg.shipToCustAcctCd);
        itemList.add(scrnMsg.shipToCustCd);
        itemList.add(scrnMsg.soldToCustAcctNm);
        itemList.add(scrnMsg.sellToCustCd);
        itemList.add(scrnMsg.sellToFirstRefCmntTxt); // 2017/12/12 S21_NA#20164 Add
        itemList.add(scrnMsg.soldToCustLocCd);
        itemList.add(scrnMsg.negoDealAmt);
        itemList.add(scrnMsg.slsRepTocNm);
        itemList.add(scrnMsg.psnNum); // 2016/05/11 S21_NA#7861 Mod
        itemList.add(scrnMsg.prcCatgNm);
        itemList.add(scrnMsg.custIssPoNum);
        itemList.add(scrnMsg.leaseCmpyPoNum);
        itemList.add(scrnMsg.custIssPoDt);
        itemList.add(scrnMsg.aquNum);
        // QC#1692
        itemList.add(scrnMsg.ordLogTpDescTxt_NM);
        itemList.add(scrnMsg.dsOrdTpDescTxt); // QC#5000
        itemList.add(scrnMsg.invCmntTxt); // 2019/01/23 S21_NA#29996 Add

        for (EZDBItem item : itemList) {
            scrnMsg.addCheckItem(item);
            if (item.isError()) {
                scrnMsg.xxDplyTab.setValue(TAB_HEADER);
            }
        }
        // 2016/02/25 S21_NA#1729 Add Start
        if (scrnMsg.getMessageInfo() != null) {
            if (chkWarningForHeaderTab(scrnMsg)) {
                scrnMsg.xxDplyTab.setValue(TAB_HEADER);
            }
        }
        // 2016/02/25 S21_NA#1729 Add End

        // Additional Header Tab
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
            itemList.add(scrnMsg.frtCondDescTxt);
            itemList.add(scrnMsg.shpgSvcLvlCd);
            itemList.add(scrnMsg.carrSvcLvlDescTxt);
            itemList.add(scrnMsg.carrAcctNum);
            itemList.add(scrnMsg.pmtTermCashDiscDescTxt);
            itemList.add(scrnMsg.dsPmtMethCd);
            itemList.add(scrnMsg.prePmtAmt);
            itemList.add(scrnMsg.addRddDt);
            itemList.add(scrnMsg.prcContrNum);
            itemList.add(scrnMsg.prcContrNm);
            itemList.add(scrnMsg.flPrcListCd);
            itemList.add(scrnMsg.flPrcListNm);
            itemList.add(scrnMsg.loanPerDaysAot);
            itemList.add(scrnMsg.csmpContrNum);
            itemList.add(scrnMsg.dlrRefNum);
            itemList.add(scrnMsg.ordSgnDt);
            itemList.add(scrnMsg.dclnSvcCd); // S21_NA#8388 ADD
            itemList.add(scrnMsg.leasePrchOptCd);
            itemList.add(scrnMsg.leaseTermMthAot);
            itemList.add(scrnMsg.leasePmtFreqCd);
            itemList.add(scrnMsg.leaseTotPmtAmt);
            itemList.add(scrnMsg.leaseTermMthAot_EM); // QC#22789 2017/11/28 Add
            itemList.add(scrnMsg.dsPmtMethCd); // 2016/07/11 S21_NA#9228 Add
            // 2017/11/02 S21_NA#20146 Add Start
            itemList.add(scrnMsg.psnNum_GS);
            itemList.add(scrnMsg.tocNm_GS);
            itemList.add(scrnMsg.coaExtnCd_GS);
            itemList.add(scrnMsg.coaExtnDescTxt_GS);
            itemList.add(scrnMsg.coaBrCd_GS);
            itemList.add(scrnMsg.coaBrDescTxt_GS);
            itemList.add(scrnMsg.coaCcCd_GS);
            itemList.add(scrnMsg.coaCcDescTxt_GS);
            // 2017/11/02 S21_NA#20146 Add End

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_ADDL_HEADER);
                }
            }
        }

        // Line Config Tab
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            itemList = new LinkedHashSet<EZDBItem>();
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                itemList.add(scrnMsg.A.no(i).xxChkBox_LC); // 2016/03/04 S21_NA#5000
                itemList.add(scrnMsg.A.no(i).configTpCd_LC);
                if (chkConfigId) { // 2018/04/02 S21_NA#25043 Add Condition
                    itemList.add(scrnMsg.A.no(i).svcConfigMstrPk_LC);
                } // 2018/04/02 S21_NA#25043 Add Condition
                itemList.add(scrnMsg.A.no(i).shipToCustAcctCd_LC);
                itemList.add(scrnMsg.A.no(i).shipToCustCd_LC);
                itemList.add(scrnMsg.A.no(i).shipToCustAcctNm_LC);
                itemList.add(scrnMsg.A.no(i).billToCustAcctCd_LC);
                itemList.add(scrnMsg.A.no(i).billToCustCd_LC);
                itemList.add(scrnMsg.A.no(i).billToCustAcctNm_LC);
                itemList.add(scrnMsg.A.no(i).mdlNm_LC);
                itemList.add(scrnMsg.A.no(i).dclnSvcCd_LC); // S21_NA#8388 ADD
                itemList.add(scrnMsg.A.no(i).csmpContrNum_LC); // 2016/08/26 S21_NA#9806 Add Start
                itemList.add(scrnMsg.A.no(i).dlrRefNum_LC); // 2016/08/26 S21_NA#9806 Add Start
            }

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (!ORD_LINE_STS.CANCELLED.equals(scrnMsg.B.no(i).ordLineStsCd_LL.getValue())) { // QC#1620
                    itemList.add(scrnMsg.B.no(i).xxChkBox_LL);
                    itemList.add(scrnMsg.B.no(i).mdseCd_LL);
                    itemList.add(scrnMsg.B.no(i).ordCustUomQty_LL);
                    itemList.add(scrnMsg.B.no(i).custUomCd_LL);
                    itemList.add(scrnMsg.B.no(i).entCpoDtlDealSlsAmt_LL);
                    itemList.add(scrnMsg.B.no(i).entDealNetUnitPrcAmt_LL);
                    itemList.add(scrnMsg.B.no(i).prcCatgNm_LL); // 2015/12/11 S21_NA#1783
                    itemList.add(scrnMsg.B.no(i).dsOrdLineCatgCd_LL);
                    itemList.add(scrnMsg.B.no(i).ordLineSrcCd_LL);
                    if (chkWh) {
                        itemList.add(scrnMsg.B.no(i).rtlWhNm_LL);
                    }
                    itemList.add(scrnMsg.B.no(i).rtlSwhNm_LL);
                    itemList.add(scrnMsg.B.no(i).flPrcListNm_LL); // S21_NA#2082
                    itemList.add(scrnMsg.B.no(i).funcUnitFlPrcAmt_LL); // QC#22372 2018/01/10 Add
                    itemList.add(scrnMsg.B.no(i).serNum_LL);
                    itemList.add(scrnMsg.B.no(i).sbstMdseCd_LL);
                    itemList.add(scrnMsg.B.no(i).dplyLineRefNum_LL);
                    itemList.add(scrnMsg.B.no(i).prcBaseDt_LL);
                    itemList.add(scrnMsg.B.no(i).custMdseCd_LL);
                    itemList.add(scrnMsg.B.no(i).rddDt_LL);
                    itemList.add(scrnMsg.B.no(i).supdLockFlg_LL);
                    itemList.add(scrnMsg.B.no(i).dplyLineRefNum_LL);

                    itemList.add(scrnMsg.B.no(i).crRebilCd_LL); // 2016/02/28 S21_NA#4291 Add
                }
            }

            for (EZDBItem item : itemList) {
                scrnMsg.addCheckItem(item);
                if (item.isError()) {
                    scrnMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                }
            }
            // 2016/02/23 S21_NA#3239 Add Start
            if (scrnMsg.getMessageInfo() != null) {
                if (scrnMsg.getMessageInfo().getCode().endsWith("W")) {
                    scrnMsg.xxDplyTab.setValue(TAB_LINE_CONFIG);
                }
            }
            // 2016/02/23 S21_NA#3239 Add End
        }

        // RMA Tab
        itemList = new LinkedHashSet<EZDBItem>();
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            itemList.add(scrnMsg.C.no(i).xxChkBox_RC); // 2016/07/25 S21_NA#5062, 9789 Add
            itemList.add(scrnMsg.C.no(i).configTpCd_RC);
            if (chkConfigId) { // 2018/04/02 S21_NA#25043 Add Condition
                itemList.add(scrnMsg.C.no(i).svcConfigMstrPk_RC);
            } // 2018/04/02 S21_NA#25043 Add Condition
            itemList.add(scrnMsg.C.no(i).shipToCustAcctCd_RC);
            itemList.add(scrnMsg.C.no(i).shipToCustCd_RC);
            itemList.add(scrnMsg.C.no(i).shipToCustAcctNm_RC);
            itemList.add(scrnMsg.C.no(i).billToCustAcctCd_RC);
            itemList.add(scrnMsg.C.no(i).billToCustCd_RC);
            itemList.add(scrnMsg.C.no(i).billToCustAcctNm_RC);
            itemList.add(scrnMsg.C.no(i).mdlNm_RC);
            itemList.add(scrnMsg.C.no(i).csmpContrNum_RC); // 2016/08/26 S21_NA#9806 Add Start
            itemList.add(scrnMsg.C.no(i).dlrRefNum_RC); // 2016/08/26 S21_NA#9806 Add Start
        }

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            if (!RTRN_LINE_STS.CANCELLED.equals(scrnMsg.D.no(i).ordLineStsCd_RL.getValue())) { // QC#1620
                itemList.add(scrnMsg.D.no(i).mdseCd_RL);
                itemList.add(scrnMsg.D.no(i).ordCustUomQty_RL);
                itemList.add(scrnMsg.D.no(i).custUomCd_RL);
                itemList.add(scrnMsg.D.no(i).entCpoDtlDealSlsAmt_RL);
                itemList.add(scrnMsg.D.no(i).entDealNetUnitPrcAmt_RL);
                itemList.add(scrnMsg.D.no(i).prcCatgNm_RL);
                itemList.add(scrnMsg.D.no(i).dsOrdLineCatgCd_RL);
                if (chkWh) { // 2016/06/16 S21_NA#9855
                    itemList.add(scrnMsg.D.no(i).rtlWhNm_RL);
                } // 2016/06/16 S21_NA#9855
                itemList.add(scrnMsg.D.no(i).rtlSwhNm_RL);
                itemList.add(scrnMsg.D.no(i).serNum_RL);
                itemList.add(scrnMsg.D.no(i).flPrcListNm_RL); // S21_NA#2082
                itemList.add(scrnMsg.D.no(i).funcUnitFlPrcAmt_RL); // QC#22372 2018/01/10 Add
                itemList.add(scrnMsg.D.no(i).dplyLineRefNum_RL);
                itemList.add(scrnMsg.D.no(i).prcBaseDt_RL);
                itemList.add(scrnMsg.D.no(i).rqstPickUpDt_RL);

                itemList.add(scrnMsg.D.no(i).hddRmvCd_RL); // 2016/02/28 S21_NA#4291 Add
                itemList.add(scrnMsg.D.no(i).rtrnRsnCd_RL); // 2016/02/28 S21_NA#4291 Add
                itemList.add(scrnMsg.D.no(i).thirdPtyDspTpCd_RL); // 2016/02/28 S21_NA#4291 Add
                itemList.add(scrnMsg.D.no(i).serNum_RL); // 2016/04/08 S21_NA#5356 Add Start
            }
        }

        for (EZDBItem item : itemList) {
            scrnMsg.addCheckItem(item);
            if (item.isError()) {
                scrnMsg.xxDplyTab.setValue(TAB_RMA);
            }
        }
        // 2016/02/23 S21_NA#3239 Add Start
        if (scrnMsg.getMessageInfo() != null) {
            if (scrnMsg.getMessageInfo().getCode().endsWith("W")) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
                    scrnMsg.xxDplyTab.setValue(TAB_RMA);
                }
            }
        }
        scrnMsg.addCheckItem(scrnMsg.ordEntryActCd_AC); // 2016/03/30 S21_NA#5523 Add
        scrnMsg.addCheckItem(scrnMsg.ordEntryActCd_IF); // 2016/10/25 S21_NA#9705 Add
        // 2016/02/23 S21_NA#3239 Add End
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            scrnMsg.xxDplyTab.setValue(origxxDplyTab);
        }
    }

    /**
     * Get Param NWAL1130 For Order Category
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 For Order Category
     */
    public static Object[] getParamNWAL1130ForOrdCatg(NWAL1500BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Order Category Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DOC.GLBL_CMPY_CD         AS GLBL_CMPY_CD");
        sb.append("   ,DOC.EZCANCELFLAG         AS EZCANCELFLAG");
        sb.append("   ,DOC.DS_ORD_CATG_CD       AS DS_ORD_CATG_CD");
        sb.append("   ,DOC.DS_ORD_CATG_DESC_TXT AS DS_ORD_CATG_DESC_TXT");
        sb.append("   ,DOC.DS_ORD_CATG_SORT_NUM AS DS_ORD_CATG_SORT_NUM ");
        sb.append("FROM");
        sb.append("    DS_ORD_CATG DOC ");
        sb.append("WHERE");
        sb.append("    DOC.DS_ORD_CATG_CD IN (");
        sb.append("        SELECT");
        sb.append("            DOT.DS_ORD_CATG_CD AS DS_ORD_CATG_CD");
        sb.append("        FROM");
        sb.append("            AVAL_DS_ORD_TP_APP_ID AD");
        sb.append("           ,DS_ORD_TP             DOT");
        sb.append("        WHERE");
        sb.append("            AD.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("            AND AD.BIZ_APP_ID    = '").append(BIZ_ID).append("' ");
        sb.append("            AND AD.EZCANCELFLAG  = '0'");
        sb.append("            AND AD.GLBL_CMPY_CD  = DOT.GLBL_CMPY_CD");
        sb.append("            AND AD.DS_ORD_TP_CD  = DOT.DS_ORD_TP_CD");
        sb.append("            AND DOT.EZCANCELFLAG = '0'");
        sb.append("        GROUP BY");
        sb.append("            DOT.DS_ORD_CATG_CD )");
        sb.append("    AND DOC.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND DOC.EZCANCELFLAG = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Order Category Name";
        whereArray[IDX_1] = "DS_ORD_CATG_DESC_TXT";

        // S21_NA#8393 Mod Start
        // whereArray[IDX_2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdCatgDescTxt)) {
            whereArray[IDX_2] = scrnMsg.dsOrdCatgDescTxt.getValue();
        } else {
            whereArray[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End

        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Order Category Code";
        columnArray0[IDX_1] = "DS_ORD_CATG_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Category Name";
        columnArray1[IDX_1] = "DS_ORD_CATG_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "DS_ORD_CATG_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Order Reason
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 For Order Reason
     */
    public static Object[] getParamNWAL1130ForOrdRsn(NWAL1500BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Order Reason Search";

        // S21_NA#8544
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DOT.GLBL_CMPY_CD       AS GLBL_CMPY_CD");
        sb.append("   ,DOT.EZCANCELFLAG       AS EZCANCELFLAG");
        sb.append("   ,DOT.DS_ORD_TP_CD       AS DS_ORD_TP_CD");
        sb.append("   ,DOT.DS_ORD_TP_DESC_TXT AS DS_ORD_TP_DESC_TXT");
        sb.append("   ,DOT.DS_ORD_TP_SORT_NUM AS DS_ORD_TP_SORT_NUM ");
        sb.append("FROM");
        sb.append("    DS_ORD_TP DOT ");
        sb.append("    ,DS_ORD_TP_PROC_DFN  DOTPD ");
        sb.append("WHERE");
        sb.append("    DOT.GLBL_CMPY_CD       = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND DOT.DS_ORD_CATG_CD = '").append(scrnMsg.dsOrdCatgCd.getValue()).append("'");
        sb.append("    AND DOT.EZCANCELFLAG   = '0'");
        sb.append("    AND DOT.EZCANCELFLAG   = DOTPD.EZCANCELFLAG");
        sb.append("    AND DOT.GLBL_CMPY_CD   = DOTPD.GLBL_CMPY_CD");
        sb.append("    AND DOT.DS_ORD_TP_CD   = DOTPD.DS_ORD_TP_CD");
        sb.append("    AND DOTPD.ACTV_FLG     = 'Y'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Order Reason Name";
        whereArray[IDX_1] = "DS_ORD_TP_DESC_TXT";

        // S21_NA#8393 Mod Start
        // whereArray[IDX_2] = scrnMsg.dsOrdTpDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpDescTxt)) {
            whereArray[IDX_2] = scrnMsg.dsOrdTpDescTxt.getValue();
        } else {
            whereArray[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End

        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Order Reason Code";
        columnArray0[IDX_1] = "DS_ORD_TP_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Reason Name";
        columnArray1[IDX_1] = "DS_ORD_TP_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "DS_ORD_TP_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Carrier Service Level
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 Carrier Service Level
     */
    public static Object[] getParamNWAL1130ForCarrSvcLvl(NWAL1500BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Carrier Service Level Search";

        // 2018/12/12 QC#29315 Add Start
        String freitTermCode = scrnMsg.frtCondCd.getValue();
        // 2018/12/12 QC#29315 Add End

        StringBuilder sb = new StringBuilder();
        // 2018/12/12 QC#29315 Mod Start
        //sb.append("SELECT ");
        sb.append("SELECT DISTINCT ");
        // 2018/12/12 QC#29315 Mod Start
        sb.append("    CSL.EZCANCELFLAG ");
        sb.append("   ,CSL.GLBL_CMPY_CD ");
        sb.append("   ,CSL.CARR_SVC_LVL_CD ");
        sb.append("   ,CSL.CARR_SVC_LVL_DESC_TXT ");
        sb.append("   ,CSL.CARR_SVC_LVL_SORT_NUM ");
        sb.append("FROM  ");
        sb.append("    FRT_COND_SVC_LVL_RELN RELN ");
        sb.append("   ,DS_ORD_TP_PROC_DFN OTD ");
        sb.append("   ,CARR_SVC_LVL CSL ");
        // 2018/12/12 QC#29315 Add Start
        if (!FRT_COND_CD_COLLECT.equals(freitTermCode)) {
            sb.append("   ,SHPG_SVC_LVL_CARR_RELN SCRL ");
            sb.append("   ,(SELECT ");
            sb.append("         DC.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
            sb.append("        ,DC.VND_CD AS VND_CD ");
            sb.append("     FROM");
            sb.append("         DS_ACCT_CARR DC ");
            sb.append("        ,SHIP_TO_CUST SC ");
            sb.append("     WHERE ");
            sb.append("         SC.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("     AND SC.EZCANCELFLAG    = '0' ");
            sb.append("     AND SC.SHIP_TO_CUST_CD = '").append(scrnMsg.shipToCustCd.getValue()).append("' ");
            sb.append("     AND DC.GLBL_CMPY_CD    = SC.GLBL_CMPY_CD ");
            sb.append("     AND DC.DS_BIZ_AREA_CD  = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("     AND DC.LOC_NUM         = SC.LOC_NUM ");
            sb.append("     AND DC.LINE_BIZ_TP_CD  = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("     AND DC.EFF_FROM_DT     <= '").append(scrnMsg.slsDt.getValue()).append("' ");
            sb.append("     AND (DC.EFF_THRU_DT IS NULL ");
            sb.append("     OR   DC.EFF_THRU_DT    >= '").append(scrnMsg.slsDt.getValue()).append("') ");
            sb.append("     AND DC.EZCANCELFLAG    = '0' ");
            sb.append("     UNION ALL ");
            sb.append("     SELECT ");
            sb.append("         GLBL_CMPY_CD ");
            sb.append("        ,VND_CD ");
            sb.append("     FROM ");
            sb.append("         DS_ACCT_CARR ");
            sb.append("     WHERE");
            sb.append("         GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("     AND DS_BIZ_AREA_CD  = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("     AND EZCANCELFLAG    = '0' ");
            sb.append("     AND LINE_BIZ_TP_CD  = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("     AND EFF_FROM_DT     <= '").append(scrnMsg.slsDt.getValue()).append("' ");
            sb.append("     AND (EFF_THRU_DT IS NULL ");
            sb.append("     OR   EFF_THRU_DT    >= '").append(scrnMsg.slsDt.getValue()).append("') ");
            sb.append("     AND DS_ACCT_NUM     = '").append(scrnMsg.shipToCustAcctCd.getValue()).append("' ");
            sb.append("     AND NOT EXISTS ");
            sb.append("         (SELECT ");
            sb.append("             1 ");
            sb.append("         FROM ");
            sb.append("             DS_ACCT_CARR DC ");
            sb.append("            ,SHIP_TO_CUST SC ");
            sb.append("         WHERE");
            sb.append("             SC.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("         AND SC.EZCANCELFLAG    = '0' ");
            sb.append("         AND SC.SHIP_TO_CUST_CD  = '").append(scrnMsg.shipToCustCd.getValue()).append("' ");
            sb.append("         AND DC.GLBL_CMPY_CD    = SC.GLBL_CMPY_CD ");
            sb.append("         AND DC.DS_BIZ_AREA_CD  = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("         AND DC.LOC_NUM         = SC.LOC_NUM ");
            sb.append("         AND DC.LINE_BIZ_TP_CD  = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("         AND DC.EFF_FROM_DT     <= '").append(scrnMsg.slsDt.getValue()).append("' ");
            sb.append("         AND (DC.EFF_THRU_DT IS NULL ");
            sb.append("         OR   DC.EFF_THRU_DT    >= '").append(scrnMsg.slsDt.getValue()).append("') ");
            sb.append("         AND DC.EZCANCELFLAG    = '0' ");
            sb.append("         )" );
            sb.append("   ) DAC ");
        }
        sb.append("WHERE ");
        if (FRT_COND_CD_COLLECT.equals(freitTermCode)) {
            sb.append("    RELN.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        } else {
            sb.append("    DAC.GLBL_CMPY_CD     = SCRL.GLBL_CMPY_CD ");
            sb.append("AND DAC.VND_CD           = SCRL.CARR_CD ");
            sb.append("AND SCRL.GLBL_CMPY_CD    = RELN.GLBL_CMPY_CD ");
            sb.append("AND SCRL.SHPG_SVC_LVL_CD = RELN.SHPG_SVC_LVL_CD ");
            sb.append("AND SCRL.CARR_SVC_LVL_CD = RELN.CARR_SVC_LVL_CD "); // 2019/01/09 S21_NA#29789 Add
            sb.append("AND SCRL.EZCANCELFLAG    = '0' ");
        }
        // 2018/12/12 QC#29315 Add End
        sb.append("AND RELN.EZCANCELFLAG    = '0' ");
        sb.append("AND RELN.LINE_BIZ_TP_CD  = '").append(scrnMsg.lineBizTpCd.getValue()).append("' "); // 2019/01/09 S21_NA#29789 Add
        sb.append("AND OTD.GLBL_CMPY_CD     = RELN.GLBL_CMPY_CD ");
        sb.append("AND OTD.EZCANCELFLAG     = '0' ");
        sb.append("AND OTD.DS_ORD_TP_CD     = '").append(scrnMsg.dsOrdTpCd.getValue()).append("' ");
        sb.append("AND OTD.LINE_BIZ_TP_CD   = RELN.LINE_BIZ_TP_CD ");
        sb.append("AND RELN.FRT_COND_CD     = '").append(scrnMsg.frtCondCd.getValue()).append("' ");
        sb.append("AND RELN.SHPG_SVC_LVL_CD = '").append(scrnMsg.shpgSvcLvlCd.getValue()).append("' ");
        sb.append("AND CSL.GLBL_CMPY_CD     = RELN.GLBL_CMPY_CD ");
        sb.append("AND CSL.EZCANCELFLAG     = '0' ");
        sb.append("AND CSL.CARR_SVC_LVL_CD  = RELN.CARR_SVC_LVL_CD ");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        whereArray[IDX_0] = "Carr Svc Lvl Nm";
        whereArray[IDX_1] = "CARR_SVC_LVL_DESC_TXT";

        // S21_NA#8393 Mod Start
        // whereArray[IDX_2] = scrnMsg.carrSvcLvlDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.carrSvcLvlDescTxt)) {
            whereArray[IDX_2] = scrnMsg.carrSvcLvlDescTxt.getValue();
        } else {
            whereArray[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Carr Svc Lvl Cd";
        columnArray0[IDX_1] = "CARR_SVC_LVL_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_13);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Carr Svc Lvl Nm";
        columnArray1[IDX_1] = "CARR_SVC_LVL_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "CARR_SVC_LVL_SORT_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Salesrep
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 For Salesrep
     */
    public static Object[] getParamNWAL1130ForSlsrep(NWAL1500BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sales Rep Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    T.GLBL_CMPY_CD");
        sb.append("   ,T.EZCANCELFLAG");
        sb.append("   ,SP.PSN_NUM"); // 2016/05/11 S21_NA#7861 Mod
        sb.append("   ,T.TOC_NM");
        sb.append("   ,SP.LINE_BIZ_TP_CD");
        sb.append("   ,CB.COA_BR_NM");
        sb.append("   ,OFRT.ORG_FUNC_ROLE_TP_NM");
        sb.append("   ,T.TOC_CD ");
        sb.append("FROM");
        sb.append("    TOC T");
        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
        sb.append("   ,BIZ_AREA_ORG BA");
        sb.append("   ,ORG_FUNC_ASG OFA");
        sb.append("   ,S21_PSN SP");
        sb.append("   ,COA_BR CB ");
        sb.append("   ,S21_ORG SO "); // 2016/05/16 S21_NA#7861 Add
        sb.append("WHERE");
        sb.append("        T.GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND T.EZCANCELFLAG        = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
        sb.append("    AND BA.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
        // 2016/05/16 S21_NA#7861 Add Start
        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
        // 2016/10/21 S21_NA#14607 Add Start
        sb.append("    AND SO.RGTN_STS_CD        != '").append(RGTN_STS.TERMINATED).append("'");
        // 2016/10/21 S21_NA#14607 Add End
        sb.append("    AND SO.EZCANCELFLAG        = '0'");
        // 2016/05/16 S21_NA#7861 Add End
        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
        // 2018/04/03 S21_NA#24982 Add Start
        sb.append("    AND OFA.EFF_FROM_DT        <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (OFA.EFF_THRU_DT       >= '").append(scrnMsg.slsDt.getValue()).append("' OR OFA.EFF_THRU_DT IS NULL)");
        // 2018/04/03 S21_NA#24982 Add End
        sb.append("    AND SP.EFF_FROM_DT        <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(scrnMsg.slsDt.getValue()).append("' OR SP.EFF_THRU_DT IS NULL)");
        sb.append("    AND SP.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_5];
        // 2016/05/11 S21_NA#7861 Mod Start
        // whereArray0[IDX_0] = "Employee ID";
        // whereArray0[IDX_1] = "PSN_CD";
        // whereArray0[IDX_2] = scrnMsg.slsRepPsnCd.getValue();
        whereArray0[IDX_0] = "Resource Number";
        whereArray0[IDX_1] = "PSN_NUM";
        whereArray0[IDX_2] = scrnMsg.psnNum.getValue();
        // S21_NA#8393 Mod Start
        // whereArray0[IDX_2] = scrnMsg.psnNum.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.psnNum) || ZYPCommonFunc.hasValue(scrnMsg.slsRepTocNm)) {
            whereArray0[IDX_2] = scrnMsg.psnNum.getValue();
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        // 2016/05/11 S21_NA#7861 Mod End
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28425 Add Start
        whereArray0[IDX_4] = ZYPConstant.FLG_OFF_N;
        // 2018/10/23 S21_NA#28425 Add End
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_5];
        whereArray1[IDX_0] = "Name";
        whereArray1[IDX_1] = "TOC_NM";
        whereArray1[IDX_2] = scrnMsg.slsRepTocNm.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28425 Add Start
        whereArray1[IDX_4] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28425 Add End
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        // 2016/05/11 S21_NA#7861 Mod Start
        // columnArray0[IDX_0] = "Employee ID";
        // columnArray0[IDX_1] = "PSN_CD";
        // columnArray0[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray0[IDX_0] = "Resource Number";
        columnArray0[IDX_1] = "PSN_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_12);
        // 2016/05/11 S21_NA#7861 Mod End
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Name";
        columnArray1[IDX_1] = "TOC_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Line of Business"; // 2016/04/14 S21_NA#6937 Bussiness -> Business
        columnArray2[IDX_1] = "LINE_BIZ_TP_CD";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_10);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Branch";
        columnArray3[IDX_1] = "COA_BR_NM";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Role";
        columnArray4[IDX_1] = "ORG_FUNC_ROLE_TP_NM";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "TOC_CD";
        columnArray5[IDX_1] = "TOC_CD";
        columnArray5[IDX_2] = BigDecimal.valueOf(IDX_0);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PSN_NUM"; // 2016/05/11 S21_NA#7861 Mod 
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For CSMP Number
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 For CSMP Number
     */
    public static Object[] getParamNWAL1130ForCSMPNumber(NWAL1500BMsg scrnMsg) {

        int cellIdx = scrnMsg.xxCellIdx.getValueInt(); // 2016/08/26 S21_NA#9806 Add
        String dplyTab = scrnMsg.xxDplyTab.getValue(); // 2016/08/26 S21_NA#9806 Add

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "CSMP# Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    CC.EZCANCELFLAG");
        sb.append("   ,CC.GLBL_CMPY_CD");
        sb.append("   ,CC.CSMP_NUM");
        sb.append("   ,CC.DLR_REF_NUM");
        sb.append("   ,CC.CSMP_NUM_CMNT_TXT ");
        sb.append("FROM ");
        sb.append("    CSMP_CONTR CC ");
        sb.append("WHERE ");
        sb.append("    CC.GLBL_CMPY_CD            = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND CC.EZCANCELFLAG        = '0'");
        // 2016/08/26 S21_NA#9806 Mod Start
        // sb.append("    AND CC.DS_ACCT_NUM         = '").append(scrnMsg.sellToCustCd.getValue()).append("'");
        if (cellIdx < 0) {
            sb.append("    AND CC.DS_ACCT_NUM         = '").append(scrnMsg.sellToCustCd.getValue()).append("'");
        } else {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                sb.append("    AND CC.DS_ACCT_NUM         = '").append(scrnMsg.A.no(cellIdx).sellToCustCd_LC.getValue()).append("'");
            } else if (TAB_RMA.equals(dplyTab)) {
                sb.append("    AND CC.DS_ACCT_NUM         = '").append(scrnMsg.C.no(cellIdx).sellToCustCd_RC.getValue()).append("'");
            }
        }
        // 2016/08/26 S21_NA#9806 Mod End
        sb.append("    AND CC.EFF_FROM_DT         <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (CC.EFF_THRU_DT        >= '").append(scrnMsg.slsDt.getValue()).append("' OR CC.EFF_THRU_DT IS NULL)");
        sb.append("    AND CC.CSMP_CONTR_ACTV_FLG = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND (CC.DLR_REF_NUM IS NOT NULL OR CC.CSMP_NUM IS NOT NULL)");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "CSMP Number";
        whereArray0[IDX_1] = "CSMP_NUM";

        // 2016/08/26 S21_NA#9806 Mod Start
        // S21_NA#8393 Mod Start
        // whereArray0[IDX_2] = scrnMsg.csmpContrNum.getValue();
//        if (ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum) || ZYPCommonFunc.hasValue(scrnMsg.dlrRefNum)) {
//            whereArray0[IDX_2] = scrnMsg.csmpContrNum.getValue();
//        } else {
//            whereArray0[IDX_2] = PERCENT;
//        }
        // S21_NA#8393 Mod End
        if (cellIdx < 0) {
            if (ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum) || ZYPCommonFunc.hasValue(scrnMsg.dlrRefNum)) {
                whereArray0[IDX_2] = scrnMsg.csmpContrNum.getValue();
            } else {
                whereArray0[IDX_2] = PERCENT;
            }
        } else {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                NWAL1500_ABMsg lineConfigMsg = scrnMsg.A.no(cellIdx);
                if (ZYPCommonFunc.hasValue(lineConfigMsg.csmpContrNum_LC) || ZYPCommonFunc.hasValue(lineConfigMsg.dlrRefNum_LC)) {
                    whereArray0[IDX_2] = lineConfigMsg.csmpContrNum_LC.getValue();
                } else {
                    whereArray0[IDX_2] = PERCENT;
                }
            } else if (TAB_RMA.equals(dplyTab)) {
                NWAL1500_CBMsg rmaConfigMsg = scrnMsg.C.no(cellIdx);
                if (ZYPCommonFunc.hasValue(rmaConfigMsg.csmpContrNum_RC) || ZYPCommonFunc.hasValue(rmaConfigMsg.dlrRefNum_RC)) {
                    whereArray0[IDX_2] = rmaConfigMsg.csmpContrNum_RC.getValue();
                } else {
                    whereArray0[IDX_2] = PERCENT;
                }
            }
        }
        // 2016/08/26 S21_NA#9806 Mod End

        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "CSA Number";
        whereArray1[IDX_1] = "DLR_REF_NUM";
        // 2016/08/26 S21_NA#9806 Mod Start
//        whereArray1[IDX_2] = scrnMsg.dlrRefNum.getValue();
        if (cellIdx < 0) {
            whereArray1[IDX_2] = scrnMsg.dlrRefNum.getValue();
        } else {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                NWAL1500_ABMsg lineConfigMsg = scrnMsg.A.no(cellIdx);
                whereArray1[IDX_2] = lineConfigMsg.dlrRefNum_LC.getValue();
            } else if (TAB_RMA.equals(dplyTab)) {
                NWAL1500_CBMsg rmaConfigMsg = scrnMsg.C.no(cellIdx);
                whereArray1[IDX_2] = rmaConfigMsg.dlrRefNum_RC.getValue();
            }
        }
        // 2016/08/26 S21_NA#9806 Mod End
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "CSMP Number";
        columnArray0[IDX_1] = "CSMP_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "CSA Number";
        columnArray1[IDX_1] = "DLR_REF_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "CSMP Number Comment";
        columnArray2[IDX_1] = "CSMP_NUM_CMNT_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "CSMP_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "DLR_REF_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Freight Term
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 For Freight Term
     */
    public static Object[] getParamNWAL1130ForFrtTerm(NWAL1500BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Freight Terms Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT DISTINCT");
        sb.append("     FC.FRT_COND_CD");
        sb.append("    ,FC.FRT_COND_DESC_TXT");
        sb.append("    ,FC.EZCANCELFLAG");
        sb.append("    ,FC.GLBL_CMPY_CD ");
        sb.append("FROM");
        sb.append("     FRT_COND              FC");
        sb.append("    ,FRT_COND_SVC_LVL_RELN FCSLR ");
        sb.append("WHERE");
        sb.append("    FC.GLBL_CMPY_CD          = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND FC.EZCANCELFLAG      = 0");
        sb.append("    AND FC.GLBL_CMPY_CD      = FCSLR.GLBL_CMPY_CD");
        sb.append("    AND FC.FRT_COND_CD       = FCSLR.FRT_COND_CD ");
        if (ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd)) {
            sb.append("AND FCSLR.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("'");
        }
        sb.append("    AND FCSLR.EZCANCELFLAG   = 0");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Freight Terms Name";
        whereArray0[IDX_1] = "FRT_COND_DESC_TXT";

        // S21_NA#8393 Mod Start
        // whereArray0[IDX_2] = scrnMsg.frtCondDescTxt.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.frtCondDescTxt)) {
            whereArray0[IDX_2] = scrnMsg.frtCondDescTxt.getValue();
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End

        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Freight Terms Code";
        columnArray0[IDX_1] = "FRT_COND_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Freight Terms Name";
        columnArray1[IDX_1] = "FRT_COND_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "FRT_COND_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);
        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Association Program
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 For Association Program
     */
    public static Object[] getParamNWAL1130ForAssnProgram(NWAL1500BMsg scrnMsg) {

        String dateFormat = ZYPDateUtil.getDateFormatString(true);

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Association Program Search";

        StringBuilder sb = new StringBuilder();
        // S21_NA#8412
//        sb.append("SELECT ");
//        sb.append("     PC.GLBL_CMPY_CD");
//        sb.append("    ,PC.EZCANCELFLAG");
//        sb.append("    ,PC.PRC_CONTR_NUM");
//        sb.append("    ,PC.PRC_CONTR_NM ");
//        sb.append("    ,DECODE(PC.ACTV_FLG, 'Y', 'ACTIVE', 'INACTIVE') ACTV_FLG");
//        sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_FROM_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_FROM_DT");
//        sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_THRU_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_THRU_DT ");
//        sb.append("FROM");
//        sb.append("    PRC_CONTR PC ");
//        sb.append("WHERE");
//        sb.append("    PC.GLBL_CMPY_CD       = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
//        sb.append("    AND PC.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("'");
//        sb.append("    AND PC.EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
//        sb.append("    AND (PC.EFF_THRU_DT  >= '").append(scrnMsg.slsDt.getValue()).append("' OR PC.EFF_THRU_DT IS NULL)");
//        sb.append("    AND PC.EZCANCELFLAG   = 0");

        // 2018/07/17 S21_NA#27073 Add Start
        if (LINE_BIZ_TP.LFS.equals(scrnMsg.lineBizTpCd.getValue()) || LINE_BIZ_TP.PPS.equals(scrnMsg.lineBizTpCd.getValue())) {
            sb.append("SELECT ");
            sb.append("     PC.GLBL_CMPY_CD");
            sb.append("    ,PC.EZCANCELFLAG");
            sb.append("    ,PC.PRC_CONTR_NUM");
            sb.append("    ,PC.PRC_CONTR_NM ");
            sb.append("    ,DECODE(PC.ACTV_FLG, 'Y', 'ACTIVE', 'INACTIVE') ACTV_FLG");
            sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_FROM_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_FROM_DT");
            sb.append("    ,TO_CHAR(TO_DATE(PC.EFF_THRU_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_THRU_DT ");
            sb.append("FROM");
            sb.append("    PRC_CONTR PC ");
            sb.append("WHERE");
            sb.append("    PC.GLBL_CMPY_CD       = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
            sb.append("    AND PC.EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("    AND NVL(PC.EFF_THRU_DT, '99991231')  >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("    AND PC.EZCANCELFLAG   = 0");
        } else {
        // 2018/07/17 S21_NA#27073 Add End
            sb.append("SELECT ");
            sb.append("    DISTINCT ");
            sb.append("    CPCV.GLBL_CMPY_CD");
            sb.append("    ,CPCV.EZCANCELFLAG");
            sb.append("    ,CPCV.PRC_CONTR_NUM");
            sb.append("    ,CPCV.PRC_CONTR_NM");
            sb.append("    ,DECODE(CPCV.ACTV_FLG, 'Y', 'ACTIVE', 'INACTIVE') ACTV_FLG");
            sb.append("    ,TO_CHAR(TO_DATE(CPCV.PRC_CONTR_EFF_FROM_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_FROM_DT");
            sb.append("    ,TO_CHAR(TO_DATE(CPCV.PRC_CONTR_EFF_THRU_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_THRU_DT ");
            sb.append("FROM ");
            sb.append("    CUST_PRC_CONTR_V CPCV ");
            sb.append("WHERE ");
            sb.append("    CPCV.EZCANCELFLAG   = '0'");
            sb.append("    AND CPCV.GLBL_CMPY_CD       = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
            sb.append("    AND CPCV.LINE_BIZ_TP_CD = '").append(scrnMsg.lineBizTpCd.getValue()).append("'");
            sb.append("    AND CPCV.DS_ACCT_NUM = '").append(scrnMsg.sellToCustCd.getValue()).append("'");
            sb.append("    AND CPCV.PRC_CONTR_EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("    AND NVL(CPCV.PRC_CONTR_EFF_THRU_DT, '99991231') >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("    AND CPCV.DS_PRC_CATG_EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("    AND NVL(CPCV.DS_PRC_CATG_EFF_THRU_DT, '99991231') >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("    AND ");
            sb.append("    (");
            sb.append("        CPCV.CSMP_NUM IS NULL");
            sb.append("        OR");
            sb.append("        (");
            sb.append("            CPCV.CSMP_NUM IS NOT NULL");
            sb.append("            AND CPCV.CSMP_CONTR_EFF_FROM_DT   <= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("            AND NVL(CPCV.CSMP_CONTR_EFF_THRU_DT, '99991231') >= '").append(scrnMsg.slsDt.getValue()).append("'");
            sb.append("        )");
            sb.append("    )");
        // 2018/07/17 S21_NA#27073 Add Start
        }
        // 2018/07/17 S21_NA#27073 Add End

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "Price Contract #";
        whereArray0[IDX_1] = "PRC_CONTR_NUM";

        // S21_NA#8393 Mod Start
        // whereArray0[IDX_2] = scrnMsg.prcContrNum.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.prcContrNum) || ZYPCommonFunc.hasValue(scrnMsg.prcContrNm)) {
            whereArray0[IDX_2] = scrnMsg.prcContrNum.getValue();
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End

        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Price Contract Name";
        whereArray1[IDX_1] = "PRC_CONTR_NM";
        whereArray1[IDX_2] = scrnMsg.prcContrNm.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Price Contract #";
        columnArray0[IDX_1] = "PRC_CONTR_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_33);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Price Contract Name";
        columnArray1[IDX_1] = "PRC_CONTR_NM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_33);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Status";
        columnArray2[IDX_1] = "ACTV_FLG";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_8);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Eff From Date";
        columnArray3[IDX_1] = "EFF_FROM_DT";
        columnArray3[IDX_2] = BigDecimal.valueOf(IDX_9);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Eff Thru Date";
        columnArray4[IDX_1] = "EFF_THRU_DT";
        columnArray4[IDX_2] = BigDecimal.valueOf(IDX_9);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "PRC_CONTR_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRC_CONTR_NM";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Warehouse
     * @param scrnMsg NWAL1500BMsg
     * @param isWh WH:true SWH:false
     * @return Param NWAL1130 For Warehouse
     */
    public static Object[] getParamNWAL1130ForWh(NWAL1500BMsg scrnMsg, boolean isWh) {

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();
        String rtlWhNm = null;
        String rtlSubWhNm = null;
        String rtlWhCd = null;

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            rtlWhNm = scrnMsg.B.no(selectIndex).rtlWhNm_LL.getValue();
            rtlSubWhNm = scrnMsg.B.no(selectIndex).rtlSwhNm_LL.getValue();
            rtlWhCd = scrnMsg.B.no(selectIndex).rtlWhCd_LL.getValue();
        } else {
            rtlWhNm = scrnMsg.D.no(selectIndex).rtlWhNm_RL.getValue();
            // 2017/11/17 S21_NA#22252 Add Start
            rtlSubWhNm = scrnMsg.D.no(selectIndex).rtlSwhNm_RL.getValue();
            rtlWhCd = scrnMsg.D.no(selectIndex).rtlWhCd_RL.getValue();
            // 2017/11/17 S21_NA#22252 Add End
        }

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        if (isWh) {
            params[IDX_1] = "Ship From Warehouse Search";
        } else {
            params[IDX_1] = "Ship From Sub Warehouse Search";
        }
        // QC#1615
        params[IDX_2] = getSlctTblNmForWh(scrnMsg, isWh, rtlWhCd);

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];
        // QC#1615
        if (isWh) {
            whereArray[IDX_0] = "Warehouse Name";
            whereArray[IDX_1] = "UPPER(RTL_WH_NM)";

            // S21_NA#8393 Mod Start
            // whereArray[IDX_2] = rtlWhNm;
            if (ZYPCommonFunc.hasValue(rtlWhNm)) {
                whereArray[IDX_2] = rtlWhNm;
            } else {
                whereArray[IDX_2] = PERCENT;
            }
            // S21_NA#8393 Mod End

            whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
            whereList.add(whereArray);
        } else {
            whereArray[IDX_0] = "Sub Warehouse Name";
            whereArray[IDX_1] = "UPPER(RTL_SWH_NM)";

            // S21_NA#8393 Mod Start
            // whereArray[IDX_2] = rtlSubWhNm;
            if (ZYPCommonFunc.hasValue(rtlSubWhNm)) {
                whereArray[IDX_2] = rtlSubWhNm;
            } else {
                whereArray[IDX_2] = PERCENT;
            }
            // S21_NA#8393 Mod End

            whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
            whereList.add(whereArray);
        }

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];

        if (isWh) {
            columnArray0[IDX_0] = "Warehouse Code";
            columnArray0[IDX_1] = "RTL_WH_CD";
        } else {
            columnArray0[IDX_0] = "Sub Warehouse Code";
            columnArray0[IDX_1] = "RTL_SWH_CD";
        }
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_30);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];

        if (isWh) {
            columnArray1[IDX_0] = "Warehouse Name";
            columnArray1[IDX_1] = "RTL_WH_NM";
        } else {
            columnArray1[IDX_0] = "Sub Warehouse Name";
            columnArray1[IDX_1] = "RTL_SWH_NM";
        }
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        // QC#1615
        if (isWh) {
            sortConditionArray0[IDX_0] = "RTL_WH_CD";
        } else {
            sortConditionArray0[IDX_0] = "RTL_SWH_CD";
        }
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Param NWAL1130 For Log Type
     * @param scrnMsg NWAL1500BMsg
     * @param isLink link:true blur:false
     * @return Param NWAL1130 For Log Type
     */
    public static Object[] getParamNWAL1130ForLogType(NWAL1500BMsg scrnMsg, boolean isLink) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Order Log Type Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    OLT.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        sb.append("   ,OLT.EZCANCELFLAG AS EZCANCELFLAG ");
        sb.append("   ,OLT.ORD_LOG_TP_CD AS ORD_LOG_TP_CD ");
        sb.append("   ,UPPER(OLT.ORD_LOG_TP_DESC_TXT) AS ORD_LOG_TP_DESC_TXT ");
        sb.append("FROM ");
        sb.append("    ORD_LOG_TP  OLT ");
        sb.append("WHERE ");
        sb.append("    OLT.GLBL_CMPY_CD      = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND OLT.EZCANCELFLAG       = '0'");
        sb.append("ORDER BY ");
        sb.append("    OLT.ORD_LOG_TP_SORT_NUM");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[IDX_4];

        // S21_NA#5337 Mod Start
        // whereArray[IDX_0] = "Order Log Type Name";
        // whereArray[IDX_1] = "UPPER(ORD_LOG_TP_DESC_TXT)";
        // whereArray[IDX_2] = scrnMsg.ordLogTpDescTxt_NM.getValue();
        // whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        // whereList.add(whereArray);
        whereArray[IDX_0] = "Order Log Type Code";
        whereArray[IDX_1] = "ORD_LOG_TP_CD";
        // S21_NA#8393 Mod Start
        // whereArray[IDX_2] = scrnMsg.ordLogTpCd.getValue();
        if (ZYPCommonFunc.hasValue(scrnMsg.ordLogTpCd)) {
            whereArray[IDX_2] = scrnMsg.ordLogTpCd.getValue();
        } else {
            whereArray[IDX_2] = PERCENT;
        }
        // S21_NA#8393 Mod End
        whereArray[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        Object[] whereArray1 = new Object[IDX_4];

        whereArray1[IDX_0] = "Order Log Type Name";
        whereArray1[IDX_1] = "ORD_LOG_TP_DESC_TXT";
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        // S21_NA#5337 Mod End

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Order Log Type Code";
        columnArray0[IDX_1] = "ORD_LOG_TP_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Order Log Type Name";
        columnArray1[IDX_1] = "ORD_LOG_TP_DESC_TXT";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    /**
     * Get Select Table Name For Werehouse
     * @param scrnMsg NWAL1500BMsg
     * @param isWh WH:true SWH:false
     * @param rtlWhCd WHEN SWH == false THEN rtlWhCd
     * @return Select Table Name For Werehouse
     */
    private static String getSlctTblNmForWh(NWAL1500BMsg scrnMsg, boolean isWh, String rtlWhCd) {

        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();
        String slsDt = scrnMsg.slsDt.getValue();
        String dsOrdTpCd = scrnMsg.dsOrdTpCd.getValue();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        // QC#1615
        sb.append("    DISTINCT ");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        // QC#1615
        if (isWh) {
            sb.append("   ,WH.RTL_WH_CD");
            sb.append("   ,WH.RTL_WH_NM ");
        } else {
            sb.append("   ,WH.RTL_SWH_CD");
            sb.append("   ,WH.RTL_SWH_NM ");
        }
        sb.append("FROM");

        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            sb.append("    DS_INVTY_LOC_V WH ");
            sb.append("WHERE ");
            sb.append("        WH.GLBL_CMPY_CD                 = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.EZCANCELFLAG                 = '0'");
            sb.append("    AND WH.RGTN_STS_CD                  = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                  <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            sb.append("    AND EXISTS(");
            // 2018/10/16 S21_NA#28712 Mod Start
            // sb.append("        SELECT");
            sb.append("        SELECT /*+ USE_NL(LRR LU) INDEX(LU LOC_USG_I3) */");
            // 2018/10/16 S21_NA#28712 Mod End
            sb.append("            *");
            sb.append("        FROM    ");
            sb.append("             DS_ORD_TP_LOC_ROLE_RELN    LRR   ");
            sb.append("            ,LOC_USG                    LU");
            sb.append("        WHERE");
            sb.append("                LRR.DS_ORD_TP_CD        = '").append(dsOrdTpCd).append("'");
            sb.append("            AND LRR.EZCANCELFLAG        = '0'");
            sb.append("            AND LRR.GLBL_CMPY_CD        = LU.GLBL_CMPY_CD");
            sb.append("            AND LRR.LOC_ROLE_TP_CD      = LU.LOC_ROLE_TP_CD");
            sb.append("            AND LU.EZCANCELFLAG         = '0'");
            sb.append("            AND LU.GLBL_CMPY_CD         = WH.GLBL_CMPY_CD");
            sb.append("            AND LU.PTY_LOC_PK           = WH.PTY_LOC_PK");
            sb.append("    )");
        } else {
            sb.append("    DS_INVTY_LOC_V                       WH ");
            sb.append("WHERE");
            sb.append("        WH.GLBL_CMPY_CD                  = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                   <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(slsDt).append("'");
            sb.append("    AND WH.EZCANCELFLAG                  = '0'");
        }
        if (!isWh && ZYPCommonFunc.hasValue(rtlWhCd)) {
            sb.append("    AND WH.RTL_WH_CD                     = '").append(rtlWhCd).append("'");
        }
        return sb.toString();
    }

    // 2016/07/02 S21_NA#1698,3235 Mod Start
    /**
     * Get Param NWAL1130 For Substitute Item
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 For Substitute Item
     */
//    public static Object[] getParamNWAL1130ForSbstItem(NWAL1500BMsg scrnMsg) {
//
//        int selectIndex = scrnMsg.xxCellIdx.getValueInt();
//        String mdseCd = scrnMsg.B.no(selectIndex).mdseCd_LL.getValue();
//        String sbstMdseCd = scrnMsg.B.no(selectIndex).sbstMdseCd_LL.getValue();
//
//        Object[] params = new Object[IDX_7];
//        params[IDX_0] = "";
//        params[IDX_1] = "Sup/Relation Item Search";
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT");
//        sb.append("    V.GLBL_CMPY_CD");
//        sb.append("   ,V.EZCANCELFLAG");
//        sb.append("   ,V.MDSE_CD");
//        sb.append("   ,V.MDSE_NM ");
//        sb.append("FROM");
//        sb.append("    ALL_MDSE_V V ");
//        sb.append("WHERE");
//        sb.append("    V.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
//        sb.append("    AND V.RGTN_STS_CD = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
//        sb.append("    AND V.MDSE_CD IN (");
//        sb.append("        SELECT");
//        sb.append("            SUPD.SUPD_TO_MDSE_CD");
//        sb.append("        FROM");
//        sb.append("            SUPD_RELN SUPD");
//        sb.append("        WHERE");
//        sb.append("            SUPD.GLBL_CMPY_CD          = V.GLBL_CMPY_CD");
//        sb.append("            AND SUPD.SUPD_FROM_MDSE_CD = '").append(mdseCd).append("'");
//        sb.append("            AND SUPD.EZCANCELFLAG      = '0'");
//        sb.append("        UNION");
//        sb.append("        SELECT");
//        sb.append("            SUPD.SUPD_TO_MDSE_CD");
//        sb.append("        FROM");
//        sb.append("            ORD_TAKE_MDSE OTM");
//        sb.append("           ,SUPD_RELN SUPD");
//        sb.append("        WHERE");
//        sb.append("            OTM.GLBL_CMPY_CD         = V.GLBL_CMPY_CD");
//        sb.append("            AND OTM.ORD_TAKE_MDSE_CD = '").append(mdseCd).append("'");
//        sb.append("            AND OTM.EZCANCELFLAG     = '0'");
//        sb.append("            AND OTM.GLBL_CMPY_CD     = SUPD.GLBL_CMPY_CD");
//        sb.append("            AND OTM.MDSE_CD          = SUPD.SUPD_FROM_MDSE_CD");
//        sb.append("            AND SUPD.EZCANCELFLAG    = '0'");
//        sb.append("        UNION");
//
//        // 2016/03/05 S21_NA#1698 Mod Start
////        sb.append("        SELECT");
////        sb.append("            FLIP.RELN_MDSE_CD");
////        sb.append("        FROM");
////        sb.append("            MDSE_ITEM_FLIP_SET FLIP");
////        sb.append("        WHERE");
////        sb.append("            FLIP.GLBL_CMPY_CD     = V.GLBL_CMPY_CD");
////        sb.append("            AND FLIP.MDSE_CD      = '").append(mdseCd).append("'");
////        sb.append("            AND FLIP.EZCANCELFLAG = '0'");
//        sb.append("        SELECT");
//        sb.append("            NVL(OTM.ORD_TAKE_MDSE_CD, FLIP.RELN_MDSE_CD)");
//        sb.append("        FROM");
//        sb.append("            MDSE_ITEM_FLIP_SET FLIP ,");
//        sb.append("            ORD_TAKE_MDSE OTM");
//        sb.append("        WHERE");
//        sb.append("            FLIP.GLBL_CMPY_CD     = V.GLBL_CMPY_CD");
//        sb.append("            AND FLIP.MDSE_CD      = '").append(mdseCd).append("'");
//        sb.append("            AND FLIP.EZCANCELFLAG = '0'");
//        sb.append("            AND OTM.GLBL_CMPY_CD(+)     = FLIP.GLBL_CMPY_CD");
//        sb.append("            AND OTM.ORD_TAKE_MDSE_CD(+) = SUBSTR(FLIP.RELN_MDSE_CD, 1, 8)");
//        sb.append("            AND OTM.EZCANCELFLAG(+)     = '0'");
//        sb.append("        UNION");
//
////        sb.append("        SELECT");
////        sb.append("            FLIP.RELN_MDSE_CD");
////        sb.append("        FROM");
////        sb.append("            ORD_TAKE_MDSE OTM");
////        sb.append("           ,MDSE_ITEM_FLIP_SET FLIP");
////        sb.append("        WHERE");
////        sb.append("            OTM.GLBL_CMPY_CD         = V.GLBL_CMPY_CD");
////        sb.append("            AND OTM.ORD_TAKE_MDSE_CD = '").append(mdseCd).append("'");
////        sb.append("            AND OTM.EZCANCELFLAG     = '0'");
////        sb.append("            AND OTM.GLBL_CMPY_CD     = FLIP.GLBL_CMPY_CD");
////        sb.append("            AND OTM.MDSE_CD          = FLIP.MDSE_CD");
////        sb.append("            AND FLIP.EZCANCELFLAG    = '0')");
//        sb.append("        SELECT");
//        sb.append("            OTM.ORD_TAKE_MDSE_CD");
//        sb.append("        FROM");
//        sb.append("            MDSE_ITEM_FLIP_SET FLIP");
//        sb.append("           ,ORD_TAKE_MDSE OTM");
//        sb.append("        WHERE");
//        sb.append("            FLIP.GLBL_CMPY_CD        = V.GLBL_CMPY_CD");
//        sb.append("            AND FLIP.MDSE_CD LIKE SUBSTR('").append(mdseCd).append("', 1, 8) || '%'");
//        sb.append("            AND FLIP.EZCANCELFLAG    = '0'");
//        sb.append("            AND OTM.GLBL_CMPY_CD     = FLIP.GLBL_CMPY_CD");
//        sb.append("            AND OTM.ORD_TAKE_MDSE_CD = SUBSTR(FLIP.RELN_MDSE_CD, 1, 8)");
//        sb.append("            AND OTM.EZCANCELFLAG     = '0')");
//        // 2016/03/05 S21_NA#1698 Mod Start
//        sb.append("    AND V.EZCANCELFLAG    = '0'");
//
//        params[IDX_2] = sb.toString();
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//        Object[] whereArray0 = new Object[IDX_4];
//        whereArray0[IDX_0] = "Sup/Relation Item Cd";
//        whereArray0[IDX_1] = "MDSE_CD";
//        // S21_NA#8393 Mod Start
//        // whereArray0[IDX_2] = sbstMdseCd;
//        if (ZYPCommonFunc.hasValue(sbstMdseCd)) {
//            whereArray0[IDX_2] = sbstMdseCd;
//        } else {
//            whereArray0[IDX_2] = PERCENT;
//        }
//        // S21_NA#8393 Mod End
//        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray0);
//
//        Object[] whereArray1 = new Object[IDX_4];
//        whereArray1[IDX_0] = "Sup/Relation Item Nm";
//        whereArray1[IDX_1] = "MDSE_NM";
//        whereArray1[IDX_2] = null;
//        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray1);
//
//        params[IDX_3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//        Object[] columnArray0 = new Object[IDX_4];
//        columnArray0[IDX_0] = "Sup/Relation Item Cd";
//        columnArray0[IDX_1] = "MDSE_CD";
//        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
//        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray0);
//
//        Object[] columnArray1 = new Object[IDX_4];
//        columnArray1[IDX_0] = "Sup/Relation Item Nm";
//        columnArray1[IDX_1] = "MDSE_NM";
//        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
//        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray1);
//
//        params[IDX_4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray0 = new Object[IDX_2];
//        sortConditionArray0[IDX_0] = "MDSE_CD";
//        sortConditionArray0[IDX_1] = "ASC";
//        sortConditionList.add(sortConditionArray0);
//
//        params[IDX_5] = sortConditionList;
//
//        ZYPTableUtil.clear(scrnMsg.Z);
//        params[IDX_6] = scrnMsg.Z;
//
//        return params;
//    }

    /**
     * Get Param NWAL1130 For Substitute Item
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 For Substitute Item
     */
    public static Object[] getParamNWAL1130ForSbstItem(NWAL1500BMsg scrnMsg) {

        int selectIndex = scrnMsg.xxCellIdx.getValueInt();
        String mdseCd = scrnMsg.B.no(selectIndex).mdseCd_LL.getValue();
        String mdse8LenCd = mdseCd.length() > 8 ? mdseCd.substring(0, 8) : mdseCd;

        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, scrnMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, mdse8LenCd);
        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            // Not Find In ORD_TAKE_MDSE
            mdse8LenCd = mdseCd;
        }

        String sbstMdseCd = scrnMsg.B.no(selectIndex).sbstMdseCd_LL.getValue();

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Sup/Relation Item Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    VV.GLBL_CMPY_CD");
        sb.append("    , VV.EZCANCELFLAG");
        sb.append("    , VV.MDSE_CD");
        // Mod Start 2016/09/14 QC#11614
//        sb.append("    , VV.MDSE_NM ");
        sb.append("    , VV.MDSE_DESC_SHORT_TXT ");
        // Mod End 2016/09/14 QC#11614
        sb.append("FROM (");
        sb.append("    SELECT");
        sb.append("        V.GLBL_CMPY_CD");
        sb.append("        , V.EZCANCELFLAG");
        sb.append("        , V.MDSE_CD");
        // Mod Start 2016/09/14 QC#11614
//        sb.append("        , V.MDSE_NM");
        sb.append("        , V.MDSE_DESC_SHORT_TXT");
        // Mod End 2016/09/14 QC#11614
        sb.append("      FROM");
        sb.append("         ALL_MDSE_V V");
        sb.append("     WHERE");
        sb.append("           V.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("       AND V.RGTN_STS_CD = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
        // 2017/06/14 S21_NA#18623 Add Start
        sb.append("       AND V.SELL_HLD_FLG = '").append(ZYPConstant.FLG_OFF_N).append("'");
        // 2017/06/14 S21_NA#18623 Add End
        sb.append("       AND V.MDSE_CD IN (");
        sb.append("            SELECT");
        sb.append("                    SR.SUPD_TO_MDSE_CD SBST_MDSE_CD");
        sb.append("                FROM");
        sb.append("                    SUPD_RELN       SR");
        sb.append("                    ,ORD_TAKE_MDSE  OTM");
        sb.append("                WHERE");
        sb.append("                    SR.GLBL_CMPY_CD          = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("                    AND SR.EZCANCELFLAG      = '0'");
        sb.append("                    AND SUBSTR(SR.SUPD_FROM_MDSE_CD, 0, 8) = '").append(mdse8LenCd).append("'");
        sb.append("                    AND SR.GLBL_CMPY_CD     = OTM.GLBL_CMPY_CD");
        sb.append("                    AND SR.EZCANCELFLAG     = OTM.EZCANCELFLAG");
        // 2017/06/14 S21_NA#18623 Mod Start
//        sb.append("                    AND SUBSTR(SR.SUPD_TO_MDSE_CD, 0, 8) = OTM.ORD_TAKE_MDSE_CD");
        sb.append("                    AND SUBSTR(SR.SUPD_FROM_MDSE_CD, 0, 8) = OTM.ORD_TAKE_MDSE_CD");
        // 2017/06/14 S21_NA#18623 Mod End
        sb.append("            UNION ALL");
        sb.append("                SELECT");
        sb.append("                        FS.RELN_MDSE_CD SBST_MDSE_CD");
        sb.append("                    FROM");
        sb.append("                        MDSE_ITEM_FLIP_SET FS");
        sb.append("                        ,ORD_TAKE_MDSE  OTM");
        sb.append("                    WHERE");
        sb.append("                        FS.GLBL_CMPY_CD     = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("                        AND FS.EZCANCELFLAG = '0'");
        sb.append("                        AND SUBSTR(FS.MDSE_CD, 0, 8)      = '").append(mdse8LenCd).append("'");
        sb.append("                        AND FS.GLBL_CMPY_CD     = OTM.GLBL_CMPY_CD");
        sb.append("                        AND FS.EZCANCELFLAG     = OTM.EZCANCELFLAG");
        // 2017/06/14 S21_NA#18623 Mod Start
//        sb.append("                        AND SUBSTR(FS.RELN_MDSE_CD, 0, 8) = OTM.ORD_TAKE_MDSE_CD");
        sb.append("                        AND SUBSTR(FS.MDSE_CD, 0, 8) = OTM.ORD_TAKE_MDSE_CD");
        // 2017/06/14 S21_NA#18623 Mod End
        sb.append("             UNION ALL");
        sb.append("                 SELECT");
        sb.append("                     SR.SUPD_TO_MDSE_CD SBST_MDSE_CD");
        sb.append("                 FROM");
        sb.append("                     SUPD_RELN       SR");
        sb.append("                 WHERE");
        sb.append("                     SR.GLBL_CMPY_CD          = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("                     AND SR.EZCANCELFLAG      = '0'");
        // 2017/06/14 S21_NA#18623 Mod Start
//        sb.append("                     AND SR.SUPD_FROM_MDSE_CD = '").append(mdse8LenCd).append("'");
        sb.append("                     AND SR.SUPD_FROM_MDSE_CD = '").append(mdseCd).append("'");
        // 2017/06/14 S21_NA#18623 Mod End
        sb.append("             UNION ALL");
        sb.append("                 SELECT");
        sb.append("                     FS.RELN_MDSE_CD SBST_MDSE_CD");
        sb.append("                 FROM");
        sb.append("                     MDSE_ITEM_FLIP_SET FS");
        sb.append("                 WHERE");
        sb.append("                     FS.GLBL_CMPY_CD     = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("                     AND FS.EZCANCELFLAG = '0'");
        // 2017/06/14 S21_NA#18623 Mod Start
//        sb.append("                     AND FS.MDSE_CD      = '").append(mdse8LenCd).append("'");
        sb.append("                     AND FS.MDSE_CD      = '").append(mdseCd).append("'");
        // 2017/06/14 S21_NA#18623 Mod End
        sb.append("       )");
        sb.append("     ) VV");


        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_5];
        whereArray0[IDX_0] = "Sup/Relation Item Cd";
        whereArray0[IDX_1] = "MDSE_CD";
        if (ZYPCommonFunc.hasValue(sbstMdseCd)) {
            whereArray0[IDX_2] = sbstMdseCd;
        } else {
            whereArray0[IDX_2] = PERCENT;
        }
        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28426 Add Start
        whereArray0[IDX_4] = ZYPConstant.FLG_OFF_N;
        // 2018/10/23 S21_NA#28426 Add End
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_5];
        whereArray1[IDX_0] = "Sup/Relation Item Nm";
        // Mod Start 2016/09/14 QC#11614
//        whereArray1[IDX_1] = "MDSE_NM";
        whereArray1[IDX_1] = "MDSE_DESC_SHORT_TXT";
        // Mod End 2016/09/14 QC#11614
        whereArray1[IDX_2] = null;
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28426 Add Start
        whereArray0[IDX_4] = ZYPConstant.FLG_OFF_N;
        // 2018/10/23 S21_NA#28426 Add End
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "Sup/Relation Item Cd";
        columnArray0[IDX_1] = "MDSE_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Sup/Relation Item Nm";
        // Mod Start 2016/09/14 QC#11614
//        columnArray1[IDX_1] = "MDSE_NM";
        columnArray1[IDX_1] = "MDSE_DESC_SHORT_TXT";
        // Mod End 2016/09/14 QC#11614
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "MDSE_CD";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }

    // 2016/07/02 S21_NA#1698,3235 Mod End

    /**
     * Get Param NMAL6760 For Bill To
     * @param scrnMsg NWAL1500BMsg
     * @return Param NMAL6760 For Bill To
     */
    public static Object[] getParamNMAL6760ForBillTo(NWAL1500BMsg scrnMsg) {

        // 2018/07/27 S21_NA#14307 Add Start
        String trxRuleTp = scrnMsg.xxPopPrm_P9.getValue();
        String funcCatgId = scrnMsg.xxPopPrm_PC.getValue();
        // 2018/07/27 S21_NA#14307 Add End

        // clear
        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        clearPopUpParam(scrnMsg);

        String billToAcctNum = getBillToAcctNum(scrnMsg);

        // 2016/05/09 for NMAL6760 Constants Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
        // 2016/05/09 for NMAL6760 Constants Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(billToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, billToAcctNum);

        // 2018/07/27 S21_NA#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PB, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PC, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PD, scrnMsg.lineBizTpCd);
        // 2018/07/27 S21_NA#14307 Add End

        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            paramList.add(scrnMsg.xxPopPrm_P6);
            paramList.add(scrnMsg.A.no(selectIdx).billToCustAcctNm_LC);
        } else if (TAB_RMA.equals(dplyTab)) {
            paramList.add(scrnMsg.xxPopPrm_P6);
            paramList.add(scrnMsg.C.no(selectIdx).billToCustAcctNm_RC);
        } else {
            paramList.add(scrnMsg.xxPopPrm_P6);
            paramList.add(scrnMsg.billToCustAcctNm);
        }

        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.firstLineAddr);
        paramList.add(scrnMsg.ctyAddr);
        paramList.add(scrnMsg.stCd);
        paramList.add(scrnMsg.postCd);
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_P1); // Active Only
        paramList.add(scrnMsg.xxPopPrm_P2); // Bill To's Only
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_P7); // Bill To Location
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.scdLineAddr);
        paramList.add(scrnMsg.thirdLineAddr);
        paramList.add(scrnMsg.frthLineAddr);
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_P3); // Display Hierarkey Active Flag
        paramList.add(scrnMsg.xxPopPrm_P4); // Account Number Active Flag
        paramList.add(scrnMsg.xxPopPrm_P5); // Status Active Flag

        // 2018/07/27 S21_NA#14307 Add Start
        paramList.add(scrnMsg.xxPopPrm_PA); // [27]
        paramList.add(scrnMsg.xxPopPrm_PA); // [28]
        paramList.add(scrnMsg.xxPopPrm_PA); // [29]
        paramList.add(scrnMsg.xxPopPrm_PA); // [30]
        paramList.add(scrnMsg.xxPopPrm_PA); // [31]
        paramList.add(scrnMsg.xxPopPrm_PA); // [32]
        paramList.add(scrnMsg.xxPopPrm_PA); // [33]
        paramList.add(scrnMsg.xxPopPrm_PA); // [34]
        paramList.add(scrnMsg.xxPopPrm_PA); // [35]
        paramList.add(scrnMsg.xxPopPrm_P8); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.xxPopPrm_P9); // [37] Transaction Type
        paramList.add(scrnMsg.xxPopPrm_PA); // [38] Business Area
        paramList.add(scrnMsg.xxPopPrm_PB); // [39] Function ID
        paramList.add(scrnMsg.xxPopPrm_PC); // [40] Function Category ID
        paramList.add(scrnMsg.xxPopPrm_PD); // [41] Line of Business Code
        // 2018/07/27 S21_NA#14307 Add End

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6760 For Ship To
     * @param scrnMsg NWAL1500BMsg
     * @return Param NMAL6760 For Ship To
     */
    public static Object[] getParamNMAL6760ForShipTo(NWAL1500BMsg scrnMsg) {

        // 2018/07/27 S21_NA#14307 Add Start
        String trxRuleTp = scrnMsg.xxPopPrm_P9.getValue();
        String funcCatgId = scrnMsg.xxPopPrm_PC.getValue();
        // 2018/07/27 S21_NA#14307 Add End

        // clear
        scrnMsg.firstRefCmntTxt.clear();
        scrnMsg.scdRefCmntTxt.clear();
        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.cntyNm.clear();
        scrnMsg.provNm.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        scrnMsg.ctryCd.clear();
        clearPopUpParam(scrnMsg);

        String shipToAcctNum = getShipToAcctNum(scrnMsg);

        // 2016/05/09 for NMAL6760 Constants Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760Constant.DISP_HRCH_ACCT_CD_SHIP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760_DISP_HRCH_ACCT_CD_SHIP);
        // 2016/05/09 for NMAL6760 Constants Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(shipToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, shipToAcctNum);

        // 2018/07/27 S21_NA#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PB, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PC, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PD, scrnMsg.lineBizTpCd);
        // 2018/07/27 S21_NA#14307 Add End

        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            paramList.add(scrnMsg.xxPopPrm_P6); // Ship To Account
            paramList.add(scrnMsg.A.no(selectIdx).shipToCustAcctNm_LC);
        } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) { // 2018/03/09 S21_NA#19808 (LINE_CONFIG->RMA)
            paramList.add(scrnMsg.xxPopPrm_P6); // Ship To Account
            paramList.add(scrnMsg.C.no(selectIdx).shipToCustAcctNm_RC);
        } else {
            paramList.add(scrnMsg.xxPopPrm_P6); // Ship To Account
            paramList.add(scrnMsg.shipToCustAcctNm);
        }

        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.firstLineAddr);
        paramList.add(scrnMsg.ctyAddr);
        paramList.add(scrnMsg.stCd);
        paramList.add(scrnMsg.postCd);
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_P1); // Active Only
        paramList.add(scrnMsg.xxPopPrm_P2); // Ship To's Only
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_P7); // Ship To Location
        paramList.add(scrnMsg.scdLineAddr);
        paramList.add(scrnMsg.thirdLineAddr);
        paramList.add(scrnMsg.frthLineAddr);
        paramList.add(scrnMsg.cntyNm);
        paramList.add(scrnMsg.provNm);
        paramList.add(scrnMsg.ctryCd);
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_P3); // Display Hierarkey Active Flag
        paramList.add(scrnMsg.xxPopPrm_P4); // Account Number Active Flag
        paramList.add(scrnMsg.xxPopPrm_P5); // Status Active Flag

        // 2016/03/11 S21_NA#4389 Add Start
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used

        paramList.add(scrnMsg.firstRefCmntTxt);
        paramList.add(scrnMsg.scdRefCmntTxt);
        // 2016/03/11 S21_NA#4389 Add End

        // 2018/07/27 S21_NA#14307 Add Start
        paramList.add(scrnMsg.xxPopPrm_PA); // [33]
        paramList.add(scrnMsg.xxPopPrm_PA); // [34]
        paramList.add(scrnMsg.xxPopPrm_PA); // [35]
        paramList.add(scrnMsg.xxPopPrm_P8); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.xxPopPrm_P9); // [37] Transaction Type
        paramList.add(scrnMsg.xxPopPrm_PA); // [38] Business Area
        paramList.add(scrnMsg.xxPopPrm_PB); // [39] Function ID
        paramList.add(scrnMsg.xxPopPrm_PC); // [40] Function Category ID
        paramList.add(scrnMsg.xxPopPrm_PD); // [41] Line of Business Code
        // 2018/07/27 S21_NA#14307 Add End

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6760 For Sold To
     * @param scrnMsg NWAL1500BMsg
     * @return Param NMAL6760 For Sold To
     */
    public static Object[] getParamNMAL6760ForSoldTo(NWAL1500BMsg scrnMsg) {

        // 2018/07/27 S21_NA#14307 Add Start
        String trxRuleTp = scrnMsg.xxPopPrm_P9.getValue();
        String funcCatgId = scrnMsg.xxPopPrm_PC.getValue();
        // 2018/07/27 S21_NA#14307 Add End

        // clear
        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.postCd.clear();
        clearPopUpParam(scrnMsg);

        String soldToAcctNum = getSoldToAcctNum(scrnMsg);

        // 2016/05/09 for NMAL6760 Constants Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760Constant.STATUS_CD_ACTIVE);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760Constant.DISP_HRCH_ACCT_CD_BILL);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NMAL6760_STATUS_CD_ACTIVE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, NMAL6760_DISP_HRCH_ACCT_CD_BILL);
        // 2016/05/09 for NMAL6760 Constants Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_ON_Y);
        if (ZYPCommonFunc.hasValue(soldToAcctNum)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, soldToAcctNum);

        // 2018/07/27 S21_NA#14307 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P9, trxRuleTp);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PB, BIZ_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PC, funcCatgId);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PD, scrnMsg.lineBizTpCd);
        // 2018/07/27 S21_NA#14307 Add End

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.xxPopPrm_P6); // Sold To Account
        paramList.add(scrnMsg.soldToCustAcctNm);
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.firstLineAddr);
        paramList.add(scrnMsg.ctyAddr);
        paramList.add(scrnMsg.stCd);
        paramList.add(scrnMsg.postCd);
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_P1); // Active Only
        paramList.add(scrnMsg.xxPopPrm_P2); // Bill To's Only
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_P7); // Sold To Location
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.scdLineAddr);
        paramList.add(scrnMsg.thirdLineAddr);
        paramList.add(scrnMsg.frthLineAddr);
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_PA); // no used
        paramList.add(scrnMsg.xxPopPrm_P3); // Display Hierarkey Active Flag
        paramList.add(scrnMsg.xxPopPrm_P4); // Account Number Active Flag
        paramList.add(scrnMsg.xxPopPrm_P5); // Status Active Flag

        // 2018/07/27 S21_NA#14307 Add Start
        paramList.add(scrnMsg.xxPopPrm_PA); // [27]
        paramList.add(scrnMsg.xxPopPrm_PA); // [28]
        paramList.add(scrnMsg.xxPopPrm_PA); // [29]
        paramList.add(scrnMsg.xxPopPrm_PA); // [30]
        paramList.add(scrnMsg.xxPopPrm_PA); // [31]
        paramList.add(scrnMsg.xxPopPrm_PA); // [32]
        paramList.add(scrnMsg.xxPopPrm_PA); // [33]
        paramList.add(scrnMsg.xxPopPrm_PA); // [34]
        paramList.add(scrnMsg.xxPopPrm_PA); // [35]
        paramList.add(scrnMsg.xxPopPrm_P8); // [36] Display Control Customer Special Instruction
        paramList.add(scrnMsg.xxPopPrm_P9); // [37] Transaction Type
        paramList.add(scrnMsg.xxPopPrm_PA); // [38] Business Area
        paramList.add(scrnMsg.xxPopPrm_PB); // [39] Function ID
        paramList.add(scrnMsg.xxPopPrm_PC); // [40] Function Category ID
        paramList.add(scrnMsg.xxPopPrm_PD); // [41] Line of Business Code
        // 2018/07/27 S21_NA#14307 Add End

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL0140
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL0140
     */
    public static Object[] getParamNWAL0140(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        // 2016/03/02 S21_NA#4377 Mod Start
        if (checkReadOnlyByStatus(scrnMsg)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, ZYPConstant.FLG_OFF_N);
        }
        // 2016/03/02 S21_NA#4377 Mod End

        if (NO_SLCT_DTL == selectIdx) {
            paramList.add(scrnMsg.xxPopPrm_PA); // no used
            paramList.add(scrnMsg.shipToCustCd);
            paramList.add(scrnMsg.shipToLocNm);
            paramList.add(scrnMsg.shipToAddlLocNm);
            paramList.add(scrnMsg.shipToFirstLineAddr);
            paramList.add(scrnMsg.shipToScdLineAddr);
            paramList.add(scrnMsg.shipToThirdLineAddr);
            paramList.add(scrnMsg.shipToFrthLineAddr);
            paramList.add(scrnMsg.shipToFirstRefCmntTxt);
            paramList.add(scrnMsg.shipToScdRefCmntTxt);
            paramList.add(scrnMsg.shipToCtyAddr);
            paramList.add(scrnMsg.shipToStCd);
            paramList.add(scrnMsg.shipToPostCd);
            paramList.add(scrnMsg.shipToCtryCd);
            paramList.add(scrnMsg.shipToCntyNm);
            paramList.add(scrnMsg.dropShipFlg);
            paramList.add(scrnMsg.xxPopPrm_P1); // ReadOnlyFlg
            paramList.add(scrnMsg.billToCustCd);
            paramList.add(scrnMsg.xxPopPrm_PA); // no used
            paramList.add(scrnMsg.sellToCustCd);
            paramList.add(scrnMsg.xxPopPrm_PA); // no used
            paramList.add(scrnMsg.shipToProvNm);
        } else {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                paramList.add(scrnMsg.A.no(selectIdx).dsOrdPosnNum_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToCustCd_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToLocNm_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToAddlLocNm_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToFirstLineAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToScdLineAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToThirdLineAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToFrthLineAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToFirstRefCmntTxt_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToScdRefCmntTxt_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToCtyAddr_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToStCd_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToPostCd_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToCtryCd_LC);
                paramList.add(scrnMsg.A.no(selectIdx).shipToCntyNm_LC);
                paramList.add(scrnMsg.A.no(selectIdx).dropShipFlg_LC);
                paramList.add(scrnMsg.xxPopPrm_P1); // ReadOnlyFlg
                paramList.add(scrnMsg.A.no(selectIdx).billToCustCd_LC);
                paramList.add(scrnMsg.xxPopPrm_PA); // no used
                paramList.add(scrnMsg.sellToCustCd);
                paramList.add(scrnMsg.xxPopPrm_PA); // no used
                paramList.add(scrnMsg.A.no(selectIdx).shipToProvNm_LC);
            } else {
                paramList.add(scrnMsg.C.no(selectIdx).dsOrdPosnNum_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToCustCd_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToLocNm_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToAddlLocNm_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToFirstLineAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToScdLineAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToThirdLineAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToFrthLineAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToFirstRefCmntTxt_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToScdRefCmntTxt_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToCtyAddr_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToStCd_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToPostCd_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToCtryCd_RC);
                paramList.add(scrnMsg.C.no(selectIdx).shipToCntyNm_RC);
                paramList.add(scrnMsg.C.no(selectIdx).dropShipFlg_RC);
                paramList.add(scrnMsg.xxPopPrm_P1); // ReadOnlyFlg
                paramList.add(scrnMsg.C.no(selectIdx).billToCustCd_RC);
                paramList.add(scrnMsg.xxPopPrm_PA); // no used
                paramList.add(scrnMsg.sellToCustCd);
                paramList.add(scrnMsg.xxPopPrm_PA); // no used
                paramList.add(scrnMsg.C.no(selectIdx).shipToProvNm_RC);
            }
        }

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6800
     * @param scrnMsg NWAL1500BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6800(NWAL1500BMsg scrnMsg) {

        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        clearPopUpParam(scrnMsg);

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, EIGHT_DIGIT_MODE);
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.srchOrigItemFlg_MF.getValue())) { // S21_NA#9761 2016/09/28 ADD
                paramList.add(scrnMsg.B.no(selectIdx).mdseCd_LL);
            } else {
                // S21_NA#9761 2016/09/28 ADD
                String mnfItemCd = scrnMsg.B.no(selectIdx).mdseCd_LL.getValue();
                scrnMsg.B.no(selectIdx).mdseCd_LL.clear();
                paramList.add(scrnMsg.B.no(selectIdx).mdseCd_LL);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, mnfItemCd);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, TEN_DIGIT_MODE);
            if (!ZYPConstant.FLG_ON_Y.equals(scrnMsg.srchOrigItemFlg_MF.getValue())) { // S21_NA#9761 2016/09/28 ADD
                paramList.add(scrnMsg.D.no(selectIdx).mdseCd_RL);
            } else {
                // S21_NA#9761 2016/09/28 ADD 
                String mnfItemCd = scrnMsg.D.no(selectIdx).mdseCd_RL.getValue();
                scrnMsg.D.no(selectIdx).mdseCd_RL.clear();
                paramList.add(scrnMsg.D.no(selectIdx).mdseCd_RL);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, mnfItemCd);
            }
        }

        paramList.add(scrnMsg.xxPopPrm_PA); // no used 1
        paramList.add(scrnMsg.xxPopPrm_PA); // no used 2
        paramList.add(scrnMsg.xxPopPrm_PA); // no used 3
        paramList.add(scrnMsg.xxPopPrm_PA); // no used 4
        paramList.add(scrnMsg.xxPopPrm_PA); // no used 5
        paramList.add(scrnMsg.xxPopPrm_PA); // no used 6
        paramList.add(scrnMsg.xxPopPrm_PA); // no used 7
        paramList.add(scrnMsg.xxPopPrm_PA); // no used 8
        paramList.add(scrnMsg.xxPopPrm_P0); // no used 9
        paramList.add(scrnMsg.xxPopPrm_PA); // no used 10
        paramList.add(scrnMsg.xxPopPrm_PA); // no used 11
        paramList.add(scrnMsg.xxPopPrm_P1); // no used 12  // S21_NA#9761 2016/09/28 ADD

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NMAL6050 For Payment Term
     * @param scrnMsg NWAL1500BMsg
     * @return Param NMAL6800
     */
    public static Object[] getParamNMAL6050ForPmtTerm(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        // [0]:TBL_NM
        scrnMsg.xxPopPrm_P0.setValue("PMT_TERM_CASH_DISC");
        paramList.add(scrnMsg.xxPopPrm_P0);

        // [1]:TBL_CD_COL_NM
        scrnMsg.xxPopPrm_P1.setValue("PMT_TERM_CASH_DISC_CD");
        paramList.add(scrnMsg.xxPopPrm_P1);

        // [2]:TBL_NM_COL_NM
        scrnMsg.xxPopPrm_P2.setValue("PMT_TERM_CASH_DISC_DESC_TXT");
        paramList.add(scrnMsg.xxPopPrm_P2);

        // [3]:TBL_SORT_COL_NM
        scrnMsg.xxPopPrm_P3.setValue("PMT_TERM_CASH_DISC_SORT_NUM");
        paramList.add(scrnMsg.xxPopPrm_P3);

        // [4]:SCR_NM
        scrnMsg.xxPopPrm_P4.setValue("Payment Term Search");
        paramList.add(scrnMsg.xxPopPrm_P4);

        // [5]:HDR_CD_LB_NM
        scrnMsg.xxPopPrm_P5.setValue("Payment Term Code");
        paramList.add(scrnMsg.xxPopPrm_P5);

        // [6]:HDR_NM_LB_NM
        scrnMsg.xxPopPrm_P6.setValue("Payment Term Name");
        paramList.add(scrnMsg.xxPopPrm_P6);

        // [7]:DTL_CD_LB_NM
        scrnMsg.xxPopPrm_P7.setValue("Payment Term Code");
        paramList.add(scrnMsg.xxPopPrm_P7);

        // [8]:DTL_NM_LB_NM
        scrnMsg.xxPopPrm_P8.setValue("Payment Term Name");
        paramList.add(scrnMsg.xxPopPrm_P8);

        // [9]:COND_CD
        scrnMsg.xxPopPrm_P9.clear();
        paramList.add(scrnMsg.xxPopPrm_P9);

        // [10]:COND_NM
        paramList.add(scrnMsg.pmtTermCashDiscDescTxt);

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL1600
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1600
     */
    public static Object[] getParamNWAL1600(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        // START 2023/06/06 T.Doi [CSA-QC#61465, DEL]
        // int slctConfNum = scrnMsg.xxCellIdx.getValueInt();
        // END 2023/06/06 T.Doi [CSA-QC#61465, DEL]
        EZDBMsgArray slsCrList = null;

        // 2017/10/13 S21_NA#21267 Add Start
        boolean isOrderCredit = NWAL1500CommonLogic.isOrderCredit(scrnMsg);
        // 2017/10/13 S21_NA#21267 Add End

        // 2016/03/02 S21_NA#4377 Mod Start
        if (checkReadOnlyByStatus(scrnMsg) //
                || isOrderCredit) { // 2017/10/13 S21_NA#21267 Add isOrderCredit
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1600Constant.MODE_REFERENCE);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1600Constant.MODE_EDIT_ALL);
        }
        // 2016/03/02 S21_NA#4377 Mod End
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.cpoOrdNum);

        // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
        String dsOrdPosnNumTxt = scrnMsg.xxComnScrColValTxt_P2.getValue();
        List<String> dsOrdPosnNumList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(dsOrdPosnNumTxt)) {
            dsOrdPosnNumList = Arrays.asList(dsOrdPosnNumTxt.split(","));
        }
        // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // START 2023/06/06 T.Doi [CSA-QC#61465, DEL]
            //NWAL1500_ABMsg configMsg = scrnMsg.A.no(slctConfNum);
            //String dsOrdPosnNum = configMsg.dsOrdPosnNum_LC.getValue();

            //ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, configMsg.dsOrdPosnNum_LC);
            // END 2023/06/06 T.Doi [CSA-QC#61465, DEL]
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, "O");
            scrnMsg.O.clear();

            int prmIdx = 0;

            // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
            if (dsOrdPosnNumList.size() == 1) {
                String dsOrdPosnNum = dsOrdPosnNumList.get(0);
            // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]

                for (int i = 0; i < scrnMsg.G.getValidCount(); i++) {
                    NWAL1500_GBMsg slsCrMsg = scrnMsg.G.no(i);
                    if (dsOrdPosnNum.equals(slsCrMsg.dsOrdPosnNum_GS.getValue())) {
                        String xxRqstTpCd = slsCrMsg.xxRqstTpCd_GS.getValue();
                        if (ZYPCommonFunc.hasValue(xxRqstTpCd) && NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(xxRqstTpCd)) {
                            continue;
                        }
                        EZDMsg.copy(slsCrMsg, "GS", scrnMsg.O.no(prmIdx), "O");
                        scrnMsg.O.no(prmIdx).configCatgCd_O.setValue(CONFIG_CATG.OUTBOUND);
                        prmIdx++;
                    }
                }
            // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
            }
            // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]
            scrnMsg.O.setValidCount(prmIdx);
            slsCrList = scrnMsg.O;
        } else if (TAB_RMA.equals(dplyTab)) {
            // START 2023/06/06 T.Doi [CSA-QC#61465, DEL]
            //NWAL1500_CBMsg rmaConfigMsg = scrnMsg.C.no(slctConfNum);
            //String dsOrdPosnNum = rmaConfigMsg.dsOrdPosnNum_RC.getValue();

            //ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, rmaConfigMsg.dsOrdPosnNum_RC);
            // END 2023/06/06 T.Doi [CSA-QC#61465, DEL]
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, "O");
            scrnMsg.O.clear();

            int prmIdx = 0;

            // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
            if (dsOrdPosnNumList.size() == 1) {
                String dsOrdPosnNum = dsOrdPosnNumList.get(0);
            // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]

                for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
                    NWAL1500_HBMsg rmaSlsCrMsg = scrnMsg.H.no(i);
                    if (dsOrdPosnNum.equals(rmaSlsCrMsg.dsOrdPosnNum_HS.getValue())) {
                        String xxRqstTpCd = rmaSlsCrMsg.xxRqstTpCd_HS.getValue();
                        if (ZYPCommonFunc.hasValue(xxRqstTpCd) && NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(xxRqstTpCd)) {
                            continue;
                        }
                        EZDMsg.copy(rmaSlsCrMsg, "HS", scrnMsg.O.no(prmIdx), "O");
                        scrnMsg.O.no(prmIdx).configCatgCd_O.setValue(CONFIG_CATG.INBOUND);
                        prmIdx++;
                    }
                }
            // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
            }
            // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]
            scrnMsg.O.setValidCount(prmIdx);
            slsCrList = scrnMsg.O;
        } else {
            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            // scrnMsg.xxPopPrm_P2.clear();
            scrnMsg.xxComnScrColValTxt_P2.clear();
            // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, "O");
            getHdrSlCreditList(scrnMsg);
            slsCrList = scrnMsg.O;
        }

        Object[] params = new Object[IDX_6];
        params[IDX_0] = scrnMsg.xxPopPrm_P0;
        params[IDX_1] = scrnMsg.xxPopPrm_P1;
        // START 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        // params[IDX_2] = scrnMsg.xxPopPrm_P2;
        params[IDX_2] = scrnMsg.xxComnScrColValTxt_P2;
        // END 2023/06/06 T.Doi [CSA-QC#61465, MOD]
        params[IDX_3] = scrnMsg.xxPopPrm_P3;
        params[IDX_4] = slsCrList;
        params[IDX_5] = scrnMsg.xxPopPrm_P4;
        return params;
    }

    /**
     * Get Param NWAL1600
     * @param scrnMsg NWAL1500BMsg
     */
    public static void getHdrSlCreditList(NWAL1500BMsg scrnMsg) {

        String hdrSlsRepTocCd = scrnMsg.slsRepTocCd.getValue();
        scrnMsg.O.clear();
        scrnMsg.O.setValidCount(0);

        if (ZYPCommonFunc.hasValue(hdrSlsRepTocCd)) {
            boolean chkAllDelete = true;
            for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
                NWAL1500_FBMsg slsCrMsg = scrnMsg.F.no(i);
                String xxRqstTpCd = slsCrMsg.xxRqstTpCd_FS.getValue();
                BigDecimal dsCpoSlsCrPk = slsCrMsg.dsCpoSlsCrPk_FS.getValue();
                if (!ZYPCommonFunc.hasValue(xxRqstTpCd) && !ZYPCommonFunc.hasValue(dsCpoSlsCrPk)) {
                    xxRqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_NEW;
                } else if (!ZYPCommonFunc.hasValue(xxRqstTpCd) && ZYPCommonFunc.hasValue(dsCpoSlsCrPk)) {
                    xxRqstTpCd = NWZC150001Constant.RQST_TP_SLS_CR_MODIFY;
                }
                if (!NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(xxRqstTpCd)) {
                    chkAllDelete = false;
                    break;
                }
            }
            if (scrnMsg.F.getValidCount() > 0 && !chkAllDelete) {
                for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
                    NWAL1500_FBMsg slsRepMsg = scrnMsg.F.no(i);
                    String lineBizRoleTpCd = slsRepMsg.slsRepRoleTpCd_FS.getValue();

                    // 2017/11/02 S21_NA#20146 Mod Start
                    //if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd) || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd)) {
                    //    ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_FS, hdrSlsRepTocCd);
                    //    String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                    //    if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                    //        ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_WRITER);
                    //        ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_WRITER);
                    //    } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                    //        ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.PPS_WRITER);
                    //        ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.PPS_WRITER);
                    //    } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                    //        ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.LFS_WRITER);
                    //        ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.LFS_WRITER);
                    //    }
                    if (LINE_BIZ_ROLE_TP.ESS_WRITER.equals(lineBizRoleTpCd) //
                            || LINE_BIZ_ROLE_TP.PPS_WRITER.equals(lineBizRoleTpCd) //
                            || LINE_BIZ_ROLE_TP.LFS_WRITER.equals(lineBizRoleTpCd) //
                            || LINE_BIZ_ROLE_TP.EMSD_WRITER.equals(lineBizRoleTpCd)) {
                        ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_FS, hdrSlsRepTocCd);
                        String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                        if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_WRITER);
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_WRITER);
                        } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.PPS_WRITER);
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.PPS_WRITER);
                        } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.LFS_WRITER);
                            ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.LFS_WRITER);
                        } else if (LINE_BIZ_TP.EMSD.equals(lineBizTpCd)) {
                            // 2017/11/16 S21_NA#22620 Mod Start
                            //String resultFlg = NWXC150001DsCheck.getOrdCatgBizCtxFlg(scrnMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, scrnMsg.dsOrdCatgCd.getValue(), scrnMsg.dsOrdTpCd.getValue());
                            //if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
                            //    ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_WRITER);
                            //    ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.ESS_WRITER);
                            //} else {
                            //    ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, LINE_BIZ_ROLE_TP.EMSD_WRITER);
                            //    ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS, LINE_BIZ_ROLE_TP.EMSD_WRITER);
                            //}
                            String fstBizCtxAttbTxt = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(scrnMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, scrnMsg.dsOrdCatgCd.getValue(), scrnMsg.dsOrdTpCd.getValue());
                            if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                                ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS, fstBizCtxAttbTxt);
                                ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS, fstBizCtxAttbTxt);
                            }
                            // 2017/11/16 S21_NA#22620 Mod End
                        }
                    // 2017/11/02 S21_NA#20146 Mod End
                        break;
                    }
                }
            } else {
                // int hdrSlsCrCnt = scrnMsg.F.getValidCount();
                // NWAL1500_FBMsg slsRepMsg = scrnMsg.F.no(hdrSlsCrCnt);
                // ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepTocCd_FS, hdrSlsRepTocCd);
                // ZYPEZDItemValueSetter.setValue(slsRepMsg.slsCrQuotFlg_FS, ZYPConstant.FLG_ON_Y);
                //
                // String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                // if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                // ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS,
                // LINE_BIZ_ROLE_TP.ESS_WRITER);
                // ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS,
                // LINE_BIZ_ROLE_TP.ESS_WRITER);
                // } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                // ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS,
                // LINE_BIZ_ROLE_TP.PPS_WRITER);
                // ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS,
                // LINE_BIZ_ROLE_TP.PPS_WRITER);
                // } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                // ZYPEZDItemValueSetter.setValue(slsRepMsg.slsRepRoleTpCd_FS,
                // LINE_BIZ_ROLE_TP.LFS_WRITER);
                // ZYPEZDItemValueSetter.setValue(slsRepMsg.lineBizRoleTpCd_FS,
                // LINE_BIZ_ROLE_TP.LFS_WRITER);
                // }
                // scrnMsg.F.setValidCount(hdrSlsCrCnt + 1);

                NWAL1500_OBMsg paramSlsRepMsg = scrnMsg.O.no(0);
                ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.slsRepTocCd_O, hdrSlsRepTocCd);
                ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.slsCrQuotFlg_O, ZYPConstant.FLG_ON_Y);
                paramSlsRepMsg.slsRepCrPct_O.setValue(BigDecimal.valueOf(100));

                String lineBizTpCd = scrnMsg.lineBizTpCd.getValue();
                if (LINE_BIZ_TP.ESS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, LINE_BIZ_ROLE_TP.ESS_WRITER);
                } else if (LINE_BIZ_TP.PPS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, LINE_BIZ_ROLE_TP.PPS_WRITER);
                } else if (LINE_BIZ_TP.LFS.equals(lineBizTpCd)) {
                    ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, LINE_BIZ_ROLE_TP.LFS_WRITER);
                // 2017/11/02 S21_NA#20146 Add Start
                } else if (LINE_BIZ_TP.EMSD.equals(lineBizTpCd)) {
                    // 2017/11/16 S21_NA#22620 Mod Start
                    //String resultFlg = NWXC150001DsCheck.getOrdCatgBizCtxFlg(scrnMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, scrnMsg.dsOrdCatgCd.getValue(), scrnMsg.dsOrdTpCd.getValue());
                    //if (ZYPConstant.FLG_ON_Y.equals(resultFlg)) {
                    //    ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, LINE_BIZ_ROLE_TP.ESS_WRITER);
                    //} else {
                    //    ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, LINE_BIZ_ROLE_TP.EMSD_WRITER);
                    //}
                    String fstBizCtxAttbTxt = NWXC150001DsCheck.getOrdCatgBizCtxFstAttbTxt(scrnMsg.glblCmpyCd.getValue(), ORD_CATG_CTX_TP.EMSD_ORDER_VALUE_SET, scrnMsg.dsOrdCatgCd.getValue(), scrnMsg.dsOrdTpCd.getValue());
                    if (ZYPCommonFunc.hasValue(fstBizCtxAttbTxt)) {
                        ZYPEZDItemValueSetter.setValue(paramSlsRepMsg.lineBizRoleTpCd_O, fstBizCtxAttbTxt);
                    }
                    // 2017/11/16 S21_NA#22620 Mod End
                // 2017/11/02 S21_NA#20146 Add End
                }
                scrnMsg.O.setValidCount(1);
            }
        }

        if (scrnMsg.O.getValidCount() == 0) {
            int paramSlsCrCnt = 0;
            for (int i = 0; i < scrnMsg.F.getValidCount(); i++) {
                NWAL1500_FBMsg hdrSlsCrMsg = scrnMsg.F.no(i);
                String xxRqstTpCd = hdrSlsCrMsg.xxRqstTpCd_FS.getValue();
                if (NWZC150001Constant.RQST_TP_SLS_CR_DELETE.equals(xxRqstTpCd)) {
                    continue;
                }
                EZDMsg.copy(hdrSlsCrMsg, "FS", scrnMsg.O.no(paramSlsCrCnt), "O");
                paramSlsCrCnt++;
            }
            scrnMsg.O.setValidCount(paramSlsCrCnt);
        }

        return;
    }

    /**
     * Get Param NWAL1630
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1630
     */
    public static Object[] getParamNWAL1630(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int slctConfNum = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(slctConfNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, lineMsg.csmpPrcListCd_LL);
            if (checkReadOnlyByLineStatus(lineMsg)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1630Constant.MODE_REFERENCE);
            }
        } else {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(slctConfNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, rmaLineMsg.csmpPrcListCd_RL);
            if (checkReadOnlyByRmaLineStatus(rmaLineMsg)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1630Constant.MODE_REFERENCE);
            }
        }
        // 2017/10/13 S21_NA#21267 Add Start
        boolean isOrderCredit = NWAL1500CommonLogic.isOrderCredit(scrnMsg);
        if (isOrderCredit) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1630Constant.MODE_REFERENCE);
        }
        // 2017/10/13 S21_NA#21267 Add End

        // 2016/03/02 S21_NA#4377 Mod Start
        if (checkReadOnlyByStatus(scrnMsg)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1630Constant.MODE_REFERENCE);
        }
        // 2016/03/02 S21_NA#4377 Mod End

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.cpoOrdNum);                 // [0]cpoOrdNum
        paramList.add(scrnMsg.sellToCustCd);              // [1]sellToCustCd  S21_NA#2557
        paramList.add(scrnMsg.dsOrdCatgCd);               // [2]dsOrdCatgCd
        paramList.add(scrnMsg.dsOrdTpCd);                 // [3]dsOrdTpCd
        paramList.add(scrnMsg.lineBizTpCd);               // [4]lineBizTpCd
        paramList.add(scrnMsg.ordDt);                     // [5]ordDt
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(slctConfNum);
            paramList.add(lineMsg.dsOrdPosnNum_LL);       // [6]dsOrdPosnNum
            paramList.add(lineMsg.dsCpoLineNum_LL);       // [7]dsCpoLineNum
            paramList.add(lineMsg.dsCpoLineSubNum_LL);    // [8]dsCpoLineSubNum
            // 2016/06/10 S21_NA#8912 Mod Start
//            paramList.add(scrnMsg.csmpContrNum);
//            paramList.add(scrnMsg.dlrRefNum);
            paramList.add(lineMsg.csmpContrNum_LL);       // [9]csmpContrNum
            paramList.add(lineMsg.dlrRefNum_LL);          // [10]dlrRefNum
            // 2016/06/10 S21_NA#8912 Mod End
            paramList.add(lineMsg.csmpPrcListCd_LL);      // [11]csmpPrcListCd
        } else {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(slctConfNum);
            paramList.add(rmaLineMsg.dsOrdPosnNum_RL);    // [6]dsOrdPosnNum
            paramList.add(rmaLineMsg.dsCpoLineNum_RL);    // [7]dsCpoLineNum
            paramList.add(rmaLineMsg.dsCpoLineSubNum_RL); // [8]dsCpoLineSubNum
            // 2016/06/10 S21_NA#8912 Mod Start
//            paramList.add(scrnMsg.csmpContrNum);
//            paramList.add(scrnMsg.dlrRefNum);
            paramList.add(rmaLineMsg.csmpContrNum_RL);    // [9]csmpContrNum
            paramList.add(rmaLineMsg.dlrRefNum_RL);       // [10]dlrRefNum
            // 2016/06/10 S21_NA#8912 Mod End
            paramList.add(rmaLineMsg.csmpPrcListCd_RL);   // [11]csmpPrcListCd
        }

        paramList.add(scrnMsg.xxPopPrm_PA);               // [12]splyAbuseNodePrflCd
        paramList.add(scrnMsg.xxPopPrm_PA);               // [13]serNum
        // 2017/09/22 QC#18859 Mod Start
//      paramList.add(scrnMsg.xxPopPrm_PA);
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // 2018/06/29 S21_NA#25441 Mod Start
//            paramList.add(scrnMsg.xxPopPrm_PA);           
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(slctConfNum);
            paramList.add(lineMsg.dsContrNum_LL);         // [14]dsContrNum
            // 2018/06/29 S21_NA#25441 Mod End
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(slctConfNum);
            paramList.add(rmaLineMsg.dsContrNum_RL);      // [14]dsContrNum
        }
        // 2019/01/23 S21_NA#29446 Del Start
        // 2017/09/22 QC#18859 Mod End
        // paramList.add(scrnMsg.xxPopPrm_PA);               // [15]dlrFleetNum
        // paramList.add(scrnMsg.xxPopPrm_PA);               // [16]splyCdTxt
        // 2018/02/01 S21_NA#19808 Mod Start
        // if (TAB_LINE_CONFIG.equals(dplyTab)) {
        //     paramList.add(scrnMsg.xxPageShowOfNum_LL);        // [17]annTermCapNum_BW
        //     paramList.add(scrnMsg.xxPageShowOfNum_LL);        // [18]annTermCapNum_CL
        // } else if (TAB_RMA.equals(dplyTab)) {
        //     paramList.add(scrnMsg.xxPageShowOfNum_RL);        // [17]annTermCapNum_BW
        //     paramList.add(scrnMsg.xxPageShowOfNum_RL);        // [18]annTermCapNum_CL
        // }
        // 2018/02/01 S21_NA#19808 Mod End
        // paramList.add(scrnMsg.ordDt);                     // [19]rntlTrmnDt 
        // 2016/03/02 S21_NA#4377 Add Start
        paramList.add(scrnMsg.xxPopPrm_P1);               // [15]xxModeCd Mod 20→15
        // 2016/03/02 S21_NA#4377 Add End
        // 2017/09/22 QC#18859 Add Start
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // 2018/06/29 S21_NA#25441 Mod Start
//            paramList.add(scrnMsg.xxPageShowOfNum_LL);
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(slctConfNum);
            paramList.add(lineMsg.svcMachMstrPk_LL);      // [16]svcMachMstrPk Mod 21→16
            // 2018/06/29 S21_NA#25441 Mod End
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(slctConfNum);
            paramList.add(rmaLineMsg.svcMachMstrPk_RL);   // [16]svcMachMstrPk Mod 21→16
        }
        // 2017/09/22 QC#18859 Add End

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL1640
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1640
     */
    public static Object[] getParamNWAL1640(NWAL1500BMsg scrnMsg) {

        NWAL1500_BBMsg lineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());

        // 2016/03/02 S21_NA#4377 Mod Start
        if (checkReadOnlyByStatus(scrnMsg) || checkReadOnlyByLineStatus(lineMsg)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PC, NWAL1640Constant.MODE_REFERENCE);
        }
        // 2016/03/02 S21_NA#4377 Mod End

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.cpoOrdNum);
        paramList.add(lineMsg.dsOrdPosnNum_LL);
        paramList.add(lineMsg.dsCpoLineNum_LL);
        paramList.add(lineMsg.dsCpoLineSubNum_LL);
        paramList.add(scrnMsg.sellToCustCd);
        paramList.add(scrnMsg.soldToCustLocCd);
        // 2019/10/04 S21_NA#51372 Add Start
        String sellToCustAcctNm = scrnMsg.xxPopPrm_P0.getValue();
        if (sellToCustAcctNm.length() >  lineMsg.getAttr("prntVndNm_LL").getDigit()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, sellToCustAcctNm.substring(0, lineMsg.getAttr("prntVndNm_LL").getDigit()));
        }
        // 2019/10/04 S21_NA#51372 Add End
        paramList.add(scrnMsg.xxPopPrm_P0); // SELL_TO_CUST_LOC_NM
        paramList.add(scrnMsg.xxPopPrm_P1); // SELL_TO_CUST_FIRST_LINE_ADDR
        paramList.add(scrnMsg.xxPopPrm_P2); // SELL_TO_CUST_CTY_ADDR
        paramList.add(scrnMsg.xxPopPrm_P3); // SELL_TO_CUST_ST_CD
        paramList.add(scrnMsg.xxPopPrm_P4); // SELL_TO_CUST_POST_CD
        paramList.add(scrnMsg.xxPopPrm_P5); // Bill To Account Number(Config)
        paramList.add(scrnMsg.xxPopPrm_PB); // Bill To Location Number(Config)
        // 2019/10/04 S21_NA#51372 Add Start
        String billToCustLocNm = scrnMsg.xxPopPrm_P6.getValue();
        if (billToCustLocNm.length() >  lineMsg.getAttr("prntVndNm_LL").getDigit()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, billToCustLocNm.substring(0, lineMsg.getAttr("prntVndNm_LL").getDigit()));
        }
        // 2019/10/04 S21_NA#51372 Add End
        paramList.add(scrnMsg.xxPopPrm_P6); // BILL_TO_CUST_LOC_NM
        paramList.add(scrnMsg.xxPopPrm_P7); // BILL_TO_CUST_FIRST_LINE_ADDR
        paramList.add(scrnMsg.xxPopPrm_P8); // BILL_TO_CUST_CTY_ADDR
        paramList.add(scrnMsg.xxPopPrm_P9); // BILL_TO_CUST_ST_CD
        paramList.add(scrnMsg.xxPopPrm_PA); // BILL_TO_CUST_POST_CD
        paramList.add(lineMsg.splyTpCd_LL);
        // 2019/10/04 S21_NA#51372 Mod Start
        //paramList.add(lineMsg.splyNm_LL);
        paramList.add(lineMsg.prntVndNm_LL);
        // 2019/10/04 S21_NA#51372 Mod End
        paramList.add(lineMsg.splyFirstAddr_LL);
        paramList.add(lineMsg.splyCtyAddr_LL);
        paramList.add(lineMsg.splyStCd_LL);
        paramList.add(lineMsg.splyPostCd_LL);
        // 2016/03/02 S21_NA#4377 Add Start
        paramList.add(scrnMsg.xxPopPrm_PC); // Mode
        // 2016/03/02 S21_NA#4377 Add End

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL1650
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1650
     */
    public static Object[] getParamNWAL1650(NWAL1500BMsg scrnMsg) {

        NWAL1500_BBMsg lineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());
        String dsOrdPosnNum = lineMsg.dsOrdPosnNum_LL.getValue();

        clearPopUpParam(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, getConfShipToCd(scrnMsg, dsOrdPosnNum));

        // 2016/03/02 S21_NA#4377 Mod Start
        if (checkReadOnlyByStatus(scrnMsg) || checkReadOnlyByLineStatus(lineMsg)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1650Constant.MODE_REFERENCE);
        }
        // 2016/03/02 S21_NA#4377 Mod End

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.cpoOrdNum);
        paramList.add(lineMsg.dsOrdPosnNum_LL);
        paramList.add(lineMsg.dsCpoLineNum_LL);
        paramList.add(lineMsg.dsCpoLineSubNum_LL);
        paramList.add(scrnMsg.xxPopPrm_P0); // Ship To
        paramList.add(lineMsg.bllgAttrbCustAcctCd_LL);
        paramList.add(lineMsg.firstBllgAttrbNm_LL);
        paramList.add(lineMsg.firstBllgAttrbValTxt_LL);
        paramList.add(lineMsg.scdBllgAttrbNm_LL);
        paramList.add(lineMsg.scdBllgAttrbValTxt_LL);
        paramList.add(lineMsg.thirdBllgAttrbNm_LL);
        paramList.add(lineMsg.thirdBllgAttrbValTxt_LL);
        paramList.add(lineMsg.frthBllgAttrbNm_LL);
        paramList.add(lineMsg.frthBllgAttrbValTxt_LL);
        paramList.add(lineMsg.fifthBllgAttrbNm_LL);
        paramList.add(lineMsg.fifthBllgAttrbValTxt_LL);
        paramList.add(lineMsg.sixthBllgAttrbNm_LL);
        paramList.add(lineMsg.sixthBllgAttrbValTxt_LL);
        // 2016/03/02 S21_NA#4377 Add Start
        paramList.add(scrnMsg.xxPopPrm_P1); // Mode
        // 2016/03/02 S21_NA#4377 Add End

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Config Ship To Code
     * @param scrnMsg NWAL1500BMsg
     * @param dsOrdPosnNum DS Order Position Number
     * @return Config Ship To Code
     */
    private static String getConfShipToCd(NWAL1500BMsg scrnMsg, String dsOrdPosnNum) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            String confDsOrdPosnNum = scrnMsg.A.no(i).dsOrdPosnNum_LC.getValue();
            if (dsOrdPosnNum.equals(confDsOrdPosnNum)) {
                return scrnMsg.A.no(i).shipToCustCd_LC.getValue();
            }
        }

        return null;
    }

    /**
     * Get Param NLCL1000
     * @param scrnMsg NWAL1500BMsg
     * @return Param NLCL1000
     */
    public static Object[] getParamNLCL1000(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P5, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, ZYPConstant.FLG_OFF_N);

        NWAL1500_MBMsgArray detailList = scrnMsg.M;
        // 2018/03/05 S21_NA#19808 Del Start
//        int paramListSize = scrnMsg.M.length(); // 2016/11/16 S21_NA#16021 Add
//        // S21_NA#9221 start
//        List<Integer> wkCheckList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
//        List<Integer> checkList = editCheckListForSet(wkCheckList, scrnMsg.B);
//        // S21_NA#9221 end
//
//        int validCnt = 0;
////        for (; validCnt < checkList.size(); validCnt++) { 2016/11/16 S21_NA#16021 Del
//        for (; validCnt < checkList.size() && validCnt < paramListSize; validCnt++) { // 2016/11/16 S21_NA#16021 Add Condition validCnt < paramListSize
//            int slctLineNum = checkList.get(validCnt);
//            NWAL1500_BBMsg slctLineMsg = scrnMsg.B.no(slctLineNum);
//
//            // set detailList
//            NWAL1500_MBMsg detailMsg = detailList.no(validCnt);
//
//            // Add Start NA QC#2177
//            StringBuilder dispLineNum = new StringBuilder();
//            dispLineNum.append(slctLineMsg.dsOrdPosnNum_LL.getValue());
//            dispLineNum.append(".");
//            dispLineNum.append(slctLineMsg.dsCpoLineNum_LL.getValue());
//            // 2016/03/07 S21_NA#5000#86 Add Start
//            if (ZYPCommonFunc.hasValue(slctLineMsg.dsCpoLineSubNum_LL)) {
//                dispLineNum.append(".");
//                dispLineNum.append(slctLineMsg.dsCpoLineSubNum_LL.getValue());
//            }
//            // 2016/03/07 S21_NA#5000#86 Add End
//            // Add End NA QC#2177
//
//            ZYPEZDItemValueSetter.setValue(detailMsg.xxScrItem20Txt_P1, dispLineNum.toString());
//
//            // 2016/07/05 S21_NA#7441 Mod Start
//            // 2016/08/23 S21_NA#12024 Mod Start
//            List<String> dropShipWHCodeList = getDropShipWHCodeList(scrnMsg);
////            if (ZYPCommonFunc.hasValue(slctLineMsg.rtlWhCd_LL) && ZYPCommonFunc.hasValue(slctLineMsg.rtlSwhCd_LL)) {
//            // 2016/08/23 S21_NA#12024 Mod End
//            if ((ZYPCommonFunc.hasValue(slctLineMsg.rtlWhCd_LL) && ZYPCommonFunc.hasValue(slctLineMsg.rtlSwhCd_LL))
//                    || (ZYPCommonFunc.hasValue(slctLineMsg.rtlWhCd_LL) && dropShipWHCodeList.contains(slctLineMsg.rtlWhCd_LL.getValue()))) {
//                ZYPEZDItemValueSetter.setValue(detailMsg.rtlWhCd_P1, slctLineMsg.rtlWhCd_LL);
//                ZYPEZDItemValueSetter.setValue(detailMsg.rtlSwhCd_P1, slctLineMsg.rtlSwhCd_LL);
//            } else if (ZYPCommonFunc.hasValue(slctLineMsg.invtyLocCd_LL)) {
//                ZYPEZDItemValueSetter.setValue(detailMsg.invtyLocCd_P1, slctLineMsg.invtyLocCd_LL);
//            }
//            // 2016/07/05 S21_NA#7441 Mod End
//
//            ZYPEZDItemValueSetter.setValue(detailMsg.shipToCustCd_P1, getConfShipToCustCd(scrnMsg, slctLineMsg));
//
//            ZYPEZDItemValueSetter.setValue(detailMsg.mdseCd_P1, slctLineMsg.mdseCd_LL);
//            ZYPEZDItemValueSetter.setValue(detailMsg.ordQty_P1, slctLineMsg.ordQty_LL);
//            ZYPEZDItemValueSetter.setValue(detailMsg.rddDt_P1, slctLineMsg.rddDt_LL);
//        }
//        detailList.setValidCount(validCnt);
        // 2018/03/05 S21_NA#19808 Del End

        Object[] params = new Object[IDX_10];
        params[IDX_0] = scrnMsg.xxPopPrm_P0;
        params[IDX_1] = scrnMsg.xxPopPrm_P1;
        params[IDX_2] = scrnMsg.xxPopPrm_P2;
        params[IDX_3] = scrnMsg.xxPopPrm_P3;
        params[IDX_4] = scrnMsg.xxPopPrm_P4;
        params[IDX_5] = scrnMsg.xxPopPrm_P5;
        params[IDX_6] = scrnMsg.xxPopPrm_P6;
        params[IDX_7] = scrnMsg.xxPopPrm_P7;
        params[IDX_8] = scrnMsg.xxPopPrm_P8;
        params[IDX_9] = detailList;
        return params;
    }

    // 2016/08/23 S21_NA#12024 Add Start
    private static List<String> getDropShipWHCodeList(NWAL1500BMsg scrnMsg) {
        List<String> externalLocList = new ArrayList<String>();
        String externalLocConstValue = ZYPCodeDataUtil.getVarCharConstValue(NLCL1000_DROP_SHIP_RTL_WH_CD, scrnMsg.glblCmpyCd.getValue());
        if (externalLocConstValue != null) {
            String[] externalLocConstArr = externalLocConstValue.split(",");
            for (int i = 0; i < externalLocConstArr.length; i++) {
                externalLocList.add(externalLocConstArr[i]);
            }
        } else {
            externalLocList.add(DEF_DROP_SHIP_RTL_WH_CD);
        }
        return externalLocList;
    }
    // 2016/08/23 S21_NA#12024 Add End

    // S21_NA#9221
    private static List<Integer> editCheckListForSet(//
            List<Integer> checkList, NWAL1500_BBMsgArray bDtlMsgArray) {
        List<Integer> rtnList = new ArrayList<Integer>();
        for (int ix : checkList) {
            NWAL1500_BBMsg bDtlMsg = bDtlMsgArray.no(ix);
            // is not Set parent item
            if (!"000".equals(bDtlMsg.cpoDtlLineSubNum_LL.getValue())) {
                rtnList.add(ix);
                continue;
            }

            StringBuilder dispLineNum = new StringBuilder();
            dispLineNum.append(bDtlMsg.dsOrdPosnNum_LL.getValue());
            dispLineNum.append(".");
            dispLineNum.append(bDtlMsg.dsCpoLineNum_LL.getValue());
            if (ZYPCommonFunc.hasValue(bDtlMsg.dsCpoLineSubNum_LL)) {
                dispLineNum.append(".");
                dispLineNum.append(bDtlMsg.dsCpoLineSubNum_LL.getValue());
            }
            for (int ixC = ix + 1; ixC < bDtlMsgArray.getValidCount(); ixC++) {
                //
                NWAL1500_BBMsg cmpBDtlMsg = bDtlMsgArray.no(ixC);
                StringBuilder compLineNum = new StringBuilder();
                compLineNum.append(cmpBDtlMsg.dsOrdPosnNum_LL.getValue());
                compLineNum.append(".");
                compLineNum.append(cmpBDtlMsg.dsCpoLineNum_LL.getValue());
                if (ZYPCommonFunc.hasValue(cmpBDtlMsg.dsCpoLineSubNum_LL)) {
                    compLineNum.append(".");
                    compLineNum.append(cmpBDtlMsg.dsCpoLineSubNum_LL.getValue());
                }
                //
                if (compLineNum.toString().startsWith(dispLineNum.toString())) {
                    rtnList.add(ixC);
                }
            }
        }
        return rtnList;

    }

    /**
     * Get Config Ship To Customer Code
     * @param scrnMsg NWAL1500BMsg
     * @param slctLineMsg Select Line Msg
     * @return Line Numbers
     */
    public static String getConfShipToCustCd(NWAL1500BMsg scrnMsg, NWAL1500_BBMsg slctLineMsg) {

        String dsOrdPosnNum = slctLineMsg.dsOrdPosnNum_LL.getValue();

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NWAL1500_ABMsg confMsg = scrnMsg.A.no(i);
            if (dsOrdPosnNum.equals(confMsg.dsOrdPosnNum_LC.getValue())) {
                return confMsg.shipToCustCd_LC.getValue();
            }
        }

        return null;
    }

    /**
     * Get Line Numbers
     * @param scrnMsg NWAL1500BMsg
     * @return Line Numbers
     */
    public static String getLineNums(NWAL1500BMsg scrnMsg) {

        if (scrnMsg.E.getValidCount() == 0) {
            return null;
        }

        StringBuilder lineNums = new StringBuilder();
        for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
            String lineNum = scrnMsg.E.no(i).xxLineNum_AW.getValue();
            if (ZYPCommonFunc.hasValue(lineNum)) {
                if (i != 0) {
                    lineNums.append(COMMA);
                }
                lineNums.append(lineNum);
            }
        }

        return lineNums.toString();
    }

    /**
     * Combine Customer Address
     * @param scrnMsg NWAL1500BMsg
     * @return Customer Address
     */
    public static String cmbnAddr(NWAL1500BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToFirstLineAddr)) {
            return null;
        }

        StringBuilder addr = new StringBuilder(scrnMsg.shipToFirstLineAddr.getValue());

        if (ZYPCommonFunc.hasValue(scrnMsg.shipToScdLineAddr)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToScdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToThirdLineAddr)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToThirdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToFrthLineAddr)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToFrthLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCtyAddr)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToCtyAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToStCd)) {
            addr.append(COMMA);
            addr.append(scrnMsg.shipToStCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToPostCd)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToPostCd.getValue());
        }

        return addr.toString();
    }

    /**
     * Combine Customer Address
     * @param scrnMsg NWAL1500BMsg
     * @param connectStr String
     * @return Customer Address
     */
    public static String cmbnAddr(NWAL1500BMsg scrnMsg, String connectStr) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.shipToFirstLineAddr)) {
            return null;
        }

        // 2018/01/26 S21_NA#23140 Mod Start
        StringBuilder addr = new StringBuilder("");
        // 2017/12/07 S21_NA#21621 Add Start
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, scrnMsg.dropShipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.addShipToLocNm, scrnMsg.shipToLocNm);
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToAddlLocNm)) {
                addr.append(scrnMsg.shipToAddlLocNm.getValue());
                addr.append(connectStr);
            }
        }
        // 2017/12/07 S21_NA#21621 Add End
        //StringBuilder addr = new StringBuilder(scrnMsg.shipToFirstLineAddr.getValue());
        addr.append(scrnMsg.shipToFirstLineAddr.getValue());
        // 2018/01/26 S21_NA#23140 Mod End

        if (ZYPCommonFunc.hasValue(scrnMsg.shipToScdLineAddr)) {
            addr.append(connectStr);
            addr.append(scrnMsg.shipToScdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToThirdLineAddr)) {
            addr.append(connectStr);
            addr.append(scrnMsg.shipToThirdLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToFrthLineAddr)) {
            addr.append(connectStr);
            addr.append(scrnMsg.shipToFrthLineAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCtyAddr)) {
            addr.append(connectStr);
            addr.append(scrnMsg.shipToCtyAddr.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToStCd)) {
            addr.append(COMMA);
            addr.append(scrnMsg.shipToStCd.getValue());
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToPostCd)) {
            addr.append(SPACE);
            addr.append(scrnMsg.shipToPostCd.getValue());
        }

        return addr.toString();
    }

    // 2018/01/26 S21_NA#23140 Add Start
    public static String cmbnAddrForConf(NWAL1500BMsg scrnMsg, String connectStr, int selectIdx) {

        StringBuilder addr = new StringBuilder("");
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            NWAL1500_ABMsg confMsg = scrnMsg.A.no(selectIdx);
            if (!ZYPCommonFunc.hasValue(confMsg.shipToFirstLineAddr_LC)) {
                return null;
            }
            if (ZYPConstant.FLG_ON_Y.equals(confMsg.dropShipFlg_LC.getValue()) //
                    && ZYPCommonFunc.hasValue(confMsg.shipToAddlLocNm_LC)) {
                addr.append(confMsg.shipToAddlLocNm_LC.getValue());
                addr.append(connectStr);
            }
            addr.append(confMsg.shipToFirstLineAddr_LC.getValue());
            if (ZYPCommonFunc.hasValue(confMsg.shipToScdLineAddr_LC)) {
                addr.append(connectStr);
                addr.append(confMsg.shipToScdLineAddr_LC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToThirdLineAddr_LC)) {
                addr.append(connectStr);
                addr.append(confMsg.shipToThirdLineAddr_LC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToFrthLineAddr_LC)) {
                addr.append(connectStr);
                addr.append(confMsg.shipToFrthLineAddr_LC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToCtyAddr_LC)) {
                addr.append(connectStr);
                addr.append(confMsg.shipToCtyAddr_LC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToStCd_LC)) {
                addr.append(COMMA);
                addr.append(confMsg.shipToStCd_LC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToPostCd_LC)) {
                addr.append(SPACE);
                addr.append(confMsg.shipToPostCd_LC.getValue());
            }
        } else {
            NWAL1500_CBMsg confMsg = scrnMsg.C.no(selectIdx);
            if (!ZYPCommonFunc.hasValue(confMsg.shipToFirstLineAddr_RC)) {
                return null;
            }
            if (ZYPConstant.FLG_ON_Y.equals(confMsg.dropShipFlg_RC.getValue()) // 
                    && ZYPCommonFunc.hasValue(confMsg.shipToAddlLocNm_RC)) {
                addr.append(confMsg.shipToAddlLocNm_RC.getValue());
                addr.append(connectStr);
            }
            addr.append(confMsg.shipToFirstLineAddr_RC.getValue());
            if (ZYPCommonFunc.hasValue(confMsg.shipToScdLineAddr_RC)) {
                addr.append(connectStr);
                addr.append(confMsg.shipToScdLineAddr_RC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToThirdLineAddr_RC)) {
                addr.append(connectStr);
                addr.append(confMsg.shipToThirdLineAddr_RC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToFrthLineAddr_RC)) {
                addr.append(connectStr);
                addr.append(confMsg.shipToFrthLineAddr_RC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToCtyAddr_RC)) {
                addr.append(connectStr);
                addr.append(confMsg.shipToCtyAddr_RC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToStCd_RC)) {
                addr.append(COMMA);
                addr.append(confMsg.shipToStCd_RC.getValue());
            }
            if (ZYPCommonFunc.hasValue(confMsg.shipToPostCd_RC)) {
                addr.append(SPACE);
                addr.append(confMsg.shipToPostCd_RC.getValue());
            }
        }
        return addr.toString();
    }
    // 2018/01/26 S21_NA#23140 Add End

    // S21_NA#1634 start
    // /**
    // * Control Merchandise Field
    // * @param scrnMsg NWAL1500BMsg
    // */
    // public static void controlMdseField(NWAL1500BMsg scrnMsg) {
    //
    // for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
    // NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
    // if (ZYPCommonFunc.hasValue(lineMsg.mdseCd_LL)) {
    // lineMsg.mdseCd_LL.setInputProtected(true);
    // lineMsg.xxImageTxt_BI.clear();
    // } else {
    // lineMsg.mdseCd_LL.setInputProtected(false);
    // lineMsg.xxImageTxt_BI.setValue(ZYPConstant.FLG_ON_Y);
    // }
    // }
    // }
    //
    // /**
    // * Control Merchandise Field For RMA
    // * @param scrnMsg NWAL1500BMsg
    // */
    // public static void controlMdseFieldForRma(NWAL1500BMsg scrnMsg)
    // {
    //
    // for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
    // NWAL1500_DBMsg rmaLineMsg = scrnMsg.D.no(i);
    // if (ZYPCommonFunc.hasValue(rmaLineMsg.mdseCd_RL)) {
    // rmaLineMsg.mdseCd_RL.setInputProtected(true);
    // rmaLineMsg.xxImageTxt_DI.clear();
    // } else {
    // rmaLineMsg.mdseCd_RL.setInputProtected(false);
    // rmaLineMsg.xxImageTxt_DI.setValue(ZYPConstant.FLG_ON_Y);
    // }
    // }
    // }
    // S21_NA#1634 end

    /**
     * Control Pre Payment Field
     * @param scrnMsg NWAL1500BMsg
     */
    public static void controlPrePmtField(NWAL1500BMsg scrnMsg) {

        final String hdrStsNm = scrnMsg.ordHdrStsDescTxt.getValue();

        if (HEADER_STS_NM_BOOKED.equals(hdrStsNm) || HEADER_STS_NM_CLOSED.equals(hdrStsNm) || HEADER_STS_NM_CANCELLED.equals(hdrStsNm)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.prePmtTpCd)) {
            scrnMsg.prePmtChkNum.setInputProtected(false);
            scrnMsg.prePmtAmt.setInputProtected(false);
        } else {
            scrnMsg.prePmtChkNum.setInputProtected(true);
            scrnMsg.prePmtChkNum.clear();
            scrnMsg.prePmtAmt.setInputProtected(true);
            scrnMsg.prePmtAmt.clear();
        }
    }

    /**
     * Get Param NWAL1760
     * @param scrnMsg NWAL1500BMsg
     * @param prcCtxTp Price Ctx Type
     * @return Param NWAL1760
     */
    public static Object[] getParamNWAL1760(NWAL1500BMsg scrnMsg, String prcCtxTp) {
        return getParamNWAL1760(scrnMsg, prcCtxTp, false);
    }

    /**
     * Get Param NWAL1760
     * @param scrnMsg NWAL1500BMsg
     * @param prcCtxTp Price Ctx Type
     * @param onBlurFlg True: Onblur Mode False: Popup
     * @return Param NWAL1760
     */
    public static Object[] getParamNWAL1760(NWAL1500BMsg scrnMsg, String prcCtxTp, Boolean onBlurFlg) {

        clearPopUpParam(scrnMsg);

        int selectIdx = scrnMsg.xxCellIdx.getValueInt();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, prcCtxTp);
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();

        if (NO_SLCT_DTL == selectIdx) {
            // QC#9437 2016/06/21 Mod Start
            // if (ZYPCommonFunc.hasValue(scrnMsg.ordDt)) {
            //     paramList.add(scrnMsg.ordDt);
            // } else {
            //     paramList.add(scrnMsg.slsDt);
            // }
            paramList.add(scrnMsg.slsDt);
            // QC#9437 2016/06/21 Mod End
            paramList.add(scrnMsg.xxPopPrm_P1);
            paramList.add(scrnMsg.dsOrdCatgCd);
            paramList.add(scrnMsg.dsOrdTpCd);
            paramList.add(scrnMsg.sellToCustCd);
            paramList.add(scrnMsg.csmpContrNum);
            paramList.add(scrnMsg.dlrRefNum);
            paramList.add(scrnMsg.prcContrNum);
            paramList.add(scrnMsg.coaBrCd);
        } else {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                // QC#9437 2016/06/21 Mod Start
                // if (ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIdx).prcBaseDt_LL)) {
                //    paramList.add(scrnMsg.B.no(selectIdx).prcBaseDt_LL);
                // } else {
                //     paramList.add(scrnMsg.slsDt);
                // }
                paramList.add(scrnMsg.slsDt);
                // QC#9437 2016/06/21 Mod End
                paramList.add(scrnMsg.xxPopPrm_P1);
                paramList.add(scrnMsg.dsOrdCatgCd);
                paramList.add(scrnMsg.dsOrdTpCd);
                paramList.add(scrnMsg.sellToCustCd);
                paramList.add(scrnMsg.B.no(selectIdx).csmpContrNum_LL);
                paramList.add(scrnMsg.B.no(selectIdx).dlrRefNum_LL);
                paramList.add(scrnMsg.prcContrNum);
                paramList.add(scrnMsg.coaBrCd);
            } else {
                // QC#9437 2016/06/21 Mod Start
                //if (ZYPCommonFunc.hasValue(scrnMsg.D.no(selectIdx).prcBaseDt_RL)) {
                //    paramList.add(scrnMsg.D.no(selectIdx).prcBaseDt_RL);
                //} else {
                //    paramList.add(scrnMsg.slsDt);
                //}
                paramList.add(scrnMsg.slsDt);
                // QC#9437 2016/06/21 Mod End
                paramList.add(scrnMsg.xxPopPrm_P1);
                paramList.add(scrnMsg.dsOrdCatgCd);
                paramList.add(scrnMsg.dsOrdTpCd);
                paramList.add(scrnMsg.sellToCustCd);
                paramList.add(scrnMsg.D.no(selectIdx).csmpContrNum_RL);
                paramList.add(scrnMsg.D.no(selectIdx).dlrRefNum_RL);
                paramList.add(scrnMsg.prcContrNum);
                paramList.add(scrnMsg.coaBrCd);
            }
        }
        paramList.add(scrnMsg.xxPopPrm_P2);
        paramList.add(scrnMsg.xxPopPrm_P3);
        paramList.add(scrnMsg.xxPopPrm_P4);
        paramList.add(scrnMsg.xxPopPrm_P5);

        // 2016/03/07 Add Start
        if (onBlurFlg) {
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                paramList.add(scrnMsg.B.no(selectIdx).prcCatgNm_LL);
            } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
                paramList.add(scrnMsg.D.no(selectIdx).prcCatgNm_RL);
            } else {
                paramList.add(scrnMsg.prcCatgNm);
            }
        }
        // 2016/03/07 Add End

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Parameter NWAL1620 for Header
     * @param scrnMsg NWAL1500BMsg
     * @return Parameter NWAL1620 for Header
     */
    public static Object[] getParamNWAL1620ForHeader(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1620Constant.COPY_MODE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1620Constant.HEADER_MODE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.cpoOrdNum);
        paramList.add(scrnMsg.xxPopPrm_P0);
        paramList.add(scrnMsg.xxPopPrm_P1);
        paramList.add(scrnMsg.glblCmpyCd);
        paramList.add(scrnMsg.xxPopPrm_P2);
        paramList.add(scrnMsg.xxPopPrm_P3); // OutParam
        paramList.add(scrnMsg.xxPopPrm_P4); // no use
        paramList.add(scrnMsg.dsOrdPosnNum_P1); // no use
        paramList.add(scrnMsg.dsCpoLineNum_P1); // no use
        paramList.add(scrnMsg.xxQty10Num_AW); // OutParam
        paramList.add(scrnMsg.xxPopPrm_P5); // OutParam // Add 2017/09/21 S21_NA#18859

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Parameter NWAL1620 for Detail
     * @param scrnMsg NWAL1500BMsg
     * @return Parameter NWAL1620 for Detail
     */
    public static Object[] getParamNWAL1620ForDetail(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        int slctConfLine = scrnMsg.xxCellIdx.getValueInt();
        int slctLine = scrnMsg.xxCellIdx_BB.getValueInt();

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            if (ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y).size() > 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1620Constant.COPY_MODE);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1620Constant.CONFIG_MODE);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, CONFIG_CATG.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_P1, scrnMsg.A.no(slctConfLine).dsOrdPosnNum_LC.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1620Constant.COPY_MODE);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1620Constant.LINE_MODE);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, CONFIG_CATG.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_P1, scrnMsg.B.no(slctLine).dsOrdPosnNum_LL.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum_P1, scrnMsg.B.no(slctLine).dsCpoLineNum_LL.getValue());
            }
        } else {
            if (ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y).size() > 0) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1620Constant.COPY_MODE);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1620Constant.CONFIG_MODE);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, CONFIG_CATG.INBOUND);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_P1, scrnMsg.C.no(slctConfLine).dsOrdPosnNum_RC.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1620Constant.COPY_MODE);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1620Constant.LINE_MODE);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.cpoOrdNum);
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, CONFIG_CATG.INBOUND);
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum_P1, scrnMsg.D.no(slctLine).dsOrdPosnNum_RL.getValue());
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum_P1, scrnMsg.D.no(slctLine).dsCpoLineNum_RL.getValue());
            }
        }

        paramList.add(scrnMsg.xxPopPrm_P0);
        paramList.add(scrnMsg.xxPopPrm_P1);
        paramList.add(scrnMsg.glblCmpyCd);
        paramList.add(scrnMsg.xxPopPrm_P2);
        paramList.add(scrnMsg.xxPopPrm_P3); // OutParam
        paramList.add(scrnMsg.xxPopPrm_P4); // CONFIG_CATG
        paramList.add(scrnMsg.dsOrdPosnNum_P1);
        paramList.add(scrnMsg.dsCpoLineNum_P1);
        paramList.add(scrnMsg.xxQty10Num_AW); // OutParam

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Parameter NWAL1620 for copy from
     * @param scrnMsg NWAL1500BMsg
     * @return Parameter NWAL1620 for copy from
     */
    public static Object[] getParamNWAL1620ForCopyFrom(NWAL1500BMsg scrnMsg) {
        clearPopUpParam(scrnMsg);
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1620Constant.COPY_FROM_MODE);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, scrnMsg.cpoOrdNum);

        if (ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y).size() > 0 && TAB_LINE_CONFIG.equals(dplyTab)) { // S21_NA#12684 Mod
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1620Constant.LINE_MODE);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, CONFIG_CATG.OUTBOUND);
        } else if (ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y).size() > 0 && TAB_RMA.equals(dplyTab)) { // S21_NA#12684 Mod
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1620Constant.LINE_MODE);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, CONFIG_CATG.INBOUND);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1620Constant.CONFIG_MODE);
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, CONFIG_CATG.OUTBOUND);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, CONFIG_CATG.INBOUND);
            }
        }
        paramList.add(scrnMsg.xxPopPrm_P0);
        paramList.add(scrnMsg.xxPopPrm_P1);
        paramList.add(scrnMsg.glblCmpyCd);
        paramList.add(scrnMsg.xxPopPrm_P2);
        paramList.add(scrnMsg.xxPopPrm_P3); // OutParam
        paramList.add(scrnMsg.xxPopPrm_P4); // CONFIG_CATG
        paramList.add(scrnMsg.dsOrdPosnNum_P1);
        paramList.add(scrnMsg.dsCpoLineNum_P1);
        paramList.add(scrnMsg.xxQty10Num_AW); // OutParam

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Parameter NWAL2090
     * @param scrnMsg NWAL1500BMsg
     * @return Parameter NWAL2090
     */
    public static Object[] getParamNWAL2090(NWAL1500BMsg scrnMsg) {
        clearPopUpParam(scrnMsg);
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.cpoOrdNum);
        paramList.add(scrnMsg.glblCmpyCd);
        paramList.add(scrnMsg.xxPopPrm_P0);
        paramList.add(scrnMsg.xxPopPrm_P1);
        paramList.add(scrnMsg.xxPopPrm_P2);

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Parameter NWAL1610
     * @param scrnMsg NWAL1500BMsg
     * @return Parameter NWAL1610
     */
    public static Object[] getParamNWAL1610(NWAL1500BMsg scrnMsg) {

        // 2019/07/11 S21_NA#51287 Add Start
        String xxModeCd = scrnMsg.xxPopPrm_P0.getValue();
        // 2019/07/11 S21_NA#51287 Add End

        clearPopUpParam(scrnMsg);
        scrnMsg.ordQty_AW.clear();
        scrnMsg.rddDt_AW.clear();
        scrnMsg.rqstPickUpDt_AW.clear(); // S21_NA#9189 Add 

        List<Integer> checkListItemConfig = new ArrayList<Integer>();
        List<Integer> checkListItemLine = new ArrayList<Integer>();
        List<Integer> checkListRMAConfig = new ArrayList<Integer>();
        List<Integer> checkListRMALine = new ArrayList<Integer>();

        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            checkListItemConfig = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            checkListItemLine = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
        } else {
            checkListRMAConfig = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            checkListRMALine = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
        }

        // 2019/07/11 S21_NA#51287 Mod Start
        //if (checkListItemConfig.size() > 0) {
        if (NWAL1610Constant.CONFIG_MODE.equals(xxModeCd)) {
        // 2019/07/11 S21_NA#51287 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1610Constant.CONFIG_MODE);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PE, scrnMsg.sellToCustCd); // 2016/08/26 S21_NA#9806 Add
        }
        // 2019/07/11 S21_NA#51287 Mod Start
        //if (checkListItemLine.size() > 0) {
        if (NWAL1610Constant.LINE_MODE.equals(xxModeCd)) {
        // 2019/07/11 S21_NA#51287 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1610Constant.LINE_MODE);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PE, scrnMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PF, scrnMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PG, scrnMsg.dsOrdCatgCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PH, scrnMsg.dsOrdTpCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PI, scrnMsg.dsOrdRsnCd);
            if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdTs)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdTs_AW, scrnMsg.cpoOrdTs);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdTs_AW, scrnMsg.slsDt.getValue());
            }
        }
        // 2019/07/11 S21_NA#51287 Mod Start
        //if (checkListRMAConfig.size() > 0) {
        //    ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1610Constant.CONFIG_MODE);
        //    ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PE, scrnMsg.sellToCustCd); // 2016/08/26 S21_NA#9806 Add
        //}
        //if (checkListRMALine.size() > 0) {
        if (NWAL1610Constant.RMA_MODE.equals(xxModeCd)) {
        // 2019/07/11 S21_NA#51287 Mod End
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1610Constant.RMA_MODE);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PE, scrnMsg.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PF, scrnMsg.prcContrNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PG, scrnMsg.dsOrdCatgCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PH, scrnMsg.dsOrdTpCd);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PI, scrnMsg.dsOrdRsnCd);
            if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdTs)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdTs_AW, scrnMsg.cpoOrdTs);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdTs_AW, scrnMsg.slsDt.getValue());
            }
        }

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.xxPopPrm_P0); // xxModeCd
        paramList.add(scrnMsg.glblCmpyCd); // Global Company Code
        paramList.add(scrnMsg.xxPopPrm_P1); // Ship To Customer Location Code
        paramList.add(scrnMsg.xxPopPrm_P2); // Bill To Customer Location Code
        paramList.add(scrnMsg.ordQty_AW); // Order Quantity
        paramList.add(scrnMsg.xxPopPrm_P4); // DS Order Line Category Code
        paramList.add(scrnMsg.xxPopPrm_P5); // Order Line Source Code
        paramList.add(scrnMsg.xxPopPrm_P6); // Retail Warehouse Code
        paramList.add(scrnMsg.xxPopPrm_P7); // Retail Sub Warehouse Code
        paramList.add(scrnMsg.xxPopPrm_P8); // Return Reason Code
        paramList.add(scrnMsg.xxPopPrm_P9); // Substitute Item Code
        paramList.add(scrnMsg.xxPopPrm_PA); // Price Category Code
        paramList.add(scrnMsg.xxPopPrm_PB); // Floor Price List Code
        paramList.add(scrnMsg.prcBaseDt_AW); // Price Base Date
        paramList.add(scrnMsg.xxPopPrm_PD); // Hard Disc Drive Removal Code
        paramList.add(scrnMsg.xxPopPrm_PE); // Sell To Customer Code
        paramList.add(scrnMsg.xxPopPrm_PF); // Price Contract Number
        paramList.add(scrnMsg.xxPopPrm_PG); // DS Order Category Code
        paramList.add(scrnMsg.xxPopPrm_PH); // DS Order Type Code
        paramList.add(scrnMsg.xxPopPrm_PI); // DS Order Reason Code
        paramList.add(scrnMsg.cpoOrdTs_AW); // CPO Order Timestamp
        paramList.add(scrnMsg.rddDt_AW); // RDD
        paramList.add(scrnMsg.rqstPickUpDt_AW); // Request Pick Up Date // S21_NA#9189 Add
        paramList.add(scrnMsg.shipToCustAcctCd); // [23] Bill To Account QC#6148 Add
        paramList.add(scrnMsg.billToCustAcctCd); // [24] Ship To Account QC#6148 Add
        paramList.add(scrnMsg.xxPopPrm_PC); // [25] CSMP Contoract # 2016/08/26 S21_NA#9806 Add
        paramList.add(scrnMsg.xxPopPrm_PJ); // [26] CSA Dealer Reference # 2016/08/26 S21_NA#9806 Add

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * Get Param NWAL1810
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1810
     */
    public static Object[] getParamNWAL1810(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        String lvlCd = getLvlCd(scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, scrnMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, NWAL1810Constant.SOURCE_ID_ORDER);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, lvlCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, NWAL1810Constant.DEF_CD_SUMMARY);

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(scrnMsg.xxPopPrm_P0);
        paramList.add(scrnMsg.xxPopPrm_P1);
        paramList.add(scrnMsg.xxPopPrm_P2);
        paramList.add(scrnMsg.xxPopPrm_P3);

        if (!NWAL1810Constant.LVL_CD_ALL.equals(lvlCd)) {
            paramList.add(getCheckNumList(scrnMsg));
        } else {
            paramList.add(scrnMsg.xxPopPrm_P4); // no use
        }

        return paramList.toArray(new Object[0]);
    }

    /**
     * Get Param NWAL1870
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1870
     */
    public static Object[] getParamNWAL1870(NWAL1500BMsg scrnMsg) {

        NWAL1500_BBMsg lineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());
        scrnMsg.xxPopPrm_P0.clear();

        List<Object> paramList = new ArrayList<Object>();
        paramList.add(lineMsg.mdseCd_LL);
        paramList.add(lineMsg.mdseNm_LL);
        paramList.add(scrnMsg.mdseCd_SS);
        paramList.add(scrnMsg.mdseNm_SS);
        paramList.add(scrnMsg.xxPopPrm_P0);

        return paramList.toArray(new Object[0]);
    }

    // 2017/08/07 Sol#249 Add Start
    /**
     * Get Parameter NWAL1890
     * @param scrnMsg NWAL1500BMsg
     * @return Parameter NWAL1890
     */
    public static Object[] getParamNWAL1890(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);

        // InParameter
        String dplyTab = scrnMsg.xxDplyTab.getValue();
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1890Constant.LINE_CONFIG_MODE);
        }
        if (TAB_RMA.equals(dplyTab)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1890Constant.RMA_MODE);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, scrnMsg.cpoOrdNum);

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.xxPopPrm_P0); // xxModeCd
        paramList.add(scrnMsg.xxPopPrm_P1); // CPO Order Number

        // OutParameter
        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            // Config Level Information(Line Config TAB)
            paramList.add(scrnMsg.dsOrdPosnNum_FL);
            paramList.add(scrnMsg.xxConfigIdSrchTxt_FL);
            paramList.add(scrnMsg.xxMdlSrchTxt_FL);
            paramList.add(scrnMsg.xxBillToAcctNmSrchTxt_FL);
            paramList.add(scrnMsg.xxBillToAcctCdSrchTxt_FL);
            paramList.add(scrnMsg.xxBillToLocCdSrchTxt_FL);
            paramList.add(scrnMsg.xxShipToAcctNmSrchTxt_FL);
            paramList.add(scrnMsg.xxShipToAcctCdSrchTxt_FL);
            paramList.add(scrnMsg.xxShipToLocCdSrchTxt_FL);
            paramList.add(scrnMsg.xxSoldToAcctNmSrchTxt_FL);
            paramList.add(scrnMsg.xxSoldToAcctCdSrchTxt_FL);
            paramList.add(scrnMsg.xxSoldToLocCdSrchTxt_FL);
            paramList.add(scrnMsg.xxShowInclLineFlg_FL);

        } else if (TAB_RMA.equals(dplyTab)) {
            // Config Level Information(RMA TAB)
            paramList.add(scrnMsg.dsOrdPosnNum_FR);
            paramList.add(scrnMsg.xxConfigIdSrchTxt_FR);
            paramList.add(scrnMsg.xxMdlSrchTxt_FR);
            paramList.add(scrnMsg.xxBillToAcctNmSrchTxt_FR);
            paramList.add(scrnMsg.xxBillToAcctCdSrchTxt_FR);
            paramList.add(scrnMsg.xxBillToLocCdSrchTxt_FR);
            paramList.add(scrnMsg.xxShipToAcctNmSrchTxt_FR);
            paramList.add(scrnMsg.xxShipToAcctCdSrchTxt_FR);
            paramList.add(scrnMsg.xxShipToLocCdSrchTxt_FR);
            paramList.add(scrnMsg.xxSoldToAcctNmSrchTxt_FR);
            paramList.add(scrnMsg.xxSoldToAcctCdSrchTxt_FR);
            paramList.add(scrnMsg.xxSoldToLocCdSrchTxt_FR);
            paramList.add(scrnMsg.xxShowInclLineFlg_FR);
        }

        // Line Level Information(Line Config TAB)
        paramList.add(scrnMsg.xxLineNum_FL);
        paramList.add(scrnMsg.xxLineStsSrchTxt_FL);
        paramList.add(scrnMsg.xxOrdItemSrchTxt_FL);
        paramList.add(scrnMsg.xxRtlWhSrchTxt_FL);
        paramList.add(scrnMsg.xxRtlSwhSrchTxt_FL);
        paramList.add(scrnMsg.xxCpoSrcTpSrchTxt_FL);
        paramList.add(scrnMsg.xxOrdSrcRefNumSrchTxt_FL);
        paramList.add(scrnMsg.xxSbstItemSrchTxt_FL);
        paramList.add(scrnMsg.xxSerNumSrchTxt_FL);
        paramList.add(scrnMsg.xxShowInclCloLineFlg_FL);
        paramList.add(scrnMsg.xxShowInclCancLineFlg_FL);

        // Line Level Information(RMA TAB)
        paramList.add(scrnMsg.xxLineNum_FR);
        paramList.add(scrnMsg.xxLineStsSrchTxt_FR);
        paramList.add(scrnMsg.xxOrdItemSrchTxt_FR);
        paramList.add(scrnMsg.xxRtrnRsnSrchTxt_FR);
        paramList.add(scrnMsg.xxRtlWhSrchTxt_FR);
        paramList.add(scrnMsg.xxRtlSwhSrchTxt_FR);
        paramList.add(scrnMsg.xxOrdSrcRefNumSrchTxt_FR);
        paramList.add(scrnMsg.xxSerNumSrchTxt_FR);
        paramList.add(scrnMsg.xxShowInclCloLineFlg_FR);
        paramList.add(scrnMsg.xxShowInclCancLineFlg_FR);

        return paramList.toArray(new EZDBItem[0]);
    }
    // 2017/08/07 Sol#249 Add End

    /**
     * Get Level Code
     * @param scrnMsg NWAL1500BMsg
     * @return Level Code
     */
    private static String getLvlCd(NWAL1500BMsg scrnMsg) {

        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            List<Integer> checkListItemConfig = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            if (checkListItemConfig.size() > 0) {
                return NWAL1810Constant.LVL_CD_CONF;
            } else if (checkListItemLine.size() > 0) {
                return NWAL1810Constant.LVL_CD_LINE;
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            List<Integer> checkListRMAConfig = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

            if (checkListRMAConfig.size() > 0) {
                return NWAL1810Constant.LVL_CD_CONF;
            } else if (checkListRMALine.size() > 0) {
                return NWAL1810Constant.LVL_CD_LINE;
            }
        }

        return NWAL1810Constant.LVL_CD_ALL;
    }

    /**
     * Get Check Number List
     * @param scrnMsg NWAL1500BMsg
     * @return Check Number List
     */
    private static List<String[]> getCheckNumList(NWAL1500BMsg scrnMsg) {

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        List<String[]> checkNumList = new ArrayList<String[]>();
        String[] numArray = null;

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            List<Integer> checkListItemConfig = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListItemLine = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

            if (checkListItemConfig.size() > 0) {
                for (int confIndex : checkListItemConfig) {
                    numArray = new String[4];
                    numArray[0] = scrnMsg.A.no(confIndex).dsOrdPosnNum_LC.getValue();
                    numArray[1] = ""; // S21_NA#5283
                    numArray[2] = ""; // S21_NA#5283
                    numArray[3] = CONFIG_CATG.OUTBOUND;
                    checkNumList.add(numArray);
                }

            } else if (checkListItemLine.size() > 0) {
                for (int lineIndex : checkListItemLine) {
                    numArray = new String[4];
                    numArray[0] = scrnMsg.B.no(lineIndex).dsOrdPosnNum_LL.getValue(); // S21_NA#5283
                    numArray[1] = scrnMsg.B.no(lineIndex).cpoDtlLineNum_LL.getValue(); // S21_NA#5283
                    numArray[2] = scrnMsg.B.no(lineIndex).cpoDtlLineSubNum_LL.getValue(); // S21_NA#5283
                    numArray[3] = CONFIG_CATG.OUTBOUND;
                    checkNumList.add(numArray);
                }
            }

        } else if (TAB_RMA.equals(dplyTab)) {
            List<Integer> checkListRMAConfig = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
            List<Integer> checkListRMALine = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);

            if (checkListRMAConfig.size() > 0) {
                for (int confIndex : checkListRMAConfig) {
                    numArray = new String[4];
                    numArray[0] = scrnMsg.C.no(confIndex).dsOrdPosnNum_RC.getValue();
                    numArray[1] = ""; // S21_NA#5283
                    numArray[2] = ""; // S21_NA#5283
                    numArray[3] = CONFIG_CATG.INBOUND;
                    checkNumList.add(numArray);
                }

            } else if (checkListRMALine.size() > 0) {
                for (int lineIndex : checkListRMALine) {
                    numArray = new String[4];
                    numArray[0] = scrnMsg.D.no(lineIndex).dsOrdPosnNum_RL.getValue(); // S21_NA#5283
                    numArray[1] = scrnMsg.D.no(lineIndex).cpoDtlLineNum_RL.getValue(); // S21_NA#5283
                    numArray[2] = scrnMsg.D.no(lineIndex).cpoDtlLineSubNum_RL.getValue(); // S21_NA#5283
                    numArray[3] = CONFIG_CATG.INBOUND;
                    checkNumList.add(numArray);
                }
            }
        }

        return checkNumList;
    }

    /**
     * Set RMA Qty As Negative
     * @param rmaLine NWAL1500_DBMsg
     */
    public static void setRmaQtyAsNegative(NWAL1500_DBMsg rmaLine) {

        if (ZYPCommonFunc.hasValue(rmaLine.ordCustUomQty_RL) && rmaLine.ordCustUomQty_RL.getValue().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal ordCustUomQty = rmaLine.ordCustUomQty_RL.getValue();
            ordCustUomQty = ordCustUomQty.multiply(BigDecimal.valueOf(-1));
            rmaLine.ordCustUomQty_RL.setValue(ordCustUomQty);
        }
    }

    /**
     * compare to bigdecimal
     * @param source source value
     * @param target target value
     * @return result (0, > 0 , < 0)
     */
    public static int compareBigDecimal(BigDecimal source, BigDecimal target) {

        if (source == null) {
            if (target == null) {
                return 0;
            } else {
                return -1;
            }
        } else {
            if (target == null) {
                return 1;
            } else {
                return source.compareTo(target);
            }
        }
    }

    // 2016/03/02 S21_NA#4377 Add Start
    public static boolean checkReadOnlyByStatus(NWAL1500BMsg scrnMsg) {
        if (HEADER_STS_NM_CLOSED.equals(scrnMsg.ordHdrStsDescTxt.getValue())
                || HEADER_STS_NM_CANCELLED.equals(scrnMsg.ordHdrStsDescTxt.getValue())) {
            return true;
        }
        // 2016/10/13 S21_NA#7700 Add Start
        if (!NWAL1500CommonLogic.chkOrdEntryScrEdtblFlg(scrnMsg)) {
            return true;
        }
        // 2016/10/13 S21_NA#7700 Add End

        return false;
    }
    // 2016/03/02 S21_NA#4377 Add End

    // 2016/03/03 S21_NA#2176 Add Start
    public static boolean checkReadOnlyByLineStatus(NWAL1500_BBMsg lineMsg) {
        String lineStsNm = lineMsg.ordLineStsDescTxt_LL.getValue();
        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)) {
            return true;
        }
        return false;
    }

    public static boolean checkReadOnlyByRmaLineStatus(NWAL1500_DBMsg rmaLineMsg) {
        String lineStsNm = rmaLineMsg.rtrnLineStsDescTxt_RL.getValue();
        if (LINE_STS_NM_CANCELLED.equals(lineStsNm)) {
            return true;
        }
        return false;
    }
    // 2016/03/02 S21_NA#2176 Add End

    // 2016/03/04 S21_NA#1729 Add Start
    private static boolean chkWarningForHeaderTab(NWAL1500BMsg scrnMsg) {
        if (scrnMsg.getMessageInfo().getCode().endsWith("W")) {
            if (NWAM0368W.equals(scrnMsg.getMessageInfo().getCode())
                    || NWAM0779W.equals(scrnMsg.getMessageInfo().getCode())) {
                return true;
            }
        }
        return false;
    }
    // 2016/03/04 S21_NA#1729 Add End

    /**
     * Get Bill To Account Number For Popup Parameter
     * @param scrnMsg NWAL1500BMsg
     * @return Bill To Account Number For Popup Parameter
     */
    private static String getBillToAcctNum(NWAL1500BMsg scrnMsg) {

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIdx = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500_ABMsg confMsg = scrnMsg.A.no(selectIdx);
            if (ZYPCommonFunc.hasValue(confMsg.billToCustAcctCd_LC)) {
                return confMsg.billToCustAcctCd_LC.getValue();
            } else if (ZYPCommonFunc.hasValue(confMsg.shipToCustAcctCd_LC)) {
                return confMsg.shipToCustAcctCd_LC.getValue();
            } else if (ZYPCommonFunc.hasValue(confMsg.sellToCustCd_LC)) {
                return confMsg.sellToCustCd_LC.getValue();
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500_CBMsg rmaConfMsg = scrnMsg.C.no(selectIdx);
            if (ZYPCommonFunc.hasValue(rmaConfMsg.billToCustAcctCd_RC)) {
                return rmaConfMsg.billToCustAcctCd_RC.getValue();
            } else if (ZYPCommonFunc.hasValue(rmaConfMsg.shipToCustAcctCd_RC)) {
                return rmaConfMsg.shipToCustAcctCd_RC.getValue();
            } else if (ZYPCommonFunc.hasValue(rmaConfMsg.sellToCustCd_RC)) {
                return rmaConfMsg.sellToCustCd_RC.getValue();
            }
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctCd)) {
                return scrnMsg.billToCustAcctCd.getValue();
            } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
                return scrnMsg.shipToCustAcctCd.getValue();
            } else if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
                return scrnMsg.sellToCustCd.getValue();
            }
        }

        return null;
    }

    /**
     * Get Ship To Account Number For Popup Parameter
     * @param scrnMsg NWAL1500BMsg
     * @return Ship To Account Number For Popup Parameter
     */
    private static String getShipToAcctNum(NWAL1500BMsg scrnMsg) {

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIdx = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500_ABMsg confMsg = scrnMsg.A.no(selectIdx);
            if (ZYPCommonFunc.hasValue(confMsg.shipToCustAcctCd_LC)) {
                return confMsg.shipToCustAcctCd_LC.getValue();
            } else if (ZYPCommonFunc.hasValue(confMsg.sellToCustCd_LC)) {
                return confMsg.sellToCustCd_LC.getValue();
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500_CBMsg rmaConfMsg = scrnMsg.C.no(selectIdx);
            if (ZYPCommonFunc.hasValue(rmaConfMsg.shipToCustAcctCd_RC)) {
                return rmaConfMsg.shipToCustAcctCd_RC.getValue();
            } else if (ZYPCommonFunc.hasValue(rmaConfMsg.sellToCustCd_RC)) {
                return rmaConfMsg.sellToCustCd_RC.getValue();
            }
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
                return scrnMsg.shipToCustAcctCd.getValue();
            } else if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
                return scrnMsg.sellToCustCd.getValue();
            }
        }

        return null;
    }

    /**
     * Get Sold To Account Number For Popup Parameter
     * @param scrnMsg NWAL1500BMsg
     * @return Sold To Account Number For Popup Parameter
     */
    private static String getSoldToAcctNum(NWAL1500BMsg scrnMsg) {

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIdx = scrnMsg.xxCellIdx.getValueInt();

        if (TAB_LINE_CONFIG.equals(dplyTab)) {
            NWAL1500_ABMsg confMsg = scrnMsg.A.no(selectIdx);
            if (ZYPCommonFunc.hasValue(confMsg.sellToCustCd_LC)) {
                return confMsg.sellToCustCd_LC.getValue();
            } else if (ZYPCommonFunc.hasValue(confMsg.shipToCustAcctCd_LC)) {
                return confMsg.shipToCustAcctCd_LC.getValue();
            }
        } else if (TAB_RMA.equals(dplyTab)) {
            NWAL1500_CBMsg rmaConfMsg = scrnMsg.C.no(selectIdx);
            if (ZYPCommonFunc.hasValue(rmaConfMsg.sellToCustCd_RC)) {
                return rmaConfMsg.sellToCustCd_RC.getValue();
            } else if (ZYPCommonFunc.hasValue(rmaConfMsg.shipToCustAcctCd_RC)) {
                return rmaConfMsg.shipToCustAcctCd_RC.getValue();
            }
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd)) {
                return scrnMsg.sellToCustCd.getValue();
            } else if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
                return scrnMsg.shipToCustAcctCd.getValue();
            }
        }

        return null;
    }

    /**
     * <pre>
     * Set Drop Ship Flag Link property (related to CPO_V.DROP_SHIP_AVAL_FLG)
     * (This method add for 2016/03/25 S21_NA#4693
     * @param scrnMsg Screen Message
     * </pre>
     */
    public static void setDropShipFlgProtectProperty(NWAL1500BMsg scrnMsg) {
        boolean isDsLinkEnable = ZYPConstant.FLG_ON_Y.equals(scrnMsg.dropShipAvalFlg.getValue());

        if (isDsLinkEnable) {
            scrnMsg.dropShipFlg_LK.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            scrnMsg.dropShipFlg_LK.clear();
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (isDsLinkEnable) {
                scrnMsg.A.no(i).xxImageTxt_AD.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.A.no(i).xxImageTxt_AD.clear();
            }
        }

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            if (isDsLinkEnable) {
                scrnMsg.C.no(i).xxImageTxt_CD.setValue(ZYPConstant.FLG_ON_Y);
            } else {
                scrnMsg.C.no(i).xxImageTxt_CD.clear();
            }
        }
    }
    // 2016/10/13 S21_NA#7700 Add Start
    /**
     * <pre>
     * Check Order Entry Screen Editable Flag (related to CPO_SRC_TP.ORD_ENTRY_SCR_EDTBL_FLG)
     * @param scrnMsg Screen Message
     * @return boolean true: editable false: not editable
     * </pre>
     */
    public static boolean chkOrdEntryScrEdtblFlg(NWAL1500BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.ordEntryScrEdtblFlg)
                && ZYPConstant.FLG_OFF_N.equals(scrnMsg.ordEntryScrEdtblFlg.getValue())) {

            return false;

        }
        return true;
    }
    // 2016/10/13 S21_NA#7700 Add End
    // 2016/10/19 S21_NA#14728 Add Start
    /**
     * <pre>
     * Check Only Reference Authority 
     * @param scrnMsg Screen Message
     * @return boolean : true: reference authority false: has other autohrity
     * </pre>
     */
    public static boolean isOnlyReferenceAuthority(NWAL1500BMsg scrnMsg) {
        if (scrnMsg.L.getValidCount() == 1) {
            String funcId = scrnMsg.L.no(0).xxFuncId.getValue();
            if (REF_AUTH.equals(funcId)) {
                return true;
            }
        }
        return false;
    }
    // 2016/10/19 S21_NA#14728 Add End

    // 2017/10/13 S21_NA#21267 Add Start
    /**
     * <pre>
     * Check the order Credit or not
     * @param scrnMsg Screen Message
     * @return true: Credit Order false: other
     */
    public static boolean isOrderCredit(NWAL1500BMsg scrnMsg) {

        boolean hasCredit = false;
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            NWAL1500_BBMsg lineMsg = scrnMsg.B.no(i);
            if (CR_REBIL.CREDIT.equals(lineMsg.crRebilCd_LL.getValue())) {
                hasCredit = true;
                break;
            }
        }
        return hasCredit;
    }
    // 2017/10/13 S21_NA#21267 Add End

    // 2018/04/04 S21_NA#25152 Add Start
    /**
     * <pre>
     * Check the search key is suitable or not.
     * @param strItem String item
     * @return false: error, true: normal.
     * </pre>
     */
    public static boolean checkSerchKey(EZDBStringItem strItem) {

        if (!ZYPCommonFunc.hasValue(strItem)) {
            return true;
        }
        String searchKey = strItem.getValue();
        boolean isKeyPercent = S21StringUtil.isEquals(PERCENT, searchKey);
        boolean isKeyPercentAndOneChar = searchKey.startsWith(PERCENT) && searchKey.endsWith(PERCENT) && searchKey.length() == 3;
        if (isKeyPercent || isKeyPercentAndOneChar) {
            strItem.setErrorInfo(1, NWAM0953E);
            return false;
        }
        return true;
    }
    // 2018/04/04 S21_NA#25152 Add End

    // 2018/05/11 QC#22139 Add Start
    /**
     * Get Parameter NWAL1790
     * @param scrnMsg NWAL1500BMsg
     * @return params NWAL1790
     */
    public static Object[] getParamNWAL1790(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        ZYPTableUtil.clear(scrnMsg.Q);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P0, NWAL1790Constant.CONFIRMATION_FLAG);

        scrnMsg.Q.setValidCount(IDX_20);

        Object[] params = new Object[IDX_5];
        params[IDX_0] = scrnMsg.xxPopPrm_P0; // Process Mode
        params[IDX_1] = scrnMsg.Q;
        params[IDX_2] = "QL"; // Suffix
        params[IDX_3] = scrnMsg.xxRqstFlg_ML; // Mail Send Flag
        params[IDX_4] = scrnMsg.xxRqstFlg_PR; // Print Flag

        return params;
    }

    /**
     * Get Parameter NWAL2420
     * @param scrnMsg NWAL1500BMsg
     * @return params NWAL2420
     */
    public static Object[] getParamNWAL2420(NWAL1500BMsg scrnMsg) {

        clearPopUpParam(scrnMsg);
        // 2018/07/03 QC#26932 Start
//        ZYPTableUtil.clear(scrnMsg.Q);
        // 2018/07/03 QC#26932 End

        scrnMsg.Q.setValidCount(IDX_20);

        Object[] params = new Object[IDX_6];
        params[IDX_0] = scrnMsg.Q;//ctacPsnEmlAddr_QL
        params[IDX_1] = "QL"; // Suffix
        params[IDX_2] = scrnMsg.xxRqstFlg_ML; // Mail Send Flag
        params[IDX_3] = scrnMsg.xxRqstFlg_PR; // Print Flag
        params[IDX_4] = scrnMsg.rmaRptTpCd_RA; // RMA Returned by Code
        params[IDX_5] = scrnMsg.rtrnAuthCmntTxt_RA; // Return Comments

        return params;
    }
    // 2018/05/11 QC#22139 Add End
    // 2018/07/03 S21_NA#26908 Add Start
    public static Object[] getParamNWAL2260(NWAL1500BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = scrnMsg.ordSrcRefNum_EA;
        params[IDX_1] = scrnMsg.cpoSrcTpCd_EA;
        params[IDX_2] = scrnMsg.dsImptOrdPk_EA;
        params[IDX_3] = scrnMsg.xxReadOnlyFlg_EA;
        params[IDX_4] = scrnMsg.dsImptOrdDtlPk_EA;
        params[IDX_5] = scrnMsg.ordSrcRefLineNum_EA;
        params[IDX_6] = scrnMsg.ordSrcRefLineSubNum_EA;

        return params;
    }
    // 2018/07/03 S21_NA#26908 Add End
    
     // 2018/07/27 S21_NA#14307 Add Start
    /**
     * Check Special Instruction Data
     * @param scrnMsg NWAL1500BMsg
     * @return boolean
     */
    public static boolean checkSpecialInstrctionData(NWAL1500BMsg scrnMsg) {
        
        if (ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_SP) || 
            ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_SP) || 
            ZYPCommonFunc.hasValue(scrnMsg.shipToCustCd_SP)) {

            return true;
        }
        return false;
    }
    // 2018/07/27 S21_NA#14307 Add End

    // START 2023/06/06 T.Doi [CSA-QC#61465, ADD]
    public static Object[] getParamNWAL1130ForViewAllSalesCredit(NWAL1500BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "View All Sales Credit";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("     X.GLBL_CMPY_CD ");
        sb.append("    ,X.EZCANCELFLAG  ");
        sb.append("    ,X.CPO_ORD_NUM ");
        sb.append("    ,CASE WHEN X.CONFIG_CATG_CD = LAG(X.CONFIG_CATG_CD) ");
        sb.append("                 OVER(PARTITION BY X.CPO_ORD_NUM, X.DS_CPO_CONFIG_PK ");
        sb.append("                          ORDER BY X.RANK) ");
        sb.append("          THEN NULL ");
        sb.append("          ELSE X.CONFIG_CATG_CD ");
        sb.append("     END                            AS CONFIG_CATG_CD ");
        sb.append("    ,CASE WHEN X.DS_ORD_POSN_NUM = LAG(X.DS_ORD_POSN_NUM) ");
        sb.append("                 OVER(PARTITION BY X.CPO_ORD_NUM, X.DS_CPO_CONFIG_PK ");
        sb.append("                          ORDER BY X.RANK) ");
        sb.append("          THEN NULL ");
        sb.append("          ELSE X.DS_ORD_POSN_NUM ");
        sb.append("     END                            AS DS_ORD_POSN_NUM ");
        sb.append("    ,CASE WHEN X.T_MDL_NM = LAG(X.T_MDL_NM) ");
        sb.append("                 OVER(PARTITION BY X.CPO_ORD_NUM, X.DS_CPO_CONFIG_PK ");
        sb.append("                          ORDER BY X.RANK) ");
        sb.append("          THEN NULL ");
        sb.append("          ELSE X.T_MDL_NM ");
        sb.append("     END                            AS T_MDL_NM ");
        sb.append("    ,X.SLS_CR_QUOT_FLG ");
        sb.append("    ,X.LINE_BIZ_ROLE_TP_DESC_TXT ");
        sb.append("    ,X.TOC_NM ");
        sb.append("    ,X.PSN_NUM ");
        sb.append("    ,X.SLS_REP_CR_PCT ");
        sb.append("    ,X.RANK ");
        sb.append("FROM ( ");
        sb.append("      SELECT  ");
        sb.append("           CSC.GLBL_CMPY_CD ");
        sb.append("          ,CSC.EZCANCELFLAG  ");
        sb.append("          ,CSC.CPO_ORD_NUM ");
        sb.append("          ,CSC.DS_CPO_SLS_CR_PK ");
        sb.append("          ,CSC.DS_CPO_CONFIG_PK ");
        sb.append("          ,DCC.CONFIG_CATG_CD ");
        sb.append("          ,CASE WHEN CSC.DS_ORD_POSN_NUM IS NULL THEN 'Header' ");
        sb.append("                ELSE CSC.DS_ORD_POSN_NUM ");
        sb.append("           END AS DS_ORD_POSN_NUM ");
        sb.append("          ,CSC.SLS_CR_QUOT_FLG ");
        sb.append("          ,MN.T_MDL_NM ");
        sb.append("          ,LBRT.LINE_BIZ_ROLE_TP_DESC_TXT ");
        sb.append("          ,T.TOC_NM ");
        sb.append("          ,SP.PSN_NUM ");
        sb.append("          ,CASE WHEN CSC.SLS_CR_QUOT_FLG = 'Y' THEN CSC.SLS_REP_CR_PCT || '%' ");
        sb.append("                ELSE NULL ");
        sb.append("           END AS SLS_REP_CR_PCT ");
        sb.append("          ,ROW_NUMBER() OVER(PARTITION BY CSC.CPO_ORD_NUM ");
        sb.append("                                 ORDER BY DCC.CONFIG_CATG_CD DESC ");
        sb.append("                                         ,TO_NUMBER(DCC.DS_ORD_POSN_NUM) NULLS FIRST ");
        sb.append("                                         ,CSC.SLS_CR_QUOT_FLG DESC ");
        sb.append("                                         ,CSC.DS_CPO_SLS_CR_PK)  AS RANK ");
        sb.append("      FROM ");
        sb.append("           DS_CPO_SLS_CR    CSC ");
        sb.append("          ,DS_CPO_CONFIG    DCC ");
        sb.append("          ,MDL_NM           MN ");
        sb.append("          ,LINE_BIZ_ROLE_TP LBRT ");
        sb.append("          ,TOC              T ");
        sb.append("          ,ORG_FUNC_ASG     OFA ");
        sb.append("          ,S21_PSN          SP ");
        sb.append("      WHERE ");
        sb.append("              CSC.GLBL_CMPY_CD       = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("          AND CSC.CPO_ORD_NUM        = '").append(scrnMsg.cpoOrdNum.getValue()).append("' ");
        sb.append("          AND CSC.GLBL_CMPY_CD       = DCC.GLBL_CMPY_CD(+) ");
        sb.append("          AND CSC.CPO_ORD_NUM        = DCC.CPO_ORD_NUM(+) ");
        sb.append("          AND CSC.DS_CPO_CONFIG_PK   = DCC.DS_CPO_CONFIG_PK(+) ");
        sb.append("          AND DCC.GLBL_CMPY_CD       = MN.T_GLBL_CMPY_CD(+) ");
        sb.append("          AND DCC.MDL_ID             = MN.T_MDL_ID(+) ");
        sb.append("          AND CSC.GLBL_CMPY_CD       = LBRT.GLBL_CMPY_CD ");
        sb.append("          AND CSC.SLS_REP_ROLE_TP_CD = LBRT.LINE_BIZ_ROLE_TP_CD ");
        sb.append("          AND CSC.GLBL_CMPY_CD       = T.GLBL_CMPY_CD ");
        sb.append("          AND CSC.SLS_REP_TOC_CD     = T.TOC_CD ");
        sb.append("          AND T.GLBL_CMPY_CD         = OFA.GLBL_CMPY_CD(+) ");
        sb.append("          AND T.TOC_CD               = OFA.TOC_CD(+) ");
        sb.append("          AND OFA.EFF_FROM_DT(+)     <= '").append(scrnMsg.slsDt.getValue()).append("' ");
        sb.append("          AND (OFA.EFF_THRU_DT(+)    >= '").append(scrnMsg.slsDt.getValue()).append("' OR OFA.EFF_THRU_DT(+) IS NULL) ");
        sb.append("          AND OFA.GLBL_CMPY_CD       = SP.GLBL_CMPY_CD(+) ");
        sb.append("          AND OFA.PSN_CD             = SP.PSN_CD(+) ");
        sb.append("          AND SP.EFF_FROM_DT(+)      <= '").append(scrnMsg.slsDt.getValue()).append("' ");
        sb.append("          AND (SP.EFF_THRU_DT(+)     >= '").append(scrnMsg.slsDt.getValue()).append("' OR SP.EFF_THRU_DT(+) IS NULL) ");
        sb.append("          AND CSC.EZCANCELFLAG       = '0' ");
        sb.append("          AND DCC.EZCANCELFLAG(+)    = '0' ");
        sb.append("          AND MN.EZCANCELFLAG(+)     = '0' ");
        sb.append("          AND LBRT.EZCANCELFLAG      = '0' ");
        sb.append("          AND T.EZCANCELFLAG         = '0' ");
        sb.append("          AND OFA.EZCANCELFLAG(+)    = '0' ");
        sb.append("          AND SP.EZCANCELFLAG(+)     = '0' ");
        sb.append("     ) X ");
        params[IDX_2] = sb.toString();

        params[IDX_3] = new ArrayList<Object[]>(0);

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "I/O";
        columnArray0[IDX_1] = "CONFIG_CATG_CD";
        columnArray0[IDX_2] = BigDecimal.valueOf(3);
        columnArray0[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Config#";
        columnArray1[IDX_1] = "DS_ORD_POSN_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(6);
        columnArray1[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Model";
        columnArray2[IDX_1] = "T_MDL_NM";
        columnArray2[IDX_2] = BigDecimal.valueOf(15);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[IDX_0] = "Quota";
        columnArray3[IDX_1] = "SLS_CR_QUOT_FLG";
        columnArray3[IDX_2] = BigDecimal.valueOf(5);
        columnArray3[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[IDX_0] = "Role Type";
        columnArray4[IDX_1] = "LINE_BIZ_ROLE_TP_DESC_TXT";
        columnArray4[IDX_2] = BigDecimal.valueOf(20);
        columnArray4[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[IDX_4];
        columnArray5[IDX_0] = "Sales Rep Name";
        columnArray5[IDX_1] = "TOC_NM";
        columnArray5[IDX_2] = BigDecimal.valueOf(25);
        columnArray5[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[IDX_4];
        columnArray6[IDX_0] = "Employee ID";
        columnArray6[IDX_1] = "PSN_NUM";
        columnArray6[IDX_2] = BigDecimal.valueOf(10);
        columnArray6[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray6);

        Object[] columnArray7 = new Object[IDX_4];
        columnArray7[IDX_0] = "Percent";
        columnArray7[IDX_1] = "SLS_REP_CR_PCT";
        columnArray7[IDX_2] = BigDecimal.valueOf(7);
        columnArray7[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray7);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "CPO_ORD_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "RANK";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[IDX_6] = scrnMsg.Z;

        return params;
    }
    // END 2023/06/06 T.Doi [CSA-QC#61465, ADD]
}
