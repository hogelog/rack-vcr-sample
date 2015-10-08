require "rack"
require "rack/vcr"
require "pry"

VCR.configure do |config|
  config.cassette_library_dir = File.join(File.dirname(__FILE__), "cassettes")
end

class CassetteLocator
  def initialize(app)
    @app = app
  end

  def call(env)
    cassette = env["HTTP_X_VCR_CASSETTE"]
    match = (env["HTTP_X_VCR_MATCH"] || "path query").split.map(&:to_sym)
    if cassette
      VCR.use_cassette(cassette, record: :none, match_requests_on: match) do
        @app.call(env)
      end
    else
      @app.call
    end
  end
end

class MockApp
  def self.call(env)
    [501, {}, ["Not Implemented"]]
  end
end

app = Rack::Builder.new do
  use CassetteLocator
  use Rack::VCR, replay: true
  run MockApp
end

run app
