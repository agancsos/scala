#!/usr/bin/env python3
###############################################################################
# Name        : clean_akka.py                                                 #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Cleans the AKKA conf file.                                    #
###############################################################################
import os, sys;

class AkkaCleaner:
	conf_path=None;target_path=None;debug=None;clean=None;
	def __init__(self, params=dict()):
		self.conf_path = params["-p"] if "-p" in params.keys() else "";
		self.target_path = params["-t"] if "-t" in params.keys() else "";
		self.debug = True if "--debug" in params.keys() and int(params["--debug"]) > 0 else False;
		self.clean = True if "--clean" in params.keys() and int(params["--clean"]) > 0 else False;
	def invoke(self):
		assert self.conf_path != "" and os.path.exists(self.conf_path), "Conf path cannot be empty and must exist...";
		if not self.debug: assert self.target_path != "", "Target path cannot be empty...";
		if self.target_path != "" and os.path.exists(self.target_path) and self.clean: os.remove(self.target_path);
		with open(self.conf_path, "r") as fin:
			line = fin.readline();
			while line != "":
				if line.strip() == "" or (len(line.strip()) > 0 and line.strip()[0] == "#") or (len(line.strip()) > 1 and line.strip()[0:1] == "//"):
					line = fin.readline(); 
					continue;
				if self.debug: print(line);
				else:
					with open(self.target_path, "a") as fout: fout.write(line);
				line = fin.readline();
	pass;
if __name__ == "__main__":
	params = dict();
	for i in range(0, len(sys.argv) - 1): params[sys.argv[i]] = sys.argv[i + 1];
	session = AkkaCleaner(params);
	session.invoke();

