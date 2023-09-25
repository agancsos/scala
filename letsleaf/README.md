# LetsLeaf

## Synopsis
LetsLeaf is an open-source JavaScript API to build node-based tree map graphs.  Unlike other map graph API's, LetsLeaf offers a way to build maps that represent parent-child relationships or dependency maps.

## Assumptions
* A page will have only a single graph.
* The map will have a reasonably defined limitation of nodes to render.
* The processing will be purely done on the client end.
* The API will be implemented as a single module.
* The API itself will use native JavaScript features and no other dependencies should be required.

## Requirements
* The solution will be able to be instantiated with predefined objects.
* The solution will be able to render nodes.
* The solution will be able to render child nodes.
* The solution will be able to connect related nodes.
* The solution will be semi-customizable.
* The solution will be portable.
* The solution will be light-weight.

## Implementation Details
The solution is implemented using a simple JavaScript singleton function-based Object that holds some basic configurations, such as the node width and the connector color.  It also holds the parent DOM container where to place the dynamically generated elements alone with the parent-child items structure to be used for rendering.  Upon rendering, the first parent node is placed in the container DOM and then we recursively dig through the children, placing the child node, after which we calculate the connector's placement based on the parent node's "center of gravity".  

## Example
```html
<!DOCTYPE html>
<html>
    <head>
        <title>LetsLeaf Driver</title>
        <link href="main.css" rel="stylesheet"/>
        <script src="letsleaf.js"></script>
    </head>
    <body>
        <div id="banner">
            <div id="banner-inner">
                LetsLeaf
            </div>
        </div>
        <div id="main">
            <div id="main-inner">
                <div id="container">
                </div>
            </div>
        </div>
        <div id="footer">
            <div id="footer-inner">
				&copy; <script> (new Date()).fullDate.getFullYear(); </script>  Gancsos Labs<br/>All Rights Reserved
            </div>
        </div>
		<script>
			LetsLeaf.initialize(ViewService.getConfiguration().nodes, document.getElementById("container"));
        	LetsLeaf.generate();		
		</script>
    </body>
</html>
```
Please note that:
* JQuery might be needed for AJAX requests, but is not show as part of the API example.
* The default for "nodeWidth" is 255 (int) and the default for "connectorColor" is "gold" (string).

## TODO
1. Implement auto-fit zoom

## References

