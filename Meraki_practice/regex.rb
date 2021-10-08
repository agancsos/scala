#!/usr/bin/ruby

class Matcher
	@text=nil;@search=nil;

	def initialize(params={})
		@text = params["--text"] != nil ? params["--text"] : "test";
		@search = params["--search"] != nil ? params["--search"] : "te";
	end

	def invoke()
		matches = /#{@search}/.match("#{@text}");
		print "#{matches}\n";
	end
end

params = {};
for i in 0..ARGV.length - 1
    params[ARGV[i]] = ARGV[i + 1];
end

session = Matcher.new(params);
session.invoke();
