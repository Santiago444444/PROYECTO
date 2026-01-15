package com.transporte.gestion.dao.Flota;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.Bus;
import com.transporte.gestion.modelo.Flota.BusView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class BusDAO {

    private static final String SQL_FIND_BY_PLACA
            = "SELECT b.placa, b.modelo_id, m.nombre AS modelo_nombre, "
            + "       ma.id AS marca_id, ma.nombre AS marca_nombre, "
            + "       b.cooperativa_id, c.nombre AS cooperativa_nombre, "
            + "       b.capacidad, b.estado, b.anio_fabricacion "
            + "FROM bus b "
            + "JOIN modelo m ON m.id = b.modelo_id "
            + "LEFT JOIN marca ma ON ma.id = m.marca_id "
            + "JOIN cooperativa c ON c.id = b.cooperativa_id "
            + "WHERE b.placa = ?";

    private static final String SQL_LIST
            = "SELECT b.placa, b.modelo_id, m.nombre AS modelo_nombre, "
            + "       ma.id AS marca_id, ma.nombre AS marca_nombre, "
            + "       b.cooperativa_id, c.nombre AS cooperativa_nombre, "
            + "       b.capacidad, b.estado, b.anio_fabricacion "
            + "FROM bus b "
            + "JOIN modelo m ON m.id = b.modelo_id "
            + "LEFT JOIN marca ma ON ma.id = m.marca_id "
            + "JOIN cooperativa c ON c.id = b.cooperativa_id "
            + "WHERE ( ? IS NULL OR UPPER(b.estado) <> 'INACTIVO' ) "
            + "  AND ( ? IS NULL OR TRIM(?) = '' "
            + "   OR b.placa ILIKE '%' || ? || '%' "
            + "   OR m.nombre ILIKE '%' || ? || '%' "
            + "   OR ma.nombre ILIKE '%' || ? || '%' "
            + "   OR c.nombre ILIKE '%' || ? || '%' "
            + "   OR b.estado ILIKE '%' || ? || '%' ) "
            + "ORDER BY b.placa ASC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT
            = "SELECT COUNT(*) "
            + "FROM bus b "
            + "JOIN modelo m ON m.id = b.modelo_id "
            + "LEFT JOIN marca ma ON ma.id = m.marca_id "
            + "JOIN cooperativa c ON c.id = b.cooperativa_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR b.placa ILIKE '%' || ? || '%' "
            + "   OR m.nombre ILIKE '%' || ? || '%' "
            + "   OR ma.nombre ILIKE '%' || ? || '%' "
            + "   OR c.nombre ILIKE '%' || ? || '%' "
            + "   OR b.estado ILIKE '%' || ? || '%' )";

    private static final String SQL_INSERT
            = "INSERT INTO bus (placa, modelo_id, cooperativa_id, capacidad, estado, anio_fabricacion) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE
            = "UPDATE bus SET modelo_id = ?, cooperativa_id = ?, capacidad = ?, estado = ?, anio_fabricacion = ? "
            + "WHERE placa = ?";

    private static final String SQL_UPDATE_ESTADO
            = "UPDATE bus SET estado = ? WHERE placa = ?";

    public BusView buscarPorPlaca(String placa) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_PLACA)) {

            ps.setString(1, placa);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapBusView(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en BusDAO.buscarPorPlaca: " + e.getMessage());
        }
        return null;
    }

    public List<BusView> listarPaginado(String filtro, int limit, int offset) {
        return listarPaginado(filtro, limit, offset, null);
    }

    public List<BusView> listarPaginado(String filtro, int limit, int offset, String soloActivos) {
        List<BusView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST)) {

            ps.setString(1, soloActivos);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);
            ps.setString(6, filtro);
            ps.setString(7, filtro);
            ps.setString(8, filtro);

            ps.setInt(9, limit);
            ps.setInt(10, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapBusView(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado bus: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_COUNT)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);
            ps.setString(6, filtro);
            ps.setString(7, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error contar bus: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(Bus b) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, b.getPlaca());
            ps.setInt(2, b.getModeloId());
            ps.setInt(3, b.getCooperativaId());

            if (b.getCapacidad() != null) {
                ps.setInt(4, b.getCapacidad());
            } else {
                ps.setNull(4, java.sql.Types.SMALLINT);
            }

            ps.setString(5, b.getEstado() != null ? b.getEstado() : "ACTIVO");

            if (b.getAnioFabricacion() != null) {
                ps.setInt(6, b.getAnioFabricacion());
            } else {
                ps.setNull(6, java.sql.Types.SMALLINT);
            }

            ps.executeUpdate();
            return ResultadoOperacion.ok("Bus registrado correctamente.");

        } catch (PSQLException e) {
            // 23505 unique_violation (placa repetida)
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un bus con esa placa.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Bus b) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, b.getModeloId());
            ps.setInt(2, b.getCooperativaId());

            if (b.getCapacidad() != null) {
                ps.setInt(3, b.getCapacidad());
            } else {
                ps.setNull(3, java.sql.Types.SMALLINT);
            }

            ps.setString(4, b.getEstado());

            if (b.getAnioFabricacion() != null) {
                ps.setInt(5, b.getAnioFabricacion());
            } else {
                ps.setNull(5, java.sql.Types.SMALLINT);
            }

            ps.setString(6, b.getPlaca());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Bus actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (PSQLException e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion cambiarEstado(String placa, String estado) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE_ESTADO)) {

            ps.setString(1, estado);
            ps.setString(2, placa);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Estado actualizado: " + estado);
            }
            return ResultadoOperacion.error("No se encontró el bus.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    private BusView mapBusView(ResultSet rs) throws SQLException {
        BusView b = new BusView();
        b.setPlaca(rs.getString("placa"));

        b.setModeloId(rs.getInt("modelo_id"));
        b.setModeloNombre(rs.getString("modelo_nombre"));

        int marcaId = rs.getInt("marca_id");
        b.setMarcaId(rs.wasNull() ? null : marcaId);
        b.setMarcaNombre(rs.getString("marca_nombre"));

        b.setCooperativaId(rs.getInt("cooperativa_id"));
        b.setCooperativaNombre(rs.getString("cooperativa_nombre"));

        int cap = rs.getInt("capacidad");
        b.setCapacidad(rs.wasNull() ? null : cap);

        b.setEstado(rs.getString("estado"));

        int anio = rs.getInt("anio_fabricacion");
        b.setAnioFabricacion(rs.wasNull() ? null : anio);

        return b;
    }

    private void crearAsientos(Connection con, String placa, int desde, int hasta) throws SQLException {
        String sqlAsiento = "INSERT INTO asientos (bus_id, numero, estado, ubicacion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sqlAsiento)) {
            for (int i = desde; i <= hasta; i++) {
                ps.setString(1, placa);
                ps.setInt(2, i);
                ps.setBoolean(3, true);
                ps.setString(4, "Dentro del bus");
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    public ResultadoOperacion insertarConAsientos(Bus b) {
        String sqlBus = "INSERT INTO bus (placa, modelo_id, cooperativa_id, capacidad, estado, anio_fabricacion) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement psBus = con.prepareStatement(sqlBus)) {

                psBus.setString(1, b.getPlaca());
                psBus.setInt(2, b.getModeloId());
                psBus.setInt(3, b.getCooperativaId());

                psBus.setInt(4, b.getCapacidad());

                psBus.setString(5, b.getEstado());

                psBus.setInt(6, b.getAnioFabricacion());

                psBus.executeUpdate();

                int cap = (b.getCapacidad() == null) ? 0 : b.getCapacidad();
                if (cap > 0) {
                    crearAsientos(con, b.getPlaca(), 1, cap);
                }

                con.commit();
                return ResultadoOperacion.ok("Bus registrado y asientos creados (" + cap + ").");

            } catch (Exception e) {
                con.rollback();
                return ResultadoOperacion.error("No se pudo guardar bus/asientos: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }

        } catch (Exception e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizarConAsientos(Bus b) {
        String sqlGetCap = "SELECT capacidad FROM bus WHERE placa = ?";
        String sqlUpdate = "UPDATE bus SET modelo_id=?, cooperativa_id=?, capacidad=?, anio_fabricacion=? WHERE placa=?";

        try (Connection con = ConexionBD.getConnection()) {
            con.setAutoCommit(false);

            try {
                short capActual = 0;
                try (PreparedStatement ps = con.prepareStatement(sqlGetCap)) {
                    ps.setString(1, b.getPlaca());
                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            con.rollback();
                            return ResultadoOperacion.error("No se encontró el bus.");
                        }
                        capActual = rs.getShort("capacidad");
                    }
                }

                int capNueva = (b.getCapacidad() == null) ? 0 : b.getCapacidad();

                if (capNueva < capActual) {
                    con.rollback();
                    return ResultadoOperacion.error("No se puede reducir la capacidad (" + capActual + " → " + capNueva + ").");
                }

                try (PreparedStatement psUp = con.prepareStatement(sqlUpdate)) {
                    psUp.setInt(1, b.getModeloId());
                    psUp.setInt(2, b.getCooperativaId());
                    psUp.setInt(3, capNueva);

                    psUp.setInt(4, b.getAnioFabricacion());

                    psUp.setString(5, b.getPlaca());

                    int rows = psUp.executeUpdate();
                    if (rows <= 0) {
                        con.rollback();
                        return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");
                    }
                }

                if (capNueva > capActual) {

                    short maxNumero = 0;
                    try (PreparedStatement psMax = con.prepareStatement("SELECT COALESCE(MAX(numero),0) FROM asientos WHERE bus_id = ?")) {
                        psMax.setString(1, b.getPlaca());
                        try (ResultSet rs = psMax.executeQuery()) {
                            if (rs.next()) {
                                maxNumero = rs.getShort(1);
                            }
                        }
                    }

                    short desde = (short) (maxNumero + 1);
                    if (desde <= capNueva) {
                        crearAsientos(con, b.getPlaca(), desde, capNueva);
                    }
                }

                con.commit();
                return ResultadoOperacion.ok("Bus actualizado correctamente.");

            } catch (Exception e) {
                con.rollback();
                return ResultadoOperacion.error("Error al actualizar: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }

        } catch (Exception e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        }
    }
    public java.util.List<com.transporte.gestion.modelo.Flota.Bus> listarBusesActivosPorChofer(int choferId) {
        java.util.List<com.transporte.gestion.modelo.Flota.Bus> lista = new java.util.ArrayList<>();
        String sql = """
            SELECT b.* FROM bus b
            INNER JOIN chofer_bus cb ON b.placa = cb.bus_id
            WHERE cb.chofer_id = ? 
              AND cb.estado = TRUE
              AND b.estado IN ('ACTIVO', 'MANTENIMIENTO')
        """;
        
        try (java.sql.Connection con = com.transporte.gestion.global.ConexionBD.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
             
            ps.setInt(1, choferId);
            java.sql.ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                com.transporte.gestion.modelo.Flota.Bus b = new com.transporte.gestion.modelo.Flota.Bus();
                b.setPlaca(rs.getString("placa"));
                b.setModeloId(rs.getInt("modelo_id"));
                b.setCooperativaId(rs.getInt("cooperativa_id"));
                b.setCapacidad(rs.getInt("capacidad"));
                b.setEstado(rs.getString("estado"));
                lista.add(b);
            }
        } catch (Exception e) {
            System.out.println("Error al listar buses del chofer: " + e.getMessage());
        }
        return lista;
    }
}
