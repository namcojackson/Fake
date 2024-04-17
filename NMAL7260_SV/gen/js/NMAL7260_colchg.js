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
			S21ColOrderEx.hiddenTable(headerId);
//			return;
		}

		this.columnObj.columnsDef =  orderDef.split(this.ordExSeparater);

		// Column Order Change Table Renew
		S21ColOrderEx.renewTableInner(this.ordExHeadrId, this.ordExDataId, this.columnObj.columnsDef);

	},

	// Table And Parent Visibility : Hidden
	hiddenTable : function(headerId) {

		tableElm = document.getElementById(headerId);
		if (!tableElm) {
			return;
		}
		divElm = tableElm.parentNode;
		if (!divElm) {
			return;
		}

		tableElm.visibility = 'hidden';
		divElm.visibility = 'hidden';
	},

	// Table Renew
	renewTableInner : function(headerId, dataId, ids) {

		// Order
		var idOrder = [];

		var headTdElms = [];
		var tableElm;

		// Head
		tableElm = document.getElementById(headerId);
		headTdElms = tableElm.getElementsByTagName('td');

		var cellLen = headTdElms.length;
		for (var i = 0; i < cellLen; i++) {
			var id = headTdElms[i].id;
			idOrder[id] = i;
		}

		// New Order index
		var newOrder = [];
		var rowLen = tableElm.rows.length;
		var len = ids.length;
		for (var i = 0; i < len; i++) {
			newOrder.push(idOrder[ids[i]]);
		}

		// Move Header
		S21ColOrderEx.newOrderTableCols(newOrder, tableElm);
		S21ColOrderEx.newOrderTableCells(newOrder, tableElm);

		// Move Data
		var tableDataElm = document.getElementById(dataId);
		S21ColOrderEx.newOrderTableCols(newOrder, tableDataElm);
		S21ColOrderEx.newOrderTableCells(newOrder, tableDataElm);
	},

	// Table Renew
	newOrderTableCols : function(newOrder, tableElm) {

		var colg = tableElm.getElementsByTagName('colgroup').item(0);
		var cols = S21ColOrderEx.getTableChildColNodes(tableElm, "colgroup");

		var newColg = document.createElement("colgroup");

		var parent = colg.parentNode;

		var elms = [];
		var len = newOrder.length;
		for (var i = 0; i < len; i++) {
			var elm = cols[newOrder[i]];
			//newColg.appendChild(elm);
			if (elm) {
				elms.push(elm);
			}
		}

		var len = elms.length;
		for (var i = 0; i < len; i++) {
			newColg.appendChild(elms[i]);
		}

		parent.removeChild(colg);
		parent.appendChild(newColg);

	},

	newOrderTableCells : function(newOrder, tableElm) {

	    var rowNum = tableElm.rows.length;

		var cellLen = newOrder.length;

		var tbElm = tableElm.tBodies[0];
		var newTbElm = document.createElement("tbody");

	    for(var i = 0; i < rowNum ; i++) {
	    	// Record
	    	var trElm = tableElm.rows[i];

	    	var cloneTrElm = trElm.cloneNode(false);

			var elms = [];
			var len = newOrder.length;
			for (var j = 0; j < len; j++) {
				var tdElm = trElm.cells[newOrder[j]];
				if (tdElm) {
					elms.push(tdElm);
				}
			}

			var len = elms.length;
			for (var j = 0; j < len; j++) {
				cloneTrElm.appendChild(elms[j]);
			}

			var parent = trElm.parentNode;

			//parent.removeChild(trElm);
			newTbElm.appendChild(cloneTrElm);
		}

		tableElm.removeChild(tbElm);

		newTbElm.style.display = 'none';
		tableElm.appendChild(newTbElm);
		newTbElm.style.display = '';
	},

	getTableChildColNodes : function(table, tagname){
		var cols = [];

		var colgroupElm = table.getElementsByTagName(tagname);
	    if(!colgroupElm){
	        return null;
	    }

		return colgroupElm.item(0).getElementsByTagName('col');
	}

}
