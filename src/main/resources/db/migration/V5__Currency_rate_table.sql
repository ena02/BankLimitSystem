CREATE TABLE currency_rate (
    pair_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    base_currency CHAR(3) NOT NULL,
    quote_currency CHAR(3) NOT NULL,
    exchange_rate DECIMAL(19, 2) NOT NULL, -- Example precision and scale
    datetime timestamptz NOT NULL,
    UNIQUE (base_currency, quote_currency)
);