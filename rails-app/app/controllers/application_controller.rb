class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception

  private
  
  def api_client
    conn ||= Faraday.new(url: Rails.application.config.api_url) do |faraday|
      faraday.request :url_encoded
      faraday.response :logger
      faraday.adapter Faraday.default_adapter
    end
  end
end
