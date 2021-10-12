#!/usr/bin/env python3
###############################################################################
# Name        : fibonacci.py                                                  #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with Fibonacci sequence.                     #
###############################################################################
import os, sys;
class Fibonacci:
	max_number=None;
	def __init__(self, params=dict()):
		self.max_number = int(params["--max"]) if "--max" in params.keys() else 200;
		pass;
	def calculate(self, value):
		if (value < 1): return value;
		return abs(self.calculate(value - 1) + self.calculate(value - 2));
		pass;
	def invoke(self):
		for i in range(0, self.max_number): print("{0} => {1}".format(i, self.calculate(i)));
		pass;
	pass;

if __name__ == "__main__":
	params = dict();
	for i in range(0, len(sys.argv) - 1): params[sys.argv[i]] = sys.argv[i + 1];
	session = Fibonacci(params);
	session.invoke();
	pass;
