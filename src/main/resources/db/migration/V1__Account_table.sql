CREATE TABLE account (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    currency_shortname CHAR(3) NOT NULL,
    balance DECIMAL(19, 2) NOT NULL
)