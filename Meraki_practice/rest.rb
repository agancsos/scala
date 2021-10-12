#!/usr/bin/ruby
###############################################################################
# Name        : rest.rb                                                       #
# Author      : Abel Gancsos                                                  #
# Version     : v. 1.0.0.0                                                    #
# Description : Practice working with REST.                                   #
###############################################################################
require "uri";
require "net/http";

class Rester
	@methodType=nil;@endpoint=nil;@headers=nil;@body=nil;
	
	def initialize(params={})
		@methodType = params["-m"] != nil ? params["-m"] : "GET";
		@endpoint = params["--endpoint"] != nil ? params["--endpoint"] : "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";
		@headers = {};
		@body = {};
		if (params["-h"] != nil)
			pairs = params["-h"].split(",");
			for i in 0..pairs.length
				comps = pairs[i].split(":");
				if (comps.length == 2)
					@headers[comps[0]] = comps[1];
				end
			end
		end
		if (params["-d"] != nil)
			pairs = params["-d"].split(",");
			for i in 0..pairs.length
				comps = pairs[i].split(":");
				if (comps.length == 2)
					@body[comps[0]] = comps[1];
				end
			end
		end
	end

	def invoke_get(endpoint, headers)
		req = Net::HTTP::Get.new(URI(@endpoint));
		for k in @headers.keys
			req[k] = @headers[k];
		end
		rsp = Net::HTTP.start(req.uri.host, :use_ssl => req.uri.scheme == 'https') do |http|
			http.request(req)
		end
		return rsp;
	end

	def invoke_post(endpoint, headers, body)
		req = Net::HTTP::Post.new(URI(@endpoint));
		req.body = "#{body}";
		for k in @headers.keys
			req[k] = @headers[k];
		end
		rsp = Net::HTTP.start(req.uri.host, :use_ssl => req.uri.scheme == 'https') do |http|
			http.request(req)
		end
		return rsp;
	end

	def invoke()
		rsp = nil;
		case (@methodType)
			when "GET"
				rsp = self.invoke_get(@endpoint, @headers);
			when "POST"
				rsp = self.invoke_post(@endpoint, @headers, @body);
			else
				print "Method #{@methodType} not valid...\n";
				exit(-1);
		end
		print "Response: #{rsp.body}";
	end 
end

params = {};
for i in 0..ARGV.length - 1
	params[ARGV[i]] = ARGV[i + 1];
end

session = Rester.new(params);
session.invoke();
