/*import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobInfo;
import com.google.appengine.api.blobstore.BlobInfoFactory;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class uploadPhoto extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
    	  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
         
    	 UserService userService = UserServiceFactory.getUserService(); 
    	 ImagesService img = ImagesServiceFactory.getImagesService();
    	
    	 
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("idPhoto");
        
        
          

        if (blobKeys == null || blobKeys.isEmpty()) {
            res.sendRedirect("/");
        } else {
         
       

        // Stocke l'utilisateur
        
        Entity user = new Entity("User");

        user.setProperty("name", userService.getCurrentUser().getNickname());
        user.setProperty("idPhoto",blobKeys.get(0).getKeyString());
        user.setProperty("urlPhoto",img.getServingUrl(ServingUrlOptions.Builder.withBlobKey(blobKeys.get(0)) ));
        user.setProperty("date", new Date());

        datastore.put(user);
        
       // res.sendRedirect("/");
        res.sendRedirect("/");
        }
    }
}*/

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class uploadPhoto extends HttpServlet {
	UserService userService = UserServiceFactory.getUserService(); 

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

 		 Query q = new Query("Users");
         List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
         this.getServletContext().getRequestDispatcher("/pageUser.jsp").forward(req, resp);

		for (Entity user : results) {
 		
		 if (user.getProperty("userR").equals(req.getParameter("nom"))) {
			 resp.sendRedirect("/pageUser.jsp");
          }

		 }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) {	
        try {
            DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
          
            Entity user = new Entity("Users");

            user.setProperty("nom",req.getParameter("nom"));
            user.setProperty("mail", userService.getCurrentUser().getNickname());
            user.setProperty("urlPhoto",req.getParameter("idPhoto"));
            user.setProperty("message", req.getParameter("message"));
            user.setProperty("date", new Date());

            datastore.put(user);

            resp.sendRedirect("/affichagePhoto.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
