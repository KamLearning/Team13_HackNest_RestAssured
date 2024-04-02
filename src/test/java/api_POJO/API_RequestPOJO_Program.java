package api_POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class API_RequestPOJO_Program {

	private String programName;
	private String programDescription;
    private String programStatus;
    
    public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getProgramDescription() {
		return programDescription;
	}
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
	public String getProgramStatus() {
		return programStatus;
	}
	public void setProgramStatus(String programStatus) {
		this.programStatus = programStatus;
	}
	
	@Override
	public String toString() {
		return "{" +
                
                "\"programName\":\"" + programName + "\"" +
                ",\"programDescription\":\"" + programDescription + "\"" +
                ",\"programStatus\":\"" + programStatus + "\"" +
                
                "}";
	}
}
