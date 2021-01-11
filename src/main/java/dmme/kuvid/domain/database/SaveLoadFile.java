package dmme.kuvid.domain.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Molecules.*;
import dmme.kuvid.domain.GameObjects.Powerup.PowerUp;
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
        //save("powerArsenal");

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
            if(name.equals("shootedAtom")) {
            	fileWriter.write(this.shootedAtomToJson());
            }else if(name.equals("shootedPower")) {
            	fileWriter.write(this.shootedPowerToJson());
            }else if(name.equals("powerArsenal")) {
            	fileWriter.write(this.powerArsenalToJson());
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
    
    public String powerArsenalToJson() {
        /*
        @requires: this not null, objectType not null, subType not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
    	Gson son = new GsonBuilder().registerTypeAdapter(PowerUp.class, new AbstractPowerAdapter()).setPrettyPrinting().create();
    	Type type = new TypeToken<List<PowerUp>>(){}.getType();
        return son.toJson(KUVidGame.getPowerArsenal(),type);
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
        try {
            outputData = new String(Files.readAllBytes(Paths.get(pathHandler.makePath(f.getAbsolutePath(), fileName) + ".json")));

            if (fileName.contains("atom_alpha")) {
                list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.ALPHA);
                //KUVidGame.getGameObjectMap().put(new Key(ObjectType.ATOM, AtomType.ALPHA), list);
                list.forEach(n -> DomainFactory.getInstance().addAtom(n));

            } else if (fileName.contains("atom_beta")) {
                list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.BETA);
                //KUVidGame.getGameObjectMap().put(new Key(ObjectType.ATOM, AtomType.BETA), list);
                list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                
            } else if (fileName.contains("atom_gamma")) {
                list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.GAMMA);
                //KUVidGame.getGameObjectMap().put(new Key(ObjectType.ATOM, AtomType.GAMMA), list);
                list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                
            } else if (fileName.contains("atom_sigma")) {
                list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.SIGMA);
                //KUVidGame.getGameObjectMap().put(new Key(ObjectType.ATOM, AtomType.SIGMA), list);
                list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                
            } else if (fileName.contains("molecule_alpha")) {
                list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.ALPHA);
                KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.ALPHA), list);
            } else if (fileName.contains("molecule_beta")) {
                list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.BETA);
                KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), list);
            } else if (fileName.contains("molecule_gamma")) {
                list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.GAMMA);
                KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), list);
            } else if (fileName.contains("molecule_sigma")) {
                list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.SIGMA);
                KUVidGame.getGameObjectMap().put(new Key(ObjectType.MOLECULE, MoleculeType.BETA), list);
            } else if (fileName.contains("shootedAtom")) {
            	list =this.jsonToList(outputData,ObjectType.ATOM);
                list.forEach(n -> DomainFactory.getInstance().addAtom(n));
            }else if (fileName.contains("shootedPower")) {
                //list = jsonToGameObject(outputData, ObjectType.ATOM, AtomType.BETA);
                //KUVidGame.getGameObjectMap().put(new Key(ObjectType.ATOM, AtomType.BETA), list);
                //list.forEach(n -> DomainFactory.getInstance().addAtom(n));
            	list =this.jsonToList(outputData,ObjectType.POWER_UP);
                list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
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


}
