package com.canon.cusa.s21.common.NYX.NYXC889010.test;

import java.math.BigDecimal;

import com.canon.cusa.s21.common.NYX.NYXC889010.test.S21NwfUtilTokenObjExt;
import com.canon.cusa.s21.common.NYX.NYXC889010.test.S21NwfUtilTokenObjExtLine;

import com.canon.cusa.s21.common.NYX.NYXC889010.NYEL8890TokenBizFactory;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 * Token Biz Object生成Facotryサンプル
 * @author q09079
 */
public class NYEL8899TokenBizTestFactory implements NYEL8890TokenBizFactory {

    /**
     * Token Biz Object生成
     */
    public S21NwfUtilTokenObj crateTokenBizObject(String pattern) {
        S21NwfUtilTokenObj ret = null;

        // パターン名でインスタンス生成処理を分岐
        if (pattern.equals("pattern1")) {
            ret = createPattern1();
        } else if (pattern.equals("pattern2")) {
            ret = createPattern2();
        } else {
            throw new S21AbendException("Illegal pattern[" + pattern + "]");
        }

        return ret;
    }

    /**
     * patten1 lineデータなし
     * @return
     */
    private S21NwfUtilTokenObj createPattern1() {
        S21NwfUtilTokenObjExt tokenObj = new S21NwfUtilTokenObjExt();

        tokenObj.setAttribute1("testAttr1");
        tokenObj.setAttribute1Lbl("testAttrLbl1");

        return tokenObj;
    }

    /**
     * pattern2 複数lineデータあり
     * @return
     */
    private S21NwfUtilTokenObj createPattern2() {
        S21NwfUtilTokenObjExt tokenObj = new S21NwfUtilTokenObjExt();

        tokenObj.setAttribute1("testAttr1");
        tokenObj.setAttribute1Lbl("testAttrLbl1");

        S21NwfUtilTokenObjExtLine line = new S21NwfUtilTokenObjExtLine();
        line.setTestBigDecimalAttribute(new BigDecimal(999.01));
        line.setTestIntAttribute(12345);
        line.setTestStringAttribute("testStrAttribute1");

        tokenObj.addLineData(line);

        line = new S21NwfUtilTokenObjExtLine();
        line.setTestBigDecimalAttribute(new BigDecimal(521));
        line.setTestIntAttribute(8909);
        line.setTestStringAttribute("testStrAttribute2");

        tokenObj.addLineData(line);

        return tokenObj;
    }
}
