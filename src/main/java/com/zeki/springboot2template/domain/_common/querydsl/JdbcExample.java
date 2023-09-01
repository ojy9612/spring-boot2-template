package com.zeki.springboot2template.domain._common.querydsl;


import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JdbcExample {
    private static final int BATCH_SIZE = 10000;
    private static final String SCHEMA_NAME = "";
    private final JdbcTemplate jdbcTemplate;

    // TODO : 알맞은 Entity로 변경
//    public void saveAll(List<ChangeEntity> items) {
//        while (!items.isEmpty()) {
//            int currentBatchSize = Math.min(BATCH_SIZE, items.size());
//            List<ChangeEntity> subItems = items.subList(0, currentBatchSize);
//            this.batchInsert(subItems);
//            items.subList(0, currentBatchSize).clear();
//        }
//    }
//
//    private void batchInsert(List<ChangeEntity> subItems) {
//        StringBuilder sb = new StringBuilder();
//
//        sb.append("INSERT INTO " + SCHEMA_NAME + ".opera_room_block_info (hotel_code, availability_date, room_type, block, updated_at)");
//        sb.append(" VALUES ");
//
//        for (int i = 0; i < subItems.size(); i++) {
//            sb.append("(?, ?, ?, ?, now())");
//            sb.append(",");
//        }
//        sb.deleteCharAt(sb.length() - 1);
//
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement(sb.toString());
//
//            for (int i = 0; i < subItems.size(); i++) {
//                ps.setString(i * 4 + 1, subItems.get(i).getId().getHotelCode());
//                ps.setDate(i * 4 + 2, Date.valueOf(subItems.get(i).getId().getAvailabilityDate()));
//                ps.setString(i * 4 + 3, subItems.get(i).getId().getRoomType());
//                ps.setInt(i * 4 + 4, subItems.get(i).getBlock());
//            }
//
//            return ps;
//        });
//    }
//
//    public void updateAll(List<ChangeEntity> updateBlockInfoList) {
//        while (!updateBlockInfoList.isEmpty()) {
//            int currentBatchSize = Math.min(BATCH_SIZE, updateBlockInfoList.size());
//            List<ChangeEntity> subItems = updateBlockInfoList.subList(0, currentBatchSize);
//            this.batchUpdate(subItems);
//            updateBlockInfoList.subList(0, currentBatchSize).clear();
//        }
//    }
//
//    private void batchUpdate(List<ChangeEntity> subItems) {
//        String sql = "UPDATE " + SCHEMA_NAME + ".opera_room_block_info SET block = ?, updated_at = now() WHERE hotel_code = ? AND room_type = ? AND availability_date = ?";
//
//        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ChangeEntity entity = subItems.get(i);
//                ps.setInt(1, entity.getBlock());
//                ps.setString(2, entity.getId().getHotelCode());
//                ps.setString(3, entity.getId().getRoomType());
//                ps.setDate(4, Date.valueOf(entity.getId().getAvailabilityDate()));
//            }
//
//            @Override
//            public int getBatchSize() {
//                return subItems.size();
//            }
//        });
//    }
}
