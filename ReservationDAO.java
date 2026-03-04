package com.oceanview.dao;

import com.oceanview.factory.RoomFactory;
import com.oceanview.model.Reservation;
import com.oceanview.util.DBConnection;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO Pattern Implementation
 * Data Access Object for Reservation operations
 * Encapsulates all database operations for Reservation entity
 */
public class ReservationDAO {
    private Connection connection;

    public ReservationDAO() {
        this.connection = DBConnection.getInstance().getConnection();
    }

    /**
     * Create new reservation
     * @param reservation Reservation object
     * @return true if reservation created successfully
     */
    public boolean createReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (guest_id, guest_name, nic_passport, address, phone, email, " +
                    "room_type, ac_type, adults, children, check_in, check_out, meal_plan, total_amount) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, reservation.getGuestId());
            stmt.setString(2, reservation.getGuestName());
            stmt.setString(3, reservation.getNicPassport());
            stmt.setString(4, reservation.getAddress());
            stmt.setString(5, reservation.getPhone());
            stmt.setString(6, reservation.getEmail());
            stmt.setString(7, reservation.getRoomType());
            stmt.setString(8, reservation.getAcType());
            stmt.setInt(9, reservation.getAdults());
            stmt.setInt(10, reservation.getChildren());
            stmt.setDate(11, Date.valueOf(reservation.getCheckIn()));
            stmt.setDate(12, Date.valueOf(reservation.getCheckOut()));
            stmt.setString(13, reservation.getMealPlan());
            stmt.setBigDecimal(14, reservation.getTotalAmount());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error creating reservation: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get all reservations ordered by latest first
     * @return List of all reservations
     */
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations ORDER BY created_at DESC";
        
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                reservations.add(mapResultSetToReservation(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error getting all reservations: " + e.getMessage());
        }
        
        return reservations;
    }

    /**
     * Get reservation by ID
     * @param id Reservation ID
     * @return Reservation object if found, null otherwise
     */
    public Reservation getReservationById(int id) {
        String sql = "SELECT * FROM reservations WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToReservation(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting reservation by ID: " + e.getMessage());
        }
        
        return null;
    }

    /**
     * Get reservation by guest ID
     * @param guestId Guest ID
     * @return Reservation object if found, null otherwise
     */
    public Reservation getReservationByGuestId(String guestId) {
        String sql = "SELECT * FROM reservations WHERE guest_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, guestId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToReservation(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error getting reservation by guest ID: " + e.getMessage());
        }
        
        return null;
    }

    /**
     * Search reservations by guest name
     * @param guestName Guest name (partial match)
     * @return List of matching reservations
     */
    public List<Reservation> searchReservationsByGuestName(String guestName) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE guest_name LIKE ? ORDER BY created_at DESC";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + guestName + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                reservations.add(mapResultSetToReservation(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error searching reservations by guest name: " + e.getMessage());
        }
        
        return reservations;
    }

    /**
     * Update reservation
     * @param reservation Reservation object
     * @return true if reservation updated successfully
     */
    public boolean updateReservation(Reservation reservation) {
        String sql = "UPDATE reservations SET guest_name = ?, nic_passport = ?, address = ?, " +
                    "phone = ?, email = ?, room_type = ?, ac_type = ?, adults = ?, children = ?, " +
                    "check_in = ?, check_out = ?, meal_plan = ?, total_amount = ? WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, reservation.getGuestName());
            stmt.setString(2, reservation.getNicPassport());
            stmt.setString(3, reservation.getAddress());
            stmt.setString(4, reservation.getPhone());
            stmt.setString(5, reservation.getEmail());
            stmt.setString(6, reservation.getRoomType());
            stmt.setString(7, reservation.getAcType());
            stmt.setInt(8, reservation.getAdults());
            stmt.setInt(9, reservation.getChildren());
            stmt.setDate(10, Date.valueOf(reservation.getCheckIn()));
            stmt.setDate(11, Date.valueOf(reservation.getCheckOut()));
            stmt.setString(12, reservation.getMealPlan());
            stmt.setBigDecimal(13, reservation.getTotalAmount());
            stmt.setInt(14, reservation.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating reservation: " + e.getMessage());
            return false;
        }
    }

    /**
     * Delete reservation
     * @param id Reservation ID
     * @return true if reservation deleted successfully
     */
    public boolean deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting reservation: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if guest ID exists
     * @param guestId Guest ID
     * @return true if guest ID exists
     */
    public boolean guestIdExists(String guestId) {
        String sql = "SELECT COUNT(*) FROM reservations WHERE guest_id = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, guestId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error checking guest ID: " + e.getMessage());
        }
        
        return false;
    }

    /**
     * Generate unique guest ID
     * @return Unique guest ID
     */
    public String generateGuestId() {
        String guestId;
        do {
            // Generate ID with format: GUEST + 6-digit random number
            guestId = "GUEST" + String.format("%06d", (int)(Math.random() * 1000000));
        } while (guestIdExists(guestId));
        
        return guestId;
    }

    /**
     * Map ResultSet to Reservation object
     * @param rs ResultSet
     * @return Reservation object
     * @throws SQLException
     */
    private Reservation mapResultSetToReservation(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("id"));
        reservation.setGuestId(rs.getString("guest_id"));
        reservation.setGuestName(rs.getString("guest_name"));
        reservation.setNicPassport(rs.getString("nic_passport"));
        reservation.setAddress(rs.getString("address"));
        reservation.setPhone(rs.getString("phone"));
        reservation.setEmail(rs.getString("email"));
        reservation.setRoomType(rs.getString("room_type"));
        reservation.setAcType(rs.getString("ac_type"));
        reservation.setAdults(rs.getInt("adults"));
        reservation.setChildren(rs.getInt("children"));
        reservation.setCheckIn(rs.getDate("check_in").toLocalDate());
        reservation.setCheckOut(rs.getDate("check_out").toLocalDate());
        reservation.setMealPlan(rs.getString("meal_plan"));
        reservation.setTotalAmount(rs.getBigDecimal("total_amount"));
        reservation.setCreatedAt(rs.getString("created_at"));
        reservation.setUpdatedAt(rs.getString("updated_at"));
        return reservation;
    }
}
