#!/usr/bin/ruby
###############################################################################
# Name        : dutch_flag.rb                                                 #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with Dutch Flag problem.                     #
###############################################################################

class DutchFlag
	@numbers=nil;
	def initialize(params={})
		@numbers = params["--list"] == nil ? [1,2,3,4,5] : params["--list"];
	end
	def invoke()
		print "Original: #{@numbers}\n";
		sorted = @numbers.sort_by {|v| (v % 2 != 0) ? 0 : 1};
		print "Sorted  : #{sorted}\n";
	end
end

params = {};
for i in 0..ARGV.length - 1
	params[ARGV[i]] = ARGV[i + 1]
end

session = DutchFlag.new(params);
session.invoke();
