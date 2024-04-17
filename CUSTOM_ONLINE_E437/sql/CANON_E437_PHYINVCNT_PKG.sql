-- START 2023/8/21 G.Quan [QC#61208, ADD]
SET DEFINE OFF
-- END 2023/8/21 G.Quan [QC#61208, ADD]
CREATE OR REPLACE PACKAGE CANON_E437_PHYINVCNT_PKG
AS
/**
 * Tech PI for Handy Terminal Scanner
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/17   CITS            T.Kikuhara      Create          QC#10572
 *
 */
-------------------------------------------------------------------------
-- Ref Cursor
TYPE g_ref_cur_type IS REF CURSOR;
-------------------------------------------------------------------------
-- Global Error Variables
g_process_name   VARCHAR2 (4000) := NULL;
g_key1           VARCHAR2 (4000) := NULL;
g_key2           VARCHAR2 (4000) := NULL;
g_key3           VARCHAR2 (4000) := NULL;
g_key4           VARCHAR2 (4000) := NULL;
g_key5           VARCHAR2 (4000) := NULL;
g_error_msg      VARCHAR2 (4000) := NULL;
-------------------------------------------------------------------------
-- Procedure To Return/Validate Technician Details
PROCEDURE gettechdtls (
    p_user_id     IN       VARCHAR2
   ,p_tech_id     IN       VARCHAR2
   ,p_mode        IN       VARCHAR2
   ,p_form_mode   IN       VARCHAR2
   ,x_tech_rec    OUT      g_ref_cur_type
   ,x_ret_code    OUT      VARCHAR2
   ,x_ret_msg     OUT      VARCHAR2
);
-------------------------------------------------------------------------
-- Procedure To Return/Validate Warehouse Physical Inventory Details
PROCEDURE getwhphyinvdtls (
    p_whouse_id    IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_subinv       IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_emp_id       OUT      NUMBER
   ,x_emp_user     OUT      VARCHAR2
   ,x_phy_inv_id   OUT      NUMBER
   ,x_phy_inv      OUT      VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
); 
-------------------------------------------------------------------------
-- Procedure To Return/Validate Technician Physical Inventory Details
PROCEDURE gettechphyinvdtls (
    p_whouse_id    IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_tech         IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_emp_id       OUT      NUMBER
   ,x_emp_user     OUT      VARCHAR2
   ,x_phy_inv_id   OUT      NUMBER
   ,x_phy_inv      OUT      VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
);
-------------------------------------------------------------------------
-- Procedure To Get Scanned Data
PROCEDURE getscanneddata (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_emp_id       IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_item_rec     OUT      g_ref_cur_type
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
);
-------------------------------------------------------------------------
-- Procedure To Get Scanned Data Per Page
PROCEDURE getscanneddatabypage (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_emp_id       IN       VARCHAR2
   ,p_page_num     IN       VARCHAR2
   ,p_begin_item   IN       VARCHAR2
   ,p_end_item     IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_item_rec     OUT      g_ref_cur_type
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
);
-------------------------------------------------------------------------
-- Procedure To Return Pending Count
PROCEDURE checkpendingcounts (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_cnt_msg      OUT      VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
);
-------------------------------------------------------------------------
-- Procedure To Validate Scanned Item And Update Tag Quantity
PROCEDURE validateitem (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_emp_id       IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_item_num     IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_item_rec     OUT      g_ref_cur_type
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
);
-------------------------------------------------------------------------
-- Update Procedure To Update Manual Counts In Staging
PROCEDURE updatedbmanual (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_tag_id       IN       VARCHAR2
   ,p_tag_qty      IN       NUMBER
   ,p_emp_id       IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_subinv       IN       VARCHAR2
   ,p_locator_id   IN       VARCHAR2
   ,p_item_id      IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
);
-------------------------------------------------------------------------
-- Procedure To Assign Zero To Pending Tags
PROCEDURE assignzerocounts (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_emp_id       IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
);
-------------------------------------------------------------------------
-- Procedure To Print Report For Counted Parts
PROCEDURE printreport (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
);
-------------------------------------------------------------------------
-- Procedure To Reset Default JTF Login Responsibility
PROCEDURE resetuser (
    p_user_id      IN       VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
);
END CANON_E437_PHYINVCNT_PKG;
/
--------------------------------------------------------------------------
--------------------------------------------------------------------------
--------------------------------------------------------------------------
CREATE OR REPLACE PACKAGE BODY CANON_E437_PHYINVCNT_PKG
IS
/**
 * Tech PI for Handy Terminal Scanner
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/17   CITS            T.Kikuhara      Create          QC#10572
 * 2019/03/05   Fujitsu         T.Ogura         Update          QC#30572
 * 2019/03/15   Fujitsu         T.Ogura         Update          QC#30766
 * 2022/08/29   Hitachi         T.NEMA          Update          QC#60412
 * 2023/08/15   Hitachi         G.Quan          Update          QC#61208
 *
 */
-------------------------------------------------------------------------
-- This procedure inserts the error into canon_errors with corresponding global variables
PROCEDURE insert_error
IS
BEGIN
    INSERT INTO CANON_E437_ERRORS
                (program_name
                ,process_name
                ,key1
                ,key2
                ,key3
                ,key4
                ,key5
                ,error_msg
                ,error_date
                )
    VALUES      ('CANON_E437_PHYINVCNT_PKG'
                ,SUBSTR (g_process_name, 1, 200)
                ,SUBSTR (g_key1, 1, 80)
                ,SUBSTR (g_key2, 1, 80)
                ,SUBSTR (g_key3, 1, 80)
                ,SUBSTR (g_key4, 1, 80)
                ,SUBSTR (g_key5, 1, 80)
                ,SUBSTR (g_error_msg, 1, 2000)
                ,SYSDATE
                );
    COMMIT;
END insert_error;
-------------------------------------------------------------------------
-- Procedure To Return/Validate Technician Details
PROCEDURE gettechdtls (
    p_user_id     IN       VARCHAR2
   ,p_tech_id     IN       VARCHAR2
   ,p_mode        IN       VARCHAR2
   ,p_form_mode   IN       VARCHAR2
   ,x_tech_rec    OUT      g_ref_cur_type
   ,x_ret_code    OUT      VARCHAR2
   ,x_ret_msg     OUT      VARCHAR2
)
IS
    -- Local Variables
    l_tech_cnt NUMBER := 0;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('GETTECHDTLS');
    g_process_name    := 'GETTECHDTLS';
    g_key1            := 'p_user_id '   || p_user_id;
    g_key2            := 'p_tech_id '   || p_tech_id;
    g_key3            := 'p_mode '      || p_mode;
    g_key4            := 'p_form_mode ' || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
    --
    -- Get The Technicians Assigned To Logged In User
    -- START 2023/8/15 G.Quan [QC#61208, ADD]
    WITH HR_TECH_LIST AS(
       SELECT 1 AS LV, SP.PSN_CD, TRIM(SP.PSN_LAST_NM || ',' || SP.PSN_FIRST_NM || ' ' || SP.PSN_MID_NM) AS PSN_NM, SP.HR_SUPV_ID, SP.HR_SUPV_NM 
       FROM S21_PSN SP
       WHERE
            SP.GLBL_CMPY_CD  = 'ADB'
       AND SP.EZCANCELFLAG  = '0'
       AND SP.PSN_CD = p_user_id
       UNION ALL

       SELECT LEVEL AS LV, SP.PSN_CD, TRIM(SP.PSN_LAST_NM || ',' || SP.PSN_FIRST_NM || ' ' || SP.PSN_MID_NM) AS PSN_NM, SP.HR_SUPV_ID, SP.HR_SUPV_NM 
       FROM   S21_PSN SP
       WHERE
            SP.GLBL_CMPY_CD  = 'ADB'
        AND SP.EZCANCELFLAG  = '0'
        START WITH SP.HR_SUPV_ID  = p_user_id
       CONNECT BY PRIOR SP.PSN_CD = SP.HR_SUPV_ID
    )
    -- END 2023/8/15 G.Quan [QC#61208, ADD]
    SELECT
        COUNT(1)
    INTO
        l_tech_cnt
    FROM
        TECH_MSTR  TM
       ,S21_PSN    SP
       -- START 2023/8/15 G.Quan [QC#61208, DEL]
       --,s21_csa_apps.S21_PSN    SP_S
       -- END 2023/8/15 G.Quan [QC#61208, DEL]
    WHERE
        TM.GLBL_CMPY_CD                     = 'ADB'
    AND TM.TECH_TOC_CD                      = SP.PSN_CD
    AND TM.GLBL_CMPY_CD                     = SP.GLBL_CMPY_CD
--    AND (SP.HR_SUPV_ID                      = p_user_id
--         OR SP.PSN_CD                       = p_user_id)
    -- START 2023/8/15 G.Quan [QC#61208, DEL]
    --AND (SP_S.PSN_NUM                       = p_user_id
    --     OR SP.PSN_NUM                      = p_user_id)
    -- END 2023/8/15 G.Quan [QC#61208, DEL]
    -- START 2023/8/15 G.Quan [QC#61208, ADD]
    AND EXISTS (SELECT 1 FROM HR_TECH_LIST X WHERE X.PSN_CD = SP.PSN_CD) 
    -- END 2023/8/15 G.Quan [QC#61208, ADD]
    AND SP.EFF_FROM_DT                     <= TO_CHAR(SYSDATE, 'YYYYMMDD')
    AND NVL(SP.EFF_THRU_DT, '99991231')    >= TO_CHAR(SYSDATE, 'YYYYMMDD')
    AND TM.EZCANCELFLAG                     = '0'
    AND SP.EZCANCELFLAG                     = '0'
    -- START 2023/8/15 G.Quan [QC#61208, DEL]
    --AND SP_S.EZCANCELFLAG(+)                = '0'
    --AND SP.HR_SUPV_ID                       = SP_S.PSN_CD(+)
    --AND SP.GLBL_CMPY_CD                     = SP_S.GLBL_CMPY_CD(+)
    -- END 2023/8/15 G.Quan [QC#61208, DEL]
    ;
    -- If No Technicians Found, Return Error
    -- Else, Technician Details
    IF l_tech_cnt = 0 THEN
        x_ret_code := 'E';
        IF p_mode = 'S' THEN
            x_ret_msg := 'No Technicians Found';
        ELSE
            x_ret_msg := 'Invalid Technician';
        END IF;
        RETURN;
    END IF;
    --
    -- Return Technician Details
    OPEN x_tech_rec FOR
        -- START 2023/8/15 G.Quan [QC#61208, ADD]
        WITH HR_TECH_LIST AS(
           SELECT 1 AS LV, SP.PSN_CD, TRIM(SP.PSN_LAST_NM || ',' || SP.PSN_FIRST_NM || ' ' || SP.PSN_MID_NM) AS PSN_NM, SP.HR_SUPV_ID, SP.HR_SUPV_NM 
           FROM S21_PSN SP
           WHERE
                SP.GLBL_CMPY_CD  = 'ADB'
           AND SP.EZCANCELFLAG  = '0'
           AND SP.PSN_CD = p_user_id
           UNION ALL

           SELECT LEVEL AS LV, SP.PSN_CD, TRIM(SP.PSN_LAST_NM || ',' || SP.PSN_FIRST_NM || ' ' || SP.PSN_MID_NM) AS PSN_NM, SP.HR_SUPV_ID, SP.HR_SUPV_NM  
           FROM   S21_PSN SP
           WHERE
                SP.GLBL_CMPY_CD  = 'ADB'
            AND SP.EZCANCELFLAG  = '0'
            START WITH SP.HR_SUPV_ID  = p_user_id
           CONNECT BY PRIOR SP.PSN_CD = SP.HR_SUPV_ID
        )
        -- END 2023/8/15 G.Quan [QC#61208, ADD]
        SELECT DISTINCT
            NULL person_id
--           ,TM.TECH_TOC_CD
           ,CASE WHEN TM.TECH_TOC_CD = RW.RTL_WH_CD THEN TM.TECH_TOC_CD ELSE RW.RTL_WH_CD END AS TECH_TOC_CD
--           ,TM.TECH_NM
           -- START 2023/8/18 G.Quan [QC#61208, MOD]
           --,CASE WHEN TM.TECH_TOC_CD = RW.RTL_WH_CD THEN TM.TECH_NM ELSE RW.RTL_WH_NM END AS TECH_NM
           ,CASE WHEN TM.TECH_TOC_CD = RW.RTL_WH_CD THEN REPLACE(TM.TECH_NM, '&', '&amp;') ELSE REPLACE(RW.RTL_WH_NM, '&', '&amp;') END AS TECH_NM
           -- END 2023/8/18 G.Quan [QC#61208, MOD]
           ,NULL tech_org_id
        FROM
            TECH_MSTR  TM
           ,S21_PSN    SP
           ,TECH_LOC   TL
           -- START 2023/8/15 G.Quan [QC#61208, DEL]
           --,s21_csa_apps.S21_PSN    SP_S
           -- END 2023/8/15 G.Quan [QC#61208, DEL]
           ,S21_CSA_APPS.RTL_WH     RW
        WHERE
            TM.GLBL_CMPY_CD                     = 'ADB'
        AND TM.TECH_TOC_CD                      = SP.PSN_CD
        AND TM.GLBL_CMPY_CD                     = SP.GLBL_CMPY_CD
--        AND (SP.HR_SUPV_ID                      = p_user_id
--             OR SP.PSN_CD                       = p_user_id)
        -- START 2023/8/15 G.Quan [QC#61208, DEL]
        --AND (SP_S.PSN_NUM                       = p_user_id
        --     OR SP.PSN_NUM                      = p_user_id)
        -- END 2023/8/15 G.Quan [QC#61208, DEL]
        -- START 2023/8/15 G.Quan [QC#61208, ADD]
        AND EXISTS (SELECT 1 FROM HR_TECH_LIST X WHERE X.PSN_CD = SP.PSN_CD)
        -- END 2023/8/15 G.Quan [QC#61208, ADD]
        AND SP.EFF_FROM_DT                     <= TO_CHAR(SYSDATE, 'YYYYMMDD')
        AND NVL(SP.EFF_THRU_DT, '99991231')    >= TO_CHAR(SYSDATE, 'YYYYMMDD')
        AND TM.GLBL_CMPY_CD                     = TL.GLBL_CMPY_CD(+)
        AND TM.TECH_TOC_CD                      = TL.TECH_TOC_CD(+)
        AND TM.EZCANCELFLAG                     = '0'
        AND SP.EZCANCELFLAG                     = '0'
        AND TL.EZCANCELFLAG(+)                  = '0'
        -- START 2023/8/15 G.Quan [QC#61208, DEL]
        --AND SP_S.EZCANCELFLAG(+)                = '0'
        --AND SP.HR_SUPV_ID                       = SP_S.PSN_CD(+)
        --AND SP.GLBL_CMPY_CD                     = SP_S.GLBL_CMPY_CD(+)
        -- END 2023/8/15 G.Quan [QC#61208, DEL]
        AND TM.TECH_TOC_CD                      = RW.TECH_TOC_CD
        AND TM.GLBL_CMPY_CD                     = RW.GLBL_CMPY_CD
        AND RW.EZCANCELFLAG                     = '0'
        -- START 2023/9/19 K.Ochiai [QC#61208, ADD]
        AND RW.EFF_FROM_DT                     <= TO_CHAR(SYSDATE, 'YYYYMMDD')
        AND NVL(RW.EFF_THRU_DT, '99991231')    >= TO_CHAR(SYSDATE, 'YYYYMMDD')
        -- END 2023/9/19 K.Ochiai [QC#61208, ADD]
        ORDER BY TECH_TOC_CD
        ;
EXCEPTION
    WHEN OTHERS THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Error In GETTECHDTLS: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg := x_ret_msg;
        insert_error;
END gettechdtls;
-------------------------------------------------------------------------
-- Procedure To Return/Validate Warehouse Physical Inventory Details
PROCEDURE getwhphyinvdtls (
    p_whouse_id    IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_subinv       IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_emp_id       OUT      NUMBER
   ,x_emp_user     OUT      VARCHAR2
   ,x_phy_inv_id   OUT      NUMBER
   ,x_phy_inv      OUT      VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
)
IS
    -- Local Variables
    l_other_exception  EXCEPTION;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('GETWHPHYINVDTLS');
    g_process_name    := 'GETWHPHYINVDTLS';
    g_key1            := 'p_whouse_id ' || p_whouse_id;
    g_key2            := 'p_user_id '   || p_user_id;
    g_key3            := 'p_subinv '    || p_subinv;
    g_key4            := 'p_form_mode ' || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
    x_phy_inv_id      := NULL;
    x_phy_inv         := NULL;
    x_emp_id          := NULL;
    x_emp_user        := p_user_id;
    --
    BEGIN
        SELECT
            PIC.PHYS_INVTY_CTRL_PK
           ,RW.RTL_WH_CD || ' ' || RW.RTL_WH_NM
        INTO
            x_phy_inv_id
           ,x_phy_inv
        FROM
            PHYS_INVTY_CTRL  PIC
           ,RTL_WH           RW
        WHERE
            PIC.GLBL_CMPY_CD       = 'ADB'
        AND PIC.WH_CD              = p_subinv
        AND PIC.PHYS_INVTY_STS_CD  = '01' -- OPEN
        AND PIC.GLBL_CMPY_CD       = RW.GLBL_CMPY_CD
        AND PIC.RTL_WH_CD          = RW.RTL_WH_CD
        AND PIC.EZCANCELFLAG       = '0'
        AND RW.EZCANCELFLAG        = '0'
        AND ROWNUM                 = 1
        ;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            x_ret_msg := 'No Open Phy Inv For Subinv ' || p_subinv;
            RAISE l_other_exception;
        WHEN OTHERS THEN
            x_ret_msg := 'Error: ' || SQLERRM;
            RAISE l_other_exception;
    END;
EXCEPTION
    WHEN l_other_exception THEN
        x_ret_code  := 'E';
        g_error_msg := x_ret_msg;
        insert_error;
    WHEN OTHERS THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Error In GETWHPHYINVDTLS: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg := x_ret_msg;
        insert_error;
END getwhphyinvdtls;
-------------------------------------------------------------------------
-- Procedure To Return/Validate Technician Physical Inventory Details
PROCEDURE gettechphyinvdtls (
    p_whouse_id    IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_tech         IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_emp_id       OUT      NUMBER
   ,x_emp_user     OUT      VARCHAR2
   ,x_phy_inv_id   OUT      NUMBER
   ,x_phy_inv      OUT      VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
)
IS
    -- Local Variables
    l_other_exception  EXCEPTION;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('GETTECHPHYINVDTLS');
    g_process_name    := 'GETTECHPHYINVDTLS';
    g_key1            := 'p_whouse_id ' || p_whouse_id;
    g_key2            := 'p_user_id '   || p_user_id;
    g_key3            := 'p_tech '      || p_tech;
    g_key4            := 'p_form_mode ' || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
    x_phy_inv_id      := NULL;
    x_phy_inv         := NULL;
    x_emp_id          := NULL;
    x_emp_user        := p_user_id;
    --
    BEGIN
        SELECT
            PIC.PHYS_INVTY_CTRL_PK
           ,RW.RTL_WH_CD || ' ' || RW.RTL_WH_NM
        INTO
            x_phy_inv_id
           ,x_phy_inv
        FROM
            PHYS_INVTY_CTRL  PIC
           ,RTL_WH           RW
        WHERE
            PIC.GLBL_CMPY_CD       = 'ADB'
        AND PIC.WH_CD              LIKE p_tech || '%'
        AND PIC.PHYS_INVTY_STS_CD  = '01' -- OPEN
        AND PIC.GLBL_CMPY_CD       = RW.GLBL_CMPY_CD
        AND PIC.RTL_WH_CD          = RW.RTL_WH_CD
        AND PIC.EZCANCELFLAG       = '0'
        AND RW.EZCANCELFLAG        = '0'
        AND ROWNUM                 = 1
        ;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            x_ret_msg := 'No Open Phy Inv For Tech ' || p_tech;
            RAISE l_other_exception;
        WHEN OTHERS THEN
            x_ret_msg := 'Error: ' || SQLERRM;
            RAISE l_other_exception;
    END;
EXCEPTION
    WHEN l_other_exception THEN
        x_ret_code  := 'E';
        g_error_msg := x_ret_msg;
        insert_error;
    WHEN OTHERS THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Error In GETTECHPHYINVDTLS: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg := x_ret_msg;
        insert_error;
END gettechphyinvdtls;
-------------------------------------------------------------------------
-- Procedure To Get Scanned Data
PROCEDURE getscanneddata (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_emp_id       IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_item_rec     OUT      g_ref_cur_type
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
)
IS
    -- Local Variables
    l_cnt              NUMBER := 0;
    l_disp_rowcnt      NUMBER := 5;
    l_other_exception  EXCEPTION;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('GETSCANNEDDATA');
    g_process_name    := 'GETSCANNEDDATA';
    g_key1            := 'p_whouse_id '  || p_whouse_id;
    g_key2            := 'p_phy_inv_id ' || p_phy_inv_id;
    g_key3            := 'p_emp_id '     || p_emp_id;
    g_key4            := 'p_form_mode '  || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
    --
    SELECT
        COUNT(1)
    INTO
        l_cnt
    FROM
        PHYS_INVTY_CTRL                PIC
       ,PHYS_INVTY_CTRL                PIC2
       ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
    WHERE
        PIC.GLBL_CMPY_CD          = 'ADB'
    AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
    AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
    AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
    AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
    AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
    AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
    AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
    AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD
    AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK
    AND PIC.EZCANCELFLAG          = '0'
    AND PIC2.EZCANCELFLAG         = '0'
    AND PICD.EZCANCELFLAG         = '0'
    ;
    -- If No Items Scanned By Current User, Return Message
    -- Else, Return Scanned Item Details
    IF l_cnt = 0 THEN
        x_ret_msg := 'Scan Item To Start Counting';
        RAISE l_other_exception;
    END IF;
    --
    OPEN x_item_rec FOR
        SELECT * FROM (
            SELECT
                NULL whouse_id
               ,p_phy_inv_id
               ,NULL item_number
               ,PICD.MDSE_CD
               ,PICD.PHYS_INVTY_CNT_DTL_PK
               ,PICD.TAG_NO
               ,PICD.RTL_WH_CD || PICD.RTL_SWH_CD WH_CD
               ,NULL locator_id
               ,NULL locator_name
               ,PICD.CNT_INP_QTY
               ,PICD.RTL_WH_CD
               ,NULL
            FROM
                PHYS_INVTY_CTRL                PIC
               ,PHYS_INVTY_CTRL                PIC2
               ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
            WHERE
                PIC.GLBL_CMPY_CD          = 'ADB'
            AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
            AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
            AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
            AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
            AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
            AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
            AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
            AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD
            AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK
            AND PIC.EZCANCELFLAG          = '0'
            AND PIC2.EZCANCELFLAG         = '0'
            AND PICD.EZCANCELFLAG         = '0'
            ORDER BY
                PICD.EZUPTIME DESC)
        WHERE
            ROWNUM <= l_disp_rowcnt
        ;
EXCEPTION
    WHEN l_other_exception THEN
        x_ret_code  := 'E';
        g_error_msg := x_ret_msg;
        insert_error;
    WHEN OTHERS THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Error In GETSCANNEDDATA: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg := x_ret_msg;
        insert_error;
END getscanneddata;
-------------------------------------------------------------------------
-- Procedure To Display Scanned Data Per Page
PROCEDURE getscanneddatabypage (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_emp_id       IN       VARCHAR2
   ,p_page_num     IN       VARCHAR2
   ,p_begin_item   IN       VARCHAR2
   ,p_end_item     IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_item_rec     OUT      g_ref_cur_type
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
)
IS
    -- Local Variables
    l_cnt              NUMBER  := 0;
    l_cnt2             NUMBER  := 0;
    l_disp_rowcnt      NUMBER  := 5;
    l_other_exception  EXCEPTION;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('GETSCANNEDDATABYPAGE');
    g_process_name    := 'GETSCANNEDDATABYPAGE';
    g_key1            := 'p_whouse_id '  || p_whouse_id;
    g_key2            := 'p_phy_inv_id ' || p_phy_inv_id;
    g_key3            := 'p_emp_id '     || p_emp_id;
    g_key4            := 'p_page_num '   || p_page_num;
    g_key5            := 'p_form_mode '  || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
    --
    SELECT
        COUNT (1)
    INTO
        l_cnt
    FROM
        PHYS_INVTY_CTRL      PIC
    WHERE
        PIC.GLBL_CMPY_CD          = 'ADB'
    AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
    AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
    ;
    --
    SELECT
        COUNT(1)
    INTO
        l_cnt2
    FROM
       (SELECT
            PICD.PHYS_INVTY_CNT_DTL_PK
           ,ROW_NUMBER () OVER (ORDER BY PIC.WH_CD ,PICD.MDSE_CD) RN
        FROM
            PHYS_INVTY_CTRL                PIC
           ,PHYS_INVTY_CTRL                PIC2
           ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
        WHERE
            PIC.GLBL_CMPY_CD          = 'ADB'
        AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
        AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
        AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
        AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
        AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
        AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
        AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
        AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD
        AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK
        AND UPPER(PICD.MDSE_CD)      >= NVL(p_begin_item, UPPER(PICD.MDSE_CD))
        AND UPPER(PICD.MDSE_CD)      <= NVL(p_end_item, UPPER(PICD.MDSE_CD))
        AND PIC.EZCANCELFLAG          = '0'
        AND PIC2.EZCANCELFLAG         = '0'
        AND PICD.EZCANCELFLAG         = '0')
    WHERE
        RN BETWEEN ((l_disp_rowcnt * (p_page_num - 1)) + 1) AND (l_disp_rowcnt * (p_page_num))
    ;
    --
    -- If No Items Scanned By Current User and set Page, Return Message
    -- Else, Return Scanned Item Details By Page
    IF l_cnt2 = 0 THEN
        IF l_cnt > 0 THEN
            x_ret_msg := 'End Of Records';
        ELSE
            x_ret_msg := 'No Records Found';
        END IF;
        RAISE l_other_exception;
    END IF;
    --
    -- Return Scanned Item Details By Page
    OPEN x_item_rec FOR
        SELECT
            NULL whouse_id
           ,p_phy_inv_id
           ,NULL item_number
           ,MDSE_CD
           ,PHYS_INVTY_CNT_DTL_PK
           ,TAG_NO
           ,WH_CD
           ,NULL locator_id
           ,NULL locator_name
           ,CNT_INP_QTY
           ,RTL_WH_CD
           ,NULL
        FROM
           (SELECT
                PICD.MDSE_CD
               ,PICD.PHYS_INVTY_CNT_DTL_PK
               ,PICD.TAG_NO
               ,PICD.RTL_WH_CD || PICD.RTL_SWH_CD WH_CD
               ,PICD.CNT_INP_QTY
               ,PICD.RTL_WH_CD
               ,ROW_NUMBER () OVER (ORDER BY PIC.WH_CD ,PICD.MDSE_CD) RN
            FROM
                PHYS_INVTY_CTRL                PIC
               ,PHYS_INVTY_CTRL                PIC2
               ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
            WHERE
                PIC.GLBL_CMPY_CD          = 'ADB'
            AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
            AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
            AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
            AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
            AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
            AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
            AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
            AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD
            AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK
            AND UPPER(PICD.MDSE_CD)      >= NVL(p_begin_item, UPPER(PICD.MDSE_CD))
            AND UPPER(PICD.MDSE_CD)      <= NVL(p_end_item, UPPER(PICD.MDSE_CD))
            AND PIC.EZCANCELFLAG          = '0'
            AND PIC2.EZCANCELFLAG         = '0'
            AND PICD.EZCANCELFLAG         = '0')
        WHERE
            RN BETWEEN ((l_disp_rowcnt * (p_page_num - 1)) + 1) AND (l_disp_rowcnt * (p_page_num))
        ORDER BY
            RN ASC
        ;
EXCEPTION
    WHEN l_other_exception THEN
        x_ret_code  := 'E';
        g_error_msg := x_ret_msg;
        insert_error;
    WHEN OTHERS THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Error In GETSCANNEDDATABYPAGE: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg := x_ret_msg;
        insert_error;
END getscanneddatabypage;
-------------------------------------------------------------------------
-- Procedure To Return Pending Count
PROCEDURE checkpendingcounts (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_cnt_msg      OUT      VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
)
IS
    -- Local Variables
    l_cnt           NUMBER;
    l_pend_cnt      NUMBER;
    --
    CURSOR pend_cur
    IS
        SELECT
            INV_CNT.WH_CD              WH_CD
           ,INV_CNT.CNT - PICD_CNT.CNT CNT
        FROM
           (SELECT
                PIC2.WH_CD           WH_CD
               ,COUNT(INV.MDSE_CD)   CNT
            FROM
                PHYS_INVTY_CTRL      PIC
               ,PHYS_INVTY_CTRL      PIC2
               ,INVTY                INV
            WHERE
                PIC.GLBL_CMPY_CD          = 'ADB'
            AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
            AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
            AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
            AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
            AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
            AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
            AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
            AND PIC2.GLBL_CMPY_CD         = INV.GLBL_CMPY_CD
            AND PIC2.WH_CD                = INV.INVTY_LOC_CD
-- START 2019/03/05 T.Ogura [QC#30572,ADD]
            AND INV.LOC_STS_CD            = '03' -- DC Stock
-- END   2019/03/05 T.Ogura [QC#30572,ADD]
            AND PIC.EZCANCELFLAG          = '0'
            AND PIC2.EZCANCELFLAG         = '0'
            AND INV.EZCANCELFLAG          = '0'
            AND INV.INVTY_QTY             > 0
            GROUP BY
                PIC2.WH_CD) INV_CNT
          ,(SELECT
                PIC2.WH_CD           WH_CD
               ,COUNT(PICD.MDSE_CD)  CNT
            FROM
                PHYS_INVTY_CTRL                PIC
               ,PHYS_INVTY_CTRL                PIC2
               ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
            WHERE
                PIC.GLBL_CMPY_CD          = 'ADB'
            AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
            AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
            AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
            AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
            AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
            AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
            AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
            AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD(+)
            AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK(+)
            AND PIC.EZCANCELFLAG          = '0'
            AND PIC2.EZCANCELFLAG         = '0'
            AND PICD.EZCANCELFLAG(+)      = '0'
            GROUP BY
                PIC2.WH_CD) PICD_CNT
        WHERE
            INV_CNT.WH_CD = PICD_CNT.WH_CD
        AND INV_CNT.CNT > PICD_CNT.CNT
        ;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('CHECKPENDINGCOUNTS');
    g_process_name    := 'CHECKPENDINGCOUNTS';
    g_key1            := 'p_whouse_id '  || p_whouse_id;
    g_key2            := 'p_phy_inv_id ' || p_phy_inv_id;
    g_key3            := 'p_form_mode '  || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
    --
    SELECT
        COUNT (*)
    INTO
        l_cnt
    FROM
        PHYS_INVTY_CTRL PIC
    WHERE
        PIC.GLBL_CMPY_CD        = 'ADB'
    AND PIC.PHYS_INVTY_CTRL_PK  = p_phy_inv_id
    AND PIC.PHYS_INVTY_STS_CD   = '01' -- OPEN
    ;
    --
    IF l_cnt = 0 THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Unable To Find Phy Inv Records';
    ELSE
        SELECT
            SUM(CNT)
        INTO
            l_pend_cnt
        FROM 
           (SELECT
                INV_CNT.WH_CD               WH_CD
               ,INV_CNT.CNT - PICD_CNT.CNT  CNT
            FROM
               (SELECT
                    PIC2.WH_CD          WH_CD
                   ,COUNT(INV.MDSE_CD)  CNT
                FROM
                    PHYS_INVTY_CTRL      PIC
                   ,PHYS_INVTY_CTRL      PIC2
                   ,INVTY                INV
                WHERE
                    PIC.GLBL_CMPY_CD          = 'ADB'
                AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
                AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
                AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
                AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
                AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
                AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
                AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
                AND PIC2.GLBL_CMPY_CD         = INV.GLBL_CMPY_CD
                AND PIC2.WH_CD                = INV.INVTY_LOC_CD
                AND PIC.EZCANCELFLAG          = '0'
                AND PIC2.EZCANCELFLAG         = '0'
                AND INV.EZCANCELFLAG          = '0'
                AND INV.INVTY_QTY             > 0
                GROUP BY
                    PIC2.WH_CD) INV_CNT
              ,(SELECT
                    PIC2.WH_CD           WH_CD
                   ,COUNT(PICD.MDSE_CD)  CNT
                FROM
                    PHYS_INVTY_CTRL                PIC
                   ,PHYS_INVTY_CTRL                PIC2
                   ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
                WHERE
                    PIC.GLBL_CMPY_CD          = 'ADB'
                AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
                AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
                AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
                AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
                AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
                AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
                AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
                AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD(+)
                AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK(+)
                AND PIC.EZCANCELFLAG          = '0'
                AND PIC2.EZCANCELFLAG         = '0'
                AND PICD.EZCANCELFLAG(+)      = '0'
                GROUP BY
                    PIC2.WH_CD) PICD_CNT
           WHERE
               INV_CNT.WH_CD = PICD_CNT.WH_CD
           );
        --
        IF l_pend_cnt <= 0 THEN
            x_cnt_msg := 'Counting Compl For All Items In Phy Inv.';
        ELSE
            FOR rec IN pend_cur
            LOOP
                IF pend_cur%ROWCOUNT = 1 THEN
                    x_cnt_msg := 'Counting Pending For ' || rec.CNT || ' Item(s) In ' || rec.WH_CD || '.';
                ELSE
                    x_cnt_msg :=
                        x_cnt_msg || CHR (10) || 'Counting Pending For ' || rec.CNT || ' Item(s) In ' || rec.WH_CD
                        || '.';
                END IF;
            END LOOP;
        END IF;
        x_cnt_msg := x_cnt_msg || CHR (10) || 'Select OK To View All Scanned Items.';
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Error In CHECKPENDINGCOUNTS: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg := x_ret_msg;
        insert_error;
END checkpendingcounts;
-------------------------------------------------------------------------
-- Procedure To Validate Scanned Item And Update Tag Quantity
PROCEDURE validateitem (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_emp_id       IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_item_num     IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_item_rec     OUT      g_ref_cur_type
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
)
IS
    -- Local Variables
    --l_item_Name        MDSE.MDSE_NM%TYPE                                         := NULL;  --5/18
    l_item_Name        MDSE.MDSE_DESC_SHORT_TXT%TYPE                             := NULL;
    l_invLocCd         INVTY.INVTY_LOC_CD%TYPE                                   := NULL;
    l_piCntPk          PHYS_INVTY_CTRL.PHYS_INVTY_CTRL_PK%TYPE                   := NULL;
    l_piCntNm          PHYS_INVTY_CTRL.PHYS_INVTY_CTRL_NM%TYPE                   := NULL;
    l_piDt             PHYS_INVTY_CTRL.PHYS_INVTY_DT%TYPE                        := NULL;
    l_piWhCd           PHYS_INVTY_CTRL.WH_CD%TYPE                                := NULL;
    l_piRtlWhCd        PHYS_INVTY_CTRL.RTL_WH_CD%TYPE                            := NULL;
    l_piRtlSwhCd       PHYS_INVTY_CTRL.RTL_SWH_CD%TYPE                           := NULL;
    l_piCntHdrPk       CANON_E437_PHYS_INVTY_CNT_HDR.PHYS_INVTY_CNT_HDR_PK%TYPE  := NULL;
    l_piCntDtlPk       CANON_E437_PHYS_INVTY_CNT_DTL.PHYS_INVTY_CNT_DTL_PK%TYPE  := NULL;
    l_piTagNo          CANON_E437_PHYS_INVTY_CNT_DTL.TAG_NO%TYPE                 := NULL;
    l_techTocCd        PHYS_INVTY_CTRL.TECH_TOC_CD%TYPE                          := NULL; 
    l_cntQty           NUMBER                                                    := 0;
    l_cnt              NUMBER                                                    := 0;
    l_cnt2             NUMBER                                                    := 0;
    l_cnt3             NUMBER                                                    := 0;
    l_other_exception  EXCEPTION;
    --
    CURSOR another_pi_cur
    IS
        SELECT
            PIC2.PHYS_INVTY_CTRL_PK
           ,PIC2.PHYS_INVTY_CTRL_NM
           ,PIC2.PHYS_INVTY_DT
           ,PIC2.WH_CD
           ,PIC2.RTL_WH_CD
           ,PIC2.RTL_SWH_CD
        FROM
            PHYS_INVTY_CTRL      PIC
           ,PHYS_INVTY_CTRL      PIC2
        WHERE
            PIC.GLBL_CMPY_CD          = 'ADB'
        AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
        AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
        AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
        AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
        AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
        AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
        AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
        AND PIC.EZCANCELFLAG          = '0'
        AND PIC2.EZCANCELFLAG         = '0'
    ;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('VALIDATEITEM');
    g_process_name    := 'VALIDATEITEM';
    g_key1            := 'p_whouse_id '  || p_whouse_id;
    g_key2            := 'p_phy_inv_id ' || p_phy_inv_id;
    g_key3            := 'p_emp_id '     || p_emp_id || ' ,p_user_id ' || p_user_id;
    g_key4            := 'p_item_num '   || p_item_num;
    g_key5            := 'p_form_mode '  || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
    --
    -- Validate Scanned Item
    BEGIN
        SELECT
            --MDSE_NM  --5/18
            MDSE_DESC_SHORT_TXT
        INTO
            l_item_Name
        FROM
            MDSE
        WHERE
            GLBL_CMPY_CD    = 'ADB'
        AND MDSE_CD         = p_item_num
        AND EZCANCELFLAG    = '0'
        ;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            x_ret_msg := 'Item Not Found In Item Master';
            RAISE l_other_exception;
        WHEN OTHERS THEN
            x_ret_msg := 'Error: ' || SQLERRM;
            RAISE l_other_exception;
    END;
    --
    -- Ceck PI Count Detail already exist.
    SELECT
        COUNT(1)
    INTO
        l_cnt
    FROM
        PHYS_INVTY_CTRL                PIC
       ,PHYS_INVTY_CTRL                PIC2
       ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
    WHERE
        PIC.GLBL_CMPY_CD          = 'ADB'
    AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
    AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
    AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
    AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
    AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
    AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
    AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
    AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD  --del at 5/23
    --AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD(+) --add at 5/23
    AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK  --del at 5/23
    --AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK(+) --add at 5/23
    AND PICD.MDSE_CD           = p_item_num  --del at 5/23
    --AND PICD.MDSE_CD(+)           = p_item_num --add at 5/23
    AND PIC.EZCANCELFLAG          = '0'
    AND PIC2.EZCANCELFLAG         = '0'
    AND PICD.EZCANCELFLAG      = '0'  --del at 5/23
    --AND PICD.EZCANCELFLAG(+)      = '0' --add at 5/23
    ;
    IF l_cnt = 1 THEN
        -- If 1 record data is exist. Update Count detail.
        --2018/06/12 MOD START
        SELECT
            PHYS_INVTY_CNT_DTL_PK
           ,NVL(CNT_INP_QTY, 0)
        INTO
            l_piCntDtlPk
           ,l_cntQty
        FROM
            CANON_E437_PHYS_INVTY_CNT_DTL
        WHERE
            GLBL_CMPY_CD       = 'ADB'
        AND PHYS_INVTY_CTRL_PK = p_phy_inv_id
        AND MDSE_CD            = p_item_num
        AND EZCANCELFLAG       = '0'
        ;
       
       -- Ceck Inventory.
        SELECT
            COUNT(1)
        INTO
            l_cnt3
        FROM
            INVTY
        WHERE
            INVTY_LOC_CD LIKE p_user_id || '%'
        AND MDSE_CD         = p_item_num
-- START 2019/03/15 T.Ogura [QC#30766,ADD]
        AND LOC_STS_CD      = '03' -- DC Stock
        AND INVTY_QTY       > 0
-- END   2019/03/15 T.Ogura [QC#30766,ADD]
        AND EZCANCELFLAG    = '0'
        ;

        IF l_cnt3 = 1 THEN
            SELECT
                PHYS_INVTY_CNT_DTL_PK
               ,NVL(CNT_INP_QTY, 0)
            INTO
                l_piCntDtlPk
               ,l_cntQty
            FROM
                PHYS_INVTY_CTRL                PIC
               ,PHYS_INVTY_CTRL                PIC2
               ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
            WHERE
                PIC.GLBL_CMPY_CD          = 'ADB'
            AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
            AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
            AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
            AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
            AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
            AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
            AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
            AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD
            AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK
            AND PICD.MDSE_CD              = p_item_num
            AND PIC.EZCANCELFLAG          = '0'
            AND PIC2.EZCANCELFLAG         = '0'
            AND PICD.EZCANCELFLAG         = '0'
            ;
            --20186/06/12 ADD END

            -- Update Count detail.
            updatedbmanual(
                           NULL
                          ,NULL
                          ,l_PiCntDtlPk
                          ,l_cntQty + 1
                          --,NULL --del at 5/23
                          ,p_emp_id --add at 5/23
                          ,p_user_id
                          ,NULL
                          ,NULL
                          ,p_item_num
                          ,p_form_mode
                          ,x_ret_code
                          ,x_ret_msg
                          );
            x_ret_msg  := l_item_Name; --5/18
            -----------------------------------------------------------------------------
        ELSIF l_cnt3 > 1 or l_cntQty >= 1 THEN  
            -- If Many record data is exist.(like K02053G, K02053R)
            -- Let User Select The Correct Combination.
            x_ret_code := 'M';
            x_ret_msg  := l_item_Name;
            OPEN x_item_rec FOR
                SELECT
                    NULL whouse_id
                   --,p_phy_inv_id  --del at 5/23
                   ,PIC2.PHYS_INVTY_CTRL_PK  --add at 5/23
                   ,NULL item_number
                   --,PICD.MDSE_CD --del at 5/23
                   ,p_item_num  --add at 5/23
                   ,PICD.PHYS_INVTY_CNT_DTL_PK
                   ,PICD.TAG_NO
                   --,PICD.RTL_WH_CD || PICD.RTL_SWH_CD WH_CD  --del at 5/23
                   ,PIC2.WH_CD  --add at 5/23
                   ,NULL locator_id
                   ,NULL locator_name
                   --,PICD.CNT_INP_QTY  --del at 5/23
                   ,NVL(PICD.CNT_INP_QTY, 0) CNT_INP_QTY  --add at 5/23
                   --,PICD.RTL_WH_CD  --del at 5/23
                   ,PIC2.RTL_WH_CD  --add at 5/23
                   ,NULL
                FROM
                    PHYS_INVTY_CTRL                PIC
                   ,PHYS_INVTY_CTRL                PIC2
                   ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
                   --START 2022/08/29 T.NEMA [QC#60412, ADD]
                   ,MDSE                           M
                   --END   2022/08/29 T.NEMA [QC#60412, ADD]
                WHERE
                    PIC.GLBL_CMPY_CD          = 'ADB'
                AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
                AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
                AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
                AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
                AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
                AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
                AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
                --AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD  --del at 5/23
                AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD(+)  --add at 5/23
                --AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK  --del at 5/23
                AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK(+)  --add at 5/23
                --AND PICD.MDSE_CD           = p_item_num  --del at 5/23
                AND PICD.MDSE_CD(+)           = p_item_num  --add at 5/23
                AND PIC.EZCANCELFLAG          = '0'
                AND PIC2.EZCANCELFLAG         = '0'
                --AND PICD.EZCANCELFLAG      = '0'  --del at 5/23
                AND PICD.EZCANCELFLAG(+)      = '0'  --add at 5/23
                --START 2022/08/29 T.NEMA [QC#60412, ADD]
                AND M.GLBL_CMPY_CD            = PIC.GLBL_CMPY_CD
                AND M.MDSE_CD                 = p_item_num
                AND (CASE WHEN PIC2.RTL_SWH_CD ='R' AND M.RTRN_CTRL_TP_CD IS NULL THEN 'false' ELSE 'true' END) = 'true'
                AND M.EZCANCELFLAG            = '0'
                --END   2022/08/29 T.NEMA [QC#60412, ADD]
                ORDER BY
                    --START 2022/08/29 T.NEMA [QC#60412, ADD]
                    PIC2.WH_CD ASC,
                    --END   2022/08/29 T.NEMA [QC#60412, ADD]
                    PICD.EZUPTIME DESC
                ;
                x_ret_msg  := l_item_Name; --5/18
        ELSE
            -- If Many record data is exist.(like K02053G, K02053R)
            -- Or No record found.
            -- Let User Select The Correct Combination.
            x_ret_code := 'M';
            x_ret_msg  := l_item_Name;
            --
            -- Get Count Sequence
            SELECT
                NVL(MAX(PHYS_INVTY_CNT_HDR_PK), 0) + 1
            INTO
                l_piCntHdrPk
            FROM
                CANON_E437_PHYS_INVTY_CNT_HDR
            ;
            SELECT
                NVL(MAX(PHYS_INVTY_CNT_DTL_PK), 0) + 1
            INTO
                l_piCntDtlPk
            FROM
                CANON_E437_PHYS_INVTY_CNT_DTL
            ;
            SELECT
                LPAD(NVL(MAX(TO_NUMBER(TAG_NO)), 0) + 1, 3, '0')
            INTO
                l_piTagNo
            FROM
                CANON_E437_PHYS_INVTY_CNT_DTL
            WHERE
                PHYS_INVTY_CTRL_PK = p_phy_inv_id
            ;
            --2018/12/12 add
            SELECT
                TECH_TOC_CD
            INTO
                l_techTocCd
            FROM
                PHYS_INVTY_CTRL
            WHERE
                PHYS_INVTY_CTRL_PK = p_phy_inv_id
            ;
            OPEN x_item_rec FOR
                SELECT
                    NULL whouse_id
                   ,PIC2.PHYS_INVTY_CTRL_PK
                   ,NULL item_number
                   ,p_item_num
                   ,l_piCntDtlPk
                   ,l_piTagNo
                   ,PIC2.WH_CD
                   ,NULL locator_id
                   ,NULL locator_name
                   ,1
                   ,PIC2.RTL_WH_CD
                   ,NULL
                FROM
                    PHYS_INVTY_CTRL      PIC
                   ,PHYS_INVTY_CTRL      PIC2
                WHERE
                    PIC.GLBL_CMPY_CD          = 'ADB'
                AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
                AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
                AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
                AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
                AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
                AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
                AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
                AND PIC.EZCANCELFLAG          = '0'
                AND PIC2.EZCANCELFLAG         = '0'
                ORDER BY
                    PIC2.PHYS_INVTY_CTRL_PK
                ;
            -- insert Count Header data
            -- but WH_CD and SWH_CD are null
            -- These column value is after user choose update.
            INSERT INTO CANON_E437_PHYS_INVTY_CNT_HDR
               (EZTABLEID
               ,EZCANCELFLAG
               ,EZINTIME
               ,EZINTIMEZONE
               ,EZINCOMPANYCODE
               ,EZINUSERID
               ,EZINAPLID
               ,EZUPTIME
               ,EZUPTIMEZONE
               ,EZUPCOMPANYCODE
               ,EZUPUSERID
               ,EZUPAPLID
               ,GLBL_CMPY_CD
               ,PHYS_INVTY_CNT_HDR_PK
               ,PHYS_INVTY_CTRL_PK
               ,PHYS_INVTY_CTRL_NM
               ,PHYS_INVTY_DT
               ,WH_CD
               ,RTL_WH_CD
               ,RTL_SWH_CD
               ,MDSE_CD
               ,STK_STS_CD
               ,TECH_LOC_TP_CD
               ,TECH_TOC_CD
               ,SYS_CNT_REGD_FLG
               ,FIRST_CNT_INP_QTY
               ,FIRST_CNT_INP_TS
               ,SCD_CNT_INP_QTY
               ,SCD_CNT_INP_TS
               ,LTST_CNT_INP_QTY
               ,LTST_CNT_INP_TS
               ,INVTY_AVAL_QTY
               ,INVTY_REGD_TS
               ,ADJ_VAR_FLG
               ,ADJ_VAR_QTY
               ,ADJ_VAR_AMT
               ,INVTY_ORD_NUM
               ,PHYS_INVTY_ADJ_NM)
            VALUES
               ('PHYS_INVTY_CNT_HDR'
               ,'0'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,'ADB'
               ,l_piCntHdrPk
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,p_item_num
               ,'1'
               ,NULL
               --,p_user_id --del at 6/11
               --,p_emp_id --add at 6/11
               ,l_techTocCd
               ,'N'
               ,0
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,'Y'
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               );
            --
            IF SQL%ROWCOUNT <> 0 THEN
                COMMIT;
            ELSE
                x_ret_msg := 'Quantity Could Not Be Updated';
                RAISE l_other_exception;
            END IF;
            --
            -- insert Count Detail data
            INSERT INTO CANON_E437_PHYS_INVTY_CNT_DTL
               (EZTABLEID
               ,EZCANCELFLAG
               ,EZINTIME
               ,EZINTIMEZONE
               ,EZINCOMPANYCODE
               ,EZINUSERID
               ,EZINAPLID
               ,EZUPTIME
               ,EZUPTIMEZONE
               ,EZUPCOMPANYCODE
               ,EZUPUSERID
               ,EZUPAPLID
               ,GLBL_CMPY_CD
               ,PHYS_INVTY_CNT_DTL_PK
               ,PHYS_INVTY_CNT_HDR_PK
               ,PHYS_INVTY_CTRL_PK
               ,RTL_WH_CD
               ,RTL_SWH_CD
               ,MDSE_CD
               ,SER_NUM
               ,CNT_INP_QTY
               ,FIRST_CNT_INP_QTY
               ,FIRST_CNT_INP_TS
               ,SCD_CNT_INP_QTY
               ,SCD_CNT_INP_TS
               ,LTST_CNT_INP_QTY
               ,LTST_CNT_INP_TS
               ,INVTY_AVAL_QTY
               ,INVTY_REGD_TS
               ,ADJ_VAR_FLG
               ,ADJ_VAR_QTY
               ,ADJ_VAR_AMT
               ,PHYS_INVTY_ADJ_STS_CD
               ,PHYS_INVTY_ADJ_MSG_TXT
               ,SVC_CONFIG_MSTR_PK
               ,TAG_NO)
            VALUES
               ('PHYS_INVTY_CNT_DTL'
               ,'0'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,'ADB'
               ,l_piCntDtlPk
               ,l_piCntHdrPk
               ,NULL
               ,NULL
               ,NULL
               ,p_item_num
               ,NULL
               ,1
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,'Y'
               ,NULL
               ,NULL
               ,'00'
               ,NULL
               ,NULL
               ,l_piTagNo
               );
            --
            IF SQL%ROWCOUNT <> 0 THEN
                COMMIT;
            ELSE
                x_ret_msg := 'Quantity Could Not Be Updated';
                RAISE l_other_exception;
            END IF;
        END IF;
        -------------------------------------------------------------------------------------
    --
    ELSIF l_cnt > 0 THEN
        -- If Many record data is exist.(like K02053G, K02053R)
        -- Let User Select The Correct Combination.
        x_ret_code := 'M';
        x_ret_msg  := l_item_Name;
        OPEN x_item_rec FOR
            SELECT
                NULL whouse_id
               --,p_phy_inv_id  --del at 5/23
               ,PIC2.PHYS_INVTY_CTRL_PK  --add at 5/23
               ,NULL item_number
               --,PICD.MDSE_CD --del at 5/23
               ,p_item_num  --add at 5/23
               ,PICD.PHYS_INVTY_CNT_DTL_PK
               ,PICD.TAG_NO
               --,PICD.RTL_WH_CD || PICD.RTL_SWH_CD WH_CD  --del at 5/23
               ,PIC2.WH_CD  --add at 5/23
               ,NULL locator_id
               ,NULL locator_name
               --,PICD.CNT_INP_QTY  --del at 5/23
               ,NVL(PICD.CNT_INP_QTY, 0) CNT_INP_QTY  --add at 5/23
               --,PICD.RTL_WH_CD  --del at 5/23
               ,PIC2.RTL_WH_CD  --add at 5/23
               ,NULL
            FROM
                PHYS_INVTY_CTRL                PIC
               ,PHYS_INVTY_CTRL                PIC2
               ,CANON_E437_PHYS_INVTY_CNT_DTL  PICD
               --START 2022/08/29 T.NEMA [QC#60412, ADD]
               ,MDSE                           M
               --END   2022/08/29 T.NEMA [QC#60412, ADD]
            WHERE
                PIC.GLBL_CMPY_CD          = 'ADB'
            AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
            AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
            AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
            AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
            AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
            AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
            AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
            --AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD  --del at 5/23
            AND PIC2.GLBL_CMPY_CD         = PICD.GLBL_CMPY_CD(+)  --add at 5/23
            --AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK  --del at 5/23
            AND PIC2.PHYS_INVTY_CTRL_PK   = PICD.PHYS_INVTY_CTRL_PK(+)  --add at 5/23
            --AND PICD.MDSE_CD           = p_item_num  --del at 5/23
            AND PICD.MDSE_CD(+)           = p_item_num  --add at 5/23
            AND PIC.EZCANCELFLAG          = '0'
            AND PIC2.EZCANCELFLAG         = '0'
            --AND PICD.EZCANCELFLAG      = '0'  --del at 5/23
            AND PICD.EZCANCELFLAG(+)      = '0'  --add at 5/23
            --START 2022/08/29 T.NEMA [QC#60412, ADD]
            AND M.GLBL_CMPY_CD            = PIC.GLBL_CMPY_CD
            AND M.MDSE_CD                 = p_item_num
            AND (CASE WHEN PIC2.RTL_SWH_CD ='R' AND M.RTRN_CTRL_TP_CD IS NULL THEN 'false' ELSE 'true' END) = 'true'
            AND M.EZCANCELFLAG            = '0'
            --END   2022/08/29 T.NEMA [QC#60412, ADD]
            ORDER BY
                --START 2022/08/29 T.NEMA [QC#60412, ADD]
                PIC2.WH_CD ASC,
                --END   2022/08/29 T.NEMA [QC#60412, ADD]
                PICD.EZUPTIME DESC
            ;
    --
    ELSE
        -- No record found.
        -- Ceck Inventory.
        SELECT
            COUNT(1)
        INTO
            l_cnt2
        FROM
            INVTY
        WHERE
            INVTY_LOC_CD LIKE p_user_id || '%'
        AND MDSE_CD         = p_item_num
-- START 2019/03/15 T.Ogura [QC#30766,ADD]
        AND LOC_STS_CD      = '03' -- DC Stock
        AND INVTY_QTY       > 0
-- END   2019/03/15 T.Ogura [QC#30766,ADD]
        AND EZCANCELFLAG    = '0'
        ;
        --
        IF l_cnt2 = 1 THEN
            x_ret_msg  := l_item_Name;
            -- If 1 record data is exist. insert Count detail.
            -- Get Count Detail column value.
            SELECT
                INVTY_LOC_CD
            INTO
                l_invLocCd
            FROM
                INVTY
            WHERE
                INVTY_LOC_CD LIKE p_user_id || '%'
            AND MDSE_CD         = p_item_num
-- START 2019/03/15 T.Ogura [QC#30766,ADD]
            AND LOC_STS_CD      = '03' -- DC Stock
            AND INVTY_QTY       > 0
-- END   2019/03/15 T.Ogura [QC#30766,ADD]
            AND EZCANCELFLAG    = '0'
            ;
            SELECT
                PIC.PHYS_INVTY_CTRL_PK
               ,PIC.PHYS_INVTY_CTRL_NM
               ,PIC.PHYS_INVTY_DT
               ,PIC.WH_CD
               ,PIC.RTL_WH_CD
               ,PIC.RTL_SWH_CD
               ,PIC.TECH_TOC_CD
            INTO
                l_piCntPk
               ,l_piCntNm
               ,l_piDt
               ,l_piWhCd
               ,l_piRtlWhCd
               ,l_piRtlSwhCd
               ,l_techTocCd
            FROM
                PHYS_INVTY_CTRL      PIC
            WHERE
                PIC.GLBL_CMPY_CD         = 'ADB'
            AND PIC.PHYS_INVTY_STS_CD    = '01' -- OPEN
            AND PIC.WH_CD                = l_invLocCd
            AND PIC.EZCANCELFLAG         = '0'
            ;
            --
            -- Get Count Sequence
            SELECT
                NVL(MAX(PHYS_INVTY_CNT_HDR_PK), 0) + 1
            INTO
                l_piCntHdrPk
            FROM
                CANON_E437_PHYS_INVTY_CNT_HDR
            ;
            SELECT
                NVL(MAX(PHYS_INVTY_CNT_DTL_PK), 0) + 1
            INTO
                l_piCntDtlPk
            FROM
                CANON_E437_PHYS_INVTY_CNT_DTL
            ;
            SELECT
                LPAD(NVL(MAX(TO_NUMBER(TAG_NO)), 0) + 1, 3, '0')
            INTO
                l_piTagNo
            FROM
                CANON_E437_PHYS_INVTY_CNT_DTL
            WHERE
                PHYS_INVTY_CTRL_PK = p_phy_inv_id
            ;
            --
            -- insert Count Header data
            INSERT INTO CANON_E437_PHYS_INVTY_CNT_HDR
               (EZTABLEID
               ,EZCANCELFLAG
               ,EZINTIME
               ,EZINTIMEZONE
               ,EZINCOMPANYCODE
               ,EZINUSERID
               ,EZINAPLID
               ,EZUPTIME
               ,EZUPTIMEZONE
               ,EZUPCOMPANYCODE
               ,EZUPUSERID
               ,EZUPAPLID
               ,GLBL_CMPY_CD
               ,PHYS_INVTY_CNT_HDR_PK
               ,PHYS_INVTY_CTRL_PK
               ,PHYS_INVTY_CTRL_NM
               ,PHYS_INVTY_DT
               ,WH_CD
               ,RTL_WH_CD
               ,RTL_SWH_CD
               ,MDSE_CD
               ,STK_STS_CD
               ,TECH_LOC_TP_CD
               ,TECH_TOC_CD
               ,SYS_CNT_REGD_FLG
               ,FIRST_CNT_INP_QTY
               ,FIRST_CNT_INP_TS
               ,SCD_CNT_INP_QTY
               ,SCD_CNT_INP_TS
               ,LTST_CNT_INP_QTY
               ,LTST_CNT_INP_TS
               ,INVTY_AVAL_QTY
               ,INVTY_REGD_TS
               ,ADJ_VAR_FLG
               ,ADJ_VAR_QTY
               ,ADJ_VAR_AMT
               ,INVTY_ORD_NUM
               ,PHYS_INVTY_ADJ_NM)
            VALUES
               ('PHYS_INVTY_CNT_HDR'
               ,'0'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,'ADB'
               ,l_piCntHdrPk
               ,l_piCntPk
               ,l_piCntNm
               ,l_piDt
               ,l_piWhCd
               ,l_piRtlWhCd
               ,l_piRtlSwhCd
               ,p_item_num
               ,'1'
               ,NULL
                --,p_user_id --del at 6/11
                --,l_piRtlWhCd  --mod at 6/11
               ,l_techTocCd --2018/12/12
               ,'N'
               ,0
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,'Y'
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               );
            --
            IF SQL%ROWCOUNT <> 0 THEN
                COMMIT;
            ELSE
                x_ret_msg := 'Quantity Could Not Be Updated';
                RAISE l_other_exception;
            END IF;
            --
            -- insert Count Detail data
            INSERT INTO CANON_E437_PHYS_INVTY_CNT_DTL
               (EZTABLEID
               ,EZCANCELFLAG
               ,EZINTIME
               ,EZINTIMEZONE
               ,EZINCOMPANYCODE
               ,EZINUSERID
               ,EZINAPLID
               ,EZUPTIME
               ,EZUPTIMEZONE
               ,EZUPCOMPANYCODE
               ,EZUPUSERID
               ,EZUPAPLID
               ,GLBL_CMPY_CD
               ,PHYS_INVTY_CNT_DTL_PK
               ,PHYS_INVTY_CNT_HDR_PK
               ,PHYS_INVTY_CTRL_PK
               ,RTL_WH_CD
               ,RTL_SWH_CD
               ,MDSE_CD
               ,SER_NUM
               ,CNT_INP_QTY
               ,FIRST_CNT_INP_QTY
               ,FIRST_CNT_INP_TS
               ,SCD_CNT_INP_QTY
               ,SCD_CNT_INP_TS
               ,LTST_CNT_INP_QTY
               ,LTST_CNT_INP_TS
               ,INVTY_AVAL_QTY
               ,INVTY_REGD_TS
               ,ADJ_VAR_FLG
               ,ADJ_VAR_QTY
               ,ADJ_VAR_AMT
               ,PHYS_INVTY_ADJ_STS_CD
               ,PHYS_INVTY_ADJ_MSG_TXT
               ,SVC_CONFIG_MSTR_PK
               ,TAG_NO)
            VALUES
               ('PHYS_INVTY_CNT_DTL'
               ,'0'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,'ADB'
               ,(SELECT NVL(MAX(PHYS_INVTY_CNT_DTL_PK), 0) + 1 FROM CANON_E437_PHYS_INVTY_CNT_DTL)
               ,l_piCntHdrPk
               ,l_piCntPk
               ,l_piRtlWhCd
               ,l_piRtlSwhCd
               ,p_item_num
               ,NULL
               ,1
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,'Y'
               ,NULL
               ,NULL
               ,'00'
               ,NULL
               ,NULL
               ,l_piTagNo
               );
            --
            IF SQL%ROWCOUNT <> 0 THEN
               COMMIT;
            ELSE
                x_ret_msg := 'Quantity Could Not Be Updated';
                RAISE l_other_exception;
            END IF;
        ELSE
            -- If Many record data is exist.(like K02053G, K02053R)
            -- Or No record found.
            -- Let User Select The Correct Combination.
            x_ret_code := 'M';
            x_ret_msg  := l_item_Name;
            --
            -- Get Count Sequence
            SELECT
                NVL(MAX(PHYS_INVTY_CNT_HDR_PK), 0) + 1
            INTO
                l_piCntHdrPk
            FROM
                CANON_E437_PHYS_INVTY_CNT_HDR
            ;
            SELECT
                NVL(MAX(PHYS_INVTY_CNT_DTL_PK), 0) + 1
            INTO
                l_piCntDtlPk
            FROM
                CANON_E437_PHYS_INVTY_CNT_DTL
            ;
            SELECT
                LPAD(NVL(MAX(TO_NUMBER(TAG_NO)), 0) + 1, 3, '0')
            INTO
                l_piTagNo
            FROM
                CANON_E437_PHYS_INVTY_CNT_DTL
            WHERE
                PHYS_INVTY_CTRL_PK = p_phy_inv_id
            ;
            OPEN x_item_rec FOR
                SELECT
                    NULL whouse_id
                   ,PIC2.PHYS_INVTY_CTRL_PK
                   ,NULL item_number
                   ,p_item_num
                   ,l_piCntDtlPk
                   ,l_piTagNo
                   ,PIC2.WH_CD
                   ,NULL locator_id
                   ,NULL locator_name
                   ,1
                   ,PIC2.RTL_WH_CD
                   ,NULL
                FROM
                    PHYS_INVTY_CTRL      PIC
                   ,PHYS_INVTY_CTRL      PIC2
                   --START 2022/08/29 T.NEMA [QC#60412, ADD]
                   ,MDSE                 M
                   --END   2022/08/29 T.NEMA [QC#60412, ADD]
                WHERE
                    PIC.GLBL_CMPY_CD          = 'ADB'
                AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
                AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
                AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
                AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
                AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
                AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
                AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
                AND PIC.EZCANCELFLAG          = '0'
                AND PIC2.EZCANCELFLAG         = '0'
                --START 2022/08/29 T.NEMA [QC#60412, ADD]
                AND M.GLBL_CMPY_CD            = PIC.GLBL_CMPY_CD
                AND M.MDSE_CD                 = p_item_num
                AND (CASE WHEN PIC2.RTL_SWH_CD ='R' AND M.RTRN_CTRL_TP_CD IS NULL THEN 'false' ELSE 'true' END) = 'true'
                AND M.EZCANCELFLAG            = '0'
                --END   2022/08/29 T.NEMA [QC#60412, ADD]
                ORDER BY
                    --START 2022/08/29 T.NEMA [QC#60412, ADD]
                    PIC2.WH_CD ASC,
                    --END   2022/08/29 T.NEMA [QC#60412, ADD]
                    PIC2.PHYS_INVTY_CTRL_PK
                ;
            -- insert Count Header data
            -- but WH_CD and SWH_CD are null
            -- These column value is after user choose update.
            INSERT INTO CANON_E437_PHYS_INVTY_CNT_HDR
               (EZTABLEID
               ,EZCANCELFLAG
               ,EZINTIME
               ,EZINTIMEZONE
               ,EZINCOMPANYCODE
               ,EZINUSERID
               ,EZINAPLID
               ,EZUPTIME
               ,EZUPTIMEZONE
               ,EZUPCOMPANYCODE
               ,EZUPUSERID
               ,EZUPAPLID
               ,GLBL_CMPY_CD
               ,PHYS_INVTY_CNT_HDR_PK
               ,PHYS_INVTY_CTRL_PK
               ,PHYS_INVTY_CTRL_NM
               ,PHYS_INVTY_DT
               ,WH_CD
               ,RTL_WH_CD
               ,RTL_SWH_CD
               ,MDSE_CD
               ,STK_STS_CD
               ,TECH_LOC_TP_CD
               ,TECH_TOC_CD
               ,SYS_CNT_REGD_FLG
               ,FIRST_CNT_INP_QTY
               ,FIRST_CNT_INP_TS
               ,SCD_CNT_INP_QTY
               ,SCD_CNT_INP_TS
               ,LTST_CNT_INP_QTY
               ,LTST_CNT_INP_TS
               ,INVTY_AVAL_QTY
               ,INVTY_REGD_TS
               ,ADJ_VAR_FLG
               ,ADJ_VAR_QTY
               ,ADJ_VAR_AMT
               ,INVTY_ORD_NUM
               ,PHYS_INVTY_ADJ_NM)
            VALUES
               ('PHYS_INVTY_CNT_HDR'
               ,'0'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,'ADB'
               ,l_piCntHdrPk
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,p_item_num
               ,'1'
               ,NULL
               ,p_user_id --del at 6/11
               --,p_emp_id --add at 6/11
               ,'N'
               ,0
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,'Y'
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               );
            --
            IF SQL%ROWCOUNT <> 0 THEN
                COMMIT;
            ELSE
                x_ret_msg := 'Quantity Could Not Be Updated';
                RAISE l_other_exception;
            END IF;
            --
            -- insert Count Detail data
            INSERT INTO CANON_E437_PHYS_INVTY_CNT_DTL
               (EZTABLEID
               ,EZCANCELFLAG
               ,EZINTIME
               ,EZINTIMEZONE
               ,EZINCOMPANYCODE
               ,EZINUSERID
               ,EZINAPLID
               ,EZUPTIME
               ,EZUPTIMEZONE
               ,EZUPCOMPANYCODE
               ,EZUPUSERID
               ,EZUPAPLID
               ,GLBL_CMPY_CD
               ,PHYS_INVTY_CNT_DTL_PK
               ,PHYS_INVTY_CNT_HDR_PK
               ,PHYS_INVTY_CTRL_PK
               ,RTL_WH_CD
               ,RTL_SWH_CD
               ,MDSE_CD
               ,SER_NUM
               ,CNT_INP_QTY
               ,FIRST_CNT_INP_QTY
               ,FIRST_CNT_INP_TS
               ,SCD_CNT_INP_QTY
               ,SCD_CNT_INP_TS
               ,LTST_CNT_INP_QTY
               ,LTST_CNT_INP_TS
               ,INVTY_AVAL_QTY
               ,INVTY_REGD_TS
               ,ADJ_VAR_FLG
               ,ADJ_VAR_QTY
               ,ADJ_VAR_AMT
               ,PHYS_INVTY_ADJ_STS_CD
               ,PHYS_INVTY_ADJ_MSG_TXT
               ,SVC_CONFIG_MSTR_PK
               ,TAG_NO)
            VALUES
               ('PHYS_INVTY_CNT_DTL'
               ,'0'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               ,p_emp_id --add at 5/23
               ,'MOBILE_PI'
               ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
               ,'EDT'
               ,'ADB'
               --,p_user_id --del at 5/23
               --,p_emp_id --add at 5/23
               ,p_user_id --del at 2/04/2019
               ,'MOBILE_PI'
               ,'ADB'
               ,l_piCntDtlPk
               ,l_piCntHdrPk
               ,NULL
               ,NULL
               ,NULL
               ,p_item_num
               ,NULL
               ,1
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,NULL
               ,'Y'
               ,NULL
               ,NULL
               ,'00'
               ,NULL
               ,NULL
               ,l_piTagNo
               );
            --
            IF SQL%ROWCOUNT <> 0 THEN
                COMMIT;
            ELSE
                x_ret_msg := 'Quantity Could Not Be Updated';
                RAISE l_other_exception;
            END IF;
        END IF;
    END IF;
EXCEPTION
    WHEN l_other_exception THEN
        x_ret_code  := 'E';
        g_error_msg := x_ret_msg;
        insert_error;
    WHEN OTHERS THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Error In VALIDATEITEM: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg := x_ret_msg;
        insert_error;
END validateitem;
-------------------------------------------------------------------------
-- Update Procedure To Update Manual Counts In Staging
PROCEDURE updatedbmanual (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_tag_id       IN       VARCHAR2
   ,p_tag_qty      IN       NUMBER
   ,p_emp_id       IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_subinv       IN       VARCHAR2
   ,p_locator_id   IN       VARCHAR2
   ,p_item_id      IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
)
IS
    -- Local Variables
    l_cnt              NUMBER                                                    := 0;
    l_piCntNm          PHYS_INVTY_CTRL.PHYS_INVTY_CTRL_NM%TYPE                   := NULL;
    l_piDt             PHYS_INVTY_CTRL.PHYS_INVTY_DT%TYPE                        := NULL;
    l_piWhCd           PHYS_INVTY_CTRL.WH_CD%TYPE                                := NULL;
    l_piRtlWhCd        PHYS_INVTY_CTRL.RTL_WH_CD%TYPE                            := NULL;
    l_piRtlSwhCd       PHYS_INVTY_CTRL.RTL_SWH_CD%TYPE                           := NULL;
    l_piCntHdrPk       CANON_E437_PHYS_INVTY_CNT_HDR.PHYS_INVTY_CNT_HDR_PK%TYPE  := NULL;
    
    l_piCntPk          PHYS_INVTY_CTRL.PHYS_INVTY_CTRL_PK%TYPE                   := NULL;
    l_piCntDtlPk       CANON_E437_PHYS_INVTY_CNT_DTL.PHYS_INVTY_CNT_DTL_PK%TYPE  := NULL;
    l_piTagNo          CANON_E437_PHYS_INVTY_CNT_DTL.TAG_NO%TYPE                 := NULL;
    l_cntQty           NUMBER                                                    := 0;
    l_cnt2             NUMBER                                                    := 0;
    
    l_techTocCd        PHYS_INVTY_CTRL.TECH_TOC_CD%TYPE                          := NULL;  -- add 2018/12/12
    
    l_other_exception  EXCEPTION;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('UPDATEDBMANUAL');
    g_process_name    := 'UPDATEDBMANUAL';
    g_key1            := 'p_whouse_id '  || p_whouse_id;
    g_key2            := 'p_phy_inv_id ' || p_phy_inv_id;
    g_key3            := 'p_tag_id '     || p_tag_id  || ' ,p_tag_qty '   || p_tag_qty;
    g_key4            := 'p_emp_id '     || p_emp_id  || ' ,p_user_id '   || p_user_id;
    g_key5            := 'p_item_id '    || p_item_id || ' ,p_form_mode ' || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
    --
    -- Check Physical Inventory Count Details
    -- PHYS_INVTY_CTRL_PK is not null
    IF p_tag_id IS NOT NULL AND p_tag_id <> '-' THEN  --add at 5/23
    SELECT
        COUNT(1)
    INTO
        l_cnt
    FROM
        CANON_E437_PHYS_INVTY_CNT_DTL PICD
    WHERE
        PICD.GLBL_CMPY_CD          = 'ADB'
    AND PICD.PHYS_INVTY_CNT_DTL_PK = p_tag_id
    AND PICD.PHYS_INVTY_CTRL_PK    IS NOT NULL
    AND PICD.EZCANCELFLAG          = '0'
    ;
    END IF;  --add at 5/23
    --
    --IF l_cnt > 0 THEN  --del at 5/23
    IF l_cnt = 1 THEN  --add at 5/23
        -- Update Physical Inventory Count Details
        -- For PHYS_INVTY_CTRL_PK is not null
        -- This data is change item qty on screen.
        UPDATE
            CANON_E437_PHYS_INVTY_CNT_DTL PICD
        SET
            PICD.EZUPTIME        = TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
           --,PICD.EZUPUSERID      = p_user_id  --del at 5/23
           ,PICD.EZUPUSERID      = p_emp_id  --add at 5/23
           ,PICD.EZUPAPLID       = 'MOBILE_PI'
           ,PICD.CNT_INP_QTY     = p_tag_qty
        WHERE
            PICD.GLBL_CMPY_CD          = 'ADB'
        AND PICD.PHYS_INVTY_CNT_DTL_PK = p_tag_id
        AND PICD.PHYS_INVTY_CTRL_PK    IS NOT NULL
        AND PICD.EZCANCELFLAG          = '0'
        ;
        --
        IF SQL%ROWCOUNT <> 0 THEN
            COMMIT;
        ELSE
            x_ret_msg := 'Quantity Could Not Be Updated';
            RAISE l_other_exception;
        END IF;
        
    --add at 5/23 start
    --ELSIF l_cnt = 0 THEN  --del 02/04/2019 
    ELSE   --add 02/04/2019 
    

        IF p_tag_id IS NOT NULL AND p_tag_id <> '-' THEN  --add at 5/23
            SELECT
                COUNT(1)
            INTO
                l_cnt
            FROM
                CANON_E437_PHYS_INVTY_CNT_DTL PICD
            WHERE
                PICD.GLBL_CMPY_CD          = 'ADB'
            AND PICD.PHYS_INVTY_CNT_DTL_PK = p_tag_id
            AND PICD.EZCANCELFLAG          = '0'
        ;
        END IF;  --add 02/04/2019 

        IF l_cnt = 0 THEN    --add 02/04/2019 

        SELECT
            PIC.PHYS_INVTY_CTRL_PK
           ,PIC.PHYS_INVTY_CTRL_NM
           ,PIC.PHYS_INVTY_DT
           ,PIC.WH_CD
           ,PIC.RTL_WH_CD
           ,PIC.RTL_SWH_CD
           ,PIC.TECH_TOC_CD
        INTO
            l_piCntPk
           ,l_piCntNm
           ,l_piDt
           ,l_piWhCd
           ,l_piRtlWhCd
           ,l_piRtlSwhCd
           ,l_techTocCd
        FROM
            PHYS_INVTY_CTRL      PIC
        WHERE
            PIC.GLBL_CMPY_CD         = 'ADB'
        AND PIC.PHYS_INVTY_STS_CD    = '01' -- OPEN
        AND PIC.WH_CD                = p_subinv
        AND PIC.EZCANCELFLAG         = '0'
        ;
        --
        -- Get Count Sequence
        SELECT
            NVL(MAX(PHYS_INVTY_CNT_HDR_PK), 0) + 1
        INTO
            l_piCntHdrPk
        FROM
            CANON_E437_PHYS_INVTY_CNT_HDR
        ;
        SELECT
            NVL(MAX(PHYS_INVTY_CNT_DTL_PK), 0) + 1
        INTO
            l_piCntDtlPk
        FROM
            CANON_E437_PHYS_INVTY_CNT_DTL
        ;
        SELECT
            LPAD(NVL(MAX(TO_NUMBER(TAG_NO)), 0) + 1, 3, '0')
        INTO
            l_piTagNo
        FROM
            CANON_E437_PHYS_INVTY_CNT_DTL
        WHERE
            PHYS_INVTY_CTRL_PK = p_phy_inv_id
        ;
        -- insert Count Header data
        INSERT INTO CANON_E437_PHYS_INVTY_CNT_HDR
           (EZTABLEID
           ,EZCANCELFLAG
           ,EZINTIME
           ,EZINTIMEZONE
           ,EZINCOMPANYCODE
           ,EZINUSERID
           ,EZINAPLID
           ,EZUPTIME
           ,EZUPTIMEZONE
           ,EZUPCOMPANYCODE
           ,EZUPUSERID
           ,EZUPAPLID
           ,GLBL_CMPY_CD
           ,PHYS_INVTY_CNT_HDR_PK
           ,PHYS_INVTY_CTRL_PK
           ,PHYS_INVTY_CTRL_NM
           ,PHYS_INVTY_DT
           ,WH_CD
           ,RTL_WH_CD
           ,RTL_SWH_CD
           ,MDSE_CD
           ,STK_STS_CD
           ,TECH_LOC_TP_CD
           ,TECH_TOC_CD
           ,SYS_CNT_REGD_FLG
           ,FIRST_CNT_INP_QTY
           ,FIRST_CNT_INP_TS
           ,SCD_CNT_INP_QTY
           ,SCD_CNT_INP_TS
           ,LTST_CNT_INP_QTY
           ,LTST_CNT_INP_TS
           ,INVTY_AVAL_QTY
           ,INVTY_REGD_TS
           ,ADJ_VAR_FLG
           ,ADJ_VAR_QTY
           ,ADJ_VAR_AMT
           ,INVTY_ORD_NUM
           ,PHYS_INVTY_ADJ_NM
        )
        VALUES
           ('PHYS_INVTY_CNT_HDR'
           ,'0'
           ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
           ,'EDT'
           ,'ADB'
           ,p_emp_id
           ,'MOBILE_PI'
           ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
           ,'EDT'
           ,'ADB'
           ,p_emp_id
           ,'MOBILE_PI'
           ,'ADB'
           ,l_piCntHdrPk
           ,l_piCntPk
           ,l_piCntNm
           ,l_piDt
           ,l_piWhCd
           ,l_piRtlWhCd
           ,l_piRtlSwhCd
           ,p_item_id
           ,'1'
           ,NULL
            --,p_user_id --del at 6/11
            --,l_piRtlWhCd --add at 6/11
           ,l_techTocCd  --mod 2018/12/12
           ,'N'
           ,l_cntQty
           ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
           ,NULL
           ,NULL
           ,NULL
           ,NULL
           ,NULL
           ,NULL
           ,'Y'
           ,NULL
           ,NULL
           ,NULL
           ,NULL
           );

        --
        IF SQL%ROWCOUNT <> 0 THEN
            COMMIT;
        ELSE
            x_ret_msg := 'Quantity Could Not Be Updated';
            RAISE l_other_exception;
        END IF;
        --
        -- insert Count Detail data
        INSERT INTO CANON_E437_PHYS_INVTY_CNT_DTL
           (EZTABLEID
           ,EZCANCELFLAG
           ,EZINTIME
           ,EZINTIMEZONE
           ,EZINCOMPANYCODE
           ,EZINUSERID
           ,EZINAPLID
           ,EZUPTIME
           ,EZUPTIMEZONE
           ,EZUPCOMPANYCODE
           ,EZUPUSERID
           ,EZUPAPLID
           ,GLBL_CMPY_CD
           ,PHYS_INVTY_CNT_DTL_PK
           ,PHYS_INVTY_CNT_HDR_PK
           ,PHYS_INVTY_CTRL_PK
           ,RTL_WH_CD
           ,RTL_SWH_CD
           ,MDSE_CD
           ,SER_NUM
           ,CNT_INP_QTY
           ,FIRST_CNT_INP_QTY
           ,FIRST_CNT_INP_TS
           ,SCD_CNT_INP_QTY
           ,SCD_CNT_INP_TS
           ,LTST_CNT_INP_QTY
           ,LTST_CNT_INP_TS
           ,INVTY_AVAL_QTY
           ,INVTY_REGD_TS
           ,ADJ_VAR_FLG
           ,ADJ_VAR_QTY
           ,ADJ_VAR_AMT
           ,PHYS_INVTY_ADJ_STS_CD
           ,PHYS_INVTY_ADJ_MSG_TXT
           ,SVC_CONFIG_MSTR_PK
           ,TAG_NO
           ,CNT_COMP_FLG)
        VALUES
           ('PHYS_INVTY_CNT_DTL'
           ,'0'
           ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
           ,'EDT'
           ,'ADB'
           --,p_user_id --del at 5/23
           ,p_emp_id --add at 5/23
           ,'MOBILE_PI'
           ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
           ,'EDT'
           ,'ADB'
           --,p_user_id --del at 5/23
           ,p_emp_id --add at 5/23
           ,'MOBILE_PI'
           ,'ADB'
           ,(SELECT NVL(MAX(PHYS_INVTY_CNT_DTL_PK), 0) + 1 FROM CANON_E437_PHYS_INVTY_CNT_DTL)
           ,l_piCntHdrPk
           ,l_piCntPk
           ,l_piRtlWhCd
           ,l_piRtlSwhCd
           ,p_item_id
           ,NULL
           ,1
           ,NULL
           ,NULL
           ,NULL
           ,NULL
           ,NULL
           ,NULL
           ,NULL
           ,NULL
           ,'Y'
           ,NULL
           ,NULL
           ,'00'
           ,NULL
           ,NULL
           ,l_piTagNo
           ,'N');
        --
        IF SQL%ROWCOUNT <> 0 THEN
           COMMIT;
        ELSE
            x_ret_msg := 'Quantity Could Not Be Updated';
            RAISE l_other_exception;
        END IF;
    --add at 5/23 end

        ELSE
        -- Update Physical Inventory Count Details
        -- For PHYS_INVTY_CTRL_PK is null
        -- This data is choose multi item exist in inventory.(like K02053G, K02053R)
        --
        -- Get PHYS_INVTY_CTRL before update.
        SELECT
            PIC.PHYS_INVTY_CTRL_NM
           ,PIC.PHYS_INVTY_DT
           ,PIC.WH_CD
           ,PIC.RTL_WH_CD
           ,PIC.RTL_SWH_CD
        INTO
            l_piCntNm
           ,l_piDt
           ,l_piWhCd
           ,l_piRtlWhCd
           ,l_piRtlSwhCd
        FROM
            PHYS_INVTY_CTRL      PIC
        WHERE
            PIC.GLBL_CMPY_CD         = 'ADB'
        AND PIC.PHYS_INVTY_STS_CD    = '01' -- OPEN
        AND PIC.PHYS_INVTY_CTRL_PK   = p_phy_inv_id
        ;
        --
        -- Get PHYS_INVTY_CNT_HDR_PK before update.
        SELECT
            PICD.PHYS_INVTY_CNT_HDR_PK
        INTO
            l_piCntHdrPk
        FROM
            CANON_E437_PHYS_INVTY_CNT_DTL      PICD
        WHERE
            PICD.GLBL_CMPY_CD           = 'ADB'
        AND PICD.PHYS_INVTY_CNT_DTL_PK  = p_tag_id
        AND PICD.EZCANCELFLAG           = '0'
        ;
        --
        -- update Count Header.
        UPDATE
            CANON_E437_PHYS_INVTY_CNT_HDR PICH
        SET
            PICH.EZUPTIME              = TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
           --,PICH.EZUPUSERID            = p_user_id  --del at 5/23
           ,PICH.EZUPUSERID            = p_emp_id  --add at 5/23
           ,PICH.EZUPAPLID             = 'MOBILE_PI'
           ,PICH.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
           ,PICH.PHYS_INVTY_CTRL_NM    = l_piCntNm
           ,PICH.PHYS_INVTY_DT         = l_piDt
           ,PICH.WH_CD                 = l_piWhCd
           ,PICH.RTL_WH_CD             = l_piRtlWhCd
           ,PICH.RTL_SWH_CD            = l_piRtlSwhCd
        WHERE
            PICH.GLBL_CMPY_CD          = 'ADB'
        AND PICH.PHYS_INVTY_CNT_HDR_PK = l_piCntHdrPk
        AND PICH.PHYS_INVTY_CTRL_PK    IS NULL
        AND PICH.EZCANCELFLAG          = '0'
        ;
        --
        IF SQL%ROWCOUNT <> 0 THEN
           COMMIT;
        ELSE
            x_ret_msg := 'Quantity Could Not Be Updated';
            RAISE l_other_exception;
        END IF;
        --
        -- update Count Detail.
        UPDATE
            CANON_E437_PHYS_INVTY_CNT_DTL PICD
        SET
            PICD.EZUPTIME               = TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
           --,PICD.EZUPUSERID             = p_user_id  --del at 5/23
           ,PICD.EZUPUSERID             = p_emp_id  --add at 5/23
           ,PICD.EZUPAPLID              = 'MOBILE_PI'
           --,PICD.PHYS_INVTY_CNT_HDR_PK  = p_tag_id --del 5/23
           ,PICD.PHYS_INVTY_CNT_DTL_PK  = p_tag_id --add 5/23
           ,PICD.PHYS_INVTY_CTRL_PK     = p_phy_inv_id
           ,PICD.RTL_WH_CD              = l_piRtlWhCd
           ,PICD.RTL_SWH_CD             = l_piRtlSwhCd
        WHERE
            PICD.GLBL_CMPY_CD           = 'ADB'
        AND PICD.PHYS_INVTY_CNT_DTL_PK  = p_tag_id
        AND PICD.PHYS_INVTY_CTRL_PK     IS NULL
        AND PICD.EZCANCELFLAG           = '0'
        ;
        --
        IF SQL%ROWCOUNT <> 0 THEN
            COMMIT;
        ELSE
            x_ret_msg := 'Quantity Could Not Be Updated';
            RAISE l_other_exception;
        END IF;
        END IF;
    END IF;  --add 02/04/2019
EXCEPTION
    WHEN l_other_exception THEN
        x_ret_code  := 'E';
        g_error_msg := x_ret_msg;
        insert_error;
    WHEN OTHERS THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Error In UPDATEDBMANUAL: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg := x_ret_msg;
        insert_error;
END updatedbmanual;
-------------------------------------------------------------------------
-- Procedure To Assign Zero To Pending Tags
PROCEDURE assignzerocounts (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_emp_id       IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
)
IS
    -- Local Variables
    l_ret_msg          VARCHAR (4000)  := NULL;
    l_other_exception  EXCEPTION;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('ASSIGNZEROCOUNTS');
    g_process_name    := 'ASSIGNZEROCOUNTS';
    g_key1            := 'p_whouse_id '  || p_whouse_id;
    g_key2            := 'p_phy_inv_id ' || p_phy_inv_id;
    g_key3            := 'p_emp_id '     || p_emp_id || ' ,p_user_id ' || p_user_id;
    g_key4            := 'p_form_mode '  || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
    --
    -- Insert 0 Count data.
    -- When No Scan item exist in Inventory
    -- insert Count Header data
    INSERT INTO CANON_E437_PHYS_INVTY_CNT_HDR
       (EZTABLEID
       ,EZCANCELFLAG
       ,EZINTIME
       ,EZINTIMEZONE
       ,EZINCOMPANYCODE
       ,EZINUSERID
       ,EZINAPLID
       ,EZUPTIME
       ,EZUPTIMEZONE
       ,EZUPCOMPANYCODE
       ,EZUPUSERID
       ,EZUPAPLID
       ,GLBL_CMPY_CD
       ,PHYS_INVTY_CNT_HDR_PK
       ,PHYS_INVTY_CTRL_PK
       ,PHYS_INVTY_CTRL_NM
       ,PHYS_INVTY_DT
       ,WH_CD
       ,RTL_WH_CD
       ,RTL_SWH_CD
       ,MDSE_CD
       ,STK_STS_CD
       ,TECH_LOC_TP_CD
       ,TECH_TOC_CD
       ,SYS_CNT_REGD_FLG
       ,FIRST_CNT_INP_QTY
       ,FIRST_CNT_INP_TS
       ,SCD_CNT_INP_QTY
       ,SCD_CNT_INP_TS
       ,LTST_CNT_INP_QTY
       ,LTST_CNT_INP_TS
       ,INVTY_AVAL_QTY
       ,INVTY_REGD_TS
       ,ADJ_VAR_FLG
       ,ADJ_VAR_QTY
       ,ADJ_VAR_AMT
       ,INVTY_ORD_NUM
       ,PHYS_INVTY_ADJ_NM)
    SELECT
        'PHYS_INVTY_CNT_HDR'
       ,'0'
       ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
       ,'EDT'
       ,'ADB'
       ,p_user_id
       ,'MOBILE_PI'
       ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
       ,'EDT'
       ,'ADB'
       ,p_user_id
       ,'MOBILE_PI'
       ,'ADB'
       ,(SELECT NVL(MAX(PHYS_INVTY_CNT_HDR_PK), 0) FROM CANON_E437_PHYS_INVTY_CNT_HDR) + ROWNUM
       ,PIC2.PHYS_INVTY_CTRL_PK
       ,PIC2.PHYS_INVTY_CTRL_NM
       ,PIC2.PHYS_INVTY_DT
       ,PIC2.WH_CD
       ,PIC2.RTL_WH_CD
       ,PIC2.RTL_SWH_CD
       ,INV.MDSE_CD
       ,'1'
       ,NULL
        --,p_user_id --del at 6/11
       --,PIC2.RTL_WH_CD --add at 6/11
       ,PIC2.TECH_TOC_CD -- mod QC#28755
       ,'N'
       ,0 -- Zero Count
       ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
       ,NULL
       ,NULL
       ,NULL
       ,NULL
       ,NULL
       ,NULL
       ,'Y'
       ,NULL
       ,NULL
       ,NULL
       ,NULL
    FROM
        PHYS_INVTY_CTRL      PIC
       ,PHYS_INVTY_CTRL      PIC2
       ,INVTY                INV
    WHERE
        PIC.GLBL_CMPY_CD          = 'ADB'
    AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
    AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
    AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
    AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
    AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
    AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
    AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
    AND PIC2.GLBL_CMPY_CD         = INV.GLBL_CMPY_CD
    AND PIC2.WH_CD                = INV.INVTY_LOC_CD
-- START 2019/03/05 T.Ogura [QC#30572,ADD]
    AND INV.LOC_STS_CD            = '03' -- DC Stock
-- END   2019/03/05 T.Ogura [QC#30572,ADD]
    AND NOT EXISTS
       (SELECT
            1
        FROM
            CANON_E437_PHYS_INVTY_CNT_DTL  PICD
        WHERE
            PICD.GLBL_CMPY_CD       = PIC2.GLBL_CMPY_CD
        AND PICD.PHYS_INVTY_CTRL_PK = PIC2.PHYS_INVTY_CTRL_PK
        AND PICD.MDSE_CD            = INV.MDSE_CD
        AND PICD.EZCANCELFLAG       = '0'
        )
    AND PIC.EZCANCELFLAG            = '0'
    AND PIC2.EZCANCELFLAG           = '0'
    AND INV.EZCANCELFLAG            = '0'
    AND INV.INVTY_QTY               >  0
    ;
    --
    COMMIT;
    -- insert Count Detail data
    INSERT INTO CANON_E437_PHYS_INVTY_CNT_DTL
       (EZTABLEID
       ,EZCANCELFLAG
       ,EZINTIME
       ,EZINTIMEZONE
       ,EZINCOMPANYCODE
       ,EZINUSERID
       ,EZINAPLID
       ,EZUPTIME
       ,EZUPTIMEZONE
       ,EZUPCOMPANYCODE
       ,EZUPUSERID
       ,EZUPAPLID
       ,GLBL_CMPY_CD
       ,PHYS_INVTY_CNT_DTL_PK
       ,PHYS_INVTY_CNT_HDR_PK
       ,PHYS_INVTY_CTRL_PK
       ,RTL_WH_CD
       ,RTL_SWH_CD
       ,MDSE_CD
       ,SER_NUM
       ,CNT_INP_QTY
       ,FIRST_CNT_INP_QTY
       ,FIRST_CNT_INP_TS
       ,SCD_CNT_INP_QTY
       ,SCD_CNT_INP_TS
       ,LTST_CNT_INP_QTY
       ,LTST_CNT_INP_TS
       ,INVTY_AVAL_QTY
       ,INVTY_REGD_TS
       ,ADJ_VAR_FLG
       ,ADJ_VAR_QTY
       ,ADJ_VAR_AMT
       ,PHYS_INVTY_ADJ_STS_CD
       ,PHYS_INVTY_ADJ_MSG_TXT
       ,SVC_CONFIG_MSTR_PK
       ,TAG_NO
       ,CNT_COMP_FLG)
    SELECT
        'PHYS_INVTY_CNT_DTL'
       ,'0'
       ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
       ,'EDT'
       ,'ADB'
       ,p_user_id
       ,'MOBILE_PI'
       ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISSFF3')
       ,'EDT'
       ,'ADB'
       ,p_user_id
       ,'MOBILE_PI'
       ,'ADB'
       ,(SELECT NVL(MAX(PHYS_INVTY_CNT_DTL_PK), 0) FROM CANON_E437_PHYS_INVTY_CNT_DTL) + ROWNUM
       ,PICH.PHYS_INVTY_CNT_HDR_PK
       ,PICH.PHYS_INVTY_CTRL_PK
       ,PICH.RTL_WH_CD
       ,PICH.RTL_SWH_CD
       ,PICH.MDSE_CD
       ,NULL
       ,0 -- Zero Count
       ,NULL
       ,NULL
       ,NULL
       ,NULL
       ,NULL
       ,NULL
       ,NULL
       ,NULL
       ,'Y'
       ,NULL
       ,NULL
       ,'00'
       ,NULL
       ,NULL
       ,LPAD((SELECT NVL(MAX(TO_NUMBER(TAG_NO)), 0) FROM CANON_E437_PHYS_INVTY_CNT_DTL WHERE PHYS_INVTY_CTRL_PK = p_phy_inv_id) + ROWNUM, 3, '0')
       ,'Y'
    FROM
        PHYS_INVTY_CTRL                PIC
       ,PHYS_INVTY_CTRL                PIC2
       ,CANON_E437_PHYS_INVTY_CNT_HDR  PICH
    WHERE
        PIC.GLBL_CMPY_CD          = 'ADB'
    AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
    AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
    AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
    AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
    AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
    AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
    AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
    AND PIC2.GLBL_CMPY_CD         = PICH.GLBL_CMPY_CD
    AND PIC2.PHYS_INVTY_CTRL_PK   = PICH.PHYS_INVTY_CTRL_PK
    AND NOT EXISTS
       (SELECT
            1
        FROM
            CANON_E437_PHYS_INVTY_CNT_DTL  PICD
        WHERE
            PICD.GLBL_CMPY_CD          = PICH.GLBL_CMPY_CD
        AND PICD.PHYS_INVTY_CTRL_PK    = PICH.PHYS_INVTY_CTRL_PK
        AND PICD.PHYS_INVTY_CNT_HDR_PK = PICH.PHYS_INVTY_CNT_HDR_PK
        AND PICD.MDSE_CD               = PICH.MDSE_CD
        AND PICD.EZCANCELFLAG          = '0'
        )
    AND PIC.EZCANCELFLAG            = '0'
    AND PIC2.EZCANCELFLAG           = '0'
    AND PICH.EZCANCELFLAG           = '0'
    ;
    --
    -- Count Complete
    UPDATE
        CANON_E437_PHYS_INVTY_CNT_DTL PICD
    SET
        PICD.CNT_COMP_FLG = 'Y'
    WHERE
        PICD.PHYS_INVTY_CTRL_PK IN
           (SELECT
                PIC2.PHYS_INVTY_CTRL_PK
            FROM
                PHYS_INVTY_CTRL           PIC
               ,PHYS_INVTY_CTRL           PIC2
            WHERE
                PIC.GLBL_CMPY_CD          = 'ADB'
            AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
            AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
            AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
            AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
            AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
            AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
            AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
            AND PIC.EZCANCELFLAG          = '0'
            AND PIC2.EZCANCELFLAG         = '0')
    ;
    --
    COMMIT;
    --
    l_ret_msg := 'Submitted For Performing Adjustments.';
    --
    -- Print RC80 Report
    x_ret_msg := l_ret_msg || CHR (10) || 'Submitted For Printing RC80 Report.';
EXCEPTION
    WHEN l_other_exception THEN
        x_ret_code  := 'E';
        g_error_msg := x_ret_msg;
        insert_error;
    WHEN OTHERS THEN
        x_ret_code  := 'E';
        x_ret_msg   := 'Error In ASSIGNZEROCOUNTS: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg := x_ret_msg;
        insert_error;
END assignzerocounts;
-------------------------------------------------------------------------
-- Procedure To Print Report For Counted Parts
PROCEDURE printreport (
    p_whouse_id    IN       VARCHAR2
   ,p_phy_inv_id   IN       VARCHAR2
   ,p_user_id      IN       VARCHAR2
   ,p_form_mode    IN       VARCHAR2
   ,x_ret_code     OUT      VARCHAR2
   ,x_ret_msg      OUT      VARCHAR2
)
IS
    -- Local Variables
    l_rowcnt            NUMBER                         := 0;
    l_cnt               NUMBER                         := 0;
BEGIN
    -- Initialize Global Variables
    DBMS_APPLICATION_INFO.set_action ('PRINTREPORT');
    g_process_name    := 'PRINTREPORT';
    g_key1            := 'p_whouse_id '  || p_whouse_id;
    g_key2            := 'p_phy_inv_id ' || p_phy_inv_id;
    g_key3            := 'p_user_id '    || p_user_id;
    g_key4            := 'p_form_mode '  || p_form_mode;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := 'Submitted For Printing E437 Report.';
    --
    -- insert PI Count Print Request
    INSERT INTO CANON_E437_PRINT_REQUEST
       (PHYS_INVTY_CTRL_PK
       ,USER_ID
       ,REQ_TIME)
    SELECT
        PIC2.PHYS_INVTY_CTRL_PK
       ,p_user_id
       ,TO_CHAR(CURRENT_TIMESTAMP, 'YYYYMMDDHH24MISS')
    FROM
        PHYS_INVTY_CTRL      PIC
       ,PHYS_INVTY_CTRL      PIC2
    WHERE
        PIC.GLBL_CMPY_CD          = 'ADB'
    AND PIC.PHYS_INVTY_CTRL_PK    = p_phy_inv_id
    AND PIC.PHYS_INVTY_STS_CD     = '01' -- OPEN
    AND PIC.GLBL_CMPY_CD          = PIC2.GLBL_CMPY_CD
    AND PIC.PHYS_INVTY_CTRL_NM    = PIC2.PHYS_INVTY_CTRL_NM
    AND PIC.PHYS_INVTY_DT         = PIC2.PHYS_INVTY_DT
    AND PIC.TECH_TOC_CD           = PIC2.TECH_TOC_CD
    AND PIC2.PHYS_INVTY_STS_CD    = '01' -- OPEN
    AND PIC.EZCANCELFLAG          = '0'
    AND PIC2.EZCANCELFLAG         = '0'
    ;
    --
    COMMIT;
    --
EXCEPTION
    WHEN OTHERS THEN
        x_ret_code     := 'E';
        x_ret_msg      := 'Error In PRINTREPORT: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg    := x_ret_msg;
        insert_error;
END printreport;
-------------------------------------------------------------------------
-- This is Dummy Procedure By S21
PROCEDURE resetuser (
    p_user_id    IN       VARCHAR2
   ,x_ret_code   OUT      VARCHAR2
   ,x_ret_msg    OUT      VARCHAR2
)
IS
BEGIN
    -- Initialize Global Variables
    g_process_name    := 'RESETUSER';
    g_key1            := 'p_user_id ' || p_user_id;
    --
    -- Initialize Return Variables
    x_ret_code        := 'S';
    x_ret_msg         := NULL;
EXCEPTION
    WHEN OTHERS THEN
        x_ret_code     := 'E';
        x_ret_msg      := 'Error In RESETUSER: ' || SQLERRM || ' At ' || DBMS_UTILITY.format_error_backtrace;
        g_error_msg    := x_ret_msg;
        insert_error;
END resetuser;
-------------------------------------------------------------------------
BEGIN
   DBMS_APPLICATION_INFO.set_module ('CANON_E437_PHYINVCNT_PKG', NULL);
-------------------------------------------------------------------------
END CANON_E437_PHYINVCNT_PKG;
/
