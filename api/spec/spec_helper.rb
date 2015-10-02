require "vcr"

VCR.configure do |config|
  config.cassette_library_dir = 'spec/fixtures/cassettes'
end

RSpec.configure do |config|
  config.around(:each, type: :request) do |example|
    host! "api.example.com"

    vcr_cassette = example.metadata[:vcr]
    if vcr_cassette
      VCR.use_cassette(vcr_cassette, record: :all) do
        example.run
      end
    else
      example.run
    end
  end

  config.expect_with :rspec do |expectations|
    expectations.include_chain_clauses_in_custom_matcher_descriptions = true
  end

  config.mock_with :rspec do |mocks|
    mocks.verify_partial_doubles = true
  end
end
