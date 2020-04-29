package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import world.GameMap;
import world.customgamemap.CustomGameMapData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

import entities.EntityType;

import static com.badlogic.gdx.net.HttpRequestBuilder.json;

public class EntityLoader {

    private static Json json = new Json();

    public static ArrayList<Entity> loadEntities(String id, GameMap map, ArrayList<Entity> currentEntities) {
        Gdx.files.local("desktop/maps/").file().mkdirs();
        FileHandle file = Gdx.files.local("desktop/maps/" + id + ".entities");
        if (file.exists()) {
            EntitySnapshot[] snapshots = json.fromJson(EntitySnapshot[].class, file.readString());
            ArrayList<Entity> entities = new ArrayList<Entity>();
            for (EntitySnapshot snapshot : snapshots) {
                entities.add( EntityType.createEntityUsingSnapshot(snapshot, map));
            }
            return entities;
        } else {
            saveEntities(id, currentEntities);
            return currentEntities;
        }
    }

    public static void saveEntities (String id, ArrayList<Entity> entities) {
        ArrayList<EntitySnapshot> snapshots = new ArrayList<EntitySnapshot>();
        for (Entity entity : entities) {
            snapshots.add(entity.getSaveSnapShot());
        }
        Gdx.files.local("desktop/maps/").file().mkdirs();
        FileHandle file = Gdx.files.local("desktop/maps/" + id + ".entities");
        file.writeString(json.prettyPrint(snapshots), false);
    }
}
