package dev.St4lV.RPGEcoSimulationApp.Utils;

public class Textures {
    public static String PathToTextures = "/assets/";
    public String type;
    public String mod_id;
    public String texture;

    public String getTexturePath(String mod_texture, String type){
        this.type = type;
        String[] parts = mod_texture.split(":");
        this.mod_id = parts[0];
        this.texture = parts[1];
        return PathToTextures + mod_id + "/textures/" + type + "/" + texture+".png";
    }
}