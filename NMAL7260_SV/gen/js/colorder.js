/*@cc_on var _d = document; eval('var document = _d'); @*/

// Column Order Change Javascript
S21ColOrderEx = {

	ordExHeadrId : "",
	ordExDataId : "",
	ordExDefId : "",
	ordExSeparater : ":",
	columnObj : {
		columnsOrg : [],
		columnsDef : []
	},

	initialize : function(headerId, dataId, orderDefId){

		this.ordExHeadrId = headerId;
		this.ordExDataId = dataId;
		this.ordExDefId = orderDefId;

//		// Table
//		var table = document.getElementById(this.ordExHeadrId);

//		if (table == null) {
//			return;
//		}

//		var trElm = table.rows[0];
//		var len = trElm.cells.length;
//		var tdElm;
//		var value;
//		var id;
//		for(var i = 0; i < len; i++) {
//			tdElm = trElm.cells[i];
//			id = tdElm.getAttribute("id");
//			this.columnObj.columnsOrg[i] = id;
//		}

		// Order Def
		var orderDef = document.getElementById(this.ordExDefId).value;

		if (!orderDef) {
			return;
		}

		this.columnObj.columnsDef =  orderDef.split(this.ordExSeparater);

		// Column Order Change Table Renew
		S21Column.renewTableInner(this.ordExHeadrId, this.ordExDataId, this.columnObj.columnsDef);

	}

}


