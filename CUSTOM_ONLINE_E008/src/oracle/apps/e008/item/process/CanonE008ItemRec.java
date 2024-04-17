package oracle.apps.e008.item.process;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Timestamp;

import oracle.apps.e008.item.process.CanonE008ItemProcessDAO.RowMapper;
//import oracle.apps.e008.item.process.CanonE008ItemProcessDAO.projectLineHeaderInfo;

public class CanonE008ItemRec implements java.sql.SQLData, java.io.Serializable  {
	  
	  private String eztableid;
	  private String companycode;
	  private BigDecimal projectid;
	  private BigDecimal templateid;
	  private String templatename;
	  private BigDecimal projitemid; 
	  private String accountingrules;
	  private String accrual;    
	  private String assembledcountry;
	  private String assetrecoverycost;
	  private String assurancepointmin;
	  private String assurancepointsmax;  
	  private String assurancepointsperlicense; 
	  private String assuranceptsfixedperordqty;
	  private String bundledmaintenanceitem;
	  private String commissionsgroup; 
	  private String costofgoods; 
	  private String costed;
	  private String coveragebasetype;
	  private String coveragetype;     
	  private String criticality;  
	  private String customerordereable;  
	  private String defaultsourcesubwarehouse;
	  private String defaultsourcewarehouse;
	  private String itemdepth;
	  private String elancontrolled;
	  private String expense;
	  private String extendedmaintpopavailable;
	  private String fixednoofseats;
	  private String height;
	  private String imagewareremoteitem;
	  private String imagewareremoteenabled;    
	  private String imagewareremotemodel;
	  private String installbasecontrolled;
	  private String inventorytrackable;
	  private String invoiceable;
	  private String invoicingrules;
	  private String itemclassfication;
	  private String itemdescription;
	  private String itemnumber;
	  private String itemtype;
	  private String itemlength;
	  private String licensecontrol;
	  private String longdescription;
	  private String mainengine;
	  private String maintenanceitemterm;
	  private String maintenanceitemtype;
	  private String maintenancepopavaliable;
	  private String manufacturedcountry;
	  private String manufacturer;
	  private String manufactureritemnumber;
	  private String marketingmodel;
	  private String marketingsegment;
	  private String materialgroup1;
	  private String materialgroup2;
	  private String materialgroup3;
	  private String maximumorderquantity;
	  private String merchandisetype;
	  private String meteredmachine;
	  private String minimumorderquantity;
	  private String nfmcclass;
	  private String orderincrements;
	  private String owningdivision;
	  private String parttype;
	  private String partyielddays;
	  private String partyieldoutputs;
	  private String partsdropshipdisabled;
	  private String partssurveyrequired;
	  private String planninggroup;
	  private String privatelabelflag;
	  private String productcode;
	  private String productlevel1;
	  private String productlevel2;
	  private String productlevel3;
	  private String productlevel4;
	  private String productlevel5;
	  private String remanavailable;
	  private String remanufactureditemexists;
	  private String returncontroltype;
	  private String returncontrolled;
	  private String returnsubwarehouse;
	  private String revenue;
	  private String rmaallowed;
	  private String rmainspectionrequired;
	  private String safetyhazardousclass;
	  private String safetyhazardousid;
	  private String safetyhazardousmaterial;
	  private String safetyhazardousnumber;
	  private String safetyhazardousshiplabel;
	  private String seatsfrom;
	  private String seatsto;
	  private String serialcontrol;
	  private String serialfrom;
	  private String serialto;
	  private String serviceallocationtype;  
	  private String servicecallenabled;
	  private String servicemodel;
	  private String softwarecategory;
	  private String softwaremaintenancecontrol;
	  private String softwareproductcategory;
	  private String softwareversion;
	  private String sowrequired;
	  private String standardcost;
	  private String supplycolor;
	  private String supplyoemcode;
	  private String supplyoemmanufactuer;
	  private String supplytype;
	  private String supplyyield;
	  private String supplyyieldtype;
	  private String supplyyielduom;
	  private String taxcode;
	  private String tcoption;
	  private String tcoptionvalue;
	  private String thirdparty;
	  private String uom;
	  private String uomclass;
	  private String upccode;
	  private String warranty;
	  private String warrantyperiod;
	  private String weight;
	  private String tariffcode;
	  private String freightclasscode;
	  private String returnwarehouse;
	  private String intangiblemdsetype;
	  private String purchasePrice;
	  private String supplier;
	  private String supplierItem;
	  private String supplierSite;
	  private String supersededBy;
	  private String areaofpaper;
	  private String itembillingtype;
	  private String configurationflag;
	  private String returnvendor;
	  private String returnvendorsite;
	  private String hardallocationtype;
	  private String defaultsourcetype;
	  private String easypacki;
	  private String approvalstatus;
	  private String processflag;
	  private String processmessage;
	  private String createdby;
	  private Timestamp creationdate;
	  private String lastupdateby;
	  private Timestamp lastupdatedate;
	  private String mercuryinclude;
	  private String msrp;
	  private String unboxedweight;
	  private String unboxedweightuom;
	  private String unboxedlength;
	  private String unboxedlengthuom;
	  private String unboxedwidth;
	  private String unboxedwidthuom;
	  private String unboxedheight;
	  private String unboxedheightuom;
	  private String leadTime;
	  private String internalItem;  
  
  public CanonE008ItemRec(){
  }
  
