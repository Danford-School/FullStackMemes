//Danford Compton and Stephanie Beagle
//full stack class
//this is a backup for database troubles.
//it will require running an instance of BackupDB and leaving it running.
//This will replace "GetSavedMemes.java" which only saves to an external file.

import java.util.ArrayList;

public class BackupDB {
    //this is the replacement database
    ArrayList<DBUnit> storage = new ArrayList<>();

    //This will add a new user with an empty saved(url) list
    public String addAUser(String name, String password) {
        DBUnit addMe = new DBUnit();
        addMe.name = name;
        addMe.password = password;
        return "User added!";
    }

    //This will save a url for a user. If the user does not exist, it will create a DBUnit for the user and add it.
    public String saveAMeme(String name, String password, String url) {
        DBUnit temp;
        int i = StorageSearch(name, password);
        if(i != -1) {
            //First get the DBUnit from storage
            temp = storage.get(i);
            //Remove the DBUnit to avoid duplicates
            storage.remove(i);
            //add the new url to the DBUnit
            temp.saved.add(url);
            //Re-add the DBUnit to storage
            storage.add(temp);
            return "Success!";
        } else {
            //if it doesn't find the user, it creates a new element and adds it
            temp = new DBUnit();
            temp.name = name;
            temp.password = password;
            temp.saved.add(url);
            storage.add(temp);
            return "Success, new user added!";
        }
    }

    //This will remove a url from a users saved urls. It will not remove the user entirely.
    public String removeAMeme(String name, String password, String url) {
        DBUnit temp;
        int i = StorageSearch(name, password);
        if(i != -1) {
            //First get the DBUnit from storage
            temp = storage.get(i);
            //Remove the DBUnit to avoid duplicates
            storage.remove(i);
            //remove the url from the DBUnit
            if (temp.saved.remove(url)) {
                storage.add(temp);
                return "Success!";
            } else {
                return "The url was not and is still not saved.";
            }
        }
        return "Failed to find user :(";
    }

    //this will take a name and password and return an arraylist of urls that the user saved
    public ArrayList<String> getSavedMemes(String name, String password) {
        DBUnit temp;
        int i = StorageSearch(name, password);
        if(i != -1) {
            temp = storage.get(i);
            return temp.saved;
        }
        return null;
    }

    //This will remove a user. All hail garbage collection!
    public String removeUser(String name, String password) {
        int i = StorageSearch(name, password);
        if(i != -1) {
            storage.remove(i);
            return "User removed!";
        } else {
            return "User not found :(";
        }
    }


    //helper function to search the storage returns the index or -1
    public int StorageSearch(String name, String password) {
        for(int i = 0; i < storage.size(); i++) {
            if (storage.get(i).name.equals(name) && storage.get(i).password.equals(password)) {
                return i;
            }
        }
        return -1;
    }
}
