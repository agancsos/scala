#!/usr/bin/ruby
###############################################################################
# Name        : tree.rb                                                       #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with binary search trees.                    #
###############################################################################

class Node
	attr_accessor :left, :right, :data;
	def initialize(left=nil, right=nil, data=nil)
		@left = left; @right = right; @data = data;
	end
end

class Tree
	@root=nil;
	def initialize(children=[])
		@root = Node.new(Node.new(), Node.new(), nil);
		children.each do |child|
			self.add_child(@root, child.to_i);
		end
	end

	def add_child(node, child)
		inserted = false;
		node = @root if node == nil;
		if (node.data == nil)
			node.data= child;
			return true;
		end 
		if (node.left == nil and node.data > child)
			node.left = Node.new(Node.new(nil, nil, nil), Node.new(nil, nil, nil), child);
			return true;
		end
		if (node.right == nil and node.data < child)
			node.right = Node.new(Node.new(nil, nil, nil), Node.new(nil, nil, nil), child);
			return true;
		end
		inserted = self.add_child(node.left, child) if not inserted;
		inserted = self.add_child(node.right, child) if not inserted;
		return inserted;
	end

	def search(value, node=nil)
		node = @root if node == nil;
		return node if node.data== value;
		return node.left if node.left != nil and node.left.data == value;
		return node.right if node.right != nil and node.right.data == value;
		return search(value, node.left) if node.left != nil and search(value, node.left) != nil;
		return search(value, node.right) if node.right != nil and search(value, node.right) != nil;
		return nil;
	end
end

class Driver
	@tree=nil;@search=nil;
	def initialize(params={})
		children = params["--children"] == nil ? "10,4,3,2,1" : params["--children"];
		throw "Children cannot be empty..." if children == nil;
		@tree = Tree.new(children.split(","));
		@search = params["-s"] == nil ? 2 : params["-s"];
		throw "Search cannot be empty..." if @search == nil;
	end

	def invoke()
		node = @tree.search(@search);
		if node == nil
			print "No node found...\n";
		else
			print "#{node.data if node != nil}\n";
		end
	end
end

params = {};
for i in 0..ARGV.length
	params[ARGV[i]] = ARGV[i + 1];
end
session = Driver.new(params);
session.invoke();

