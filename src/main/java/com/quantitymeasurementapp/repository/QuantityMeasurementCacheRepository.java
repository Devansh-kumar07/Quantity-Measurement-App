package com.quantitymeasurementapp.repository;

import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.exception.DatabaseException;
import com.quantitymeasurementapp.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    @Override
    public void save(QuantityMeasurementEntity entity) {

        String sql = "INSERT INTO quantity_measurement (operation, value, unit) VALUES (?, ?, ?)";

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, entity.getOperation());
            ps.setDouble(2, entity.getValue());
            ps.setString(3, entity.getUnit());

            ps.executeUpdate();

        } catch (Exception e) {
            throw new DatabaseException("DB Save Failed", e);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        String sql = "SELECT operation, value, unit FROM quantity_measurement";

        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                list.add(new QuantityMeasurementEntity(
                        rs.getString("operation"),
                        rs.getDouble("value"),
                        rs.getString("unit")
                ));
            }

        } catch (Exception e) {
            throw new DatabaseException("DB Read Failed", e);
        }

        return list;
    }
}