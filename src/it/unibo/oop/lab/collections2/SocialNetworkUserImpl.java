package it.unibo.oop.lab.collections2;

import java.util.*;

/**
 * 
 * Instructions
 * 
 * This will be an implementation of
 * {@link it.unibo.oop.lab.collections2.SocialNetworkUser}:
 * 
 * 1) complete the definition of the methods by following the suggestions
 * included in the comments below.
 * 
 * @param <U>
 *            Specific user type
 */
public class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    /*
     * [FIELDS]
     * 
     * Define any necessary field
     * 
     * In order to save the people followed by a user organized in groups, adopt
     * a generic-type Map:
     * 
     * think of what type of keys and values would best suit the requirements
     */
	
	private final Map<String, Set<U>> followedMap;

    /*
     * [CONSTRUCTORS]
     * 
     * 1) Complete the definition of the constructor below, for building a user
     * participating in a social network, with 4 parameters, initializing:
     * 
     * - firstName - lastName - user name - age and every other necessary field
     * 
     * 2) Define a further constructor where age is defaulted to -1
     */
	
    /**
     * Builds a new {@link SocialNetworkUserImpl}.
     * 
     * @param name
     *            the user first name
     * @param surname
     *            the user last name
     * @param userAge
     *            user's age
     * @param user
     *            alias of the user, i.e. the way a user is identified on an
     *            application
     */
    public SocialNetworkUserImpl(final String name, final String surname, final String user, final int userAge) {
        super(name, surname, user, userAge);
        this.followedMap = new HashMap<String, Set<U>>();
    }

    public SocialNetworkUserImpl(final String name, final String surname, final String user) {
    	this(name, surname, user, -1);
    }
    
    /*
     * [METHODS]
     * 
     * Implements the methods below
     */

    @Override
    public boolean addFollowedUser(final String circle, final U user) {
    	if (!this.followedMap.containsKey(circle)) {
    		this.followedMap.put(circle, new HashSet<U>());
    	}
    	// add the person only if he/she is not already followed
    	return this.followedMap.get(circle).add(user);
    }

    @Override
    public Collection<U> getFollowedUsersInGroup(final String groupName) {
    	final var usersInGroup = new HashSet<U>();
    	if (this.followedMap.containsKey(groupName)) {
    		usersInGroup.addAll(this.followedMap.get(groupName));
        }
        return usersInGroup;
    }

    @Override
    public List<U> getFollowedUsers() {
        final var allUsers = new LinkedList<U>();
        final var groups = this.followedMap.entrySet();
        final var iterator = groups.iterator();
        while (iterator.hasNext()) {
        	// assuming every person can be in only one group of friends (or there might be duplicates)
        	allUsers.addAll(iterator.next().getValue());  
        }
        return allUsers;
    }

}
