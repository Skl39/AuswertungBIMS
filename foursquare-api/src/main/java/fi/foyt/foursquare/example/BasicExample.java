package fi.foyt.foursquare.example;

import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.Keyword;
import fi.foyt.foursquare.api.entities.RecommendationGroup;
import fi.foyt.foursquare.api.entities.Recommended;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

/**
 * Basic search example
 * @TODO - more examples please :)
 * @author rmangi
 *
 */
/**
 * @author Sebastian
 *
 */
public class BasicExample {

	  public static void main(String[] args) {
	    String ll = args.length > 0 ? args[0] : "51.96236,7.62571";
	    try {
	      (new BasicExample()).exploreVenues();
	    } catch (FoursquareApiException e) {
	    }
	  }

	
	public void exploreVenues() throws FoursquareApiException {
	    // First we need a initialize FoursquareApi. 
	    FoursquareApi foursquareApi = new FoursquareApi(
	    				"OSFIUBV4NKB2VIVCQKIHBA4HS3VDMEWT5WHAKAWO5Q1WGL14", 
	    				"YDXX05UPRTBIE5DGPYQROYB5UL02ASXQY0XCOPQGFXAGZP5Y", 
	    				"http://localhost:8080/FoursquareMS/Controller");
	    
	    /*
	     * https://api.foursquare.com/v2/venues/explore
			  ?client_id=OSFIUBV4NKB2VIVCQKIHBA4HS3VDMEWT5WHAKAWO5Q1WGL14
			  &client_secret=YDXX05UPRTBIE5DGPYQROYB5UL02ASXQY0XCOPQGFXAGZP5Y
			  &v=20130815
			  &near=Münster,NRW
			  &radius=5000
			  &sortByDistance=1
  	     */
	    HashMap<String, String> params = new HashMap<String, String>();
	 //   params.put("client_id", "OSFIUBV4NKB2VIVCQKIHBA4HS3VDMEWT5WHAKAWO5Q1WGL14");
	//    params.put("client_secret", "YDXX05UPRTBIE5DGPYQROYB5UL02ASXQY0XCOPQGFXAGZP5Y");
	//    params.put("v", "20130815");
	    params.put("near", "Münster,NRW");
	    params.put("radius", "5000");
	    params.put("sortByDistance", "1");
	    
	    // After client has been initialized we can make queries.
	    Result<VenuesSearchResult> r1 = foursquareApi.venuesSearch(params);
	    
	    if (r1.getMeta().getCode() == 200) { // Response = OK 

	    	
	    	for (CompactVenue venue : r1.getResult().getVenues()) {
	        	 	
//	        	 System.out.println("ID: " + venue.getId()+ " Name: " +  venue.getName() + " Adresse: "  
//	        			 + venue.getLocation().getAddress() + " PLZ:"
//	        			 + venue.getLocation().getPostalCode() 
//	        			 + " Rating:" + venue.getRating() );
//	        

	    		
	    	System.out.println("Kategorien:");
	        Category[] kategorien = venue.getCategories();
	        for(Category k : kategorien){
	        	System.out.println(k.getId() +" " + k.getName() +" " + k.getParents() + " " +k.getPluralName() + " " + k.getPrimary());
	        }
	        // Orte
	           	 System.out.println(venue.getId()+ ";" +  venue.getName() + ";"  
	 	        			 + venue.getLocation().getAddress() + ";"
	 	        			 + venue.getLocation().getPostalCode() 
	 	        			 + ";" + venue.getRating() );
	           	 
	      }
	    } else {
	      // TODO: Proper error handling
	      System.out.println("Error occured: ");
	      System.out.println("  code: " + r1.getMeta().getCode());
	      System.out.println("  type: " + r1.getMeta().getErrorType());
	      System.out.println("  detail: " + r1.getMeta().getErrorDetail()); 
	    }
	    
	  }
	
  private void searchVenues(String ll) throws FoursquareApiException {
		
		  
		  FoursquareApi foursquareApi = new FoursquareApi(
  				"OSFIUBV4NKB2VIVCQKIHBA4HS3VDMEWT5WHAKAWO5Q1WGL14", 
  				"YDXX05UPRTBIE5DGPYQROYB5UL02ASXQY0XCOPQGFXAGZP5Y", 
  				"http://localhost:8080/FoursquareMS/Controller");
		    Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll, null, null, null, null, null, null, null, null, null, null, null, null);
		   
		    System.out.println(result.getMeta().getCode());
		    if (result.getMeta().getCode() == 200) {
		      // if query was ok we can finally we do something with the data
		         for (CompactVenue venue : result.getResult().getVenues()) {
		    
		        	 	System.out.println(venue.getName());
		        
		      }
		    } else {
		      // TODO: Proper error handling
		      System.out.println("Error occured: ");
		      System.out.println("  code: " + result.getMeta().getCode());
		      System.out.println("  type: " + result.getMeta().getErrorType());
		      System.out.println("  detail: " + result.getMeta().getErrorDetail()); 
		    }
		  
		  
		
	}

  
  
  
  
	  
}
