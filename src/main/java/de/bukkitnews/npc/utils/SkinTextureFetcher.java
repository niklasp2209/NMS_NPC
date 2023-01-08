package de.bukkitnews.npc.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class SkinTextureFetcher {
    /**
     * Parses the uuid of a player by its name.
     *
     * @param name The name of the player whose uuid gets parsed.
     * @return a Future with a String.
     */
    private static CompletableFuture<String> parseUUID(String name) {
        CompletableFuture<String> future = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {

                HttpURLConnection connection = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + name).openConnection();
                connection.setRequestMethod("GET");

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                StringBuffer respone = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    respone.append(line);
                }

                bufferedReader.close();

                JSONObject jsonObject = new JSONObject(respone.toString());
                String uuid = jsonObject.getString("id");
                future.complete(uuid);

            } catch (IOException e) {
                e.printStackTrace();
                future.complete(null);
            }
        });

        return future;
    }

    /**
     * Parses the skin textures of a player by its uuid.
     *
     * @param uuid The uuid of the player whose textures gets parsed.
     * @return a Future with a StringArray.
     */
    private static CompletableFuture<String[]> parseTextures(String uuid) {
        CompletableFuture<String[]> future = new CompletableFuture<>();
        CompletableFuture.runAsync(() -> {
            try {

                HttpURLConnection connection = (HttpURLConnection) new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid + "?unsigned=false").openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                StringBuffer respone = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    respone.append(line);
                }

                reader.close();

                // Get respone as JSONObject
                JSONObject jsonObject = new JSONObject(respone.toString());
                /* Get properties array which contains the textures. */
                JSONArray properties = jsonObject.getJSONArray("properties");

                for (int i = 0; i < properties.length(); i++) {
                    JSONObject property = (JSONObject) properties.get(i);
                    future.complete(new String[]{property.getString("value"), property.getString("signature")});
                }

            } catch (IOException e) {
                e.printStackTrace();
                future.complete(null);
            }
        });

        return future;
    }

    public static void setSkin(GameProfile gameProfile){
        gameProfile.getProperties().removeAll("textures");

        parseUUID(gameProfile.getName()).thenAccept(uuid -> {
            if(uuid == null){
                return;
            }

            parseTextures(uuid).thenAccept(textures ->{
               if(textures == null){
                   return;
               }

               gameProfile.getProperties().put("textures", new Property("textures", textures[0], textures[1]));
            });
        });
    }
}
