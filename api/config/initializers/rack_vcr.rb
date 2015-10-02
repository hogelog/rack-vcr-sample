if Rails.env.test?
  Rails.configuration.middleware.insert(0, Rack::VCR)
end
