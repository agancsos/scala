#!/usr/bin/ruby
###############################################################################
# Name        : practice.rb                                                   #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with data structures and sorting.            #
###############################################################################

class Practice
	@list = nil;@mapping=nil;
	def initialize(params={})
		@list = params["--list"] != nil ? params["--list"] : [1, 2, 4, 6, 7, 8, 1, 4, 2];
		@mapping = {};
		@list.sort;
		for i in 0..@list.length - 1
			@mapping[@list[i]] = @mapping[@list[i]] == nil ? 1 : @mapping[@list[i]] + 1;
		end
	end

	def convert_to_binary(value)
		result = "";
		for part in value.split(".")
			result += "." if result != "";
			result += part.to_i.to_s(2).rjust(8, "0");
		end
		return result;
	end

	def get_lowest_repeated()
		@mapping.sort_by {|k, v| k};
		return @mapping.keys[0];
	end

	def get_highest_repeated()
		@mapping.sort_by {|k, v| k}
		return @mapping.keys[@mapping.keys.length - 1];
	end

	def get_least_repeated()
		@mapping.sort_by {|k, v| v}
		return @mapping.keys[0];
	end

	def get_most_repeated()
		@mapping.sort_by {|k, v| v}
		return @mapping.keys[@mapping.keys.length - 1];
	end

	def invoke()
		ip = "127.0.0.1";
		print "IP: #{ip}; #{self.convert_to_binary(ip)}\n";
		print "#{@mapping}\n";
		print "Lowest repeated value: #{self.get_lowest_repeated} = #{@mapping[self.get_lowest_repeated]}\n";
		print "Highest repeated value: #{self.get_highest_repeated} = #{@mapping[self.get_highest_repeated]}\n";
		print "Lest repeated value: #{self.get_least_repeated} = #{@mapping[self.get_least_repeated]}\n";
		print "Most repeated value: #{self.get_most_repeated} = #{@mapping[self.get_most_repeated]}\n";
	end 	
end

params = {};
for i in 0..ARGV.length - 1
	params[ARGV[i]] = ARGV[i + 1];
end

session = Practice.new(params);
session.invoke();
