package api.SwaggerTestNG.pojo.request;

public class PostRequestPayloadforStores {
	
	StoresPostPayload p = new StoresPostPayload();
	Services s = new Services();
	
	public StoresPostPayload postReqPayloadStores(String name,String type,String address,String address2,String city,String state,String zip,double lat,double lng,String hours,
										String personname,long personid,String personworkadd) {
	
		p.setName(name);
		p.setType(type);
		p.setAddress(address);
		p.setAddress2(address2);
		p.setCity(city);
		p.setState(state);
		p.setZip(zip);
		p.setLat(lat);
		p.setLng(lng);
		p.setHours(hours);
		
		s.setPersonname(personname);
		s.setPersonid(personid);
		s.setPersonworkadd(personworkadd);
		
		p.setServices(s);
		
		return p;
	}
}
