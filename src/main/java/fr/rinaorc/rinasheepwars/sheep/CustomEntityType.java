package fr.rinaorc.rinasheepwars.sheep;

import net.minecraft.server.v1_8_R3.EntityInsentient;
import net.minecraft.server.v1_8_R3.EntitySheep;
import net.minecraft.server.v1_8_R3.EntityTypes;
import org.bukkit.entity.EntityType;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum CustomEntityType {

    SHEEP("Sheep", 91, EntityType.SHEEP, EntitySheep.class, CustomSheep.class);

    private String name;
    private int id;
    private EntityType entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;

    private CustomEntityType(String name, int id, EntityType entityType, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass) {
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Class<? extends EntityInsentient> getNMSClass() {
        return nmsClass;
    }

    public Class<? extends EntityInsentient> getCustomClass() {
        return customClass;
    }

    // "aw", "at", "au", "av"

    public static void registerEntity(String name, int id, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass){
        try {

            List<Map<?, ?>> dataMap = new ArrayList<>();
            for (Field f : EntityTypes.class.getDeclaredFields()){
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())){
                    f.setAccessible(true);
                    dataMap.add((Map<?, ?>) f.get(null));
                }
            }

            if (dataMap.get(2).containsKey(id)){
                dataMap.get(0).remove(name);
                dataMap.get(2).remove(id);
            }

            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, customClass, name, id);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object getPrivateStatic(Class clazz, String f)throws Exception{
        Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);
        return field.get(null);
    }

   /* private static void a(Class<?> paramClass, String paramString, int paramInt){
        try {
            ((Map)getPrivateStatic(EntityTypes.class, "c")).put(paramString, paramClass);
            ((Map)getPrivateStatic(EntityTypes.class, "d")).put(paramClass, paramString);
            ((Map)getPrivateStatic(EntityTypes.class, "e")).put(Integer.valueOf(paramInt), paramClass);
            ((Map)getPrivateStatic(EntityTypes.class, "f")).put(paramClass, Integer.valueOf(paramInt));
            ((Map)getPrivateStatic(EntityTypes.class, "g")).put(paramString, Integer.valueOf(paramInt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
