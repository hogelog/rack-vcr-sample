require "rails_helper"

RSpec.describe BooksController, type: :controller do
  describe "#index", vcr: "books_index" do
    it "show books" do
      get :index
      expect(response).to have_http_status(200)
      expect(assigns(:books).map{|book| book["title"] }).to  eq(%w(K&R Camel))
    end
  end

  describe "#show" do
    it "show books", vcr: "books_show_200" do
      get :show, id: 1
      expect(response).to have_http_status(200)
      expect(assigns(:book)["title"]).to  eq("K&R")
    end
  end
end
