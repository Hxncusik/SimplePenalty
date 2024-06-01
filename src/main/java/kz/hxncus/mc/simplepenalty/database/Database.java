package kz.hxncus.mc.simplepenalty.database;

import lombok.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jooq.DSLContext;
import org.jooq.QueryPart;
import org.jooq.Record;
import org.jooq.Result;

import java.sql.Connection;

public interface Database {
    void createConnection();
    Database.Type getType();
    Connection getConnection();
    void closeConnection();
    DSLContext getDSLContext(@NonNull Connection connection);
    @NonNull
    Result<Record> fetch(@NonNull String sql);
    @NonNull Result<Record> fetch(@NonNull String sql, Object @NonNull ... bindings);
    @NonNull Result<Record> fetch(@NonNull String sql, QueryPart @NonNull ... parts);
    @Nullable
    Record fetchOne(@NonNull String sql);
    @Nullable Record fetchOne(@NonNull String sql, Object @NonNull ... bindings);
    @Nullable Record fetchOne(@NonNull String sql, QueryPart @NonNull ... parts);
    int execute(@NonNull String sql);
    int execute(@NonNull String sql, Object @NonNull ... bindings);
    int execute(@NonNull String sql, QueryPart @NonNull ... parts);
    int getNewId(@NonNull String table);
    void reload();

    enum Type {
        SQLITE(SQLite.class), MYSQL(MySQL.class), MARIADB(MariaDB.class);
        final Class<? extends Database> clazz;
        Type(Class<? extends Database> clazz) {
            this.clazz = clazz;
        }
    }
}
