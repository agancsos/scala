#!/usr/bin/env python3
###############################################################################
# Name        : palindrome_checker.py                                         #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with palindromes.                            #
###############################################################################
import os, sys;

class PalindromeChecker:
	file_path=None;text=None;
	def __init__(self, params=dict()):
		sorted_dict = sorted(params.items(), key=lambda x:x[1]);
		print(sorted_dict);
		self.text = params["--text"] if "--text" in params.keys() else "";
		self.file_path = params["-f"] if "-f" in params.keys() else "";
		if (self.file_path != ""):
			with open(self.file_path, 'r') as fh: self.text = fh.read();
		pass;
	def check_palindrome(self, text):
		for i in range(0, len(text) - 1):
			if (text[i] != text[len(text) - i - 1]): return False;
		return True;
	def invoke(self):
		for line in self.text.split("\n"): 
			if (line == ""): continue;
			print("{0} => {1}".format(line, self.check_palindrome(line)));
		pass;
	pass;

if __name__ == "__main__":
	params = dict();
	for i in range(0, len(sys.argv) - 1): params[sys.argv[i]] = sys.argv[i + 1];
	session = PalindromeChecker(params);
	session.invoke();
	pass;

