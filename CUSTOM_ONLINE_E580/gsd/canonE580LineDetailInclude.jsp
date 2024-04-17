<%@page contentType="text/html;charset=UTF-8" %>

<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchUtil"%>
<%@page import="oracle.apps.e580.itt.workbench.CanonE580ITTWorkbenchDAO"%>
<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="static canon.apps.common.CanonS21SessionValidate.commonRoot"%>
<%@page import="canon.apps.pci.util.CanonPCISecurityUtil" %>

<%!

	final static String SERIAL_NUM_PLACE_HOLDER="Serial";
	final static String DEALER_PO_NUM_PLACE_HOLDER="Dealer PO:";
	final static String PO_NUM_PLACE_HOLDER="PO";
	
    static final String SOURCED_BY_DEALER="DEALER";
    static final String ITT_STATUS_NEW= "New"; 
    static final String ITT_STATUS_PENDING_DEALER_ASSIGNMENT="Pending Dealer Assignment";
    static final int NOTES_ROWS_PAGE=15;
    final int TAB_MAIN=0;
    final int TAB_MAINTENANCE=1; 
    final int TAB_NOTES=2;
    final int TAB_TIMESTAMPS=3;
    final static int ALL_ROWS = Integer.MAX_VALUE;
    
    static String getSetItem(String aitem,List list){
        for(int i=0;i<list.size();i++){
            CanonE580ITTWorkbenchDAO.LineDetailItem bitem=(CanonE580ITTWorkbenchDAO.LineDetailItem)list.get(i);
            if(aitem.equals(bitem.getLineNumber())){
                return null;
                
            }else if(aitem.startsWith(bitem.getLineNumber())){
                return readable(bitem.getLineNumber());
            }
        }
        return null;
    }
    
    static String readable(String a){
        return CanonE580ITTWorkbenchUtil.nonNullify(a).replaceAll("\\.","_");
    }

    static String lineTypeClass(CanonE580ITTWorkbenchDAO.LineDetailItem line){
        return CanonE580ITTWorkbenchUtil.nonNullify(line.getSoLineType()).replaceAll("\\s","_").toLowerCase();
    }
    
    static String expenseItemCode(){
    	return (String)CanonE580ITTWorkbenchUtil.first(CanonE580ITTWorkbenchDAO.getExpenseItem());
    }
    
    static boolean isITTLocalShip(CanonE580ITTWorkbenchDAO.LineDetailItem line){
    	return "ITT LOCAL SHIP".equals(line.getSoLineType()) && "CSA".equals(line.getProductSourcedBy());
    }
    
