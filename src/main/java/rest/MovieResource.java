package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Movie;
import utils.EMF_Creator;
import facades.MovieFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Todo Remove or change relevant parts before ACTUAL use
@Path("movie")
public class MovieResource {

    //MovieFacade movs = new MovieFacade();
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/startcode",
            "dev",
            "ax2",
            EMF_Creator.Strategy.DROP_AND_CREATE);
    private static final MovieFacade FACADE = MovieFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public MovieResource() {
        //  FACADE.populate();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @Path("/title/{title}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getByTitle(@PathParam("title") String name) {
        if (!FACADE.findByTitle(name).isEmpty()) {
            return GSON.toJson(FACADE.findByTitle(name).get(0));
        } else {
            return "Something went wrong with the fetching";
        }
    }

//    @POST
//    @Consumes({MediaType.APPLICATION_JSON})
//    public void create(Movie entity) {
//        throw new UnsupportedOperationException();
//    }
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllEmployeesList() {
        return GSON.toJson(FACADE.allMovies());
    }

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public int getCount() {
        return FACADE.count();
    }

    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getById(@PathParam("id") long id) {
        Movie mov = FACADE.findByID(id);
        return GSON.toJson(mov);
    }
}