  public CanonE008ItemRec(String eztableid,
		  String companycode,
		  BigDecimal projectid,
		  BigDecimal templateid,
		  String templatename,
		  BigDecimal projitemid,
		  String accountingrules,
		  String accrual,
		  String assembledcountry,
		  String assetrecoverycost,
		  String assurancepointmin,
		  String assurancepointsmax,
		  String assurancepointsperlicense,
		  String assuranceptsfixedperordqty,
		  String bundledmaintenanceitem,
		  String commissionsgroup,
		  String costofgoods,
		  String costed,
		  String coveragebasetype,
		  String coveragetype,
		  String criticality,
		  String customerordereable,
		  String defaultsourcesubwarehouse,
		  String defaultsourcewarehouse,
		  String itemdepth,
		  String elancontrolled,
		  String expense,
		  String extendedmaintpopavailable,
		  String fixednoofseats,
		  String height,
		  String imagewareremoteitem,
		  String imagewareremoteenabled,
		  String imagewareremotemodel,
		  String installbasecontrolled,
		  String inventorytrackable,
		  String invoiceable,
		  String invoicingrules,
		  String itemclassfication,
		  String itemdescription,
		  String itemnumber,
		  String itemtype,
		  String itemlength,
		  String licensecontrol,
		  String longdescription,
		  String mainengine,
		  String maintenanceitemterm,
		  String maintenanceitemtype,
		  String maintenancepopavaliable,
		  String manufacturedcountry,
		  String manufacturer,
		  String manufactureritemnumber,
		  String marketingmodel,
		  String marketingsegment,
		  String materialgroup1,
		  String materialgroup2,
		  String materialgroup3,
		  String maximumorderquantity,
		  String merchandisetype,
		  String meteredmachine,
		  String minimumorderquantity,
		  String nfmcclass,
		  String orderincrements,
		  String owningdivision,
		  String parttype,
		  String partyielddays,
		  String partyieldoutputs,
		  String partsdropshipdisabled,
		  String partssurveyrequired,
		  String planninggroup,
		  String privatelabelflag,
		  String productcode,
		  String productlevel1,
		  String productlevel2,
		  String productlevel3,
		  String productlevel4,
		  String productlevel5,
		  String remanavailable,
		  String remanufactureditemexists,
		  String returncontroltype,
		  String returncontrolled,
		  String returnsubwarehouse,
		  String revenue,
		  String rmaallowed,
		  String rmainspectionrequired,
		  String safetyhazardousclass,
		  String safetyhazardousid,
		  String safetyhazardousmaterial,
		  String safetyhazardousnumber,
		  String safetyhazardousshiplabel,
		  String seatsfrom,
		  String seatsto,
		  String serialcontrol,
		  String serialfrom,
		  String serialto,
		  String serviceallocationtype,
		  String servicecallenabled,
		  String servicemodel,
		  String softwarecategory,
		  String softwaremaintenancecontrol,
		  String softwareproductcategory,
		  String softwareversion,
		  String sowrequired,
		  String standardcost,
		  String supplycolor,
		  String supplyoemcode,
		  String supplyoemmanufactuer,
		  String supplytype,
		  String supplyyield,
		  String supplyyieldtype,
		  String supplyyielduom,
		  String taxcode,
		  String tcoption,
		  String tcoptionvalue,
		  String thirdparty,
		  String uom,
		  String uomclass,
		  String upccode,
		  String warranty,
		  String warrantyperiod,
		  String weight,
		  String tariffcode,
		  String freightclasscode,
		  String returnwarehouse,
		  String intangiblemdsetype,
		  String purchasePrice,
		  String supplier,
		  String supplierItem,
		  String supplierSite,
		  String supersededBy,
		  String areaofpaper,
		  String itembillingtype,
		  String configurationflag,
		  String returnvendor,
		  String returnvendorsite,
		  String hardallocationtype,
		  String defaultsourcetype,
		  String easypacki,
		  String approvalstatus,
		  String processflag,
		  String processmessage,
		  String createdby,
		  Timestamp creationdate,
		  String lastupdateby,
		  Timestamp lastupdatedate,
		  String mercuryinclude,
		  String msrp,
		  String unboxedweight,
		  String unboxedweightuom,
		  String unboxedlength,
		  String unboxedlengthuom,
		  String unboxedwidth,
		  String unboxedwidthuom,
		  String unboxedheight,
		  String unboxedheightuom,
		  String leadTime,
		  String internalItem
		  ) {
		super();
		   this.eztableid=				          eztableid;
		   this.companycode=                      companycode;
		   this.projectid=                        projectid;
		   this.templateid=                       templateid;
		   this.templatename = 					  templatename;
		   this.projitemid=                       projitemid;
		   this.accountingrules=                  accountingrules;
		   this.accrual=                          accrual;
		   this.assembledcountry=                 assembledcountry;
		   this.assetrecoverycost=                assetrecoverycost;
		   this.assurancepointmin=                assurancepointmin;
		   this.assurancepointsmax=               assurancepointsmax;
		   this.assurancepointsperlicense=        assurancepointsperlicense;
		   this.assuranceptsfixedperordqty=       assuranceptsfixedperordqty;
		   this.bundledmaintenanceitem=           bundledmaintenanceitem;
		   this.commissionsgroup=                 commissionsgroup;
		   this.costofgoods=                      costofgoods;
		   this.costed=                           costed;
		   this.coveragebasetype=                 coveragebasetype;
		   this.coveragetype=                     coveragetype;
		   this.criticality=                      criticality;
		   this.customerordereable=               customerordereable;
		   this.defaultsourcesubwarehouse=        defaultsourcesubwarehouse;
		   this.defaultsourcewarehouse=           defaultsourcewarehouse;
		   this.itemdepth=                        itemdepth;
		   this.elancontrolled=                   elancontrolled;
		   this.expense=                          expense;
		   this.extendedmaintpopavailable=        extendedmaintpopavailable;
		   this.fixednoofseats=                   fixednoofseats;
		   this.height=                           height;
		   this.imagewareremoteitem=              imagewareremoteitem;
		   this.imagewareremoteenabled=           imagewareremoteenabled;
		   this.imagewareremotemodel=             imagewareremotemodel;
		   this.installbasecontrolled=            installbasecontrolled;
		   this.inventorytrackable=               inventorytrackable;
		   this.invoiceable=                      invoiceable;
		   this.invoicingrules=                   invoicingrules;
		   this.itemclassfication=                itemclassfication;
		   this.itemdescription=                  itemdescription;
		   this.itemnumber=                       itemnumber;
		   this.itemtype=                         itemtype;
		   this.itemlength=                       itemlength;
		   this.licensecontrol=                   licensecontrol;
		   this.longdescription=                  longdescription;
		   this.mainengine=                       mainengine;
		   this.maintenanceitemterm=              maintenanceitemterm;
		   this.maintenanceitemtype=              maintenanceitemtype;
		   this.maintenancepopavaliable=          maintenancepopavaliable;
		   this.manufacturedcountry=              manufacturedcountry;
		   this.manufacturer=                     manufacturer;
		   this.manufactureritemnumber=           manufactureritemnumber;
		   this.marketingmodel=                   marketingmodel;
		   this.marketingsegment=                 marketingsegment;
		   this.materialgroup1=                   materialgroup1;
		   this.materialgroup2=                   materialgroup2;
		   this.materialgroup3=                   materialgroup3;
		   this.maximumorderquantity=             maximumorderquantity;
		   this.merchandisetype=                  merchandisetype;
		   this.meteredmachine=                   meteredmachine;
		   this.minimumorderquantity=             minimumorderquantity;
		   this.nfmcclass=                        nfmcclass;
		   this.orderincrements=                  orderincrements;
		   this.owningdivision=                   owningdivision;
		   this.parttype=                         parttype;
		   this.partyielddays=                    partyielddays;
		   this.partyieldoutputs=                 partyieldoutputs;
		   this.partsdropshipdisabled=            partsdropshipdisabled;
		   this.partssurveyrequired=              partssurveyrequired;
		   this.planninggroup=                    planninggroup;
		   this.privatelabelflag=                 privatelabelflag;
		   this.productcode=                      productcode;
		   this.productlevel1=                    productlevel1;
		   this.productlevel2=                    productlevel2;
		   this.productlevel3=                    productlevel3;
		   this.productlevel4=                    productlevel4;
		   this.productlevel5=                    productlevel5;
		   this.remanavailable=                   remanavailable;
		   this.remanufactureditemexists=         remanufactureditemexists;
		   this.returncontroltype=                returncontroltype;
		   this.returncontrolled=                 returncontrolled;
		   this.returnsubwarehouse=               returnsubwarehouse;
		   this.revenue=                          revenue;
		   this.rmaallowed=                       rmaallowed;
		   this.rmainspectionrequired=            rmainspectionrequired;
		   this.safetyhazardousclass=             safetyhazardousclass;
		   this.safetyhazardousid=                safetyhazardousid;
		   this.safetyhazardousmaterial=          safetyhazardousmaterial;
		   this.safetyhazardousnumber=            safetyhazardousnumber;
		   this.safetyhazardousshiplabel=         safetyhazardousshiplabel;
		   this.seatsfrom=                        seatsfrom;
		   this.seatsto=                          seatsto;
		   this.serialcontrol=                    serialcontrol;
		   this.serialfrom=                       serialfrom;
		   this.serialto=                         serialto;
		   this.serviceallocationtype=            serviceallocationtype;
		   this.servicecallenabled=               servicecallenabled;
		   this.servicemodel=                     servicemodel;
		   this.softwarecategory=                 softwarecategory;
		   this.softwaremaintenancecontrol=       softwaremaintenancecontrol;
		   this.softwareproductcategory=          softwareproductcategory;
		   this.softwareversion=                  softwareversion;
		   this.sowrequired=                      sowrequired;
		   this.standardcost=                     standardcost;
		   this.supplycolor=                      supplycolor;
		   this.supplyoemcode=                    supplyoemcode;
		   this.supplyoemmanufactuer=             supplyoemmanufactuer;
		   this.supplytype=                       supplytype;
		   this.supplyyield=                      supplyyield;
		   this.supplyyieldtype=                  supplyyieldtype;
		   this.supplyyielduom=                   supplyyielduom;
		   this.taxcode=                          taxcode;
		   this.tcoption=                         tcoption;
		   this.tcoptionvalue=                    tcoptionvalue;
		   this.thirdparty=                       thirdparty;
		   this.uom=                              uom;
		   this.uomclass= 					      uomclass;		
		   this.upccode=                          upccode;
		   this.warranty=                         warranty;
		   this.warrantyperiod=                   warrantyperiod;
		   this.weight=                           weight;
		   this.tariffcode=                       tariffcode;
		   this.freightclasscode=                 freightclasscode;
		   this.returnwarehouse=                  returnwarehouse;
		   this.intangiblemdsetype=               intangiblemdsetype;
		   this.purchasePrice=             		  purchasePrice;
		   this.supplier=             		  	  supplier;
		   this.supplierItem=             		  supplierItem;
		   this.supplierSite=             		  supplierSite;
		   this.supersededBy=             		  supersededBy;
		   this.areaofpaper=             		  areaofpaper;
		   this.itembillingtype=                  itembillingtype;
		   this.configurationflag=                configurationflag;
		   this.returnvendor=                     returnvendor;
		   this.returnvendorsite=                 returnvendorsite;
		   this.hardallocationtype=               hardallocationtype;
		   this.defaultsourcetype=                defaultsourcetype;
		   this.easypacki=                        easypacki;
		   this.approvalstatus=                   approvalstatus;
		   this.processflag=                      processflag;
		   this.processmessage=                   processmessage;
		   this.createdby=                        createdby;
		   this.creationdate=                     creationdate;
		   this.lastupdateby=                     lastupdateby;
		   this.lastupdatedate=                   lastupdatedate;
		   this.mercuryinclude=                   mercuryinclude;
		   this.msrp=                    		  msrp;
		   this.unboxedweight=                    unboxedweight;
		   this.unboxedweightuom=                 unboxedweightuom;
		   this.unboxedlength=                    unboxedlength;
		   this.unboxedlengthuom=                 unboxedlengthuom;
		   this.unboxedwidth=                     unboxedwidth;
		   this.unboxedwidthuom=                  unboxedwidthuom;
		   this.unboxedheight=                    unboxedheight;
		   this.unboxedheightuom=                 unboxedheightuom;
		   this.leadTime=                         leadTime;
		   this.internalItem=					  internalItem;
	}


