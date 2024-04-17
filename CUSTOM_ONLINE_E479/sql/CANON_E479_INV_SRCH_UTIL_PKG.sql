CREATE OR REPLACE PACKAGE CANON_E479_INV_SRCH_UTIL_PKG
AS
   /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_INV_SRCH_UTIL_PKG.sql                                       *
     * DESCRIPTION     : This Package Contains LOV procedure for Invoice Search screen          *
     *                   Moved from multiple sources to one place                              *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 2/21/2017     Initial Creation
   *************************************************************************************************/

   TYPE RESULT_CURSOR IS REF CURSOR;

   lv_package_name VARCHAR2(30) := 'CANON_E479_INV_SRCH_UTIL_PKG';

	PROCEDURE get_customer_name_LOV(
		p_customer_name    IN       VARCHAR2,
		p_start_range      IN       NUMBER,
		p_end_range        IN       NUMBER,
		p_sort_by          IN       VARCHAR2,
		p_sort_type        IN       VARCHAR2,
		p_result_data            OUT RESULT_CURSOR,
		p_count                  OUT NUMBER,
		p_validation_status      OUT VARCHAR2,
		p_error_msg              OUT VARCHAR2
	);

	PROCEDURE get_bill_to_location_LOV(
		p_customer_number       IN       VARCHAR2,
		p_bill_location       IN       VARCHAR2,
		p_start_range     IN     NUMBER,
		p_end_range       IN     NUMBER,
		p_sort_by          in       varchar2,
		p_sort_type        in       varchar2,
		p_result_data              OUT RESULT_CURSOR,
		p_count            OUT      NUMBER,
		p_validation_status        OUT VARCHAR2,
		p_error_msg                OUT VARCHAR2
	);
	
   PROCEDURE get_parent_customer_LOV
                                    (p_parent_customer  IN     VARCHAR2,
                                     p_start_range    IN     NUMBER,
                                     p_end_range      IN     NUMBER,
                                     p_sort_by        IN     VARCHAR2,
                                     p_sort_type      IN     VARCHAR2,
                                     x_data              OUT result_cursor,
                                     x_count             OUT NUMBER);
									 
   PROCEDURE get_customer_group_LOV
                                    (p_customer_group  IN     VARCHAR2,
                                     p_start_range    IN     NUMBER,
                                     p_end_range      IN     NUMBER,
                                     x_data              OUT result_cursor,
                                     x_count             OUT NUMBER);			
END CANON_E479_INV_SRCH_UTIL_PKG;
/

SHOW ERRORS;

CREATE OR REPLACE PACKAGE BODY CANON_E479_INV_SRCH_UTIL_PKG
AS
   /************************************************************************************************
     *                                                                                          *
     * File NAME       : CANON_E479_INV_SRCH_UTIL_PKG.sql                                       *
     * DESCRIPTION     : This Package Contains LOV procedure for Invoice Search screen          *
     *                   Moved from multiple sources to one place                              *
     * REVISION HISTORY:                                                                        *
     *                                                                                          *
     * DEVELOPER         DATE           DESCRIPTION                                             *
     * -------------     -----------    ---------------------------                             *
     * Lakshmi Gopalsami 2/21/2017     Initial Creation
   *************************************************************************************************/
/* Customer Number LOV */

PROCEDURE get_customer_name_LOV(
    p_customer_name    IN      VARCHAR2,
	p_start_range      IN       NUMBER,
	p_end_range        IN       NUMBER,
	p_sort_by          IN       VARCHAR2,
	p_sort_type        IN       VARCHAR2,
    p_result_data            OUT RESULT_CURSOR,
	p_count                  OUT NUMBER,
	p_validation_status      OUT VARCHAR2,
	p_error_msg              OUT VARCHAR2
)
IS
	l_sql_common      varchar2 (3999) := ' ';
	l_sql_qry         varchar2(3999)  := ' ';
	l_sql_count_qry   varchar2(3999)  := ' ';
	l_sort_by varchar2(200) := ' ';
	l_sort_type varchar2(200) := ' ';
    l_procedure_name   VARCHAR2 (30) := 'get_customer_name_LOV';


