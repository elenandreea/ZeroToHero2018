package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EntityUtils {

    public EntityUtils() throws UnsupportedOperationException{

    }

    public static String getTableName(Class entity){
        return ((Table)entity.getAnnotation(Table.class)).name();
    }

    public static ArrayList<ColumnInfo> getColumns(Class entity){
        Field [] fields = (entity.getDeclaredFields());
        ArrayList<ColumnInfo> columnInfoList = new ArrayList<ColumnInfo>();
        for(int i = 0; i < fields.length; i++){
            ColumnInfo column = new ColumnInfo();
            if(fields[i].isAnnotationPresent(Column.class)){
                column.setColumnName(fields[i].getName());
                column.setDbColumnName(fields[i].getAnnotation(Column.class).name());
                column.setColumnType(fields[i].getType());
                column.setId(false);
                columnInfoList.add(column);
            }
            if(fields[i].isAnnotationPresent(Id.class)){
                column.setColumnName(fields[i].getName());
                column.setDbColumnName(fields[i].getAnnotation(Id.class).name());
                column.setColumnType(fields[i].getType());
                column.setId(true);
                columnInfoList.add(column);
            }
        }
        return columnInfoList;
    }

    public static Object castFromSqlType(Object value, Class wantedType){
        if(value instanceof BigDecimal && wantedType.equals(Integer.TYPE))
            return (Integer) value;
        if(value instanceof BigDecimal && wantedType.equals(Long.TYPE))
            return (Long) value;
        if(value instanceof BigDecimal && wantedType.equals(Float.TYPE))
            return (Float) value;
        if(value instanceof BigDecimal && wantedType.equals(Double.TYPE))
            return (Double) value;
        return value;
    }

    public static List getFieldsByAnnotations(Class clazz, Class annotation){
        Field [] fields = clazz.getDeclaredFields();
        ArrayList fieldList = new ArrayList();
        for(int i = 0; i < fields.length; i++){
            if(fields[i].isAnnotationPresent(annotation)){
                fieldList.add(fields[i]);
            }
        }
        return fieldList;
    }

    public static Object getSqlValue(Object object){
        if(object.getClass().isAnnotationPresent(Table.class)){
            Field [] fields = object.getClass().getDeclaredFields();
            for(int i = 0; i < fields.length; i++){
                if(fields[i].isAnnotationPresent(Id.class)) {
                    fields[i].setAccessible(true);
                    return fields[i];
                }
            }
        }
        return object;
    }

}