  public String getInternalItem() {
		return internalItem;
	}

	public void setInternalItem(String internalItem) {
		this.internalItem = internalItem;
	}



public String getLeadTime() {
	return leadTime;
}

public void setLeadTime(String leadTime) {
	this.leadTime = leadTime;
}

public String getUom() {
	return uom;
}

public void setUom(String uom) {
	this.uom = uom;
}

public String getUomclass() {
	return uomclass;
}

public void setUomclass(String uomclass) {
	this.uomclass = uomclass;
}

public String getEztableid() {
	return eztableid;
}

public void setEztableid(String eztableid) {
	this.eztableid = eztableid;
}

public String getCompanycode() {
	return companycode;
}

public void setCompanycode(String companycode) {
	this.companycode = companycode;
}

public BigDecimal getProjectid() {
	return projectid;
}

public void setProjectid(BigDecimal projectid) {
	this.projectid = projectid;
}

public BigDecimal getTemplateid() {
	return templateid;
}

public void setTemplateid(BigDecimal templateid) {
	this.templateid = templateid;
}

public String getTemplatename() {
return templatename;
}

public void setTemplatename(String templatename) {
this.templatename = templatename;
}

public BigDecimal getProjitemid() {
	return projitemid;
}

public void setProjitemid(BigDecimal projitemid) {
	this.projitemid = projitemid;
}

public String getAccountingrules() {
	return accountingrules;
}

public void setAccountingrules(String accountingrules) {
	this.accountingrules = accountingrules;
}

public String getAccrual() {
	return accrual;
}

public void setAccrual(String accrual) {
	this.accrual = accrual;
}

public String getAssembledcountry() {
	return assembledcountry;
}

public void setAssembledcountry(String assembledcountry) {
	this.assembledcountry = assembledcountry;
}

public String getAssetrecoverycost() {
	return assetrecoverycost;
}

public void setAssetrecoverycost(String assetrecoverycost) {
	this.assetrecoverycost = assetrecoverycost;
}

public String getAssurancepointmin() {
	return assurancepointmin;
}

public void setAssurancepointmin(String assurancepointmin) {
	this.assurancepointmin = assurancepointmin;
}

public String getAssurancepointsmax() {
	return assurancepointsmax;
}

public void setAssurancepointsmax(String assurancepointsmax) {
	this.assurancepointsmax = assurancepointsmax;
}

public String getAssurancepointsperlicense() {
	return assurancepointsperlicense;
}

public void setAssurancepointsperlicense(String assurancepointsperlicense) {
	this.assurancepointsperlicense = assurancepointsperlicense;
}

public String getAssuranceptsfixedperordqty() {
	return assuranceptsfixedperordqty;
}

public void setAssuranceptsfixedperordqty(String assuranceptsfixedperordqty) {
	this.assuranceptsfixedperordqty = assuranceptsfixedperordqty;
}

public String getBundledmaintenanceitem() {
	return bundledmaintenanceitem;
}

public void setBundledmaintenanceitem(String bundledmaintenanceitem) {
	this.bundledmaintenanceitem = bundledmaintenanceitem;
}

public String getCommissionsgroup() {
	return commissionsgroup;
}

public void setCommissionsgroup(String commissionsgroup) {
	this.commissionsgroup = commissionsgroup;
}

public String getCostofgoods() {
	return costofgoods;
}

public void setCostofgoods(String costofgoods) {
	this.costofgoods = costofgoods;
}

public String getCosted() {
	return costed;
}

public void setCosted(String costed) {
	this.costed = costed;
}

public String getCoveragebasetype() {
	return coveragebasetype;
}

public void setCoveragebasetype(String coveragebasetype) {
	this.coveragebasetype = coveragebasetype;
}

public String getCoveragetype() {
	return coveragetype;
}

public void setCoveragetype(String coveragetype) {
	this.coveragetype = coveragetype;
}

public String getCriticality() {
	return criticality;
}

public void setCriticality(String criticality) {
	this.criticality = criticality;
}

public String getCustomerordereable() {
	return customerordereable;
}

public void setCustomerordereable(String customerordereable) {
	this.customerordereable = customerordereable;
}

public String getDefaultsourcesubwarehouse() {
	return defaultsourcesubwarehouse;
}

public void setDefaultsourcesubwarehouse(String defaultsourcesubwarehouse) {
	this.defaultsourcesubwarehouse = defaultsourcesubwarehouse;
}

public String getDefaultsourcewarehouse() {
	return defaultsourcewarehouse;
}

public void setDefaultsourcewarehouse(String defaultsourcewarehouse) {
	this.defaultsourcewarehouse = defaultsourcewarehouse;
}

public String getItemdepth() {
	return itemdepth;
}

public void setItemdepth(String itemdepth) {
	this.itemdepth = itemdepth;
}

public String getElancontrolled() {
	return elancontrolled;
}

public void setElancontrolled(String elancontrolled) {
	this.elancontrolled = elancontrolled;
}

public String getExpense() {
	return expense;
}

public void setExpense(String expense) {
	this.expense = expense;
}

public String getExtendedmaintpopavailable() {
	return extendedmaintpopavailable;
}

public void setExtendedmaintpopavailable(String extendedmaintpopavailable) {
	this.extendedmaintpopavailable = extendedmaintpopavailable;
}

public String getFixednoofseats() {
	return fixednoofseats;
}

public void setFixednoofseats(String fixednoofseats) {
	this.fixednoofseats = fixednoofseats;
}

public String getHeight() {
	return height;
}

public void setHeight(String height) {
	this.height = height;
}

public String getImagewareremoteitem() {
	return imagewareremoteitem;
}

public void setImagewareremoteitem(String imagewareremoteitem) {
	this.imagewareremoteitem = imagewareremoteitem;
}

public String getImagewareremoteenabled() {
	return imagewareremoteenabled;
}

public void setImagewareremoteenabled(String imagewareremoteenabled) {
	this.imagewareremoteenabled = imagewareremoteenabled;
}

public String getImagewareremotemodel() {
	return imagewareremotemodel;
}

public void setImagewareremotemodel(String imagewareremotemodel) {
	this.imagewareremotemodel = imagewareremotemodel;
}

public String getInstallbasecontrolled() {
	return installbasecontrolled;
}

public void setInstallbasecontrolled(String installbasecontrolled) {
	this.installbasecontrolled = installbasecontrolled;
}

public String getInventorytrackable() {
	return inventorytrackable;
}

public void setInventorytrackable(String inventorytrackable) {
	this.inventorytrackable = inventorytrackable;
}

public String getInvoiceable() {
	return invoiceable;
}

public void setInvoiceable(String invoiceable) {
	this.invoiceable = invoiceable;
}

public String getInvoicingrules() {
	return invoicingrules;
}

public void setInvoicingrules(String invoicingrules) {
	this.invoicingrules = invoicingrules;
}

public String getItemclassfication() {
	return itemclassfication;
}

public void setItemclassfication(String itemclassfication) {
	this.itemclassfication = itemclassfication;
}

public String getItemdescription() {
	return itemdescription;
}

public void setItemdescription(String itemdescription) {
	this.itemdescription = itemdescription;
}

public String getItemnumber() {
	return itemnumber;
}

public void setItemnumber(String itemnumber) {
	this.itemnumber = itemnumber;
}

public String getItemtype() {
	return itemtype;
}

public void setItemtype(String itemtype) {
	this.itemtype = itemtype;
}

public String getItemlength() {
	return itemlength;
}

public void setItemlength(String itemlength) {
	this.itemlength = itemlength;
}

public String getLicensecontrol() {
	return licensecontrol;
}

public void setLicensecontrol(String licensecontrol) {
	this.licensecontrol = licensecontrol;
}

public String getLongdescription() {
	return longdescription;
}

public void setLongdescription(String longdescription) {
	this.longdescription = longdescription;
}

public String getMainengine() {
	return mainengine;
}

public void setMainengine(String mainengine) {
	this.mainengine = mainengine;
}

public String getMaintenanceitemterm() {
	return maintenanceitemterm;
}

public void setMaintenanceitemterm(String maintenanceitemterm) {
	this.maintenanceitemterm = maintenanceitemterm;
}

public String getMaintenanceitemtype() {
	return maintenanceitemtype;
}

public void setMaintenanceitemtype(String maintenanceitemtype) {
	this.maintenanceitemtype = maintenanceitemtype;
}

public String getMaintenancepopavaliable() {
	return maintenancepopavaliable;
}

public void setMaintenancepopavaliable(String maintenancepopavaliable) {
	this.maintenancepopavaliable = maintenancepopavaliable;
}

public String getManufacturedcountry() {
	return manufacturedcountry;
}

public void setManufacturedcountry(String manufacturedcountry) {
	this.manufacturedcountry = manufacturedcountry;
}

public String getManufacturer() {
	return manufacturer;
}

public void setManufacturer(String manufacturer) {
	this.manufacturer = manufacturer;
}

public String getManufactureritemnumber() {
	return manufactureritemnumber;
}

public void setManufactureritemnumber(String manufactureritemnumber) {
	this.manufactureritemnumber = manufactureritemnumber;
}

public String getMarketingmodel() {
	return marketingmodel;
}

public void setMarketingmodel(String marketingmodel) {
	this.marketingmodel = marketingmodel;
}

public String getMarketingsegment() {
	return marketingsegment;
}

public void setMarketingsegment(String marketingsegment) {
	this.marketingsegment = marketingsegment;
}

public String getMaterialgroup1() {
	return materialgroup1;
}

public void setMaterialgroup1(String materialgroup1) {
	this.materialgroup1 = materialgroup1;
}

public String getMaterialgroup2() {
	return materialgroup2;
}

public void setMaterialgroup2(String materialgroup2) {
	this.materialgroup2 = materialgroup2;
}

public String getMaterialgroup3() {
	return materialgroup3;
}

public void setMaterialgroup3(String materialgroup3) {
	this.materialgroup3 = materialgroup3;
}

public String getMaximumorderquantity() {
	return maximumorderquantity;
}

public void setMaximumorderquantity(String maximumorderquantity) {
	this.maximumorderquantity = maximumorderquantity;
}

public String getMerchandisetype() {
	return merchandisetype;
}

public void setMerchandisetype(String merchandisetype) {
	this.merchandisetype = merchandisetype;
}

public String getMeteredmachine() {
	return meteredmachine;
}

public void setMeteredmachine(String meteredmachine) {
	this.meteredmachine = meteredmachine;
}

public String getMinimumorderquantity() {
	return minimumorderquantity;
}

public void setMinimumorderquantity(String minimumorderquantity) {
	this.minimumorderquantity = minimumorderquantity;
}

public String getNfmcclass() {
	return nfmcclass;
}

public void setNfmcclass(String nfmcclass) {
	this.nfmcclass = nfmcclass;
}

public String getOrderincrements() {
	return orderincrements;
}

public void setOrderincrements(String orderincrements) {
	this.orderincrements = orderincrements;
}

public String getOwningdivision() {
	return owningdivision;
}

public void setOwningdivision(String owningdivision) {
	this.owningdivision = owningdivision;
}

public String getParttype() {
	return parttype;
}

public void setParttype(String parttype) {
	this.parttype = parttype;
}

public String getPartyielddays() {
	return partyielddays;
}

public void setPartyielddays(String partyielddays) {
	this.partyielddays = partyielddays;
}

public String getPartyieldoutputs() {
	return partyieldoutputs;
}

public void setPartyieldoutputs(String partyieldoutputs) {
	this.partyieldoutputs = partyieldoutputs;
}

public String getPartsdropshipdisabled() {
	return partsdropshipdisabled;
}

public void setPartsdropshipdisabled(String partsdropshipdisabled) {
	this.partsdropshipdisabled = partsdropshipdisabled;
}

public String getPartssurveyrequired() {
	return partssurveyrequired;
}

public void setPartssurveyrequired(String partssurveyrequired) {
	this.partssurveyrequired = partssurveyrequired;
}

public String getPlanninggroup() {
	return planninggroup;
}

public void setPlanninggroup(String planninggroup) {
	this.planninggroup = planninggroup;
}

public String getPrivatelabelflag() {
	return privatelabelflag;
}

public void setPrivatelabelflag(String privatelabelflag) {
	this.privatelabelflag = privatelabelflag;
}

public String getProductcode() {
	return productcode;
}

public void setProductcode(String productcode) {
	this.productcode = productcode;
}

public String getProductlevel1() {
	return productlevel1;
}

public void setProductlevel1(String productlevel1) {
	this.productlevel1 = productlevel1;
}

public String getProductlevel2() {
	return productlevel2;
}

public void setProductlevel2(String productlevel2) {
	this.productlevel2 = productlevel2;
}

public String getProductlevel3() {
	return productlevel3;
}

public void setProductlevel3(String productlevel3) {
	this.productlevel3 = productlevel3;
}

public String getProductlevel4() {
	return productlevel4;
}

public void setProductlevel4(String productlevel4) {
	this.productlevel4 = productlevel4;
}

public String getProductlevel5() {
	return productlevel5;
}

public void setProductlevel5(String productlevel5) {
	this.productlevel5 = productlevel5;
}

public String getRemanavailable() {
	return remanavailable;
}

public void setRemanavailable(String remanavailable) {
	this.remanavailable = remanavailable;
}

public String getRemanufactureditemexists() {
	return remanufactureditemexists;
}

public void setRemanufactureditemexists(String remanufactureditemexists) {
	this.remanufactureditemexists = remanufactureditemexists;
}

public String getReturncontroltype() {
	return returncontroltype;
}

public void setReturncontroltype(String returncontroltype) {
	this.returncontroltype = returncontroltype;
}

public String getReturncontrolled() {
	return returncontrolled;
}

public void setReturncontrolled(String returncontrolled) {
	this.returncontrolled = returncontrolled;
}

public String getReturnsubwarehouse() {
	return returnsubwarehouse;
}

public void setReturnsubwarehouse(String returnsubwarehouse) {
	this.returnsubwarehouse = returnsubwarehouse;
}

public String getRevenue() {
	return revenue;
}

public void setRevenue(String revenue) {
	this.revenue = revenue;
}

public String getRmaallowed() {
	return rmaallowed;
}

public void setRmaallowed(String rmaallowed) {
	this.rmaallowed = rmaallowed;
}

public String getRmainspectionrequired() {
	return rmainspectionrequired;
}

public void setRmainspectionrequired(String rmainspectionrequired) {
	this.rmainspectionrequired = rmainspectionrequired;
}

public String getSafetyhazardousclass() {
	return safetyhazardousclass;
}

public void setSafetyhazardousclass(String safetyhazardousclass) {
	this.safetyhazardousclass = safetyhazardousclass;
}

public String getSafetyhazardousid() {
	return safetyhazardousid;
}

public void setSafetyhazardousid(String safetyhazardousid) {
	this.safetyhazardousid = safetyhazardousid;
}

public String getSafetyhazardousmaterial() {
	return safetyhazardousmaterial;
}

public void setSafetyhazardousmaterial(String safetyhazardousmaterial) {
	this.safetyhazardousmaterial = safetyhazardousmaterial;
}

public String getSafetyhazardousnumber() {
	return safetyhazardousnumber;
}

public void setSafetyhazardousnumber(String safetyhazardousnumber) {
	this.safetyhazardousnumber = safetyhazardousnumber;
}

public String getSafetyhazardousshiplabel() {
	return safetyhazardousshiplabel;
}

public void setSafetyhazardousshiplabel(String safetyhazardousshiplabel) {
	this.safetyhazardousshiplabel = safetyhazardousshiplabel;
}

public String getSeatsfrom() {
	return seatsfrom;
}

public void setSeatsfrom(String seatsfrom) {
	this.seatsfrom = seatsfrom;
}

public String getSeatsto() {
	return seatsto;
}

public void setSeatsto(String seatsto) {
	this.seatsto = seatsto;
}

public String getSerialcontrol() {
	return serialcontrol;
}

public void setSerialcontrol(String serialcontrol) {
	this.serialcontrol = serialcontrol;
}


public String getSerialfrom() {
	return serialfrom;
}

public void setSerialfrom(String serialfrom) {
	this.serialfrom = serialfrom;
}

public String getSerialto() {
	return serialto;
}

public void setSerialto(String serialto) {
	this.serialto = serialto;
}

public String getServiceallocationtype() {
	return serviceallocationtype;
}

public void setServiceallocationtype(String serviceallocationtype) {
	this.serviceallocationtype = serviceallocationtype;
}

public String getServicecallenabled() {
	return servicecallenabled;
}

public void setServicecallenabled(String servicecallenabled) {
	this.servicecallenabled = servicecallenabled;
}

public String getServicemodel() {
	return servicemodel;
}

public void setServicemodel(String servicemodel) {
	this.servicemodel = servicemodel;
}

public String getSoftwarecategory() {
	return softwarecategory;
}

public void setSoftwarecategory(String softwarecategory) {
	this.softwarecategory = softwarecategory;
}

public String getSoftwaremaintenancecontrol() {
	return softwaremaintenancecontrol;
}

public void setSoftwaremaintenancecontrol(String softwaremaintenancecontrol) {
	this.softwaremaintenancecontrol = softwaremaintenancecontrol;
}

public String getSoftwareproductcategory() {
	return softwareproductcategory;
}

public void setSoftwareproductcategory(String softwareproductcategory) {
	this.softwareproductcategory = softwareproductcategory;
}

public String getSoftwareversion() {
	return softwareversion;
}

public void setSoftwareversion(String softwareversion) {
	this.softwareversion = softwareversion;
}

public String getSowrequired() {
	return sowrequired;
}

public void setSowrequired(String sowrequired) {
	this.sowrequired = sowrequired;
}

public String getStandardcost() {
	return standardcost;
}

public void setStandardcost(String standardcost) {
	this.standardcost = standardcost;
}

public String getSupplycolor() {
	return supplycolor;
}

public void setSupplycolor(String supplycolor) {
	this.supplycolor = supplycolor;
}

public String getSupplyoemcode() {
	return supplyoemcode;
}

public void setSupplyoemcode(String supplyoemcode) {
	this.supplyoemcode = supplyoemcode;
}

public String getSupplyoemmanufactuer() {
	return supplyoemmanufactuer;
}

public void setSupplyoemmanufactuer(String supplyoemmanufactuer) {
	this.supplyoemmanufactuer = supplyoemmanufactuer;
}

public String getSupplytype() {
	return supplytype;
}

public void setSupplytype(String supplytype) {
	this.supplytype = supplytype;
}

public String getSupplyyield() {
	return supplyyield;
}

public void setSupplyyield(String supplyyield) {
	this.supplyyield = supplyyield;
}

public String getSupplyyieldtype() {
	return supplyyieldtype;
}

public void setSupplyyieldtype(String supplyyieldtype) {
	this.supplyyieldtype = supplyyieldtype;
}

public String getSupplyyielduom() {
	return supplyyielduom;
}

public void setSupplyyielduom(String supplyyielduom) {
	this.supplyyielduom = supplyyielduom;
}

public String getTaxcode() {
	return taxcode;
}

public void setTaxcode(String taxcode) {
	this.taxcode = taxcode;
}

public String getTcoption() {
	return tcoption;
}

public void setTcoption(String tcoption) {
	this.tcoption = tcoption;
}

public String getTcoptionvalue() {
	return tcoptionvalue;
}

public void setTcoptionvalue(String tcoptionvalue) {
	this.tcoptionvalue = tcoptionvalue;
}

public String getThirdparty() {
	return thirdparty;
}

public void setThirdparty(String thirdparty) {
	this.thirdparty = thirdparty;
}

public String getUpccode() {
	return upccode;
}

public void setUpccode(String upccode) {
	this.upccode = upccode;
}

public String getWarranty() {
	return warranty;
}

public void setWarranty(String warranty) {
	this.warranty = warranty;
}

public String getWarrantyperiod() {
	return warrantyperiod;
}

public void setWarrantyperiod(String warrantyperiod) {
	this.warrantyperiod = warrantyperiod;
}

public String getWeight() {
	return weight;
}

public void setWeight(String weight) {
	this.weight = weight;
}


public String getTariffcode() {
	return tariffcode;
}

public void setTariffcode(String tariffcode) {
	this.tariffcode = tariffcode;
}

public String getFreightclasscode() {
	return freightclasscode;
}

public void setFreightclasscode(String freightclasscode) {
	this.freightclasscode = freightclasscode;
}

public String getReturnwarehouse() {
	return returnwarehouse;
}

public void setReturnwarehouse(String returnwarehouse) {
	this.returnwarehouse = returnwarehouse;
}

public String getIntangiblemdsetype() {
	return intangiblemdsetype;
}

public void setIntangiblemdsetype(String intangiblemdsetype) {
	this.intangiblemdsetype = intangiblemdsetype;
}

public String getApprovalstatus() {
	return approvalstatus;
}

public void setApprovalstatus(String approvalstatus) {
	this.approvalstatus = approvalstatus;
}

public String getProcessflag() {
	return processflag;
}

public void setProcessflag(String processflag) {
	this.processflag = processflag;
}

public String getProcessmessage() {
	return processmessage;
}

public void setProcessmessage(String processmessage) {
	this.processmessage = processmessage;
}

public String getCreatedby() {
	return createdby;
}

public void setCreatedby(String createdby) {
	this.createdby = createdby;
}

public Timestamp getCreationdate() {
	return creationdate;
}

public void setCreationdate(Timestamp creationdate) {
	this.creationdate = creationdate;
}

public String getLastupdateby() {
	return lastupdateby;
}

public void setLastupdateby(String lastupdateby) {
	this.lastupdateby = lastupdateby;
}

public Timestamp getLastupdatedate() {
	return lastupdatedate;
}

public void setLastupdatedate(Timestamp lastupdatedate) {
	this.lastupdatedate = lastupdatedate;
}

public String getPurchasePrice() {
	return purchasePrice;
}

public void setPurchasePrice(String purchasePrice) {
	this.purchasePrice = purchasePrice;
}


public String getSupplier() {
	return supplier;
}

public void setSupplier(String supplier) {
	this.supplier = supplier;
}

public String getSupplierItem() {
	return supplierItem;
}

public void setSupplierItem(String supplierItem) {
	this.supplierItem = supplierItem;
}

public String getSupplierSite() {
	return supplierSite;
}

public void setSupplierSite(String supplierSite) {
	this.supplierSite = supplierSite;
}

public String getSupersededBy() {
	return supersededBy;
}

public void setSupersededBy(String supersededBy) {
	this.supersededBy = supersededBy;
}


public String getAreaofpaper() {
	return areaofpaper;
}

public void setAreaofpaper(String areaofpaper) {
	this.areaofpaper = areaofpaper;
}


public String getReturnvendor() {
	return returnvendor;
}

public void setReturnvendor(String returnvendor) {
	this.returnvendor = returnvendor;
}

public String getReturnvendorsite() {
	return returnvendorsite;
}

public void setReturnvendorsite(String returnvendorsite) {
	this.returnvendorsite = returnvendorsite;
}


public String getItembillingtype() {
	return itembillingtype;
}

public void setItembillingtype(String itembillingtype) {
	this.itembillingtype = itembillingtype;
}

public String getConfigurationflag() {
	return configurationflag;
}

public void setConfigurationflag(String configurationflag) {
	this.configurationflag = configurationflag;
}



public String getHardallocationtype() {
	return hardallocationtype;
}

public void setHardallocationtype(String hardallocationtype) {
	this.hardallocationtype = hardallocationtype;
}

public String getDefaultsourcetype() {
	return defaultsourcetype;
}

public void setDefaultsourcetype(String defaultsourcetype) {
	this.defaultsourcetype = defaultsourcetype;
}

public String getEasypacki() {
	return easypacki;
}

public void setEasypacki(String easypacki) {
	this.easypacki = easypacki;
}

public String getMercuryinclude() {
	return mercuryinclude;
}

public void setMercuryinclude(String mercuryinclude) {
	this.mercuryinclude = mercuryinclude;
}



public String getMsrp() {
	return msrp;
}

public void setMsrp(String msrp) {
	this.msrp = msrp;
}


public String getUnboxedweight() {
	return unboxedweight;
}

public void setUnboxedweight(String unboxedweight) {
	this.unboxedweight = unboxedweight;
}

public String getUnboxedweightuom() {
	return unboxedweightuom;
}

public void setUnboxedweightuom(String unboxedweightuom) {
	this.unboxedweightuom = unboxedweightuom;
}

public String getUnboxedlength() {
	return unboxedlength;
}

public void setUnboxedlength(String unboxedlength) {
	this.unboxedlength = unboxedlength;
}

public String getUnboxedlengthuom() {
	return unboxedlengthuom;
}

public void setUnboxedlengthuom(String unboxedlengthuom) {
	this.unboxedlengthuom = unboxedlengthuom;
}

public String getUnboxedwidth() {
	return unboxedwidth;
}

public void setUnboxedwidth(String unboxedwidth) {
	this.unboxedwidth = unboxedwidth;
}

public String getUnboxedwidthuom() {
	return unboxedwidthuom;
}

public void setUnboxedwidthuom(String unboxedwidthuom) {
	this.unboxedwidthuom = unboxedwidthuom;
}

public String getUnboxedheight() {
	return unboxedheight;
}

public void setUnboxedheight(String unboxedheight) {
	this.unboxedheight = unboxedheight;
}

public String getUnboxedheightuom() {
	return unboxedheightuom;
}

public void setUnboxedheightuom(String unboxedheightuom) {
	this.unboxedheightuom = unboxedheightuom;
}

public static RowMapper getRowMapper(){
    return new RowMapper() {
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
   			return new CanonE008ItemRec (
   					rs.getString("EZTABLEID"),
   					rs.getString("COMPANY_CODE"),
   					rs.getBigDecimal("PROJECT_ID"),
   					rs.getBigDecimal("TEMPLATE_ID"),
   					rs.getString("TEMPLATE_NAME"),
   					rs.getBigDecimal("PROJ_ITEM_ID"),
   					rs.getString("ACCOUNTING_RULES"),
   					rs.getString("ACCRUAL"),
   					rs.getString("ASSEMBLED_COUNTRY"),
   					rs.getString("ASSET_RECOVERY_COST"),
   					rs.getString("ASSURANCE_POINTS_MIN"),
   					rs.getString("ASSURANCE_POINTS_MAX"),
   					rs.getString("ASSURANCE_POINTS_PER_LICENSE"),
   					rs.getString("ASSURANCE_PTS_FIXED_PERORD_QTY"),
   					rs.getString("BUNDLED_MAINTENANCE_ITEM"),
   					rs.getString("COMMISSIONS_GROUP"),
   					rs.getString("COST_OF_GOODS"),
   					rs.getString("COSTED"),
   					rs.getString("COVERAGE_BASE_TYPE"),
   					rs.getString("COVERAGE_TYPE"),
   					rs.getString("CRITICALITY"),
   					rs.getString("CUSTOMER_ORDEREABLE"),
   					rs.getString("DEFAULT_SRC_SUB_WH"),
   					rs.getString("DEFAULT_SRC_WH"),
   					rs.getString("ITEM_DEPTH"),
   					rs.getString("ELAN_CONTROLLED"),
   					rs.getString("EXPENSE"),
   					rs.getString("EXTENDED_MAINT_POP_AVAILABLE"),
   					rs.getString("FIXED_NO_OF_SEATS"),
   					rs.getString("HEIGHT"),
   					rs.getString("IMAGEWARE_REMOTE_ITEM"),
   					rs.getString("IMAGEWARE_REMOTE_ENABLED"),
   					rs.getString("IMAGEWARE_REMOTE_MODEL"),
   					rs.getString("INSTALLBASE_CONTROLLED"),
   					rs.getString("INVENTORY_TRACKABLE"),
   					rs.getString("INVOICEABLE"),
   					rs.getString("INVOICING_RULES"),
   					rs.getString("ITEM_CLASSIFICATION"),
   					rs.getString("ITEM_DESCRIPTION"),
   					rs.getString("ITEM_NUMBER"),
   					rs.getString("ITEM_TYPE"),
   					rs.getString("ITEM_LENGTH"),
   					rs.getString("LICENSE_CONTROL"),
   					rs.getString("LONG_DESCRIPTION"),
   					rs.getString("MAIN_ENGINE"),
   					rs.getString("MAINTENANCE_ITEM_TERM"),
   					rs.getString("MAINTENANCE_ITEM_TYPE"),
   					rs.getString("MAINTENANCE_POP_AVALIABLE"),
   					rs.getString("MANUFACTURED_COUNTRY"),
   					rs.getString("MANUFACTURER"),
   					rs.getString("MANUFACTURER_ITEM_NUMBER"),
   					rs.getString("MARKETING_MODEL"),
   					rs.getString("MARKETING_SEGMENT"),
   					rs.getString("MATERIAL_GROUP1"),
   					rs.getString("MATERIAL_GROUP2"),
   					rs.getString("MATERIAL_GROUP3"),
   					rs.getString("MAXIMUM_ORDER_QUANTITY"),
   					rs.getString("MERCHANDISE_TYPE"),
   					rs.getString("METERED_MACHINE"),
   					rs.getString("MINIMUM_ORDER_QUANTITY"),
   					rs.getString("NMFC_CLASS"),
   					rs.getString("ORDER_INCREMENTS"),
   					rs.getString("OWNING_DIVISION"),
   					rs.getString("PART_TYPE"),
   					rs.getString("PART_YIELD_DAYS"),
   					rs.getString("PART_YIELD_OUTPUTS"),
   					rs.getString("PARTS_DROPSHIP_DISABLED"),
   					rs.getString("PARTS_SURVEY_REQUIRED"),
   					rs.getString("PLANNING_GROUP"),
   					rs.getString("PRIVATE_LABEL_FLAG"),
   					rs.getString("PRODUCT_CODE"),
   					rs.getString("PRODUCT_LEVEL1"),
   					rs.getString("PRODUCT_LEVEL2"),
   					rs.getString("PRODUCT_LEVEL3"),
   					rs.getString("PRODUCT_LEVEL4"),
   					rs.getString("PRODUCT_LEVEL5"),
   					rs.getString("REMAN_AVAILABLE"),
   					rs.getString("REMANUFACTURED_ITEM_EXISTS"),
   					rs.getString("RETURN_CONTROL_TYPE"),
   					rs.getString("RETURN_CONTROLLED"),
   					rs.getString("RETURN_SUB_WAREHOUSE"),
   					rs.getString("REVENUE"),
   					rs.getString("RMA_ALLOWED"),
   					rs.getString("RMA_INSPECTION_REQUIRED"),
   					rs.getString("SAFETY_HAZARDOUS_CLASS"),
   					rs.getString("SAFETY_HAZARDOUS_ID"),
   					rs.getString("SAFETY_HAZARDOUS_MATERIAL"),
   					rs.getString("SAFETY_HAZARDOUS_NUMBER"),
   					rs.getString("SAFETY_HAZARDOUS_SHIP_LABEL"),
   					rs.getString("SEATS_FROM"),
   					rs.getString("SEATS_TO"),
   					rs.getString("SERIAL_CONTROL"),
   					rs.getString("SERIAL_FROM"),
   					rs.getString("SERIAL_TO"),
   					rs.getString("SERVICE_ALLOCATION_TYPE"),
   					rs.getString("SERVICE_CALL_ENABLED"),
   					rs.getString("SERVICE_MODEL"),
   					rs.getString("SOFTWARE_CATEGORY"),
   					rs.getString("SOFTWARE_MAINTENANCE_CONTROL"),
   					rs.getString("SOFTWARE_PRODUCT_CATEGORY"),
   					rs.getString("SOFTWARE_VERSION"),
   					rs.getString("SOW_REQUIRED"),
   					rs.getString("STANDARD_COST"),
   					rs.getString("SUPPLY_COLOR"),
   					rs.getString("SUPPLY_OEM_CODE"),
   					rs.getString("SUPPLY_OEM_MANUFACTURER"),
   					rs.getString("SUPPLY_TYPE"),
   					rs.getString("SUPPLY_YIELD"),
   					rs.getString("SUPPLY_YIELD_TYPE"),
   					rs.getString("SUPPLY_YIELD_UOM"),
   					rs.getString("TAX_CODE"),
   					rs.getString("TC_OPTION"),
   					rs.getString("TC_OPTION_VALUE"),
   					rs.getString("THIRD_PARTY"),
   					rs.getString("UNIT_OF_MEASURE"),
   					rs.getString("UOM_CLASS"),
   					rs.getString("UPC_CODE"),
   					rs.getString("WARRANTY"),
   					rs.getString("WARRANTY_PERIOD"),
   					rs.getString("WEIGHT"),
   					rs.getString("TARIFF_CODE"),
   					rs.getString("FREIGHT_CLASS_CODE"),
   					rs.getString("RETURN_WAREHOUSE"),
   					rs.getString("INTANGIBLE_MDSE_TYPE"),
   					rs.getString("PURCHASE_PRICE"),
   					rs.getString("SUPPLIER"),
   					rs.getString("SUPPLIER_ITEM"),
   					rs.getString("SUPPLIER_SITE"),
   					rs.getString("SUPERSEDED_BY"),
   					rs.getString("AREA_OF_PAPER"),
   					rs.getString("ITEM_BILLING_TYPE"),
   					rs.getString("CONFIGURATION_FLAG"),
   					rs.getString("RETURN_VENDOR"),
   					rs.getString("RETURN_VENDOR_SITE"),
   					rs.getString("HARD_ALLOCATION_TYPE"),
   					rs.getString("DEFAULT_SOURCE_TYPE"),
   					rs.getString("EASY_PACK_I"),
   					rs.getString("APPROVAL_STATUS"),
   					rs.getString("PROCESS_FLAG"),
   					rs.getString("PROCESS_MESSAGE"),
   					rs.getString("CREATED_BY"),
   					rs.getTimestamp("CREATION_DATE"),
   					rs.getString("LAST_UPDATE_BY"),
   					rs.getTimestamp("LAST_UPDATE_DATE"),
   					rs.getString("MERCURY_INCLUDE"),
   					rs.getString("MSRP_COST"),
   					rs.getString("UNBOXED_WEIGHT"),
   					rs.getString("UNBOXED_WEIGHT_UOM"),
   					rs.getString("UNBOXED_ITEM_LENGTH"),
   					rs.getString("UNBOXED_LENGTH_UOM"),
   					rs.getString("UNBOXED_WIDTH"),
   					rs.getString("UNBOXED_WIDTH_UOM"),
   					rs.getString("UNBOXED_HEIGHT"),
   					rs.getString("UNBOXED_HEIGHT_UOM"),
   					rs.getString("LEAD_TIME"),
   					rs.getString("INTERNAL_ITEM")
   					);
         	}
    	};
	}

	public String getSQLTypeName() throws SQLException {
        return "CANON_E008_PROJ_ITEMS_REC";
    }

    public void readSQL(SQLInput stream, String typeName) throws SQLException {
    	eztableid=stream.readString();
    	companycode=stream.readString();
    	projectid=stream.readBigDecimal();
    	templateid=stream.readBigDecimal();
    	templatename=stream.readString();
    	projitemid=stream.readBigDecimal();
    	accountingrules=stream.readString();
    	accrual=stream.readString();
    	assembledcountry=stream.readString();
    	assetrecoverycost=stream.readString();
    	assurancepointmin=stream.readString();
    	assurancepointsmax=stream.readString();
    	assurancepointsperlicense=stream.readString();
    	assuranceptsfixedperordqty=stream.readString();
    	bundledmaintenanceitem=stream.readString();
    	commissionsgroup=stream.readString();
    	costofgoods=stream.readString();
    	costed=stream.readString();
    	coveragebasetype=stream.readString();
    	coveragetype=stream.readString();
    	criticality=stream.readString();
    	customerordereable=stream.readString();
    	defaultsourcesubwarehouse=stream.readString();
    	defaultsourcewarehouse=stream.readString();
    	itemdepth=stream.readString();
    	elancontrolled=stream.readString();
    	expense=stream.readString();
    	extendedmaintpopavailable=stream.readString();
    	fixednoofseats=stream.readString();
    	height=stream.readString();
    	imagewareremoteitem=stream.readString();
    	imagewareremoteenabled=stream.readString();
    	imagewareremotemodel=stream.readString();
    	installbasecontrolled=stream.readString();
    	inventorytrackable=stream.readString();
    	invoiceable=stream.readString();
    	invoicingrules=stream.readString();
    	itemclassfication=stream.readString();
    	itemdescription=stream.readString();
    	itemnumber=stream.readString();
    	itemtype=stream.readString();
    	itemlength=stream.readString();
    	licensecontrol=stream.readString();
    	longdescription=stream.readString();
    	mainengine=stream.readString();
    	maintenanceitemterm=stream.readString();
    	maintenanceitemtype=stream.readString();
    	maintenancepopavaliable=stream.readString();
    	manufacturedcountry=stream.readString();
    	manufacturer=stream.readString();
    	manufactureritemnumber=stream.readString();
    	marketingmodel=stream.readString();
    	marketingsegment=stream.readString();
    	materialgroup1=stream.readString();
    	materialgroup2=stream.readString();
    	materialgroup3=stream.readString();
    	maximumorderquantity=stream.readString();
    	merchandisetype=stream.readString();
    	meteredmachine=stream.readString();
    	minimumorderquantity=stream.readString();
    	nfmcclass=stream.readString();
    	orderincrements=stream.readString();
    	owningdivision=stream.readString();
    	parttype=stream.readString();
    	partyielddays=stream.readString();
    	partyieldoutputs=stream.readString();
    	partsdropshipdisabled=stream.readString();
    	partssurveyrequired=stream.readString();
    	planninggroup=stream.readString();
    	privatelabelflag=stream.readString();
    	productcode=stream.readString();
    	productlevel1=stream.readString();
    	productlevel2=stream.readString();
    	productlevel3=stream.readString();
    	productlevel4=stream.readString();
    	productlevel5=stream.readString();
    	remanavailable=stream.readString();
    	remanufactureditemexists=stream.readString();
    	returncontroltype=stream.readString();
    	returncontrolled=stream.readString();
    	returnsubwarehouse=stream.readString();
    	revenue=stream.readString();
    	rmaallowed=stream.readString();
    	rmainspectionrequired=stream.readString();
    	safetyhazardousclass=stream.readString();
    	safetyhazardousid=stream.readString();
    	safetyhazardousmaterial=stream.readString();
    	safetyhazardousnumber=stream.readString();
    	safetyhazardousshiplabel=stream.readString();
    	seatsfrom=stream.readString();
    	seatsto=stream.readString();
    	serialcontrol=stream.readString();
    	serialfrom=stream.readString();
    	serialto=stream.readString();
    	serviceallocationtype=stream.readString();
    	servicecallenabled=stream.readString();
    	servicemodel=stream.readString();
    	softwarecategory=stream.readString();
    	softwaremaintenancecontrol=stream.readString();
    	softwareproductcategory=stream.readString();
    	softwareversion=stream.readString();
    	sowrequired=stream.readString();
    	standardcost=stream.readString();
    	supplycolor=stream.readString();
    	supplyoemcode=stream.readString();
    	supplyoemmanufactuer=stream.readString();
    	supplytype=stream.readString();
    	supplyyield=stream.readString();
    	supplyyieldtype=stream.readString();
    	supplyyielduom=stream.readString();
    	taxcode=stream.readString();
    	tcoption=stream.readString();
    	tcoptionvalue=stream.readString();
    	thirdparty=stream.readString();
    	uom=stream.readString();
    	uomclass=stream.readString();
    	upccode=stream.readString();
    	warranty=stream.readString();
    	warrantyperiod=stream.readString();
    	weight=stream.readString();
    	tariffcode=stream.readString();
    	freightclasscode=stream.readString();
    	returnwarehouse=stream.readString();
    	intangiblemdsetype=stream.readString();
    	purchasePrice=stream.readString();
    	supplier=stream.readString();
    	supplierItem=stream.readString();
    	supplierSite=stream.readString();
    	supersededBy=stream.readString();
    	areaofpaper=stream.readString();
    	itembillingtype=stream.readString();
    	configurationflag=stream.readString();
    	returnvendor=stream.readString();
    	returnvendorsite=stream.readString();
    	hardallocationtype=stream.readString();
    	defaultsourcetype=stream.readString();
    	easypacki=stream.readString();
    	approvalstatus=stream.readString();
    	processflag=stream.readString();
    	processmessage=stream.readString();
    	createdby=stream.readString();
    	creationdate=stream.readTimestamp();
    	lastupdateby=stream.readString();
    	lastupdatedate=stream.readTimestamp();
    	mercuryinclude=stream.readString();
    	msrp=stream.readString();
    	unboxedweight=stream.readString();
    	unboxedweightuom=stream.readString();
    	unboxedlength=stream.readString();
    	unboxedlengthuom=stream.readString();
    	unboxedwidth=stream.readString();
    	unboxedwidthuom=stream.readString();
    	unboxedheight=stream.readString();
    	unboxedheightuom=stream.readString();
    	leadTime=stream.readString();
    	internalItem=stream.readString();
    }
    
	public void writeSQL(SQLOutput stream) throws SQLException {
    	stream.writeString(eztableid);
    	stream.writeString(companycode);
    	stream.writeBigDecimal(projectid);
    	stream.writeBigDecimal(templateid);
    	stream.writeString(templatename);
    	stream.writeBigDecimal(projitemid);
    	stream.writeString(accountingrules);
    	stream.writeString(accrual);
    	stream.writeString(assembledcountry);
    	stream.writeString(assetrecoverycost);
    	stream.writeString(assurancepointmin);
    	stream.writeString(assurancepointsmax);
    	stream.writeString(assurancepointsperlicense);
    	stream.writeString(assuranceptsfixedperordqty);
    	stream.writeString(bundledmaintenanceitem);
    	stream.writeString(commissionsgroup);
    	stream.writeString(costofgoods);
    	stream.writeString(costed);
    	stream.writeString(coveragebasetype);
    	stream.writeString(coveragetype);
    	stream.writeString(criticality);
    	stream.writeString(customerordereable);
    	stream.writeString(defaultsourcesubwarehouse);
    	stream.writeString(defaultsourcewarehouse);
    	stream.writeString(itemdepth);
    	stream.writeString(elancontrolled);
    	stream.writeString(expense);
    	stream.writeString(extendedmaintpopavailable);
    	stream.writeString(fixednoofseats);
    	stream.writeString(height);
    	stream.writeString(imagewareremoteitem);
    	stream.writeString(imagewareremoteenabled);
    	stream.writeString(imagewareremotemodel);
    	stream.writeString(installbasecontrolled);
    	stream.writeString(inventorytrackable);
    	stream.writeString(invoiceable);
    	stream.writeString(invoicingrules);
    	stream.writeString(itemclassfication);
    	stream.writeString(itemdescription);
    	stream.writeString(itemnumber);
    	stream.writeString(itemtype);
    	stream.writeString(itemlength);
    	stream.writeString(licensecontrol);
    	stream.writeString(longdescription);
    	stream.writeString(mainengine);
    	stream.writeString(maintenanceitemterm);
    	stream.writeString(maintenanceitemtype);
    	stream.writeString(maintenancepopavaliable);
    	stream.writeString(manufacturedcountry);
    	stream.writeString(manufacturer);
    	stream.writeString(manufactureritemnumber);
    	stream.writeString(marketingmodel);
    	stream.writeString(marketingsegment);
    	stream.writeString(materialgroup1);
    	stream.writeString(materialgroup2);
    	stream.writeString(materialgroup3);
    	stream.writeString(maximumorderquantity);
    	stream.writeString(merchandisetype);
    	stream.writeString(meteredmachine);
    	stream.writeString(minimumorderquantity);
    	stream.writeString(nfmcclass);
    	stream.writeString(orderincrements);
    	stream.writeString(owningdivision);
    	stream.writeString(parttype);
    	stream.writeString(partyielddays);
    	stream.writeString(partyieldoutputs);
    	stream.writeString(partsdropshipdisabled);
    	stream.writeString(partssurveyrequired);
    	stream.writeString(planninggroup);
    	stream.writeString(privatelabelflag);
    	stream.writeString(productcode);
    	stream.writeString(productlevel1);
    	stream.writeString(productlevel2);
    	stream.writeString(productlevel3);
    	stream.writeString(productlevel4);
    	stream.writeString(productlevel5);
    	stream.writeString(remanavailable);
    	stream.writeString(remanufactureditemexists);
    	stream.writeString(returncontroltype);
    	stream.writeString(returncontrolled);
    	stream.writeString(returnsubwarehouse);
    	stream.writeString(revenue);
    	stream.writeString(rmaallowed);
    	stream.writeString(rmainspectionrequired);
    	stream.writeString(safetyhazardousclass);
    	stream.writeString(safetyhazardousid);
    	stream.writeString(safetyhazardousmaterial);
    	stream.writeString(safetyhazardousnumber);
    	stream.writeString(safetyhazardousshiplabel);
    	stream.writeString(seatsfrom);
    	stream.writeString(seatsto);
    	stream.writeString(serialcontrol);
    	stream.writeString(serialfrom);
    	stream.writeString(serialto);
    	stream.writeString(serviceallocationtype);
    	stream.writeString(servicecallenabled);
    	stream.writeString(servicemodel);
    	stream.writeString(softwarecategory);
    	stream.writeString(softwaremaintenancecontrol);
    	stream.writeString(softwareproductcategory);
    	stream.writeString(softwareversion);
    	stream.writeString(sowrequired);
    	stream.writeString(standardcost);
    	stream.writeString(supplycolor);
    	stream.writeString(supplyoemcode);
    	stream.writeString(supplyoemmanufactuer);
    	stream.writeString(supplytype);
    	stream.writeString(supplyyield);
    	stream.writeString(supplyyieldtype);
    	stream.writeString(supplyyielduom);
    	stream.writeString(taxcode);
    	stream.writeString(tcoption);
    	stream.writeString(tcoptionvalue);
    	stream.writeString(thirdparty);
    	stream.writeString(uom);
    	stream.writeString(uomclass);
    	stream.writeString(upccode);
    	stream.writeString(warranty);
    	stream.writeString(warrantyperiod);
    	stream.writeString(weight);
    	stream.writeString(tariffcode);
    	stream.writeString(freightclasscode);
    	stream.writeString(returnwarehouse);
    	stream.writeString(intangiblemdsetype);
    	stream.writeString(purchasePrice);
    	stream.writeString(supplier);
    	stream.writeString(supplierItem);
    	stream.writeString(supplierSite);
    	stream.writeString(supersededBy);
    	stream.writeString(areaofpaper);
    	stream.writeString(itembillingtype);
    	stream.writeString(configurationflag);
    	stream.writeString(returnvendor);
    	stream.writeString(returnvendorsite);
    	stream.writeString(hardallocationtype);
    	stream.writeString(defaultsourcetype);
    	stream.writeString(easypacki);
    	stream.writeString(approvalstatus);
    	stream.writeString(processflag);
    	stream.writeString(processmessage);    	
    	stream.writeString(createdby);
    	stream.writeTimestamp(creationdate);
    	stream.writeString(lastupdateby);
    	stream.writeTimestamp(lastupdatedate);  
    	stream.writeString(mercuryinclude);
    	stream.writeString(msrp);
    	stream.writeString(unboxedweight);
    	stream.writeString(unboxedweightuom);
    	stream.writeString(unboxedlength);
    	stream.writeString(unboxedlengthuom);
    	stream.writeString(unboxedwidth);
    	stream.writeString(unboxedwidthuom);
    	stream.writeString(unboxedheight);
    	stream.writeString(unboxedheightuom);
    	stream.writeString(leadTime);  
    	stream.writeString(internalItem);
    }  


    public String toString() {
        return "CanonE008ItemRec{" + "eztableId="+eztableid+", companyCode=" +companycode+", " + "projectid="+projectid+", templateid="+ templateid+", templatename="+ templatename
        		+"," + " projitemid="+projitemid+", accountingrules="+  accountingrules+", accrual="+accrual+", assembledcountry="+assembledcountry+
        		", assetrecoverycost="+ assetrecoverycost+", assurancepointmin="+assurancepointmin+", assurancepointsmax="+assurancepointsmax+", assurancepointsperlicense="+assurancepointsperlicense+
        		", assuranceptsfixedperordqty="+assuranceptsfixedperordqty+", bundledmaintenanceitem="+bundledmaintenanceitem+", commissionsgroup="
        		+commissionsgroup+", costofgoods="+costofgoods+", costed="+costed+", coveragebasetype="+
        		coveragebasetype+", coveragetype="+coveragetype+", criticality="+criticality+", customerordereable="+customerordereable+", defaultsourcesubwarehouse="+defaultsourcesubwarehouse+", defaultsourcewarehouse="+defaultsourcewarehouse+", itemdepth="+itemdepth+", elancontrolled="+elancontrolled+", expense="+expense+", extendedmaintpopavailable="+extendedmaintpopavailable+", fixednoofseats="+fixednoofseats+", height="+height+", imagewareremoteitem="+imagewareremoteitem+", imagewareremoteenabled="+imagewareremoteenabled+", imagewareremotemodel="+imagewareremotemodel+", installbasecontrolled="+installbasecontrolled+", inventorytrackable="+inventorytrackable+", invoiceable="+invoiceable+", invoicingrules="+invoicingrules+", itemclassfication="+itemclassfication+", itemdescription="+itemdescription+", itemnumber="+itemnumber+", itemtype="+itemtype+'}';
    }
    
   /* public static interface RowMapper {
        Object mapRow(ResultSet rs, int rowNum) throws SQLException;
    }*/
 
}

