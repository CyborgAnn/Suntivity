package suntivity_model;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Represents the central data model for the Suntivity application.
 *
 * <p>The Model class manages collections of accounts, parent accounts,
 * store items, and plant statuses. It provides methods for adding, removing,
 * and retrieving application data.</p>
 */
public class Model {

    /** A collection of parent accounts stored in the application. */
    private HashSet<ParentAccount> parentAccounts;

    /** A list of items available in the store. */
    private ArrayList<Item> storeItems;

    /** A list of available plant statuses. */
    private ArrayList<PlantStatus> plantStatuses;

    /** A list containing all user accounts. */
    private ArrayList<Account> accounts;

    /**
     * Creates a new Model with empty collections for accounts, store items,
     * and plant statuses.
     */
    public Model() {
        parentAccounts = new HashSet<>();
        storeItems = new ArrayList<>();
        plantStatuses = new ArrayList<>();
        accounts = new ArrayList<>();
    }

    // Accounts

    /**
     * Adds an account to the application.
     *
     * <p>If the account is a ParentAccount, it is also added to the collection
     * of parent accounts for linking code searches.</p>
     *
     * @param account the account to add
     */
    public void addAccount(Account account) {
        accounts.add(account);

        if (account instanceof ParentAccount) {
            parentAccounts.add((ParentAccount) account);
        }
    }

    /**
     * Removes an account from the application.
     *
     * <p>If the account is a ParentAccount, it is also removed from the
     * collection of parent accounts.</p>
     *
     * @param account the account to remove
     */
    public void removeAccount(Account account) {
        accounts.remove(account);

        if (account instanceof ParentAccount) {
            parentAccounts.remove((ParentAccount) account);
        }
    }

    /**
     * Retrieves all accounts stored in the application.
     *
     * @return a list of all accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    /**
     * Searches for an account by username.
     *
     * @param username the username to search for
     * @return the matching account, or {@code null} if no account is found
     */
    public Account getAccountByUsername(String username) {

        for (Account account : accounts) {
            if (account.getUserName().equals(username)) {
                return account;
            }
        }

        return null;
    }

    // Parent Accounts

    /**
     * Adds a parent account to the collection of parent accounts.
     *
     * @param parent the parent account to add
     */
    public void addParentAccount(ParentAccount parent) {
        parentAccounts.add(parent);
    }

    /**
     * Removes a parent account from the collection of parent accounts.
     *
     * @param parent the parent account to remove
     */
    public void removeParentAccount(ParentAccount parent) {
        parentAccounts.remove(parent);
    }

    /**
     * Retrieves all parent accounts stored in the application.
     *
     * @return a set of parent accounts
     */
    public HashSet<ParentAccount> getParentAccounts() {
        return parentAccounts;
    }

    /**
     * Searches for a parent account using a linking code.
     *
     * @param code the linking code associated with the parent account
     * @return the matching parent account, or {@code null} if no match exists
     */
    public ParentAccount getParentByLinkingCode(int code) {

        for (ParentAccount parent : parentAccounts) {
            if (parent.getLinkingCode() == code) {
                return parent;
            }
        }

        return null;
    }

    // Store Items

    /**
     * Adds an item to the store inventory.
     *
     * @param item the item to add
     */
    public void addStoreItem(Item item) {
        storeItems.add(item);
    }

    /**
     * Removes an item from the store inventory.
     *
     * @param item the item to remove
     */
    public void removeStoreItem(Item item) {
        storeItems.remove(item);
    }

    /**
     * Retrieves all items available in the store.
     *
     * @return a list of store items
     */
    public ArrayList<Item> getStoreItems() {
        return storeItems;
    }

    /**
     * Searches for a store item by name.
     *
     * @param name the item name to search for
     * @return the matching item, or {@code null} if no item is found
     */
    public Item getStoreItem(String name) {

        for (Item item : storeItems) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }

        return null;
    }

    // Plant Statuses

    /**
     * Adds a plant status to the collection of available statuses.
     *
     * @param status the plant status to add
     */
    public void addPlantStatus(PlantStatus status) {
        plantStatuses.add(status);
    }

    /**
     * Retrieves all available plant statuses.
     *
     * @return a list of plant statuses
     */
    public ArrayList<PlantStatus> getPlantStatuses() {
        return plantStatuses;
    }

    /**
     * Searches for a plant status by name.
     *
     * @param name the plant status name to search for
     * @return the matching plant status, or {@code null} if no status is found
     */
    public PlantStatus getPlantStatus(String name) {

        for (PlantStatus status : plantStatuses) {
            if (status.getName().equalsIgnoreCase(name)) {
                return status;
            }
        }

        return null;
    }
}
