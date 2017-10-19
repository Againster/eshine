package so;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by root on 5/26/17.
 */
public class so {
    public static final <T> void sqlNullOrValue(PreparedStatement ps, int parameterIndex, int sqlType, T value) throws SQLException, SQLException {
        if(value == null) {
            ps.setNull(parameterIndex, sqlType);
        } else {
            ps.setString(parameterIndex, value.toString());
        }
    }
}
