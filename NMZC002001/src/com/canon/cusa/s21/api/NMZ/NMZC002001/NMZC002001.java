/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC002001;

import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.CTAC_PNT_FMT_US;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.EXT_MAX_LENGTH;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.MAX_EFF_THRU_DT;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM0803E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8430E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8466E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8467E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8468E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8469E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8470E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8471E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8472E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8473E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8474E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8475E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMAM8698E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0009E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0011E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0054E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0055E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0187E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0188E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0190E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0191E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0192E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0193E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0227E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0228E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0230E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0253E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0254E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0255E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0307E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0308E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0309E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0347E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0348E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.NMZM0349E;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.PROCESS_MODE_CTAC_CRAT;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.PROCESS_MODE_CTAC_UPD;
import static com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant.PROCESS_MODE_GET_CTAC_PSN_PK;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CTAC_PSNTMsg;
import business.db.DS_CTAC_PNTTMsg;
import business.db.DS_CTAC_PNT_TPTMsg;
import business.db.DS_CTAC_PSN_DEPTTMsg;
import business.db.DS_CTAC_PSN_RELNTMsg;
import business.db.DS_CTAC_PSN_SALTTMsg;
import business.db.DS_CTAC_PSN_TTLTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC002001_ContactPointInfoListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.common.NMX.NMXC106001.NMXC106001CommonCheckUtil;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Contact Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/02   Fujitsu         N.Sugiura       Create          N/A
 * 2015/11/20   Fujitsu         T.Ishii         Update          S21_NA#879
 * 2015/12/10   Fujitsu         N.Sugiura       Update          S21_NA#1770
 * 2015/12/15   Fujitsu         N.Sugiura       Update          S21_NA#1921
 * 2016/04/06   SRAA            Y.Chen          Update          S21_NA#6455
 * 2016/04/06   SRAA            Y.Chen          Update          S21_NA#6551
 * 2016/04/20   SRAA            Y.Chen          Update          S21_NA#7197
 * 2016/04/27   Fujitsu         N.Sugiura       Update          S21_NA#7274
 * 2016/08/02   Fujitsu         M.Hara          Update          S21_NA#7704
 * 2016/10/05   Fujitsu         Mz.Takahashi    Update          S21_NA#14431
 * 2016/10/18   Fujitsu         Y.Kanefusa      Update          S21_NA#14628
 * 2017/08/04   Fujitsu         Y.Kanefusa      Update          S21_NA#20209(temporary)
 * 2017/08/04   Fujitsu         T.Ogura         Update          S21_NA#8598
 * 2017/10/17   Fujitsu         K.Ishizuka      Update          QC#20246(Sol#454)
 * 2017/12/18   Fujitsu         Hd.Sugawara     Update          QC#22286
 * 2017/12/22   Fujitsu         Hd.Sugawara     Update          QC#20164(Sol#356)
 * 2018/02/02   Fujitsu         Hd.Sugawara     Update          QC#23904
 * 2018/06/18   Hitachi         K.Kitachi       Update          QC#18591
 * 2018/12/14   Fujitsu         Hd.Sugawara     Update          QC#24022
 * 2019/01/16   Fujitsu         S.Kosaka        Update          QC#29642
 * 2019/06/05   Fujitsu         S.Kosaka        Update          QC#50651
 * 2019/09/13   Fujitsu         A.Kazuki        Update          QC#52693
 * 2021/01/27   CITS            A.Marte         Update          QC#58163
 *</pre>
 */