%>
<%
final String EXPENSE_ITEM_CODE=expenseItemCode();
System.out.println("Inside lineInclude.jsp::::::::::::::::::");

    CanonE580ITTWorkbenchDAO dao = new CanonE580ITTWorkbenchDAO();

    String p_itt_number = request.getParameter("itt_number");
    String p_so_number = request.getParameter("order_number");
    String save_flag= request.getParameter("save_flag");
    String [] names=CanonE580ITTWorkbenchUtil.getUserNameAndFullNameS21(request, response);
    String p_user_name=names[1];
	System.out.println("p_user_name is: " +p_user_name);			
	String usernameValue = p_user_name.toUpperCase();
	String userIdValue = "";//TODO dao.getUserId(usernameValue);		
	System.out.println("userIdValue is: " +userIdValue);		
    int tab_index=request.getParameter("tab_index")==null? 0: CanonE580ITTWorkbenchUtil.toInt(request.getParameter("tab_index"));
  	String selected_div=request.getParameter("selected_div")==null? "tabs-0": request.getParameter("selected_div");
  	String selected_tab=request.getParameter("selected_tab")==null? "tabs-0-tab": request.getParameter("selected_tab");
    boolean forward=false;
    boolean exportExcel=false;
    boolean editMode="Y".equals(CanonE580ITTWorkbenchUtil.first(dao.ittEditModeCheck(p_user_name)));
    boolean GSDeditMode="Y".equals(CanonE580ITTWorkbenchUtil.first(dao.ittGSDOrdertypeCheck(p_itt_number)));  //Added Raghavendra Uppala ITG690711
	boolean GSDlocalEditMode="Y".equals(CanonE580ITTWorkbenchUtil.first(dao.ittGSDLocalShipCheck(p_itt_number)));  //Added Raghavendra Uppala ITG690711
	System.out.println("p_so_number GSD is: " +p_so_number);
	System.out.println("GSDeditMode is: " +GSDeditMode);
	System.out.println("GSDlocalEditMode is: " +GSDlocalEditMode);
	System.out.println("itt_number is: " +p_itt_number);	
    System.out.println("editMode:"+editMode);
    System.out.println("save flag value:"+save_flag);
    if("Y".equalsIgnoreCase(save_flag)){

        String p_itt_status=request.getParameter("header_status");
        String p_itt_admin_owner=request.getParameter("itt_order_processor_id");
        String p_dealer_code_display = request.getParameter("dealer_code_display");
        String dealer_code_name = CanonE580ITTWorkbenchUtil.isEmpty(p_dealer_code_display)? null: request.getParameter("dealer_code_name") ;
        String p_dealer_whse_code = CanonE580ITTWorkbenchUtil.isEmpty(p_dealer_code_display)? null: request.getParameter("dealer_code_id");
        
        String p_cmap_yes_no=request.getParameter("cmap");
        String p_dealer_contact_name=request.getParameter("dealer_contact_name");
        String p_dealer_phone=request.getParameter("dealer_phone");
        String p_dealer_email=request.getParameter("dealer_email");
        String p_mail_invoice_to=request.getParameter("mail_invoice_to");
        
        String p_cust_contact_name=request.getParameter("cust_contact_name");
        String p_cust_contact_phone=request.getParameter("cust_contact_phone");
        String p_cust_contact_fax=request.getParameter("cust_contact_fax");
        String p_cust_contact_email=request.getParameter("cust_contact_email");
        String dealer_address[]=CanonE580ITTWorkbenchUtil.splitAddress(request.getParameter("dealer_address"));
        String p_dealer_address_line1=dealer_address!=null & dealer_address.length>0?dealer_address[0]:null;
        String p_dealer_address_line2=dealer_address!=null & dealer_address.length>1?dealer_address[1]:null;
        String p_dealer_address_line3=dealer_address!=null & dealer_address.length>2?dealer_address[2]:null;
        
        String cityState[]=CanonE580ITTWorkbenchUtil.splitCityState(request.getParameter("dealer_city_state"));
        String p_dealer_city=cityState!=null & cityState.length>0?cityState[0]:null;
        String p_dealer_state=cityState!=null & cityState.length>1?cityState[1]:null;
        String p_dealer_zip=request.getParameter("dealer_zip");
        String p_dealer_country=request.getParameter("dealer_country");
        String p_dealer_ship_to_cna_code=request.getParameter("dealer_ship_to_cna_code");
        BigDecimal p_vendor_id = CanonE580ITTWorkbenchUtil.toBigDecimal(request.getParameter("vendor_id"),false);
        BigDecimal p_vendor_site_id = CanonE580ITTWorkbenchUtil.toBigDecimal(request.getParameter("vendor_site_id"),false);
        String p_dealer_supplier_code=request.getParameter("dealer_supplier_code")==null?"":request.getParameter("dealer_supplier_code");
		//Added Raghavendra Uppala ITG690711 Start
		
		String p_dealer_shipto_name=request.getParameter("dealer_shipto_name");
		String p_dealer_shipto_contact_name=request.getParameter("dealer_shipto_contact_name");
		String p_dealer_shipto_phone=request.getParameter("dealer_shipto_phone");
		String p_dealer_shipto_email=request.getParameter("dealer_shipto_email"); 
		
		String dealer_shipto_address[]=CanonE580ITTWorkbenchUtil.splitAddress(request.getParameter("dealer_shipto_address"));
        String p_dealer_shipto_add_ln1=dealer_shipto_address!=null & dealer_shipto_address.length>0?dealer_shipto_address[0]:null;
        String p_dealer_shipto_add_ln2=dealer_shipto_address!=null & dealer_shipto_address.length>1?dealer_shipto_address[1]:null;
        String p_dealer_shipto_add_ln3=dealer_shipto_address!=null & dealer_shipto_address.length>2?dealer_shipto_address[2]:null;
		
		String shiptocityState[]=CanonE580ITTWorkbenchUtil.splitCityState(request.getParameter("dealer_shipto_city_state"));
        String p_dealer_shipto_city=shiptocityState!=null & shiptocityState.length>0?shiptocityState[0]:null;
        String p_dealer_shipto_state=shiptocityState!=null & shiptocityState.length>1?shiptocityState[1]:null;
        String p_dealer_shipto_zip=request.getParameter("dealer_shipto_zip");
        String p_dealer_shipto_country=request.getParameter("dealer_shipto_country");
		Timestamp p_req_deli_date=CanonE580ITTWorkbenchUtil.toTimestamp2(request.getParameter("req_delivery_date"));
			System.out.println("p_req_deli_date is: " +p_req_deli_date);
        //Added Raghavendra Uppala ITG690711End
        
        System.out.println("Prior to ittHeaderUpdate call:");
        CanonE580ITTWorkbenchDAO.ittHeaderUpdate(p_so_number, 
           p_itt_number, 
           p_user_name, 
           p_itt_status, 
           p_itt_admin_owner, 
           dealer_code_name, 
           p_dealer_whse_code,
           p_dealer_address_line1,
           p_dealer_address_line2,
           p_dealer_address_line3,
           p_dealer_city,
           p_dealer_state,
           p_dealer_zip,
           p_dealer_country,
           p_dealer_phone,
           p_dealer_email,
           p_cmap_yes_no, 
           "", //p_product_sourced_by, 
           p_dealer_contact_name,
           p_mail_invoice_to,
           p_cust_contact_name,
           p_cust_contact_phone,
           p_cust_contact_email,
           p_cust_contact_fax,
           p_dealer_ship_to_cna_code,
           p_vendor_id,
           p_vendor_site_id,
           new BigDecimal(0),
           p_dealer_supplier_code,
		   p_dealer_shipto_name,      //Added Raghavendra Uppala ITG690711 Start
		   p_dealer_shipto_contact_name,   
		   p_dealer_shipto_phone,
		   p_dealer_shipto_email,
		   p_dealer_shipto_add_ln1,
		   p_dealer_shipto_add_ln2,
		   p_dealer_shipto_add_ln3,
		   p_dealer_shipto_city,
		   p_dealer_shipto_state,
		   p_dealer_shipto_zip,
		   p_dealer_shipto_country,
		   p_req_deli_date		   //Added Raghavendra Uppala ITG690711End
		   );     
        
               
        switch(tab_index){
            case TAB_MAIN:
                String current_itt_status=request.getParameter("current_itt_status");
                String [] line_numbers=request.getParameterValues("line_number");
                String [] item_codes=request.getParameterValues("item_code");
                String [] desciptions=request.getParameterValues("description");
                String [] purchase_prices=request.getParameterValues("purchase_price");
                String [] itt_statuss=request.getParameterValues("itt_status");
                String [] delivery_scheduled_dates=request.getParameterValues("delivery_scheduled_date");
                String [] install_credits=request.getParameterValues("install_credit");
                String [] finder_fees=request.getParameterValues("finder_fee");
                String [] serial_numbers=request.getParameterValues("serial_number");
                String [] reason_codes=request.getParameterValues("reason_code");
                String [] po_numbers=request.getParameterValues("po_number");
                String [] product_sourced_bys=request.getParameterValues("product_sourced_by");
				String [] gsd_delivery_numbers=request.getParameterValues("gsd_delivery_number");
				String [] gsd_delivery_statuss=request.getParameterValues("gsd_delivery_status");

                if(line_numbers!=null && line_numbers.length>0){
                    for(int i=0;i<line_numbers.length;i++){
                        String p_line_number=line_numbers[i];
                        String p_item_code=item_codes[i];
                        String p_item_description=desciptions[i];
                        String itt_status=itt_statuss[i];
                        Timestamp p_delivery_scheduled_date=CanonE580ITTWorkbenchUtil.toTimestamp2(delivery_scheduled_dates[i]);
                        BigDecimal p_install_credit=null;
						BigDecimal p_finder_fee=null;
						if(!GSDeditMode){      //Added Raghavendra Uppala ITG690711
							p_install_credit=CanonE580ITTWorkbenchUtil.toBigDecimal(install_credits[i], false);						
							p_finder_fee=CanonE580ITTWorkbenchUtil.toBigDecimal(finder_fees[i],false);
						}
                        BigDecimal p_item_purchase_price=CanonE580ITTWorkbenchUtil.toBigDecimal(purchase_prices[i], false);
                        String p_serial_number=serial_numbers[i];
                        String p_reason_code=reason_codes[i];
						String p_po_number=null;
						if(!GSDlocalEditMode){ 
                              p_po_number=po_numbers[i];
						}
                        String p_product_sourced_by=product_sourced_bys[i];
						String p_gsd_delivery_number=null;
						String p_gsd_delivery_status=null;
						if(GSDlocalEditMode){ 
						   p_gsd_delivery_number=gsd_delivery_numbers[i];
						   p_gsd_delivery_status=gsd_delivery_statuss[i];
						}

                        if(EXPENSE_ITEM_CODE.equalsIgnoreCase(p_item_code)){
                            if(CanonE580ITTWorkbenchUtil.isEmpty(p_line_number)){
                                dao.ittExpenseLineInsert(p_so_number,
                                    p_itt_number,
                                    p_user_name,
                                    p_item_code,
                                    p_item_description,
                                    new BigDecimal(1),
                                    p_item_purchase_price,
                                    null, //p_install_credit,
                                    null //p_finder_fee
                                    );
                            }else{
                                dao.ittExpenseLineUpdate(p_itt_number,
                                    p_line_number,
                                    p_user_name,
                                    p_item_code,
                                    p_item_description,
                                    new BigDecimal(1),
                                    p_item_purchase_price,
                                    null, //p_install_credit,
                                    null, //p_finder_fee,
                                    p_po_number
                                    );

                            }
                        }else{
                        	System.out.println("Inside else prior to call ittGoodsLineUpdate");
                            dao.ittGoodsLineUpdate(p_itt_number,
                                p_line_number,
                                p_user_name,
                                (ITT_STATUS_NEW.equals(current_itt_status) & 
                                    ITT_STATUS_NEW.equals(itt_status) &
                                    !CanonE580ITTWorkbenchUtil.isEmpty(p_itt_admin_owner)?
                                    ITT_STATUS_PENDING_DEALER_ASSIGNMENT : itt_status),
                                p_delivery_scheduled_date,
                                p_install_credit,
                                p_finder_fee,
                                p_serial_number,
                                p_reason_code,
                                p_po_number,
                                p_product_sourced_by,
                                p_item_purchase_price);
                        }
                    }
                }
            break;
            case TAB_MAINTENANCE:
                String [] models=request.getParameterValues("model");
                String [] contract_types=request.getParameterValues("contract_type");
                String [] plan_types=request.getParameterValues("plan_type");
                String [] terms=request.getParameterValues("term");
                String [] base_prices=request.getParameterValues("base_price");
                String [] base_bill_cycles=request.getParameterValues("base_bill_cycle");
                String [] overage_bill_cycles=request.getParameterValues("overage_bill_cycle");
                String [] meter_types=request.getParameterValues("meter_type");
                String [] overage_rates=request.getParameterValues("overage_rate");
                String [] copy_inclusions=request.getParameterValues("copy_inclusion");
                String [] multipliers=request.getParameterValues("multiplier");
				String [] monthly_additional_comps=request.getParameterValues("monthly_additional_comp");  //Added Raghavendra Uppala ITG690711
				String [] monthly_admin_comps=request.getParameterValues("monthly_admin_comp");            //Added Raghavendra Uppala ITG690711 
                String last_model=null;
                String last_contract_type=null;
                String last_plan_type=null;
                BigDecimal last_term=null;
                BigDecimal last_base_price=null;
                String last_base_bill_cycle=null;
                String last_overage_bill_cycle=null;
                
                for(int i=0;models!=null && i<models.length;i++){
                    String p_equip_model=models[i];
                    boolean sameModel=p_equip_model!=null && p_equip_model.equals(last_model);
                    String p_contract_type=sameModel? last_contract_type: contract_types[i];
                    String p_plan_type=sameModel? last_plan_type: plan_types[i];
                    BigDecimal p_term=sameModel? last_term:  CanonE580ITTWorkbenchUtil.toBigDecimal(terms[i], false);
                    BigDecimal p_base_price=sameModel? last_base_price: CanonE580ITTWorkbenchUtil.toBigDecimal(base_prices[i], false);
                    String p_base_bill_cycle=sameModel? last_base_bill_cycle: base_bill_cycles[i];
                    String p_overage_bill_cycle=sameModel? last_overage_bill_cycle: overage_bill_cycles[i];
                    String p_meter_type=meter_types[i];
                    BigDecimal p_overage_rate=CanonE580ITTWorkbenchUtil.toBigDecimal(overage_rates[i], false);
                    BigDecimal p_copy_inclusion=CanonE580ITTWorkbenchUtil.toBigDecimal(copy_inclusions[i], false);
                    BigDecimal p_multiplier =CanonE580ITTWorkbenchUtil.toBigDecimal(multipliers[i], false);
					BigDecimal p_mon_add_comp=CanonE580ITTWorkbenchUtil.toBigDecimal(monthly_additional_comps[i], false);    //Added Raghavendra Uppala ITG690711
                    BigDecimal p_mon_admin_comp=CanonE580ITTWorkbenchUtil.toBigDecimal(monthly_admin_comps[i], false);       //Added Raghavendra Uppala ITG690711
                    dao.ittUpdatePricing( p_itt_number,
                            p_user_name,
                            p_equip_model,
                            p_meter_type,
                            p_contract_type,
                            p_overage_rate,
                            p_plan_type,
                            p_term,
                            p_base_price,
                            p_base_bill_cycle,
                            p_overage_bill_cycle,
                            p_copy_inclusion,
                            p_multiplier,
							p_mon_add_comp,        //Added Raghavendra Uppala ITG690711
							p_mon_admin_comp       //Added Raghavendra Uppala ITG690711 
							);
                 
                    last_model=p_equip_model;
                    last_contract_type=p_contract_type;
                    last_plan_type=p_plan_type;
                    last_term=p_term;
                    last_base_price=p_base_price;
                    last_base_bill_cycle=p_base_bill_cycle;                    
                    last_overage_bill_cycle=p_overage_bill_cycle;                    
                }
                               
            break;
            case TAB_NOTES:
                String comments=request.getParameter("comments");
                if(!CanonE580ITTWorkbenchUtil.isEmpty(comments)){
                    String p_add_to_popdf=request.getParameter("add_to_popdf")!=null ? request.getParameter("add_to_popdf") : "N";
                    dao.ittNotesInsert(p_so_number, p_itt_number, p_user_name, comments,p_add_to_popdf);
                }

                String [] notes_seq_numbers=request.getParameterValues("notes_seq_number");
                String [] notes_add_to_popdfs=request.getParameterValues("notes_add_to_popdf");
                for(int i=0;notes_seq_numbers!=null && i<notes_seq_numbers.length;i++){
                    BigDecimal notes_seq_number=CanonE580ITTWorkbenchUtil.toBigDecimal(notes_seq_numbers[i],false);
                    String notes_add_to_popdf=notes_add_to_popdfs[i];
                    dao.ittNotesUpdate(p_so_number, p_itt_number, p_user_name,notes_seq_number,notes_add_to_popdf);
                }
                
            break;
            case TAB_TIMESTAMPS:
                Timestamp p_po_date=CanonE580ITTWorkbenchUtil.toTimestamp4(request.getParameter("po_date"));
                Timestamp p_po_accepted_date_by_dlr=CanonE580ITTWorkbenchUtil.toTimestamp4(request.getParameter("po_accepted_date_by_dlr"));
                Timestamp p_po_sent_date_to_cusa=CanonE580ITTWorkbenchUtil.toTimestamp4(request.getParameter("po_sent_date_to_cusa"));
                Timestamp p_shipped_date_from_cusa=CanonE580ITTWorkbenchUtil.toTimestamp4(request.getParameter("shipped_date_from_cusa"));
                Timestamp p_equip_arrive_at_dlr_date=CanonE580ITTWorkbenchUtil.toTimestamp4(request.getParameter("equip_arrive_at_dlr_date"));
                Timestamp p_pod_rcvd_from_dlr_date=CanonE580ITTWorkbenchUtil.toTimestamp4(request.getParameter("pod_rcvd_from_dlr_date"));
                Timestamp p_cna_po_aprvd_by_dlr_date=CanonE580ITTWorkbenchUtil.toTimestamp4(request.getParameter("date_cna_po_approved_by_dealer"));
                dao.ittTimestampUpdate(p_itt_number, p_user_name, p_po_date, p_po_accepted_date_by_dlr, p_po_sent_date_to_cusa, p_shipped_date_from_cusa, p_equip_arrive_at_dlr_date, p_pod_rcvd_from_dlr_date, p_cna_po_aprvd_by_dlr_date);
            break;
                                      

        }
        
    }else if("CREATE_PO".equalsIgnoreCase(save_flag)){
        pageContext.forward("canonE580Service.jsp");
        forward=true;
    }else if("GENERATE_PO_PDF".equalsIgnoreCase(save_flag)){
        pageContext.forward("canonE580GeneratePOPDF.jsp");
        forward=true;
    }else if("GENERATE_GSD_PDF".equalsIgnoreCase(save_flag)){
        pageContext.forward("canonE580GenerateGSDOrderAckPDF.jsp");
        forward=true;		
    }else if("export-excel".equalsIgnoreCase(save_flag)){
        exportExcel=true;
        forward=true;
    }    
            
    if(!forward ||exportExcel){
    String p_order_by = request.getParameter("order_by");
    String p_asc_desc = request.getParameter("asc_desc");
    String p_rows_per_page = request.getParameter("rowsPerPage");
    int rowsPerPage = p_rows_per_page == null ? ALL_ROWS : CanonE580ITTWorkbenchUtil.toInt(p_rows_per_page);
    int page_no = CanonE580ITTWorkbenchUtil.isEmpty(request.getParameter("page_no")) ? -1 : CanonE580ITTWorkbenchUtil.toInt(request.getParameter("page_no"));
    BigDecimal p_from_record = page_no == -1 ? new BigDecimal(1) : new BigDecimal((page_no - 1) * rowsPerPage + 1);
    BigDecimal p_to_record = p_from_record == null ? null : p_from_record.add(new BigDecimal(rowsPerPage - 1));
    List list=new ArrayList();
    List summary=null;
    int total_record=0;
    try{
        Object[] result = dao.ittLineDetails(p_itt_number,
                p_so_number,
                exportExcel?null:p_from_record,
                exportExcel?null:p_to_record,
                p_order_by,
                p_asc_desc);
        summary = (List)CanonE580ITTWorkbenchUtil.first(result);
        list = CanonE580ITTWorkbenchUtil.second(result)!=null? (List) CanonE580ITTWorkbenchUtil.second(result): list;
        total_record = ((BigDecimal) CanonE580ITTWorkbenchUtil.third(result)).intValue();
        System.out.println(" total_record  is "+total_record );
    }catch(Exception e){
        e.printStackTrace();
    }
    boolean poFreezed=false;
    for (int i = 0; i < list.size(); i++) {
        CanonE580ITTWorkbenchDAO.LineDetailItem line = (CanonE580ITTWorkbenchDAO.LineDetailItem) list.get(i);
        if(!CanonE580ITTWorkbenchUtil.isEmpty(line.getCusaPoNumber())){
            poFreezed=true;
            break;
        }
    }
    CanonE580ITTWorkbenchDAO.LineDetailSummary firstLine=list!=null && list.size()>0? (CanonE580ITTWorkbenchDAO.LineDetailSummary) summary.get(0): null;
    String p_order_number= firstLine!=null? firstLine.getOrderNumber():null;
    String p_itt_status= firstLine!=null? firstLine.getIttStatus():null;
    Date p_booker_date=firstLine!=null? firstLine.getOrderBookedDate() :null;
    String p_ship_to_cust = firstLine!=null? firstLine.getShipToCustomer():null;
    String p_markview_images=".. TODO ";//TODO
    String p_itt_order_processor_id = firstLine!=null? firstLine.getIttAdminOwner():null;
    String p_itt_order_processor_name = firstLine!=null? firstLine.getIttAdminOwnerName():null;
    String p_itt_order_processor_display = firstLine!=null? CanonE580ITTWorkbenchUtil.ittOrderProcessor(firstLine.getIttAdminOwner(),firstLine.getIttAdminOwnerName()) :null;
    String p_header_status =  firstLine!=null? firstLine.getIttStatus():null;
    String p_sales_rep = firstLine!=null? firstLine.getSalesRep() : null;
    String p_sales_zone = firstLine!=null? firstLine.getSalesZone() : null;
    String p_sales_branch = firstLine!=null? firstLine.getSalesBranch() : null;
    String p_ship_to_address = firstLine!=null? firstLine.getShopToAddress() : null;
    String p_city_state = firstLine!=null? firstLine.getCityState() : null;
    String p_postal = firstLine!=null? firstLine.getPostalCode(): null;
    String p_dealer_code_id = firstLine!=null? firstLine.getDealerWhseCode() : null;
    String p_dealer_code_name = firstLine!=null? firstLine.getDealer() : null;
    String p_dealer_code_display = firstLine!=null? CanonE580ITTWorkbenchUtil.dealerCode(firstLine.getDealerWhseCode(), firstLine.getDealer()) : null;
    String p_CMAP = firstLine!=null? firstLine.getCmapYesNo() : null;
    String p_product_sourced_by= firstLine!=null? firstLine.getProductSourcedBy() : null;
    String p_dealer_contact_name= firstLine!=null? firstLine.getDealerContact() : null;
    String p_dealer_phone=firstLine!=null? firstLine.getDealerContactNumber() : null;
    String p_dealer_email=firstLine!=null? firstLine.getDealerEmail() : null;
    String p_mail_invoice_to=firstLine!=null? firstLine.getMailInvoicesTo() : null;
    String p_cust_contact_name=firstLine!=null? firstLine.getCustContactName() : null;
    String p_cust_contact_phone=firstLine!=null? firstLine.getCustContactPhone() : null;
    String p_cust_contact_fax=firstLine!=null? firstLine.getCustContactFax() : null;
    String p_cust_contact_email=firstLine!=null? firstLine.getCustContactEmail() : null;
    String p_dealer_address_line1=firstLine!=null? firstLine.getDealerAddressLine1() : null;
    String p_dealer_address_line2=firstLine!=null? firstLine.getDealerAddressLine2() : null;
    String p_dealer_address_line3=firstLine!=null? firstLine.getDealerAddressLine3() : null;
    String p_dealer_city=firstLine!=null? firstLine.getDealerCity() : null;
    String p_dealer_state=firstLine!=null? firstLine.getDealerState() : null;
    String p_dealer_zip=firstLine!=null? firstLine.getDealerZip() : null;
    String p_dealer_ship_to_cna_code=firstLine!=null? firstLine.getDealerShipToCnaCode() : null;
    String p_delear_po_number=firstLine!=null? firstLine.getDealerPoNumber() : null;
    BigDecimal p_vendor_id = firstLine!=null? firstLine.getVendorId() : null;
    BigDecimal p_vendor_site_id = firstLine!=null? firstLine.getVendorSiteId() : null;
    String p_prnt_vnd_cd=firstLine!=null? firstLine.getPrntVndCd() : null;
    boolean lineAddded=firstLine!=null && "Line Added".equals(firstLine.getIttStatus());
    
	//ITG690711 start
	String p_dealer_shipto_name=firstLine!=null? firstLine.getDealerShiptoName() : null;
	String p_dealer_shipto_contact=firstLine!=null? firstLine.getDealerShiptoContact() : null;
	String p_dealer_shipto_zip=firstLine!=null? firstLine.getDealerShiptoZip() : null;
	String p_dealer_shipto_phone=firstLine!=null? firstLine.getDealerShiptoContactNo() : null;
	String p_dealer_shipto_email=firstLine!=null? firstLine.getDealerShiptoEmail() : null;
	String p_dealer_shipto_add_ln1=firstLine!=null? firstLine.getDealerShiptoAddLn1() : null;
	String p_dealer_shipto_add_ln2=firstLine!=null? firstLine.getDealerShiptoAddLn2() : null;
    String p_dealer_shipto_add_ln3=firstLine!=null? firstLine.getDealerShiptoAddLn3() : null;
	String p_dealer_shipto_city=firstLine!=null? firstLine.getDealerShiptoCity() : null;
	String p_dealer_shipto_state=firstLine!=null? firstLine.getDealerShiptoState() : null;
	Date p_req_delivery_date=firstLine!=null? firstLine.getreqdeliverydate() :null;
	String p_comp_status=firstLine!=null? firstLine.getCompAprvStatus() : null;
		
	//ITG690711 End
    
    Object [] result = dao.ittPricingDetails(p_itt_number);
    List pricingList=CanonE580ITTWorkbenchUtil.first(result)!=null?(List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;

    result = dao.ittDealerCompExtract(p_itt_number); 
    List dlrcompList=CanonE580ITTWorkbenchUtil.first(result)!=null?(List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;

    result = dao.ittDealerTotalComp(p_itt_number); 
    List comptotalList=CanonE580ITTWorkbenchUtil.first(result)!=null?(List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;
    
    result = dao.ittTimestampDetails(p_itt_number);
    List timestampList=CanonE580ITTWorkbenchUtil.first(result)!=null?(List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;
    CanonE580ITTWorkbenchDAO.TimestampDetail timestamp=timestampList.size()>0? (CanonE580ITTWorkbenchDAO.TimestampDetail)timestampList.get(0): null;

    String notes_order_by = request.getParameter("notes_order_by");
    notes_order_by =notes_order_by ==null? "SEQ_NUMBER" :notes_order_by;
    String notes__asc_desc = request.getParameter("notes_asc_desc");
    notes__asc_desc =notes__asc_desc ==null? "ASC": notes__asc_desc;
    int notes_page_no = CanonE580ITTWorkbenchUtil.isEmpty(request.getParameter("notes_page_no")) ? -1 : CanonE580ITTWorkbenchUtil.toInt(request.getParameter("notes_page_no"));
    BigDecimal notes_from_record = notes_page_no == -1 ? new BigDecimal(1) : new BigDecimal((notes_page_no - 1) * NOTES_ROWS_PAGE + 1);
    BigDecimal notes_to_record = notes_from_record == null ? null : notes_from_record.add(new BigDecimal(NOTES_ROWS_PAGE - 1));
    names= CanonE580ITTWorkbenchUtil.getUserNameAndFullNameS21(request,response);
    String fullName =names[1];//CanonE580ITTWorkbenchDAO.getFullName(p_user_name);    
    result = dao.ittNotesDetails(p_itt_number,
            exportExcel?null:notes_from_record, 
            exportExcel?null:notes_to_record, 
            notes_order_by, 
            notes__asc_desc);
   
    List notesList=CanonE580ITTWorkbenchUtil.first(result)!=null?(List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;
    int notes_total_record = ((BigDecimal) CanonE580ITTWorkbenchUtil.second(result)).intValue();
    
    if(exportExcel){
        pageContext.setAttribute("export-excel-detail", 
                CanonE580ITTWorkbenchUtil.createLineDetailInfo(list, pricingList, notesList, timestamp,dlrcompList,comptotalList),
                PageContext.REQUEST_SCOPE);
        pageContext.forward("canonE580ExcelDownload.jsp");
    }else{
    result = dao.ittCmapLov();
    List cmapLovList = CanonE580ITTWorkbenchUtil.first(result)!=null ? (List) CanonE580ITTWorkbenchUtil.first(result): Collections.EMPTY_LIST;
    result = dao.ittProductSourceLov();
    List productSourceLovList = CanonE580ITTWorkbenchUtil.first(result)!=null? (List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;
    result = dao.ittStatusLov();
    List statusList = CanonE580ITTWorkbenchUtil.first(result)!=null ? (List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;
    result = dao.ittMailInvoicesToLov();
    List mailInvoicesToLovList = CanonE580ITTWorkbenchUtil.first(result)!=null ? (List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;
    List CompList=new ArrayList();
    try{
        Object[] compresult = dao.ittDealerTotalComp(p_itt_number);
        CompList = CanonE580ITTWorkbenchUtil.first(compresult)!=null? (List) CanonE580ITTWorkbenchUtil.first(compresult): CompList;
    }catch(Exception e){
        e.printStackTrace();
    }
	
    List CompChkList=new ArrayList();
    try{
        Object[] compchkresult = dao.ittDealerCompChk(p_itt_number,"CHKCOMP");
        CompChkList = CanonE580ITTWorkbenchUtil.first(compchkresult)!=null? (List) CanonE580ITTWorkbenchUtil.first(compchkresult): CompChkList;
    }catch(Exception e){
        e.printStackTrace();
    }	
	
    List OverrideChkList=new ArrayList();
    try{
        Object[] overrideresult = dao.ittDealerCompChk(p_itt_number,"CHKOVERRIDE");
        OverrideChkList = CanonE580ITTWorkbenchUtil.first(overrideresult)!=null? (List) CanonE580ITTWorkbenchUtil.first(overrideresult): OverrideChkList;
    }catch(Exception e){
        e.printStackTrace();
    }	
	
    List PendingDlrList=new ArrayList();
    try{
        Object[] pendingdlrresult = dao.ittDealerCompChk(p_itt_number,"DLRCONFIRM");
        PendingDlrList = CanonE580ITTWorkbenchUtil.first(pendingdlrresult)!=null? (List) CanonE580ITTWorkbenchUtil.first(pendingdlrresult): PendingDlrList;
    }catch(Exception e){
        e.printStackTrace();
    }	
	
    System.out.println("All database calling  completed:");
%>    

<style>
    
    #report_tbl_first th{
   font-family: "OpticSansBook",sans-serif;
    font-size: 12px;"
    }
    

    td select{
        width: 100%; 
        box-sizing: border-box;
        -webkit-box-sizing:border-box;
        -moz-box-sizing: border-box;
    }
    
    .itt_tbl_first {
      background-color: #CCCC99;
    }

    .itt_tbl_first td {
      background-color: #F7F7E7;
    }

    .itt_tbl_first td.altcol {
      background-color: #E5E5C7;
    }

    .itt_tbl_first a {
      color: #115599;
    }

    .itt_tbl_first a:hover {
      color: #BB0000;
    }

    .itt_tbl_first .hd {
      color: #FFFFFF;
      background-color: #2B547E;
      text-decoration: none;
      text-align: center;
      font-weight: bold;
    }
    
	#itt_tbl_first th{
   font-family: "OpticSansBook",sans-serif;
    font-size: 12px;"
    }
    .bgwhite {
      background-color: #FFFFFF;
    }
    
    .itt_model_qty {
      text-decoration: none;
      text-align: center;
      font-weight: bold;
      font-size: +10;
    }
    
    div#notes-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#notes-contain table td, div#notes-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    div#notes-contain table td{text-align: center;}
    
    .ui-dialog .ui-state-error { padding: .3em; }
    
    .mass_apply_button{
        display:none
    }

    .edit_mode{
        <% if (!editMode) { %>
        display:none
        <% } %>
    }
    
    .free_text {text-align: left;}
    .status-message{
        color:blue;
    }
    
    .required_field{
        background-color:yellow;
    }
    .select_all a {
        color: blue;
    }
    .select_all a:hover {
        color: red;
    }
    .select_all_div {
        float:left;
        font-size:smaller;
        padding-top:10px;
    }
    .clear
    {
        clear:both;
    }
    #dealer_address
    {
        width:100%;
        overflow:auto
    }
    .input_seperator
    {
	background-color: #d7e6fa;
        color:#e17009;
        font-weight:bold;
    }
    .input_seperator td
    {
    }
    #header_table input
    {
        width:100%;
    }
    input.currency,input.autoInteger,input.autoMultiplier,input.rate,input.term {
        text-align:right;
        padding: 0 2px 0 0; /* fix ie right alignment cursor disapear issue */
    }
    .itt_timestamp{
        text-align:center;
    }
    
    #generate_po_pdf_warning{
        color:blue;
        font-weight: bold;
        text-align: center;
        display:none;
    }
    .rdl{
    background-color: #cccccc;
    }
    
    #itt_timestamp_table td{
	 padding:5px;
	 }
    
    .ui-dialog .ui-dialog-buttonpane button{
    	background: #8e191c linear-gradient(#cc0000, #8e191c) repeat scroll 0 0;
	    border: medium none;
	    border-radius: 3px;
	    color: #ffffff !important;
	    cursor: pointer;
	    font-size: 12px !important;
	    padding: 5px 12px;
	    text-decoration: none !important;
    }
 
 

</style>

<script>

    $(document).ready(function()
    {
    	$("input.date-picker").datepicker({
   		 dateFormat: 'mm/dd/yy',
   		 onSelect: function(datetext){   			  			    			
   		        var d = new Date(); // for now

   		        var h = d.getHours();
   		        var ampm="";
   		        ampm=(h < 12) ? "AM" : "PM" ;
   		        h = (h < 12) ? ("0" + h) : h%12 ;
   		        
   		        
   		        var m = d.getMinutes();
   		        m = (m < 10) ? ("0" + m) : m ;

   		        datetext = datetext + " " + h + ":" + m +" "+ampm;

   		        $('#'+$(this).attr("id")).val(datetext);
   		    },
   		 changeMonth: true,
   		 changeYear: true
   	 }); 
    	
    	 $("input.delivery_scheduled_date-picker").datepicker({
      		 dateFormat: 'mm/dd/yy',      		 
      		 changeMonth: true,
      		 changeYear: true
      	 }); 
       	
    	 $('.rdl').each(function() {
    		  $(this).prop('title', $(this).val());
    	 });
    	
    	 $('#myTabs a').removeClass('active');
    	 $('div[id^="tabs-"]').hide();
    	 
    	 var selected_tabid=$('#selected_tab').val();    	 
    	 
    	 $('#'+selected_tabid).addClass('active');
    	
    	 
    	 var selected_divid=$('#selected_div').val();

    	 $('#'+selected_divid).show();    	 
    	 $( ".inner_table input:text" ).css(  
		 	"float","left"
		 );
    	 
        // BEGIN RECEIVE PO 
        var RECEIVE_PO_DIV_ID="tabs-5";
        function openReceivePO(itt_number)
        {
            var jsp="canonE580ReceivePO.jsp";
            var urlData = jsp + "?itt_number=" + itt_number;

            blockUsrInterface();
            $.ajax({
                url: urlData,
                cache: false,
                success: function (data) {                	
                    unBlockUsrInterface();                    
                    var modelName = "#" + RECEIVE_PO_DIV_ID;                     
                    $("#myTabs").append('<a id=\"tabs-5-tab\" href="#tabs-5" class="active">Receive PO <span class="close_receive_po_confirm" style="margin-left:20px;background:#ffffff;color:#000000">X</span></a>');
                    //<span class="close_receive_po_confirm ui-icon ui-icon-close">Remove Tab</span>
                    
                    $('#myTabs a').removeClass('active');
           	        $('#tabs-5-tab').addClass('active');

           	        $('div[id^="tabs-"]').hide();
           	     $("#tabs-5").append(data);
           	     $("#tabs-5").css({'display':'block'});	
           	       // $('div' + $('tabs-5').attr('href')).show();
                    
                     
                   //$("#tabs-5").css({'display':'block'});	
                   //$("#tabs-1").css({'display':'none'});	
                  
                    /* var tabs = $( "#tabs" ).tabs();
                    tabs.tabs("option","tabTemplate","<li><a href='\#{href}'>\#{label}</a> <span class='close_receive_po_confirm ui-icon ui-icon-close'>Remove Tab</span></li>");
                    tabs.append("<div id="+RECEIVE_PO_DIV_ID+">"+data+"</div>");
                    tabs.tabs( "add",modelName, "Receive PO");
                    tabs.tabs( "select", 4);
                    tabs.tabs( "disable", 0);
                    tabs.tabs( "disable", 1);
                    tabs.tabs( "disable", 2);
                    tabs.tabs( "disable", 3); */
                    /* $(".close_receive_po_confirm",tabs).click(function(){
                            closeReceivePO();
                        }
                    ); */
                    $(".close_receive_po_confirm").click(function(){
                        closeReceivePO();
                    });
                    registerReveicePoHandler();
                }
            });
        }
        
        var RECEIVE_PO_EXP_DIV_ID="tabs-6";
        function openReceiveExpPO(itt_number)
        {
            var jsp="canonE580ReceiveExpensePO.jsp";
            var urlData = jsp + "?itt_number=" + itt_number;

            blockUsrInterface();
            $.ajax({
                url: urlData,
                cache: false,
                success: function (data) {
                    unBlockUsrInterface();
                    var modelName = "#" + RECEIVE_PO_EXP_DIV_ID;
                    $("#myTabs").append('<a id=\"tabs-6-tab\" href="#tabs-6" class="active">Receive Expense PO <span class="close_receive_po_confirm" style="margin-left:20px;background:#ffffff;color:#000000">X</span></a>');
                    var tabs = $( "#tabs" ).tabs();
                    $('#myTabs a').removeClass('active');
           	        $('#tabs-6-tab').addClass('active');

           	        $('div[id^="tabs-"]').hide();
           	     $("#tabs-6").html("");
           	     $("#tabs-6").append(data);
           	     $("#tabs-6").css({'display':'block'});	
                    
                   /*  tabs.tabs("option","tabTemplate","<li><a href='\#{href}'>\#{label}</a> <span class='close_receive_po_confirm ui-icon ui-icon-close'>Remove Tab</span></li>");
                    tabs.append("<div id="+RECEIVE_PO_EXP_DIV_ID+">"+data+"</div>");
                    tabs.tabs( "add",modelName, "Receive Expense PO");
                    tabs.tabs( "select", 4);
                    tabs.tabs( "disable", 0);
                    tabs.tabs( "disable", 1);
                    tabs.tabs( "disable", 2);
                    tabs.tabs( "disable", 3);
                    $(".close_receive_po_confirm",tabs).click(function(){
                            closeReceivePO();
                        }
                    ); */
                    $(".close_receive_po_confirm").click(function(){
                        closeReceivePO();
                    });
                    registerReveicePoHandler();
                }
            });
        }
		
        function refreshReceivePO(itt_number)
        {
            var jsp="canonE580ReceivePO.jsp";
            var urlData = jsp + "?itt_number=" + itt_number;

            blockUsrInterface();
            $.ajax({
                url: urlData,
                cache: false,
                success: function (data) {
                    unBlockUsrInterface();
                    var modelName = "#" + RECEIVE_PO_DIV_ID;
                    $(modelName).html(data);
                    registerReveicePoHandler();
                }
            });
        }
        
        function closeReceivePO()
        {
        	$('#myTabs a').removeClass('active');
   	        $("#tabs-0-tab").addClass('active');

   	    	$('#tabs-5-tab').remove();
   	    	$('#tabs-6-tab').remove();
   	    	
   	    	$('#tabs-5').html("");
   	    	$('#tabs-6').html("");
   	     
   	        $('div[id^="tabs-"]').hide();
   	     $("#tabs-0").css({'display':'block'});	
   	       // $('div' + $("#tabs-1").attr('href')).show();
        	
           /*  var tabs = $( "#tabs" ).tabs();
            tabs.tabs( "remove", 4);
            tabs.tabs( "enable", 0);
            tabs.tabs( "enable", 1);
            tabs.tabs( "enable", 2);
            tabs.tabs( "enable", 3);
            tabs.tabs( "select", 0);
            var modelName = "#" + RECEIVE_PO_DIV_ID;
            $(modelName).html(""); */
        }

        
        function checkCheckedAtLeast(checkboxes, atLeastNum, errormsg, popup) {
          var len=checkboxes.filter(function(index){return this.checked}).length;
          if ( len < atLeastNum) {
            checkboxes.addClass( "ui-state-error" );
            updateTips(errormsg, popup);
            return false;
          } else {
            return true;
          }
        }

        function confirmReceivePO()
        {
            var receive_po_selection = $( [] ).add($(".receive_po_line_selector_helper"));

            var allReceivePOFields = receive_po_selection.add($(".receive_po-serial_number"))
                    .add($(".receive_po-lot_number"))
                    .add($(".receive_po-qty"))
                    .add($(".receive_po-license_number"));
            allReceivePOFields.removeClass( "ui-state-error" );

            var selected_trs=$("tr:has(.receive_po_line_selector_helper:checked)");
            var bValid = true;
            bValid = bValid && checkCheckedAtLeast( receive_po_selection, 1, "Please select at least one line.",true );
            $(".receive_po-qty",selected_trs).each(function(){
                bValid = bValid && checkIfTrue($(this),"QTY must be greater than zero.",$(this).val()>0,true);
            });
            $(".receive_po-serial_number",selected_trs).each(function(){
                bValid = bValid && checkRequired($(this),"<%=SERIAL_NUM_PLACE_HOLDER%>",true);
            });
            
            $(".receive_po-license_number.required_field",selected_trs).each(function(){
                bValid = bValid && checkRequired($(this),"License Number",true);
            });
            
            $(".receive_po-lot_number",selected_trs).each(function(){
                bValid = bValid && checkRequired($(this),"LOT#",true);
            });
            if ( bValid ) {
                $("#receive_po_save_flag").val('Y');
                var url = $("#receive_po_form").attr('action');                
                blockUsrInterface();
                $.post(url, $("#receive_po_form").serialize(), function(data) {
                	
                    unBlockUsrInterface();
                    var msg=$.trim(data);                           
                    if( msg.substring(0,6)==="ERROR:"){
                        updateTips(msg.substring(6));
                        $(".ui-dialog-titlebar").addClass("hd");                        
                    }else{
                    	
                        var modelName = "#" + RECEIVE_PO_DIV_ID;
                        $(modelName).html("");
                        $(modelName).html(data);                       
                        registerReveicePoHandler();
                        $( "#status-message-dialog" ).dialog({
                            buttons: {
                              Ok: function() {
                                $("#receive_po_confirm").attr("disabled", true).addClass("ui-state-disabled");
                                $( this ).dialog( "close" );
                                $( this ).dialog( "destroy" );
                                closeReceivePO();
                              },
                              Cancel: function() {
                                $( this ).dialog( "close" );
                                $( this ).dialog( "destroy" );
                                refreshReceivePO("<%=p_itt_number%>");
                              }
                            }
                          }).dialog("widget")
                          .find("button:first").attr("title","Ok to close the Receive PO tab")
                          .end()
                          .find("button:last").attr("title","Cancal to keep the Receive PO tab open");
                    }
                });
            }
        }

        function registerReveicePoHandler(){

            $("#receive_po_refresh").click(function(){
                refreshReceivePO("<%=p_itt_number%>");
            });

            $("#receive_po_confirm").click(function(){
                    confirmReceivePO();
                }
            );

            $("#receive_po_close").click(function(){
                    closeReceivePO();
                }
            );

            $('.receive_po_line_selector_helper').click(function()
            {
				$(this).prev().val(this.checked? 'Y' :'N');
            });
            
            $('#receive_po_select_all_button').click(function()
            {
                $('.receive_po_line_selector_helper').each(function(){
                    this.checked=true;
                    $(this).prev().val('Y');
                });
            });

            $('#receive_po_deselect_all_button').click(function()
            {
                $('.receive_po_line_selector_helper').each(function(){
                    this.checked=false;
                    $(this).prev().val('N');
                });
            });

        }
        // END RECEIVE PO 

        // BEGIN DEALER INSTALL CONFIR
        var DEALER_INSTALL_CONFIRM_DIV_ID="tabs-6";
        function openDealerInstallConfirm(itt_number)
        {
            var jsp="canonE580DealerInstallConfirm.jsp";
            var urlData = jsp + "?itt_number=" + itt_number;

            blockUsrInterface();
            $.ajax({
                url: urlData,
                cache: false,
                success: function (data) {
                    unBlockUsrInterface();
                    var modelName = "#" + DEALER_INSTALL_CONFIRM_DIV_ID;

                    $("#myTabs").append('<a id=\"tabs-6-tab\" href="#tabs-6" class="active">Dealer Install Confirm <span class="close_dealer_install_confirm" style="margin-left:20px;background:#ffffff;color:#000000">X</span></a>');
                    var tabs = $( "#tabs" ).tabs();
                    $('#myTabs a').removeClass('active');
           	        $('#tabs-6-tab').addClass('active');

           	        $('div[id^="tabs-"]').hide();
           	     $("#tabs-6").html("");
           	     $("#tabs-6").append(data);
           	     $("#tabs-6").css({'display':'block'});	
                    
                   /*  var tabs = $( "#tabs" ).tabs();
                    tabs.tabs("option","tabTemplate","<li><a href='\#{href}'>\#{label}</a> <span class='close_dealer_install_confirm ui-icon ui-icon-close'>Remove Tab</span></li>");
                    tabs.append("<div id="+DEALER_INSTALL_CONFIRM_DIV_ID+">"+data+"</div>");
                    tabs.tabs( "add",modelName, "Dealer Install Confirm");
                    tabs.tabs( "select", 4);
                    tabs.tabs( "disable", 0);
                    tabs.tabs( "disable", 1);
                    tabs.tabs( "disable", 2);
                    tabs.tabs( "disable", 3);
                    $(".close_dealer_install_confirm",tabs).click(function(){
                            closeDealerInstallConfirm();
                        }
                    ); */
                    $(".close_dealer_install_confirm").click(function(){
                        closeDealerInstallConfirm();
                    }
                );
                    registerDealerInstallConfirmHandler();
                }
            });
        }
        
        function closeDealerInstallConfirm()
        {
        	$('#myTabs a').removeClass('active');
   	        $("#tabs-0-tab").addClass('active');
   	    	
   	    	$('#tabs-6-tab').remove();
   	     
   	        $('div[id^="tabs-"]').hide();
   	     $("#tabs-0").css({'display':'block'});	
        	
           /*  var tabs = $( "#tabs" ).tabs();
            tabs.tabs( "remove", 4);
            tabs.tabs( "enable", 0);
            tabs.tabs( "enable", 1);
            tabs.tabs( "enable", 2);
            tabs.tabs( "enable", 3);
            tabs.tabs( "select", 0);
            var modelName = "#" + DEALER_INSTALL_CONFIRM_DIV_ID;
            $(modelName).html(""); */
        }
        
        function confirmDealerInstallConfirm()
        {
         
            var dealercheckboxes= $( [] ).add($(".dealer_install_confirm_line_selector_helper"));
            var dealercomments=$("#dealer_install_confirm_release_comment");
            var alldealerfields = $( [] ).add(dealercheckboxes).add(dealercomments);
            alldealerfields.removeClass( "ui-state-error" );

            var bValid = true;
            bValid = bValid && checkCheckedAtLeast( dealercheckboxes, 1, "Please select at least one line." );
            bValid = bValid && checkLength( dealercomments, "Release comments", 1, 4000 );
            
            if ( bValid ) {
                $("#dealer_install_confirm_save_flag").val('Y');
                var url = $("#dealer_install_confirm_form").attr('action');
                blockUsrInterface();
                $.post(url, $("#dealer_install_confirm_form").serialize(), function(data) {
                    unBlockUsrInterface();
                    var msg=$.trim(data);                           
                    if( msg.substring(0,6)==="ERROR:"){
                        updateTips(msg.substring(6));
                        $(".ui-dialog-titlebar").addClass("hd");                        
                    }else{
	                    var modelName = "#" + DEALER_INSTALL_CONFIRM_DIV_ID;
	                    var uncheckedTotal=$(".dealer_install_confirm_line_selector_helper:not(:checked)").length;
	                    if(uncheckedTotal==0){
	                        closeDealerInstallConfirm();
	                    } else{
	                        $(modelName).html("");
	                        $(modelName).html(data);
	                        registerDealerInstallConfirmHandler();
	                    }
                	}
                });
            }
        }

        function registerDealerInstallConfirmHandler(){

            $("#dealer_install_confirm_confirm").click(function(){
                    confirmDealerInstallConfirm();
                }
            );

            $("#dealer_install_confirm_close").click(function(){
                    closeDealerInstallConfirm();
                }
            );

            $('.dealer_install_confirm_line_selector_helper').click(function()
            {
            	var checkboxName=$(this).attr('name');                        
                var isChecked=$('input:checkbox[name='+checkboxName+']').is(':checked');
                $(this).prev().val(isChecked? 'Y' :'N');   
                //$(this).prev().val($(this).attr('checked')? 'Y' :'N');
            });
            
            $('#dealer_install_confirm_select_all_button').click(function(event)
            {
           	    event.preventDefault();
                $('.dealer_install_confirm_line_selector_helper').each(function(){
                    this.checked=true;
                    $(this).prev().val('Y');
                });
            });
            
            $('#dealer_install_confirm_deselect_all_button').click(function(event)
            {
           	    event.preventDefault();
                $('.dealer_install_confirm_line_selector_helper').each(function(){
                    this.checked=false;
                    $(this).prev().val('N');
                });
            });

        }
        // END DEALER INSTALL CONFIR

        function openDealerShipToCNACodeLOV(mName,options){
            openITTLOV(mName,"Dealer Ship To Code (C.N.A) LOV",'canonE580DealerShipToCNACodeLOV.jsp',300,480, options);
        }
        
		function openDealerShipToCNADnameLOV(mName,options){
            openITTLOV(mName,"Dealer Ship To Code (C.N.A) LOV",'canonE580DealerShipToCNADnameLOV.jsp',300,600, options);
        }

        function openReasonCodeLOV(mName,options){
            openITTLOV(mName,"Reason Code LOV",'canonE580ReasonCodeLOV.jsp',300,480, options);
        }
    
        function openScheduledDDHistory(width, options)
        {
            title="Scheduled Delivery Date History";
            var url = "canonE580ScheduledDDHistory.jsp";
            var modelName = "#ScheduledDDHistory-DataDiv";
            if (options) {
                data =jQuery.param(options);
            }
            $(modelName).dialog({
				height: 300,
				title: title, 
				modal: true ,
				zIndex:1005,
				width: 650,		
             resizable: false      
			});
            
           
            blockUsrInterface();
            $.ajax({
                url: url,
                data: data,
                cache: false,
                success: function(data) {
                    unBlockUsrInterface();
                   
                    $(modelName).html("");
                    $(modelName).html(data);
                   
                }
            });   
            $(modelName).dialog("open");
            $(".ui-dialog-titlebar").addClass("hd");
    	     $(".ui-dialog").css({"top":"330px"}); 
    	     $('.ui-widget-overlay').css({'background':'none'});
        }
    
        $( "#tabs" ).tabs({
        	
            selected:<%=tab_index%>,  
            select: function( event, a ) {
                if(a.index!=$("#tab_index").val()){
                    $("#tab_index").val(a.index);
                    alert("here");
                }
        }}).show();

        $("a.linkPage").click(function(event) {
            var pageNo = $(this).data("page");
            $("#page_no").val(pageNo);
            ITTDetailSearch();
            event.preventDefault();
        });

        $('#itt_order_processor_lov_icon').click(function()
        {
            var dc=$.trim($("#itt_order_processor_display").val());
            var m=dc.match (/(.*)\(.*\)/);
            openITTOrderProcessorLOV("ITTOrderProcessorLOV-DataDiv", {"processor_name": m? $.trim(m[1].toUpperCase()):dc });
        });
        
        $('#dealer_code_lov_icon').click(function()
        {
            var dc=$("#dealer_code_display").val();
            var m=dc.match (/\((.*)\)/);
            openDealerLOV("DealerCodeLOV-DataDiv",{"dealer_name": m? m[1].toUpperCase():dc });
        });
        
		 $('#dealer_codec_lov_icon').click(function()
			        {
			            var dc=$("#dealer_code_id").val();
						//alert(dc);
			            var m=dc.match (/\((.*)\)/);
			            openDealerCodeLOV("DealerCodeCLOV-DataDiv",{"dealer_code": m? m[1].toUpperCase():dc });
			        });
			        
        
        $('#markview_lov_icon').click(function()
        {
            openMarkviewLOV("MarkviewLOV-DataDiv",{"itt_number":"<%=p_itt_number%>"});
        });
        
        var mass_apply_enabled=false;
        var partial_po_pdf_generation_enabled=false;
        
        $('#toggle_mass_apply_button').click(function(){
        	if(partial_po_pdf_generation_enabled){
        		$('#toggle_partial_po_pdf_generation_button').trigger( "click" );
        	}
            mass_apply_enabled=!mass_apply_enabled;
            if(mass_apply_enabled){
                $('<col width="20px" />').insertBefore($("#itt_line_detail_table col:first"));
                $("#itt_line_detail_table tr").each(function(){
                        if($(this).is(".GOOD_LINE")){
                            $(this).prepend('<td class="mass_apply"><input type="checkbox" class="mass_apply_line_number"  checked></td>');
                        }else{
                            
                        	if(this.id=="report_tbl_first"){
                            	$(this).prepend('<td class="hd mass_apply" style="vertical-align: middle;"><input type="checkbox" id="mass_apply_select_unselect_all" title="Select/Unselect all" checked></td>');
                        	}else{
                                $(this).prepend('<td class="hd mass_apply"></td>');
                        	}
                            
                        }
                    });
                $(".mass_apply_button").show();
            }else{
                $(".mass_apply").remove();
                $(".mass_apply_button").hide();
                $("#itt_line_detail_table col:first").remove();
            }
        });
        
        $('#toggle_partial_po_pdf_generation_button').click(function(){
        	if(mass_apply_enabled){
        		$('#toggle_mass_apply_button').trigger( "click" );
        	}
        	partial_po_pdf_generation_enabled=!partial_po_pdf_generation_enabled;
            if(partial_po_pdf_generation_enabled){
                $('<col width="20px" />').insertBefore($("#itt_line_detail_table col:first"));
                $("#itt_line_detail_table tr").each(function(){
                		lineNum=$(this).find("input[name='line_number']").val();
                        if($(this).is(".GOOD_LINE")){
                            $(this).prepend('<td class="partial_po_pdf_generation">'
                            		+'<input type="checkbox" class="partial_line_number" name="partial_po_pdf_generation_line_number" value="'+lineNum+'"></td>');
                        }else {
                        	if(this.id=="report_tbl_first"){
                            	$(this).prepend('<td class="hd partial_po_pdf_generation" style="vertical-align: middle;"><input type="checkbox" id="select_unselect_all" title="Select/Unselect all"></td>');
                        	}else{
								$(this).prepend('<td class="hd partial_po_pdf_generation"></td>');
                        	}
                        }
                    });
            }else{
                $(".partial_po_pdf_generation").remove();
                $("#itt_line_detail_table col:first").remove();
            }
        });
        
        $("#report_tbl_first").on("click","#select_unselect_all", function(){
			var selected=$("#select_unselect_all")[0].checked;
			$(".partial_po_pdf_generation :checkbox").prop("checked", selected);
        });
        
        $("#itt_line_detail_table").on("click",".partial_line_number", function(){
        	var all_=$("#itt_line_detail_table .partial_line_number").length;
        	var checked_=$("#itt_line_detail_table .partial_line_number:checked").length;
        	var allChecked_=all_==checked_;
       		$("#select_unselect_all").prop("checked", allChecked_);
        });

        $("#report_tbl_first").on("click","#mass_apply_select_unselect_all", function(){
			var selected=$("#mass_apply_select_unselect_all")[0].checked;
			$(".mass_apply :checkbox").prop("checked", selected);
        });
        
        $("#itt_line_detail_table").on("click",".mass_apply_line_number", function(){
        	var all_=$("#itt_line_detail_table .mass_apply_line_number").length;
        	var checked_=$("#itt_line_detail_table .mass_apply_line_number:checked").length;
        	var allChecked_=all_==checked_;
       		$("#mass_apply_select_unselect_all").prop("checked", allChecked_);
        });

        
        $(".mass_apply_button").click(function(){
            var column_index=$(this).parent().index();
            var mass_checked=$(".mass_apply input[type='checkbox']:checked").not("#mass_apply_select_unselect_all");
            var mass_value;
            mass_checked.each(function(index){
                var mass_tr=$(this).parent().parent();
                var mass_td=mass_tr.children()[column_index];
                var mass_element=$(mass_td).find("select,input");
                if(index==0){
                    mass_value=mass_element.val();
                }else{
                    mass_element.val(mass_value);
                    if(mass_element.is("input[type='hidden']")){
                        mass_element.next().text(mass_value);
                    }    
                }
            });
        });
            
        $("#add_mics_expense_charge_button").click(function(){
            var html='<tr class="oddtableDataCell">'+
                (mass_apply_enabled? '<td class="hd mass_apply"></td>': '')+
                    '<td align="center">'+
                        '<input type="hidden" name="line_number">'+
                    '</td>'+
                    '<td align="center">'+
                        '<input type="hidden" name="item_code" value="<%=EXPENSE_ITEM_CODE%>"><%=EXPENSE_ITEM_CODE%>'+
                    '</td>'+
                    '<td align="center">'+
                        '<input name="description" class="required_field" type="text" size="20" value="" style="width:100%">'+
                    '</td>'+
                    '<td></td>'+
                    '<td align="center">1 </td>'+
                    '<td align="center">'+
                        '<input name="purchase_price" class="currency required_field" type="text" size="10" value="">'+
                    '</td>'+
                    '<td align="center">'+
                        '<input name="finder_fee" type="hidden">'+
                    '</td>'+
                    '<td align="center">'+
                        '<input name="install_credit" type="hidden">'+
                    '</td>'+
                    '<td align="center">'+
                        '<input name="product_sourced_by" type="hidden" >'+
                    '</td>'+
                    '<td align="center">'+
                        '<input name="itt_status" type="hidden" >'+
                    '</td>'+
                    '<td align="center">'+
                        '<input name="delivery_scheduled_date" type="hidden" >'+
                    '</td>'+
                    '<td align="center">'+
                        '<input name="reason_code" type="hidden" >'+
                    '</td>'+
                    '<td align="center">'+
                        '<input name="serial_number" type="hidden">'+
                    '</td>'+
                    '<td align="center">'+
                        '<input name="po_number" type="hidden" >'+
                    '</td>'+
                '</tr>';
            var newTr=$(html);
            $('input.currency',newTr).autoNumeric();
            $('#itt_line_detail_table tr:last').after(newTr);
            $('#itt_line_detail_table tr:last input[name="description"]').focus();
            return false;
        });

        $('#generate_po_pdf').click(function()
        {
            var bValid = true;
        	if(partial_po_pdf_generation_enabled){
	            var _checkboxes= $( [] ).add($(".partial_po_pdf_generation :checkbox"));
    	        _checkboxes.removeClass( "ui-state-error" );
                bValid = bValid && checkCheckedAtLeast( _checkboxes, 1, "Please select at least one line for Partial PO PDF Generation." );
        	}
        	if(bValid){
	            $("#save_flag").val("GENERATE_PO_PDF");
	            <% if (timestamp!=null && timestamp.getPdfCreationDate()==null){%>
	                $("#generate_po_pdf_warning").show();
	            <%}%>
	            $("#lineDetailForm").submit();
        	}
        });
        
        $('#generate_gsd_pdf').click(function()
                {
                    $("#save_flag").val("GENERATE_GSD_PDF");
                    $("#lineDetailForm").submit();
                });		
                
        
        $("#create_po").click(function(){
			$("#cusa_po_num").val("");
			$("#dealer_po_num").val("");
			$("#add_to_multiple_po").val("");
            
            ITTDetailCreatePO('C','Create PO',"<%=p_itt_number%>");
        });
        
        $("#add_to_existing_po").click(function(){
			$("#cusa_po_num").val("");
			$("#dealer_po_num").val("");
			$("#add_to_multiple_po").val("");
            
        	ITTDetailCreatePO('A','Add To Existing PO',"<%=p_itt_number%>");
        });

        $("#receive_po").click(function(){
            openReceivePO("<%=p_itt_number%>");
        });
        $("#receive_exp_po").click(function(){
            openReceiveExpPO("<%=p_itt_number%>");
        });
        
        $("#receive_po_refresh").click(function(){
            refreshReceivePO("<%=p_itt_number%>");
        });

        $("#dealer_install_confirm").click(function(){
            openDealerInstallConfirm("<%=p_itt_number%>");
        });

        $('input.currency').autoNumeric();
        $("input.autoInteger").autoNumeric({mDec: 0,  mNum:20});
        $("input.autoMultiplier").autoNumeric({mDec: 4,  mNum:20, aPad:false});
        $("input.rate").autoNumeric({mDec: 5,  mNum:20 });
        
        //$('input.date-picker').datepicker();
        
        $('.itt_timestamp').datetimepicker({
            controlType: 'select',
            timeFormat: 'hh:mm TT'
        });
        
        var comments = $( "#comments" ),
          allFields = $( [] ).add( comments );

        function updateTips( t, popup ) {
        	        	
            var html=
                '<div title="Alert">'
                    +'<p>'
                    +    '<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 50px 0;"></span>'
                    + t 
                    + '</p>'+
                '</div>';
            $( html ).dialog({
              modal: true,
              buttons: {
                Ok: function() {
                  $( this ).dialog( "close" );
                }
              }
            });               
            $(".ui-dialog-title").addClass("hd");
            $(".ui-dialog-titlebar").addClass("hd");
            
        }

        function showmsgs( t, popup ) {
            var html=
                '<div title="Enter and SAVE the below fields">'
                    +'<p>'
                    +    '<span style="float: left; margin: 0 7px 50px 0;"></span>'
                    + t 
                    + '</p>'+
                '</div>';
            $( html ).dialog({
              modal: true,
              height: "auto !important",
              buttons: {
                Ok: function() {
                  $( this ).dialog( "close" );
                }
              }
            });                
        }		
        
        function checkLength( o, n, min, max ) {
          if ( o.val().length > max || o.val().length < min ) {
            o.addClass( "ui-state-error" );
            updateTips( "Length of " + n + " must be between " +
              min + " and " + max + "." );
            return false;
          } else {
            return true;
          }
        }

        function checkRequired( o, n, popup) {
          if ( empty(o.val())) {
            o.addClass( "ui-state-error" );
            updateTips( n + " is required" , popup);
            return false;
          } else {
            return true;
          }
        }

        function checkIfTrue( o, n, valid, popup) {
          if (!valid) {
            o.addClass( "ui-state-error" );
            updateTips(n, popup);
            return false;
          } else {
            return true;
          }
        }

        function checkEmail( o, n ) {
            return checkRegexp( o, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, n );
        }
        
        function checkRegexp( o, regexp, n ) {
          if ( !( regexp.test( o.val() ) ) ) {
            o.addClass( "ui-state-error" );
            updateTips( n );
            return false;
          } else {
            return true;
          }
        }

        function checkDateTimeAfter( o, n, after, after_name) {
            var afterDateTime=new Date(after);
            var d=new Date(o.val());
            if (d.getTime()<=afterDateTime.getTime()){
              o.addClass( "ui-state-error" );
              updateTips( n + " must be after "+after_name +"("+ after+")");
              return false;
            } else {
              return true;
            }
        }

        function checkDateTimeBefore( o, n, before, before_name) {
            var beforeDateTime=new Date(before);
            var d=new Date(o.val());
            if (d.getTime()>=beforeDateTime.getTime()){
              o.addClass( "ui-state-error" );
              updateTips( n + " must be before "+before_name +"("+ before+")");
              return false;
            } else {
              return true;
            }
        }

        function empty(str){
            return !str || !/[^\s]+/.test(str);
        }
        
        function checkIttOrderProcessor(){
            if ( !empty($.trim($("#itt_order_processor_display").val())) && empty($("#itt_order_processor_id").val()) ){
              updateTips("Please select an ITT Order Processor.");
              return false;
            }else{
              return true;  
            }
        }
        
		//Added Raghavendra Uppala ITG690711 Start
		function confirm_reset() {
           return confirm("Are you sure you want to reset all text?");
        }        


		function refreshlines() {
		javascript:location.reload();
		}
	
		 $('.Comp_link').click(function()
        {
			if ( $("#dealer_code_id").val()==''){				
				updateTips('Enter Dealer Code and Save it');
				$("#dealer_code_id").addClass( "ui-state-error" );				
			} else {
			var header_id=$(this).data("header_id");
			var line_id=$(this).data("line_id");	
            var price_list=$(this).data("price_list");			
            var order_line_num=$(this).data("order_line_num");
			var order_item_code=$(this).data("order_item_code");
			var itt_number=$(this).data("itt_number");
			var p_comp_status=$(this).data("p_comp_status");
			var p_dealer_code_id=$(this).data("p_dealer_code_id");
			var p_dealer_code_name=$(this).data("p_dealer_code_name");
			//alert(" comp value "+ order_line_num + "::" + order_item_code + "::" + itt_number+ "::" + price_list+ "::" ++ p_dealer_code_id+ "::" + p_dealer_code_name);
            console.log("order_line_num is "+order_line_num);
            openDealerCompLOV("DealerCompLOV-DataDiv",{"header_id":header_id,"line_id":line_id,"line_number":order_line_num,"item_code":order_item_code,"itt_number":itt_number,"dealer_code":p_dealer_code_id,"dealer_name":p_dealer_code_name,"price_list":price_list,"comp_status":p_comp_status});
			}});	
			
		 $('.dealer_confirm').click(function()
        {		
		  var messages='<ul class="message">';
		  var bValid=true;	 
		  var bVal = true;
		  if ( empty($("#dealer_code_id").val())) {
             messages+="<li>"+'Original Dealer Code Cannot be null'+"</li>";
		   	bValid=false;
		  $("#dealer_code_id").addClass( "ui-state-error" );
           }		 
		  if ( empty($("#itt_order_processor_display").val())) {
             messages+="<li>"+'ITT Order Processor Cannot be null'+"</li>";
		   	bValid=false;
		  $("#itt_order_processor_display").addClass( "ui-state-error" );
           }		  
		  if ( empty($("#dealer_code_display").val())) {
            messages+="<li>"+'Original Dealer Name Cannot be null'+"</li>";
			$("#dealer_code_display").addClass( "ui-state-error" );
			bValid=false;
           }		   		  
	      if ( empty($("#dealer_email").val())) {
		   messages+="<li>"+'Original Dealer Email Address Cannot be null'+"</li>"; 
			$("#dealer_email").addClass( "ui-state-error" );		   
		   bValid=false;
          }		 
		  //if ( $("#dealer_email").val()!=''){
		  //	  bVal=chkEmail(dealer_email);
		  //	  if (bVal==false){
	      //	messages+="<li>"+'Invalid Original Dealer Email Format'+"</li>";
		  //  $("#dealer_email").addClass( "ui-state-error" );				
		  //			bValid=false;
		  //		}
		  // }		   
		  if ( empty($("#dealer_shipto_name").val())) {
            messages+="<li>"+'Ship To Dealer Name Cannot be null'+"</li>";
			$("#dealer_shipto_name").addClass( "ui-state-error" );			
			bValid=false;
           }	  
	      if ( empty($("#dealer_shipto_email").val())) {
		   messages+="<li>"+'Ship To Dealer Email Address Cannot be null'+"</li>"; 
			$("#dealer_shipto_email").addClass( "ui-state-error" );		   
		   bValid=false;
          }	
		  if ( $("#dealer_shipto_email").val()!=''){
			  bVal=chkEmail(dealer_shipto_email);
			  if (bVal==false){
				messages+="<li>"+'Invalid Ship To Dealer Email Format'+"</li>";
			  $("#dealer_shipto_email").addClass( "ui-state-error" );				
				bValid=false;
				}
		   }
            <%
                if (CompChkList.size() > 0) {
                    for (int i = 0; i < CompChkList.size(); i++) {
                        CanonE580ITTWorkbenchDAO.DlrCompChk comp = (CanonE580ITTWorkbenchDAO.DlrCompChk) CompChkList.get(i);

            %>		
				messages+="<li>"+'No Dealer Comp exists for Line#<%=CanonE580ITTWorkbenchUtil.nonNullify(comp.getLineNum())%>, Item:<%=CanonE580ITTWorkbenchUtil.nonNullify(comp.getItemCode())%>'+"</li>";			
				bValid=false;
		   <%
                }
            } 
			if (OverrideChkList.size() > 0) {
            %>	
				messages+="<li>"+'Comp Override Approval is Required'+"</li>";			
				bValid=false;			
            <% } %>	
		  if ( $("#compstatus").val()=="INPROGRESS") {
             messages+="<li>"+'Comp Override Approval is INPROGRESS.'+"</li>";
		   	bValid=false;
		  }				
		  if ( $("#compstatus").val()=="REJECTED") {
             messages+="<li>"+'Comp Override Approval is REJECTED.'+"</li>";
		   	bValid=false;
		  }			
           messages+="</ul>";
		 if (bValid==false){		   
		   updateTips(messages);
		 }else{
            var html='<div id="dialog-confirm" title="Dealer Confirm">'+
            '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span> Are you sure to Confirm the Dealer?</p></div>';

            $( html ).dialog({
                  resizable: false,
                  height:150,
                  modal: true,
                  buttons: {
                    Cancel: function() {
                      $( this ).dialog( "close" );
                    },
                    "Yes": function() {
						ITTDetailSave();
						var itt_number="<%=p_itt_number%>";			
						//alert(itt_number);
						var msg;
						var urlDetail="canonE580DealerConfirm.jsp";
						var  qryString="itt_number="+itt_number
                        $( this ).dialog( "close" );
                        blockUsrInterface();      
					    	$.ajax({
							url: urlDetail,
							cache: false,
							type:"POST",
							data:qryString,
							async:false,
							success: function(data){     
								var sRes="";
								sRes=data;
								sRes= $.trim(sRes); 
								msg=sRes;					   
							}             
							});
					  unBlockUsrInterface();
					  updateTips(msg);	
                    }
                  }
            });
           }		   
         });			
			
		 $('.comp_override').click(function()
        {	
		var bValid=true;
		var messages='<ul class="message">';
            <%
                if (CompChkList.size() > 0) {
                    for (int i = 0; i < CompChkList.size(); i++) {
                        CanonE580ITTWorkbenchDAO.DlrCompChk comp = (CanonE580ITTWorkbenchDAO.DlrCompChk) CompChkList.get(i);

            %>		
				messages+="<li>"+'No Dealer Comp exists for Line#<%=CanonE580ITTWorkbenchUtil.nonNullify(comp.getLineNum())%>, Item:<%=CanonE580ITTWorkbenchUtil.nonNullify(comp.getItemCode())%>'+"</li>";			
				bValid=false;
		   <%
                }
            } 
            %>	
			
           messages+="</ul>";
		 if (bValid==false){		   
		   updateTips(messages);
		 }else{			
            var html='<div id="dialog-confirm" title="Submit Workflow">'+
            '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span> Are you sure to Submit the Workflow?</p></div>';

            $( html ).dialog({
                  resizable: false,
                  height:150,
                  modal: true,
                  buttons: {
                    Cancel: function() {
                      $( this ).dialog( "close" );
                    },
                    "Yes": function() {
						var itt_number="<%=p_itt_number%>";	
						var user_id="<%=userIdValue%>";									
						//alert(itt_number);
						var msg;
						var urlDetail="canonE580SubmitCompApprvWF.jsp";
						var  qryString="itt_number="+itt_number+"&user_id="+user_id
                        $( this ).dialog( "close" );
                        blockUsrInterface();      
					    	$.ajax({
							url: urlDetail,
							cache: false,
							type:"POST",
							data:qryString,
							async:false,
							success: function(data){     
								var sRes="";
								sRes=data;
								sRes= $.trim(sRes); 
								msg=sRes;					   
							}             
							});
					  unBlockUsrInterface();
					  showmessage(msg);	
					  
                    }
                  }
            });	
		 }			
         });	
		 
		 $('.comp_refresh').click(function()
        {			
            var html='<div id="dialog-confirm" title="Submit Comp Refresh">'+
            '<p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span> Are you sure to Refresh the Dealer Compensation from PriceList?</p></div>';

            $( html ).dialog({
                  resizable: false,
                  height:150,
                  modal: true,
                  buttons: {
                    Cancel: function() {
                      $( this ).dialog( "close" );
                    },
                    "Yes": function() {
						var itt_number="<%=p_itt_number%>";	
						//var user_id="<%=userIdValue%>";									
						//alert(itt_number);
						var msg;
						var urlDetail="canonE580SubmitCompRefresh.jsp";
						var  qryString="itt_number="+itt_number
                        $( this ).dialog( "close" );
                        blockUsrInterface();      
					    	$.ajax({
							url: urlDetail,
							cache: false,
							type:"POST",
							data:qryString,
							async:false,
							success: function(data){     
								var sRes="";
								sRes=data;
								sRes= $.trim(sRes); 
								msg=sRes;					   
							}             
							});
					  unBlockUsrInterface();
					  showmessage(msg);	
					  
                    }
                  }
            });			
         });		 
		 
        function showmessage( t, popup ) {
            var html=
                '<div title="Alert">'
                    +'<p>'
                    +    '<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 50px 0;"></span>'
                    + t 
                    + '</p>'+
                '</div>';
            $( html ).dialog({
              modal: true,
              height: "auto !important",
              buttons: {
                Ok: function() {
                  $( this ).dialog( "close" );
				  refreshlines();
                }
              }
            });                
        }
		
		<% if (PendingDlrList.size() > 0) {%>
            $("#dealer_confirm").attr("disabled", false).removeClass("ui-state-disabled");
		<% } else { %>	
		    $("#dealer_confirm").attr("disabled", true).addClass("ui-state-disabled");
		<% } %>	
		
        <% if("INPROGRESS".equalsIgnoreCase(p_comp_status)) {%>
            //$("#dealer_confirm").attr("disabled", true).addClass("ui-state-disabled");
            $("#dealer_comp_refresh").attr("disabled", true).addClass("ui-state-disabled");	
			$("#dealer_comp_override").attr("disabled", true).addClass("ui-state-disabled");			
        <% } %>		

        <% if("APPROVED".equalsIgnoreCase(p_comp_status)) {%>
            $("#dealer_comp_refresh").attr("disabled", true).addClass("ui-state-disabled");				
        <% } %>			
		//Added ITG690711 End
        
        function isValidShipToCNACode(ele,handler){
            var code=ele.val();
            if(empty(code) || code == ele.data("saved_value")) {
                handler.pass();
            }else{
                $.ajax({
                    type: "GET",
                    url: "canonE580Service.jsp",
                    data: "action=validate_ship_to_cna_code&v="+code,
                    dataType: "json",
                    success: function(data){
                        if(data.pass){
                            handler.pass();
                        }else{
                            handler.fail();
                        }
                    },
                    error: function( jqXHR, textStatus, errorThrown ){
                        console.log(textStatus);
                        console.log(errorThrown);
                        updateTips("System error cooured.");
                        handler.fail();
                    }
                });
            }
        }

        $( "#add-notes-dialog" ).dialog({
          autoOpen: false,
          height: 320,
          width: 400,
          modal: true,
          buttons: {
            "Add Note": function() {
              var bValid = true;
              allFields.removeClass( "ui-state-error" );
              bValid = bValid && checkLength( comments, "comments", 1, 4000 );
              if ( bValid ) {
                ITTDetailSave();
                $( this ).dialog( "close" );
              }
            },
            Cancel: function() {
              $( this ).dialog( "close" );
            }
            
          },         
          close: function() {
            allFields.val( "" ).removeClass( "ui-state-error" );
          }
          
        });

        $("#add-new-note-button").click(function() {
            $("#comments").val("");
            $( "#add-notes-dialog" ).dialog( "open" );
            $(".ui-dialog-titlebar").addClass("hd");
    	     $(".ui-dialog").css({"top":"330px"}); 
    	     $('.ui-widget-overlay').css({'background':'none'});    	     
    	     $(".ui-dialog-buttonset button:nth-child(1)").addClass("btn");
    	     $(".ui-dialog-buttonset button:nth-child(2)").addClass("btn");
        });
        
        var dealer_email = $("#dealer_email" ),
          dealer_phone = $("#dealer_phone" ),
          dealer_contact_name = $("#dealer_contact_name" ),
          cust_contact_email=$("#cust_contact_email" ),
		  dealer_shipto_email = $("#dealer_shipto_email" ),
          ship_to_cna_code=$("#dealer_ship_to_cna_code"),
          dealer_supplier_code=$("#dealer_supplier_code"),
          lineDetailFields = $( [] ).add( dealer_email, dealer_phone, dealer_contact_name, cust_contact_email, ship_to_cna_code,dealer_supplier_code );
        
        $('.save_button').click(function()
        {
            $(".ui-state-error").removeClass("ui-state-error");
           // var tabIndex=parseInt($("#tab_index").val());
           // alert($('a[class=active]').attr('href'));
           // #tabs-4
           
            
            var selectedTab=$('a[class=active]').attr('href');
            var selectedTabPosition=selectedTab.substring(selectedTab.indexOf("-")+1,selectedTab.length);
            var tabIndex=parseInt(selectedTabPosition);                       
            var bValid = true;
            bValid = bValid && checkIttOrderProcessor();
            bValid = bValid && checkLength( dealer_contact_name, "dealer contact name", 0, 4000 );
            bValid = bValid && checkLength( dealer_phone, "dealer phone", 0, 4000 );
 		   	if ($("#gsdeditmode").val()!='YES') {
            	bValid = bValid && (empty($("#dealer_email").val()) || checkEmail( dealer_email, "Invalid dealer email format." ));
            	bValid = bValid && (empty($("#cust_contact_email").val()) || checkEmail( cust_contact_email,"Invalid ship to customer contact email format." ));
           	}
 		   	
 			  if ($("#gsdeditmode").val()=='YES') {
 					
 				  var messages='<ul class="message">';
 				  var bValid=true;	 
 				  var bVal = true;
 				  if ( empty($("#dealer_code_id").val())) {
 		             messages="<li>"+'Original Dealer Code Cannot be null'+"</li>";
 				   	bValid=false;
 				  $("#dealer_code_id").addClass( "ui-state-error" );
 		           }		 
 				  if ( empty($("#itt_order_processor_display").val())) {
 		             messages="<li>"+'ITT Order Processor Cannot be null'+"</li>";
 				   	bValid=false;
 				  $("#itt_order_processor_display").addClass( "ui-state-error" );
 		           }		  
 				  if ( empty($("#dealer_code_display").val())) {
 		            messages+="<li>"+'Original Dealer Name Cannot be null'+"</li>";
 					$("#dealer_code_display").addClass( "ui-state-error" );
 					bValid=false;
 		           }		   		  
 			      if ( empty($("#dealer_email").val())) {
 				   messages+="<li>"+'Original Dealer Email Address Cannot be null'+"</li>"; 
 					$("#dealer_email").addClass( "ui-state-error" );		   
 				   bValid=false;
 		          }		 
 				//  if ( $("#dealer_email").val()!=''){
 				//	  bVal=chkEmail(dealer_email);
 				//	  if (bVal==false){
 				//		messages+="<li>"+'Invalid Original Dealer Email Format'+"</li>";
 				//	  $("#dealer_email").addClass( "ui-state-error" );				
 				//		bValid=false;
 				//	}
 				//   }		   
 					//  if ( empty($("#dealer_shipto_name").val())) {
 					//    messages+="<li>"+'Ship To Dealer Name Cannot be null'+"</li>";
 					//	$("#dealer_shipto_name").addClass( "ui-state-error" );			
 					//	bValid=false;
 					//   }	  
 					//  if ( empty($("#dealer_shipto_email").val())) {
 					//   messages+="<li>"+'Ship To Dealer Email Address Cannot be null'+"</li>"; 
 					//$("#dealer_shipto_email").addClass( "ui-state-error" );		   
 					//   bValid=false;
 					//  }	//Commented dealer_shipto_name,dealer_shipto_email ITG#730424
 				//  if ( $("#dealer_shipto_email").val()!=''){
 				//   bVal=chkEmail(dealer_shipto_email);
 				//  if (bVal==false){
 				//		messages+="<li>"+'Invalid Ship To Dealer Email Format'+"</li>";
 				//	  $("#dealer_shipto_email").addClass( "ui-state-error" );				
 				//		bValid=false;
 				//		}
 				//   }		  
 		           messages+="</ul>";
 				 if (bValid==false){		   
 				   updateTips(messages);
 				 }
 				   	
 				 } //ITG#690711 End

 		   	
            bValid = bValid && (isValidShipToCNACode(ship_to_cna_code,
            {
                pass:function(){
                    var bValid = true; // different context
                    $(".required_field").each(function(){
                        bValid =bValid & checkRequired($(this),$("#TH_"+$(this).attr("name").toUpperCase()).text());
                        if(!bValid ) return false;
                    });
                    
                    
                    if(tabIndex==<%=TAB_MAIN%>){
                    	$("select.product_sourced_by.itt_local_ship").each(function(){
                            bValid = bValid && checkIfTrue($(this),'Sourced By must be "CSA" on ITT LOCAL SHIP',$(this).val()=="CSA",true);
                    	});
                    }                    
                    if(tabIndex==<%=TAB_TIMESTAMPS%>){
                        bValid = bValid && (empty($("#po_accepted_date_by_dlr").val()) || checkDateTimeAfter($("#po_accepted_date_by_dlr"), "Date PO Accepted by Dealer", $("#booked_date").text(),"Order Booked Date"));
                        if(!empty($("#po_date").text())){
                            bValid = bValid && (empty($("#po_accepted_date_by_dlr").val()) || checkDateTimeBefore($("#po_accepted_date_by_dlr"), "Date PO Accepted by Dealer", $("#po_date").text(),"PO Creation Date"));
                            bValid = bValid && (empty($("#po_sent_date_to_cusa").val()) || checkDateTimeAfter($("#po_sent_date_to_cusa"), "Date PO Sent to CUSA", $("#po_date").text(),"PO Creation Date"));
                        }
                        bValid = bValid && (empty($("#date_cna_po_approved_by_dealer").val()) || checkDateTimeAfter($("#date_cna_po_approved_by_dealer"), "Date CNA PO Approved by Dealer", $("#po_sent_date_to_cusa").val(),"Date PO Sent to CUSA"));
                        bValid = bValid && (empty($("#shipped_date_from_cusa").val()) || checkDateTimeAfter($("#shipped_date_from_cusa"), "Date Equipment Shipped from CUSA", $("#date_cna_po_approved_by_dealer").val(),"Date CNA PO Approved by Dealer"));
                        bValid = bValid && (empty($("#equip_arrive_at_dlr_date").val()) || checkDateTimeAfter($("#equip_arrive_at_dlr_date"), "Date Equipment Arrives at Dealer", $("#shipped_date_from_cusa").val(),"Date Equipment Shipped from CUSA"));
                        bValid = bValid && (empty($("#pod_rcvd_from_dlr_date").val()) || checkDateTimeAfter($("#pod_rcvd_from_dlr_date"), "Date POD Received from Dealer", $("#equip_arrive_at_dlr_date").val(),"Date Equipment Arrives at Dealer"));
                    }
                    if ( bValid ) {
                        ITTDetailSave();
                    }
                },
                fail:function(){
                    ship_to_cna_code.addClass( "ui-state-error");
                    updateTips( "Invalid Ship To Code (C.N.A.)");
                }
            }));

        }).attr("title","click to save the changes including header information");
      
        $('.export-excel-button').click(function()
        {
            $("#save_flag").val("export-excel");
            $("#lineDetailForm").submit();
        });
        
        $('#show_all_button').click(function()
        {
            $("#page_no").val("1");
            $("#rowsPerPage").val("<%=ALL_ROWS%>");
            ITTDetailSearch();
            event.preventDefault();
        });
        
        $('.add_to_popdf_helper').click(function()
        {
        	var checkboxName=$(this).attr('name');                        
            var isChecked=$('input:checkbox[name='+checkboxName+']').is(':checked');
            $(this).prev().val(isChecked? 'Y' :'N');     
        	
            //$(this).prev().val($(this).attr('checked')? 'Y' :'N');
        });
        
        <% if(poFreezed) {%>
            $("#add_mics_expense_charge_button").attr("disabled", true).addClass("ui-state-disabled");
        <% } %>
            
        $(".product_sourced_by").change(function(){
            var td=$(this).parent().prev().prev().prev();
            var oldval=td.data("purchase_price");
            if($(this).val()=="DEALER"){
                if(td.has("input[type='text']").length==0){
                    td.html('<input name="purchase_price" class="currency" type="text" size="10" value="'+oldval+'">');
                    $('input.currency',td).autoNumeric();
                }
            }else{
                if(td.has("input[type='text']").length>0){
                    td.html('<input type="hidden" name="purchase_price" value="'+oldval+'">'+oldval);
                }
            }
            var line_number=$(this).data("line-number");
            var psb=$(this).val();
            $("."+line_number).each(function(){
                    $(this).val(psb);
                    $(this).next().text(psb);
                }
            );
            return true;
        });
        
        $('#dealer_ship_to_cna_code_lov_icon').click(function()
         {
            var dc=$("#dealer_ship_to_cna_code").val();
            openDealerShipToCNACodeLOV("DealerShipToCNACodeLOV-DataDiv",{"dealer_ship_to_cna_code": dc });
         });
        
		$('#dealer_ship_to_cna_dname_lov_icon').click(function()
		         {
		            var dc=$("#dealer_shipto_name").val();
					//alert(dc);
		            openDealerShipToCNADnameLOV("DealerShipToCNADnameLOV-DataDiv",{"dealer_shipto_name": dc });
		         });
		         
        
         
       // $("#.reason_code").attr('readonly', true).addClass("oddtableDataCell");
        $(".reason_code_lov_icon").click(function()
        {        	        	        	
            $.reason_code_container=$(this).parents(".reason_code_container");
            openReasonCodeLOV("ReasonCodeLOV-DataDiv");
        });
        
        $(".reason_code_container").bind("selected", function(event, object) {
            $("#ReasonCodeLOV-DataDiv").html("");
            $("#ReasonCodeLOV-DataDiv").dialog("close");
            $("#ReasonCodeLOV-DataDiv").dialog("destroy");
            var ele=$(this).find(".reason_code");
            ele.val(object.reason_code);
            ele.attr("title",object.reason_code);
        });

//        $(".reason_code").attr("disabled","disabled");

        $(".scheduled_dt_history").click(function()
        {
            var itt_number="<%=p_itt_number%>";
            var line_number=$(this).parent().parent().find("input[name='line_number']").val();
            openScheduledDDHistory(480,{"itt_number":itt_number,"line_number":line_number});
        });

        $(".tracking_num").attr('readonly', true).addClass("oddtableDataCell");

        $("a.linkPage").click(function(event) {
            var pageNo = $(this).data("page");
            $("#notes_page_no").val(pageNo);
            ITTDetailSearch();
            event.preventDefault();
        }); //ITG690711 Added the function for itt_notes_details
	
        $('#myTabs a').click(function (e) {
       		$('#myTabs a').removeClass('active');
   	        $(e.currentTarget).addClass('active');
   	        
   	        var selectedDidLinkId=$(e.currentTarget).attr('id');
   	    	$('#selected_tab').val(selectedDidLinkId);

   	    	$('#tabs-5-tab').remove();
   	    	$('#tabs-6-tab').remove();
   	    	$("#tabs-5").html("");
   	    	$("#tabs-6").html("");
   	    	
   	        $('div[id^="tabs-"]').hide();
   	        
   	        var selectedDivId=$('div' + $(e.currentTarget).attr('href')).attr('id');
   	        $('#selected_div').val(selectedDivId);
   	        var selectedDivIndex=selectedDivId.substring(selectedDivId.indexOf("-")+1,selectedDivId.length);   	     
         var tabIndex=parseInt(selectedDivIndex);      
   	     $("#tab_index").val(tabIndex);
   	    
   	        $('div' + $(e.currentTarget).attr('href')).show();
       	});

        
        	/* $('#myTabs a').click(function (e) {
        	var href = $(this).attr('href'); 
        	var id=$(this).attr('id');
        	 
        	 var tabHyperLinks = [
            	           'tabs-1-tab',
            	           'tabs-2-tab',
            	           'tabs-3-tab',
            	           'tabs-4-tab'        	           
            	        ];
        	
        	$.each(tabHyperLinks, function (index, value) {        		
   	         if(value==id)
   	        	$('#'+value).addClass('active');   	        	 
   	         else
   	        	$('#'+value).removeClass('active');        	          
   	        });
        	
        	var alltabs = [
        	           '#tabs-1',
        	           '#tabs-2',
        	           '#tabs-3',
        	           '#tabs-4',
        	           '#tabs-5',
        	           '#tabs-6'
        	        ];
        	        $.each(alltabs, function (index, value) {
        	         if(value==href)
        	        	 $(value).css({'display':'block'});	
        	         else
        	        	 $(value).css({'display':'none'});         	          
        	        });
        	
        	
        	 
        	
        	 $("#tabs-2").css({'display':'none'});	
        	 $("#tabs-3").css({'display':'none'});	
        	 $("#tabs-4").css({'display':'none'});	
        	$('#myTabs a[href="#tabs-2"]').tab('show'); // Select tab by name        
        	
        	 
        	}); */
    });

</script>

<form name="lineDetailForm" id="lineDetailForm" method="post" action="canonE580LineDetailInclude.jsp">
    <input type="hidden" name="tab_index" id="tab_index" value="<%=tab_index%>"/>
    <input type="hidden" name="save_flag" id="save_flag"/>
    <input type="hidden" name="rowsPerPage" id="rowsPerPage" value="<%=rowsPerPage%>"/>
    <input type="hidden" name="page_no" id="page_no" value="<%=page_no%>"/>
    <input type='hidden' name='order_by' id='order_by' value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_order_by)%>">
    <input type='hidden' name='asc_desc' id='asc_desc' value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_asc_desc)%>">
    <input type='hidden' name='itt_number' id='itt_number' value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_number)%>">
    <input type='hidden' name='order_number' id='order_number' value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_order_number)%>">
    <input type="hidden" name="itt_order_processor_id" id="itt_order_processor_id"  value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_order_processor_id)%>"/>
    <input type="hidden" name="itt_order_processor_name" id="itt_order_processor_name"  value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_order_processor_name)%>"/>
    <input type="hidden" name="dealer_code_id" id="dealer_code_id"  value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_code_id)%>"/>
    <input type="hidden" name="dealer_code_name" id="dealer_code_name"  value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_code_name)%>"/>
    <input type="hidden" name="current_itt_status" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_status)%>"/>

    <input type="hidden" name="vendor_id" id="vendor_id"  value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_vendor_id)%>"/>
    <input type="hidden" name="vendor_site_id" id="vendor_site_id"  value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_vendor_site_id)%>"/>
    <input type="hidden" id="dealer_supplier_code" name="dealer_supplier_code" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_prnt_vnd_cd)%>"/>
    <input type="hidden" id="selected_div" name="selected_div" value="<%=selected_div%>"/>
    <input type="hidden" id="selected_tab" name="selected_tab" value="<%=selected_tab%>"/>
    <input type="hidden" name="create_po_called_from" id="create_po_called_from"/>
    <input type="hidden" name="add_to_multiple_po" id="add_to_multiple_po"/>
    <input type="hidden" name="cusa_po_num" id="cusa_po_num"/>
    <input type="hidden" name="dealer_po_num" id="dealer_po_num"/>
    
    <fieldset>
    <br>
    <div class="inner_table">
     <table align="center" class="linedetails-table" style="border: 0px solid #cccccc;display:inline">
     <tr>
     <td valign="top">
    
      <table id="linedetailsITTtable" style="background: #ffffff;border: 1px solid #cccccc" >
       <tr>
        <th colspan="4" class="hd">ITT</th>
       </tr>
       <tr>
          <td style="text-align:right;"><b>ITT#:</b></td>
          <td >
            <input style="width:120px;background:#cccccc" class="rdl inTxt" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_number)%>" name="ITTNumber" readonly="readonly">
          </td>
          <td style="text-align:right;"><b>Order # :</b></td>
          <td >
           <input class="rdl inTxt" style="width:120px;background:#cccccc" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_order_number)%>" name="Order" readonly="readonly">
          </td>
       </tr>
       <tr>
                <td style="text-align:right;"><b>Booked Date:</b></td>
                <td >
                <input class="rdl inTxt"  style="width:120px;background:#cccccc" type="text" value="<%=CanonE580ITTWorkbenchUtil.formatDate2(p_booker_date)%>" name="bookeddate" readonly="readonly">
                </td>
                <td  style="text-align:right;"><b>ITT Status:</b></td>
                    <td>
                    <input class="rdl inTxt" style="width:120px;background:#cccccc" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_status)%>" name="ITTstatus" readonly="readonly">
                    </td>
            </tr>
            <tr>
                <td   style="text-align:right;" ><b>CSA Salesrep:</b></td>
                    <td>
                    <input class="rdl inTxt" style="width:120px;background:#cccccc"  type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_sales_rep)%>" name="csaSalesRep" readonly="readonly">
                    
                    </td>
                <td  style="text-align:right;"><b>Sales Zone:</b></td>                
                    <td>
                    <input class="rdl inTxt" style="width:120px;" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_sales_zone)%>" name="csaSalesRep" readonly="readonly">
                    
                    </td>
            </tr>
    		<tr>
                <td  style="text-align:right;"><b>Branch:</b></td>
                    <td>
                    <input class="rdl inTxt" style="width:120px;background:#cccccc" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_sales_branch)%>" name="csaSalesRep" readonly="readonly">                    
                    </td>
                <td   style="text-align:right;"><b>ITT Order Processor:</b></td>
                    <td>    
                                <input type="text" class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="itt_order_processor_display" style="width:98px;" name="itt_order_processor_display"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_itt_order_processor_display)%>"  onkeypress=""  onKeyUp ="">
                                    <img class="lov_icon" id="itt_order_processor_lov_icon" src="<%=commonRoot(request)%>/images/download.png" alt="" height="16">
                    </td>
            </tr>
            <tr>
                <td  style="text-align:right;"><b>CMAP:</b></td>
                    <td>                     
                        <select name="cmap" id="cmap"  style="width:50%;float:left;margin-left:8px;">
                            <option value=""></option>
                            <% for (int i = 0; i < cmapLovList.size(); i++) {
                                    String cmap_lov = (String) cmapLovList.get(i);
                            %>
                            <option value="<%=cmap_lov%>"  <%=cmap_lov.equalsIgnoreCase(p_CMAP) ? "SELECTED" : ""%> > <%=cmap_lov%> </option>
                            <%}%>
                        </select>									
                   </td>
                 <td   style="text-align:right;"><b>Mail Invoices To:</b></td>
                    <td>
                    <div class="select-container" >
                        <select name="mail_invoice_to" id="mail_invoice_to" style="width:120px;margin-left:5px;">
                        <option value=""></option>
                        <% for (int i = 0; i < mailInvoicesToLovList.size(); i++) {
                                String alov = (String) mailInvoicesToLovList.get(i);
                        %>
                        <option value="<%=alov%>"  <%=alov.equalsIgnoreCase(p_mail_invoice_to) ? "SELECTED" : ""%> > <%=alov%> </option>
                        <%}%>
                        </select>
                    </div>
                   </td>
                   </tr>
    			<tr>
                <td  style="text-align:right;"><b>Therefore Images:</b></td>
                    <td><img id="markview_lov_icon" src="<%=commonRoot(request)%>/images/download.png" alt="" height="16" style="float:left;margin-left:8px;"></td>
                <td  style="text-align:right;"><b><%=DEALER_PO_NUM_PLACE_HOLDER%></b></td>
                    <td>
                    <input class="rdl inTxt" style="width:120px;background:#cccccc" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_delear_po_number)%>" name="csaSalesRep" readonly="readonly">
                    </td>
                    
            </tr>
            </table>
     	</td>
       <td valign="top">
       <table align="center" class="linedetails-table" style="background: #ffffff;border: 1px solid #cccccc" >		
            <tr>
                <th colspan="4" class="hd">Customer</th>
            </tr>
            <tr>
                <td   style="text-align:right;"><b>Ship To Cust:</b></td>
                    <td>
                    <input class="rdl inTxt" style="width:120px;background:#cccccc" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_ship_to_cust)%>" name="csaSalesRep" readonly="readonly">                    
                    </td>
                <td   style="text-align:right;"><b>Location:</b></td>
                    <td>
                     <input class="rdl inTxt" style="width:120px;background:#cccccc" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_ship_to_address)%>" name="csaSalesRep" readonly="readonly">
                    </td>
                    </tr>
                    <tr>
                <td  style="text-align:right;"><b>City,State:</b></td>
                    <td>
                     <input class="rdl inTxt" style="width:120px;background:#cccccc" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_city_state)%>" name="csaSalesRep" readonly="readonly">
                    </td>
                <td  style="text-align:right;"><b>Postal:</b></td>
                    <td>
                      <input class="rdl inTxt" style="width:120px;background:#cccccc" type="text" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_postal)%>" name="csaSalesRep" readonly="readonly">
                    </td>
            </tr>
            
            <tr>
                <td  style="text-align:right;"><b><span title="Ship To Customer Contact">Contact Name:</span></b></td>
                    <td> <input type="text" style="width:120px;" class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="cust_contact_name" name ="cust_contact_name"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_cust_contact_name)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                <td  style="text-align:right;"><b><span title="Ship To Customer Contact Phone">Contact Phone:</span></b></td>
                    <td> <input type="text" style="width:120px;" class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="cust_contact_phone" name ="cust_contact_phone"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_cust_contact_phone)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                     </tr>
                    <tr>
                <td style="text-align:right;"><b><span title="Ship To Customer Contact Fax">Contact Fax:</span></b></td>
                    <td> <input type="text"  style="width:120px;" class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="cust_contact_fax" name ="cust_contact_fax"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_cust_contact_fax)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                <td  style="text-align:right;"><b><span title="Ship To Customer Contact Email">Contact Email</span></b></td>
                    <td> <input type="text" style="width:120px;" class="inTxt" onblur= "this.value = (this.value).replace(/^\s+|\s+$/g, '');" id="cust_contact_email" name ="cust_contact_email"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_cust_contact_email)%>"  onkeypress=""  onKeyUp ="">
                    </td>
            </tr>
            </table>
       </td>
       
     <td  valign="top">
     <table class="linedetails-table" style="background: #ffffff;border: 1px solid #cccccc">
        <tr>
          <th colspan="4" class="hd">Dealer</th>
            </tr>
            
            <tr>
                <td  style="text-align:right;"><b>Dealer Code:</b></td>
                    <td>
                        <div style="width:100%;">
                                <input type="text" id="dealer_code_id" name="dealer_code_id"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_code_id)%>">
                                <img class="lov_icon" style="float:rigth" id="dealer_codec_lov_icon" src="<%=commonRoot(request)%>/images/download.png" alt="" height="16">                            
                            <div style="clear:both"></div>
                        </div>
                        </td>
                        
                <td  style="text-align:right;"><b>Dealer Name:</b></td>
                    <td>
                        <div style="width:100%;">
                            
                                <input type="text" class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" style="width: 100px;" id="dealer_code_display" name="dealer_code_display"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_code_display)%>">                                                                                        
                                <img class="lov_icon" style="float:rigth" id="dealer_code_lov_icon" src="<%=commonRoot(request)%>/images/download.png" alt="" height="16">                            
                            <div style="clear:both"></div>
                        </div>
                        </td>
                    
                    </tr>
            <tr>
                <td  style="text-align:right;"><b>Contact Name:</b></td>
                    <td> <input type="text" style="width:120px;" class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="dealer_contact_name" name ="dealer_contact_name"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_contact_name)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                        
                <td  style="text-align:right;"><b>Phone:</b></td>
                    <td> <input type="text" style="width:120px;"  class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="dealer_phone" name ="dealer_phone"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_phone)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                    </tr>
            
            <tr>
                <td  style="text-align:right;"><b>Location:</b></td>
                <td><textarea rows="3" cols="10" name ="dealer_address" id="dealer_address" style="width:120px;float:left;margin-left:5px;font-size:13px;" class="inTxt"><%=CanonE580ITTWorkbenchUtil.threeLineAddress(p_dealer_address_line1,p_dealer_address_line2,p_dealer_address_line3)%></textarea>
                    </td>
                <td  style="text-align:right;"><b>Email:</b></td>
                    <td> <input type="text" style="width:120px;"  class="inTxt" onblur= "this.value = (this.value).replace(/^\s+|\s+$/g, '');" id="dealer_email" name ="dealer_email"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_email)%>"  onkeypress=""  onKeyUp ="">
                    </td>
            </tr>
            <tr>
                <td  style="text-align:right;"><b>City, State:</b></td>
                    <td> <input type="text" style="width:120px;"  class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="dealer_city_state" name ="dealer_city_state"  value = "<%=CanonE580ITTWorkbenchUtil.cityState(p_dealer_city,p_dealer_state)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                <td   style="text-align:right;"><b>Postal:</b></td>
                    <td> <input type="text" style="width:120px;"  class="inTxt" onblur= "this.value = (this.value).replace(/^\s+|\s+$/g, '');" id="dealer_zip" name="dealer_zip"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_zip)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                    </tr>
     </table>
     </td>
     
       
     <td  valign="top">
     <table class="linedetails-table" style="background: #ffffff;border: 1px solid #cccccc">
        <tr>
          <th colspan="4" class="hd">Ship To Dealer</th>
            </tr>
            
            <tr>
                <td  style="text-align:right;"><b>Ship To Code:</b></td>
                    <td>
                        <div style="width:100%;">
                                <input type="text" class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" style="width: 100px;" id="dealer_ship_to_cna_code"" name="dealer_ship_to_cna_code"  value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_ship_to_cna_code)%>"  data-saved_value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_ship_to_cna_code)%>">
                                <img class="lov_icon" style="float:rigth" id="dealer_ship_to_cna_code_lov_icon" src="<%=commonRoot(request)%>/images/download.png" alt="" height="16">                            
                            <div style="clear:both"></div>
                        </div>
                        </td>
                        
                <td  style="text-align:right;"><b>Ship To Dealer Name:</b></td>
                    <td>
                        <div style="width:100%;">
                                <input type="text" class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" style="width: 100px;" id="dealer_shipto_name" name="dealer_shipto_name"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_shipto_name)%>">
                                <img class="lov_icon" style="float:rigth" id="dealer_ship_to_cna_dname_lov_icon" src="<%=commonRoot(request)%>/images/download.png" alt="" height="16">                            
                            <div style="clear:both"></div>
                        </div>
                        </td>
                    
                    </tr>
            <tr>
                <td  style="text-align:right;"><b>Contact Name:</b></td>
                    <td> <input type="text" style="width:120px;" class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="dealer_shipto_contact_name" name ="dealer_shipto_contact_name"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_shipto_contact)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                        
                <td  style="text-align:right;"><b>Phone:</b></td>
                    <td> <input type="text" style="width:120px;"  class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="dealer_shipto_phone" name ="dealer_shipto_phone"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_shipto_phone)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                    </tr>
            
            <tr>
                <td  style="text-align:right;"><b>Location:</b></td>
                <td><textarea rows="3" cols="10" name ="dealer_shipto_address" id="dealer_shipto_address" style="width:120px;float:left;margin-left:5px;font-size:13px;" class="inTxt"><%=CanonPCISecurityUtil.htmlEncode(CanonE580ITTWorkbenchUtil.threeLineAddress(p_dealer_shipto_add_ln1,p_dealer_shipto_add_ln2,p_dealer_shipto_add_ln3))%></textarea>
                    </td>
                <td  style="text-align:right;"><b>Email:</b></td>
                    <td> <input type="text" style="width:120px;"  class="inTxt" onblur= "this.value = (this.value).replace(/^\s+|\s+$/g, '');" id="dealer_shipto_email" name ="dealer_shipto_email"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_shipto_email)%>"  onkeypress=""  onKeyUp ="">
                    </td>
            </tr>
            <tr>
                <td  style="text-align:right;"><b>City, State:</b></td>
                    <td> <input type="text" style="width:120px;"  class="inTxt" onblur= "this.value = (this.value.toUpperCase()).replace(/^\s+|\s+$/g, '');" id="dealer_shipto_city_state" name ="dealer_shipto_city_state"  value = "<%=CanonE580ITTWorkbenchUtil.cityState(p_dealer_shipto_city,p_dealer_shipto_state)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                <td   style="text-align:right;"><b>Postal:</b></td>
                    <td> <input type="text" style="width:120px;"  class="inTxt" onblur= "this.value = (this.value).replace(/^\s+|\s+$/g, '');" id="dealer_shipto_zip" name="dealer_shipto_zip"  value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(p_dealer_shipto_zip)%>"  onkeypress=""  onKeyUp ="">
                    </td>
                    </tr>
            
     </table>
     </td>

     
     </tr>	
     </table>
    
	<%if(GSDeditMode){%>     <!--ITG690711--> 
        <table id="header_table" align="center" cellspacing="2" width="100%" border="0" style="table-layout:fixed;" >		
    
            <col width="110px" />
            <col width="150px" />
            <col width="60px" />
            <col width="100px" />
            <col width="60px" />
            <col width="80px" />
            <col width="50px" />
            <col width="100px" />			
			<tr>
            <td>			
			<button id="dealer_comp_refresh" class="ui-state-default ui-corner-all comp_refresh edit_mode" type="button"  style="margin-left: 20px">
            <span class="ui-button-text">Refresh Comp($)</span>
			</button>	
			</td>
			<td>
			<input type="hidden" id="gsdeditmode" name="gsdeditmode" value="YES">
			<input type="hidden" id="compstatus" name="compstatus" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(p_comp_status)%>">
            <button id="dealer_comp_override" class="ui-state-default ui-corner-all comp_override edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Approve(Comp Override)</span>
            </button>	
            </td>
			<td NOWRAP class ="search_text"  align = "right"><b>Comp Status:</b></td>
			<% if ("REJECTED".equalsIgnoreCase(p_comp_status)){ %>
            <td><font color="red"><%=CanonE580ITTWorkbenchUtil.nonNullify(p_comp_status)%></font></td>	
			<%}  else if ("APPROVED".equalsIgnoreCase(p_comp_status)){ %>	
			<td><font color="blue"><%=CanonE580ITTWorkbenchUtil.nonNullify(p_comp_status)%></font></td>	
			<%}  else {  %>	
			<td><%=CanonE580ITTWorkbenchUtil.nonNullify(p_comp_status)%></td>		
			<%} %>			
			</tr>	 
        </table>		
        <%if (CompList.size() > 0) { %>	
        <table id="header_table" align="center" cellspacing="2" width="100%" border="0" style="table-layout:fixed;" >		
    
            <col width="50px" />
            <col width="30px" />
            <col width="50px" />	
            <col width="120px" />
            <col width="50px" />
            <col width="50px" />	
            <col width="70px" />
            <col width="50px" />				
            <tr class="input_seperator">
                <td colspan="8" >&nbsp;Dealer Comp Summary</td>
            </tr>
            <%
                if (CompList.size() > 0) {
                    for (int i = 0; i < CompList.size(); i++) {
                        CanonE580ITTWorkbenchDAO.DealerCompTotal comp = (CanonE580ITTWorkbenchDAO.DealerCompTotal) CompList.get(i);

            %>			
			<tr>
			    <td NOWRAP class ="search_text"  align = "right"><b>Dealer Code:</b></td><td><%=CanonE580ITTWorkbenchUtil.nonNullify(comp.getDealerCode())%> </td>	
                <td NOWRAP class ="search_text"  align = "right"><b>Dealer Name:</b></td><td><%=CanonE580ITTWorkbenchUtil.nonNullify(comp.getDealer())%> </td>	
				<td NOWRAP class ="search_text"  align = "right"><b>Total Comp:$</b></td><td><%=CanonE580ITTWorkbenchUtil.formatBigDecimal(comp.getTotalComp())%> </td>		
				<td NOWRAP class ="search_text"  align = "right"><b>Total Maintenance Comp:$</b></td><td><%=CanonE580ITTWorkbenchUtil.formatBigDecimal(comp.getTotalMaintComp())%> </td>					     
		   </tr>	
            <%
                }
            } 
            %>			
		</table>
		<% } %>		
	<%}%>		
			<!--ITG690711 End-->			
            
       </div> 
    </fieldset>
    
    
    
	

    <div class="tab-information">
    
    <div class="tab-nav" id="myTabs">
      
        	<a href="#tabs-0"	id="tabs-0-tab" data-toggle="tab" >Main</a>
        	<a href="#tabs-1"   id="tabs-1-tab" data-toggle="tab" >Maintenance</a>
       		<a href="#tabs-2"	id="tabs-2-tab" data-toggle="tab" >Notes</a>
        	<a href="#tabs-3"	id="tabs-3-tab" data-toggle="tab" >Timestamps</a>    
        	  
        <!-- <a href="#tabs-1" style="color:#000000;">Main</a>
        <a href="#tabs-2" style="color:#000000;">Maintenance</a>
        <a href="#tabs-3" style="color:#000000;">Notes</a>
        <a href="#tabs-4" style="color:#000000;">Timestamps</a> -->
      </div>
      <br>
      <div id="tabs-0">
        <div id="generate_po_pdf_warning">PO PDF created for the first time, Line status might have been changed, please <a href="javascript:location.reload();">refresh</a> the page after open or save the pdf.</div>
        <% if (list.size() > 0) {
                int totalPages = (total_record - 1) / rowsPerPage + 1;
                int currentPage = (p_from_record.intValue() - 1) / rowsPerPage + 1;
        %>
        <div style="float:left">
        <%=CanonE580ITTWorkbenchUtil.genPaginationSummary(total_record, rowsPerPage, currentPage)%>
        <% if (rowsPerPage!=ALL_ROWS && list.size() < total_record) { %>
        <a href="#" id="show_all_button">Show All</a>
        <%}%>
        <% if (list.size() > 1 && editMode) { %>
                <div style="display: inline;margin-left: 310px">
                    <input type="checkbox" id="toggle_partial_po_pdf_generation_button" name="toggle_partial_po_pdf_generation_button">
                    <label for="toggle_partial_po_pdf_generation_button">Partial PO PDF Generation</label>
                    <input type="checkbox"  id="toggle_mass_apply_button" name="toggle_mass_apply_button">
                    <label for="toggle_mass_apply_button">Enable Mass Apply</label>
                </div>
        <% } %>
        </div>
        <div style="float:right">
            <button class="btn save_button edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Save</span>
            </button>
            <button class="btn export-excel-button edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Export Excel</span>
            </button>
        </div>
        <br>
        <% } %>
        <br>
        <div class="inner_table">
        <table border="0" width="100%" cellspacing="1" cellpadding="4" id="itt_line_detail_table" class="supplies-table" style="margin-left:-10px;" >
