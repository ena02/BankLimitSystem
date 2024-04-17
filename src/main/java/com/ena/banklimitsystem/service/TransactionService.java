package com.ena.banklimitsystem.service;

import com.ena.banklimitsystem.dto.TransactionLimitDTO;
import com.ena.banklimitsystem.model.TransactionEntity;
import com.ena.banklimitsystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final LimitService limitService;
    private final JdbcTemplate jdbcTemplate;

    public TransactionEntity createTransaction(TransactionEntity transaction) {

        if (transaction.getDateTime() == null) transaction.setDateTime(ZonedDateTime.now());


        Integer limitId = limitService.isExceeded(transaction.getAccountFrom(), transaction.getExpenseCategoryId(), transaction.getSum(), transaction.getCurrencyShortname());

        if (limitId > 0) {
            transaction.setLimitExceeded(true);
            transaction.setExceededLimitId(limitId);
        } else {
            transaction.setLimitExceeded(false);
            transaction.setExceededLimitId(null);
        }


        return transactionRepository.save(transaction);
    }

    public TransactionEntity getTransactionById(Integer id) {
        return transactionRepository.findById(id).get();
    }

    public List<TransactionLimitDTO> getAllTransactionByUserId(Integer userId) {
        return getTransactionsForAccount(userId);
    }

    public List<TransactionEntity> getAllTransactions() {
        return changeTimeZone(transactionRepository.findAll());
    }

    public List<TransactionLimitDTO> getTransactionsForAccount(long accountId) {
        String sql = "SELECT " +
                "t.account_from, " +
                "t.account_to, " +
                "t.currency_shortname AS transactionCurrency, " +
                "t.sum AS transactionSum, " +
                "t.expense_category_id AS transactionCategoryId, " +
                "t.datetime AS transactionDatetime, " +
                "t.limit_exceeded, " +
                "l.limit_sum AS limitSum, " +
                "l.limit_datetime AS limitDatetime, " +
                "l.currency_shortname AS limitCurrency " +
                "FROM " +
                "transaction_table t " +
                "LEFT JOIN limit_table l ON t.exceeded_limit_id = l.id " +
                "WHERE " +
                "t.account_from = ? AND " +
                "t.limit_exceeded = true";

        return jdbcTemplate.query(sql, new Object[]{accountId}, new TransactionRowMapper());
    }

    private static class TransactionRowMapper implements RowMapper<TransactionLimitDTO> {
        @Override
        public TransactionLimitDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            TransactionLimitDTO dto = new TransactionLimitDTO();
            dto.setAccountFrom(rs.getInt("account_from"));
            dto.setAccountTo(rs.getInt("account_to"));
            dto.setTransactionCurrency(rs.getString("transactionCurrency"));
            dto.setTransactionSum(rs.getBigDecimal("transactionSum"));
            dto.setTransactionCategoryId(rs.getInt("transactionCategoryId"));
            dto.setTransactionDatetime(convertToZonedDateTime(rs.getTimestamp("transactionDatetime")));
            dto.setLimitExceeded(rs.getBoolean("limit_exceeded"));
            dto.setLimitSum(rs.getBigDecimal("limitSum"));
            dto.setLimitDatetime(convertToZonedDateTime(rs.getTimestamp("limitDatetime")));
            dto.setLimitCurrency(rs.getString("limitCurrency"));
            return dto;
        }

        private ZonedDateTime convertToZonedDateTime(java.sql.Timestamp timestamp) {
            return timestamp != null ? ZonedDateTime.ofInstant(timestamp.toInstant(), ZoneId.systemDefault()) : null;
        }
    }

    private List<TransactionEntity> changeTimeZone(List<TransactionEntity> transactions) {
        List<TransactionEntity> newTransactions = new ArrayList<>();

        for (TransactionEntity transaction : transactions) {
            transaction.setDateTime(transaction.getDateTime().withZoneSameInstant(ZoneId.systemDefault()));
            newTransactions.add(transaction);
        }

        return newTransactions;
    }
}
