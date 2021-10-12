#!/usr/bin/env python3
###############################################################################
# Name        : dutch_flag.py                                                 #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with Dutch Flag problem.                     #
###############################################################################
import os, sys;

class DutchFlag:
	numbers=None;
	def __init__(self, params=dict()):
		self.numbers = params["--list"].split(",") if "--list" in params.keys() else [1,2,3,4,5];
		pass;
	def invoke(self):
		print("Original: {0}".format(self.numbers));
		sorted_value = sorted(self.numbers, key=lambda x:x % 2 == 0);
		print("Sorted  : {0}".format(sorted_value));
	pass;

if __name__ == "__main__":
	params = dict();
	for i in range(0, len(sys.argv) - 1): params[sys.argv[i]] = sys.argv[i + 1];
	session = DutchFlag(params);
	session.invoke();
	pass;