public class NMZC002001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Contact Information Map */
    private Map<String, ContactInfoBean> contactInfoMap;

    /**
     * Constructor
     */
    public NMZC002001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Customer Update API
     * </pre>
     * @param param      Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(final NMZC002001PMsg param, final ONBATCH_TYPE onBatchType) {

        /** contactInfo Map */
        contactInfoMap = new HashMap<String, ContactInfoBean>();

        // Checking Input value
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);
        if (!checkInput(msgMap)) {
            msgMap.flush();
            return;
        }

        // main
        doProcess(msgMap);
        // end
        msgMap.flush();

    }
    /**
     * <pre>
     * Customer Update API (List)
     * </pre>
     * @param params      Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(List<NMZC002001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NMZC002001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }
    /**
     * Validation check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkValidation(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC002001PMsg param = (NMZC002001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();
        // Account Number
        if (ZYPCommonFunc.hasValue(param.dsAcctNum.getValue())) {
            if (!chkDsAcctNum(param.dsAcctNum.getValue(), glblCmpyCd)) {
                if (!chkDsAcctPros(param.dsAcctNum.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMAM8467E);
                    returnValue = false;
                }
            }
        }
        // Location Number
        if (ZYPCommonFunc.hasValue(param.locNum.getValue())) {
            if (!chkLocNum(param.locNum.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMAM8468E);
                returnValue = false;
            }
        }
        // Contact Person (Check active)
        if (ZYPConstant.FLG_ON_Y.equals(param.dsPrimLocFlg.getValue())) {
            if (ZYPCommonFunc.hasValue(param.ctacPsnPk.getValue())) {
                if (!chkCtacPsn(param.ctacPsnPk.getValue(), glblCmpyCd)) {
                    msgMap.addXxMsgId(NMAM8473E);
                    returnValue = false;
                }
            }
        }
        // Contact type Code
        if (ZYPCommonFunc.hasValue(param.ctacTpCd.getValue())) {
            //S21_NA ADD START QC#20246(Sol#454)
            // Del Start 2017/12/22 QC#20164(Sol#356)
            //if(CTAC_TP.ORDER_CONTACT.equals(param.ctacTpCd.getValue())){
            //    param.ctacTpCd.setValue(CTAC_TP.DELIVERY_INSTALL);
            //    msgMap.setPmsg(param);
            //}
            // Del End 2017/12/22 QC#20164(Sol#356)
            //S21_NA ADD END QC#20246(Sol#454)
            
            if (!chkCtacTp(param.ctacTpCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMAM8469E);
                returnValue = false;
            }
            // Mod Start 2018/02/02 QC#23904
        //}
        } else {
            // If ctacTpCd has not value, It means DELIVERY / INSTALL.
            param.ctacTpCd.setValue(CTAC_TP.DELIVERY_INSTALL);
        }
        // Mod End 2018/02/02 QC#23904

        // Direct Sales Contact Person Salutation Code
        if (ZYPCommonFunc.hasValue(param.dsCtacPsnSaltCd.getValue())) {
            if (!chkDsCtacPsnSalt(param.dsCtacPsnSaltCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMAM8470E);
                returnValue = false;
            }
        }
        // Direct Sales Contact Person Department Code
        if (ZYPCommonFunc.hasValue(param.dsCtacPsnDeptCd.getValue())) {
            if (!chkDsCtacPsnDept(param.dsCtacPsnDeptCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMAM8471E);
                returnValue = false;
            }
        }
        // Direct Sales Contact Person Title Code
        if (ZYPCommonFunc.hasValue(param.dsCtacPsnTtlCd.getValue())) {
            if (!chkDsCtacPsnTtl(param.dsCtacPsnTtlCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMAM8472E);
                returnValue = false;
            }
        }
        // Direct Sales Primary Contact Type Code (Check active)
        if (ZYPCommonFunc.hasValue(param.dsPrimCtacTpCd.getValue())) {
            if (!chkDsPrimCtacTp(param.dsPrimCtacTpCd.getValue(), glblCmpyCd)) {
                msgMap.addXxMsgId(NMAM8474E);
                returnValue = false;
            }
        }
        // Primary Flag = Y, Active Flag = N
        if (ZYPConstant.FLG_ON_Y.equals(param.dsPrimLocFlg.getValue())
                && ZYPConstant.FLG_OFF_N.equals(param.ctacPsnActvFlg.getValue())) {
            msgMap.addXxMsgId(NMAM8475E);
            returnValue = false;
        }

        for (int i = 0; i < param.ContactPointInfoList.getValidCount(); i++) {
            NMZC002001_ContactPointInfoListPMsg linePMsg = param.ContactPointInfoList.no(i);

            // Direct Sales Contact Point Value Text
            if (ZYPCommonFunc.hasValue(linePMsg.dsCtacPntValTxt.getValue())) {
                if (DS_CTAC_PNT_TP.PHONE_ASSISTANT.equals(linePMsg.dsCtacPntTpCd.getValue())
                        || DS_CTAC_PNT_TP.PHONE_MOBILE.equals(linePMsg.dsCtacPntTpCd.getValue())
                        || DS_CTAC_PNT_TP.PHONE_WORK.equals(linePMsg.dsCtacPntTpCd.getValue())
                        || DS_CTAC_PNT_TP.FAX_WORK.equals(linePMsg.dsCtacPntTpCd.getValue())) {

                    // 2019/09/13 QC#52693 Add Start
                  if (!checkTelFormat(linePMsg.dsCtacPntValTxt)) {
                      msgMap.addXxMsgId(NMZC002001Constant.NMAM8348E);
                      returnValue = false;
                  }
                  // 2019/09/13 QC#52693 Add End

                      // 2017/08/04 QC#8598 Del Start
//                    if (!checkTelFormat(linePMsg.dsCtacPntValTxt)) {
//                        msgMap.addXxMsgId(NMZC002001Constant.NMAM8348E);
//                        returnValue = false;
//                    }
                      // 2017/08/04 QC#8598 Del End
                } else if (DS_CTAC_PNT_TP.EMAIL_WORK.equals(linePMsg.dsCtacPntTpCd.getValue())) {
                    if (!checkEmailFormat(linePMsg.dsCtacPntValTxt)) {
                        msgMap.addXxMsgId(NMZC002001Constant.NMAM8347E);
                        returnValue = false;
                    }
                }
                if (!checkExtFormat(linePMsg.dsCtacPsnExtnNum)) {
                    // Mod Start 2018/12/14 QC#24022
                    //msgMap.addXxMsgId(NMZC002001Constant.NMAM8349E);
                    msgMap.addXxMsgId(NMAM8698E);
                    // Mod End 2018/12/14 QC#24022
                    returnValue = false;
                }
            }
        }

        return returnValue;
    }
    // 2019/09/13 QC#52693 Add Start
  private boolean checkTelFormat(EZDPStringItem item) {
      if (ZYPCommonFunc.hasValue(item)) {
          String telNum = item.getValue();
          if (telNum.length() < 7 || telNum.length() > 20) {
              return false;
          }
      }
      return true;
  }
    // 2019/09/13 QC#52693 Add End

// 2017/08/04 QC#8598 Del Start
//    private boolean checkTelFormat(EZDPStringItem item) {
//        if (ZYPCommonFunc.hasValue(item)) {
//            String telNum = item.getValue();
//            if (telNum.length() < 7 || telNum.length() > 20) {
//                return false;
//            } else {
//                telNum = telNum.replaceAll(NMZC002001Constant.SLASH, NMZC002001Constant.HYPHEN);
//                telNum = telNum.replaceAll(NMZC002001Constant.PERIOD, NMZC002001Constant.HYPHEN);
//                ZYPEZDItemValueSetter.setValue(item, telNum);
//            }
//        }
//        return true;
//    }
// 2017/08/04 QC#8598 Del End
    private boolean checkExtFormat(EZDPStringItem item) {
        if (ZYPCommonFunc.hasValue(item)) {
            String extNum = item.getValue();
            // Mod Start 2018/12/14 QC#24022
            //if (extNum.length() > 4) {
            if (extNum.length() > EXT_MAX_LENGTH) {
                // Mod End 2018/12/14 QC#24022
                return false;
            }
        }
        return true;
    }

    private boolean checkEmailFormat(EZDPStringItem item) {
        if (ZYPCommonFunc.hasValue(item)) {
            return NMXC106001CommonCheckUtil.checkEmailFormat(item.getValue());
        }
        return true;
    }

    /**
     * Input parameter check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkInput(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC002001PMsg param = (NMZC002001PMsg) msgMap.getPmsg();

        // Common mandatory check
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NMZM0009E);
            returnValue = false;
        }

        if (!ZYPCommonFunc.hasValue(param.slsDt)) {
            msgMap.addXxMsgId(NMZM0011E);
            returnValue = false;
        }

        if (!ZYPCommonFunc.hasValue(param.xxModeCd)) {
            msgMap.addXxMsgId(NMZM0054E);
            returnValue = false;
        } else {
            // START 2018/06/18 K.Kitachi [QC#18591, MOD]
            if (!PROCESS_MODE_CTAC_CRAT.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_CTAC_UPD.equals(param.xxModeCd.getValue())
                    && !PROCESS_MODE_GET_CTAC_PSN_PK.equals(param.xxModeCd.getValue())) {
            // END 2018/06/18 K.Kitachi [QC#18591, MOD]
                msgMap.addXxMsgId(NMZM0055E);
                returnValue = false;
            }
        }

        // Mandatory check for mode 01
        if (PROCESS_MODE_CTAC_CRAT.equals(param.xxModeCd.getValue())) {
            // Contact Person First Name
            if (!ZYPCommonFunc.hasValue(param.ctacPsnFirstNm.getValue())) {
                msgMap.addXxMsgId(NMZM0187E);
                returnValue = false;
            }
            // Contact Person Last Name
            if (!ZYPCommonFunc.hasValue(param.ctacPsnLastNm.getValue())) {
                msgMap.addXxMsgId(NMZM0188E);
                returnValue = false;
            }
            // Del Start 2018/02/02 QC#23904
            // Contact type Code
            //if (!ZYPCommonFunc.hasValue(param.ctacTpCd.getValue())) {
            //    msgMap.addXxMsgId(NMZM0189E);
            //    returnValue = false;
            //}
            // Del End 2018/02/02 QC#23904

            if (!updateMode(msgMap, param)) {
                returnValue = false;
            }
        }

        // Mandatory check for mode 02
        if (PROCESS_MODE_CTAC_UPD.equals(param.xxModeCd.getValue())) {
            // Contact Person Primary Key
            if (!ZYPCommonFunc.hasValue(param.ctacPsnPk.getValue())) {
                msgMap.addXxMsgId(NMZM0190E);
                returnValue = false;
            }
            // 2017/08/04 QC#8598 Add Start
            Map< ? , ? > dsCtacPntMap;
            List< ? > dsCtacPntPkList = getDsCtacPntPkList(param);
            String dsCtacPntTpCd;
            for (int i = 0; i < dsCtacPntPkList.size(); i++) {
                dsCtacPntMap = (Map< ? , ? >) dsCtacPntPkList.get(i);
                dsCtacPntTpCd = (String) dsCtacPntMap.get("DS_CTAC_PNT_TP_CD");

                for (int j = 0; j < param.ContactPointInfoList.getValidCount(); j++) {
                    if (S21StringUtil.isEquals(dsCtacPntTpCd, param.ContactPointInfoList.no(j).dsCtacPntTpCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(param.ContactPointInfoList.no(j).xxModeCd,  PROCESS_MODE_CTAC_UPD);
                        ZYPEZDItemValueSetter.setValue(param.ContactPointInfoList.no(j).dsCtacPntPk,  (BigDecimal) dsCtacPntMap.get("DS_CTAC_PNT_PK"));
                        break;
                    }
                }
            }
            // 2017/08/04 QC#8598 Add End
        }
        // Mandatory by conditions.
        if (!ZYPCommonFunc.hasValue(param.dsAcctNum.getValue()) && !ZYPCommonFunc.hasValue(param.locNum.getValue())) {
            msgMap.addXxMsgId(NMAM8466E);
            returnValue = false;
        }
        if (ZYPCommonFunc.hasValue(param.dsAcctNum.getValue()) && ZYPCommonFunc.hasValue(param.locNum.getValue())) {
            param.dsAcctNum.clear();
        }

        // Mandatory check for Contact Point
        for (int i = 0; i < param.ContactPointInfoList.getValidCount(); i++) {
            NMZC002001_ContactPointInfoListPMsg linePMsg =  param.ContactPointInfoList.no(i);

            if (!ZYPCommonFunc.hasValue(linePMsg.xxModeCd)) {
                msgMap.addXxMsgId(NMZM0054E);
                returnValue = false;
            } else {
                if (!PROCESS_MODE_CTAC_CRAT.equals(linePMsg.xxModeCd.getValue())
                        && !PROCESS_MODE_CTAC_UPD.equals(linePMsg.xxModeCd.getValue())) {
                    msgMap.addXxMsgId(NMZM0055E);
                    returnValue = false;
                }
            }
            if (PROCESS_MODE_CTAC_CRAT.equals(param.xxModeCd.getValue())) {
                // Direct Sales Contact Point Type Code
                if (!ZYPCommonFunc.hasValue(linePMsg.dsCtacPntTpCd.getValue())) {
                    msgMap.addXxMsgId(NMZM0191E);
                    returnValue = false;
                }
                // Direct Sales Contact Point Value Text
                if (!ZYPCommonFunc.hasValue(linePMsg.dsCtacPntValTxt.getValue())) {
                    msgMap.addXxMsgId(NMZM0192E);
                    returnValue = false;
                }
                if (PROCESS_MODE_CTAC_UPD.equals(linePMsg.xxModeCd.getValue())) {
                    msgMap.addXxMsgId(NMZM0055E);
                    returnValue = false;
                }
            }
            if (PROCESS_MODE_CTAC_UPD.equals(linePMsg.xxModeCd.getValue())) {
                //Direct Sales Contact Point Primary Key
                if (!ZYPCommonFunc.hasValue(linePMsg.dsCtacPntPk.getValue())) {
                    msgMap.addXxMsgId(NMZM0193E);
                    returnValue = false;
                }
            }
        }

        if (PROCESS_MODE_CTAC_CRAT.equals(param.xxModeCd.getValue())) {
            if (param.ContactPointInfoList.getValidCount() == 0) {
                msgMap.addXxMsgId(NMAM8430E);
                returnValue = false;
            }
        }
        if (ZYPCommonFunc.hasValue(param.effFromDt) && ZYPCommonFunc.hasValue(param.effThruDt)) {
            if (param.effFromDt.getValue().compareTo(param.effThruDt.getValue()) > 0) {
                msgMap.addXxMsgId(NMAM0803E);
                returnValue = false;
            }
        }
        // 2017/08/04 QC#8598 Add Start
        // Mandatory Contact Point Check
        if (!chkMandatoryCtacPnt(msgMap, param)) {
            returnValue = false;
        }
        // 2017/08/04 QC#8598 Add End

        return returnValue;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {

        // Validation check
        if (!checkValidation(msgMap)) {
            return;
        }

        // START 2018/06/18 K.Kitachi [QC#18591, ADD]
        if (isProcessModeGetCtacPsnPk(msgMap)) {
            setCtacPsnPk(msgMap);
            return;
        }
        // END 2018/06/18 K.Kitachi [QC#18591, ADD]

        // Insert / Update
        if (!insertUpdateTable(msgMap)) {
            return;
        }

        // Set Output Parameter
        setOutputParameter(msgMap);

    }
    /**
     * chkDsAcctNum
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsAcctNum(String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("sellToCustCd", dsAcctNum);

        Integer resCnt = (Integer) this.ssmBatchClient.queryObject("cntSellToCust", param);
        if (resCnt < 1) {
            return false;
        }
        return true;
    }
    /**
     * chkDsAcctPros
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsAcctPros(String dsAcctNum, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsAcctNum", dsAcctNum);

        Integer resCnt = (Integer) this.ssmBatchClient.queryObject("cntDsAcctPros", param);
        if (resCnt < 1) {
            return false;
        }
        return true;
    }
    /**
     * chkLocNum
     * @param locNUm String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkLocNum(String locNUm, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("locNum", locNUm);

        Integer resCnt = (Integer) this.ssmBatchClient.queryObject("cntPtyLocWrk", param);
        if (resCnt < 1) {
            return false;
        }
        return true;
    }
    /**
     * chkCtacPsn
     * @param ctacPsnPk BigDecimal
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCtacPsn(BigDecimal ctacPsnPk, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("ctacPsnPk", ctacPsnPk);

        Integer resCnt = (Integer) this.ssmBatchClient.queryObject("cntDsCtacPsn", param);
        if (resCnt < 1) {
            return false;
        }
        return true;
    }
    /**
     * chkCtacTp
     * @param ctacTpCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCtacTp(String ctacTpCd, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("ctacTpCd", ctacTpCd);

        Integer resCnt = (Integer) this.ssmBatchClient.queryObject("cntCtacTp", param);
        if (resCnt < 1) {
            return false;
        }
        return true;
    }
    /**
     * chkDsCtacPsnSalt
     * @param dsCtacPsnSaltCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsCtacPsnSalt(String dsCtacPsnSaltCd, String glblCmpyCd) {

        DS_CTAC_PSN_SALTTMsg chkDsCtacPsnSaltTMsg = new DS_CTAC_PSN_SALTTMsg();
        ZYPEZDItemValueSetter.setValue(chkDsCtacPsnSaltTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkDsCtacPsnSaltTMsg.dsCtacPsnSaltCd, dsCtacPsnSaltCd);
        chkDsCtacPsnSaltTMsg = (DS_CTAC_PSN_SALTTMsg) EZDTBLAccessor.findByKey(chkDsCtacPsnSaltTMsg);
        if (chkDsCtacPsnSaltTMsg == null) {
            return false;
        }
        return true;
    }
    /**
     * chkDsCtacPsnDept
     * @param dsCtacPsnDeptCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsCtacPsnDept(String dsCtacPsnDeptCd, String glblCmpyCd) {

        DS_CTAC_PSN_DEPTTMsg chkDsCtacPsnDeptTMsg = new DS_CTAC_PSN_DEPTTMsg();
        ZYPEZDItemValueSetter.setValue(chkDsCtacPsnDeptTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkDsCtacPsnDeptTMsg.dsCtacPsnDeptCd, dsCtacPsnDeptCd);
        chkDsCtacPsnDeptTMsg = (DS_CTAC_PSN_DEPTTMsg) EZDTBLAccessor.findByKey(chkDsCtacPsnDeptTMsg);
        if (chkDsCtacPsnDeptTMsg == null) {
            return false;
        }
        return true;
    }
    /**
     * chkDsCtacPsnTtl
     * @param dsCtacPsnTtlCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsCtacPsnTtl(String dsCtacPsnTtlCd, String glblCmpyCd) {

        DS_CTAC_PSN_TTLTMsg chkDsCtacPsnTtlTMsg = new DS_CTAC_PSN_TTLTMsg();
        ZYPEZDItemValueSetter.setValue(chkDsCtacPsnTtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkDsCtacPsnTtlTMsg.dsCtacPsnTtlCd, dsCtacPsnTtlCd);
        chkDsCtacPsnTtlTMsg = (DS_CTAC_PSN_TTLTMsg) EZDTBLAccessor.findByKey(chkDsCtacPsnTtlTMsg);
        if (chkDsCtacPsnTtlTMsg == null) {
            return false;
        }
        return true;
    }
    /**
     * chkDsPrimCtacTp
     * @param ctacPsnPk String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkDsPrimCtacTp(String dsPrimCtacTpCd, String glblCmpyCd) {

        DS_CTAC_PNT_TPTMsg chkDsCtacPntTpTMsg = new DS_CTAC_PNT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(chkDsCtacPntTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkDsCtacPntTpTMsg.dsCtacPntTpCd, dsPrimCtacTpCd);
        chkDsCtacPntTpTMsg = (DS_CTAC_PNT_TPTMsg) EZDTBLAccessor.findByKey(chkDsCtacPntTpTMsg);
        if (chkDsCtacPntTpTMsg == null) {
            return false;
        }
        return true;
    }
    /**
     * getPrimCtac
     * @param dsAcctNum String
     * @param locNum String
     * @param glblCmpyCd String
     * @param primFlg String
     * @param ctacPsnPk BigDecimal
     * @param ctacTpCd String
     * @param slsDt String
     * @return BigDecimal
     */
    private BigDecimal getPrimCtac(String dsAcctNum, String locNum, String glblCmpyCd, String primFlg, BigDecimal ctacPsnPk, String ctacTpCd, String slsDt) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("primFlg", primFlg);
        param.put("ctacPsnPk", ctacPsnPk);
        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            param.put("dsAcctNum", dsAcctNum);
        } else {
            param.put("dsAcctNum", null);
        }
        if (ZYPCommonFunc.hasValue(locNum)) {
            param.put("locNum", locNum);
        } else {
            param.put("locNum", null);
        }
        
        // 2017/08/04 S21_NA#8598 Add Start
        param.put("ctacTpCd", ctacTpCd);
        param.put("maxEffThruDt", MAX_EFF_THRU_DT);
        param.put("slsDt", slsDt);
        // 2017/08/04 S21_NA#8598 Add End
        return (BigDecimal) this.ssmBatchClient.queryObject("getPrimCtacPk", param);
    }
    /**
     * getDsCtacPsnRelnPk
     * @param param NMZC002001PMsg
     * @param glblCmpyCd String
     * @return BigDecimal
     */
    // 2017/08/04 QC#8598 Mod Start
    private BigDecimal getDsCtacPsnRelnPk(NMZC002001PMsg param, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ctacPsnPk", param.ctacPsnPk.getValue());
        // 2017/08/04 S21_NA#8598 Add Start
        ssmParam.put("ctacTpCd", param.ctacTpCd.getValue());
        if (ZYPCommonFunc.hasValue(param.locNum)) {
            ssmParam.put("locNum", param.locNum.getValue());
        } else {
            ssmParam.put("dsAcctNum", param.dsAcctNum.getValue());
        }
        ssmParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        ssmParam.put("slsDt", param.slsDt.getValue());
        // 2017/08/04 S21_NA#8598 Add End

        return (BigDecimal) this.ssmBatchClient.queryObject("getDsCtacPsnRelnPk", ssmParam);
    }
    // 2017/08/04 QC#8598 Mod End

    // Add Start 2017/12/18 QC#22286
    /**
     * getDsCtacPsnRelnPk
     * @param glblCmpyCd String
     * @param ctacPsnPk BigDecimal
     * @param ctacTpCd String
     * @param locNum String
     * @param dsAcctNum String
     * @param slsDt String
     * @return BigDecimal
     */
    private BigDecimal getDsCtacPsnRelnPk(String glblCmpyCd, BigDecimal ctacPsnPk, String ctacTpCd, String locNum, String dsAcctNum, String slsDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ctacPsnPk", ctacPsnPk);
        ssmParam.put("ctacTpCd", ctacTpCd);

        if (ZYPCommonFunc.hasValue(locNum)) {
            ssmParam.put("locNum", locNum);
        } else {
            ssmParam.put("dsAcctNum", dsAcctNum);
        }

        ssmParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        ssmParam.put("slsDt", slsDt);

        return (BigDecimal) this.ssmBatchClient.queryObject("getDsCtacPsnRelnPk", ssmParam);
    }
    // Add End 2017/12/18 QC#22286

    /**
     * UpdateMode
     * @param msgMap S21ApiMessageMap
     * @param param NMZC002001PMsg
     * @return boolean
     */
    private boolean updateMode(S21ApiMessageMap msgMap, NMZC002001PMsg param) {
        List< ? > ctacPsnPkList = getDsCtacPsnPkListByName(param);

        int ctacPsnPkSize = ctacPsnPkList.size();
        // 2017/08/04 S21_NA#8598 Mod Start
//        if (ctacPsnPkSize > 1) {
              // QC#20209 2017/08/04 Mod Start
//            msgMap.addXxMsgId(NMAM8420E);
//            return false;
//        } else if (ctacPsnPkSize == 1) {
        if (ctacPsnPkSize >= 1) {
        // 2017/08/04 S21_NA#8598 Mod End
            // To All Update Mode
            ZYPEZDItemValueSetter.setValue(param.xxModeCd,  PROCESS_MODE_CTAC_UPD);
            ZYPEZDItemValueSetter.setValue(param.ctacPsnPk, (BigDecimal) ctacPsnPkList.get(0));

            // 2017/08/04 QC#8598 Del Start
//            Map<?, ?> dsCtacPntMap;
//            List< ? > dsCtacPntPkList = getDsCtacPntPkList(param);
//            String dsCtacPntTpCd;
//            for (int i = 0; i < dsCtacPntPkList.size(); i++) {
//                dsCtacPntMap = (Map<?, ?>)dsCtacPntPkList.get(i);
//                dsCtacPntTpCd = (String) dsCtacPntMap.get("DS_CTAC_PNT_TP_CD");
//
//                for (int j = 0; j < param.ContactPointInfoList.getValidCount(); j++) {
//                    if (S21StringUtil.isEquals(dsCtacPntTpCd, param.ContactPointInfoList.no(j).dsCtacPntTpCd.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(param.ContactPointInfoList.no(j).xxModeCd,  PROCESS_MODE_CTAC_UPD);
//                        ZYPEZDItemValueSetter.setValue(param.ContactPointInfoList.no(j).dsCtacPntPk,  (BigDecimal) dsCtacPntMap.get("DS_CTAC_PNT_PK"));
//                        break;
//                    }
//                }
//            }
            // 2017/08/04 S21_NA#8598 Del End
        }

        return true;
    }

    /**
     * getDsCtacPsnPkListByName
     * @param param NMZC002001PMsg
     * @return List<BigDecimal>
     */
    private List< ? > getDsCtacPsnPkListByName(NMZC002001PMsg param) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        // 2017/08/04 S21_NA#8598 Del Start
