#!/usr/bin/ruby

class PalindromeChecker
	@file_path=nil;@text=nil;
	def initialize(params={})
		@file_path = params["-f"] if params["-f"] != nil and params["--text"] == nil;
		@text = params["--text"].strip if params["--text"] != nil and params["-f"] == nil;
	end
	def read_file()
		if (File.exist?("#{@file_path}"))
			File.open(@file_path, 'r') do |f|
				f.each_line do |line|
					@text += "#{line}";
				end
			end
		else
			throw "File does not exist...";
		end
	end
	def check_palindrome(text)
		for i in 0..text.length - 1
			if (text[i] != text[text.length - i - 1])
				return false;
			end
		end
		return true;
	end
	def invoke()
		if (@file_path != nil)
			@text = "";
			self.read_file();
		end
		@text.split("\n").each do |l| print "#{l} => #{self.check_palindrome(l)}\n"; end;
	end
end

params = {}
for i in 0..ARGV.length
	params[ARGV[i]] = ARGV[i + 1];
end

session = PalindromeChecker.new(params);
session.invoke();
