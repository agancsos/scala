#!/usr/bin/env python3
###############################################################################
# Name        : practice.py                                                   #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with data structures and sorting.            #
###############################################################################
import os, sys;

class Practice:
	ls = None; mapping = None;
	def __init__(self, params=dict()):
		self.ls = params["--list"] if "--list" in params.keys() else [1, 2, 4, 6, 7, 8, 1, 4, 2];
		self.mapping = {};
		for key in self.ls: self.mapping[key] = self.mapping[key] + 1 if key in self.mapping.keys() else 1;
		pass;
	def convert_to_binary(self, value):
		result = "";
		for part in value.split("."):
			if (result != ""): result += ".";
			result += bin(int(part)).replace("b", "").rjust(8, "0");
		return result;
		pass;
	def get_lowest_repeated(self):
		sorted_map = sorted(self.mapping.keys(), key=lambda x:x);
		return sorted_map[0];
	def get_highest_repeated(self):
		sorted_map = sorted(self.mapping.keys(), key=lambda x:x);
		return sorted_map[len(sorted_map) - 1];
	def get_least_repeated(self):
		sorted_map = sorted(self.mapping);
		return sorted_map[0];
	def get_most_repeated(self):
		sorted_map = sorted(self.mapping);
		return sorted_map[len(sorted_map) - 1];
	def invoke(self):
		ip = "127.0.0.1";
		print("IP: {0}; {1}".format(ip, self.convert_to_binary(ip)));
		print("{0}".format(self.mapping));
		print("Lowest repeated value: {0} = {1}".format(self.get_lowest_repeated(), self.mapping[self.get_lowest_repeated()]));
		print("Highest repeated value: {0} = {1}".format(self.get_highest_repeated(), self.mapping[self.get_highest_repeated()]));
		print("Least repeated value: {0} = {1}".format(self.get_least_repeated(), self.mapping[self.get_least_repeated()]));
		print("Most repeated value: {0} = {1}".format(self.get_most_repeated(), self.mapping[self.get_most_repeated()]));
		pass;
	pass;

if __name__ == "__main__":
	params = dict();
	for i in range(0, len(sys.argv) - 1, 2): params[sys.argv[i]] = sys.argv[i + 1];
	session = Practice(params);
	session.invoke();
	pass;
