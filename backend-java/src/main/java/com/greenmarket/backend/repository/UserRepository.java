package com.greenmarket.backend.repository;

import com.greenmarket.backend.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<User> USER_ROW_MAPPER = new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id_user"));
            user.setNama_lengkap(rs.getString("nama"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            return user;
        }
    };

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id_user, nama, email, password, role FROM users WHERE email = ? LIMIT 1";
        return jdbcTemplate.query(sql, USER_ROW_MAPPER, email).stream().findFirst();
    }

    public User save(User user) {
        String sql = "INSERT INTO users (nama, email, password, role) VALUES (?, ?, ?, ?) RETURNING id_user";
        Integer id = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                user.getNama_lengkap(),
                user.getEmail(),
                user.getPassword(),
                user.getRole()
        );
        user.setId(id);
        return user;
    }
}
