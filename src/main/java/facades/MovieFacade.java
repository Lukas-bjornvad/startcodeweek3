package facades;

import entities.Movie;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MovieFacade {

    private static MovieFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    public MovieFacade() {
    populate();
    }
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MovieFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MovieFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    //TODO Remove/Change this before use
//    public long count(){
//        EntityManager em = emf.createEntityManager();
//        try{
//            long count = (long)em.createQuery("SELECT COUNT(r) FROM RenameMe r").getSingleResult();
//            return count;
//        }finally{  
//            em.close();
//        }}
    
    public List<Movie> findByTitle(String name){
    EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Movie> query = 
                       em.createQuery("Select c from Movie c where c.name = :name",Movie.class);
            query.setParameter("name", name);
            return query.getResultList();
        }finally {
            em.close();
        }
}
        public Movie findByID(long id){
        EntityManager em = emf.createEntityManager();
        try{
             TypedQuery<Movie> query = 
                       em.createQuery("Select m from Movie m where m.id = :id", Movie.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        }finally {
            em.close();
        }
 }
        public List<Movie> allMovies(){
     EntityManager em = emf.createEntityManager();
     
        try{
            TypedQuery<Movie> query = 
                       em.createQuery("Select m from Movie m",Movie.class);
            return query.getResultList();
        }finally {
            em.close();
        }
}
        public Movie addMovie(Movie mov){
    try{    
        EntityManager em = emf.createEntityManager(); 
            em.getTransaction().begin();
            em.persist(mov);
            em.getTransaction().commit();
            
    }catch(Exception ex){
        ex.printStackTrace();
    }return mov;
}
        public int count(){
            return allMovies().size();
        }
        
public void populate(){
    String[] lor = new String[3];
    lor[0]="Cameron";
     lor[1]="Karry";
    lor[2]="Davis";
addMovie(new Movie(1999, "In the deep", lor));
    lor[0]="Kaden";
     lor[1]="Barry";
    lor[2]="Lero";
addMovie(new Movie(1998, "Down under", lor));
    lor[0]="Ladel";
     lor[1]="Jenkins";
    lor[2]="Leroy";
addMovie(new Movie(2015, "Leroy J.", lor));
    lor[0]="Lark";
     lor[1]="Porter";
    lor[2]="Puffin";
addMovie(new Movie(2000, "War of Penguins.", lor));
} 
    }

