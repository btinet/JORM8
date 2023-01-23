package core.model;

import core.global.Database;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QueryBuilder {

    private PreparedStatement statement;
    private Entity entity;

    private Boolean naturalCase;
    private Boolean ucFirst;
    private String alias;
    private final StringBuilder projection = new StringBuilder();

    private final StringBuilder insertion = new StringBuilder();
    private final StringBuilder insertData = new StringBuilder();

    private final StringBuilder table = new StringBuilder();

    private final StringBuilder condition = new StringBuilder();

    private final StringBuilder values = new StringBuilder();

    private final StringBuilder joins = new StringBuilder();

    private final StringBuilder orderBy = new StringBuilder();

    private final StringBuilder groupBy = new StringBuilder();

    private final StringBuilder having = new StringBuilder();

    private final StringBuilder query = new StringBuilder();



    private HashMap<Integer, String> stringParameters = new HashMap<>();
    private HashMap<Integer, Integer> integerParameters = new HashMap<>();


    public QueryBuilder (Boolean naturalCase, Boolean ucFirst, Entity entity,@Nullable String alias) {
        this.entity = entity;
        this.naturalCase = naturalCase;
        this.ucFirst = ucFirst;
        this.alias = alias;
        System.out.println();
        System.out.println("Neue SQL-Abfrage generieren:");
        System.out.println("============================");
    }

    private String generateSnakeTailString(String value)
    {
        String string = String.join("_", value.split("(?=\\p{Upper})")).toLowerCase();
        if(string.charAt(0) == '_'){
            return string.substring(0);
        }
        return string;
    }

    public QueryBuilder insertOrm(){
        this.insertion.append(this.getInsertColumns("id"));
        this.insertData.append(this.getInsertData("id"));
        return this;
    }

    public QueryBuilder insertOrm(String primaryKey){
        this.insertion.append(this.getInsertColumns(primaryKey));
        this.insertData.append(this.getInsertData(primaryKey));
        return this;
    }

    private String getInsertData(String primaryKey){
        Field[] fields = this.entity.getClass().getDeclaredFields();
        StringBuilder columnBuilder = new StringBuilder();
        int i = 0;
        for (Field field : fields) {
            if(field.getModifiers() == Modifier.PROTECTED && !field.getName().equals(primaryKey)){
                if(i != 0) {
                    columnBuilder.append(",");
                }
                if(this.naturalCase){
                    try {
                        field.setAccessible(true);
                        columnBuilder.append("'").append(field.get(this.entity)).append("'");
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                i++;
            }
        }
        if(columnBuilder.length() > 0){
            return columnBuilder.toString();
        }
        return "";
    }

    private String getInsertColumns(String primaryKey){
        Field[] fields = this.entity.getClass().getDeclaredFields();
        StringBuilder columnBuilder = new StringBuilder();
        int i = 0;
        for (Field field : fields) {
            if(field.getModifiers() == Modifier.PROTECTED && !field.getName().equals(primaryKey)){
                if(i != 0) {
                    columnBuilder.append(",");
                }
                if(this.naturalCase){
                    columnBuilder.append(field.getName());
                } else {
                    columnBuilder.append(this.generateSnakeTailString(field.getName()));
                }
                i++;
            }
        }
        if(columnBuilder.length() > 0){
            return columnBuilder.toString();
        }
        return "";
    }

    private String getColumns(){
        Field[] fields = this.entity.getClass().getDeclaredFields();
        StringBuilder columnBuilder = new StringBuilder();
        int i = 1;
        for (Field field : fields) {
            if(field.getModifiers() == Modifier.PROTECTED){
                if(this.naturalCase){
                    columnBuilder.append(field.getName());
                } else {
                    columnBuilder
                            .append(this.generateSnakeTailString(field.getName()))
                            .append(" AS ")
                            .append(field.getName())
                    ;
                }
                ;
                if(i < fields.length) {
                    columnBuilder.append(", ");
                } else {
                    columnBuilder.append(" ");
                }
                i++;
            }
        }
        if(columnBuilder.length() > 0){
            return columnBuilder.toString();
        }
        return "";
    }

    public QueryBuilder select(String fields) {
        this.projection.append(fields);
        return this;
    }

    public QueryBuilder selectOrm() {
        this.projection.append(this.getColumns());
        return this;
    }

    public QueryBuilder insertInto(){
        if(this.naturalCase){
            this.table.append(this.entity.getClass().getSimpleName());
        } else {
            this.query.append(this.generateSnakeTailString(this.entity.getClass().getSimpleName()));
        }
        return this;
    }

    public QueryBuilder innerJoin(String table,String key){
        this.joins.append(" INNER JOIN ").append(table).append(" USING (").append(key).append(")");
        return this;
    }

    public QueryBuilder innerJoin(String table,String left, String right){
        this.joins.append(" INNER JOIN ")
                .append(table)
                .append(" ON (")
                .append(left)
                .append(" = ")
                .append(right)
                .append(")")
        ;
        return this;
    }

    public int addValues(String primaryKey){
        if(0 != this.values.length()){
            this.values.append(" SET ");
        }
        int i = 1;
        for (Field f: this.entity.getClass().getDeclaredFields()){
            if(f.getModifiers() == Modifier.PROTECTED && !f.getName().equals(primaryKey)){
                String fName;
                if(i > 1) {
                    this.values.append(", ");
                }
                if(this.naturalCase){
                    fName = f.getName();
                } else {
                    fName = this.generateSnakeTailString(f.getName());
                }
                try {
                    f.setAccessible(true);
                    this.values.append(fName);
                    if ("null".equals(f.get(this.entity))) {
                        this.values.append("IS NULL");
                    } else {
                        this.values.append("=?");
                        this.setParameter(i, f.get(this.entity).toString());
                        i++;
                    }
                    f.setAccessible(false);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return i;
    }

    public QueryBuilder andWhere(String condition){
        if(0 != this.condition.length()){
            this.condition.append(" AND ");
        }
        this.condition.append(condition);
        return this;
    }

    public QueryBuilder orWhere(String condition){
        if(0 != this.condition.length()){
            this.condition.append(" OR ");
        }
        this.condition.append(condition);
        return this;
    }

    public QueryBuilder groupBy(String field){
        if(0 != this.groupBy.length()){
            this.groupBy.append(", ");
        }
        this.groupBy.append(field);
        return this;
    }

    public QueryBuilder andHaving(String condition){
        if(0 != this.having.length()){
            this.having.append(" AND ");
        }
        this.having.append(condition);
        return this;
    }

    public QueryBuilder orHaving(String condition){
        if(0 != this.having.length()){
            this.having.append(" OR ");
        }
        this.having.append(condition);
        return this;
    }

    public QueryBuilder orderBy(HashMap<String, String> orderBy){
        int i = 1;
        for(Map.Entry<String, String> entry: orderBy.entrySet()){
            this.orderBy.append(entry.getKey()).append(" ").append(entry.getValue().toUpperCase());
            if(i < orderBy.size()){
                this.orderBy.append(", ");
            }
            i++;
        }
        return this;
    }

    public QueryBuilder setParameter(Integer parameter, int value){
        this.integerParameters.put(parameter, value);
        System.out.println(parameter + ". ? ist: " + value);
        return this;
    }

    public QueryBuilder setParameter(Integer parameter, String value){
        this.stringParameters.put(parameter, value);
        System.out.println(parameter + ". ? ist: " + value);
        return this;
    }

    public QueryBuilder getUpdateQuery() throws SQLException {
        this.query.append("UPDATE ");
        if(this.naturalCase){
            this.query.append(this.entity.getClass().getSimpleName());
        } else {
            this.query.append(this.generateSnakeTailString(this.entity.getClass().getSimpleName()));
        }

        this.query.append(" SET ");

        this.query.append(this.values);
        if(0 != this.condition.length()){
            this.query.append(" WHERE ").append(this.condition);
        }

        try {
            this.statement = Database.getConnection().prepareStatement(this.query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(!this.stringParameters.isEmpty()){
            for(Map.Entry<Integer, String> entry: this.stringParameters.entrySet()){
                if(!Objects.equals(entry.getValue(), "null")){
                    this.statement.setString(entry.getKey(),entry.getValue());
                }
            }
        }

        if(!this.integerParameters.isEmpty()){
            for(Map.Entry<Integer, Integer> entry: this.integerParameters.entrySet()){
                if(!Objects.equals(entry.getValue(), 0)){
                    this.statement.setInt(entry.getKey(),entry.getValue());
                }
            }
        }

        System.out.println("UPDATE Query: " + this.query);
        return this;
    }

    public QueryBuilder getRemoveQuery(){
        this.query.append("DELETE FROM ");

        if(this.naturalCase){
            this.query.append(this.entity.getClass().getSimpleName());
        } else {
            this.query.append(this.generateSnakeTailString(this.entity.getClass().getSimpleName()));
        }

        if(0 != this.condition.length()){
            this.query.append(" WHERE ").append(this.condition);
        }

        try {
            this.statement = Database.getConnection().prepareStatement(this.query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(!this.stringParameters.isEmpty()){
            for(Map.Entry<Integer, String> entry: this.stringParameters.entrySet()){
                if(!Objects.equals(entry.getValue(), "null")){
                    try {
                        this.statement.setString(entry.getKey(),entry.getValue());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        if(!this.integerParameters.isEmpty()){
            for(Map.Entry<Integer, Integer> entry: this.integerParameters.entrySet()){
                if(!Objects.equals(entry.getValue(), 0)){
                    try {
                        this.statement.setInt(entry.getKey(),entry.getValue());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        System.out.println("DELETE Query: " + this.query);
        return this;
    }

    public QueryBuilder getInsertQuery() throws SQLException {
        // TODO: Parameter nicht in das SQL-Statement aufnehmen!
        this.query.append("INSERT INTO ");
        if(this.naturalCase){
            this.query.append(this.entity.getClass().getSimpleName());
        } else {
            this.query.append(this.generateSnakeTailString(this.entity.getClass().getSimpleName()));
        }
        this.query.append(" ( ").append(this.insertion).append(" ) ");
        this.query.append(" VALUES ");
        this.query.append(" ( ").append(this.insertData).append(" ) ");

        try {
            this.statement = Database.getConnection().prepareStatement(this.query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(!this.stringParameters.isEmpty()){
            for(Map.Entry<Integer, String> entry: this.stringParameters.entrySet()){
                if(!Objects.equals(entry.getValue(), "null")){
                    this.statement.setString(entry.getKey(),entry.getValue());
                }
            }
        }

        if(!this.integerParameters.isEmpty()){
            for(Map.Entry<Integer, Integer> entry: this.integerParameters.entrySet()){
                if(!Objects.equals(entry.getValue(), 0)){
                    this.statement.setInt(entry.getKey(),entry.getValue());
                }
            }
        }

        System.out.println("INSERT Query: " + this.query);
        return this;
    }

    public QueryBuilder getQuery() throws SQLException {
        this.query.append("SELECT ");

        if(0 != this.projection.length()){
            this.query.append(this.projection);
        }

        if(this.naturalCase){
            this.query.append(" FROM ").append(this.entity.getClass().getSimpleName());
        } else {
            this.query.append(" FROM ").append(this.generateSnakeTailString(this.entity.getClass().getSimpleName()));
        }

        if(null != this.alias){
            this.query.append(" ").append(this.alias);
        }

        if(0 != this.joins.length()){
            this.query.append(this.joins);
        }

        if(0 != this.condition.length()){
            this.query.append(" WHERE ").append(this.condition);
        }

        if(0 != this.groupBy.length()){
            this.query.append(" GROUP BY ").append(this.groupBy);
        }

        if(0 != this.having.length()){
            this.query.append(" HAVING ").append(this.having);
        }

        if(0 != this.orderBy.length()){
            this.query.append(" ORDER BY ").append(this.orderBy);
        }

        try {
            this.statement = Database.getConnection().prepareStatement(this.query.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(!this.stringParameters.isEmpty()){
            for(Map.Entry<Integer, String> entry: this.stringParameters.entrySet()){
                if(!Objects.equals(entry.getValue(), "null")){
                    this.statement.setString(entry.getKey(),entry.getValue());
                }
            }
        }

        if(!this.integerParameters.isEmpty()){
            for(Map.Entry<Integer, Integer> entry: this.integerParameters.entrySet()){
                if(!Objects.equals(entry.getValue(), 0)){
                    this.statement.setInt(entry.getKey(),entry.getValue());
                }
            }
        }
        System.out.println("SELECT Query: " + this.query);
        return this;
    }

    public void getPersistResult() {
        try {
            Boolean done = this.statement.execute();
            System.out.println("Anzahl betroffener Tupel: " + this.statement.getUpdateCount());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.err.println("Fehlercode: " + e.getSQLState());
        }

    }

    public ArrayList<HashMap<String, String>> getListResult() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        this.statement.executeQuery();
        ResultSet result = this.statement.getResultSet();
        ResultSetMetaData metaData = result.getMetaData();

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        System.err.println(metaData.getColumnCount() + " Spalten");
        while (result.next()) {
            if(!result.wasNull()){
                HashMap<String, String> row = new HashMap<>(metaData.getColumnCount());
                for (int i = 1; i <= metaData.getColumnCount();i++) {
                    row.put(metaData.getColumnLabel(i),result.getObject(i).toString());
                }
                list.add(row);
            }
        }
        return list;
    }

    public Entity getOneOrNullResult() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        this.statement.setMaxRows(1);
        this.statement.executeQuery();
        ResultSet result;
        result = this.statement.getResultSet();
        ResultSetMetaData metaData = result.getMetaData();

        Entity object = null;

        while (result.next()) {

            object = this.entity.getClass().getConstructor().newInstance();

            for (Field field : this.entity.getClass().getDeclaredFields()) {
                if (field.getModifiers() == Modifier.PROTECTED) {
                    String fieldName = "";



                    if(!this.naturalCase){
                        fieldName += this.generateSnakeTailString(field.getName());
                    } else {
                        fieldName += field.getName();
                    }


                    field.setAccessible(true);
                    if(field.getType().getSimpleName().equals("int")){
                        field.set(object, result.getInt(fieldName));
                    }
                    if(field.getType().getSimpleName().equals("Integer")){
                        field.set(object, result.getInt(fieldName));
                    }
                    if(field.getType().getSimpleName().equals("String")){
                        field.set(object, result.getString(fieldName));
                    }
                    if(field.getType().getSimpleName().equals("Boolean")){
                        field.set(object, (1 == result.getInt(fieldName)));
                    }
                    field.setAccessible(false);



                }
            }
        }
        return object;
    }

    public ArrayList<Entity> getResult() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        this.statement.executeQuery();
        ResultSet result;
        result = this.statement.getResultSet();
        ArrayList<Entity> list = new ArrayList<>();

        Entity object;

        while (result.next()) {

            object = this.entity.getClass().getConstructor().newInstance();

            for (Field field : this.entity.getClass().getDeclaredFields()) {
                if (field.getModifiers() == Modifier.PROTECTED) {
                    String fieldName = "";
                    if(!this.naturalCase){
                        fieldName = this.generateSnakeTailString(field.getName());
                    } else {
                        fieldName = field.getName();
                    }

                    field.setAccessible(true);
                    if(field.getType().getSimpleName().equals("int")){
                        field.set(object, result.getInt(fieldName));
                    }
                    if(field.getType().getSimpleName().equals("Integer")){
                        field.set(object, result.getInt(fieldName));
                    }
                    if(field.getType().getSimpleName().equals("String")){
                        field.set(object, result.getString(fieldName));
                    }
                    if(field.getType().getSimpleName().equals("Boolean")){
                        field.set(object, (1 == result.getInt(fieldName)));
                    }
                    field.setAccessible(false);

                }
            }
            list.add(object);
        }
        return list;
    }

}
