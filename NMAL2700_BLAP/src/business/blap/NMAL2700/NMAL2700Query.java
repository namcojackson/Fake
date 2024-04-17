/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2700;

import static business.blap.NMAL2700.constant.NMAL2700Constant.ACTV_FLAG;
import static business.blap.NMAL2700.constant.NMAL2700Constant.BIZ_AREA_ORG_NM;
import static business.blap.NMAL2700.constant.NMAL2700Constant.CHK_A;
import static business.blap.NMAL2700.constant.NMAL2700Constant.CMSN_FLAG;
import static business.blap.NMAL2700.constant.NMAL2700Constant.EQUIP_FLG;
import static business.blap.NMAL2700.constant.NMAL2700Constant.GLOBAL_CMPY_CODE;
import static business.blap.NMAL2700.constant.NMAL2700Constant.LIST_SIZE_200;
import static business.blap.NMAL2700.constant.NMAL2700Constant.MGR_FLAG;
import static business.blap.NMAL2700.constant.NMAL2700Constant.NAME;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ORG_FUNC_ROLE_TP_CD;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ORG_FUNC_ROLE_TP_DESC;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ORG_FUNC_ROLE_TP_NM;
import static business.blap.NMAL2700.constant.NMAL2700Constant.RGTN_STS_CD;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ROLE_LIST;
import static business.blap.NMAL2700.constant.NMAL2700Constant.ROW_NUM;
import static business.blap.NMAL2700.constant.NMAL2700Constant.SPCL_FLG;
import static business.blap.NMAL2700.constant.NMAL2700Constant.TRTY_STRU_FLG;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL2700Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/08   Fujitsu         M.Suzuki        Update          S21_NA#4304
 * 2016/03/16   Fujitsu         M.Suzuki        Update          S21_NA#4305
 *</pre>
 */
public final class NMAL2700Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2700Query MY_INSTANCE = new NMAL2700Query();

    /**
     * Private constructor
     */
    private NMAL2700Query() {
        super();
    }

    /**
     * createSearcRoleMnt
     * @param bizMsg bizMsg
     * @return Map<String, Object>
     */
    public Map<String, Object> createSearcRoleMnt(NMAL2700CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        setParam(bizMsg, ssmParam);

        return ssmParam;
    }

    /**
     * Get the NMAL2700Query instance.
     * @return NMAL2700Query instance
     */
    public static NMAL2700Query getInstance() {
        return MY_INSTANCE;
    }
    //2016/03/08 S21_NA#4304 Mod Start --------------
    /**
     * Search
     * @param bizMsg NMAL2700CMsg
     * @param glblMsg NMAL2700SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL2700CMsg bizMsg, NMAL2700SMsg glblMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      setParam(bizMsg, params);
      params.put(ROW_NUM, glblMsg.A.length() + 1);
      return getSsmEZDClient().queryObjectList("getRoleMntList", params);
    }
    //2016/03/08 S21_NA#4304 Mod Start --------------

    private void setParam(NMAL2700CMsg bizMsg, Map<String, Object> params) {
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
          params.put(BIZ_AREA_ORG_NM, bizMsg.firstOrgCd.getValue());
          params.put(ORG_FUNC_ROLE_TP_CD, bizMsg.orgFuncRoleTpCd.getValue());
          params.put(ORG_FUNC_ROLE_TP_NM, bizMsg.orgFuncRoleTpNm.getValue());
          params.put(ORG_FUNC_ROLE_TP_DESC, bizMsg.orgFuncRoleTpDescTxt.getValue());

          if (ZYPCommonFunc.hasValue(bizMsg.mgrFlg)) {
              params.put(MGR_FLAG, bizMsg.mgrFlg.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.spclFlg)) {
              params.put(SPCL_FLG, bizMsg.spclFlg.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.equipFlg)) {
              params.put(EQUIP_FLG, bizMsg.equipFlg.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.cmsnFlg)) {
              params.put(CMSN_FLAG, bizMsg.cmsnFlg.getValue());
          }
          if (ZYPCommonFunc.hasValue(bizMsg.actvFlg)) {
              params.put(ACTV_FLAG, bizMsg.actvFlg.getValue());
          }
    }

    /**
     * Search
     * @param bizMsg NMAL2700CMsg
     * @param isUpdate boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRoleRequest(NMAL2700CMsg bizMsg, String orgFuncTp) {
      Map<String, Object> params = new HashMap<String, Object>();

      params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
      ArrayList<String> orgFuncList = new ArrayList<String>(LIST_SIZE_200);

      if (ZYPCommonFunc.hasValue(orgFuncTp)) {
          orgFuncList.add(orgFuncTp);
      } else {
          List<Integer> selectRows = ZYPTableUtil.getSelectedRows(bizMsg.A, CHK_A, FLG_ON_Y);
          for (int idx : selectRows) {
              orgFuncList.add(bizMsg.A.no(idx).orgFuncRoleTpCd_A.getValue());
          }
      }
      params.put(ROLE_LIST, orgFuncList);
      // 2016/03/16 S21_NA#4305 Add Start --------------
      params.put(RGTN_STS_CD, RGTN_STS.TERMINATED);
      // 2016/03/16 S21_NA#4305 Add Start --------------
      return getSsmEZDClient().queryObjectList("getRoleRequest", params);
    }

    /**
     * roleCodeDuplicatedCheck
     * @param bizMsg NMAL2700CMsg
     * @param roleList ArrayList<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult roleCodeDuplicatedCheck(NMAL2700CMsg bizMsg, ArrayList<String> roleList) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
      params.put(ROLE_LIST, roleList);
      return getSsmEZDClient().queryObjectList("roleCodeDuplicatedCheck", params);
    }

    /**
     * roleNameDuplicatedCheck
     * @param bizMsg NMAL2700CMsg
     * @param roleList ArrayList<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult roleNameDuplicatedCheck(NMAL2700CMsg bizMsg, ArrayList<String> roleList) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
      params.put(ROLE_LIST, roleList);
      return getSsmEZDClient().queryObjectList("roleNameDuplicatedCheck", params);
    }

    /**
     * checkSFDCProfileName
     * @param name String
     * @return Integer
     */
    public Integer checkSFDCProfileName(String sfdcPrflNm) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put(NAME, sfdcPrflNm);
      return (Integer)getSsmEZDClient().queryObject("checkSfdcProfileName", params).getResultObject();
    }

    /**
     * @param bizMsg NMAL2700CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBizAreaOrgPulldownList() {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        ssmParam.put(TRTY_STRU_FLG, ZYPConstant.FLG_OFF_N);
        return getSsmEZDClient().queryObjectList("getBizAreaOrgPulldownList", ssmParam);
    }
}