<!-- 
before gsd changes:
	there are 15 columns, last column is the scheduled delivery date history, 
	after click mass apply,extra column enabled, totally 16 columns.
-->
        
<!--Order Line#-->         <col width="50px" />
<!--Item Code-->	   <col width="50px" />
<!--Description-->	   <col width="200px" />
<!--Line Type-->	   <col width="80px" />
<!--QTY-->	           <col width="30px" />
<!--Purchase Price-->      <col width="50px" />
						   <%if(GSDeditMode){ %> 
<!--Dealer Comp-->         <col width="50px" />       <!--ITG690711-->  
						   <%}%>
						   <%if(!GSDeditMode){ %> 
<!--FINDER FEE-->          <col width="50px" />
<!--Install Credit-->      <col width="50px" />
						   <%}%>
<!--Sourced By-->	   <col width="70px" />
<!--Status-->	           <col width="180px" />
<!--Scheduled Deliv Date--><col width="50px" />
<!--Reason Code-->	   <col width="150px" />
<!--Serial#-->	           <col width="50px" />
<%if(!GSDlocalEditMode){%> 
<!--PO#-->	           <col width="50px" />
<%}%>		  
<%if(GSDeditMode){%> 
<%if(!GSDlocalEditMode){%> <!--ITG#732264 Added new columns for GSD dropship-->
<!--PO Ack Status-->	       <col width="50px" />	
<!--shipped Date-->	       <col width="50px" />			  
<!--ship method-->	       <col width="50px" />			  
<!--tracking number-->	   <col width="150px" />
<!--shipment#-->	           <col width="50px" />			  		
<%}%>
<%if(GSDlocalEditMode){%> 
<!--Delivery#-->	       <col width="50px" />
<!--Delivery Status-->     <col width="50px" />
<!--Shipset#-->	           <col width="100px" />
<!--tracking number-->	   <col width="50px" />
<!--ship method-->	       <col width="50px" />	  
<%} }%>
<!--Scheduled History-->	       <col width="50px" />	            
            
            <% if (list.size() > 1) { %>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
				<%if(!GSDeditMode){ %>
				<td></td>
				<%}%>
				<%if(GSDeditMode){ %>
				<input type="hidden" id="gsd" name="gsd" value="YES">
				<%}%>	
                <td style="text-align: center">
                    <button class="btn ui-state-default ui-corner-all mass_apply_button edit_mode" type="button" title="apply first selected value to others">
                    <span class="ui-button-text">Apply</span></button>
                </td>
                <td style="text-align: center">
                    <button class="btn ui-state-default ui-corner-all mass_apply_button edit_mode" type="button" title="apply first selected value  to others">
                    <span class="ui-button-text">Apply</span></button>
                </td>
                <td style="text-align: center">
                    <button class="btn ui-state-default ui-corner-all mass_apply_button edit_mode" type="button" title="apply first selected value  to others">
                    <span class="ui-button-text">Apply</span></button>
                </td>
                <td></td>
                <td></td>
				<%if(GSDeditMode){%>  
				<%if(!GSDlocalEditMode){%> <!--ITG#732264 Added new columns for GSD dropship-->
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>				
				<%} }%>				
				<%if(GSDlocalEditMode){ %>
				<td></td>
				<td></td>
				<td></td>
				<td></td>				
				<%}%>				
            </tr>
            <% } %>
            
            <tr id="report_tbl_first">
                <th style="width:50px">Order Line#</th>
                <th style="width:50px">Item Code</th>
                <th style="width:200px" id="TH_DESCRIPTION">Description</th>
                <th style="width:80px">Line Type</th>
                <th style="width:30px">QTY</th>
                <th style="width:50px" id="TH_PURCHASE_PRICE">Purchase Price</th>
				<%if(GSDeditMode){%>     <!--Added Raghavendra Uppala ITG690711--> 
				<th style="width:50px">Dealer Comp($)</th>
				<%}%>
				<%if(!GSDeditMode){%>    <!--Added Raghavendra Uppala ITG690711--> 
                <th style="width:50px">FINDER FEE</th>
                <th style="width:50px">Install Credit</th>
				<%}%>
                <th style="width:70px">Sourced By</th>
                <th style="width:180px">Status</th>
                <th style="width:50px">Scheduled Delivery Date</th>
                <th style="width:150px">Reason Code</th>
                <th style="width:50px"><%=SERIAL_NUM_PLACE_HOLDER%></th>
				<%if(!GSDeditMode){%> 
                <th style="width:50px"><%=PO_NUM_PLACE_HOLDER%></th>
				<%}%>
				<%if(GSDeditMode){%>  
				<%if(!GSDlocalEditMode){%> <!--ITG#732264 Added new columns for GSD dropship-->
				<th style="width:50px">PO#</th> 
				<th style="width:50px">POACK Status</th>
				<th style="width:50px">Shipped Date</th>
				<th style="width:50px">Shipping Method</th>
				<th style="width:50px">Tracking Number</th> 
				<th style="width:50px">Shipment#</th> 				               
				<%} }%>
				<%if(GSDlocalEditMode){%> 
                <th style="width:50px">Delivery#</th>  
                <th style="width:50px">Delivery Status</th>  
				<th style="width:50px">Ship Set</th> 
                <th style="width:50px">Tracking Number</th> 	
                <th style="width:50px">Shipping Method</th> 				
                <%}%>				
                <th ></th>
            </tr>

            <%
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        CanonE580ITTWorkbenchDAO.LineDetailItem line = (CanonE580ITTWorkbenchDAO.LineDetailItem) list.get(i);
                        boolean isNewLine=CanonE580ITTWorkbenchUtil.isEmpty(line.getCusaPoNumber());
						String order_line_num = line.getLineNumber();     // ITG690711
						String order_item_code = line.getItem();          // ITG690711 
						String itt_number = line.getIttNumber();          // ITG690711 
						String price_list = line.getPriceListName();          // ITG690711 
						//System.out.println("price_list is: " +price_list);	
						String line_id = line.getLineId();                //ITG690711 
						BigDecimal header_id = line.getheaderId();            //ITG690711 
						BigDecimal amt = line.getcomplinetotal();            //ITG690711 
						String comp_amt=amt.toString();
            %>

            <tr  class="<%=i%2==0? "evenRow":"oddRow" %> <%= EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem())? "":"GOOD_LINE"%>">
            
                <td align="center">
                    <input type="hidden" name="line_number" value="<%=line.getLineNumber()%>">
                    <%=line.getLineId()==null?" ":line.getLineId()%>
                </td>
                <td align="center">
                    <input type="hidden" name="item_code" value="<%=line.getItem()%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getItem())%> 
                </td>
                <td align="left">
                    <% if (!poFreezed && EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem())){
                    %>
                        <input name="description"  class="<%=(editMode && EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem())) ? " required_field":""%>" type="text" size="20" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getItemDescription())%>" style="width:100%" >
                    <%
                    }else{
                    %>
                        <input type="hidden" name="description" value="<%=line.getItemDescription()%>">
                        <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getItemDescription())%> 
                    <%
                    }
                    %>
                </td>
                <td align="center"><%=CanonE580ITTWorkbenchUtil.nonNullify(line.getSoLineType())%> </td>
                <td align="center"><%=line.getOrderedQuantity()%> </td>
                <td style="text-align: right" data-purchase_price="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getItemPurchasePrice())%>">
                    <%
                        String setItem=getSetItem(line.getLineNumber(),list);
                        if (!poFreezed && (EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem()) || SOURCED_BY_DEALER.equalsIgnoreCase(line.getProductSourcedBy())) && setItem==null){
                    %>
                        <input name="purchase_price" class="currency<%=(editMode && EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem())) ? " required_field":""%>" type="text" size="10" value = "<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getItemPurchasePrice())%>" >
                    <%
                    }else{
                    %>
                        <input type="hidden" name="purchase_price" value="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getItemPurchasePrice())%>">
                        <%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getItemPurchasePrice())%>
                    <%
                    }
                    %>
                </td>
				<%if(GSDeditMode){%>	<!--Added Raghavendra Uppala ITG690711--> 
				<input type="hidden" id="gsdedit" name="gsdedit" value="YES">
				<%if("0".equalsIgnoreCase(comp_amt)){%>		
                <td align="center"><a href="#" class="Comp_link" data-header_id="<%=header_id%>" data-line_id="<%=line_id%>" data-order_line_num="<%=order_line_num%>" data-order_item_code="<%=order_item_code%>" data-itt_number="<%=itt_number%>" data-p_dealer_code_id="<%=p_dealer_code_id%>" data-p_dealer_code_name="<%=p_dealer_code_name%>" data-price_list="<%=price_list%>" data-p_comp_status="<%=p_comp_status%>">...</a> </td>					
				<%} else {%>                 
				<td align="center"><a href="#" class="Comp_link" data-header_id="<%=header_id%>" data-line_id="<%=line_id%>" data-order_line_num="<%=order_line_num%>" data-order_item_code="<%=order_item_code%>" data-itt_number="<%=itt_number%>" data-p_dealer_code_id="<%=p_dealer_code_id%>" data-p_dealer_code_name="<%=p_dealer_code_name%>" data-price_list="<%=price_list%>" data-p_comp_status="<%=p_comp_status%>"><%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getcomplinetotal())%></a> </td>					
                <%}%>
				<%}%>
				<%if(!GSDeditMode){%>	<!--Added Raghavendra Uppala ITG690711--> 
                <td align="center">
                    <% if ((poFreezed && !isNewLine) || EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem()) || setItem!=null){
                    %>
                        <input type="hidden" name="finder_fee" value="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getFinderFee())%>" >
                        <input style="width:50px;" class="currency" readonly="readonly" value="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getFinderFee())%>"/>
                    <%}else{%>
                        <input style="width:50px;" name="finder_fee" class="currency" type="text" size="10" value = "<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getFinderFee())%>" >
                    <%}%>
                </td>
                <td align="center">
                    <% if ((poFreezed && !isNewLine) || EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem()) || setItem!=null){
                    %>
                        <input type="hidden" name="install_credit" value="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getInstallCredit())%>" >
                         <input style="width:50px;" class="currency" readonly="readonly" value="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getInstallCredit())%>"/>
                    <%}else{%>
                        <input name="install_credit" style="width:50px;" class="currency" type="text" size="10" value = "<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(line.getInstallCredit())%>" >
                    <%}%>
                </td>
				<%}%>
                <td align="center">
                    <% 
                        if ((poFreezed && !isNewLine) || EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem()) || setItem!=null || isITTLocalShip(line)){
                    %>
                    <input type="hidden" name="product_sourced_by" class="product_sourced_by <%=setItem%>" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getProductSourcedBy())%>">
                    <input style="width:70px;" readonly="readonly" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getProductSourcedBy())%>"/>
                    <%
                    }else{
                    %>
                     <select name="product_sourced_by" style="width:70px;" class="product_sourced_by <%=lineTypeClass(line)%>" data-line-number="<%=readable(line.getLineNumber())%>">
                        <option value=""></option>
                        <% for (int ii = 0; ii < productSourceLovList.size(); ii++) {
                                String productSource_lov = (String) productSourceLovList.get(ii);
                        %>
                        <option title="<%=productSource_lov%>" value="<%=productSource_lov%>"  <%=productSource_lov.equalsIgnoreCase(line.getProductSourcedBy()) ? "SELECTED" : ""%> > <%=productSource_lov%> </option>
                        <%}%>
                     </select>
                    <%
                    }
                    %>
                </td>
                <td align="center">
                    <% if (EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem())){
                    %>
                    <input type="hidden" name="itt_status" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getIttStatus())%>">
                    <input style="width:70px;" readonly="readonly" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getIttStatus())%>"/>
                    <%
                    }else{
                    %>
                     <select name="itt_status" style="width:70px;">
                           <option value=""></option>
                           <% for (int ii = 0; ii < statusList.size(); ii++) {
                                   String status_lov = (String) statusList.get(ii);
                           %>
                           <option title="<%=status_lov%>" value="<%=status_lov%>"  <%=status_lov.equalsIgnoreCase(line.getIttStatus()) ? "SELECTED" : ""%> > <%=status_lov%> </option>
                           <%}%>
                        </select>
                    <%
                    }
                    %>
                </td>
                <td align="center">
                    <% if (EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem())){
                    %>
                    <input type="hidden" name="delivery_scheduled_date" value="<%=CanonE580ITTWorkbenchUtil.formatDate2(line.getDeliveryScheduledDate())%>">
                    <input style="width:70px;" readonly="readonly" value="<%=CanonE580ITTWorkbenchUtil.formatDate2(line.getDeliveryScheduledDate())%>"/>
                    <%
                    }else{
                    %>
                    <input name="delivery_scheduled_date" style="width:80px;" class="delivery_scheduled_date-picker" type="text" size="10" value = "<%=CanonE580ITTWorkbenchUtil.formatDate2(line.getDeliveryScheduledDate())%>" >
                    <%
                    }
                    %>
                </td>
                <td align="center">
                    <% if (EXPENSE_ITEM_CODE.equalsIgnoreCase(line.getItem())){
                    %>
                    <input type="hidden" name="reason_code" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getReasonCode())%>">
                    <input style="width:110px;" readonly="readonly" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getReasonCode())%>"/>
                    <%
                    }else{
                    %>
                        <div style="width:110px;" class="reason_code_container">
                            
                                <input name="reason_code" class="reason_code" style="width:80px;" size="16" type="text" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getReasonCode())%>"  title = "<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getReasonCode())%>">
                            
                             <img height="16" src="/s21extn/common/images/download.png" class="reason_code_lov_icon">                                
                            
                            <div style="clear:both"></div>
                        </div>
                    <%
                    }
                    %>
                </td>
                <td align="center">
                    <input type="hidden" name="serial_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getSerialNumber())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getSerialNumber())%>
                </td>
				<%if(!GSDeditMode){%> 
                <td align="center">
                    <input type="hidden" name="po_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getCusaPoNumber())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getCusaPoNumber())%>
                </td>
				<%}%>
				<%if(GSDeditMode){%> 
				<%if(!GSDlocalEditMode){%> <!--ITG#732264 Added new columns for GSD dropship-->
                <td align="center">
                    <input type="hidden" name="po_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getCusaPoNumber())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getCusaPoNumber())%>
                </td>				
                <td align="center">
                    <input type="hidden" name="poack_status" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getPoAckStatus())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getPoAckStatus())%>
                </td>
                <td align="center">
                    <input type="hidden" name="shipped_date" value="<%=CanonE580ITTWorkbenchUtil.formatDate2(line.getShippedDate())%>">
                    <%=CanonE580ITTWorkbenchUtil.formatDate2(line.getShippedDate())%>
                </td>
				<td align="center">
                    <input type="hidden" name="ship_method" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getshipmethod())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getshipmethod())%>
                </td>					
				<td align="center">
                    <div style="width:140px;" class="tracking_num_container">
                        <div style="float:Center">
                            <input name="tracking_num" class="tracking_num" size="20" type="text" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(line.gettrackingnumber())%>"  title = "<%=CanonE580ITTWorkbenchUtil.nonNullify(line.gettrackingnumber())%>">
                        </div>
                    </div>				   
                </td>	
				<td align="center">
                    <input type="hidden" name="shipment_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getShipmentNumber())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getShipmentNumber())%>
                </td>
				<%}}%>
				<%if(GSDlocalEditMode){%> 
				<td align="center">
                    <input type="hidden" name="gsd_delivery_number" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getGSDDeliveryNumber())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getGSDDeliveryNumber())%>
                </td>
				<td align="center">
                    <input type="hidden" name="gsd_delivery_status" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getGSDDeliveryStatus())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getGSDDeliveryStatus())%>
                </td>
				<td align="center">
                    <input type="hidden" name="gsd_ship_set" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getshipset())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getshipset())%>
                </td>				
				<td align="center">
                    <div style="width:140px;" class="tracking_num_container">
                        <div style="float:Center">
                            <input name="tracking_num" class="tracking_num" size="20" type="text" value = "<%=CanonE580ITTWorkbenchUtil.nonNullify(line.gettrackingnumber())%>"  title = "<%=CanonE580ITTWorkbenchUtil.nonNullify(line.gettrackingnumber())%>">
                        </div>
                    </div>				   
                </td>
				<td align="center">
                    <input type="hidden" name="ship_method" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(line.getshipmethod())%>">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(line.getshipmethod())%>
                </td>				
				<%}%>
                <td align="center">
                    <img class="scheduled_dt_history" src="/s21extn/e580/images/canonE580SchedHistory_enabled.gif" title="Scheduled Delivery Date History">
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td class="search_res" style="color:#FF0000" colspan="12" align="center"> No records found. Please adjust your search criteria.&nbsp;</td>
            </tr>
            <%
             }
            %>
        </table>
        </div>
        <p></p>
        <br>
        <div style="float:left">
		    <%if(!GSDlocalEditMode){%> 
			<%if(!GSDeditMode){%>
            <button class="btn edit_mode" type="button" id="add_mics_expense_charge_button" >
                <span class="ui-button-text">Add Expense Line</span>
            </button>
			<% } %> 			
            <button id="create_po" class="btn edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Create PO</span>
            </button>
            <% if(lineAddded){ %>
            <button id="add_to_existing_po" class="btn edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Add To Existing PO</span>
            </button>
            <% } %>
            <button id="receive_po" class="btn edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Receive PO</span>
            </button>
			<%if(!GSDeditMode){%>			
            <button id="receive_exp_po" class="btn edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Receive Exp. PO</span>
            </button>
            <button id="generate_po_pdf" class="btn edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Generate PO PDF</span>
            </button>
			<% } } %> 	
			<%if(GSDeditMode){%>	
			<button id="generate_gsd_pdf" class="ui-state-default ui-corner-all edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Generate Order Ack PDF</span>
            </button>	
			<%  } %> 			
            <button id="dealer_install_confirm" class="btn edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Dealer Install Confirm</span>
            </button>
        </div>	
         <!--ITG690711--> 
        <div style="float:right">
            <button class="btn save_button edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Save</span>
            </button>
            <button class="btn export-excel-button edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Export Excel</span>
            </button>
        </div>

        <br>
      </div>
      <div id="tabs-1">
        <%
            result = dao.ittContractTypeLov();
            List contractTypeList = CanonE580ITTWorkbenchUtil.first(result)!=null?(List) CanonE580ITTWorkbenchUtil.first(result) : Collections.EMPTY_LIST;
            result = dao.ittPlanTypeLov();
            List planTypeList = CanonE580ITTWorkbenchUtil.first(result)!=null? (List) CanonE580ITTWorkbenchUtil.first(result): Collections.EMPTY_LIST;
            result = dao.ittBillCycleLov();
            List billCycleList = CanonE580ITTWorkbenchUtil.first(result)!=null? (List) CanonE580ITTWorkbenchUtil.first(result): Collections.EMPTY_LIST;
            if(pricingList.size()>0){
        %>    
          <div class="inner_table">
        <table border="0" width="80%" cellspacing="1" cellpadding="2" id="itt_maintenance_table" class="supplies-table" style="margin-left:-10px;border:none;background:white">
            <tr id="itt_tbl_first"> 	
			    <th STYLE="text-align:center; font-weight:bold; font-size: +10;" class="hd">Decline Maintenance</th	>
                <th>MODEL(QTY)</th>
                <th>Contract Type</th>
                <th>Plan Type</th>
                <th>Term</th>
                <th>Base Price</th>
                <th>Base Bill Cycle</th>
                <th>Overage Bill Cycle</th>
				<th class="hd">Configuration Bundle</th>  <!--ITG#728624--> 
            </tr>
            <%
                String lastModel=null;
                for(int j=0;j<pricingList.size();j++){
                    CanonE580ITTWorkbenchDAO.PricingDetail pd=(CanonE580ITTWorkbenchDAO.PricingDetail)pricingList.get(j);
                    String model=pd.getEquipModel();
                    boolean sameModel=lastModel!=null ? lastModel.equalsIgnoreCase(model) : false;
                    BigDecimal qty=pd.getQuantity();
                    String contract_type=pd.getContractType();
                    String plan_type=pd.getPlanType();
                    BigDecimal term=pd.getTerm();
                    BigDecimal base_price= pd.getBasePrice();
                    String meter_type=pd.getMeterType();
                    BigDecimal overage_rate=pd.getOverageRate();
                    BigDecimal copy_inclusion=pd.getCopyInclusion();
                    BigDecimal multiplier=pd.getMultiplier();
                    String base_bill_cycle=pd.getBaseBillCycle();
                    String overage_bill_cycle=pd.getOverageBillCycle();
					BigDecimal monthly_additional_comp=pd.getMonthlyAddComp();    //Added Raghavendra Uppala ITG690711  
					BigDecimal monthly_admin_comp=pd.getMonthlyAdminComp();       //Added Raghavendra Uppala ITG690711
                    String decline_maint=pd.getDeclineMaint();
					String config_bundle=pd.getConfigBundle();  //ITG#728624
					String strChecked = (decline_maint.equalsIgnoreCase("Y"))?" checked='checked' ":"";
                    if(!sameModel){
                        if(j>0){
            %>
            <tr><td colspan="6"></td></tr>
            <%          } %>
            <tr >
				<td align="center" style="" width="10px"><input type="checkbox" "<%=strChecked%>" onclick="setDeclineMaint(this,'<%=p_itt_number%>','<%=model%>');"; /></td>
                <td align="center" class="itt_model_qty" ><%=CanonE580ITTWorkbenchUtil.nonNullify(model) %> (<%=qty%>) 
                    <input type="hidden" name="model" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> value="<%=CanonE580ITTWorkbenchUtil.nonNullify(model)%>">
                </td>
                <td align="center">
                     <select name="contract_type" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> >
                        <option value=""></option>
                        <% for (int i = 0; i < contractTypeList.size(); i++) {
                                String contract_type_lov = (String) contractTypeList.get(i);
                        %>
                        <option value="<%=contract_type_lov%>"  <%=contract_type_lov.equalsIgnoreCase(contract_type) ? "SELECTED" : ""%> > <%=contract_type_lov%> </option>
                        <%}%>
                     </select>									
                </td>
                <td align="center">
                     <select name="plan_type" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> >
                        <option value=""></option>
                        <% for (int i = 0; i < planTypeList.size(); i++) {
                                String plan_type_lov = (String) planTypeList.get(i);
                        %>
                        <option value="<%=plan_type_lov%>"  <%=plan_type_lov.equalsIgnoreCase(plan_type) ? "SELECTED" : ""%> > <%=plan_type_lov%> </option>
                        <%}%>
                     </select>									
                </td>
                <td align="center">
                    <div>
                        <input name="term"  class="term" type="text" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> value ="<%=term==null?"":term.toString()%>" style="width:100%">
                    </div>    
                </td>
                <td align="center">
                    <div>
                        <input name="base_price"  class="currency" type="text" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> value ="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal(base_price)%>"  style="width:100%">
                    </div>    
                </td>
                <td align="center">
                     <select name="base_bill_cycle" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> >
                        <option value=""></option>
                        <% for (int i = 0; i < billCycleList.size(); i++) {
                                String bill_cycle_lov = (String) billCycleList.get(i);
                        %>
                        <option value="<%=bill_cycle_lov%>"  <%=bill_cycle_lov.equalsIgnoreCase(base_bill_cycle) ? "SELECTED" : ""%> > <%=bill_cycle_lov%> </option>
                        <%}%>
                     </select>
                </td>
                <td align="center">
                     <select name="overage_bill_cycle" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%>>
                        <option value=""></option>
                        <% for (int i = 0; i < billCycleList.size(); i++) {
                                String bill_cycle_lov = (String) billCycleList.get(i);
                        %>
                        <option value="<%=bill_cycle_lov%>"  <%=bill_cycle_lov.equalsIgnoreCase(overage_bill_cycle) ? "SELECTED" : ""%> > <%=bill_cycle_lov%> </option>
                        <%}%>
                     </select>									
                </td>
				<td align="center" class="itt_config_bundle" ><%=CanonE580ITTWorkbenchUtil.nonNullify(config_bundle) %>
                    <input type="hidden" name="config_bundle" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(config_bundle)%>">
                </td>
            </tr>
            <tr id="itt_tbl_first">
                <td align="center"></td>
                <th align="center">METER TYPE</td>
                <th align="center">Overage Rate</td>
                <th align="center">Copy Inclusion</td>
                <th align="center">Multiplier</td>
                <th align="center"></td>
                <th align="center"></td>
            </tr>
            <% } %>
            <tr >
                <td align="center" class="bgwhite"></td>
                <td align="center">
                    <%=CanonE580ITTWorkbenchUtil.nonNullify(meter_type)%>
                    <input type="hidden" name="meter_type" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(meter_type)%>">
                    <% if(sameModel){ %>
                    <input type="hidden" name="model" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(model)%>">
                    <input type="hidden" name="contract_type" value="">
                    <input type="hidden" name="plan_type" value="">
                    <input type="hidden" name="term" value="">
                    <input type="hidden" name="base_price" value="">
                    <input type="hidden" name="base_bill_cycle" value="">
                    <input type="hidden" name="overage_bill_cycle" value="">
                    <% } %>
                </td>
                <td align="center">
                    <div>
                        <input name="overage_rate"  class="rate" type="text" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> value ="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal3(overage_rate)%>" style="width:100%">
                    </div>    
                </td>
                <td align="center">
                    <div>
                       <input type="text" name="copy_inclusion" class="autoInteger" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%>  value ="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal2(copy_inclusion)%>" style="width:100%">
                    </div>    
                </td>
                <td align="center">
                    <div>
                        <input type="text" name="multiplier" class="autoMultiplier"  <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> value ="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal2(multiplier)%>" style="width:100%">
                    </div>    
                </td>
                <td align="center"></td>
                <td align="center"></td>
            </tr>
         <!--Added Raghavendra Uppala ITG690711 Start--> 
         <%if(GSDeditMode){%>	
			<tr class="itt_tbl_first"> 	
			            <td align="center"></td>
                        <td class="hd" align="center">MONTHLYRECURRING</td>
                        <td class="hd" align="center"></td>
                    </tr>                    
                    <tr class="oddtableDataCell">
					  <td align="center" class="bgwhite"></td>
                      <td >Monthly additional Comp</td>
					  <td>
                        <div>
                           <input type="text" name="monthly_additional_comp" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> value ="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal2(monthly_additional_comp)%>" id="monthly_additional_comp" style="width:100%">
                        </div>    
                      </td>
                    </tr>   
                     <tr class="oddtableDataCell">
					  <td align="center" class="bgwhite"></td>
                      <td >Monthly Admin Comp</td>
					  <td>
                        <div>
                           <input type="text" name="monthly_admin_comp" <%if("Y".equalsIgnoreCase(decline_maint)){ %> disabled <% }%> value ="<%=CanonE580ITTWorkbenchUtil.formatBigDecimal2(monthly_admin_comp)%>" id="monthly_admin_comp" style="width:100%">
                        </div>    
                      </td>
                    </tr>  
		 <% } else{ %>	
		     <input type='hidden' name='monthly_additional_comp' id='monthly_additional_comp' value="NULL">
			 <input type='hidden' name='monthly_admin_comp' id='monthly_admin_comp' value="NULL">
         <% } %>
		 <!--Added Raghavendra Uppala ITG690711 End--> 
            <% 
                lastModel=model;
             }
            %>
        </table>
        </div>
        <% } %>
          
        <div style ="margin-top: 10px;text-align: center;">
            <button class="btn save_button edit_mode" type="button" >
                <span class="ui-button-text">Save</span>
            </button>
            <button class="btn export-excel-button edit_mode" type="button"  style="margin-left: 20px">
                <span class="ui-button-text">Export Excel</span>
            </button>
        </div>	
        
      </div>
    
    <input type="hidden" name="notes_page_no" id="notes_page_no" value="<%=notes_page_no%>"/>
    <input type='hidden' name='notes_order_by' id='notes_order_by' value="<%=CanonE580ITTWorkbenchUtil.nonNullify(notes_order_by)%>">
    <input type='hidden' name='notes_asc_desc' id='notes_asc_desc' value="<%=CanonE580ITTWorkbenchUtil.nonNullify(notes__asc_desc)%>">
        
      <div id="tabs-2">

            <% if (notesList.size() > 0) {
                    int totalPages = (notes_total_record - 1) / NOTES_ROWS_PAGE + 1;
                    int currentPage = (notes_from_record.intValue() - 1) / NOTES_ROWS_PAGE + 1;
            %>
            <div style="float:left;">
                <div  style="float:left;">
                    <%=CanonE580ITTWorkbenchUtil.genPaginationSummary(notes_total_record, NOTES_ROWS_PAGE, currentPage)%>
                </div>
                <div style="float:right;">
                    <%=CanonE580ITTWorkbenchUtil.genPageLinks(null, null, null, totalPages, NOTES_ROWS_PAGE, currentPage, notes_total_record)%>
                </div>
            </div>
            
            
            <div class="inner_table" style="width: 100%;">
                <table border="0" width="100%" cellspacing="1" cellpadding="2" id="itt_notes_table" class="supplies-table" style="margin-left:-10px;">
                    <tr id="itt_tbl_first"> 	
                        <th width="10%" align="center">ID</th>
                        <th width="20%" align="center">Date Created</th>
                        <th width="10%" align="center">Created By</th>
						 <%if(GSDeditMode){%>
						<th width="10%" align="center">Add To Install Report</th>
						 <% } else { %>	
                        <th width="10%" align="center" nowrap>Add To PO PDF</th>
						<% } %>	
                        <th width="50%" align="center" >Comments</th>
                    </tr>
                    <% for(int i=0;i<notesList.size();i++){
                            CanonE580ITTWorkbenchDAO.NotesDetail notesDetail=(CanonE580ITTWorkbenchDAO.NotesDetail)notesList.get(i);
                    %>
                    <tr class="<%=i%2==0? "evenRow":"oddRow"%>">
                      <td align="center" style="text-align:center"><%=notesDetail.getSeqNumber()%>
                        <input type="hidden" name="notes_seq_number" value="<%=notesDetail.getSeqNumber()%>">
                      </td>
                      <td align="center" style="text-align:center"><%=CanonE580ITTWorkbenchUtil.formatDate2(notesDetail.getCreationDate()) %></td>
                      <td align="center" style="text-align:center"><%=CanonE580ITTWorkbenchUtil.nonNullify(notesDetail.getCreatedByName()) %></td>
                      <td align="center" style="text-align:center">
                          <input type="hidden" name="notes_add_to_popdf" value="<%=CanonE580ITTWorkbenchUtil.nonNullify(notesDetail.getAddToPoPdf()) %>">
                          <input type="checkbox" class="add_to_popdf_helper" name="chk<%=i%>" value="Y" <%="Y".equals(notesDetail.getAddToPoPdf())?"checked":""%> >
                      </td>
                      <td style="text-align: left;"><pre><%=CanonE580ITTWorkbenchUtil.nonNullify(notesDetail.getNotes())%></pre></td>
                    </tr>
                    <% } %>
                </table>
            </div>
            <% } %>
                
            <div style ="margin-top: 10px;text-align:center;">
                <button class="btn edit_mode" type="button" id="add-new-note-button">
                    <span class="ui-button-text">ADD NEW NOTE</span>
                </button>
                <button class="btn save_button edit_mode" type="button"  style="margin-left: 20px;">
                    <span class="ui-button-text">Save</span>
                </button>
                <button class="btn export-excel-button edit_mode" type="button"  style="margin-left: 20px">
                    <span class="ui-button-text">Export Excel</span>
                </button>
            </div>	
                
      </div>
                
      <div id="tabs-3">

            <div id="timestamp-contain">
                <table border="0"  cellspacing="1" cellpadding="2" id="itt_timestamp_table" class="supplies-table" style="margin-left:-10px;width:50%;">
                    <tr> 	
                        <th>Fields</th>
                        <th>Timestamp</th>
                    </tr>
                    <tr class="evenRow">
                      <td style="padding-right:75px;">Order Creation Date</td>
                      <td><%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getOrderCreationDate())%></td>
                    </tr>
                    <tr class="oddRow">
                      <td style="padding-right:75px;">Order Booked Date</td>
                      <td id="booked_date"><%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getBookedDate())%></td>
                    </tr>
                    <tr class="evenRow">
                      <td style="padding-right:65px;">PO PDF Creation Date</td>
                      <td><%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getPdfCreationDate())%></td>
                    </tr>
                    <tr class="oddRow">
                      <td style="padding-right:50px;">PO PDF Last update Date</td>
                      <td><%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getPdfLastUpdateDate())%></td>
                    </tr>
                    <tr class="evenRow">
                      <td style="padding-right:40px;">Date PO Accepted by Dealer</td>
                      <td>
                           <input type="text" name="po_accepted_date_by_dlr"  id="po_accepted_date_by_dlr" class="date-picker" value ="<%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getPoAcceptedDateByDlr())%>" style="width:100%;text-align:center;">
                      </td>
                    </tr>
                    <tr class="oddRow">
                      <td style="padding-right:95px;">PO Creation Date</td>
                      <td id="po_date"><%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getPoDate())%></td>
                    </tr>
                    <tr class="evenRow">
                      <td style="padding-right:70px;">Date PO Sent to CUSA</td>
                      <td>
                           <input type="text" name="po_sent_date_to_cusa" id="po_sent_date_to_cusa" class="date-picker" value ="<%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getPoSentDateToCusa())%>" style="width:100%;text-align:center;">
                      </td>
                    </tr>
                    <tr class="oddRow">
                      <td style="padding-right:20px;">Date CNA PO Approved by Dealer</td>
                      <td>
                           <input type="text" name="date_cna_po_approved_by_dealer" id="date_cna_po_approved_by_dealer" class="date-picker" value ="<%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getCnaPoAprvdByDlrDate())%>" style="width:100%;text-align:center;">
                      </td>
                    </tr>
                    <tr class="evenRow">
                      <td>Date Equipment Shipped from CUSA</td>
                      <td>
                           <input type="text" name="shipped_date_from_cusa" id="shipped_date_from_cusa" class="date-picker" value ="<%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getShippedDateFromCusa())%>" style="width:100%;text-align:center;">
                      </td>
                    </tr>
                    <tr class="oddRow">
                      <td style="padding-right:20px;">Date Equipment Arrives at Dealer</td>
                      <td>
                           <input type="text" name="equip_arrive_at_dlr_date" id="equip_arrive_at_dlr_date" class="date-picker" value ="<%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getEquipArriveAtDlrDate())%>" style="width:100%;text-align:center;">
                      </td>
                    </tr>
                    <tr class="evenRow">
                      <td style="padding-right:20px;">Date POD Received from Dealer</td>
                      <td>
                           <input type="text" name="pod_rcvd_from_dlr_date" id="pod_rcvd_from_dlr_date" class="date-picker" value ="<%=timestamp==null? "": CanonE580ITTWorkbenchUtil.formatDate4(timestamp.getPodRcvdFromDlrDate())%>" style="width:100%;text-align:center;">
                      </td>
                    </tr>
                </table>

                <div style ="margin-top: 10px; text-align: left;margin-left:200px;">
                    <button class="btn save_button edit_mode" type="button" >
                        <span class="ui-button-text">Save</span>
                    </button>
                    <button class="btn export-excel-button edit_mode" type="button"  style="margin-left: 20px">
                        <span class="ui-button-text">Export Excel</span>
                    </button>
                </div>
                        
            </div>
                        
                
      </div>
       <div id="tabs-5" title="PO Receiving"></div>
		<div id="tabs-6" title="PO Expense Receiving"></div>
      
                        
    </div>
                
    <div id="add-notes-dialog" title="Add New Note" style="width:300px;height: 200px;" >
        <div>Date Created: <%=CanonE580ITTWorkbenchUtil.formatDate2(new Date()) %></div>
        <div>Created By: <%=p_user_name%> <%=fullName!=null?"("+fullName+")" :"" %></div>
        <br/>
        <div>
            <textarea name="comments" id="comments"  style="width:380px;height: 130px" ></textarea>
			<%if(GSDeditMode){%>
			  <input type="checkbox" name="add_to_popdf" id="add_to_popdf" value="Y" >Add To Install Report
			<% } else { %>	
            <input type="checkbox" name="add_to_popdf" id="add_to_popdf" value="Y" >Add to PO PDF
			<% } %>	
        </div>
    </div>
        
</form>
<%
}
}
%>