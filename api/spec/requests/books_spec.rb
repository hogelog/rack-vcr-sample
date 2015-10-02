require 'rails_helper'

RSpec.describe "Books", type: :request, vcr: "books" do
  fixtures :books

  describe "books#index" do
    it "returns books" do
      get "/books"
      expect(response).to have_http_status(200)
      data = JSON.parse(response.body)
      expect(data.size).to eq(2)
      expect(data.map{|book| book["title"] }).to eq(%w(K&R Camel))
    end
  end

  describe "books#show" do
    it "returns book" do
      get "/books/#{books(:c).id}"
      expect(response).to have_http_status(200)
      data = JSON.parse(response.body)
      expect(data["title"]).to eq("K&R")
    end
  end
end
