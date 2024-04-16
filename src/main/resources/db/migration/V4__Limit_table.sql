CREATE TABLE limit_table (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INT NOT NULL,
    currency_shortname CHAR(3) NOT NULL,
    expense_category_id INT NOT NULL,
    limit_sum DECIMAL(19, 2) DEFAULT 1000.00 NOT NULL,
    remaining_limit DECIMAL(19, 2) NOT NULL,
    limit_dateTIME TIMESTAMP WITH TIME ZONE NOT NULL,
    CONSTRAINT fk_user_id
        FOREIGN KEY(user_id)
            REFERENCES account(id),
    CONSTRAINT fk_expense_category_id
        FOREIGN KEY(expense_category_id)
            REFERENCES expense_category(id)
)