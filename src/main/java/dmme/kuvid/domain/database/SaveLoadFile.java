package dmme.kuvid.domain.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Molecules.*;
import dmme.kuvid.domain.GameObjects.Powerup.*;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.lib.types.*;
import dmme.kuvid.ui.Factory;
import dmme.kuvid.utils.PathHandler;
import dmme.kuvid.utils.observer.Observable;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class SaveLoadFile extends Observable implements SaveMode {

    @Override
    public boolean saveGame() {
        save(ObjectType.ATOM, AtomType.ALPHA, "atom_alpha");
        save(ObjectType.ATOM, AtomType.BETA, "atom_beta");
        save(ObjectType.ATOM, AtomType.GAMMA, "atom_gamma");
        save(ObjectType.ATOM, AtomType.SIGMA, "atom_sigma");

        save(ObjectType.MOLECULE, MoleculeType.ALPHA, "molecule_alpha");
        save(ObjectType.MOLECULE, MoleculeType.BETA, "molecule_beta");
        save(ObjectType.MOLECULE, MoleculeType.GAMMA, "molecule_gamma");
        save(ObjectType.MOLECULE, MoleculeType.SIGMA, "molecule_sigma");

        save(ObjectType.REACTION_BLOCKER, ReactionType.ALPHA_R, "reaction_alpha");
        save(ObjectType.REACTION_BLOCKER, ReactionType.BETA_R, "reaction_beta");
        save(ObjectType.REACTION_BLOCKER, ReactionType.GAMMA_R, "reaction_gamma");
        save(ObjectType.REACTION_BLOCKER, ReactionType.SIGMA_R, "reaction_sigma");
        
        save("shootedAtom");
        save("shootedPower");

        save("power_arsenal_alpha");
        save("power_arsenal_beta");
        save("power_arsenal_gamma");
        save("power_arsenal_sigma");

        return false;
    }

    @Override
    public boolean loadGame() {
        load("atom_alpha");
        load("atom_beta");
        load("atom_gamma");
        load("atom_sigma");
        load("shootedAtom");
        load("shootedPower");

        load("power_arsenal_alpha");
        load("power_arsenal_beta");
        load("power_arsenal_gamma");
        load("power_arsenal_sigma");

        publishPropertyEvent("updateAtom",null,null);

        /*
        load("molecule_alpha");
        load("molecule_beta");
        load("molecule_gamma");
        load("molecule_sigma");
         */

        return false;
    }


    public String save(ObjectType objectType, Enum<?> subType, String name) {
        /*
        @requires: this not null, objectType not null, subType not null, name not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String and saves corresponding String as a ".json" file
         */
        PathHandler pathHandler = PathHandler.getInstance();

        String saveFolder = "snapshots";
        String saveFolderPath = pathHandler.makePath(".",saveFolder,"");
        File f = new File(saveFolderPath);

        if (!f.exists()) {
            f.mkdir();
        }

        String jsonString = "";
        try {
            FileWriter fileWriter = new FileWriter(pathHandler.makePath(f.getAbsolutePath(), name)+".json");
            fileWriter.write(gameObjectToJson(objectType, subType));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("SERIALIZED");
        //System.out.println("Alpha Atom Info: " + jsonString);
        return jsonString;
    }
    
    public String save(String name) {
        /*
        @requires: this not null, objectType not null, subType not null, name not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String and saves corresponding String as a ".json" file
         */
        PathHandler pathHandler = PathHandler.getInstance();

        String saveFolder = "snapshots";
        String saveFolderPath = pathHandler.makePath(".",saveFolder,"");
        File f = new File(saveFolderPath);

        if (!f.exists()) {
            f.mkdir();
        }

        String jsonString = "";
        try {
            FileWriter fileWriter = new FileWriter(pathHandler.makePath(f.getAbsolutePath(), name)+".json");
            switch (name) {
                case "shootedAtom":
                    fileWriter.write(this.shootedAtomToJson());
                    break;
                case "shootedPower":
                    fileWriter.write(this.shootedPowerToJson());
                    break;
                case "power_arsenal_alpha":
                    fileWriter.write(this.powerArsenalToJson(PowerType.ALPHA_B));
                    break;
                case "power_arsenal_beta":
                    fileWriter.write(this.powerArsenalToJson(PowerType.BETA_B));
                    break;
                case "power_arsenal_gamma":
                    fileWriter.write(this.powerArsenalToJson(PowerType.GAMMA_B));
                    break;
                case "power_arsenal_sigma":
                    fileWriter.write(this.powerArsenalToJson(PowerType.SIGMA_B));
                    break;
            }

            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("SERIALIZED");
        //System.out.println("Alpha Atom Info: " + jsonString);
        return jsonString;
    }

    public String gameObjectToJson(ObjectType objectType, Enum<?> subType) {
        /*
        @requires: this not null, objectType not null, subType not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
        return new GsonBuilder().setPrettyPrinting().create()
                .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)));
    }
    
    public String shootedAtomToJson() {
        /*
        @requires: this not null, objectType not null, subType not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
    	Gson son = new GsonBuilder().registerTypeAdapter(Atom.class, new AbstractAtomAdapter()).setPrettyPrinting().create();
    	Type type = new TypeToken<List<Atom>>(){}.getType();
        return son.toJson(KUVidGame.getShootedAtom(),type);
    }
    
    public String shootedPowerToJson() {
        /*
        @requires: this not null, objectType not null, subType not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
    	Gson son = new GsonBuilder().registerTypeAdapter(PowerUp.class, new AbstractPowerAdapter()).setPrettyPrinting().create();
    	Type type = new TypeToken<List<PowerUp>>(){}.getType();
        return son.toJson(KUVidGame.getShootedPower(),type);
    }
    
    public String powerArsenalToJson(PowerType powerType) {
        /*
        @requires: this not null, objectType not null, subType not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
        return new GsonBuilder().setPrettyPrinting().create()
                .toJson(KUVidGame.getPowerArsenal().get(powerType));
    }

    public String load(String fileName) {
        /*
        @requires: this not null, fileName not null
        @effects: finds corresponding json file, creates an object from list
        and returns the object as a JSON string.
         */
        PathHandler pathHandler = PathHandler.getInstance();

        String saveFolder = "snapshots";
        String saveFolderPath = pathHandler.makePath(".",saveFolder,"");
        File f = new File(saveFolderPath);
        String outputData = "adsf";

        if (!f.exists()) {
            return null;
        }

        List<GameObject> list;
        List<PowerUp> powerUpList;
        try {
            outputData = new String(Files.readAllBytes(Paths.get(pathHandler.makePath(f.getAbsolutePath(), fileName) + ".json")));

            switch (fileName) {
                case "atom_alpha":
                    list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.ALPHA);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "atom_beta":
                    list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.BETA);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "atom_gamma":
                    list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.GAMMA);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "atom_sigma":
                    list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.SIGMA);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "molecule_alpha":
                    list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.ALPHA);
                    //KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA), list);
                    break;
                case "molecule_beta":
                    list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.BETA);
                    //KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), list);
                    break;
                case "molecule_gamma":
                    list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.GAMMA);
                    //KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), list);
                    break;
                case "molecule_sigma":
                    list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.SIGMA);
                    //KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), list);
                    break;
                case "shootedAtom":
                    list = this.jsonToList(outputData, ObjectType.ATOM);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "shootedPower":
                    list = this.jsonToList(outputData, ObjectType.POWER_UP);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_arsenal_alpha":
                    powerUpList = jsonToPowerUp(outputData, PowerType.ALPHA_B);
                    //KUVidGame.getPowerArsenal().put(PowerType.ALPHA_B, powerUpList);
                    powerUpList.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_arsenal_beta":
                    powerUpList = jsonToPowerUp(outputData, PowerType.BETA_B);
                    //KUVidGame.getPowerArsenal().put(PowerType.BETA_B, powerUpList);
                    powerUpList.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_arsenal_gamma":
                    powerUpList = jsonToPowerUp(outputData, PowerType.GAMMA_B);
                    //KUVidGame.getPowerArsenal().put(PowerType.GAMMA_B, powerUpList);
                    powerUpList.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_arsenal_sigma":
                    powerUpList = jsonToPowerUp(outputData, PowerType.SIGMA_B);
                    //KUVidGame.getPowerArsenal().put(PowerType.SIGMA_B, powerUpList);
                    powerUpList.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
            }

            //System.out.println(outputData);
            //System.out.println(list.get(0).getType());
            //System.out.println(list.get(0).getSubType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputData;
    }



    public List<GameObject> jsonToList(String jsonString,ObjectType t) {
        /*
        @requires: this not null, objectType not null, subType not null, jsonString not null
        @effects: Generates a List object from properties of the given jsonString.
            Returns generated object as a GameObject List.
         */
    	Gson son=null;
    	Type type=null;
    	if(t.equals(ObjectType.ATOM)) {
	    	son = new GsonBuilder().registerTypeAdapter(Atom.class, new AbstractAtomAdapter()).setPrettyPrinting().create();
	    	type = new TypeToken<List<Atom>>(){}.getType();
    	}else if(t.equals(ObjectType.POWER_UP)){
    		son = new GsonBuilder().registerTypeAdapter(PowerUp.class, new AbstractPowerAdapter()).setPrettyPrinting().create();
	    	type = new TypeToken<List<PowerUp>>(){}.getType();
    	}
      
        return son.fromJson(jsonString, type);
    }
    
    public List<GameObject> jsonToGameObject(String jsonString, ObjectType objectType, Enum<?> subType) {
        /*
        @requires: this not null, objectType not null, subType not null, jsonString not null
        @effects: Generates a List object from properties of the given jsonString.
            Returns generated object as a GameObject List.
         */
        Type type = null;
        if (objectType == ObjectType.ATOM) {
            if (subType == AtomType.ALPHA) {
                type = new TypeToken<List<AlphaAtom>>(){}.getType();
            } else if (subType == AtomType.BETA) {
                type = new TypeToken<List<BetaAtom>>(){}.getType();
            } else if (subType == AtomType.GAMMA) {
                type = new TypeToken<List<GamaAtom>>(){}.getType();
            } else if (subType == AtomType.SIGMA) {
                type = new TypeToken<List<SigmaAtom>>(){}.getType();
            }
        } else if (objectType == ObjectType.MOLECULE) {
            if (subType == MoleculeType.ALPHA) {
                type = new TypeToken<List<AlphaMolecule>>(){}.getType();
            } else if (subType == MoleculeType.BETA) {
                type = new TypeToken<List<BetaMolecule>>(){}.getType();
            } else if (subType == MoleculeType.GAMMA) {
                type = new TypeToken<List<GamaMolecule>>(){}.getType();
            } else if (subType == MoleculeType.SIGMA) {
                type = new TypeToken<List<SigmaMolecule>>(){}.getType();
            }
        }
        return new GsonBuilder().setPrettyPrinting().create().fromJson(jsonString, type);
    }

    private List<PowerUp> jsonToPowerUp(String jsonString, PowerType powerType) {
        Type type = null;
        if (powerType == PowerType.ALPHA_B) {
            type = new TypeToken<List<AlphaPowerUp>>(){}.getType();
        } else if (powerType == PowerType.BETA_B) {
            type = new TypeToken<List<BetaPowerUp>>(){}.getType();
        } else if (powerType == PowerType.GAMMA_B) {
            type = new TypeToken<List<GammaPowerUp>>(){}.getType();
        } else if (powerType == PowerType.SIGMA_B) {
            type = new TypeToken<List<SigmaPowerUp>>(){}.getType();
        }
        return new GsonBuilder().setPrettyPrinting().create().fromJson(jsonString, type);
    }

}
