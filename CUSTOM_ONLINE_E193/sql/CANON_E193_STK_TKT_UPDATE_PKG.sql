CREATE OR REPLACE PACKAGE CANON_E193_STK_TKT_UPDATE_PKG
IS
/*******************************************************************************
Program Name        : CANON_E193_STK_TKT_UPDATE_PKG
Functional Overview : Update the Force close a customer care ticket that's already in closed status
                      with its line(s) in assigned status

********************************************************************************/
   PROCEDURE UPDATE_CLOSE_STUCK_TICKET (
      errbuf        OUT      VARCHAR2,
      retcode       OUT      NUMBER
   );
END CANON_E193_STK_TKT_UPDATE_PKG;
/
CREATE OR REPLACE PACKAGE BODY CANON_E193_STK_TKT_UPDATE_PKG
IS
   PROCEDURE UPDATE_CLOSE_STUCK_TICKET (
      errbuf        OUT      VARCHAR2,
      retcode       OUT      NUMBER
   )
   IS
      l_line_id     NUMBER;
      l_org_id      NUMBER;
      l_created_by  NUMBER;
      l_note_id     NUMBER;
      l_ret_status  VARCHAR2(1);
      lv_ticket_num  VARCHAR2(100);
   BEGIN
   
   	BEGIN
   	SELECT value
	INTO lv_ticket_num
	FROM canon_e193_pgm_in_val_v
	WHERE parameter_name='Ticket Number'
	AND PROGRAM_NAME='UPDATE_CLOSE_STUCK_TICKET';
   	EXCEPTION
   	WHEN OTHERS
   	THEN
   	lv_ticket_num:=NULL;
   	END;
   	
   	IF lv_ticket_num IS NOT NULL
   	THEN
      UPDATE canon_e193_cs_headers
        SET ticket_status = 'CLOSE'
        WHERE 1 = 1
         AND ticket_status <> 'CLOSE'
         AND ticket_number = lv_ticket_num;
         
     COMMIT;    
   
      UPDATE canon_e193_cs_assignments cca
         SET line_status = 'CLOSE',
             assign_status = 'CLOSE'
       WHERE 1=1 --cca.assign_status = 'ACTIVE'
        -- AND cca.line_status = 'ASSIGNED'
         AND EXISTS (
                SELECT 'x'
                  FROM canon_e193_cs_lines l
                 WHERE 1 = 1
                   AND l.line_id = cca.line_id
                   AND l.line_status <> 'CLOSE'
                   AND EXISTS (
                          SELECT 'x'
                            FROM canon_e193_cs_headers h
                           WHERE h.ticket_id = l.ticket_id
                             AND h.ticket_status = 'CLOSE'
                             AND h.ticket_number = NVL (lv_ticket_num, h.ticket_number)));
                        errbuf := SQL%ROWCOUNT || ' row(s) updated in canon_e193_cs_assignments.';
                        retcode := 0;

      COMMIT;

      UPDATE canon_e193_cs_lines l
         SET l.line_status = 'CLOSE'
       WHERE 1 = 1
         AND l.line_status <> 'CLOSE'
         AND EXISTS (
                SELECT 'x'
                  FROM canon_e193_cs_headers h
                 WHERE h.ticket_id = l.ticket_id
                   AND h.ticket_status = 'CLOSE'
                   AND h.ticket_number = NVL (lv_ticket_num, h.ticket_number));

      errbuf := SQL%ROWCOUNT || ' row(s) updated in canon_e193_cs_lines.';
      retcode := 0;
      COMMIT;
      
       BEGIN   
          SELECT line_id, org_id
          INTO l_line_id, l_org_id
             FROM canon_e193_cs_lines l,
                  canon_e193_cs_headers h
             WHERE h.ticket_number = lv_ticket_num 
             AND l.ticket_id = h.ticket_id;
       EXCEPTION
        WHEN OTHERS
        THEN
        l_line_id := -1;
        l_org_id := -1;
       END;
       l_created_by:=-1;
       
       IF(l_line_id!= -1 AND l_org_id!=-1)
       THEN     
           canon_e193_cs_evolution_pkg.add_notes (l_org_id
                     ,l_line_id
                     ,l_created_by
                     ,'TICKET CLOSED BY CONCURRENT PROGRAM'
                     ,l_note_id
                     ,l_ret_status
                     ); 
       END IF;    
        END IF; 
   EXCEPTION
      WHEN OTHERS
      THEN
         errbuf := SQLERRM;
         retcode := 2;
   END UPDATE_CLOSE_STUCK_TICKET;
END CANON_E193_STK_TKT_UPDATE_PKG;
/
SHOW ERRORS