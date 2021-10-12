#!/usr/bin/ruby
###############################################################################
# Name        : fibonacci.rb                                                  #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with Fibonacci sequence.                     #
###############################################################################

class Fibonacci
	@max_number=nil;
	def initialize(params={})
		@max_number = (params["--max"] != nil ? params["--max"].to_i : 200);
	end
	def calculate(value)
		return value if value < 2;
		return self.calculate(value - 2) + self.calculate(value - 1);
	end
	def invoke()
		for i in 0..@max_number do print "#{i} => #{self.calculate(i)}\n" end
	end
end

params = {};
for i in 0..ARGV.length - 1 do params[ARGV[i]] = ARGV[i + 1]; end
session = Fibonacci.new(params);
session.invoke();