//        ssmParam.put("ctacTpCd", param.ctacTpCd.getValue());
        // 2017/08/04 S21_NA#8598 Del End
        ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);

        if (ZYPCommonFunc.hasValue(param.dsAcctNum)) {
            ssmParam.put("dsAcctNum", param.dsAcctNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(param.locNum)) {
            ssmParam.put("locNum", param.locNum.getValue());
        }
        if (ZYPCommonFunc.hasValue(param.ctacPsnFirstNm.getValue().trim())) {
            ssmParam.put("ctacPsnFirstNm", param.ctacPsnFirstNm.getValue().trim().toUpperCase());
        }
        if (ZYPCommonFunc.hasValue(param.ctacPsnLastNm.getValue().trim())) {
            ssmParam.put("ctacPsnLastNm", param.ctacPsnLastNm.getValue().trim().toUpperCase());
        }
        // 2017/08/04 S21_NA#8598 Add Start
        ssmParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        ssmParam.put("slsDt", param.slsDt.getValue());
        // 2017/08/04 S21_NA#8598 Add End
        // 2019/06/05 QC#50651 Add Start
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        // 2019/06/05 QC#50651 Add End

        return (List< ? >) this.ssmBatchClient.queryObjectList("getDsCtacPsnPkListByName", ssmParam);
    }

    /**
     * getDsCtacPntPkList
     * @param param NMZC002001PMsg
     * @return List< ? >
     */
    private List< ? > getDsCtacPntPkList(NMZC002001PMsg param) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("ctacPsnPk", param.ctacPsnPk.getValue());

        return (List< ? >) this.ssmBatchClient.queryObjectList("getDsCtacPntPkList", ssmParam);
    }

    /**
     * Insert / Update
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean insertUpdateTable(S21ApiMessageMap msgMap) {

        NMZC002001PMsg param = (NMZC002001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();

        // Add Start 2017/12/22 QC#20164(Sol#356)
        if (CTAC_TP.ORDER_CONTACT.equals(param.ctacTpCd.getValue())) {
            param.ctacTpCd.setValue(CTAC_TP.DELIVERY_INSTALL);
            msgMap.setPmsg(param);
        }
        // Add End 2017/12/22 QC#20164(Sol#356)

        if (PROCESS_MODE_CTAC_CRAT.equals(param.xxModeCd.getValue())) {
            if (!tableCreation(msgMap, param, glblCmpyCd)) {
                return false;
            }

        } else if (PROCESS_MODE_CTAC_UPD.equals(param.xxModeCd.getValue())) {
            if (!tableUpdate(msgMap, param, glblCmpyCd)) {
                return false;
            }
        }

        return true;
    }
    /**
     * Contact creation (Mode : 01)
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean tableCreation(S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, String glblCmpyCd) {

        // Insert DS_CTAC_PSN_RELN
        if (!dsCtacPsnRelnCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert DS_CTAC_PNT
        if (!dsCtacPntCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Insert CTAC_PSN
        if (!ctacPsnCreation(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }

        return true;
    }
    /**
     * Contact Update (Mode : 02)
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean tableUpdate(S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, String glblCmpyCd) {

        // Update DS_CTAC_PSN_RELN
        if (!dsCtacPsnRelnUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update CTAC_PSN
        if (!ctacPsnUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // Update DS_CTAC_PNT
        if (!dsCtacPntUpdate(msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        return true;
    }
    /**
     * dsCtacPsnRelnCreation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsCtacPsnRelnCreation(S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, String glblCmpyCd) {

        DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTmsg = new DS_CTAC_PSN_RELNTMsg();

        // 2017/08/07 S21_NA#8598 Mod Start
//        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsAcctNum, pMsg.dsAcctNum.getValue());
//        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.locNum, pMsg.locNum.getValue());
//        if (ZYPCommonFunc.hasValue(pMsg.effFromDt.getValue())) {
//            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.effFromDt, pMsg.effFromDt.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.effFromDt, pMsg.slsDt.getValue());
//        }
//        if (ZYPCommonFunc.hasValue(pMsg.effThruDt.getValue())) {
//            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.effThruDt, pMsg.effThruDt.getValue());
//        }
//        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dsPrimLocFlg.getValue())) {
//            String date = pMsg.slsDt.getValue();
//            date = ZYPDateUtil.addDays(date, -1);
//            if (pMsg.effThruDt.getValue().equals(date)) {
//                ZYPEZDItemValueSetter.setValue(pMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
//            }
//        }
//        BigDecimal dsCtacPsnRelnPk = getPrimCtac(pMsg.dsAcctNum.getValue(), pMsg.locNum.getValue(), glblCmpyCd, ZYPConstant.FLG_ON_Y, null);
//        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dsPrimLocFlg.getValue())) {
//            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsPrimLocFlg, pMsg.dsPrimLocFlg.getValue());
//            if (ZYPCommonFunc.hasValue(dsCtacPsnRelnPk)) {
//                if (!updateOtherCtacFlg(msgMap, dsCtacPsnRelnPk, glblCmpyCd, ZYPConstant.FLG_OFF_N)) {
//                    return false;
//                }
//            }
//        } else if (ZYPConstant.FLG_OFF_N.equals(pMsg.dsPrimLocFlg.getValue())) {
//            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsPrimLocFlg, pMsg.dsPrimLocFlg.getValue());
//        } else {
//            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
//        }
//        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsCtacPsnRelnPk, getDsCtacPsnRelnPk());
//        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.ctacPsnPk, getCtacPsnPk());
//        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.ctacTpCd, pMsg.ctacTpCd.getValue());
//        int idx = 0;
//        ContactInfoBean contactInfoBean = new ContactInfoBean();
//        contactInfoBean.setDsCtacPsnRelnPk(dsCtacPsnRelnTmsg.dsCtacPsnRelnPk.getValue());
//        contactInfoBean.setCtacPsnPk(dsCtacPsnRelnTmsg.ctacPsnPk.getValue());
//        contactInfoMap.put(String.valueOf(idx), contactInfoBean);
        if (!dsCtacPsnRelnCreationSetter(dsCtacPsnRelnTmsg, msgMap, pMsg, glblCmpyCd)) {
            return false;
        }
        // 2017/08/07 S21_NA#8598 Mod End

        S21ApiTBLAccessor.create(dsCtacPsnRelnTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCtacPsnRelnTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0227E);
            return false;
        }

        // Add Start 2017/12/18 QC#22286
        // Insert or update DELIVERY / INSTALL 
        boolean delivInsResult = insertUpdateDeliveryInstall(msgMap, pMsg, dsCtacPsnRelnTmsg);
        if (!delivInsResult) {
            return false;
        }
        // Add End 2017/12/18 QC#22286

        return true;
    }

    /**
     * ctacPsnCreation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean ctacPsnCreation(S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, String glblCmpyCd) {

        CTAC_PSNTMsg ctacPsnTmsg = new CTAC_PSNTMsg();

        int idx = 0;
        ContactInfoBean contactInfoBean = contactInfoMap.get(String.valueOf(idx));

        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnPk, contactInfoBean.getCtacPsnPk());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnFirstNm, pMsg.ctacPsnFirstNm.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnLastNm, pMsg.ctacPsnLastNm.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnPvtLoginUsrNm, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnRcvMlFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnCmntTxt, pMsg.ctacPsnCmntTxt.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnPvtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnScrTrgtFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnNum, contactInfoBean.getCtacPsnPk().toString());

        if (ZYPCommonFunc.hasValue(pMsg.ctacPsnActvFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnActvFlg, pMsg.ctacPsnActvFlg.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);
        }
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.dsCtacPsnSaltCd, pMsg.dsCtacPsnSaltCd.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.dsCtacPsnDeptCd, pMsg.dsCtacPsnDeptCd.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.dsCtacPsnTtlCd, pMsg.dsCtacPsnTtlCd.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.dsPrimCtacTpCd)) {
            ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.dsPrimCtacTpCd, pMsg.dsPrimCtacTpCd);
        } else {
            ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.dsPrimCtacTpCd, contactInfoBean.getDsCtacPntTpCd());
        }

        S21ApiTBLAccessor.create(ctacPsnTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ctacPsnTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0228E);
            return false;
        }

        return true;
    }
    /**
     * dsCtacPntCreation
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsCtacPntCreation(S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, String glblCmpyCd) {

        List<DS_CTAC_PNTTMsg> dsCtacPntTmsgList = new ArrayList<DS_CTAC_PNTTMsg>();

        ContactInfoBean contactInfoBean = contactInfoMap.get(String.valueOf(0));
        BigDecimal ctacPsnPk = contactInfoBean.getCtacPsnPk();

        for (int i = 0; i < pMsg.ContactPointInfoList.getValidCount(); i++) {
            DS_CTAC_PNTTMsg dsCtacPntTmsg = new DS_CTAC_PNTTMsg();
            NMZC002001_ContactPointInfoListPMsg linePrm = pMsg.ContactPointInfoList.no(i);
            contactInfoBean = contactInfoMap.get(String.valueOf(i));
            if (contactInfoBean == null) {
                contactInfoBean = new ContactInfoBean();
            }
            ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntPk, getDsCtacPntPk());
            ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.ctacPsnPk, ctacPsnPk);
            ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntTpCd, linePrm.dsCtacPntTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntValTxt, linePrm.dsCtacPntValTxt.getValue());
            ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPsnExtnNum, linePrm.dsCtacPsnExtnNum.getValue());
            if (ZYPCommonFunc.hasValue(linePrm.dsOpsOutFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsOpsOutFlg, linePrm.dsOpsOutFlg.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            }
            if (ZYPCommonFunc.hasValue(linePrm.dsCtacPntActvFlg.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntActvFlg, linePrm.dsCtacPntActvFlg.getValue());
            } else {
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.ctacPntFmtTxt, getCtacPntFmtTxt(linePrm.dsCtacPntTpCd.getValue(), glblCmpyCd)); // 2017/08/04 S21_NA#8598 Add

            dsCtacPntTmsgList.add(dsCtacPntTmsg);
            contactInfoBean.setDsCtacPntPk(dsCtacPntTmsg.dsCtacPntPk.getValue());
            contactInfoBean.setDsCtacPntActvFlg(dsCtacPntTmsg.dsCtacPntActvFlg.getValue());
            contactInfoBean.setDsCtacPntTpCd(dsCtacPntTmsg.dsCtacPntTpCd.getValue());
            contactInfoMap.put(String.valueOf(i), contactInfoBean);
        }
        for (DS_CTAC_PNTTMsg inTmsg : dsCtacPntTmsgList) {
            S21ApiTBLAccessor.create(inTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTmsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0230E);
                return false;
            }
        }
        return true;
    }
    /**
     * dsCtacPsnRelnUpdate
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsCtacPsnRelnUpdate(S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, String glblCmpyCd) {

        // Del Start 2018/02/02 QC#23904
        //if (!ZYPCommonFunc.hasValue(pMsg.ctacTpCd)) {
        //    ContactInfoBean contactInfoBean = new ContactInfoBean();
        //    contactInfoBean.setCtacPsnPk(pMsg.ctacPsnPk.getValue());
        //    contactInfoMap.put(String.valueOf(0), contactInfoBean);
        //    return true;
        //}
        // Del End 2018/02/02 QC#23904
        boolean isUpdEffFromDt = true;
        boolean isUpdEffThruDt = true;
        DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTmsg = new DS_CTAC_PSN_RELNTMsg();
        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.glblCmpyCd, glblCmpyCd);
        // 2017/08/04 S21_NA#8598 Mod Start
//        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsCtacPsnRelnPk, getDsCtacPsnRelnPk(pMsg.ctacPsnPk.getValue(), glblCmpyCd));
        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsCtacPsnRelnPk, getDsCtacPsnRelnPk(pMsg, glblCmpyCd));
        // 2017/08/04 S21_NA#8598 Mod End

        if (ZYPCommonFunc.hasValue(dsCtacPsnRelnTmsg.dsCtacPsnRelnPk)) {
            dsCtacPsnRelnTmsg = (DS_CTAC_PSN_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(dsCtacPsnRelnTmsg);
            if (dsCtacPsnRelnTmsg == null) {

                msgMap.addXxMsgId(NMZM0307E);
                return false;
            } else {
                
                if (ZYPCommonFunc.hasValue(pMsg.effFromDt) && ZYPCommonFunc.hasValue(dsCtacPsnRelnTmsg.effFromDt)) {
                    if (pMsg.effFromDt.getValue().equals(dsCtacPsnRelnTmsg.effFromDt.getValue())) {
                        isUpdEffFromDt = false;
                    }
                    if (ZYPDateUtil.compare(dsCtacPsnRelnTmsg.effFromDt.getValue(), pMsg.slsDt.getValue()) <= 0) {
                        if (ZYPDateUtil.compare(pMsg.effFromDt.getValue(), dsCtacPsnRelnTmsg.effFromDt.getValue()) > 0) {
                            isUpdEffFromDt = false;
                        }
                    }
                } else {
                    if (!ZYPCommonFunc.hasValue(pMsg.effFromDt)) {
                        isUpdEffFromDt = false;
                    }
                }
                if (isUpdEffFromDt) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.effFromDt, pMsg.effFromDt.getValue());
                }

                ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsAcctNum, pMsg.dsAcctNum.getValue());
                ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.locNum, pMsg.locNum.getValue());

                String chkEffThruDtPmsg = "";
                String chkEffThruDtTmsg = "";
                if (ZYPCommonFunc.hasValue(pMsg.effThruDt)) {
                    chkEffThruDtPmsg = pMsg.effThruDt.getValue();
                } else {
                    chkEffThruDtPmsg = MAX_EFF_THRU_DT;
                }
                if (ZYPCommonFunc.hasValue(dsCtacPsnRelnTmsg.effThruDt)) {
                    chkEffThruDtTmsg = dsCtacPsnRelnTmsg.effThruDt.getValue();
                } else {
                    chkEffThruDtTmsg = MAX_EFF_THRU_DT;
                }if (chkEffThruDtPmsg.equals(chkEffThruDtTmsg)) {
                    isUpdEffThruDt = false;
                }
                if (isUpdEffThruDt) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.effThruDt, pMsg.effThruDt.getValue());
                }
                if (ZYPConstant.FLG_ON_Y.equals(pMsg.dsPrimLocFlg.getValue())) {
                    String date = pMsg.slsDt.getValue();
                    date = ZYPDateUtil.addDays(date, -1);
                    if (pMsg.effThruDt.getValue().equals(date)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
                    }
                }
                if (ZYPCommonFunc.hasValue(pMsg.dsPrimLocFlg) && ZYPCommonFunc.hasValue(pMsg.ctacTpCd)) {

                    if (ZYPConstant.FLG_ON_Y.equals(pMsg.dsPrimLocFlg.getValue())) {
                        BigDecimal dsCtacPsnRelnPk = getPrimCtac(pMsg.dsAcctNum.getValue(), pMsg.locNum.getValue(), glblCmpyCd, ZYPConstant.FLG_ON_Y, pMsg.ctacPsnPk.getValue(), pMsg.ctacTpCd.getValue(), pMsg.slsDt.getValue());

                        if (ZYPCommonFunc.hasValue(dsCtacPsnRelnPk)) {
                            if (!updateOtherCtacFlg(msgMap, dsCtacPsnRelnPk, glblCmpyCd, ZYPConstant.FLG_OFF_N)) {
                                return false;
                            }
                        }
                    }
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsPrimLocFlg, pMsg.dsPrimLocFlg.getValue());
                }
                // 2017/08/04 S21_NA#8598 Del Start
//                if (ZYPCommonFunc.hasValue(pMsg.ctacTpCd)) {
//                    ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.ctacTpCd, pMsg.ctacTpCd.getValue());
//                }
                // 2017/08/04 S21_NA#8598 Del End
                S21ApiTBLAccessor.update(dsCtacPsnRelnTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCtacPsnRelnTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0253E);
                    return false;
                }
                int idx = 0;
                ContactInfoBean contactInfoBean = new ContactInfoBean();
                contactInfoBean.setDsCtacPsnRelnPk(dsCtacPsnRelnTmsg.dsCtacPsnRelnPk.getValue());
                contactInfoBean.setCtacPsnPk(dsCtacPsnRelnTmsg.ctacPsnPk.getValue());
                contactInfoMap.put(String.valueOf(idx), contactInfoBean);

                // Add Start 2017/12/18 QC#22286
                // Insert or update DELIVERY / INSTALL 
                boolean delivInsResult = insertUpdateDeliveryInstall(msgMap, pMsg, dsCtacPsnRelnTmsg);
                if (!delivInsResult) {
                    return false;
                }
                // Add End 2017/12/18 QC#22286

                return true;

            }
        } else {
            dsCtacPsnRelnTmsg = new DS_CTAC_PSN_RELNTMsg();
            if (!dsCtacPsnRelnCreationSetter(dsCtacPsnRelnTmsg, msgMap, pMsg, glblCmpyCd)) {
                return false;
            }
            S21ApiTBLAccessor.create(dsCtacPsnRelnTmsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCtacPsnRelnTmsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0227E);
                return false;
            }

            // Add Start 2017/12/18 QC#22286
            // Insert or update DELIVERY / INSTALL 
            boolean delivInsResult = insertUpdateDeliveryInstall(msgMap, pMsg, dsCtacPsnRelnTmsg);
            if (!delivInsResult) {
                return false;
            }
            // Add End 2017/12/18 QC#22286
        }
        // 2017/08/04 S21_NA#8598 Add Start
        if (isUpdEffFromDt || isUpdEffThruDt) {
            DS_CTAC_PSN_RELNTMsg effUpdDsCtacPsnRelnTmsg = new DS_CTAC_PSN_RELNTMsg();
            // Update Effective Date Other Relation
            List< ? > DsCtacPsnRelnList = getDsCtacPsnRelnList(pMsg);
            Map< ? , ? > dsCtacPsnRelnMap;
            for (int i = 0; i < DsCtacPsnRelnList.size(); i++) {
                dsCtacPsnRelnMap = (Map< ? , ? >) DsCtacPsnRelnList.get(i);
                effUpdDsCtacPsnRelnTmsg = new DS_CTAC_PSN_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(effUpdDsCtacPsnRelnTmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(effUpdDsCtacPsnRelnTmsg.dsCtacPsnRelnPk, (BigDecimal) dsCtacPsnRelnMap.get("DS_CTAC_PSN_RELN_PK"));
                effUpdDsCtacPsnRelnTmsg = (DS_CTAC_PSN_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(effUpdDsCtacPsnRelnTmsg);
                ZYPEZDItemValueSetter.setValue(effUpdDsCtacPsnRelnTmsg.effFromDt, dsCtacPsnRelnTmsg.effFromDt);
                ZYPEZDItemValueSetter.setValue(effUpdDsCtacPsnRelnTmsg.effThruDt, dsCtacPsnRelnTmsg.effThruDt);
                S21ApiTBLAccessor.update(effUpdDsCtacPsnRelnTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(effUpdDsCtacPsnRelnTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0253E);
                    return false;
                }
            }
        }
        // 2017/08/04 S21_NA#8598 Add End
        return true;

    }
    // 2017/08/07 S21_NA#8598 Add Start
    /**
     * dsCtacPsnRelnCreationSetter
     * @param dsCtacPsnRelnTmsg DS_CTAC_PSN_RELNTMsg
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsCtacPsnRelnCreationSetter(DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTmsg, S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, String glblCmpyCd) {

        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsAcctNum, pMsg.dsAcctNum.getValue());
        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.locNum, pMsg.locNum.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.effFromDt.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.effFromDt, pMsg.effFromDt.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.effFromDt, pMsg.slsDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(pMsg.effThruDt.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.effThruDt, pMsg.effThruDt.getValue());
        }
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dsPrimLocFlg.getValue())) {
            String date = pMsg.slsDt.getValue();
            date = ZYPDateUtil.addDays(date, -1);
            if (pMsg.effThruDt.getValue().equals(date)) {
                ZYPEZDItemValueSetter.setValue(pMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
            }
        }
        BigDecimal dsCtacPsnRelnPk = getPrimCtac(pMsg.dsAcctNum.getValue(), pMsg.locNum.getValue(), glblCmpyCd, ZYPConstant.FLG_ON_Y, pMsg.ctacPsnPk.getValue(), pMsg.ctacTpCd.getValue(), pMsg.slsDt.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dsPrimLocFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsPrimLocFlg, pMsg.dsPrimLocFlg.getValue());
            if (ZYPCommonFunc.hasValue(dsCtacPsnRelnPk)) {
                if (!updateOtherCtacFlg(msgMap, dsCtacPsnRelnPk, glblCmpyCd, ZYPConstant.FLG_OFF_N)) {
                    return false;
                }
            }
        } else if (ZYPConstant.FLG_OFF_N.equals(pMsg.dsPrimLocFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsPrimLocFlg, pMsg.dsPrimLocFlg.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsCtacPsnRelnPk, getDsCtacPsnRelnPk());
        // 2017/08/07 S21_NA#8598 Mod Start
//        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.ctacPsnPk, getCtacPsnPk());
        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.ctacPsnPk, getCtacPsnPk(pMsg));
        // 2017/08/07 S21_NA#8598 Mod End
        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.ctacTpCd, pMsg.ctacTpCd.getValue());
        int idx = 0;
        ContactInfoBean contactInfoBean = new ContactInfoBean();
        contactInfoBean.setDsCtacPsnRelnPk(dsCtacPsnRelnTmsg.dsCtacPsnRelnPk.getValue());
        contactInfoBean.setCtacPsnPk(dsCtacPsnRelnTmsg.ctacPsnPk.getValue());
        contactInfoMap.put(String.valueOf(idx), contactInfoBean);
        return true;
    }

    // Add Start 2017/12/18 QC#22286
    /**
     * insertUpdateDeliveryInstall
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param dsCtacPsnRelnTmsg DS_CTAC_PSN_RELNTMsg
     * @return boolean
     */
    private boolean insertUpdateDeliveryInstall(S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTmsg) {
        BigDecimal dsCtacPsnRelnPk = getDsCtacPsnRelnPk(dsCtacPsnRelnTmsg.glblCmpyCd.getValue(), //
                dsCtacPsnRelnTmsg.ctacPsnPk.getValue(), CTAC_TP.DELIVERY_INSTALL, //
                dsCtacPsnRelnTmsg.locNum.getValue(), dsCtacPsnRelnTmsg.dsAcctNum.getValue(), //
                pMsg.slsDt.getValue());

        if (dsCtacPsnRelnPk == null) {
            // Insert
            DS_CTAC_PSN_RELNTMsg delivInsRelnTMsg = new DS_CTAC_PSN_RELNTMsg();

            EZDMsg.copy(dsCtacPsnRelnTmsg, null, delivInsRelnTMsg, null);
            // DS_CTAC_PSN_RELN_PK
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.dsCtacPsnRelnPk, getDsCtacPsnRelnPk());
            // DS_PRIM_LOC_FLG : 'N'
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
            // CTAC_TP_CD : DELIVERY / INSTALL
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.ctacTpCd, CTAC_TP.DELIVERY_INSTALL);

            S21ApiTBLAccessor.insert(delivInsRelnTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(delivInsRelnTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0227E);
                return false;
            }
        } else {
            // Update
            DS_CTAC_PSN_RELNTMsg delivInsRelnTMsg = new DS_CTAC_PSN_RELNTMsg();

            // Set primary key
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.glblCmpyCd, dsCtacPsnRelnTmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.dsCtacPsnRelnPk, dsCtacPsnRelnPk);

            delivInsRelnTMsg = (DS_CTAC_PSN_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(delivInsRelnTMsg);

            if (delivInsRelnTMsg == null) {
                msgMap.addXxMsgId(NMZM0307E);
                return false;
            }

            // CTAC_PSN_PK
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.ctacPsnPk, dsCtacPsnRelnTmsg.ctacPsnPk);
            // DS_ACCT_NUM
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.dsAcctNum, dsCtacPsnRelnTmsg.dsAcctNum);
            // LOC_NUM
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.locNum, dsCtacPsnRelnTmsg.locNum);
            // EFF_FROM_DT
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.effFromDt, dsCtacPsnRelnTmsg.effFromDt);
            // EFF_THRU_DT
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.effThruDt, dsCtacPsnRelnTmsg.effThruDt);
            // DS_PRIM_LOC_FLG : 'N'
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
            // CTAC_TP_CD : DELIVERY / INSTALL
            ZYPEZDItemValueSetter.setValue(delivInsRelnTMsg.ctacTpCd, CTAC_TP.DELIVERY_INSTALL);

            S21ApiTBLAccessor.update(delivInsRelnTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(delivInsRelnTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NMZM0253E);
                return false;
            }
        }

        return true;
    }
    // Add End 2017/12/18 QC#22286

    /**
     * ctacPsnUpdate
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean ctacPsnUpdate(S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, String glblCmpyCd) {

        CTAC_PSNTMsg ctacPsnTmsg = new CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnPk, pMsg.ctacPsnPk.getValue());

        ctacPsnTmsg = (CTAC_PSNTMsg) EZDTBLAccessor.findByKeyForUpdate(ctacPsnTmsg);
        if (ctacPsnTmsg == null) {
            msgMap.addXxMsgId(NMZM0308E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnFirstNm, pMsg.ctacPsnFirstNm.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnLastNm, pMsg.ctacPsnLastNm.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnCmntTxt, pMsg.ctacPsnCmntTxt.getValue());

        if (ZYPCommonFunc.hasValue(pMsg.ctacPsnActvFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.ctacPsnActvFlg, pMsg.ctacPsnActvFlg.getValue());
        }
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.dsCtacPsnSaltCd, pMsg.dsCtacPsnSaltCd.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.dsCtacPsnDeptCd, pMsg.dsCtacPsnDeptCd.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.dsCtacPsnTtlCd, pMsg.dsCtacPsnTtlCd.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.dsPrimCtacTpCd.getValue())) {
            // Direct Sales Primary Contact Type Code (Check active)
            if (chkDsPrimCtacTp(pMsg.dsPrimCtacTpCd.getValue(), glblCmpyCd)) {
                ZYPEZDItemValueSetter.setValue(ctacPsnTmsg.dsPrimCtacTpCd, pMsg.dsPrimCtacTpCd.getValue());

            } else {
                msgMap.addXxMsgId(NMAM8474E);
                return false;
            }

        }

        S21ApiTBLAccessor.update(ctacPsnTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ctacPsnTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0254E);
            return false;
        }

        return true;
    }
    /**
     * dsCtacPntUpdate
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC002001PMsg
     * @param glblCmpyCd String
     * @return Results of the check.(false:error)
     */
    private boolean dsCtacPntUpdate(S21ApiMessageMap msgMap, NMZC002001PMsg pMsg, String glblCmpyCd) {

        ContactInfoBean contactInfoBean = contactInfoMap.get(String.valueOf(0));
        BigDecimal ctacPsnPk = contactInfoBean.getCtacPsnPk();

        // START 2021/02/02 A.Marte [QC#58163, MOD]
        if (ZYPCommonFunc.hasValue(pMsg.ctacPsnPk)) {
            ctacPsnPk = pMsg.ctacPsnPk.getValue();
        }
        // END 2021/02/02 A.Marte [QC#58163, MOD]

        for (int i = 0; i < pMsg.ContactPointInfoList.getValidCount(); i++) {
            contactInfoBean = contactInfoMap.get(String.valueOf(i));
            if (contactInfoBean == null) {
                contactInfoBean = new ContactInfoBean();
            }
            NMZC002001_ContactPointInfoListPMsg linePrm = pMsg.ContactPointInfoList.no(i);
            DS_CTAC_PNTTMsg dsCtacPntTmsg = new DS_CTAC_PNTTMsg();
            if (linePrm.xxModeCd.getValue().equals(PROCESS_MODE_CTAC_CRAT)) {

                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntPk, getDsCtacPntPk());
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.ctacPsnPk, ctacPsnPk);
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntTpCd, linePrm.dsCtacPntTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntValTxt, linePrm.dsCtacPntValTxt.getValue());
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPsnExtnNum, linePrm.dsCtacPsnExtnNum.getValue());
                if (ZYPCommonFunc.hasValue(linePrm.dsOpsOutFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsOpsOutFlg, linePrm.dsOpsOutFlg.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPCommonFunc.hasValue(linePrm.dsCtacPntActvFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntActvFlg, linePrm.dsCtacPntActvFlg.getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                }
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.ctacPntFmtTxt, getCtacPntFmtTxt(linePrm.dsCtacPntTpCd.getValue(), glblCmpyCd)); // 2017/08/04 S21_NA#8598 Add

                S21ApiTBLAccessor.create(dsCtacPntTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCtacPntTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0230E);
                    return false;
                }

            } else if (linePrm.xxModeCd.getValue().equals(PROCESS_MODE_CTAC_UPD)) {
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntPk, linePrm.dsCtacPntPk.getValue());

                dsCtacPntTmsg = (DS_CTAC_PNTTMsg) EZDTBLAccessor.findByKeyForUpdate(dsCtacPntTmsg);
                if (dsCtacPntTmsg == null) {
                    msgMap.addXxMsgId(NMZM0309E);
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntTpCd, linePrm.dsCtacPntTpCd.getValue());
                ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntValTxt, linePrm.dsCtacPntValTxt.getValue());
                // 2019/01/16 QC#29642 Mod Start
                //ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPsnExtnNum, linePrm.dsCtacPsnExtnNum.getValue());
                if (ZYPConstant.FLG_ON_Y.equals(linePrm.updCtrlFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPsnExtnNum, linePrm.dsCtacPsnExtnNum.getValue());
                }
                // 2019/01/16 QC#29642 Mod End
                if (ZYPCommonFunc.hasValue(linePrm.dsOpsOutFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsOpsOutFlg, linePrm.dsOpsOutFlg.getValue());
                }
                if (ZYPCommonFunc.hasValue(linePrm.dsCtacPntActvFlg.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPntTmsg.dsCtacPntActvFlg, linePrm.dsCtacPntActvFlg.getValue());
                }
                S21ApiTBLAccessor.update(dsCtacPntTmsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCtacPntTmsg.getReturnCode())) {
                    msgMap.addXxMsgId(NMZM0255E);
                    return false;
                }
            }

            contactInfoBean.setDsCtacPntPk(dsCtacPntTmsg.dsCtacPntPk.getValue());
            contactInfoBean.setDsCtacPntActvFlg(dsCtacPntTmsg.dsCtacPntActvFlg.getValue());
            contactInfoBean.setDsCtacPntTpCd(dsCtacPntTmsg.dsCtacPntTpCd.getValue());
            contactInfoMap.put(String.valueOf(i), contactInfoBean);
        }

        return true;
    }
    /**
     * This method will return DS_CTAC_PSN_RELN_SQ for DS_CTAC_PSN_RELN_PK
     * @return BigDecimal
     */
    private BigDecimal getDsCtacPsnRelnPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CTAC_PSN_RELN_SQ);

    }
    /**
     * This method will return CTAC_PSN_PK
     * @param param NMZC002001PMsg
     * @return BigDecimal
     */
    private BigDecimal getCtacPsnPk(NMZC002001PMsg param) {
        // 2017/08/07 S21_NA#8598 Mod Start
//        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CTAC_PSN_SQ);
        List< ? > ctacPsnPkList = getDsCtacPsnPkListByName(param);

        int ctacPsnPkSize = ctacPsnPkList.size();
        if (ctacPsnPkSize == 0) {
            return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CTAC_PSN_SQ);
        }
        return (BigDecimal) ctacPsnPkList.get(0);
        // 2017/08/07 S21_NA#8598 Mod End

    }
    /**
     * This method will return DS_CTAC_PNT_SQ for DS_CTAC_PNT_PK
     * @return BigDecimal
     */
    private BigDecimal getDsCtacPntPk() {
        return ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CTAC_PNT_SQ);

    }
    /**
     * Update Contact Primary Location Flag
     * @param msgMap S21ApiMessageMap
     * @param pMsg NMZC001001PMsg
     * @param glblCmpyCd String
     * @param primFlg String
     * @return Results of the check.(false:error)
     */
    private boolean updateOtherCtacFlg(S21ApiMessageMap msgMap, BigDecimal dsCtacPsnRelnPk, String glblCmpyCd, String primFlg) {

        DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTmsg = new DS_CTAC_PSN_RELNTMsg();

        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsCtacPsnRelnPk, dsCtacPsnRelnPk);
        dsCtacPsnRelnTmsg = (DS_CTAC_PSN_RELNTMsg) EZDTBLAccessor.findByKeyForUpdate(dsCtacPsnRelnTmsg);
        if (dsCtacPsnRelnTmsg == null) {
            msgMap.addXxMsgId(NMZM0307E);
            return false;
        }

        ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTmsg.dsPrimLocFlg, primFlg);
        S21ApiTBLAccessor.update(dsCtacPsnRelnTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsCtacPsnRelnTmsg.getReturnCode())) {
            msgMap.addXxMsgId(NMZM0253E);
            return false;
        }
        return true;
    }
    /**
     * <pre>
     * Set the output parameters.
     * </pre>
     * @param msgMap S21ApiMessageMap
     */
    private void setOutputParameter(S21ApiMessageMap msgMap) {

        NMZC002001PMsg param = (NMZC002001PMsg) msgMap.getPmsg();
        ContactInfoBean contactInfoBean = contactInfoMap.get(String.valueOf(0));
        BigDecimal ctacPsnPk = contactInfoBean.getCtacPsnPk();

        ZYPEZDItemValueSetter.setValue(param.ctacPsnPk, ctacPsnPk);
        for (int i = 0; i < param.ContactPointInfoList.getValidCount(); i++) {
            contactInfoBean = contactInfoMap.get(String.valueOf(i));
            NMZC002001_ContactPointInfoListPMsg linePrm = param.ContactPointInfoList.no(i);
            ZYPEZDItemValueSetter.setValue(linePrm.dsCtacPntPk, contactInfoBean.getDsCtacPntPk());
        }
    }
    // 2017/08/04 S21_NA#8598 Add Start
    /**
     * chkMandatoryCtacPnt
     * 
     * @param msgMap S21ApiMessageMap
     * @param param NMZC002001PMsg
     * @return boolean
     */
    private boolean chkMandatoryCtacPnt(S21ApiMessageMap msgMap, NMZC002001PMsg param) {

        boolean rtnFlg = true;
        // Del Start 2018/02/02 QC#23904
        //if (!ZYPCommonFunc.hasValue(param.ctacTpCd)) {
        //    return true;
        //}
        // Del End 2018/02/02 QC#23904

        String dsCtacPntTpCd = "";
        boolean mandatoryChkFlg = false;
        Map< ? , ? > mandatoryCtacPntMap;
        List< ? > mandatoryCtacPntList = getMandatoryCtacPnt(param);
        for (int i = 0; i < mandatoryCtacPntList.size(); i++) {
            mandatoryCtacPntMap = (Map< ? , ? >) mandatoryCtacPntList.get(i);
            dsCtacPntTpCd = (String) mandatoryCtacPntMap.get("DS_CTAC_PNT_TP_CD");
            mandatoryChkFlg = false;
            for (int j = 0; j < param.ContactPointInfoList.getValidCount(); j++) {
                if (S21StringUtil.isEquals(dsCtacPntTpCd, param.ContactPointInfoList.no(j).dsCtacPntTpCd.getValue())) {
                    mandatoryChkFlg = true;
                    break;
                }
            }
            if (!mandatoryChkFlg) {
                if (DS_CTAC_PNT_TP.PHONE_WORK.equals(dsCtacPntTpCd)) {
                    msgMap.addXxMsgId(NMZM0347E);
                    rtnFlg = false; // QC#21634
                }
                if (DS_CTAC_PNT_TP.EMAIL_WORK.equals(dsCtacPntTpCd)) {
                    msgMap.addXxMsgId(NMZM0348E);
                    rtnFlg = false; // QC#21634
                }
                if (DS_CTAC_PNT_TP.FAX_WORK.equals(dsCtacPntTpCd)) {
                    msgMap.addXxMsgId(NMZM0349E);
                    rtnFlg = false; // QC#21634
                }
            }
        }
        return rtnFlg;
    }
    /**
     * getDsCtacPntPkList
     * @param param NMZC002001PMsg
     * @return List< ? >
     */
    private List< ? > getMandatoryCtacPnt(NMZC002001PMsg param) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("ctacTpCd", param.ctacTpCd.getValue());

        return (List< ? >) this.ssmBatchClient.queryObjectList("getMandatoryCtacPntList", ssmParam);
    }
    /**
     * getDsCtacPntPkList
     * @param param NMZC002001PMsg
     * @return List< ? >
     */
    private List< ? > getDsCtacPsnRelnList(NMZC002001PMsg param) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", param.glblCmpyCd.getValue());
        ssmParam.put("ctacPsnPk", param.ctacPsnPk.getValue());
        if (ZYPCommonFunc.hasValue(param.locNum)) {
            ssmParam.put("locNum", param.locNum.getValue());
        } else {
            ssmParam.put("dsAcctNum", param.dsAcctNum.getValue());
        }

        return (List< ? >) this.ssmBatchClient.queryObjectList("getDsCtacPsnRelnList", ssmParam);
    }
    /**
     * chkAutoFmtFlg
     * @param dsCtacPntTpCd String
     * @param glblCmpyCd String
     * @return String
     */
    private String getCtacPntFmtTxt(String dsCtacPntTpCd, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsCtacPntTpCd", dsCtacPntTpCd);

        Integer resCnt = (Integer) this.ssmBatchClient.queryObject("cntAutoFmtFlg", param);
        if (resCnt < 1) {
            return "";
        }
        return CTAC_PNT_FMT_US;
    }
    // 2017/08/04 S21_NA#8598 Add End

    // START 2018/06/18 K.Kitachi [QC#18591, ADD]
    /**
     * isProcessModeGetCtacPsnPk
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean isProcessModeGetCtacPsnPk(S21ApiMessageMap msgMap) {
        NMZC002001PMsg param = (NMZC002001PMsg) msgMap.getPmsg();
        if (PROCESS_MODE_GET_CTAC_PSN_PK.equals(param.xxModeCd.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * setCtacPsnPk
     * @param msgMap S21ApiMessageMap
     */
    private void setCtacPsnPk(S21ApiMessageMap msgMap) {
        NMZC002001PMsg param = (NMZC002001PMsg) msgMap.getPmsg();
        List<?> ctacPsnPkList = getDsCtacPsnPkListByName(param);
        int ctacPsnPkSize = ctacPsnPkList.size();
        if (ctacPsnPkSize >= 1) {
            ZYPEZDItemValueSetter.setValue(param.ctacPsnPk, (BigDecimal) ctacPsnPkList.get(0));
        }
    }
    // END 2018/06/18 K.Kitachi [QC#18591, ADD]
}
