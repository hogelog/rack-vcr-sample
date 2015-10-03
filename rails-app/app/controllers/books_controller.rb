class BooksController < ApplicationController
  def index
    @books = JSON.parse(api_client.get("/books").body)
  end

  def show
    @book = JSON.parse(api_client.get("/books/#{params[:id]}").body)
  end
end
