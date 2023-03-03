CREATE TABLE IF NOT EXISTS accidents_articles (
   id SERIAL PRIMARY KEY,
   accident_id INT NOT NULL REFERENCES accidents(id),
   article_id INT NOT NULL REFERENCES articles(id)
);