#!/usr/bin/env python3
###############################################################################
# Name        : tree.py                                                       #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with binary search trees.                    #
###############################################################################
import os, sys;

class Node:
	left=None;right=None;data=None;
	def __init__(self, left=None, right=None, data=None):
		self.left = left; self.right = right; self.data = data;
		pass;
	pass;

class Tree:
	root=None;
	def __init__(self, children = []):
		self.root = Node(Node(), Node(), None);
		for child in children: self.insert_child(self.root, child);
		pass;
	def insert_child(self, node=None, child=None):
		inserted = False;
		if node == None: node = self.root;
		if (node.data == None): node.data = child; return True;
		if (node.left == None and (node.right == None or node.right.data > child)): node.left = Node(Node(), Node(), child); return True;
		if (node.right == None and (node.right == None or node.right.data < child)): node.right = Node(Node(), Node(), child); return True;
		if not inserted and node.left != None: inserted = self.insert_child(node.left, child);
		if not inserted and node.right != None: inserted = self.insert_child(node.right, child);
		return inserted;
		pass;
	def search(self, value, node = None):
		if (node == None): node = self.root;
		if (node.data == value): return node;
		if (node.left != None and node.left.data == value): return node.left;
		if (node.right != None and node.right.data == value): return node.right;
		if node.left != None and self.search(value, node.left) != None: return self.search(value, node.left);
		if node.right != None and self.search(value, node.right) != None: return self.search(value, node.right);
		return None;
		pass;

class Driver:
	tree=None;search=None;
	def __init__(self, params=dict()):
		self.search = int(params["-s"]) if "-s" in params.keys() else 2;
		self.tree = Tree(params["--children"].split(",")) if "--children" in params.keys() else Tree([10, 4, 3, 2, 1]);
		pass;
	def invoke(self):
		node = self.tree.search(self.search);
		if (node == None): print("No node found...");
		else: print(node.data);
		pass;

if __name__ == "__main__":
	params = dict();
	for i in range(0, len(sys.argv) - 1): params[sys.argv[i]] = sys.argv[i + 1];
	session = Driver(params);
	session.invoke();
	pass;

