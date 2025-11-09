package com.snacks.dao;

import com.snacks.model.Snack;
import com.snacks.util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SnackDAO {

    public List<Snack> getAllSnacks(int page, int pageSize) {
        List<Snack> snacks = new ArrayList<>();
        String sql = "SELECT * FROM japanese_snacks ORDER BY id LIMIT ? OFFSET ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int offset = (page - 1) * pageSize;
            stmt.setInt(1, pageSize);
            stmt.setInt(2, offset);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Snack snack = new Snack(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("japanese"),
                        rs.getString("english"),
                        rs.getString("description"),
                        rs.getString("image_name")
                );
                snacks.add(snack);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return snacks;
    }

    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM japanese_snacks";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Snack getSnackById(int id) {
        String sql = "SELECT * FROM japanese_snacks WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Snack(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("japanese"),
                        rs.getString("english"),
                        rs.getString("description"),
                        rs.getString("image_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}