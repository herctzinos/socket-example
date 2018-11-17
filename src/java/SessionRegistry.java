
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.websocket.Session;

/**
 *
 * @author Spyros
 */
@Singleton
public class SessionRegistry {

    private Set<Session> sessions = new HashSet<>();
//Concurrency lock type for singleton beans with container-managed concurrency. 
//Annotate a singleton’s business or timeout method with @Lock(READ) if the method
//can be concurrently accessed, or shared, with many clients. 
//Annotate the business or timeout method with @Lock(WRITE) 
//if the singleton session bean should be locked to other clients while a client is calling that method. 
    //Typically,
//the @Lock(WRITE) annotation is used when clients are 
//modifying the state of the singleton.

    @Lock(LockType.READ)
    public Set<Session> getAll() {
        return Collections.unmodifiableSet(sessions);
    }

    @Lock(LockType.WRITE)
    public void add(Session session) {
        sessions.add(session);
    }

    @Lock(LockType.WRITE)
    public void remove(Session session) {
        sessions.remove(session);
    }

    public int getPeople() {
        return sessions.size();
    }
}