Rails.application.routes.draw do
  resources :books, only: %w(index show)
end
