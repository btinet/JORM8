package core.model;

import com.sun.istack.internal.Nullable;
import core.global.Database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class QueryBuilder {

    private PreparedStatement statement;
    private Entity entity;

    private Boolean naturalCase;
    private Boolean ucFirst;
    private @Nullable String alias;
    private final StringBuilder projection = new StringBuilder();

    private final StringBuilder condition = new StringBuilder();

    private final StringBuilder query = new StringBuilder();

    private HashMap<Integer, String> stringParameters = new HashMap<>();
    private HashMap<Integer, Integer> integerParameters = new HashMap<>();


    public QueryBuilder (Boolean naturalCase, Boolean ucFirst, Entity entity, @Nullable String alias) {
        this.entity = entity;
        this.naturalCase = naturalCase;
        this.ucFirst = ucFirst;
        this.alias = alias;
    }

    private String generateSnakeTailString(String value)
    {
        String string = String.join("_", value.split("(?=\\p{Upper})")).toLowerCase();
        if(string.charAt(0) == '_'){
            return string.substring(0);
        }
        return string;
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

    protected QueryBuilder select(String fields) {
        this.projection.append(fields);
        return this;
    }

    protected QueryBuilder selectOrm() {
        this.projection.append(this.getColumns());
        return this;
    }

    protected QueryBuilder andWhere(String condition){
        if(0 != this.condition.length()){
            this.condition.append(" AND ");
        }
        this.condition.append(condition);
        return this;
    }

    protected QueryBuilder orWhere(String condition){
        if(0 != this.condition.length()){
            this.condition.append(" OR ");
        }
        this.condition.append(condition);
        return this;
    }

    protected QueryBuilder setParameter(Integer parameter, int value){
        this.integerParameters.put(parameter, value);
        return this;
    }

    protected QueryBuilder setParameter(Integer parameter, String value){
        this.stringParameters.put(parameter, value);
        return this;
    }

    protected QueryBuilder getQuery() throws SQLException {
        this.query.append("SELECT ");

        if(0 != this.projection.length()){
            this.query.append(this.projection);
        }

        if(this.naturalCase){
            this.query.append(" FROM ").append(this.entity.getClass().getSimpleName());
        } else {
            this.query.append(" FROM ").append(this.generateSnakeTailString(this.entity.getClass().getSimpleName()));
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
        System.out.println("Query: " + this.query);
        return this;
    }

    protected Entity getOnOrNullResult() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        this.statement.setMaxRows(1);
        this.statement.executeQuery();
        ResultSet result;
        result = this.statement.getResultSet();

        Entity object = null;

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
        }
        return object;
    }

    protected ArrayList<Entity> getResult() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        this.statement.executeQuery();
        ResultSet result = null;
        result = this.statement.getResultSet();
        ArrayList<Entity> list = new ArrayList<>();

        Entity object = null;

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
