package dmme.kuvid.domain.database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import dmme.kuvid.domain.Controllers.DomainFactory;
import dmme.kuvid.domain.Controllers.movementHandler;
import dmme.kuvid.domain.GameObjects.Atoms.Atom;
import dmme.kuvid.domain.GameObjects.GameObject;
import dmme.kuvid.domain.GameObjects.Molecules.Molecule;
import dmme.kuvid.domain.GameObjects.Player;
import dmme.kuvid.domain.GameObjects.Powerup.*;
import dmme.kuvid.domain.GameObjects.ReactionBlocker.ReactionBlocker;
import dmme.kuvid.domain.GameObjects.Shooter;
import dmme.kuvid.domain.KUVidGame;
import dmme.kuvid.lib.types.*;
import org.bson.Document;

import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveLoadDatabase implements SaveMode {


    @Override
    public boolean saveGame() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://dmme:dmme@cluster0.mf2pm.mongodb.net/Comp302?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("Comp302");

        MongoCollection<Document> collection = database.getCollection("KuvidCollection");
        Document doc = new Document();

        save(ObjectType.ATOM, AtomType.ALPHA, "atom_alpha", doc);
        save(ObjectType.ATOM, AtomType.BETA, "atom_beta", doc);
        save(ObjectType.ATOM, AtomType.GAMMA, "atom_gamma", doc);
        save(ObjectType.ATOM, AtomType.SIGMA, "atom_sigma", doc);

        save(ObjectType.MOLECULE, MoleculeType.ALPHA, "molecule_alpha", doc);
        save(ObjectType.MOLECULE, MoleculeType.BETA, "molecule_beta", doc);
        save(ObjectType.MOLECULE, MoleculeType.GAMMA, "molecule_gamma", doc);
        save(ObjectType.MOLECULE, MoleculeType.SIGMA, "molecule_sigma", doc);

        save(ObjectType.REACTION_BLOCKER, ReactionType.ALPHA_R, "reaction_alpha", doc);
        save(ObjectType.REACTION_BLOCKER, ReactionType.BETA_R, "reaction_beta", doc);
        save(ObjectType.REACTION_BLOCKER, ReactionType.GAMMA_R, "reaction_gamma", doc);
        save(ObjectType.REACTION_BLOCKER, ReactionType.SIGMA_R, "reaction_sigma", doc);

        save(ObjectType.POWER_UP, PowerType.ALPHA_B, "power_alpha", doc);
        save(ObjectType.POWER_UP, PowerType.BETA_B, "power_beta", doc);
        save(ObjectType.POWER_UP, PowerType.GAMMA_B, "power_gamma", doc);
        save(ObjectType.POWER_UP, PowerType.SIGMA_B, "power_sigma", doc);

        save("shootedAtom", doc);
        save("shootedPower", doc);

        save("power_arsenal_alpha", doc);
        save("power_arsenal_beta", doc);
        save("power_arsenal_gamma", doc);
        save("power_arsenal_sigma", doc);

        save("movementHandler", doc);
        save("player", doc);
        save("shooter", doc);

        
        collection.insertOne(doc);
        
        return false;
    }

    @Override
    public boolean loadGame() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://dmme:dmme@cluster0.mf2pm.mongodb.net/Comp302?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("Comp302");

        MongoCollection<Document> collection = database.getCollection("KuvidCollection");

        
        Document doc = collection.find().sort(new Document("_id", -1)).first();

        load("shooter", doc);

        load("atom_alpha", doc);
        load("atom_beta", doc);
        load("atom_gamma", doc);
        load("atom_sigma", doc);
        load("shootedAtom", doc);
        load("shootedPower", doc);

        load("power_arsenal_alpha", doc);
        load("power_arsenal_beta", doc);
        load("power_arsenal_gamma", doc);
        load("power_arsenal_sigma", doc);


        load("molecule_alpha", doc);
        load("molecule_beta", doc);
        load("molecule_gamma", doc);
        load("molecule_sigma", doc);

        load("movementHandler", doc);


        load("reaction_alpha", doc);
        load("reaction_beta", doc);
        load("reaction_gamma", doc);
        load("reaction_sigma", doc);

        load("power_alpha", doc);
        load("power_beta", doc);
        load("power_gamma", doc);
        load("power_sigma", doc);

        return false;
    }


    public void save(ObjectType objectType, Enum<?> subType, String name, Document doc) {
        doc.append(name, gameObjectToJson(objectType, subType));
    }

    public void save(String name, Document doc) {


        /*
        // finding the document: 1st way
        Document my_doc = collection.find().first();
        System.out.println(my_doc.toJson()); // printing whole document
        System.out.println(my_doc.get("username")); // pringint only the username

        // 2nd way: by user
        my_doc = collection.find(eq("username", "u1")).first();
        System.out.println(my_doc.toJson()); // printing whole document
        my_doc = collection.find(eq("username", "u2")).first(); // since there is no user with u2 username the result will be empy

        try {
            System.out.println(my_doc.toJson()); // printing whole document
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        */

        switch (name) {
            case "shootedAtom":
                doc.append(name, this.shootedAtomToJson());
                break;
            case "shootedPower":
                doc.append(name, this.shootedPowerToJson());
                break;
            case "power_arsenal_alpha":
                doc.append(name, this.powerArsenalToJson(PowerType.ALPHA_B));
                break;
            case "power_arsenal_beta":
                doc.append(name, this.powerArsenalToJson(PowerType.BETA_B));
                break;
            case "power_arsenal_gamma":
                doc.append(name, this.powerArsenalToJson(PowerType.GAMMA_B));
                break;
            case "power_arsenal_sigma":
                doc.append(name, this.powerArsenalToJson(PowerType.SIGMA_B));
                break;
            case "movementHandler":
                doc.append(name, this.handlerToJson());
                break;
            case "player":
                doc.append(name, this.playerToJson());
                break;
            case "shooter":
                doc.append(name, this.shooterToJson());
                break;
        }
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
        if (objectType.equals(ObjectType.MOLECULE)) {
            Type type = new TypeToken<List<Molecule>>() {
            }.getType();
            return new GsonBuilder().registerTypeAdapter(Molecule.class, new AbstractMoleculeAdapter()).setPrettyPrinting().create()
                    .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)), type);
        } else if (objectType.equals(ObjectType.REACTION_BLOCKER)) {
            Type type = new TypeToken<List<ReactionBlocker>>() {
            }.getType();
            return new GsonBuilder().registerTypeAdapter(ReactionBlocker.class, new AbstractBlockerAdapter()).setPrettyPrinting().create()
                    .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)), type);
        } else if (objectType.equals(ObjectType.POWER_UP)) {
            Type type = new TypeToken<List<PowerUp>>() {
            }.getType();
            return new GsonBuilder().registerTypeAdapter(PowerUp.class, new AbstractPowerAdapter()).setPrettyPrinting().create()
                    .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)), type);
        }
        Type type = new TypeToken<List<Atom>>() {
        }.getType();
        return new GsonBuilder().registerTypeAdapter(Atom.class, new AbstractAtomAdapter()).setPrettyPrinting().create()
                .toJson(KUVidGame.getGameObjectMap().get(new Key(objectType, subType)), type);
    }

    public String shootedAtomToJson() {
        /*
        @requires: this not null, objectType not null, subType not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
        Gson son = new GsonBuilder().registerTypeAdapter(Atom.class, new AbstractAtomAdapter()).setPrettyPrinting().create();
        Type type = new TypeToken<List<Atom>>() {
        }.getType();

        return son.toJson(KUVidGame.getShootedAtom(), type);
    }

    public String shootedPowerToJson() {
        /*
        @requires: this not null, objectType not null, subType not null
        @effects: Generates a JSON string from properties of the given specified List of GameObjects.
            Returns generated String.
         */
        Gson son = new GsonBuilder().registerTypeAdapter(PowerUp.class, new AbstractPowerAdapter()).setPrettyPrinting().create();
        Type type = new TypeToken<List<PowerUp>>() {
        }.getType();
        return son.toJson(KUVidGame.getShootedPower(), type);
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

    public void load(String fileName, Document doc) {

        List<GameObject> list;
        List<PowerUp> powerUpList;
        try {

            switch (fileName) {
                case "atom_alpha":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.ATOM, AtomType.ALPHA);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "atom_beta":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.ATOM, AtomType.BETA);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "atom_gamma":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.ATOM, AtomType.GAMMA);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "atom_sigma":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.ATOM, AtomType.SIGMA);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "molecule_alpha":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.MOLECULE, MoleculeType.ALPHA);
                    list.forEach(n -> DomainFactory.getInstance().addMolecule(n));
                    break;
                case "molecule_beta":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.MOLECULE, MoleculeType.BETA);
                    list.forEach(n -> DomainFactory.getInstance().addMolecule(n));
                    break;
                case "molecule_gamma":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.MOLECULE, MoleculeType.GAMMA);
                    list.forEach(n -> DomainFactory.getInstance().addMolecule(n));
                    break;
                case "molecule_sigma":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.MOLECULE, MoleculeType.SIGMA);
                    list.forEach(n -> DomainFactory.getInstance().addMolecule(n));
                    break;
                case "shootedAtom":
                    list = this.jsonToList(doc.get(fileName).toString(), ObjectType.ATOM);
                    list.forEach(n -> DomainFactory.getInstance().addAtom(n));
                    break;
                case "shootedPower":
                    list = this.jsonToList(doc.get(fileName).toString(), ObjectType.POWER_UP);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_arsenal_alpha":
                    powerUpList = jsonToPowerUp(doc.get(fileName).toString(), PowerType.ALPHA_B);
                    powerUpList.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_arsenal_beta":
                    powerUpList = jsonToPowerUp(doc.get(fileName).toString(), PowerType.BETA_B);
                    powerUpList.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_arsenal_gamma":
                    powerUpList = jsonToPowerUp(doc.get(fileName).toString(), PowerType.GAMMA_B);
                    powerUpList.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_arsenal_sigma":
                    powerUpList = jsonToPowerUp(doc.get(fileName).toString(), PowerType.SIGMA_B);
                    powerUpList.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "movementHandler":
                    jsonToHandler(doc.get(fileName).toString());
                    break;
                case "player":
                    jsonToPlayer(doc.get(fileName).toString());
                    break;
                case "shooter":
                    jsonToShooter(doc.get(fileName).toString());
                    break;
                case "reaction_alpha":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.REACTION_BLOCKER, ReactionType.ALPHA_R);
                    list.forEach(n -> DomainFactory.getInstance().addBlocker(n));
                    break;
                case "reaction_beta":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.REACTION_BLOCKER, ReactionType.BETA_R);
                    list.forEach(n -> DomainFactory.getInstance().addBlocker(n));
                    break;
                case "reaction_gamma":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.REACTION_BLOCKER, ReactionType.GAMMA_R);
                    list.forEach(n -> DomainFactory.getInstance().addBlocker(n));
                    break;
                case "reaction_sigma":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.REACTION_BLOCKER, ReactionType.SIGMA_R);
                    list.forEach(n -> DomainFactory.getInstance().addBlocker(n));
                    break;
                case "power_alpha":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.POWER_UP, PowerType.ALPHA_B);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_gamma":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.POWER_UP, PowerType.GAMMA_B);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_beta":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.POWER_UP, PowerType.BETA_B);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
                case "power_sigma":
                    list = jsonToGameObject(doc.get(fileName).toString(), ObjectType.POWER_UP, PowerType.SIGMA_B);
                    list.forEach(n -> DomainFactory.getInstance().addPowerUp(n));
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }


    private void jsonToShooter(String outputData) {
        Shooter savedInst = new GsonBuilder().setPrettyPrinting().create().fromJson(outputData, Shooter.class);
        KUVidGame.getInstance().setShooter(savedInst);
    }

    private void jsonToPlayer(String outputData) {
        Player savedInst = new GsonBuilder().setPrettyPrinting().create().fromJson(outputData, Player.class);
        Player.setInstance(savedInst);
    }

    private void jsonToHandler(String outputData) {
        movementHandler savedInst = new GsonBuilder().setPrettyPrinting().create().fromJson(outputData, movementHandler.class);
        movementHandler.setInstance(savedInst);
    }

    public List<GameObject> jsonToList(String jsonString, ObjectType t) {
        /*
        @requires: this not null, objectType not null, subType not null, jsonString not null
        @effects: Generates a List object from properties of the given jsonString.
            Returns generated object as a GameObject List.
         */
        Gson son = null;
        Type type = null;
        if (t.equals(ObjectType.ATOM)) {
            son = new GsonBuilder().registerTypeAdapter(Atom.class, new AbstractAtomAdapter()).setPrettyPrinting().create();
            type = new TypeToken<List<Atom>>() {
            }.getType();
        } else if (t.equals(ObjectType.POWER_UP)) {
            son = new GsonBuilder().registerTypeAdapter(PowerUp.class, new AbstractPowerAdapter()).setPrettyPrinting().create();
            type = new TypeToken<List<PowerUp>>() {
            }.getType();
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (objectType == ObjectType.ATOM) {
            type = new TypeToken<List<Atom>>() {
            }.getType();
            gson = new GsonBuilder().registerTypeAdapter(Atom.class, new AbstractAtomAdapter()).setPrettyPrinting().create();
        } else if (objectType == ObjectType.MOLECULE) {
            type = new TypeToken<List<Molecule>>() {
            }.getType();
            gson = new GsonBuilder().registerTypeAdapter(Molecule.class, new AbstractMoleculeAdapter()).setPrettyPrinting().create();
        } else if (objectType == ObjectType.REACTION_BLOCKER) {
            type = new TypeToken<List<ReactionBlocker>>() {
            }.getType();
            gson = new GsonBuilder().registerTypeAdapter(ReactionBlocker.class, new AbstractBlockerAdapter()).setPrettyPrinting().create();
        } else if (objectType == ObjectType.POWER_UP) {
            type = new TypeToken<List<PowerUp>>() {
            }.getType();
            gson = new GsonBuilder().registerTypeAdapter(PowerUp.class, new AbstractPowerAdapter()).setPrettyPrinting().create();
        }
        return gson.fromJson(jsonString, type);
    }

    private List<PowerUp> jsonToPowerUp(String jsonString, PowerType powerType) {
        Type type = null;
        if (powerType == PowerType.ALPHA_B) {
            type = new TypeToken<List<AlphaPowerUp>>() {
            }.getType();
        } else if (powerType == PowerType.BETA_B) {
            type = new TypeToken<List<BetaPowerUp>>() {
            }.getType();
        } else if (powerType == PowerType.GAMMA_B) {
            type = new TypeToken<List<GammaPowerUp>>() {
            }.getType();
        } else if (powerType == PowerType.SIGMA_B) {
            type = new TypeToken<List<SigmaPowerUp>>() {
            }.getType();
        }
        return new GsonBuilder().setPrettyPrinting().create().fromJson(jsonString, type);
    }

    @Override
    public boolean loadInit() {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);

        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://dmme:dmme@cluster0.mf2pm.mongodb.net/Comp302?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("Comp302");

        MongoCollection<Document> collection = database.getCollection("KuvidCollection");

        //Document doc = collection.find().first();
        Document doc = collection.find().sort(new Document("_id", -1)).first();

        load("player", doc);
        return false;
    }
}
