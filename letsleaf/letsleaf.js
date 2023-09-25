//*****************************************************************************
// Name        : LetsLeaf                                                     *
// Author      : Abel Gancsos                                                 *
// Version     : v. 1.0.0.0                                                   *
// Description : API to build node-based tree maps.                           *
//*****************************************************************************
var LetsLeaf = {
	items: null,
	parentView: null,
	nodeWidth: null,
	connectorColor: null,

	initialize: function(items, parentView, nodeWidth="255", connectorColor="gold") {
		this.items = items;
		this.parentView = parentView;
		this.nodeWidth = nodeWidth;
		this.connectorColor = connectorColor;
	},

	// Adds the nodes and connectors for the tree map
	generate: function(node=null, shouldFloat=false, level=0, sibling=0) {
		var x = this;
		if (node === null) {
			for (const item in LetsLeaf.items) {
				LetsLeaf.generate(LetsLeaf.items[item], false, 0);
			}
		} else {
			if (document.getElementById("node-" + node.title) == null) {
				var nodeStyle  = "position: absolute;display: inline-block;";
				var parentRect = document.getElementById("main-inner").getBoundingClientRect();
				nodeStyle      += "border-radius: 80px;border: 2px solid black;height: 40px;";
				nodeStyle      += "min-width: " + LetsLeaf.nodeWidth + "px;padding-top: 2%;text-align: center;margin-bottom: 40px;";
				if (level == 0) {
					nodeStyle += `top: ${(level * 40)}px;`;
				} else {
					nodeStyle += `top: ${level * 40 * 2}px;`;
				}
				if (level == 0) {
					nodeStyle += `left: ${parentRect.width / 2}px;`;
				} else {
					if (sibling % 2 == 0) {
						nodeStyle += `left: ${(parentRect.width / 2 - (LetsLeaf.nodeWidth * sibling))}px;`;
					} else {
						nodeStyle += `left: ${(parentRect.width / 2 + (sibling * LetsLeaf.nodeWidth))}px;`;
					}
				}
				LetsLeaf.parentView.innerHTML += `<div style='${nodeStyle}' id='node-${node.title}'>${node.title}</div>`;
				for (const child in node.children) {
					LetsLeaf.generate(node.children[child], true, level + 1, child);
					LetsLeaf.addConnector(node, node.children[child], level + 1);
				}
			}
		}
	},

	// Connects to nodes to extend an existing path
	addConnector: function(node1, node2, level) {
		const node1DOM     = document.getElementById(`node-${node1.title}`);
		const node2DOM     = document.getElementById(`node-${node2.title}`);
		const node1Rect    = node1DOM.getBoundingClientRect();
		const node2Rect    = node2DOM.getBoundingClientRect();
		var connectorX     = (node2Rect.left + (node2Rect.width / 2));
		var connectorStyle = `position: absolute;background-color: ${LetsLeaf.connectorColor};width: 5px !important;left: ${connectorX}px;`;
		var ch             = (node2Rect.top - node1Rect.bottom);
		var ct             = node1Rect.top;
		console.log(`${level}; ${node1.title}; ${node2.title}`);
		var nh = false;
		var hd = "";

		// Detect position, height, and width of connector based on orientation of the parent's center
		if ((node2Rect.left + node2Rect.width / 2) < (node1Rect.left + node1Rect.width / 2)) {
			nh = true;
			hd = "l";
			ch = (node2Rect.top - ct) / 3;
		} else if ((node2Rect.left + node2Rect.width / 2) > (node1Rect.left + node1Rect.width / 2)) {
			nh = true;
			hd = "r";
			ch = (node2Rect.top - ct) / 3;
		}

		// Place connector base line
		connectorStyle += `top: ${ct}px; height: ${ch}px;`;
		LetsLeaf.parentView.innerHTML += `<div style='${connectorStyle}'>&nbsp;</div>`;
		
		// Add hookline and sinker
		if (nh) {
			if (hd == "l") {
				var cw = (node1Rect.left - (node1Rect.width / 2));
				connectorStyle = `position: absolute;background-color: ${LetsLeaf.connectorColor};height: 5px !important;left: ${connectorX}px;width: ${cw}px;top: ${ct}px;`;
				LetsLeaf.parentView.innerHTML += `<div style='${connectorStyle}'>&nbsp;</div>`;
			} else if (hd == "r") {
				var connectorX2 = (node1Rect.left + (node1Rect.width / 2));
				var cw = (connectorX - connectorX2);
                connectorStyle = `position: absolute;background-color: ${LetsLeaf.connectorColor};height: 5px !important;left: ${connectorX2}px;width: ${cw}px;top: ${ct}px;`;
                LetsLeaf.parentView.innerHTML += `<div style='${connectorStyle}'>&nbsp;</div>`;
			}
		}
	}
};

