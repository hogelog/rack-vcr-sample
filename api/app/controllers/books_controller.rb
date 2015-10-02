class BooksController < ApplicationController
  def index
    @books = Book.all
    render json: @books.to_json
  end

  def show
    @book = Book.find(params[:id])
    render json: @book.to_json
  end
end
