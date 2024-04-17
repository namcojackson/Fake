CREATE OR REPLACE PACKAGE canon_e404_rd143_contdtl_pkg
AS

PROCEDURE extract_contdtl(
   x_errbuf          OUT VARCHAR2
  ,x_retcode         OUT VARCHAR2
);

PROCEDURE get_contdtl(
   x_cursor OUT sys_refcursor
);

PROCEDURE get_contdtl_delete_data(
   x_cursor       OUT      sys_refcursor
);

end CANON_E404_RD143_CONTDTL_PKG;
/

CREATE OR REPLACE PACKAGE BODY canon_e404_rd143_contdtl_pkg
AS
   G_PACKAGE_NAME        VARCHAR2 (5000) := 'CANON_E404_RD143_CONTDTL_PKG';
   

PROCEDURE extract_contdtl(
   x_errbuf          OUT VARCHAR2
  ,x_retcode         OUT VARCHAR2
)
IS
   l_procedure_name        VARCHAR2 (30);
   xSysRefCursor	sys_refcursor;
   
BEGIN
   l_procedure_name := 'extract_contdtl';
   
   MERGE
    INTO canon_e404_rd143_contdtl_tbl e404_main
   USING(SELECT DISTINCT
                rd143.aggregate_number
               ,rd143.base_cycle
               ,rd143.baseamount_percycle base_price
               ,rd143.billto_address1
               ,rd143.billto_address2
               ,rd143.billto_city
               ,rd143.bill_to_party billto_party
               ,rd143.billto_postalcode
               ,rd143.billto_state
               ,rd143.branch
               ,rd143.contract_id
               ,rd143.contract_number
               ,rd143.contract_number_modifier
               ,rd143.contract_status
               ,rd143.cont_end_date
               ,rd143.cont_start_date
               ,rd143.fleet
               ,rd143.instance_id
               ,rd143.line_description
               ,rd143.line_end_date
               ,rd143.line_number
               ,rd143.line_price
               ,rd143.line_start_date
               ,rd143.line_status
               ,rd143.line_term_date
               ,rd143.meter_collection_method
               ,rd143.order_number
               ,rd143.po_number
               ,e404.salesforce_contdtl_id salesforce_contdtl_id
               ,NULL salesforce_ib_id
               ,rd143.serial_number
               ,rd143.service_category
               ,rd143.shipto_address1
               ,rd143.shipto_address2
               ,rd143.shipto_city
               ,rd143.shipto_party
               ,rd143.shipto_postalcode
               ,rd143.shipto_state
               ,rd143.staples_incl
               ,NULL status_flag
               ,NULL status_message
               ,rd143.tier1_allowance
               ,rd143.tier1_rate
               ,rd143.tier2_allowance
               ,rd143.tier2_rate
               ,rd143.tier3_allowance
               ,rd143.tier3_rate
               ,rd143.tier4_allowance
               ,rd143.tier4_rate
--               ,rd143.contract_number||rd143.serial_number||rd143.line_number unique_oracle_id
               ,rd143.contract_number||rd143.instance_id||rd143.billing_cntr_id unique_oracle_id
               ,rd143.usage_cycle
               ,rd143.billing_cntr_id
               ,rd143.billing_cntr_desc
           FROM canon_rd143_cont_mach_tbl    rd143
               ,canon_e404_rd143_contdtl_tbl e404
          WHERE 1 = 1
            --AND rd143.fleet = 'N'
            --AND rd143.fleet IN ('N','A')
            AND TRUNC(SYSDATE) BETWEEN rd143.cont_start_date AND NVL(rd143.cont_end_date, TRUNC(SYSDATE))
            AND UPPER(rd143.contract_status) = 'EFFECTIVE'
            AND UPPER(rd143.line_status) NOT IN ('EXPIRED', 'CANCELLED', 'TERMINATED')
            AND TRUNC(SYSDATE) BETWEEN rd143.line_start_date AND NVL(rd143.line_end_date, TRUNC(SYSDATE))
            --may not need in S21 world ??
           -- AND rd143.subline_status NOT IN ('EXPIRED', 'CANCELLED', 'TERMINATED')
           -- AND TRUNC (SYSDATE) BETWEEN rd143.subline_start_date AND NVL (rd143.subline_end_date, TRUNC (SYSDATE))
--            AND(rd143.contract_number||rd143.serial_number||rd143.line_number)
            AND rd143.contract_number  = e404.contract_number(+)
            AND rd143.instance_id      = e404.instance_id(+)
--            AND rd143.line_number      = e404.line_number(+)
                AND rd143.billing_cntr_id      = e404.billing_cntr_id(+)
            AND(     NVL(rd143.aggregate_number, '-1')                  != NVL(e404.aggregate_number, '-1' )
                  OR NVL(rd143.base_cycle, '-1')                        != NVL(e404.base_cycle, '-1' )
                  OR NVL(rd143.baseamount_percycle, -1)                 != NVL(e404.base_price, -1 )
                  OR NVL(rd143.billto_address1, '-1')                   != NVL(e404.billto_address1, '-1' )
                  OR NVL(rd143.billto_address2, '-1')                   != NVL(e404.billto_address2, '-1' )
                  OR NVL(rd143.billto_city, '-1')                       != NVL(e404.billto_city, '-1' )
                  OR NVL(rd143.bill_to_party, '-1')                     != NVL(e404.billto_party, '-1' )
                  OR NVL(rd143.billto_postalcode, '-1')                 != NVL(e404.billto_postalcode, '-1' )
                  OR NVL(rd143.billto_state, '-1')                      != NVL(e404.billto_state, '-1' )
                  OR NVL(rd143.branch, '-1')                            != NVL(e404.branch, '-1' )
                  OR NVL(rd143.contract_id, -1)                         != NVL(e404.contract_id, -1 )
                  OR NVL(rd143.contract_number, '-1')                   != NVL(e404.contract_number, '-1' )
                  OR NVL(rd143.contract_number_modifier, '-1')          != NVL(e404.contract_number_modifier, '-1' )
                  OR NVL(rd143.contract_status, '-1')                   != NVL(e404.contract_status, '-1' )
                  OR NVL(rd143.cont_end_date, TO_DATE('31-DEC-9999'))   != NVL(e404.cont_end_date, TO_DATE('31-DEC-9999'))
                  OR NVL(rd143.cont_start_date, TO_DATE('31-DEC-9999')) != NVL(e404.cont_start_date, TO_DATE('31-DEC-9999'))
                  OR NVL(rd143.fleet, '-1')                             != NVL(e404.fleet, '-1' )
                  OR NVL(rd143.instance_id, -1)                         != NVL(e404.instance_id, -1 )
                  OR NVL(rd143.line_description, '-1')                  != NVL(e404.line_description, '-1' )
                  OR NVL(rd143.line_end_date, TO_DATE('31-DEC-9999'))   != NVL(e404.line_end_date, TO_DATE('31-DEC-9999'))
                  OR NVL(rd143.line_number, '-1')                       != NVL(e404.line_number, '-1' )
                  OR NVL(rd143.line_price, -1)                          != NVL(e404.line_price, -1 )
                  OR NVL(rd143.line_start_date, TO_DATE('31-DEC-9999')) != NVL(e404.line_start_date, TO_DATE('31-DEC-9999'))
                  OR NVL(rd143.line_status, '-1')                       != NVL(e404.line_status, '-1' )
                  OR NVL(rd143.line_term_date, TO_DATE('31-DEC-9999'))  != NVL(e404.line_term_date, TO_DATE('31-DEC-9999'))
                  OR NVL(rd143.meter_collection_method, '-1')           != NVL(e404.meter_collection_method, '-1' )
                  OR NVL(rd143.order_number, -1)                        != NVL(e404.order_number, -1 )
                  OR NVL(rd143.po_number, '-1')                         != NVL(e404.po_number, '-1' )
                  OR NVL(rd143.serial_number, '-1')                     != NVL(e404.serial_number, '-1' )
                  OR NVL(rd143.service_category, '-1')                  != NVL(e404.service_category, '-1' )
                  OR NVL(rd143.shipto_address1, '-1')                   != NVL(e404.shipto_address1, '-1' )
                  OR NVL(rd143.shipto_address2, '-1')                   != NVL(e404.shipto_address2, '-1' )
                  OR NVL(rd143.shipto_city, '-1')                       != NVL(e404.shipto_city, '-1' )
                  OR NVL(rd143.shipto_party, '-1')                      != NVL(e404.shipto_party, '-1' )
                  OR NVL(rd143.shipto_postalcode, '-1')                 != NVL(e404.shipto_postalcode, '-1' )
                  OR NVL(rd143.shipto_state, '-1')                      != NVL(e404.shipto_state, '-1' )
                  OR NVL(rd143.staples_incl, '-1')                      != NVL(e404.staples_incl, '-1' )
                  OR NVL(rd143.tier1_allowance, '-1')                   != NVL(e404.tier1_allowance, '-1' )
                  OR NVL(rd143.tier1_rate, '-1')                        != NVL(e404.tier1_rate, '-1' )
                  OR NVL(rd143.tier2_allowance, '-1')                   != NVL(e404.tier2_allowance, '-1' )
                  OR NVL(rd143.tier2_rate, '-1')                        != NVL(e404.tier2_rate, '-1' )
                  OR NVL(rd143.tier3_allowance, '-1')                   != NVL(e404.tier3_allowance, '-1' )
                  OR NVL(rd143.tier3_rate, '-1')                        != NVL(e404.tier3_rate, '-1' )
                  OR NVL(rd143.tier4_allowance, '-1')                   != NVL(e404.tier4_allowance, '-1' )
                  OR NVL(rd143.tier4_rate, '-1')                        != NVL(e404.tier4_rate, '-1' )
                  OR NVL(rd143.usage_cycle, '-1')                       != NVL(e404.usage_cycle, '-1' )
                  OR NVL(rd143.billing_cntr_id, '-1')                   != NVL(e404.billing_cntr_id, '-1')
                  OR NVL(rd143.billing_cntr_desc, '-1')                 != NVL(e404.billing_cntr_desc,'-1')
               )
        ) rd143
      ON(e404_main.unique_oracle_id = rd143.unique_oracle_id
        )
    WHEN MATCHED THEN
  UPDATE
     --SET aggregate_number         = NVL(rd143.aggregate_number, e404_main.aggregate_number)
     SET aggregate_number         = DECODE(rd143.fleet,'A',NVL(rd143.aggregate_number, e404_main.aggregate_number),NULL)
        ,base_cycle               = NVL(rd143.base_cycle, e404_main.base_cycle)
        ,base_price               = NVL(rd143.base_price, e404_main.base_price)
        ,billto_address1          = NVL(rd143.billto_address1, e404_main.billto_address1)
        ,billto_address2          = NVL(rd143.billto_address2, e404_main.billto_address2)
        ,billto_city              = NVL(rd143.billto_city, e404_main.billto_city)
        ,billto_party             = NVL(rd143.billto_party, e404_main.billto_party)
        ,billto_postalcode        = NVL(rd143.billto_postalcode, e404_main.billto_postalcode)
        ,billto_state             = NVL(rd143.billto_state, e404_main.billto_state)
        ,branch                   = NVL(rd143.branch, e404_main.branch)
        ,contract_id              = NVL(rd143.contract_id, e404_main.contract_id)
        ,contract_number          = NVL(rd143.contract_number, e404_main.contract_number)
        ,contract_number_modifier = NVL(rd143.contract_number_modifier, e404_main.contract_number_modifier)
        ,contract_status          = NVL(rd143.contract_status, e404_main.contract_status)
        ,cont_end_date            = NVL(rd143.cont_end_date, e404_main.cont_end_date)
        ,cont_start_date          = NVL(rd143.cont_start_date, e404_main.cont_start_date)
        ,fleet                    = NVL(rd143.fleet, e404_main.fleet)
        ,instance_id              = NVL(rd143.instance_id, e404_main.instance_id)
        ,last_updated_by          = null --G_LAST_UPDATED_BY
        ,last_update_date         = SYSDATE
        ,line_description         = NVL(rd143.line_description, e404_main.line_description)
        ,line_end_date            = NVL(rd143.line_end_date, e404_main.line_end_date)
        ,line_number              = NVL(rd143.line_number, e404_main.line_number)
        ,line_price               = NVL(rd143.line_price, e404_main.line_price)
        ,line_start_date          = NVL(rd143.line_start_date, e404_main.line_start_date)
        ,line_status              = NVL(rd143.line_status, e404_main.line_status)
        ,line_term_date           = NVL(rd143.line_term_date, e404_main.line_term_date)
        ,meter_collection_method  = NVL(rd143.meter_collection_method, e404_main.meter_collection_method)
        ,order_number             = NVL(rd143.order_number, e404_main.order_number)
        ,po_number                = NVL(rd143.po_number, e404_main.po_number)
        ,salesforce_contdtl_id    = NVL(rd143.salesforce_contdtl_id, e404_main.salesforce_contdtl_id)
        ,serial_number            = NVL(rd143.serial_number, e404_main.serial_number)
        ,service_category         = NVL(rd143.service_category, e404_main.service_category)
        ,shipto_address1          = NVL(rd143.shipto_address1, e404_main.shipto_address1)
        ,shipto_address2          = NVL(rd143.shipto_address2, e404_main.shipto_address2)
        ,shipto_city              = NVL(rd143.shipto_city, e404_main.shipto_city)
        ,shipto_party             = NVL(rd143.shipto_party, e404_main.shipto_party)
        ,shipto_postalcode        = NVL(rd143.shipto_postalcode, e404_main.shipto_postalcode)
        ,shipto_state             = NVL(rd143.shipto_state, e404_main.shipto_state)
        ,staples_incl             = NVL(rd143.staples_incl, e404_main.staples_incl)
        ,status_flag              = 'U'
        ,status_message           = 'Identified for UPDATE'
        ,tier1_allowance          = NVL(rd143.tier1_allowance, e404_main.tier1_allowance)
        ,tier1_rate               = NVL(rd143.tier1_rate, e404_main.tier1_rate)
        ,tier2_allowance          = NVL(rd143.tier2_allowance, e404_main.tier2_allowance)
        ,tier2_rate               = NVL(rd143.tier2_rate, e404_main.tier2_rate)
        ,tier3_allowance          = NVL(rd143.tier3_allowance, e404_main.tier3_allowance)
        ,tier3_rate               = NVL(rd143.tier3_rate, e404_main.tier3_rate)
        ,tier4_allowance          = NVL(rd143.tier4_allowance, e404_main.tier4_allowance)
        ,tier4_rate               = NVL(rd143.tier4_rate, e404_main.tier4_rate)
        ,usage_cycle              = NVL(rd143.usage_cycle, e404_main.usage_cycle)
        ,billing_cntr_id          = NVL(rd143.billing_cntr_id, e404_main.billing_cntr_id)
        ,billing_cntr_desc        = NVL(rd143.billing_cntr_desc, e404_main.billing_cntr_desc)
    WHEN NOT MATCHED THEN
  INSERT
        (aggregate_number
        ,base_cycle
        ,base_price
        ,billto_address1
        ,billto_address2
        ,billto_city
        ,billto_party
        ,billto_postalcode
        ,billto_state
        ,branch
        ,contract_id
        ,contract_number
        ,contract_number_modifier
        ,contract_status
        ,cont_end_date
        ,cont_start_date
        ,fleet
        ,instance_id
        ,created_by
        ,creation_date
        ,last_updated_by
        ,last_update_date
        ,line_description
        ,line_end_date
        ,line_number
        ,line_price
        ,line_start_date
        ,line_status
        ,line_term_date
        ,meter_collection_method
        ,order_number
        ,po_number
        ,salesforce_contdtl_id
        ,serial_number
        ,service_category
        ,shipto_address1
        ,shipto_address2
        ,shipto_city
        ,shipto_party
        ,shipto_postalcode
        ,shipto_state
        ,staples_incl
        ,status_flag
        ,status_message
        ,tier1_allowance
        ,tier1_rate
        ,tier2_allowance
        ,tier2_rate
        ,tier3_allowance
        ,tier3_rate
        ,tier4_allowance
        ,tier4_rate
        ,usage_cycle
        ,unique_oracle_id
        ,billing_cntr_id
        ,billing_cntr_desc
        )
  VALUES
        (DECODE(rd143.fleet,'A',rd143.aggregate_number,NULL)
        ,rd143.base_cycle
        ,rd143.base_price
        ,rd143.billto_address1
        ,rd143.billto_address2
        ,rd143.billto_city
        ,rd143.billto_party
        ,rd143.billto_postalcode
        ,rd143.billto_state
        ,rd143.branch
        ,rd143.contract_id
        ,rd143.contract_number
        ,rd143.contract_number_modifier
        ,rd143.contract_status
        ,rd143.cont_end_date
        ,rd143.cont_start_date
        ,rd143.fleet
        ,rd143.instance_id
        ,null --G_CREATED_BY
        ,SYSDATE
        ,null --G_LAST_UPDATED_BY
        ,SYSDATE
        ,rd143.line_description
        ,rd143.line_end_date
        ,rd143.line_number
        ,rd143.line_price
        ,rd143.line_start_date
        ,rd143.line_status
        ,rd143.line_term_date
        ,rd143.meter_collection_method
        ,rd143.order_number
        ,rd143.po_number
        ,NULL
        ,rd143.serial_number
        ,rd143.service_category
        ,rd143.shipto_address1
        ,rd143.shipto_address2
        ,rd143.shipto_city
        ,rd143.shipto_party
        ,rd143.shipto_postalcode
        ,rd143.shipto_state
        ,rd143.staples_incl
        ,'I'
        ,'Identified for Insert.'        
        ,rd143.tier1_allowance
        ,rd143.tier1_rate
        ,rd143.tier2_allowance
        ,rd143.tier2_rate
        ,rd143.tier3_allowance
        ,rd143.tier3_rate
        ,rd143.tier4_allowance
        ,rd143.tier4_rate
        ,rd143.usage_cycle
        ,rd143.unique_oracle_id
        ,rd143.billing_cntr_id
        ,rd143.billing_cntr_desc
        );
   dbms_output.put_line('After BIG Merge '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'After BIG Merge '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
   
   MERGE 
    INTO canon_e404_rd143_contdtl_tbl e404_cont_main
   USING(SELECT DISTINCT
                e404_cont.unique_oracle_id
               ,e404_ib.salesforce_ib_id
           FROM canon_e404_rd143_contdtl_tbl e404_cont
               ,canon_e404_install_base_ob_tbl e404_ib
          WHERE e404_cont.status_flag IN ('I', 'E', 'U')
            AND e404_cont.instance_id = e404_ib.instance_id
            AND e404_ib.salesforce_ib_id IS NOT NULL
        ) e404_ib_main
      ON(e404_cont_main.unique_oracle_id = e404_ib_main.unique_oracle_id)
    WHEN MATCHED THEN
  UPDATE
     SET e404_cont_main.salesforce_ib_id = e404_ib_main.salesforce_ib_id
        ,last_updated_by = null --G_LAST_UPDATED_BY
        ,last_update_date = SYSDATE;
   dbms_output.put_line('After Small Merge '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'After Small Merge '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
    
   UPDATE canon_e404_rd143_contdtl_tbl
      SET status_flag = 'E'
         ,status_message = 'Missing Salesforce IB ID.'
         ,last_updated_by = null --G_LAST_UPDATED_BY
         ,last_update_date = SYSDATE
    WHERE status_flag IN('I', 'E', 'U')
      AND salesforce_ib_id IS NULL;
   dbms_output.put_line('After Update '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
    canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'After Update '||TO_CHAR(SYSDATE, 'DD-MON-YYYY HH24:MI:SS'));
    
   COMMIT;
   
   get_contdtl_delete_data(xSysRefCursor);
   
   x_retcode := 'S';
   
   
EXCEPTION
   WHEN OTHERS THEN
   canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
--      log_error_message(
--         p_in_package_name   => G_PACKAGE_NAME
--        ,p_in_process_name   => l_procedure_name
--        ,p_in_key1           => G_REQUEST_ID
--        ,p_in_key2           => NULL
--        ,p_in_key3           => NULL
--        ,p_in_key4           => NULL
--        ,p_in_key5           => NULL
--        ,p_in_error_msg      => SQLERRM
--        ,p_in_error_date     => SYSDATE
--      );
      x_errbuf := SQLERRM;
      x_retcode := '2';

END;

PROCEDURE get_contdtl(
   x_cursor OUT sys_refcursor
)
AS
   l_procedure_name VARCHAR2(60) := 'get_contdtl';
   l_request_id     NUMBER;
   l_error_message  VARCHAR2(2000);
BEGIN

--    BEGIN
--        SELECT request_id
--          INTO l_request_id
--          FROM fnd_conc_req_summary_v
--         WHERE user_concurrent_program_name LIKE 'Canon: E404 SFDC Bulk Upload Program'
--         AND argument_text LIKE '%CanonE404ContractDetailHandler%'
--           AND SYSDATE BETWEEN actual_start_date AND NVL(actual_completion_date, SYSDATE);
--    EXCEPTION
--        WHEN OTHERS THEN
--        l_request_id := TO_NUMBER(TO_CHAR(SYSDATE, 'YYYYMMDDHH24MI'));
--    END;

   OPEN x_cursor
    FOR
 SELECT aggregate_number
       ,base_cycle
       ,TO_CHAR(base_price) base_price
       ,billto_address1
       ,billto_address2
       ,billto_city
       ,billto_party
       ,billto_postalcode
       ,billto_state
       ,branch
       ,TO_CHAR(contract_id) contract_id
       ,contract_number
       ,contract_number_modifier
       ,contract_status
       --,TO_DATE(cont_end_date, 'YYYY-MM-DD') cont_end_date
       --,TO_DATE(cont_start_date, 'YYYY-MM-DD') cont_start_date
       ,cont_end_date
       ,cont_start_date
       ,fleet
       ,fleet_serial_number
       --,TO_CHAR(instance_id) instance_id
       ,line_description
       --,TO_DATE(line_end_date, 'YYYY-MM-DD') line_end_date
       ,line_end_date
       ,TO_CHAR(line_number) line_number
       ,TO_CHAR(line_price) line_price
       --,TO_DATE(line_start_date, 'YYYY-MM-DD') line_start_date 
       ,line_start_date
       ,line_status
       --,TO_DATE(line_term_date, 'YYYY-MM-DD') line_term_date
       ,line_term_date
       ,meter_collection_method
       ,TO_CHAR(order_number) order_number
       ,po_number
       ,salesforce_ib_id
       --,salesforce_contdtl_id
       ,serial_number
       ,service_category
       ,shipto_address1
       ,shipto_address2
       ,shipto_city
       ,shipto_party
       ,shipto_postalcode
       ,shipto_state
       ,staples_incl
       ,tier1_allowance
       ,tier1_rate
       ,tier2_allowance
       ,tier2_rate
       ,tier3_allowance
       ,tier3_rate
       ,tier4_allowance
       ,tier4_rate
       ,usage_cycle
       ,unique_oracle_id
   FROM canon_e404_rd143_contdtl_tbl
  WHERE status_flag IN ('I', 'U', 'E')
    AND salesforce_ib_id IS NOT NULL
  ORDER BY salesforce_ib_id;

EXCEPTION
   WHEN OTHERS THEN
      OPEN x_cursor
       FOR
    SELECT 1
      FROM canon_e404_rd143_contdtl_tbl
     WHERE 1 = 2;
     canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
--      l_error_message := SQLERRM;
--      log_error_message(
--        p_in_package_name      =>    'CANON_E404_RD143_CONTDTL_PKG',
--         p_in_process_name      =>    l_procedure_name,
--         p_in_key1              =>    l_request_id,
--         p_in_key2              =>    NULL,
--         p_in_key3              =>    NULL,
--         p_in_key4              =>    NULL,
--         p_in_key5              =>    NULL,
--         p_in_error_msg         =>    SQLERRM,
--         p_in_error_date        =>    SYSDATE
--      );
END get_contdtl;


PROCEDURE get_contdtl_delete_data(
   x_cursor       OUT      sys_refcursor
)
IS
   l_procedure_name VARCHAR2(60) := 'get_contdtl_delete_data';        
BEGIN

MERGE INTO canon_e404_rd143_to_del_tbl a
   USING(SELECT distinct e404.contract_number
         ,e404.contract_number_modifier
         ,e404.contract_id
         ,e404.contract_status
         ,e404.cont_start_date
         ,e404.cont_end_date
         ,e404.fleet
         ,e404.fleet_serial_number
         ,e404.branch
         ,e404.service_category
         ,e404.instance_id
         ,e404.serial_number
         ,e404.flee_serial_number
         ,e404.aggregate_number
         ,e404.staples_incl
         ,e404.line_number
         ,e404.line_description
         ,e404.line_start_date
         ,e404.line_end_date
         ,e404.line_term_date
         ,e404.line_status
         ,e404.billto_party
         ,e404.billto_address1
         ,e404.billto_address2
         ,e404.billto_city
         ,e404.billto_postalcode
         ,e404.billto_state
         ,e404.shipto_party
         ,e404.shipto_address1
         ,e404.shipto_address2
         ,e404.shipto_city
         ,e404.shipto_postalcode
         ,e404.shipto_state
         ,e404.line_price
         ,e404.base_price
         ,e404.base_cycle
         ,e404.meter_collection_method
         ,e404.order_number
         ,e404.po_number
         ,e404.usage_cycle
         ,e404.tier1_allowance
         ,e404.tier1_rate
         ,e404.tier2_allowance
         ,e404.tier2_rate
         ,e404.tier3_allowance
         ,e404.tier3_rate
         ,e404.tier4_allowance
         ,e404.tier4_rate
         ,e404.salesforce_ib_id
         ,e404.unique_oracle_id
         ,e404.salesforce_contdtl_id
         ,(CASE WHEN e404.salesforce_contdtl_id IS NOT NULL THEN
                  'D'||e404.status_flag
                 ELSE
                  'D'||e404.status_flag||'P'
            END
          ) status_flag
         ,'Marked for Delete(d).' status_message
         ,SYSDATE creation_date
         ,null created_by --G_CREATED_BY 
         ,SYSDATE last_update_date
         ,null last_updated_by --G_LAST_UPDATED_BY
     FROM canon_e404_rd143_contdtl_tbl e404
         ,(SELECT rd143.contract_number||rd143.instance_id||rd143.billing_cntr_id unique_oracle_id
             FROM canon_rd143_cont_mach_tbl    rd143
            WHERE 1 = 1
              AND rd143.contract_number IS NOT NULL
              AND TRUNC (SYSDATE) BETWEEN rd143.cont_start_date AND NVL (rd143.cont_end_Date, TRUNC (SYSDATE))
              AND UPPER(rd143.contract_status) IN('EXPIRED','CANCELLED', 'TERMINATED')
              AND UPPER(rd143.line_status) IN('EXPIRED', 'CANCELLED', 'TERMINATED')
              AND TRUNC (SYSDATE) BETWEEN rd143.line_start_date AND NVL (rd143.line_end_date, TRUNC (SYSDATE))
              UNION
              SELECT rd143.contract_number||rd143.instance_id||rd143.billing_cntr_id unique_oracle_id
                FROM canon_rd143_cont_mach_tbl    rd143
              WHERE rd143.cont_end_Date is not null
                AND trunc(rd143.cont_end_Date) < trunc(sysdate)
          ) rd143
    WHERE 1 = 1
      --AND e404.fleet = 'N'
      --AND e404.fleet IN ('N','A') --ITG#537363
      AND e404.unique_oracle_id = rd143.unique_oracle_id
      ) del_cont
   ON(del_cont.unique_oracle_id = a.unique_oracle_id AND 
      del_cont.salesforce_contdtl_id = a.salesforce_contdtl_id)
   WHEN MATCHED THEN UPDATE
      SET a.status_flag = del_cont.status_flag
          ,a.status_message = del_cont.status_message
          ,a.creation_date = del_cont.creation_date
          ,a.last_update_date = del_cont.last_update_date
    WHEN NOT MATCHED THEN INSERT
    (contract_number
         ,contract_number_modifier
         ,contract_id
         ,contract_status
         ,cont_start_date
         ,cont_end_date
         ,fleet
         ,fleet_serial_number
         ,branch
         ,service_category
         ,instance_id
         ,serial_number
         ,flee_serial_number
         ,aggregate_number
         ,staples_incl
         ,line_number
         ,line_description
         ,line_start_date
         ,line_end_date
         ,line_term_date
         ,line_status
         ,billto_party
         ,billto_address1
         ,billto_address2
         ,billto_city
         ,billto_postalcode
         ,billto_state
         ,shipto_party
         ,shipto_address1
         ,shipto_address2
         ,shipto_city
         ,shipto_postalcode
         ,shipto_state
         ,line_price
         ,base_price
         ,base_cycle
         ,meter_collection_method
         ,order_number
         ,po_number
         ,usage_cycle
         ,tier1_allowance
         ,tier1_rate
         ,tier2_allowance
         ,tier2_rate
         ,tier3_allowance
         ,tier3_rate
         ,tier4_allowance
         ,tier4_rate
         ,salesforce_ib_id
         ,unique_oracle_id
         ,salesforce_contdtl_id
         ,status_flag
         ,status_message
         ,creation_date
         ,created_by
         ,last_update_date
         ,last_updated_by
         ) values
          (del_cont.contract_number
         ,del_cont.contract_number_modifier
         ,del_cont.contract_id
         ,del_cont.contract_status
         ,del_cont.cont_start_date
         ,del_cont.cont_end_date
         ,del_cont.fleet
         ,del_cont.fleet_serial_number
         ,del_cont.branch
         ,del_cont.service_category
         ,del_cont.instance_id
         ,del_cont.serial_number
         ,del_cont.flee_serial_number
         ,del_cont.aggregate_number
         ,del_cont.staples_incl
         ,del_cont.line_number
         ,del_cont.line_description
         ,del_cont.line_start_date
         ,del_cont.line_end_date
         ,del_cont.line_term_date
         ,del_cont.line_status
         ,del_cont.billto_party
         ,del_cont.billto_address1
         ,del_cont.billto_address2
         ,del_cont.billto_city
         ,del_cont.billto_postalcode
         ,del_cont.billto_state
         ,del_cont.shipto_party
         ,del_cont.shipto_address1
         ,del_cont.shipto_address2
         ,del_cont.shipto_city
         ,del_cont.shipto_postalcode
         ,del_cont.shipto_state
         ,del_cont.line_price
         ,del_cont.base_price
         ,del_cont.base_cycle
         ,del_cont.meter_collection_method
         ,del_cont.order_number
         ,del_cont.po_number
         ,del_cont.usage_cycle
         ,del_cont.tier1_allowance
         ,del_cont.tier1_rate
         ,del_cont.tier2_allowance
         ,del_cont.tier2_rate
         ,del_cont.tier3_allowance
         ,del_cont.tier3_rate
         ,del_cont.tier4_allowance
         ,del_cont.tier4_rate
         ,del_cont.salesforce_ib_id
         ,del_cont.unique_oracle_id
         ,del_cont.salesforce_contdtl_id
         ,del_cont.status_flag
         ,del_cont.status_message
         ,del_cont.creation_date
         ,del_cont.CREATED_BY
         ,del_cont.last_update_date
         ,del_cont.LAST_UPDATED_BY);

   DELETE 
     FROM canon_e404_rd143_contdtl_tbl
    WHERE 1 = 1
      AND unique_oracle_id IN(SELECT unique_oracle_id
                                FROM canon_e404_rd143_to_del_tbl
                               WHERE 1 = 1
							     AND status_flag IN ('DP')
                             );

   COMMIT;

   OPEN x_cursor
    FOR
 SELECT salesforce_contdtl_id
   FROM canon_e404_rd143_to_del_tbl e404
  WHERE 1 = 1
    AND salesforce_contdtl_id IS NOT NULL
    AND e404.status_flag IN ('DI', 'DU', 'DP', 'DE', 'DIE', 'DUE', 'DPE', 'DEE');

EXCEPTION
   WHEN OTHERS THEN
      ROLLBACK;
      OPEN x_cursor
       FOR
    SELECT 1
      FROM canon_e404_rd143_to_del_tbl
     WHERE 1 = 2;
     canon_e404_sf_error_log_pkg.log_error(G_PACKAGE_NAME,l_PROCEDURE_NAME,NULL,NULL,NULL,NULL,NULL,'Unexpected ERROR:'||SQLERRM);
--      log_error_message(
--         p_in_package_name      =>    G_PACKAGE_NAME,
--         p_in_process_name      =>    l_procedure_name,
--         p_in_key1              =>    NULL,
--         p_in_key2              =>    NULL,
--         p_in_key3              =>    NULL,
--         p_in_key4              =>    NULL,
--         p_in_key5              =>    NULL,
--         p_in_error_msg         =>    SQLERRM,
--         p_in_error_date        =>    SYSDATE
--      );
END get_contdtl_delete_data;

END canon_e404_rd143_contdtl_pkg;
/
