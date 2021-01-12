package dmme.kuvid.domain.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import dmme.kuvid.domain.GameObjects.Atoms.*;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Player;
import dmme.kuvid.domain.GameObjects.Shooter;
import dmme.kuvid.domain.GameObjects.Molecules.*;
import dmme.kuvid.domain.GameObjects.Powerup.*;
import dmme.kuvid.domain.GameObjects.ReactionBlocker.ReactionBlocker;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.Controllers.movementHandler;
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
        
        save(ObjectType.POWER_UP, PowerType.ALPHA_B, "power_alpha");
        save(ObjectType.POWER_UP, PowerType.BETA_B, "power_beta");
        save(ObjectType.POWER_UP, PowerType.GAMMA_B, "power_gamma");
        save(ObjectType.POWER_UP, PowerType.SIGMA_B, "power_sigma");
        
        save("shootedAtom");
        save("shootedPower");

        save("power_arsenal_alpha");
        save("power_arsenal_beta");
        save("power_arsenal_gamma");
        save("power_arsenal_sigma");

        save("movementHandler");
        save("player");
        save("shooter");
        return false;
    }

    @Override
    public boolean loadGame() {
    	load("player");
    	load("shooter");
    	
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


        load("molecule_alpha");
        load("molecule_beta");
        load("molecule_gamma");
        load("molecule_sigma");
        
        load("movementHandler");
        
        
        
        load("reaction_alpha");
        load("reaction_beta");
        load("reaction_gamma");
        load("reaction_sigma");
        
        load("power_alpha");
        load("power_beta");
        load("power_gamma");
        load("power_sigma");

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
                case "movementHandler":
                	fileWriter.write(this.handlerToJson());
                    break;
                case "player":
                	fileWriter.write(this.playerToJson());
                    break;
                case "shooter":
                	fileWriter.write(this.shooterToJson());
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

    public String shooterToJson() {
		// TODO Auto-generated method stub
    	return new GsonBuilder().setPrettyPrinting().create().toJson(KUVidGame.getInstance().getShooter(), Shooter.class);
	}

	public String playerToJson() {
		// TODO Auto-generated method stub
		return new GsonBuilder().setPrettyPrinting().create().toJson(Player.getInstance(), Player.class);
	}

	public String handlerToJson() {
		// TODO Auto-generated method stub
		return new GsonBuilder().setPrettyPrinting().create().toJson(movementHandler.getInstance(), movementHandler.class);
	}

	public String gameObjectToJson(ObjectType objectType, Enum<?> subType) {
        /*
        @requires: this not null, objectType not null, subType not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
    	if(objectType.equals(ObjectType.MOLECULE)) {
    		Type type = new TypeToken<List<Molecule>>(){}.getType();
    		return new GsonBuilder().registerTypeAdapter(Molecule.class, new AbstractMoleculeAdapter()).setPrettyPrinting().create()
                    .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)),type);
    	}else if(objectType.equals(ObjectType.REACTION_BLOCKER)) {
    		Type type = new TypeToken<List<ReactionBlocker>>(){}.getType();
    		return new GsonBuilder().registerTypeAdapter(ReactionBlocker.class, new AbstractBlockerAdapter()).setPrettyPrinting().create()
                    .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)),type);
    	}else if(objectType.equals(ObjectType.POWER_UP)) {
	    	Type type = new TypeToken<List<PowerUp>>(){}.getType();
	        return new GsonBuilder().registerTypeAdapter(PowerUp.class, new AbstractPowerAdapter()).setPrettyPrinting().create()
	                .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)),type);
    	}
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
                    list.forEach(n -> DomainFactory.getInstance().addMolecule(n));
                    break;
                case "molecule_beta":
                    list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.BETA);
                    list.forEach(n -> DomainFactory.getInstance().addMolecule(n));
                    break;
                case "molecule_gamma":
                    list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.GAMMA);
                    list.forEach(n -> DomainFactory.getInstance().addMolecule(n));
                    break;
                case "molecule_sigma":
                    list = jsonToGameObject(outputData, ObjectType.MOLECULE, MoleculeType.SIGMA);
                    list.forEach(n -> DomainFactory.getInstance().addMolecule(n));
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
                case "movementHandler":
                	jsonToHandler(outputData);
                	break;
                case "player":
                	jsonToPlayer(outputData);
                	break;
                case "shooter":
                	jsonToShooter(outputData);
                	break;
                case "reaction_alpha":
                	list = jsonToGameObject(outputData, ObjectType.REACTION_BLOCKER, ReactionType.ALPHA_R);
                    list.forEach(n -> DomainFactory.getInstance().addBlocker(n));
                    break;
                case "reaction_beta":
                	list = jsonToGameObject(outputData, ObjectType.REACTION_BLOCKER, ReactionType.BETA_R);
                    list.forEach(n -> DomainFactory.getInstance().addBlocker(n));
                    break;
                case "reaction_gamma":
                	list = jsonToGameObject(outputData, ObjectType.REACTION_BLOCKER, ReactionType.GAMMA_R);
                    list.forEach(n -> DomainFactory.getInstance().addBlocker(n));
                    break;
                case "reaction_sigma":
                	list = jsonToGameObject(outputData, ObjectType.REACTION_BLOCKER, ReactionType.SIGMA_R);
                    list.forEach(n -> DomainFactory.getInstance().addBlocker(n));
                    break;
                case "power_alpha":
                	list = jsonToGameObject(outputData, ObjectType.POWER_UP, PowerType.ALPHA_B);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_gamma":
                	list = jsonToGameObject(outputData, ObjectType.POWER_UP, PowerType.GAMMA_B);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_beta":
                	list = jsonToGameObject(outputData, ObjectType.POWER_UP, PowerType.BETA_B);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_sigma":
                	list = jsonToGameObject(outputData, ObjectType.POWER_UP, PowerType.SIGMA_B);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
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



    private void jsonToShooter(String outputData) {
		// TODO Auto-generated method stub
    	Shooter savedInst=new GsonBuilder().setPrettyPrinting().create().fromJson(outputData, Shooter.class);
		KUVidGame.getInstance().setShooter(savedInst);
	}

	private void jsonToPlayer(String outputData) {
		// TODO Auto-generated method stub
    	Player savedInst=new GsonBuilder().setPrettyPrinting().create().fromJson(outputData, Player.class);
		Player.setInstance(savedInst);
	}

	private void jsonToHandler(String outputData) {
		// TODO Auto-generated method stub
		movementHandler savedInst=new GsonBuilder().setPrettyPrinting().create().fromJson(outputData, movementHandler.class);
		movementHandler.setInstance(savedInst);
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
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
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
            type = new TypeToken<List<Molecule>>(){}.getType();
            gson=new GsonBuilder().registerTypeAdapter(Molecule.class, new AbstractMoleculeAdapter()).setPrettyPrinting().create();
        }else if (objectType == ObjectType.REACTION_BLOCKER) {
            type = new TypeToken<List<ReactionBlocker>>(){}.getType();
            gson=new GsonBuilder().registerTypeAdapter(ReactionBlocker.class, new AbstractBlockerAdapter()).setPrettyPrinting().create();
        }else if (objectType == ObjectType.POWER_UP) {
            type = new TypeToken<List<PowerUp>>(){}.getType();
            gson=new GsonBuilder().registerTypeAdapter(PowerUp.class, new AbstractPowerAdapter()).setPrettyPrinting().create();
        }
        return gson.fromJson(jsonString, type);
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
