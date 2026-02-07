package cubes.main.rest;

public class MessageResponse {

	public int code; //kod greske
	public String message;
	public long time; //vreme kad se desila greska
	
	public MessageResponse() {}
	
	public MessageResponse(int code, String message, long time) {
		super();
		this.code = code;
		this.message = message;
		this.time = time;
	}
	
	
}
