CREATE TABLE transaction_table (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_from BIGINT NOT NULL,
    account_to BIGINT NOT NULL,
    currency_shortname VARCHAR(10) NOT NULL,
    sum DECIMAL(19, 2) NOT NULL,
    expense_category_id INT NOT NULL,
    datetime timestamptz NOT NULL,
    limit_exceeded BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_account_from
        FOREIGN KEY(account_from)
            REFERENCES account(id),
    CONSTRAINT fk_account_to
        FOREIGN KEY(account_to)
            REFERENCES account(id),
    CONSTRAINT fk_expense_category_id
        FOREIGN KEY(expense_category_id)
            REFERENCES expense_category(id)
)