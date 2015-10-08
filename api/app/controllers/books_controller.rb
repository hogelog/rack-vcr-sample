class BooksController < ApplicationController
  def index
    @books = Book.all
    render json: @books.to_json
  end

  def show
    @book = Book.find_by(id: params[:id])
    if @book
      render json: @book.to_json
    else
      head 404
    end
  end
end