BEGIN

       l_sql_qry :=
            'SELECT DISTINCT acct.DS_ACCT_NM customer_name, '
         || ' acct.DS_ACCT_NUM customer_number';
      l_sql_common := ' FROM DS_ACCT_CUST acct,  '
	  || ' CANON_E479_INV_SRCH_TBL srch '
	  || ' where 1=1  '
	  || ' AND acct.DS_ACCT_NM =  srch.customer_name   '
	  ||' AND ACCT.ezcancelflag = ''0'' '
	  ||' AND ACCT.glbl_cmpy_cd =''ADB'' ';
	
      IF (p_customer_name IS NOT NULL AND LENGTH (TRIM (p_customer_name)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and srch.DS_ACCT_NM like '''
            || TRIM (UPPER (p_customer_name))
            || '%''';
      END IF;


      l_sort_by := 'acct.ds_acct_nm';
      l_sort_type := 'asc';

      IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
      THEN
         IF (UPPER (p_sort_by) = 'CUSTOMER_NAME')
         THEN
            l_sort_by := 'acct.DS_ACCT_NM';
         ELSIF (UPPER (p_sort_by) = 'CUSTOMER_number')
         THEN
            l_sort_by := 'acct.DS_ACCT_NUM';
         END IF;
      ELSE
         l_sort_by := 'acct.DS_ACCT_NM';
      END IF;

      IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
      THEN
         l_sort_type := p_sort_type;
      ELSE
         l_sort_type := 'asc';
      END IF;

      l_sql_count_qry :=
         ' select count(*) from (' || l_sql_qry || ' ' || l_sql_common || ')';

      l_sql_qry :=
            'SELECT customer_name, customer_number, customer_number customer_id'
         || '  from ( select rownum row_number,temp.* from ('
         || l_sql_qry
         || l_sql_common
         || ' order by '
         || l_sort_by
         || ' '
         || l_sort_type
         || ')temp )'
         || '  WHERE row_number BETWEEN '
         || p_start_range
         || ' AND '
         || p_end_range;

      DBMS_OUTPUT.put_line ('query ' || l_sql_qry);


      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      EXECUTE IMMEDIATE l_sql_count_qry INTO p_count;

      OPEN p_result_data FOR l_sql_qry;

EXCEPTION
  WHEN OTHERS THEN
    OPEN p_result_data FOR SELECT '1' FROM DUAL WHERE 1=2;
	p_count := 0;
	p_validation_status := 'E';
   p_error_msg := SUBSTR(SQLERRM,1,2000);
END get_customer_name_LOV;

/* Bill-to Location LOV */

PROCEDURE get_bill_to_location_LOV(
    p_customer_number       IN       VARCHAR2,
	p_bill_location       IN       VARCHAR2,
	p_start_range     IN     NUMBER,
	p_end_range       IN     NUMBER,
	p_sort_by          in       varchar2,
	p_sort_type        in       varchar2,
    p_result_data              OUT RESULT_CURSOR,
	p_count            OUT      NUMBER,
	p_validation_status        OUT VARCHAR2,
	p_error_msg                OUT VARCHAR2
)
IS
	l_sql_common      varchar2 (3999) := ' ';
	l_sql_qry         varchar2(3999)  := ' ';
	l_sql_count_qry   varchar2(3999)  := ' ';
	l_sort_by varchar2(200) := ' ';
	l_sort_type varchar2(200) := ' ';
	 l_procedure_name   VARCHAR2 (30) := 'get_bill_to_location_LOV';

BEGIN
      l_sql_qry :=
            l_sql_qry
         || 'select distinct acct.ds_acct_nm customer_name, '
		 || ' acct.DS_ACCT_NUM CUSTOMER_NUMBER , '
		 || ' bill_to.LOC_NUM location , '
		 || ' bill_to.FIRST_LINE_ADDR address1, '
		 || ' bill_to.SCD_LINE_ADDR address2, '
		 || ' bill_to.CTY_ADDR city, '
		 || ' bill_to.ST_CD state, '
		 || ' bill_to.POST_CD postal_code ';
      l_sql_common :=
            l_sql_common
         || ' FROM '
         || ' DS_ACCT_CUST ACCT, '
         || ' CANON_E479_INV_SRCH_TBL srch, '
         || ' ACCT_LOC LOC, '
         || ' BILL_TO_CUST BILL_TO '
         || ' WHERE ACCT.DS_ACCT_NUM = LOC.DS_ACCT_NUM '
         || ' AND LOC.PTY_LOC_PK = BILL_TO.PTY_LOC_PK '
         || ' AND LOC.LOC_NUM = BILL_TO.LOC_NUM '
         || ' AND BILL_TO.LOC_NUM = srch.bill_location '
		 ||' AND ACCT.ezcancelflag = ''0'' '
		 ||' AND ACCT.glbl_cmpy_cd =''ADB'' '
		 ||' AND loc.ezcancelflag = ACCT.ezcancelflag '
		 ||' AND loc.glbl_cmpy_cd = ACCT.glbl_cmpy_cd '
		 ||' AND loc.ezcancelflag = bill_to.ezcancelflag '
		 ||' AND loc.glbl_cmpy_cd = bill_to.glbl_cmpy_cd '
		 ||' AND acct.ds_acct_nm = srch.customer_name '
		 ;


      IF (p_bill_location IS NOT NULL AND LENGTH (TRIM (p_bill_location)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and srch.bill_location = '''
            || TRIM (p_bill_location)
            || '''';
      END IF;

      IF (p_customer_number IS NOT NULL AND LENGTH (TRIM (p_customer_number)) > 0)
      THEN
         l_sql_common :=
               l_sql_common
            || '  and  acct.ds_acct_num like '''
            || TRIM (UPPER (p_customer_number))
            || '%''';
      END IF;



      IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
      THEN
         IF (UPPER (p_sort_by) = 'CUSTOMER_NAME')
         THEN
            l_sort_by := 'acct.ds_acct_nm';
         ELSIF (UPPER (p_sort_by) = 'LOCATION')
         THEN
            l_sort_by := 'bill_to.bill_to_cust_cd';
         ELSIF (UPPER (p_sort_by) = 'ADDRESS1')
         THEN
            l_sort_by := 'bill_to.FIRST_LINE_ADDR';
         END IF;
      ELSE
         l_sort_by := 'acct.ds_acct_nm';
      END IF;

      IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
      THEN
         l_sort_type := p_sort_type;
      ELSE
         l_sort_type := 'asc';
      END IF;

      l_sql_count_qry := 'select count(*) from ('||    l_sql_qry        || l_sql_common||')';
	  

      l_sql_qry :=
            'SELECT customer_name, location, address1, address2,city,state,postal_code'
         || '  from ( select rownum row_number,temp.* from ('
         || l_sql_qry
         || l_sql_common
         || ' order by '
         || l_sort_by
         || ' '
         || l_sort_type
         || ')temp )'
         || '  WHERE row_number BETWEEN '
         || p_start_range
         || ' AND '
         || p_end_range;

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);
      dbms_output.put_line(' Count Query: '||l_sql_count_qry );
	  dbms_output.put_line(' SQL Query to be executed: '||l_sql_qry );
													 
      EXECUTE IMMEDIATE l_sql_count_qry INTO p_count;

      OPEN p_result_data FOR l_sql_qry;
	  
     p_validation_status := 'S';
     p_error_msg := NULL;

EXCEPTION
  WHEN OTHERS THEN
   p_count := 0;
   OPEN p_result_data FOR SELECT '1' FROM DUAL WHERE 1=2;
   p_validation_status := 'E';
   p_error_msg := SUBSTR(SQLERRM,1,2000);
END get_bill_to_location_LOV;   


  PROCEDURE get_parent_customer_LOV
                                    (p_parent_customer  IN     VARCHAR2,
                                     p_start_range    IN     NUMBER,
                                     p_end_range      IN     NUMBER,
                                     p_sort_by        IN     VARCHAR2,
                                     p_sort_type      IN     VARCHAR2,
                                     x_data              OUT result_cursor,
                                     x_count             OUT NUMBER)
   AS
      l_procedure_name   VARCHAR2 (30) := 'get_parent_customer';
      l_sql_common       VARCHAR2 (3999) := ' ';
      l_sql_qry          VARCHAR2 (3999) := ' ';
      l_sql_count_qry    VARCHAR2 (3999) := ' ';
      l_sort_by          VARCHAR2 (200) := ' ';
      l_sort_type        VARCHAR2 (200) := ' ';
   BEGIN
      /* S21 REPLACEMENT */
       l_sql_qry :=
         'SELECT DISTINCT '
			||'acct.ds_acct_nm customer_name, acct.ds_accT_num customer_number '
			||'FROM ds_acct_reln reln, DS_ACCT_CUST acct , canon_e479_inv_srch_tbl srch'
			--||'WHERE     reln.ds_acct_reln_tp_cd = 2 '
			||'WHERE     reln.ds_acct_reln_tp_cd = 1 ' -- 3/30/17. change as per inputs from Kohei Aratani
			||'AND reln.ds_acct_num <> reln.reln_ds_acct_num '
			||'AND reln.reln_ds_acct_num = acct.ds_acct_num '
			||' AND ACCT.ezcancelflag = ''0'' '
			||' AND ACCT.glbl_cmpy_cd =''ADB'' '
			||' AND loc.ezcancelflag = ACCT.ezcancelflag '
			||' AND loc.glbl_cmpy_cd = ACCT.glbl_cmpy_cd '
			||'AND acct.ds_acct_nm = srch.parent_customer_name ';

      IF (p_parent_customer IS NOT NULL AND LENGTH (TRIM (p_parent_customer)) > 0)
      THEN
         l_sql_qry :=
               l_sql_qry
            || '  and acct.DS_ACCT_NM like '''
            || TRIM (UPPER (p_parent_customer))
            || '%''';
      END IF;


      l_sort_by := 'acct.ds_acct_nm';
      l_sort_type := 'asc';

      IF (p_sort_by IS NOT NULL AND LENGTH (TRIM (p_sort_by)) > 0)
      THEN
         IF (UPPER (p_sort_by) = 'CUSTOMER_NAME')
         THEN
            l_sort_by := 'acct.DS_ACCT_NM';
         ELSIF (UPPER (p_sort_by) = 'CUSTOMER_number')
         THEN
            l_sort_by := 'acct.DS_ACCT_NUM';
         END IF;
      ELSE
         l_sort_by := 'acct.DS_ACCT_NM';
      END IF;

      IF (p_sort_type IS NOT NULL AND LENGTH (TRIM (p_sort_type)) > 0)
      THEN
         l_sort_type := p_sort_type;
      ELSE
         l_sort_type := 'asc';
      END IF;

      l_sql_count_qry :=
         ' select count(*) from (' || l_sql_qry || ')';

      l_sql_qry :=
            'SELECT customer_name, customer_number '
         || '  from ( select rownum row_number,temp.* from ('
         || l_sql_qry
         || l_sql_common
         || ' order by '
         || l_sort_by
         || ' '
         || l_sort_type
         || ')temp )'
         || '  WHERE row_number BETWEEN '
         || p_start_range
         || ' AND '
         || p_end_range;

      DBMS_OUTPUT.put_line ('query ' || l_sql_qry);


      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN x_data FOR l_sql_qry;


   EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_parent_customer,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, '1'
              FROM DUAL
             WHERE 1 = -1;

         x_count := 0;
   END get_parent_customer_LOV;
   
   PROCEDURE get_customer_group_LOV
                                    (p_customer_group  IN     VARCHAR2,
                                     p_start_range    IN     NUMBER,
                                     p_end_range      IN     NUMBER,
                                     x_data              OUT result_cursor,
                                     x_count             OUT NUMBER)
IS
  l_procedure_name   VARCHAR2 (30) := 'get_customer_group';
      l_sql_common       VARCHAR2 (3999) := ' ';
      l_sql_qry          VARCHAR2 (3999) := ' ';
      l_sql_count_qry    VARCHAR2 (3999) := ' ';
      l_sort_by          VARCHAR2 (200) := ' ';
      l_sort_type        VARCHAR2 (200) := ' ';
BEGIN
 
		   l_sql_qry := 
		         'SELECT DISTINCT GRP.ds_acct_grp_nm group_name '
				||'FROM '
				||' canon_e479_inv_srch_tbl srch, '
				||' ds_acct_grp grp '
				||' WHERE 1=1 '
				||' AND grp.ezcancelflag =''0'' '
				||' AND grp.glbl_cmpy_cd =''ADB'' '
				||' AND grp.ds_acct_grp_nm = srch.DS_ACCT_GRP_NM ';
				
													 

      IF (p_customer_group IS NOT NULL AND LENGTH (TRIM (p_customer_group)) > 0)
      THEN
         l_sql_qry :=
               l_sql_qry
            || '  and srch.DS_ACCT_GRP_NM like '''
            || TRIM (UPPER (p_customer_group))
            || '%''';
      END IF;


      l_sort_by := 'GRP.ds_acct_grp_nm ';
      l_sort_type := 'asc';

      l_sql_count_qry :=
         ' select count(*) from (' || l_sql_qry ||')';

      l_sql_qry :=
            'SELECT group_name '
         || '  from ( select rownum row_number,temp.* from ('
         || l_sql_qry
         || ' order by '
         || l_sort_by
         || ' '
         || l_sort_type
         || ')temp )'
         || '  WHERE row_number BETWEEN '
         || p_start_range
         || ' AND '
         || p_end_range;

      DBMS_OUTPUT.put_line ('query ' || l_sql_qry);


      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_count_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      CANON_E479_CUST_BILL_UTIL_PKG.log_dynamic_sql (lv_package_name,
                                                     l_procedure_name,
                                                     l_sql_qry,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL,
                                                     NULL);

      EXECUTE IMMEDIATE l_sql_count_qry INTO x_count;

      OPEN x_data FOR l_sql_qry;
EXCEPTION
      WHEN OTHERS
      THEN
         CANON_E479_CUST_BILL_UTIL_PKG.log_error (lv_package_name,
                                                  l_procedure_name,
                                                  'SQL',
                                                  p_customer_group,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  NULL,
                                                  SQLERRM);

         OPEN x_data FOR
            SELECT 1, '1'
              FROM DUAL
             WHERE 1 = -1;

         x_count := 0;
END get_customer_group_LOV;


END CANON_E479_INV_SRCH_UTIL_PKG;
/

SHOW ERRORS;