package api_POJO;

public class API_Pojo_ProgBatch {
	private int batchId;
	private String batchDescription;
	private String batchName;
	private int batchNoOfClasses;
	private String batchStatus;
	private int programId;
	private String programName;

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int id) {
		this.batchId = id;
	}

	public String getBatchDescription() {
		return batchDescription;
	}

	public void setBatchDescription(String desc) {
		this.batchDescription = desc;
	}
	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public int getBatchNoOfClasses() {
		return batchNoOfClasses;
	}

	public void setBatchNoOfClasses(int batchNoOfClasses) {
		this.batchNoOfClasses = batchNoOfClasses;
	}

	public String getBatchStatus() {
		return batchStatus;
	}

	public void setBatchStatus(String batchStatus) {
		this.batchStatus = batchStatus;
	}

	public int getProgramId() {
		return programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

}
